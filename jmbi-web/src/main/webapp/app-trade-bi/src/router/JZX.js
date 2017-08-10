import Vue from 'vue'
import Router from 'vue-router'
import {
  getRoutes
} from './routerHelper'

Vue.use(Router)

let routeData = [{
    menuId: '81602',
    path: 'flow-analysis'
  },
  {
    menuId: '81603',
    path: 'source-analysis'
  },
  {
    menuId: '81604',
    path: 'login-analysis'
  },
  {
    menuId: '81606',
    path: 'operate-analysis'
  }
]

const router = new Router({
  mode: 'history',
  routes: getRoutes(routeData, 'JZX')
})

export default router;
