'use strict';

require('semantic-ui-css/semantic.min.css');
require('./main.css');

var LoginGrid = require('./components/Login/Grid.jsx');

function main() {
    React.render(<LoginGrid />, document.getElementById('app'));
}

main();