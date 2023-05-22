<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 搜索表单 -->
      <role-search @search="reload" />
      <!-- 表格 -->
      <ele-pro-table
        ref="tableRef"
        row-key="roleId"
        :columns="columns"
        :datasource="datasource"
        v-model:selection="selection"
        :scroll="{ x: 800 }"
        cache-key="proSystemRoleTable"
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
          </a-space>
        </template>
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'action'">
            <a-space>
              <a @click="openEdit(record)">修改</a>
              <a-divider type="vertical" />
              <a @click="openAuth(record)">分配权限</a>
              <a-divider type="vertical" />
              <a-popconfirm
                placement="topRight"
                title="确定要删除此角色吗？"
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
    <role-edit v-model:visible="showEdit" :data="current" @done="reload" />
    <!-- 权限分配弹窗 -->
    <role-auth v-model:visible="showAuth" :data="current" />
  </div>
</template>

<script setup>
  import { createVNode, ref } from 'vue';
  import { message, Modal } from 'ant-design-vue/es';
  import {
    PlusOutlined,
    DeleteOutlined,
    ExclamationCircleOutlined
  } from '@ant-design/icons-vue';
  import { messageLoading, toDateString } from 'ele-admin-pro/es';
  import RoleSearch from './components/role-search.vue';
  import RoleEdit from './components/role-edit.vue';
  import RoleAuth from './components/role-auth.vue';
  import { pageRoles, removeRole, removeRoles } from '@/api/system/role';

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
      title: '角色名称',
      dataIndex: 'roleName',
      sorter: true,
      showSorterTooltip: false
    },
    {
      title: '角色标识',
      dataIndex: 'roleCode',
      sorter: true,
      showSorterTooltip: false
    },
    {
      title: '备注',
      dataIndex: 'comments',
      sorter: true,
      showSorterTooltip: false
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

  // 是否显示权限分配弹窗
  const showAuth = ref(false);

  // 表格数据源
  const datasource = ({ page, limit, where, orders }) => {
    return pageRoles({ ...where, ...orders, page, limit });
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

  /* 打开权限分配弹窗 */
  const openAuth = (row) => {
    current.value = row ?? null;
    showAuth.value = true;
  };

  /* 删除单个 */
  const remove = (row) => {
    const hide = messageLoading('请求中..', 0);
    removeRole(row.roleId)
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
      content: '确定要删除选中的角色吗?',
      icon: createVNode(ExclamationCircleOutlined),
      maskClosable: true,
      onOk: () => {
        const hide = messageLoading('请求中..', 0);
        removeRoles(selection.value.map((d) => d.roleId))
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
</script>

<script>
  export default {
    name: 'SystemRole'
  };
</script>
