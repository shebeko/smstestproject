/** @jsx React.DOM */
var React = require('react');
var ReactDOM = require('react-dom');
var SMSConsole = require('./SMSConsole.jsx');

ReactDOM.render(
    <SMSConsole/>, document.getElementById("mount_node")
);