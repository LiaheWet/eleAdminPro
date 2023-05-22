<!-- 顶栏右侧区域 -->
<template>
  <div class="ele-admin-header-tool">
    <!-- 全屏切换 -->
    <div
      class="ele-admin-header-tool-item hidden-sm-and-down"
      @click="toggleFullscreen"
    >
      <fullscreen-exit-outlined v-if="fullscreen" />
      <fullscreen-outlined v-else />
    </div>
    <!-- 语言切换 -->
    <div class="ele-admin-header-tool-item">
      <i18n-icon />
    </div>
    <!-- 消息通知 -->
    <div class="ele-admin-header-tool-item">
      <header-notice />
    </div>
    <!-- 用户信息 -->
    <div class="ele-admin-header-tool-item">
      <a-dropdown placement="bottom" :overlay-style="{ minWidth: '120px' }">
        <div class="ele-admin-header-avatar">
          <a-avatar :src="loginUser.avatar">
            <template v-if="!loginUser.avatar" #icon>
              <user-outlined />
            </template>
          </a-avatar>
          <span class="hidden-sm-and-down">{{ loginUser.nickname }}</span>
          <down-outlined style="margin-left: 6px" />
        </div>
        <template #overlay>
          <a-menu :selectable="false" @click="onUserDropClick">
            <a-menu-item key="profile">
              <div class="ele-cell">
                <user-outlined />
                <div class="ele-cell-content">
                  {{ t('layout.header.profile') }}
                </div>
              </div>
            </a-menu-item>
            <a-menu-item key="password">
              <div class="ele-cell">
                <key-outlined />
                <div class="ele-cell-content">
                  {{ t('layout.header.password') }}
                </div>
              </div>
            </a-menu-item>
            <a-menu-divider />
            <a-menu-item key="logout">
              <div class="ele-cell">
                <logout-outlined />
                <div class="ele-cell-content">
                  {{ t('layout.header.logout') }}
                </div>
              </div>
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </div>
    <!-- 主题设置 -->
    <div class="ele-admin-header-tool-item" @click="openSetting">
      <more-outlined />
    </div>
  </div>
  <!-- 修改密码弹窗 -->
  <password-modal v-model:visible="passwordVisible" />
  <!-- 主题设置抽屉 -->
  <setting-drawer v-model:visible="settingVisible" />
</template>

<script setup>
  import { computed, createVNode, ref } from 'vue';
  import { useRouter } from 'vue-router';
  import { useI18n } from 'vue-i18n';
  import { Modal } from 'ant-design-vue/es';
  import {
    DownOutlined,
    MoreOutlined,
    UserOutlined,
    KeyOutlined,
    LogoutOutlined,
    ExclamationCircleOutlined,
    FullscreenOutlined,
    FullscreenExitOutlined
  } from '@ant-design/icons-vue';
  import HeaderNotice from './header-notice.vue';
  import PasswordModal from './password-modal.vue';
  import SettingDrawer from './setting-drawer.vue';
  import I18nIcon from './i18n-icon.vue';
  import { useUserStore } from '@/store/modules/user';
  import { logout } from '@/utils/page-tab-util';

  const emit = defineEmits(['fullscreen']);

  defineProps({
    // 是否是全屏
    fullscreen: Boolean
  });

  const { push } = useRouter();
  const { t } = useI18n();
  const userStore = useUserStore();

  // 是否显示修改密码弹窗
  const passwordVisible = ref(false);

  // 是否显示主题设置抽屉
  const settingVisible = ref(false);

  // 当前用户信息
  const loginUser = computed(() => userStore.info ?? {});

  /* 用户信息下拉点击 */
  const onUserDropClick = ({ key }) => {
    if (key === 'password') {
      passwordVisible.value = true;
    } else if (key === 'profile') {
      push('/user/profile');
    } else if (key === 'logout') {
      // 退出登录
      Modal.confirm({
        title: t('layout.logout.title'),
        content: t('layout.logout.message'),
        icon: createVNode(ExclamationCircleOutlined),
        maskClosable: true,
        onOk: () => {
          logout();
        }
      });
    }
  };

  /* 切换全屏 */
  const toggleFullscreen = () => {
    emit('fullscreen');
  };

  /* 打开主题设置抽屉 */
  const openSetting = () => {
    settingVisible.value = true;
  };
</script>
