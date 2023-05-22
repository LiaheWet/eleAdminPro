<!-- 全局页脚 -->
<template>
  <div class="ele-text-center" style="padding: 16px 0">
    <a-space size="large">
      <a class="ele-text-secondary" href="https://eleadmin.com" target="_blank">
        {{ t('layout.footer.website') }}
      </a>
      <a
        class="ele-text-secondary"
        href="https://eleadmin.com/doc/eleadminpro/"
        target="_blank"
      >
        {{ t('layout.footer.document') }}
      </a>
      <a
        class="ele-text-secondary"
        href="https://eleadmin.com/goods/9"
        target="_blank"
      >
        {{ t('layout.footer.authorization') }}
      </a>
    </a-space>
    <div class="ele-text-secondary" style="margin-top: 8px">
      {{ t('layout.footer.copyright') }}
    </div>
  </div>
</template>

<script setup>
  import { useI18n } from 'vue-i18n';

  const { t } = useI18n();
</script>
