module.exports = {
    entry: './static/jsx/app.jsx',
    output: {
        filename: 'bundle.js',
        path: __dirname + '/static/build/js'
    },
    module: {
        loaders: [
            {test: /\.jsx$/, loader: 'jsx-loader?insertPragma=React.DOM&harmony'}
        ]
    },
    resolve: {
        extensions: ['', '.js', '.jsx']
    }
};
