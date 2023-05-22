<!-- 角色权限分配弹窗 -->
<template>
  <ele-modal
    :width="460"
    title="分配权限"
    :visible="visible"
    :confirm-loading="loading"
    @update:visible="updateVisible"
    @ok="save"
  >
    <a-spin :spinning="authLoading">
      <div style="height: 60vh" class="ele-scrollbar-hover">
        <a-tree
          :checkable="true"
          :show-icon="true"
          :tree-data="authData"
          v-model:expandedKeys="expandKeys"
          v-model:checkedKeys="checkedKeys"
        >
          <template #icon="{ menuIcon }">
            <component v-if="menuIcon" :is="menuIcon" />
          </template>
        </a-tree>
      </div>
    </a-spin>
  </ele-modal>
</template>

<script setup>
  import { ref, watch, nextTick } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { toTreeData, eachTreeData } from 'ele-admin-pro/es';
  import { listRoleMenus, updateRoleMenus } from '@/api/system/role';

  const emit = defineEmits(['update:visible']);

  const props = defineProps({
    // 弹窗是否打开
    visible: Boolean,
    // 当前角色数据
    data: Object
  });

  // 权限数据
  const authData = ref([]);

  // 权限数据请求状态
  const authLoading = ref(false);

  // 提交状态
  const loading = ref(false);

  // 角色权限展开的keys
  const expandKeys = ref([]);

  // 角色权限选中的keys
  const checkedKeys = ref([]);

  /* 查询权限数据 */
  const query = () => {
    authData.value = [];
    expandKeys.value = [];
    checkedKeys.value = [];
    if (!props.data) {
      return;
    }
    authLoading.value = true;
    listRoleMenus(props.data.roleId)
      .then((data) => {
        authLoading.value = false;
        // 转成树形结构的数据
        authData.value = toTreeData({
          data: data?.map((d) => ({
            ...d,
            key: d.menuId,
            icon: undefined,
            menuIcon: d.icon
          })),
          idField: 'menuId',
          parentIdField: 'parentId',
          addParentIds: true,
          parentIds: []
        });
        // 全部默认展开以及回显选中的数据
        nextTick(() => {
          const eks = [];
          const cks = [];
          eachTreeData(authData.value, (d) => {
            if (d.key) {
              if (d.children?.length) {
                eks.push(d.key);
              } else if (d.checked) {
                cks.push(d.key);
              }
            }
          });
          expandKeys.value = eks;
          checkedKeys.value = cks;
        });
      })
      .catch((e) => {
        authLoading.value = false;
        message.error(e.message);
      });
  };

  /* 保存权限分配 */
  const save = () => {
    loading.value = true;
    // 获取选中的id，包含所有半选的父级的id
    const ids = new Set();
    eachTreeData(authData.value, (d) => {
      if (d.key && checkedKeys.value.some((c) => c === d.key)) {
        ids.add(d.key);
        if (d.parentIds) {
          d.parentIds.forEach((id) => {
            ids.add(id);
          });
        }
      }
    });
    updateRoleMenus(props.data?.roleId, Array.from(ids))
      .then((msg) => {
        loading.value = false;
        message.success(msg);
        updateVisible(false);
      })
      .catch((e) => {
        loading.value = false;
        message.error(e.message);
      });
  };

  /* 更新visible */
  const updateVisible = (value) => {
    emit('update:visible', value);
  };

  watch(
    () => props.visible,
    (visible) => {
      if (visible) {
        query();
      }
    }
  );
</script>

<script>
  import * as MenuIcons from '@/layout/menu-icons';

  export default {
    components: MenuIcons
  };
</script>
