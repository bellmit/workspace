import Vue from 'vue'
import Router from 'vue-router'

const FlowAnalysis = resolve => require.ensure([], () => resolve(require('@/views/FlowAnalysis')), 'FlowAnalysis')
const loginAnalysis = resolve => require.ensure([], () => resolve(require('@/views/loginAnalysis')), 'loginAnalysis')
const Overview = resolve => require.ensure([], () => resolve(require('@/views/Overview')), 'Overview')
const IncreasedUser = resolve => require.ensure([], () => resolve(require('@/views/IncreasedUser')), 'IncreasedUser')
const AuthenticatedUser = resolve => require.ensure([], () => resolve(require('@/views/AuthenticatedUser')), 'AuthenticatedUser')
const SignaturePay = resolve => require.ensure([], () => resolve(require('@/views/SignaturePay')), 'SignaturePay')
const ShopActivate = resolve => require.ensure([], () => resolve(require('@/views/ShopActivate')), 'ShopActivate')
const ShopOperation = resolve => require.ensure([], () => resolve(require('@/views/ShopOperation')), 'ShopOperation')
const Dealing = resolve => require.ensure([], () => resolve(require('@/views/Dealing')), 'Dealing')
const GeographicalAmount = resolve => require.ensure([], () => resolve(require('@/views/GeographicalAmount')), 'GeographicalAmount')


Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [{
      path: '/flow-analysis',
      name: 'flow-analysis',
      component: FlowAnalysis,
      meta: {
        menuId: '8102'
      }
    },
    {
      path: '/login-analysis',
      name: 'login-analysis',
      component: loginAnalysis,
      meta: {
        menuId: '8103'
      }
    },
    // {
    //   path: '/user-attension',
    //   name: 'user-attension',
    //   component: UserAttension,
    //   meta: {
    //     menuId: '8104'
    //   }
    // },
    {
      path: '/overview',
      name: 'overview',
      component: Overview,
      meta: {
        menuId: '8106'
      }
    },
    {
      path: '/increased-user',
      name: 'increased-user',
      component: IncreasedUser,
      meta: {
        menuId: '8108'
      }
    },
    {
      path: '/authenticated-user',
      name: 'authenticated-user',
      component: AuthenticatedUser,
      meta: {
        menuId: '8109'
      }
    },
    {
      path: '/signature-pay',
      name: 'signature-pay',
      component: SignaturePay,
      meta: {
        menuId: '8116'
      }
    },
    {
      path: '/shop-activate',
      name: 'shop-activate',
      component: ShopActivate,
      meta: {
        menuId: '8111'
      }
    },
    {
      path: '/shop-operation',
      name: 'shop-operation',
      component: ShopOperation,
      meta: {
        menuId: '8112'
      }
    },
    {
      path: '/dealing',
      name: 'dealing',
      component: Dealing,
      meta: {
        menuId: '8114'
      }
    },
    {
      path: '/geographical-amount',
      name: 'geographical-amount',
      component: GeographicalAmount,
      meta: {
        menuId: '8115'
      }
    }
    // {
    //   path: '/',
    //   redirect: '/flow-analysis'
    // }
  ]
})

export default router;
