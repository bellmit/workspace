import {
  tradePlatformIds
} from '@/data/platform.config.js'

// 交易BI
import MenuData_Trade from '@/data/tradeMenu.json'
// 聚贸总站
import MenuData_100100 from '@/data/ZZMenu.json'
// 聚运通BI
import MenuData_100400 from '@/data/JYTMenu.json'
// 聚融通BI
import MenuData_101500 from '@/data/JRTMenu.json'
// 聚咨询BI
import MenuData_101900 from '@/data/JZXMenu.json'




// 聚融通英文版BI
import MenuData_101501 from '@/data/EnJRTMenu.json'
// 聚咨询英文版BI
import MenuData_101901 from '@/data/EnJZXMenu.json'

// 聚运通英文版BI
import MenuData_100401 from '@/data/EnJYTMenu.json'

const menuData = {
  MenuData_Trade,
  MenuData_100100,
  MenuData_100400,
  MenuData_100401,//聚运通英文版
  MenuData_101500,
  MenuData_101900,
  MenuData_101501,
  MenuData_101901
}

export function getMenuData(platformId) {
  if (tradePlatformIds.indexOf(platformId) !== -1) {
    return menuData.MenuData_Trade
  }

  return menuData["MenuData_" + platformId]
}
