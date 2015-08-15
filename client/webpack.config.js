var path = require('path');
var webpack = require('webpack');
var node_modules = path.resolve(__dirname, 'node_modules');

var deps = [
    'react/dist/react.min.js',
    'jquery/dist/jquery.min.js'
];

var config = {

    entry: {
        app: [
            'webpack/hot/dev-server',
            path.resolve(__dirname, 'src/main.js'),
            path.resolve(node_modules, 'semantic-ui-css/semantic.min.css')
        ],

        vendors: [
            'react',
            'jquery',
            'semantic-ui-css/semantic.min.js'
        ]
    },

    resolve: {
        alias: {},
        modulesDirectories: ['node_modules']
    },

    output: {
        path: path.resolve(__dirname, 'build'),
        filename: 'bundle.js'
    },

    module: {
        loaders: [

            { test: path.resolve(node_modules, deps[1]), loader: "expose?jQuery"},
            { test: path.resolve(node_modules, deps[0]), loader: "expose?React"},

            {test: /\.jsx?$/, loader: 'babel'},
            {test: /\.css$/, loader: 'style!css!postcss'},
            {test: /\.(png|jpg)$/, loader: 'url?limit=25000'},

            { test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,   loader: "url?limit=10000&mimetype=application/font-woff" },
            { test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,   loader: "url?limit=10000&mimetype=application/font-woff2" },
            { test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,    loader: "url?limit=10000&mimetype=application/octet-stream" },
            { test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,    loader: "file" },
            { test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,    loader: "url?limit=10000&mimetype=image/svg+xml" }


        ],

        noParse: [
            ///\.min\.js/
        ]
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