<template>
  <div>

    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>用户行为</el-breadcrumb-item>
      <el-breadcrumb-item>业务分析</el-breadcrumb-item>
    </el-breadcrumb>

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>

    <data-overview apiName="jrtServiceDataOverview" :apiParams="apiParams" panelTitle="业务数据"></data-overview>

    <el-row class="jm-grid-border-1px">

      <el-col :span="24">
        <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">新增入驻企业 /上架产品</header>
        <div class="grid-content jm-grid-box-bg-white  chart-setting-items-container">
          <div class="jm-padding-20X20 jm-text-center">
            <el-button :type="isEnrollEnterprise" @click="EnrollOrGoods('enter')">入驻企业</el-button>
            <el-button :type="isGoods" @click="EnrollOrGoods('shelf')">上架产品</el-button>
            <div class="jm-padding-top-15 raw-stype-radio-group" v-if="isEnrollEnterprise">
              <el-radio-group v-model="selectedEnrollEnterpriseItems" @change="selectedEnrollEnterpriseItemChanged">
                <el-radio-button :label="item.name" v-for="item in enrollEnterpriseItems" :key="item"></el-radio-button>
              </el-radio-group>
            </div>
            <div class="jm-padding-top-15 raw-stype-radio-group" v-if="isGoods">
              <el-radio-group v-model="selectedshelfProductType" @change="selectedShelfProductTypeChanged">
                <el-radio-button :label="item.product_type_name" v-for="item in shelfProductTypes" :key="item"></el-radio-button>
              </el-radio-group>
            </div>

          </div>

          <chart-box v-on:loadOption="loadOverviewLineChart" :chartBoxClass="enrollShelfLineChartBoxClass" apiName="jrtCompanyEnrollOrShelfTrend" :apiParams="jrtCompanyEnrollOrShelfTrendApiParams" seriesDataProperty="seriesData"></chart-box>

        </div>
      </el-col>

    </el-row>
    <!-- end of zhibiao-->

    <el-row class="jm-grid-border-1px">

      <el-col :span="24">
        <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">融资趋势分析</header>
        <div class="grid-content jm-grid-box-bg-white">
          <div class="jm-padding-top-15 jm-text-center">
            <el-radio-group v-model="selectedFinancingItem" @change="selectedFinancingItemChanged">
              <el-radio-button :label="item.name" v-for="item in FinancingItems" :key="item"></el-radio-button>
            </el-radio-group>
          </div>
          <chart-box v-on:loadOption="loadFinanceLineChart" :chartBoxClass="enrollShelfLineChartBoxClass" apiName="jrtFinancingTrendAnalysis" :apiParams="jrtFinancingTrendAnalysisApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>

        </div>
      </el-col>

    </el-row>

    <el-row class="jm-grid-border-1px">

      <el-col :span="24">
        <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">融资明细</header>
        <div class="grid-content jm-grid-box-bg-white jm-padding-15X15" v-loading="loadingFinancingTrendDetail" element-loading-text="加载中...">
          <div class="">
            <el-radio-group v-model="selectedFinancingDetailItem" @change="selectedFinancingDetailItemChanged">
              <el-radio-button :label="item.name" v-for="item in financingDetailItems" :key="item"></el-radio-button>
            </el-radio-group>
          </div>

          <el-table :data="tableData" border style="width: 100%">
            <el-table-column header-align="center" :prop="c.name" :label="c.label" :width="c.width" v-for="c in tableColumns" :key="c">
            </el-table-column>
          </el-table>
          <div class="jm-padding-15X15 j-text-right">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" :page-sizes="[10, 30, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="totalSize">
            </el-pagination>
          </div>
        </div>
      </el-col>

    </el-row>

  </div>
</template>
<script>

import {
  jrtCompanyShelfProductType,
  jrtFinancingTrendDetail
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker3'
import DataOverview from '@/components/common/DataOverview'
import chartBox from '@/components/chartBox'

import echarts from 'echarts';

export default {
  name: 'loginAnalysis',
  components: {
    rangeDatePicker,
    DataOverview,
    chartBox
  },
  created() {
    // 初始化开始时间为7天之前
    const start = new Date();
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 6);
    this.startDate = start.format();

    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }

    this.getShelfProductType()
  },
  computed: {
    isEnrollEnterprise() {
      return this.enrollOrGoods === 'enter' ? 'primary' : ''
    },
    isGoods() {
      return this.enrollOrGoods === 'shelf' ? 'primary' : ''
    },
    jrtCompanyEnrollOrShelfTrendApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate,
        type: this.enrollOrGoods,
        itemType: this.activeEnrollorSelfTypeId
      }
    },
    jrtFinancingTrendAnalysisApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate,
        type: this.selectedFinancingItemId,
      }
    },
    financingDetailApiParams() {
      return {
        limit: this.pageSize,
        offset: this.currentPage - 1,
        type: this.selectedFinancingDetailItemId
      }
    },
    apiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate
      }
    },
    tableColumns() {
      var id = this.selectedFinancingDetailItemId;
      switch (id) {
        case 0:
          // 融资申请笔数
          return this.sqColumns
        case 1:
          // 融资需求笔数
          return this.xqColumns
        case 2:
          // 融资签约笔数
          return this.qyColumns
        default:
          return this.xqColumns
      }
    }

  },
  methods: {
    handleSizeChange(val) {

      this.pageSize = val
      this.loadfinancingTrendDetail();

    },
    handleCurrentChange(val) {

      this.currentPage = val
      this.loadfinancingTrendDetail();

    },
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.loadAllData();
      this.getShelfProductType()
    },
    EnrollOrGoods(type) {
      this.enrollOrGoods = type
    },
    selectedEnrollEnterpriseItemChanged(value) {
      let item = this.enrollEnterpriseItems.find(x => x.name === value)

      if (item) {
        this.selectedEnrollEnterpriseItemsId = item.id
        this.activeEnrollorSelfTypeId = item.id
        this.activeEnrollorSelfType = item.name
      }

    },
    selectedShelfProductTypeChanged(value) {
      let item = this.shelfProductTypes.find(x => x.product_type_name === value)

      if (item) {
        this.selectedshelfProductTypeId = item.product_type
        this.activeEnrollorSelfTypeId = item.product_type
        this.activeEnrollorSelfType = item.product_type_name
      } else {
        this.activeEnrollorSelfTypeId = null
        this.activeEnrollorSelfType = ''
      }

    },
    selectedFinancingItemChanged(value) {
      let item = this.FinancingItems.find(x => x.name === value)

      if (item) {
        this.selectedFinancingItemId = item.id

      }

    },
    selectedFinancingDetailItemChanged(value) {
      let item = this.financingDetailItems.find(x => x.name === value)

      if (item) {
        this.selectedFinancingDetailItemId = item.id

      }

      this.loadfinancingTrendDetail();

    },
    getShelfProductType() {
      var vm = this
      jrtCompanyShelfProductType({
        startDate: vm.startDate,
        endDate: vm.endDate
      }).then(function (response) {

        vm.shelfProductTypes = response.data
        var first = vm.shelfProductTypes[0] || {}
        vm.selectedshelfProductType = first.product_type_name
        vm.selectedshelfProductTypeId = first.product_type

      })
        .catch(function (error) {
          console.log(error);
        });
    },
    loadAllData() {
      var vm = this;

      vm.loadfinancingTrendDetail();

    },
    loadfinancingTrendDetail() {
      var vm = this
      vm.loadingFinancingTrendDetail = true
      jrtFinancingTrendDetail(vm.financingDetailApiParams).then(function (response) {
        vm.loadingFinancingTrendDetail = false
        var tableData = response.data.tableData
        vm.totalSize = response.data.totalSize
        vm.tableData = tableData
      })
        .catch(function (error) {
          console.log(error);
        });
    },
    loadOverviewLineChart({ chartData, context, chartId }) {

      //chartData = chartData.lineChart
      let unit = chartData.unit || ''

      let yAxis = {
        name: unit,
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#158bca'
          }
        }
      }

      if (unit.indexOf('元') === -1) {

        // 非金额单位
        // 从0开始，最小间隔为整数。
        let maxVal = Math.max(...chartData.seriesData)
        if (maxVal < 5) {
          yAxis.splitNumber = maxVal || 1
          yAxis.minInterval = 1

        } else {
          yAxis.splitNumber = 0
          yAxis.minInterval = 0
        }
      } else {
        yAxis.splitNumber = 0
        yAxis.minInterval = 0
      }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          top: '10%',
          left: '3%',
          right: '5%',
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
          data: chartData.xAxisData
        }],
        yAxis: yAxis,
        series: [{
          name: chartData.title || this.activeEnrollorSelfType,
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
          data: chartData.seriesData
        }

        ]
      };

      context.$initChart(context, chartId, option)
    },

    loadFinanceLineChart({ chartData, context, chartId }) {

      chartData = chartData.lineChart
      let unit = chartData.unit || ''

      let yAxis = {
        name: unit,
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#158bca'
          }
        }
      }

      if (unit.indexOf('元') === -1) {

        // 非金额单位
        // 从0开始，最小间隔为整数。
        let maxVal = Math.max(...chartData.seriesData)
        if (maxVal < 5) {
          yAxis.splitNumber = maxVal || 1
          yAxis.minInterval = 1

        } else {
          yAxis.splitNumber = 0
          yAxis.minInterval = 0
        }
      } else {
        yAxis.splitNumber = 0
        yAxis.minInterval = 0
      }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          top: '10%',
          left: '3%',
          right: '5%',
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
          data: chartData.xAxisData
        }],
        yAxis: yAxis,
        series: [{
          name: chartData.title,
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
          data: chartData.seriesData
        }

        ]
      };

      context.$initChart(context, chartId, option)
    },



  },
  mounted() {

    var vm = this;

    vm.loadAllData()


  },
  data() {
    return {
      currentPage: 1,
      pageSize: 10,
      totalSize: 0,
      enrollOrGoods: "enter",
      startDate: new Date().format(),
      endDate: new Date().format(),
      activeEnrollorSelfTypeId: 53,
      activeEnrollorSelfType: "工贸一体",
      selectedEnrollEnterpriseItems: "工贸一体",
      selectedEnrollEnterpriseItemsId: 53,
      enrollEnterpriseItems: [
        { id: 53, name: "工贸一体" },
        { id: 52, name: "贸易商" },
        { id: 51, name: "制造工厂" },
        { id: 0, name: "其他" }],
      selectedshelfProductType: null,
      selectedshelfProductTypeId: null,
      shelfProductTypes: [],
      selectedFinancingItem: "融资申请",
      selectedFinancingItemId: 0,
      FinancingItems: [
        { id: 0, name: "融资申请" },
        { id: 1, name: "融资需求" },
        { id: 2, name: "融资签约" }],
      selectedFinancingDetailItem: "需求",
      selectedFinancingDetailItemId: 1,
      financingDetailItems: [
        { id: 0, name: "申请" },
        { id: 1, name: "需求" },
        { id: 2, name: "签约" }],
      enrollShelfLineChartBoxClass: {
        'enroll-shelf-line-chart-box-height': true
      },
      tableData: [],
      loadingFinancingTrendDetail: false,
      xqColumns: [
        {
          name: "create_time",
          label: "日 期",
          width: 120
        },
        {
          name: "req_no",
          label: "需求编号"
        },
        {
          name: "name",
          label: "需求名称"
        },
        {
          name: "company_name",
          label: "融资企业名称"
        },
        {
          name: "amount_app",
          label: "融资金额"
        },
        {
          name: "month_app",
          label: "融资期限"
        }
      ],
      sqColumns: [
        {
          name: "create_time",
          label: "日 期",
          width: 120
        },
        {
          name: "apply_no",
          label: "申请单号"
        },
        {
          name: "company_name",
          label: "融资企业名称"
        },
        {
          name: "product_name",
          label: "融资产品名称"
        },
        {
          name: "org_company_name",
          label: "机构名称"
        },
        {
          name: "amount_app",
          label: "融资金额"
        },
        {
          name: "month_app",
          label: "融资期限"
        }
      ],
      qyColumns: [
        {
          name: "create_time",
          label: "日 期",
          width: 120
        },
        {
          name: "loan_no",
          label: "贷款单号"
        },
        {
          name: "contract_no",
          label: "合同编号"
        },
        {
          name: "company_name",
          label: "融资企业名称"
        },
        {
          name: "product_name",
          label: "融资产品名称"
        },
        {
          name: "org_company_name",
          label: "机构名称"
        },
        {
          name: "reply_amount",
          label: "批复金额"
        },
        {
          name: "replay_term",
          label: "批复期限"
        }
      ]
    }
  }
}

</script>

<style lang="scss">
.box.enroll-shelf-line-chart-box-height {
  height: 400px;
}

.raw-stype-radio-group {
  .el-radio-button__orig-radio:checked+.el-radio-button__inner {
    color: #0588e6;
    background-color: #fff;
    border-color: #fff;
    box-shadow: -1px 0 0 0 #fff;
  }

  .el-radio-button__inner {
    border: none;
  }

  .el-radio-button:first-child .el-radio-button__inner {
    border-left: none;
  }
}
</style>
