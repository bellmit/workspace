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
      <el-breadcrumb-item>支付分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">支付分析
  
      </header>
      <div class="auth-user-botton-group">
  
        <el-radio-group v-model="selectedLinechartItem" @change="selectedLinechartItemChanged">
          <el-radio-button :label="item.name" v-for="item in ButtonLinechartItems" :key="item"></el-radio-button>
        </el-radio-group>
  
      </div>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
          <chart-box v-on:loadOption="loadauthUserOverviewChart" :chartBoxClass="PayLineChartBoxClass" apiName="jytPayOne" :apiParams="ApiParams" seriesDataProperty="xAxisData"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white three-certificate-audit-container">
  
          <div class="three-certificate-audit">
            <p class="title">支付开通历史累计：</p>
            <p class="amount">{{payTotal}}</p>
          </div>
  
          <chart-box v-on:loadOption="loadPayChannelProportionPieChart" :chartBoxClass="PayChannelProportionChartBoxClass" apiName="jytPayChannelProportion" seriesDataProperty="proportion"></chart-box>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">支付开通地域分布
  
      </header>
  
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white map">
          <chart-box v-on:loadOption="loadMapChart" apiName="jytPayOpenClime" :apiParams="ApiParams" seriesDataProperty="proportion"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <chart-box v-on:loadOption="loadBarChart" apiName="jytPayOpenClimeTop" :apiParams="ApiParams" seriesDataProperty="seriesData"></chart-box>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
  </div>
</template>

<script>
import {
  jytPayTotal
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'
import chartBox from '@/components/chartBox'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'PayAnalysis',
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

    this.buttonListChanged()
  },
  computed: {
    isOneDay() {
      return this.startDate === this.endDate
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
    buttonListChanged() {
      let vm = this
      if (this.isOneDay) {

        this.ButtonLinechartItems = this.ButtonLinechartItems.filter(x => x.id === "payOpen")

      } else {
        this.OneDayExceptButtonLinechartItems.forEach(function (item) {
          let included = vm.ButtonLinechartItems.find(x => x.name === item.name)
          if (!included) {
            vm.ButtonLinechartItems.unshift(item)
          }
        })
      }

      let included = this.ButtonLinechartItems.find(x => x.name === this.selectedLinechartItem)
      if (!included) {
        this.selectedLinechartItem = this.ButtonLinechartItems[0].name
        this.selectedLinechartItemId = this.ButtonLinechartItems[0].id
      }
    },
    selectedLinechartItemChanged(value) {
      let vm = this;
      vm.selectedLinechartItemId = vm.ButtonLinechartItems.find(x => x.name === value).id
      vm.loadauthUserOverviewChart(vm.payChartData)
    },

    getRangeDate({ startDate, endDate }) {
      let vm = this
      this.startDate = startDate;
      this.endDate = endDate;

      this.buttonListChanged()

      this.loadAllData();
    },
    loadAllData() {
      var vm = this;

    },

    // 认证用户情况
    loadauthUserOverviewChart({ chartData, context, chartId }) {
      var vm = this;

      vm.payChartData = { chartData, context, chartId }

      var lineChart = chartData[vm.selectedLinechartItemId]


      let yAxis = {
        name: lineChart.unit,
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
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          top: '10%',
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
          data: chartData.xAxisData
        },
        yAxis: yAxis,
        series: [{
          name: lineChart.seriesName,
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

      context.$initChart(context, chartId, option)
    },

    loadPayChannelProportionPieChart({ chartData, context, chartId }) {
      var vm = this;

      //let data = chartData.proportion.map(x => { return { name: Object.values(x)[0], value: Object.keys(x)[0] } })
      // 绘制图表
      var option = {
        color: ['#1790cf', '#3ab882', '#fcb738', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          text: "支付渠道占比:",
          left: '10%',
          top: '20%',
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
          name: "支付渠道占比",
          type: 'pie',
          radius: ['30%', '55%'],
          center: ['50%', '55%'],
          data: chartData.proportion,
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

      var mapData = chartData.proportion

      let maxValue = 0;
      mapData.forEach(function (item) {
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
          data: ['支付开通地域分布']//map.legendData
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
          data: mapData
        }]
      };

      context.$initChart(context, chartId, option)
    },

    loadBarChart({ chartData, context, chartId }) {
      var vm = this;

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
      let maxVal = Math.max(...chartData.xAxisData)
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
          data: chartData.seriesData.reverse()
        },
        series: [{
          name: "支付开通地域分布",
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#1790cf',
              barBorderRadius: 2
            }
          },
          data: chartData.xAxisData.reverse()
        }]
      };

      context.$initChart(context, chartId, option)
    }

  },

  mounted() {
    var vm = this;

    jytPayTotal().then(function (response) {

      vm.payTotal = response.data.data.total || 0;

    })
      .catch(function (error) {
        console.log(error);
      });

    vm.loadAllData()




  },
  data() {
    return {
      payTotal: 0,
      payChartData: {},
      OneDayExceptButtonLinechartItems: [
        { id: "pv", name: "浏览量PV" },
        { id: "uv", name: "独立访客UV" }
      ],
      ButtonLinechartItems: [
        { id: "pv", name: "浏览量PV" },
        { id: "uv", name: "独立访客UV" },
        { id: "payOpen", name: "支付开通数" }
      ],
      selectedLinechartItem: '浏览量PV',
      selectedLinechartItemId: "pv",
      startDate: new Date().format(),
      endDate: new Date().format(),

      PayLineChartBoxClass: {
        'row-group-last': true
      },
      PayChannelProportionChartBoxClass: {
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

.three-certificate-audit-container {
  position: relative;
}

.three-certificate-audit {
  position: absolute;
  top: -50px;
  left: 50%;
  transform: translate(-50%, 0);
}

.three-certificate-audit .title {
  font-size: 16px;
}

.three-certificate-audit .amount {
  font-size: 40px;
  color: #3ab882;
  text-align: center;
}
</style>
