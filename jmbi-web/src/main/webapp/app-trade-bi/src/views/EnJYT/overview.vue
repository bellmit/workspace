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

    <el-row :gutter="20" class="summary-wrapper jm-grid-border-1px" v-loading="loadingOverviewItems" element-loading-text="加载中...">
      <header class="summary-title">历史累计：截止{{nowDate}}
        <!--<setting-display-items v-on:checkedSettingItems="getCheckedSettingItems"></setting-display-items>-->
      </header>
      <el-col :xs="8" :sm="8" :md="8" :lg="4" v-for="(item, index) in overviewDataList" :key="item">
        <div class="grid-content">
          <div class="box-summary " v-bind:class="item.classObject">

            <p>
              <span class="number">{{item.value}}</span>
              <span class="unit">{{item.unit}}</span>
            </p>
            <p class="title">{{item.name}}</p>

          </div>
        </div>
      </el-col>

    </el-row>
    <!-- end of summary -->

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>

    <el-row>

      <el-col :span="24">

        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px zhibiao-activeTabName chart-setting-items-container">

          <!--<setting-display-items v-on:checkedSettingItems="getChartCheckedSettingItems"></setting-display-items>-->
          <div class="setting-items-button-group">
            <el-radio-group v-model="selectedOverviewLinechartItem" @change="selectedOverviewLinechartItemChanged">
              <el-radio-button :label="item.name" v-for="item in overviewLineChartDisplayItems" :key="item"></el-radio-button>

            </el-radio-group>
          </div>

          <chart-box v-on:loadOption="loadOverviewLineChart" :chartBoxClass="overviewLineChartBoxClass" apiName="enjytOverviewLineChart" :apiParams="overviewLineChartApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>

        </div>
      </el-col>

    </el-row>
    <!-- end of zhibiao-->

    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">物流线路</header>

          <chart-box v-on:loadOption="loadTransportLineChartOption" apiName="enjytOverviewTransportLine" :apiParams="overviewTransportLineApiParams" seriesDataProperty="pieChart.seriesData"></chart-box>

        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">需求发布数排行
          </header>
          <chart-box v-on:loadOption="loadRequireTop5ChartOption" apiName="enjytOverviewRequireTop5" :apiParams="overviewRequireTop5ApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>

        </div>
      </el-col>
    </el-row>
    <!-- end of 物流线路 & 需求发布数排行 -->

  </div>
</template>

<script>
import util from '@/service/util'

import {
  enjytOverview
} from '@/service/api'

import rangeDatePicker from '@/components/RangeDatePicker'
import settingDisplayItems from '@/components/JYT/settingDisplayItems'
import chartBox from '@/components/chartBox'

import echarts from 'echarts';


export default {
  name: 'overview',
  components: {
    rangeDatePicker,
    settingDisplayItems,
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
    overviewLineChartApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate,
        itemId: this.selectedOverviewLinechartItemId
      }
    },
    overviewTransportLineApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate
      }
    },
    overviewRequireTop5ApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate
      }
    }
  },
  methods: {
    selectedOverviewLinechartItemChanged(value) {
      let item = this.overviewLineChartDisplayItems.find(x => x.name === value)

      if (item) {
        this.selectedOverviewLinechartItemId = item.id
      }

    },
    getChartCheckedSettingItems(checkedSettingItems) {
      this.overviewLineChartDisplayItems = checkedSettingItems.sort((a, b) => a.id - b.id)

      let included = this.overviewLineChartDisplayItems.find(x => x.name === this.selectedOverviewLinechartItem)
      if (!included) {
        this.selectedOverviewLinechartItem = this.overviewLineChartDisplayItems[0].name
        this.selectedOverviewLinechartItemId = this.overviewLineChartDisplayItems[0].id
      }
    },
    getCheckedSettingItems(checkedSettingItems) {
      let itemIds = checkedSettingItems.map(x => x.id).sort((a, b) => a - b).join('-')
      this.loadOverviewItems(itemIds)
    },
    loadOverviewItems(itemIds) {
      var vm = this;
      vm.loadingOverviewItems = true
      enjytOverview({
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate,
        itemIds: itemIds
      }).then(function (response) {
        vm.loadingOverviewItems = false
        var dataList = response.data.data;
        vm.nowDate = response.data.nowDate
        vm.overviewDataList = dataList.map(function (currentValue, index) {
          return Object.assign(currentValue, { classObject: "summary-bg-" + (index + 1) })
        })
      })
    },
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;

      this.loadAllData();
    },
    loadAllData() {
      var vm = this;

      vm.loadOverviewItems('1-2-4-5')

    },

    loadOverviewLineChart({ chartData, context, chartId }) {

      chartData = chartData.lineChart

      let yAxis = {
        name: chartData.unit,
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#158bca'
          }
        }
      }

      if (chartData.unit.indexOf('元') === -1) {

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

    loadTransportLineChartOption({ chartData, context, chartId }) {
      var vm = this;

      let pieChart = chartData.pieChart

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          text: `物流线路:${chartData.counts}条`,
          top: '3%',
          left: 'center',
          textStyle: {
            fontWeight: 100,
            fontSize: 22
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
          {
            name: '物流线路',
            type: 'pie',
            radius: ['30%', '55%'],
            center: ['50%', '50%'],
            data: pieChart.seriesData,
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

      context.$initChart(context, chartId, option)
    },

    loadRequireTop5ChartOption({ chartData, context, chartId }) {

      let lineChart = chartData.lineChart

      let xAxis = {
        // name: '次',
        type: 'value',
        boundaryGap: false,
        axisLine: {
          lineStyle: {
            color: '#158bca'
          }
        }
      }

      // 非金额单位
      // 从0开始，最小间隔为整数。
      let maxVal = Math.max(...lineChart.seriesData)
      if (maxVal < 5) {
        xAxis.splitNumber = maxVal || 1
        xAxis.minInterval = 1

      } else {
        xAxis.splitNumber = 0
        xAxis.minInterval = 0
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
          data: lineChart.yAxisData.reverse()
        },
        series: [{
          name: "需求发布数",
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#44cd8a',
              barBorderRadius: 2
            }
          },
          data: lineChart.seriesData.reverse()
        }]
      };

      context.$initChart(context, chartId, option)
    }

  },
  data() {
    return {
      overviewDataList: [],
      selectedOverviewLinechartItem: "供应商企业数",
      selectedOverviewLinechartItemId: 1,
      startDate: new Date().format(),
      endDate: new Date().format(),
      summaryData: {},// todo
      loadingOverviewItems: false,
      overviewLineChartDisplayItems: [
        { id: 1, name: "供应商企业数" },
        { id: 2, name: "委托方企业数" },
//        { id: 3, name: "委托方个人数" },
        { id: 4, name: "仓库数" },
        { id: 5, name: "线路数" }],
//        { id: 6, name: "货值" }],
      overviewLineChartBoxClass: {
        'overview-line-chart-box-height': true
      },
      nowDate: ""
    }
  },
  mounted() {
    let vm = this;

    vm.loadAllData();

  }
}

</script>

<style lang="scss">
.chart-title {
  margin: 35px 0 0;
  font-size: 36px;
  text-align: center;
  span {
    margin-left: 5px;
    font-size: 18px;
    color: #00CCFF;
  }
}

.table-container {
  padding: 30px;
}

.summary-wrapper {
  min-height: 208px;
  margin-left: 0!important;
  margin-right: 0!important;
  padding: 20px 10px;
}

.summary-title {
  position: relative;
  margin: 0 10px 15px;
  font-size: 16px;
  color: #333333;

  .el-icon-setting {
    position: absolute;
    right: 10px;
    top: 0;
    font-size: 20px;
    cursor: pointer;
  }
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

.chart-setting-items-container {
  position: relative;
  .el-icon-setting {
    position: absolute;
    right: 25px;
    top: 20px;
    font-size: 20px;
    cursor: pointer;
    z-index: 1000;
  }
}

.setting-items-button-group {
  text-align: center;
  padding: 20px;
}

.box.overview-line-chart-box-height {
  height: 400px;
}
</style>
