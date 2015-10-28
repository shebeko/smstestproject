сборка проекта: 

1) из корня выполнить npm install (чтобы подтянуть все необходимые node_modules)
2) mvn clean install

В папке target будет создан файл дистрибутива sms_project.war, который можно задепплоить на tomcat.

Проект компилировал на Java 8 (использовал Spring4), при этом использовались только
особенности Java7.

Тестировал на Tomcat 8 и MySQL.
 
То есть для запуска нужны JRE 8, Tomcat 8 и MySQL.

Скрипт создания базы в MySQL (см. файл init/sql/initscript.sql в пересылаемом архиве):
CREATE DATABASE IF NOT EXISTS smsDB;
USE smsDB;
DROP TABLE IF EXISTS sms_records;
CREATE TABLE IF NOT EXISTS sms_records (
   sms_id INT NOT NULL AUTO_INCREMENT,
   phone_number VARCHAR(11) NOT NULL,
   message  VARCHAR(255) NOT NULL,
   send_date DATETIME NOT NULL,
   send_status VARCHAR(40) NOT NULL,
   PRIMARY KEY (sms_id)
);

В Tomcat  нужно прописать коннект к базе,

для этого достаточно  создать в файле conf/context.xml Tomcat'a элемент <Resource> внутри тега <Context>
(см. файл init/tomcat/context.xml в пересылаемом архиве):

<Resource name="jdbc/smsDB" auth="Container" type="javax.sql.DataSource"
               maxTotal="100" maxIdle="30" maxWaitMillis="10000"
               username="root" password="123456" driverClassName="com.mysql.jdbc.Driver"
               url="jdbc:mysql://localhost:3306/smsDB"/>

Соответственно, в примере используется пользователь MySQL root с паролем 123456, имя базы smsDB.

Клиентская сторона написана на React.js, бэкэнд - на Spring-MVC c использованием Spring-JDBC.

Скриншоты в папке demo.