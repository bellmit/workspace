import Vue from 'vue'
import Router from 'vue-router'

// split code to improve the performance
const FlowAnalysis = () =>
  import ( /* webpackChunkName: "jrt_flowAnalysis" */ '@/views/EnJRT/FlowAnalysis')
const loginAnalysis = () =>
  import ( /* webpackChunkName: "jrt_loginAnalysis" */ '@/views/EnJRT/loginAnalysis')
const Overview = () =>
  import ( /* webpackChunkName: "jrt_Overview" */ '@/views/EnJRT/Overview')
const business = () =>
  import ( /* webpackChunkName: "jrt_business" */ '@/views/EnJRT/business')


Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [{
    path: '/flow-analysis',
    name: 'flow-analysis',
    component: FlowAnalysis,
    meta: {
      menuId: '91302'
    }
  }, {
    path: '/login-analysis',
    name: 'login-analysis',
    component: loginAnalysis,
    meta: {
      menuId: '91303'
    }
  }, {
    path: '/overview',
    name: 'overview',
    component: Overview,
    meta: {
      menuId: '91305'
    }
  }, {
    path: '/business',
    name: 'business',
    component: business,
    meta: {
      menuId: '91306'
    }
  }]
})

export default router;
