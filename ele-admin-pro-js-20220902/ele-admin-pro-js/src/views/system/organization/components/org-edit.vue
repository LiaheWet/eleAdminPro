<!-- 机构编辑弹窗 -->
<template>
  <ele-modal
    :width="620"
    :visible="visible"
    :confirm-loading="loading"
    :title="isUpdate ? '修改机构' : '添加机构'"
    :body-style="{ paddingBottom: '8px' }"
    @update:visible="updateVisible"
    @ok="save"
  >
    <a-form
      ref="formRef"
      :model="form"
      :rules="rules"
      :label-col="{ md: 7, sm: 24 }"
      :wrapper-col="{ md: 17, sm: 24 }"
    >
      <a-row :gutter="16">
        <a-col :md="12" :sm="24" :xs="24">
          <a-form-item label="上级机构" name="parentId">
            <org-select
              :data="organizationList"
              placeholder="请选择上级机构"
              v-model:value="form.parentId"
            />
          </a-form-item>
          <a-form-item label="机构名称" name="organizationName">
            <a-input
              allow-clear
              :maxlength="20"
              placeholder="请输入机构名称"
              v-model:value="form.organizationName"
            />
          </a-form-item>
          <a-form-item label="机构全称" name="organizationFullName">
            <a-input
              allow-clear
              :maxlength="100"
              placeholder="请输入机构全称"
              v-model:value="form.organizationFullName"
            />
          </a-form-item>
          <a-form-item label="机构代码">
            <a-input
              allow-clear
              :maxlength="20"
              placeholder="请输入机构代码"
              v-model:value="form.organizationCode"
            />
          </a-form-item>
        </a-col>
        <a-col :md="12" :sm="24" :xs="24">
          <a-form-item label="机构类型" name="organizationType">
            <org-type-select v-model:value="form.organizationType" />
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
          <a-form-item label="备注">
            <a-textarea
              :rows="4"
              :maxlength="200"
              placeholder="请输入备注"
              v-model:value="form.comments"
            />
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </ele-modal>
</template>

<script setup>
  import { ref, reactive, watch } from 'vue';
  import { message } from 'ant-design-vue/es';
  import useFormData from '@/utils/use-form-data';
  import OrgSelect from './org-select.vue';
  import OrgTypeSelect from './org-type-select.vue';
  import {
    addOrganization,
    updateOrganization
  } from '@/api/system/organization';

  const emit = defineEmits(['done', 'update:visible']);

  const props = defineProps({
    // 弹窗是否打开
    visible: Boolean,
    // 修改回显的数据
    data: Object,
    // 机构id
    organizationId: Number,
    // 全部机构
    organizationList: Array
  });

  //
  const formRef = ref(null);

  // 是否是修改
  const isUpdate = ref(false);

  // 提交状态
  const loading = ref(false);

  // 表单数据
  const { form, resetFields, assignFields } = useFormData({
    organizationId: undefined,
    parentId: undefined,
    organizationName: '',
    organizationFullName: '',
    organizationCode: '',
    organizationType: undefined,
    sortNumber: undefined,
    comments: ''
  });

  // 表单验证规则
  const rules = reactive({
    organizationName: [
      {
        required: true,
        message: '请输入机构名称',
        type: 'string',
        trigger: 'blur'
      }
    ],
    organizationFullName: [
      {
        required: true,
        message: '请输入机构全称',
        type: 'string',
        trigger: 'blur'
      }
    ],
    organizationType: [
      {
        required: true,
        message: '请选择机构类型',
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
        const orgData = {
          ...form,
          parentId: form.parentId || 0
        };
        const saveOrUpdate = isUpdate.value
          ? updateOrganization
          : addOrganization;
        saveOrUpdate(orgData)
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

  watch(
    () => props.visible,
    (visible) => {
      if (visible) {
        if (props.data) {
          assignFields(props.data);
          isUpdate.value = true;
        } else {
          form.parentId = props.organizationId;
          isUpdate.value = false;
        }
      } else {
        resetFields();
        formRef.value?.clearValidate();
      }
    }
  );
</script>
