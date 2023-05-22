<template>
  <div>
    <ele-pro-table
      ref="tableRef"
      row-key="id"
      :columns="columns"
      :datasource="datasource"
      v-model:selection="selection"
      :scroll="{ x: 600 }"
    >
      <template #toolbar>
        <a-space>
          <a-button type="primary" class="ele-btn-icon" @click="okBatch">
            批量完成
          </a-button>
          <a-button
            danger
            type="primary"
            class="ele-btn-icon"
            @click="removeBatch"
          >
            删除待办
          </a-button>
        </a-space>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'status'">
          <span :class="['ele-text-warning', 'ele-text-info'][record.status]">
            {{ ['未完成', '已完成'][record.status] }}
          </span>
        </template>
        <template v-else-if="column.key === 'action'">
          <a-space>
            <a @click="ok(record)">完成</a>
            <a-divider type="vertical" />
            <a-popconfirm
              placement="topRight"
              title="确定要删除此消息吗？"
              @confirm="remove(record)"
            >
              <a class="ele-text-danger">删除</a>
            </a-popconfirm>
          </a-space>
        </template>
      </template>
    </ele-pro-table>
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { pageTodos } from '@/api/user/message';

  const emit = defineEmits(['update-data']);

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
      title: '待办内容',
      dataIndex: 'title',
      ellipsis: true
    },
    {
      title: '结束时间',
      dataIndex: 'time',
      ellipsis: true,
      width: 140,
      align: 'center'
    },
    {
      title: '状态',
      key: 'status',
      width: 90,
      align: 'center'
    },
    {
      title: '操作',
      key: 'action',
      width: 120,
      align: 'center',
      hideInSetting: true
    }
  ]);

  // 列表选中数据
  const selection = ref([]);

  // 表格数据源
  const datasource = ({ page, limit, where, orders }) => {
    return pageTodos({ ...where, ...orders, page, limit });
  };

  /* 完成 */
  const ok = (row) => {
    console.log(row);
    message.info('点击了完成');
  };

  /* 删除单个 */
  const remove = (row) => {
    console.log(row);
    message.info('点击了删除');
    updateUnReadNum();
  };

  /* 批量删除 */
  const removeBatch = () => {
    if (!selection.value.length) {
      message.error('请至少选择一条数据');
      return;
    }
    message.info('点击了删除');
    updateUnReadNum();
  };

  /* 批量完成 */
  const okBatch = () => {
    if (!selection.value.length) {
      message.error('请至少选择一条数据');
      return;
    }
    selection.value.forEach((d) => {
      d.status = 1;
    });
    updateUnReadNum();
  };

  /* 触发更新未读数量事件 */
  const updateUnReadNum = () => {
    emit('update-data');
  };
</script>
