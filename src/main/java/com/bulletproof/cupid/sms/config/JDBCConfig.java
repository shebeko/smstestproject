package com.bulletproof.cupid.sms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

/**
 * Created by shds on 17.10.2015.
 */
@Configuration
public class JDBCConfig {

    @Bean
    public JndiObjectFactoryBean dataSource() {
        JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
        jndiObjectFB.setJndiName("jdbc/smsDB");
        jndiObjectFB.setResourceRef(true);
        jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
        return jndiObjectFB;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() {
        DataSource dataSource = (DataSource)(dataSource().getObject());
        NamedParameterJdbcTemplate t = new NamedParameterJdbcTemplate(dataSource);
        return t;
    }
}