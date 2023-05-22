<!-- 最新动态 -->
<template>
  <a-card :title="title" :bordered="false" :body-style="{ padding: '6px 0' }">
    <template #extra>
      <more-icon @remove="onRemove" @edit="onEdit" />
    </template>
    <div
      style="height: 346px; padding: 22px 20px 0 20px"
      class="ele-scrollbar-hover"
    >
      <a-timeline>
        <a-timeline-item
          v-for="item in activities"
          :key="item.id"
          :color="item.color"
        >
          <em>{{ item.time }}</em>
          <em>{{ item.title }}</em>
        </a-timeline-item>
      </a-timeline>
    </div>
  </a-card>
</template>

<script setup>
  import { ref } from 'vue';
  import MoreIcon from './more-icon.vue';

  defineProps({
    title: String
  });

  const emit = defineEmits(['remove', 'edit']);

  // 最新动态数据
  const activities = ref([]);

  /* 查询最新动态 */
  const queryActivities = () => {
    activities.value = [
      {
        id: 1,
        title: 'SunSmile 解决了bug 登录提示操作失败',
        time: '20:30',
        color: 'gray'
      },
      {
        id: 2,
        title: 'Jasmine 解决了bug 按钮颜色与设计不符',
        time: '19:30',
        color: 'gray'
      },
      {
        id: 3,
        title: '项目经理 指派了任务 解决项目一的bug',
        time: '18:30'
      },
      {
        id: 4,
        title: '项目经理 指派了任务 解决项目二的bug',
        time: '17:30'
      },
      {
        id: 5,
        title: '项目经理 指派了任务 解决项目三的bug',
        time: '16:30'
      },
      {
        id: 6,
        title: '项目经理 指派了任务 解决项目四的bug',
        time: '15:30',
        color: 'gray'
      },
      {
        id: 7,
        title: '项目经理 指派了任务 解决项目五的bug',
        time: '14:30',
        color: 'gray'
      },
      {
        id: 8,
        title: '项目经理 指派了任务 解决项目六的bug',
        time: '12:30',
        color: 'gray'
      },
      {
        id: 9,
        title: '项目经理 指派了任务 解决项目七的bug',
        time: '11:30'
      },
      {
        id: 10,
        title: '项目经理 指派了任务 解决项目八的bug',
        time: '10:30',
        color: 'gray'
      },
      {
        id: 11,
        title: '项目经理 指派了任务 解决项目九的bug',
        time: '09:30',
        color: 'green'
      },
      {
        id: 12,
        title: '项目经理 指派了任务 解决项目十的bug',
        time: '08:30',
        color: 'red'
      }
    ];
  };

  const onRemove = () => {
    emit('remove');
  };

  const onEdit = () => {
    emit('edit');
  };

  queryActivities();
</script>

<style lang="less" scoped>
  .ele-scrollbar-hover
    :deep(.ant-timeline-item-last > .ant-timeline-item-content) {
    min-height: auto;
  }
</style>
