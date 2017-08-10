<template>
  <!--dialog start -->
  <section class="jm-dialog-container order-area-chart-dialog">
    <el-dialog class="order-area-order-analysis-dialog"
               :title="dialogTitle"
               v-model="showMe"
               v-on:close="closeMe">

      <advance-search v-on:searchCriteria="getSearchCriteria"></advance-search>
      <div class="box sixteen-nine">
        <div class="content"
             ref='orderTrendChart'>

        </div>
      </div>

    </el-dialog>
  </section>
  <!--dialog end-->
</template>
<script>
import util from '@/service/util'

import {
  getOrderTrend
} from '@/service/api'


import advanceSearch from '@/components/AdvanceSearch'

import echarts from 'echarts';

export default {
  name: 'orderDetails',
  components: {
    advanceSearch
  },
  props: {
    'dialogTitle': {
      type: String
    },
    'popupVisible': {
      type: Boolean,
      default: false
    },
    'orderType': {
      // 2: order price, 1: order amount
      type: Number
    }
  },
  mounted() {
    var vm = this;
    window.addEventListener('resize', chartsResize, false);

    function chartsResize() {
      vm.orderTrendChart && vm.orderTrendChart.resize()
    }
  },
  updated() {
    var vm = this;

    if (vm.showMe && !vm.dialogOpened) {
      vm.loadorderTrendChart(vm.searchCriteria);
      vm.dialogOpened = true;
    }
  },
  computed: {
    showMe() {
      return this.popupVisible;
    }
  },
  data() {
    return {
      orderTrendChart: null,
      dialogOpened: false,
      searchCriteria: {
        timeType: 'week',
        startDate: util.firstDayOfWeek().format(),
        endDate: util.lastDayOfWeek().format(),
        customsCode: 'all',
        interval: 0
      }
    }
  },
  methods: {
    getSearchCriteria(searchCriteria) {
      this.searchCriteria = Object.assign({}, searchCriteria);
      this.loadorderTrendChart(searchCriteria);
    },
    closeMe() {
      this.dialogOpened = false
      this.$emit('closeMe')
    },
    loadorderTrendChart(searchCriteria) {
      var vm = this;

      var unit = vm.orderType === 2 ? "万美元" : "票";
      // 基于准备好的dom，初始化echarts实例
      vm.orderTrendChart = echarts.init(vm.$refs.orderTrendChart);
      var criteria = Object.assign({}, searchCriteria, {
        flag: vm.orderType
      })

      getOrderTrend(criteria).then(function (response) {
        var chartData = response.data;
        loadOption(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });

      function loadOption(chartData) {

        var series = chartData.series || [];
        var colors = {
          "出口": {
            color: '#2ab2d6'
          },
          "进口": {
            color: '#2391cd'
          },
          "总计": {
            color: '#44cd8a'
          }
        }
        var mergedSeries = series.map(function (obj) {

          var rObj = Object.assign({}, obj, {
            itemStyle: {
              normal: {
                color: colors[obj.name].color
              }
            },
            barGap: 0
          })

          return rObj;
        });

        let chartType = vm.orderType;

        var option = {
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            },
            formatter: function (arr) {

              var tooltip = arr[0].name
              arr.forEach(function (series) {
                var unit, color = series.color,
                  a = series.seriesName,
                  c = series.value || 0;

                if (chartType === 1) {
                  //unit = '票'
                } else {
                  //unit = '万美元'
                  c = util.numberWithCommas(c.toFixed(4))
                }

                var template = `<br /><span class="order-trend-chart-tooltip-icon" style="background-color:${color}"></span>${a}: ${c}`;
                tooltip = tooltip.concat(template)
              })
              return tooltip;
            },
          },
          legend: {
            bottom: '0',
            data: chartData.legendData
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '10%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            axisLine: {
              lineStyle: {
                color: '#158bca'
              }
            },
            data: chartData.xAxisData
          },
          yAxis: {
            name: unit,
            type: 'value',
            axisLine: {
              lineStyle: {
                color: '#158bca'
              }
            }

          },
          series: mergedSeries
        };

        vm.orderTrendChart.setOption(option)
      }
    }
  }
}

</script>

<style lang="scss">
.order-area-chart-dialog {
  .el-dialog--small {
    width: 55%;
  }
  .order-advance-search-bar {
    text-align: center;
  }
}

.order-trend-chart-tooltip-icon {
  display: inline-block;
  margin-right: 5px;
  border-radius: 10px;
  width: 9px;
  height: 9px;
}
</style>
