<template>
  <div>

    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>用户行为</el-breadcrumb-item>
      <el-breadcrumb-item>流量分析</el-breadcrumb-item>
    </el-breadcrumb>

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>

    <data-overview-flow apiName="_enjrtOperationOverview" :apiParams="apiParams" dataProperty="data" panelTitle="概览"></data-overview-flow>

    <!--趋势分析开始-->
    <el-row>

      <el-col :span="24">

        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px zhibiao-activeTabName chart-setting-items-container">
          <header class="panel-header jm-grid-border-bottom-1px">趋势分析</header>

          <div class="setting-items-button-group">
            <el-radio-group v-model="selectedOverviewLinechartItem" @change="selectedOverviewLinechartItemChanged">
              <el-radio-button :label="item.name" v-for="item in overviewLineChartDisplayItems" :key="item.id"></el-radio-button>

            </el-radio-group>
          </div>

          <chart-box v-on:loadOption="loadOverviewLineChart" :chartBoxClass="overviewLineChartBoxClass" apiName="en_jrtOverviewLineChart" :apiParams="en_jrtOverviewLineChartApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>

        </div>
      </el-col>

    </el-row>
    <!--趋势分析结束-->

    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">页面流量排名
            <el-tooltip class="item" content="统计本网站流量次数TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>

          <chart-box v-on:loadOption="loadymllpmChart" apiName="home_pageVisit" :chartBoxClass="ymtcChartBoxClass"  :apiParams="apiParams" seriesDataProperty="lineChart.seriesData"></chart-box>

        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">页面跳出排名
            <el-tooltip class="item" content="统计本网站页面跳出TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>

          <chart-box v-on:loadOption="loadymtclpmChart" apiName="home_pageBounce"  :chartBoxClass="ymtcChartBoxClass" :apiParams="apiParams" seriesDataProperty="lineChart.seriesData"></chart-box>

        </div>
      </el-col>
    </el-row>
    <!-- end of 页面流量排名 & 页面跳出率排名 -->

    <el-row class="jm-grid-border-1px">

      <div class="grid-content jm-grid-box-bg-white ">
        <header class="panel-header jm-grid-border-bottom-1px">地域分布
          <el-tooltip class="item" content="按照访问量，统计访客地域分布，深色到浅色代表访客量由多到少。" placement="top-end">
            <span class="jm-tooltip-icon"></span>
          </el-tooltip>
        </header>

        <div class="box sixteen-nine world-map-chart-box-height" v-loading="loadingDyfbChart" element-loading-text="加载中...">
          <div class="content" ref='dyfbChart'>

          </div>
        </div>

        <!-- <chart-box v-on:loadOption="loadMap" apiName="enjrtCompanyArea" :chartBoxClass="overviewLineChartBoxClass"  :apiParams="apiParams" seriesDataProperty="pieChart.seriesData"></chart-box>
                                                           -->
      </div>

    </el-row>
    <!-- end of 用户流向 & 地域分布 -->

    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">访问来源
        <el-tooltip class="item" content="网站访客来自哪里，构成比例。" placement="top-end">
          <span class="jm-tooltip-icon"></span>
        </el-tooltip>
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">

          <chart-box v-on:loadOption="loadfwlyPieChart" :chartBoxClass="VisitorSourceChartBoxClass" apiName="home_getSources" :apiParams="apiParams" seriesDataProperty="pieChart.seriesData"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">

          <chart-box v-on:loadOption="loadfwlyLineChart" :chartBoxClass="VisitorSourceChartBoxClass" apiName="home_getLineSources" :apiParams="lineSourceApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>
        </div>
      </el-col>

    </el-row>

  </div>
</template>
<script>

import {
  baiduTrend,
  home_pageVisit,//页面流量
  home_getSources, //访问来源
  home_enjrtCompanyArea,//区域分布
  en_jrtOverviewLineChart,//趋势分析
  enjrtCompanyArea,// 总览的 区域分布 世界地图
  dataOverviewFlow,
  jytOverview
} from '@/service/api'
import util from '@/service/util'
import DataOverviewFlow from '@/components/common/DataOverviewFlow'
import rangeDatePicker from '@/components/RangeDatePicker3'
import chartBox from '@/components/chartBox'
import echarts from 'echarts';
import 'echarts/map/js/world'

function openInNewTab(url) {
  var a = document.createElement("a");
  a.target = "_blank";
  a.href = url;
  a.click();
}

export default {
  name: 'flowAnalysis',
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
  },
  components: {
    rangeDatePicker,
    DataOverviewFlow,
    chartBox,
  },
  computed: {
    //趋势分析
    en_jrtOverviewLineChartApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate,
        ItemId: this.selectedOverviewLinechartItemId   //?
      }
    },
    apiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate
      }
    },
    lineSourceApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate,
        type: 0,
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
    //趋势分析
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
      vm.loadingDyfbChart = true
      home_enjrtCompanyArea({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingDyfbChart = false
        var chartData = response.data
        vm.loaddyfbChart(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });
    },

    //趋势分析
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
          left: '10%',
          right: '10%',
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
              color: '#158bca'
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

    // 访问来源
    loadfwlyLineChart({ chartData, context, chartId }) {

      var lineChart = chartData.lineChart
      var colors = ["#44cd8a", "#5eb2ec", "#b6a4dd"]
      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '5%',
          containLabel: true
        },
        legend: {
          orient: 'vertical',
          align: 'left',
          data: lineChart.legendData
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
        yAxis: {
          name: '次',
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        },
        series: {
          name: lineChart.title,
          type: 'line',
          smooth: true,
          data: lineChart.seriesData
        }
      };
      context.$initChart(context, chartId, option)
    },

    // 访问来源
    loadfwlyPieChart({ chartData, context, chartId }) {
      var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          top: 'middle',
          right: '5%',
          data: pieChart.legendData
        },
        series: [{
          name: '访问来源',
          type: 'pie',
          center: ['40%', '50%'],
          radius: ['40%', '60%'],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: false,
              position: 'center'
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: '30',
                fontWeight: 'bold'
              }
            }
          },
          labelLine: {
            normal: {
              show: false
            }
          },
          color: ['#44cd8a', '#5eb2ec', '#b6a2dc'],
          data: pieChart.seriesData
        }]
      };
      context.$initChart(context, chartId, option)
    },

    // 地域分布
    loaddyfbChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.dyfbChart = echarts.init(vm.$refs.dyfbChart);


      // 绘制图表
      var option = {
        title: {
          text: '访问量：',
          left: '15px',
          top: '19px',
          textStyle: {
            fontSize: 12,
            fontWeight: 'normal'
          }

        },
        tooltip: {
          trigger: 'item',
          formatter:function (dyfb) {
//              console.log(dyfb)
            var value=isNaN(dyfb.value) ? "--":dyfb.value;
              return dyfb.data.name + "<br>浏览量："+value
          }
        },
        visualMap: {
          orient: 'horizontal',
          inverse: 'true',
          min: 0,
          max: chartData.max || 1000,
          left: '65px',
          top: '15px',
          text: ['高', '低'], // 文本，默认为数值文本
          inRange: {
            color: ['#6ca6df', '#006edd']
          }
        },
        geo: {
          map: 'world',
          roam: true,
          itemStyle: {
            normal: {
              borderColor: 'rgba(0, 0, 0, 0.2)'
            },
            emphasis: {
              areaColor: null,
              shadowOffsetX: 0,
              shadowOffsetY: 0,
              shadowBlur: 10,
              borderWidth: 0,
            }
          }
        },
        series: [{
          name: chartData.pieChart.seriesData,
          type: 'map',
          geoIndex: 0,
          data: chartData.pieChart.seriesData
        }]
      };

      vm.dyfbChart.setOption(option)
    },
    // 页面流量排名
    loadymllpmChart({ chartData, context, chartId }) {
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
        xAxis: {
          name: '次',
          type: 'value',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        },
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
          data: chartData.lineChart.yAxisData.reverse()
        },
        series: [{
          name: chartData.lineChart.title,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#5eb2ed',
              barBorderRadius: 2
            }
          },
          data: chartData.lineChart.seriesData.reverse()
        }]
      };

      context.$initChart(context, chartId, option)
      context[chartId].on('click', function (obj) {
        openInNewTab(obj.name);
      })

    },
    // 页面跳出排名
    loadymtclpmChart({ chartData, context, chartId }) {
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
        xAxis: {
          name: '次',
          type: 'value',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        },
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
          data: chartData.lineChart.yAxisData.reverse()
        },
        series: [{
          name: chartData.lineChart.title,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#44cd8a',
              barBorderRadius: 2
            }
          },
          data: chartData.lineChart.seriesData.reverse()
        }]
      };

      context.$initChart(context, chartId, option)

      context[chartId].on('click', function (obj) {
        openInNewTab(obj.name);
      })
    },

  },
  mounted() {

    var vm = this;

    vm.loadAllData()

    window.addEventListener('resize', chartsResize, false);

    var resizeTimer;

    function chartsResize() {
      if (resizeTimer) {
        clearTimeout(resizeTimer)
      }
      resizeTimer = setTimeout(function () {

        vm.ymllpmChart && vm.ymllpmChart.resize()
        vm.ymtclpmChart && vm.ymtclpmChart.resize()
        vm.dyfbChart && vm.dyfbChart.resize()
        vm.fwlyLineChart && vm.fwlyLineChart.resize()
        vm.fwlyPieChart && vm.fwlyPieChart.resize()
      }, 100);

    }
  },
  data() {
    return {
      loadingIndex: false,
      loadingYmllpmChart: false,
      loadingYmllpmChartText: '加载中...',
      loadingYmtclpmChartText: '加载中...',
      loadingYmtclpmChart: false,
      loadingNewOldVisitor: false,
      loadingDyfbChart: false,
      loadingFwlyLineChart: false,
      loadingYhfwsjfbChart: false,
      indexList: null,
      startDate: new Date().format(),
      endDate: new Date().format(),
      zhibiao_activeTabName: 'pv',
      tableData: [],
      selectedOverviewLinechartItem: "浏览量",
      selectedOverviewLinechartItemId: 0,
      overviewLineChartDisplayItems: [
        { id: 0, name: "浏览量" },
        { id: 1, name: "独立访客" },
        { id: 2, name: "平均停留时间" },
        { id: 3, name: "访问深度" },
        { id: 4, name: "新访客" },
        { id: 5, name: "跳出率" },
        { id: 6, name: "访问次数" }],
      overviewLineChartBoxClass: {
        'overview-line-chart-box-height': true
      },
      VisitorSourceChartBoxClass: {
        'row-group-last': true
      },
      WorldMapChartBoxHeightBoxClass:{
          'world-map-chart-box-height':true
      },
      ymtcChartBoxClass: {
          'ymtc-list-chart-box-height':true
      }

    }
  }
}

</script>

<style lang="scss">
.analy-wrapper {
  .el-tabs__header {
    border-bottom: none;
  }
  .el-tabs--card .el-tabs__nav .el-tabs__item.is-active {
    border-bottom: none;
  }
  .el-tabs__nav-scroll {
    text-align: center;
  }
  .el-tabs__nav {
    display: inline-block;
  }
}

.zhibiao-tooltip {
  width: 300px;
}

.zhibiao-activeTabName {
  position: relative;
  .el-tooltip {
    position: absolute;
    display: block;
    width: 17px;
    height: 17px;
    padding: 0 0 0 5px;
    top: 22px;
    right: 15px;
    margin-top: -8px;
    cursor: pointer;
    background: url(../../assets/images/icon-tooltip.png) no-repeat center;
  }
}

.new-old-visitor *,
.new-old-visitor *:before,
.new-old-visitor *:after {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

.new-old-visitor {
  text-align: center;
  li {
    line-height: 56px;
    list-style: none;
  }
  li+li {
    border-left: 1px solid #d9d9d9;
  }
  .label {
    vertical-align: middle;
    color: #666666;
  }
  .new-percentage {
    vertical-align: middle;
    font-size: 30px;
    color: #23ad44;
  }
  .old-percentage {
    vertical-align: middle;
    font-size: 30px;
    color: #0588e6;
  }
}

.new-old-visitor-detail {
  padding: 0 20px 30px;
}

.bar-trend-chart-tooltip-icon {
  display: inline-block;
  margin-right: 5px;
  border-radius: 10px;
  width: 9px;
  height: 9px;
}

.chart.yhfwsjfb {
  height: 400px;
}

.box.overview-line-chart-box-height {
  height: 400px;
}
.box.world-map-chart-box-height{
  height: 500px;
}
.box.ymtc-list-chart-box-height{
  height: 400px;
}
/*趋势分析*/

.setting-items-button-group {
  text-align: center;
  padding: 20px;
}
</style>
