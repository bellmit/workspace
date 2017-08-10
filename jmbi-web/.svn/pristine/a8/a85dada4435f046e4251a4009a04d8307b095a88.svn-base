import Vue from 'vue'
import Router from 'vue-router'

// split code to improve the performance
const FlowAnalysis = () =>
  import ( /* webpackChunkName: "jyt_flowAnalysis" */ '@/views/JYT/FlowAnalysis')
const loginAnalysis = () =>
  import ( /* webpackChunkName: "jyt_loginAnalysis" */ '@/views/JYT/loginAnalysis')
const userAttension = () =>
  import ( /* webpackChunkName: "jyt_userAttension" */ '@/views/JYT/userAttension')
const Overview = () =>
  import ( /* webpackChunkName: "jyt_overview" */ '@/views/JYT/Overview')
const IncreasedUser = () =>
  import ( /* webpackChunkName: "jyt_IncreasedUser" */ '@/views/JYT/dailyIncreasedUser')
const enterpriseUser = () =>
  import ( /* webpackChunkName: "jyt_enterpriseUser" */ '@/views/JYT/enterpriseUser')
const PayAnalysis = () =>
  import ( /* webpackChunkName: "jyt_PayAnalysis" */ '@/views/JYT/PayAnalysis')
const capacity = () =>
  import ( /* webpackChunkName: "jyt_capacity" */ '@/views/JYT/capacity')
const warehouse = () =>
  import ( /* webpackChunkName: "jyt_warehouse" */ '@/views/JYT/warehouse')
const Dealing = () =>
  import ( /* webpackChunkName: "jyt_Dealing" */ '@/views/JYT/Dealing')
const logistics = () =>
  import ( /* webpackChunkName: "jyt_logistics" */ '@/views/JYT/logistics')
const DealingAnalysis = () =>
  import ( /* webpackChunkName: "jyt_DealingAnalysis" */ '@/views/JYT/DealingAnalysis')
const shipping = () =>
  import ( /* webpackChunkName: "jyt_shipping" */ '@/views/JYT/shipping')



Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [{
    path: '/flow-analysis',
    name: 'flow-analysis',
    component: FlowAnalysis,
    meta: {
      menuId: '81202'
    }
  }, {
    path: '/login-analysis',
    name: 'login-analysis',
    component: loginAnalysis,
    meta: {
      menuId: '81203'
    }
  }, {
    path: '/user-attension',
    name: 'user-attension',
    component: userAttension,
    meta: {
      menuId: '81204'
    }
  }, {
    path: '/overview',
    name: 'overview',
    component: Overview,
    meta: {
      menuId: '81206'
    }
  }, {
    path: '/increased-user',
    name: 'increased-user',
    component: IncreasedUser,
    meta: {
      menuId: '81208'
    }
  }, {
    path: '/enterprise-user',
    name: 'enterprise-user',
    component: enterpriseUser,
    meta: {
      menuId: '81209'
    }
  }, {
    path: '/pay-analysis',
    name: 'pay-analysis',
    component: PayAnalysis,
    meta: {
      menuId: '81210'
    }
  }, {
    path: '/capacity',
    name: 'capacity',
    component: capacity,
    meta: {
      menuId: '81211'
    }
  }, {
    path: '/warehouse',
    name: 'warehouse',
    component: warehouse,
    meta: {
      menuId: '81212'
    }
  }, {
    path: '/dealing',
    name: 'dealing',
    component: Dealing,
    meta: {
      menuId: '81214'
    }
  }, {
    path: '/logistics',
    name: 'logistics',
    component: logistics,
    meta: {
      menuId: '81215'
    }
  }, {
    path: '/dealing-analysis',
    name: 'dealing-analysis',
    component: DealingAnalysis,
    meta: {
      menuId: '81216'
    }
  }, {
    path: '/shipping',
    name: 'shipping',
    component: shipping,
    meta: {
      menuId: '81217'
    }
  }]
})

export default router;
