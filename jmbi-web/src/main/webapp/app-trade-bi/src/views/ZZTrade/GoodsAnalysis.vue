<template>
  <div class="shop-activate-page">
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>商品分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
  
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">发布商品数
          </header>
  
          <div class="box chart row-group-last" v-loading="loadingPublishGoodsAmountChart" element-loading-text="加载中...">
            <div class="content" ref='PublishGoodsAmountChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 发布商品数-->
  
    <master-detail-chart panelTitle="发布商品数行业/类目占比" masterChartTitle="发布商品数行业占比" :startDate="startDate" :endDate="endDate" MasterApi="mainPublishGoodsIndustry" DetailApi="mainPublishGoodsIndustryDetail"></master-detail-chart>
  
    <!--<el-row class="jm-grid-border-1px">
    
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
          <div class="grid-content jm-grid-box-bg-white">
            <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">商品行业差异综合分析
            </header>
    
            <div class="box chart goods-industry-diff-chart" :class="{'no-data': loadingGoodsIndustryDiffChartText === '暂无数据'}" v-loading="loadingGoodsIndustryDiffChart" :element-loading-text="loadingGoodsIndustryDiffChartText">
              <div class="content" ref='goodsIndustryDiffChart'>
    
              </div>
    
            </div>
          </div>
        </el-col>
    
      </el-row>-->
    <!--end of 商品 行业差异 综合分析-->
  
  </div>
</template>

<script>

import {
  mainPublishGoodsNumber,
  shopOpenPerfect,
  bubbleGradient
} from '@/service/api'


import rangeDatePicker from '@/components/RangeDatePicker'
import masterDetailChart from '@/components/ZZ/masterDetailChart/_goodsAnalysis'

import echarts from 'echarts';


export default {
  name: 'GoodsAnalysis',
  components: {
    rangeDatePicker,
    masterDetailChart
  },
  created() {

    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }
  },
  methods: {
    getRangeDate({
      startDate,
      endDate
    }) {
      this.startDate = startDate;
      this.endDate = endDate;

      this.loadAllData();
    },
    loadAllData() {
      var vm = this;

      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;

      // 发布商品数
      vm.loadingPublishGoodsAmountChart = true
      mainPublishGoodsNumber({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingPublishGoodsAmountChart = false
        var chartData = response.data
        vm.loadPublishGoodsChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


      // vm.loadingGoodsIndustryDiffChart = true
      // vm.loadingGoodsIndustryDiffChartText = '加载中...'
      // bubbleGradient({
      //   platform,
      //   startDate,
      //   endDate
      // }).then(function (response) {
      //   vm.loadingGoodsIndustryDiffChart = false
      //   var chartData = response.data
      //   if (chartData.data.length === 0) {
      //     vm.loadingGoodsIndustryDiffChart = true
      //     vm.loadingGoodsIndustryDiffChartText = '暂无数据'
      //   }

      //   vm.$clearChartData(vm, 'goodsIndustryDiffChart')
      //   vm.loadgoodsIndustryDiffChart(chartData)

      // })
      //   .catch(function (error) {
      //     console.log(error);
      //   });

    },
    // 发布商品数
    loadPublishGoodsChart(chartData) {
      var vm = this;
      var chartId = "PublishGoodsAmountChart"

      var lineChart = chartData.lineChart;

      let yAxis = {
        type: 'value'
      };

      // 从0开始，最小间隔为整数。
      let maxVal = Math.max(...lineChart.seriesData)
      if (maxVal < 5) {
        yAxis.splitNumber = maxVal || 1
        yAxis.minInterval = 1

      }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          boundaryGap: false,
          data: lineChart.xAxisData
        }],
        yAxis: yAxis,
        series: [{
          name: lineChart.legendData,
          type: 'line',
          smooth: true,
          areaStyle: {
            normal: {}
          },
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
          data: lineChart.seriesData
        }

        ]
      };

      vm.$initChart(vm, chartId, option)
    },

    loadgoodsIndustryDiffChart(chartData) {
      var vm = this;

      var chartId = "goodsIndustryDiffChart"

      var legendData = [];
      var seriesData = chartData.data.map(x => {
        legendData.push(x[3])
        return {
          name: x[3],
          data: [x],
          type: 'scatter',
          symbolSize: function (data) {
            return Math.sqrt(data[2]) / 8;
          },
          label: {
            emphasis: {
              show: true,
              formatter: function (param) {
                return param.data[3];
              },
              position: 'top'
            }
          }
        }
      })

      var option = {
        title: {
          text: 'Z-平均价格',
          top: '8%',
          right: '5%',
          textStyle: {
            fontWeight: '100',
            fontSize: 14
          }
        },
        tooltip: {
          show: true,
          trigger: 'item',
          formatter: function (obj) {
            console.log('ffff', obj)
            return `${obj.seriesName}<br>${obj.marker}浏览量: ${obj.data[1]}次<br>${obj.marker}转化率: ${obj.data[0]}%<br>${obj.marker}平均价格: ${obj.data[2]}万元`;
          }
        },
        legend: {
          left: 'center',
          data: legendData
        },
        xAxis: {
          name: '转化率',
          splitLine: {
            lineStyle: {
              type: 'dashed'
            }
          }
        },
        yAxis: {
          name: '浏览量',
          splitLine: {
            lineStyle: {
              type: 'dashed'
            }
          },
          scale: true
        },
        series: seriesData
      };

      vm.$initChart(vm, chartId, option)
    }

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
        vm.PublishGoodsAmountChart && vm.PublishGoodsAmountChart.resize()
        vm.goodsIndustryDiffChart && vm.goodsIndustryDiffChart.resize()

      }, 100);
    }

  },
  data() {
    return {
      cancelSource: '',
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingPublishGoodsAmountChart: false,
      loadingTODOChart: false,
      loadingTODO: false,
      loadingGoodsIndustryDiffChart: false,
      loadingGoodsIndustryDiffChartText: '加载中...',
      masterSeriesData: []
    }
  }
}



</script>

<style lang="scss">
.shop-activate-page {

  padding-bottom: 20px; // .chart-title {
  //   margin: 0;
  //   padding: 35px 0 0;
  //   font-size: 36px;
  //   text-align: center;
  //   span {
  //     margin-left: 5px;
  //     font-size: 18px;
  //     color: #00CCFF;
  //   }
  // }
  .sub-chart-title {
    font-size: 22px;
    padding-bottom: 8px;
  }
}

.goods-industry-diff-chart {
  height: 500px;
}
</style>
