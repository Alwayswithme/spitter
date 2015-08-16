'use strict';
var Header = require('./header.jsx');
var Message = require('./message.jsx');
var Dropdown = require('./dropdown.jsx');

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