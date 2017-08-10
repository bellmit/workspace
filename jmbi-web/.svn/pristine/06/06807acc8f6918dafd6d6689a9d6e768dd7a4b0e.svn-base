<template>
  <div class="">
  
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>成交分析</el-breadcrumb-item>
      <el-breadcrumb-item>物流商分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">物流方式
  
      </header>
  
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white jm-padding-20X20">
  
          <logistics-panel subTitle="物流货运Top-人民币" apiName="jytProvidersAnalysisTypeByRMBTop" :apiParams="apiParams" descendantsProperty="tableChart.seriesData" DataProcessServiceName="LogisticsTransportService"></logistics-panel>
  
        </div>
      </el-col>
    </el-row>
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">仓储分析
  
      </header>
  
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white jm-padding-20X20">
  
          <logistics-panel subTitle="仓储Top-人民币" apiName="jytProvidersWarehousingByRMBTop" :apiParams="apiParams" descendantsProperty="tableChart.seriesData" DataProcessServiceName="WarehousingService"></logistics-panel>
        </div>
      </el-col>
    </el-row>
  
  </div>
</template>
<script>

import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'
import logisticsPanel from '@/components/JYT/logisticsPanel'

import echarts from 'echarts';

export default {
  name: 'logistics',
  components: {
    rangeDatePicker,
    logisticsPanel
  },
  created() {
    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }
  },
  computed: {
    apiParams() {
      return {
        platform: this.$platformId,
        startDate: this.startDate,
        endDate: this.endDate,
      }
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



    },



  },
  mounted() {

    var vm = this;

    vm.loadAllData()


  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format()
    }
  }
}

</script>

<style lang="scss">
.logistics-page {
  padding-bottom: 50px;
  margin-bottom: 50px;
}
</style>
