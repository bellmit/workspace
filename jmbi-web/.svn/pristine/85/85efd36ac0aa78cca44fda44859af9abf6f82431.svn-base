<template>
  <div class="register-analysis-page">
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>注册分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">新增用户数
      </header>
      <div class="jm-grid-box-bg-white jm-text-center register-panel-title">交易全站
      </div>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingNewAddedUsersLineChartText === '暂无数据'}" v-loading="loadingNewAddedUsersLineChart" :element-loading-text="loadingNewAddedUsersLineChartText">
            <div class="content" ref='newAddedUsersLineChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box  chart row-group-last" :class="{'no-data': loadingNewAddedUserPieChartText === '暂无数据'}" v-loading="loadingNewAddedUserPieChart" :element-loading-text="loadingNewAddedUserPieChartText">
            <div class="content" ref='newAddedUserPieChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
    <el-row class="jm-grid-border-1px">
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
          <div class="jm-grid-box-bg-white jm-text-center register-panel-title">交易全站
          </div>
          <div class="box chart row-group-last" :class="{'no-data': loadingnewAddedUsersLineMainSiteText === '暂无数据'}" v-loading="loadingnewAddedUsersLineMainSite" :element-loading-text="loadingnewAddedUsersLineMainSiteText">
            <div class="content" ref='newAddedUsersLineMainSiteChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 交易全站-->
  </div>
</template>

<script>

import {
  newlyIncreased,
  mainNewlyIncreasedPie
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'registerAnalysis',
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

      vm.loadingNewAddedUsersLineChart = true
      vm.loadingNewAddedUsersLineChartText = '加载中...'
      // 交易全站
      newlyIncreased({
        platform: 0,// 0表示交易全站
        startDate,
        endDate,
        dataType: 'lineChart'
      }).then(function (response) {
        vm.loadingNewAddedUsersLineChart = false
        var chartData = response.data

        if (chartData.lineChart.seriesData.length === 0) {
          vm.loadingNewAddedUsersLineChart = true
          vm.loadingNewAddedUsersLineChartText = '暂无数据'
        }
        var chartId = "newAddedUsersLineChart";
        vm.loadnewAddedUsersLineChart(chartData, chartId)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingNewAddedUserPieChart = true
      vm.loadingNewAddedUserPieChartText = '加载中...'
      mainNewlyIncreasedPie({
        platform: 0,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingNewAddedUserPieChart = false
        var chartData = response.data

        if (chartData.pieChart.seriesData.length === 0) {
          vm.loadingNewAddedUserPieChart = true
          vm.loadingNewAddedUserPieChartText = '暂无数据'
        }

        vm.loadnewAddedUserPieChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      // 交易全站
      vm.loadingnewAddedUsersLineMainSite = true
      vm.loadingnewAddedUsersLineMainSiteText = '加载中...'
      newlyIncreased({
        platform,
        startDate,
        endDate,
        dataType: 'lineChart'
      }).then(function (response) {
        vm.loadingnewAddedUsersLineMainSite = false
        var chartData = response.data

        if (chartData.lineChart.seriesData.length === 0) {
          vm.loadingnewAddedUsersLineMainSite = true
          vm.loadingnewAddedUsersLineMainSiteText = '暂无数据'
        }
        var chartId = "newAddedUsersLineMainSiteChart";
        vm.loadnewAddedUsersLineChart(chartData, chartId)

      })
        .catch(function (error) {
          console.log(error);
        });

    },
    // 新增注册数
    loadnewAddedUsersLineChart(chartData, chartId) {
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

      }

      // 绘制图表
      var option = {
        color: ["#44cd8a", "#5eb2ec", "#b6a4dd"],
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
          data: lineChart.xAxisData
        },
        yAxis: yAxis,
        series: [{
          name: lineChart.legendData[0],
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

      vm.$initChart(vm, chartId, option)
    },

    // 新增注册数
    loadnewAddedUserPieChart(chartData) {
      var vm = this;

      var chartId = 'newAddedUserPieChart'

      var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          //text: "新增注册数",
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
          radius: '55%',
          center: ['50%', '50%'],
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

  },
  mounted() {
    var vm = this;

    vm.loadAllData();

    window.addEventListener('resize', chartsResize, false);

    var resizeTimer;

    function chartsResize() {
      if (resizeTimer) {
        clearTimeout(resizeTimer)
      }
      resizeTimer = setTimeout(function () {
        vm.newAddedUsersLineChart && vm.newAddedUsersLineChart.resize()
        vm.newAddedUserPieChart && vm.newAddedUserPieChart.resize()
        vm.newAddedUsersLineMainSiteChart && vm.newAddedUsersLineMainSiteChart.resize()

      }, 100);
    }

  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingNewAddedUsersLineChart: false,
      loadingNewAddedUsersLineChartText: "加载中...",
      loadingNewAddedUserPieChart: false,
      loadingNewAddedUserPieChartText: "加载中...",
      loadingnewAddedUsersLineMainSite: false,
      loadingnewAddedUsersLineMainSiteText: "加载中...",
      loadingTableData: false
    }
  }
}


</script>

<style>
.register-analysis-page {
  padding-bottom: 50px;
}

.register-panel-title {
  height: 60px;
  line-height: 60px;
  font-size: 20px;
  /*padding-top: 15px;
  padding-bottom: 15px;*/
}

.text-right {
  text-align: right;
}

.margin-right-20 {
  margin-right: 20px;
}
</style>
