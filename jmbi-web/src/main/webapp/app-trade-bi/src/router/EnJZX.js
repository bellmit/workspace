import Vue from 'vue'
import Router from 'vue-router'
import {
  getRoutes
} from './routerHelper'

Vue.use(Router)

let routeData = [{
    menuId: '91602',
    path: 'flow-analysis'
  },
  {
    menuId: '91603',
    path: 'login-analysis'
  },
  {
    menuId: '91605',
    path: 'operate-analysis'
  }
]

const router = new Router({
  mode: 'history',
  routes: getRoutes(routeData, 'EnJZX')
})

export default router;
