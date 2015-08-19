'use strict';
var Header = require('./Header.jsx');
var Message = require('./Message.jsx');
var Dropdown = require('./Dropdown.jsx');

var Hello = React.createClass({
    render() {
        return (
            <div className="ui grid container">
                <Header />
                <Message />
                <Dropdown />
            </div>
        );
    }
});

module.exports = Hello;