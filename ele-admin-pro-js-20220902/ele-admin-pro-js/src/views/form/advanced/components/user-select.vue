<template>
  <a-card :bordered="false" title="选择成员">
    <a-table
      size="middle"
      row-key="key"
      :pagination="false"
      :data-source="users"
      :columns="columns"
      :scroll="{ x: 900 }"
    >
      <template #bodyCell="{ column, record, index }">
        <template v-if="column.key === 'name'">
          <a-input
            v-if="record.isEdit"
            v-model:value="record.name"
            placeholder="请输入用户名"
          />
          <div v-else>{{ record.name }}</div>
        </template>
        <template v-else-if="column.key === 'number'">
          <a-input
            v-if="record.isEdit"
            v-model:value="record.number"
            placeholder="请输入工号"
          />
          <div v-else>{{ record.number }}</div>
        </template>
        <template v-else-if="column.key === 'department'">
          <a-select
            v-if="record.isEdit"
            v-model:value="record.department"
            placeholder="请选择部门"
            class="ele-fluid"
          >
            <a-select-option value="研发部">研发部</a-select-option>
            <a-select-option value="测试部">测试部</a-select-option>
            <a-select-option value="产品部">产品部</a-select-option>
          </a-select>
          <div v-else>{{ record.department }}</div>
        </template>
        <template v-else-if="column.key === 'action'">
          <a-space>
            <a v-if="record.isEdit" @click="done(record, index)">完成</a>
            <a v-else @click="edit(record, index)">修改</a>
            <a-divider type="vertical" />
            <a-popconfirm
              title="确定要删除此用户吗？"
              @confirm="remove(record, index)"
            >
              <a class="ele-text-danger">删除</a>
            </a-popconfirm>
          </a-space>
        </template>
      </template>
    </a-table>
    <a-button block type="dashed" style="margin-top: 16px" @click="add">
      <template #icon>
        <plus-outlined />
      </template>
      <span>新增成员</span>
    </a-button>
  </a-card>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { PlusOutlined } from '@ant-design/icons-vue';
  import { uuid } from 'ele-admin-pro/es';
  import { queryList } from '@/api/form/advanced';

  // 已添加成员
  const users = ref([]);

  // 列
  const columns = reactive([
    {
      key: 'index',
      align: 'center',
      width: 48,
      customRender: ({ index }) => index + 1,
      fixed: 'left'
    },
    {
      title: '用户名',
      key: 'name',
      width: 200
    },
    {
      title: '工号',
      key: 'number',
      width: 200
    },
    {
      title: '所属部门',
      key: 'department',
      width: 200
    },
    {
      title: '操作',
      key: 'action',
      align: 'center',
      width: 160
    }
  ]);

  /* 添加 */
  const add = () => {
    users.value.push({
      key: uuid(8),
      isEdit: true,
      number: '00001',
      name: 'John Brown',
      department: '研发部'
    });
  };

  /* 编辑 */
  const edit = (_row, index) => {
    users.value[index].isEdit = true;
  };

  /* 完成编辑 */
  const done = (_row, index) => {
    users.value[index].isEdit = false;
  };

  /* 删除 */
  const remove = (_row, index) => {
    users.value.splice(index, 1);
  };

  /* 查询已添加 */
  queryList()
    .then((data) => {
      users.value = data.map((d) => {
        return {
          ...d,
          isEdit: false
        };
      });
    })
    .catch((e) => {
      message.error(e.message);
    });
</script>
