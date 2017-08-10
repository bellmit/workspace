<template>
  <div class="box sixteen-nine" :class="[{'no-data': noData }, chartBoxClass]" v-loading="loading" :element-loading-text="loadingText">
    <div class="content" ref='chartId'>
  
    </div>
  </div>
</template>
<script>
import * as api from '@/service/api'

export default {
  name: 'chartBox',
  props: ["apiName", "apiParams", "seriesDataProperty", 'chartBoxClass'],
  created() {

  },
  watch: {
    apiParams() {
      this.loadData()
    }
  },
  computed: {
    noData() {
      return this.loadingText === '暂无数据'
    }
  },
  data() {
    return {
      loading: false,
      loadingText: "加载中..."
    }
  },
  methods: {
    getSeriesDataByProperty(chartData) {
      var cloneChartData = Object.assign({}, chartData)
      var properties = this.seriesDataProperty.split('.')
      while (this.seriesDataProperty && properties.length > 0) {
        let property = properties.shift()
        if (!cloneChartData[property]) return null

        cloneChartData = cloneChartData[property]
      }

      return cloneChartData
    },
    loadData() {
      var vm = this;
      vm.loading = true
      vm.loadingText = '加载中...'
      api[vm.apiName](vm.apiParams).then(function (response) {
        vm.loading = false
        var chartData = response.data
        let seriesData = vm.getSeriesDataByProperty(chartData)

        if (seriesData === null || seriesData.length === 0 || Object.keys(seriesData).length === 0) {
          vm.loading = true
          vm.loadingText = '暂无数据'
        }

        vm.$emit('loadOption', { chartData, context: vm, chartId: "chartId" });

      })
        .catch(function (error) {
          console.log(error);
        });

    },
  },
  mounted() {
    let vm = this
    vm.loadData()

    window.addEventListener('resize', chartsResize, false);
    //var resizeTimer;
    function chartsResize() {
      // if (resizeTimer) {
      //   clearTimeout(resizeTimer)
      // }
      setTimeout(function () {
        let chartId = 'chartId'

        vm[chartId] && vm[chartId].resize()

      }, 100);

    }
  }
}

</script>

<style lang="scss">

</style>
