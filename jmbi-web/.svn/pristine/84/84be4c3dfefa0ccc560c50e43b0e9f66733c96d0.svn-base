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
      <el-breadcrumb-item>仓储分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px master-detail-view">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">仓储发布图
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white jm-padding-20X20">
          <div class="warehouse-map-container map">
            <main>
              <chart-box v-on:loadOption="loadChinaMapChart" apiName="jytStorageHeat" :apiParams="apiParams" seriesDataProperty="proportion"></chart-box>
            </main>
            <aside class="warehouse-summary">
              <div>
                <div class="title">仓库总数(个)</div>
                <div class="number">{{storageAreaData.houseAmount}}</div>
              </div>
              <div>
                <div class="title">可用面积(m²)</div>
                <div class="number">{{storageAreaData.availabelArea}}</div>
              </div>
              <div>
                <div class="title">总面积(m²)</div>
                <div class="number">{{storageAreaData.totalArea}}</div>
              </div>
            </aside>
          </div>
  
        </div>
      </el-col>
  
    </el-row>
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">仓库面积TOP5
          </header>
  
          <chart-box v-on:loadOption="loadnewAddedUserBarChart" apiName="jytStorageAreaTop" :apiParams="apiParams" seriesDataProperty="seriesData"></chart-box>
  
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">仓库类型（面积）TOP5
          </header>
  
          <chart-box v-on:loadOption="loadnewAddedUserPieChart" apiName="jytStorageTypeTop" :apiParams="apiParams" seriesDataProperty="proportion"></chart-box>
  
        </div>
      </el-col>
    </el-row>
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">企业排名TOP5
          </header>
  
          <chart-box v-on:loadOption="loadnewAddedUserBarChart" apiName="jytStorageCompanyTop" :apiParams="apiParams" seriesDataProperty="seriesData"></chart-box>
  
        </div>
      </el-col>
  
    </el-row>
  
  </div>
</template>

<script>

import {
  jytStorageArea
} from '@/service/api'

import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'
import chartBox from '@/components/chartBox'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'warehouse',
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
    }
  },
  methods: {
    selectedLinechartItemChanged(value) {
      let vm = this;
      vm.selectedLinechartItemId = vm.ButtonLinechartItems.find(x => x.name === value).id
      // vm.loadauthUserOverviewChart(vm.payChartData)
    },
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.loadAllData();
    },
    loadAllData() {
      var vm = this;

      vm.storageAreaData = {
        availabelArea: 0,
        totalArea: 0,
        houseAmount: 0
      }
      jytStorageArea(this.apiParams).then(function (response) {

        var data = response.data.proportion
        if (data && data.length) {
          vm.storageAreaData = {
            availabelArea: data[0]["可用面积"],
            totalArea: data[0]["总面积"],
            houseAmount: data[0]["仓库总数"]
          }
        }

      })
        .catch(function (error) {
          console.log(error);
        });
    },


    loadnewAddedUserBarChart({ chartData, context, chartId }) {
      var vm = this;

      //var barChart = chartData.lineChart

      let xAxis = {
        name: chartData.unit,
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
        // title: {
        //   text: "仓库面积",
        //   left: 'center',
        //   top: '5%',
        //   textStyle: {
        //     color: '#666',
        //     fontWeight: 'normal',
        //     fontSize: 14
        //   }
        // },
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
          right: '8%',
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
          name: chartData.seriesName,
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

    loadnewAddedUserPieChart({ chartData, context, chartId }) {
      var vm = this;

      // var seriesData = chartData.proportion.map(x => {
      //   return {
      //     name: Object.values(x)[0],
      //     value: Object.keys(x)[0]
      //   }
      // })
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        // title: {
        //   text: "仓库类型（面积）",
        //   left: 'center',
        //   top: '5%',
        //   textStyle: {
        //     color: '#666',
        //     fontWeight: 'normal',
        //     fontSize: 14
        //   }
        // },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
          name: "仓库类型（面积）",
          type: 'pie',
          radius: ['30%', '60%'],
          center: ['50%', '55%'],
          data: chartData.proportion,

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
    loadChinaMapChart({ chartData, context, chartId }) {
      var vm = this;

      var geoCoordMap = {
        // 省会经纬度
        '甘肃': [103.73, 36.03],
        '青海': [101.74, 36.56],
        '四川': [104.06, 30.67],
        '河北': [114.48, 38.03],
        '云南': [102.73, 25.04],
        '贵州': [106.71, 26.57],
        '湖北': [114.31, 30.52],
        '河南': [113.65, 34.76],
        '山东': [117, 36.65],
        '江苏': [118.78, 32.04],
        '安徽': [117.27, 31.86],
        '浙江': [120.19, 30.26],
        '江西': [115.89, 28.68],
        '福建': [119.3, 26.08],
        '广东': [113.23, 23.16],
        '湖南': [113, 28.21],
        '海南': [110.35, 20.02],
        '辽宁': [123.38, 41.8],
        '吉林': [125.35, 43.88],
        '黑龙江': [126.63, 45.75],
        '山西': [112.53, 37.87],
        '陕西': [108.95, 34.27],
        '台湾': [121.30, 25.03],
        '北京': [116.46, 39.92],
        '上海': [121.48, 31.22],
        '重庆': [106.54, 29.59],
        '天津': [117.2, 39.13],
        '内蒙古': [111.65, 40.82],
        '广西': [108.33, 22.84],
        '西藏': [91.11, 29.97],
        '宁夏': [106.27, 38.47],
        '新疆': [87.68, 43.77],
        '香港': [114.17, 22.28],
        '澳门': [113.54, 22.19],
        // 市级经纬度
        '海门': [121.15, 31.89],
        '鄂尔多斯': [109.781327, 39.608266],
        '招远': [120.38, 37.35],
        '舟山': [122.207216, 29.985295],
        '齐齐哈尔': [123.97, 47.33],
        '盐城': [120.13, 33.38],
        '赤峰': [118.87, 42.28],
        '青岛': [120.33, 36.07],
        '乳山': [121.52, 36.89],
        '金昌': [102.188043, 38.520089],
        '泉州': [118.58, 24.93],
        '莱西': [120.53, 36.86],
        '日照': [119.46, 35.42],
        '胶南': [119.97, 35.88],
        '南通': [121.05, 32.08],
        '拉萨': [91.11, 29.97],
        '云浮': [112.02, 22.93],
        '梅州': [116.1, 24.55],
        '文登': [122.05, 37.2],
        '上海': [121.48, 31.22],
        '攀枝花': [101.718637, 26.582347],
        '威海': [122.1, 37.5],
        '承德': [117.93, 40.97],
        '厦门': [118.1, 24.46],
        '汕尾': [115.375279, 22.786211],
        '潮州': [116.63, 23.68],
        '丹东': [124.37, 40.13],
        '太仓': [121.1, 31.45],
        '曲靖': [103.79, 25.51],
        '烟台': [121.39, 37.52],
        '福州': [119.3, 26.08],
        '瓦房店': [121.979603, 39.627114],
        '即墨': [120.45, 36.38],
        '抚顺': [123.97, 41.97],
        '玉溪': [102.52, 24.35],
        '张家口': [114.87, 40.82],
        '阳泉': [113.57, 37.85],
        '莱州': [119.942327, 37.177017],
        '湖州': [120.1, 30.86],
        '汕头': [116.69, 23.39],
        '昆山': [120.95, 31.39],
        '宁波': [121.56, 29.86],
        '湛江': [110.359377, 21.270708],
        '揭阳': [116.35, 23.55],
        '荣成': [122.41, 37.16],
        '连云港': [119.16, 34.59],
        '葫芦岛': [120.836932, 40.711052],
        '常熟': [120.74, 31.64],
        '东莞': [113.75, 23.04],
        '河源': [114.68, 23.73],
        '淮安': [119.15, 33.5],
        '泰州': [119.9, 32.49],
        '南宁': [108.33, 22.84],
        '营口': [122.18, 40.65],
        '惠州': [114.4, 23.09],
        '江阴': [120.26, 31.91],
        '蓬莱': [120.75, 37.8],
        '韶关': [113.62, 24.84],
        '嘉峪关': [98.289152, 39.77313],
        '广州': [113.23, 23.16],
        '延安': [109.47, 36.6],
        '太原': [112.53, 37.87],
        '清远': [113.01, 23.7],
        '中山': [113.38, 22.52],
        '昆明': [102.73, 25.04],
        '寿光': [118.73, 36.86],
        '盘锦': [122.070714, 41.119997],
        '长治': [113.08, 36.18],
        '深圳': [114.07, 22.62],
        '珠海': [113.52, 22.3],
        '宿迁': [118.3, 33.96],
        '咸阳': [108.72, 34.36],
        '铜川': [109.11, 35.09],
        '平度': [119.97, 36.77],
        '佛山': [113.11, 23.05],
        '海口': [110.35, 20.02],
        '江门': [113.06, 22.61],
        '章丘': [117.53, 36.72],
        '肇庆': [112.44, 23.05],
        '大连': [121.62, 38.92],
        '临汾': [111.5, 36.08],
        '吴江': [120.63, 31.16],
        '石嘴山': [106.39, 39.04],
        '沈阳': [123.38, 41.8],
        '苏州': [120.62, 31.32],
        '茂名': [110.88, 21.68],
        '嘉兴': [120.76, 30.77],
        '长春': [125.35, 43.88],
        '胶州': [120.03336, 36.264622],
        '银川': [106.27, 38.47],
        '张家港': [120.555821, 31.875428],
        '三门峡': [111.19, 34.76],
        '锦州': [121.15, 41.13],
        '南昌': [115.89, 28.68],
        '柳州': [109.4, 24.33],
        '三亚': [109.511909, 18.252847],
        '自贡': [104.778442, 29.33903],
        '吉林': [126.57, 43.87],
        '阳江': [111.95, 21.85],
        '泸州': [105.39, 28.91],
        '西宁': [101.74, 36.56],
        '宜宾': [104.56, 29.77],
        '呼和浩特': [111.65, 40.82],
        '成都': [104.06, 30.67],
        '大同': [113.3, 40.12],
        '镇江': [119.44, 32.2],
        '桂林': [110.28, 25.29],
        '张家界': [110.479191, 29.117096],
        '宜兴': [119.82, 31.36],
        '北海': [109.12, 21.49],
        '西安': [108.95, 34.27],
        '金坛': [119.56, 31.74],
        '东营': [118.49, 37.46],
        '牡丹江': [129.58, 44.6],
        '遵义': [106.9, 27.7],
        '绍兴': [120.58, 30.01],
        '扬州': [119.42, 32.39],
        '常州': [119.95, 31.79],
        '潍坊': [119.1, 36.62],
        '重庆': [106.54, 29.59],
        '台州': [121.420757, 28.656386],
        '南京': [118.78, 32.04],
        '滨州': [118.03, 37.36],
        '贵阳': [106.71, 26.57],
        '无锡': [120.29, 31.59],
        '本溪': [123.73, 41.3],
        '克拉玛依': [84.77, 45.59],
        '渭南': [109.5, 34.52],
        '马鞍山': [118.48, 31.56],
        '宝鸡': [107.15, 34.38],
        '焦作': [113.21, 35.24],
        '句容': [119.16, 31.95],
        '北京': [116.46, 39.92],
        '徐州': [117.2, 34.26],
        '衡水': [115.72, 37.72],
        '包头': [110, 40.58],
        '绵阳': [104.73, 31.48],
        '乌鲁木齐': [87.68, 43.77],
        '枣庄': [117.57, 34.86],
        '杭州': [120.19, 30.26],
        '淄博': [118.05, 36.78],
        '鞍山': [122.85, 41.12],
        '溧阳': [119.48, 31.43],
        '库尔勒': [86.06, 41.68],
        '安阳': [114.35, 36.1],
        '开封': [114.35, 34.79],
        '济南': [117, 36.65],
        '德阳': [104.37, 31.13],
        '温州': [120.65, 28.01],
        '九江': [115.97, 29.71],
        '邯郸': [114.47, 36.6],
        '临安': [119.72, 30.23],
        '兰州': [103.73, 36.03],
        '沧州': [116.83, 38.33],
        '临沂': [118.35, 35.05],
        '南充': [106.110698, 30.837793],
        '天津': [117.2, 39.13],
        '富阳': [119.95, 30.07],
        '泰安': [117.13, 36.18],
        '诸暨': [120.23, 29.71],
        '郑州': [113.65, 34.76],
        '哈尔滨': [126.63, 45.75],
        '聊城': [115.97, 36.45],
        '芜湖': [118.38, 31.33],
        '唐山': [118.02, 39.63],
        '平顶山': [113.29, 33.75],
        '邢台': [114.48, 37.05],
        '德州': [116.29, 37.45],
        '济宁': [116.59, 35.38],
        '荆州': [112.239741, 30.335165],
        '宜昌': [111.3, 30.7],
        '义乌': [120.06, 29.32],
        '丽水': [119.92, 28.45],
        '洛阳': [112.44, 34.7],
        '秦皇岛': [119.57, 39.95],
        '株洲': [113.16, 27.83],
        '石家庄': [114.48, 38.03],
        '莱芜': [117.67, 36.19],
        '常德': [111.69, 29.05],
        '保定': [115.48, 38.85],
        '湘潭': [112.91, 27.87],
        '金华': [119.64, 29.12],
        '岳阳': [113.09, 29.37],
        '长沙': [113, 28.21],
        '衢州': [118.88, 28.97],
        '廊坊': [116.7, 39.53],
        '菏泽': [115.480656, 35.23375],
        '合肥': [117.27, 31.86],
        '武汉': [114.31, 30.52],
        '大庆': [125.03, 46.58]
      };


      loadMapData(chartData.proportion)

      function loadMapData(data) {

        var convertData = function (data) {
          var res = [];
          for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
              res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value),
                data: data[i]
              });
            }
          }
          return res;
        };

        var option = {
          tooltip: {
            trigger: 'item',
            formatter: function (obj) {

              var data = obj.data.data;
              var result = `${data.name}：${data.value}个<br />面积：${data.area}平米`

              return result;
            }
          },
          geo: {
            map: 'china',
            roam: true,
            zoom: 1.2,
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
            type: 'scatter',
            coordinateSystem: 'geo',
            data: convertData(data),
            symbolSize: function (val) {
              return 10;
            },
            showEffectOn: 'render',
            rippleEffect: {
              brushType: 'stroke'
            },
            hoverAnimation: true,
            label: {
              normal: {
                formatter: '{b}',
                position: 'right',
                show: true
              }
            },
            itemStyle: {
              normal: {
                color: '#0588e6',
                shadowBlur: 10,
                shadowColor: '#333'
              }
            },
            zlevel: 1
          }
          ]
        };

        context.$initChart(context, chartId, option)
        // vm.orderChinaMapChart.on('click', function (scatter) {

        //   if (scatter && scatter.seriesType === "scatter") {
        //     let data = scatter.data.data;
        //     vm.selectedCustomCode = data.code;
        //     vm.loadOrderTopData()

        //   }

        // });
      }


    }

  },
  mounted() {
    var vm = this;

    vm.loadAllData();

  },
  data() {
    return {
      storageAreaData: {},
      // ButtonLinechartItems: [
      //   { id: "Highway", name: "公路" },
      //   { id: "Railway", name: "铁路" },
      //   { id: "InlandNavigation", name: "内河航运" },
      //   { id: "OceanShipping", name: "海运" },
      //   { id: "AirTransport", name: "空运" },
      //   { id: "TODO", name: "仓储" }
      // ],
      // selectedLinechartItem: '公路',
      // selectedLinechartItemId: "Highway",
      startDate: new Date().format(),
      endDate: new Date().format(),
      newAddedUsersLineChartBoxClass: {
        'row-group-last': true
      }
    }
  }
}


</script>

<style lang="scss">
.warehouse-map-container {
  position: relative;
  box-sizing: border-box;
  main {
    height: 640px;
    padding-right: 345px;
    box-sizing: border-box;
  }
  aside {
    position: absolute;
    width: 245px;
    height: 640px;
    right: 0;
    top: 0px;
    box-sizing: border-box;
    border: 1px solid rgb(223, 230, 236);
  }

  .warehouse-summary {
    &>div {
      box-sizing: border-box;
      height: 213.3333px;
      text-align: center;
    }

    &>div+div {
      border-top: 1px solid rgb(223, 230, 236);
    }

    .title {
      height: 50px;
      font-size: 16px;
      padding-top: 40px;
    }

    .number {
      font-size: 40px;
      font-weight: bold;
      color: #0588e6;
    }
  }
}
</style>
