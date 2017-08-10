<template>
  <el-row class="jm-grid-box-border-radius jm-grid-box-bg-white jm-grid-border-1px jm-padding-15X20 jm-margin-top20 jm-margin-bottom20 primary-timer-filter">
    <el-col :span="12">
      <div class="grid-content">
        <span>时间段选择：</span>
        <el-button :type="latest1Days" @click="selectDatePeriod(1)">今日</el-button>
        <el-button :type="latest15Days" @click="selectDatePeriod(15)">最近15天</el-button>
        <el-button :type="latest30Days" @click="selectDatePeriod(30)">最近30天</el-button>
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

// 当前选中的按钮：今天，最近15天，最近30天中的一个
let activedButtonPeriod = 1;

export default {
  name: 'rangeDatePicker',
  created() {
    // 初始化开始时间为今天之前
    const start = new Date();
    const period = 1;
    start.setTime(start.getTime() - 3600 * 1000 * 24 * (period - 1));
    this.global_time_filter_value[0] = start;

    activedButtonPeriod = period;

    this.loadDatePeriodByQuery()
  },
  data() {
    return {
      latest1Days: 'primary',
      latest15Days: '',
      latest30Days: '',
      global_time_filter_value: [new Date(), new Date()],
      globalPickerOptions: {
        shortcuts: [{
          text: '今日',
          onClick(picker) {
            setPickerDate(picker, 0)
          }
        }, {
          text: '最近15天',
          onClick(picker) {
            setPickerDate(picker, 14)
          }
        }, {
          text: '最近30天',
          onClick(picker) {
            setPickerDate(picker, 29)
          }
        }]
      },
    }
  },
  methods: {
    dateChanged() {
      var period = activedButtonPeriod
      this.updateFilterButtonState(period);
      var startDate = this.global_time_filter_value[0].format(),
        endDate = this.global_time_filter_value[1].format();
      this.$router.push({ path: this.$router.path, query: { startDate, endDate } })
      this.$emit('selectedRangeDate', { startDate, endDate });
      activedButtonPeriod = -1;
    },
    selectDatePeriod(period) {
      activedButtonPeriod = period

      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * (period - 1));

      this.global_time_filter_value = [start, end]
      this.updateFilterButtonState(activedButtonPeriod);
    },
    loadDatePeriodByQuery() {
      let query = this.$route.query;
      if (query.startDate && query.endDate) {
        let startDate = query.startDate
        let endDate = query.endDate
        startDate = util.parseDate(startDate)
        endDate = util.parseDate(endDate)

        // 如果是今日、最近15天、最近30天，则选中对应的按钮
        var periodIndex = util.daydiff(startDate,endDate)
        if (endDate.format() === new Date().format()) {
          if (periodIndex === 0) {
            periodIndex = 1
          } else if (periodIndex === 14) {
            periodIndex = 15
          } else if (periodIndex === 29) {
            periodIndex = 30
          }
        } else {
          periodIndex = -1
        }

        activedButtonPeriod = -1;// 如果是自定义时间选择区间，则取消按钮的高亮选中状态。
        this.updateFilterButtonState(periodIndex);
        this.global_time_filter_value = [startDate, endDate]

      }
    },
    updateFilterButtonState(period) {

      switch (period) {
        case 1:
          this.latest1Days = 'primary'
          this.latest15Days = ''
          this.latest30Days = ''
          break;
        case 15:
          this.latest1Days = ''
          this.latest15Days = 'primary'
          this.latest30Days = ''
          break;
        case 30:
          this.latest1Days = ''
          this.latest15Days = ''
          this.latest30Days = 'primary'
          break;
        default:
          this.latest1Days = ''
          this.latest15Days = ''
          this.latest30Days = ''
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

function setPickerDate(picker, period) {
  activedButtonPeriod = period + 1;

  const end = new Date();
  const start = new Date();
  start.setTime(start.getTime() - 3600 * 1000 * 24 * period);
  picker.$emit('pick', [start, end]);
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
