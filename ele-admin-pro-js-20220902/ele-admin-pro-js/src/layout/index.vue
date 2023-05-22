<template>
  <ele-pro-layout
    :menus="menus"
    :tabs="tabs"
    :collapse="collapse"
    :side-nav-collapse="sideNavCollapse"
    :body-fullscreen="bodyFullscreen"
    :show-tabs="showTabs"
    :show-footer="showFooter"
    :head-style="headStyle"
    :side-style="sideStyle"
    :layout-style="layoutStyle"
    :side-menu-style="sideMenuStyle"
    :tab-style="tabStyle"
    :fixed-header="fixedHeader"
    :fixed-sidebar="fixedSidebar"
    :fixed-body="fixedBody"
    :body-full="bodyFull"
    :logo-auto-size="logoAutoSize"
    :colorful-icon="colorfulIcon"
    :side-unique-open="sideUniqueOpen"
    :project-name="projectName"
    :hide-footers="HIDE_FOOTERS"
    :hide-sidebars="HIDE_SIDEBARS"
    :repeatable-tabs="REPEATABLE_TABS"
    :home-title="HOME_TITLE || t('layout.home')"
    :home-path="HOME_PATH"
    :layout-path="LAYOUT_PATH"
    :redirect-path="REDIRECT_PATH"
    :locale="locale"
    :i18n="i18n"
    @update:collapse="updateCollapse"
    @update:side-nav-collapse="updateSideNavCollapse"
    @update:body-fullscreen="updateBodyFullscreen"
    @tab-add="addPageTab"
    @tab-remove="removePageTab"
    @tab-remove-all="removeAllPageTab"
    @tab-remove-left="removeLeftPageTab"
    @tab-remove-right="removeRightPageTab"
    @tab-remove-other="removeOtherPageTab"
    @reload-page="reloadPageTab"
    @logo-click="onLogoClick"
    @screen-size-change="screenSizeChange"
    @set-home-components="setHomeComponents"
    @tab-context-menu="onTabContextMenu"
  >
    <!-- 路由出口 -->
    <router-layout />
    <!-- logo 图标 -->
    <template #logo>
      <img src="/src/assets/logo.svg" alt="logo" />
    </template>
    <!-- 顶栏右侧区域 -->
    <template #right>
      <header-tools :fullscreen="fullscreen" @fullscreen="onFullscreen" />
    </template>
    <!-- 全局页脚 -->
    <template #footer>
      <page-footer />
    </template>
    <!-- 菜单图标 -->
    <template #icon="{ icon }">
      <component :is="icon" class="ant-menu-item-icon" />
    </template>
    <!-- 自定义菜单标题增加徽章、小红点 -->
    <template #title="{ item }">
      <menu-title :item="item" />
    </template>
    <template #top-title="{ item }">
      <menu-title :item="item" />
    </template>
    <template #nav-title="{ item }">
      <menu-title :item="item" />
    </template>
  </ele-pro-layout>
</template>

<script setup>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  import { storeToRefs } from 'pinia';
  import { useI18n } from 'vue-i18n';
  import { message } from 'ant-design-vue/es';
  import { toggleFullscreen, isFullscreen } from 'ele-admin-pro/es';
  import { useUserStore } from '@/store/modules/user';
  import { useThemeStore } from '@/store/modules/theme';
  import RouterLayout from '@/components/RouterLayout/index.vue';
  import HeaderTools from './components/header-tools.vue';
  import PageFooter from './components/page-footer.vue';
  import MenuTitle from './components/menu-title.vue';
  import {
    HIDE_SIDEBARS,
    HIDE_FOOTERS,
    REPEATABLE_TABS,
    HOME_TITLE,
    HOME_PATH,
    LAYOUT_PATH,
    REDIRECT_PATH,
    I18N_ENABLE
  } from '@/config/setting';
  import {
    addPageTab,
    removePageTab,
    removeAllPageTab,
    removeLeftPageTab,
    removeRightPageTab,
    removeOtherPageTab,
    reloadPageTab,
    setHomeComponents
  } from '@/utils/page-tab-util';

  const { push } = useRouter();
  const { t, locale } = useI18n();
  const userStore = useUserStore();
  const themeStore = useThemeStore();

  // 项目名
  const projectName = import.meta.env.VITE_APP_NAME;

  // 是否全屏
  const fullscreen = ref(false);

  // 菜单数据
  const { menus } = storeToRefs(userStore);

  // 布局风格
  const {
    tabs,
    collapse,
    sideNavCollapse,
    bodyFullscreen,
    showTabs,
    showFooter,
    headStyle,
    sideStyle,
    layoutStyle,
    sideMenuStyle,
    tabStyle,
    fixedHeader,
    fixedSidebar,
    fixedBody,
    bodyFull,
    logoAutoSize,
    colorfulIcon,
    sideUniqueOpen
  } = storeToRefs(themeStore);

  /* 侧栏折叠切换 */
  const updateCollapse = (value) => {
    themeStore.setCollapse(value);
  };

  /* 双侧栏一级折叠切换 */
  const updateSideNavCollapse = (value) => {
    themeStore.setSideNavCollapse(value);
  };

  /* 内容区域全屏切换 */
  const updateBodyFullscreen = (value) => {
    themeStore.setBodyFullscreen(value);
  };

  /* logo 点击事件 */
  const onLogoClick = (isHome) => {
    isHome || push(LAYOUT_PATH);
  };

  /* 监听屏幕尺寸改变 */
  const screenSizeChange = () => {
    themeStore.updateScreenSize();
    fullscreen.value = isFullscreen();
  };

  /* 全屏切换 */
  const onFullscreen = () => {
    try {
      fullscreen.value = toggleFullscreen();
    } catch (e) {
      message.error('您的浏览器不支持全屏模式');
    }
  };

  /* 页签右键菜单点击事件 */
  const onTabContextMenu = ({ key, tabKey, item, active }) => {
    switch (key) {
      case 'reload': // 刷新
        reloadPageTab({
          isHome: !item,
          fullPath: item?.fullPath ?? tabKey
        });
        break;
      case 'close': // 关闭当前
        removePageTab({
          key: item?.fullPath ?? tabKey,
          active
        });
        break;
      case 'left': // 关闭左侧
        removeLeftPageTab({
          key: tabKey,
          active
        });
        break;
      case 'right': // 关闭右侧
        removeRightPageTab({
          key: tabKey,
          active
        });
        break;
      case 'other': // 关闭其他
        removeOtherPageTab({
          key: tabKey,
          active
        });
        break;
    }
  };

  /* 菜单标题国际化 */
  const i18n = (_path, key) => {
    if (!I18N_ENABLE || !key) {
      return;
    }
    const k = 'route.' + key + '._name';
    const title = t(k);
    if (title !== k) {
      return title;
    }
  };
</script>

<script>
  import * as MenuIcons from './menu-icons';

  export default {
    name: 'EleLayout',
    components: MenuIcons
  };
</script>

<style lang="less">
  // 侧栏菜单徽章样式，定位在右侧垂直居中并调小尺寸
  .ele-menu-badge {
    position: absolute;
    top: 50%;
    right: 14px;
    line-height: 1;
    margin-top: -9px;
    font-size: 0;

    .ant-badge-count {
      height: 18px;
      line-height: 18px;
      border-radius: 9px;
      box-shadow: none;
      min-width: 18px;
      padding: 0 4px;
    }

    .ant-scroll-number-only {
      height: 18px;

      & > p.ant-scroll-number-only-unit {
        height: 18px;
      }
    }
  }

  // 父级菜单标题中右侧多定位一点，避免与箭头重合
  .ant-menu-submenu-title > .ant-menu-title-content .ele-menu-badge {
    right: 36px;
  }

  // 折叠悬浮中样式调整
  .ant-menu-submenu-popup {
    .ant-menu-submenu-title > .ant-menu-title-content .ele-menu-badge {
      right: 30px;
    }
  }

  // 顶栏菜单标题中样式调整
  .ele-admin-header-nav > .ant-menu {
    & > .ant-menu-item,
    & > .ant-menu-submenu > .ant-menu-submenu-title {
      & > .ant-menu-title-content .ele-menu-badge {
        position: static;
        right: auto;
        top: auto;
        display: inline-block;
        vertical-align: 5px;
        margin: 0 0 0 4px;
      }
    }
  }

  // 双侧栏时一级侧栏菜单中样式调整，定位在右上角
  .ele-admin-sidebar-nav-menu > .ant-menu {
    & > .ant-menu-item,
    & > .ant-menu-submenu > .ant-menu-submenu-title {
      & > .ant-menu-title-content .ele-menu-badge {
        top: 0;
        right: 0;
        margin: 0;
      }
    }
  }

  // 双侧栏时一级侧栏菜单折叠后样式调整
  .ele-admin-nav-collapse .ele-admin-sidebar-nav-menu > .ant-menu {
    & > .ant-menu-item,
    & > .ant-menu-submenu > .ant-menu-submenu-title {
      & > .ant-menu-title-content .ele-menu-badge {
        top: 0;
        right: 0;
      }
    }
  }

  // 菜单折叠后在 tooltip 中不显示徽章
  .ant-tooltip-inner .ele-menu-badge {
    display: none;
  }
</style>
