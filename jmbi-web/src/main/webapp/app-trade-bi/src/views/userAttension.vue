<template>
  <div>
  
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">聚贸通平台</el-breadcrumb-item>
      <el-breadcrumb-item>用户行为</el-breadcrumb-item>
      <el-breadcrumb-item>用户关注</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row :gutter="30" class="table-list ">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-top-1px jm-grid-border-left-1px jm-grid-border-right-1px jm-grid-border-bottom-1px-header-blue jm-margin-left15 jm-margin-right15">用户关注情况
        <el-tooltip class="item" content="网站访客来自哪里，构成比例。" placement="top-end">
          <span class="jm-tooltip-icon"></span>
        </el-tooltip>
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box" v-loading="loadingUserAttensionTable" element-loading-text="加载中...">
            <div class="content">
              <header class="table-header jm-grid-border-left-1px jm-grid-border-right-1px">用户筛选品种排行top10</header>
              <el-table :data="tableData" border style="width: 100%">
  
                <el-table-column prop="name" header-align="center" label="商品品种" width="180">
                </el-table-column>
                <el-table-column prop="address" header-align="center" label="累计筛选量">
                </el-table-column>
              </el-table>
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box " v-loading="loadingUserAttensionTable" element-loading-text="加载中...">
            <div class="content">
              <header class="table-header jm-grid-border-left-1px jm-grid-border-right-1px">用户筛选产地排行top10</header>
              <el-table :data="tableData" border style="width: 100%">
  
                <el-table-column prop="name" header-align="center" label="商品产地" width="180">
                </el-table-column>
                <el-table-column prop="address" header-align="center" label="累计关注量">
                </el-table-column>
              </el-table>
            </div>
          </div>
  
        </div>
      </el-col>
  
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box " v-loading="loadingUserAttensionTable" element-loading-text="加载中...">
            <div class="content">
              <header class="table-header jm-grid-border-left-1px jm-grid-border-right-1px">用户筛选牌号top10</header>
              <el-table :data="tableData" border style="width: 100%">
  
                <el-table-column prop="name" header-align="center" label="商品牌号" width="180">
                </el-table-column>
                <el-table-column prop="address" header-align="center" label="累计关注量">
                </el-table-column>
              </el-table>
            </div>
          </div>
  
        </div>
      </el-col>
      <div class="jm-grid-border-bottom-1px clearfix jm-margin-left15 jm-margin-right15"></div>
    </el-row>
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">搜索词云
            <el-tooltip class="item" content="统计本网站流量次数TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box four-three" v-loading="loadingSearchWordCloudChart" element-loading-text="加载中...">
            <div class="content" ref='searchWordCloudChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">关键词搜索占比TOP10
            <el-tooltip class="item" content="统计本网站页面跳出TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box four-three" v-loading="loadingKeywordsSearchRateChart" element-loading-text="加载中...">
            <div class="content" ref='keywordsSearchRateChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 页面流量排名 & 页面跳出率排名 -->
  
  </div>
</template>
<script>
import {
  baiduTrend,
  getSearchWordCloud,
  //getKeywordsSearchRate,
  getSources,
  pageVisit,
  getAreas
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';

import 'echarts-wordcloud'


function openInNewTab(url) {
  var a = document.createElement("a");
  a.target = "_blank";
  a.href = url;
  a.click();
}

export default {
  name: 'userAttension',
  components: {
    rangeDatePicker
  },
  methods: {
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.loadAllData();
    },
    loadAllData() {
      var vm = this;

      var platform = vm.platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;


      vm.loadingSearchWordCloudChart = true
      getSearchWordCloud().then(function (response) {
        vm.loadingSearchWordCloudChart = false
        var chartData = response.data
        vm.loadSearchWordCloudChart(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingKeywordsSearchRateChart = true
      // todo: 
      pageVisit({
        platform,
        startDate,
        endDate
      }).then(function (response) {

        vm.loadingKeywordsSearchRateChart = false
        var chartData = response.data
        console.log(chartData)
        vm.loadKeywordsSearchRateChart(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });






    },

    // 用户登录情况
    loadfwlyLineChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.fwlyLineChart = echarts.init(vm.$refs.fwlyLineChart);

      var lineChart = chartData.lineChart
      var colors = ["#44cd8a", "#5eb2ec", "#b6a4dd"]
      var series = []
      for (let index = 0; index < lineChart.legendData.length; index++) {
        var _index = index > 2 ? 0 : index;
        series.push({
          name: lineChart.legendData[index],
          type: 'line',
          smooth: true,
          itemStyle: {
            normal: {
              color: colors[_index]
            }
          },
          data: lineChart.seriesData[index]
        })
      }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          top: '2%',
          data: lineChart.legendData
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '5%',
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
        yAxis: {
          name: '次',
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        },
        series: series
      };

      vm.fwlyLineChart.setOption(option)
    },

    // 用户登录情况
    loadfwlyPieChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.fwlyPieChart = echarts.init(vm.$refs.fwlyPieChart);

      var pieChart = chartData.pieChart
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

      vm.fwlyPieChart.setOption(option)
    },
    loadSearchWordCloudChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.searchWordCloudChart = echarts.init(vm.$refs.searchWordCloudChart);
      var keywords = {
        "visualMap": 22199,
        "continuous": 10288,
        "contoller": 620,
        "series": 274470,
        "gauge": 12311,
        "detail": 1206,
        "piecewise": 4885,
        "textStyle": 32294,
        "markPoint": 18574,
        "pie": 38929,
        "roseType": 969,
        "label": 37517,
        "emphasis": 12053,
        "yAxis": 57299,
        "name": 15418,
        "type": 22905,
        "gridIndex": 5146,
        "normal": 49487,
        "itemStyle": 33837,
        "min": 4500,
        "silent": 5744,
        "animation": 4840,
        "offsetCenter": 232,
        "inverse": 3706,
        "borderColor": 4812,
        "markLine": 16578,
        "line": 76970,
        "radiusAxis": 6704,
        "radar": 15964,
        "data": 60679,
        "dataZoom": 24347,
        "tooltip": 43420,
        "toolbox": 25222,
        "geo": 16904,
        "parallelAxis": 4029,
        "parallel": 5319,
        "max": 3393,
        "bar": 43066,
        "heatmap": 3110,
        "map": 20285,
        "animationDuration": 3425,
        "animationDelay": 2431,
        "splitNumber": 5175,
        "axisLine": 12738,
        "lineStyle": 19601,
        "splitLine": 7133,
        "axisTick": 8831,
        "axisLabel": 17516,
        "pointer": 590,
        "color": 23426,
        "title": 38497,
        "formatter": 15214,
        "slider": 7236,
        "legend": 66514,
        "grid": 28516,
        "smooth": 1295,
        "smoothMonotone": 696,
        "sampling": 757,
        "feature": 12815,
        "saveAsImage": 2616,
        "polar": 6279,
        "calculable": 879,
        "backgroundColor": 9419,
        "excludeComponents": 130,
        "show": 20620,
        "text": 2592,
        "icon": 2782,
        "dimension": 478,
        "inRange": 1060,
        "animationEasing": 2983,
        "animationDurationUpdate": 2259,
        "animationDelayUpdate": 2236,
        "animationEasingUpdate": 2213,
        "xAxis": 89459,
        "angleAxis": 5469,
        "showTitle": 484,
        "dataView": 2754,
        "restore": 932,
        "timeline": 10104,
        "range": 477,
        "value": 5732,
        "precision": 878,
        "target": 1433,
        "zlevel": 5361,
        "symbol": 8718,
        "interval": 7964,
        "symbolSize": 5300,
        "showSymbol": 1247,
        "inside": 8913,
        "xAxisIndex": 3843,
        "orient": 4205,
        "boundaryGap": 5073,
        "nameGap": 4896,
        "zoomLock": 571,
        "hoverAnimation": 2307,
        "legendHoverLink": 3553,
        "stack": 2907,
        "throttle": 466,
        "connectNulls": 897,
        "clipOverflow": 826,
        "startValue": 551,
        "minInterval": 3292,
        "opacity": 3097,
        "splitArea": 4775,
        "filterMode": 635,
        "end": 409,
        "left": 6475,
        "funnel": 2238,
        "lines": 6403,
        "baseline": 431,
        "align": 2608,
        "coord": 897,
        "nameTextStyle": 7477,
        "width": 4338,
        "shadowBlur": 4493,
        "effect": 929,
        "period": 225,
        "areaColor": 631,
        "borderWidth": 3654,
        "nameLocation": 4418,
        "position": 11723,
        "containLabel": 1701,
        "scatter": 10718,
        "areaStyle": 5310,
        "scale": 3859,
        "pieces": 414,
        "categories": 1000,
        "selectedMode": 3825,
        "itemSymbol": 273,
        "effectScatter": 7147,
        "fontStyle": 3376,
        "fontSize": 3386,
        "margin": 1034,
        "iconStyle": 2257,
        "link": 1366,
        "axisPointer": 5245,
        "showDelay": 896,
        "graph": 22194,
        "subtext": 1442,
        "selected": 2881,
        "barCategoryGap": 827,
        "barGap": 1094,
        "barWidth": 1521,
        "coordinateSystem": 3622,
        "barBorderRadius": 284,
        "z": 4014,
        "polarIndex": 1456,
        "shadowOffsetX": 3046,
        "shadowColor": 3771,
        "shadowOffsetY": 2475,
        "height": 1988,
        "barMinHeight": 575,
        "lang": 131,
        "symbolRotate": 2752,
        "symbolOffset": 2549,
        "showAllSymbol": 942,
        "transitionDuration": 993,
        "bottom": 3724,
        "fillerColor": 229,
        "nameMap": 1249,
        "barMaxWidth": 747,
        "radius": 2103,
        "center": 2425,
        "magicType": 3276,
        "labelPrecision": 248,
        "option": 654,
        "seriesIndex": 935,
        "controlPosition": 121,
        "itemGap": 3188,
        "padding": 3481,
        "shadowStyle": 347,
        "boxplot": 1394,
        "labelFormatter": 264,
        "realtime": 631,
        "dataBackgroundColor": 239,
        "showDetail": 247,
        "showDataShadow": 217,
        "x": 684,
        "valueDim": 499,
        "onZero": 931,
        "right": 3255,
        "clockwise": 1035,
        "itemWidth": 1732,
        "trigger": 3840,
        "axis": 379,
        "selectedOffset": 670,
        "startAngle": 1293,
        "minAngle": 590,
        "top": 4637,
        "avoidLabelOverlap": 870,
        "labelLine": 3785,
        "sankey": 2933,
        "endAngle": 213,
        "start": 779,
        "roam": 1738,
        "fontWeight": 2828,
        "fontFamily": 2490,
        "subtextStyle": 2066,
        "indicator": 853,
        "sublink": 708,
        "zoom": 1038,
        "subtarget": 659,
        "length": 1060,
        "itemSize": 505,
        "controlStyle": 452,
        "yAxisIndex": 2529,
        "edgeLabel": 1188,
        "radiusAxisIndex": 354,
        "scaleLimit": 1313,
        "geoIndex": 535,
        "regions": 1892,
        "itemHeight": 1290,
        "nodes": 644,
        "candlestick": 3166,
        "crossStyle": 466,
        "edges": 369,
        "links": 3277,
        "layout": 846,
        "barBorderColor": 721,
        "barBorderWidth": 498,
        "treemap": 3865,
        "y": 367,
        "valueIndex": 704,
        "showLegendSymbol": 482,
        "mapValueCalculation": 492,
        "optionToContent": 264,
        "handleColor": 187,
        "handleSize": 271,
        "showContent": 1853,
        "angleAxisIndex": 406,
        "endValue": 327,
        "triggerOn": 1720,
        "contentToOption": 169,
        "buttonColor": 71,
        "rotate": 1144,
        "hoverLink": 335,
        "outOfRange": 491,
        "textareaColor": 58,
        "textareaBorderColor": 58,
        "textColor": 60,
        "buttonTextColor": 66,
        "category": 336,
        "hideDelay": 786,
        "alwaysShowContent": 1267,
        "extraCssText": 901,
        "effectType": 277,
        "force": 1820,
        "rippleEffect": 723,
        "edgeSymbolSize": 329,
        "showEffectOn": 271,
        "gravity": 199,
        "edgeLength": 193,
        "layoutAnimation": 152,
        "length2": 169,
        "enterable": 957,
        "dim": 83,
        "readOnly": 143,
        "levels": 444,
        "textGap": 256,
        "pixelRatio": 84,
        "nodeScaleRatio": 232,
        "draggable": 249,
        "brushType": 158,
        "radarIndex": 152,
        "large": 182,
        "edgeSymbol": 675,
        "largeThreshold": 132,
        "leafDepth": 73,
        "childrenVisibleMin": 73,
        "minSize": 35,
        "maxSize": 35,
        "sort": 90,
        "funnelAlign": 61,
        "source": 336,
        "nodeClick": 200,
        "curveness": 350,
        "areaSelectStyle": 104,
        "parallelIndex": 52,
        "initLayout": 359,
        "trailLength": 116,
        "boxWidth": 20,
        "back": 53,
        "rewind": 110,
        "zoomToNodeRatio": 80,
        "squareRatio": 60,
        "parallelAxisDefault": 358,
        "checkpointStyle": 440,
        "nodeWidth": 49,
        "color0": 62,
        "layoutIterations": 56,
        "nodeGap": 54,
        "color(Array": 76,
        "<string>)": 76,
        "repulsion": 276,
        "tiled": 105,
        "currentIndex": 145,
        "axisType": 227,
        "loop": 97,
        "playInterval": 112,
        "borderColor0": 23,
        "gap": 43,
        "autoPlay": 123,
        "showPlayBtn": 25,
        "breadcrumb": 119,
        "colorMappingBy": 85,
        "id": 18,
        "blurSize": 85,
        "minOpacity": 50,
        "maxOpacity": 54,
        "prevIcon": 12,
        "children": 21,
        "shape": 98,
        "nextIcon": 12,
        "showNextBtn": 17,
        "stopIcon": 21,
        "visibleMin": 83,
        "visualDimension": 97,
        "colorSaturation": 56,
        "colorAlpha": 66,
        "emptyItemWidth": 10,
        "inactiveOpacity": 4,
        "activeOpacity": 4,
        "showPrevBtn": 19,
        "playIcon": 26,
        "ellipsis": 19,
        "gapWidth": 19,
        "borderColorSaturation": 10,
        "handleIcon": 2,
        "handleStyle": 6,
        "borderType": 1,
        "constantSpeed": 1,
        "polyline": 2,
        "blendMode": 1,
        "dataBackground": 1,
        "textAlign": 1,
        "textBaseline": 1,
        "brush": 3
      };
      var data = [];
      for (var name in keywords) {
        data.push({
          name: name,
          value: Math.sqrt(keywords[name])
        })
      }

      var option = {
        tooltip: {
          formatter: '{b}'
        },
        series: [{
          type: 'wordCloud',
          // sizeRange: [10, 100],
          // rotationRange: [-90, 90],
          // rotationStep: 45,
          // gridSize: 2,
          // shape: 'circle',
          gridSize: 15,
          sizeRange: [10, 100],
          rotationRange: [0, 0],
          // rotationStep: 45,
          shape: 'circle',
          width: '80%',
          height: '80%',
          textStyle: {
            normal: {
              color: function () {
                return 'rgb(' + [
                  Math.round(Math.random() * 160),
                  Math.round(Math.random() * 160),
                  Math.round(Math.random() * 160)
                ].join(',') + ')';
              }
            }
          },
          data: data.sort(function (a, b) {
            return b.value - a.value;
          })
        }]
      };


      vm.searchWordCloudChart.setOption(option)

      // vm.searchWordCloudChart.on('click', function (obj) {
      //   openInNewTab(obj.name);
      // })

    },
    // 关键词搜索占比TOP10
    loadKeywordsSearchRateChart(chartData) {
      var vm = this
      // 基于准备好的dom，初始化echarts实例
      vm.keywordsSearchRateChart = echarts.init(vm.$refs.keywordsSearchRateChart);

      var existCountChart = chartData.existCountChart

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
              color: '#008acd'
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
              color: '#008acd'
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
          data: existCountChart.yAxisData.reverse()
        },
        series: [{
          name: existCountChart.seriesName,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#1790cf',
              barBorderRadius: 2
            }
          },
          data: existCountChart.seriesData.reverse()
        }]
      };

      vm.keywordsSearchRateChart.setOption(option)

      vm.keywordsSearchRateChart.on('click', function (obj) {
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
        vm.searchWordCloudChart && vm.searchWordCloudChart.resize()
        vm.keywordsSearchRateChart && vm.keywordsSearchRateChart.resize()

      }, 100);

    }
  },
  data() {
    return {
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],

      loadingSearchWordCloudChart: false,
      loadingKeywordsSearchRateChart: false,

      loadingUserAttensionTable: false,
      platformId: '102000',
      startDate: new Date().format(),
      endDate: new Date().format()
    }
  }
}

</script>

<style lang="scss">
.table-list .el-table::before {
  height: 0;
}

.table-header {
  height: 42px;
  line-height: 42px;
  text-align: center;
  background-color: #e5f4ff;
}

.el-table th>.cell {
  font-size: 14px;
  color: #333333;
  font-weight: normal;
}
</style>
