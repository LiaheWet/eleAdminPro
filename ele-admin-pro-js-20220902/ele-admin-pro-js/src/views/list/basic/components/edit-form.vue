<template>
  <a-form
    ref="formRef"
    :model="form"
    :rules="rules"
    :label-col="{ sm: 4, xs: 24 }"
    :wrapper-col="{ sm: 20, xs: 24 }"
    style="max-width: 600px; margin: 0 auto"
  >
    <a-form-item label="用户账号" name="username">
      <a-input
        allow-clear
        :maxlength="20"
        placeholder="请输入用户账号"
        v-model:value="form.username"
      />
    </a-form-item>
    <a-form-item label="用户名" name="nickname">
      <a-input
        allow-clear
        :maxlength="20"
        placeholder="请输入用户名"
        v-model:value="form.nickname"
      />
    </a-form-item>
    <a-form-item label="性别" name="sex">
      <sex-select v-model:value="form.sex" />
    </a-form-item>
    <a-form-item label="角色" name="roles">
      <role-select v-model:value="form.roles" />
    </a-form-item>
    <a-form-item label="邮箱" name="email">
      <a-input
        allow-clear
        :maxlength="100"
        placeholder="请输入邮箱"
        v-model:value="form.email"
      />
    </a-form-item>
    <a-form-item label="手机号" name="phone">
      <a-input
        allow-clear
        :maxlength="11"
        placeholder="请输入手机号"
        v-model:value="form.phone"
      />
    </a-form-item>
    <a-form-item label="出生日期">
      <a-date-picker
        class="ele-fluid"
        placeholder="请选择出生日期"
        value-format="YYYY-MM-DD"
        v-model:value="form.birthday"
      />
    </a-form-item>
    <a-form-item v-if="!isUpdate" label="登录密码" name="password">
      <a-input-password
        :maxlength="20"
        v-model:value="form.password"
        placeholder="请输入登录密码"
      />
    </a-form-item>
    <a-form-item label="个人简介">
      <a-textarea
        :rows="4"
        :maxlength="200"
        placeholder="请输入个人简介"
        v-model:value="form.introduction"
      />
    </a-form-item>
    <a-form-item :wrapper-col="{ sm: { offset: 4 } }">
      <a-space size="middle">
        <a-button @click="onClose">关闭</a-button>
        <a-button type="primary" :loading="loading" @click="save">
          保存
        </a-button>
      </a-space>
    </a-form-item>
  </a-form>
</template>

<script setup>
  import { ref, reactive, watch, unref } from 'vue';
  import { useRouter } from 'vue-router';
  import { message } from 'ant-design-vue/es';
  import { emailReg, phoneReg } from 'ele-admin-pro/es';
  import useFormData from '@/utils/use-form-data';
  import RoleSelect from '@/views/system/user/components/role-select.vue';
  import SexSelect from '@/views/system/user/components/sex-select.vue';
  import { addUser, updateUser, checkExistence } from '@/api/system/user';
  import { removePageTab, reloadPageTab } from '@/utils/page-tab-util';

  const props = defineProps({
    // 修改回显的数据
    data: Object
  });

  const { currentRoute, push } = useRouter();

  //
  const formRef = ref(null);

  // 是否是修改
  const isUpdate = ref(false);

  // 提交状态
  const loading = ref(false);

  // 表单数据
  const { form, resetFields, assignFields } = useFormData({
    userId: undefined,
    username: '',
    nickname: '',
    sex: undefined,
    roles: [],
    email: '',
    phone: '',
    password: '',
    introduction: '',
    birthday: ''
  });

  // 表单验证规则
  const rules = reactive({
    username: [
      {
        required: true,
        type: 'string',
        validator: (_rule, value) => {
          return new Promise((resolve, reject) => {
            if (!value) {
              return reject('请输入用户账号');
            }
            checkExistence('username', value, form.userId)
              .then(() => {
                reject('账号已经存在');
              })
              .catch(() => {
                resolve();
              });
          });
        },
        trigger: 'blur'
      }
    ],
    nickname: [
      {
        required: true,
        message: '请输入用户名',
        type: 'string',
        trigger: 'blur'
      }
    ],
    sex: [
      {
        required: true,
        message: '请选择性别',
        type: 'string',
        trigger: 'blur'
      }
    ],
    roles: [
      {
        required: true,
        message: '请选择角色',
        type: 'array',
        trigger: 'blur'
      }
    ],
    email: [
      {
        pattern: emailReg,
        message: '邮箱格式不正确',
        type: 'string',
        trigger: 'blur'
      }
    ],
    password: [
      {
        required: true,
        type: 'string',
        validator: async (_rule, value) => {
          if (isUpdate.value || /^[\S]{5,18}$/.test(value)) {
            return Promise.resolve();
          }
          return Promise.reject('密码必须为5-18位非空白字符');
        },
        trigger: 'blur'
      }
    ],
    phone: [
      {
        pattern: phoneReg,
        message: '手机号格式不正确',
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
        const saveOrUpdate = isUpdate.value ? updateUser : addUser;
        saveOrUpdate(form)
          .then((msg) => {
            loading.value = false;
            message.success(msg);
            onDone();
          })
          .catch((e) => {
            loading.value = false;
            message.error(e.message);
          });
      })
      .catch(() => {});
  };

  /* 关闭当前页面并跳转到列表页面 */
  const onClose = () => {
    removePageTab({ key: unref(currentRoute).path });
    push('/list/basic');
  };

  /* 关闭当前页面并刷新列表页面 */
  const onDone = () => {
    removePageTab({ key: unref(currentRoute).path });
    reloadPageTab({ fullPath: '/list/basic' });
  };

  watch(
    () => props.data,
    () => {
      if (props.data) {
        assignFields({
          ...props.data,
          password: ''
        });
        isUpdate.value = true;
      } else {
        isUpdate.value = false;
        resetFields();
        formRef.value?.clearValidate();
      }
    },
    { immediate: true }
  );
</script>
