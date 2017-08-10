<template>
  <div>

    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>用户行为</el-breadcrumb-item>
      <el-breadcrumb-item>来源分析</el-breadcrumb-item>
    </el-breadcrumb>

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
    <data-overview apiName="baiduOverivew" :apiParams="apiParams" dataProperty="overview" panelTitle="概览"></data-overview>


    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">访客地域分布

      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white map">

          <chart-box v-on:loadOption="loadMap" :chartBoxClass="CapacityMapChartBoxClass" apiName="jzxVisitorMapFlow" :apiParams="trafficApiParams" seriesDataProperty="flowChart.seriesData"></chart-box>
        </div>
      </el-col>

    </el-row>


    <!-- end of 用户访问时间分布 -->

    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">
            地域流量榜单
            <el-tooltip class="item" content="地域流量榜单" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
          <div class="box sixteen-nine">
            <div class="content">

          <el-table
            :data="table"
            border
            style="width: 100%">
            <el-table-column
              prop="country"
              label="地域"
             >
            </el-table-column>
            <el-table-column label="网站基础指标">
                <el-table-column
                  prop="pv"
                  label="流量（PV）"
                  >
                </el-table-column>
                <el-table-column
                  prop="uv"
                  label="访客数（UV）"
                  >
                </el-table-column>
                <el-table-column
                  prop="ip"
                  label="IP数"
                 >
                </el-table-column>
              </el-table-column>
          </el-table>
            </div>
          </div>

        </div>
      </el-col>
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

      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <el-table
          :data="tableSource"
          border
          style="width: 100%">
          <el-table-column
            prop="name"
            label="来源类型"
            >
          </el-table-column>
          <el-table-column label="网站基础指标">
            <el-table-column
              prop="pv"
              label="浏览量（PV）"
              >
            </el-table-column>
            <el-table-column
              prop="uv"
              label="访客数（UV）"
              >
            </el-table-column>
            <el-table-column
              prop="ip"
              label="IP数"
             >
            </el-table-column>
          </el-table-column>
          <el-table-column label="流量质量指标">
            <el-table-column
              prop="exitRate"
              label="跳出率(%)"
              >
            </el-table-column>
          </el-table-column>
        </el-table>
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
    getAreas,
    jzxVisitorMapFlow
  } from '@/service/api'

import * as api from '@/service/api'

console.log(api)

import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'
import DataOverview from '@/components/common/DataOverview'
  import chartBox from '@/components/chartBox'

import echarts from 'echarts';

export default {
  name: 'sourceAnalysis',
  components: {
    rangeDatePicker,
    DataOverview,
    chartBox
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
        endDate: this.endDate
      }
    }
  },
  created() {
    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }
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

      vm.loadingFwlyLineChart = true
      getSources({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingFwlyLineChart = false
        var chartData = response.data
        vm.tableSource = chartData.table
        vm.loadfwlyLineChart(chartData)
        vm.loadfwlyPieChart(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });


      jzxVisitorMapFlow({
        platform,
        startDate,
        endDate}).then(function(response){
          var table = response.data.table;
          vm.table = table;
      }).catch(function (error) {
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



// 世界地图
    loadMap({ chartData, context, chartId }) {
      var vm = this;

      var nameMap = {
        'Afghanistan':'阿富汗',
        'Angola':'安哥拉',
        'Albania':'阿尔巴尼亚',
        'United Arab Emirates':'阿联酋',
        'Argentina':'阿根廷',
        'Armenia':'亚美尼亚',
        'French Southern and Antarctic Lands':'法属南半球和南极领地',
        'Australia':'澳大利亚',
        'Austria':'奥地利',
        'Azerbaijan':'阿塞拜疆',
        'Burundi':'布隆迪',
        'Belgium':'比利时',
        'Benin':'贝宁',
        'Burkina Faso':'布基纳法索',
        'Bangladesh':'孟加拉国',
        'Bulgaria':'保加利亚',
        'The Bahamas':'巴哈马',
        'Bosnia and Herzegovina':'波斯尼亚和黑塞哥维那',
        'Belarus':'白俄罗斯',
        'Belize':'伯利兹',
        'Bermuda':'百慕大',
        'Bolivia':'玻利维亚',
        'Brazil':'巴西',
        'Brunei':'文莱',
        'Bhutan':'不丹',
        'Botswana':'博茨瓦纳',
        'Central African Republic':'中非共和国',
        'Canada':'加拿大',
        'Switzerland':'瑞士',
        'Chile':'智利',
        'China':'中国',
        'Ivory Coast':'象牙海岸',
        'Cameroon':'喀麦隆',
        'Democratic Republic of the Congo':'刚果民主共和国',
        'Republic of the Congo':'刚果共和国',
        'Colombia':'哥伦比亚',
        'Costa Rica':'哥斯达黎加',
        'Cuba':'古巴',
        'Northern Cyprus':'北塞浦路斯',
        'Cyprus':'塞浦路斯',
        'Czech Republic':'捷克共和国',
        'Germany':'德国',
        'Djibouti':'吉布提',
        'Denmark':'丹麦',
        'Dominican Republic':'多明尼加共和国',
        'Algeria':'阿尔及利亚',
        'Ecuador':'厄瓜多尔',
        'Egypt':'埃及',
        'Eritrea':'厄立特里亚',
        'Spain':'西班牙',
        'Estonia':'爱沙尼亚',
        'Ethiopia':'埃塞俄比亚',
        'Finland':'芬兰',
        'Fiji':'斐',
        'Falkland Islands':'福克兰群岛',
        'France':'法国',
        'Gabon':'加蓬',
        'United Kingdom':'英国',
        'Georgia':'格鲁吉亚',
        'Ghana':'加纳',
        'Guinea':'几内亚',
        'Gambia':'冈比亚',
        'Guinea Bissau':'几内亚比绍',
        'Equatorial Guinea':'赤道几内亚',
        'Greece':'希腊',
        'Greenland':'格陵兰',
        'Guatemala':'危地马拉',
        'French Guiana':'法属圭亚那',
        'Guyana':'圭亚那',
        'Honduras':'洪都拉斯',
        'Croatia':'克罗地亚',
        'Haiti':'海地',
        'Hungary':'匈牙利',
        'Indonesia':'印尼',
        'India':'印度',
        'Ireland':'爱尔兰',
        'Iran':'伊朗',
        'Iraq':'伊拉克',
        'Iceland':'冰岛',
        'Israel':'以色列',
        'Italy':'意大利',
        'Jamaica':'牙买加',
        'Jordan':'约旦',
        'Japan':'日本',
        'Kazakhstan':'哈萨克斯坦',
        'Kenya':'肯尼亚',
        'Kyrgyzstan':'吉尔吉斯斯坦',
        'Cambodia':'柬埔寨',
        'South Korea':'韩国',
        'Kosovo':'科索沃',
        'Kuwait':'科威特',
        'Laos':'老挝',
        'Lebanon':'黎巴嫩',
        'Liberia':'利比里亚',
        'Libya':'利比亚',
        'Sri Lanka':'斯里兰卡',
        'Lesotho':'莱索托',
        'Lithuania':'立陶宛',
        'Luxembourg':'卢森堡',
        'Latvia':'拉脱维亚',
        'Morocco':'摩洛哥',
        'Moldova':'摩尔多瓦',
        'Madagascar':'马达加斯加',
        'Mexico':'墨西哥',
        'Macedonia':'马其顿',
        'Mali':'马里',
        'Myanmar':'缅甸',
        'Montenegro':'黑山',
        'Mongolia':'蒙古',
        'Mozambique':'莫桑比克',
        'Mauritania':'毛里塔尼亚',
        'Malawi':'马拉维',
        'Malaysia':'马来西亚',
        'Namibia':'纳米比亚',
        'New Caledonia':'新喀里多尼亚',
        'Niger':'尼日尔',
        'Nigeria':'尼日利亚',
        'Nicaragua':'尼加拉瓜',
        'Netherlands':'荷兰',
        'Norway':'挪威',
        'Nepal':'尼泊尔',
        'New Zealand':'新西兰',
        'Oman':'阿曼',
        'Pakistan':'巴基斯坦',
        'Panama':'巴拿马',
        'Peru':'秘鲁',
        'Philippines':'菲律宾',
        'Papua New Guinea':'巴布亚新几内亚',
        'Poland':'波兰',
        'Puerto Rico':'波多黎各',
        'North Korea':'北朝鲜',
        'Portugal':'葡萄牙',
        'Paraguay':'巴拉圭',
        'Qatar':'卡塔尔',
        'Romania':'罗马尼亚',
        'Russia':'俄罗斯',
        'Rwanda':'卢旺达',
        'Western Sahara':'西撒哈拉',
        'Saudi Arabia':'沙特阿拉伯',
        'Sudan':'苏丹',
        'South Sudan':'南苏丹',
        'Senegal':'塞内加尔',
        'Solomon Islands':'所罗门群岛',
        'Sierra Leone':'塞拉利昂',
        'El Salvador':'萨尔瓦多',
        'Somaliland':'索马里兰',
        'Somalia':'索马里',
        'Republic of Serbia':'塞尔维亚共和国',
        'Suriname':'苏里南',
        'Slovakia':'斯洛伐克',
        'Slovenia':'斯洛文尼亚',
        'Sweden':'瑞典',
        'Swaziland':'斯威士兰',
        'Syria':'叙利亚',
        'Chad':'乍得',
        'Togo':'多哥',
        'Thailand':'泰国',
        'Tajikistan':'塔吉克斯坦',
        'Turkmenistan':'土库曼斯坦',
        'East Timor':'东帝汶',
        'Trinidad and Tobago':'特里尼达和多巴哥',
        'Tunisia':'突尼斯',
        'Turkey':'土耳其',
        'United Republic of Tanzania':'坦桑尼亚联合共和国',
        'Uganda':'乌干达',
        'Ukraine':'乌克兰',
        'Uruguay':'乌拉圭',
        'United States of America':'美国',
        'Uzbekistan':'乌兹别克斯坦',
        'Venezuela':'委内瑞拉',
        'Vietnam':'越南',
        'Vanuatu':'瓦努阿图',
        'West Bank':'西岸',
        'Yemen':'也门',
        'South Africa':'南非',
        'Zambia':'赞比亚',
        'Zimbabwe':'津巴布韦'
      };

      let e2c = {}
      var keys =Object.keys(nameMap)
      var values =Object.values(nameMap)
      for(let index = 0; index < keys.length; index++) {
          e2c[values[index]] = keys[index]
      }

      console.log('e2c',e2c)
      let tipWrap = chartData.data;
      console.log('tipWrap', tipWrap)
      let option = {
        tooltip: {
          trigger: 'item',
          formatter: function(obj){
            console.log(obj)
            let data = obj.data.value ;
            if (data) {
              return data[0] + '<br>' + data[1]

            }

            return nameMap[obj.name]

          }
        },

        visualMap: {
          min: 0,
          max: chartData.max,
          text:['High','Low'],
          realtime: false,
          calculable: true,
          inRange: {
            color: ['lightskyblue','yellow', 'orangered']
          }
        },

        series: [ {
          name: 'World Population (2010)',
          type: 'map',
          mapType: 'world',
          roam: true,
          itemStyle:{
            emphasis:{label:{show:true}}
          },
          data:tipWrap.map(x=> {

              return {
                  name: e2c[x.name],
                value :[x.name].concat(x.value)
              }
          })
        }]
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
      loadingFwlyLineChart: false,
      loadingNewOldVisitor: false,
      loadingDyfbChart: false,
      loadingFwlyLineChart: false,
      loadingYhfwsjfbChart: false,
      CapacityMapChartBoxClass: {
        'Capacity-MapChart-BoxClass': true
      },
      table: [],
      tableSource: [],
      tableData: []

    }
  }
}

</script>

<style lang="scss">
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

  .box.Capacity-MapChart-BoxClass {
    height: 600px;
  }
</style>
