// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'normalize.css'
import '@/assets/style.scss'

import Vue from 'vue'

import Public from '@/service/public.js'

import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-default/index.css'
import './theme/index.css'
import App from './App'

import {
  requestCancels
} from '@/service/api/baseApi.config'

import {
  getRouter
} from './router'

Vue.use(Public)

Vue.use(ElementUI)

Vue.config.productionTip = false

let visibleMenuList = document.querySelector('#visibleMenuList').value.split(',')

let currentPlatformId = document.querySelector('#currentPlatformId').value
let currentPlatformName = document.querySelector('#currentPlatformName').value

Vue.prototype.$platformId = currentPlatformId
Vue.prototype.$platformName = currentPlatformName

let currentRouter = getRouter(currentPlatformId);

currentRouter.beforeEach((to, from, next) => {

  while (requestCancels.length) {
    var cancel = requestCancels.pop();
    cancel();
  }

  if (to.matched.some(record => visibleMenuList.indexOf(record.meta.menuId) !== -1)) {
    // 有匹配到有权限访问的页面
    next()
  } else {

    // 如果点击的是父节点：注册分析（2-1），店铺分析（2-2），成交分析（2-3）
    if (["/2-1", "/2-2", "/2-3"].indexOf(to.path) !== -1) {
      next(false)
      return;
    }

    // 如果没有匹配到相应页面
    // 则重定向到第一个的有权限访问的页面
    if (visibleMenuList.length) {
      let routes = currentRouter.options.routes;
      for (let index = 0; index < routes.length; index++) {
        if (visibleMenuList.indexOf(routes[index].meta.menuId) !== -1) {
          next(routes[index].path)
          break;
        }
      }
    }

  }

  next(false)

})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: currentRouter,
  template: '<App/>',
  components: {
    App
  }
})
