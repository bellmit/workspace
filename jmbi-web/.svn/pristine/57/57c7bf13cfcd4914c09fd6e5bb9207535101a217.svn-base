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
          <el-tabs v-model="zhibiao_activeTabName" type="card" @tab-click="handle_zhibiao_Click" v-loading="loadingIndex" element-loading-text="加载中...">
            <el-tab-pane label="浏览量PV" name="pv" v-if="!isOneDay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='pvChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="访客数UV" name="uv" v-if="!isOneDay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='uvChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
  
            <el-tab-pane label="三证审核" name="threeCard" v-if="showDelay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='threeCardChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="开通店铺数" name="openStoreTotal" v-if="showDelay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='openStoreTotalChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="发布商品数" name="itemTotal" v-if="showDelay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='itemTotalChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="订单数" name="orderTotal" v-if="showDelay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='orderTotalChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="支付笔数" name="payTotal" v-if="showDelay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='payTotalChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="支付金额" name="tranTotal" v-if="showDelay">
  
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
  
    <el-row>
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">买家转化率统计
  
          </header>
  
          <div class="checkbox-list">
            <el-checkbox v-model="authConvChecked" @change="buyerFilter">认证转化率</el-checkbox>
            <el-checkbox v-model="releaseDemandConvChecked" @change="buyerFilter">发布需求转化率</el-checkbox>
            <el-checkbox v-model="orderConvChecked" @change="buyerFilter">下单转化率</el-checkbox>
          </div>
          <div class="box chart sixteen-nine yhfwsjfb" :class="{'no-data': loadingGetBuyerRateChartText === '暂无数据'}" v-loading="loadingGetBuyerRateChart" :element-loading-text="loadingGetBuyerRateChartText">
            <div class="content" ref='buyerChart'>
  
            </div>
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!-- end of 买家转化率统计 -->
  
    <el-row>
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">卖家转化率统计
  
          </header>
  
          <div class="checkbox-list">
            <el-checkbox v-model="seller_authConvchecked" @change="sellerFilter">认证转化率</el-checkbox>
            <el-checkbox v-model="seller_openStoreConvchecked" @change="sellerFilter">店铺开通转化率</el-checkbox>
            <el-checkbox v-model="seller_itemConvchecked" @change="sellerFilter">发布商品转化率</el-checkbox>
            <el-checkbox v-model="seller_dealConvchecked" @change="sellerFilter">成交转化率</el-checkbox>
          </div>
  
          <div class="box chart sixteen-nine yhfwsjfb" :class="{'no-data': loadingGetSellerRateChartText === '暂无数据'}" v-loading="loadingGetSellerRateChart" :element-loading-text="loadingGetSellerRateChartText">
            <div class="content" ref='sellerChart'>
  
            </div>
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!-- end of 卖家转化率统计 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">关联公司top5
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingRelativeCompanyGetTopChartText === '暂无数据'}" v-loading="loadingRelativeCompanyGetTopChart" :element-loading-text="loadingRelativeCompanyGetTopChartText">
            <div class="content" ref='relativeCompanyChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">子账户开通top5
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingSubAccountGetTopChartText === '暂无数据'}" v-loading="loadingSubAccountGetTopChart" :element-loading-text="loadingSubAccountGetTopChartText">
            <div class="content" ref='subAccountOpenedChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 关联公司top5 & 子账户开通top5 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">发布商品类目占有率
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingoperateGoodsCateChartText === '暂无数据'}" v-loading="loadingoperateGoodsCateChart" :element-loading-text="loadingoperateGoodsCateChartText">
            <div class="content">
              <el-row>
                <el-col :span="12">
                  <div class="box one-one">
                    <div class="content fbsplm-container" ref="publishGoodsRateChart">
                    </div>
                  </div>
                </el-col>
                <el-col :span="12">
                  <div class="table-container">
                    <el-table :data="tableData" border style="width: 100%">
  
                      <el-table-column prop="name" header-align="center" label="商品名称">
                      </el-table-column>
                      <el-table-column prop="value" header-align="center" label="数量">
                      </el-table-column>
                    </el-table>
                  </div>
                </el-col>
              </el-row>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">转化漏斗
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingoperateConvFunnelChartText === '暂无数据'}" v-loading="loadingoperateConvFunnelChart" :element-loading-text="loadingoperateConvFunnelChartText">
            <div class="content" ref="funnelChart">
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 发布商品类目占有率 & 转化漏斗 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">订单类型
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingoperateOrderTypeChartText === '暂无数据'}" v-loading="loadingoperateOrderTypeChart" :element-loading-text="loadingoperateOrderTypeChartText">
            <div class="content" ref='orderTypeChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">订单金额</header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingoperateOrderPayChartText === '暂无数据'}" v-loading="loadingoperateOrderPayChart" :element-loading-text="loadingoperateOrderPayChartText">
            <div class="content" ref='amountMoneyChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 订单类型 & 订单金额 -->
  
  </div>
</template>

<script>
import {

  operateTotal,
  operateIndex,
  getBuyerRate,
  getSellerRate,
  operateOrderType,
  operateOrderPay,
  operateGetTop,
  operateGoodsCate,
  operateConvFunnel

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

    this.loadTabItem();
  },
  computed: {
    isOneDay() {
      return this.startDate === this.endDate
    }
  },
  watch: {
    isOneDay(val) {
      var vm = this;
      if (!val) {
        this.showDelay = false;
        this.$nextTick(function () {
          this.showDelay = true;
        })

        setTimeout(function () {
          vm.loadTabContentChart(vm.zhibiao_activeTabName)
        }, 50);
      }
    }
  },
  methods: {
    loadTabItem() {
      var vm = this;
      if (vm.isOneDay) {
        if (vm.zhibiao_activeTabName === 'pv' || vm.zhibiao_activeTabName === 'uv') {
          vm.zhibiao_activeTabName = 'threeCard'
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

      var platform = vm.$platformId;
      var startDate = vm.startDate;
      var endDate = vm.endDate;

      vm.loadTabItem();


      vm.loadTabContentChart(vm.zhibiao_activeTabName)


      vm.buyerFilter()
      vm.sellerFilter()

      // 运营总览关联公司top5、子账户开通top5
      vm.loadingRelativeCompanyGetTopChart = true
      vm.loadingRelativeCompanyGetTopChartText = '加载中...'
      vm.loadingSubAccountGetTopChart = true
      vm.loadingSubAccountGetTopChartText = '加载中...'
      operateGetTop({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingRelativeCompanyGetTopChart = false
        vm.loadingSubAccountGetTopChart = false
        var chartData = response.data

        if (chartData.affiliateCompanyChart.xAxisData.length === 0) {
          vm.loadingRelativeCompanyGetTopChart = true
          vm.loadingRelativeCompanyGetTopChartText = '暂无数据'
        }
        if (chartData.subAccountOpening.xAxisData.length === 0) {
          vm.loadingSubAccountGetTopChart = true
          vm.loadingSubAccountGetTopChartText = '暂无数据'
        }


        vm.loadrelativeCompanyChart(chartData)
        vm.loadsubAccountOpenedChart(chartData)


      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingoperateOrderTypeChart = true
      vm.loadingoperateOrderTypeChartText = '加载中...'
      operateOrderType({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingoperateOrderTypeChart = false
        var chartData = response.data

        if (chartData.pieChart.seriesData.length === 0) {
          vm.loadingoperateOrderTypeChart = true
          vm.loadingoperateOrderTypeChartText = '暂无数据'
        }


        vm.loadOrderTypeChartOption(chartData)


      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingoperateGoodsCateChart = true
      vm.loadingoperateGoodsCateChartText = '加载中...'
      operateGoodsCate({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingoperateGoodsCateChart = false
        var chartData = response.data

        if (chartData.pieChart.seriesData.length === 0) {
          vm.loadingoperateGoodsCateChart = true
          vm.loadingoperateGoodsCateChartText = '暂无数据'
        }


        vm.loadPublishGoodsRateChartOption(chartData)

        var tdata = chartData.pieChart.seriesData.concat();
        tdata.sort(function (a, b) {
          return Number(b.value) - Number(a.value);
        });
        vm.tableData = tdata

      })
        .catch(function (error) {
          console.log(error);
        });



      vm.loadingoperateOrderPayChart = true
      vm.loadingoperateOrderPayChartText = '加载中...'
      operateOrderPay({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingoperateOrderPayChart = false
        var chartData = response.data

        if (chartData.lineChart.xAxisData.length === 0) {
          vm.loadingoperateOrderPayChart = true
          vm.loadingoperateOrderPayChartText = '暂无数据'
        }


        vm.loadAmountMoneyChartOption(chartData)


      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingoperateConvFunnelChart = true
      vm.loadingoperateConvFunnelChartText = '加载中...'
      operateConvFunnel({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingoperateConvFunnelChart = false
        var chartData = response.data

        if (chartData.funnelChart.seriesData.length === 0) {
          vm.loadingoperateConvFunnelChart = true
          vm.loadingoperateConvFunnelChartText = '暂无数据'
        }


        vm.loadfunnelChart(chartData)


      })
        .catch(function (error) {
          console.log(error);
        });

    },
    buyerFilter() {

      var vm = this;

      var platform = vm.$platformId;
      var startDate = vm.startDate;
      var endDate = vm.endDate;

      // 店铺分类排行
      vm.loadingGetBuyerRateChart = true
      vm.loadingGetBuyerRateChartText = '加载中...'
      getBuyerRate({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingGetBuyerRateChart = false
        var chartData = response.data

        if (chartData.xAxisData.length === 0) {
          vm.loadingGetBuyerRateChart = true
          vm.loadingGetBuyerRateChartText = '暂无数据'
        }


        vm.loadsellerBuyerChart(chartData, 'buyerChart')
        //vm.loadsellerBuyerChart(chartData, 'sellerChart')

      })
        .catch(function (error) {
          console.log(error);
        });

    },
    sellerFilter() {

      var vm = this;
      var platform = vm.$platformId;
      var startDate = vm.startDate;
      var endDate = vm.endDate;

      // 店铺分类排行
      vm.loadingGetSellerRateChart = true
      vm.loadingGetSellerRateChartText = '加载中...'
      getSellerRate({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingGetSellerRateChart = false
        var chartData = response.data

        if (chartData.xAxisData.length === 0) {
          vm.loadingGetSellerRateChart = true
          vm.loadingGetSellerRateChartText = '暂无数据'
        }

        vm.loadsellerBuyerChart(chartData, 'sellerChart')

      })
        .catch(function (error) {
          console.log(error);
        });

    },
    loadfunnelChart(chartData) {
      var vm = this;

      var chartId = 'funnelChart'
      // 基于准备好的dom，初始化echarts实例
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var funnelChart = chartData.funnelChart

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c}"
        },

        legend: {
          top: 5,
          data: funnelChart.legendData//['展现', '点击', '访问', '咨询', '订单']
        },
        calculable: true,
        series: [
          {
            name: '转化漏斗',
            type: 'funnel',
            left: '10%',
            top: 80,
            //x2: 80,
            bottom: 60,
            width: '80%',
            // height: {totalHeight} - y - y2,
            // min: 0,
            // max: 100,
            // minSize: '0%',
            // maxSize: '100%',
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
            data: funnelChart.seriesData
          }
        ]
      };

      vm[chartId].setOption(option)

    },
    // todo
    loadsellerBuyerChart(chartData, chartId) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var seriesData = [];
      var legendData = [];

      // buyer
      if (chartId === 'buyerChart') {
        if (vm.authConvChecked && chartData.authConv) {
          let authConv = chartData.authConv
          legendData.push(authConv.seriesName)
          seriesData.push({
            name: authConv.seriesName,
            type: 'line',
            smooth: true,
            data: authConv.seriesData
          })
        }

        if (vm.releaseDemandConvChecked && chartData.releaseDemandConv) {
          let releaseDemandConv = chartData.releaseDemandConv
          legendData.push(releaseDemandConv.seriesName)
          seriesData.push({
            name: releaseDemandConv.seriesName,
            type: 'line',
            smooth: true,
            data: releaseDemandConv.seriesData
          })
        }

        if (vm.orderConvChecked && chartData.orderConv) {
          let orderConv = chartData.orderConv
          legendData.push(orderConv.seriesName)
          seriesData.push({
            name: orderConv.seriesName,
            type: 'line',
            smooth: true,
            data: orderConv.seriesData
          })
        }
      }

      if (chartId === 'sellerChart') {
        // seller 
        if (vm.seller_authConvchecked && chartData.authConv) {
          let seller_authConv = chartData.authConv
          legendData.push(seller_authConv.seriesName)
          seriesData.push({
            name: seller_authConv.seriesName,
            type: 'line',
            smooth: true,
            data: seller_authConv.seriesData
          })
        }
        if (vm.seller_openStoreConvchecked && chartData.openStoreConv) {
          let seller_openStoreConv = chartData.openStoreConv
          legendData.push(seller_openStoreConv.seriesName)
          seriesData.push({
            name: seller_openStoreConv.seriesName,
            type: 'line',
            smooth: true,
            data: seller_openStoreConv.seriesData
          })
        }

        if (vm.seller_itemConvchecked && chartData.itemConv) {
          let seller_itemConv = chartData.itemConv
          legendData.push(seller_itemConv.seriesName)
          seriesData.push({
            name: seller_itemConv.seriesName,
            type: 'line',
            smooth: true,
            data: seller_itemConv.seriesData
          })
        }

        if (vm.seller_dealConvchecked && chartData.dealConv) {
          let seller_dealConv = chartData.dealConv
          legendData.push(seller_dealConv.seriesName)
          seriesData.push({
            name: seller_dealConv.seriesName,
            type: 'line',
            smooth: true,
            data: seller_dealConv.seriesData
          })
        }
      }


      // 绘制图表
      var option = {
        color: ['#a68be1', '#4a9bfc', '#2fb2e4', '#34bb96', '#8bcb47', '#f69940'],
        tooltip: {
          trigger: 'axis',
        },
        legend: {
          top: '5%',
          data: legendData
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
          {
            name: '%',
            type: 'value'
          }
        ],
        series: seriesData
      };

      vm[chartId].setOption(option)


    },

    // 关联公司top5
    loadrelativeCompanyChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.relativeCompanyChart = echarts.init(vm.$refs.relativeCompanyChart);

      var affiliateCompanyChart = chartData.affiliateCompanyChart

      let xAxis = {
        name: '个',
        type: 'value',
        boundaryGap: false,
        axisLine: {
          lineStyle: {
            color: '#158bca'
          }
        }
      };

      // 从0开始，最小间隔为整数。
      let maxVal = Math.max(...affiliateCompanyChart.xAxisData)
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
          },
          formatter: function (params) {
            var name = params[0].name;
            var index = 50;

            if (name.length > index) {
              name = name.substring(0, index) + '<br>' + name.substring(index)
            }
            var color = params[0].color

            return name + '<br><span class="bar-trend-chart-tooltip-icon" style="background-color:' + color + '"></span>' + params[0].seriesName + ': ' + params[0].value
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
              color: '#158bca'
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
          data: affiliateCompanyChart.seriesData.reverse()
        },
        series: [{
          name: affiliateCompanyChart.seriesName,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#1790cf',
              barBorderRadius: 2
            }
          },
          data: affiliateCompanyChart.xAxisData.reverse()
        }]
      };

      vm.relativeCompanyChart.setOption(option)

      // vm.relativeCompanyChart.on('click', function (obj) {
      //   openInNewTab(obj.name);
      // })

    },
    // 子账户开通top5
    loadsubAccountOpenedChart(chartData) {
      var vm = this
      // 基于准备好的dom，初始化echarts实例
      vm.subAccountOpenedChart = echarts.init(vm.$refs.subAccountOpenedChart);

      var subAccountOpening = chartData.subAccountOpening

      let xAxis = {
        name: '个',
        type: 'value',
        boundaryGap: false,
        axisLine: {
          lineStyle: {
            color: '#158bca'
          }
        }
      };

      // 从0开始，最小间隔为整数。
      let maxVal = Math.max(...subAccountOpening.xAxisData)
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
              color: '#158bca'
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
          data: subAccountOpening.seriesData.reverse()
        },
        series: [{
          name: subAccountOpening.seriesName,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#1790cf',
              barBorderRadius: 2
            }
          },
          data: subAccountOpening.xAxisData.reverse()
        }]
      };

      vm.subAccountOpenedChart.setOption(option)

      // vm.subAccountOpenedChart.on('click', function (obj) {
      //   openInNewTab(obj.name);
      // })
    },
    handle_zhibiao_Click(tab, event) {
      var vm = this;

      var id = tab.name;


      vm.$nextTick(function () {
        vm.loadTabContentChart(id, false)
      });

    },
    loadTabContentChart(id, reload = true) {
      var vm = this;
      var chartId = id + 'Chart';
      if (!vm.$refs[chartId]) return;
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var platform = vm.$platformId;

      var startDate = vm.startDate,
        endDate = vm.endDate;

      if (reload || vm.indexList === null) {
        vm.loadingIndex = true
        operateIndex({
          platform,
          startDate,
          endDate
        }).then(function (response) {
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

        setTimeout(function () {
          vm[chartId] && vm[chartId].resize()
        }, 50);
      }
    },

    // 发布商品类目占有率
    loadPublishGoodsRateChartOption(chartData) {
      var vm = this;
      var chartId = 'publishGoodsRateChart';
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var pieChart = chartData.pieChart;

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        calculable: true,
        series: [
          {
            name: '发布商品类目占有率',
            type: 'pie',
            label: {
              normal: {
                show: true,
                formatter: function (obj) {
                  var name = obj.name || '';
                  if (name.length > 6) {
                    return name.substring(0, 5) + '...'
                  }
                  return name
                }
              }
            },
            radius: ['25%', '46%'],
            center: ['50%', '50%'],
            // roseType: 'area',
            data: pieChart.seriesData
          }
        ]
      };
      vm[chartId].setOption(option)
    },
    // 订单类型
    loadOrderTypeChartOption(chartData) {
      var vm = this;
      var chartId = 'orderTypeChart';
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var pieChart = chartData.pieChart;

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],

        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          right: 20,
          top: 'middle',
          data: pieChart.legendData//['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        series: [
          {
            name: '订单类型',
            type: 'pie',
            radius: '60%',
            center: ['45%', '50%'],
            data: pieChart.seriesData,
            // [
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
          }
        ]
      };
      vm[chartId].setOption(option)
    },

    // 订单金额
    loadAmountMoneyChartOption(chartData) {
      var vm = this;
      var chartId = 'amountMoneyChart';
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var lineChart = chartData.lineChart;

      let yAxis1 = {
        type: 'value',
        name: '数量(笔)'
      };

      // 从0开始，最小间隔为整数。
      let maxVal = Math.max(...lineChart.seriesData.left)
      if (maxVal < 5 && maxVal > 0) {
        yAxis1.splitNumber = maxVal
        yAxis1.minInterval = 1

      }

      // 绘制图表
      var option = {
        color: ['#1790cf', '#6B9C29'],
        tooltip: {
          trigger: 'axis',
        },

        legend: {
          data: ['数量', '金额']
        },
        xAxis: [
          {
            type: 'category',
            data: lineChart.xAxisData,//['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          yAxis1,
          {
            type: 'value',
            name: '金额(万元)'
          }
        ],
        series: [

          {
            name: '数量',
            type: 'bar',
            barMaxWidth: 30,
            data: lineChart.seriesData.left//[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
          },
          {
            name: '金额',
            type: 'line',
            yAxisIndex: 1,
            data: lineChart.seriesData.right//[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          }
        ]
      };
      vm[chartId].setOption(option)
    },


    loadChartOption(chartData, chartId) {
      var vm = this;
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var seriesData = chartData.seriesData || [];
      if (seriesData.length === 0) {
        vm[chartId + 'Empty'] = true;
      } else {
        vm[chartId + 'Empty'] = false;
      }

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          text: chartData.title,
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
      vm[chartId].setOption(option)
    }
  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      authConvChecked: true,
      releaseDemandConvChecked: true,
      orderConvChecked: true,
      seller_authConvchecked: true,
      seller_dealConvchecked: true,
      seller_itemConvchecked: true,
      seller_openStoreConvchecked: true,
      loadingGetBuyerRateChart: false,
      loadingGetBuyerRateChartText: '加载中...',
      loadingGetSellerRateChart: false,
      loadingGetSellerRateChartText: '加载中...',
      loadingoperateOrderTypeChart: false,
      loadingoperateOrderTypeChartText: '加载中...',
      loadingoperateOrderPayChart: false,
      loadingoperateOrderPayChartText: '加载中...',
      loadingRelativeCompanyGetTopChart: false,
      loadingRelativeCompanyGetTopChartText: '加载中...',
      loadingSubAccountGetTopChart: false,
      loadingSubAccountGetTopChartText: '加载中...',
      loadingoperateGoodsCateChart: false,
      loadingoperateGoodsCateChartText: '加载中...',
      loadingoperateConvFunnelChart: false,
      loadingoperateConvFunnelChartText: '加载中...',
      tableData: [],
      loadingIndex: false,
      zhibiao_activeTabName: 'pv',
      showDelay: true,
      summaryData: [{ "MemberTotal": 0 }, { "OpenStoreTotal": 0 }, { "ItemTotal": 0 }, { "OrderTotal": 0 }, { "PayTotal": 0 }, { "TranTotal": 0 }],

    }
  },
  mounted() {
    var vm = this;

    vm.loadAllData();

    var platform = vm.$platformId

    operateTotal(platform).then(function (response) {
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

        vm.buyerChart && vm.buyerChart.resize()
        vm.sellerChart && vm.sellerChart.resize()
        vm.relativeCompanyChart && vm.relativeCompanyChart.resize()
        vm.subAccountOpenedChart && vm.subAccountOpenedChart.resize()
        vm.publishGoodsRateChart && vm.publishGoodsRateChart.resize()
        vm.funnelChart && vm.funnelChart.resize()
        vm.orderTypeChart && vm.orderTypeChart.resize()
        vm.amountMoneyChart && vm.amountMoneyChart.resize()

      }, 100);

    }
  }
}

</script>

<style lang="scss">
.checkbox-list {
  padding: 30px 20px 0;
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
    background: url(../assets/images/overview/summary-#{$i}-bg.png) no-repeat;
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
