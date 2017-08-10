import Vue from 'vue'
import Router from 'vue-router'
import {
  getRoutes
} from './routerHelper'

Vue.use(Router)

let routeData = [{
  menuId: '91202',//流量分析
  path: 'flow-analysis'
},
  {
    menuId: '91203',//登入分析
    path: 'login-analysis'
  },
  {
    menuId: '91204',//用户关注
    path: 'user-attension'
  },
  {
    menuId: '91206',//总览
    path: 'overview'
  },
  {
    menuId: '91208',//日新增用户
    path: 'increased-user'
  },
  {
    menuId: '91209',//企业用户
    path: 'enterprise-user'
  },
  {
    menuId: '91210',//支付分析
    path: 'pay-analysis'
  },
  {
    menuId: '91211',//运力分析
    path: 'capacity'
  },
  {
    menuId: '91212',//仓储分析
    path: 'warehouse'
  },
  {
    menuId: '91214',//成交概览
    path: 'dealing'
  },
  {
    menuId: '91215',//物流商分析
    path: 'logistics'
  },
  {
    menuId: '91216',//交易地域分析
    path: 'dealing-analysis'
  },
  {
    menuId: '91217',//物流方式分析
    path: 'shipping'
  }
]

const router = new Router({
  mode: 'history',
  routes: getRoutes(routeData, 'EnJYT')
})

export default router;
