//import address from '../../config/address.config'
var address = require('../../config/address.config')
import axios from 'axios'

// https://github.com/mzabriskie/axios
axios.defaults.baseURL = address.SERVER_ADDRESS;
//axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
//axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

// // Add a request interceptor
// axios.interceptors.request.use(function (config) {
//   // Do something before request is sent
//   return config;
// }, function (error) {
//   // Do something with request error
//   return Promise.reject(error);
// });

// // Add a response interceptor
// axios.interceptors.response.use(function (response) {
//   // Do something with response data
//   return response;
// }, function (error) {
//   // Do something with response error
//   return Promise.reject(error);
// });

function fetch(url) {
  return axios.get(url)
}

/**
 * 获取百度指标趋势(pv,uv,stayTime,ips,exists,avgVisitPages)
 * @param {*} platform 
 * @param {*} startDate 
 * @param {*} endDate 
 */
export function baiduTrend({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/baidu/trend/platform/1002000/startDate/20170101/endDate/20170301
  let url = `/biservice/v1/baidu/trend/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

/**
 * 页面流量排名 || 页面跳出排名
 * @param {*} platform 
 * @param {*} startDate 
 * @param {*} endDate 
 */
export function pageVisit({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/baidu/vist/platform/102000/startDate/20170101/endDate/20170301
  let url = `/biservice/v1/baidu/vist/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

/**
 * 访问来源
 * @param {*} platform 
 * @param {*} startDate 
 * @param {*} endDate 
 */
export function getSources({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/baidu/sources/platform/102000/startDate/20170101/endDate/20170301
  let url = `/biservice/v1/baidu/sources/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

/**
 * 新老访客
 * @param {*} platform 
 * @param {*} startDate 
 * @param {*} endDate 
 */
export function getVistors({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/baidu/vistors/platform/102000/startDate/20170101/endDate/20170301
  let url = `/biservice/v1/baidu/vistors/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

/**
 * 地域分布
 * @param {*} platform 
 * @param {*} startDate 
 * @param {*} endDate 
 */
export function getAreas({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/baidu/areas/platform/102000/startDate/20170101/endDate/20170301
  let url = `/biservice/v1/baidu/areas/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}



/**
 * 总览
 */
export function getOverview() {
  // /biservice/v1/overview/getOverview
  let url = '/biservice/v1/overview/getOverview'
  return fetch(url)
}

/**
 * 星级水平
 * @param {*} area 
 */
export function starLevel(area) {
  // /biservice/v1/overview/starLevel/{area}/
  let url = `/biservice/v1/overview/starLevel/${area}`
  return fetch(url)
}

/**
 * 拍档
 * @param {*} area 
 */
export function partner(area) {
  // /biservice/v1/overview/partner/{area}/
  let url = `/biservice/v1/overview/partner/${area}`
  return fetch(url)
}

/**
 * 客户
 * @param {*} area 
 */
export function customer(area) {
  // /biservice/v1/overview/customer/{area}/
  let url = `/biservice/v1/overview/customer/${area}`
  return fetch(url)
}

/**
 * 列表
 * p: 客户支出TOP5, i: 拍档收入TOP5, a: 货值TOP5, c: 订单数TOP5
 * @param {*} startDate 
 * @param {*} endDate 
 * @param {*} customsCode 
 */
export function getAreaOrderList(startDate, endDate, customsCode) {
  // /biservice/v1/areaorder/list/{startDate}/{endDate}/{customsCode}
  let url = `/biservice/v1/areaorder/list/${startDate}/${endDate}/${customsCode}`
  return fetch(url)
}

/**
 * 趋势图
 * @param {*} flag 1: 订单货值趋势 2: 订单数趋势
 * @param {*} timeType 时间类型：week、month、quarter、year
 * @param {*} startDate 
 * @param {*} endDate 
 * @param {*} customsCode 
 * @param {*} interval 0: startDate and endData are same, else 1
 */
export function getOrderTrend({
  flag,
  timeType,
  startDate,
  endDate,
  customsCode,
  interval
}) {
  // /biservice/v1/areaorder/trend/{flag}/{timeType}/{startDate}/{endDate}/{customsCode}/{interval}
  let url = `/biservice/v1/areaorder/trend/${flag}/${timeType}/${startDate}/${endDate}/${customsCode}/${interval}`
  return fetch(url)
}

/**
 * 区域订单统计
 * @param {*} timeType 
 * @param {*} startDate 
 * @param {*} endDate 
 */
export function getOrderMap(timeType, startDate, endDate) {
  // /biservice/v1/areaorder/map/{timeType}/{startDate}/{endDate}
  let url = `/biservice/v1/areaorder/map/${timeType}/${startDate}/${endDate}`
  return fetch(url)
}

/**
 * 获取关区列表
 */
export function firstLevelCustoms() {
  var instance = axios.create({
    baseURL: ''
  });
  // /v1/dict/firstLevelCustoms
  let url = `/v1/dict/firstLevelCustoms`
  return instance.get(url)
}

/**
 * 获取某一关区下的：海关列表
 * @param {*} code 关区编码
 */
export function secondLevelCustoms(code) {
  var instance = axios.create({
    baseURL: ''
  });
  // /v1/dict/secondLevelCustoms/code/02
  let url = `/v1/dict/secondLevelCustoms/code/${code}`
  return instance.get(url)
}

//=============企业分类统计==========

/**
 * 企业新增数||企业活跃数
 * @param {*} timeType 时间类型：week、month、quarter、year {2个表，一个是天统计，一个是月统计}
 * @param {*} startDate 开始时间
 * @param {*} endDate 结束时间
 * @param {*} chart 查询的图表：{企业分类统计的}1、企业新增数；；2、企业活跃数
 */
export function getCompanyNum({
  timeType,
  startDate,
  endDate,
  chart
}) {
  // /biservice/v1/company/getCompanyNum/timeType/month/startDate/20170301/endDate/20170308/chart/1
  let url = `/biservice/v1/company/getCompanyNum/timeType/${timeType}/startDate/${startDate}/endDate/${endDate}/chart/${chart}`
  return fetch(url)
}

/**
 * 拍档企业 || 客户企业
 * @param {*} timeType 时间类型：week、month、quarter、year
 * @param {*} startDate 开始时间
 * @param {*} endDate 结束时间
 * @param {*} customsCode 海关/关区：编码
 * @param {*} chart 查询的图表：{企业分类统计的}1、客户企业订单数top；；2、拍档企业订单数top；； 3、客户企业收入top；；4、拍档企业收入top；；5、客户企业:订单货值top；；6、拍档企业:订单货值top
 */
export function getCompanyTopAmount({
  timeType,
  startDate,
  endDate,
  customsCode,
  chart
}) {
  // /biservice/v1/company/getCompanyAmount/timeType/month/startDate/20160101/endDate/20170307/customsCode/all/chart/1
  let url = `/biservice/v1/company/getCompanyAmount/timeType/${timeType}/startDate/${startDate}/endDate/${endDate}/customsCode/${customsCode}/chart/${chart}`
  return fetch(url)
}

/**
 * 进出口分类统计：进口贸易国别top
 * @param {*} timeType 时间类型：week、month、quarter、year
 * @param {*} startDate 开始时间
 * @param {*} endDate 结束时间
 * @param {*} customsCode 海关/关区：编码
 * @param {*} inout in进口、out出口
 */
export function getInOutCountryAmount({
  timeType,
  startDate,
  endDate,
  customsCode,
  inout
}) {
  // /biservice/v1/inout/getInOutCountryAmount/timeType/month/startDate/20160101/endDate/20170307/customsCode/all/inout/in
  let url = `/biservice/v1/inout/getInOutCountryAmount/timeType/${timeType}/startDate/${startDate}/endDate/${endDate}/customsCode/${customsCode}/inout/${inout}`
  return fetch(url)
}

/**
 * 进出口分类统计：进口商品top
 * @param {*} timeType 时间类型：week、month、quarter、year
 * @param {*} startDate 开始时间
 * @param {*} endDate 结束时间
 * @param {*} customsCode 海关/关区：编码
 * @param {*} inout in进口、out出口
 */
export function getInOutGoodsAmount({
  timeType,
  startDate,
  endDate,
  customsCode,
  inout
}) {
  // /biservice/v1/inout/getInOutGoodsAmount/timeType/month/startDate/20160101/endDate/20170307/customsCode/0102/inout/in
  let url = `/biservice/v1/inout/getInOutGoodsAmount/timeType/${timeType}/startDate/${startDate}/endDate/${endDate}/customsCode/${customsCode}/inout/${inout}`
  return fetch(url)
}

/**
 * 进出口分类统计：进口口岸top
 * @param {*} timeType 时间类型：week、month、quarter、year
 * @param {*} startDate 开始时间
 * @param {*} endDate 结束时间
 * @param {*} customsCode 海关/关区：编码
 * @param {*} inout in进口、out出口
 */
export function getInOutCustomsAmount({
  timeType,
  startDate,
  endDate,
  customsCode,
  inout
}) {
  // /biservice/v1/inout/getInOutCustomsAmount/timeType/month/startDate/20160101/endDate/20170307/customsCode/0102/inout/in
  let url = `/biservice/v1/inout/getInOutCustomsAmount/timeType/${timeType}/startDate/${startDate}/endDate/${endDate}/customsCode/${customsCode}/inout/${inout}`
  return fetch(url)
}
