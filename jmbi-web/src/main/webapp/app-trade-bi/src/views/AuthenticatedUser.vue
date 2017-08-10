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
      <el-breadcrumb-item>认证用户</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">认证用户情况
  
      </header>
      <div class="auth-user-botton-group">
        <el-button size="small" :type="primary0" @click="loadAuthUserOverview(0)" v-if="!isOneDay">浏览量</el-button>
        <el-button size="small" :type="primary1" @click="loadAuthUserOverview(1)" v-if="!isOneDay">独立访客</el-button>
        <el-button size="small" :type="primary2" @click="loadAuthUserOverview(2)">注册会员</el-button>
        <el-button size="small" :type="primary3" @click="loadAuthUserOverview(3)">三证审核</el-button>
        <el-button size="small" :type="primary4" @click="loadAuthUserOverview(4)">授权书</el-button>
  
      </div>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingAuthUserOverviewText === '暂无数据'}" v-loading="loadingAuthUserOverview" :element-loading-text="loadingAuthUserOverviewText">
            <div class="content" ref='authUserOverviewChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white three-certificate-audit-container">
  
          <div class="three-certificate-audit">
            <p class="title">三证审核历史累计：</p>
            <p class="amount">{{threeCertTotal}}</p>
          </div>
  
          <div class="box  chart row-group-last" :class="{'no-data': loadingThreeCertTotalPieText === '暂无数据'}" v-loading="loadingThreeCertTotalPie" :element-loading-text="loadingThreeCertTotalPieText">
            <div class="content" ref='threeCertTotalPieChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
    <el-row class="jm-grid-border-1px">
  
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingAuthUserPassedText === '暂无数据'}" v-loading="loadingAuthUserPassed" :element-loading-text="loadingAuthUserPassedText">
            <div class="content" ref='certAuditChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box  chart row-group-last" :class="{'no-data': loadingthreeInOneText === '暂无数据'}" v-loading="loadingthreeInOne" :element-loading-text="loadingthreeInOneText">
            <div class="content" ref='threeInOnePieChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 注册用户地域分布-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">三证、授权地域分布
  
      </header>
  
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingareaDistChinaMapText === '暂无数据'}" v-loading="loadingareaDistChinaMap" :element-loading-text="loadingareaDistChinaMapText">
            <div class="content" ref='threeCertAuditMapChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <div class="box  chart row-group-last" :class="{'no-data': loadingareaDistBarChartText === '暂无数据'}" v-loading="loadingareaDistBarChart" :element-loading-text="loadingareaDistBarChartText">
            <div class="content" ref='threeCertAuditBarChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
  </div>
</template>

<script>
import {
  authUserOverview,
  authUserCert3,
  authUserPassed,
  threeInOne,
  areaDistChinaMap,
  areaDistBarChart
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'authUser',
  components: {
    rangeDatePicker
  },
  created() {

    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }

    this.setActiveItem();
  },
  computed: {
    isOneDay() {
      return this.startDate === this.endDate
    }
  },
  methods: {
    setActiveItem() {
      var vm = this;
      if (vm.isOneDay) {
       
        if (vm.overviewType === 0 || vm.overviewType === 1) {
            vm.overviewType = 2;
      
            vm.primary0 = vm.getPrimary(0)
            vm.primary1 = vm.getPrimary(1)
            vm.primary2 = vm.getPrimary(2)
            vm.primary3 = vm.getPrimary(3)
            vm.primary4 = vm.getPrimary(4)
      
   
        }
        
      }
    },
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.loadAllData();
    },
    loadAllData() {
      var vm = this;

       vm.setActiveItem();

      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate,
        type = vm.overviewType;

       

      vm.loadAuthUserOverview(type)

      vm.loadingThreeCertTotalPie = true
      vm.loadingThreeCertTotalPieText = '加载中...'
      authUserCert3(platform).then(function (response) {
        vm.loadingThreeCertTotalPie = false
        var chartData = response.data
        if (chartData.pieChart.seriesData.length === 0) {
          vm.loadingThreeCertTotalPie = true
          vm.loadingThreeCertTotalPieText = '暂无数据'
        }

        vm.threeCertTotal = chartData.counts;
        vm.loadthreeCertTotalPieChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingAuthUserPassed = true
      vm.loadingAuthUserPassedText = '加载中...'
      authUserPassed({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingAuthUserPassed = false
        var chartData = response.data
        if (chartData.lineChart.seriesData.length === 0) {
          vm.loadingAuthUserPassed = true
          vm.loadingAuthUserPassedText = '暂无数据'
        }


        vm.loadcertAuditChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingthreeInOne = true
      vm.loadingthreeInOneText = '加载中...'
      threeInOne({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingthreeInOne = false
        var chartData = response.data
        if (chartData.pieChart.seriesData.length === 0) {
          vm.loadingthreeInOne = true
          vm.loadingthreeInOneText = '暂无数据'
        }


        vm.loadthreeInOnePieChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingareaDistChinaMap = true
      vm.loadingareaDistChinaMapText = '加载中...'
      areaDistChinaMap({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingareaDistChinaMap = false
        var chartData = response.data
        // if (chartData.pieChart.seriesData.length === 0) {
        //   vm.loadingareaDistChinaMap = true
        //   vm.loadingareaDistChinaMapText = '暂无数据'
        // }


        vm.loadthreeCertAuditMapChart(chartData.pieChart)

      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingareaDistBarChart = true
      vm.loadingareaDistBarChartText = '加载中...'
      areaDistBarChart({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingareaDistBarChart = false
        var chartData = response.data
        if (chartData.lineChart.seriesData.length === 0) {
          vm.loadingareaDistBarChart = true
          vm.loadingareaDistBarChartText = '暂无数据'
        }


        vm.loadthreeCertAuditBarChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });



    },
    getPrimary(type) {
      return this.overviewType == type ? "primary" : '';
    },
    loadAuthUserOverview(type) {
      var vm = this;
      var platform = vm.$platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;
      vm.overviewType = type;

      vm.primary0 = vm.getPrimary(0)
      vm.primary1 = vm.getPrimary(1)
      vm.primary2 = vm.getPrimary(2)
      vm.primary3 = vm.getPrimary(3)
      vm.primary4 = vm.getPrimary(4)

      vm.loadingAuthUserOverview = true
      vm.loadingAuthUserOverviewText = '加载中...'
      authUserOverview({
        platform,
        startDate,
        endDate,
        type
      }).then(function (response) {
        vm.loadingAuthUserOverview = false
        var chartData = response.data
        if (chartData.lineChart.seriesData.length === 0) {
          vm.loadingAuthUserOverview = true
          vm.loadingAuthUserOverviewText = '暂无数据'
        }
        vm.loadauthUserOverviewChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

    },
    // 认证用户情况
    loadauthUserOverviewChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.authUserOverviewChart = echarts.init(vm.$refs.authUserOverviewChart);

      var lineChart = chartData.lineChart

      
      let yAxis = {
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
          name: lineChart.legendData,
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

      vm.authUserOverviewChart.setOption(option)
    },

    // 三证审核历史累计
    loadthreeCertTotalPieChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.threeCertTotalPieChart = echarts.init(vm.$refs.threeCertTotalPieChart);
      // console.log('用户登录情况', chartData)
      var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1790cf', '#3ab882', '#fcb738', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          //text: chartData.title,
          left: 'center',
          bottom: '5%',
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
          name: "三证审核历史累计",
          type: 'pie',
          radius: ['30%', '60%'],
          center: ['50%', '55%'],
          data: pieChart.seriesData,
          // data: [
          //   { value: 335, name: '直接访问' },
          //   { value: 310, name: '邮件营销' },
          //   { value: 234, name: '联盟广告' },
          //   { value: 135, name: '视频广告' },
          //   { value: 1548, name: '搜索引擎' }
          // ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.threeCertTotalPieChart.setOption(option)
    },
    // 认证审核通过
    loadcertAuditChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.certAuditChart = echarts.init(vm.$refs.certAuditChart);
      
      var lineChart = chartData.lineChart
      
      
      let yAxis = {
            type: 'value'
          };
 
        // 从0开始，最小间隔为整数。
        let maxVal = Math.max(...lineChart.seriesData)
        if (maxVal < 5) {
          yAxis.splitNumber = maxVal || 1
          yAxis.minInterval = 1
          
        }

      // 绘制图表
      var option = {
        color: ['#1790cf', '#6B9C29'],
        title: {
          text: '认证审核通过',
          textStyle: {
            fontSize: 16,
            fontWeight: 'normal'
          },
          left: 10,
          top: 10

        },
        tooltip: {
          trigger: 'axis',
        },

        // legend: {
        //   data: ['数量', '金额']
        // },
        grid: {
          top: '20%',
          left: '3%',
          right: '7%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: lineChart.xAxisData,
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: yAxis,
        series: [

          {
            name: '认证审核通过',
            type: 'bar',
            barMaxWidth: '20px',
            data: lineChart.seriesData
          }

        ]
      };

      vm.certAuditChart.setOption(option)
    },

    // 三证合一比例 饼图
    loadthreeInOnePieChart(chartData) {
      var vm = this
      // 基于准备好的dom，初始化echarts实例
      vm.threeInOnePieChart = echarts.init(vm.$refs.threeInOnePieChart);

      var pieChart = chartData.pieChart

      // 绘制图表
      var option = {
        color: ['#1bb2d8', '#1790cf', '#fcb738', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          text: '三证合一比例',
          textStyle: {
            fontSize: 16,
            fontWeight: 'normal'
          },
          left: 10,
          top: 10

        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          left: 'center',
          top: '10%',
          data: pieChart.legendData//['直接访问', '邮件营销']
        },
        series: [{
          name: '三证合一比例',
          type: 'pie',
          radius: '50%',
          center: ['50%', '50%'],
          data: pieChart.seriesData,
          // data: [
          //   { value: 335, name: '直接访问' },
          //   { value: 310, name: '邮件营销' }

          // ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.threeInOnePieChart.setOption(option)

      // vm.threeInOnePieChart.on('click', function (obj) {
      //   openInNewTab(obj.name);
      // })
    },

    // 三证审核地域分布
    loadthreeCertAuditMapChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.threeCertAuditMapChart = echarts.init(vm.$refs.threeCertAuditMapChart);

      // function randomData() {
      //   return Math.round(Math.random() * 1000);
      // }
      let maxValue = 0;
      chartData.seriesData.forEach(function (item) {
        let v = Number(item.value)
        if (v > maxValue) {
          maxValue = v;
        }
      })

      if (maxValue === 0) {
        maxValue = 255;
      }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'item'
        },
        visualMap: {
          // orient: 'horizontal',
          inverse: 'true',
          min: 0,
          max: maxValue,// chartData.max, // todo
          // left: '65px',
          // top: '15px',
          text: ['高', '低'], // 文本，默认为数值文本
          inRange: {
            color: ['#e0ffff', '#006edd']
          }
        },
        geo: {
          map: 'china',
          roam: true,
          label: {
            normal: {
              show: true,
              textStyle: {
                color: 'rgba(0,0,0,0.4)'
              }
            }
          },
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
          name: '三证、授权地域分布',
          type: 'map',
          geoIndex: 0,
          data: chartData.seriesData
        }]
      };

      vm.threeCertAuditMapChart.setOption(option)
    },

    // 三证审核地域分布
    loadthreeCertAuditBarChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.threeCertAuditBarChart = echarts.init(vm.$refs.threeCertAuditBarChart);

      var lineChart = chartData.lineChart
      let xAxis = {
          type: 'value',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#008acd'
            }
          }
        };
        
        // 从0开始，最小间隔为整数。
        let maxVal = Math.max(...lineChart.seriesData)
        if (maxVal < 5 && maxVal > 0) {
          xAxis.splitNumber = maxVal
          xAxis.minInterval = 1
          
        }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          top: '8%',
          left: '5%',
          right: '5%',
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
              color: '#008acd'
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
          data: lineChart.yAxisData.reverse()
        },
        series: [{
          name: '三证、授权地域分布',//lineChart.legendData,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#1790cf',
              barBorderRadius: 2
            }
          },
          data: lineChart.seriesData.reverse()
        }]
      };

      vm.threeCertAuditBarChart.setOption(option)
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
        vm.authUserOverviewChart && vm.authUserOverviewChart.resize()
        vm.threeCertTotalPieChart && vm.threeCertTotalPieChart.resize()
        vm.certAuditChart && vm.certAuditChart.resize()
        vm.threeInOnePieChart && vm.threeInOnePieChart.resize()
        vm.threeCertAuditMapChart && vm.threeCertAuditMapChart.resize()
        vm.threeCertAuditBarChart && vm.threeCertAuditBarChart.resize()


      }, 100);
    }

  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      overviewType: 0, // 浏览量
      primary0: 'primary',
      primary1: '',
      primary2: '',
      primary3: '',
      primary4: '',
      threeCertTotal: 0,
      loadingAuthUserOverview: false,
      loadingAuthUserOverviewText: "加载中...",
      loadingThreeCertTotalPie: false,
      loadingThreeCertTotalPieText: '加载中...',
      loadingAuthUserPassed: false,
      loadingAuthUserPassedText: "加载中...",
      loadingthreeInOne: false,
      loadingthreeInOneText: "加载中...",
      loadingareaDistChinaMap: false,
      loadingareaDistChinaMapText: "加载中...",
      loadingareaDistBarChart: false,
      loadingareaDistBarChartText: "加载中...",
      loadingTODOChart: false,

    }
  }
}


</script>

<style>
.auth-user-botton-group {
  background: #fff;
  padding: 20px 65px;
}

.three-certificate-audit-container {
  position: relative;
}

.three-certificate-audit {
  position: absolute;
  top: -50px;
  left: 50%;
  transform: translate(-50%, 0);
}

.three-certificate-audit .title {
  font-size: 16px;
}

.three-certificate-audit .amount {
  font-size: 40px;
  color: #3ab882;
  text-align: center;
}
</style>
