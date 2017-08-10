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
      <el-breadcrumb-item>企业用户</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">认证、授权书用户分析</header>
  
      <div class="auth-user-botton-group">
        <el-radio-group v-model="selectedLinechartItem" @change="selectedLinechartItemChanged">
          <el-radio-button :label="item.name" v-for="item in ButtonLinechartItems" :key="item"></el-radio-button>
        </el-radio-group>
      </div>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
          <chart-box v-on:loadOption="loadRegisterAuthUserLineChart" :chartBoxClass="RegisterAuthUserLineChartBoxClass" apiName="jytRegisterAuthUserLineChart" :apiParams="jytRegisterAuthUserLineChartApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white signature-pay-container">
  
          <div class="signature-pay clearfix">
            <div class="left content">
              <p class="title">委托方历史累积：</p>
              <p class="amount">{{visaAndPayHistoryData.entrustHistoryCounts}}</p>
            </div>
            <div class="right content">
              <p class="title">供应商历史累积：</p>
              <p class="amount">{{visaAndPayHistoryData.supplyHistoryCounts}}</p>
            </div>
          </div>
  
          <chart-box v-on:loadOption="loadsignaturePayHistoryChart" :chartBoxClass="RegisterAuthUserHistoryPieChartBoxClass" apiName="jytRegisterAuthUserHistoryPieChart" :apiParams="HistoryPieChartApiParams" seriesDataProperty="pieChart.seriesData"></chart-box>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 认证、授权书用户分析-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">供应商地域分布
  
      </header>
  
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white map">
          <chart-box v-on:loadOption="loadMapChart" apiName="jytRegisterSupplyAreaDistChinaMap" :apiParams="ApiParams" seriesDataProperty="pieChart.seriesData"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <chart-box v-on:loadOption="loadBarChart" apiName="jytRegisterSupplyAreaDistBarChart" :apiParams="ApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 供应商地域分布-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">委托方地域分布
  
      </header>
  
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white map">
          <chart-box v-on:loadOption="loadMapChart" apiName="jytRegisterEntrustAreaDistChinaMap" :apiParams="ApiParams" seriesDataProperty="pieChart.seriesData"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <chart-box v-on:loadOption="loadBarChart" apiName="jytRegisterEntrustAreaDistBarChart" :apiParams="ApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 委托方地域分布-->
  
  </div>
</template>

<script>

import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'
import chartBox from '@/components/chartBox'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'enterpriseUser',
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
    jytRegisterAuthUserLineChartApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate,
        type: this.selectedLinechartItemId
      }
    },
    HistoryPieChartApiParams() {
      return {
        platform: this.$platformId
      }
    },
    ApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate
      }
    }
  },
  methods: {
    selectedLinechartItemChanged(value) {
      let vm = this;
      vm.selectedLinechartItemId = vm.ButtonLinechartItems.find(x => x.name === value).id
    },
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
    },

    // 签章支付
    loadRegisterAuthUserLineChart({ chartData, context, chartId }) {
      var vm = this;


      var lineChart = chartData.lineChart

      let yAxis = {
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
          top: '5%',
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
        series: [
          {
            name: lineChart.legendData,
            type: 'line',
            smooth: true,
            data: lineChart.seriesData
          }]
      };

      // vm.signaturePayLineChart.setOption(option)
      context.$initChart(context, chartId, option)
    },

    // 用户登录情况
    loadsignaturePayHistoryChart({ chartData, context, chartId }) {
      var vm = this;

      var pieChart = chartData.pieChart;

      vm.visaAndPayHistoryData = {
        supplyHistoryCounts: chartData.supplyHistoryCounts,
        entrustHistoryCounts: chartData.entrustHistoryCounts
      }

      // 绘制图表
      var option = {
        color: ['#1790cf', '#3ab882', '#fcb738', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          text: "经营模式占比:",
          left: '10%',
          top: '20%',
          textStyle: {
            color: '#666',
            fontWeight: 'normal',
            fontSize: 16
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
          name: "经营模式占比",
          type: 'pie',
          radius: ['30%', '50%'],
          center: ['50%', '60%'],
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

      context.$initChart(context, chartId, option)
    },

    loadMapChart({ chartData, context, chartId }) {
      var vm = this;

      var map = chartData.pieChart

      let maxValue = 0;
      map.seriesData.forEach(function (item) {
        let v = Number(item.value)
        if (v > maxValue) {
          maxValue = v;
        }
      })

      if (maxValue === 0) {
        maxValue = 255;
      }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: map.legendData
        },
        visualMap: {
          inverse: 'true',
          min: 0,
          max: maxValue,
          text: ['高', '低'],
          inRange: {
            color: ['#e0ffff', '#006edd']
          }
        },
        series: [{
          type: 'map',
          mapType: 'china',
          roam: true,
          label: {
            normal: {
              show: true
            },
            emphasis: {
              show: true
            }
          },
          data: map.seriesData
        }]
      };

      context.$initChart(context, chartId, option)
    },

    loadBarChart({ chartData, context, chartId }) {
      var vm = this;

      var lineChart = chartData.lineChart

      let xAxis = {
        type: 'value',
        boundaryGap: false,
        axisLine: {
          lineStyle: {
            color: '#008acd'
          }
        }
      };

      // 从0开始，最小间隔为整数。
      let maxVal = Math.max(...lineChart.seriesData)
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
              color: '#008acd'
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
          data: lineChart.yAxisData.reverse()
        },
        series: [{
          name: lineChart.title,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#1790cf',
              barBorderRadius: 2
            }
          },
          data: lineChart.seriesData.reverse()
        }]
      };

      context.$initChart(context, chartId, option)
    },

  },
  mounted() {
    var vm = this;

  },
  data() {
    return {
      ButtonLinechartItems: [
        { id: 0, name: "实名认证" },
        { id: 1, name: "委托方三证认证" },
        { id: 2, name: "供应商三证认证" },
        { id: 3, name: "委托商授权" },
        { id: 4, name: "供应商授权" }
      ],
      selectedLinechartItem: '实名认证',
      selectedLinechartItemId: 0,
      startDate: new Date().format(),
      endDate: new Date().format(),
      visaAndPayHistoryData: {
        supplyHistoryCounts: 0,
        entrustHistoryCounts: 0
      },
      RegisterAuthUserLineChartBoxClass: {
        'row-group-last': true
      },
      RegisterAuthUserHistoryPieChartBoxClass: {
        'row-group-last': true,
        'chart': true,
        'sixteen-nine': false
      }
    }
  }
}


</script>

<style>
.auth-user-botton-group {
  background: #fff;
  padding: 20px 65px;
}

.signature-pay-container {
  position: relative;
}

.signature-pay {
  position: absolute;
  top: -50px;
  width: 100%;
  text-align: center;
  /*left: 50%;*/
}

.signature-pay .content {
  width: 50%;
}

.signature-pay .content:first-child::after {
  position: absolute;
  content: '';
  display: block;
  width: 0;
  height: 90%;
  top: 5%;
  right: 50%;
  border-right: 1px solid #eeeeee;
}

.signature-pay .title {
  font-size: 16px;
}

.signature-pay .amount {
  font-size: 40px;
  color: #3ab882;
  text-align: center;
}

.chart.area-map-container {
  height: 450px;
}

.chart.area-bar-container {
  height: 402px;
}

.signature-pay-area-wrapper {
  text-align: center;
  padding: 10px;
}
</style>
