import { reactive } from 'vue';

/**
 * 搜索表单 hook
 * @param initValue 默认值
 */
export default function (initValue) {
  const where = reactive({ ...initValue });

  const resetFields = () => {
    Object.keys(where).forEach((key) => {
      where[key] = initValue ? initValue[key] : void 0;
    });
  };

  const assignFields = (data) => {
    Object.keys(where).forEach((key) => {
      where[key] = data[key];
    });
  };

  return {
    where,
    resetFields,
    assignFields
  };
}
