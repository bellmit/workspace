<template>
  <div>
    <el-popover ref="popover" placement="left-start" trigger="click" popper-class="setting-group">
      <p>设置显示项，最多选6个</p>
      <el-checkbox-group v-model="checkedSettingItems" :min="1" :max="6" @change="selectedChanged">
        <el-checkbox v-for="setting in summarySettingItems" :label="setting.id" :key="setting.id">{{setting.name}}</el-checkbox>
      </el-checkbox-group>
    </el-popover>
  
    <i class="el-icon-setting" title="设置" v-popover:popover></i>
  </div>
</template>
<script>


export default {
  name: 'settingDisplayItems',
  //props: ['checkedSettingItems'],
  created() {
    this.oldCheckedSettingItems = this.checkedSettingItems.concat()
  },
  data() {
    return {
      summarySettingItems: [
        { id: 1, name: "供应商企业数" },
        { id: 2, name: "委托方企业数" },
        { id: 3, name: "委托方个人数" },
        { id: 4, name: "仓库数" },
        { id: 5, name: "线路数" },
        { id: 6, name: "货值" },
        { id: 7, name: "订单金额" },
        { id: 8, name: "订单数" },
        { id: 9, name: "司机数" },
        { id: 10, name: "车辆数" }//,
        //{ id: 999, name: "其他" }
      ],
      checkedSettingItems: [
        1,
        2,
        3,
        4,
        5,
        6
      ],
      oldCheckedSettingItems: []
    }
  },
  methods: {
    selectedChanged(value) {
      var vm = this
      var newVal = value.concat().sort().join('-')
      var oldVal = this.oldCheckedSettingItems.concat().sort().join('-')
      if (newVal === oldVal) return

      this.oldCheckedSettingItems = this.checkedSettingItems.concat()

      let result = this.checkedSettingItems.map(function (currentValue, index) {
        let item = vm.summarySettingItems.find(x => x.id == currentValue)
        return Object.assign({}, item)
      })

      this.$emit('checkedSettingItems', result);

    }
  }
}

</script>

<style lang="scss">
.setting-group {
  padding-bottom: 15px;
  p {
    margin-top: 0;
  }
  .el-checkbox {
    display: block;
    margin-left: 15px;

    &+.el-checkbox {
      margin-top: 10px;
    }
  }
}
</style>
