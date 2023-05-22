<template>
  <a-card :bordered="false" :body-style="{ padding: '10px 20px' }">
    <ele-pro-table
      ref="tableRef"
      size="small"
      title="可控的排序和筛选"
      row-key="userId"
      :columns="columns"
      :datasource="datasource"
      :scroll="{ x: 800 }"
      @change="onChange"
    >
      <template #toolkit>
        <a-space size="small" style="flex-wrap: wrap">
          <a-button type="primary" class="ele-btn-icon" @click="setSorter">
            设置用户名排序
          </a-button>
          <a-button type="primary" class="ele-btn-icon" @click="setFilter">
            设置性别筛选
          </a-button>
          <a-button type="primary" class="ele-btn-icon" @click="resetAll">
            重置排序和筛选
          </a-button>
          <a-divider type="vertical" />
        </a-space>
      </template>
    </ele-pro-table>
  </a-card>
</template>

<script setup>
  import { ref, computed, unref } from 'vue';
  import { toDateString } from 'ele-admin-pro/es';
  import { pageUsers } from '@/api/system/user';

  // 表格实例
  const tableRef = ref(null);

  // 表格筛选值
  const filteredInfo = ref(null);

  // 表格排序值
  const sortedInfo = ref(null);

  // 表格列配置
  const columns = computed(() => {
    const sorted =
      (Array.isArray(sortedInfo.value)
        ? sortedInfo.value[0]
        : sortedInfo.value) ?? {};
    const filtered = unref(filteredInfo) ?? {};
    const cols = [
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
        sorter: true,
        ellipsis: true,
        sortOrder: sorted.field === 'username' ? sorted.order : null
      },
      {
        title: '用户名',
        dataIndex: 'nickname',
        sorter: true,
        ellipsis: true,
        sortOrder: sorted.field === 'nickname' ? sorted.order : null
      },
      {
        title: '性别',
        dataIndex: 'sexName',
        width: 140,
        align: 'center',
        sorter: true,
        filters: [
          {
            text: '男',
            value: '男'
          },
          {
            text: '女',
            value: '女'
          }
        ],
        filterMultiple: false,
        sortOrder: sorted.field === 'sexName' ? sorted.order : null,
        filteredValue: filtered.sexName || null
      },
      {
        title: '手机号',
        dataIndex: 'phone',
        sorter: true,
        ellipsis: true,
        sortOrder: sorted.field === 'phone' ? sorted.order : null
      },
      {
        title: '创建时间',
        dataIndex: 'createTime',
        sorter: true,
        ellipsis: true,
        customRender: ({ text }) => toDateString(text),
        width: 180,
        sortOrder: sorted.field === 'createTime' ? sorted.order : null
      }
    ];
    return cols;
  });

  // 表格数据源
  const datasource = ({ page, limit, orders, filters }) => {
    return pageUsers({ ...orders, ...filters, page, limit });
  };

  // 表格分页、排序、筛选改变事件
  const onChange = (_pagination, filters, sorter) => {
    filteredInfo.value = filters;
    sortedInfo.value = sorter;
  };

  // 重置排序和筛选
  const resetAll = () => {
    filteredInfo.value = {};
    sortedInfo.value = {};
    tableRef?.value?.reload({
      page: 1,
      sorter: sortedInfo.value,
      filters: filteredInfo.value
    });
  };

  // 设置排序
  const setSorter = () => {
    sortedInfo.value = {
      order: 'descend',
      field: 'nickname'
    };
    tableRef?.value?.reload({
      page: 1,
      sorter: sortedInfo.value
    });
  };

  // 设置筛选
  const setFilter = () => {
    filteredInfo.value = {
      sexName: ['女']
    };
    tableRef?.value?.reload({
      page: 1,
      filters: filteredInfo.value
    });
  };
</script>
