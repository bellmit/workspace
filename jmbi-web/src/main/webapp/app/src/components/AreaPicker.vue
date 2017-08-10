<template>

  <el-button class="jm-area-picker" @mouseover.native="handleHover" @mouseout.native="handleLeave"><span class="select-area-text">{{selectedCustomItemName}}</span><i class="el-icon-arrow-down"></i>
    <i class="el-icon el-icon-close" title="关闭" v-show="hovering" @click="closeAreaList"></i>
    <button class="js-area-select-all el-button el-button--primary no-selection-text" v-show="hovering" @click="selectedAreaAll">全国</button>
    <el-tabs type="border-card" class="area-list" v-show="hovering" v-model="activeTab" @tab-click="tabChanged">
      <el-tab-pane label="选择关区" name="guanqu">
        <ul class="clearfix">
          <li v-for="item in guanquList" :key="item">
            <span class="item" 
            v-bind:title="item.customsName"
            v-bind:class="{ 'current': selectedCustomItemCode === item.customsCode }"
            @click="selectCustomItem(item)">{{item.customsName}}</span>
            </li>
        </ul>

      </el-tab-pane>
      <el-tab-pane label="选择口岸" name="kouan">
        <ul class="clearfix">
          <li v-for="item in kouanList" :key="item"><span class="item"  
            v-bind:title="item.customsName"
            v-bind:class="{ 'current': selectedCustomSecondItemCode === item.customsCode }"
          @click="selectCustomSecondItem(item)">{{item.customsName}}</span></li>
        </ul>
      </el-tab-pane>
      
      
    </el-tabs>

  </el-button>

</template>
<script>
import {
  firstLevelCustoms,
  secondLevelCustoms
} from '@/service/api'

export default {
  name: 'areaPicker',
  created() {
    var vm = this;
    firstLevelCustoms().then(function (response) {
        var customs = response.data;
        vm.guanquList = customs.data
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  data() {
    return {
      selectedCustomItemCode: '',
      selectedCustomSecondItemCode: '',
      selectedCustomItemName: '全国',
      activeTab: 'guanqu',
      hovering: false,
      guanquList: [],
      kouanList: []
    }
  },
  methods: {
    selectedAreaAll() {
      var vm = this;
      vm.selectedCustomSecondItemCode = ''
      vm.selectedCustomItemName = '';
      vm.selectedCustomItemName = '全国';
      vm.broadcast('all')
      this.hovering = false
    },
    broadcast(customCode, isKouan = false) {
      this.$emit('selectedCustomItemCode', {customCode,isKouan});
    },
    closeAreaList() {
      this.hovering = false
    },
    handleHover() {
      this.hovering = true
    },
    handleLeave() {
      this.hovering = false
    },
    selectCustomItem(customItem) {
      var vm = this;
      vm.selectedCustomItemCode = customItem.customsCode
      vm.selectedCustomSecondItemCode = ''
      vm.selectedCustomItemName = customItem.customsName;

      vm.broadcast(customItem.customsCode)

      secondLevelCustoms(customItem.customsCode).then(function (response) {
          var customs = response.data;
          vm.kouanList = customs.data
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    selectCustomSecondItem(customItem) {
      var vm = this;
      vm.selectedCustomSecondItemCode = customItem.customsCode
      vm.selectedCustomItemName = customItem.customsName;
      var isKouan = true
      vm.broadcast(customItem.customsCode, isKouan)

      vm.hovering = false;
    },
    tabChanged() {

    }
  }
}

</script>

<style lang="scss">

.select-area-text {
  width: 125px;
  display: inline-block;
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.jm-area-picker {
  position: relative;
  padding-right: 25px;
  .el-icon-close {
    position: absolute;
    top: 50px;
    right: 30px;
    z-index: 39999999;
  }
  .el-icon-arrow-down {
    position: absolute;
    right: 5px;
    transition: all 0.5s;
  }
  .js-area-select-all {
    position: absolute;
    top: 43px;
    right: 80px;
    z-index: 39999999;
  }
  &:after {
    display: block;
    content: '';
    position: absolute;
    width: 100%;
    height: 100%;
    top: 50%;
    right: -1px;
    z-index: 19999999;
  }
  &:active {
    color: inherit;
    border-color: inherit;
  }
  &:hover {
    color: rgb(5, 122, 207);
    border-color: rgb(5, 122, 207);
    .el-icon-arrow-down {
      transform: rotate(180deg);
    }
  }
  .area-list {
    display: block;
    position: absolute;
    min-width: 462px;
    right: -1px;
    top: 35px;
    z-index: 10;
    border-radius: 3px;
    border: 1px solid #0086e9;
    color: #666;
    background: #fff;
    z-index: 29999999;
    ul {
      padding-left: 30px;
      margin: 0 0 15px;
    }
    li {
      box-sizing: border-box;
      float: left;
      list-style: none;
      text-align: left;
      width: 33.33333333%;
      margin-bottom: 5px;
      padding-right: 30px;
      .item {
        width: 100%;
        display: inline-block;
        padding: 5px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        &:hover {
          background-color: rgb(228, 232, 241);
        }
        &.current {
          color: #fff;
          background-color: #0086e9;
        }
      }
    }
  }
}

</style>
