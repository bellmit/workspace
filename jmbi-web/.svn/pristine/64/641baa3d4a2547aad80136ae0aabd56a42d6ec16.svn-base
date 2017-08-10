<template>
  <div>
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item"><span>我的位置：</span></span>
      <el-breadcrumb-item :to="{ path: '/home' }">聚贸通平台</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>企业分类统计</el-breadcrumb-item>
    </el-breadcrumb>
  
    <el-row :gutter="20"
            class="jm-margin-top20 jm-margin-bottom20">
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px c-el-tabs">
          <span class="el-tabs-name">企业新增数</span>
          <el-tabs v-model="cNewAdd_activeTabName"
                   type="board-card"
                   @tab-click="handle_cNewAdd_Click">
            <el-tab-pane label="本周"
                         name="week">
              <div class="box chart sixteen-nine comany-order-box">
                <div class="content "
                     ref='weekNewAddChart'></div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="本月"
                         name="month">
              <div class="box chart sixteen-nine comany-order-box">
                <div class="content "
                     ref='monthNewAddChart'></div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="本季"
                         name="quarter">
              <div class="box chart sixteen-nine comany-order-box">
                <div class="content "
                     ref='quarterNewAddChart'></div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="本年"
                         name="year">
              <div class="box chart sixteen-nine comany-order-box">
                <div class="content "
                     ref='yearNewAddChart'></div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px c-el-tabs">
          <span class="el-tabs-name">企业活跃数</span>
          <el-tabs v-model="cActive_activeTabName"
                   type="board-card"
                   @tab-click="handle_cActive_Click">
            <el-tab-pane label="本周"
                         name="week">
              <div class="box chart sixteen-nine comany-order-box">
                <div class="content "
                     ref='weekActivityChart'></div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="本月"
                         name="month">
              <div class="box chart sixteen-nine comany-order-box">
                <div class="content "
                     ref='monthActivityChart'></div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="本季"
                         name="quarter">
              <div class="box chart sixteen-nine comany-order-box">
                <div class="content "
                     ref='quarterActivityChart'></div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="本年"
                         name="year">
              <div class="box chart sixteen-nine comany-order-box">
                <div class="content "
                     ref='yearActivityChart'></div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>
    <el-row class="jm-grid-box-bg-white jm-grid-border-1px company-rank-container">
      <header class="panel-header panel-advance-header-search-header jm-grid-border-bottom-1px c-header">
        <span class="title">拍档企业</span>
        <div class="advance-header-search">
          <advance-search v-on:searchCriteria="getSearchCriteria"></advance-search>
  
        </div>
  
      </header>
      <div class="clearfix">
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="8">
          <div class="grid-content">
            <div class="box sixteen-nine c-order-box"
                 v-loading="cOrderNumChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cOrderNumChart'>
  
              </div>
              <a v-bind:href="exportLink2"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
          </div>
        </el-col>
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="8">
          <div class="grid-content">
            <div class="box sixteen-nine c-order-box"
                 v-loading="cOrderValChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cOrderValChart'>
  
              </div>
              <a v-bind:href="exportLink4"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
          </div>
        </el-col>
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="8">
          <div class="grid-content">
            <div class="box sixteen-nine c-order-box"
                 v-loading="cOrderIncomeChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cOrderIncomeChart'>
  
              </div>
              <a v-bind:href="exportLink6"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
  
          </div>
        </el-col>
      </div>
      <header class="panel-header jm-grid-border-bottom-1px c-header c-header1"><span>客户企业</span></header>
      <div class="clearfix">
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="8">
          <div class="grid-content">
            <div class="box sixteen-nine c-order-box"
                 v-loading="cCustomerNumChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cCustomerNumChart'>
  
              </div>
              <a v-bind:href="exportLink1"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
          </div>
        </el-col>
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="8">
          <div class="grid-content">
            <div class="box sixteen-nine c-order-box"
                 v-loading="cCustomerValChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cCustomerValChart'>
  
              </div>
              <a v-bind:href="exportLink3"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
          </div>
        </el-col>
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="8">
          <div class="grid-content">
            <div class="box sixteen-nine c-order-box"
                 v-loading="cCustomerIncomeChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cCustomerIncomeChart'>
  
              </div>
              <a v-bind:href="exportLink5"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
  
          </div>
        </el-col>
      </div>
    </el-row>
  </div>
</template>

<script>
import {
  getCompanyNum,
  getCompanyTopAmount
} from '@/service/api'
import util from '@/service/util'

import advanceSearch from '@/components/AdvanceSearch'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'company',
  components: {
    advanceSearch
  },
  methods: {
    getLink(linkObj, chartIndex) {
      var vm = this;
      vm['exportLink' + chartIndex] = `/v1/company/exportCompanyAmount/timeType/${linkObj.timeType}/startDate/${linkObj.startDate}/endDate/${linkObj.endDate}/customsCode/${linkObj.customsCode}/chart/${chartIndex}`
    },
    getSearchCriteria(searchCriteria) {

      var vm = this;

      for (let index = 1; index <= 7; index++) {
        vm.getLink(searchCriteria, index)
      }

      vm.linkObj = Object.assign({}, searchCriteria)

      // 2、拍档企业订单数top
      vm.companyRank('cOrderNumChart', '票', '#2ec7c9', 2, searchCriteria)
      // 4、拍档企业收入top
      vm.companyRank('cOrderValChart', '万元', '#b6a2de', 4, searchCriteria)
      // 6、拍档企业:订单货值top
      vm.companyRank('cOrderIncomeChart', '万美元', '#5ab1ef', 6, searchCriteria)
      // 1、客户企业订单数top
      vm.companyRank('cCustomerNumChart', '票', '#2ec7c9', 1, searchCriteria)
      // 3、客户企业收入top
      vm.companyRank('cCustomerValChart', '万元', '#b6a2de', 3, searchCriteria)
      // 5、客户企业:订单货值top
      vm.companyRank('cCustomerIncomeChart', '万美元', '#5ab1ef', 5, searchCriteria)
    },
    // 企业排名
    companyRank(ref, unit, color, chartIndex, searchCriteria) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm[ref] = echarts.init(vm.$refs[ref]);

      var timeType = searchCriteria.timeType
      var startDate = searchCriteria.startDate,
        endDate = searchCriteria.endDate,
        customsCode = searchCriteria.customsCode;

      getCompanyTopAmount({
        timeType,
        startDate,
        endDate,
        customsCode,
        chart: chartIndex
      }).then(function (response) {
        var chartData = response.data;
        loadChartData(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });

      function loadChartData(chartData) {

        vm[ref + 'Empty'] = chartData.seriesData.length === 0;

        // 绘制图表
        var option = {
          title: {
            text: chartData.title,
            left: 'center',
            top: 20,
            textStyle: {
              fontSize: 16,
              color: '#767676',
              fontWeight: 'normal'
            }
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          grid: {
            top: '20%',
            left: '5%',
            right: '15%',
            bottom: '10%',
            containLabel: true
          },
          xAxis: {
            type: 'value',
            boundaryGap: false,
            name: unit,
            axisLabel: {
              rotate: 20
            }
          },
          yAxis: {
            type: 'category',
            splitLine: {
              show: true
            },
            axisLabel: {
              formatter: function (value, index) {
                var value = value || ''
                var maxLength = 8
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
            name: (chartData.title || '').replace('TOP5', ''),
            type: 'bar',
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: color,
                barBorderRadius: 2
              }
            },
            data: chartData.seriesData.reverse()
          }]
        };

        vm[ref].setOption(option)

      }
    },
    handle_cNewAdd_Click(tab, event) {
      var vm = this;

      var chartId = `${tab.name}NewAddChart`;

      var chartConfig = {
        index: 1,
        type: 'bar'
      }
      var timeType = vm.cNewAdd_activeTabName
      setTimeout(function () {
        vm.companyNewAddChart(chartId, chartConfig, timeType)
      }, 200);


    },
    // 企业新增数 || 企业活跃数
    companyNewAddChart(chartId, chartConfig, timeType) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var startDate, endDate

      if (timeType === 'week') {
        startDate = util.firstDayOfWeek().format()
        endDate = util.lastDayOfWeek().format()
      }

      if (timeType === 'month') {
        startDate = util.firstDayOfMonth().format()
        endDate = util.lastDayOfMonth().format()
      }

      if (timeType === 'quarter') {
        startDate = util.firstDayOfQuarter().format()
        endDate = util.lastDayOfQuarter().format()
      }

      if (timeType === 'year') {
        startDate = util.firstDayOfYear().format()
        endDate = util.lastDayOfYear().format()
      }

      getCompanyNum({
        timeType,
        startDate,
        endDate,
        chart: chartConfig.index
      }).then(function (response) {
        var chartData = response.data;
        var chartType = chartConfig.type
        loadChartData(chartData, chartType)
      })
        .catch(function (error) {
          console.log(error);
        });

      function loadChartData(chartData, chartType) {

        var series = chartData.series || [];
        var index = 0;

        var mergedSeries = series.map(function (obj) {

          var rObj = Object.assign({}, obj, {
            type: chartType,
            markLine: {
              data: [{
                type: 'average',
                name: '平均值'
              }]
            }
          })
          index++;
          return rObj;
        });

        var legendData = chartData.legendData || [];
        var updatedLegendData = legendData.map((val) => {
          return {
            name: val,
            icon: 'roundRect'
          }
        })

        // 绘制图表
        var option = {
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: updatedLegendData,
            right: 20
          },
          color: ['#2ec7c9', '#b6a2de'],
          calculable: true,
          xAxis: [{
            type: 'category',
            data: chartData.xAxisData
          }],
          yAxis: [{
            name: '数量',
            type: 'value'
          }],
          series: mergedSeries
        };

        vm[chartId].setOption(option)
      }

    },

    handle_cActive_Click(tab, event) {
      var vm = this;

      var chartId = `${tab.name}ActivityChart`;

      var chartConfig = {
        index: 2,
        type: 'line'
      }
      var timeType = vm.cActive_activeTabName
      setTimeout(function () {
        vm.companyNewAddChart(chartId, chartConfig, timeType)
      }, 200);

    }
  },

  mounted() {
    var vm = this;

    vm.companyNewAddChart(vm.cNewAdd_activeTabName + 'NewAddChart', {
      index: 1,
      type: 'bar'
    }, vm.cNewAdd_activeTabName)

    vm.companyNewAddChart(vm.cActive_activeTabName + 'ActivityChart', {
      index: 2,
      type: 'line'
    }, vm.cActive_activeTabName)

    var searchCriteria = {
      timeType: 'week',
      startDate: util.firstDayOfWeek().format(),
      endDate: util.lastDayOfWeek().format(),
      customsCode: 'all'
    }

    for (let index = 1; index <= 6; index++) {
      vm.getLink(searchCriteria, index)
    }

    // 2、拍档企业订单数top
    vm.companyRank('cOrderNumChart', '票', '#2ec7c9', 2, searchCriteria)
    // 4、拍档企业收入top
    vm.companyRank('cOrderValChart', '万元', '#b6a2de', 4, searchCriteria)
    // 6、拍档企业:订单货值top
    vm.companyRank('cOrderIncomeChart', '万美元', '#5ab1ef', 6, searchCriteria)
    // 1、客户企业订单数top
    vm.companyRank('cCustomerNumChart', '票', '#2ec7c9', 1, searchCriteria)
    // 3、客户企业收入top
    vm.companyRank('cCustomerValChart', '万元', '#b6a2de', 3, searchCriteria)
    // 5、客户企业:订单货值top
    vm.companyRank('cCustomerIncomeChart', '万美元', '#5ab1ef', 5, searchCriteria)

    window.addEventListener('resize', chartsResize, false);

    function chartsResize() {

      // 企业新增数
      var cNewCompanyChart = `${vm.cNewAdd_activeTabName}NewAddChart`

      console.log(cNewCompanyChart)
      if (vm[cNewCompanyChart]) {
        vm[cNewCompanyChart].resize()
      }

      // 企业活跃数
      var cActiveContentChart = `${vm.cActive_activeTabName}ActivityChart`
      if (vm[cActiveContentChart]) {
        vm[cActiveContentChart].resize()
      }

      ['cOrderNumChart'
        , 'cOrderValChart'
        , 'cOrderIncomeChart'
        , 'cCustomerNumChart'
        , 'cCustomerValChart'
        , 'cCustomerIncomeChart'].forEach((id) => {
          vm[id] && vm[id].resize()
        })


    }
  },
  data() {
    return {
      cNewAdd_activeTabName: 'week',
      cActive_activeTabName: 'week',
      exportLink1: '',
      exportLink2: '',
      exportLink3: '',
      exportLink4: '',
      exportLink5: '',
      exportLink6: '',
      cOrderNumChartEmpty: false,
      cOrderValChartEmpty: false,
      cOrderIncomeChartEmpty: false,
      cCustomerNumChartEmpty: false,
      cCustomerValChartEmpty: false,
      cCustomerIncomeChartEmpty: false
    }
  }
}


</script>

<style>
.comany-order-box {
  height: 365px;
}

.company-rank-container .el-loading-spinner .circular {
  display: none;
}

.company-rank-container .el-loading-mask {
  z-index: 1000;
  background-color: rgba(255, 255, 255, 0);
}
</style>
