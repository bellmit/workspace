<template>
  <div class="shop-activate-page">
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>店铺分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <master-detail-chart panelTitle="店铺商品发布数排行" unit="个" ItemType="商品发布数" :intergerxAxis="true" :startDate="startDate" :endDate="endDate" MasterApi="goodsCategoriesTop" DetailApi="goodsIndustryRatio"></master-detail-chart>
  
    <master-detail-chart panelTitle="店铺成交金额排行" unit="万元" ItemType="成交金额" :intergerxAxis="false" :startDate="startDate" :endDate="endDate" MasterApi="moneyTransactionMoneyTop" DetailApi="moneyIndustryRatio"></master-detail-chart>
  
  </div>
</template>

<script>

import {
  shopOpenTrend,
  shopOpenPerfect
} from '@/service/api'


import rangeDatePicker from '@/components/RangeDatePicker'
import masterDetailChart from '@/components/ZZ/masterDetailChart/_shopOperateAnalysis'

import echarts from 'echarts';


export default {
  name: 'GoodsAnalysis',
  components: {
    rangeDatePicker,
    masterDetailChart
  },
  created() {

    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }
  },
  methods: {
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
    },

  },
  mounted() {

  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
    }
  }
}


</script>

<style lang="scss">

</style>
