import Vue from 'vue'
import Router from 'vue-router'

// split code to improve the performance
const FlowAnalysis = () => import('@/views/ZZTrade/FlowAnalysis')
const AttensionAnalysis = () => import('@/views/ZZTrade/AttensionAnalysis')
const loginAnalysis = () => import('@/views/ZZTrade/loginAnalysis')
const DealingOverall = () => import('@/views/ZZTrade/DealingOverall')
const DealingAnalysis = () => import('@/views/ZZTrade/DealingAnalysis')
const NationalPavilion = () => import('@/views/ZZTrade/NationalPavilion')
const ProvincialPavilion = () => import('@/views/ZZTrade/ProvincialPavilion')
const BrandPavilion = () => import('@/views/ZZTrade/BrandPavilion')
const BeltAndRoad = () => import('@/views/ZZTrade/BeltAndRoad')
const TUV = () => import('@/views/ZZTrade/TUV')
const Overview = () => import('@/views/ZZTrade/Overview')
const RegisterAnalysis = () => import('@/views/ZZTrade/RegisterAnalysis')
const GoodsAnalysis = () => import('@/views/ZZTrade/GoodsAnalysis')
const ShopOperateAnalysis = () => import('@/views/ZZTrade/ShopOperateAnalysis')


Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [{
      path: '/flow-analysis',
      name: 'flow-analysis',
      component: FlowAnalysis,
      meta: {
        menuId: '8002'
      }
    },
    {
      path: '/attension-analysis',
      name: 'attension-analysis',
      component: AttensionAnalysis,
      meta: {
        menuId: '8003'
      }
    },
    {
      path: '/login-analysis',
      name: 'login-analysis',
      component: loginAnalysis,
      meta: {
        menuId: '8004'
      }
    },

    {
      path: '/overview',
      name: 'overview',
      component: Overview,
      meta: {
        menuId: '8006'
      }
    },
    {
      path: '/register-analysis',
      name: 'register-analysis',
      component: RegisterAnalysis,
      meta: {
        menuId: '8007'
      }
    },
    {
      path: '/goods-analysis',
      name: 'goods-analysis',
      component: GoodsAnalysis,
      meta: {
        menuId: '8008'
      }
    },
    {
      path: '/shop-operate-analysis',
      name: 'shop-operate-analysis',
      component: ShopOperateAnalysis,
      meta: {
        menuId: '8009'
      }
    },
    {
      path: '/dealing-overall',
      name: 'dealing-overall',
      component: DealingOverall,
      meta: {
        menuId: '8011'
      }
    },
    {
      path: '/dealing-analysis',
      name: 'dealing-analysis',
      component: DealingAnalysis,
      meta: {
        menuId: '8012'
      }
    },
    {
      path: '/national-pavilion',
      name: 'national-pavilion',
      component: NationalPavilion,
      meta: {
        menuId: '8013'
      }
    },
    {
      path: '/provincial-pavilion',
      name: 'provincial-pavilion',
      component: ProvincialPavilion,
      meta: {
        menuId: '8014'
      }
    },
    {
      path: '/brand-pavilion',
      name: 'brand-pavilion',
      component: BrandPavilion,
      meta: {
        menuId: '8015'
      }
    },
    {
      path: '/belt-and-road',
      name: 'belt-and-road',
      component: BeltAndRoad,
      meta: {
        menuId: '8016'
      }
    },
    {
      path: '/tuv',
      name: 'tuv',
      component: TUV,
      meta: {
        menuId: '8017'
      }
    }
  ]
})

export default router;
