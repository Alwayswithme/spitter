var path = require('path');
var webpack = require('webpack');
var node_modules = path.resolve(__dirname, 'node_modules');

var deps = [
    'react/dist/react.min.js'
];

var config = {

    entry: {
        app: path.resolve(__dirname, 'src/main.js'),
        vendors: ['react']
    },

    resolve: {
        alias: {}
    },
    output: {
        path: path.resolve(__dirname, 'build'),
        filename: 'bundle.js'
    },

    module: {
        loaders: [
            {test: /\.jsx?$/, loader: 'babel-loader'},
            {test: /\.css$/, loader: 'style-loader!css-loader!postcss-loader'},
            {test: /\.(png|jpg)$/, loader: 'url?limit=25000'},
            {test: /\.woff$/, loader: 'url?limit=100000'}
        ],
        noParse: []
    },

    plugins: [
        new webpack.optimize.CommonsChunkPlugin('vendors', 'vendors.js')
    ],

    postcss: [
        require('postcss-nested')(),
        require('cssnext')()
    ]
};

deps.forEach(function (dep) {
    var depPath = path.resolve(node_modules, dep);
    config.resolve.alias[dep.split(path.sep)[0]] = depPath;
    config.module.noParse.push(depPath);
});

module.exports = config;