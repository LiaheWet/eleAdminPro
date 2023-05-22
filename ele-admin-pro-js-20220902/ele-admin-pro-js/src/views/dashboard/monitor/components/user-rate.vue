<template>
  <a-card :bordered="false" title="用户评价">
    <div class="ele-cell ele-cell-align-bottom">
      <div style="font-size: 51px; line-height: 1">4.5</div>
      <div class="ele-cell-content">
        <a-rate :value="userRate" disabled />
        <span style="color: #fadb14; margin-left: 8px">很棒</span>
      </div>
    </div>
    <div class="ele-cell" style="margin: 18px 0">
      <div style="font-size: 28px; line-height: 1" class="ele-text-placeholder">
        -0%
      </div>
      <div class="ele-cell-content ele-text-small ele-text-secondary">
        当前没有评价波动
      </div>
    </div>
    <div class="ele-cell">
      <div class="ele-cell-content">
        <a-progress :percent="60" stroke-color="#52c41a" :show-info="false" />
      </div>
      <div class="monitor-evaluate-text">
        <star-filled />
        <span>5 : 368 人</span>
      </div>
    </div>
    <div class="ele-cell">
      <div class="ele-cell-content">
        <a-progress :percent="40" stroke-color="#1890ff" :show-info="false" />
      </div>
      <div class="monitor-evaluate-text">
        <star-filled />
        <span>4 : 256 人</span>
      </div>
    </div>
    <div class="ele-cell">
      <div class="ele-cell-content">
        <a-progress :percent="20" stroke-color="#faad14" :show-info="false" />
      </div>
      <div class="monitor-evaluate-text">
        <star-filled />
        <span>3 : 49 人</span>
      </div>
    </div>
    <div class="ele-cell">
      <div class="ele-cell-content">
        <a-progress :percent="10" stroke-color="#f5222d" :show-info="false" />
      </div>
      <div class="monitor-evaluate-text">
        <star-filled />
        <span>2 : 14 人</span>
      </div>
    </div>
    <div class="ele-cell">
      <div class="ele-cell-content">
        <a-progress :percent="0" :show-info="false" />
      </div>
      <div class="monitor-evaluate-text">
        <star-filled />
        <span>1 : 0 人</span>
      </div>
    </div>
  </a-card>
</template>

<script setup>
  import { ref } from 'vue';
  import { StarFilled } from '@ant-design/icons-vue';

  // 用户评分
  const userRate = ref(4.5);
</script>

<style lang="less" scoped>
  .monitor-evaluate-text {
    width: 90px;
    flex-shrink: 0;
    white-space: nowrap;
    opacity: 0.8;

    & > .anticon {
      font-size: 12px;
      margin: 0 6px 0 8px;
    }
  }
</style>
