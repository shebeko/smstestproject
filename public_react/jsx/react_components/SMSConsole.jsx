var React = require('react');
var SMSForm = require('./SMSForm.jsx');
var $ = require('jquery');
var Griddle = require('griddle-react');

/** @jsx React.DOM */
module.exports = React.createClass({

    getInitialState: function () {
        return {data: []}
    },

    loadData: function () {
        $.ajax({
            url: '/sms_project/sms/',
            type: 'get',
            success: function (response) {
                this.setState({data: response.data});
            }.bind(this)
        });
    },

    componentWillMount: function () {
        this.loadData();
    },

    render: function () {
        return (
            <div>
                <SMSForm phonenumber_title="Номер телефона:" message_title="Сообщение:" button_label="Отправить"
                         phonenumber_initialMsg="79011234567" message_initialMsg="Текст сообщения"
                         callback={this.loadData}/>
                <div className="griddleClass">
                    <Griddle results={this.state.data} resultsPerPage="10" tableClassName="table" showFilter={false}
                         showSettings={false} initialSortAscending={false} initialSort={"sentDate"} columns={["number", "sentDate", "sentStatus", "text"]}/>
                </div>
            </div>
        );
    }
});