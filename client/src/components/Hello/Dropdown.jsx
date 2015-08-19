"use strict";

module.exports = React.createClass({
    componentDidMount: function() {
        $(document).ready(function() {
                $('.ui.selection.dropdown').dropdown();
                $('.ui.menu .ui.dropdown').dropdown({
                    on: 'hover'
                });
            })
        ;
    },

    render: function() {
        return (
            <div className="row">
                <div className="column">
                    <h1 className="ui header">Dropdown</h1>

                    <div className="ui divider"></div>
                    <div className="ui selection dropdown">
                        <input type="hidden" name="selection"/>
                        <i className="dropdown icon"></i>

                        <div className="default text">Select</div>
                        <div className="menu">
                            <div className="item" data-value="male">Male</div>
                            <div className="item" data-value="female">Female</div>
                        </div>
                    </div>

                    <div className="ui vertical menu">
                        <a className="active item">
                            Friends
                        </a>
                        <a className="item">
                            Messages
                        </a>

                        <div className="ui dropdown item">
                            <i className="dropdown icon"></i>
                            More
                            <div className="menu">
                                <a className="item">Edit Profile</a>
                                <a className="item">Choose Language</a>
                                <a className="item">Account Settings</a>
                            </div>
                        </div>
                    </div>

                    <div className="ui dropdown">
                        <div className="visible menu">
                            <div className="header">Categories</div>
                            <div className="item">
                                <i className="dropdown icon"></i>
                                <span className="text">Clothing</span>

                                <div className="menu">
                                    <div className="header">Mens</div>
                                    <div className="item">Shirts</div>
                                    <div className="item">Pants</div>
                                    <div className="item">Jeans</div>
                                    <div className="item">Shoes</div>
                                    <div className="divider"></div>
                                    <div className="header">Womens</div>
                                    <div className="item">Dresses</div>
                                    <div className="item">Shoes</div>
                                    <div className="item">Bags</div>
                                </div>
                            </div>
                            <div className="item">Home Goods</div>
                            <div className="item">Bedroom</div>
                            <div className="divider"></div>
                            <div className="header">Order</div>
                            <div className="item">Status</div>
                            <div className="item">Cancellations</div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});