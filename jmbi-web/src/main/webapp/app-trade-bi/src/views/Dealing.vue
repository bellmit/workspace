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
          <header class="panel-header jm-grid-border-bottom-1px">交易转化跟踪
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealTranslateChartText === '暂无数据'}" v-loading="loadingdealTranslateChart" :element-loading-text="loadingdealTranslateChartText">
            <div class="content" ref='tradeTrackChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">付款天数用户占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingpaymentDaysChartText === '暂无数据'}" v-loading="loadingpaymentDaysChart" :element-loading-text="loadingpaymentDaysChartText">
            <div class="content" ref='payedDaysUserChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 交易转化跟踪 & 付款天数用户占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">提货方式用户占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealPickupChartText === '暂无数据'}" v-loading="loadingdealPickupChart" :element-loading-text="loadingdealPickupChartText">
            <div class="content" ref='pickUpGoodsChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">交货时间占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdeliverGoodsChartText === '暂无数据'}" v-loading="loadingdeliverGoodsChart" :element-loading-text="loadingdeliverGoodsChartText">
            <div class="content" ref='deliverGoodsChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 交易转化跟踪 & 付款天数用户占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">确认收货时间占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingconfirmReceivingGoodsText === '暂无数据'}" v-loading="loadingconfirmReceivingGoods" :element-loading-text="loadingconfirmReceivingGoodsText">
            <div class="content" ref='confirmReceivingGoodsChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">结算方式占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealSettleText === '暂无数据'}" v-loading="loadingdealSettle" :element-loading-text="loadingdealSettleText">
            <div class="content" ref='settlementMethodChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 确认收货货时间 & 结算方式占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">支付方式占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealPayText === '暂无数据'}" v-loading="loadingdealPay" :element-loading-text="loadingdealPayText">
            <div class="content" ref='methodOfPayChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">合同方式占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealContractText === '暂无数据'}" v-loading="loadingdealContract" :element-loading-text="loadingdealContractText">
            <div class="content" ref='formOfTreatyChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 支付方式 & 合同方式 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">支付渠道金额占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealPayChannelText === '暂无数据'}" v-loading="loadingdealPayChannel" :element-loading-text="loadingdealPayChannelText">
            <div class="content" ref='channelOfDisbursementChart'>
  
            </div>
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!-- end of 支付渠道金额占比 -->
  
  </div>
</template>

<script>

import {
  paymentDays,
  dealOperate,
  dealTranslate,
  dealPickup,
  dealDelivery,
  dealReceipt,
  dealSettle,
  dealPay,
  dealContract,
  dealPayChannel,
  formatDate
} from '@/service/api'

import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker2'

import echarts from 'echarts';
import 'echarts/map/js/china'


export default {
  name: 'Increased',
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

// 付款天数用户占比
        vm.loadingpaymentDaysChart = true
      vm.loadingpaymentDaysChartText = '加载中...'
      paymentDays({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingpaymentDaysChart = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingpaymentDaysChart = true
          vm.loadingpaymentDaysChartText = '暂无数据'
        }
        vm.loadpayedDaysUserChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingdealPickupChart = true
      vm.loadingdealPickupChartText = '加载中...'
      dealPickup({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealPickupChart = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealPickupChart = true
          vm.loadingdealPickupChartText = '暂无数据'
        }
        vm.loadpickUpGoodsChart(chartData)

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

      vm.loadingconfirmReceivingGoods = true
      vm.loadingconfirmReceivingGoodsText = '加载中...'
      dealReceipt({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingconfirmReceivingGoods = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingconfirmReceivingGoods = true
          vm.loadingconfirmReceivingGoodsText = '暂无数据'
        }
        vm.loadconfirmReceivingGoodsChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingdealSettle = true
      vm.loadingdealSettleText = '加载中...'
      dealSettle({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealSettle = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealSettle = true
          vm.loadingdealSettleText = '暂无数据'
        }
        vm.loadsettlementMethodChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingdealPay = true
      vm.loadingdealPayText = '加载中...'
      dealPay({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealPay = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealPay = true
          vm.loadingdealPayText = '暂无数据'
        }
        vm.loadmethodOfPayChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingdealContract = true
      vm.loadingdealContractText = '加载中...'
      dealContract({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealContract = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealContract = true
          vm.loadingdealContractText = '暂无数据'
        }
        vm.loadformOfTreatyChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingdealPayChannel = true
      vm.loadingdealPayChannelText = '加载中...'
      dealPayChannel({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealPayChannel = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealPayChannel = true
          vm.loadingdealPayChannelText = '暂无数据'
        }
        vm.loadchannelOfDisbursementChart(chartData)

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
      // 基于准备好的dom，初始化echarts实例
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      //var pvChartData = chartData.pvChart

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

      vm[chartId].setOption(option)
    },

    // 付款天数用户占比
    loadpayedDaysUserChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.payedDaysUserChart = echarts.init(vm.$refs.payedDaysUserChart);

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

      vm.payedDaysUserChart.setOption(option)
    },

    loaddeliverGoodsChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.deliverGoodsChart = echarts.init(vm.$refs.deliverGoodsChart);

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

      vm.deliverGoodsChart.setOption(option)
    },

    // 提货方式用户占比
    loadpickUpGoodsChart(chartData) {
      var vm = this
      // 基于准备好的dom，初始化echarts实例
      vm.pickUpGoodsChart = echarts.init(vm.$refs.pickUpGoodsChart);

      // 绘制图表
      var option = {
        color: ['#1bb2d8', '#1790cf', '#fcb738', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        // title: {
        //   text: '三证合一比例',
        //   textStyle: {
        //     fontSize: 16,
        //     fontWeight: 'normal'
        //   },
        //   left: 10,
        //   top: 10

        // },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          left: 'center',
          top: '10%',
          data: chartData.legendData//['直接访问', '邮件营销']
        },
        series: [{
          name: chartData.seriesName,
          type: 'pie',
          radius: '50%',
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

      vm.pickUpGoodsChart.setOption(option)


    },

    loadconfirmReceivingGoodsChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.confirmReceivingGoodsChart = echarts.init(vm.$refs.confirmReceivingGoodsChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
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

      vm.confirmReceivingGoodsChart.setOption(option)
    },

    loadsettlementMethodChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.settlementMethodChart = echarts.init(vm.$refs.settlementMethodChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
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
          radius: ['40%', '60%'],
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

      vm.settlementMethodChart.setOption(option)
    },

    loadmethodOfPayChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.methodOfPayChart = echarts.init(vm.$refs.methodOfPayChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
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
          radius: ['40%', '60%'],
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

      vm.methodOfPayChart.setOption(option)
    },

    loadformOfTreatyChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.formOfTreatyChart = echarts.init(vm.$refs.formOfTreatyChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
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
          radius: ['40%', '60%'],
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

      vm.formOfTreatyChart.setOption(option)
    },

    loadchannelOfDisbursementChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.channelOfDisbursementChart = echarts.init(vm.$refs.channelOfDisbursementChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
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
          radius: ['40%', '60%'],
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

      vm.channelOfDisbursementChart.setOption(option)
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
        vm.sellerOrderAmountBarChart && vm.sellerOrderAmountBarChart.resize()
        vm.payedDaysUserChart && vm.payedDaysUserChart.resize()
        vm.pickUpGoodsChart && vm.pickUpGoodsChart.resize()
        vm.deliverGoodsChart && vm.deliverGoodsChart.resize()
        vm.confirmReceivingGoodsChart && vm.confirmReceivingGoodsChart.resize()
        vm.settlementMethodChart && vm.settlementMethodChart.resize()
        vm.methodOfPayChart && vm.methodOfPayChart.resize()
        vm.formOfTreatyChart && vm.formOfTreatyChart.resize()
        vm.channelOfDisbursementChart && vm.channelOfDisbursementChart.resize()

      }, 100);

    }

  },
  data() {
    return {
      startDate: util.firstDayOfMonth().format(),
      endDate: new Date().format(),
      loadingdealTranslateChart: false,
      loadingdealTranslateChartText: '加载中...',
      loadingdealPickupChart: false,
      loadingdealPickupChartText: '加载中...',
      loadingdeliverGoodsChart: false,
      loadingdeliverGoodsChartText: '加载中...',
      loadingconfirmReceivingGoods: false,
      loadingconfirmReceivingGoodsText: '加载中...',
      loadingdealSettle: false,
      loadingdealSettleText: '加载中...',
      loadingdealPay: false,
      loadingdealPayText: '加载中...',
      loadingdealContract: false,
      loadingdealContractText: '加载中...',
      loadingdealPayChannel: false,
      loadingdealPayChannelText: '加载中...',
      loadingdealOperate: false,
      loadingpaymentDaysChart: false,
      loadingpaymentDaysChartText:'加载中...',

      tableData: [
      ]
    }
  }
}


</script>

<style>

</style>
