var React = require('react');
var $ = require('jquery');

var regexp = /^79\d{9}$/;
var msgs = {
    errorMsg:  "Произошла ошибка, сообщение не отправлено!",
    successMsg: "Сообщение успешно отправлено!",
    isSendingMsg: "Отправка сообщения ..."
};

/** @jsx React.DOM */
module.exports = React.createClass({

    getInitialState: function() {
        return {'phonenumber_value': this.props.phonenumber_initialMsg,
            'message_value': this.props.message_initialMsg,
            'phonenumber_disabled': false,
            'message_disabled': false,
            'button_disabled': false
        };
    },

    handlePNChange: function(event) {
        this.setState({phonenumber_value: event.target.value});
    },

    handleMChange: function(event) {
        this.setState({message_value: event.target.value});
    },

    onClick: function() {
        var msg = "";
        // проверка на пустые поля
        if (!this.state.phonenumber_value.trim()) {
            msg = "Телефонный номер не указан. ";
        }
        if (!this.state.message_value.trim()) {
            msg = msg + "Сообщение не может быть пустым."
        }
        if (msg) {
            this.setState({
                'statusbar_class':"statusBarErrorClass",
                'statusbar_message': msg
            });
            return;
        }

        // проверка на соответствие телефонного номера регулярному выражению
        if (regexp.test(this.state.phonenumber_value)) {
            // клиентские проверки успешно проведены, можно делать запрос на сервер
            // блокируются поля ввода и кнопка
            this.setState({
                'statusbar_class': "statusBarClass",
                'statusbar_message': msgs.isSendingMsg,
                'phonenumber_disabled': true,
                'message_disabled': true,
                'button_disabled':true
            });
            // сообщение отправляется на сервер
            var sms = {
                'phoneNumber': this.state.phonenumber_value,
                'text': this.state.message_value
            };
            $.ajax({
                url: '/sms_project/sms/submit/',
                type: 'post',
                dataType: 'json',
                contentType:'application/json',
                data: JSON.stringify(sms),
                success: function(data){
                    var statusBarMsg = "";
                    if (data.sentStatus == "SUCCESS") {
                        this.setState({
                            'statusbar_message': msgs.successMsg,
                            'statusbar_class': "statusBarClass",
                            'message_value': "",
                            'phonenumber_value': "",
                            'phonenumber_disabled': false,
                            'message_disabled': false,
                            'button_disabled': false
                        });
                    } else if (data.sentStatus == "FAIL") {
                        this.setState({
                            'statusbar_message': msgs.errorMsg,
                            'statusbar_class': "statusBarErrorClass",
                            'phonenumber_disabled': false,
                            'message_disabled': false,
                            'button_disabled': false
                        });
                    }
                    // вызывается callback функция для обновления таблицы
                    this.props.callback();
                }.bind(this),
                error: function(xhr, status, err) {
                    console.error(this.props.url, status, err.toString());
                }.bind(this)
            });
        } else {
            // введенный телефонный номер не соответствует регулярному выражению
            msg = this.state.phonenumber_value + " не является валидным телефонным номером";
            this.setState({
                'statusbar_message': msg,
                'statusbar_class': "statusBarErrorClass"
            });
        }
    },

    render: function () {
        return (
            <div className="smsFormClass">
                <div className="containerClass">
                    <div className="phoneNumberClass">
                        <div className="titleClass">{this.props.phonenumber_title}</div>
                        <input type="text" value={this.state.phonenumber_value} disabled={this.state.phonenumber_disabled} onChange={this.handlePNChange}/>
                    </div>
                    <div className="messageClass">
                        <div className="titleClass">{this.props.message_title}</div>
                        <textarea rows="4" cols="20" value={this.state.message_value} disabled={this.state.message_disabled} onChange={this.handleMChange}/>
                    </div>
                    <div className="buttonClass">
                        <button onClick={this.onClick} disabled={this.state.button_disabled}>{this.props.button_label}</button>
                    </div>
                </div>
                <div id="statusbar" className={this.state.statusbar_class}>
                    {this.state.statusbar_message}
                </div>
            </div>
        );
    }
});