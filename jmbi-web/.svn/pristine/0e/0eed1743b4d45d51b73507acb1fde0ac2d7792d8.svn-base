<template>
  <div>
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>成交分析</el-breadcrumb-item>
      <el-breadcrumb-item>交易地域分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <map-bar apiName="jytAreaAmount" panelTitle="订单笔数地域分布" chartTitle="订单笔数" :apiParams="orderApiParams" :typeItems="TypeItems"></map-bar>
    <map-bar apiName="jytAreaAmount" panelTitle="订单金额地域分布" chartTitle="订单金额" :apiParams="moneyApiParams" :typeItems="TypeItems"></map-bar>
  
  </div>
</template>

<script>

import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker2'
import mapBar from '@/components/JYT/mapBar'

export default {
  name: 'dealingAnalysis',
  components: {
    rangeDatePicker,
    mapBar
  },
  created() {

    let query = this.$route.query;
    if (query.startDate && query.endDate) {
      this.startDate = query.startDate
      this.endDate = query.endDate
    }
  },
  computed: {
    orderApiParams() {
      return {
        startDate: this.startDate,
        endDate: this.endDate,
        currencyCode: '142',
        sortField: 'ordernum'
      }
    },
    moneyApiParams() {
      return {
        startDate: this.startDate,
        endDate: this.endDate,
        currencyCode: '142',
        sortField: 'ordertotal'
      }
    }
  },
  methods: {
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
    }
  },

  mounted() {

  },
  data() {
    return {
      TypeItems: [
        { id: 231, name: "公路" },
        { id: 232, name: "铁路" },
        { id: 233, name: "内河航运" },
        { id: 234, name: "海运" },
        { id: 237, name: "空运" }
      ],

      startDate: util.firstDayOfMonth().format(),
      endDate: new Date().format(),

    }
  }
}


</script>

<style lang="scss">

</style>
