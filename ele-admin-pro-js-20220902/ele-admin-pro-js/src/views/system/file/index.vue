<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 搜索表单 -->
      <file-search @search="reload" />
      <!-- 表格 -->
      <ele-pro-table
        ref="tableRef"
        row-key="id"
        :columns="columns"
        :datasource="datasource"
        v-model:selection="selection"
        :scroll="{ x: 800 }"
        cache-key="proSystemFileTable"
      >
        <template #toolbar>
          <a-space>
            <a-upload :show-upload-list="false" :customRequest="onUpload">
              <a-button type="primary" class="ele-btn-icon">
                <template #icon>
                  <upload-outlined />
                </template>
                <span>上传</span>
              </a-button>
            </a-upload>
            <a-button
              danger
              type="primary"
              class="ele-btn-icon"
              @click="removeBatch"
            >
              <template #icon>
                <delete-outlined />
              </template>
              <span>删除</span>
            </a-button>
          </a-space>
        </template>
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'path'">
            <a :href="record.url" target="_blank">
              {{ record.path }}
            </a>
          </template>
          <template v-else-if="column.key === 'action'">
            <a-space>
              <a :href="record.downloadUrl" target="_blank">下载</a>
              <a-divider type="vertical" />
              <a-popconfirm
                placement="topRight"
                title="确定要删除此文件吗？"
                @confirm="remove(record)"
              >
                <a class="ele-text-danger">删除</a>
              </a-popconfirm>
            </a-space>
          </template>
        </template>
      </ele-pro-table>
    </a-card>
  </div>
</template>

<script setup>
  import { createVNode, ref } from 'vue';
  import { message, Modal } from 'ant-design-vue/es';
  import {
    UploadOutlined,
    DeleteOutlined,
    ExclamationCircleOutlined
  } from '@ant-design/icons-vue';
  import { messageLoading, toDateString } from 'ele-admin-pro/es';
  import FileSearch from './components/file-search.vue';
  import {
    pageFiles,
    removeFile,
    removeFiles,
    uploadFile
  } from '@/api/system/file';

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
      title: '文件名称',
      dataIndex: 'name',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '文件路径',
      key: 'path',
      dataIndex: 'path',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '文件大小',
      dataIndex: 'length',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true,
      customRender: ({ text }) => {
        if (text < 1024) {
          return text + 'B';
        } else if (text < 1024 * 1024) {
          return (text / 1024).toFixed(1) + 'KB';
        } else if (text < 1024 * 1024 * 1024) {
          return (text / 1024 / 1024).toFixed(1) + 'M';
        } else {
          return (text / 1024 / 1024 / 1024).toFixed(1) + 'G';
        }
      },
      width: 120
    },
    {
      title: '上传人',
      dataIndex: 'createNickname',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true,
      width: 120
    },
    {
      title: '上传时间',
      dataIndex: 'createTime',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true,
      customRender: ({ text }) => toDateString(text),
      width: 160
    },
    {
      title: '操作',
      key: 'action',
      width: 120,
      align: 'center'
    }
  ]);

  // 表格选中数据
  const selection = ref([]);

  // 表格数据源
  const datasource = ({ page, limit, where, orders }) => {
    return pageFiles({ ...where, ...orders, page, limit });
  };

  /* 搜索 */
  const reload = (where) => {
    selection.value = [];
    tableRef?.value?.reload({ page: 1, where });
  };

  /* 删除单个 */
  const remove = (row) => {
    const hide = messageLoading('请求中..', 0);
    removeFile(row.id)
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
      content: '确定要删除选中的文件吗?',
      icon: createVNode(ExclamationCircleOutlined),
      maskClosable: true,
      onOk: () => {
        const hide = messageLoading('请求中..', 0);
        removeFiles(selection.value.map((d) => d.id))
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

  /* 上传 */
  const onUpload = ({ file }) => {
    if (file.size / 1024 / 1024 > 100) {
      message.error('大小不能超过 100MB');
      return false;
    }
    const hide = messageLoading({
      content: '上传中..',
      duration: 0,
      mask: true
    });
    uploadFile(file)
      .then(() => {
        hide();
        message.success('上传成功');
        reload();
      })
      .catch((e) => {
        hide();
        message.error(e.message);
      });
    return false;
  };
</script>

<script>
  export default {
    name: 'SystemFile'
  };
</script>
