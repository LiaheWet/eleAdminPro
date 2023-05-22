<template>
  <a-dropdown placement="bottomRight">
    <more-outlined class="ele-text-secondary" style="font-size: 18px" />
    <template #overlay>
      <a-menu :selectable="false" @click="onClick">
        <a-menu-item key="edit">
          <div class="ele-cell">
            <edit-outlined />
            <div class="ele-cell-content">编辑</div>
          </div>
        </a-menu-item>
        <a-menu-item key="remove">
          <div class="ele-cell ele-text-danger">
            <delete-outlined />
            <div class="ele-cell-content">删除</div>
          </div>
        </a-menu-item>
      </a-menu>
    </template>
  </a-dropdown>
</template>

<script setup>
  import {
    MoreOutlined,
    EditOutlined,
    DeleteOutlined
  } from '@ant-design/icons-vue';

  const emit = defineEmits(['remove', 'edit']);

  const onClick = ({ key }) => {
    emit(key);
  };
</script>
