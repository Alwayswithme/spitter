var React = require('react');

var HelloWorld = React.createClass({
    render: function() {
        return (
            <div>
                <h1>
                    Hello React!
                </h1>
                <p>It works</p>
            </div>
        )
    }
});

module.exports = HelloWorld;