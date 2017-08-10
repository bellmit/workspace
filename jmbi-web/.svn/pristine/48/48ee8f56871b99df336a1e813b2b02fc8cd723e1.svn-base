<template>
  <div>

    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营分析</el-breadcrumb-item>
      <el-breadcrumb-item>国家馆</el-breadcrumb-item>
    </el-breadcrumb>

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>

    <data-overview :platform="platformId" :startDate="startDate" :endDate="endDate"></data-overview>
    <baidu-index-trend :platform="platformId" :startDate="startDate" :endDate="endDate"></baidu-index-trend>

  </div>
</template>
<script>

import {
  baiduTrend
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'
import DataOverview from '@/components/ZZ/DataOverview'
import BaiduIndexTrend from '@/components/ZZ/BaiduIndexTrend'

import echarts from 'echarts';

export default {
  name: 'NationalPavilion',
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
    }
  },
  data() {
    return {
      platformId: '102300',
      startDate: new Date().format(),
      endDate: new Date().format()
    }
  }
}

</script>

<style lang="scss">


</style>
