<template>
  <a-card title="多选" :bordered="false">
    <div style="max-width: 260px">
      <ele-table-select
        :multiple="true"
        :allow-clear="true"
        placeholder="请选择"
        value-key="userId"
        label-key="nickname"
        v-model:value="selectedValue"
        :table-config="tableConfig"
        :overlay-style="{ width: '520px', maxWidth: '80%' }"
        :disabled="disabled"
        :init-value="initValue"
        :max-tag-text-length="3"
        :max-tag-count="5"
      >
        <!-- 角色列 -->
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'roles'">
            <a-tag v-for="item in record.roles" :key="item.roleId" color="blue">
              {{ item.roleName }}
            </a-tag>
          </template>
        </template>
      </ele-table-select>
    </div>
    <div class="ele-cell" style="margin-top: 15px">
      <div style="line-height: 22px">&nbsp;禁用：</div>
      <div class="ele-cell-content">
        <a-radio-group v-model:value="disabled" name="disabled">
          <a-radio :value="false">否</a-radio>
          <a-radio :value="true">是</a-radio>
        </a-radio-group>
      </div>
    </div>
    <div style="margin-top: 12px">
      <a-button type="primary" @click="setInitValue">回显数据</a-button>
    </div>
  </a-card>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { listUsers } from '@/api/system/user';

  const selectedValue = ref([]);

  // 表格配置
  const tableConfig = reactive({
    datasource: [],
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
    toolbar: false,
    pageSize: 5,
    pageSizeOptions: ['5', '10', '15', '20'],
    size: 'small',
    rowSelection: {
      columnWidth: 38,
      preserveSelectedRowKeys: true,
      fixed: 'left'
    },
    scroll: { x: 480 }
  });

  // 禁用
  const disabled = ref(false);

  listUsers().then((data) => {
    tableConfig.datasource = data;
  });

  /* 回显数据 */
  const setInitValue = () => {
    selectedValue.value = [14, 18, 19];
  };
</script>
