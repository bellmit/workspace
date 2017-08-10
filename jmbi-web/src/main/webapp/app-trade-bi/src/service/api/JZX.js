import {
  fetch
} from './baseApi.config'

// ----------------------总览----------------------
// 运营数据概览
// /biservice/v1/jzx/overview/OperationOverview
// {"status":{"code":"200","message":"Successful!"},"data":[{"name":"入驻企业","value":"279","unit":"家"},{"name":"已服务企业","value":"58","unit":"家"},{"name":"入驻服务机构","value":"147","unit":"家"},{"name":"上架服务","value":"68","unit":"个"},{"name":"项目订单","value":"270","unit":"笔"},{"name":"合同金额","value":"1238892.35","unit":"万元"}]}
export function jzxOperationOverview() {
  let url = `/biservice/v1/jzx/overview/OperationOverview`
  return fetch(url)
}

// 项目订单分析
// 新增项目订单
// /biservice/v1/jzx/overview/newProjectOrder/20170101/20171201
// {"status":{"code":"200","message":"Successful!"},"total":"207","proportion":[{"name":"企业服务管理","value":"176"},{"name":"市场营销服务","value":"2"},{"name":"综合咨询服务","value":"29"}],"unit":"笔","titel":"新增项目订单"}
export function jzxNewProjectOrder({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jzx/overview/newProjectOrder/${startDate}/${endDate}`
  return fetch(url)
}

// 市场营销订单  各行业占比
// /biservice/v1/jzx/overview/industryShare/20170101/20171201
// {"status":{"code":"200","message":"Successful!"},"total":"","proportion":[{"name":"fdsafsdafdsa","value":"1"},{"name":"发声大方的撒","value":"1"},{"name":"3333","value":"1"},{"name":"ceshidata002","value":"1"},{"name":"测试数据请忽略2","value":"1"},{"name":"ceshi0329","value":"1"},{"name":"测试数据庆丰","value":"1"},{"name":"fsafdsafasfdsaf","value":"1"},{"name":"fdsa","value":"1"},{"name":"测试数据004","value":"1"},{"name":"而我热舞热舞","value":"2"},{"name":"财务行业","value":"5"},{"name":"测试行业","value":"6"},{"name":"工业品行业","value":"8"},{"name":"IT行业","value":"15"},{"name":"高端服饰行业","value":"16"},{"name":"跨境贸易行业","value":"56"},{"name":"liyuan99980","value":"58"}],"unit":"","titel":"市场营销订单  各行业占比"}
export function jzxIndustryShare({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jzx/overview/industryShare/${startDate}/${endDate}`
  return fetch(url)
}

// 市场营销订单  各细分服务类型占比
// /biservice/v1/jzx/overview/TypeRatio/20170101/20171201
// {"status":{"code":"200","message":"Successful!"},"total":"","proportion":[{"name":"战略咨询","value":"15"},{"name":"精益管理","value":"8"},{"name":"财务咨询","value":"68"},{"name":"企业培训","value":"16"},{"name":"法律咨询","value":"69"}],"unit":"","titel":"市场营销订单  各细分服务类型占比"}
export function jzxTypeRatio({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jzx/overview/TypeRatio/${startDate}/${endDate}`
  return fetch(url)
}

// 新增注册用户
// /biservice/v1/jzx/overview/newRegisteredUser/20170101/20171201
// {"status":{"code":"200","message":"Successful!"},"total":"216","proportion":[{"name":"咨询需求方","value":"189"},{"name":"咨询服务商","value":"24"},{"name":"其他","value":"3"}],"unit":"个","titel":"新增注册用户"}
export function jzxNewRegisteredUser({
  startDate,
  endDate
}) {
  let url = `/biservice/v1/jzx/overview/newRegisteredUser/${startDate}/${endDate}`
  return fetch(url)
}

//访客地域分布（世界地图）和地域流量榜单
//http://jmbi-service.jm.com/biservice/v1/baidu/world/platform/100200/startDate/201601321/endDate/20171222
// {"status":{"code":"200","message":"Successful!"},"data":[{"typeOrId":-1,"name":"巴西","value":"19"},{"typeOrId":-1,"name":"泰国","value":"36"},{"typeOrId":-1,"name":"委内瑞拉","value":"13"},{"typeOrId":-1,"name":"新西兰","value":"24"},{"typeOrId":-1,"name":"柬埔寨","value":"24"},{"typeOrId":-1,"name":"莫桑比克","value":"1"},{"typeOrId":-1,"name":"伊拉克","value":"4"},{"typeOrId":-1,"name":"加纳","value":"1"},{"typeOrId":-1,"name":"越南","value":"97"},{"typeOrId":-1,"name":"阿根廷","value":"9"},{"typeOrId":-1,"name":"摩尔多瓦","value":"1"},{"typeOrId":-1,"name":"纳米比亚","value":"1"},{"typeOrId":-1,"name":"美国","value":"607"},{"typeOrId":-1,"name":"肯尼亚","value":"2"},{"typeOrId":-1,"name":"卡塔尔","value":"1"},{"typeOrId":-1,"name":"乌兹别克斯坦","value":"1"},{"typeOrId":-1,"name":"韩国","value":"285"},{"typeOrId":-1,"name":"其他","value":"250"},{"typeOrId":-1,"name":"哥伦比亚","value":"3"},{"typeOrId":-1,"name":"巴拿马","value":"5"},{"typeOrId":-1,"name":"德国","value":"14"},{"typeOrId":-1,"name":"立陶宛","value":"1"},{"typeOrId":-1,"name":"吉尔吉斯斯坦","value":"2"},{"typeOrId":-1,"name":"老挝","value":"12"},{"typeOrId":-1,"name":"埃塞俄比亚","value":"1"},{"typeOrId":-1,"name":"印度","value":"40"},{"typeOrId":-1,"name":"英国","value":"46"},{"typeOrId":-1,"name":"瑞士","value":"3"},{"typeOrId":-1,"name":"伊朗","value":"12"},{"typeOrId":-1,"name":"奥地利","value":"2"},{"typeOrId":-1,"name":"意大利","value":"26"},{"typeOrId":-1,"name":"白俄罗斯","value":"7"},{"typeOrId":-1,"name":"土耳其","value":"12"},{"typeOrId":-1,"name":"阿尔及利亚","value":"5"},{"typeOrId":-1,"name":"斯洛伐克","value":"2"},{"typeOrId":-1,"name":"比利时","value":"3"},{"typeOrId":-1,"name":"多米尼加","value":"1"},{"typeOrId":-1,"name":"埃及","value":"5"},{"typeOrId":-1,"name":"拉脱维亚","value":"1"},{"typeOrId":-1,"name":"巴巴多斯","value":"2"},{"typeOrId":-1,"name":"爱尔兰","value":"1"},{"typeOrId":-1,"name":"中国","value":"163584"},{"typeOrId":-1,"name":"阿尔巴尼亚","value":"2"},{"typeOrId":-1,"name":"澳大利亚","value":"82"},{"typeOrId":-1,"name":"印度尼西亚","value":"28"},{"typeOrId":-1,"name":"马其顿","value":"1"},{"typeOrId":-1,"name":"坦桑尼亚","value":"1"},{"typeOrId":-1,"name":"毛里求斯","value":"1"},{"typeOrId":-1,"name":"塞尔维亚","value":"1"},{"typeOrId":-1,"name":"巴勒斯坦","value":"1"},{"typeOrId":-1,"name":"阿曼","value":"2"},{"typeOrId":-1,"name":"巴基斯坦","value":"9"},{"typeOrId":-1,"name":"卢森堡","value":"1"},{"typeOrId":-1,"name":"黎巴嫩","value":"1"},{"typeOrId":-1,"name":"哥斯达黎加","value":"1"},{"typeOrId":-1,"name":"尼泊尔","value":"1"},{"typeOrId":-1,"name":"罗马尼亚","value":"8"},{"typeOrId":-1,"name":"沙特阿拉伯","value":"4"},{"typeOrId":-1,"name":"秘鲁","value":"10"},{"typeOrId":-1,"name":"厄瓜多尔","value":"4"},{"typeOrId":-1,"name":"阿拉伯联合酋长国","value":"4"},{"typeOrId":-1,"name":"安哥拉","value":"2"},{"typeOrId":-1,"name":"以色列","value":"6"},{"typeOrId":-1,"name":"斐济","value":"2"},{"typeOrId":-1,"name":"墨西哥","value":"16"},{"typeOrId":-1,"name":"危地马拉","value":"1"},{"typeOrId":-1,"name":"希腊","value":"2"},{"typeOrId":-1,"name":"亚美尼亚","value":"3"},{"typeOrId":-1,"name":"捷克","value":"3"},{"typeOrId":-1,"name":"保加利亚","value":"3"},{"typeOrId":-1,"name":"匈牙利","value":"3"},{"typeOrId":-1,"name":"波兰","value":"3"},{"typeOrId":-1,"name":"法国","value":"16"},{"typeOrId":-1,"name":"俄罗斯","value":"74"},{"typeOrId":-1,"name":"斯里兰卡","value":"2"},{"typeOrId":-1,"name":"荷兰","value":"7"},{"typeOrId":-1,"name":"哈萨克斯坦","value":"10"},{"typeOrId":-1,"name":"斯洛文尼亚","value":"1"},{"typeOrId":-1,"name":"瑞典","value":"7"},{"typeOrId":-1,"name":"摩洛哥","value":"6"},{"typeOrId":-1,"name":"西班牙","value":"19"},{"typeOrId":-1,"name":"缅甸","value":"5"},{"typeOrId":-1,"name":"乌克兰","value":"26"},{"typeOrId":-1,"name":"乌拉圭","value":"1"},{"typeOrId":-1,"name":"突尼斯","value":"3"},{"typeOrId":-1,"name":"智利","value":"7"},{"typeOrId":-1,"name":"乌干达","value":"2"},{"typeOrId":-1,"name":"加拿大","value":"119"},{"typeOrId":-1,"name":"日本","value":"39"},{"typeOrId":-1,"name":"爱沙尼亚","value":"2"},{"typeOrId":-1,"name":"南非","value":"3"},{"typeOrId":-1,"name":"马来西亚","value":"105"},{"typeOrId":-1,"name":"葡萄牙","value":"7"},{"typeOrId":-1,"name":"新加坡","value":"35"},{"typeOrId":-1,"name":"津巴布韦","value":"1"},{"typeOrId":-1,"name":"塞内加尔","value":"2"},{"typeOrId":-1,"name":"菲律宾","value":"28"},{"typeOrId":-1,"name":"孟加拉国","value":"7"}],"max":null,"table":[{"country":"中国","pv":278872,"uv":163584,"ip":159527},{"country":"美国","pv":830,"uv":607,"ip":601},{"country":"韩国","pv":411,"uv":285,"ip":277},{"country":"其他","pv":348,"uv":250,"ip":241},{"country":"加拿大","pv":165,"uv":119,"ip":119},{"country":"累计汇总","pv":282082,"uv":165906,"ip":161817}]}
export function jzxVisitorMapFlow({
  platform,
  startDate,
  endDate
}){
  let url = `/biservice/v1/baidu/world/platform/${platform}/startDate/${startDate}/endDate/${endDate}`
  return fetch(url)
}

// 意向单
// /biservice/v1/jzx/businessTrend/intentionOrder/platform/102000/startDate/20170604/endDate/20170623/dateType/D
// {"status":{"code":"200","message":"Successful!"},"lineChart":{"legendData":"意向单","title":"意向单","xAxisData":["2017/06/04","2017/06/05","2017/06/06","2017/06/07","2017/06/08","2017/06/09","2017/06/10","2017/06/11","2017/06/12","2017/06/13","2017/06/14","2017/06/15","2017/06/16","2017/06/17","2017/06/18","2017/06/19","2017/06/20","2017/06/21","2017/06/22","2017/06/23"],"seriesData":[0,6,0,1,0,15,0,0,20,6,5,25,13,0,0,6,1,0,0,0],"unit":"笔"}}
export function jzxIntentionOrder({
  service,
  platform,
  startDate,
  endDate,
  dateType
}) {
  // let url = `/biservice/v1/jzx/businessTrend/intentionOrder/platform/${platform}/startDate/${startDate}/endDate/${endDate}/dateType/${dateType}`
  let url = `/biservice/v1/jzx/businessTrend/${service}/platform/${platform}/startDate/${startDate}/endDate/${endDate}/dateType/${dateType}`
  return fetch(url)
}
