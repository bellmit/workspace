import {
  fetch
} from './baseApi.config'

// ====用户行为  登录分析=====

// 用户登录情况接口
export function userTraceLogin({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/usertrace/login/platform/102000/startDate/20170420/endDate/20170425
  let url = `/biservice/v1/usertrace/login/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 用户使用浏览器情况
export function userTraceBrowser({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/usertrace/browser/platform/102000/startDate/20170420/endDate/20170425
  let url = `/biservice/v1/usertrace/browser/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// ====店铺运营=====

// 开通店铺趋势图
export function shopOpenTrend({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/shop/openTrend/20170401/20170428/
  let url = `/biservice/v1/shop/openTrend/${startDate}/${endDate}`
  return fetch(url)
}

// 基础资料完善统计及占比
export function shopOpenPerfect({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/shop/perfect/20170401/20170428/
  let url = `/biservice/v1/shop/perfect/${startDate}/${endDate}`
  return fetch(url)
}

// 商品发布数排行
export function shopReleaseRank({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/shop/releaseRank/20170401/20170428/
  let url = `/biservice/v1/shop/releaseRank/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

// 店铺分类排行
export function shopCategoryRank({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/shop/categoryRank/20170401/20170428/
  let url = `/biservice/v1/shop/categoryRank/${startDate}/${endDate}`
  return fetch(url)
}

// 店铺成交排行
export function shopDealRank({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/shop/dealRank/20170401/20170428/
  let url = `/biservice/v1/shop/dealRank/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

// 商品发布列表
// /biservice/v1/shop/releaseList/20170401/20170428/
export function shopReleaseList({
  platform,
  startDate,
  endDate
}) {

  let url = `/biservice/v1/shop/releaseList/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

// 店铺分类列表
// /biservice/v1/shop/categoryList/20170401/20170428/
export function shopCategoryList({
  platform,
  startDate,
  endDate
}) {

  let url = `/biservice/v1/shop/categoryList/${startDate}/${endDate}`
  return fetch(url)
}

// 成交分析列表
// /biservice/v1/shop/dealList/20170401/20170428/
export function shopDealList({
  platform,
  startDate,
  endDate
}) {

  let url = `/biservice/v1/shop/dealList/${startDate}/${endDate}/${platform}`
  return fetch(url)
}


//-------------- 注册分析  日新增用户--------------

// 新增注册数
/**
 *
 * @param {*} platform
 * @param {*} startDate
 * @param {*} endDate
 * @param {*} dataType : lineChart,pieChart,table
 */
export function newlyIncreased({
  platform,
  startDate,
  endDate,
  dataType
}) {
  // biservice/v1/register/newlyIncr/lineChart/platform/1025/startDate/20160501/endDate/20170701
  let url = `/biservice/v1/register/newlyIncr/${dataType}/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

export function newlyIncreasedTableList({
  platform,
  startDate,
  endDate,
  limit,
  offset
}) {
  // biservice/v1/register/newlyIncr/lineChart/platform/1025/startDate/20160501/endDate/20170701
  let url = `/biservice/v1/register/newlyIncr/table/platform/${platform}/startDate/${startDate}/endDate/${endDate}/limit/${limit}/offset/${offset}`
  return fetch(url)
}

//-------------- 注册分析  认证用户 --------------
// 认证用户情况 折线图
// type，0：浏览量，1：独立访客，2：注册会员，3：三证审核，4：授权书
export function authUserOverview({
  platform,
  startDate,
  endDate,
  type
}) {
  // biservice/v1/register/authUser/overview/lineChart/platform/1025/startDate/20160501/endDate/20170701/type/1
  let url = `biservice/v1/register/authUser/overview/lineChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}/type/${type}`
  return fetch(url)
}

// 认证用户情况 三证审核历史累计
export function authUserCert3(platform) {
  // biservice/v1/register/authUser/cert3/history/platform/1025
  let url = `biservice/v1/register/authUser/cert3/history/platform/${platform}`
  return fetch(url)
}


// 认证审核通过 柱状图，格式和折线图一样
export function authUserPassed({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/register/authUser/passed/histogram/platform/1025/startDate/20160501/endDate/20170701
  let url = `biservice/v1/register/authUser/passed/histogram/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 三证合一比例 饼图
export function threeInOne({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/register/authUser/licPercent/pieChart/platform/1025/startDate/20160501/endDate/20170701
  let url = `biservice/v1/register/authUser/licPercent/pieChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 三证、授权地域分布 中国地图
export function areaDistChinaMap({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/register/authUser/areaDist/chinaMap/platform/1025/startDate/20160501/endDate/20170701
  let url = `biservice/v1/register/authUser/areaDist/chinaMap/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 三证、授权地域分布TOP10（条形图 y轴数据 在yAxisData）
export function areaDistBarChart({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/register/authUser/areaDist/barChart/platform/1025/startDate/20160501/endDate/20170701
  let url = `biservice/v1/register/authUser/areaDist/barChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

//
// 签章\支付 开通的历史累计饼图
export function visaAndPayHistory(platform) {
  // biservice/v1/register/visaAndPay/history/pieChart/platform/102000
  let url = `biservice/v1/register/visaAndPay/history/pieChart/platform/${platform}`
  return fetch(url)
}


// 运营总览
export function operateTotal(platform) {
  // biservice/v1/operate/total
  let url = `biservice/v1/operate/total/${platform}`
  return fetch(url)
}

// 运营总览12个基本指标
export function operateIndex({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/operate/getBasic/20170402/20170501
  let url = `/biservice/v1/operate/getBasic/${platform}/${startDate}/${endDate}`
  return fetch(url)
}

// 运营总览 买家转化率统计图
export function getBuyerRate({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/operate/getBuyer/20170402/20170501
  let url = `/biservice/v1/operate/getBuyer/${platform}/${startDate}/${endDate}`
  return fetch(url)
}
// 运营总览 卖家转化率统计图
export function getSellerRate({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/operate/getSeller/20170402/20170501
  let url = `/biservice/v1/operate/getSeller/${platform}/${startDate}/${endDate}`
  return fetch(url)
}
// 运营总览订单类型
export function operateOrderType({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/operateOrder/orderType/platform/100900/startDate/20170420/endDate/20170505
  let url = `/biservice/v1/operateOrder/orderType/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}
// 运营总览订单金额
export function operateOrderPay({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/operateOrder/orderPay/platform/100900/startDate/20170420/endDate/20170505
  let url = `/biservice/v1/operateOrder/orderPay/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 运营总览关联公司top5、子账户开通top5
export function operateGetTop({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/operate/getTop/20160420/20170501
  let url = `/biservice/v1/operate/getTop/${startDate}/${endDate}`
  return fetch(url)
}

// 运营总览发布商品类目占有率
export function operateGoodsCate({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/operateGoods/goodsCate/platform/100900/startDate/20170420/endDate/20170505
  let url = `/biservice/v1/operateGoods/goodsCate/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 运营总览转化漏斗
export function operateConvFunnel({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/operateConv/convFunnel/platform/100900/startDate/20170420/endDate/20170505
  let url = `/biservice/v1/operateConv/convFunnel/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

//-------------- 注册分析  签章支付 --------------
// 概览 折线图：
// biservice/v1/register/visaAndPay/overview/lineChart/platform/102000/startDate/20160501/endDate/20170701/type/1
// type，0：浏览量，1：独立访客，2：签章开通数，3：支付开通数
export function signaturePayIndex({
  platform,
  startDate,
  endDate,
  type
}) {

  let url = `/biservice/v1/register/visaAndPay/overview/lineChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}/type/${type}`
  return fetch(url)
}


// 签章和支付 开通地域分布 中国地图:
export function signaturePayChinaMap({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/register/visaAndPay/areaDist/chinaMap/platform/1025/startDate/20160501/endDate/20170701
  let url = `/biservice/v1/register/visaAndPay/areaDist/chinaMap/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 签章 支付 开通地域分布top10 条形图
/**
 * type: visa, pay
 */
export function signaturePayBarChart({
  platform,
  startDate,
  endDate,
  type
}) {
  // biservice/v1/register/visa/areaDist/barChart/platform/1025/startDate/20160501/endDate/20170701
  let url = `/biservice/v1/register/${type}/areaDist/barChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

//-------------- 成交分析 --------------
//平台运营情况表
// /biservice/v1/deal/operate/20170401/20170428/100700/
export function dealOperate({
  platform,
  startDate,
  endDate,
}) {

  let url = `/biservice/v1/deal/operate/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

//交易转化跟踪
// /biservice/v1/deal/translate/20170401/20170428/100700/
export function dealTranslate({
  platform,
  startDate,
  endDate,
}) {

  let url = `/biservice/v1/deal/translate/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

//提货方式用户占比
// /biservice/v1/deal/pickup/20170401/20170428/100700/
export function dealPickup({
  platform,
  startDate,
  endDate,
}) {

  let url = `/biservice/v1/deal/pickup/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

//交货时间占比
// /biservice/v1/deal/delivery/20170401/20170428/100700/
export function dealDelivery({
  platform,
  startDate,
  endDate,
}) {

  let url = `/biservice/v1/deal/delivery/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

//确认收货时间
// /biservice/v1/deal/receipt/20170401/20170428/100700/
export function dealReceipt({
  platform,
  startDate,
  endDate,
}) {

  let url = `/biservice/v1/deal/receipt/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

//结算方式占比
// /biservice/v1/deal/settle/20170401/20170428/100700/
export function dealSettle({
  platform,
  startDate,
  endDate,
}) {

  let url = `/biservice/v1/deal/settle/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

//支付方式
// /biservice/v1/deal/pay/20170401/20170428/100700/
export function dealPay({
  platform,
  startDate,
  endDate,
}) {

  let url = `/biservice/v1/deal/pay/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

//合同方式
// /biservice/v1/deal/contract/20170401/20170428/100700/
export function dealContract({
  platform,
  startDate,
  endDate,
}) {

  let url = `/biservice/v1/deal/contract/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

//支付渠道金额占比
// /biservice/v1/deal/payChannel/20170401/20170428/102000/
export function dealPayChannel({
  platform,
  startDate,
  endDate,
}) {

  let url = `/biservice/v1/deal/payChannel/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

//地域金额
// role 角色：seller 卖家、buyer 买家
// sortField 排序字段：ordernum 笔数、paytotal 金额
// //biservice/v1/deal/areaAmount/seller/20170401/20170428/100300/ordernum
export function dealAreaAmount({
  platform,
  startDate,
  endDate,
  role,
  sortField
}) {

  let url = `/biservice/v1/deal/areaAmount/${role}/${startDate}/${endDate}/${platform}/${sortField}`
  return fetch(url)
}

// 获取百度统计数据概览
export function baiduOverivew({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/baidu/overivew/platform/100300/startDate/20170518/endDate/20170524
  let url = `/biservice/v1/baidu/overivew/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
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
 * paymentDays:付款天数用户占比.
 * @param {*} param0
 */
export function paymentDays({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/deal/paymentDays/20170501/20170523/101200/
  let url = `/biservice/v1/deal/paymentDays/${startDate}/${endDate}/${platform}`
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

export function getSearchWordCloud() {
  // /biservice/v1/inout/getInOutCustomsAmount/timeType/month/startDate/20160101/endDate/20170307/customsCode/0102/inout/in
  let url = `/biservice/v1/getSearchWordCloud/1`
  return fetch(url)
}
// export function getKeywordsSearchRate() {
//   // /biservice/v1/inout/getInOutCustomsAmount/timeType/month/startDate/20160101/endDate/20170307/customsCode/0102/inout/in
//   let url = `/biservice/v1/getSearchWordCloud/1`
//   return fetch(url)
// }

// ------------------------
// --  交易总平台、聚贸总站 --
// ------------------------
// 运营总览顶部6个统计图
export function masOperateTotal(platform) {
  let url = `/biservice/v1/masoperate/total/${platform}`
  return fetch(url)
}

// 交易全平台整体趋势
export function masOperateIndex(startDate, endDate) {
  let url = `/biservice/v1/masoperate/getBasic/${startDate}/${endDate}`
  return fetch(url)
}

// 发布商品数 行业 占比
export function getItemProportion(startDate, endDate) {
  let url = `/biservice/v1/masoperate/getItemProportion/${startDate}/${endDate}`
  return fetch(url)
}

// 订单笔数 行业 占比
export function getOrderProportion(startDate, endDate) {
  let url = `/biservice/v1/masoperate/getOrderProportion/${startDate}/${endDate}`
  return fetch(url)
}

// 订单金额 行业 占比
export function getOrderMoneyProportion(startDate, endDate) {
  let url = `/biservice/v1/masoperate/getOrderMoneyProportion/${startDate}/${endDate}`
  return fetch(url)
}

// 交易全站 或 交易全站 新增注册数 饼图，用 platform 区分：
export function mainNewlyIncreasedPie({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/register/ms/newlyIncr/pieChart/platform/1025/startDate/20160501/endDate/20170701
  let url = `/biservice/v1/register/ms/newlyIncr/pieChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 发布商品数 折线图：
export function mainPublishGoodsNumber({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/goods/newlyIncr/lineChart/platform/1025/startDate/20160501/endDate/20170701
  let url = `/biservice/v1/goods/newlyIncr/lineChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 发布商品数 行业 占比 饼图
export function mainPublishGoodsIndustry({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/goods/industry/pieChart/platform/1025/startDate/20160501/endDate/20170701
  let url = `/biservice/v1/goods/industry/pieChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 发布商品数 某行业的类目 占比 饼图
export function mainPublishGoodsIndustryDetail({
  industry,
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/goods/industry/4/category/pieChart/platform/1025/startDate/20160501/endDate/20170701
  let url = `/biservice/v1/goods/industry/${industry}/category/pieChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 行业差异 综合分析
export function bubbleGradient({
  platform,
  startDate,
  endDate
}) {
  // biservice/v1/goods/industry/diff/bubbleGradient/platform/1025/startDate/20160501/endDate/20170701
  let url = `/biservice/v1/goods/industry/diff/bubbleGradient/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// -------------- 店铺运营分析 --------------

// 店铺商品发布数排行
export function goodsCategoriesTop({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/shopAnalysis/goodsCategoriesTop/platform/102000/startDate/20170501/endDate/20170525
  let url = `/biservice/v1/shopAnalysis/goodsCategoriesTop/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 商品发布数行业占比
export function goodsIndustryRatio({
  itemId,
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/shopAnalysis/goodsIndustryRatio/platform/102000/startDate/20170501/endDate/20170525/itemId/1000000224
  let url = `/biservice/v1/shopAnalysis/goodsIndustryRatio/platform/${platform}/startDate/${startDate}/endDate/${endDate}/itemId/${itemId}`
  return fetch(url)
}


// 店铺成交金额排行
export function moneyTransactionMoneyTop({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/shopAnalysis/moneyTransactionMoneyTop/platform/102000/startDate/20170501/endDate/20170525
  let url = `/biservice/v1/shopAnalysis/moneyTransactionMoneyTop/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 成交金额行业占比
export function moneyIndustryRatio({
  itemId,
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/shopAnalysis/moneyIndustryRatio/platform/102000/startDate/20170501/endDate/20170525/itemId/100086
  let url = `/biservice/v1/shopAnalysis/moneyIndustryRatio/platform/${platform}/startDate/${startDate}/endDate/${endDate}/itemId/${itemId}`
  return fetch(url)
}

/**
 * 总体情况
 * industryPieCharts:合同方式、结算方式和支付方式按行业分组的饼图.
 *
 * @author Administrator
 * @date 2017年6月7日 下午3:48:23
 * @param startDate
 * @param endDate
 * @param code
 * @param key 合同方式:contract_type;结算方式:clearing_type;支付方式:payment_type;
 * @return
 * @throws Exception
 */
//  /biservice/v1/deal/industryPieCharts/20170401/20170428/10/clearing_type/
//  {"legendData":["工业品","食品","煤炭","机械","消费品","钢铁","矿产","有色","农产品"],"seriesName":"","seriesData":[{"value":1,"name":"工业品","code":"8"},{"value":3,"name":"食品","code":"7"},{"value":4,"name":"煤炭","code":"1"},{"value":4,"name":"机械","code":"9"},{"value":4,"name":"消费品","code":"6"},{"value":5,"name":"钢铁","code":"5"},{"value":7,"name":"矿产","code":"3"},{"value":9,"name":"有色","code":"2"},{"value":10,"name":"农产品","code":"4"}],"title":""}
export function industryPieChartsDetail({
  platform,
  startDate,
  endDate,
  code,
  clearing_type
}) {

  let url = `/biservice/v1/deal/industryPieCharts/${startDate}/${endDate}/${code}/${clearing_type}`
  return fetch(url)
}

/**
 *
 * industryAmount:行业订单分析.
 *
 * @author Administrator
 * @date 2017年5月31日 下午2:15:39
 * @param startDate
 * @param endDate
 * @param sortField  排序字段：ordernum 笔数、paytotal 金额
 * @return
 * @throws Exception
 */
// /biservice/v1/deal/industryAmount/20170501/20170531/paytotal/
export function industryOrderAmount({
  platform,
  startDate,
  endDate,
  sortField
}) {

  let url = `/biservice/v1/deal/industryAmount/${startDate}/${endDate}/${sortField}`
  return fetch(url)
}


/**
 * categoryAmount:指定行业订单笔数各类目占比.
 *
 * @author Administrator
 * @date 2017年6月8日 下午2:47:36
 * @param startDate
 * @param endDate
 * @param industryId
 * @param sortField
 * @return
 * @throws Exception
 */
// /biservice/v1/deal/categoryAmount/20170501/20170531/4/paytotal/
export function industryOrderAmountDetail({
  platform,
  startDate,
  endDate,
  industryId,
  sortField
}) {

  let url = `/biservice/v1/deal/categoryAmount/${startDate}/${endDate}/${industryId}/${sortField}`
  return fetch(url)
}


// JYT API
// -------------- ↓ 总览 -----------------------------------------------
// /biservice/v1/jyt/overview/numbers/itemIds/1-2-3/platform/1025/startDate/20160501/endDate/20170701
export function jytOverview({
  platform,
  startDate,
  endDate,
  itemIds
}) {

  let url = `/biservice/v1/jyt/overview/numbers/itemIds/${itemIds}/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 总览折线图
// /biservice/v1/jyt/overview/lineChart/itemId/1/platform/1025/startDate/20160501/endDate/20170701
export function jytOverviewLineChart({
  platform,
  startDate,
  endDate,
  itemId
}) {

  let url = `/biservice/v1/jyt/overview/lineChart/itemId/${itemId}/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 总览物流线路 饼图
// /biservice/v1/jyt/overview/transportLine/pieChart/platform/1025/startDate/20160501/endDate/20170701
export function jytOverviewTransportLine({
  platform,
  startDate,
  endDate
}) {

  let url = `/biservice/v1/jyt/overview/transportLine/pieChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 需求发布数 top5
// /biservice/v1/jyt/overview/requireTop5/barChart/platform/1025/startDate/20160501/endDate/20170701
export function jytOverviewRequireTop5({
  platform,
  startDate,
  endDate
}) {

  let url = `/biservice/v1/jyt/overview/requireTop5/barChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// -------------- ↓ 注册分析 日新增用户 -----------------------------------------------
// 新增注册数 折线图
// /biservice/v1/jyt/register/newlyIncr/lineChart/platform/1025/startDate/20160501/endDate/20170701
export function jytRegisterNewlyIncr({
  platform,
  startDate,
  endDate
}) {

  let url = `/biservice/v1/jyt/register/newlyIncr/lineChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 新增用户来源 top5
// /biservice/v1/jyt/register/newlyIncr/barChart/platform/1025/startDate/20160501/endDate/20170701
export function jytRegisterNewlyIncrTop5({
  platform,
  startDate,
  endDate
}) {

  let url = `/biservice/v1/jyt/register/newlyIncr/barChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 新增用户来源 饼图
// /biservice/v1/jyt/register/newlyIncr/pieChart/platform/1025/startDate/20160501/endDate/20170701
export function jytRegisterNewlyIncrPieChart({
  platform,
  startDate,
  endDate
}) {

  let url = `/biservice/v1/jyt/register/newlyIncr/pieChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// -------------- ↓ 注册分析 认证分析 -----------------------------------------------

// 认证用户 概览折线图，type 如下
// SMRZ(0, "实名认证"), WTFSZRZ(1, "委托方三证认证"), GYSSZRZ(2, "供应商三证认证"),
//     WTSSQ(3, "委托商授权"), GYSSQ(4, "供应商授权");
// /biservice/v1/jyt/register/authUser/lineChart/platform/1025/startDate/20160501/endDate/20170701/type/0
export function jytRegisterAuthUserLineChart({
  platform,
  startDate,
  endDate,
  type
}) {
  let url = `/biservice/v1/jyt/register/authUser/lineChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}/type/${type}`
  return fetch(url)
}


// 委托、供应 历史累积 饼图
// /biservice/v1/jyt/register/authUser/history/pieChart/platform/1025
export function jytRegisterAuthUserHistoryPieChart({
  platform
}) {
  let url = `/biservice/v1/jyt/register/authUser/history/pieChart/platform/${platform}`
  return fetch(url)
}

// 供应商地域分布 中国地图
// /biservice/v1/jyt/register/supply/areaDist/chinaMap/platform/1025/startDate/20170501/endDate/20170701
export function jytRegisterSupplyAreaDistChinaMap({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/register/supply/areaDist/chinaMap/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 供应商地域分布 top10
// /biservice/v1/jyt/register/supply/areaDist/barChart/platform/1025/startDate/20170501/endDate/20170701
export function jytRegisterSupplyAreaDistBarChart({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/register/supply/areaDist/barChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 委托商地域分布 中国地图
// /biservice/v1/jyt/register/entrust/areaDist/chinaMap/platform/1025/startDate/20170501/endDate/20170701
export function jytRegisterEntrustAreaDistChinaMap({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/register/entrust/areaDist/chinaMap/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 委托商地域分布 top10
// /biservice/v1/jyt/register/entrust/areaDist/barChart/platform/1025/startDate/20170501/endDate/20170701
export function jytRegisterEntrustAreaDistBarChart({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/register/entrust/areaDist/barChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

//  -----------------------------支付分析-----------------------------
// 支付分析支付开通历史累计图1
// /biservice/v1/jyt/pay/payOne/100400/20161201/20170131")
export function jytPayOne({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/pay/payOne/${platform}/${startDate}/${endDate}`
  return fetch(url)
}

// 支付分析支付开通历史累计数
// /biservice/v1/jyt/pay/payTotal
export function jytPayTotal() {
  let url = `/biservice/v1/jyt/pay/payTotal`
  return fetch(url)
}

// 支付分析支付渠道占比
// /biservice/v1/jyt/pay/channelProportion
export function jytPayChannelProportion() {
  let url = `/biservice/v1/jyt/pay/channelProportion`
  return fetch(url)
}

// 支付分析支付开通地域分布
// /biservice/v1/jyt/pay/payOpenClime
export function jytPayOpenClime({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/pay/payOpenClime/${startDate}/${endDate}`
  return fetch(url)
}

// 支付分析支付开通地域分布top
// /biservice/v1/jyt/pay/payOpenClimeTop
export function jytPayOpenClimeTop({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/pay/payOpenClimeTop/${startDate}/${endDate}`
  return fetch(url)
}


//  -----------------------------运力分析-----------------------------
// 运力图公路
// /biservice/v1/jyt/pay/trafficHighway
// 运力图铁路
// /biservice/v1/jyt/pay/trafficRailway
// 运力图内河航运
// /biservice/v1/jyt/pay/trafficInlandNavigation
// 运力图海运
// /biservice/v1/jyt/pay/trafficOceanShipping
// 运力图空运
// /biservice/v1/jyt/pay/trafficAirTransport
/**
 *
 * @param {*} transport Highway,Railway,InlandNavigation,OceanShipping,AirTransport
 */
export function jytTrafficway({
  startDate,
  endDate,
  transport
}) {
  let url = `/biservice/v1/jyt/pay/traffic${transport}/${startDate}/${endDate}`
  return fetch(url)
}


// 运力排行
// /biservice/v1/jyt/pay/TransportTop
export function jytTransportTop({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/pay/TransportTop/${startDate}/${endDate}`
  return fetch(url)
}

// 车辆分析
// /biservice/v1/jyt/pay/CarAnalysis
export function jytCarAnalysis({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/pay/CarAnalysis/${startDate}/${endDate}`
  return fetch(url)
}


// -------------- ↓ 成交分析交易相关 -----------------------------------------------

/**
 * operate:平台运营情况表.
 *
 * @author Administrator
 * @date 2017年6月13日 下午1:12:04
 * @param startDate
 * @param endDate
 * @param platform
 * @return
 * @throws Exception
 */

//  /biservice/v1/jyt/deal/operate/20170501/20170523/101200/
//  [{"count":64,"weight":8583.0000000000,"total_price":0.10},{"count":409,"weight":11194047.2300000000,"total_price":13.86}]
export function jytDealOperate({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/deal/operate/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

/**
 * translate:交易转化跟踪.
 *
 * @author Administrator
 * @date 2017年6月13日 下午2:47:51
 * @param startDate
 * @param endDate
 * @param platform
 * @return
 * @throws Exception
 */
// @GET
// /biservice/v1/jyt/deal/translate/{startDate}/{endDate}/{platform}/
export function jytTranslate({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/deal/translate/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

/**
 * payChannel:支付渠道金额占比.
 *
 * @author Administrator
 * @date 2017年5月8日 上午11:15:36
 * @param startDate
 * @param endDate
 * @param platform
 * @return
 * @throws Exception
 */
// /biservice/v1/jyt/deal/payChannel/{startDate}/{endDate}/{platform}/
export function jytPayChannel({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/deal/payChannel/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

/**
 * settle:结算方式占比.
 *
 * @author Administrator
 * @date 2017年6月13日 下午3:21:46
 * @param startDate
 * @param endDate
 * @param platform
 * @return
 * @throws Exception
 */

// /biservice/v1/jyt/deal/settle/20170501/20170531/100700/
// {"legendData":["线下支付","直接支付","担保支付"],"seriesName":"结算方式占比","seriesData":[{"value":28,"name":"线下支付","code":"0"},{"value":38,"name":"直接支付","code":"100"},{"value":25,"name":"担保支付","code":"200"}],"title":"结算方式占比"}
export function jytDealSettle({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/deal/settle/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

/**
 * lineType:运输方式
 *
 * @author Administrator
 * @date 2017年6月13日 下午4:04:27
 * @param startDate
 * @param endDate
 * @param platform
 * @return
 * @throws Exception
 */
// /biservice/v1/jyt/deal/lineType/20170501/20170531/100700/
//   { "legendData": ["公路", "海运", "仓储", "空运"], "seriesName": "运输方式", "seriesData": [{ "value": 92, "name": "公路", "code": "231" }, { "value": 1, "name": "海运", "code": "234" }, { "value": 7, "name": "仓储", "code": "235" }, { "value": 1, "name": "空运", "code": "237" }], "title": "运输方式" }
export function jytDealLineType({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/deal/lineType/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

/**
 * orderSource:订单来源.
 *
 * @author Administrator
 * @date 2017年6月14日 上午10:31:31
 * @param startDate
 * @param endDate
 * @param platform
 * @return
 * @throws Exception
 */
//  /biservice/v1/jyt/deal/orderSource/20170501/20170531/100700/
//  {"legendData":["公路","海运","仓储","空运"],"seriesName":"运输方式","seriesData":[{"value":92,"name":"公路","code":"231"},{"value":1,"name":"海运","code":"234"},{"value":7,"name":"仓储","code":"235"},{"value":1,"name":"空运","code":"237"}],"title":"运输方式"}
export function jytDealOrderSource({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/deal/orderSource/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

/**
 * orderType:订单类型.
 *
 * @author Administrator
 * @date 2017年6月14日 下午1:56:38
 * @param startDate
 * @param endDate
 * @param platform
 * @return
 * @throws Exception
 */
// /biservice/v1/jyt/deal/orderType/{startDate}/20170531/100700/
//  /biservice/v1/jyt/deal/orderType/20170501/20170531/100700/
//   { "legendData": ["线上订单"], "seriesName": "订单类型占比", "seriesData": [{ "value": 81, "name": "线上订单", "code": "1" }], "title": "订单类型占比" }
export function jytDealOrderType({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/deal/orderType/${startDate}/${endDate}/${platform}`
  return fetch(url)
}

/**
 * areaAmount:区域金额.
 *
 * @author Administrator
 * @date 2017年6月20日 上午9:57:29
 * @param currencyCode 币种（暂时用不到）
 * @param lineType 线路类型：公路-231,铁路-232,内河航运-233 海运-234 空运-237
 * @param startDate
 * @param endDate
 * @param sortField 排序字段：ordernum 笔数、paytotal 金额
 * @return
 * @throws Exception
 */
//  /biservice/v1/jyt/deal/areaAmount/142/231/20170501/20170531/ordernum/
export function jytDealAreaAmount({
  currencyCode,
  lineType,
  platform,
  startDate,
  endDate,
  sortField
}) {
  let url = `/biservice/v1/jyt/deal/areaAmount/${currencyCode}/${lineType}/${platform}/${startDate}/${endDate}/${sortField}`
  return fetch(url)
}

//  -------------- ↑ 成交分析交易相关 -----------------------------------------------

//  -------------- ↓ 物流商分析 -----------------------------------------------
//  物流方式top-人民币：
// /biservice/v1/lgProvidersAnalysis/lgTypeByRMBTop/platform/102000/startDate/20170601/endDate/20170625/lineType/all
/**
 *
 * @param {*} lineType  --全部: all --公路: 231 --铁路: 232 --内河航运: 233 --海运: 234 --空运:237
 */
export function jytProvidersAnalysisTypeByRMBTop({
  platform,
  startDate,
  endDate,
  way
}) {
  let url = `/biservice/v1/jyt/lgProvidersAnalysis/lgTypeByRMBTop/platform/${platform}/startDate/${startDate}/endDate/${endDate}/lineType/${way}`
  return fetch(url)
}

// 仓储top分析-人民币
// /biservice/v1/lgProvidersAnalysis/warehousingByRMBTop/platform/102000/startDate/20170601/endDate/20170625/wareHouseType/all
export function jytProvidersWarehousingByRMBTop({
  platform,
  startDate,
  endDate,
  way
}) {
  let url = `/biservice/v1/jyt/lgProvidersAnalysis/warehousingByRMBTop/platform/${platform}/startDate/${startDate}/endDate/${endDate}/wareHouseType/${way}`
  return fetch(url)
}


/**
 * areaAmount:区域金额.
 *
 * @author Administrator
 * @date 2017年6月20日 上午9:57:29
 * @param currencyCode 币种 ：142:人民币（暂时用不到）
 * @param lineType 线路类型：公路-231,铁路-232,内河航运-233 海运-234 空运-237
 * @param startDate
 * @param endDate
 * @param sortField 排序字段：ordernum 笔数、ordertotal 金额
 * @return
 * @throws Exception
 */
//  /biservice/v1/jyt/deal/areaAmount/{currencyCode}/{lineType}/{startDate}/{endDate}/{sortField}/
export function jytAreaAmount({
  currencyCode,
  lineType,
  startDate,
  endDate,
  sortField
}) {
  let url = `/biservice/v1/jyt/deal/areaAmount/${currencyCode}/${lineType}/${startDate}/${endDate}/${sortField}`
  return fetch(url)
}

//  -------------- ↓ 物流方式分析 -----------------------------------------------
// 物流货运流向图:
// /biservice/v1/jyt/lgType/freightFlow/platform/102000/startDate/20160101/endDate/20170625/lineType/231
// --公路: 231 --铁路: 232 --内河航运: 233 --海运: 234 --空运:237
export function jytFreightFlow({
  platform,
  startDate,
  endDate,
  way
}) {
  let url = `/biservice/v1/jyt/lgType/freightFlow/platform/${platform}/startDate/${startDate}/endDate/${endDate}/lineType/${way}`
  return fetch(url)
}

// 物流方式分析:
// /biservice/v1/jyt/lgType/lgTypeByRMBTop/platform/102000/startDate/20160101/endDate/20170625/lineType/231
export function jytlgTypeByRMBTop({
  platform,
  startDate,
  endDate,
  way
}) {
  let url = `/biservice/v1/jyt/lgType/lgTypeByRMBTop/platform/${platform}/startDate/${startDate}/endDate/${endDate}/lineType/${way}`
  return fetch(url)
}

// ====用户行为  用户关注   参数与之前保持一致platform平台编码， startDate开始日期， endDate结束日期=====

// 用户搜索关键字排行接口
// /biservice/v1/userattention/wordrank/platform/100400/startDate/20170610/endDate/20170620
export function userattentionWordrank({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/userattention/wordrank/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 用户搜索词云接口
// /biservice/v1/userattention/wordcloud/platform/100400/startDate/20170610/endDate/20170620
export function userattentionWordcloud({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/userattention/wordcloud/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}


// -----------------------------仓储分析-----------------------------

// 仓储分析热力图
// /biservice/v1/jyt/pay/StorageHeat/20150101/20170521
// {"status":{"code":"200","message":"Successful!"},"proportion":[{"value":"2","name":"福建省"},{"value":"1","name":"山东省"},{"value":"13","name":"浙江省"},{"value":"1","name":"贵州省"},{"value":"2","name":"江西省"},{"value":"3","name":"江苏省"},{"value":"1","name":"上海市"},{"value":"2","name":"湖北省"},{"value":"10","name":"天津市"},{"value":"1","name":"吉林省"},{"value":"27","name":"北京市"},{"value":"11","name":"黑龙江省"}],"unit":"个"}
export function jytStorageHeat({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/pay/StorageHeat/${startDate}/${endDate}`
  return fetch(url)
}

// 仓储分析仓库总数、可用面积、总面积
// /biservice/v1/jyt/pay/StorageArea/20150101/20170521
// {"status":{"code":"200","message":"Successful!"},"proportion":[{"可用面积":"2157722.00","总面积":"283923.00","仓库总数":"74"}],"unit":"平方米"}
export function jytStorageArea({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/pay/StorageArea/${startDate}/${endDate}`
  return fetch(url)
}

// 仓库总面积top5
// /biservice/v1/jyt/pay/StorageAreaTop/20150101/20170521
// {"status":{"code":"200","message":"Successful!"},"title":"仓库面积top5","seriesName":"仓库面积","unit":"平方米","xAxisData":[1246035.00,437054.00,318842.00,103600.00,33787.00],"seriesData":["北京市","天津市","浙江省","江苏省","黑龙江省"]}
export function jytStorageAreaTop({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/pay/StorageAreaTop/${startDate}/${endDate}`
  return fetch(url)
}


// 仓库类型（面积）饼图
// /biservice/v1/jyt/pay/StorageTypeTop/20150101/20170521
// {"status":{"code":"200","message":"Successful!"},"proportion":[{"value":"1263527.00","name":"普通"},{"value":"203623.00","name":"危险"},{"value":"280846.00","name":"冷库"},{"value":"75624.00","name":"其他"},{"value":"309084.00","name":"恒温"},{"value":"1401.00","name":"保税"},{"value":"23617.00","name":"海关监管"}],"unit":"平方米"}
export function jytStorageTypeTop({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/pay/StorageTypeTop/${startDate}/${endDate}`
  return fetch(url)
}

// 仓库面积企业排名top5
// /biservice/v1/jyt/pay/StorageCompanyTop/20150101/20170521
// {"status":{"code":"200","message":"Successful!"},"title":"仓库面积top5","seriesName":"仓库面积","unit":"平方米","xAxisData":[1549365.00,411637.00,150000.00,14400.00,12884.00],"seriesData":["杭州***公司","流星***公司","浙江***公司","现代***公司","上海***物流"]}
export function jytStorageCompanyTop({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jyt/pay/StorageCompanyTop/${startDate}/${endDate}`
  return fetch(url)
}

// -------------- ↓ 融资趋势、分析、明细 -----------------------------------------------

// 融资趋势折线图
// type 类型：APPLY(0, "融资申请笔数"), REQUIRE(1, "融资需求笔数"), SIGNED(2, "融资签约笔数")
// /biservice/v1/jrt/financing/trend/lineChart/platform/1025/startDate/20170101/endDate/20170707/type/0
// {"status":{"code":"200","message":"Successful!"},"lineChart":{"legendData":"平台融资趋势","title":"平台融资趋势","xAxisData":["20170701","20170702"],"yAxisData":[],"seriesData":[0,0]}}
export function jrtFinancingTrend({
  type
}) {
  let url = `/biservice/v1/jrt/financing/trend/lineChart/type/${type}`
  return fetch(url)
}


/**
 * type:入驻企业类别饼图.
 *
 * @author Administrator
 * @date 2017年7月5日 上午10:23:41
 * @return
 * @throws Exception
 */
//  /biservice/v1/jrt/company/type/
export function jrtEnrollCompanyType() {
  let url = `/biservice/v1/jrt/company/type`
  return fetch(url)
}

/**
 * productType:上架产品类型饼图.
 *
 * @author Administrator
 * @date 2017年7月5日 上午10:27:00
 * @return
 * @throws Exception
 */
//  /biservice/v1/jrt/company/productType/
export function jrtCompanyProductType() {
  let url = `/biservice/v1/jrt/company/productType`
  return fetch(url)
}


/**
 * area:企业用户区域分布.
 *
 * @author Administrator
 * @date 2017年7月5日 上午10:28:45
 * @return
 * @throws Exception
 */
//  /biservice/v1/jrt/company/area/
export function jrtCompanyArea() {
  let url = `/biservice/v1/jrt/company/area`
  return fetch(url)
}


// ----------------------------总览数据概览-----------------------------
// /biservice/v1/jrt/overview/OperationOverview
export function jrtOperationOverview() {
  let url = `/biservice/v1/jrt/overview/OperationOverview`
  return fetch(url)
}

// ----------------------------业务数据概览-----------------------------
// /biservice/v1/jrt/overview/ServiceDataOverview/20160401/20170701
export function jrtServiceDataOverview({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jrt/overview/ServiceDataOverview/${startDate}/${endDate}`
  return fetch(url)
}

// ----------------------------总览注册转化-----------------------------
// /biservice/v1/jrt/overview/RegistrationConversion/20160401/20170701
// {"status":{"code":"200","message":"Successful!"},"map":{"注册":{"value":"250","Conv":""},"认证或加入公司":{"value":"88","Conv":"35.20"},"提交融资需求":{"value":"26","Conv":"29.54"},"提交融资申请":{"value":"17","Conv":"65.38"},"签约完成":{"value":"11","Conv":"64.70"}}}
export function jrtRegistrationConversion({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jrt/overview/RegistrationConversion/${startDate}/${endDate}`
  return fetch(url)
}


/**
 * enterTrend:入驻企业趋势图.
 * shelfTrend:上架产品区域图.
 *
 * @author Administrator
 * @date 2017年7月5日 上午10:43:50
 * @param startDate
 * @param endDate
 * @param enterpriseType 0:其它;51:工厂;52:贸易商;53:工贸一体
 * @param itemType: enterpriseType(0:其它;51:工厂;52:贸易商;53:工贸一体) or productType(...)
 * @param type enter:入驻企业 shelf:上架产品
 * @return
 * @throws Exception
 */
//  /biservice/v1/jrt/company/enterTrend/{startDate}/{endDate}/{enterpriseType}/
// /biservice/v1/jrt/company/shelfTrend/{startDate}/{endDate}/{productType}/
export function jrtCompanyEnrollOrShelfTrend({
  startDate,
  endDate,
  type,
  itemType
}) {
  let url = `/biservice/v1/jrt/company/${type}Trend/${startDate}/${endDate}/${itemType}`
  return fetch(url)
}

/**
 * shelfTrend:上架产品区域图.
 *
 * @author Administrator
 * @date 2017年7月5日 上午10:49:30
 * @param startDate
 * @param endDate
 * @param productType
 * @return
 * @throws Exception
 */
//  /biservice/v1/jrt/company/shelfTrend/{startDate}/{endDate}/{productType}/

/**
 * shelfProductType:上架产品类型列表.
 *
 * @author Administrator
 * @date 2017年7月11日 上午9:42:28
 * @param startDate
 * @param endDate
 * @return
 * @throws Exception
 */
// /biservice/v1/jrt/company/shelfProductType/{startDate}/{endDate}/
export function jrtCompanyShelfProductType({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jrt/company/shelfProductType/${startDate}/${endDate}`
  return fetch(url)
}

// 融资趋势分析折线图
// type 类型：APPLY(0, "融资申请笔数"), REQUIRE(1, "融资需求笔数"), SIGNED(2, "融资签约笔数")
// /biservice/v1/jrt/financing/trend/analysis/lineChart/platform/1025/startDate/20170101/endDate/20170707/type/0
// {"status":{"code":"200","message":"Successful!"},"lineChart":{"legendData":"平台融资趋势","title":"平台融资趋势","xAxisData":["20170701","20170702"],"yAxisData":[],"seriesData":[0,0]}}
export function jrtFinancingTrendAnalysis({
  platform,
  startDate,
  endDate,
  type
}) {
  let url = `/biservice/v1/jrt/financing/trend/analysis/lineChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}/type/${type}`
  return fetch(url)
}

// 融资明细 表格
// type APPLY(0, "融资申请笔数"), REQUIRE(1, "融资需求笔数"), SIGNED(2, "融资签约笔数")
// /biservice/v1/jrt/financing/trend/detail/limit/10/offset/0/type/0
export function jrtFinancingTrendDetail({
  limit,
  offset,
  type
}) {
  let url = `/biservice/v1/jrt/financing/trend/detail/limit/${limit}/offset/${offset}/type/${type}`
  return fetch(url)
}
