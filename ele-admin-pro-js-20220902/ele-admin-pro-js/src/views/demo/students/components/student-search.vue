<!-- 搜索表单 -->
<template>
  <a-form
    :label-col="{ xl: 7, lg: 5, md: 7, sm: 4 }"
    :wrapper-col="{ xl: 17, lg: 19, md: 17, sm: 20 }"
  >
    <a-row :gutter="8">
      <a-col :xl="6" :lg="12" :md="12" :sm="24" :xs="24">
        <a-form-item label="学生名字">
          <a-input
            v-model:value.trim="form.studentName"
            placeholder="请输入"
            allow-clear
          />
        </a-form-item>
      </a-col>
      <a-col :xl="6" :lg="12" :md="12" :sm="24" :xs="24">
        <a-form-item label="学号">
          <a-input
            v-model:value.trim="form.studentNumber"
            placeholder="请输入"
            allow-clear
          />
        </a-form-item>
      </a-col>
      <a-col :xl="6" :lg="12" :md="12" :sm="24" :xs="24">
        <a-form-item label="专业">
          <a-input
            v-model:value.trim="form.major"
            placeholder="请输入"
            allow-clear
          />
        </a-form-item>
      </a-col>
      <a-col :xl="6" :lg="12" :md="12" :sm="24" :xs="24">
        <a-form-item class="ele-text-right" :wrapper-col="{ span: 24 }">
          <a-space>
            <a-button type="primary" @click="search">查询</a-button>
            <a-button @click="reset">重置</a-button>
          </a-space>
        </a-form-item>
      </a-col>
    </a-row>
  </a-form>
</template>

<script setup>
  import useFormData from '@/utils/use-form-data';

  const emit = defineEmits(['search']);

  // 表单数据
  const { form, resetFields } = useFormData({
    studentName: '',
    studentNumber: '',
    major: ''
  });

  /* 搜索 */
  const search = () => {
    emit('search', form);
  };

  /*  重置 */
  const reset = () => {
    resetFields();
    search();
  };
</script>
