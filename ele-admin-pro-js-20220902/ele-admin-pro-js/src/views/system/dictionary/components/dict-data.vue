<template>
  <!-- 表格 -->
  <ele-pro-table
    ref="tableRef"
    row-key="dictDataId"
    :columns="columns"
    :datasource="datasource"
    tool-class="ele-toolbar-form"
    v-model:selection="selection"
    :row-selection="{ columnWidth: 48 }"
    :scroll="{ x: 800 }"
    height="calc(100vh - 290px)"
    tools-theme="default"
    bordered
    cache-key="proSystemDictDataTable"
    class="sys-dict-data-table"
  >
    <template #toolbar>
      <dict-data-search
        @search="reload"
        @add="openEdit()"
        @remove="removeBatch"
      />
    </template>
    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'action'">
        <a-space>
          <a @click="openEdit(record)">修改</a>
          <a-divider type="vertical" />
          <a-popconfirm
            placement="topRight"
            title="确定要删除此字典项吗？"
            @confirm="remove(record)"
          >
            <a class="ele-text-danger">删除</a>
          </a-popconfirm>
        </a-space>
      </template>
    </template>
  </ele-pro-table>
  <!-- 编辑弹窗 -->
  <dict-data-edit
    v-model:visible="showEdit"
    :data="current"
    :dict-id="dictId"
    @done="reload"
  />
</template>

<script setup>
  import { createVNode, ref, watch } from 'vue';
  import { message, Modal } from 'ant-design-vue/es';
  import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
  import { messageLoading, toDateString } from 'ele-admin-pro/es';
  import DictDataSearch from './dict-data-search.vue';
  import DictDataEdit from './dict-data-edit.vue';
  import {
    pageDictionaryData,
    removeDictionaryData,
    removeDictionaryDataBatch
  } from '@/api/system/dictionary-data';

  const props = defineProps({
    // 字典id
    dictId: Number
  });

  // 表格实例
  const tableRef = ref(null);

  // 表格列配置
  const columns = ref([
    {
      title: '字典项名称',
      dataIndex: 'dictDataName',
      ellipsis: true,
      sorter: true,
      showSorterTooltip: false
    },
    {
      title: '字典项值',
      dataIndex: 'dictDataCode',
      ellipsis: true,
      sorter: true,
      showSorterTooltip: false
    },
    {
      title: '排序号',
      dataIndex: 'sortNumber',
      sorter: true,
      showSorterTooltip: false,
      width: 120,
      align: 'center'
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true,
      customRender: ({ text }) => toDateString(text)
    },
    {
      title: '操作',
      key: 'action',
      width: 130,
      align: 'center'
    }
  ]);

  // 表格选中数据
  const selection = ref([]);

  // 当前编辑数据
  const current = ref(null);

  // 是否显示编辑弹窗
  const showEdit = ref(false);

  // 表格数据源
  const datasource = ({ page, limit, where, orders }) => {
    return pageDictionaryData({
      ...where,
      ...orders,
      page,
      limit,
      dictId: props.dictId
    });
  };

  /* 刷新表格 */
  const reload = (where) => {
    tableRef?.value?.reload({ page: 1, where });
  };

  /* 打开编辑弹窗 */
  const openEdit = (row) => {
    current.value = row ?? null;
    showEdit.value = true;
  };

  /* 删除单个 */
  const remove = (row) => {
    const hide = messageLoading('请求中..', 0);
    removeDictionaryData(row.dictDataId)
      .then((msg) => {
        hide();
        message.success(msg);
        reload();
      })
      .catch((e) => {
        hide();
        message.error(e.message);
      });
  };

  /* 批量删除 */
  const removeBatch = () => {
    if (!selection.value.length) {
      message.error('请至少选择一条数据');
      return;
    }
    Modal.confirm({
      title: '提示',
      content: '确定要删除选中的字典项吗?',
      icon: createVNode(ExclamationCircleOutlined),
      maskClosable: true,
      onOk: () => {
        const hide = messageLoading('请求中..', 0);
        removeDictionaryDataBatch(selection.value.map((d) => d.dictDataId))
          .then((msg) => {
            hide();
            message.success(msg);
            reload();
          })
          .catch((e) => {
            hide();
            message.error(e.message);
          });
      }
    });
  };

  // 监听字典id变化
  watch(
    () => props.dictId,
    () => {
      reload();
    }
  );
</script>

<style lang="less" scoped>
  .sys-dict-data-table :deep(.ant-table-body) {
    overflow: auto !important;
    overflow: overlay !important;
  }

  .sys-dict-data-table :deep(.ant-table-pagination.ant-pagination) {
    padding: 0 4px;
    margin-bottom: 0;
  }
</style>
