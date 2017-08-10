<template>
  <div>
  
    <el-breadcrumb separator=">"
                   class="jm-margin-bottom20">
      <span class="el-breadcrumb__item"><span>我的位置：</span></span>
      <el-breadcrumb-item :to="{ path: '/home' }">聚贸通平台</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>区域订单统计</el-breadcrumb-item>
    </el-breadcrumb>
  
    <section class="order-content jm-grid-box-bg-white jm-grid-border-1px jm-grid-box-border-radius">
      <div class="order-map-wrapper">
        <div class="order-button-list">
          <el-button type="primary">区域订单分布统计</el-button>
          <el-button @click="openDialog('orderPrice')">订单货值趋势</el-button>
          <el-button @click="openDialog('orderAmount')">订单数趋势</el-button>
        </div>
  
        <div class="box">
          <div class="content orderChinaMapChart"
               ref='orderChinaMapChart'>
  
          </div>
        </div>
  
      </div>
      <aside class="order-data-summary">
        <header>
          <ul class="clearfix">
            <li class="left"
                v-bind:class="{ 'is-active': currentSelectedPeriod === 'week' }"
                @click="selectPeriod('week')">
              本周
            </li>
            <li class="left"
                v-bind:class="{ 'is-active': currentSelectedPeriod === 'month' }"
                @click="selectPeriod('month')">
              本月
            </li>
            <li class="left "
                v-bind:class="{ 'is-active': currentSelectedPeriod === 'quarter' }"
                @click="selectPeriod('quarter')">
              本季
            </li>
            <li class="left "
                v-bind:class="{ 'is-active': currentSelectedPeriod === 'year' }"
                @click="selectPeriod('year')">
              本年
            </li>
          </ul>
        </header>
        <section class="order-data-summary-data-list"
                 v-loading="loadingTopList"
                 element-loading-text="加载中...">
  
          <div>
            <header class="order-details-summary-header table-header-color-1">订单数TOP5</header>
            <div class="order-details-summary-body">
              <table class="jm-table">
                <thead>
                  <tr>
                    <th>排名</th>
                    <th class="jm-text-align-left">关区</th>
                    <th class="jm-text-align-right number-head-col"><span>订单量（票）</span></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in areaOrderList.c">
                    <td scope="row">{{index+1}}</td>
                    <td :title="item.name">{{item.name}}</td>
                    <td class="jm-text-align-right"
                        :title="item.value | numberWithCommas">{{item.value | numberWithCommas}}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!--订单数TOP5-->
  
          <div>
            <header class="order-details-summary-header table-header-color-2">货值TOP5</header>
            <div class="order-details-summary-body">
              <table class="jm-table">
                <thead>
                  <tr>
                    <th>排名</th>
                    <th class="jm-text-align-left">关区</th>
                    <th class="jm-text-align-right number-head-col"><span>货值（万美元）</span></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in areaOrderList.a">
                    <td scope="row">{{index+1}}</td>
                    <td :title="item.name">{{item.name}}</td>
                    <td class="jm-text-align-right"
                        :title="item.value | numberWithCommas">{{item.value | numberWithCommas}}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!--货值TOP5-->
  
          <div>
            <header class="order-details-summary-header table-header-color-3">拍档收入TOP5</header>
            <div class="order-details-summary-body">
              <table class="jm-table">
                <thead>
                  <tr>
                    <th>排名</th>
                    <th class="jm-text-align-left">企业名称</th>
                    <th class="jm-text-align-right number-head-col"><span>收入（万元）</span></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in areaOrderList.i">
                    <td scope="row">{{index+1}}</td>
                    <td :title="item.name">{{item.name}}</td>
                    <td class="jm-text-align-right"
                        :title="item.value | numberWithCommas">{{item.value | numberWithCommas}}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!--拍档收入TOP5-->
  
          <div>
            <header class="order-details-summary-header table-header-color-4">客户支出TOP5</header>
            <div class="order-details-summary-body">
              <table class="jm-table">
                <thead>
                  <tr>
                    <th>排名</th>
                    <th class="jm-text-align-left">企业名称</th>
                    <th class="jm-text-align-right number-head-col"><span>支出（万元）</span></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in areaOrderList.p">
                    <td scope="row">{{index+1}}</td>
                    <td :title="item.name">{{item.name}}</td>
                    <td class="jm-text-align-right"
                        :title="item.value | numberWithCommas">{{item.value | numberWithCommas}}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!--客户支出TOP5-->
  
        </section>
      </aside>
    </section>
  
    <!--dialog start -->
    <!--订单货值趋势-->
    <order-details :popupVisible="dialogOrderPriceChartVisible"
                   v-on:closeMe="dialogOrderPriceChartVisible = false"
                   :orderType="2"
                   :dialogTitle="dialogOrderPriceTitle">
    </order-details>
  
    <!--订单数趋势-->
    <order-details :popupVisible="dialogOrderAmountChartVisible"
                   v-on:closeMe="dialogOrderAmountChartVisible = false"
                   :orderType="1"
                   :dialogTitle="dialogOrderAmountTitle">
    </order-details>
    <!--dialog end-->
  
  </div>
</template>

<script>
import {
  getAreaOrderList,
  getOrderMap
} from '@/service/api'
import util from '@/service/util'

import orderDetails from '@/components/OrderDetails'

import echarts from 'echarts';

import 'echarts/map/js/china'


export default {
  name: 'areaOrder',
  components: {
    orderDetails
  },
  data() {
    return {
      currentSelectedPeriod: 'week',
      selectedCustomCode: 'all',
      orderChinaMapChart: null,
      dialogOrderPriceChartVisible: false,
      dialogOrderAmountChartVisible: false,
      dialogOrderPriceTitle: '订单货值趋势',
      dialogOrderAmountTitle: '订单数趋势',
      areaOrderList: {},
      loadingTopList: false
    }
  },
  methods: {
    loadorderChinaMapChart() {
      var vm = this;

      // 基于准备好的dom，初始化echarts实例
      vm.orderChinaMapChart = echarts.init(vm.$refs.orderChinaMapChart);

      var geoCoordMap = {
        '北京关区': [116.395645, 39.929986],
        '天津关区': [117.210813, 39.14393],
        '石家庄区': [114.522082, 38.048958],
        '太原关区': [112.550864, 37.890277],
        '满洲里关': [117.603687, 49.500032],
        '呼特关区': [111.7636960000, 40.8393860000],
        '沈阳关区': [123.432791, 41.808645],
        '大连海关': [121.677966, 38.900436],
        '长春关区': [125.313642, 43.898338],
        '哈尔滨区': [126.657717, 45.773225],
        '上海海关': [121.496612, 31.242302],
        '南京海关': [118.808885, 32.033477],
        '杭州关区': [120.219375, 30.259244],
        '宁波关区': [121.579006, 29.885259],
        '合肥海关': [117.216614, 31.784732],
        '福州关区': [119.330221, 26.047125],
        '厦门关区': [118.103886, 24.489231],
        '南昌关区': [115.893528, 28.689578],
        '青岛海关': [120.318241, 36.063675],
        '济南海关': [117.009595, 36.609187],
        '郑州关区': [113.649644, 34.75661],
        '武汉海关': [114.211217, 30.643065],
        '长沙关区': [112.979353, 28.213478],
        '广东分署': [113.394818, 23.408004],
        '广州海关': [113.326719, 23.126047],
        '黄埔关区': [113.492885, 23.108712],
        '深圳海关': [114.072743, 22.547001],
        '拱北关区': [113.561566, 22.225828],
        '汕头海关': [116.747613, 23.38354],
        '海口关区': [110.330802, 20.022071],
        '湛江关区': [110.365067, 21.257463],
        '江门关区': [113.078125, 22.575117],
        '南宁关区': [108.297234, 22.806493],
        '成都关区': [104.067923, 30.679943],
        '重庆关区': [106.530635, 29.544606],
        '贵阳海关': [106.719491, 26.576723],
        '昆明关区': [102.714601, 25.049153],
        '拉萨海关': [91.088738, 29.665236],
        '西安关区': [108.896607, 34.230453],
        '乌关区': [87.578478, 43.852043],
        '兰州关区': [103.823305, 36.064226],
        '银川海关': [106.254539, 38.482057],
        '西宁关区': [101.767921, 36.640739]
      };

      var period = vm.currentSelectedPeriod;
      var startDate, endDate;

      if (period === 'week') {
        startDate = util.firstDayOfWeek().format()
        endDate = util.lastDayOfWeek().format()
      }

      if (period === 'month') {
        startDate = util.firstDayOfMonth().format()
        endDate = util.lastDayOfMonth().format()
      }

      if (period === 'quarter') {
        startDate = util.firstDayOfQuarter().format()
        endDate = util.lastDayOfQuarter().format()
      }

      if (period === 'year') {
        startDate = util.firstDayOfYear().format()
        endDate = util.lastDayOfYear().format()
      }

      getOrderMap(period, startDate, endDate).then(function (response) {
        var chartData = response.data;
        loadMapData(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });



      function loadMapData(chartData) {

        var data = chartData;


        var convertData = function (data) {
          var res = [];
          for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
              res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value),
                data: data[i]
              });
            }
          }
          return res;
        };

        function randomValue() {
          return Math.round(Math.random() * 1000);
        }

        var option = {
          tooltip: {
            trigger: 'item',
            formatter: function (obj) {
              var data = obj.data.data;
              var count = util.numberWithCommas(data.count)
              var amount = data.amount / 10000
              amount = util.numberWithCommas(amount.toFixed(4))
              var partner = data.partner / 10000
              partner = util.numberWithCommas(partner.toFixed(4))
              var customer = data.customer / 10000
              customer = util.numberWithCommas(customer.toFixed(4))
              return `${data.name}<br />订单数：${count}票<br />货 值：${amount}万美元<br />拍档收入：${partner}万元<br />客户支出：${customer}万元`
            }
          },
          geo: {
            map: 'china',
            roam: true,
            zoom: 1.2,
            itemStyle: {
              normal: {
                borderColor: 'rgba(0, 0, 0, 0.2)'
              },
              emphasis: {
                areaColor: null,
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                shadowBlur: 20,
                borderWidth: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          },
          series: [{
            type: 'scatter',
            coordinateSystem: 'geo',
            data: convertData(data),
            symbolSize: function (val) {
              return 10;
            },
            showEffectOn: 'render',
            rippleEffect: {
              brushType: 'stroke'
            },
            hoverAnimation: true,
            label: {
              normal: {
                formatter: '{b}',
                position: 'right',
                show: true
              }
            },
            itemStyle: {
              normal: {
                color: '#0588e6',
                shadowBlur: 10,
                shadowColor: '#333'
              }
            },
            zlevel: 1
          }
          ]
        };

        vm.orderChinaMapChart.setOption(option)

        vm.orderChinaMapChart.on('click', function (scatter) {

          if (scatter && scatter.seriesType === "scatter") {
            let data = scatter.data.data;
            vm.selectedCustomCode = data.code;
            vm.loadOrderTopData()

          }

        });
      }


    },
    loadOrderTopData() {
      var vm = this;
      var startDate, endDate;
      var period = vm.currentSelectedPeriod;
      var customsCode = vm.selectedCustomCode;

      if (period === 'week') {
        startDate = util.firstDayOfWeek().format()
        endDate = util.lastDayOfWeek().format()
      }

      if (period === 'month') {
        startDate = util.firstDayOfMonth().format()
        endDate = util.lastDayOfMonth().format()
      }

      if (period === 'quarter') {
        startDate = util.firstDayOfQuarter().format()
        endDate = util.lastDayOfQuarter().format()
      }

      if (period === 'year') {
        startDate = util.firstDayOfYear().format()
        endDate = util.lastDayOfYear().format()
      }

      vm.loadingTopList = true
      getAreaOrderList(startDate, endDate, customsCode).then(function (response) {
        vm.loadingTopList = false
        vm.areaOrderList = response.data;

      })
        .catch(function (error) {
          console.log(error);
        });
    },
    selectPeriod(period) {
      var vm = this;
      vm.selectedCustomCode = 'all';
      vm.currentSelectedPeriod = period;
      vm.loadorderChinaMapChart();
      vm.loadOrderTopData()
    },
    openDialog(orderType) {

      if (orderType === "orderPrice") {
        this.dialogOrderPriceChartVisible = true;
        this.dialogOrderAmountChartVisible = false;
      } else {
        this.dialogOrderPriceChartvisible = false;
        this.dialogOrderAmountChartVisible = true;
      }

    }
  },
  filters: {
    // http://stackoverflow.com/questions/2901102/how-to-print-a-number-with-commas-as-thousands-separators-in-javascript
    numberWithCommas: function (value) {
      return util.numberWithCommas(value)
    }
  },
  mounted() {
    var vm = this;

    vm.selectPeriod('week')

    vm.loadorderChinaMapChart()

    window.addEventListener('resize', chartsResize, false);

    function chartsResize() {
      vm.orderChinaMapChart && vm.orderChinaMapChart.resize()
    }
  }
}



</script>

<style lang="scss">
$order-sidebar-width: 400px !default;
$body-padding-width: 20px !default;
$sidebar-width: 172px !default;
.orderChinaMapChart {
  position: absolute;
  top: 60px;
  left: 0;
  right: 401px;
  bottom: 0;
}

.order-content {
  position: fixed;
  top: 113px;
  left: $sidebar-width + $body-padding-width;
  right: $body-padding-width;
  bottom: 20px;
  min-width: 978px; // 1190-172-20-20
  .order-map-wrapper {
    margin-right: $order-sidebar-width;
  }
  .order-data-summary {
    width: $order-sidebar-width;
    height: 100%;
    position: absolute;
    top: 0;
    right: 0;
    overflow: hidden;
  }
}

.order-data-summary-data-list {
  min-height: 100%;
  height: 100%;
  overflow-y: auto;
  padding-bottom: 60px;
}

.order-button-list {
  padding-top: 20px;
  text-align: center;
  .el-button {
    padding-top: 8px;
    padding-bottom: 8px;
  }
}

.order-data-summary *,
.order-data-summary *:before,
.order-data-summary *:after {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

.order-data-summary {
  border-left: 1px solid #d9d9d9;
  header {
    ul {
      margin: 0;
      padding: 0;
    }
    li {
      list-style: none;
      width: 25%;
      text-align: center;
      font-size: 16px;
      line-height: 30px;
      padding: 15px 0;
      border-bottom: 1px solid #d9d9d9;
      cursor: pointer;
      position: relative;
      &:after {
        content: '';
        display: block;
        position: absolute;
        right: -1px;
        top: 50%;
        width: 0;
        height: 36px;
        margin-top: -18px;
        border-right: 1px solid #d9d9d9;
      }
      &.is-active {
        color: #0588e6;
        border-left: 1px solid #d9d9d9;
        border-right: 1px solid #d9d9d9;
        border-bottom: none;
        &:last-child {
          border-right: 0;
        }
        &:first-child {
          border-left: 0;
        }
      }
      &:last-child:after {
        border-right: 0;
      }
    }
  }
}

.order-details-summary-header {
  line-height: 40px;
  padding-left: 20px;
  font-size: 16px;
  background: #f5f5f5;
}

.jm-text-align-left {
  text-align: left;
}

.jm-text-align-right {
  text-align: right;
}

.order-details-summary-body {
  padding: 0 20px;
  .jm-table {
    width: 100%;
    max-width: 100%;
    border-spacing: 0;
    th,
    td {
      display: inline-block;
      padding: 8px 0 8px 15px;
      line-height: 1.42857143;
      vertical-align: top;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    th:nth-of-type(1),
    td:nth-of-type(1) {
      width: 18%;
    }
    th:nth-of-type(2),
    td:nth-of-type(2) {
      width: 37%;
    }
    th:nth-of-type(3),
    td:nth-of-type(3) {
      width: 45%;
    }

    thead th {
      color: #666;
      font-weight: normal;
      vertical-align: bottom;
      border-bottom: 2px solid #ddd;
    }
  }
}

.table-header-color-1 {
  color: #50c14e;
}

.table-header-color-2 {
  color: #f89934;
}

.table-header-color-3 {
  color: #5f8aed;
}

.table-header-color-4 {
  color: #978bdd;
}

.number-head-col {
  span {
    position: relative;
    right: -5px;
  }
}
</style>
