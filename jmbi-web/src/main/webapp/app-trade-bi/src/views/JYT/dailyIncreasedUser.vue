<template>
  <div>
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>注册分析</el-breadcrumb-item>
      <el-breadcrumb-item>日新增用户</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">新增注册数
          </header>
  
          <chart-box v-on:loadOption="loadnewAddedUsersLineChart" :chartBoxClass="newAddedUsersLineChartBoxClass" apiName="jytRegisterNewlyIncr" :apiParams="jytRegisterNewlyIncrChartApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 新增注册数-->
  
    <el-row class="jm-grid-border-1px master-detail-view">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">新增用户来源
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <chart-box v-on:loadOption="loadnewAddedUserBarChart" apiName="jytRegisterNewlyIncrTop5" :apiParams="jytRegisterNewlyIncrChartApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <chart-box v-on:loadOption="loadnewAddedUserPieChart" apiName="jytRegisterNewlyIncrPieChart" :apiParams="jytRegisterNewlyIncrChartApiParams" seriesDataProperty="pieChart.seriesData"></chart-box>
  
        </div>
      </el-col>
    </el-row>
  
  </div>
</template>

<script>

import {
  newlyIncreased,
  mainNewlyIncreasedPie
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'
import chartBox from '@/components/chartBox'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'registerAnalysis',
  components: {
    rangeDatePicker,
    chartBox
  },
  created() {

    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }
  },
  computed: {
    jytRegisterNewlyIncrChartApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate
      }
    }
  },
  methods: {
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.loadAllData();
    },
    loadAllData() {
      var vm = this;
    },
    // 新增注册数
    loadnewAddedUsersLineChart({ chartData, context, chartId }) {
      var vm = this;

      var lineChart = chartData.lineChart

      let yAxis = {
        name: '个',
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#158bca'
          }
        }
      };

      // 从0开始，最小间隔为整数。
      let maxVal = Math.max(...lineChart.seriesData)
      if (maxVal < 5) {
        yAxis.splitNumber = maxVal || 1
        yAxis.minInterval = 1
      } else {
        yAxis.splitNumber = 0
        yAxis.minInterval = 0
      }

      // 绘制图表
      var option = {
        color: ["#44cd8a", "#5eb2ec", "#b6a4dd"],
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '5%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          },
          data: lineChart.xAxisData
        },
        yAxis: yAxis,
        series: [{
          name: "新增注册数",
          type: 'line',
          smooth: true,
          itemStyle: {
            normal: {
              color: "#44cd8a"
            }
          },
          data: lineChart.seriesData
        }]
      };

      // vm.$initChart(vm, chartId, option)
      context.$initChart(context, chartId, option)
    },

    loadnewAddedUserBarChart({ chartData, context, chartId }) {
      var vm = this;

      var barChart = chartData.lineChart

      let xAxis = {
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#158bca'
          }
        }
      };

      // 从0开始，最小间隔为整数。
      let maxVal = Math.max(...barChart.seriesData)
      if (maxVal < 5 && maxVal > 0) {
        xAxis.splitNumber = maxVal
        xAxis.minInterval = 1
      } else {
        xAxis.splitNumber = 5
        xAxis.minInterval = 0
      }

      // 绘制图表
      var option = {
        title: {
          text: "新增用户来源排行TOP5",
          left: 'center',
          top: '5%',
          textStyle: {
            color: '#666',
            fontWeight: 'normal',
            fontSize: 14
          }
        },
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
          top: '15%',
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
          name: `新增用户来源排行`,
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

      context.$initChart(context, chartId, option)
    },

    // 新增注册数
    loadnewAddedUserPieChart({ chartData, context, chartId }) {
      var vm = this;

      var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          text: "新增来源占比",
          left: 'center',
          top: '5%',
          textStyle: {
            color: '#666',
            fontWeight: 'normal',
            fontSize: 14
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
          name: "新增注册数",
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

      // vm.$initChart(vm, chartId, option)
      context.$initChart(context, chartId, option)
    },

  },
  mounted() {
    var vm = this;

    vm.loadAllData();

  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      newAddedUsersLineChartBoxClass: {
        'row-group-last': true
      }
    }
  }
}


</script>

<style>
/*.register-analysis-page {
  padding-bottom: 50px;
}*/

.register-panel-title {
  font-size: 20px;
  padding-top: 15px;
}

.text-right {
  text-align: right;
}

.margin-right-20 {
  margin-right: 20px;
}
</style>
