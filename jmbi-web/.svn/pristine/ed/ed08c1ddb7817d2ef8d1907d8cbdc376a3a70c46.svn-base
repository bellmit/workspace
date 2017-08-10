<template>
  <header class="top-header clearfix">
    <a href="/index" class="jm-logo left">
      <img src="../assets/images/top-header/header-logo.png" alt="">
      <span>聚贸大数据BI平台</span>
    </a>
    <span class="welcome left">热烈欢迎，{{currentUserName}}！</span>
    <div class="user-profile right">
      <a class="platform">平台切换：
        <span class="dropdown">聚贸通
          <i class="icon-link"></i>
        </span>

        <!--下拉框 -->
        <el-tabs v-model="activeName2" type="card" @tab-click="handleClick" class="second-nav clearfix">
          <el-tab-pane label="中文站" class="tab" name="first">
            <div class="item-list clearfix">
              <ul >
                <h6>交易平台BI</h6>
                <template v-if="hasVisiblePlatform(tradePlatformList)">
                  <li v-if="isVisible('100100')">
                    <p>
                      <a href="/index2?platformId=100100">交易全站</a>
                    </p>
                  </li>
                  <li v-if="isVisible('100300')">
                    <p>
                      <a href="/index2?platformId=100300">聚贸有色</a>
                    </p>
                  </li>
                  <li v-if="isVisible('100700')">
                    <p>
                      <a href="/index2?platformId=100700">聚贸煤炭</a>
                    </p>
                  </li>
                  <li v-if="isVisible('100800')">
                    <p>
                      <a href="/index2?platformId=100800">聚贸钢铁</a>
                    </p>
                  </li>
                  <li v-if="isVisible('100900')">
                    <p>
                      <a href="/index2?platformId=100900">聚贸矿产</a>
                    </p>
                  </li>
                  <li v-if="isVisible('101000')">
                    <p>
                      <a href="/index2?platformId=101000">聚贸农产品</a>
                    </p>
                  </li>
                  <li v-if="isVisible('101100')">
                    <p>
                      <a href="/index2?platformId=101100">聚贸工业品</a>
                    </p>
                  </li>
                  <li v-if="isVisible('101300')">
                    <p>
                      <a href="/index2?platformId=101300">聚贸机械</a>
                    </p>
                  </li>
                  <li v-if="isVisible('101200')">
                    <p>
                      <a href="/index2?platformId=101200">聚贸消费品</a>
                    </p>
                  </li>
                  <li v-if="isVisible('101400')">
                    <p>
                      <a href="/index2?platformId=101400">聚贸食品</a>
                    </p>
                  </li>
                </template>
                <template v-else>
                  <li>
                    <p>
                      须授权访问
                    </p>
                  </li>
                </template>
              </ul>
              <ul>
                <h6>服务平台BI</h6>
                <template v-if="hasVisiblePlatform(servicePlatformIds)">
                  <li v-if="isVisible('102000')">
                    <p>
                      <a href="/index2?platformId=102000">聚贸通</a>
                    </p>
                  </li>
                  <li v-if="isVisible('100400')">
                    <p>
                      <a href="/index2?platformId=100400">聚运通</a>
                    </p>
                  </li>
                  <li v-if="isVisible('101500')">
                    <p>
                      <a href="/index2?platformId=101500">聚融通</a>
                    </p>
                  </li>
                  <li v-if="isVisible('101900')">
                    <p>
                      <a href="/index2?platformId=101900">聚咨询</a>
                    </p>
                  </li>
                </template>
                <template v-else>
                  <li>
                    <p>
                      须授权访问
                    </p>
                  </li>
                </template>
              </ul>
            </div>
          </el-tab-pane>
          <el-tab-pane label="国际站" name="second">
            <!--英文地址切换-->
            <div class="en-item-list clearfix">
              <ul >
                <h6>Commodities</h6>
                <template v-if="hasVisiblePlatform(tradePlatformList)">
                  <li v-if="isVisible('100101')">
                    <p>
                      <a href="/index2?platformId=100101">JUMORE Global</a>
                    </p>
                  </li>
                  <li v-if="isVisible('100301')">
                    <p>
                      <a href="/index2?platformId=100301">JUMORE Non-ferrous</a>
                    </p>
                  </li>
                  <li v-if="isVisible('100701')">
                    <p>
                      <a href="/index2?platformId=100701">JUMORE Coal</a>
                    </p>
                  </li>
                  <li v-if="isVisible('100801')">
                    <p>
                      <a href="/index2?platformId=100801">JUMORE Steel</a>
                    </p>
                  </li>
                  <li v-if="isVisible('100901')">
                    <p>
                      <a href="/index2?platformId=100901">JUMORE Mineral</a>
                    </p>
                  </li>
                  <li v-if="isVisible('101001')">
                    <p>
                      <a href="/index2?platformId=101001">JUMORE Agricultural Products</a>
                    </p>
                  </li>
                </template>
                <template v-else>
                  <li>
                    <p>
                      须授权访问
                    </p>
                  </li>
                </template>
              </ul>
              <ul >
                <h6>Intermediate & Final Goods</h6>
                <template v-if="hasVisiblePlatform(tradePlatformList)">
                  <li v-if="isVisible('101101')">
                    <p>
                      <a href="/index2?platformId=101101">JUMORE Industrial Products</a><!--聚贸工业品-->
                    </p>
                  </li>
                  <li v-if="isVisible('101301')">
                    <p>
                      <a href="/index2?platformId=101301">JUMORE Machinery</a><!--聚贸机械-->
                    </p>
                  </li>
                  <li v-if="isVisible('101201')">
                    <p>
                      <a href="/index2?platformId=101201">JUMORE Consumer Goods</a><!--聚贸消费品-->
                    </p>
                  </li>
                  <li v-if="isVisible('101401')">
                    <p>
                      <a href="/index2?platformId=101401">JUMORE Food</a><!--聚贸食品-->
                    </p>
                  </li>
                </template>
                <template v-else>
                  <li>
                    <p>
                      须授权访问
                    </p>
                  </li>
                </template>
              </ul>
              <ul>
                <h6>Value-added Services</h6>
                <template v-if="hasVisiblePlatform(servicePlatformIds)">
                  <li v-if="isVisible('100401')">
                    <p>
                      <a href="/index2?platformId=100401">JUMORE EtransMore</a><!--运通-->
                    </p>
                  </li>
                  <li v-if="isVisible('101501')">
                    <p>
                      <a href="/index2?platformId=101501">JUMORE Finance</a><!--融通-->
                    </p>
                  </li>
                  <li v-if="isVisible('101801')">
                    <p>
                      <a href="/index2?platformId=101801">JUMORE Technology</a><!--智能-->
                    </p>
                  </li>
                  <li v-if="isVisible('101701')">
                    <p>
                      <a href="/index2?platformId=101701">JUMORE Certification</a><!--认证-->
                    </p>
                  </li>
                  <li v-if="isVisible('101901')">
                    <p>
                      <a href="/index2?platformId=101901">JUMORE Consultancy</a><!--咨询-->
                    </p>
                  </li>
                </template>
                <template v-else>
                  <li>
                    <p>
                      须授权访问
                    </p>
                  </li>
                </template>
              </ul>
            </div>
            <!--英文地址切换-->
          </el-tab-pane>
        </el-tabs>
        <!--下拉框 -->

      </a>
      <a href="/user/user" class="icon-user"></a>
      <a href="/user/list" class="icon-setting" v-show="currentUserRoleId !== '1'"></a>
      <a class="icon-logout" href="/doLogout"></a>
    </div>

  </header>
</template>
<script>
export default {
  name: 'header',
  created() {
    this.currentUserName = document.getElementById('currentUserName').value;
    this.currentUserRoleId = document.getElementById('currentUserRoleId').value;
    this.isAdmin = document.getElementById('currentUserRoleId').value !== '1';

    var visiblePlatformIds = document.querySelector('#visiblePlatformIds').value.split(',')
    this.platformList = visiblePlatformIds;

  },
  methods: {
    //中英文切换
    handleClick(tab, event) {
      console.log(tab, event);
    },
    hasVisiblePlatform(platformIds = []) {
      if (this.isAdmin) return true;
      for (var index = 0; index < platformIds.length; index++) {
        var id = platformIds[index];
        //可见的平台

        for (var j = 0; j < this.platformList.length; j++) {
          var accessableId = this.platformList[j];
          if (id === accessableId) {
            return true;
          }
        }
      }

      return false;
    },
    isVisible(platformId) {
      if (this.isAdmin) return true;
      return this.platformList.find(id => id === platformId);
    }
  },
  data() {
    return {
      activeName2: 'first',//切换
      currentUserName: '',
      currentUserRoleId: '',
      platformList: [],
      hasServicePlatform: false,
      hasTradePlatform: false,
      isAdmin: false,
      servicePlatformIds: ['100100', '100300', '100700', '100800', '100900', '101000', '101100', '101300', '101200', '101400'],
      tradePlatformIds: ['102000', '100400']
    }
  }
}

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">

  $topbar-height: 60px !default;
  .top-header {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 50000;
    width: 100%;
    height: $topbar-height;
    color: #fff;
    background-color: #01143d;
    min-width: 320px;
    padding-left: 20px;
  }

  .top-header .jm-logo {
    color: #fff;
    font-size: 18px;
    line-height: $topbar-height;
  }

  .top-header .jm-logo img {
    width: 37px;
    height: 30px;
    margin-right: 10px;
    display: inline-block;
    vertical-align: middle;
  }

  .welcome {
    font-size: 14px;
    padding-left: 20px;
    line-height: $topbar-height;
  }

  .user-profile {
    line-height: $topbar-height;
  .platform {
    font-size: 14px;
    position: relative;
    top: -2px;
  }
  }

  .icon-user {
    display: inline-block;
    width: 19px;
    height: 18px;
    margin-left: 20px;
    background: url(../assets/images/top-header/icon-user.png) no-repeat;
  }

  .icon-setting {
    display: inline-block;
    width: 18px;
    height: 18px;
    margin-left: 20px;
    background: url(../assets/images/top-header/icon-setting.png) no-repeat;
  }

  .icon-logout {
    display: inline-block;
    width: 18px;
    height: 19px;
    margin-left: 20px;
    margin-right: 40px;
    background: url(../assets/images/top-header/icon-logout.png) no-repeat;
  }
  /*英文样式修改*/
  .second-nav {
  .el-tabs__item{
    padding-left: 20px;
    padding-right: 20px;
    margin-right: 15px;
    display: inline-block;
    height: 28px;
    line-height: 28px;
    border-radius: 21px;
    color: #fff;
    font-size: 14px;
    background: #cdcdcd;
    text-align: center;
  }
  .el-tabs__item.is-active{
    background: #2788e8;
  }
  }

  .second-nav .el-tabs__nav-scroll{
    border-bottom: 1px solid #2788e8;
    padding-bottom: 15px;
  }

  .platform {
    position: relative;
    height: 60px;
    line-height: 1.375;
    display: inline-block;
  .dropdown {
  .icon-link {
    display: inline-block;
    width: 12px;
    height: 7px;
    position: relative;
    top: -2px;
    margin-left: 10px;
    background: url(../assets/images/icon-arrow-up.png) no-repeat;
    transition: all 0.5s
  }
  }
  .second-nav {
    position: absolute;
    border: 1px solid #d9d9d9;
    right: 0;
    top: 41px;
    z-index: 6;
    background: #fff;
    overflow: hidden;
    display: none;
    width: 680px;
    padding: 15px;
    color: #666;

  .en-item-list{

  ul {
    display: inline-block;
    float: left;
    list-style: none;
    margin: 0;
    padding: 0;
    width: 198px;
    box-sizing: border-box;
    border-right: 1px solid #dedede;
    margin-left: 30px;
    padding-right:20px;
  &:first-child{
     margin-left: 15px;
     padding-right:0;
   }
  &:last-child {
     width: 200px;
     margin-left: 30px;
     border-right: none;
     padding-right:0px;
   }

  li {

    box-sizing: border-box;
    margin-bottom: 15px;
  }
  p {
    margin: 0;
  }
  h6 {
    margin: 0;
    margin-bottom: 18px;
    font-size: 14px;
    font-weight: normal;
    font-weight: 700;
  }
  a {
    font-size: 14px;
    color: #666;
  &:hover {
     color: #0588e6;
   }
  }
  }
  }
  .tab-title{
    border-bottom: 1px solid #2788e8;
    margin:0 15px;
    padding: 10px 20px;
  span.tab{
    padding-left: 20px;
    padding-right: 20px;
    margin-right: 15px;
    display: inline-block;
    height: 28px;
    line-height: 28px;
    border-radius: 21px;
    color: #fff;
    font-size: 14px;
    background: #cdcdcd;
    text-align: center;

  }
  span.tab-current{
    background: #2788e8;
  }
  }
  .item-list{
  /*margin-top: 20px;*/
  ul {
    display: inline-block;
    float: left;
    list-style: none;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  &:first-child {
     width: 325px;
     margin-left:15px;
     border-right: 1px solid #dedede;
   }
  &:last-child {
     width: 215px;
     margin-left: 30px;
   }
  li {
    float: left;
    width: 33.3333333%;
    box-sizing: border-box;
    margin-bottom: 15px;
  }
  p {
    margin: 0;
  }
  h6 {
    margin: 0;
    margin-bottom: 18px;
    font-size: 16px;
    font-weight: normal;
  }
  a {
    font-size: 14px;
    color: #666;
  &:hover {
     color: #0588e6;
   }
  }
  }
  }

  }
  &:hover {
  .dropdown {
  .icon-link {
    transform: rotate(180deg);
  }
  }
  .second-nav {
    display: block;
  }
  }
  }
</style>
