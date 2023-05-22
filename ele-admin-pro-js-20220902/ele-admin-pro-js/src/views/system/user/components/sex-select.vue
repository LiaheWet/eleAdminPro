<!-- 角色选择下拉框 -->
<template>
  <a-select
    show-search
    optionFilterProp="label"
    :options="data"
    allow-clear
    :value="value"
    :placeholder="placeholder"
    @update:value="updateValue"
    @blur="onBlur"
  />
</template>

<script setup>
  import { ref } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { listDictionaryData } from '@/api/system/dictionary-data';

  const emit = defineEmits(['update:value', 'blur']);

  defineProps({
    value: String,
    placeholder: {
      type: String,
      default: '请选择性别'
    }
  });

  // 字典数据
  const data = ref([]);

  /* 更新选中数据 */
  const updateValue = (value) => {
    emit('update:value', value);
  };

  /* 获取字典数据 */
  listDictionaryData({
    dictCode: 'sex'
  })
    .then((list) => {
      data.value = list.map((d) => {
        return {
          value: d.dictDataCode,
          label: d.dictDataName
        };
      });
    })
    .catch((e) => {
      message.error(e.message);
    });

  /* 失去焦点 */
  const onBlur = () => {
    emit('blur');
  };
</script>
