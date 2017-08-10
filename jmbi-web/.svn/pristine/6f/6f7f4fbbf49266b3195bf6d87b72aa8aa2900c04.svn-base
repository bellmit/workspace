<template>
  <aside class="jm-sidebar">
    <el-menu :default-active="activeIndex" class="" theme="dark" :router="true">

      <el-submenu :index="menu.path" v-if="isVisible(menu.meta)" v-for="menu in dataStructure.menuData" :key="menu.path">
        <template slot="title">
          <i class="icon" :class="menu.iconClass"></i>{{menu.name}}</template>

        <template v-for="subMenu in menu.items">
          <el-menu-item :index="subMenu.path" v-if="isVisible(subMenu.meta)">
            <span :class="subMenu.iconClass">{{subMenu.name}}</span>
          </el-menu-item>

          <el-menu-item :index="_subMenu.path" v-if="isVisible(_subMenu.meta)" v-for="_subMenu in subMenu.items" :key="_subMenu.path">
            <span :class="_subMenu.iconClass">{{_subMenu.name}}</span>
          </el-menu-item>
        </template>

      </el-submenu>

    </el-menu>
  </aside>
</template>
<script>
import { getMenuData } from '@/data/indexMenu.js'

export default {
  name: 'sideBarNav',
  created() {
    var visibleMenuList = document.querySelector('#visibleMenuList').value.split(',')
    this.isAdmin = document.getElementById('currentUserRoleId').value !== '1';
    this.MenuList = visibleMenuList;

    this.dataStructure = getMenuData(this.$platformId)

  },
  methods: {
    isVisible(menuIds) {
      if (this.isAdmin) return true;
      for (var index = 0; index < menuIds.length; index++) {
        var menuId = menuIds[index];
        var exist = this.MenuList.find(id => id === menuId);
        if (exist) {
          return true;
        }
      }
      return false;
    }
  },
  computed: {
    activeIndex() {
      return this.$route.name;
    }
  },
  data() {
    return {
      MenuList: [],
      dataStructure: [],
      isAdmin: false
    }
  }
}

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
$sidebar-width: 172px !default;
$topbar-height: 60px !default;
.jm-sidebar {
  width: $sidebar-width;
  top: $topbar-height;
  left: 0;
  z-index: 1001;
  display: block;
  min-height: 100%;
  color: #8f9aae;
  background-color: #0a245d;
  height: 100%;
  padding-top: 10px;
  position: fixed;
  .icon-item {
    position: relative;
    padding-left: 5px;
    &::after {
      content: '-';
      display: block;
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
      left: -10px;
    }
  }
  .sub-node-item {
    padding-left: 20px;
  }

  .el-menu--dark {
    background-color: #0a245d;
  }
  .el-menu--dark .el-menu-item.is-active,
  .el-menu--dark .el-menu-item:hover {
    color: #fff;
    background-color: #1c408e;
  }
  .el-menu--dark .el-submenu .el-menu .el-menu-item:hover {
    background-color: #1c408e;
  }
  .el-submenu__title {
    font-size: 14px;

    .icon {
      display: inline-block;
      width: 20px;
      height: 20px;
      position: relative;
      top: 4px;
      left: -8px;
      background: url(../assets/images/sidebar/menu-icon-group.png) no-repeat;
    }

    .icon-yhxw {
      background-position: 0px 0px
    }
    .icon-yyqk {
      background-position: -28px 0px
    }
    .icon-yjzx {
      background-position: -55px 0px
    }
    .icon-hdfx {
      background-position: -80px 0px
    }
    .icon-bibg {
      background-position: -108px 0px
    }
  }


  .el-submenu {
    border-bottom: 1px solid transparent;
  }
  .el-menu--dark .el-menu-item {
    background-color: #0a245d;
  }

  .el-menu--dark .el-submenu .el-menu {
    background-color: #0a245d;
  }

  .el-menu--dark .el-submenu__title {
    background-color: #061d4f;
  }

  .el-submenu.is-opened>.el-menu {
    margin-top: 15px;
    margin-bottom: 15px;
  }

  .el-menu-item,
  .el-submenu__title {
    height: 35px;
    line-height: 35px;
  }
}
</style>
