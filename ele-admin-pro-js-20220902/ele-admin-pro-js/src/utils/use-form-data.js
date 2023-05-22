import { reactive } from 'vue';

/**
 * 表单数据 hook
 * @param initValue 默认值
 */
export default function (initValue) {
  const form = reactive({ ...initValue });

  const resetFields = () => {
    Object.keys(form).forEach((key) => {
      form[key] = initValue ? initValue[key] : void 0;
    });
  };

  const assignFields = (data) => {
    Object.keys(form).forEach((key) => {
      form[key] = data[key];
    });
  };

  return {
    form,
    // 重置为初始值
    resetFields,
    // 赋值不改变字段
    assignFields
  };
}
