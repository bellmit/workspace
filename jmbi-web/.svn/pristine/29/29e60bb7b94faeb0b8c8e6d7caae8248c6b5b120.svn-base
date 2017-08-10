<template>
  <div>

    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营分析</el-breadcrumb-item>
      <el-breadcrumb-item>运营分析</el-breadcrumb-item>
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
  name: 'TUV',
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


      }, 100);

    }
  },
  data() {
    return {
     platformId: '103500',
      startDate: new Date().format(),
      endDate: new Date().format(),

    }
  }
}

</script>

<style lang="scss">


</style>
