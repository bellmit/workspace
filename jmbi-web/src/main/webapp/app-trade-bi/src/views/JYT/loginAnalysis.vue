<template>
  <div class="login-analysis-page">
  
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>用户行为</el-breadcrumb-item>
      <el-breadcrumb-item>登录分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">用户登录情况
  
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" v-loading="loadinguserLoginLineChart" element-loading-text="加载中...">
            <div class="content" ref='userLoginLineChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box  chart row-group-last" :class="{'no-data': loadinguserLoginPieChartText === '暂无数据'}" v-loading="loadinguserLoginPieChart" :element-loading-text="loadinguserLoginPieChartText">
            <div class="content" ref='userLoginPieChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
  
<!--<el-row :gutter="20">
        <el-col :xs="24" :sm="24" :md="24" :lg="12">
          <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
            <header class="panel-header jm-grid-border-bottom-1px">登录用户来源情况
              <el-tooltip class="item" content="统计本网站流量次数TOP5页面。" placement="top-end">
                <span class="jm-tooltip-icon"></span>
              </el-tooltip>
            </header>
    
            <div class="box sixteen-nine" v-loading="false" element-loading-text="加载中...">
              <div class="content" ref='logonUserFromChart'>
    
              </div>
            </div>
          </div>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="12">
          <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
            <header class="panel-header jm-grid-border-bottom-1px">登录用户去向情况
              <el-tooltip class="item" content="统计本网站页面跳出TOP5页面。" placement="top-end">
                <span class="jm-tooltip-icon"></span>
              </el-tooltip>
            </header>
    
            <div class="box sixteen-nine" v-loading="false" element-loading-text="加载中...">
              <div class="content" ref='logonUserGoAwayChart'>
    
              </div>
            </div>
    
          </div>
        </el-col>
      </el-row>-->
        <!--end of 页面流量排名 & 页面跳出率排名-->
  
  </div>
</template>
<script>

import {
  userTraceLogin,
  userTraceBrowser,
  pageVisit
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';

export default {
  name: 'loginAnalysis',
  components: {
    rangeDatePicker
  },
  created() {
    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
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

      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;

      // 用户登录情况
      vm.loadinguserLoginLineChart = true
      userTraceLogin({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadinguserLoginLineChart = false
        var chartData = response.data
        vm.loaduserLoginLineChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadinguserLoginPieChart = true
      vm.loadinguserLoginPieChartText = "加载中..."
      userTraceBrowser({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadinguserLoginPieChart = false
        var chartData = response.data

        if (chartData.pieChart.seriesData.length === 0) {
          vm.loadinguserLoginPieChart = true
          vm.loadinguserLoginPieChartText = "暂无数据"
        }
        vm.loaduserLoginPieChart(chartData);
      })
        .catch(function (error) {
          console.log(error);
        });

      // 用户登录情况 end

      vm.loadingYmllpmChart = true
      vm.loadingYmtclpmChart = true
      vm.loadingYmllpmChartText = '加载中...'
      vm.loadingYmtclpmChartText = '加载中...'
      pageVisit({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingYmllpmChart = false
        vm.loadingYmtclpmChart = false
        var chartData = response.data

        if (chartData.pvChart.seriesData.length === 0) {
          vm.loadingYmllpmChart = true
          vm.loadingYmllpmChartText = '暂无数据'
        }
        if (chartData.existCountChart.seriesData.length === 0) {
          vm.loadingYmtclpmChart = true
          vm.loadingYmtclpmChartText = '暂无数据'
        }

        // vm.loadymllpmChart(chartData)
        // vm.loadymtclpmChart(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });

    },

    // 用户登录情况
    loaduserLoginLineChart(chartData) {
      var vm = this;
      var chartId = "userLoginLineChart"

      var lineChart = chartData.lineChart;

      let yAxis = {
            type: 'value'
          };
 
        // 从0开始，最小间隔为整数。
        let maxVal = Math.max(...lineChart.seriesData[0])
        if (maxVal < 5) {
          yAxis.splitNumber = maxVal || 1
          yAxis.minInterval = 1
          
        }

      // 绘制图表
      var option = {
        color: ['#1790cf'],
        tooltip: {
          trigger: 'axis'
        },
        calculable: true,
        grid: {
          left: '3%',
          right: '4%',
          bottom: '5%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: lineChart.xAxisData
          }
        ],
        yAxis: yAxis,
        series: [
          {
            name: '用户登录情况',
            type: 'line',
            smooth: true,
            data: lineChart.seriesData[0],
            markPoint: {
              data: [
                { type: 'max', name: '最大值' },
                { type: 'min', name: '最小值' }
              ]
            },
            markLine: {
              data: [
                { type: 'average', name: '平均值' }
              ]
            }
          }
        ]
      };

      vm.$initChart(vm, chartId, option)
    },

    // 用户登录情况
    loaduserLoginPieChart(chartData) {
      var vm = this;
      var chartId = "userLoginPieChart"

      var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          //text: "用户登录分析",
          left: 'center',
          bottom: '5%',
          textStyle: {
            color: '#666',
            fontWeight: 'normal',
            fontSize: 14
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: "{b} : {c} ({d}%)"
        },
        legend: {
          top: '5%',
          data: pieChart.legendData
        },
        series: [{
          type: 'pie',
          radius: '55%',
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

    },

    // 页面流量排名
    // 登录用户来源情况
    // loadymllpmChart(chartData) {
    //   var vm = this;
    //   // 基于准备好的dom，初始化echarts实例
    //   vm.logonUserFromChart = echarts.init(vm.$refs.logonUserFromChart);

    //   var pvChartData = chartData.pvChart

    //   // 绘制图表
    //   var option = {
    //     tooltip: {
    //       trigger: 'axis',
    //       axisPointer: {
    //         type: 'shadow'
    //       },
    //       formatter: function (params) {
    //         var name = params[0].name;
    //         var index = 50;

    //         if (name.length > index) {
    //           name = name.substring(0, index) + '<br>' + name.substring(index)
    //         }
    //         var color = params[0].color

    //         return name + '<br><span class="bar-trend-chart-tooltip-icon" style="background-color:' + color + '"></span>' + params[0].seriesName + ': ' + params[0].value
    //       }
    //     },
    //     grid: {
    //       top: '8%',
    //       left: '5%',
    //       right: '5%',
    //       bottom: '8%',
    //       containLabel: true
    //     },
    //     xAxis: {
    //       name: '次',
    //       type: 'value',
    //       boundaryGap: false,
    //       axisLine: {
    //         lineStyle: {
    //           color: '#158bca'
    //         }
    //       }
    //     },
    //     yAxis: {
    //       type: 'category',
    //       splitLine: {
    //         show: true
    //       },
    //       axisLine: {
    //         lineStyle: {
    //           color: '#158bca'
    //         }
    //       },
    //       axisLabel: {
    //         formatter: function (value, index) {
    //           var value = value || ''
    //           var maxLength = 15
    //           if (value.length > maxLength) {
    //             value = value.substring(0, maxLength) + '...';
    //           }
    //           return value;
    //         },
    //         rotate: 20
    //       },
    //       data: pvChartData.yAxisData.reverse()
    //     },
    //     series: [{
    //       name: pvChartData.seriesName,
    //       type: 'bar',
    //       barWidth: '20px',
    //       itemStyle: {
    //         normal: {
    //           color: '#5eb2ed',
    //           barBorderRadius: 2
    //         }
    //       },
    //       data: pvChartData.seriesData.reverse()
    //     }]
    //   };

    //   vm.logonUserFromChart.setOption(option)

    //   // vm.logonUserFromChart.on('click', function (obj) {
    //   //   openInNewTab(obj.name);
    //   // })

    // },
    // 页面跳出排名
    // 登录用户去向情况
    // loadymtclpmChart(chartData) {
    //   var vm = this
    //   // 基于准备好的dom，初始化echarts实例
    //   vm.logonUserGoAwayChart = echarts.init(vm.$refs.logonUserGoAwayChart);

    //   var existCountChart = chartData.existCountChart
    //   // 绘制图表
    //   var option = {
    //     tooltip: {
    //       trigger: 'axis',
    //       axisPointer: {
    //         type: 'shadow'
    //       }
    //     },
    //     grid: {
    //       top: '8%',
    //       left: '5%',
    //       right: '5%',
    //       bottom: '8%',
    //       containLabel: true
    //     },
    //     xAxis: {
    //       name: '次',
    //       type: 'value',
    //       boundaryGap: false,
    //       axisLine: {
    //         lineStyle: {
    //           color: '#158bca'
    //         }
    //       }
    //     },
    //     yAxis: {
    //       type: 'category',
    //       splitLine: {
    //         show: true
    //       },
    //       axisLine: {
    //         lineStyle: {
    //           color: '#158bca'
    //         }
    //       },
    //       axisLabel: {
    //         formatter: function (value, index) {
    //           var value = value || ''
    //           var maxLength = 15
    //           if (value.length > maxLength) {
    //             value = value.substring(0, maxLength) + '...';
    //           }
    //           return value;
    //         },
    //         rotate: 20
    //       },
    //       data: existCountChart.yAxisData.reverse()
    //     },
    //     series: [{
    //       name: existCountChart.seriesName,
    //       type: 'bar',
    //       barWidth: '20px',
    //       itemStyle: {
    //         normal: {
    //           color: '#44cd8a',
    //           barBorderRadius: 2
    //         }
    //       },
    //       data: existCountChart.seriesData.reverse()
    //     }]
    //   };

    //   vm.logonUserGoAwayChart.setOption(option)

    //   // vm.logonUserGoAwayChart.on('click', function (obj) {
    //   //   openInNewTab(obj.name);
    //   // })
    // }

  },
  mounted() {

    var vm = this;

    vm.loadAllData()

    window.addEventListener('resize', chartsResize, false);

    var resizeTimer;

    function chartsResize() {
      if (resizeTimer) {
        clearTimeout(resizeTimer)
      }
      resizeTimer = setTimeout(function () {
        vm.userLoginLineChart && vm.userLoginLineChart.resize()
        vm.userLoginPieChart && vm.userLoginPieChart.resize()
      }, 100);

    }
  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadinguserLoginLineChart: false,
      loadinguserLoginPieChart: false,
      loadinguserLoginPieChartText: '加载中...',
      loadingYmllpmChart: false,
      loadingYmtclpmChart: false,
      loadingYmllpmChartText: '加载中...',
      loadingYmtclpmChartText:'加载中...'
    }
  }
}

</script>

<style lang="scss">
.login-analysis-page {
  padding-bottom: 30px;
}
</style>
