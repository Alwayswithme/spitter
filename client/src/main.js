"use strict";
require('./main.css');

var React = require('react');
var Hello = require('./component.jsx');


function main() {
    React.render(<Hello />, document.getElementById('app'));
}

main();