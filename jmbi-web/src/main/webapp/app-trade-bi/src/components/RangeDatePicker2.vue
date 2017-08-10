<template>
  <el-row class="jm-grid-box-border-radius jm-grid-box-bg-white jm-grid-border-1px jm-padding-15X20 jm-margin-top20 jm-margin-bottom20 primary-timer-filter">
    <el-col :span="12">
      <div class="grid-content">
        <span>时间段选择：</span>
        <el-button :type="currentMonth" @click="selectDatePeriod(1)">本月</el-button>
        <el-button :type="PreviousMonth" @click="selectDatePeriod(2)">上月</el-button>
  
      </div>
    </el-col>
    <el-col :span="12">
      <div class="grid-content clearfix">
        <el-date-picker class="right" :editable="false" :clearable="false" v-model="global_time_filter_value" type="daterange" :picker-options="globalPickerOptions" placeholder="选择时间范围" @change="dateChanged" align="right">
        </el-date-picker>
      </div>
    </el-col>
  </el-row>
</template>
<script>
import util from '@/service/util'

// 当前选中的按钮：本月，上月
let activedButtonPeriod = 1;

export default {
  name: 'rangeDatePicker2',
  created() {
    // 初始化开始时间为本月
    const start = new Date();
    start.setDate(1)
    this.global_time_filter_value[0] = start;

    activedButtonPeriod = 1;

    this.loadDatePeriodByQuery()
  },
  data() {
    return {
      currentMonth: 'primary',
      PreviousMonth: '',
      global_time_filter_value: [new Date(), new Date()],
      globalPickerOptions: {
        shortcuts: [{
          text: '本月',
          onClick(picker) {
            setPickerDate(picker, 1)
          }
        }, {
          text: '上月',
          onClick(picker) {
            setPickerDate(picker, 2)
          }
        }]
      },
    }
  },
  methods: {
    dateChanged() {
      //var period = activedButtonPeriod
      
      var startDate = this.global_time_filter_value[0].format(),
        endDate = this.global_time_filter_value[1].format();
       
      this.$router.push({
        path: this.$router.path,
        query: {
          startDate,
          endDate
        }
      })
      this.$emit('selectedRangeDate', {
        startDate,
        endDate
      });

      var periodIndex = this.getPeriodIndex(this.global_time_filter_value[0], this.global_time_filter_value[1])
      this.updateFilterButtonState(periodIndex);
      activedButtonPeriod = -1; //clear
    },
    selectDatePeriod(index) {
      activedButtonPeriod = index
      this.global_time_filter_value = getStartEnd(index)
      this.updateFilterButtonState(activedButtonPeriod);
    },
    loadDatePeriodByQuery() {
      let query = this.$route.query;
      if (query.startDate && query.endDate) {
        let startDate = query.startDate
        let endDate = query.endDate
        startDate = util.parseDate(startDate)
        endDate = util.parseDate(endDate)

        // 如果是本月，上月，则选中对应的按钮
        var periodIndex = this.getPeriodIndex(startDate, endDate)

        activedButtonPeriod = -1; // 如果是自定义时间选择区间，则取消按钮的高亮选中状态。
        this.updateFilterButtonState(periodIndex);
        this.global_time_filter_value = [startDate, endDate]

      }
    },
    getPeriodIndex(startDate, endDate) {
      // 如果是本月，上月，则选中对应的按钮
        var periodIndex = util.daydiff(startDate, endDate)

        // 本月
        if (periodIndex + 1 === endDate.getDate() && endDate.format() === new Date().format()) {

          periodIndex = 1
        } else {
          var _end = new Date();
          var month = _end.getMonth()
          _end.setMonth(month, 0)

          var _begin = new Date()
          _begin.setMonth(month - 1, 1)

          // 上月
          if (_end.format() === endDate.format() && startDate.format() === _begin.format()) {
            periodIndex = 2
          } else {
            periodIndex = -1
          }
        }

        return periodIndex;
    },
    updateFilterButtonState(index) {

      switch (index) {
        case 1:
          this.currentMonth = 'primary'
          this.PreviousMonth = ''
          break;
        case 2:
          this.currentMonth = ''
          this.PreviousMonth = 'primary'
          break;
        default:
          this.currentMonth = ''
          this.PreviousMonth = ''
          break;
      }
    }
  },
  mounted() {
    window.addEventListener('resize', fixSearchBar, false);
    window.addEventListener('scroll', fixSearchBar, false);

    var searchFilter = document.querySelector('.primary-timer-filter');

    function fixSearchBar() {

      if (document.body.scrollTop > 113) {
        searchFilter.style.position = 'fixed'
        searchFilter.style.top = '40px'
        searchFilter.style.left = '192px'
        searchFilter.style.right = '20px'
        searchFilter.style.zIndex = 40000
      } else {
        searchFilter.style.position = 'static'
        searchFilter.style.top = ''
        searchFilter.style.left = ''
        searchFilter.style.right = ''

      }


    }
  }
}

function setPickerDate(picker, index) {
  activedButtonPeriod = index;
  picker.$emit('pick', getStartEnd(index));
}

function getStartEnd(index) {
  const _date = new Date();
  let end = new Date();
  let start = new Date();

  if (index === 1) {
    // 本月
    start.setDate(1)
  } else {
    // 上月
    let _month = _date.getMonth()
    _date.setMonth(_month -1, 1)
    start = util.firstDayOfMonth(_date)
    end = util.lastDayOfMonth(_date)
  }

  return [start, end];

}

</script>

<style lang="scss">
.primary-timer-filter {
  .el-button {
    padding-top: 8px;
    padding-bottom: 8px;
  }
}
</style>
