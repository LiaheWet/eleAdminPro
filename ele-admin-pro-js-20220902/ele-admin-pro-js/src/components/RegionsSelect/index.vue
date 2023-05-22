<!-- 省市区级联选择器 -->
<template>
  <a-cascader
    :value="value"
    :options="regionsData"
    :show-search="showSearch"
    :placeholder="placeholder"
    dropdown-class-name="ele-pop-wrap-higher"
    @update:value="updateValue"
  />
</template>

<script setup>
  import { ref, watch } from 'vue';
  import { getRegionsData } from './load-data';

  const props = defineProps({
    value: Array,
    placeholder: String,
    options: Array,
    valueField: String,
    type: String,
    showSearch: {
      type: Boolean,
      default: true
    }
  });

  const emit = defineEmits(['update:value', 'load-data-done']);

  // 级联选择器数据
  const regionsData = ref([]);

  /* 更新 value */
  const updateValue = (value) => {
    emit('update:value', value);
  };

  /* 级联选择器数据 value 处理 */
  const formatData = (data) => {
    if (props.valueField === 'label') {
      return data.map((d) => {
        const item = {
          label: d.label,
          value: d.label
        };
        if (d.children) {
          item.children = d.children.map((c) => {
            const cItem = {
              label: c.label,
              value: c.label
            };
            if (c.children) {
              cItem.children = c.children.map((cc) => {
                return {
                  label: cc.label,
                  value: cc.label
                };
              });
            }
            return cItem;
          });
        }
        return item;
      });
    } else {
      return data;
    }
  };

  /* 省市区数据筛选 */
  const filterData = (data) => {
    if (props.type === 'provinceCity') {
      return formatData(
        data.map((d) => {
          const item = {
            label: d.label,
            value: d.value
          };
          if (d.children) {
            item.children = d.children.map((c) => {
              return {
                label: c.label,
                value: c.value
              };
            });
          }
          return item;
        })
      );
    } else if (props.type === 'province') {
      return formatData(
        data.map((d) => {
          return {
            label: d.label,
            value: d.value
          };
        })
      );
    } else {
      return formatData(data);
    }
  };

  watch(
    () => props.options,
    (options) => {
      regionsData.value = filterData(options ?? []);
      if (!options) {
        getRegionsData().then((data) => {
          regionsData.value = filterData(data ?? []);
          emit('load-data-done', data);
        });
      }
    },
    {
      immediate: true
    }
  );
</script>
