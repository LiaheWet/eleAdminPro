<!-- 角色编辑弹窗 -->
<template>
  <ele-modal
    :width="460"
    :visible="visible"
    :confirm-loading="loading"
    :title="isUpdate ? '修改角色' : '添加角色'"
    :body-style="{ paddingBottom: '8px' }"
    @update:visible="updateVisible"
    @ok="save"
  >
    <a-form
      ref="formRef"
      :model="form"
      :rules="rules"
      :label-col="{ md: 5, sm: 5, xs: 24 }"
      :wrapper-col="{ md: 19, sm: 19, xs: 24 }"
    >
      <a-form-item label="学生名字" name="studentName">
        <a-input
          allow-clear
          :maxlength="20"
          placeholder="请输入学生名字"
          v-model:value="form.studentName"
        />
      </a-form-item>
      <a-form-item label="学生学号" name="studentNumber">
        <a-input
          allow-clear
          :maxlength="20"
          placeholder="请输入学生学号识"
          v-model:value="form.studentNumber"
        />
      </a-form-item>
      <a-form-item label="专业" name="major">
        <a-input
          allow-clear
          :maxlength="20"
          placeholder="请输入专业"
          v-model:value="form.major"
        />
      </a-form-item>
      <a-form-item label="性别" name="sex" >
<!--        <sex-select
          v-model:value="form.sex"
          @blur="validate('sex', { trigger: 'blur' }).catch(() => {})"
        />-->
        <a-input
          allow-clear
          :maxlength="20"
          placeholder="请输入性别"
          v-model:value="form.sex"
        />
      </a-form-item>

    </a-form>
  </ele-modal>
</template>

<script setup>
  import { ref, reactive, watch } from 'vue';
  import { message } from 'ant-design-vue/es';
  import useFormData from '@/utils/use-form-data';
  import { addStudent, updateStudent } from '@/api/demo/students';
  const emit = defineEmits(['done', 'update:visible']);

  const props = defineProps({
    // 弹窗是否打开
    visible: Boolean,
    // 修改回显的数据
    data: Object
  });

  //
  const formRef = ref(null);

  // 是否是修改
  const isUpdate = ref(false);

  // 提交状态
  const loading = ref(false);

  // 表单数据
  const { form, resetFields, assignFields } = useFormData({
    studentId: undefined,
    studentName: '',
    studentNumber: '',
    major: '',
    sex:undefined
  });

  // 表单验证规则
  const rules = reactive({
    studentName: [
      {
        required: true,
        message: '请输入学生名字',
        type: 'string',
        trigger: 'blur'
      }
    ],
    studentNumber: [
      {
        required: true,
        message: '请输入学生学号',
        type: 'string',
        trigger: 'blur'
      }
    ],
    major: [
      {
        required: true,
        message: '请输入专业',
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
        const saveOrUpdate = isUpdate.value ? updateStudent : addStudent;
        saveOrUpdate(form)
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
