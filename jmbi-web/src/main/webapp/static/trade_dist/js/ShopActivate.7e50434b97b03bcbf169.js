webpackJsonp([20],{618:function(t,e,a){a(773);var r=a(80)(a(689),a(805),null,null);t.exports=r.exports},636:function(t,e,a){"use strict";function r(t){var e=t.toString().split(".");return e[0]=e[0].replace(/\B(?=(\d{3})+(?!\d))/g,","),e.join(".")}function n(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,e=new Date(t),a=e.getDay(),r=e.getDate(),n=r-a;return e.setDate(n),e}function i(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,e=new Date(t),a=e.getDay(),r=e.getDate(),n=6-a,i=r+n;return e.setDate(i),e}function o(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,e=new Date(t);return e.setDate(1),e}function s(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,e=new Date(t),a=e.getMonth(),r=a+1;return e.setMonth(r,0),e}function l(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,e=new Date(t),a=e.getMonth();return a>=0&&a<=2&&e.setMonth(0,1),a>=3&&a<=5&&e.setMonth(3,1),a>=6&&a<=8&&e.setMonth(6,1),a>=9&&a<=11&&e.setMonth(9,1),e}function d(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,e=new Date(t),a=e.getMonth();return a>=0&&a<=2&&e.setMonth(3,0),a>=3&&a<=5&&e.setMonth(6,0),a>=6&&a<=8&&e.setMonth(9,0),a>=9&&a<=11&&e.setMonth(12,0),e}function c(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,e=new Date(t);return e.setMonth(0,1),e}function u(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,e=new Date(t);return e.setMonth(12,0),e}function p(t){var e=t.match(/\d+/g),a=+e[0],r=+e[1],n=new Date(a,0,1),i=h(r),o=n.getTime()+i,s=n.setTime(o);return new Date(s)}function h(t){return 6048e5*(t-1)}function f(t){if(8!==t.length)return new Date;var e=+t.substring(0,4),a=Number(t.substring(4,6))-1,r=+t.substring(6,8);return new Date(e,a,r)}function g(t,e){return Math.round((e-t)/864e5)}function m(t){return 8!==t.length?new Date:+t.substring(0,4)+"年"+Number(t.substring(4,6))+"月"+ +t.substring(6,8)+"日"}String.prototype.padStart||(String.prototype.padStart=function(t,e){return t>>=0,e=String(e||" "),this.length>t?String(this):(t-=this.length,t>e.length&&(e+=e.repeat(t/e.length)),e.slice(0,t)+String(this))}),Date.prototype.format=function(){return""+this.getFullYear()+(this.getMonth()+1).toString().padStart(2,"0")+this.getDate().toString().padStart(2,"0")},e.a={firstDayOfWeek:n,lastDayOfWeek:i,firstDayOfMonth:o,lastDayOfMonth:s,firstDayOfQuarter:l,lastDayOfQuarter:d,firstDayOfYear:c,lastDayOfYear:u,getDayByWeek:p,numberWithCommas:r,parseDate:f,formatDate:m,daydiff:g}},637:function(t,e,a){"use strict";function r(t,e){i=e+1;var a=new Date,r=new Date;r.setTime(r.getTime()-864e5*e),t.$emit("pick",[r,a])}Object.defineProperty(e,"__esModule",{value:!0});var n=a(636),i=1;e.default={name:"rangeDatePicker",created:function(){var t=new Date;t.setTime(t.getTime()-0),this.global_time_filter_value[0]=t,i=1,this.loadDatePeriodByQuery()},data:function(){return{latest1Days:"primary",latest15Days:"",latest30Days:"",global_time_filter_value:[new Date,new Date],globalPickerOptions:{shortcuts:[{text:"今日",onClick:function(t){r(t,0)}},{text:"最近15天",onClick:function(t){r(t,14)}},{text:"最近30天",onClick:function(t){r(t,29)}}]}}},methods:{dateChanged:function(){var t=i;this.updateFilterButtonState(t);var e=this.global_time_filter_value[0].format(),a=this.global_time_filter_value[1].format();this.$router.push({path:this.$router.path,query:{startDate:e,endDate:a}}),this.$emit("selectedRangeDate",{startDate:e,endDate:a}),i=-1},selectDatePeriod:function(t){i=t;var e=new Date,a=new Date;a.setTime(a.getTime()-864e5*(t-1)),this.global_time_filter_value=[a,e],this.updateFilterButtonState(i)},loadDatePeriodByQuery:function(){var t=this.$route.query;if(t.startDate&&t.endDate){var e=t.startDate,a=t.endDate;e=n.a.parseDate(e),a=n.a.parseDate(a);var r=n.a.daydiff(e,a);a.format()===(new Date).format()?0===r?r=1:14===r?r=15:29===r&&(r=30):r=-1,i=-1,this.updateFilterButtonState(r),this.global_time_filter_value=[e,a]}},updateFilterButtonState:function(t){switch(t){case 1:this.latest1Days="primary",this.latest15Days="",this.latest30Days="";break;case 15:this.latest1Days="",this.latest15Days="primary",this.latest30Days="";break;case 30:this.latest1Days="",this.latest15Days="",this.latest30Days="primary";break;default:this.latest1Days="",this.latest15Days="",this.latest30Days=""}}},mounted:function(){function t(){document.body.scrollTop>113?(e.style.position="fixed",e.style.top="40px",e.style.left="192px",e.style.right="20px",e.style.zIndex=4e4):(e.style.position="static",e.style.top="",e.style.left="",e.style.right="")}window.addEventListener("resize",t,!1),window.addEventListener("scroll",t,!1);var e=document.querySelector(".primary-timer-filter")}}},638:function(t,e,a){e=t.exports=a(609)(),e.push([t.i,".primary-timer-filter .el-button{padding-top:8px;padding-bottom:8px}","",{version:3,sources:["F:/Developer/2016/big-data/jmdata/code/jmbi/trunk/jmbi-web/src/main/webapp/app-trade-bi/src/components/RangeDatePicker.vue"],names:[],mappings:"AACA,iCACE,gBAAiB,AACjB,kBAAoB,CACrB",file:"RangeDatePicker.vue",sourcesContent:["\n.primary-timer-filter .el-button {\n  padding-top: 8px;\n  padding-bottom: 8px;\n}\n"],sourceRoot:""}])},639:function(t,e,a){var r=a(638);"string"==typeof r&&(r=[[t.i,r,""]]),r.locals&&(t.exports=r.locals);a(610)("7ea804fb",r,!0)},640:function(t,e,a){a(639);var r=a(80)(a(637),a(641),null,null);t.exports=r.exports},641:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-row",{staticClass:"jm-grid-box-border-radius jm-grid-box-bg-white jm-grid-border-1px jm-padding-15X20 jm-margin-top20 jm-margin-bottom20 primary-timer-filter"},[a("el-col",{attrs:{span:12}},[a("div",{staticClass:"grid-content"},[a("span",[t._v("时间段选择：")]),t._v(" "),a("el-button",{attrs:{type:t.latest1Days},on:{click:function(e){t.selectDatePeriod(1)}}},[t._v("今日")]),t._v(" "),a("el-button",{attrs:{type:t.latest15Days},on:{click:function(e){t.selectDatePeriod(15)}}},[t._v("最近15天")]),t._v(" "),a("el-button",{attrs:{type:t.latest30Days},on:{click:function(e){t.selectDatePeriod(30)}}},[t._v("最近30天")])],1)]),t._v(" "),a("el-col",{attrs:{span:12}},[a("div",{staticClass:"grid-content clearfix"},[a("el-date-picker",{staticClass:"right",attrs:{editable:!1,clearable:!1,type:"daterange","picker-options":t.globalPickerOptions,placeholder:"选择时间范围",align:"right"},on:{change:t.dateChanged},model:{value:t.global_time_filter_value,callback:function(e){t.global_time_filter_value=e},expression:"global_time_filter_value"}})],1)])],1)},staticRenderFns:[]}},642:function(t,e,a){t.exports={default:a(644),__esModule:!0}},643:function(t,e,a){"use strict";e.__esModule=!0;var r=a(642),n=function(t){return t&&t.__esModule?t:{default:t}}(r);e.default=function(t){if(Array.isArray(t)){for(var e=0,a=Array(t.length);e<t.length;e++)a[e]=t[e];return a}return(0,n.default)(t)}},644:function(t,e,a){a(264),a(646),t.exports=a(49).Array.from},645:function(t,e,a){"use strict";var r=a(59),n=a(125);t.exports=function(t,e,a){e in t?r.f(t,e,n(0,a)):t[e]=a}},646:function(t,e,a){"use strict";var r=a(60),n=a(124),i=a(259),o=a(261),s=a(260),l=a(126),d=a(645),c=a(263);n(n.S+n.F*!a(262)(function(t){Array.from(t)}),"Array",{from:function(t){var e,a,n,u,p=i(t),h="function"==typeof this?this:Array,f=arguments.length,g=f>1?arguments[1]:void 0,m=void 0!==g,v=0,b=c(p);if(m&&(g=r(g,f>2?arguments[2]:void 0,2)),void 0==b||h==Array&&s(b))for(e=l(p.length),a=new h(e);e>v;v++)d(a,v,m?g(p[v],v):p[v]);else for(u=b.call(p),a=new h;!(n=u.next()).done;v++)d(a,v,m?o(u,g,[n.value,v],!0):n.value);return a.length=v,a}})},689:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=a(643),n=a.n(r),i=a(123),o=a(640),s=a.n(o),l=a(81),d=a.n(l);e.default={name:"shopActived",components:{rangeDatePicker:s.a},created:function(){var t=this.$route.query;t.startDate&&t.endDate&&(this.startDate=t.startDate,this.endDate=t.endDate)},methods:{getRangeDate:function(t){var e=t.startDate,a=t.endDate;this.startDate=e,this.endDate=a,this.loadAllData()},loadAllData:function(){var t=this,e=t.$platformId,r=t.startDate,n=t.endDate;t.loadingOpenedShopChart=!0,a.i(i.shopOpenTrend)({platform:e,startDate:r,endDate:n}).then(function(e){t.loadingOpenedShopChart=!1;var a=e.data;t.loadopenedShopChart(a)}).catch(function(t){console.log(t)}),t.loadingBaseInfoStatisticsChart=!0,a.i(i.shopOpenPerfect)({platform:e,startDate:r,endDate:n}).then(function(e){t.loadingBaseInfoStatisticsChart=!1;var a=e.data;t.loadbaseInfoStatisticsChart(a),t.loadbaseInfoProportionChart(a)}).catch(function(t){console.log(t)})},loadopenedShopChart:function(t){var e=this,a="openedShopChart";e[a]=d.a.init(e.$refs[a]);var r={type:"value"},i=Math.max.apply(Math,n()(t.seriesData));i<5&&(r.splitNumber=i||1,r.minInterval=1);var o={tooltip:{trigger:"axis"},grid:{left:"3%",right:"4%",bottom:"3%",containLabel:!0},xAxis:[{type:"category",boundaryGap:!1,data:t.xAxisData}],yAxis:[r],series:[{name:"开通店铺趋势图",type:"line",smooth:!0,areaStyle:{normal:{}},itemStyle:{normal:{color:"#abd6f3"}},lineStyle:{normal:{color:"#5eb2ec"}},data:t.seriesData}]};e[a].setOption(o)},loadbaseInfoStatisticsChart:function(t){var e=this;e.baseInfoStatisticsChart=d.a.init(e.$refs.baseInfoStatisticsChart);var a={type:"value",axisLine:{lineStyle:{color:"#158bca"}}},r=Math.max.apply(Math,n()(t.seriesData));r<5&&(a.splitNumber=r||1,a.minInterval=1);var i={tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},grid:{left:"3%",right:"4%",bottom:"3%",containLabel:!0},xAxis:a,yAxis:{type:"category",axisLine:{lineStyle:{color:"#158bca"}},data:t.yAxisData},series:[{name:"基础资料完善统计",type:"bar",barWidth:"20px",itemStyle:{normal:{color:"#5eb2ed",barBorderRadius:2}},data:t.seriesData}]};e.baseInfoStatisticsChart.setOption(i)},loadbaseInfoProportionChart:function(t){var e=this;e.baseInfoProportionChart=d.a.init(e.$refs.baseInfoProportionChart);var a=[];t.seriesData.forEach(function(e,r){a.push({value:e,name:t.yAxisData[r]})});var r={color:["#1790cf","#3ab882","#fcb738","#99d2dd","#88b0bb","#22b1e6","#4598ff"],title:{left:"center",bottom:"5%",textStyle:{color:"#666",fontWeight:"normal",fontSize:14}},tooltip:{trigger:"item",formatter:"{a} <br/>{b} : {c} ({d}%)"},series:[{name:"基础资料完善占比",type:"pie",radius:["30%","60%"],center:["50%","55%"],data:a,itemStyle:{emphasis:{shadowBlur:10,shadowOffsetX:0,shadowColor:"rgba(0, 0, 0, 0.5)"}}}]};e.baseInfoProportionChart.setOption(r)}},mounted:function(){function t(){a&&clearTimeout(a),a=setTimeout(function(){e.openedShopChart&&e.openedShopChart.resize(),e.baseInfoStatisticsChart&&e.baseInfoStatisticsChart.resize(),e.baseInfoProportionChart&&e.baseInfoProportionChart.resize(),e.shopUvChart&&e.shopUvChart.resize(),e.shopStayPeriodChart&&e.shopStayPeriodChart.resize()},100)}var e=this;e.loadAllData(),window.addEventListener("resize",t,!1);var a},data:function(){return{cancelSource:"",startDate:(new Date).format(),endDate:(new Date).format(),loadingOpenedShopChart:!1,loadingBaseInfoStatisticsChart:!1,loadingTODOChart:!1,loadingTODO:!1}}}},740:function(t,e,a){e=t.exports=a(609)(),e.push([t.i,".shop-activate-page{padding-bottom:20px}","",{version:3,sources:["F:/Developer/2016/big-data/jmdata/code/jmbi/trunk/jmbi-web/src/main/webapp/app-trade-bi/src/views/ShopActivate.vue"],names:[],mappings:"AACA,oBACE,mBAAqB,CACtB",file:"ShopActivate.vue",sourcesContent:["\n.shop-activate-page {\r\n  padding-bottom: 20px;\n}\r\n"],sourceRoot:""}])},773:function(t,e,a){var r=a(740);"string"==typeof r&&(r=[[t.i,r,""]]),r.locals&&(t.exports=r.locals);a(610)("769fb92f",r,!0)},805:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"shop-activate-page"},[a("el-breadcrumb",{attrs:{separator:">"}},[a("span",{staticClass:"el-breadcrumb__item"},[a("span",[t._v("我的位置：")])]),t._v(" "),a("el-breadcrumb-item",{attrs:{to:{path:"/"}}},[t._v(t._s(t.$platformName))]),t._v(" "),a("el-breadcrumb-item",[t._v("运营情况")]),t._v(" "),a("el-breadcrumb-item",[t._v("店铺分析")]),t._v(" "),a("el-breadcrumb-item",[t._v("店铺激活")])],1),t._v(" "),a("range-date-picker",{on:{selectedRangeDate:t.getRangeDate}}),t._v(" "),a("el-row",{staticClass:"jm-grid-border-1px"},[a("el-col",{attrs:{xs:24,sm:24,md:24,lg:24}},[a("div",{staticClass:"grid-content jm-grid-box-bg-white"},[a("header",{staticClass:"panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px"},[t._v("开通店铺趋势图\n        ")]),t._v(" "),a("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loadingOpenedShopChart,expression:"loadingOpenedShopChart"}],staticClass:"box chart row-group-last",attrs:{"element-loading-text":"加载中..."}},[a("div",{ref:"openedShopChart",staticClass:"content"})])])])],1),t._v(" "),a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{xs:24,sm:24,md:24,lg:12}},[a("div",{staticClass:"grid-content jm-grid-box-bg-white jm-grid-border-1px"},[a("header",{staticClass:"panel-header jm-grid-border-bottom-1px"},[t._v("基础资料完善统计\n        ")]),t._v(" "),a("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loadingBaseInfoStatisticsChart,expression:"loadingBaseInfoStatisticsChart"}],staticClass:"box sixteen-nine",attrs:{"element-loading-text":"加载中..."}},[a("div",{ref:"baseInfoStatisticsChart",staticClass:"content"})])])]),t._v(" "),a("el-col",{attrs:{xs:24,sm:24,md:24,lg:12}},[a("div",{staticClass:"grid-content jm-grid-box-bg-white jm-grid-border-1px"},[a("header",{staticClass:"panel-header jm-grid-border-bottom-1px"},[t._v("基础资料完善占比\n\n        ")]),t._v(" "),a("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loadingBaseInfoStatisticsChart,expression:"loadingBaseInfoStatisticsChart"}],staticClass:"box sixteen-nine",attrs:{"element-loading-text":"加载中..."}},[a("div",{ref:"baseInfoProportionChart",staticClass:"content"})])])])],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=ShopActivate.7e50434b97b03bcbf169.js.map