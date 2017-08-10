<template>
  <div class="attension-analysis-page">
  
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>用户行为</el-breadcrumb-item>
      <el-breadcrumb-item>用户关注</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">搜索词排行
  
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
          <chart-box v-on:loadOption="loadSearchWordCloudChart" apiName="userattentionWordcloud" :apiParams="apiParams" seriesDataProperty="wordCloud"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
  
          <chart-box v-on:loadOption="loadSearchWordCloudBar" apiName="userattentionWordrank" :apiParams="apiParams" seriesDataProperty="barChart.seriesData"></chart-box>
  
        </div>
      </el-col>
    </el-row>
  
  </div>
</template>
<script>

// import {
//   userTraceLogin,
//   userTraceBrowser
// } from '@/service/api'
import util from '@/service/util'


import rangeDatePicker from '@/components/RangeDatePicker'
import chartBox from '@/components/chartBox'

// import echarts from 'echarts';
// import 'echarts-wordcloud'

export default {
  name: 'loginAnalysis',
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
    apiParams() {
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
    },

    loadSearchWordCloudChart({ chartData, context, chartId }) {
      var vm = this;

      context.$clearChartData(context, chartId)

      var option = {
        tooltip: {
          formatter: '{b}'
        },
        series: [{
          type: 'wordCloud',
          // sizeRange: [10, 100],
          // rotationRange: [-90, 90],
          // rotationStep: 45,
          // gridSize: 2,
          // shape: 'circle',
          gridSize: 12,
          sizeRange: [15, 50],
          rotationRange: [0, 0],
          // rotationStep: 45,
          shape: 'circle',
          width: '100%',
          height: '100%',
          textStyle: {
            normal: {
              color: function () {
                return 'rgb(' + [
                  Math.round(Math.random() * 160),
                  Math.round(Math.random() * 160),
                  Math.round(Math.random() * 160)
                ].join(',') + ')';
              }
            }
          },
          data: chartData.wordCloud.sort(function (a, b) {
            return b.value - a.value;
          })
        }]
      };
      context.$initChart(context, chartId, option)
    },



    loadSearchWordCloudBar({ chartData, context, chartId }) {
      var vm = this
      //var chartId = "searchWordCloudBar"
      var barChart = chartData.barChart

      let xAxis = {
        type: 'value',
        boundaryGap: false,
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
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
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
          data: barChart.yAxisData.reverse()//['巴西', '印尼', '美国', '印度', '中国', '世界人口(万)']
        },
        series: [{
          name: barChart.seriesName,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#44cd8a',
              barBorderRadius: 2
            }
          },
          data: barChart.seriesData.reverse()//[19325, 23438, 31000, 121594, 134141, 681807]
        }]
      };

      // vm.$initChart(vm, chartId, option)
      context.$initChart(context, chartId, option)

    }



  },
  mounted() {

    var vm = this;


  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format()
    }
  }
}

</script>

<style lang="scss">
.row-search-word-group {

  .content {
    padding: 30px;
    box-sizing: border-box;
  }
}

.attension-analysis-page {
  padding-bottom: 30px;
}
</style>
