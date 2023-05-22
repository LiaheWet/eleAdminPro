<!-- 字典项编辑弹窗 -->
<template>
  <ele-modal
    :width="460"
    :visible="visible"
    :confirm-loading="loading"
    :body-style="{ paddingBottom: '8px' }"
    :title="isUpdate ? '修改字典项' : '添加字典项'"
    @update:visible="updateVisible"
    @ok="save"
  >
    <a-form
      ref="formRef"
      :model="form"
      :rules="rules"
      :label-col="{ md: 6, sm: 6, xs: 24 }"
      :wrapper-col="{ md: 18, sm: 18, xs: 24 }"
    >
      <a-form-item label="字典项名称" name="dictDataName">
        <a-input
          allow-clear
          :maxlength="20"
          placeholder="请输入字典项名称"
          v-model:value="form.dictDataName"
        />
      </a-form-item>
      <a-form-item label="字典项值" name="dictDataCode">
        <a-input
          allow-clear
          :maxlength="20"
          placeholder="请输入字典项值"
          v-model:value="form.dictDataCode"
        />
      </a-form-item>
      <a-form-item label="排序号" name="sortNumber">
        <a-input-number
          :min="0"
          :max="9999"
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
    </a-form>
  </ele-modal>
</template>

<script setup>
  import { ref, reactive, watch } from 'vue';
  import { message } from 'ant-design-vue/es';
  import useFormData from '@/utils/use-form-data';
  import {
    addDictionaryData,
    updateDictionaryData
  } from '@/api/system/dictionary-data';

  const emit = defineEmits(['done', 'update:visible']);

  const props = defineProps({
    // 弹窗是否打开
    visible: Boolean,
    // 修改回显的数据
    data: Object,
    // 字典id
    dictId: Number
  });

  //
  const formRef = ref(null);

  // 是否是修改
  const isUpdate = ref(false);

  // 提交状态
  const loading = ref(false);

  // 表单数据
  const { form, resetFields, assignFields } = useFormData({
    dictDataId: undefined,
    dictDataName: '',
    dictDataCode: '',
    sortNumber: '',
    comments: ''
  });

  // 表单验证规则
  const rules = reactive({
    dictDataName: [
      {
        required: true,
        message: '请输入字典项名称',
        type: 'string',
        trigger: 'blur'
      }
    ],
    dictDataCode: [
      {
        required: true,
        message: '请输入字典项值',
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
        const saveOrUpdate = isUpdate.value
          ? updateDictionaryData
          : addDictionaryData;
        saveOrUpdate({
          ...form,
          dictId: props.dictId
        })
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
          isUpdate.value = false;
        }
      } else {
        resetFields();
        formRef.value?.clearValidate();
      }
    }
  );
</script>
