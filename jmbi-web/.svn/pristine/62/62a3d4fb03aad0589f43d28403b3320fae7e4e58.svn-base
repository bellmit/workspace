/**
 * Created by Administrator on 2017/7/18.
 */

import {
  fetch
} from './baseApi.config'

// ----------------------------总览数据概览-----------------------------
// /biservice/v1/jrt/overview/OperationOverview
export function enjrtOperationOverview() {
  let url = `/biservice/v1/enjrt/overview/operationOverview`
  return fetch(url)
}

// ----------------------------业务数据概览-----------------------------
// /biservice/v1/jrt/overview/ServiceDataOverview/20160401/20170701
export function enjrtServiceDataOverview({
                                           startDate,
  endDate
                                         }) {
  let url = `/biservice/v1/enjrt/overview/serviceDataOverview/${startDate}/${endDate}`
  return fetch(url)
}

// ----------------------------总览注册转化-----------------------------
// /biservice/v1/jrt/overview/RegistrationConversion/20160401/20170701
// {"status":{"code":"200","message":"Successful!"},"map":{"注册":{"value":"250","Conv":""},"认证或加入公司":{"value":"88","Conv":"35.20"},"提交融资需求":{"value":"26","Conv":"29.54"},"提交融资申请":{"value":"17","Conv":"65.38"},"签约完成":{"value":"11","Conv":"64.70"}}}
export function enjrtRegistrationConversion({
                                              startDate,
  endDate
                                            }) {
  let url = `/biservice/v1/enjrt/overview/registrationConversion/${startDate}/${endDate}`
  return fetch(url)
}

// 融资趋势折线图
// type:RZSQ(0, "融资申请"), RZQYue(1, "融资签约"), RZQYe(2, "入驻企业"), SJCP(3, "上架产品"), ELSE(999, "其他");
// {"status":{"code":"200","message":"Successful!"},"lineChart":{"legendData":"平台融资趋势","title":"平台融资趋势","xAxisData":["20170701","20170702"],"yAxisData":[],"seriesData":[0,0]}}
export function enjrtFinancingTrend({
                                      type
                                    }) {
  let url = `/biservice/v1/enjrt/financing/trend/lineChart/type/${type}`
  return fetch(url)
}

// 区域分布
// type 类型：APPLY(0, "融资申请笔数"), REQUIRE(1, "融资需求笔数"), SIGNED(2, "融资签约笔数")
// /biservice/v1/enjrt/financing/areaDist/global/platform/1025/startDate/20170101/endDate/20170707/type/0
// {"status":{"code":"200","message":"Successful!"},"pieChart":{"legendData":["Canada","Russian Federation"],"seriesData":[{"typeOrId":38,"name":"Canada","value":"5"},{"typeOrId":178,"name":"Russian Federation","value":"28"}]},"counts":286,"nowDate":"2017-07-14"}
export function enjrtAreaDistAnalysis({
                                        platform,
  startDate,
  endDate,
  type
                                      }) {
  let url = `/biservice/v1/enjrt/financing/areaDist/global/platform/${platform}/startDate/${startDate}/endDate/${endDate}/type/${type}`
  return fetch(url)
}

// 融资明细 表格
// type:RZSQ(0, "融资申请"), RZQYue(1, "融资签约"), RZQYe(2, "入驻企业")
export function enjrtFinancingTrendDetail({
                                            areaId,
  platform,
  startDate,
  endDate,
  limit,
  offset,
  type
                                          }) {
  let url = `/biservice/v1/enjrt/financing/area/${areaId}/detail/platform/${platform}/startDate/${startDate}/endDate/${endDate}/limit/${limit}/offset/${offset}/type/${type}`
  return fetch(url)
}

// 总览的 区域分布 世界地图
// type:RZSQ(0, "融资申请"), RZQYue(1, "融资签约"), RZQYe(2, "入驻企业"), SJCP(3, "上架产品"), ELSE(999, "其他");
// {"status":{"code":"200","message":"Successful!"},"lineChart":{"legendData":"平台融资趋势","title":"平台融资趋势","xAxisData":["20170701","20170702"],"yAxisData":[],"seriesData":[0,0]}}
export function enjrtCompanyArea({
                                      type
                                    }) {
  let url = `/biservice/v1/enjrt/financing/areaDist/global/type/${type}`
  return fetch(url)
}


export function enjrtCompanyEnrollOrShelfTrend({
                                                 platform,
  startDate,
  endDate,
  type
                                               }) {
  let url = `/biservice/v1/enjrt/financing/trend/analysis/lineChart/platform/1025/startDate/${startDate}/endDate/${endDate}/type/${type}`
  return fetch(url)
}


/**
 * 数据概览vj
 */
// /biservice/v1/en/netflow/overview/platform/101501/startDate/20170101/endDate/20170707
export function _enjrtOperationOverview({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/en/netflow/overview/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}
/**
 * 新老访客vj
 * @param {*} platform
 * @param {*} startDate
 * @param {*} endDate
 */
export function home_getVistors({
  platform,
  startDate,
  endDate
}) {
  // /biservice/v1/baidu/vistors/platform/102000/startDate/20170101/endDate/20170301
  //{"code":"200","message":"Successful!","data":[{"id":1,"name":"pv","value":"8"},{"id":2,"name":"uv","value":"7"},{"id":3,"name":"bounce_rate","value":"91.67%"},{"id":4,"name":"avg_session_dur","value":"1.25%"}],"nowDate":"2017-07-28"}
  let url = `/biservice/v1/baidu/vistors/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}


/**
 * 趋势分析折线图vj
 * @param {*} platform
 * @param {*} startDate
 * @param {*} endDate
 * @param {*} type
 */
// type:PV(0, "浏览量"), UV(1, "独立访客"), AVG_SESSION_DUR(2, "平均停留时间"),AVG_UV(3, "访问深度"),NEW_USERS(4, "新访客"), BOUNCE_RATE(5, "跳出率"), SESSIONS(6, "访问次数");
//{"status":{"code":"200","message":"Successful!"},"lineChart":{"legendData":"趋势分析","title":"趋势分析","xAxisData":["20170701","20170702"],"yAxisData":[],"seriesData":[0,0]}}
export function en_jrtOverviewLineChart({
  platform,
  startDate,
  endDate,
  ItemId,
}) {
  let url = `/biservice/v1/en/netflow/trend/lineChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}/type/${ItemId}`
  return fetch(url)
}
/**
 * 区域分布 世界地图vj
 * @param {*} platform
 * @param {*} startDate
 * @param {*} endDate
 */
//{"status":{"code":"200","message":"Successful!"},"pieChart":{"legendData":["China"],"seriesData":[{"typeOrId":"China","name":"China","value":"8"}]},"nowDate":"2017-07-28"}
export function home_enjrtCompanyArea({
  platform,
  startDate,
  endDate,
}) {
  let url = `/biservice/v1/en/netflow/areaDist/global/pv/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

/**
 * 页面流量(uv) top5 vj
 * @param {*} platform
 * @param {*} startDate
 * @param {*} endDate
 */
//{"status":{"code":"200","message":"Successful!"},"lineChart":{"legendData":"页面流量排名","title":"页面流量排名","yAxisData":["/login"],"seriesData":[67]}}export function enjrtRegionalDistribution({
export function home_pageVisit({
  platform,
  startDate,
  endDate,
}) {
  let url = `/biservice/v1/en/netflow/pv/barChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 跳出(uv) top5
// /biservice/v1/en/netflow/bounce/barChart/platform/101501/startDate/20170101/endDate/20170807
export function home_pageBounce({
  platform,
  startDate,
  endDate,
}) {
  let url = `/biservice/v1/en/netflow/bounce/barChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

/**
 * 访问来源  饼图vj
 * @param {*} platform
 * @param {*} startDate
 * @param {*} endDate
 */
export function home_getSources({
  platform,
  startDate,
  endDate
}) {
  let url = `/biservice/v1/en/netflow/sourceMedium/uv/pieChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

/**
 * 访问来源  折线图vj
 * @param {*} platform
 * @param {*} startDate
 * @param {*} endDate
 * @param {*} type
 */
export function home_getLineSources({
  platform,
  startDate,
  endDate,
  type,
}) {
  let url = `/biservice/v1/en/netflow/sourceMedium/lineChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}/type/${type}`
  return fetch(url)
}
