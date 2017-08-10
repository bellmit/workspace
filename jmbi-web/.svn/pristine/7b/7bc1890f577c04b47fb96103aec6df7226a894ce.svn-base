/**
 * Created by Administrator on 2017/7/18.
 */

import {
  fetch
} from './baseApi.config'

// -------------- ↓ 总览 -----------------------------------------------

// /biservice/v1/enjyt/overview/numbers/itemIds/1-2-3/platform/1025/startDate/20160501/endDate/20170701
export function enjytOverview({
                                platform,
                                startDate,
                                endDate,
                                itemIds
                              }) {

  let url = `/biservice/v1/enjyt/overview/numbers/itemIds/${itemIds}/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 总览折线图
// /biservice/v1/enjyt/overview/lineChart/itemId/1/platform/1025/startDate/20160501/endDate/20170701
export function enjytOverviewLineChart({
                                       platform,
                                       startDate,
                                       endDate,
                                       itemId
                                     }) {

  let url = `/biservice/v1/enjyt/overview/lineChart/itemId/${itemId}/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 总览物流线路 饼图
// /biservice/v1/enjyt/overview/transportLine/pieChart/platform/1025/startDate/20160501/endDate/20170701
export function enjytOverviewTransportLine({
                                           platform,
                                           startDate,
                                           endDate
                                         }) {

  let url = `/biservice/v1/enjyt/overview/transportLine/pieChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}


// 需求发布数 top5
// /biservice/v1/enjyt/overview/requireTop5/barChart/platform/1025/startDate/20160501/endDate/20170701
export function enjytOverviewRequireTop5({
                                         platform,
                                         startDate,
                                         endDate
                                       }) {

  let url = `/biservice/v1/enjyt/overview/requireTop5/barChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}
// -------------- ↑ 总览 -----------------------------------------------

// -------------- ↓ 注册分析 日新增用户 -----------------------------------------------
// 新增注册数 折线图
// /biservice/v1/enjyt/register/newlyIncr/lineChart/platform/1025/startDate/20160501/endDate/20170701
export function enjytRegisterNewlyIncr({
                                       platform,
                                       startDate,
                                       endDate
                                     }) {

  let url = `/biservice/v1/enjyt/register/newlyIncr/lineChart/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}


// -------------- ↑ 注册分析 日新增用户 -----------------------------------------------


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
