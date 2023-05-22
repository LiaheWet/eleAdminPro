<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 搜索表单 -->
      <user-search :where="defaultWhere" @search="reload" />
      <!-- 表格 -->
      <ele-pro-table
        ref="tableRef"
        row-key="userId"
        :columns="columns"
        :datasource="datasource"
        v-model:selection="selection"
        :scroll="{ x: 1000 }"
        :where="defaultWhere"
        cache-key="proSystemUserTable"
      >
        <template #toolbar>
          <a-space>
            <a-button type="primary" class="ele-btn-icon" @click="openEdit()">
              <template #icon>
                <plus-outlined />
              </template>
              <span>新建</span>
            </a-button>
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
            <a-button type="dashed" class="ele-btn-icon" @click="openImport">
              <template #icon>
                <upload-outlined />
              </template>
              <span>导入</span>
            </a-button>
          </a-space>
        </template>
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'nickname'">
            <router-link :to="'/system/user/details?id=' + record.userId">
              {{ record.nickname }}
            </router-link>
          </template>
          <template v-else-if="column.key === 'roles'">
            <a-tag v-for="item in record.roles" :key="item.roleId" color="blue">
              {{ item.roleName }}
            </a-tag>
          </template>
          <template v-else-if="column.key === 'status'">
            <a-switch
              :checked="record.status === 0"
              @change="(checked) => editStatus(checked, record)"
            />
          </template>
          <template v-else-if="column.key === 'action'">
            <a-space>
              <a @click="openEdit(record)">修改</a>
              <a-divider type="vertical" />
              <a @click="resetPsw(record)">重置密码</a>
              <a-divider type="vertical" />
              <a-popconfirm
                placement="topRight"
                title="确定要删除此用户吗？"
                @confirm="remove(record)"
              >
                <a class="ele-text-danger">删除</a>
              </a-popconfirm>
            </a-space>
          </template>
        </template>
      </ele-pro-table>
    </a-card>
    <!-- 编辑弹窗 -->
    <user-edit v-model:visible="showEdit" :data="current" @done="reload" />
    <!-- 导入弹窗 -->
    <user-import v-model:visible="showImport" @done="reload" />
  </div>
</template>

<script setup>
  import { createVNode, ref, reactive } from 'vue';
  import { message, Modal } from 'ant-design-vue/es';
  import {
    PlusOutlined,
    DeleteOutlined,
    UploadOutlined,
    ExclamationCircleOutlined
  } from '@ant-design/icons-vue';
  import { toDateString, messageLoading } from 'ele-admin-pro/es';
  import UserSearch from './components/user-search.vue';
  import UserEdit from './components/user-edit.vue';
  import UserImport from './components/user-import.vue';
  import {
    pageUsers,
    removeUser,
    removeUsers,
    updateUserStatus,
    updateUserPassword
  } from '@/api/system/user';

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
      title: '手机号',
      dataIndex: 'phone',
      sorter: true,
      showSorterTooltip: false
    },
    {
      title: '角色',
      key: 'roles'
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
      title: '状态',
      key: 'status',
      dataIndex: 'status',
      sorter: true,
      showSorterTooltip: false,
      width: 90,
      align: 'center'
    },
    {
      title: '操作',
      key: 'action',
      width: 200,
      align: 'center'
    }
  ]);

  // 表格选中数据
  const selection = ref([]);

  // 当前编辑数据
  const current = ref(null);

  // 是否显示编辑弹窗
  const showEdit = ref(false);

  // 是否显示用户导入弹窗
  const showImport = ref(false);

  // 默认搜索条件
  const defaultWhere = reactive({
    username: '',
    nickname: ''
  });

  // 表格数据源
  const datasource = ({ page, limit, where, orders }) => {
    return pageUsers({ ...where, ...orders, page, limit });
  };

  /* 搜索 */
  const reload = (where) => {
    selection.value = [];
    tableRef?.value?.reload({ page: 1, where });
  };

  /* 打开编辑弹窗 */
  const openEdit = (row) => {
    current.value = row ?? null;
    showEdit.value = true;
  };

  /* 打开编辑弹窗 */
  const openImport = () => {
    showImport.value = true;
  };

  /* 删除单个 */
  const remove = (row) => {
    const hide = messageLoading('请求中..', 0);
    removeUser(row.userId)
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
      content: '确定要删除选中的用户吗?',
      icon: createVNode(ExclamationCircleOutlined),
      maskClosable: true,
      onOk: () => {
        const hide = messageLoading('请求中..', 0);
        removeUsers(selection.value.map((d) => d.userId))
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

  /* 重置用户密码 */
  const resetPsw = (row) => {
    Modal.confirm({
      title: '提示',
      content: '确定要重置此用户的密码为"123456"吗?',
      icon: createVNode(ExclamationCircleOutlined),
      maskClosable: true,
      onOk: () => {
        const hide = messageLoading('请求中..', 0);
        updateUserPassword(row.userId)
          .then((msg) => {
            hide();
            message.success(msg);
          })
          .catch((e) => {
            hide();
            message.error(e.message);
          });
      }
    });
  };

  /* 修改用户状态 */
  const editStatus = (checked, row) => {
    const status = checked ? 0 : 1;
    updateUserStatus(row.userId, status)
      .then((msg) => {
        row.status = status;
        message.success(msg);
      })
      .catch((e) => {
        message.error(e.message);
      });
  };
</script>

<script>
  export default {
    name: 'SystemUser'
  };
</script>
