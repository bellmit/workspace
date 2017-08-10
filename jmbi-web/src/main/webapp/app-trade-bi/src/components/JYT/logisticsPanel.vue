<template>
  <div class="panel-container" v-loading="loading" element-loading-text="加载中...">
    <header class="box-header-sub-title">{{subTitle}}</header>
  
    <div class="box-header-botton-group">
  
      <el-radio-group v-model="selectedTypeItemLabel" @change="itemChanged">
        <el-radio-button :label="item.name" v-for="item in TypeItems" :key="item"></el-radio-button>
      </el-radio-group>
  
    </div>
  
    <div class="grid">
  
      <div class="row row-header-bg">
        <div class="cell" :class="c.objClass" v-for="c in Columns" :key="c">
          {{c.columnName}}
        </div>
  
      </div>
  
      <div class="row" v-for="row in dataRows" :key="row">
  
        <template v-for="cell in row">
          <div class="cell" :class="cell.tag.objClass" v-if="cell.cellType.name === 'TEXT'">
            <span :title="cell.columnValue" style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">{{cell.columnValue}}</span>
          </div>
          <div class="cell" :class="cell.tag.objClass" v-if="cell.cellType.name === 'RATE'">
            <el-rate v-model="cell.columnValue" disabled text-color="#ff9900">
            </el-rate>
          </div>
          <div class="cell" :class="cell.tag.objClass" v-if="cell.cellType.name === 'PERCENTAGE'">
            <div class="percentage" :title="cell.columnValue">
              <div class="percentage-bar" :class="cell.tag.barBG" v-bind:style="{ width: cell.tag.percentage + '%' }"></div>
            </div>
          </div>
  
        </template>
  
      </div>
  
      <div class="row" v-if="totalRow && totalRow.length">
        <template v-for="cell in totalRow">
          <div class="cell" :class="cell.tag.objClass">
            <span>{{cell.columnValue}}</span>
          </div>
        </template>
      </div>
  
      <div class="row" v-if="noData">
        <div class="cell cell-valign-middle cell-align-center">暂无数据</div>
      </div>
    </div>
  </div>
</template>
<script>
import * as api from '@/service/api'
import * as dataProcessService from '@/service/dataService'

export default {
  name: 'logisticsPanel',
  props: ["apiName", "apiParams", "descendantsProperty", "DataProcessServiceName", "subTitle"],
  created() {

    this.Columns = dataProcessService[this.DataProcessServiceName].columns
    this.TypeItems = dataProcessService[this.DataProcessServiceName].dataTypes
    var first = this.TypeItems[0]
    this.selectedTypeItemLabel = first.name
    this.selectedTypeItemId = first.id

  },
  watch: {
    apiParams() {
      this.loadData()
    }
  },
  computed: {
    noData() {
      return this.loadingText === '暂无数据'
    }
  },
  methods: {
    itemChanged(value) {
      let vm = this;
      vm.selectedTypeItemId = vm.TypeItems.find(x => x.name === value).id
      vm.loadData()
    },
    getDescendantsProperty(objData) {
      var cloneObjData = Object.assign({}, objData)
      var properties = this.descendantsProperty.split('.')
      while (properties.length > 0) {
        let property = properties.shift()
        if (!cloneObjData[property]) return null

        cloneObjData = cloneObjData[property]
      }

      return cloneObjData
    },
    loadData() {
      var vm = this;
      vm.loading = true
      vm.loadingText = '加载中...'
      vm.dataRows = []
      vm.totalRow = []
      let params = Object.assign({}, vm.apiParams, { way: vm.selectedTypeItemId })
      api[vm.apiName](params).then(function (response) {
        vm.loading = false
        var data = response.data

        let seriesData = vm.getDescendantsProperty(data)

        if (seriesData === null || seriesData.length === 0) {
          vm.loadingText = '暂无数据'
          return
        }

        let dataResult = dataProcessService[vm.DataProcessServiceName].process(seriesData)
        vm.dataRows = dataResult.dataRows
        vm.totalRow = dataResult.totalRow

      })
        .catch(function (error) {
          console.log(error);
        });

    },



  },
  mounted() {

    this.loadData()


  },
  data() {
    return {
      loading: false,
      loadingText: "加载中...",
      Columns: [],
      DataTypes: [],
      dataRows: [],
      totalRow: [],
      selectedTypeItemLabel: '',
      selectedTypeItemId: "",
    }
  }
}

</script>

<style lang="scss">
$table-border: 1px solid rgb(223, 230, 236) !default;

.panel-container {
  margin-bottom: 30px;
}

.box-header-sub-title {
  font-size: 16px;
  font-weight: bold;
  text-align: center;
}

.box-header-botton-group {
  text-align: center;
  padding: 20px;
  background: #fff;
}

// The essential styling
.grid {
  min-width: 1190px;
  border-left: $table-border;
  border-top: $table-border;

  .row {
    box-sizing: border-box;
    display: table;
    table-layout: fixed;
    width: 100%;

    .cell {
      display: table-cell;
      flex: 1;
      flex-direction: column;
      position: relative;
    }
  }
}



@supports(display: flex) {
  .grid {
    .row {
      display: flex;
    } // display: block is needed to allow inline-block content inside a cell, if there is vertical alignment, we need display: flex
    .cell {
      display: block;
    }
  }

  .grid .row .cell-valign-middle,
  .grid .row .cell-valign-bottom {
    display: flex;
  }
}

.grid .cell-min {
  width: 1%;
  flex: none auto;
}

.grid .cell-max {
  width: 100%;
  flex: 1 100%;
}

// body {
//   background: url(http://desktop.freewallpaper4.me/view/original/6507/gold-bokeh.jpg);
//   background-size: cover;
//   color: white;
//   font-family: verdana;
//   padding-top: 20px;
// }
.grid {
  .row {
    // border-collapse: seperate;
    // border-spacing: 8px 0;
    // padding: 4px;
    height: 42px;
    &.row-header-bg {
      background-color: #f7f7f7;
    }
  }

  .cell {
    // border-radius: 6px;
    // background: rgba(#888, 0.85); // margin-right: 8px;
    padding: 6px;
    border-right: $table-border;
    border-bottom: $table-border;

    &.cell-3-20 {
      flex: none;
      width: 15%;
    }
    &.cell-1-5 {
      flex: none;
      width: 20%;
    }

    &.cell-1-4 {
      flex: none;
      width: 23%;
    }

    &.cell-nowrap {
      overflow: hidden;
      white-space: nowrap;
    }

    &.cell-valign-middle {
      vertical-align: middle;
      justify-content: center;
    }

    &.cell-valign-bottom {
      vertical-align: bottom;
      justify-content: flex-end;
    }

    &.cell-align-center {
      text-align: center;
    }

    &.cell-align-right {
      text-align: right;
    }

    &.cell-100px {
      flex: none;
      width: 100px;
      max-width: 100px;
    }

    &.cell-150px {
      flex: none;
      width: 150px;
      max-width: 150px;
    }

    &.up-icon {
      background: url(../../assets/images/up.png) no-repeat;
      background-size: 16px 16px;
      background-position: 70% 50%;
    }



    .percentage {
      height: 15px; // margin-top: 6px;
      margin-left: 15px;
      margin-right: 15px;
      overflow: hidden;
      background-color: #f5f5f5;
      border-radius: 2px;
      -webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, .1);
      box-shadow: inset 0 1px 2px rgba(0, 0, 0, .1);
    }

    .percentage-bar {
      width: 0;
      height: 15px; // line-height: 15px;
      text-align: center;
      color: #fff;

      &.color-0 {
        background: #5eb2ed;
      }
      &.color-1 {
        background: #5eb2ed;
      }
      &.color-2 {
        background: #39b881;
      }
      &.color-3 {
        background: #fcb738;
      }
    }
  }
}
</style>
