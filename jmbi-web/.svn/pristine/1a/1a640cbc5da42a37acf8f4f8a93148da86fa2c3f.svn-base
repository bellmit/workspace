<template>
  <div class="shipping-page">
  
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>成交分析</el-breadcrumb-item>
      <el-breadcrumb-item>物流方式分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white map">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">物流货运流向图
          </header>
  
          <div class="auth-user-botton-group">
  
            <el-radio-group v-model="selectedLinechartItem" @change="selectedLinechartItemChanged">
              <el-radio-button :label="item.name" v-for="item in ButtonLinechartItems" :key="item"></el-radio-button>
            </el-radio-group>
  
          </div>
  
          <chart-box v-on:loadOption="loadMap" :chartBoxClass="CapacityMapChartBoxClass" apiName="jytFreightFlow" :apiParams="trafficApiParams" seriesDataProperty="flowChart.seriesData"></chart-box>
        </div>
      </el-col>
  
    </el-row>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">物流方式
  
      </header>
  
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white jm-padding-20X20">
  
          <logistics-panel subTitle="物流货运Top-人民币" apiName="jytlgTypeByRMBTop" :apiParams="apiParams" descendantsProperty="tableChart.seriesData" DataProcessServiceName="ShippingService"></logistics-panel>
  
        </div>
      </el-col>
    </el-row>
  
    <!--<el-row class="jm-grid-border-1px">
                                                      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">仓储分析
                                                  
                                                      </header>
                                                  
                                                      <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                        <div class="grid-content jm-grid-box-bg-white jm-padding-20X20">
                                                  
                                                          <logistics-panel subTitle="仓储Top-人民币" apiName="jytProvidersWarehousingByRMBTop" :apiParams="apiParams" descendantsProperty="tableChart.seriesData" DataProcessServiceName="WarehousingService"></logistics-panel>
                                                        </div>
                                                      </el-col>
                                                    </el-row>-->
  
  </div>
</template>
<script>

import util from '@/service/util'

import mapGeo from '@/data/indexGeo'

import rangeDatePicker from '@/components/RangeDatePicker'
import logisticsPanel from '@/components/JYT/logisticsPanel'
import chartBox from '@/components/chartBox'

import echarts from 'echarts';
import 'echarts/map/js/world'
import 'echarts/map/js/china'

export default {
  name: 'shipping',
  components: {
    rangeDatePicker,
    logisticsPanel,
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
        endDate: this.endDate,
      }
    },
    trafficApiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate,
        way: this.selectedLinechartItemId
      }
    }
  },
  methods: {
    selectedLinechartItemChanged(value) {
      let vm = this;
      let selectedLinechartItemId = vm.ButtonLinechartItems.find(x => x.name === value).id

      if (selectedLinechartItemId === "234" || selectedLinechartItemId === "237") {
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



      var BJData = chartData.flowChart.seriesData.map(x => {
        return [
          { "name": x.departure_area },
          { "name": x.destination_area, "value": x.total_money }
        ]
      })



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

      let symbolSize = 5;

      var color = ['#0588e6', '#ffa022', '#46bee9'];
      var series = [];
      [['mapLines', BJData]].forEach(function (item, i) {
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
              return `${obj.data.fromName} -- ${obj.data.toName}: ${obj.data.data[1].value}万元`
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
    }


  },
  mounted() {

    var vm = this;

    vm.loadAllData()


  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      ButtonLinechartItems: [
        { id: "231", name: "公路" },
        { id: "232", name: "铁路" },
        { id: "233", name: "内河航运" },
        { id: "234", name: "海运" },
        { id: "237", name: "空运" },
        { id: "235", name: "仓库" }
      ],
      selectedLinechartItem: '公路',
      selectedLinechartItemId: "231",
      mapType: 'china',
      CapacityMapChartBoxClass: {
        'Capacity-MapChart-BoxClass': true
      }
    }
  }
}

</script>

<style lang="scss">
.shipping-page {
  padding-bottom: 50px;
  margin-bottom: 50px;
}

.box.Capacity-MapChart-BoxClass {
  height: 600px;
}

.auth-user-botton-group {
  background: #fff;
  padding: 20px 65px;
}
</style>
