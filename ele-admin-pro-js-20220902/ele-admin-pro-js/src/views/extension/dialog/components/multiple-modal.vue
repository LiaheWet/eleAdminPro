<template>
  <a-card title="同时打开多个弹窗" :bordered="false">
    <a-space>
      <a-button type="primary" class="ele-btn-icon" @click="openDialog1">
        打开弹窗1
      </a-button>
      <a-button type="primary" class="ele-btn-icon" @click="openDialog2">
        打开弹窗2
      </a-button>
      <a-button type="primary" class="ele-btn-icon" @click="openDialog3">
        打开弹窗3
      </a-button>
    </a-space>
    <p style="margin-top: 20px">同时打开多个弹窗时点击会自动置顶</p>
  </a-card>
  <ele-modal
    :width="400"
    title="弹窗1"
    v-model:visible="visible1"
    :resizable="true"
    :maxable="true"
    :multiple="true"
    :destroy-on-close="false"
    :move-out="true"
    :move-out-positive="true"
    position="center"
  >
    <div style="padding: 20px 0">弹窗1</div>
  </ele-modal>
  <ele-modal
    :width="400"
    title="弹窗2"
    v-model:visible="visible2"
    :resizable="true"
    :maxable="true"
    :multiple="true"
    :destroy-on-close="false"
    :move-out="true"
    :move-out-positive="true"
    position="rightTop"
  >
    <div style="padding: 20px 0">弹窗2</div>
  </ele-modal>
  <ele-modal
    :width="400"
    title="弹窗3"
    v-model:visible="visible3"
    :resizable="true"
    :maxable="true"
    :multiple="true"
    :destroy-on-close="false"
    :move-out="true"
    :move-out-positive="true"
    position="rightBottom"
  >
    <div style="padding: 20px 0">弹窗3</div>
  </ele-modal>
</template>

<script setup>
  import { ref } from 'vue';

  // 弹窗是否打开
  const visible1 = ref(false);
  const visible2 = ref(false);
  const visible3 = ref(false);

  /* 打开弹窗 */
  const openDialog1 = () => {
    visible1.value = true;
  };
  const openDialog2 = () => {
    visible2.value = true;
  };
  const openDialog3 = () => {
    visible3.value = true;
  };
</script>
