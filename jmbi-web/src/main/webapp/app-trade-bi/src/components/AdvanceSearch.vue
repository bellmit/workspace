<template>
<div>
  <div class="order-search-basic-bar jm-text-align-right no-selection-text" v-if="!advanceSearch">
      <span class="button" v-bind:class="{ 'jm--color-primary': currentSelectedPeriod === 'week' }" @click="selectPeriod('week', true)">本周</span>
      <span class="button" v-bind:class="{ 'jm--color-primary': currentSelectedPeriod === 'month' }" @click="selectPeriod('month', true)">本月</span>
      <span class="button" v-bind:class="{ 'jm--color-primary': currentSelectedPeriod === 'quarter' }" @click="selectPeriod('quarter', true)">本季</span>
      <span class="button" v-bind:class="{ 'jm--color-primary': currentSelectedPeriod === 'year' }" @click="selectPeriod('year', true)">本年</span>
      <span class="button" @click="advanceSearch = true">高级检索<i class="el-icon-search"></i></span>
    </div>
    <div class="order-advance-search-bar" v-if="advanceSearch">

      <div class="order-date-type-btn-list no-selection-text">
        <span class="icon-date-type icon-week" v-bind:class="{ 'is-active': currentSelectedPeriod === 'week' }" @click="selectPeriod('week')"></span>
        <span class="icon-date-type icon-month " v-bind:class="{ 'is-active': currentSelectedPeriod === 'month' }" @click="selectPeriod('month')"></span>
        <span class="icon-date-type icon-quarter " v-bind:class="{ 'is-active': currentSelectedPeriod === 'quarter' }" @click="selectPeriod('quarter')"></span>
        <span class="icon-date-type icon-year " v-bind:class="{ 'is-active': currentSelectedPeriod === 'year' }" @click="selectPeriod('year')"></span>
      </div>

      <div class="order-date-picker-range-list">
        <span class="">统计时间：</span>

        <div class="week-group" v-show=" currentSelectedPeriod === 'week' ">
          <el-date-picker 
            :editable="false" 
            v-model="weekFrom" 
            type="week" 
            format="yyyy第WW周" 
            placeholder="选择周">
          </el-date-picker>

          <span> - </span>

          <el-date-picker 
            :editable="false" 
            v-model="weekTo" 
            type="week" 
            format="yyyy第WW周" 
            placeholder="选择周">
          </el-date-picker>
        </div>

        <div class="month-group" v-show=" currentSelectedPeriod === 'month' ">
         
          <el-date-picker 
            :editable="false" 
            v-model="monthFrom" 
            type="month" 
            format="yyyy年MM月" 
            placeholder="选择月">
          </el-date-picker>

          <span> - </span>

          <el-date-picker :editable="false" 
            v-model="monthTo" 
            type="month" 
            placeholder="选择月"
            format="yyyy年MM月">
          </el-date-picker>
        </div>

        <div class="quarter-group" v-show=" currentSelectedPeriod === 'quarter' ">
         

          <quarter-picker v-on:selectedQuarter="getSelectedQuarterFrom"></quarter-picker>

          <span> - </span>

          <quarter-picker v-on:selectedQuarter="getSelectedQuarterTo"></quarter-picker>
        </div>

        <div class="year-group" v-show=" currentSelectedPeriod === 'year' ">
          <el-date-picker 
            :editable="false" 
            v-model="yearFrom" 
            align="right" 
            type="year" 
            format="yyyy年" 
            placeholder="选择年">
          </el-date-picker>

          <span> - </span>

          <el-date-picker 
            :editable="false" 
            v-model="yearTo" 
            align="right" 
            type="year" 
            format="yyyy年" 
            placeholder="选择年">
          </el-date-picker>
        </div>

        <area-picker v-on:selectedCustomItemCode="getCustomCode"></area-picker>

        <el-button type="primary no-selection-text"><span @click="search">统计</span></el-button>

        <span class="btn-order-search-return no-selection-text" @click="basicSearch"></span>
      </div>

    </div>
</div>
</template>

<script>
import util from '@/service/util'

import areaPicker from '@/components/AreaPicker'
import quarterPicker from '@/components/QuarterPicker'

export default {
  name: 'advanceSearch',
  components: {
    areaPicker,
    quarterPicker
  },
  methods: {
    basicSearch() {
      var vm = this;
      vm.advanceSearch = false

      reset();

        this.startDate = util.firstDayOfWeek().format()
        this.endDate = util.lastDayOfWeek().format()

        var searchCriteria = {
          timeType: this.currentSelectedPeriod,
          startDate: this.startDate,
          endDate: this.endDate,
          customsCode: this.customsCode,
          isSelectedKouan: this.isSelectedKouan,
          interval: 0
        }
        this.$emit('searchCriteria', searchCriteria);

      
      function reset() {
          vm.currentSelectedPeriod = 'week'
          vm.isSelectedKouan = false
          vm.advanceSearch = false
          vm.customsCode = 'all'
          vm.weekFrom = ''
          vm.weekTo = ''
          vm.monthFrom = ''
          vm.monthTo = ''
          vm.quarterFrom = ''
          vm.quarterTo = ''
          vm.yearFrom = ''
          vm.yearTo = ''
          vm.startDate = ''
          vm.endDate = ''
          vm.sameStartEnd = true
      }

    },
    emptyWarning() {
      this.$message({
        message: '请选择时间范围',
        type: 'warning'
      });
    },
    rangeWarning() {
      this.$message({
        message: '开始时间不能大于结束时间',
        type: 'warning'
      });
    },
    search() {
      var vm = this;
      var period = this.currentSelectedPeriod;

      if (period === 'week') {


        if (!this.weekFrom || !this.weekTo) {
          this.emptyWarning()
          return;
        }

        var weekDayFrom = util.getDayByWeek(this.weekFrom)
        var weekDayTo = util.getDayByWeek(this.weekTo)

        if (weekDayFrom - weekDayTo === 0) {
          this.sameStartEnd = true;
        } else {
          this.sameStartEnd = false;
        }


        if (weekDayFrom > weekDayTo) {
          this.rangeWarning()
          return;
        }

        this.startDate = util.firstDayOfWeek(weekDayFrom).format()
        this.endDate = util.lastDayOfWeek(weekDayTo).format()


      }

      if (period === 'month') {

        if (!this.monthFrom || !this.monthTo) {
          this.emptyWarning()
          return;
        }

        if (this.monthFrom - this.monthTo === 0) {
          this.sameStartEnd = true;
        } else {
          this.sameStartEnd = false;
        }

        if (this.monthFrom > this.monthTo) {
          this.rangeWarning()
          return;
        }

        vm.startDate = util.firstDayOfMonth(vm.monthFrom).format();
        vm.endDate = util.lastDayOfMonth(vm.monthTo).format();
      }

      if (period === 'quarter') {


        if (!this.quarterFrom || !this.quarterTo) {
          this.emptyWarning()
          return;
        }

        if (this.quarterFrom - this.quarterTo === 0) {
          this.sameStartEnd = true;
        } else {
          this.sameStartEnd = false;
        }

        if (this.quarterFrom > this.quarterTo) {
          this.rangeWarning()
          return;
        }

        this.startDate = util.firstDayOfQuarter(this.quarterFrom).format()
        this.endDate = util.lastDayOfQuarter(this.quarterTo).format()
      }

      if (period === 'year') {

        if (!this.yearFrom || !this.yearTo) {
          this.emptyWarning()
          return;
        }

        if (this.yearFrom - this.yearTo === 0) {
          this.sameStartEnd = true;
        } else {
          this.sameStartEnd = false;
        }

        if (this.yearFrom > this.yearTo) {
          this.rangeWarning()
          return;
        }

        vm.startDate = util.firstDayOfYear(vm.yearFrom).format();
        vm.endDate = util.lastDayOfYear(vm.yearTo).format();
      }

      //
      var searchCriteria = {
        timeType: this.currentSelectedPeriod,
        startDate: this.startDate,
        endDate: this.endDate,
        customsCode: this.customsCode,
        isSelectedKouan: this.isSelectedKouan,
        interval: (this.sameStartEnd ? 0 : 1)
      }

      this.$emit('searchCriteria', searchCriteria);

    },
    getSelectedQuarterFrom(date) {
      this.quarterFrom = date;

    },
    getSelectedQuarterTo(date) {
      this.quarterTo = date;

    },
    getCustomCode({customCode,isKouan}) {
      this.customsCode = customCode
      this.isSelectedKouan = isKouan
    },
    selectPeriod(period, reloadData = false) {
      this.currentSelectedPeriod = period;

      // is advance search
      if (!reloadData) return;

      if (period === 'week') {
        this.startDate = util.firstDayOfWeek().format()
        this.endDate = util.lastDayOfWeek().format()
      }

      if (period === 'month') {
        this.startDate = util.firstDayOfMonth().format()
        this.endDate = util.lastDayOfMonth().format()
      }

      if (period === 'quarter') {
        this.startDate = util.firstDayOfQuarter().format()
        this.endDate = util.lastDayOfQuarter().format()
      }

      if (period === 'year') {
        this.startDate = util.firstDayOfYear().format()
        this.endDate = util.lastDayOfYear().format()
      }

      if (reloadData) {
        var searchCriteria = {
          timeType: this.currentSelectedPeriod,
          startDate: this.startDate,
          endDate: this.endDate,
          customsCode: this.customsCode,
          isSelectedKouan: this.isSelectedKouan,
          interval: 0
        }
        this.$emit('searchCriteria', searchCriteria);
      }

    },
  },
  data() {
    return {
      currentSelectedPeriod: 'week',
      isSelectedKouan: false,
      advanceSearch: false,
      customsCode: 'all',
      weekFrom: '',
      weekTo: '',
      monthFrom: '',
      monthTo: '',
      quarterFrom: '',
      quarterTo: '',
      yearFrom: '',
      yearTo: '',
      startDate: '',
      endDate: '',
      sameStartEnd: true
    }
  }
}

</script>

<style lang="scss">
  .order-search-basic-bar {
    height: 30px;
    line-height: 30px;
    .button {
      display: inline-block;
      margin-right: 20px;
      cursor: pointer;
      .el-icon-search {
        padding-left: 5px;
      }
    }
  }

  .order-date-type-btn-list,
  .order-date-picker-range-list {
    display: inline-block;
    vertical-align: middle;
  }
  
  .order-date-type-btn-list {
    padding-right: 10px;
    margin-right: 10px;
    border-right: 1px solid #b3b3b3;
    .icon-date-type {
      position: relative;
      cursor: pointer;
      top: 2px;
    }
  }

  .icon-date-type {
    display: inline-block;
    width: 21px;
    height: 21px;
    background: url(../assets/images/icon-date-list.png) no-repeat;
  }
  
  .icon-date-type+.icon-date-type {
    margin-left: 13px;
  }
  
  .icon-week {
    background-position: 0 0;
  }
  
  .icon-week.is-active {
    background-position: 0px -25px;
  }
  
  .icon-month {
    background-position: -23px 0px;
  }
  
  .icon-month.is-active {
    background-position: -23px -25px;
  }
  
  .icon-quarter {
    background-position: -46px 0px;
  }
  
  .icon-quarter.is-active {
    background-position: -46px -25px;
  }
  
  .icon-year {
    background-position: -69px 0px;
  }
  
  .icon-year.is-active {
    background-position: -69px -25px;
  }

  .week-group,
  .month-group,
  .quarter-group,
  .year-group {
    display: inline-block;
  }
  
  .quarter-group {
    .el-input {
      width: 140px;
    }
  }
  
  .order-date-picker-range-list {
    .el-date-editor.el-input {
      width: 140px;
    }
    .el-input__inner {
      height: 30px;
    }
    .el-button {
      margin-left: 6px;
      padding-top: 6px;
      padding-bottom: 6px;
    }
  }

  .btn-order-search-return {
      display: inline-block;
      margin-left: 26px;
      width: 16px;
      height: 16px;
      position: relative;
      top: 3px;
      cursor: pointer;
      background: url(../assets/images/icon-return.png) no-repeat;
    }
</style>