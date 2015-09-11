require('semantic-ui-css/semantic.min.css');
require('./main.css');

var LoginGrid = require('./components/Login/Grid.jsx');

var Hello = require('./components/Hello/Hello.jsx');

function main() {
    React.render(<Hello />, document.getElementById('app'));
}

main();