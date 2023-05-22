<template>
  <ele-config-provider
    :map-key="MAP_KEY"
    :locale="eleLocale"
    :keep-alive="keepAlive"
    :license="LICENSE_CODE"
  >
    <a-config-provider :locale="antLocale">
      <router-view />
    </a-config-provider>
  </ele-config-provider>
</template>

<script setup>
  import { unref, computed } from 'vue';
  import { storeToRefs } from 'pinia';
  import { useThemeStore } from '@/store/modules/theme';
  import { MAP_KEY, LICENSE_CODE, TAB_KEEP_ALIVE } from '@/config/setting';
  import { useSetDocumentTitle } from '@/utils/document-title-util';
  import { useLocale } from '@/i18n/use-locale';
  const themeStore = useThemeStore();
  const { showTabs } = storeToRefs(themeStore);

  // 恢复主题
  themeStore.recoverTheme();

  // 切换路由自动更新浏览器页签标题
  useSetDocumentTitle();

  // 国际化配置
  const { antLocale, eleLocale } = useLocale();

  // 用于内链 iframe 组件获取 KeepAlive
  const keepAlive = computed(() => TAB_KEEP_ALIVE && unref(showTabs));
</script>
