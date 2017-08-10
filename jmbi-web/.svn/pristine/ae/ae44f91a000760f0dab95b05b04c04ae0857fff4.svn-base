<template>
  <el-row class="jm-grid-border-1px" v-loading="loading" element-loading-text="加载中...">
    <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">{{panelTitle}}</header>
    <div class="overview-cell jm-grid-box-bg-white jm-new-flow-list">
      <el-col :xs="12" :sm="12" :md="12" :lg="splitCount" v-for="(item, index) in overviewData" :key="index">
        <div class="grid-content jm-grid-box-bg-white jm-padding-bottom-20 jm-padding-top-20">
          <div class="box total-summary" :class="{'first-box': index === 0}">
            <div class="title">{{item.name}}</div>
            <div class="amount" :title="item.value+item.unit">{{item.value}}
              <span>{{item.unit}}</span>
            </div>

          </div>
        </div>
      </el-col>
    </div>
  </el-row>
</template>
<script>
import * as api from '@/service/api'

export default {
  name: 'dataOverview',
  props: ["apiName", "apiParams", "dataProperty", "panelTitle"],
  watch: {
    apiParams() {
      this.loadData()
    }
  },
  data() {
    return {
      loading: false,
      overviewData: {},
      splitCount: 0
    }
  },
  methods: {
    getDataByProperty(data) {
      var cloneData = Object.assign({}, data)
      var dataProperty = this.dataProperty || 'data'
      var properties = dataProperty.split('.')
      while (properties.length > 0) {
        let property = properties.shift()
        if (!cloneData[property]) return null

        cloneData = cloneData[property]
      }

      return cloneData
    },
    loadData() {
      let vm = this
      vm.loading = true
      api[vm.apiName](vm.apiParams).then(function (response) {
        vm.loading = false
        var data = response.data
        data = vm.getDataByProperty(data)
        vm.splitCount = 24 / data.length
        vm.overviewData = data
      })
        .catch(function (error) {
          console.log(error);
        });
    }
  },
  mounted() {
    this.loadData()
  }
}

</script>

<style lang="scss">
.jm-new-flow-list >div:nth-child(3) {
    .title{
      font-size: 12px;
      text-align: left;
    }
    .total-summary{
      padding: 0;
      margin-top: 30px;
      padding-left: 50px;
    }
    .amount{
      padding-top: 0;
      text-align: left;
      font-size: 24px;
      color: #4bbccb;
    }
}
.jm-new-flow-list >div:nth-child(4) {
    .title{
      font-size: 12px;
      text-align: left;
    }
    .total-summary{
      padding: 0 0 0 50px;
      margin-top: 30px;
      border-left:none;
    }
    .amount{
      padding-top: 0;
      text-align: left;
      font-size: 24px;
      color: #ac90e0;
    }
}
.overview-cell {
  height: 136px;
}

.total-summary {
  padding: 10px;
  border-left: 1px solid #dedede;
  &>div {
    text-align: center;
    line-height: 36px;
  }

  &.first-box {
    border-left: none;
  }


  .title {
    font-size: 16px;
  }

  .amount {
    padding-top: 15px;
    color: #0286ea;
    font-weight: bold;
    font-size: 30px;
    span {
      font-size: 16px;
      font-weight: normal;
    }

    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
}
</style>
