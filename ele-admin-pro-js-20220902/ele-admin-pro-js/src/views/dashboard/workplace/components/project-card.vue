<!-- 项目进度 -->
<template>
  <a-card
    :title="title"
    :bordered="false"
    :body-style="{ padding: '14px', height: '358px' }"
  >
    <template #extra>
      <more-icon @remove="onRemove" @edit="onEdit" />
    </template>
    <a-table
      row-key="id"
      size="middle"
      :pagination="false"
      :data-source="projectList"
      :columns="projectColumns"
      :scroll="{ x: 600 }"
    >
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'projectName'">
          <a>{{ record.projectName }}</a>
        </template>
        <template v-else-if="column.key === 'status'">
          <span v-if="record.status === 0" class="ele-text-success">
            进行中
          </span>
          <span v-else-if="record.status === 1" class="ele-text-danger">
            已延期
          </span>
          <span v-else-if="record.status === 2" class="ele-text-warning">
            未开始
          </span>
          <span
            v-else-if="record.status === 3"
            class="ele-text-info ele-text-delete"
          >
            已结束
          </span>
        </template>
        <template v-else-if="column.key === 'progress'">
          <a-progress :percent="record.progress" size="small" />
        </template>
      </template>
    </a-table>
  </a-card>
</template>

<script setup>
  import { ref } from 'vue';
  import MoreIcon from './more-icon.vue';

  defineProps({
    title: String
  });

  const emit = defineEmits(['remove', 'edit']);

  const projectColumns = ref([
    {
      key: 'index',
      align: 'center',
      width: 38,
      customRender: ({ index }) => index + 1,
      fixed: 'left'
    },
    {
      title: '项目名称',
      key: 'projectName',
      ellipsis: true,
      minWidth: 120
    },
    {
      title: '开始时间',
      dataIndex: 'startDate',
      align: 'center',
      minWidth: 100,
      ellipsis: true
    },
    {
      title: '结束时间',
      dataIndex: 'endDate',
      align: 'center',
      minWidth: 100,
      ellipsis: true
    },
    {
      title: '状态',
      key: 'status',
      align: 'center',
      width: 90
    },
    {
      title: '进度',
      key: 'progress',
      align: 'center',
      width: 180
    }
  ]);

  // 项目进度数据
  const projectList = ref([]);

  /* 查询项目进度 */
  const queryProjectList = () => {
    projectList.value = [
      {
        id: 1,
        projectName: '项目0000001',
        status: 0,
        startDate: '2020-03-01',
        endDate: '2020-06-01',
        progress: 30
      },
      {
        id: 2,
        projectName: '项目0000002',
        status: 0,
        startDate: '2020-03-01',
        endDate: '2020-08-01',
        progress: 10
      },
      {
        id: 3,
        projectName: '项目0000003',
        status: 1,
        startDate: '2020-01-01',
        endDate: '2020-05-01',
        progress: 60
      },
      {
        id: 4,
        projectName: '项目0000004',
        status: 1,
        startDate: '2020-06-01',
        endDate: '2020-10-01',
        progress: 0
      },
      {
        id: 5,
        projectName: '项目0000005',
        status: 2,
        startDate: '2020-01-01',
        endDate: '2020-03-01',
        progress: 100
      },
      {
        id: 6,
        projectName: '项目0000006',
        status: 3,
        startDate: '2020-01-01',
        endDate: '2020-03-01',
        progress: 100
      }
    ];
  };

  const onRemove = () => {
    emit('remove');
  };

  const onEdit = () => {
    emit('edit');
  };

  queryProjectList();
</script>
