<!-- 文本超出隐藏 -->
<template>
  <div
    :class="[
      'demo-text-ellipsis ele-bg-white ele-border-split',
      { expanded: expanded }
    ]"
  >
    <div>{{ content }}</div>
    <div
      class="demo-text-ellipsis-footer ele-border-split ele-bg-white"
      @click="expanded = !expanded"
    >
      <up-outlined v-if="expanded" />
      <down-outlined v-else />
    </div>
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import { DownOutlined, UpOutlined } from '@ant-design/icons-vue';

  defineProps({
    content: String
  });

  const expanded = ref(false);
</script>

<style lang="less" scoped>
  .demo-text-ellipsis {
    border-radius: 4px;
    padding: 6px 12px 20px 12px;
    position: relative;
    border-width: 1px;
    border-style: solid;
    word-break: break-all;

    &:not(.expanded) {
      max-height: 192px;
      overflow: hidden;
    }
  }

  .demo-text-ellipsis-footer {
    border-top-width: 1px;
    border-top-style: solid;
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    text-align: center;
    font-size: 12px;
    cursor: pointer;
    border-bottom-left-radius: 4px;
    border-bottom-right-radius: 4px;
  }
</style>
