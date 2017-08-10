<template>
  <el-row class="jm-grid-border-1px" v-loading="loadingBaiduOverivew" element-loading-text="加载中...">
    <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">数据概览</header>
    <div class="overview-cell jm-grid-box-bg-white">
      <el-col :xs="12" :sm="12" :md="12" :lg="splitCount" v-for="(item, index) in overviewData" :key="item">
        <div class="grid-content jm-grid-box-bg-white jm-padding-bottom-20 jm-padding-top-20">
          <div class="box total-summary" :class="{'first-box': index === 0}">
            <div class="title">{{item.name}}</div>
            <div class="amount">{{item.value}}
              <span>{{item.unit}}</span>
            </div>
          </div>
        </div>
      </el-col>
    </div>
  </el-row>
</template>
<script>
import {
  baiduOverivew
} from '@/service/api'

export default {
  name: 'dataOverview',
  props: {
    'platform': [String],
    'startDate': [String],
    'endDate': [String]
  },
  watch: {
    startDate() {
      this.getOverview()
    },
    endDate() {
      this.getOverview()
    }
  },
  data() {
    return {
      loadingBaiduOverivew: false,
      overviewData: {},
      splitCount: 0
    }
  },
  methods: {
    getOverview() {
      let vm = this
      vm.loadingBaiduOverivew = true
      baiduOverivew({
        platform: vm.platform,
        startDate: vm.startDate,
        endDate: vm.endDate
      }).then(function (response) {
        vm.loadingBaiduOverivew = false
        var data = response.data.overview
        vm.splitCount = 24 / data.length
        vm.overviewData = data
      })
        .catch(function (error) {
          console.log(error);
        });
    }
  },
  mounted() {
    this.getOverview()
  }
}

</script>

<style lang="scss">
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
  }
}
</style>
