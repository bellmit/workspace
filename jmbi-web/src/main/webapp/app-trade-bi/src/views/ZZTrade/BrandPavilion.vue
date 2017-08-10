<template>
  <div>

    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营分析</el-breadcrumb-item>
      <el-breadcrumb-item>品牌馆</el-breadcrumb-item>
    </el-breadcrumb>

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>

    <data-overview :platform="platformId" :startDate="startDate" :endDate="endDate"></data-overview>
    <baidu-index-trend :platform="platformId" :startDate="startDate" :endDate="endDate"></baidu-index-trend>

    <el-row class="jm-grid-border-1px" v-if="false">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">品牌馆 UV/访问时长 TOP榜</header>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">

          <div class="box sixteen-nine" :class="{'no-data': loadingbrandPavilionBarUVText === '暂无数据'}" v-loading="loadingbrandPavilionBarUV" :element-loading-text="loadingbrandPavilionBarUVText">
            <div class="content" ref='brandPavilionBarUVChart'>

            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">


          <div class="box sixteen-nine" :class="{'no-data': loadingbrandPavilionBarVisitTimeText === '暂无数据'}" v-loading="loadingbrandPavilionBarVisitTime" :element-loading-text="loadingbrandPavilionBarVisitTimeText">
            <div class="content" ref='brandPavilionBarVisitTimeChart'>

            </div>
          </div>

        </div>
      </el-col>
    </el-row>

    <el-row class="jm-grid-border-1px" v-if="false">

      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">搜索词排行
          </header>

          <div class="box row-search-word-group" v-loading="false" element-loading-text="加载中...">
            <div class="content">
               <div class="search-list-table">
                  <div class="col-header clearfix">
                    <div class="col w-fixed-width">排名</div>
                    <div class="col w-fixed-width">搜索词</div>
                    <div class="col w-fixed-width">搜索涨幅</div>
                    <div class="col w-fixed-width">搜索指数</div>
                    <div class="col-last"></div>

                  </div>

                  <div ng-class-odd="'odd'" ng-class-even="'even'" class="data-list clearfix">
                    <div class="col w-fixed-width jm-text-center">1</div>
                    <div class="col w-fixed-width"><span>橡胶</span></div>
                    <div class="col w-fixed-width jm-text-center">1.81%</div>
                    <div class="col w-fixed-width j-text-right"><span>2,230,138</span></div>

                    <div class="col-last">
                      <div class="percentage">
                        <div class="percentage-bar up" bo-class="{'up':(data.change | trendUp), 'down':(data.change | trendDown) }" style="width: 20%"></div>
                      </div>
                    </div>

                  </div>

                  <div ng-class-odd="'odd'" ng-class-even="'even'" class="data-list clearfix">
                    <div class="col w-fixed-width jm-text-center">2</div>
                    <div class="col w-fixed-width"><span>橡胶</span></div>
                    <div class="col w-fixed-width jm-text-center">1.81%</div>
                    <div class="col w-fixed-width j-text-right"><span>2,230,138</span></div>

                    <div class="col-last">
                      <div class="percentage">
                        <div class="percentage-bar up" bo-class="{'up':(data.change | trendUp), 'down':(data.change | trendDown) }" style="width: 70%"></div>
                      </div>
                    </div>

                  </div>

                  <div ng-class-odd="'odd'" ng-class-even="'even'" class="data-list clearfix">
                    <div class="col w-fixed-width jm-text-center">2</div>
                    <div class="col w-fixed-width"><span>橡胶</span></div>
                    <div class="col w-fixed-width jm-text-center">1.81%</div>
                    <div class="col w-fixed-width j-text-right"><span>2,230,138</span></div>

                    <div class="col-last">
                      <div class="percentage">
                        <div class="percentage-bar up" bo-class="{'up':(data.change | trendUp), 'down':(data.change | trendDown) }" style="width: 70%"></div>
                      </div>
                    </div>

                  </div>
                </div>
                <!--end of table-->
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
  name: 'BrandPavilion',
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
      vm.loadBarChart('chartData', 'brandPavilionBarUVChart')
      vm.loadBarChart('chartData', 'brandPavilionBarVisitTimeChart')

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
      platformId: '102500',
      loadingIndex: false,
      indexList: null,
      startDate: new Date().format(),
      endDate: new Date().format(),
      zhibiao_activeTabName: 'pv',
      loadingbrandPavilionBarUV: false,
      loadingbrandPavilionBarUVText:'加载中...',
       loadingbrandPavilionBarVisitTime: false,
      loadingbrandPavilionBarVisitTimeText:'加载中...'
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

  //------------table list
  $table-row-height:         50px !default;
$table-header-bg:         rgb(238, 241, 246) !default;
$table-border:         1px solid rgb(223, 230, 236) !default;

.col-header .col,
.data-list .col {
    box-sizing: border-box;
    float: left;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;

    height: $table-row-height;
    line-height: $table-row-height;
    border-right: $table-border;
    border-bottom: $table-border;
}

.data-list .col {
  padding: 0 15px;
}

.data-list .col.number {
  text-align: right;

}

.table-header-cell {
  box-sizing: border-box;
  text-align: center;
  line-height: $table-row-height;
   background-color:$table-header-bg;
}

.col-header .col {
  text-align: center;
   background-color:$table-header-bg;
}


.col-header .col-last {
  box-sizing: border-box;
    overflow:hidden;
    background-color:$table-header-bg;
    height: $table-row-height;
    line-height: $table-row-height;
    border-bottom: $table-border;
}
.data-list .col-last {
  box-sizing: border-box;
    overflow:hidden;
    height: $table-row-height;
    border-bottom: $table-border;
}

.bar-label {
  margin-right: 20px;
}

.icon-box {
  display: inline-block;
  width: 10px;
  height: 10px;
  margin-right: 5px;

  &.number-1 {
    background: #2762FA;
  }

  &.number-2 {
    background: #FB725E;
  }
}

 .data-list .percentage {
	height: 15px;
	margin-top: 6px;
  margin-left: 15px;
	margin-right: 15px;
	overflow: hidden;
	background-color: #f5f5f5;
	border-radius: 2px;
	-webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, .1);
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, .1);
}

.data-list .percentage-bar {
	width: 0;
	height: 15px;
	// line-height: 15px;
  text-align: center;
  color:#fff;
}

.row-search-word-group {

  .content {
    padding: 30px;
    box-sizing: border-box;
  }

  .search-list-table {
      border-top: $table-border;
     border-left: $table-border;
     border-right: $table-border;
  }

  .col-header .col,
  .col-header .col-last,
.data-list .col,
.data-list .col-last {
  height: 40px;
  line-height: 40px;
}

.col-header .col-last {
  text-align: center;
}
  .data-list .percentage {
    height: 20px;
    margin-top: 10px;

  }

  .data-list .percentage-bar {
    height: 20px;
    line-height: 20px;
  }
}

.data-list .percentage-bar.up {
	background: #2762FA;
}

.data-list .percentage-bar.down {
	background: #FB725E;
}

.w-fixed-width {
  width: 130px;
}
</style>
