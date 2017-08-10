<template>
  <div>

    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营分析</el-breadcrumb-item>
      <el-breadcrumb-item>省 馆</el-breadcrumb-item>
    </el-breadcrumb>

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>

    <data-overview :platform="platformId" :startDate="startDate" :endDate="endDate"></data-overview>
    <baidu-index-trend :platform="platformId" :startDate="startDate" :endDate="endDate"></baidu-index-trend>



  </div>
</template>
<script>

// import {
//   baiduTrend,
//   pageVisit,
//   getSources,
//   getVistors,
//   getAreas
// } from '@/service/api'
// import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'
import DataOverview from '@/components/ZZ/DataOverview'
import BaiduIndexTrend from '@/components/ZZ/BaiduIndexTrend'

import echarts from 'echarts';




export default {
  name: 'ProvincialPavilion',
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
      //this.loadAllData();
    },
    // loadAllData() {
    //   var vm = this;
    //   var platform = vm.$platformId,
    //     startDate = vm.startDate,
    //     endDate = vm.endDate;

    //   vm.loadTabContentChart(vm.zhibiao_activeTabName)

    // },


    // handle_zhibiao_Click(tab, event) {
    //   var vm = this;

    //   var id = tab.name;

    //   vm.$nextTick(function () {
    //     // DOM 现在更新了
    //     // `this` 绑定到当前实例
    //     vm.loadTabContentChart(id, false)
    //   })

    // },

    // loadTabContentChart(id, reload = true) {
    //   var vm = this;
    //   var chartId = id + 'Chart';
    //   vm[chartId] = echarts.init(vm.$refs[chartId]);

    //   var platform = vm.$platformId;

    //   var startDate = vm.startDate,
    //     endDate = vm.endDate;

    //   if (reload || vm.indexList === null) {
    //     vm.loadingIndex = true
    //     baiduTrend({
    //       platform,
    //       startDate,
    //       endDate
    //     }).then(function (response) {
    //       vm.loadingIndex = false
    //       var chartData = response.data
    //       vm.indexList = chartData;
    //       loadTabChartOption(chartData)

    //     })
    //       .catch(function (error) {
    //         console.log(error);
    //       });

    //   } else {
    //     var chartData = vm.indexList
    //     loadTabChartOption(chartData)
    //   }

    //   function loadTabChartOption(chartData) {

    //     var xAxisData = chartData.xAxisData

    //     var loadingChartData = chartData[chartId]

    //     // 绘制图表
    //     var option = {
    //       tooltip: {
    //         trigger: 'axis'
    //       },
    //       legend: {
    //         data: [loadingChartData.seriesName]
    //       },
    //       grid: {
    //         left: '3%',
    //         right: '3%',
    //         bottom: '10%',
    //         containLabel: true
    //       },
    //       xAxis: [{
    //         type: 'category',
    //         boundaryGap: false,
    //         axisLine: {
    //           lineStyle: {
    //             color: '#158bca'
    //           }
    //         },
    //         data: xAxisData
    //       }],
    //       yAxis: [{
    //         name: loadingChartData.unit,
    //         type: 'value',
    //         axisLine: {
    //           lineStyle: {
    //             color: '#158bca'
    //           }
    //         }
    //       }],
    //       series: [{
    //         name: loadingChartData.seriesName,
    //         type: 'line',
    //         smooth: true,
    //         itemStyle: {
    //           normal: {
    //             color: '#5eb2ed'
    //           }
    //         },
    //         areaStyle: {
    //           normal: {
    //             color: '#acd7f4'
    //           }
    //         },
    //         data: loadingChartData.seriesData
    //       }

    //       ]
    //     };
    //     vm[chartId].setOption(option)
    //   }
    // }

  },
  mounted() {

    // var vm = this;

    // vm.loadAllData()

    // window.addEventListener('resize', chartsResize, false);

    // var resizeTimer;

    // function chartsResize() {
    //   if (resizeTimer) {
    //     clearTimeout(resizeTimer)
    //   }
    //   resizeTimer = setTimeout(function () {
    //     var tabContentChart = `${vm.zhibiao_activeTabName}Chart`
    //     if (vm[tabContentChart]) {
    //       vm[tabContentChart].resize()
    //     }

    //   }, 100);

    // }
  },
  data() {
    return {
      platformId: '102400',
      // loadingIndex: false,
      // indexList: null,
      startDate: new Date().format(),
      endDate: new Date().format(),
      //zhibiao_activeTabName: 'pv'
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
