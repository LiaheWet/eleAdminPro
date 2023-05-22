<template>
  <a-card title="分页打印" :bordered="false">
    <a-space>
      <a-button class="ele-btn-icon" @click="printAnyPage">分页打印</a-button>
      <a-button class="ele-btn-icon" @click="printPageAddHeader">
        分页打印设置页眉页脚
      </a-button>
    </a-space>
  </a-card>
</template>

<script setup>
  import { printPage } from 'ele-admin-pro/es';

  /* 分页打印 */
  const printAnyPage = () => {
    printPage({
      pages: [
        '<h3>我是第一页</h3>',
        '<h3>我是第二页</h3>',
        '<h3>我是第三页</h3>',
        '<h3>我是第四页</h3>',
        '<h3>我是第五页</h3>'
      ],
      style: '<style> h3 { color: red; } </style>',
      loading: false
    });
  };

  /* 分页打印设置页眉页脚 */
  const printPageAddHeader = () => {
    printPage({
      pages: [
        '<h3>我是第一页</h3>',
        '<h3>我是第二页</h3>',
        '<h3>我是第三页</h3>',
        '<h3>我是第四页</h3>',
        '<h3>我是第五页</h3>'
      ],
      margin: 0,
      padding: '20px 60px',
      header: `
        <div style="display: flex;font-size: 12px;padding: 15px 30px;">
          <div>我是页眉左侧</div>
          <div style="flex: 1;text-align: center;">我是页眉</div>
          <div>我是页眉右侧</div>
        </div>`,
      footer: `
        <div style="display: flex;font-size: 12px;padding: 15px 30px;">
          <div>我是页脚左侧</div>
          <div style="flex: 1;text-align: center;">我是页脚</div>
          <div>我是页脚右侧</div>
        </div>`,
      style: '<style> h3 { color: red; } </style>',
      loading: false
    });
  };
</script>
