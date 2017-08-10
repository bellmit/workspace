require('./check-versions')()

var config = require('../config')
if (!process.env.NODE_ENV) {
  process.env.NODE_ENV = JSON.parse(config.dev.env.NODE_ENV)
}

var opn = require('opn')
var path = require('path')
var express = require('express')
var webpack = require('webpack')
var proxyMiddleware = require('http-proxy-middleware')
var webpackConfig = require('./webpack.dev.conf')

// default port where dev server listens for incoming traffic
var port = process.env.PORT || config.dev.port
// automatically open browser, if not set will be false
var autoOpenBrowser = !!config.dev.autoOpenBrowser
// Define HTTP proxies to your custom API backend
// https://github.com/chimurai/http-proxy-middleware
var proxyTable = config.dev.proxyTable

var app = express()

var appData = require('../src/mock/data.json')

var apiRoutes = express.Router()
var apiRoutes2 = express.Router()

apiRoutes.get('/v1/baidu/trend/platform/*', function (req, res) {
  console.log(req.url)

  res.json(appData.baiduTrend)
})

apiRoutes.get('/v1/baidu/vist/platform/*', function (req, res) {
  console.log(req.url)

  res.json(appData.pageVisit)
})

apiRoutes.get('/v1/baidu/sources/platform/*', function (req, res) {
  console.log(req.url)

  res.json(appData.getSource)
})

apiRoutes.get('/v1/baidu/vistors/platform/*', function (req, res) {
  console.log(req.url)

  res.json(appData.vistors)
})

apiRoutes.get('/v1/baidu/areas/platform/*', function (req, res) {
  console.log(req.url)

  res.json(appData.getAreas)
})


apiRoutes.get('/v1/company/getCompanyNum/*', function (req, res) {

  var type = req.url.lastIndexOf('1') === 0 ? '1' : '2';

  res.json(appData['getCompanyNum' + type])
})

apiRoutes.get('/v1/company/getCompanyAmount/*', function (req, res) {
  console.log(req.url)

  res.json(appData.getCompanyAmount)
})

apiRoutes.get('/v1/inout/getInOutCountryAmount/*', function (req, res) {
  console.log(req.url)

  res.json(appData.getInOutCountryAmount)
})

apiRoutes.get('/v1/inout/getInOutGoodsAmount/*', function (req, res) {
  console.log(req.url)

  res.json(appData.getInOutGoodsAmount)
})

apiRoutes.get('/v1/inout/getInOutCustomsAmount/*', function (req, res) {
  console.log(req.url)

  res.json(appData.getInOutCustomsAmount)
})

apiRoutes.get('/v1/overview/getOverview', function (req, res) {
  console.log(req.url)

  res.json(appData.getOverview)
})

apiRoutes.get('/v1/overview/starLevel/*', function (req, res) {
  console.log(req.url)

  res.json(appData.starLevel)
})

apiRoutes.get('/v1/overview/partner/*', function (req, res) {
  console.log(req.url)

  res.json(appData.partner)
})


apiRoutes.get('/v1/overview/customer/*', function (req, res) {
  console.log(req.url)

  res.json(appData.customer)
})

apiRoutes.get('/v1/areaorder/list/*', function (req, res) {
  console.log(req.url)

  res.json(appData.areaOrderList)
})

apiRoutes.get('/v1/areaorder/trend/*', function (req, res) {
  console.log(req.url)

  res.json(appData.orderTrend)
})

apiRoutes.get('/v1/areaorder/map/*', function (req, res) {
  console.log(req.url)

  res.json(appData.orderMap)
})

apiRoutes2.get('/dict/firstLevelCustoms', function (req, res) {
  console.log(req.url)

  res.json(appData.firstLevelCustoms)
})

apiRoutes2.get('/dict/secondLevelCustoms/*', function (req, res) {
  console.log(req.url)

  res.json(appData.secondLevelCustoms)
})


app.use('/biservice', apiRoutes)
app.use('/v1', apiRoutes2)


var compiler = webpack(webpackConfig)

var devMiddleware = require('webpack-dev-middleware')(compiler, {
  publicPath: webpackConfig.output.publicPath,
  quiet: true
})

var hotMiddleware = require('webpack-hot-middleware')(compiler, {
  log: () => {}
})
// force page reload when html-webpack-plugin template changes
compiler.plugin('compilation', function (compilation) {
  compilation.plugin('html-webpack-plugin-after-emit', function (data, cb) {
    hotMiddleware.publish({
      action: 'reload'
    })
    cb()
  })
})

// proxy api requests
Object.keys(proxyTable).forEach(function (context) {
  var options = proxyTable[context]
  if (typeof options === 'string') {
    options = {
      target: options
    }
  }
  app.use(proxyMiddleware(options.filter || context, options))
})

// handle fallback for HTML5 history API
app.use(require('connect-history-api-fallback')())

// serve webpack bundle output
app.use(devMiddleware)

// enable hot-reload and state-preserving
// compilation error display
app.use(hotMiddleware)

// serve pure static assets
var staticPath = path.posix.join(config.dev.assetsPublicPath, config.dev.assetsSubDirectory)
app.use(staticPath, express.static('./static'))

var uri = 'http://localhost:' + port

devMiddleware.waitUntilValid(function () {
  console.log('> Listening at ' + uri + '\n')
})

module.exports = app.listen(port, function (err) {
  if (err) {
    console.log(err)
    return
  }

  // when env is testing, don't need open it
  if (autoOpenBrowser && process.env.NODE_ENV !== 'testing') {
    opn(uri)
  }
})
