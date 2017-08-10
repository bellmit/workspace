import {
  tradePlatformIds
} from '@/data/platform.config.js'

// 交易BI
import Router_Trade from './Trade'
// 聚贸总站BI
import Router_100100 from './ZZ'
// 聚运通BI
import Router_100400 from './JYT'
// 聚融通BI
import Router_101500 from './JRT'
// 聚咨询BI
import Router_101900 from './JZX'
// 聚融通英文BI
import Router_101501 from './EnJRT'
// 聚咨询英文BI
import Router_101901 from './EnJZX'

// 聚运通英文BI
import Router_100401 from './EnJYT'

let routers = {
  Router_Trade,
  Router_100100,
  Router_100400,
  Router_100401,//聚运通英文BI
  Router_101500,
  Router_101900,
  Router_101501,
  Router_101901
}

export function getRouter(platformId) {
  if (tradePlatformIds.indexOf(platformId) !== -1) {
    return routers.Router_Trade
  }

  return routers["Router_" + platformId]
}
