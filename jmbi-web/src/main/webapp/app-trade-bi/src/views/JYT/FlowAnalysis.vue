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
  
    <el-row>
      <el-col :span="24">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px zhibiao-activeTabName">
          <el-tabs v-model="zhibiao_activeTabName" type="card" @tab-click="handle_zhibiao_Click" v-loading="loadingIndex" element-loading-text="加载中...">
            <el-tab-pane label="浏览量" name="pv">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='pvChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="独立访客" name="uv">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='uvChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="IP数" name="ip">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='ipChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="平均停留时长" name="averageTime">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='averageTimeChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="平均访问页数" name="avgVisitPages">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='avgVisitPagesChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="跳出率" name="exitRate">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='exitRateChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
          </el-tabs>
  
          <el-tooltip class="item" popper-class="zhibiao-tooltip" content="通过网站统计，可以了解用户在网站的活动情况。可以详细指导用户数量、次数、访问质量如何。通过时间日历控件，查看选定时间段内，用户流量的趋势，从而对运营起到辅助作用。" placement="top-end">
            <span class="jm-tooltip-icon"></span>
          </el-tooltip>
        </div>
      </el-col>
  
    </el-row>
    <!-- end of zhibiao-->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">页面流量排名
            <el-tooltip class="item" content="统计本网站流量次数TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingYmllpmChartText === '暂无数据'}" v-loading="loadingYmllpmChart" :element-loading-text="loadingYmllpmChartText">
            <div class="content" ref='ymllpmChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">页面跳出排名
            <el-tooltip class="item" content="统计本网站页面跳出TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingYmtclpmChartText === '暂无数据'}" v-loading="loadingYmtclpmChart" :element-loading-text="loadingYmtclpmChartText">
            <div class="content" ref='ymtclpmChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 页面流量排名 & 页面跳出率排名 -->
  
    <!--<el-row>
                                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                                  <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
                                                                    <header class="panel-header jm-grid-border-bottom-1px">用户访问时间分布
                                                                      <el-tooltip class="item" content="统计本网站流量次数TOP5页面。" placement="top-end">
                                                                        <span class="jm-tooltip-icon"></span>
                                                                      </el-tooltip>
                                                                    </header>
                                                            
                                                                    <div class="box chart sixteen-nine yhfwsjfb" v-loading="loadingYhfwsjfbChart" element-loading-text="加载中...">
                                                                      <div class="content" ref='yhfwsjfbChart'>
                                                            
                                                                      </div>
                                                                    </div>
                                                                  </div>
                                                                </el-col>
                                                            
                                                              </el-row>-->
    <!-- end of 用户访问时间分布 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">新老访客
            <el-tooltip class="item" content="根据选定时间段内，老访客、新访客比例统计网站访客情况。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine">
            <div class="content">
              <section class="new-old-visitor" v-loading="loadingNewOldVisitor" element-loading-text="加载中...">
                <ul class="clearfix">
                  <li class="left jm-width-50-p">
                    <span class="lable">新访客：</span>
                    <span class="new-percentage">{{tableData.newPercent}}</span>
                  </li>
                  <li class="left jm-width-50-p">
                    <span class="lable">老访客：</span>
                    <span class="old-percentage">{{tableData.oldPercent}}</span>
                  </li>
                </ul>
  
                <div class="new-old-visitor-detail">
  
                  <el-table :data="tableData.tableData" :show-header="false" border style="width: 100%">
                    <el-table-column prop="name" label="type">
                    </el-table-column>
                    <el-table-column prop="newNumber" label="new">
                    </el-table-column>
                    <el-table-column prop="oldNumber" label="old">
                    </el-table-column>
                  </el-table>
                </div>
  
              </section>
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">地域分布
            <el-tooltip class="item" content="按照访问量，统计访客地域分布，深色到浅色代表访客量由多到少。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine" v-loading="loadingDyfbChart" element-loading-text="加载中...">
            <div class="content" ref='dyfbChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 用户流向 & 地域分布 -->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">访问来源
        <el-tooltip class="item" content="网站访客来自哪里，构成比例。" placement="top-end">
          <span class="jm-tooltip-icon"></span>
        </el-tooltip>
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" v-loading="loadingFwlyLineChart" element-loading-text="加载中...">
            <div class="content" ref='fwlyLineChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box  chart row-group-last" v-loading="loadingFwlyLineChart" element-loading-text="加载中...">
            <div class="content" ref='fwlyPieChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
  
  </div>
</template>
<script>

import {
  baiduTrend,
  pageVisit,
  getSources,
  getVistors,
  getAreas
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker3'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

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
      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;

      vm.loadTabContentChart(vm.zhibiao_activeTabName)

      vm.loadingYmllpmChart = true
      vm.loadingYmtclpmChart = true
      vm.loadingYmllpmChartText = '加载中...'
      vm.loadingYmtclpmChartText = '加载中...'
      pageVisit({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingYmllpmChart = false
        vm.loadingYmtclpmChart = false
        var chartData = response.data

        if (chartData.pvChart.seriesData.length === 0) {
          vm.loadingYmllpmChart = true
          vm.loadingYmllpmChartText = '暂无数据'
        }
        if (chartData.existCountChart.seriesData.length === 0) {
          vm.loadingYmtclpmChart = true
          vm.loadingYmtclpmChartText = '暂无数据'
        }

        vm.loadymllpmChart(chartData)
        vm.loadymtclpmChart(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingNewOldVisitor = true
      getVistors({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingNewOldVisitor = false
        var tableData = response.data
        vm.tableData = tableData
      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingDyfbChart = true
      getAreas({
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

      vm.loadingFwlyLineChart = true
      getSources({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingFwlyLineChart = false
        var chartData = response.data
        vm.loadfwlyLineChart(chartData)
        vm.loadfwlyPieChart(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });
    },
    // 访问来源
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

    // 访问来源
    loadfwlyPieChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.fwlyPieChart = echarts.init(vm.$refs.fwlyPieChart);

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

      vm.fwlyPieChart.setOption(option)
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
          trigger: 'item'
        },
        visualMap: {
          orient: 'horizontal',
          inverse: 'true',
          min: 0,
          max: chartData.max,
          left: '65px',
          top: '15px',
          text: ['高', '低'], // 文本，默认为数值文本
          inRange: {
            color: ['#e0ffff', '#006edd']
          }
        },
        geo: {
          map: 'china',
          roam: true,
          label: {
            normal: {
              show: true,
              textStyle: {
                color: 'rgba(0,0,0,0.4)'
              }
            }
          },
          itemStyle: {
            normal: {
              borderColor: 'rgba(0, 0, 0, 0.2)'
            },
            emphasis: {
              areaColor: null,
              shadowOffsetX: 0,
              shadowOffsetY: 0,
              shadowBlur: 20,
              borderWidth: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        },
        series: [{
          name: '地域分布',
          type: 'map',
          geoIndex: 0,
          data: chartData.data
        }]
      };

      vm.dyfbChart.setOption(option)
    },
    // 页面流量排名
    loadymllpmChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.ymllpmChart = echarts.init(vm.$refs.ymllpmChart);

      var pvChartData = chartData.pvChart

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
          data: pvChartData.yAxisData.reverse()
        },
        series: [{
          name: pvChartData.seriesName,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#5eb2ed',
              barBorderRadius: 2
            }
          },
          data: pvChartData.seriesData.reverse()
        }]
      };

      vm.ymllpmChart.setOption(option)

      vm.ymllpmChart.on('click', function (obj) {
        openInNewTab(obj.name);
      })

    },
    // 页面跳出排名
    loadymtclpmChart(chartData) {
      var vm = this
      // 基于准备好的dom，初始化echarts实例
      vm.ymtclpmChart = echarts.init(vm.$refs.ymtclpmChart);

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
          data: existCountChart.yAxisData.reverse()
        },
        series: [{
          name: existCountChart.seriesName,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#44cd8a',
              barBorderRadius: 2
            }
          },
          data: existCountChart.seriesData.reverse()
        }]
      };

      vm.ymtclpmChart.setOption(option)

      vm.ymtclpmChart.on('click', function (obj) {
        openInNewTab(obj.name);
      })
    },

    handle_zhibiao_Click(tab, event) {
      var vm = this;

      var id = tab.name;

      vm.$nextTick(function () {
        // DOM 现在更新了
        // `this` 绑定到当前实例
        vm.loadTabContentChart(id, false)
      })

    },

    loadTabContentChart(id, reload = true) {
      var vm = this;
      var chartId = id + 'Chart';
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var platform = vm.$platformId;

      var startDate = vm.startDate,
        endDate = vm.endDate;

      if (reload || vm.indexList === null) {
        vm.loadingIndex = true
        baiduTrend({
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

        var loadingChartData = chartData[chartId]

        console.log(chartId)

        let yAxis = {
          name: loadingChartData.unit,
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        };

        if (chartId !== 'averageTimeChart' && chartId !== 'exitRateChart') {
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
          legend: {
            data: [loadingChartData.seriesName]
          },
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
      }
    }

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
        var tabContentChart = `${vm.zhibiao_activeTabName}Chart`
        if (vm[tabContentChart]) {
          vm[tabContentChart].resize()
        }

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
      tableData: []
    }
  }
}

</script>

<style lang="scss">
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
</style>
