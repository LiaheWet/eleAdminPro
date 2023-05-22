<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 搜索表单 -->
      <operation-record-search @search="reload" />
      <!-- 表格 -->
      <ele-pro-table
        ref="tableRef"
        row-key="id"
        :columns="columns"
        :datasource="datasource"
        :scroll="{ x: 1000 }"
        cache-key="proSystemOperationRecordTable"
      >
        <template #toolbar>
          <a-space>
            <a-button type="primary" class="ele-btn-icon" @click="exportData">
              <template #icon>
                <download-outlined />
              </template>
              <span>导出</span>
            </a-button>
          </a-space>
        </template>
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'status'">
            <a-tag v-if="record.status === 0" color="green">正常</a-tag>
            <a-tag v-else-if="record.status === 1" color="red">异常</a-tag>
          </template>
          <template v-else-if="column.key === 'action'">
            <a @click="openDetail(record)">详情</a>
          </template>
        </template>
      </ele-pro-table>
    </a-card>
    <!-- 详情弹窗 -->
    <operation-record-detail v-model:visible="showInfo" :data="current" />
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { DownloadOutlined } from '@ant-design/icons-vue';
  import { utils, writeFile } from 'xlsx';
  import { messageLoading, toDateString } from 'ele-admin-pro/es';
  import OperationRecordSearch from './components/operation-record-search.vue';
  import OperationRecordDetail from './components/operation-record-detail.vue';
  import {
    pageOperationRecords,
    listOperationRecords
  } from '@/api/system/operation-record';

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
      title: '账号',
      dataIndex: 'username',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '用户名',
      dataIndex: 'nickname',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '操作模块',
      dataIndex: 'module',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '操作功能',
      dataIndex: 'description',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '请求地址',
      dataIndex: 'url',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '请求方式',
      dataIndex: 'requestMethod',
      sorter: true,
      showSorterTooltip: false,
      width: 100,
      align: 'center'
    },
    {
      title: '状态',
      key: 'status',
      dataIndex: 'status',
      sorter: true,
      showSorterTooltip: false,
      width: 100,
      filters: [
        {
          text: '正常',
          value: 0
        },
        {
          text: '异常',
          value: 1
        }
      ],
      filterMultiple: false,
      align: 'center'
    },
    {
      title: '耗时',
      dataIndex: 'spendTime',
      sorter: true,
      showSorterTooltip: false,
      width: 100,
      customRender: ({ text }) => text / 1000 + 's'
    },
    {
      title: '操作时间',
      dataIndex: 'createTime',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true,
      customRender: ({ text }) => toDateString(text),
      align: 'center'
    },
    {
      title: '操作',
      key: 'action',
      width: 90,
      align: 'center',
      fixed: 'right'
    }
  ]);

  // 当前选中数据
  const current = ref({
    module: '',
    description: '',
    url: '',
    requestMethod: '',
    method: '',
    params: '',
    result: '',
    error: '',
    spendTime: 0,
    os: '',
    device: '',
    browser: '',
    ip: '',
    status: 0,
    createTime: '',
    nickname: '',
    username: ''
  });

  // 是否显示查看弹窗
  const showInfo = ref(false);

  // 表格数据源
  const datasource = ({ page, limit, where, orders, filters }) => {
    return pageOperationRecords({
      ...where,
      ...orders,
      ...filters,
      page,
      limit
    });
  };

  /* 刷新表格 */
  const reload = (where) => {
    tableRef?.value?.reload({ page: 1, where });
  };

  /* 详情 */
  const openDetail = (row) => {
    current.value = row;
    showInfo.value = true;
  };

  /* 导出数据 */
  const exportData = () => {
    const array = [
      [
        '账号',
        '用户名',
        '操作模块',
        '操作功能',
        '请求地址',
        '请求方式',
        '状态',
        '耗时',
        '操作时间'
      ]
    ];
    // 请求查询全部(不分页)的接口
    const hide = messageLoading('请求中..', 0);
    tableRef.value?.doRequest(({ where, orders, filters }) => {
      listOperationRecords({ ...where, ...orders, ...filters })
        .then((data) => {
          hide();
          data.forEach((d) => {
            array.push([
              d.username,
              d.nickname,
              d.module,
              d.description,
              d.url,
              d.requestMethod,
              ['正常', '异常'][d.status],
              d.spendTime / 1000 + 's',
              toDateString(d.createTime)
            ]);
          });
          writeFile(
            {
              SheetNames: ['Sheet1'],
              Sheets: {
                Sheet1: utils.aoa_to_sheet(array)
              }
            },
            '操作日志.xlsx'
          );
        })
        .catch((e) => {
          hide();
          message.error(e.message);
        });
    });
  };
</script>

<script>
  export default {
    name: 'SystemOperationRecord'
  };
</script>
