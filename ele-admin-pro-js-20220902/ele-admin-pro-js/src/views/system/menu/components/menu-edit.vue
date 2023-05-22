<!-- 编辑弹窗 -->
<template>
  <ele-modal
    :width="740"
    :visible="visible"
    :confirm-loading="loading"
    :title="isUpdate ? '修改菜单' : '新建菜单'"
    :body-style="{ paddingBottom: '8px' }"
    @update:visible="updateVisible"
    @ok="save"
  >
    <a-form
      ref="formRef"
      :model="form"
      :rules="rules"
      :label-col="{ md: 6, sm: 4, xs: 24 }"
      :wrapper-col="{ md: 18, sm: 20, xs: 24 }"
    >
      <a-row :gutter="16">
        <a-col :md="12" :sm="24" :xs="24">
          <a-form-item label="上级菜单" name="parentId">
            <a-tree-select
              allow-clear
              :tree-data="menuList"
              tree-default-expand-all
              placeholder="请选择上级菜单"
              :value="form.parentId || undefined"
              :dropdown-style="{ maxHeight: '360px', overflow: 'auto' }"
              @update:value="(value) => (form.parentId = value)"
            />
          </a-form-item>
          <a-form-item label="菜单名称" name="title">
            <a-input
              allow-clear
              placeholder="请输入菜单名称"
              v-model:value="form.title"
            />
          </a-form-item>
        </a-col>
        <a-col :md="12" :sm="24" :xs="24">
          <a-form-item label="菜单类型" name="menuType">
            <a-radio-group
              v-model:value="form.menuType"
              @change="onMenuTypeChange"
            >
              <a-radio :value="0">目录</a-radio>
              <a-radio :value="1">菜单</a-radio>
              <a-radio :value="2">按钮</a-radio>
            </a-radio-group>
          </a-form-item>
          <a-form-item label="打开方式">
            <a-radio-group
              v-model:value="form.openType"
              :disabled="form.menuType === 0 || form.menuType === 2"
              @change="onOpenTypeChange"
            >
              <a-radio :value="0">组件</a-radio>
              <a-radio :value="1">内链</a-radio>
              <a-radio :value="2">外链</a-radio>
            </a-radio-group>
          </a-form-item>
        </a-col>
      </a-row>
      <div style="margin-bottom: 22px">
        <a-divider />
      </div>
      <a-row :gutter="16">
        <a-col :md="12" :sm="24" :xs="24">
          <a-form-item label="菜单图标" name="icon">
            <ele-icon-picker
              :data="iconData"
              :allow-search="false"
              v-model:value="form.icon"
              placeholder="请输入菜单图标"
              :disabled="form.menuType === 2"
            >
              <template #icon="{ icon }">
                <component :is="icon" />
              </template>
            </ele-icon-picker>
          </a-form-item>
          <a-form-item name="path">
            <template #label>
              <a-tooltip
                v-if="form.openType === 2"
                title="需要以`http://`、`https://`、`//`开头"
              >
                <question-circle-outlined
                  style="vertical-align: -2px; margin-right: 4px"
                />
              </a-tooltip>
              <span>{{ form.openType === 2 ? '外链地址' : '路由地址' }}</span>
            </template>
            <a-input
              allow-clear
              v-model:value="form.path"
              :disabled="form.menuType === 2"
              :placeholder="
                form.openType === 2 ? '请输入外链地址' : '请输入路由地址'
              "
            />
          </a-form-item>
          <a-form-item name="component">
            <template #label>
              <a-tooltip
                v-if="form.openType === 1"
                title="需要以`http://`、`https://`、`//`开头"
              >
                <question-circle-outlined
                  style="vertical-align: -2px; margin-right: 4px"
                />
              </a-tooltip>
              <span>{{ form.openType === 1 ? '内链地址' : '组件路径' }}</span>
            </template>
            <a-input
              allow-clear
              v-model:value="form.component"
              :disabled="
                form.menuType === 0 ||
                form.menuType === 2 ||
                form.openType === 2
              "
              :placeholder="
                form.openType === 1 ? '请输入内链地址' : '请输入组件路径'
              "
            />
          </a-form-item>
        </a-col>
        <a-col :md="12" :sm="24" :xs="24">
          <a-form-item label="权限标识" name="authority">
            <a-input
              allow-clear
              placeholder="请输入权限标识"
              v-model:value="form.authority"
              :disabled="
                form.menuType === 0 ||
                (form.menuType === 1 && form.openType === 2)
              "
            />
          </a-form-item>
          <a-form-item label="排序号" name="sortNumber">
            <a-input-number
              :min="0"
              :max="99999"
              class="ele-fluid"
              placeholder="请输入排序号"
              v-model:value="form.sortNumber"
            />
          </a-form-item>
          <a-form-item label="是否展示">
            <a-switch
              checked-children="是"
              un-checked-children="否"
              :checked="form.hide === 0"
              :disabled="form.menuType === 2"
              @update:checked="updateHideValue"
            />
            <a-tooltip
              title="选择不展示只注册路由不展示在侧边栏, 比如添加页面应该选择不展示"
            >
              <question-circle-outlined
                style="vertical-align: -4px; margin-left: 16px"
              />
            </a-tooltip>
          </a-form-item>
        </a-col>
      </a-row>
      <a-form-item
        label="路由元数据"
        name="meta"
        :label-col="{ md: 3, sm: 4, xs: 24 }"
        :wrapper-col="{ md: 21, sm: 20, xs: 24 }"
      >
        <a-textarea
          :rows="4"
          :maxlength="200"
          placeholder="请输入JSON格式的路由元数据"
          v-model:value="form.meta"
        />
      </a-form-item>
    </a-form>
  </ele-modal>
</template>

<script setup>
  import { ref, reactive, watch } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { QuestionCircleOutlined } from '@ant-design/icons-vue';
  import { isExternalLink } from 'ele-admin-pro/es';
  import useFormData from '@/utils/use-form-data';
  import { addMenu, updateMenu } from '@/api/system/menu';

  const emit = defineEmits(['done', 'update:visible']);

  const props = defineProps({
    // 弹窗是否打开
    visible: Boolean,
    // 修改回显的数据
    data: Object,
    // 上级菜单id
    parentId: Number,
    // 全部菜单数据
    menuList: Array
  });

  //
  const formRef = ref(null);

  // 是否是修改
  const isUpdate = ref(false);

  // 提交状态
  const loading = ref(false);

  // 表单数据
  const { form, resetFields, assignFields } = useFormData({
    menuId: undefined,
    parentId: undefined,
    title: '',
    menuType: 0,
    openType: 0,
    icon: '',
    path: '',
    component: '',
    authority: '',
    sortNumber: undefined,
    hide: 0,
    meta: ''
  });

  // 表单验证规则
  const rules = reactive({
    title: [
      {
        required: true,
        message: '请输入菜单名称',
        type: 'string',
        trigger: 'blur'
      }
    ],
    sortNumber: [
      {
        required: true,
        message: '请输入排序号',
        type: 'number',
        trigger: 'blur'
      }
    ],
    meta: [
      {
        type: 'string',
        validator: async (_rule, value) => {
          if (value) {
            const msg = '请输入正确的JSON格式';
            try {
              const obj = JSON.parse(value);
              if (typeof obj !== 'object' || obj === null) {
                return Promise.reject(msg);
              }
            } catch (_e) {
              return Promise.reject(msg);
            }
          }
          return Promise.resolve();
        },
        trigger: 'blur'
      }
    ]
  });

  /* 保存编辑 */
  const save = () => {
    if (!formRef.value) {
      return;
    }
    formRef.value
      .validate()
      .then(() => {
        loading.value = true;
        const menuForm = {
          ...form,
          // menuType 对应的值与后端不一致在前端处理
          menuType: form.menuType === 2 ? 1 : 0,
          parentId: form.parentId || 0
        };
        const saveOrUpdate = isUpdate.value ? updateMenu : addMenu;
        saveOrUpdate(menuForm)
          .then((msg) => {
            loading.value = false;
            message.success(msg);
            updateVisible(false);
            emit('done');
          })
          .catch((e) => {
            loading.value = false;
            message.error(e.message);
          });
      })
      .catch(() => {});
  };

  /* 更新visible */
  const updateVisible = (value) => {
    emit('update:visible', value);
  };

  /* menuType选择改变 */
  const onMenuTypeChange = () => {
    if (form.menuType === 0) {
      form.authority = '';
      form.openType = 0;
      form.component = '';
    } else if (form.menuType === 1) {
      if (form.openType === 2) {
        form.authority = '';
      }
    } else {
      form.openType = 0;
      form.icon = '';
      form.path = '';
      form.component = '';
      form.hide = 0;
    }
  };

  /* openType选择改变 */
  const onOpenTypeChange = () => {
    if (form.openType === 2) {
      form.component = '';
      form.authority = '';
    }
  };

  const updateHideValue = (value) => {
    form.hide = value ? 0 : 1;
  };

  /* 判断是否是目录 */
  const isDirectory = (d) => {
    return !!d.children?.length && !d.component;
  };

  watch(
    () => props.visible,
    (visible) => {
      if (visible) {
        if (props.data) {
          const isExternal = isExternalLink(props.data.path);
          const isInner = isExternalLink(props.data.component);
          // menuType 对应的值与后端不一致在前端处理
          const menuType =
            props.data.menuType === 1 ? 2 : isDirectory(props.data) ? 0 : 1;
          assignFields({
            ...props.data,
            menuType,
            openType: isExternal ? 2 : isInner ? 1 : 0,
            parentId:
              props.data.parentId === 0 ? undefined : props.data.parentId
          });
          isUpdate.value = true;
        } else {
          form.parentId = props.parentId;
          isUpdate.value = false;
        }
      } else {
        resetFields();
        formRef.value?.clearValidate();
      }
    }
  );
</script>

<script>
  import * as icons from '@/layout/menu-icons';

  export default {
    components: icons,
    data() {
      return {
        iconData: [
          {
            title: '已引入的图标',
            icons: Object.keys(icons)
          }
        ]
      };
    }
  };
</script>
