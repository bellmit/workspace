<template>
  <div class="increate-user-page">
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>注册分析</el-breadcrumb-item>
      <el-breadcrumb-item>日新增用户</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">新增注册数
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingNewAddedUsersLineChartText === '暂无数据'}" v-loading="loadingNewAddedUsersLineChart" :element-loading-text="loadingNewAddedUsersLineChartText">
            <div class="content" ref='newAddedUsersLineChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box  chart row-group-last" :class="{'no-data': loadingNewAddedUserPieChartText === '暂无数据'}" v-loading="loadingNewAddedUserPieChart" :element-loading-text="loadingNewAddedUserPieChartText">
            <div class="content" ref='newAddedUserPieChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
    <!--<el-row class="jm-grid-border-1px">
            <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">注册用户地域分布
        
            </header>
            <el-col :xs="24" :sm="24" :md="24" :lg="12">
              <div class="grid-content jm-grid-box-bg-white">
        
                <div class="box chart row-group-last" v-loading="loadingTODOChart" element-loading-text="加载中...">
                  <div class="content" ref='registerUserAreaMapChart'>
        
                  </div>
        
                </div>
              </div>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="12">
              <div class="grid-content jm-grid-box-bg-white">
        
                <div class="box  chart row-group-last" v-loading="loadingTODOChart" element-loading-text="加载中...">
                  <div class="content" ref='registerUserAreaBarChart'>
        
                  </div>
                </div>
        
              </div>
            </el-col>
          </el-row>-->
    <!--end of 注册用户地域分布-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">注册明细表
  
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box " v-loading="loadingTableData" element-loading-text="加载中...">
            <div class="content">
              <div class="register-table-container">
                <div class="text-right">
                  <span class="margin-right-20">合计：{{tableData.counts}}人 </span>
                  <span>占总用户：{{tableData.percent}}</span>
                </div>
                <el-table :data="tableData.tableData" border style="width: 100%">
                  <el-table-column header-align="center" align="center" prop="date" label="日期">
                  </el-table-column>
                  <el-table-column header-align="center" align="center" prop="source" label="来源">
                  </el-table-column>
                  <el-table-column header-align="center" align="center" prop="amount" label="人数">
                  </el-table-column>
                  <el-table-column header-align="center" align="center" prop="rate" label="占比">
                  </el-table-column>
                </el-table>

                <el-pagination v-if="totalCount"
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="currentPage"
                  :page-sizes="[10,30,50,100]"
                  :page-size="pageSize"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="totalCount">
                </el-pagination>
              </div>
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 注册明细表-->
  
  </div>
</template>

<script>

import {
  newlyIncreased,
  newlyIncreasedTableList
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'increasedUser',
  components: {
    rangeDatePicker
  },
  created() {

    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
        console.log(`每页 ${val} 条`);
         this.getTableList() 
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val;
      
        this.getTableList() 
    },
    getTableList() {
        let vm = this;
        let platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;
        let limit = this.pageSize;
        let offset = limit * (this.currentPage - 1)
        vm.loadingTableData = true
      newlyIncreasedTableList({
        platform,
        startDate,
        endDate,
        limit,
        offset
      }).then(function (response) {
        vm.loadingTableData = false
        var data = response.data
        vm.tableData = data
        vm.totalCount = data.totalSize
      })
        .catch(function (error) {
          console.log(error);
        });
    },
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

      vm.loadingNewAddedUsersLineChart = true
      vm.loadingNewAddedUsersLineChartText = '加载中...'
      newlyIncreased({
        platform,
        startDate,
        endDate,
        dataType: 'lineChart'
      }).then(function (response) {
        vm.loadingNewAddedUsersLineChart = false
        var chartData = response.data

        if (chartData.lineChart.seriesData.length === 0) {
          vm.loadingNewAddedUsersLineChart = true
          vm.loadingNewAddedUsersLineChartText = '暂无数据'
        }
        vm.loadnewAddedUsersLineChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingNewAddedUserPieChart = true
      vm.loadingNewAddedUserPieChartText = '加载中...'
      newlyIncreased({
        platform,
        startDate,
        endDate,
        dataType: 'pieChart'
      }).then(function (response) {
        vm.loadingNewAddedUserPieChart = false
        var chartData = response.data

        if (chartData.pieChart.seriesData.length === 0) {
          vm.loadingNewAddedUserPieChart = true
          vm.loadingNewAddedUserPieChartText = '暂无数据'
        }
        vm.loadnewAddedUserPieChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

  
      this.getTableList() 

    },
    // 新增注册数
    loadnewAddedUsersLineChart(chartData) {
      var vm = this;
      var chartId = "newAddedUsersLineChart";
      // 基于准备好的dom，初始化echarts实例
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var lineChart = chartData.lineChart

      
      let yAxis = {
          name: '个',
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        };
 
        // 从0开始，最小间隔为整数。
        let maxVal = Math.max(...lineChart.seriesData)
        if (maxVal < 5) {
          yAxis.splitNumber = maxVal || 1
          yAxis.minInterval = 1
          
        }

      // 绘制图表
      var option = {
        color: ["#44cd8a", "#5eb2ec", "#b6a4dd"],
        tooltip: {
          trigger: 'axis'
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
        yAxis: yAxis,
        series: [{
          name: lineChart.legendData[0],
          type: 'line',
          smooth: true,
          itemStyle: {
            normal: {
              color: "#44cd8a"
            }
          },
          data: lineChart.seriesData
        }]
      };

      vm[chartId].setOption(option)
    },

    // 新增注册数
    loadnewAddedUserPieChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.newAddedUserPieChart = echarts.init(vm.$refs.newAddedUserPieChart);

      var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          //text: "新增注册数",
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
          name: "新增注册数",
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data: pieChart.seriesData,

          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.newAddedUserPieChart.setOption(option)
    }
    
  },
  mounted() {
    var vm = this;
    var platform = '',
      startDate = '',
      endDate = '';


    vm.loadAllData();

    window.addEventListener('resize', chartsResize, false);

    var resizeTimer;

    function chartsResize() {
      if (resizeTimer) {
        clearTimeout(resizeTimer)
      }
      resizeTimer = setTimeout(function () {
        vm.newAddedUsersLineChart && vm.newAddedUsersLineChart.resize()
        vm.newAddedUserPieChart && vm.newAddedUserPieChart.resize()
        vm.registerUserAreaMapChart && vm.registerUserAreaMapChart.resize()
        vm.registerUserAreaBarChart && vm.registerUserAreaBarChart.resize()


      }, 100);
    }

  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingNewAddedUsersLineChart: false,
      loadingNewAddedUsersLineChartText: "加载中...",
      loadingNewAddedUserPieChart: false,
      loadingNewAddedUserPieChartText: "加载中...",
      loadingTableData: false,
      loadingTODOChart: false,
      currentPage: 1,
      pageSize: 10,
      totalCount: 0,
      tableData: {}
    }
  }
}


</script>

<style>
.increate-user-page {
  padding-bottom: 50px;
}
.register-table-container {
  padding: 20px;
}

.text-right {
  text-align: right;
}

.margin-right-20 {
  margin-right: 20px;
}

.el-pagination {
  text-align: center;
  padding-top: 15px;
}
</style>
