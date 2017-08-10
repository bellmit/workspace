<template>
  <div>

    <el-breadcrumb separator=">" class="jm-margin-bottom20">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>总览</el-breadcrumb-item>
    </el-breadcrumb>

    <el-row :gutter="20" class="summary-wrapper jm-grid-border-1px">
      <header class="summary-title">历史累计：截止{{summaryData.nowDate}}</header>
      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-1 ">

            <p>
              <span class="number">{{summaryData.MemberTotal}}</span>
              <span class="unit">个</span>
            </p>
            <p class="title">累计注册会员</p>

          </div>
        </div>
      </el-col>

      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-2 ">

            <p>
              <span class="number">{{summaryData.OpenStoreTotal}}</span>
              <span class="unit">个</span>
            </p>
            <p class="title">累计店铺开通数</p>
          </div>
        </div>
      </el-col>

      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-3 ">

            <p>
              <span class="number">{{summaryData.ItemTotal}}</span>
              <span class="unit">件</span>
            </p>
            <p class="title">累计发布商品数</p>
          </div>
        </div>
      </el-col>

      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-4 ">

            <p>
              <span class="number">{{summaryData.OrderTotal}}</span>
              <span class="unit">笔</span>
            </p>
            <p class="title">累计订单数</p>
          </div>
        </div>
      </el-col>

      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-5 ">

            <p>
              <span class="number">{{summaryData.PayTotal}}</span>
              <span class="unit">笔</span>
            </p>
            <p class="title">累计支付笔数</p>
          </div>
        </div>
      </el-col>

      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-6 ">

            <p>
              <span class="number">{{summaryData.TranTotal}}</span>
              <span class="unit">万元</span>
            </p>
            <p class="title">累计支付金额</p>
          </div>
        </div>
      </el-col>

    </el-row>
    <!-- end of summary -->

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>

    <el-row>

      <el-col :span="24">

        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px zhibiao-activeTabName">
          <header class="panel-header jm-grid-border-bottom-1px">交易全站整体趋势

          </header>
          <el-tabs v-model="zhibiao_activeTabName" type="card" @tab-click="handle_zhibiao_Click" v-loading="loadingIndex" element-loading-text="加载中...">
            <el-tab-pane label="新增注册用户" name="newRegisterUser">

              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='newRegisterUserChart'>

                </div>
              </div>

            </el-tab-pane>
            <el-tab-pane label="开通店铺数" name="openStoreTotal">

              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='openStoreTotalChart'>

                </div>
              </div>

            </el-tab-pane>
            <el-tab-pane label="发布商品数" name="itemTotal">

              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='itemTotalChart'>

                </div>
              </div>

            </el-tab-pane>
            <el-tab-pane label="订单笔数" name="orderTotal">

              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='orderTotalChart'>

                </div>
              </div>

            </el-tab-pane>
            <el-tab-pane label="支付笔数" name="payTotal">

              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='payTotalChart'>

                </div>
              </div>

            </el-tab-pane>
            <el-tab-pane label="支付金额" name="tranTotal">

              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='tranTotalChart'>

                </div>
              </div>

            </el-tab-pane>
          </el-tabs>

        </div>
      </el-col>

    </el-row>
    <!-- end of zhibiao-->

    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">发布商品数行业占比</header>
          <p class="overview-chart-title">
            <span class="chart-title-key">{{goodsNumberIndustryTotal}}</span>
            <span class="chart-title-unit">个</span>
          </p>
          <div class="box sixteen-nine" :class="{'no-data': loadinggetItemProportionText === '暂无数据'}" v-loading="loadinggetItemProportion" :element-loading-text="loadinggetItemProportionText">
            <div class="content" ref='goodsNumberIndustryChart'>

            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">订单笔数行业占比
          </header>
          <!--<p class="chart-title">{{orderCountIndustryTotal}}
                  <span>笔</span>
                </p>-->
          <p class="overview-chart-title">
            <span class="chart-title-key">{{orderCountIndustryTotal}}</span>
            <span class="chart-title-unit">笔</span>
          </p>
          <div class="box sixteen-nine" :class="{'no-data': loadinggetOrderProportionText === '暂无数据'}" v-loading="loadinggetOrderProportion" :element-loading-text="loadinggetOrderProportionText">
            <div class="content" ref='orderCountIndustryChart'>

            </div>
          </div>

        </div>
      </el-col>
    </el-row>
    <!-- end of 发布商品数行业占比 & 订单笔数行业占比 -->

    <el-row :gutter="20">

      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">订单金额行业占比</header>
          <!--<p class="chart-title">{{orderAmountIndustryTotal}}
                  <span>万元</span>
                </p>-->
          <p class="overview-chart-title">
            <span class="chart-title-key">{{orderAmountIndustryTotal}}</span>
            <span class="chart-title-unit">万元</span>
          </p>
          <div class="box sixteen-nine" :class="{'no-data': loadinggetOrderMoneyProportionText === '暂无数据'}" v-loading="loadinggetOrderMoneyProportion" :element-loading-text="loadinggetOrderMoneyProportionText">
            <div class="content" ref='orderAmountIndustryChart'>

            </div>
          </div>
        </div>
      </el-col>

    </el-row>

  </div>
</template>

<script>
import {
  newlyIncreased,
  masOperateTotal,
  operateIndex,
  masOperateIndex,
  getItemProportion,
  getOrderProportion,
  getOrderMoneyProportion
} from '@/service/api'

import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';


export default {
  name: 'overview',
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

      var platform = vm.$platformId;
      var startDate = vm.startDate;
      var endDate = vm.endDate;

      vm.loadTabContentChart(vm.zhibiao_activeTabName)

      vm.loadinggetItemProportion = true
      vm.loadinggetItemProportionText = '加载中...'
      getItemProportion(
        startDate,
        endDate
      ).then(function (response) {
        vm.loadinggetItemProportion = false
        var chartData = response.data
        if (chartData.proportion.length === 0) {
          vm.loadinggetItemProportion = true
          vm.loadinggetItemProportionText = '暂无数据'
        }
        var chartId = 'goodsNumberIndustryChart';
        vm.goodsNumberIndustryTotal = chartData.total || 0;
        vm.loadOrderTypeChartOption(chartData, chartId, "发布商品数行业占比")
      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadinggetOrderProportion = true
      vm.loadinggetOrderProportionText = '加载中...'
      getOrderProportion(
        startDate,
        endDate
      ).then(function (response) {
        vm.loadinggetOrderProportion = false
        var chartData = response.data

        if (chartData.proportion.length === 0) {
          vm.loadinggetOrderProportion = true
          vm.loadinggetOrderProportionText = '暂无数据'
        }
        var chartId = 'orderCountIndustryChart';
        vm.orderCountIndustryTotal = chartData.total || 0;
        vm.loadOrderTypeChartOption(chartData, chartId, "订单笔数行业占比")
      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadinggetOrderMoneyProportion = true
      vm.loadinggetOrderMoneyProportionText = '加载中...'
      getOrderMoneyProportion(
        startDate,
        endDate
      ).then(function (response) {
        vm.loadinggetOrderMoneyProportion = false
        var chartData = response.data

        if (chartData.proportion.length === 0) {
          vm.loadinggetOrderMoneyProportion = true
          vm.loadinggetOrderMoneyProportionText = '暂无数据'
        }
        var chartId = 'orderAmountIndustryChart';
        vm.orderAmountIndustryTotal = chartData.total || 0;
        vm.loadOrderTypeChartOption(chartData, chartId, "订单金额行业占比")


      })
        .catch(function (error) {
          console.log(error);
        });



    },
    handle_zhibiao_Click(tab, event) {
      var vm = this;

      var id = tab.name;


      vm.$nextTick(function () {
        vm.loadTabContentChart(id)
      });

    },
    loadTabContentChart(id, reload = true) {
      var vm = this;
      var chartId = id + 'Chart';

      vm[chartId] = vm[chartId] || echarts.init(vm.$refs[chartId]);

      var platform = vm.$platformId;

      var startDate = vm.startDate,
        endDate = vm.endDate;


      if (id === "newRegisterUser") {
        vm.loadingIndex = true
        // 交易全站
        newlyIncreased({
          platform: 0,// 0表示交易全平台
          startDate,
          endDate,
          dataType: 'lineChart'
        }).then(function (response) {
          vm.loadingIndex = false
          let chartData = response.data

          //let chartId = "newAddedUsersLineChart";
          vm.loadnewAddedUsersLineChart(chartData, chartId)

        })
          .catch(function (error) {
            console.log(error);
          });
      } else {
        if (reload || vm.indexList === null) {
          vm.loadingIndex = true
          masOperateIndex(
            startDate,
            endDate
          ).then(function (response) {
            vm.loadingIndex = false
            var chartData = response.data
            vm.indexList = chartData;
            loadTabChartOption(chartData)

          })
            .catch(function (error) {
              console.log(error);
            });

        } else {
          var chartData = vm.indexList
          loadTabChartOption(chartData)
        }
      }



      function loadTabChartOption(chartData) {

        var xAxisData = chartData.xAxisData

        var loadingChartData = chartData[id]

        let yAxis = {
          name: loadingChartData.unit,
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        }

        if (id !== 'tranTotal') {
          // 非金额单位
          // 从0开始，最小间隔为整数。
          let maxVal = Math.max(...loadingChartData.seriesData)
          if (maxVal < 5) {
            yAxis.splitNumber = maxVal || 1
            yAxis.minInterval = 1

          }
        }

        // 绘制图表
        var option = {
          tooltip: {
            trigger: 'axis'
          },
          // legend: {
          //   data: [loadingChartData.seriesName]
          // },
          grid: {
            left: '3%',
            right: '3%',
            bottom: '10%',
            containLabel: true
          },
          xAxis: [{
            type: 'category',
            boundaryGap: false,
            axisLine: {
              lineStyle: {
                color: '#158bca'
              }
            },
            data: xAxisData
          }],
          yAxis: yAxis,
          series: [{
            name: loadingChartData.seriesName,
            type: 'line',
            smooth: true,
            itemStyle: {
              normal: {
                color: '#5eb2ed'
              }
            },
            areaStyle: {
              normal: {
                color: '#acd7f4'
              }
            },
            data: loadingChartData.seriesData
          }

          ]
        };
        vm[chartId].setOption(option)
      }
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
          //top: '10%',
          left: '3%',
          right: '3%',
          bottom: '10%',
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
              color: "#5eb2ed"
            }
          },
          areaStyle: {
            normal: {
              color: '#acd7f4'
            }
          },
          data: lineChart.seriesData
        }]
      };

      vm.$initChart(vm, chartId, option)
    },


    // 订单类型
    loadOrderTypeChartOption(chartData, chartId, chartName) {
      var vm = this;

      let seriesData = chartData.proportion.map(x => { return { name: Object.keys(x)[0], value: Object.values(x)[0] } })

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
          {
            name: chartName,//'订单类型',
            type: 'pie',
            radius: '60%',
            center: ['50%', '50%'],
            data: seriesData,
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      vm.$initChart(vm, chartId, option)
    },

  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      orderCountIndustryTotal: 0,
      orderAmountIndustryTotal: 0,
      loadinggetItemProportion: false,
      loadinggetItemProportionText: '加载中...',
      loadinggetOrderProportion: false,
      loadinggetOrderProportionText: '加载中...',
      loadinggetOrderMoneyProportion: false,
      loadinggetOrderMoneyProportionText: '加载中...',
      loadingIndex: false,
      zhibiao_activeTabName: 'newRegisterUser',
      summaryData: {},
      goodsNumberIndustryTotal: 0
    }
  },
  mounted() {
    var vm = this;

    vm.loadAllData();

    var platform = vm.$platformId

    masOperateTotal(platform).then(function (response) {
      vm.summaryData = response.data.data;
    })
      .catch(function (error) {
        console.log(error);
      });


    window.addEventListener('resize', chartsResize, false);
    var resizeTimer;
    function chartsResize() {
      if (resizeTimer) {
        clearTimeout(resizeTimer)
      }
      resizeTimer = setTimeout(function () {

        var tabContentChart = `${vm.zhibiao_activeTabName}Chart`
        if (vm[tabContentChart]) {
          vm[tabContentChart].resize()
        }

        ['goodsNumberIndustryChart', 'orderCountIndustryChart', 'orderAmountIndustryChart'].forEach(function (item) {
          vm[item] && vm[item].resize()
        }, this);

      }, 100);

    }
  }
}

</script>

<style lang="scss">
.overview-chart-title {
  margin: 35px 0 0;
  font-size: 36px;
  text-align: center;
  .chart-title-key {
    font-size: 36px;
    color: #00ccff; // font-weight: bold;
  }
  .chart-title-unit {
    color: #000;
    font-size: 18px;
  } // span {
  //   margin-left: 5px;
  //   font-size: 18px;
  //   color: #00CCFF;
  // }
}

.table-container {
  padding: 30px;
}

.summary-wrapper {
  margin-left: 0!important;
  margin-right: 0!important;
  padding: 20px 10px;
}

.summary-title {
  margin: 0 10px 15px;
  font-size: 16px;
  color: #333333;
}

.box-summary {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-end;
  color: #fff;
  width: 100%;
  height: 135px;
  border-radius: 5px;
  p {
    margin-top: 1em;
    margin-bottom: 1em;
  }
  .number {
    font-size: 28px;
  }
  .unit {
    font-size: 14px;
    margin-left: 10px;
    margin-right: 30px;
  }
  .title {
    font-size: 18px;
    margin-right: 30px;
  }
}

@for $i from 1 through 6 {
  .summary-bg-#{$i} {
    background: url(../../assets/images/overview/summary-#{$i}-bg.png) no-repeat;
    background-size: 100% 100%;
  }
}

@media (max-width: 1199px) {
  .box-summary {
    margin-bottom: 20px;
  }
}

.overview-chart-container .el-loading-spinner .circular {
  display: none;
}

.overview-chart-container .el-loading-mask {
  z-index: 1000;
  background-color: rgba(255, 255, 255, 0);
}

.table-container .el-table__body-wrapper {
  max-height: 300px;
}

.fbsplm-container {
  padding: 20px;
}
</style>
