<!-- 机构类型选择下拉框 -->
<template>
  <a-select
    allow-clear
    :value="value"
    :placeholder="placeholder"
    @update:value="updateValue"
  >
    <a-select-option
      v-for="item in data"
      :key="item.dictDataCode"
      :value="item.dictDataCode"
    >
      {{ item.dictDataName }}
    </a-select-option>
  </a-select>
</template>

<script setup>
  import { ref } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { listDictionaryData } from '@/api/system/dictionary-data';

  const emit = defineEmits(['update:value']);

  defineProps({
    value: String,
    placeholder: {
      type: String,
      default: '请选择机构类型'
    }
  });

  // 机构类型数据
  const data = ref([]);

  /* 更新选中数据 */
  const updateValue = (value) => {
    emit('update:value', value);
  };

  /* 获取机构类型数据 */
  listDictionaryData({
    dictCode: 'organization_type'
  })
    .then((list) => {
      data.value = list;
    })
    .catch((e) => {
      message.error(e.message);
    });
</script>
