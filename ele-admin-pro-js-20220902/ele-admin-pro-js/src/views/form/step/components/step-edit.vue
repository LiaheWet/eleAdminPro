<template>
  <a-form
    ref="formRef"
    :model="form"
    :rules="rules"
    :label-col="{ sm: 5, xs: 24 }"
    :wrapper-col="{ sm: 19, xs: 24 }"
  >
    <a-form-item label="付款账户" name="account">
      <a-select
        allow-clear
        v-model:value="form.account"
        placeholder="请选择付款账户"
      >
        <a-select-option value="eleadmin@eclouds.com">
          eleadmin@eclouds.com
        </a-select-option>
      </a-select>
    </a-form-item>
    <a-form-item label="收款账户" name="receiver">
      <a-input
        allow-clear
        v-model:value="form.receiver"
        placeholder="请输入收款账户"
      >
        <template #addonBefore>
          <a-select
            v-model:value="form.pay"
            style="width: 100px; margin: -5px -12px"
          >
            <a-select-option value="alipay">支付宝</a-select-option>
            <a-select-option value="wxpay">微信</a-select-option>
          </a-select>
        </template>
      </a-input>
    </a-form-item>
    <a-form-item label="收款人姓名" name="name">
      <a-input
        allow-clear
        v-model:value="form.name"
        placeholder="请输入收款人姓名"
      />
    </a-form-item>
    <a-form-item label="转账金额" name="amount">
      <a-input
        prefix="￥"
        allow-clear
        v-model:value.number="form.amount"
        placeholder="请输入转账金额"
      />
    </a-form-item>
    <a-form-item :wrapper-col="{ sm: { offset: 5 } }">
      <a-button type="primary" :loading="loading" @click="submit">
        下一步
      </a-button>
    </a-form-item>
  </a-form>
</template>

<script setup>
  import { ref, reactive } from 'vue';

  const emit = defineEmits(['done']);

  //
  const formRef = ref(null);

  // 提交状态
  const loading = ref(false);

  // 表单数据
  const form = reactive({
    account: 'eleadmin@eclouds.com',
    receiver: 'test@example.com',
    pay: 'alipay',
    name: 'Alex',
    amount: 500
  });

  // 表单验证规则
  const rules = reactive({
    account: [
      {
        required: true,
        message: '请选择付款账户',
        type: 'string',
        trigger: 'blur'
      }
    ],
    receiver: [
      {
        required: true,
        message: '请输入收款账户',
        type: 'string',
        trigger: 'blur'
      }
    ],
    name: [
      {
        required: true,
        message: '请输入收款人姓名',
        type: 'string',
        trigger: 'blur'
      }
    ],
    amount: [
      {
        required: true,
        message: '请输入合法金额数字',
        type: 'number',
        trigger: 'blur'
      }
    ]
  });

  /* 步骤一提交 */
  const submit = () => {
    if (!formRef.value) {
      return;
    }
    formRef.value
      .validate()
      .then(() => {
        loading.value = true;
        setTimeout(() => {
          loading.value = false;
          emit('done', form);
        }, 300);
      })
      .catch(() => {});
  };
</script>
