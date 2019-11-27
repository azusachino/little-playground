const path = require('path')
const webpack = require('webpack')

module.exports = {
    entry: './app/index.jsx',
    output: {path: __dirname, filename: 'dist/bundle.js'},
    module: {
        loaders: [{
            test: /.jsx?$/,
            loader: 'babel-loader',
            exclude: /node_module/,
            query: {
                presets: ['es2015', 'react']
            }

        }]
    }
}
