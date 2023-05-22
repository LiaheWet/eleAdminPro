<!-- 自定义表格筛选dropdown的内容 -->
<template>
  <div style="padding: 8px">
    <div style="margin-bottom: 8px">
      <a-input
        placeholder="请输入关键字"
        v-model:value="nickname"
        @pressEnter="search"
      />
    </div>
    <a-space>
      <a-button size="small" type="primary" @click="search">
        <template #icon>
          <search-outlined />
        </template>
        <span>搜索</span>
      </a-button>
      <a-button size="small" style="min-width: 66px" @click="reset">
        重置
      </a-button>
    </a-space>
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import { SearchOutlined } from '@ant-design/icons-vue';

  const emit = defineEmits(['search']);

  const props = defineProps({
    // 设置筛选选中的方法
    setSelectedKeys: Function,
    // 筛选确认的方法
    confirm: Function,
    // 清除筛选的方法
    clearFilters: Function
  });

  const nickname = ref('');

  /* 搜索 */
  const search = () => {
    props.setSelectedKeys(nickname.value ? [nickname.value] : []);
    props.confirm();
    emit('search', nickname.value);
  };

  /*  重置 */
  const reset = () => {
    nickname.value = '';
    props.clearFilters();
    search();
  };
</script>
