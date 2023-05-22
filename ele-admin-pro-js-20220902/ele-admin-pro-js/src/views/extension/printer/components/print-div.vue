<template>
  <a-card title="打印指定区域" :bordered="false">
    <div ref="printRef" class="demo-print-group">
      <div class="demo-print-div">示例示例示例示例示例</div>
      <div class="demo-print-right">
        <div>
          <ele-tag size="mini" color="blue">示例</ele-tag>
          <ele-tag size="mini" color="green">示例</ele-tag>
          <ele-tag size="mini" color="orange">示例</ele-tag>
        </div>
        <div style="margin-top: 12px">
          <a-input v-model:value="value" />
        </div>
      </div>
    </div>
    <div style="margin-top: 20px">
      <a-button @click="print">打印</a-button>
    </div>
  </a-card>
</template>

<script setup>
  import { ref } from 'vue';
  import { printElement } from 'ele-admin-pro/es';

  const printRef = ref(null);

  const print = () => {
    printElement(printRef.value);
  };

  const value = ref('示例示例示例');
</script>

<style lang="less" scoped>
  .demo-print-group {
    display: flex;
    align-items: center;
  }

  .demo-print-div {
    background: #096dd9;
    color: #fff;
    font-size: 18px;
    text-align: center;
    padding: 40px 0;
    flex: 1;
    border: 2px solid #096dd9;
    height: 120px;
    box-sizing: border-box;
    border-top-left-radius: 8px;
    border-bottom-left-radius: 8px;
  }

  .demo-print-right {
    flex: 1;
    padding: 20px;
    border: 2px solid #096dd9;
    height: 120px;
    box-sizing: border-box;
    border-top-right-radius: 8px;
    border-bottom-right-radius: 8px;
  }
</style>
