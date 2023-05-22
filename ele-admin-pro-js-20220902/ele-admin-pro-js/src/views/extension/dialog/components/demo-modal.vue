<template>
  <a-card title="可拖拽、拉伸、全屏弹窗" :bordered="false">
    <a-form
      style="max-width: 360px"
      :label-col="{ md: 10, sm: 8, xs: 24 }"
      :wrapper-col="{ md: 14, sm: 16, xs: 24 }"
    >
      <a-form-item label="是否可拖出边界">
        <a-select v-model:value="moveOut">
          <a-select-option :value="0">不可拖出边界</a-select-option>
          <a-select-option :value="1">可以拖出边界</a-select-option>
          <a-select-option :value="2">只可右下方向拖出边界</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="是否可拉伸大小">
        <a-select v-model:value="resizable">
          <a-select-option value="false">不可拉伸大小</a-select-option>
          <a-select-option value="true">可以拉伸大小</a-select-option>
          <a-select-option value="horizontal">只可横向拉伸</a-select-option>
          <a-select-option value="vertical">只可纵向拉伸</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="最大化切换按钮">
        <a-switch v-model:checked="maxable" size="small" />
      </a-form-item>
      <!-- <a-form-item label="是否垂直居中">
        <a-switch v-model:checked="centered" size="small" />
      </a-form-item> -->
      <a-form-item label="关闭后重置位置">
        <a-switch v-model:checked="resetOnClose" size="small" />
      </a-form-item>
      <a-form-item label="限制在主体区域">
        <a-switch v-model:checked="inner" size="small" />
      </a-form-item>
      <a-form-item label="默认位置">
        <a-select allow-clear v-model:value="position" placeholder="请选择">
          <a-select-option value="top">顶部</a-select-option>
          <a-select-option value="bottom">底部</a-select-option>
          <a-select-option value="left">左边</a-select-option>
          <a-select-option value="right">右边</a-select-option>
          <a-select-option value="leftTop">左上角</a-select-option>
          <a-select-option value="leftBottom">左下角</a-select-option>
          <a-select-option value="rightTop">右上角</a-select-option>
          <a-select-option value="rightBottom">右下角</a-select-option>
          <a-select-option value="center">正中间</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item :wrapper-col="{ md: { offset: 10 } }">
        <a-button type="primary" class="ele-btn-icon" @click="openDialog">
          打开可拖拽弹窗
        </a-button>
      </a-form-item>
    </a-form>
  </a-card>
  <ele-modal
    :width="400"
    title="拖拽弹窗"
    v-model:visible="visible"
    :move-out="moveOut > 0"
    :move-out-positive="moveOut === 2"
    :resizable="modalResizable"
    :maxable="maxable"
    :inner="inner"
    :centered="centered"
    :reset-on-close="resetOnClose"
    :position="position"
    :body-style="{ paddingBottom: '16px' }"
    @cancel="cancel"
    @ok="save"
  >
    <a-form
      ref="formRef"
      :model="form"
      :rules="rules"
      :label-col="{ flex: '70px' }"
      :wrapper-col="{ flex: '1' }"
    >
      <a-form-item label="用户名" name="nickname" style="flex-wrap: nowrap">
        <a-input
          allow-clear
          placeholder="请输入用户名"
          v-model:value="form.nickname"
        />
      </a-form-item>
      <a-form-item label="性别" name="sex">
        <a-select allow-clear placeholder="请选择性别" v-model:value="form.sex">
          <a-select-option value="男">男</a-select-option>
          <a-select-option value="女">女</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="手机号" name="phone" style="flex-wrap: nowrap">
        <a-input
          allow-clear
          placeholder="请输入手机号"
          v-model:value="form.phone"
        />
      </a-form-item>
      <a-form-item label="邮箱" name="email" style="flex-wrap: nowrap">
        <a-input
          allow-clear
          placeholder="请输入邮箱"
          v-model:value="form.email"
        />
      </a-form-item>
      <a-form-item label="个人简介" style="flex-wrap: nowrap">
        <a-textarea
          :rows="4"
          placeholder="请输入个人简介"
          v-model:value="form.introduction"
        />
      </a-form-item>
    </a-form>
  </ele-modal>
</template>

<script setup>
  import { ref, reactive, computed } from 'vue';
  import { message } from 'ant-design-vue/es';
  import useFormData from '@/utils/use-form-data';

  // 弹窗是否打开
  const visible = ref(false);

  // 是否允许拖出边界
  const moveOut = ref(0);

  // 是否可拉伸
  const resizable = ref('false');

  // 是否显示最大化切换按钮
  const maxable = ref(true);

  // 关闭后重置位置
  const resetOnClose = ref(true);

  // 限制在主体区域
  const inner = ref(false);

  // 垂直居中
  const centered = ref(false);

  // 默认位置
  const position = ref();

  //
  const formRef = ref(null);

  //
  const modalResizable = computed(() => {
    return resizable.value === 'true'
      ? true
      : resizable.value === 'false'
      ? false
      : resizable.value;
  });

  // 表单数据
  const { form, resetFields } = useFormData({
    nickname: '',
    sex: undefined,
    phone: '',
    email: '',
    introduction: ''
  });

  // 表单验证规则
  const rules = reactive({
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
    phone: [
      {
        required: true,
        message: '请输入手机号',
        type: 'string',
        trigger: 'blur'
      }
    ],
    email: [
      {
        required: true,
        message: '请输入邮箱',
        type: 'string',
        trigger: 'blur'
      }
    ]
  });

  /* 打开弹窗 */
  const openDialog = () => {
    if (!visible.value) {
      visible.value = true;
    }
  };

  /* 弹窗关闭回调 */
  const cancel = () => {
    resetFields();
    formRef.value?.clearValidate();
  };

  /* 保存编辑 */
  const save = () => {
    if (!formRef.value) {
      return;
    }
    formRef.value
      .validate()
      .then(() => {
        message.success('保存成功');
      })
      .catch(() => {});
  };
</script>
