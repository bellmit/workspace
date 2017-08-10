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
      <el-breadcrumb-item>成交概览</el-breadcrumb-item>
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
                <el-table-column prop="name" label="订单">
                </el-table-column>
                <el-table-column prop="number" :label="tableDataPeriod">
                </el-table-column>
                <el-table-column prop="totalNumber" label="本年累计">
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
          <header class="panel-header jm-grid-border-bottom-1px">交易转化跟踪
  
          </header>
  
          <chart-box v-on:loadOption="loadtradeTrackChart" apiName="jytTranslate" :apiParams="ApiParams" seriesDataProperty="seriesData"></chart-box>
  
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">结算方式占比
  
          </header>
  
          <chart-box v-on:loadOption="loadPieChart" apiName="jytDealSettle" :apiParams="ApiParams" seriesDataProperty="seriesData"></chart-box>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 交易转化跟踪 & 付款天数用户占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">运输方式占比
  
          </header>
  
          <chart-box v-on:loadOption="loadPieChart" apiName="jytDealLineType" :apiParams="ApiParams" seriesDataProperty="seriesData"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">支付渠道金额占比
  
          </header>
  
          <chart-box v-on:loadOption="loadPieChart" apiName="jytPayChannel" :apiParams="ApiParams" seriesDataProperty="seriesData"></chart-box>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 交易转化跟踪 & 付款天数用户占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">订单来源占比
  
          </header>
  
          <chart-box v-on:loadOption="loadPieChart" apiName="jytDealOrderSource" :apiParams="ApiParams" seriesDataProperty="seriesData"></chart-box>
  
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">订单类型占比
  
          </header>
  
          <chart-box v-on:loadOption="loadPieChart" apiName="jytDealOrderType" :apiParams="ApiParams" seriesDataProperty="seriesData"></chart-box>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 确认收货货时间 & 结算方式占比 -->
  
  </div>
</template>

<script>

import {
  jytDealOperate,
  jytTranslate,
  dealDelivery,
  formatDate
} from '@/service/api'

import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker2'
import chartBox from '@/components/chartBox'

import echarts from 'echarts';

export default {
  name: 'dealing',
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
    tableDataPeriod() {
      return `${util.formatDate(this.startDate)} - ${util.formatDate(this.endDate)}`
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
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.loadAllData();
    },
    loadAllData() {
      var vm = this;
      vm.loadTableData();


    },
    loadTableData() {
      var vm = this;
      var platform = vm.$platformId;
      var startDate = this.startDate;
      var endDate = this.endDate;

      vm.loadingdealOperate = true

      jytDealOperate({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealOperate = false
        var data = response.data

        vm.tableData = data;



      })
        .catch(function (error) {
          console.log(error);
        });
    },
    loadtradeTrackChart({ chartData, context, chartId }) {
      var vm = this;

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

      context.$initChart(context, chartId, option)
    },


    loadPieChart({ chartData, context, chartId }) {
      var vm = this;

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
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

      // vm.payedDaysUserChart.setOption(option)
      context.$initChart(context, chartId, option)
    },


  },

  mounted() {
    var vm = this;

    vm.loadAllData();

  },
  data() {
    return {
      startDate: util.firstDayOfMonth().format(),
      endDate: new Date().format(),

      loadingdealOperate: false,

      tableData: [
      ]
    }
  }
}


</script>

<style>

</style>
