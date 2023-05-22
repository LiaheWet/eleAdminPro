<template>
  <a-card title="打印任意内容" :bordered="false">
    <a-form
      :label-col="{ span: 6 }"
      :wrapper-col="{ span: 18 }"
      style="max-width: 320px"
    >
      <a-form-item label="loading">
        <a-radio-group v-model:value="option.loading">
          <a-radio :value="false">不显示</a-radio>
          <a-radio :value="true">显示</a-radio>
        </a-radio-group>
      </a-form-item>
    </a-form>
    <a-space style="flex-wrap: wrap">
      <a-button class="ele-btn-icon" @click="printAnyHtml">
        打印任意内容
      </a-button>
      <a-button class="ele-btn-icon" @click="printAddHeader">
        设置页眉页脚
      </a-button>
      <a-button class="ele-btn-icon" @click="printImage">打印图片</a-button>
    </a-space>
  </a-card>
</template>

<script setup>
  import { reactive } from 'vue';
  import { printHtml } from 'ele-admin-pro/es';

  // 打印任意内容参数
  const option = reactive({
    loading: false
  });

  /* 打印任意内容 */
  const printAnyHtml = () => {
    printHtml({
      ...option,
      html: [
        '<h1 style="color: #1890ff;">EleAdmin 后台管理模板</h1>',
        '<div style="color: #F51D2C;">通用型后台管理模板，界面美观、开箱即用</div>'
      ].join('')
    });
  };

  /* 打印设置页眉页脚 */
  const printAddHeader = () => {
    printHtml({
      ...option,
      margin: 0,
      html: [
        '<div style="padding: 0 60px;">',
        Array(38).join('<h3>EleAdmin 后台管理模板</h3>'),
        '</div>'
      ].join(''),
      header: `
        <div style="display: flex;font-size: 12px;padding: 15px 30px 25px;">
          <div>我是页眉左侧</div>
          <div style="flex: 1;text-align: center;">我是页眉</div>
          <div>我是页眉右侧</div>
        </div>`,
      footer: `
        <div style="display: flex;font-size: 12px;padding: 15px 30px 25px;">
          <div>我是页脚左侧</div>
          <div style="flex: 1;text-align: center;">我是页脚</div>
          <div>我是页脚右侧</div>
        </div>`,
      style: '<style> h3 { color: red; } </style>'
    });
  };

  /* 打印图片 */
  const printImage = () => {
    printHtml({
      ...option,
      margin: 0,
      html: '<img src="https://cdn.eleadmin.com/20200610/LrCTN2j94lo9N7wEql7cBr1Ux4rHMvmZ.jpg" style="width: 100%;"/>'
    });
  };
</script>
