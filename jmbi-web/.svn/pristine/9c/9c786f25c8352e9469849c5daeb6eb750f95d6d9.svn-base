<template>
  <div>

    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>运营分析</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="jm-margin-top20">
      <data-overview apiName="enjzxOperationOverview" panelTitle="运营数据概览(累计)"></data-overview>
    </div>

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>

    <el-row class="jm-grid-border-1px">

      <el-col :span="24">
        <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">业务趋势分析


        </header>
        <div class="grid-content jm-grid-box-bg-white  chart-setting-items-container">
          <div class="j-text-right jm-padding-15X15">
            <el-radio-group v-model="datePeriodItemLabel" @change="datePeriodItemChanged">
              <!--todo: :key需要值类型数据类型-->
              <el-radio-button :label="item.name" v-for="(item,index) in datePeriodItems" :key="index"
                               :disabled="item.isNotShow"></el-radio-button>
            </el-radio-group>
          </div>
          <div class="jm-text-center jm-padding-15X15">
            <el-radio-group v-model="trendTypeItemLabel" @change="trendTypeItemChanged">
              <el-radio-button :label="item.name" v-for="(item,index) in trendTypeItems" :key="index"></el-radio-button>
            </el-radio-group>
          </div>
          <div class="box chart row-group-last">
            <!--<chart-box v-on:loadOption="loadOverviewLineChart" :chartBoxClass="overviewLineChartBoxClass" apiName="jrtFinancingTrend" :apiParams="jrtFinancingTrendApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>-->
            <chart-box v-on:loadOption="loadBusiTrendLineChart" apiName="enjzxIntentionOrder"
                       :apiParams="jrtBusinessTrendApiParams" seriesDataProperty="lineChart.seriesData"></chart-box>
          </div>

        </div>
      </el-col>

    </el-row>

    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">项目订单分析</header>

      <el-col :xs="24" :sm="24" :md="24" :lg="10" class="jm-grid-box-bg-white">
        <div class="grid-content jm-grid-box-bg-white">
          <header style="text-align:center;font-size:22px;padding-top:20px;">新增项目订单各类型占比</header>
          <!--<p class="overview-chart-title" style="text-align:center;">-->
          <!--<span class="chart-title-key">{{jzxNewProjectOrderTotal}}</span>-->
          <!--<span class="chart-title-unit">家</span>-->
          <!--</p>-->
          <chart-box v-on:loadOption="loadPieChartOption_jzx" :chartBoxClass="groupPieChartBoxClass"
                     apiName="enjzxNewProjectOrder" :apiParams="enjzxNewProjectOrderParams"
                     seriesDataProperty="proportion"></chart-box>
        </div>
      </el-col>

      <el-col :xs="24" :sm="24" :md="24" :lg="7">
        <div class="grid-content jm-grid-box-bg-white">
          <header style="text-align:center;font-size:22px;padding-top:20px;">{{ industryTitle }}</header>
          <!--<p class="overview-chart-title">-->
          <!--&lt;!&ndash;<span class="chart-title-key">{{CompanyProductTypeTotal}}</span>&ndash;&gt;-->
          <!--</p>-->
          <chart-box v-on:loadOption="loadPieIndustryShare" :chartBoxClass="groupPieChartBoxClass"
                     apiName="enjzxIndustryShare" :apiParams="enjzxNewProjectOrderDetailsParams"
                     seriesDataProperty="proportion"></chart-box>
        </div>
      </el-col>

      <el-col :xs="24" :sm="24" :md="24" :lg="7">
        <div class="grid-content jm-grid-box-bg-white">
          <header style="text-align:center;font-size:22px;padding-top:20px;">{{ serviceTypeTitle }}</header>
          <!--<p class="overview-chart-title">-->
          <!--&lt;!&ndash;<span class="chart-title-key">{{CompanyProductTypeTotal}}</span>&ndash;&gt;-->
          <!--</p>-->
          <chart-box v-on:loadOption="loadPieTypeRatio" :chartBoxClass="groupPieChartBoxClass"
                     apiName="enjzxTypeRatio" :apiParams="enjzxNewProjectOrderDetailsParams"
                     seriesDataProperty="proportion"></chart-box>
        </div>
      </el-col>
    </el-row>

  </div>
</template>
<script>

  import util from '@/service/util'

  import rangeDatePicker from '@/components/RangeDatePicker4'
  import DataOverview from '@/components/common/DataOverview'
  import chartBox from '@/components/chartBox'

  import echarts from 'echarts';

  export default {
    name: 'operateAnalysis',
    components: {
      rangeDatePicker,
      DataOverview,
      chartBox
    },
    created() {
      let query = this.$route.query;
      if (query.startDate && query.endDate) {
        this.startDate = query.startDate
        this.endDate = query.endDate
      }
    },
    computed: {
      jrtBusinessTrendApiParams() {
        return {
          platform: this.$platformId,
          startDate: this.startDate,
          endDate: this.endDate,
          dateType: this.datePeriodItemId,
          service: this.serviceItemName,
        }
      },
      enjzxNewProjectOrderParams() {
        return {
          startDate: this.startDate,
          endDate: this.endDate
        }
      },
      enjzxNewProjectOrderDetailsParams() {
        return {
          startDate: this.startDate,
          endDate: this.endDate,
          // TODO
          todoDetailId: this.todoDetailId,
          }
      }
    },
    methods: {
      getRangeDate({startDate, endDate}) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dealPeriodItemDisable(startDate, endDate);
        this.loadAllData();
      },
      dealPeriodItemDisable(startDate, endDate) {
        let days = util.daydiff(util.parseDate(startDate), util.parseDate(endDate));
        console.log('days:', days);
        // 初始化全显示
        this.datePeriodItems.find(x => x.id === "D").isNotShow = false;
        this.datePeriodItems.find(x => x.id === "W").isNotShow = false;
        this.datePeriodItems.find(x => x.id === "M").isNotShow = false;
        this.datePeriodItemLabel = "按日";
        this.datePeriodItemId = "D";
        if (days <= 30) {
          this.datePeriodItems.find(x => x.id === "W").isNotShow = true;
          this.datePeriodItems.find(x => x.id === "M").isNotShow = true;
        } else if (days <= 3 * 30 && days > 30) {
          this.datePeriodItems.find(x => x.id === "M").isNotShow = true;
        } else if (days <= 6 * 30 && days > 3 * 30) {
          this.datePeriodItems.find(x => x.id === "D").isNotShow = true;
          this.datePeriodItemLabel = "按周";
          this.datePeriodItemId = "W";
        } else if (days > 6 * 30) {
          this.datePeriodItems.find(x => x.id === "D").isNotShow = true;
          this.datePeriodItems.find(x => x.id === "W").isNotShow = true;
          this.datePeriodItemLabel = "按月";
          this.datePeriodItemId = "";
        }
      },
      loadAllData() {
        var vm = this;

      },
      trendTypeItemChanged(value) {
        let item = this.trendTypeItems.find(x => x.name === value)

        if (item) {
          this.trendTypeItemId = item.id
          // 获取service名称
          let serviceItem = this.serviceItems.find(x => x.id === this.trendTypeItemId)
          this.serviceItemName = serviceItem.name
        }
      },
      datePeriodItemChanged(value) {
        let item = this.datePeriodItems.find(x => x.name === value)

        if (item) {
          this.datePeriodItemId = item.id
        }
      },
      loadBusiTrendLineChart({chartData, context, chartId}) {

        chartData = chartData.lineChart

        let yAxis = {
          name: chartData.unit,
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        }

        if (chartData.unit.indexOf('元') === -1) {

          // 非金额单位
          // 从0开始，最小间隔为整数。
          let maxVal = Math.max(...chartData.seriesData)
          if (maxVal < 5) {
            yAxis.splitNumber = maxVal || 1
            yAxis.minInterval = 1

          }
        } else {
          yAxis.splitNumber = 0
          yAxis.minInterval = 0
        }

        // 绘制图表
        var option = {
          title: {
               text: chartData.title,
               left: 'center',
               top: '8%',
               textStyle: {
                 color: '#666',
                 fontWeight: 400,
                 fontSize: 18
               }
             },
          tooltip: {
            trigger: 'axis'
          },
          grid: {
            top: '10%',
            left: '3%',
            right: '3%',
            bottom: '10%',
            containLabel: true
          },
          xAxis: [{
            type: 'category',
            boundaryGap: false,
            axisLine: {
              lineStyle: {
                color: '#158bca'
              }
            },
            data: chartData.xAxisData
          }],
          yAxis: yAxis,
          series: [{
            name: chartData.title,
            type: 'line',
            smooth: true,
            itemStyle: {
              normal: {
                color: '#5eb2ed'
              }
            },
            areaStyle: {
              normal: {
                color: '#acd7f4'
              }
            },
            data: chartData.seriesData
          }

          ]
        };

        context.$initChart(context, chartId, option)
      },
      loadPieCommon({chartData, context, chartId}) {
        var one = 30;
        var twe = 55;
        var vm = this;

        if (chartData.unit === '笔') {
          vm.jzxNewProjectOrderTotal = chartData.total;
          one = 39;
          twe = 73;
        }
        if (chartData.unit === '个') {
          vm.jzxNewProjectOrderParamsTotal = chartData.total
          one = 39;
          twe = 73;
        }

        // 默认选中第一项
        if (chartData.proportion.length > 0) {
          chartData.proportion[0].selected = true;//选中状态下的状态
          // 获得默认选中项的ID
          vm.todoDetailId = chartData.proportion[0].value// TODO
        }




        //let data = chartData.proportion.map(x => { return { name: Object.values(x)[0], value: Object.keys(x)[0] } })
        // 绘制图表
        var option = {
          color: ['#1790cf', '#3ab882', '#fcb738', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
          title: {
            text: chartData.total, /*支付渠道占比:*/
            left: 'center',
            top: '5%',
            textStyle: {
              color: '#666',
              fontWeight: 'normal',
              fontSize: 14
            }
          },
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          series: [{
            name: "", /*支付渠道占比*/
            type: 'pie',
            radius: [one + '%', twe + '%'],
            center: ['50%', '55%'],
            data: chartData.proportion,
            selectedMode: 'single',
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }]
        };

        context.$initChart(context, chartId, option)

        context[chartId].on('click', function (obj) {

          console.log(obj)

          // var itemId = vm.detailIds[obj.dataIndex]
          // 获得当前选中项的ID
          vm.todoDetailId = obj.value// TODO

        })
      },
      loadPieIndustryShare({chartData, context, chartId}) {
        var vm = this;
        vm.industryTitle = chartData.titel;
        vm.loadPieCommon({chartData, context, chartId})
      },
      loadPieTypeRatio({chartData, context, chartId}) {
        var vm = this;
        vm.serviceTypeTitle = chartData.titel;
        vm.loadPieCommon({chartData, context, chartId})
      },

      loadPieChartOption_jzx({chartData, context, chartId}) {
        this.loadPieCommon({chartData, context, chartId});
      }


    },
    mounted() {

      var vm = this;

      vm.loadAllData()

    },
    data() {
      return {
        jzxNewProjectOrderTotal: 0,
        startDate: (new Date(new Date() - (30 - 1) * 3600 * 1000 * 24)).format(),
        endDate: new Date().format(),
        trendTypeItemLabel: "意向单",
        trendTypeItemId: 0,
        trendTypeItems: [
          {id: 0, name: "意向单"},
          {id: 1, name: "项目订单"},
          {id: 2, name: "合同金额"},
          {id: 3, name: "新增用户"}],
        serviceItemName: "intentionOrder",
        serviceItems: [
          {id: 0, name: "intentionOrder"},
          {id: 1, name: "projectOrder"},
          {id: 2, name: "contractAmount"},
          {id: 3, name: "newUsers"}
        ],
        datePeriodItemLabel: "按日",
        datePeriodItemId: "D",
        datePeriodItems: [
          {id: "D", name: "按日", isNotShow: false},
          {id: "W", name: "按周", isNotShow: false},
          {id: "M", name: "按月", isNotShow: false}],
        overviewLineChartBoxClass: {
          'overview-line-chart-box-height': true
        },
        groupPieChartBoxClass: {
          'group-line-chart-box-height': true
        },
        todoDetailId: "-1", // TODO
        industryTitle: "",  //企业占比
        serviceTypeTitle:""  //细分服务类型
      }
    }
  }

</script>

<style lang="scss">
  .box.group-line-chart-box-height {
    height: 450px;
  }
</style>
