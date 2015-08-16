"use strict";

require('./Login.css');

var LoginForm = require('./Form.jsx');
var LoginSignUp = require('./SignUp.jsx');


module.exports = React.createClass({
    render: function() {
        return (
            <div className="ui middle aligned center aligned grid">
                <div className="column">
                    <h2 className="ui teal image header">
                        <img src={require('./../../public/images/logo.png')} className="image"/>

                        <div className="content">
                            Log-in to your account
                        </div>
                    </h2>
                    
                    <LoginForm />
                    <LoginSignUp />
                </div>
            </div>
        );
    }
});