<!-- 本月目标 -->
<template>
  <a-card :title="title" :bordered="false">
    <template #extra>
      <more-icon @remove="onRemove" @edit="onEdit" />
    </template>
    <div class="workplace-goal-group">
      <a-progress
        :width="180"
        :percent="80"
        type="dashboard"
        :stroke-width="4"
        :show-info="false"
      />
      <div class="workplace-goal-content">
        <ele-tag color="blue" size="large" shape="circle">
          <trophy-outlined />
        </ele-tag>
        <div class="workplace-goal-num">285</div>
      </div>
      <div class="workplace-goal-text">恭喜, 本月目标已达标!</div>
    </div>
  </a-card>
</template>

<script setup>
  import { TrophyOutlined } from '@ant-design/icons-vue';
  import MoreIcon from './more-icon.vue';

  defineProps({
    title: String
  });

  const emit = defineEmits(['remove', 'edit']);

  const onRemove = () => {
    emit('remove');
  };

  const onEdit = () => {
    emit('edit');
  };
</script>

<style lang="less" scoped>
  .workplace-goal-group {
    height: 310px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: relative;

    .workplace-goal-content {
      position: absolute;
      top: 50%;
      left: 50%;
      width: 180px;
      margin: -50px 0 0 -90px;
      text-align: center;
    }

    .workplace-goal-num {
      font-size: 40px;
    }
  }
</style>
