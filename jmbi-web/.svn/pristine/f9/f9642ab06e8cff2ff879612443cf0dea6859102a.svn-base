<template>
  <div class="dealing-analysis-page">
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>成交分析</el-breadcrumb-item>
      <el-breadcrumb-item>交易分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <master-detail-chart panelTitle="交易全站 订单分析（行业/类目）" masterChartTitle="订单" :startDate="startDate" :endDate="endDate" MasterApi="industryOrderAmount" DetailApi="industryOrderAmountDetail"></master-detail-chart>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">订单笔数地域分布
  
      </header>
      <div class="box-title">
        <span>卖家</span>
      </div>
      <div class="geographical-amount-btn-wrapper">
        <el-button size="small" :type="primarySeller1" @click="loadSellerData('ordernum')">笔数</el-button>
        <el-button size="small" :type="primarySeller2" @click="loadSellerData('paytotal')">金额</el-button>
      </div>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" v-loading="loadingdealAreaAmountSellerMap" element-loading-text="加载中...">
            <div class="content" ref='sellerOrderAmountMapChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <div class="box  chart row-group-last" :class="{'no-data': loadingdealAreaAmountSellerText === '暂无数据'}" v-loading="loadingdealAreaAmountSellerBar" :element-loading-text="loadingdealAreaAmountSellerText">
            <div class="content" ref='sellerOrderAmountBarChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">订单笔数地域分布
  
      </header>
      <div class="box-title">
        <span>买家</span>
      </div>
      <div class="geographical-amount-btn-wrapper">
        <el-button size="small" :type="primaryBuyer1" @click="loadBuyerData('ordernum')">笔数</el-button>
        <el-button size="small" :type="primaryBuyer2" @click="loadBuyerData('paytotal')">金额</el-button>
      </div>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" v-loading="loadingdealAreaAmountBuyerMap" element-loading-text="加载中...">
            <div class="content" ref='buyerOrderAmountMapChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <div class="box  chart row-group-last" :class="{'no-data': loadingdealAreaAmountBuyerText === '暂无数据'}" v-loading="loadingdealAreaAmountBuyerBar" :element-loading-text="loadingdealAreaAmountBuyerText">
            <div class="content" ref='buyerOrderAmountBarChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
  </div>
</template>

<script>

import {
  dealAreaAmount,
  shopOpenPerfect
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker2'
import masterDetailChart from '@/components/ZZ/masterDetailChart/_dealingAnalysis'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'geographicalAmount',
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
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.loadAllData();
    },
    loadAllData() {

      this.loadBuyerData(this.buyerType)
      this.loadSellerData(this.sellerType)

    },

    getBuyerPrimary(type) {
      return this.buyerType === type ? "primary" : '';
    },
    getSellerPrimary(type) {
      return this.sellerType === type ? "primary" : '';
    },
    loadBuyerData(sortField) {
      var vm = this;
      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;
      vm.buyerType = sortField;

      vm.primaryBuyer1 = vm.getBuyerPrimary('ordernum')
      vm.primaryBuyer2 = vm.getBuyerPrimary('paytotal')

      vm.loadingdealAreaAmountBuyerMap = true
      vm.loadingdealAreaAmountBuyerBar = true
      vm.loadingdealAreaAmountBuyerText = '加载中...'
      dealAreaAmount({
        platform,
        startDate,
        endDate,
        role: 'buyer',
        sortField: sortField
      }).then(function (response) {
        vm.loadingdealAreaAmountBuyerMap = false
        vm.loadingdealAreaAmountBuyerBar = false
        var chartData = response.data
        if (chartData.length === 0) {
          vm.loadingdealAreaAmountBuyerBar = true
          vm.loadingdealAreaAmountBuyerText = '暂无数据'
        }
        vm.loadOrderAmountMapChart(chartData, sortField, "buyerOrderAmountMapChart")

        let yAxisData = [];
        let seriesData = [];

        chartData.forEach(function (element) {
          yAxisData.push(element.area_name)
          seriesData.push(element[sortField])
        });

        vm.loadOrderAmountBarChart({ yAxisData, seriesData }, sortField, "buyerOrderAmountBarChart")

      })
        .catch(function (error) {
          console.log(error);
        });

    },
    loadSellerData(sortField) {
      var vm = this;
      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;
      vm.sellerType = sortField;

      vm.primarySeller1 = vm.getSellerPrimary('ordernum')
      vm.primarySeller2 = vm.getSellerPrimary('paytotal')

      vm.loadingdealAreaAmountSellerMap = true
      vm.loadingdealAreaAmountSellerBar = true
      vm.loadingdealAreaAmountSellerText = '加载中...'
      dealAreaAmount({
        platform,
        startDate,
        endDate,
        role: 'seller',
        sortField: sortField
      }).then(function (response) {
        vm.loadingdealAreaAmountSellerMap = false
        vm.loadingdealAreaAmountSellerBar = false
        var chartData = response.data
        if (chartData.length === 0) {
          vm.loadingdealAreaAmountSellerBar = true
          vm.loadingdealAreaAmountSellerText = '暂无数据'
        }
        vm.loadOrderAmountMapChart(chartData, sortField, "sellerOrderAmountMapChart")

        let yAxisData = [];
        let seriesData = [];

        chartData.forEach(function (element) {
          yAxisData.push(element.area_name)
          seriesData.push(element[sortField])
        });

        vm.loadOrderAmountBarChart({ yAxisData, seriesData }, sortField, "sellerOrderAmountBarChart")

      })
        .catch(function (error) {
          console.log(error);
        });

    },
    loadOrderAmountMapChart(chartData, sortField, chartId) {
      var vm = this;

      let maxValue = 0;

      chartData.forEach(function (data) {
        let v = Number(data[sortField])
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
          show: false,
          orient: 'vertical',
          left: 'left',
          data: [sortField]
        },
        visualMap: {
          min: 0,
          max: maxValue,
          left: 'left',
          top: 'bottom',
          text: ['高', '低'],           // 文本，默认为数值文本
          inRange: {
            color: ['#e0ffff', '#006edd']
          },
          calculable: true
        },

        series: [
          {
            name: '订单笔数地域分布',
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
            data: chartData.map(function (item) {
              let name = item.area_name || '';
              name = name.replace('自治区', '')
              name = name.replace('省', '')
              name = name.replace('市', '')

              return {
                name: name,
                value: item[sortField]
              }
            })
          }
        ]
      };

      vm.$initChart(vm, chartId, option)
    },

    loadOrderAmountBarChart(chartData, sortField, chartId) {
      var vm = this;

      let typeName = sortField === 'ordernum' ? '笔数' : '金额'

      let xAxis = {
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#008acd'
          }
        }
      };
      if (sortField === 'ordernum') {
        xAxis.name = "笔"
        // 笔数
        // 从0开始，最小间隔为整数。
        let maxVal = Math.max(...chartData.seriesData)
        // 如果没有数据，又设置了splitNumber和minInterval，则改坐标轴不会显示的bug
        if (maxVal < 5 && maxVal > 0) {
          xAxis.splitNumber = maxVal
          xAxis.minInterval = 1

        }
      } else {
        xAxis.name = "万元"
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
          data: chartData.yAxisData.reverse()
        },
        series: [{
          name: "订单笔数地域分布",
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#1790cf',
              barBorderRadius: 2
            }
          },
          data: chartData.seriesData.reverse()
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
        vm.sellerOrderAmountMapChart && vm.sellerOrderAmountMapChart.resize()
        vm.sellerOrderAmountBarChart && vm.sellerOrderAmountBarChart.resize()
        vm.buyerOrderAmountMapChart && vm.buyerOrderAmountMapChart.resize()
        vm.buyerOrderAmountBarChart && vm.buyerOrderAmountBarChart.resize()
      }, 100);

    }

  },
  data() {
    return {
      startDate: util.firstDayOfMonth().format(),
      endDate: new Date().format(),
      loadingTODOChart: false,
      buyerType: "ordernum",
      primaryBuyer1: 'primary',
      primaryBuyer2: '',
      sellerType: "ordernum",
      primarySeller1: 'primary',
      primarySeller2: '',
      loadingdealAreaAmountBuyerMap: false,
      loadingdealAreaAmountBuyerBar: false,
      loadingdealAreaAmountBuyerText: '加载中...',
      loadingdealAreaAmountSellerMap: false,
      loadingdealAreaAmountSellerBar: false,
      loadingdealAreaAmountSellerText: '加载中...',
      loadingIndustryOrderAnalysis: false,
      loadingIndustryOrderAnalysisText: '加载中...',
      loadingIndustryOrderAnalysisDetail: false,
      loadingIndustryOrderAnalysisDetailText: '加载中...'

    }
  }
}


</script>

<style lang="scss">
.dealing-analysis-page {

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

.box-title {
  background: #fff;
  text-align: center;
  padding: 10px;
  font-size: 16px;
}

.box-title span {
  display: inline-block;
  padding: 10px 65px;
  border-radius: 5px;
  color: #fff;
  background: #0086e9;
}

.geographical-amount-btn-wrapper {
  background: #fff;
  padding-left: 30px;
}
</style>
