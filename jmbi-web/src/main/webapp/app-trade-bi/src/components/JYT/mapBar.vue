<template>
  <el-row class="jm-grid-border-1px">
    <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">{{panelTitle}}
    </header>
    <div class="jyt-geographical-amount-btn-wrapper">
      <el-radio-group v-model="selectedTypeItemLabel" @change="itemChanged">
        <el-radio-button :label="item.name" v-for="item in typeItems" :key="item"></el-radio-button>
      </el-radio-group>
  
    </div>
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white map">
  
        <chart-box v-on:loadOption="loadMapOption" :chartBoxClass="chartBoxClass" :apiName="apiName" :apiParams="_ApiParams" seriesDataProperty=""></chart-box>
  
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white">
  
        <chart-box v-on:loadOption="loadBarOption" :chartBoxClass="chartBoxClass" :apiName="apiName" :apiParams="_ApiParams" seriesDataProperty=""></chart-box>
  
      </div>
    </el-col>
  </el-row>
</template>

<script>

import chartBox from '@/components/chartBox'

import echarts from 'echarts';
import 'echarts/map/js/china';

export default {
  name: 'mapBar',
  components: {
    chartBox
  },
  created() {
    var first = this.typeItems[0]
    this.selectedTypeItemLabel = first.name
    this.selectedTypeItemId = first.id
  },
  props: ["apiName", "apiParams", "typeItems", "panelTitle", "chartTitle"],
  computed: {
    _ApiParams() {
      return Object.assign({}, this.apiParams, {
        lineType: this.selectedTypeItemId
      })
    }
  },
  methods: {
    itemChanged(value) {
      let vm = this;
      vm.selectedTypeItemId = vm.typeItems.find(x => x.name === value).id
    },
    loadMapOption({ chartData, context, chartId }) {
      var vm = this;

      let maxValue = 0;
      let sortField = vm.apiParams.sortField

      let value2Filed = null
      let label1 = null
      let label2 = null
      let unit = null

      if (sortField === "ordernum") {
        // ordernum 笔数
        value2Filed = "paynum"
        label1 = "订单数"
        label2 = "付款订单数"
        unit = '笔'
      } else if (sortField === "ordertotal") {
        // ordertotal 金额
        value2Filed = "paytotal"
        label1 = "订单金额"
        label2 = "付款金额"
        unit = '万元'
      }

      chartData.forEach(function (data) {
        let v = Number(data[sortField])
        if (v > maxValue) {
          maxValue = v;
        }
      })

      if (maxValue === 0) {
        maxValue = 255;
      }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'item',
          formatter: function (obj) {
            let result = obj.name + '<hr style="margin-bottom:-10px;padding:0">';
            if (obj.data.value) {
              result += `<br>${label1}：${obj.data.value}${unit}`
            } else {
              result += `<br>${label1}：--`
            }

            if (obj.data.value2) {
              result += `<br>${label2}：${obj.data.value2}${unit}`
            } else {
              result += `<br>${label2}：--`
            }

            return result
          }
        },
        legend: {
          show: false,
          orient: 'vertical',
          left: 'left',
          data: [sortField]
        },
        visualMap: {
          min: 0,
          max: maxValue,
          left: 'left',
          top: 'bottom',
          text: ['高', '低'],
          inRange: {
            color: ['#e0ffff', '#006edd']
          },
          calculable: true
        },

        series: [
          {
            name: vm.panelTitle,
            type: 'map',
            mapType: 'china',
            roam: true,
            label: {
              normal: {
                show: true
              },
              emphasis: {
                show: true
              }
            },
            data: chartData.map(function (item) {
              let name = item.area_name || '';
              name = name.replace('自治区', '')
              name = name.replace('省', '')
              name = name.replace('市', '')

              return {
                name: name,
                value: item[sortField],
                value2: item[value2Filed]
              }
            })
          }
        ]
      };

      context.$initChart(context, chartId, option)
    },
    loadBarOption({ chartData, context, chartId }) {
      var vm = this;

      let sortField = vm.apiParams.sortField
      let unit = null

      let yAxisData = [];
      let seriesData = [];

      chartData.forEach(function (element) {
        yAxisData.push(element.area_name)
        seriesData.push(element[sortField])
      });
      console.log('yAxisData', yAxisData)
      console.log('seriesData', seriesData)

      let xAxis = {
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#008acd'
          }
        }
      };
      if (sortField === 'ordernum') {
        xAxis.name = "笔"
        // 笔数
        // 从0开始，最小间隔为整数。
        let maxVal = Math.max(...seriesData)
        // 如果没有数据，又设置了splitNumber和minInterval，则改坐标轴不会显示的bug
        if (maxVal < 5 && maxVal > 0) {
          xAxis.splitNumber = maxVal
          xAxis.minInterval = 1

        } else {
          xAxis.splitNumber = 5
          xAxis.minInterval = 0
        }
      } else {
        xAxis.name = "万元"
      }

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          top: '8%',
          left: '5%',
          right: '5%',
          bottom: '8%',
          containLabel: true
        },
        xAxis: xAxis,
        yAxis: {
          type: 'category',
          splitLine: {
            show: true
          },
          axisLine: {
            lineStyle: {
              color: '#008acd'
            }
          },
          axisLabel: {
            formatter: function (value, index) {
              var value = value || ''
              var maxLength = 15
              if (value.length > maxLength) {
                value = value.substring(0, maxLength) + '...';
              }
              return value;
            },
            rotate: 20
          },
          data: yAxisData.reverse()
        },
        series: [{
          name: vm.chartTitle,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#1790cf',
              barBorderRadius: 2
            }
          },
          data: seriesData.reverse()
        }]
      };

      context.$initChart(context, chartId, option)
    }
  },
  mounted() {

  },
  data() {
    return {
      selectedTypeItemLabel: '',
      selectedTypeItemId: "",
      chartBoxClass: {
        'chart': true,
        'row-group-last': true,
        'sixteen-nine': false
      }
    }
  }
}


</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
.jyt-geographical-amount-btn-wrapper {
  text-align: center;
  padding: 10px;
  background: #fff;
}
</style>
