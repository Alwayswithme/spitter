module.exports = React.createClass({
    componentDidMount: function() {
        $(document).ready(function() {
                $('.ui.selection.dropdown').dropdown({
                    action: 'activate'
                });
                $('.ui.menu .ui.dropdown').dropdown({
                    on: 'hover'
                });
                $('.ui.dropdown').dropdown();
            })
        ;
    },

    render: function() {
        return (
            <div className="row">
                <div className="column">
                    <h1 className="ui header">Dropdown</h1>
                    <div className="ui divider"></div>
                    <div className="ui search selection dropdown multiple">
                        <input type="hidden" name="states" />
                        <i className="dropdown icon"></i>

                        <div className="default text">States</div>
                        <div className="menu">
                            <div className="item" data-value="AL">Alabama</div>
                            <div className="item" data-value="AK">Alaska</div>
                            <div className="item" data-value="AZ">Arizona</div>
                            <div className="item" data-value="AR">Arkansas</div>
                            <div className="item" data-value="CA">California</div>
                            <div className="item" data-value="OH">Ohio</div>
                            <div className="item" data-value="OK">Oklahoma</div>
                            <div className="item" data-value="OR">Oregon</div>
                            <div className="item" data-value="PA">Pennsylvania</div>
                            <div className="item" data-value="RI">Rhode Island</div>
                            <div className="item" data-value="SC">South Carolina</div>
                            <div className="item" data-value="SD">South Dakota</div>
                            <div className="item" data-value="TN">Tennessee</div>
                            <div className="item" data-value="TX">Texas</div>
                            <div className="item" data-value="UT">Utah</div>
                            <div className="item" data-value="VT">Vermont</div>
                            <div className="item" data-value="VA">Virginia</div>
                            <div className="item" data-value="WA">Washington</div>
                            <div className="item" data-value="WV">West Virginia</div>
                            <div className="item" data-value="WI">Wisconsin</div>
                            <div className="item" data-value="WY">Wyoming</div>
                        </div>
                    </div>

                    <div className="ui selection dropdown">
                        <input type="hidden" name="selection"/>
                        <i className="dropdown icon"></i>

                        <div className="default text">Gender</div>
                        <div className="menu">
                            <div className="item" data-value="male"><i className="male icon"></i>Male</div>
                            <div className="item" data-value="female"><i className="female icon"></i>Female</div>
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
                        Shopping
                        <i className="dropdown icon"></i>
                        <div className="menu">
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