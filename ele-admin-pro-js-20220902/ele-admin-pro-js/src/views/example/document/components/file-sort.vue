<template>
  <ele-modal
    :width="1200"
    :visible="visible"
    title="卷内文件调整"
    :body-style="{ padding: '16px 16px 0 16px' }"
    @update:visible="updateVisible"
    @cancel="close"
    @ok="save"
  >
    <a-row :gutter="16">
      <a-col :lg="8" :md="24" :sm="24" :xs="24">
        <!-- 表格 -->
        <ele-pro-table
          bordered
          size="small"
          :toolkit="[]"
          height="360px"
          :current="current"
          :need-page="false"
          row-key="piece_no"
          sub-title="案卷列表"
          :columns="columns1"
          tools-theme="default"
          :datasource="documents"
          :scroll="{ x: 280 }"
          selection-type="radio"
          :row-selection="{ columnWidth: 38 }"
          :tool-style="{ padding: '7px 14px' }"
          class="demo-file-sort-table"
          style="margin-bottom: 16px"
          @update:current="updateCurrent"
        />
      </a-col>
      <a-col :lg="8" :md="24" :sm="24" :xs="24">
        <!-- 表格 -->
        <ele-pro-table
          bordered
          size="small"
          :toolkit="[]"
          height="360px"
          :need-page="false"
          :loading="loading"
          sub-title="卷内列表"
          :datasource="data1"
          :columns="columns2"
          row-key="archive_no"
          tools-theme="default"
          :scroll="{ x: 280 }"
          v-model:selection="selection1"
          :row-selection="{ columnWidth: 38 }"
          class="demo-file-sort-table"
          style="margin-bottom: 16px"
        >
          <template #toolkit>
            <a-space>
              <a-button
                ghost
                type="primary"
                class="ele-btn-icon"
                @click="moveUp"
              >
                <span><arrow-up-outlined />上移</span>
              </a-button>
              <a-button
                ghost
                type="primary"
                class="ele-btn-icon"
                @click="moveDown"
              >
                <span><arrow-down-outlined />下移</span>
              </a-button>
              <a-button
                ghost
                type="primary"
                class="ele-btn-icon"
                @click="moveOut"
              >
                <span>调出<arrow-right-outlined /></span>
              </a-button>
            </a-space>
          </template>
        </ele-pro-table>
      </a-col>
      <a-col :lg="8" :md="24" :sm="24" :xs="24">
        <!-- 表格 -->
        <ele-pro-table
          bordered
          size="small"
          :toolkit="[]"
          height="360px"
          :need-page="false"
          :loading="loading"
          :datasource="data2"
          :columns="columns2"
          sub-title="未归档列表"
          row-key="archive_no"
          tools-theme="default"
          :scroll="{ x: 280 }"
          v-model:selection="selection2"
          :row-selection="{ columnWidth: 38 }"
          class="demo-file-sort-table"
          style="margin-bottom: 16px"
        >
          <template #toolkit>
            <a-button ghost type="primary" class="ele-btn-icon" @click="moveIn">
              <span><arrow-left-outlined /> 调入</span>
            </a-button>
          </template>
        </ele-pro-table>
      </a-col>
    </a-row>
  </ele-modal>
</template>

<script setup>
  import { ref, unref, computed, watch } from 'vue';
  import { message } from 'ant-design-vue/es';
  import {
    ArrowUpOutlined,
    ArrowDownOutlined,
    ArrowLeftOutlined,
    ArrowRightOutlined
  } from '@ant-design/icons-vue';
  import { getArchiveList } from '@/api/example/document';

  const props = defineProps({
    // 弹窗是否打开
    visible: Boolean,
    // 案卷列表
    documents: Array
  });

  const emit = defineEmits(['update:visible']);

  // 案卷表格列配置
  const columns1 = ref([
    {
      title: '案卷题名',
      dataIndex: 'title',
      ellipsis: true
    },
    {
      title: '案卷档号',
      dataIndex: 'piece_no',
      ellipsis: true
    }
  ]);

  // 卷内表格列配置
  const columns2 = ref([
    {
      title: '文件题名',
      dataIndex: 'title',
      ellipsis: true
    },
    {
      title: '文件档号',
      dataIndex: 'archive_no',
      ellipsis: true
    }
  ]);

  // 所选案卷下的全部文件列表
  const data = ref([]);

  // 选中案卷
  const current = ref(null);

  // 加载loading
  const loading = ref(true);

  // 卷内列表选中数据
  const selection1 = ref([]);

  // 未归档列表选中数据
  const selection2 = ref([]);

  // 选中案卷的卷内文件
  const data1 = computed(() =>
    unref(current)
      ? data.value.filter((d) => d.piece_no === unref(current)?.piece_no)
      : []
  );

  // 未归档的卷内文件
  const data2 = computed(() => data.value.filter((d) => !d.piece_no));

  /* 上移 */
  const moveUp = () => {
    if (!selection1.value.length) {
      message.error('请选择一条数据');
      return;
    }
    if (selection1.value.length > 1) {
      message.error('只能选择一条数据');
      return;
    }
    if (data1.value.indexOf(selection1.value[0]) === 0) {
      return;
    }
    const index = data.value.indexOf(selection1.value[0]);
    const old = data.value[index - 1];
    data.value[index - 1] = selection1.value[0];
    data.value[index] = old;
    selection1.value = [data.value[index - 1]];
  };

  /* 下移 */
  const moveDown = () => {
    if (!selection1.value.length) {
      message.error('请选择一条数据');
      return;
    }
    if (selection1.value.length > 1) {
      message.error('只能选择一条数据');
      return;
    }
    if (data1.value.indexOf(selection1.value[0]) === data1.value.length - 1) {
      return;
    }
    const index = data.value.indexOf(selection1.value[0]);
    const old = data.value[index + 1];
    data.value[index + 1] = selection1.value[0];
    data.value[index] = old;
    selection1.value = [data.value[index + 1]];
  };

  /* 调出 */
  const moveOut = () => {
    if (!selection1.value.length) {
      message.error('请至少选择一条数据');
      return;
    }
    selection1.value.forEach((d) => {
      d.piece_no = '';
    });
    selection1.value = [];
  };

  /* 调入 */
  const moveIn = () => {
    if (!unref(current)) {
      return;
    }
    if (!selection2.value.length) {
      message.error('请至少选择一条数据');
      return;
    }
    selection2.value.forEach((d) => {
      d.piece_no = unref(current)?.piece_no;
    });
    selection2.value = [];
  };

  /* 保存 */
  const save = () => {
    const result = data.value.map((d) => {
      return {
        archive_no: d.archive_no,
        piece_no: d.piece_no
      };
    });
    console.log(result);
    message.success('调整成功');
    close();
  };

  /* 关闭弹窗 */
  const close = () => {
    data.value = [];
    selection1.value = [];
    selection2.value = [];
    updateVisible(false);
  };

  /* 更新visible */
  const updateVisible = (value) => {
    emit('update:visible', value);
  };

  /* 更新current */
  const updateCurrent = (value) => {
    current.value = value;
    selection1.value = [];
  };

  /* 查询所选案卷的卷内文件 */
  const query = () => {
    loading.value = true;
    getArchiveList({
      piece_no_in: props.documents.map((d) => d.piece_no)
    })
      .then((list) => {
        loading.value = false;
        data.value = list;
        current.value = props.documents[0];
      })
      .catch((e) => {
        loading.value = false;
        message.error(e.message);
      });
  };

  watch(
    () => props.documents,
    (documents) => {
      if (documents.length) {
        query();
      }
    }
  );
</script>

<style lang="less" scoped>
  :deep(.demo-file-sort-table .ant-table-body) {
    overflow: auto !important;
  }
</style>
