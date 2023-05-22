<template>
  <a-card :bordered="false" :body-style="{ padding: '10px 20px' }">
    <ele-pro-table
      ref="tableRef"
      size="small"
      title="多列排序"
      row-key="userId"
      :columns="columns"
      :datasource="datasource"
      :scroll="{ x: 800 }"
    />
  </a-card>
</template>

<script setup>
  import { ref } from 'vue';
  import { toDateString } from 'ele-admin-pro/es';
  import { pageUsers } from '@/api/system/user';

  // 表格实例
  const tableRef = ref(null);

  // 表格列配置
  const columns = ref([
    {
      key: 'index',
      width: 48,
      align: 'center',
      fixed: 'left',
      hideInSetting: true,
      customRender: ({ index }) => index + (tableRef.value?.tableIndex ?? 0)
    },
    {
      title: '用户账号',
      dataIndex: 'username',
      sorter: {
        multiple: 1
      },
      ellipsis: true
    },
    {
      title: '用户名',
      dataIndex: 'nickname',
      sorter: {
        multiple: 1
      },
      ellipsis: true
    },
    {
      title: '性别',
      dataIndex: 'sexName',
      width: 140,
      align: 'center',
      sorter: {
        multiple: 2
      }
    },
    {
      title: '手机号',
      dataIndex: 'phone',
      sorter: {
        multiple: 1
      },
      ellipsis: true
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      sorter: {
        multiple: 1
      },
      ellipsis: true,
      customRender: ({ text }) => toDateString(text),
      width: 180
    }
  ]);

  // 表格数据源
  const datasource = ({ page, limit, orders, filters }) => {
    return pageUsers({ ...orders, ...filters, page, limit });
  };
</script>
