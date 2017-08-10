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
  
    <data-overview apiName="jrtOperationOverview" panelTitle="数据概览（累计）"></data-overview>
  
    <el-row class="jm-grid-border-1px">
  
      <el-col :span="24">
        <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">平台融资趋势（上线以来）</header>
        <div class="grid-content jm-grid-box-bg-white  chart-setting-items-container">
  
          <!--<setting-display-items v-on:checkedSettingItems="getChartCheckedSettingItems"></setting-display-items>-->
          <div class="setting-items-button-group">
            <el-radio-group v-model="selectedOverviewLinechartItem" @change="selectedOverviewLinechartItemChanged">
              <el-radio-button :label="item.name" v-for="item in overviewLineChartDisplayItems" :key="item"></el-radio-button>
  
            </el-radio-group>
          </div>
  
          <chart-box v-on:loadOption="loadOverviewLineChart" :chartBoxClass="overviewLineChartBoxClass" apiName="jrtFinancingTrend" :apiParams="jrtFinancingTrendApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>
  
        </div>
      </el-col>
  
    </el-row>
    <!-- end of zhibiao-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">业务对象分析（累计）
  
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="12" class="jm-grid-box-bg-white">
        <div class="grid-content jm-grid-box-bg-white">
          <header style="text-align:center;font-size:22px;padding-top:20px;">入驻企业类别</header>
          <p class="overview-chart-title">
  
            <span class="chart-title-key">{{EnrollCompanyTypeTotal}}</span>
            <span class="chart-title-unit">家</span>
          </p>
          <chart-box v-on:loadOption="loadPieChartOption" apiName="jrtEnrollCompanyType" seriesDataProperty="pieCharts.seriesData"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
          <header style="text-align:center;font-size:22px;padding-top:20px;">上架产品类型</header>
          <p class="overview-chart-title">
  
            <span class="chart-title-key">{{CompanyProductTypeTotal}}</span>
            <span class="chart-title-unit">个</span>
          </p>
          <chart-box v-on:loadOption="loadPieChartOption" apiName="jrtCompanyProductType" seriesDataProperty="pieCharts.seriesData"></chart-box>
  
        </div>
      </el-col>
    </el-row>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">企业用户区域分布（累计）
  
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white map">
  
          <chart-box v-on:loadOption="loadMapOption" :chartBoxClass="enterpriseUserChartBoxClass" apiName="jrtCompanyArea" seriesDataProperty=""></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-padding-20X20">
  
          <div class="enterprise-user-table-boxclass">
            <header>区域企业数 TOP10</header>
            <div class="table-container" v-loading="loading" element-loading-text="加载中...">
              <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="index" label="排名">
                </el-table-column>
                <el-table-column prop="area_name" label="省份">
                </el-table-column>
                <el-table-column prop="count" label="企业数" width="180">
                </el-table-column>
                <el-table-column prop="proportion" label="占比" width="180">
                </el-table-column>
              </el-table>
            </div>
  
          </div>
  
        </div>
      </el-col>
    </el-row>
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12" class="fixed-745">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">注册转化</header>
          <div class="date-period-items-button-group">
            <el-radio-group v-model="registerConvertItem" @change="registerConvertItemChanged">
              <el-radio-button :label="item.name" v-for="item in registerConvertItems" :key="item"></el-radio-button>
  
            </el-radio-group>
          </div>
          <div class="overview-register-convert-bar-container" style="postion:relative;">
            <span class="box-tip-color-1">{{CovertRates[0]}}%</span>
            <span class="box-tip-color-2">{{CovertRates[1]}}%</span>
            <span class="box-tip-color-3">{{CovertRates[2]}}%</span>
            <span class="box-tip-color-4">{{CovertRates[3]}}%</span>
            <chart-box v-on:loadOption="loadRegisterConvertBarChartOption" :chartBoxClass="overviewRegisterConvertBarChartBoxClass" apiName="jrtRegistrationConversion" :apiParams="overviewApiParams" seriesDataProperty="data"></chart-box>
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
      vm.tableData = chartData.map((x, index) => {
        return Object.assign({}, x, {
          index: index + 1,
          proportion: x.proportion + '%'
        })
      }).slice(0, 10)

      let maxValue = 0;

      chartData.forEach(function (data) {
        let v = Number(data.count)
        if (v > maxValue) {
          maxValue = v;
        }
      })

      if (maxValue === 0) {
        maxValue = 255;
      }

      // 绘制图表
      var option = {
        title: {
          text: '入驻企业区域分布',
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
              result += `<br>入驻企业数：${obj.data.value}家`
            } else {
              result += `<br>入驻企业数：--`
            }

            if (obj.data.value2) {
              result += `<br>占比：${obj.data.value2}%`
            } else {
              result += `<br>占比：--`
            }

            return result
          }
        },
        legend: {
          show: false,
          orient: 'vertical',
          left: 'left',
          data: ['入驻企业区域分布']
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
            mapType: 'china',
            roam: true,
            label: {
              normal: {
                show: true
              },
              emphasis: {
                show: true
              }
            },
            data: chartData.map(function (item) {
              let name = item.area_name || '';
              name = name.replace('自治区', '')
              name = name.replace('省', '')
              name = name.replace('市', '')

              return {
                name: name,
                value: item.count,
                value2: item.proportion
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

    loadPieChartOption({ chartData, context, chartId }) {
      var vm = this;

      let pieChart = chartData.pieCharts

      if (pieChart.title === '入驻企业类别') {
        vm.EnrollCompanyTypeTotal = chartData.total
      }
      if (pieChart.title === '上架产品类型') {
        vm.CompanyProductTypeTotal = chartData.total
      }

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        // title: {
        //   text: pieChart.title,
        //   top: '3%',
        //   left: 'center',
        //   textStyle: {
        //     fontWeight: 100,
        //     fontSize: 16
        //   }
        // },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
          {
            name: pieChart.seriesName,
            type: 'pie',
            radius: ['30%', '55%'],
            center: ['50%', '45%'],
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
          // axisTick: {
          //   alignWithLabel: true
          // },
          data: yAxisData//['注册', 'XX某2', 'XX某3', 'XX某4', 'XX某5'],
          // axisLabel: {
          //     show: true,
          //     interval: 'auto',
          //     formatter: '{value}%',
          // },
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
      selectedOverviewLinechartItem: "融资申请笔数",
      selectedOverviewLinechartItemId: 0,
      startDate: new Date().format(),
      endDate: new Date().format(),
      overviewLineChartDisplayItems: [
        { id: 0, name: "融资申请笔数" },
        { id: 1, name: "融资需求笔数" },
        { id: 2, name: "融资签约笔数" }],
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
      tableData: [],
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
    bottom: 58.5%;
    background: #74bc4c;

    &:after {
      border-top-color: #74bc4c;
    }
  }

  .box-tip-color-2 {
    bottom: 46%;
    background: #e0b84f;

    &:after {
      border-top-color: #e0b84f;
    }
  }

  .box-tip-color-3 {
    bottom: 33.6%;
    background: #dc5d43;
    &:after {
      border-top-color: #dc5d43;
    }
  }

  .box-tip-color-4 {
    bottom: 21.5%;
    background: #d3c455;
    &:after {
      border-top-color: #d3c455;
    }
  }
}

.fixed-745 {
  width: 715px+30px;
}
</style>
