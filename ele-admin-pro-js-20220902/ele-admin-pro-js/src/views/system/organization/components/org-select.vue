<!-- 机构选择下拉框 -->
<template>
  <a-tree-select
    allow-clear
    tree-default-expand-all
    :placeholder="placeholder"
    :value="value || undefined"
    :tree-data="data"
    :dropdown-style="{ maxHeight: '360px', overflow: 'auto' }"
    @update:value="updateValue"
  />
</template>

<script setup>
  const emit = defineEmits(['update:value']);

  defineProps({
    // 选中的数据(v-modal)
    value: Number,
    // 提示信息
    placeholder: {
      type: String,
      default: '请选择角色'
    },
    // 机构数据
    data: Array
  });

  /* 更新选中数据 */
  const updateValue = (value) => {
    emit('update:value', value);
  };
</script>
