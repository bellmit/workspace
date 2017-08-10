<template>
  <div>
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>注册分析</el-breadcrumb-item>
      <el-breadcrumb-item>运力分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white map">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">运力图
          </header>
  
          <div class="auth-user-botton-group">
  
            <el-radio-group v-model="selectedLinechartItem" @change="selectedLinechartItemChanged">
              <el-radio-button :label="item.name" v-for="item in ButtonLinechartItems" :key="item"></el-radio-button>
            </el-radio-group>
  
          </div>
  
          <chart-box v-on:loadOption="loadMap" :chartBoxClass="CapacityMapChartBoxClass" apiName="jytTrafficway" :apiParams="trafficApiParams" seriesDataProperty="proportion"></chart-box>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 新增注册数-->
  
    <el-row class="jm-grid-border-1px master-detail-view">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">运力排行
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <chart-box v-on:loadOption="loadnewAddedUserBarChart" apiName="jytTransportTop" :apiParams="apiParams" seriesDataProperty="seriesData"></chart-box>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <chart-box v-on:loadOption="loadnewAddedUserPieChart" apiName="jytCarAnalysis" :apiParams="apiParams" seriesDataProperty="proportion"></chart-box>
  
        </div>
      </el-col>
    </el-row>
  
  </div>
</template>

<script>

import {
  newlyIncreased,
  mainNewlyIncreasedPie
} from '@/service/api'
import util from '@/service/util'

import mapGeo from '@/data/indexGeo'

import rangeDatePicker from '@/components/RangeDatePicker'
import chartBox from '@/components/chartBox'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/world'
import 'echarts/map/js/china'


export default {
  name: 'capacity',
  components: {
    rangeDatePicker,
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
    apiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate
      }
    },
    trafficApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate,
        transport: this.selectedLinechartItemId
      }
    }
  },
  methods: {
    selectedLinechartItemChanged(value) {
      let vm = this;
      let selectedLinechartItemId = vm.ButtonLinechartItems.find(x => x.name === value).id

      if (selectedLinechartItemId === "OceanShipping" || selectedLinechartItemId === "AirTransport") {
        vm.mapType = "world"
      } else {
        vm.mapType = "china"
      }

      vm.selectedLinechartItemId = selectedLinechartItemId

    },
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.loadAllData();
    },
    loadAllData() {
      var vm = this;
    },

    loadMap({ chartData, context, chartId }) {
      var vm = this;

      var geoCoordMap = mapGeo


      var BJData = chartData.proportion



      var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';


      var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
          var dataItem = data[i];
          var fromCoord = geoCoordMap[dataItem[0].name];
          var toCoord = geoCoordMap[dataItem[1].name];
          if (fromCoord && toCoord) {
            res.push({
              fromName: dataItem[0].name,
              toName: dataItem[1].name,
              coords: [fromCoord, toCoord],
              data: dataItem
            });
          }
        }
        return res;
      };

      var color = ['#0588e6', '#ffa022', '#46bee9'];
      var series = [];
      [['运力图', BJData]].forEach(function (item, i) {
        console.log('item', item)
        series.push({
          name: vm.selectedLinechartItem,
          type: 'lines',
          zlevel: 1,
          effect: {
            show: true,
            period: 6,
            trailLength: 0.7,
            color: '#fff',
            symbolSize: 3
          },
          lineStyle: {
            normal: {
              color: color[i],
              width: 0,
              curveness: 0.2
            }
          },
          data: convertData(item[1])
        },
          {
            name: vm.selectedLinechartItem,
            type: 'lines',
            zlevel: 2,
            symbol: ['none', 'arrow'],
            symbolSize: 10,
            effect: {
              show: true,
              period: 6,
              trailLength: 0,
              //symbol: planePath,
              symbolSize: 5
            },
            lineStyle: {
              normal: {
                color: color[i],
                width: 1,
                opacity: 0.6,
                curveness: 0.2
              }
            },
            data: convertData(item[1])
          },
          {
            name: vm.selectedLinechartItem,
            type: 'effectScatter',
            coordinateSystem: 'geo',
            zlevel: 2,
            rippleEffect: {
              brushType: 'stroke'
            },
            label: {
              normal: {
                show: true,
                position: 'right',
                formatter: '{b}'
              }
            },
            symbolSize: function (val) {
              //console.log('val', val)
              let v = val[2] || 0;
              let result = getSymbolSize(v)
              return result;
            },
            itemStyle: {
              normal: {
                color: color[i]
              }
            },
            data: item[1].map(function (dataItem) {
              let geo = geoCoordMap[dataItem[1].name] || []
              // console.log('geo', dataItem[1].name)
              // console.log('geo', geo)
              return {
                name: dataItem[1].name,
                // value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
                value: geo.concat([dataItem[1].value])
              };
            })
          }, {
            name: vm.selectedLinechartItem,
            type: 'effectScatter',
            coordinateSystem: 'geo',
            zlevel: 2,
            rippleEffect: {
              brushType: 'stroke'
            },
            label: {
              normal: {
                show: true,
                position: 'right',
                formatter: '{b}'
              }
            },
            symbolSize: function (val) {
              // console.log('val', val)
              let v = val[2] || 0;
              let result = getSymbolSize(v)
              return result;
            },
            itemStyle: {
              normal: {
                color: color[i]
              }
            },
            data: item[1].map(function (dataItem) {
              let geo = geoCoordMap[dataItem[0].name] || []
              return {
                name: dataItem[0].name,
                // value: geoCoordMap[dataItem[0].name].concat([dataItem[1].value])
                value: geo.concat([dataItem[1].value])
              };
            })
          });
      });

      function getSymbolSize(val) {
        if (val > 100) {
          val = 100
        }
        if (val < 5) {
          val = 5
        }
        return val / 8
      }

      var option = {

        tooltip: {
          trigger: 'item',
          formatter: function (obj) {

            if (obj.data.data && obj.data.data.length) {
              return `${obj.data.fromName} -- ${obj.data.toName}: ${obj.data.data[1].value}次`
            }

          }
        },
        geo: {
          map: vm.mapType,
          label: {
            emphasis: {
              show: false
            }
          },
          roam: true,
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
        series: series
      };
      context.$initChart(context, chartId, option)
    },

    loadnewAddedUserBarChart({ chartData, context, chartId }) {
      var vm = this;

      //var barChart = chartData.lineChart

      let xAxis = {
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#158bca'
          }
        }
      };

      // 从0开始，最小间隔为整数。
      let maxVal = Math.max(...chartData.xAxisData)
      if (maxVal < 5 && maxVal > 0) {
        xAxis.splitNumber = maxVal
        xAxis.minInterval = 1
      } else {
        xAxis.splitNumber = 5
        xAxis.minInterval = 0
      }

      // 绘制图表
      var option = {
        title: {
          text: "运力排行",
          left: 'center',
          top: '5%',
          textStyle: {
            color: '#666',
            fontWeight: 'normal',
            fontSize: 14
          }
        },
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
          top: '15%',
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
          data: chartData.seriesData.reverse()
        },
        series: [{
          name: `运力排行`,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#5eb2ed',
              barBorderRadius: 2
            }
          },
          data: chartData.xAxisData.reverse()
        }]
      };

      context.$initChart(context, chartId, option)
    },

    // 新增注册数
    loadnewAddedUserPieChart({ chartData, context, chartId }) {
      var vm = this;

      var seriesData = chartData.proportion;
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff', '#13ce66', '#f7ba2a'],
        title: {
          text: "车辆分析",
          left: 'center',
          top: '5%',
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
          name: "车辆分析",
          type: 'pie',
          radius: ['30%', '60%'],
          center: ['50%', '55%'],
          data: seriesData,

          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      context.$initChart(context, chartId, option)
    },

  },
  mounted() {
    var vm = this;

    vm.loadAllData();

  },
  data() {
    return {
      ButtonLinechartItems: [
        { id: "Highway", name: "公路" },
        { id: "Railway", name: "铁路" },
        { id: "InlandNavigation", name: "内河航运" },
        { id: "OceanShipping", name: "海运" },
        { id: "AirTransport", name: "空运" },
        { id: "Storage", name: "仓储" }
      ],
      selectedLinechartItem: '公路',
      selectedLinechartItemId: "Highway",
      mapType: 'china',
      startDate: new Date().format(),
      endDate: new Date().format(),
      newAddedUsersLineChartBoxClass: {
        'row-group-last': true
      },
      CapacityMapChartBoxClass: {
        'Capacity-MapChart-BoxClass': true
      }
    }
  }
}


</script>

<style>
.box.Capacity-MapChart-BoxClass {
  height: 600px;
}

.auth-user-botton-group {
  background: #fff;
  padding: 20px 65px;
}



.register-panel-title {
  font-size: 20px;
  padding-top: 15px;
}

.text-right {
  text-align: right;
}

.margin-right-20 {
  margin-right: 20px;
}
</style>
