import echarts from 'echarts';
import 'echarts-wordcloud'
import 'echarts/map/js/world'
import 'echarts/map/js/china';

const Public = {
  install(Vue, options) {
    Vue.prototype.$platformId = '000000'
    Vue.prototype.$platformName = ''

    Vue.prototype.$initChart = function (vm, refChartId, option) {
      let dom = vm.$refs[refChartId]
      if (!dom) return;

      // 基于准备好的dom，初始化echarts实例
      vm[refChartId] = vm[refChartId] || echarts.init(dom);
      vm[refChartId].setOption(option)

    }

    Vue.prototype.$clearChartData = function (vm, chartId) {

      vm[chartId] && vm[chartId].clear()
    }

  }
};

export default Public;
