<template>
  <a-card title="可搜索" :bordered="false">
    <div style="max-width: 260px">
      <ele-table-select
        ref="selectRef"
        :multiple="true"
        :allow-clear="true"
        placeholder="请选择"
        value-key="userId"
        label-key="nickname"
        v-model:value="selectedValue"
        :table-config="tableConfig"
        :overlay-style="{ width: '520px', maxWidth: '80%' }"
        :init-value="initValue"
      >
        <!-- 角色列 -->
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'roles'">
            <a-tag v-for="item in record.roles" :key="item.roleId" color="blue">
              {{ item.roleName }}
            </a-tag>
          </template>
        </template>
        <!-- 表头工具栏 -->
        <template #toolbar>
          <demo-advanced-search @search="search" />
        </template>
      </ele-table-select>
    </div>
    <div style="margin-top: 12px">
      <a-button type="primary" @click="setInitValue">回显数据</a-button>
    </div>
  </a-card>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import DemoAdvancedSearch from './demo-advanced-search.vue';
  import { pageUsers } from '@/api/system/user';

  const selectedValue = ref([]);

  // 选择框实例
  const selectRef = ref(null);

  // 表格配置
  const tableConfig = reactive({
    datasource: ({ page, limit, where, orders }) => {
      return pageUsers({ ...where, ...orders, page, limit });
    },
    columns: [
      {
        title: '用户账号',
        dataIndex: 'username',
        sorter: true,
        showSorterTooltip: false
      },
      {
        title: '用户名',
        key: 'nickname',
        dataIndex: 'nickname',
        sorter: true,
        showSorterTooltip: false
      },
      {
        title: '性别',
        dataIndex: 'sexName',
        width: 80,
        align: 'center',
        sorter: true,
        showSorterTooltip: false
      },
      {
        title: '角色',
        key: 'roles'
      }
    ],
    toolkit: ['reload', 'columns'],
    pageSize: 5,
    pageSizeOptions: ['5', '10', '15', '20'],
    size: 'small',
    rowSelection: {
      columnWidth: 38,
      preserveSelectedRowKeys: true,
      fixed: 'left'
    },
    toolsTheme: 'default',
    bordered: true,
    toolStyle: {
      padding: '0 8px'
    },
    scroll: { x: 480 }
  });

  // 回显值
  const initValue = ref();

  /* 回显数据 */
  const setInitValue = () => {
    //selectedValue.value = [14, 18, 19];
    initValue.value = [
      {
        userId: 14,
        nickname: '管理员'
      },
      {
        userId: 18,
        nickname: '用户四'
      },
      {
        userId: 19,
        nickname: '用户五'
      }
    ];
  };

  // 搜索
  const search = (where) => {
    selectRef.value?.reload({
      where,
      page: 1
    });
  };
</script>
