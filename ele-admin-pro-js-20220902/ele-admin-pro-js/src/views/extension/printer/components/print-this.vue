<template>
  <a-card title="打印当前页面" :bordered="false">
    <a-form
      :label-col="{ span: 6 }"
      :wrapper-col="{ span: 18 }"
      style="max-width: 320px"
    >
      <a-form-item label="纸张方向">
        <a-select
          allow-clear
          :value="{ true: 1, false: 0 }[String(option.horizontal)]"
          placeholder="不设置"
          @update:value="updateHorizontal"
        >
          <a-select-option :value="1">横向</a-select-option>
          <a-select-option :value="0">纵向</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="页面间距">
        <a-select
          allow-clear
          v-model:value="option.margin"
          placeholder="不设置"
        >
          <a-select-option value="0px">0px</a-select-option>
          <a-select-option value="50px">50px</a-select-option>
          <a-select-option value="100px">100px</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="页面标题">
        <a-input
          allow-clear
          v-model:value="option.title"
          placeholder="不设置"
        />
      </a-form-item>
    </a-form>
    <a-space>
      <a-button @click="print">打印</a-button>
      <a-button class="ele-btn-icon" @click="printHide">
        打印隐藏指定内容
      </a-button>
    </a-space>
    <div style="margin-top: 16px">
      <span class="ele-text-primary ele-printer-hide">
        此段内容在所有打印时隐藏, 打印完复原。
      </span>
      <span class="ele-text-danger demo-hide-1">
        此段内容在指定打印时才隐藏。
      </span>
    </div>
  </a-card>
</template>

<script setup>
  import { reactive } from 'vue';
  import { printThis } from 'ele-admin-pro/es';

  // 打印当前页面参数
  const option = reactive({
    horizontal: undefined,
    margin: undefined,
    title: ''
  });

  /* 打印当前页面 */
  const print = () => {
    printThis(option);
  };

  /* 打印隐藏指定内容 */
  const printHide = () => {
    printThis({
      ...option,
      hide: ['.demo-hide-1']
    });
  };

  const updateHorizontal = (value) => {
    option.horizontal = { 1: true, 0: false }[String(value)];
  };
</script>
