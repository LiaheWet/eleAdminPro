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
          <a-button type="primary" class="ele-btn-icon" @click="confirmBatch">
            批量确认
          </a-button>
          <a-button
            danger
            type="primary"
            class="ele-btn-icon"
            @click="removeBatch"
          >
            删除通知
          </a-button>
        </a-space>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'status'">
          <span :class="['ele-text-warning', 'ele-text-info'][record.status]">
            {{ ['未确认', '已确认'][record.status] }}
          </span>
        </template>
        <template v-else-if="column.key === 'action'">
          <a-space>
            <a @click="confirm(record)">确认</a>
            <a-divider type="vertical" />
            <a-popconfirm
              placement="topRight"
              title="确定要删除此通知吗"
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
  import { pageNotices } from '@/api/user/message';

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
      title: '通知标题',
      dataIndex: 'title',
      ellipsis: true
    },
    {
      title: '通知时间',
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
    return pageNotices({ ...where, ...orders, page, limit });
  };

  /* 确认 */
  const confirm = (row) => {
    console.log(row);
    message.info('点击了确认');
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

  /* 批量确认 */
  const confirmBatch = () => {
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
