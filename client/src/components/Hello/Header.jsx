"use strict";

var Header = React.createClass({
    render: function() {
        return (
            <div className="row">
                <div className="column left icon">
                    <i className="users icon"/>
                    <h1 className="header">Hello React!</h1>
                </div>
            </div>
        );
    }
});

module.exports = Header;