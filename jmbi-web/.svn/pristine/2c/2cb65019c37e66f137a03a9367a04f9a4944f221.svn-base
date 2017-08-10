<template>

  <el-row>
      <el-col :span="24">
        
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px zhibiao-activeTabName">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px button-header">
            <span class="title">趋势分析</span>

            <el-button-group v-if="false">
              <el-button type="primary" >按日</el-button>
              <el-button>按周</el-button>
              <el-button>按月</el-button>
            </el-button-group>
          </header>
          <el-tabs v-model="zhibiao_activeTabName" type="card" @tab-click="handle_zhibiao_Click" v-loading="loadingIndex" element-loading-text="加载中...">
            <el-tab-pane label="浏览量" name="pv" v-if="!isOneDay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='pvChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="独立访客" name="uv" v-if="!isOneDay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='uvChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="IP数" name="ip" v-if="showDelay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='ipChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="平均停留时长" name="averageTime" v-if="showDelay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='averageTimeChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="平均访问页数" name="avgVisitPages" v-if="showDelay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='avgVisitPagesChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="跳出率" name="exitRate" v-if="showDelay">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='exitRateChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
  
    </el-row>

</template>
<script>
import {
  baiduTrend
} from '@/service/api'
import util from '@/service/util'

import echarts from 'echarts';

export default {
  name: 'BaiduIndexTrend',
  props: {
    'platform':[String],
    'startDate':[String],
    'endDate':[String]
  },
  created() {

    this.loadTabItem();
  },
  computed: {
    isOneDay() {
      return this.startDate === this.endDate
    }
  },
  watch: {
    startDate() {
      this.loadAllData()
    },
    endDate() {
      this.loadAllData()
    },
    isOneDay(val) {
      var vm = this;
      if (!val) {
        this.showDelay = false;
        this.$nextTick(function () {
          this.showDelay = true;
        })
        
        setTimeout(function() {
          vm.loadTabContentChart(vm.zhibiao_activeTabName)
        }, 50);
      }
    }
  },
  methods: {
    loadTabItem() {
      var vm = this;
      if (vm.isOneDay) {
        if (vm.zhibiao_activeTabName === 'pv' || vm.zhibiao_activeTabName === 'uv') {
          vm.zhibiao_activeTabName = 'ip'
        }
      }
    },
    loadAllData() {
      var vm = this;

     
      vm.loadTabItem();
      
      vm.loadTabContentChart(vm.zhibiao_activeTabName)
     

    },
    

    handle_zhibiao_Click(tab, event) {
      var vm = this;

      var id = tab.name;
      console.log(id)

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

      var platform = vm.platform;

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
          yAxis: [{
            name: loadingChartData.unit,
            type: 'value',
            axisLine: {
              lineStyle: {
                color: '#158bca'
              }
            }
          }],
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

      }, 100);

    }
  },
  data() {
    return {
      loadingIndex: false,
      indexList: null,
      zhibiao_activeTabName: 'pv',
      showDelay: true
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
    padding: 0 10px 0 5px;
    top: 22px;
    right: 30px;
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
