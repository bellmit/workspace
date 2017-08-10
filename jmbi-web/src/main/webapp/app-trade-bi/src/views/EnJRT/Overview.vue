<template>
  <div class="jrt-overview">

    <el-breadcrumb separator=">" class="jm-margin-bottom20">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>总览</el-breadcrumb-item>
    </el-breadcrumb>

    <data-overview apiName="enjrtOperationOverview" panelTitle="数据概览（累计）"></data-overview>

    <el-row class="jm-grid-border-1px">

      <el-col :span="24">
        <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">业务趋势（上线以来）</header>
        <div class="grid-content jm-grid-box-bg-white  chart-setting-items-container">

          <!--<setting-display-items v-on:checkedSettingItems="getChartCheckedSettingItems"></setting-display-items>-->
          <div class="setting-items-button-group">
            <el-radio-group v-model="selectedOverviewLinechartItem" @change="selectedOverviewLinechartItemChanged">
              <el-radio-button :label="item.name" v-for="item in overviewLineChartDisplayItems" :key="item.id"></el-radio-button>

            </el-radio-group>
          </div>

          <chart-box v-on:loadOption="loadOverviewLineChart" :chartBoxClass="overviewLineChartBoxClass" apiName="enjrtFinancingTrend" :apiParams="jrtFinancingTrendApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>

        </div>
      </el-col>

    </el-row>
    <!-- end of zhibiao-->


    <el-row class="jm-grid-border-1px">
      <div class="grid-content jm-grid-box-bg-white  chart-setting-items-container">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">区域分布</header>
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white map">
          <div class="setting-items-button-group">
            <el-radio-group v-model="selectedOverviewMapItem" @change="selectedOverviewMapItemChanged">
              <el-radio-button :label="item.name" v-for="item in overviewMapDisplayItems" :key="item.id"></el-radio-button>

            </el-radio-group>
          </div>
          <chart-box v-on:loadOption="loadMapOption" :chartBoxClass="enterpriseUserChartBoxClass" apiName="enjrtCompanyArea" :apiParams="companyAreaApiParams" seriesDataProperty="pieChart.seriesData"></chart-box>
        </div>
      </el-col>
      </div>
    </el-row>

    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12" class="fixed-745">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">注册转化</header>
          <div class="date-period-items-button-group">
            <el-radio-group v-model="registerConvertItem" @change="registerConvertItemChanged">
              <el-radio-button :label="item.name" v-for="item in registerConvertItems" :key="item.id"></el-radio-button>

            </el-radio-group>
          </div>
          <div class="overview-register-convert-bar-container" style="postion:relative;">
            <span class="box-tip-color-1">{{CovertRates[0]}}%</span>
            <span class="box-tip-color-2">{{CovertRates[1]}}%</span>
            <span class="box-tip-color-3">{{CovertRates[2]}}%</span>
            <chart-box v-on:loadOption="loadRegisterConvertBarChartOption" :chartBoxClass="overviewRegisterConvertBarChartBoxClass" apiName="enjrtRegistrationConversion" :apiParams="overviewApiParams" seriesDataProperty="data"></chart-box>
          </div>

        </div>
      </el-col>

    </el-row>

  </div>
</template>

<script>
import util from '@/service/util'

import {
  jytOverview
} from '@/service/api'

import rangeDatePicker from '@/components/RangeDatePicker'
import settingDisplayItems from '@/components/JYT/settingDisplayItems'
import DataOverview from '@/components/common/DataOverview'
import chartBox from '@/components/chartBox'

import echarts from 'echarts';


export default {
  name: 'overview',
  components: {
    rangeDatePicker,
    settingDisplayItems,
    DataOverview,
    chartBox
  },
  created() {

    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }

    // 30days
    const start = new Date();
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 29);
    this.startDate = start.format()

  },
  computed: {
    jrtFinancingTrendApiParams() {
      return {
        type: this.selectedOverviewLinechartItemId
      }
    },
    companyAreaApiParams() {
      return {
        type: this.selectedOverviewMapItemId
      }
    },
    overviewApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate
      }
    },

  },
  methods: {
    selectedOverviewLinechartItemChanged(value) {
      let item = this.overviewLineChartDisplayItems.find(x => x.name === value)
      if (item) {
        this.selectedOverviewLinechartItemId = item.id
      }
    },
    selectedOverviewMapItemChanged(value) {
      let item = this.overviewMapDisplayItems.find(x => x.name === value)
      if (item) {
        this.selectedOverviewMapItemId = item.id
      }
    },
    registerConvertItemChanged(value) {
      let item = this.registerConvertItems.find(x => x.name === value)

      if (item) {
        this.registerConvertItemId = item.id
      }

      const start = new Date();

      // { id: 1, name: "最近30天" },
      //   { id: 2, name: "最近3个月" },
      //   { id: 3, name: "最近半年" }],
      let period = 30;
      switch (item.id) {
        case 1:
          period = 30 * 1 - 1;
          break;
        case 2:
          period = 30 * 3 - 1;
          break;
        case 3:
          period = 30 * 6 - 1;
          break;
      }
      start.setTime(start.getTime() - 3600 * 1000 * 24 * period);
      this.startDate = start.format()

    },


    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;

      this.loadAllData();
    },
    loadAllData() {
      var vm = this;
    },
    loadMapOption({ chartData, context, chartId }) {
      var vm = this;

      vm.loading = false;

      let maxValue = 0;

      chartData.pieChart.seriesData.forEach(function (data) {
        let v = Number(data.count)
        if (v > maxValue) {
          maxValue = v;
        }
      })

      if (maxValue === 0) {
        maxValue = 255;
      }
      var itemId = this.selectedOverviewMapItemId;
      // 绘制图表
      var option = {
        title: {
          text: '',
          top: '5%',
          left: 'center',
          textStyle: {
            fontWeight: 100,
            fontSize: 16
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: function (obj) {
            let result = obj.name + '<hr style="margin-bottom:-10px;padding:0">';
            if (obj.data.value) {
              if (itemId == 0) {
                result += `<br>融资申请：${obj.data.value}笔`
              } else {
                result += `<br>入驻企业：${obj.data.value}家`
              }
            } else {
              result += `<br>--`
            }

            return result
          }
        },
        legend: {
          show: false,
          orient: 'vertical',
          left: 'left',
          data: ['区域分布']
        },
        visualMap: {
          min: 0,
          max: maxValue,
          left: 'left',
          top: 'bottom',
          text: ['高', '低'],
          inRange: {
            color: ['#e0ffff', '#006edd']
          },
          calculable: true
        },

        series: [
          {
            name: '入驻企业区域分布',
            type: 'map',
            mapType: 'world',
            roam: true,
            label: {
              normal: {
                show: false
              },
              emphasis: {
                show: true
              }
            },
            data: chartData.pieChart.seriesData.map(function (item) {
              let name = item.name || '';
              return {
                name: name,
                value: item.value,
                id: item.typeOrId
              }
            })
          }
        ]
      };

      context.$initChart(context, chartId, option)
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

        }
      } else {
        yAxis.splitNumber = 5
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


    loadRegisterConvertBarChartOption({ chartData, context, chartId }) {
      let yAxisData = [];
      let seriesData = [];
      let CovertRates = [];

      chartData.data.forEach(x => {
        yAxisData.push(x.name)
        seriesData.push(x.value)
        CovertRates.push(x.Conv)
      })

      CovertRates.shift();

      yAxisData = yAxisData.reverse()
      seriesData = seriesData.reverse()
      this.CovertRates = CovertRates

      let xAxis = {
        type: 'value'
      }

      // 从0开始，最小间隔为整数。
      let maxVal = Math.max(...seriesData)
      if (maxVal < 5) {
        xAxis.splitNumber = maxVal
        xAxis.minInterval = 1
      } else {
        xAxis.splitNumber = 0
        xAxis.minInterval = 0
      }

      // 绘制图表
      var option = {
        // title: {
        //   text: '需求驳回率排名',
        // },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: function (obj) {
            var series = obj[0]
            return `${series.seriesName}<br>${series.name}: ${series.data}人`
          }
        },
        grid: {
          top: '5%',
          left: '3%',
          right: '15%',
          bottom: '10%',
          containLabel: true
        },
        xAxis: xAxis,
        yAxis: {
          type: 'category',
          splitLine: {
            show: true
          },
          data: yAxisData//['注册', 'XX某2', 'XX某3', 'XX某4', 'XX某5'],
        },
        series: [
          {
            name: '注册转化',
            type: 'bar',
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: '#4489db',
                barBorderRadius: 2,
                label: {
                  textStyle: {
                    color: '#7ca7e1',
                    fontSize: 3
                  },
                  show: true,
                  position: 'right',
                  formatter: '{c}人',


                }
              }
            },
            data: seriesData//[8, 13, 15, 17, 22],

          }
        ]
      };

      context.$initChart(context, chartId, option)
    }

  },
  data() {
    return {
      loading: true,
      EnrollCompanyTypeTotal: 0,
      CompanyProductTypeTotal: 0,
      overviewDataList: [],
      selectedOverviewLinechartItem: "融资申请",
      selectedOverviewLinechartItemId: 0,
      selectedOverviewMapItem: "融资申请",
      selectedOverviewMapItemId: 0,
      startDate: new Date().format(),
      endDate: new Date().format(),
      overviewLineChartDisplayItems: [
        { id: 0, name: "融资申请" },
        { id: 1, name: "融资签约" },
        { id: 2, name: "入驻企业" },
        { id: 3, name: "上架产品" }],
      overviewMapDisplayItems: [
        { id: 0, name: "融资申请" },
        { id: 2, name: "入驻企业" }],
      registerConvertItems: [
        { id: 1, name: "最近30天" },
        { id: 2, name: "最近3个月" },
        { id: 3, name: "最近半年" }],
      registerConvertItem: "最近30天",
      registerConvertItemId: 1,
      overviewLineChartBoxClass: {
        'overview-line-chart-box-height': true
      },
      enterpriseUserChartBoxClass: {
        'overview-enterprise-user-map-chart': true
      },
      overviewRegisterConvertBarChartBoxClass: {
        'overview-register-convert-bar-chart': true
      },
      yesterday: util.getYesterday(),
      CovertRates: []
    }
  },
  mounted() {
    let vm = this;

    vm.loadAllData();

  }
}

</script>

<style lang="scss">
.jrt-overview {
  .overview-chart-title {
    margin: 35px 0 0;
    font-size: 36px;
    text-align: center;
    .chart-title-key {
      font-size: 36px;
      color: #00ccff;
    }
    .chart-title-unit {
      color: #000;
      font-size: 18px;
    }
  }
}

.overview-enterprise-user-map-chart {
  height: 481px+28px;
}

.enterprise-user-table-boxclass {
  header {
    padding: 6px 0;
    font-size: 16px;
  }
  .table-container {
    height: 441px;
    overflow: auto;
  }
}

.setting-items-button-group {
  text-align: center;
  padding: 20px;
}

.date-period-items-button-group {
  text-align: right;
  padding: 20px;
}

.box.overview-line-chart-box-height {
  height: 400px;
}

.box.overview-register-convert-bar-chart {
  height: 401px;
}

.overview-register-convert-bar-container {

  span {
    position: absolute;
    right: 5%;
    font-size: 14px;
    color: #fff;
    padding: 5px;
    border-radius: 2px;
    min-width: 55px;
    text-align: center;

    &:after {
      position: absolute;
      left: 50%;
      bottom: -16px;
      transform: translate(-50%);
      content: '';
      display: block;
      width: 0;
      height: 0;
      border-style: solid;
      border-width: 8px;
      border-color: transparent;
    }
  }

  .box-tip-color-1 {
    bottom: 55%;
    background: #74bc4c;

    &:after {
      border-top-color: #74bc4c;
    }
  }

  .box-tip-color-2 {
    bottom: 40%;
    background: #e0b84f;

    &:after {
      border-top-color: #e0b84f;
    }
  }

  .box-tip-color-3 {
    bottom: 24%;
    background: #dc5d43;
    &:after {
      border-top-color: #dc5d43;
    }
  }

}

.fixed-745 {
  width: 715px+30px;
}
</style>
