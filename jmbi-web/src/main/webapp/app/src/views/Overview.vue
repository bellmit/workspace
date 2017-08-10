<template>
  <div>
  
    <el-breadcrumb separator=">" class="jm-margin-bottom20">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/home' }">聚贸通平台</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>总览</el-breadcrumb-item>
    </el-breadcrumb>
  
    <el-row :gutter="20">
      <header style="padding: 0 10px 5px;">历史累计：截止{{yesterday}}
  
      </header>
      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-1 ">
  
            <p>
              <span class="number">{{summaryData.amount}}</span>
              <span class="unit">万美元</span>
            </p>
            <p class="title">货值</p>
  
          </div>
        </div>
      </el-col>
  
      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-2 ">
  
            <p>
              <span class="number">{{summaryData.count}}</span>
              <span class="unit">票</span>
            </p>
            <p class="title">订单数</p>
          </div>
        </div>
      </el-col>
  
      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-3 ">
  
            <p>
              <span class="number">{{summaryData.customerCount}}</span>
              <span class="unit">家</span>
            </p>
            <p class="title">客户企业数</p>
          </div>
        </div>
      </el-col>
  
      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-4 ">
  
            <p>
              <span class="number">{{summaryData.partnerCount}}</span>
              <span class="unit">家</span>
            </p>
            <p class="title">拍档企业数</p>
          </div>
        </div>
      </el-col>
  
      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-5 ">
  
            <p>
              <span class="number">{{summaryData.partnerIncome}}</span>
              <span class="unit">万元</span>
            </p>
            <p class="title">拍档收入</p>
          </div>
        </div>
      </el-col>
  
      <el-col :xs="8" :sm="8" :md="8" :lg="4">
        <div class="grid-content">
          <div class="box-summary summary-bg-6 ">
  
            <p>
              <span class="number">{{summaryData.customerPay}}</span>
              <span class="unit">万元</span>
            </p>
            <p class="title">客户支出</p>
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!-- end of summary -->
  
    <el-row class="jm-grid-border-1px overview-chart-container">
      <header class="panel-header overview-panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">
        <span class="title">平台企业总览</span>
  
        <div class="overview-filter">统计区域：
          <el-select v-model="selectedArea" change="areaChanged" placeholder="请选择">
            <el-option v-for="item in areaOptions" :label="item.label" :value="item.value" :key="item">
            </el-option>
          </el-select>
        </div>
  
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart" v-loading="overviewLevelPieChartEmpty" element-loading-text="本地区暂无企业">
            <div class="content" ref='overviewLevelPieChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box  chart " v-loading="overviewAreaPieChartEmpty" element-loading-text="本地区暂无企业">
            <div class="content" ref='overviewAreaPieChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
  
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box  chart " v-loading="overviewCustomerPieChartEmpty" element-loading-text="本地区暂无企业">
            <div class="content" ref='overviewCustomerPieChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
  
  </div>
</template>

<script>
import util from '@/service/util'

import {
  getOverview,
  starLevel,
  partner,
  customer
} from '@/service/api'

import echarts from 'echarts';

export default {
  name: 'overview',
  components: {
  },
  watch: {
    // 如果 selectedArea 发生改变，这个函数就会运行
    selectedArea: function (newAreaCode) {
      this.selectedAreaCode = newAreaCode
      this.areaChanged()
    }
  },
  methods: {
    areaChanged() {
      this.loadStarLevel();
      this.loadPartner();
      this.loadCustomer();
    },
    loadStarLevel() {
      var vm = this;
      var area = vm.selectedAreaCode;
      starLevel(area).then(function (response) {
        var chartData = response.data;
        var chartId = 'overviewLevelPieChart'
        vm.loadChartOption(chartData, chartId)
      })
        .catch(function (error) {
          console.log(error);
        });
    },
    loadPartner() {
      var vm = this;
      var area = vm.selectedAreaCode;
      partner(area).then(function (response) {
        var chartData = response.data;
        var chartId = 'overviewAreaPieChart'
        vm.loadChartOption(chartData, chartId)
      })
        .catch(function (error) {
          console.log(error);
        });
    },
    loadCustomer() {
      var vm = this;
      var area = vm.selectedAreaCode;
      customer(area).then(function (response) {
        var chartData = response.data;
        var chartId = 'overviewCustomerPieChart'
        vm.loadChartOption(chartData, chartId)
      })
        .catch(function (error) {
          console.log(error);
        });
    },
    loadChartOption(chartData, chartId) {
      var vm = this;
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var seriesData = chartData.seriesData || [];
      if (seriesData.length === 0) {
        vm[chartId + 'Empty'] = true;
      } else {
        vm[chartId + 'Empty'] = false;
      }

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          text: chartData.title,
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
          name: chartData.seriesName,
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data: chartData.seriesData,
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };
      vm[chartId].setOption(option)
    }
  },
  data() {
    return {
      overviewLevelPieChartEmpty: false,
      overviewAreaPieChartEmpty: false,
      overviewCustomerPieChartEmpty: false,
      overviewLevelPieChart: null,
      overviewAreaPieChart: null,
      overviewCustomerPieChart: null,
      summaryData: {},
      areaOptions: [{ value: '86', label: '全国' },
      { value: '86011', label: '北京市' },
      { value: '86012', label: '天津市' },
      { value: '86013', label: '河北省' },
      { value: '86014', label: '山西省' },
      { value: '86015', label: '内蒙古自治区' },
      { value: '86021', label: '辽宁省' },
      { value: '86022', label: '吉林省' },
      { value: '86023', label: '黑龙江省' },
      { value: '86031', label: '上海市' },
      { value: '86032', label: '江苏省' },
      { value: '86033', label: '浙江省' },
      { value: '86034', label: '安徽省' },
      { value: '86035', label: '福建省' },
      { value: '86036', label: '江西省' },
      { value: '86037', label: '山东省' },
      { value: '86041', label: '河南省' },
      { value: '86042', label: '湖北省' },
      { value: '86043', label: '湖南省' },
      { value: '86044', label: '广东省' },
      { value: '86045', label: '广西壮族自治区' },
      { value: '86046', label: '海南省' },
      { value: '86050', label: '重庆市' },
      { value: '86051', label: '四川省' },
      { value: '86052', label: '贵州省' },
      { value: '86053', label: '云南省' },
      { value: '86054', label: '西藏自治区' },
      { value: '86061', label: '陕西省' },
      { value: '86062', label: '甘肃省' },
      { value: '86063', label: '青海省' },
      { value: '86064', label: '宁夏回族自治区' },
      { value: '86065', label: '新疆维吾尔自治区' },
      { value: '86071', label: '台湾省' },
      { value: '86081', label: '香港特别行政区' },
      { value: '86082', label: '澳门特别行政区' }],
      selectedArea: '全国',
      selectedAreaCode: '86',
      yesterday: util.getYesterday()
    }
  },
  mounted() {
    var vm = this;

    getOverview().then(function (response) {
      vm.summaryData = response.data;
    })
      .catch(function (error) {
        console.log(error);
      });

    this.loadStarLevel();
    this.loadPartner();
    this.loadCustomer();

    window.addEventListener('resize', chartsResize, false);

    function chartsResize() {
      if (vm.overviewLevelPieChart) {
        vm.overviewLevelPieChart.resize()
      }

      if (vm.overviewAreaPieChart) {
        vm.overviewAreaPieChart.resize()
      }

      if (vm.overviewCustomerPieChart) {
        vm.overviewCustomerPieChart.resize()
      }

    }
  }
}

</script>

<style lang="scss">
.box-summary {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-end;
  color: #fff;
  width: 100%;
  height: 135px;
  border-radius: 5px;
  p {
    margin-top: 1em;
    margin-bottom: 1em;
  }
  .number {
    font-size: 28px;
  }
  .unit {
    font-size: 14px;
    margin-left: 10px;
    margin-right: 30px;
  }
  .title {
    font-size: 18px;
    margin-right: 30px;
  }
}

@for $i from 1 through 6 {
  .summary-bg-#{$i} {
    background: url(../assets/images/overview/summary-#{$i}-bg.png) no-repeat;
    background-size: 100% 100%;
  }
}

@media (max-width: 1199px) {
  .box-summary {
    margin-bottom: 20px;
  }
}

.overview-panel-header {
  display: flex;
  justify-content: flex-end;
  .title {
    align-self: flex-start;
    margin-right: auto;
  }
}

.overview-filter {
  font-size: 14px;
  .el-input__inner {
    height: 32px;
  }
}

.overview-chart-container .el-loading-spinner .circular {
  display: none;
}

.overview-chart-container .el-loading-mask {
  z-index: 1000;
  background-color: rgba(255, 255, 255, 0);
}
</style>
