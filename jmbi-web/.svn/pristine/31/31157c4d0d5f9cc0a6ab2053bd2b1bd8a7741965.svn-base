import Vue from 'vue'
import Router from 'vue-router'

// split code to improve the performance
const FlowAnalysis = () =>
  import ( /* webpackChunkName: "jrt_flowAnalysis" */ '@/views/JRT/FlowAnalysis')
const loginAnalysis = () =>
  import ( /* webpackChunkName: "jrt_loginAnalysis" */ '@/views/JRT/loginAnalysis')
const Overview = () =>
  import ( /* webpackChunkName: "jrt_Overview" */ '@/views/JRT/Overview')
const business = () =>
  import ( /* webpackChunkName: "jrt_business" */ '@/views/JRT/business')


Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [{
    path: '/flow-analysis',
    name: 'flow-analysis',
    component: FlowAnalysis,
    meta: {
      menuId: '81302'
    }
  }, {
    path: '/login-analysis',
    name: 'login-analysis',
    component: loginAnalysis,
    meta: {
      menuId: '81303'
    }
  }, {
    path: '/overview',
    name: 'overview',
    component: Overview,
    meta: {
      menuId: '81305'
    }
  }, {
    path: '/business',
    name: 'business',
    component: business,
    meta: {
      menuId: '81306'
    }
  }]
})

export default router;
