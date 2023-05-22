<!-- 角色选择下拉框 -->
<template>
  <a-select
    allow-clear
    mode="multiple"
    :value="roleIds"
    :placeholder="placeholder"
    @update:value="updateValue"
    @blur="onBlur"
  >
    <a-select-option
      v-for="item in data"
      :key="item.roleId"
      :value="item.roleId"
    >
      {{ item.roleName }}
    </a-select-option>
  </a-select>
</template>

<script setup>
  import { ref, computed } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { listRoles } from '@/api/system/role';

  const emit = defineEmits(['update:value', 'blur']);

  const props = defineProps({
    // 选中的角色
    value: Array,
    //
    placeholder: {
      type: String,
      default: '请选择角色'
    }
  });

  // 选中的角色id
  const roleIds = computed(() => props.value?.map((d) => d.roleId));

  // 角色数据
  const data = ref([]);

  /* 更新选中数据 */
  const updateValue = (value) => {
    emit(
      'update:value',
      value.map((v) => ({ roleId: v }))
    );
  };

  /* 获取角色数据 */
  listRoles()
    .then((list) => {
      data.value = list;
    })
    .catch((e) => {
      message.error(e.message);
    });

  /* 失去焦点 */
  const onBlur = () => {
    emit('blur');
  };
</script>
