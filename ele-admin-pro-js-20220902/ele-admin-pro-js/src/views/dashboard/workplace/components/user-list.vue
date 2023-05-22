<!-- 小组成员 -->
<template>
  <a-card :title="title" :bordered="false" :body-style="{ padding: '2px 0px' }">
    <template #extra>
      <more-icon @remove="onRemove" @edit="onEdit" />
    </template>
    <div
      v-for="(item, index) in userList"
      :key="index"
      class="ele-cell user-list-item"
    >
      <div style="flex-shrink: 0">
        <a-avatar :size="46" :src="item.avatar" />
      </div>
      <div class="ele-cell-content">
        <div class="ele-cell-title ele-elip">{{ item.name }}</div>
        <div class="ele-cell-desc ele-elip">{{ item.introduction }}</div>
      </div>
      <div style="flex-shrink: 0">
        <a-tag :color="['green', 'red'][item.status]">
          {{ ['在线', '离线'][item.status] }}
        </a-tag>
      </div>
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

  // 小组成员数据
  const userList = ref([]);

  /* 查询小组成员 */
  const queryUserList = () => {
    userList.value = [
      {
        name: 'SunSmile',
        introduction: 'UI设计师、交互专家',
        status: 0,
        avatar:
          'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
      },
      {
        name: '你的名字很好听',
        introduction: '前端工程师',
        status: 0,
        avatar:
          'https://cdn.eleadmin.com/20200609/b6a811873e704db49db994053a5019b2.jpg'
      },
      {
        name: '全村人的希望',
        introduction: '前端工程师',
        status: 0,
        avatar:
          'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
      },
      {
        name: 'Jasmine',
        introduction: '产品经理、项目经理',
        status: 1,
        avatar:
          'https://cdn.eleadmin.com/20200609/f6bc05af944a4f738b54128717952107.jpg'
      },
      {
        name: '酷酷的大叔',
        introduction: '组长、后端工程师',
        status: 1,
        avatar:
          'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
      }
    ];
  };

  const onRemove = () => {
    emit('remove');
  };

  const onEdit = () => {
    emit('edit');
  };

  queryUserList();
</script>

<style lang="less" scoped>
  .user-list-item {
    padding: 12px 18px;

    & + .user-list-item {
      border-top: 1px solid hsla(0, 0%, 60%, 0.15);
    }

    .ele-cell-content {
      overflow: hidden;
    }

    .ele-cell-desc {
      margin-top: 0;
    }

    .ant-tag {
      margin: 0;
    }
  }
</style>
