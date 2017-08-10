// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'normalize.css'
import '@/assets/style.scss'

import Vue from 'vue'

import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-default/index.css'
import './theme/index.css'
import App from './App'
import router from './router'

Vue.use(ElementUI)

Vue.config.productionTip = false

let visibleMenuList = document.querySelector('#visibleMenuList').value.split(',')

router.beforeEach((to, from, next) => {

  if (to.matched.some(record => visibleMenuList.indexOf(record.meta.menuId) !== -1)) {
    // 有匹配到有权限访问的页面
    next()
  } else {

    // 如果没有匹配到相应页面
    // 则重定向到第一个的有权限访问的页面    
    if (visibleMenuList.length) {
      let routes = router.options.routes;
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
  router,
  template: '<App/>',
  components: {
    App
  }
})
