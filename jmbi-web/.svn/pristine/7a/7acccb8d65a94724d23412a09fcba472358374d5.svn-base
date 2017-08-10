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
      <el-breadcrumb-item>签章支付</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">签章支付</header>
      <div class="auth-user-botton-group">
        <el-button size="small" type="primary" :type="primary0" @click="loadSignaturePayIndex(0)" v-if="!isOneDay">浏览量</el-button>
        <el-button size="small" :type="primary1" @click="loadSignaturePayIndex(1)" v-if="!isOneDay">独立访客</el-button>
        <el-button size="small" :type="primary2" @click="loadSignaturePayIndex(2)">签章开通数</el-button>
        <el-button size="small" :type="primary3" @click="loadSignaturePayIndex(3)">支付开通数</el-button>
  
      </div>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingsignaturePayIndexText === '暂无数据'}" v-loading="loadingsignaturePayIndex" :element-loading-text="loadingsignaturePayIndexText">
            <div class="content" ref='signaturePayLineChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white signature-pay-container">
  
          <div class="signature-pay clearfix">
            <div class="left content">
              <p class="title">签章开通历史累计：</p>
              <p class="amount">{{visaAndPayHistoryData.visa_history_counts}}</p>
            </div>
            <div class="right content">
              <p class="title">支付开通历史累计：</p>
              <p class="amount">{{visaAndPayHistoryData.pay_history_counts}}</p>
            </div>
          </div>
  
          <div class="box  chart row-group-last" :class="{'no-data': loadingvisaAndPayHistoryText === '暂无数据'}" v-loading="loadingvisaAndPayHistory" :element-loading-text="loadingvisaAndPayHistoryText">
            <div class="content" ref='signaturePayHistoryChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 签章支付-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">签章、支付开通地域分布
  
      </header>
  
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart area-map-container" v-loading="loadingsignaturePayChinaMap" element-loading-text="加载中...">
            <div class="content" ref='signaturePayMapChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
          <div class="signature-pay-area-wrapper">
            <el-button size="small" :type="primaryArea1" @click="loadSignaturePayArea(1)">签章开通</el-button>
            <el-button size="small" :type="primaryArea2" @click="loadSignaturePayArea(2)">支付开通</el-button>
          </div>
  
          <div class="box  chart area-bar-container" :class="{'no-data': loadingsignaturePayBarDataText === '暂无数据'}" v-loading="loadingsignaturePayBarData" :element-loading-text="loadingsignaturePayBarDataText">
            <div class="content" ref='signaturePayBarChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 签章支付-->
  
  </div>
</template>

<script>

import {
  signaturePayIndex,
  signaturePayChinaMap,
  signaturePayBarChart,
  visaAndPayHistory
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'signaturePay',
  components: {
    rangeDatePicker
  },
  created() {

    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }

    this.setActiveItem()
  },
  computed: {
    isOneDay() {
      return this.startDate === this.endDate
    }
  },
  methods: {
    setActiveItem() {
      var vm = this;
      if (vm.isOneDay) {
       
        if (vm.indexType === 0 || vm.indexType === 1) {
            vm.indexType = 2;
      
            vm.primary0 = vm.getPrimary(0)
            vm.primary1 = vm.getPrimary(1)
            vm.primary2 = vm.getPrimary(2)
            vm.primary3 = vm.getPrimary(3)
     
      
   
        }
        
      }
    },
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.loadAllData();
    },
    loadAllData() {
      var vm = this;

      this.setActiveItem()
      var type = vm.indexType;
      vm.loadSignaturePayIndex(type)

      vm.loadSignaturePayArea(vm.signaturePayAreaType)

      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;

      vm.loadingsignaturePayChinaMap = true
      signaturePayChinaMap({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingsignaturePayChinaMap = false
        var chartData = response.data



        vm.loadsignaturePayMapChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      // todo
      vm.loadingvisaAndPayHistory = true
      vm.loadingvisaAndPayHistoryText = '加载中...'
      visaAndPayHistory(
        platform
      ).then(function (response) {
        vm.loadingvisaAndPayHistory = false
        var chartData = response.data
        if (chartData.pieChart.seriesData.length === 0) {
            vm.loadingvisaAndPayHistory = true
            vm.loadingvisaAndPayHistoryText = '暂无数据'
        }
        vm.visaAndPayHistoryData = chartData;

        vm.loadsignaturePayHistoryChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });
    },
    getPrimary(type) {
      return this.indexType == type ? "primary" : '';
    },
    getSignaturePayAreaPrimary(type) {
      return this.signaturePayAreaType == type ? "primary" : '';
    },
    loadSignaturePayArea(type) {
      var vm = this;
      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;
      vm.signaturePayAreaType = type;

      vm.primaryArea1 = vm.getSignaturePayAreaPrimary(1)
      vm.primaryArea2 = vm.getSignaturePayAreaPrimary(2)

      var dataType = type === 1 ? "visa" : "pay"

      vm.loadingsignaturePayBarData = true
      vm.loadingsignaturePayBarDataText = '加载中...'
      signaturePayBarChart({
        platform,
        startDate,
        endDate,
        type: dataType
      }).then(function (response) {
        vm.loadingsignaturePayBarData = false
        var chartData = response.data
        if (chartData.lineChart.seriesData.length === 0) {
          vm.loadingsignaturePayBarData = true
          vm.loadingsignaturePayBarDataText = '暂无数据'
        }
        vm.loadsignaturePayBarChart(chartData, dataType)
      })
        .catch(function (error) {
          console.log(error);
        });

    },
    loadSignaturePayIndex(type) {
      var vm = this;
      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;
      vm.indexType = type;

      vm.primary0 = vm.getPrimary(0)
      vm.primary1 = vm.getPrimary(1)
      vm.primary2 = vm.getPrimary(2)
      vm.primary3 = vm.getPrimary(3)

      vm.loadingsignaturePayIndex = true
      vm.loadingsignaturePayIndexText = '加载中...'
      signaturePayIndex({
        platform,
        startDate,
        endDate,
        type
      }).then(function (response) {
        vm.loadingsignaturePayIndex = false
        var chartData = response.data
        if (chartData.lineChart.seriesData.length === 0) {
          vm.loadingsignaturePayIndex = true
          vm.loadingsignaturePayIndexText = '暂无数据'
        }
        vm.loadsignaturePayLineChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

    },
    // 签章支付
    loadsignaturePayLineChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.signaturePayLineChart = echarts.init(vm.$refs.signaturePayLineChart);

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
        series: [
          {
            name: lineChart.legendData,
            type: 'line',
            smooth: true,
            data: lineChart.seriesData
          }]
      };

      vm.signaturePayLineChart.setOption(option)
    },

    // 用户登录情况
    loadsignaturePayHistoryChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.signaturePayHistoryChart = echarts.init(vm.$refs.signaturePayHistoryChart);

      var pieChart = chartData.pieChart;

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
            fontSize: 16
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
          name: "支付渠道占比",
          type: 'pie',
          radius: ['30%', '50%'],
          center: ['50%', '60%'],
          data: pieChart.seriesData,
          // data: [
          //   { value: 335, name: '直接访问' },
          //   { value: 310, name: '邮件营销' },
          //   { value: 234, name: '联盟广告' },
          //   { value: 135, name: '视频广告' },
          //   { value: 1548, name: '搜索引擎' }
          // ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.signaturePayHistoryChart.setOption(option)
    },




    // 三证审核地域分布
    loadsignaturePayMapChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.signaturePayMapChart = echarts.init(vm.$refs.signaturePayMapChart);

      var map = chartData.pieChart
      let seriesData = map.series.map(function (item) {
        return Object.assign(item, {
          type: 'map', mapType: 'china', roam: true, label: {
            normal: {
              show: true
            },
            emphasis: {
              show: true
            }
          }
        })
      })

      let maxValue = 0;
      map.series.forEach(function (item) {

        item.data.forEach(function (data) {
          let v = Number(data.value)
          if (v > maxValue) {
            maxValue = v;
          }
        })

      })

      if (maxValue === 0) {
        maxValue = 255;
      }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'item',
          formatter: function (obj) {
            var areaName = obj.name
            let arr = [];

            for (let index = 0; index < seriesData.length; index++) {
              let filteredData = seriesData[index].data.filter(function (item) {
                return item.name === areaName
              })

              arr.push({
                type: seriesData[index].name,
                data: filteredData.length > 0 ? filteredData[0] : {}
              })
            }

            let result = areaName

            arr.forEach(function (item) {
              if (typeof item.data.value !== 'undefined') {
                result += '<br>' + item.type + ': ' + item.data.value
              }

            })

            return result

          }

        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: map.legend.data
        },
        visualMap: {
          // orient: 'horizontal',
          inverse: 'true',
          min: 0,
          max: maxValue,// chartData.max, // todo
          // left: '65px',
          // top: '15px',
          text: ['高', '低'], // 文本，默认为数值文本
          inRange: {
            color: ['#e0ffff', '#006edd']
          }
        },
        series: seriesData
      };

      vm.signaturePayMapChart.setOption(option)
    },

    // 签章、支付开通地域分布
    loadsignaturePayBarChart(chartData, dataType) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.signaturePayBarChart = echarts.init(vm.$refs.signaturePayBarChart);

      var lineChart = chartData.lineChart

      let seriesName = ''
      if (dataType === 'visa') {
        seriesName = "签章开通"
      } else {
        seriesName = "支付开通"
      }

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
          name: seriesName,
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

      vm.signaturePayBarChart.setOption(option)
    },

  },
  mounted() {
    var vm = this;

    vm.loadAllData();

    // todo


    window.addEventListener('resize', chartsResize, false);

    var resizeTimer;

    function chartsResize() {
      if (resizeTimer) {
        clearTimeout(resizeTimer)
      }
      resizeTimer = setTimeout(function () {
        vm.signaturePayLineChart && vm.signaturePayLineChart.resize()
        vm.signaturePayHistoryChart && vm.signaturePayHistoryChart.resize()
        vm.signaturePayMapChart && vm.signaturePayMapChart.resize()
        vm.signaturePayBarChart && vm.signaturePayBarChart.resize()


      }, 100);
    }

  },
  data() {
    return {
      cancelSource: '',
      startDate: new Date().format(),
      endDate: new Date().format(),
      indexType: 0, // 浏览量
      primary0: 'primary',
      primary1: '',
      primary2: '',
      primary3: '',
      signaturePayAreaType: 1,
      primaryArea1: 'primary',
      primaryArea2: '',
      loadingsignaturePayIndex: false,
      loadingsignaturePayIndexText: '加载中...',
      loadingsignaturePayChinaMap: false,
      loadingsignaturePayBarData: false,
      loadingsignaturePayBarDataText: '加载中...',
      visaAndPayHistoryData: {},
      loadingvisaAndPayHistory: false,
      loadingvisaAndPayHistoryText: '加载中...',
      loadingTODOChart: false,
      loadingvisaAndPayHistoryText:'加载中...'

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
