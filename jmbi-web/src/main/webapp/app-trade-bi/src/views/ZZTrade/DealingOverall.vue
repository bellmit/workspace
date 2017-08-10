<template>
  <div>
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>成交分析</el-breadcrumb-item>
      <el-breadcrumb-item>总体情况</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
  
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">{{tableDataPeriod}}平台运营情况表
  
          </header>
  
          <div class="box " v-loading="loadingdealOperate" element-loading-text="加载中...">
            <div class="content">
              <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="title" label="订单">
                </el-table-column>
                <el-table-column prop="monthAmount" :label="tableDataPeriod">
                </el-table-column>
                <el-table-column prop="yearAmount" label="本年累计">
                </el-table-column>
  
              </el-table>
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 平台运营情况表-->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">交易全站 整体转化跟踪</header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealTranslateChartText === '暂无数据'}" v-loading="loadingdealTranslateChart" :element-loading-text="loadingdealTranslateChartText">
            <div class="content" ref='tradeTrackChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">交易全站 交货时间占比</header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdeliverGoodsChartText === '暂无数据'}" v-loading="loadingdeliverGoodsChart" :element-loading-text="loadingdeliverGoodsChartText">
            <div class="content" ref='deliverGoodsChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 整体转化跟踪 & 交易全站 交货时间占比 -->
  
    <master-detail-chart panelTitle="交易全站 合同方式占比" clearingType="contract_type" masterChartTitle="合同方式占比" detailChartTitle="合同方式占比" :startDate="startDate" :endDate="endDate" MasterApi="dealContract" DetailApi="industryPieChartsDetail"></master-detail-chart>
  
    <master-detail-chart panelTitle="交易全站 结算方式占比" clearingType="clearing_type" masterChartTitle="结算方式占比" detailChartTitle="结算方式占比" :startDate="startDate" :endDate="endDate" MasterApi="dealSettle" DetailApi="industryPieChartsDetail"></master-detail-chart>
  
    <master-detail-chart panelTitle="交易全站 支付方式占比" clearingType="payment_type" masterChartTitle="支付方式占比" detailChartTitle="支付方式占比" :startDate="startDate" :endDate="endDate" MasterApi="dealPay" DetailApi="industryPieChartsDetail"></master-detail-chart>
  
  </div>
</template>

<script>

import {
  dealOperate,
  dealTranslate,
  dealDelivery,
  formatDate
} from '@/service/api'

import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker2'
import masterDetailChart from '@/components/ZZ/masterDetailChart/_dealingOverall'

import echarts from 'echarts';
import 'echarts/map/js/china'


export default {
  name: 'DealingOverall',
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
  computed: {
    tableDataPeriod() {
      return `${util.formatDate(this.startDate)} - ${util.formatDate(this.endDate)}`
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
      vm.loadTableData();

      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;

      vm.loadingdealTranslateChart = true
      vm.loadingdealTranslateChartText = '加载中...'
      dealTranslate({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealTranslateChart = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealTranslateChart = true
          vm.loadingdealTranslateChartText = '暂无数据'
        }
        vm.loadtradeTrackChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingdeliverGoodsChart = true
      vm.loadingdeliverGoodsChartText = '加载中...'
      dealDelivery({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdeliverGoodsChart = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdeliverGoodsChart = true
          vm.loadingdeliverGoodsChartText = '暂无数据'
        }
        vm.loaddeliverGoodsChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });



    },
    loadTableData() {
      var vm = this;
      var platform = vm.$platformId;
      var startDate = this.startDate;
      var endDate = this.endDate;

      vm.loadingdealOperate = true

      dealOperate({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealOperate = false
        var data = response.data
        var monthData = data[0]
        var yearData = data[1]
        var tableData = [{
          title: '订单量',
          monthAmount: monthData.order_count,
          yearAmount: yearData.order_count
        }, {
          title: '金额（万元）',
          monthAmount: monthData.order_money,
          yearAmount: yearData.order_money
        },
        {
          title: '支付笔数',
          monthAmount: monthData.payment_count,
          yearAmount: yearData.payment_count
        },
        {
          title: '支付金额（万元）',
          monthAmount: monthData.payment_money,
          yearAmount: yearData.payment_money
        }];
        vm.tableData = tableData;



      })
        .catch(function (error) {
          console.log(error);
        });
    },
    loadtradeTrackChart(chartData) {
      var vm = this;

      var chartId = 'tradeTrackChart'

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c}"
        },

        legend: {
          top: 5,
          data: chartData.legendData
        },
        calculable: true,
        series: [
          {
            name: chartData.seriesName,
            type: 'funnel',
            left: '10%',
            top: 60,
            bottom: 60,
            width: '80%',
            sort: 'descending',
            gap: 2,
            label: {
              normal: {
                show: true,
                position: 'inside',
                textStyle: {
                  color: '#000'
                }
              },
              emphasis: {
                textStyle: {
                  fontSize: 20,
                  color: '#000'
                }
              }
            },
            labelLine: {
              normal: {
                length: 10,
                lineStyle: {
                  width: 1,
                  type: 'solid'
                }
              }
            },
            itemStyle: {
              normal: {
                borderColor: '#fff',
                borderWidth: 1
              }
            },
            data: chartData.seriesData
          }
        ]
      };

      vm.$initChart(vm, chartId, option)
    },

    loaddeliverGoodsChart(chartData) {
      var vm = this;

      var chartId = "deliverGoodsChart"
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
          name: chartData.seriesName,
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data: chartData.seriesData,

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
        vm.tradeTrackChart && vm.tradeTrackChart.resize()
        vm.deliverGoodsChart && vm.deliverGoodsChart.resize()

      }, 100);

    }

  },
  data() {
    return {
      startDate: util.firstDayOfMonth().format(),
      endDate: new Date().format(),
      loadingdealTranslateChart: false,
      loadingdealTranslateChartText: '加载中...',

      loadingdeliverGoodsChart: false,
      loadingdeliverGoodsChartText: '加载中...',

      loadingdealOperate: false,

      tableData: [
      ]
    }
  }
}


</script>

<style>

</style>
