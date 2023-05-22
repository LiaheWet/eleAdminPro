<template>
  <ele-modal
    :width="460"
    :visible="visible"
    :confirm-loading="loading"
    title="重命名"
    :body-style="{ paddingBottom: '8px' }"
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
      <a-form-item label="文件/夹名称" name="name">
        <a-input
          allow-clear
          :maxlength="20"
          placeholder="请输入文件/夹名称"
          v-model:value="form.name"
        />
      </a-form-item>
    </a-form>
  </ele-modal>
</template>

<script setup>
  import { ref, reactive, watch } from 'vue';
  import { message } from 'ant-design-vue/es';
  import useFormData from '@/utils/use-form-data';
  import { updateUserFile } from '@/api/system/user-file';

  const emit = defineEmits(['done', 'update:visible']);

  const props = defineProps({
    // 弹窗是否打开
    visible: Boolean,
    // 文件数据
    data: Object
  });

  //
  const formRef = ref(null);

  // 提交状态
  const loading = ref(false);

  // 表单数据
  const { form, resetFields } = useFormData({ name: '' });

  // 表单验证规则
  const rules = reactive({
    name: [
      {
        required: true,
        message: '请输入文件/夹名称',
        type: 'string',
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
        updateUserFile({ ...form, id: props.data?.id })
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

  /* 更新 visible */
  const updateVisible = (value) => {
    emit('update:visible', value);
  };

  watch(
    () => props.visible,
    (visible) => {
      if (!visible) {
        resetFields();
        formRef.value?.clearValidate();
      } else if (props.data) {
        form.name = props.data.name;
      }
    }
  );
</script>
