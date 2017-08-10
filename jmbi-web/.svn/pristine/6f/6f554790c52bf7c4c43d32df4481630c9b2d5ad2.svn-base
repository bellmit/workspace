<template>
  <div>

    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营分析</el-breadcrumb-item>
      <el-breadcrumb-item>一带一路馆</el-breadcrumb-item>
    </el-breadcrumb>

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>

    <data-overview :platform="platformId" :startDate="startDate" :endDate="endDate"></data-overview>
    <baidu-index-trend :platform="platformId" :startDate="startDate" :endDate="endDate"></baidu-index-trend>


    <el-row class="jm-grid-border-1px">

      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">下游流量分布
          </header>

          <div class="box chart row-group-last" v-loading="false" element-loading-text="加载中...">
            <div class="content" ref='downstreamFlowChart'>

            </div>

          </div>
        </div>
      </el-col>

    </el-row>

  </div>
</template>
<script>

import {
  //baiduTrend,
  pageVisit,
  getSources,
  getVistors,
  getAreas
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'
import DataOverview from '@/components/ZZ/DataOverview'
import BaiduIndexTrend from '@/components/ZZ/BaiduIndexTrend'

import echarts from 'echarts';

export default {
  name: 'BeltAndRoad',
  created() {

    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }
  },
  components: {
    rangeDatePicker,
    DataOverview,
    BaiduIndexTrend
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

      //vm.loadTabContentChart(vm.zhibiao_activeTabName)

      vm.loadBarChart('chartData', 'downstreamFlowChart')

    },



    loadBarChart(chartData, chartId) {
      var vm = this;

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
          //name: '次',
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
          data: ['巴西','印尼','美国','印度','中国','世界人口(万)']
        },
        series: [{
          name: "店铺商品发布数排行",
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#5eb2ed',
              barBorderRadius: 2
            }
          },
          data:  [18203, 23489, 29034, 104970, 131744, 630230]
        }]
      };

      vm.$initChart(vm, chartId, option)
      vm[chartId].off('click')
      vm[chartId].on('click', function (obj) {
        //openInNewTab(obj.name);
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
        // var tabContentChart = `${vm.zhibiao_activeTabName}Chart`
        // if (vm[tabContentChart]) {
        //   vm[tabContentChart].resize()
        // }

      }, 100);

    }
  },
  data() {
    return {
       platformId: '103100',
      // loadingIndex: false,
      // indexList: null,
      startDate: new Date().format(),
      endDate: new Date().format(),
      // 、、zhibiao_activeTabName: 'pv'
    }
  }
}

</script>

<style lang="scss">

.total-summary {
  padding: 10px;
  border-left: 1px solid #dedede;
  & > div {
    text-align: center;
    line-height: 36px;
  }

  &.first-box {
    border-left:none;
  }


  .title {
    font-size: 16px;
  }

  .amount {
    font-size: 28px;
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



.bar-trend-chart-tooltip-icon {
  display: inline-block;
  margin-right: 5px;
  border-radius: 10px;
  width: 9px;
  height: 9px;
}



.button-header {
    display: flex;
    justify-content: flex-end;
    .title {
      align-self: flex-start;
      margin-right: auto;
    }

    .el-button-group {
      margin-top: 2px;
    }
  }
</style>
