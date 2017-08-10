import Vue from 'vue'
import Router from 'vue-router'


// split code to improve the performance
const Home = resolve => require.ensure([], () => resolve(require('@/views/Home')), 'home')
const Overview = resolve => require.ensure([], () => resolve(require('@/views/Overview')), 'overview')
const AreaOrder = resolve => require.ensure([], () => resolve(require('@/views/AreaOrder')), 'areaOrder')
const Company = resolve => require.ensure([], () => resolve(require('@/views/Company')), 'company')
const ImportExport = resolve => require.ensure([], () => resolve(require('@/views/ImportExport')), 'importExport')

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [{
      path: '/home',
      name: 'home',
      component: Home,
      meta: {
        menuId: '81702'
      }
    },
    {
      path: '/overview',
      name: 'overview',
      component: Overview,
      meta: {
        menuId: '81704'
      }
    },
    {
      path: '/order',
      name: 'order',
      component: AreaOrder,
      meta: {
        menuId: '81705'
      }
    },
    {
      path: '/company',
      name: 'company',
      component: Company,
      meta: {
        menuId: '81706'
      }
    },
    {
      path: '/importExport',
      name: 'importExport',
      component: ImportExport,
      meta: {
        menuId: '81707'
      }
    }
  ]
})
