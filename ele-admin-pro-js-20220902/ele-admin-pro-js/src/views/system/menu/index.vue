<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 搜索表单 -->
      <menu-search @search="reload" />
      <!-- 表格 -->
      <ele-pro-table
        ref="tableRef"
        row-key="menuId"
        :columns="columns"
        :datasource="datasource"
        :parse-data="parseData"
        :need-page="false"
        :expand-icon-column-index="1"
        :expanded-row-keys="expandedRowKeys"
        :scroll="{ x: 1200 }"
        cache-key="proSystemMenuTable"
        @done="onDone"
        @expand="onExpand"
      >
        <template #toolbar>
          <a-space>
            <a-button type="primary" class="ele-btn-icon" @click="openEdit()">
              <template #icon>
                <plus-outlined />
              </template>
              <span>新建</span>
            </a-button>
            <a-button type="dashed" class="ele-btn-icon" @click="expandAll">
              展开全部
            </a-button>
            <a-button type="dashed" class="ele-btn-icon" @click="foldAll">
              折叠全部
            </a-button>
          </a-space>
        </template>
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'menuType'">
            <a-tag v-if="isExternalLink(record.path)" color="red">外链</a-tag>
            <a-tag v-else-if="isExternalLink(record.component)" color="orange">
              内链
            </a-tag>
            <a-tag v-else-if="isDirectory(record)" color="blue">目录</a-tag>
            <a-tag v-else-if="record.menuType === 0" color="green">菜单</a-tag>
            <a-tag v-else-if="record.menuType === 1">按钮</a-tag>
          </template>
          <template v-else-if="column.key === 'title'">
            <component v-if="record.icon" :is="record.icon" />
            <span style="padding-left: 8px">{{ record.title }}</span>
          </template>
          <template v-else-if="column.key === 'action'">
            <a-space>
              <a @click="openEdit(null, record.menuId)">添加</a>
              <a-divider type="vertical" />
              <a @click="openEdit(record)">修改</a>
              <a-divider type="vertical" />
              <a-popconfirm
                placement="topRight"
                title="确定要删除此菜单吗？"
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
    <menu-edit
      v-model:visible="showEdit"
      :data="current"
      :parent-id="parentId"
      :menu-list="menuData"
      @done="reload"
    />
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { PlusOutlined } from '@ant-design/icons-vue';
  import MenuSearch from './components/menu-search.vue';
  import {
    messageLoading,
    toDateString,
    isExternalLink,
    toTreeData,
    eachTreeData
  } from 'ele-admin-pro/es';
  import MenuEdit from './components/menu-edit.vue';
  import { listMenus, removeMenu } from '@/api/system/menu';

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
      title: '菜单名称',
      key: 'title',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '路由地址',
      dataIndex: 'path',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '组件路径',
      dataIndex: 'component',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '权限标识',
      dataIndex: 'authority',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '排序',
      dataIndex: 'sortNumber',
      sorter: true,
      showSorterTooltip: false,
      width: 90
    },
    {
      title: '可见',
      dataIndex: 'hide',
      sorter: true,
      showSorterTooltip: false,
      customRender: ({ text }) => ['是', '否'][text],
      width: 90
    },
    {
      title: '类型',
      key: 'menuType',
      sorter: true,
      showSorterTooltip: false,
      width: 90
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

  // 当前编辑数据
  const current = ref(null);

  // 是否显示编辑弹窗
  const showEdit = ref(false);

  // 上级菜单id
  const parentId = ref();

  // 菜单数据
  const menuData = ref([]);

  // 表格展开的行
  const expandedRowKeys = ref([]);

  // 表格数据源
  const datasource = ({ where }) => {
    return listMenus({ ...where });
  };

  /* 数据转为树形结构 */
  const parseData = (data) => {
    return toTreeData({
      data: data.map((d) => {
        return { ...d, key: d.menuId, value: d.menuId };
      }),
      idField: 'menuId',
      parentIdField: 'parentId'
    });
  };

  /* 表格渲染完成回调 */
  const onDone = ({ data }) => {
    menuData.value = data;
  };

  /* 刷新表格 */
  const reload = (where) => {
    tableRef?.value?.reload({ where });
  };

  /* 打开编辑弹窗 */
  const openEdit = (row, id) => {
    current.value = row ?? null;
    parentId.value = id;
    showEdit.value = true;
  };

  /* 删除单个 */
  const remove = (row) => {
    if (row.children?.length) {
      message.error('请先删除子节点');
      return;
    }
    const hide = messageLoading('请求中..', 0);
    removeMenu(row.menuId)
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

  /* 展开全部 */
  const expandAll = () => {
    let keys = [];
    eachTreeData(menuData.value, (d) => {
      if (d.children && d.children.length && d.menuId) {
        keys.push(d.menuId);
      }
    });
    expandedRowKeys.value = keys;
  };

  /* 折叠全部 */
  const foldAll = () => {
    expandedRowKeys.value = [];
  };

  /* 点击展开图标时触发 */
  const onExpand = (expanded, record) => {
    if (expanded) {
      expandedRowKeys.value = [...expandedRowKeys.value, record.menuId];
    } else {
      expandedRowKeys.value = expandedRowKeys.value.filter(
        (d) => d !== record.menuId
      );
    }
  };

  /* 判断是否是目录 */
  const isDirectory = (d) => {
    return !!d.children?.length && !d.component;
  };
</script>

<script>
  import * as MenuIcons from '@/layout/menu-icons';

  export default {
    name: 'SystemMenu',
    components: MenuIcons
  };
</script>
