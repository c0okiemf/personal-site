const VueLoaderPlugin = require('vue-loader/lib/plugin');
const process = require('process');

let config = {
    entry: './src/main/resources/js/app.js',
    module: {
        rules: [
            {
                test: /\.vue$/,
                use: 'vue-loader'
            },
            {
                test: /\.s[ac]ss$/i,
                use: [
                    'style-loader',
                    'css-loader?url=false',
                    {
                        loader: 'sass-loader',
                        options: {
                            sassOptions: {
                                indentedSyntax: true
                            }
                        }
                    }
                ],
            }
        ]
    },
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.esm.js'
        },
        extensions: ['*', '.js', '.vue', '.json']
    },
    plugins: [
        new VueLoaderPlugin()
    ],
    output: {
        path: __dirname + '/src/main/resources/static/js/',
        filename: 'app.js'
    }
};

if(process.env.npm_lifecycle_script.search(/production/g) !== -1){
    const MinifyPlugin = require("babel-minify-webpack-plugin");
    config.plugins.push(new MinifyPlugin())
}

module.exports = config;