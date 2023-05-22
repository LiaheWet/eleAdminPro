<template>
  <div class="ele-body">
    <a-card :bordered="false" :body-style="{ padding: '10px 20px' }">
      <!-- 表格 -->
      <ele-pro-table
        ref="tableRef"
        row-key="piece_no"
        :columns="columns"
        :datasource="datasource"
        v-model:selection="selection"
        :scroll="{ x: 900 }"
        :height="fixedHeight ? 'calc(100vh - 420px)' : void 0"
        @done="onTableDone"
      >
        <template #toolbar>
          <a-button type="primary" class="ele-btn-icon" @click="openFileSort">
            卷内文件调整
          </a-button>
          <span>&emsp;高度铺满<s></s><s></s></span>
          <a-switch v-model:checked="fixedHeight" size="small" />
        </template>
        <!-- 合计行 -->
        <template #summary>
          <a-table-summary fixed>
            <a-table-summary-row>
              <a-table-summary-cell />
              <a-table-summary-cell />
              <a-table-summary-cell>合计</a-table-summary-cell>
              <a-table-summary-cell />
              <a-table-summary-cell />
              <a-table-summary-cell />
              <a-table-summary-cell />
              <a-table-summary-cell />
              <a-table-summary-cell />
              <a-table-summary-cell>{{ amountSummary }}</a-table-summary-cell>
            </a-table-summary-row>
          </a-table-summary>
        </template>
      </ele-pro-table>
    </a-card>
    <!-- 卷内文件调整弹窗 -->
    <file-sort v-model:visible="showFileSort" :documents="fileSortChoose" />
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import { message } from 'ant-design-vue/es';
  import FileSort from './components/file-sort.vue';
  import { getPieceList } from '@/api/example/document';

  // 表格实例
  const tableRef = ref(null);

  // 案卷数据
  const data = ref([]);

  // 列表数据源
  const datasource = ({ page, limit }) => {
    return getPieceList({ page, limit });
  };

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
      title: '案卷档号',
      dataIndex: 'piece_no',
      ellipsis: true
    },
    {
      title: '案卷题名',
      dataIndex: 'title',
      ellipsis: true
    },
    {
      title: '年度',
      dataIndex: 'year',
      width: 100,
      ellipsis: true
    },
    {
      title: '保管期限',
      dataIndex: 'retention',
      width: 120,
      ellipsis: true
    },
    {
      title: '密级',
      dataIndex: 'secret',
      width: 100,
      ellipsis: true
    },
    {
      title: '档案类别',
      dataIndex: 'type',
      ellipsis: true
    },
    {
      title: '载体规格',
      dataIndex: 'carrier',
      width: 120,
      ellipsis: true
    },
    {
      title: '件数',
      dataIndex: 'amount',
      ellipsis: true
    }
  ]);

  // 表格选中数据
  const selection = ref([]);

  // 是否显示卷内文件调整弹窗
  const showFileSort = ref(false);

  // 选中的案卷
  const fileSortChoose = ref([]);

  // 件数合计
  const amountSummary = ref(0);

  // 表格固定高度
  const fixedHeight = ref(false);

  /* 表格数据加载完成事件 */
  const onTableDone = (res) => {
    data.value = res.data;
    amountSummary.value = res.data
      .map((item) => Number(item.amount))
      .reduce((prev, curr) => {
        const value = Number(curr);
        if (!isNaN(value)) {
          return prev + curr;
        } else {
          return prev;
        }
      }, 0);
  };

  /* 打开卷内文件调整弹窗 */
  const openFileSort = () => {
    if (selection.value.length < 2) {
      message.error('请至少选择两条数据');
      return;
    }
    // 实际项目用这一行
    /* fileSortChoose.value = selection.value.map((d) => {
      return { ...d };
    }); */
    // 演示强制选前三个演示
    fileSortChoose.value = data.value.slice(0, 3);
    showFileSort.value = true;
  };
</script>

<script>
  export default {
    name: 'ExampleDocument'
  };
</script>
