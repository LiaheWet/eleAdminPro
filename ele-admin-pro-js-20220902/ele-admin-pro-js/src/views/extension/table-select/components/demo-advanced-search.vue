<template>
  <div style="max-width: 160px">
    <a-input
      allow-clear
      size="small"
      v-model:value="where.keywords"
      placeholder="输入关键字搜索"
      prefix-icon="el-icon-search"
      @change="search"
    >
      <template #prefix>
        <search-outlined class="ele-text-placeholder" />
      </template>
    </a-input>
  </div>
</template>

<script setup>
  import { reactive } from 'vue';
  import { SearchOutlined } from '@ant-design/icons-vue';

  const emit = defineEmits(['search']);

  // 搜索表单
  const where = reactive({
    keywords: ''
  });

  /* 搜索 */
  const search = () => {
    emit('search', where);
  };
</script>
