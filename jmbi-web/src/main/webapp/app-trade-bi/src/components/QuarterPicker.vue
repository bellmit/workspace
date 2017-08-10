<template>

<div class="jm-quarter-picker-wrapper" v-on-clickaway="away">
  <el-input placeholder="选择季度" icon="date" v-model="dateValue" readonly="readonly" @click="showQuarter" @focus="showQuarter">
  </el-input>
  <transition name="el-zoom-in-top">
    <div v-show="showPicker" class="jm-quarter-picker el-picker-panel el-date-picker" x-placement="bottom-end">
      <div class="el-picker-panel__body-wrapper">
        <!---->
        <div class="el-picker-panel__body">
          <!---->
          <div class="el-date-picker__header">
            <button type="button" @click="prev" class="el-picker-panel__icon-btn el-date-picker__prev-btn el-icon-d-arrow-left"></button>
            <span class="el-date-picker__header-label">{{displayingYear}} 年</span>
            <button type="button" @click="next" class="el-picker-panel__icon-btn el-date-picker__next-btn el-icon-d-arrow-right"></button>

          </div>
          <div class="el-picker-panel__content">
            <table class="el-year-table">
              <tbody>
                <tr>
                  <td class="available" v-bind:class="{ 'current': selectedQuarter === 'Q1' }"><a class="cell" @click="selectQuarter(1)">Q1</a></td>
                  <td class="available" v-bind:class="{ 'current': selectedQuarter === 'Q2' }"><a class="cell" @click="selectQuarter(2)">Q2</a></td>
                  <td class="available" v-bind:class="{ 'current': selectedQuarter === 'Q3' }"><a class="cell" @click="selectQuarter(3)">Q3</a></td>
                  <td class="available" v-bind:class="{ 'current': selectedQuarter === 'Q4' }"><a class="cell" @click="selectQuarter(4)">Q4</a></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </transition>
</div>

</template>
<script>
import { mixin as clickaway } from 'vue-clickaway';

  export default {
    name: 'quarterPicker',
     mixins: [ clickaway ],
    data() {
      return {
        currentDate: new Date(),
        displayingYear: new Date().getFullYear(),
        dateValue: '',
        showPicker: false,
        selectedQuarter: ''
      }
    },
    computed: {
      // selectedQuarter() {
      //   var vm = this;
      //   var date = vm.currentDate;
      //   if (!date) return '';
      //   var currentYear = date.getFullYear();
      //   var currentMonth = date.getMonth();
      //   var quarter;
      //   if (vm.displayingYear === currentYear) {
      //       if (currentMonth >= 0 && currentMonth <= 3) {
      //         quarter = "Q1"
      //       } else if (currentMonth >= 4 && currentMonth <= 6) {
      //         quarter = "Q2"
      //       } else if (currentMonth >= 7 && currentMonth <= 9) {
      //         quarter = "Q3"
      //       } else if (currentMonth >= 10 && currentMonth <= 12) {
      //         quarter = "Q4"
      //       }
      //   } else {
      //     quarter = ''
      //   }
        
      //   return quarter;
      // }
    },
    methods: {
      away: function() {
        this.showPicker = false;
    },
      prev() {
        this.displayingYear -= 1;
      },
      next() {
        this.displayingYear += 1;
      },
      selectQuarter(quarter) {
        var vm = this;

        vm.selectedQuarter = 'Q' + quarter;
     
        var month;
        if (quarter === 1) {
          month = 0;
        } else if (quarter === 2) {
          month = 3;
        } else if (quarter === 3) {
          month = 6;
        } else if (quarter === 4) {
          month = 9;
        } 

        if (typeof month !== 'undefined') {
          vm.currentDate = new Date(vm.displayingYear, month)
          vm.dateValue = `${vm.currentDate.getFullYear()}年第${quarter}季`
          vm.showPicker = false
          this.$emit('selectedQuarter', vm.currentDate);
        }
      },
      showQuarter() {
        var vm = this;
        vm.showPicker = true;
      },
    }
  }

</script>

<style lang="scss">

.jm-quarter-picker-wrapper {
  position: relative;
  display: inline-block;
}

.jm-quarter-picker {
  width: 280px;
  position: absolute;
  left: 0;
  z-index: 39999999;
}

</style>
