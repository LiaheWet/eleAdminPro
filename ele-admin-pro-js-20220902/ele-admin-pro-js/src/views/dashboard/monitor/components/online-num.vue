<template>
  <a-card :bordered="false" title="在线人数">
    <div class="monitor-online-num-card">
      <div>{{ currentTime }}</div>
      <div class="monitor-online-num-title">
        <ele-count-up :end-val="onlineNum" />
      </div>
      <div class="monitor-online-num-text">在线总人数</div>
      <a-badge status="processing" :text="updateTimeText" />
    </div>
  </a-card>
</template>

<script setup>
  import { ref, computed, onBeforeUnmount } from 'vue';
  import { toDateString } from 'ele-admin-pro/es';
  // 在线人数更新定时器
  let onlineNumTimer = null;

  // 在线总人数倒计时
  const updateTime = ref(10);

  // 当前时间
  const currentTime = ref(toDateString(new Date(), 'HH:mm:ss'));

  // 在线人数
  const onlineNum = ref(228);

  // 在线人数倒计时文字
  const updateTimeText = computed(() => updateTime.value + ' 秒后更新');

  /* 在线人数更新倒计时 */
  const startUpdateOnlineNum = () => {
    onlineNumTimer = window.setInterval(() => {
      currentTime.value = toDateString(new Date(), 'HH:mm:ss');
      if (updateTime.value === 1) {
        updateTime.value = 10;
        onlineNum.value = 100 + Math.round(Math.random() * 900);
      } else {
        updateTime.value--;
      }
    }, 1000);
  };

  onBeforeUnmount(() => {
    // 销毁定时器
    if (onlineNumTimer) {
      clearInterval(onlineNumTimer);
      onlineNumTimer = null;
    }
  });

  startUpdateOnlineNum();
</script>

<style lang="less" scoped>
  .monitor-online-num-card {
    text-align: center;
  }

  .monitor-online-num-title {
    line-height: 1;
    font-size: 50px;
    margin: 22px 0 14px;
  }

  .monitor-online-num-text {
    margin-bottom: 22px;
  }
</style>
