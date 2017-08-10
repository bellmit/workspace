<template>
  <div>

    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item">
        <span>我的位置：</span>
      </span>
      <el-breadcrumb-item :to="{ path: '/' }">{{$platformName}}</el-breadcrumb-item>
      <el-breadcrumb-item>用户行为</el-breadcrumb-item>
      <el-breadcrumb-item>业务分析</el-breadcrumb-item>
    </el-breadcrumb>

    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>

    <data-overview apiName="enjrtServiceDataOverview" :apiParams="apiParams" panelTitle="业务数据"></data-overview>

    <el-row class="jm-grid-border-1px">

      <el-col :span="24">
        <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">业务趋势</header>
        <div class="grid-content jm-grid-box-bg-white  chart-setting-items-container">
          <div class="jm-padding-20X20 jm-text-center">
            <div class="jm-padding-top-15">
              <el-radio-group v-model="selectedEnrollEnterpriseItems" @change="selectedEnrollEnterpriseItemChanged">
                <el-radio-button :label="item.name" v-for="item in enrollEnterpriseItems"
                                 :key="item.id"></el-radio-button>
              </el-radio-group>
            </div>
          </div>

          <chart-box v-on:loadOption="loadOverviewLineChart" :chartBoxClass="enrollShelfLineChartBoxClass"
                     apiName="enjrtCompanyEnrollOrShelfTrend" :apiParams="jrtCompanyEnrollOrShelfTrendApiParams"
                     seriesDataProperty="lineChart.seriesData"></chart-box>

        </div>
      </el-col>

    </el-row>
    <!-- end of zhibiao-->

    <el-row class="jm-grid-border-1px">

      <el-col :span="24">
        <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">区域分布</header>
        <div class="grid-content jm-grid-box-bg-white">
          <div class="jm-padding-top-15 jm-text-center">
            <el-radio-group v-model="selectedFinancingItem" @change="selectedFinancingItemChanged">
              <el-radio-button :label="item.name" v-for="item in FinancingItems" :key="item.id"></el-radio-button>
            </el-radio-group>
          </div>
          <chart-box v-on:loadOption="loadMapOption" :chartBoxClass="enrollShelfLineChartBoxClass"
                     apiName="enjrtAreaDistAnalysis" :apiParams="enjrtAreaDistAnalysisApiParams"
                     seriesDataProperty="pieChart.seriesData" ></chart-box>

        </div>
      </el-col>

    </el-row>

    <el-row class="jm-grid-border-1px">

      <el-col :span="24">
        <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">区域：<span style="color: #00ccff">{{selectedAreaName}}</span> </header>
        <div class="grid-content jm-grid-box-bg-white jm-padding-15X15" v-loading="loadingFinancingTrendDetail"
             element-loading-text="加载中...">

          <el-table :data="tableData" border style="width: 100%">
            <el-table-column header-align="center" :prop="c.name" :label="c.label" :width="c.width"
                             v-for="c in tableColumns" :key="c.name">
            </el-table-column>
          </el-table>
          <div class="jm-padding-15X15 j-text-right">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                           :current-page="currentPage" :page-sizes="[10, 30, 50]" :page-size="pageSize"
                           layout="total, sizes, prev, pager, next, jumper" :total="totalSize">
            </el-pagination>
          </div>
        </div>
      </el-col>

    </el-row>

  </div>
</template>
<script>

  import {
    jrtCompanyShelfProductType,
    enjrtFinancingTrendDetail
  } from '@/service/api'
  import util from '@/service/util'

  import rangeDatePicker from '@/components/RangeDatePicker3'
  import DataOverview from '@/components/common/DataOverview'
  import chartBox from '@/components/chartBox'

  import echarts from 'echarts';

  export default {
    name: 'loginAnalysis',
    components: {
      rangeDatePicker,
      DataOverview,
      chartBox
    },
    created() {
      // 初始化开始时间为7天之前
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 6);
      this.startDate = start.format();

      let query = this.$route.query;
      if (query.startDate && query.endDate) {
        this.startDate = query.startDate
        this.endDate = query.endDate
      }

    },
    computed: {

      jrtCompanyEnrollOrShelfTrendApiParams() {
        return {
          platform: this.$platformId,
          startDate: this.startDate,
          endDate: this.endDate,
          type: this.activeEnrollorSelfTypeId
        }
      },
      enjrtAreaDistAnalysisApiParams() {
        return {
          platform: this.$platformId,
          startDate: this.startDate,
          endDate: this.endDate,
          type: this.selectedFinancingItemId,
        }
      },

      financingDetailApiParams() {
        return {
          areaId:this.selectedAreaId,
          platform: this.$platformId,
          startDate: this.startDate,
          endDate: this.endDate,
          limit: this.pageSize,
          offset: this.currentPage - 1,
          type: this.selectedFinancingItemId
        }
      },
      apiParams() {
        return {
          platform: this.$platformId,
          startDate: this.startDate,
          endDate: this.endDate
        }
      },
      tableColumns() {
        var id = this.selectedFinancingItemId;
        switch (id) {
          case 0:
            // 融资申请笔数
            return this.sqColumns
          case 2:
            // 入驻企业数
            return this.rzColumns
          default:
            return this.sqColumns
        }
      }

    },
    methods: {
      handleSizeChange(val) {

        this.pageSize = val
        this.loadfinancingTrendDetail();
      },
      handleCurrentChange(val) {

        this.currentPage = val
        this.loadfinancingTrendDetail();

      },
      getRangeDate({startDate, endDate}) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.loadAllData()
      },
      selectedEnrollEnterpriseItemChanged(value) {
        let item = this.enrollEnterpriseItems.find(x => x.name === value)

        if (item) {
          this.selectedEnrollEnterpriseItemsId = item.id
          this.activeEnrollorSelfTypeId = item.id
          this.activeEnrollorSelfType = item.name
        }

      },
      selectedFinancingItemChanged(value) {
        let item = this.FinancingItems.find(x => x.name === value)

        if (item) {
          this.selectedFinancingItemId = item.id

        }
        var vm = this;
      },

      loadAllData() {
        var vm = this;
        vm.loadfinancingTrendDetail();
      },
      loadfinancingTrendDetail() {
        var vm = this
        vm.loadingFinancingTrendDetail = true
        enjrtFinancingTrendDetail(vm.financingDetailApiParams).then(function (response) {
          vm.loadingFinancingTrendDetail = false
          var tableData = response.data.tableData
          vm.totalSize = response.data.totalSize
          vm.tableData = tableData
        })
          .catch(function (error) {
            console.log(error);
          });
      },
      loadOverviewLineChart({chartData, context, chartId}) {

        chartData = chartData.lineChart
        let unit = chartData.unit || ''

        let yAxis = {
          name: unit,
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        }

        if (unit.indexOf('元') === -1) {

          // 非金额单位
          // 从0开始，最小间隔为整数。
          let maxVal = Math.max(...chartData.seriesData)
          if (maxVal < 5) {
            yAxis.splitNumber = maxVal || 1
            yAxis.minInterval = 1

          } else {
            yAxis.splitNumber = 0
            yAxis.minInterval = 0
          }
        } else {
          yAxis.splitNumber = 0
          yAxis.minInterval = 0
        }

        // 绘制图表
        var option = {
          tooltip: {
            trigger: 'axis'
          },
          grid: {
            top: '10%',
            left: '3%',
            right: '5%',
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
            name: chartData.title || this.activeEnrollorSelfType,
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

      loadMapOption({chartData, context, chartId}) {
        let vm = this;
        vm.loading = false;
        let maxValue = 0;

        chartData.pieChart.seriesData.forEach(function (data) {
          let v = Number(data.value)
          if (v > maxValue) {
            maxValue = v;
          }
        })

        if (maxValue === 0) {
          maxValue = 255;
        }
        var itemId = this.selectedFinancingItemId;

        // 绘制图表
        var option = {
          title: {
            top: '5%',
            left: 'center',
            textStyle: {
              fontWeight: 100,
              fontSize: 16
            }
          },
          tooltip: {
            trigger: 'item',
            formatter: function (obj) {
              let result = obj.name + '<hr style="margin-bottom:-10px;padding:0">';
              if (obj.data.value) {
                if (itemId == 0) {
                  result += `<br>融资申请：${obj.data.value}笔`
                } else {
                  result += `<br>入驻企业数：${obj.data.value}家`
                }
              } else {
                result += `<br>--`
              }

              return result
            }
          },
          legend: {
            show: false,
            orient: 'vertical',
            left: 'left'
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
              type: 'map',
              mapType: 'world',
              roam: true,
              selectedMode: 'single',
              label: {
                normal: {
                  show: false
                },
                emphasis: {
                  show: true
                }
              },
              data: chartData.pieChart.seriesData.map(function (item) {
                let name = item.name || '';
                return {
                  name: name,
                  value: item.value,
                  id: item.typeOrId
                }
              })
            }
          ]
        };

        context.$initChart(context, chartId, option)
        // 基于准备好的dom，初始化echarts实例
        context[chartId].off("click");//移除click，防止多次请求
        context[chartId].on('click', function (obj) {
          if (obj.data.id){
            vm.selectedAreaId = obj.data.id;
            vm.selectedAreaName = obj.data.name;
            vm.loadfinancingTrendDetail();
          }
        })

      },

    },
    mounted() {

      var vm = this;
      vm.loadAllData()

    },
    data() {
      return {
        currentPage: 1,
        pageSize: 10,
        totalSize: 0,
        startDate: new Date().format(),
        endDate: new Date().format(),
        activeEnrollorSelfTypeId: 0,
        activeEnrollorSelfType: "融资申请",
        selectedEnrollEnterpriseItems: "融资申请",
        selectedEnrollEnterpriseItemsId: 0,
        enrollEnterpriseItems: [
          {id: 0, name: "融资申请"},
          {id: 1, name: "融资签约"},
          {id: 2, name: "入驻企业"},
          {id: 3, name: "上架产品"}],
        shelfProductTypes: [],
        selectedFinancingItem: "融资申请",
        selectedFinancingItemId: 0,
        FinancingItems: [
          {id: 0, name: "融资申请"},
          {id: 2, name: "入驻企业"}],
        selectedFinancingDetailItem: "需求",
        selectedFinancingDetailItemId: 1,
        enrollShelfLineChartBoxClass: {
          'enroll-shelf-line-chart-box-height': true
        },
        selectedAreaId : 0,
        selectedAreaName : "",
        tableData: [],
        loadingFinancingTrendDetail: false,
        sqColumns: [
          {
            name: "create_time",
            label: "申请日期",
            width: 120
          },
          {
            name: "apply_no",
            label: "申请单号"
          },
          {
            name: "company_name",
            label: "融资企业名称"
          },
          {
            name: "product_name",
            label: "融资产品名称"
          },
          {
            name: "org_company_name",
            label: "机构名称"
          },
          {
            name: "amount",
            label: "融资金额"
          },
          {
            name: "month_app",
            label: "融资期限"
          }
        ],
        rzColumns: [
          {
            name: "create_time",
            label: "入驻日期",
            width: 120
          },
          {
            name: "id",
            label: "企业编号"
          },
          {
            name: "company_name",
            label: "企业名称"
          },
          {
            name: "amount",
            label: "融资申请"
          },
          {
            name: "apply_counts",
            label: "融资签约"
          },
          {
            name: "reply_amount",
            label: "签约金额"
          }
        ]
      }
    }
  }

</script>

<style lang="scss">
  .box.enroll-shelf-line-chart-box-height {
    height: 400px;
  }
</style>
