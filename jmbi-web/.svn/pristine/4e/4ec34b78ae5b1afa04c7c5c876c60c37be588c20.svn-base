<template>
  <el-row class="jm-grid-border-1px master-detail-view">
    <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">{{panelTitle}}
    </header>
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white ">
  
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
          <span class="chart-title-text">{{ItemType}} 行业占比</span>
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
    'ItemType': [String],
    'startDate': [String],
    'endDate': [String],
    'intergerxAxis': [Boolean],
    'unit': [String]
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
    loadMasterData() {

      var vm = this;
      vm.loadingMasterChart = true
      vm.loadingMasterChartText = '加载中...'
      vm.clearChartData()
      api[vm.MasterApi]({
        platform: vm.$platformId,
        startDate: vm.startDate,
        endDate: vm.endDate
      }).then(function (response) {
        vm.loadingMasterChart = false
        var chartData = response.data

        if (chartData.barChart.seriesData.length === 0) {
          vm.loadingMasterChart = true
          vm.loadingMasterChartText = '暂无数据'

        }

        vm.loadMasterOption(chartData)
      })
        .catch(function (error) {
          console.log(error);
          vm.loadingMasterChart = true
        });

    },
    loadDetailData(itemId) {
      var vm = this;
      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;
      vm.loadingDetailChart = true
      vm.loadingDetailChartText = '加载中...'
      api[vm.DetailApi]({
        itemId,
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingDetailChart = false
        var chartData = response.data
        if (chartData.pieChart.seriesData.length === 0) {
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

      var barChart = chartData.barChart
      vm.industryName = ""

      if (barChart.seriesData.length > 0) {
        var itemId = barChart.itemId[0]
        vm.industryName = barChart.yAxisData[0]

        vm.$nextTick(function () {
          vm.loadDetailData(itemId)
        })

      } else {
        vm.clearChartData()
      }

      let xAxis = {
        name: vm.unit,
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#158bca'
          }
        }
      };

      // 从0开始，最小间隔为整数。
      if (vm.intergerxAxis) {
        let maxVal = Math.max(...barChart.seriesData)
        if (maxVal < 5 && maxVal > 0) {
          xAxis.splitNumber = maxVal
          xAxis.minInterval = 1
        } else {
          xAxis.splitNumber = 0
          xAxis.minInterval = 0
        }
      } else {
        xAxis.splitNumber = 0
        xAxis.minInterval = 0
      }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: function (params) {
            var name = params[0].name;
            var index = 50;

            if (name.length > index) {
              name = name.substring(0, index) + '<br>' + name.substring(index)
            }
            var color = params[0].color

            return name + '<br><span class="bar-trend-chart-tooltip-icon" style="background-color:' + color + '"></span>' + params[0].seriesName + ': ' + params[0].value
          }
        },
        grid: {
          top: '8%',
          left: '5%',
          right: '5%',
          bottom: '8%',
          containLabel: true
        },
        xAxis: xAxis,
        yAxis: {
          type: 'category',
          splitLine: {
            show: true
          },
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          },
          axisLabel: {
            formatter: function (value, index) {
              var value = value || ''
              var maxLength = 15
              if (value.length > maxLength) {
                value = value.substring(0, maxLength) + '...';
              }
              return value;
            },
            rotate: 20
          },
          data: barChart.yAxisData.reverse()
        },
        series: [{
          name: `店铺${vm.ItemType}排行`,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#5eb2ed',
              barBorderRadius: 2
            }
          },
          data: barChart.seriesData.reverse()
        }]
      };

      vm.detailIds = barChart.itemId.reverse()

      vm.$initChart(vm, chartId, option)
      vm[chartId].off('click')
      vm[chartId].on('click', function (obj) {

        vm.industryName = obj.name

        var itemId = vm.detailIds[obj.dataIndex]

        vm.loadDetailData(itemId)

      })
    },
    loadDetailOption(chartData) {
      var vm = this;

      var chartId = 'detailChart'

      var pieChart = chartData.pieChart;

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
          formatter: `{a} <br/>{b} : {c}${vm.unit}({d}%)`
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
      loadingMasterChart: false,
      loadingMasterChartText: "加载中...",
      loadingDetailChart: false,
      loadingDetailChartText: "加载中...",
      industryName: '',
      detailIds: []
    }
  }
}


</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
.goods-industry-diff-chart {
  height: 500px;
}
</style>
