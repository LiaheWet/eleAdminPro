<!-- 修改密码弹窗 -->
<template>
  <ele-modal
    :width="420"
    title="修改密码"
    :visible="visible"
    :confirm-loading="loading"
    :body-style="{ paddingBottom: '16px' }"
    @update:visible="updateVisible"
    @cancel="onCancel"
    @ok="onOk"
  >
    <a-form
      ref="formRef"
      :model="form"
      :rules="rules"
      :label-col="{ sm: 6 }"
      :wrapper-col="{ sm: 18 }"
    >
      <a-form-item label="旧密码" name="oldPassword">
        <a-input-password
          v-model:value="form.oldPassword"
          placeholder="请输入旧密码"
        />
      </a-form-item>
      <a-form-item label="新密码" name="password">
        <a-input-password
          v-model:value="form.password"
          placeholder="请输入新密码"
        />
      </a-form-item>
      <a-form-item label="确认密码" name="password2">
        <a-input-password
          v-model:value="form.password2"
          placeholder="请再次输入新密码"
        />
      </a-form-item>
    </a-form>
  </ele-modal>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { message } from 'ant-design-vue/es';
  import useFormData from '@/utils/use-form-data';
  import { updatePassword } from '@/api/layout';

  const emit = defineEmits(['update:visible']);

  defineProps({
    visible: Boolean
  });

  //
  const formRef = ref(null);

  // 提交loading
  const loading = ref(false);

  // 表单数据
  const { form, resetFields } = useFormData({
    oldPassword: '',
    password: '',
    password2: ''
  });

  // 表单验证规则
  const rules = reactive({
    oldPassword: [
      {
        required: true,
        type: 'string',
        message: '请输入旧密码',
        trigger: 'blur'
      }
    ],
    password: [
      {
        required: true,
        type: 'string',
        message: '请输入新密码',
        trigger: 'blur'
      }
    ],
    password2: [
      {
        required: true,
        type: 'string',
        validator: async (_rule, value) => {
          if (!value) {
            return Promise.reject('请再次输入新密码');
          }
          if (value !== form.password) {
            return Promise.reject('两次输入密码不一致');
          }
          return Promise.resolve();
        },
        trigger: 'blur'
      }
    ]
  });

  /* 修改visible */
  const updateVisible = (value) => {
    emit('update:visible', value);
  };

  /* 保存修改 */
  const onOk = () => {
    if (!formRef.value) {
      return;
    }
    formRef.value
      .validate()
      .then(() => {
        loading.value = true;
        updatePassword(form)
          .then((msg) => {
            loading.value = false;
            message.success(msg);
            updateVisible(false);
          })
          .catch((e) => {
            loading.value = false;
            message.error(e.message);
          });
      })
      .catch(() => {});
  };

  /* 关闭回调 */
  const onCancel = () => {
    resetFields();
    formRef.value?.clearValidate();
    loading.value = false;
  };
</script>
