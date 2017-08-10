<template>
  <div class="shop-operation-page">
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>店铺分析</el-breadcrumb-item>
      <el-breadcrumb-item>店铺运营</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">开通店铺趋势图
  
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingShopPublishChartText === '暂无数据'}" v-loading="loadingShopPublishChart" :element-loading-text="loadingShopPublishChartText">
            <div class="content" ref='shopPublishChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingShopClassifyChartText === '暂无数据'}" v-loading="loadingShopClassifyChart" :element-loading-text="loadingShopClassifyChartText">
            <div class="content" ref='shopClassifyChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingShopTradeChartText === '暂无数据'}" v-loading="loadingShopTradeChart" :element-loading-text="loadingShopTradeChartText">
            <div class="content" ref='shopTradeChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 开通店铺趋势图-->
  
    <el-row>
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px shop-tab-list">
          <el-tabs v-model="activeName" @tab-click="loadTableData">
            <el-tab-pane label="商品发布排行" name="first">
              <el-table :data="releaseListData" border style="width: 100%">
                <el-table-column prop="index" label="排名" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="company_name" label="店铺名称" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="gsxz" label="公司性质" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="total" label="发布商品数量" header-align="center" align="center">
                </el-table-column>
  
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="店铺分类排行" name="second">
              <el-table :data="categoryListData" border style="width: 100%">
                <el-table-column prop="index" label="排名" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="comp_name" label="公司名称" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="gsxz" label="公司性质" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="shop_cate_sum" label="商品覆盖分类" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="category_name" label="覆盖分类（top3）" header-align="center" align="center">
                </el-table-column>
  
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="店铺成交排行" name="third">
              <el-table :data="dealListData" border style="width: 100%">
                <el-table-column prop="index" label="排名" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="comp_name" label="公司名称" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="gsxz" label="公司性质" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="order_num" label="下单数量" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="money_total" label="下单金额（万元）" header-align="center" align="center">
                </el-table-column>
  
                <el-table-column prop="goods_name" label="商品名称" header-align="center" align="center">
                </el-table-column>
  
              </el-table>
            </el-tab-pane>
  
          </el-tabs>
  
        </div>
      </el-col>
  
    </el-row>
    <!-- end of 基础资料完善统计 & 基础资料完善占比 -->
  
  </div>
</template>

<script>

import {
  shopReleaseRank,
  shopCategoryRank,
  shopDealRank,
  shopReleaseList,
  shopCategoryList,
  shopDealList
} from '@/service/api'

import rangeDatePicker from '@/components/RangeDatePicker'


import echarts from 'echarts';


export default {
  name: 'shopOperate',
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

      // 商品发布数排行
      vm.loadingShopPublishChart = true
      vm.loadingShopPublishChartText = '加载中...'
      shopReleaseRank({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingShopPublishChart = false
        var chartData = response.data

        if (!chartData.seriesData || chartData.seriesData.length === 0) {
          vm.loadingShopPublishChart = true
          vm.loadingShopPublishChartText = '暂无数据'
        }

        vm.loadShopBarChart(chartData, 'shopPublishChart', '商品发布数排行')
      })
        .catch(function (error) {
          console.log(error);
        });

      // 店铺分类排行
      vm.loadingShopClassifyChart = true
      vm.loadingShopClassifyChartText = '加载中...'
      shopCategoryRank({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingShopClassifyChart = false
        var chartData = response.data

        if (!chartData.seriesData || chartData.seriesData.length === 0) {
          vm.loadingShopClassifyChart = true
          vm.loadingShopClassifyChartText = '暂无数据'
        }

        vm.loadShopBarChart(chartData, 'shopClassifyChart', '店铺分类排行')

      })
        .catch(function (error) {
          console.log(error);
        });

      // 店铺成交排行
      vm.loadingShopTradeChart = true
      vm.loadingShopTradeChartText = '加载中...'
      shopDealRank({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingShopTradeChart = false
        var chartData = response.data

        if (!chartData.seriesData || chartData.seriesData.length === 0) {
          vm.loadingShopTradeChart = true
          vm.loadingShopTradeChartText = '暂无数据'
        }

        vm.loadShopBarChart(chartData, 'shopTradeChart', '店铺成交排行')

      })
        .catch(function (error) {
          console.log(error);
        });



      this.loadTableData();


    },
    loadTableData() {
      var vm = this;

      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;

      console.log('vm.activeName', vm.activeName)
      if (vm.activeName === "first") {
        // 商品发布排行
        shopReleaseList({
          platform,
          startDate,
          endDate
        }).then(function (response) {

          var data = response.data || [];

          vm.releaseListData = data.map((item, index) => {
            return Object.assign(item, { index: index + 1 })
          })

        })
          .catch(function (error) {
            console.log(error);
          });
      } else if (vm.activeName === "second") {
        // 店铺分类列表
        shopCategoryList({
          platform,
          startDate,
          endDate
        }).then(function (response) {

          var data = response.data || [];

          vm.categoryListData = data.map((item, index) => {
            return Object.assign(item, { index: index + 1 })
          })

        })
          .catch(function (error) {
            console.log(error);
          });
      }
      else if (vm.activeName === "third") {
        // 店铺分类排行
        shopDealList({
          platform,
          startDate,
          endDate
        }).then(function (response) {

          var data = response.data || [];

          vm.dealListData = data.map((item, index) => {
            return Object.assign(item, { index: index + 1 })
          })

        })
          .catch(function (error) {
            console.log(error);
          });
      }


    },
    loadShopBarChart(chartData, chartId, title) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var seriesName = title;

      let xAxis = {
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        };
 
        // 从0开始，最小间隔为整数。
        if (chartId !== 'shopTradeChart') {
          let maxVal = Math.max(...chartData.seriesData)
          if (maxVal < 5 && maxVal > 0) {
            xAxis.splitNumber = maxVal
            xAxis.minInterval = 1
          }
        }
    

      // 绘制图表
      var option = {
        title: {
          text: title,
          left: "center",
          top: '3%',
          textStyle: {
            fontWeight: 'normal',
            fontSize: 16
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '10%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: xAxis,
        yAxis: {
          type: 'category',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          },
          data: chartData.yAxisData.reverse()
        },
        series: [
          {
            name: seriesName,
            type: 'bar',
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: '#5eb2ed',
                barBorderRadius: 2
              }
            },
            data: chartData.seriesData.reverse()
          }

        ]
      };

      vm[chartId].setOption(option)
    }
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
        vm.shopPublishChart && vm.shopPublishChart.resize()
        vm.shopClassifyChart && vm.shopClassifyChart.resize()
        vm.shopTradeChart && vm.shopTradeChart.resize()


      }, 100);
    }

  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingShopPublishChart: false,
      loadingShopPublishChartText: '加载中...',
      loadingShopClassifyChart: false,
      loadingShopClassifyChartText: '加载中...',
      loadingShopTradeChart: false,
      loadingShopTradeChartText: '加载中...',
      loadingTODOChart: false,
      activeName: 'first',
      releaseListData: [],
      categoryListData: [],
      dealListData: []
    }
  }
}


</script>

<style>
.shop-tab-list .el-tabs__header {
  margin: 0;
}

.shop-operation-page {
  padding-bottom: 50px;
}
</style>
