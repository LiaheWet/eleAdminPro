<template>
  <div>
    <a-page-header :ghost="false" title="基础表单">
      <div class="ele-text-secondary">
        表单页用于向用户收集或验证信息，基础表单常见于数据项较少的表单场景。
      </div>
    </a-page-header>
    <div class="ele-body">
      <a-card :bordered="false">
        <a-form
          ref="formRef"
          :model="form"
          :rules="rules"
          :label-col="{ sm: 4, xs: 24 }"
          :wrapper-col="{ sm: 20, xs: 24 }"
          style="max-width: 800px; margin: 0 auto"
        >
          <a-form-item label="标题" name="title">
            <a-input
              allow-clear
              placeholder="请输入标题"
              v-model:value="form.title"
            />
          </a-form-item>
          <a-form-item label="起止日期" name="datetime">
            <a-range-picker
              class="ele-fluid"
              value-format="YYYY-MM-DD"
              v-model:value="form.datetime"
            />
          </a-form-item>
          <a-form-item label="目标描述" name="goal">
            <a-textarea
              :rows="4"
              v-model:value="form.goal"
              placeholder="请输入目标描述"
            />
          </a-form-item>
          <a-form-item label="衡量标准" name="standard">
            <a-textarea
              :rows="4"
              v-model:value="form.standard"
              placeholder="请输入衡量标准"
            />
          </a-form-item>
          <a-form-item label="地点" name="address">
            <a-select
              allow-clear
              v-model:value="form.address"
              placeholder="请选择地点"
            >
              <a-select-option value="1">地点一</a-select-option>
              <a-select-option value="2">地点二</a-select-option>
              <a-select-option value="3">地点三</a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="邀评人">
            <a-select
              allow-clear
              mode="multiple"
              v-model:value="form.invites"
              placeholder="请选择邀评人"
            >
              <a-select-option
                v-for="item in users"
                :key="item.id"
                :value="item.name"
              >
                {{ item.name }}
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="权重">
            <a-space>
              <a-input-number :min="0" :max="100" v-model:value="form.weight" />
              <span>%</span>
            </a-space>
          </a-form-item>
          <a-form-item label="目标公开">
            <a-radio-group name="publicType" v-model:value="form.publicType">
              <a-radio :value="1">公开</a-radio>
              <a-radio :value="2">部分公开</a-radio>
              <a-radio :value="3">不公开</a-radio>
            </a-radio-group>
            <div style="margin-top: 12px">
              <a-input v-if="form.publicType === 2" placeholder="公开给" />
            </div>
            <div class="ele-text-secondary" style="margin-top: 12px">
              客户、邀评人默认被分享
            </div>
          </a-form-item>
          <a-form-item :wrapper-col="{ sm: { offset: 4 } }">
            <a-space size="middle">
              <a-button @click="finishPageTab()">关闭</a-button>
              <a-button type="primary" :loading="loading" @click="submit">
                提交
              </a-button>
            </a-space>
          </a-form-item>
        </a-form>
      </a-card>
    </div>
  </div>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { message } from 'ant-design-vue/es';
  import useFormData from '@/utils/use-form-data';
  import { finishPageTab } from '@/utils/page-tab-util';

  //
  const formRef = ref(null);

  // 加载状态
  const loading = ref(false);

  // 表单数据
  const { form, resetFields } = useFormData({
    title: '',
    datetime: ['', ''],
    goal: '',
    standard: '',
    address: undefined,
    invites: [],
    weight: 0,
    publicType: 1
  });

  // 表单验证规则
  const rules = reactive({
    title: [
      {
        required: true,
        message: '请输入标题',
        type: 'string',
        trigger: 'blur'
      }
    ],
    datetime: [
      {
        required: true,
        message: '请选择起止日期',
        type: 'array',
        trigger: 'blur'
      }
    ],
    goal: [
      {
        required: true,
        message: '请输入目标描述',
        type: 'string',
        trigger: 'blur'
      }
    ],
    standard: [
      {
        required: true,
        message: '请输入衡量标准',
        type: 'string',
        trigger: 'blur'
      }
    ],
    address: [
      {
        required: true,
        message: '请选择地点',
        type: 'string',
        trigger: 'blur'
      }
    ]
  });

  // 邀评人下拉列表数据
  const users = ref([
    { id: 1, name: 'SunSmile' },
    { id: 2, name: '你的名字很好听' },
    { id: 3, name: '全村人的希望' },
    { id: 4, name: 'Jasmine' },
    { id: 5, name: '酷酷的大叔' }
  ]);

  /* 提交 */
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
          resetFields();
          formRef.value?.clearValidate();
          message.success('提交成功');
        }, 1500);
      })
      .catch(() => {});
  };
</script>

<script>
  export default {
    name: 'FormBasic'
  };
</script>
