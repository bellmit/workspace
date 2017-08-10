<template>
  <el-row class="jm-grid-border-1px master-detail-view">
    <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">{{panelTitle}}
    </header>
    <div class="order-analysis-btn-wrapper jm-padding-top-20">
      <el-button size="small" :type="primary1" @click="loadData('ordernum','笔数')">笔数</el-button>
      <el-button size="small" :type="primary2" @click="loadData('paytotal','金额')">金额</el-button>
    </div>
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white ">
        <header class="chart-title">
          <span class="chart-title-number">{{count}}</span>
          <span class="chart-title-unit">{{unit}}</span>
        </header>
        <div class="box sixteen-nine" :class="{'no-data': loadingMasterChartText === '暂无数据'}" v-loading="loadingMasterChart" :element-loading-text="loadingMasterChartText">
          <div class="content" ref='masterChart'>
  
          </div>
        </div>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white ">
        <header class="chart-title">
          <span class="chart-title-key">{{industryName}}</span>
          <span class="chart-title-text">订单{{typeName}} 各类目占比</span>
        </header>
        <div class="box sixteen-nine" :class="{'no-data': loadingDetailChartText === '暂无数据'}" v-loading="loadingDetailChart" :element-loading-text="loadingDetailChartText">
          <div class="content" ref='detailChart'>
  
          </div>
        </div>
  
      </div>
    </el-col>
  </el-row>
</template>

<script>

import echarts from 'echarts';

import * as api from '@/service/api'

export default {
  name: 'MasterDetailChart',
  props: {
    'MasterApi': {
      type: String
    },
    'DetailApi': {
      type: String
    },
    'panelTitle': [String],
    'masterChartTitle': [String],
    'startDate': [String],
    'endDate': [String]
  },
  watch: {
    startDate() {
      this.loadMasterData()
    },
    endDate() {
      this.loadMasterData()
    }
  },

  methods: {
    clearChartData() {
      var vm = this
      var chartId = 'detailChart'
      vm.$clearChartData(vm, chartId)
      vm.loadingDetailChart = true
      vm.loadingDetailChartText = '暂无数据'
    },
    getPrimary(type) {
      return this.dataType === type ? "primary" : '';
    },
    loadData(sortField, typeName) {
      var vm = this;
      vm.dataType = sortField;
      vm.primary1 = vm.getPrimary('ordernum')
      vm.primary2 = vm.getPrimary('paytotal')

      if (sortField === 'ordernum') {
        vm.unit = "笔"
      } else {
        vm.unit = "万元"
      }

      vm.typeName = typeName

      vm.loadMasterData()
    },
    loadMasterData() {
      var vm = this;
      vm.loadingMasterChart = true
      vm.loadingMasterChartText = '加载中...'
      vm.clearChartData()
      api[vm.MasterApi]({
        platform: vm.$platformId,
        startDate: vm.startDate,
        endDate: vm.endDate,
        sortField: vm.dataType
      }).then(function (response) {
        vm.loadingMasterChart = false
        var chartData = response.data
        if (chartData.pieCharts.seriesData.length === 0) {
          vm.loadingMasterChart = true
          vm.loadingMasterChartText = '暂无数据'

        }

        vm.count = chartData.total

        vm.loadMasterOption(chartData)
      })
        .catch(function (error) {
          console.log(error);
          vm.loadingMasterChart = true
        });

    },
    loadDetailData(code) {
      var vm = this;
      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;
      vm.loadingDetailChart = true
      vm.loadingDetailChartText = '加载中...'
      api[vm.DetailApi]({
        platform,
        startDate,
        endDate,
        industryId: code,
        sortField: vm.dataType
      }).then(function (response) {
        vm.loadingDetailChart = false
        var chartData = response.data
        if (chartData.seriesData.length === 0) {
          vm.loadingDetailChart = true
          vm.loadingDetailChartText = '暂无数据'
        }
        vm.loadDetailOption(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });
    },
    loadMasterOption(chartData) {
      var vm = this;

      var chartId = 'masterChart'

      var pieChart = chartData.pieCharts
      vm.industryName = ""

      if (pieChart.seriesData.length > 0) {
        pieChart.seriesData[0].selected = true
        var code = pieChart.seriesData[0].code
        vm.industryName = pieChart.seriesData[0].name// + '订单' + vm.typeName + '各类目占比'
        vm.$nextTick(function () {
          vm.loadDetailData(code)
        })

      } else {
        vm.clearChartData()
      }

      vm.masterSeriesData = pieChart.seriesData;
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        // title: {
        //   text: `${vm.count}${vm.unit}`,
        //   left: 'center',
        //   top: '5%',
        //   textStyle: {
        //     color: '#666',
        //     fontWeight: 400,
        //     fontSize: 32
        //   }
        // },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
          name: "订单" + vm.typeName,
          type: 'pie',
          selectedMode: 'single',
          radius: ['30%', '60%'],
          center: ['50%', '55%'],
          data: pieChart.seriesData,
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.$initChart(vm, chartId, option)
      vm[chartId].off('click')
      vm[chartId].on('click', function (obj) {
        var selectedItem = vm.masterSeriesData.find(x => x.name === obj.name)
        if (selectedItem) {
          vm.industryName = selectedItem.name //+ '订单' + vm.typeName + '各类目占比'
          vm.loadDetailData(selectedItem.code)
        }

      })
    },
    loadDetailOption(chartData) {
      var vm = this;

      var chartId = 'detailChart'

      var pieChart = chartData;

      // 绘制图表
      var option = {
        color: ['#1790cf', '#3ab882', '#fcb738', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        // title: {
        //   text: vm.industryName,
        //   left: 'center',
        //   top: '5%',
        //   textStyle: {
        //     color: '#666',
        //     fontWeight: 'normal',
        //     fontSize: 28
        //   }
        // },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
          name: vm.industryName,
          type: 'pie',
          radius: ['30%', '60%'],
          center: ['50%', '55%'],
          data: pieChart.seriesData,
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.$initChart(vm, chartId, option)
    }
  },
  mounted() {
    this.loadMasterData()

    var vm = this;

    window.addEventListener('resize', chartsResize, false);
    var resizeTimer;
    function chartsResize() {
      if (resizeTimer) {
        clearTimeout(resizeTimer)
      }
      resizeTimer = setTimeout(function () {

        ['masterChart', 'detailChart'].forEach(function (item) {
          vm[item] && vm[item].resize()
        }, this);

      }, 100);

    }
  },
  data() {
    return {
      dataType: 'ordernum',
      loadingMasterChart: false,
      loadingMasterChartText: "加载中...",
      loadingDetailChart: false,
      loadingDetailChartText: "加载中...",
      count: 0,
      unit: '笔',
      industryName: '',
      primary1: 'primary',
      primary2: '',
      typeName: '笔数'
    }
  }
}


</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
.order-analysis-btn-wrapper {
  background: #fff;
  padding-left: 30px;
}
</style>
