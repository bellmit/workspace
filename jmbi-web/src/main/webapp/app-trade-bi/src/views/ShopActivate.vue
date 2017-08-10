<template>
  <div class="shop-activate-page">
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>店铺分析</el-breadcrumb-item>
      <el-breadcrumb-item>店铺激活</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
  
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">开通店铺趋势图
          </header>
  
          <div class="box chart row-group-last" v-loading="loadingOpenedShopChart" element-loading-text="加载中...">
            <div class="content" ref='openedShopChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 开通店铺趋势图-->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">基础资料完善统计
          </header>
  
          <div class="box sixteen-nine" v-loading="loadingBaseInfoStatisticsChart" element-loading-text="加载中...">
            <div class="content" ref='baseInfoStatisticsChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">基础资料完善占比
  
          </header>
  
          <div class="box sixteen-nine" v-loading="loadingBaseInfoStatisticsChart" element-loading-text="加载中...">
            <div class="content" ref='baseInfoProportionChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 基础资料完善统计 & 基础资料完善占比 -->
  
  </div>
</template>

<script>

import {
  shopOpenTrend,
  shopOpenPerfect
} from '@/service/api'


import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';


export default {
  name: 'shopActived',
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

      // 开通店铺趋势图
      vm.loadingOpenedShopChart = true
      shopOpenTrend({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingOpenedShopChart = false
        var chartData = response.data
        vm.loadopenedShopChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      // 基础资料完善统计及占比
      vm.loadingBaseInfoStatisticsChart = true
      shopOpenPerfect({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingBaseInfoStatisticsChart = false
        var chartData = response.data
        vm.loadbaseInfoStatisticsChart(chartData)
        vm.loadbaseInfoProportionChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

    },
    // 开通店铺趋势图
    loadopenedShopChart(chartData) {
      var vm = this;
      var chartId = "openedShopChart"
      // 基于准备好的dom，初始化echarts实例
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      let yAxis = {
            type: 'value'
          };
 
        // 从0开始，最小间隔为整数。
        let maxVal = Math.max(...chartData.seriesData)
        if (maxVal < 5) {
          yAxis.splitNumber = maxVal || 1
          yAxis.minInterval = 1
          
        }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis',
          // axisPointer: {
          //   type: 'cross',
          //   label: {
          //     backgroundColor: '#6a7985'
          //   }
          // }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: chartData.xAxisData
          }
        ],
        yAxis: [
          yAxis
        ],
        series: [
          {
            name: '开通店铺趋势图',
            type: 'line',
            smooth: true,
            areaStyle: { normal: {} },
            itemStyle: {
              normal: {
                color: '#abd6f3'
              }
            },
            lineStyle: {
              normal: {
                color: '#5eb2ec'
              }
            },
            data: chartData.seriesData
          }

        ]
      };

      vm[chartId].setOption(option)
    },

    loadbaseInfoStatisticsChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.baseInfoStatisticsChart = echarts.init(vm.$refs.baseInfoStatisticsChart);

      let xAxis = {
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        }
      let maxVal = Math.max(...chartData.seriesData)
        // 如果没有数据，又设置了splitNumber和minInterval，则改坐标轴不会显示的bug
        if (maxVal < 5) {
          xAxis.splitNumber = maxVal || 1
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
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: xAxis,
        yAxis: {
          type: 'category',
          // splitLine: {
          //   show: true
          // },
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          },
          data: chartData.yAxisData
        },
        series: [
          {
            name: '基础资料完善统计',
            type: 'bar',
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: '#5eb2ed',
                barBorderRadius: 2
              }
            },
            data: chartData.seriesData
          }

        ]
      };

      vm.baseInfoStatisticsChart.setOption(option)
    },


    loadbaseInfoProportionChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.baseInfoProportionChart = echarts.init(vm.$refs.baseInfoProportionChart);

      var newSeriesData = [];

      chartData.seriesData.forEach(function (value, index) {
        newSeriesData.push({
          value: value,
          name: chartData.yAxisData[index]
        })
      });


      // 绘制图表
      var option = {
        color: ['#1790cf', '#3ab882', '#fcb738', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          //text: chartData.title,
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
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
          name: "基础资料完善占比",
          type: 'pie',
          radius: ['30%', '60%'],
          center: ['50%', '55%'],
          data: newSeriesData,
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.baseInfoProportionChart.setOption(option)
    },

  },
  mounted() {
    var vm = this;

    vm.loadAllData()

    // vm.loadshopUvChart();

    // vm.loadshopStayPeriodChart();

    window.addEventListener('resize', chartsResize, false);

    var resizeTimer;

    function chartsResize() {
      if (resizeTimer) {
        clearTimeout(resizeTimer)
      }
      resizeTimer = setTimeout(function () {
        vm.openedShopChart && vm.openedShopChart.resize()
        vm.baseInfoStatisticsChart && vm.baseInfoStatisticsChart.resize()
        vm.baseInfoProportionChart && vm.baseInfoProportionChart.resize()
        vm.shopUvChart && vm.shopUvChart.resize()
        vm.shopStayPeriodChart && vm.shopStayPeriodChart.resize()



      }, 100);
    }

  },
  data() {
    return {
      cancelSource: '',
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingOpenedShopChart: false,
      loadingBaseInfoStatisticsChart: false,
      loadingTODOChart: false,
      loadingTODO: false
    }
  }
}


</script>

<style>
.shop-activate-page {
  padding-bottom: 20px;
}
</style>
