'use strict';
var a = require('semantic-ui-css/semantic.min.js');
var Hello = require('./hello.jsx');


function main() {
    React.render(<Hello />, document.getElementById('app'));
}

main();