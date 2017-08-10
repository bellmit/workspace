<template>
  <div>
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item"><span>我的位置：</span></span>
      <el-breadcrumb-item :to="{ path: '/home' }">聚贸通平台</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>进出口分类统计</el-breadcrumb-item>
    </el-breadcrumb>
  
    <el-row class="jm-grid-box-bg-white jm-grid-border-1px jm-margin-top20 import-export-container ">
      <header class="panel-header panel-advance-header-search-header jm-grid-border-bottom-1px c-header">
        <span class="title">进口</span>
        <div class="advance-header-search">
          <advance-search v-on:searchCriteria="getSearchCriteria"></advance-search>
        </div>
      </header>
      <div class="clearfix">
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="gridColumnSpan">
          <div class="grid-content">
            <div class="box sixteen-nine ie-order-box"
                 v-loading="cOrderNumChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cOrderNumChart'>
  
              </div>
              <a v-bind:href="exportInGoodsAmount"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
          </div>
        </el-col>
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="gridColumnSpan"
                v-if="!isSelectedKouan">
          <div class="grid-content kouan-container">
            <div class="box sixteen-nine ie-order-box"
                 v-loading="cOrderValChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cOrderValChart'>
  
              </div>
              <a v-bind:href="exportInCustomsAmount"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
          </div>
        </el-col>
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="gridColumnSpan">
          <div class="grid-content">
            <div class="box sixteen-nine ie-order-box"
                 v-loading="cOrderIncomeChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cOrderIncomeChart'>
  
              </div>
              <a v-bind:href="exportInCountry"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
  
          </div>
        </el-col>
      </div>
      <header class="panel-header jm-grid-border-top-1px jm-grid-border-bottom-1px c-header c-header1"><span>出口</span></header>
      <div class="clearfix jm-padding-bottom-20">
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="gridColumnSpan">
          <div class="grid-content">
            <div class="box sixteen-nine ie-order-box"
                 v-loading="cCustomerNumChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cCustomerNumChart'>
  
              </div>
              <a v-bind:href="exportOutGoodsAmount"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
          </div>
        </el-col>
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="gridColumnSpan"
                v-if="!isSelectedKouan">
          <div class="grid-content kouan-container">
            <div class="box sixteen-nine ie-order-box"
                 v-loading="cCustomerValChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cCustomerValChart'>
  
              </div>
              <a v-bind:href="exportOutCustomsAmount"
                 target="_blank"
                 class="c-order-upload"></a>
            </div>
          </div>
        </el-col>
        <el-col :xs="24"
                :sm="24"
                :md="24"
                :lg="gridColumnSpan">
          <div class="grid-content">
            <div class="box sixteen-nine ie-order-box"
                 v-loading="cCustomerIncomeChartEmpty"
                 element-loading-text="暂无运营数据">
              <div class="content"
                   ref='cCustomerIncomeChart'>
  
              </div>
              <a v-bind:href="exportOutCountry"
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
import * as api from '@/service/api'
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
    getExportLinks(searchCriteria) {
      var vm = this;
      vm.exportInCountry = `/v1/inout/exportInOutCountryAmount/timeType/${searchCriteria.timeType}/startDate/${searchCriteria.startDate}/endDate/${searchCriteria.endDate}/customsCode/${searchCriteria.customsCode}/inout/in`;
      vm.exportOutCountry = `/v1/inout/exportInOutCountryAmount/timeType/${searchCriteria.timeType}/startDate/${searchCriteria.startDate}/endDate/${searchCriteria.endDate}/customsCode/${searchCriteria.customsCode}/inout/out`;
      vm.exportInGoodsAmount = `/v1/inout/exportInOutGoodsAmount/timeType/${searchCriteria.timeType}/startDate/${searchCriteria.startDate}/endDate/${searchCriteria.endDate}/customsCode/${searchCriteria.customsCode}/inout/in`;
      vm.exportOutGoodsAmount = `/v1/inout/exportInOutGoodsAmount/timeType/${searchCriteria.timeType}/startDate/${searchCriteria.startDate}/endDate/${searchCriteria.endDate}/customsCode/${searchCriteria.customsCode}/inout/out`;
      vm.exportInCustomsAmount = `/v1/inout/exportInOutCustomsAmount/timeType/${searchCriteria.timeType}/startDate/${searchCriteria.startDate}/endDate/${searchCriteria.endDate}/customsCode/${searchCriteria.customsCode}/inout/in`;
      vm.exportOutCustomsAmount = `/v1/inout/exportInOutCustomsAmount/timeType/${searchCriteria.timeType}/startDate/${searchCriteria.startDate}/endDate/${searchCriteria.endDate}/customsCode/${searchCriteria.customsCode}/inout/out`;
    },

    getSearchCriteria(searchCriteria) {
      var vm = this;

      vm.isSelectedKouan = searchCriteria.isSelectedKouan

      vm.getExportLinks(searchCriteria)

      vm.$nextTick(() => {
        vm.companyRank('cOrderNumChart', 'getInOutGoodsAmount', 'in', '万美元', '#2ec7c9', searchCriteria)
        vm.companyRank('cOrderValChart', 'getInOutCustomsAmount', 'in', '万美元', '#b6a2de', searchCriteria)
        vm.companyRank('cOrderIncomeChart', 'getInOutCountryAmount', 'in', '万美元', '#5ab1ef', searchCriteria)

        vm.companyRank('cCustomerNumChart', 'getInOutGoodsAmount', 'out', '万美元', '#2ec7c9', searchCriteria)
        vm.companyRank('cCustomerValChart', 'getInOutCustomsAmount', 'out', '万美元', '#b6a2de', searchCriteria)
        vm.companyRank('cCustomerIncomeChart', 'getInOutCountryAmount', 'out', '万美元', '#5ab1ef', searchCriteria)
      })


    },
    // 企业排名
    companyRank(ref, fnName, inout, unit, color, searchCriteria) {
      var vm = this;

      if (!vm.$refs[ref]) {
        return;
      }
      // 基于准备好的dom，初始化echarts实例
      vm[ref] = echarts.init(vm.$refs[ref]);

      var timeType = searchCriteria.timeType
      var startDate = searchCriteria.startDate,
        endDate = searchCriteria.endDate,
        customsCode = searchCriteria.customsCode;

      api[fnName]({
        timeType,
        startDate,
        endDate,
        customsCode,
        inout: inout
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
            name: chartData.title.replace('TOP5', ''),
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
    }
  },
  mounted() {
    var vm = this;

    var searchCriteria = {
      timeType: 'week',
      startDate: util.firstDayOfWeek().format(),
      endDate: util.lastDayOfWeek().format(),
      customsCode: 'all'
    }

    vm.getExportLinks(searchCriteria)

    vm.companyRank('cOrderNumChart', 'getInOutGoodsAmount', 'in', '万美元', '#2ec7c9', searchCriteria)
    vm.companyRank('cOrderValChart', 'getInOutCustomsAmount', 'in', '万美元', '#b6a2de', searchCriteria)
    vm.companyRank('cOrderIncomeChart', 'getInOutCountryAmount', 'in', '万美元', '#5ab1ef', searchCriteria)

    vm.companyRank('cCustomerNumChart', 'getInOutGoodsAmount', 'out', '万美元', '#2ec7c9', searchCriteria)
    vm.companyRank('cCustomerValChart', 'getInOutCustomsAmount', 'out', '万美元', '#b6a2de', searchCriteria)
    vm.companyRank('cCustomerIncomeChart', 'getInOutCountryAmount', 'out', '万美元', '#5ab1ef', searchCriteria)

    window.addEventListener('resize', chartsResize, false);

    function chartsResize() {

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
  computed: {
    gridColumnSpan() {
      return this.isSelectedKouan ? 12 : 8
    }
  },
  data() {
    return {
      exportInCountry: '',
      exportInGoodsAmount: '',
      exportInCustomsAmount: '',
      exportOutCountry: '',
      exportOutGoodsAmount: '',
      exportOutCustomsAmount: '',
      isSelectedKouan: false,
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
.ie-order-box {
  height: 407px;
}

.import-export-container .el-loading-spinner .circular {
  display: none;
}

.import-export-container .el-loading-mask {
  z-index: 1000;
  background-color: rgba(255, 255, 255, 0);
}
</style>