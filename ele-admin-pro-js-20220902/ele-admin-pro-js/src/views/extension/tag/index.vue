<template>
  <div class="ele-body ele-body-card">
    <a-card title="标签组件" :bordered="false">
      <div class="ele-cell">
        <div>预设颜色:</div>
        <div class="ele-cell-content">
          <ele-tag
            v-for="(item, index) in list"
            :key="item"
            :size="size"
            :color="colors[type][index]"
          >
            标签{{ item }}
          </ele-tag>
        </div>
      </div>
      <div class="ele-cell">
        <div>圆角样式:</div>
        <div class="ele-cell-content">
          <ele-tag
            v-for="(item, index) in list"
            :key="item"
            :size="size"
            shape="round"
            :color="colors[type][index]"
          >
            标签{{ item }}
          </ele-tag>
        </div>
      </div>
      <div class="ele-cell">
        <div>圆形样式:</div>
        <div class="ele-cell-content">
          <ele-tag
            v-for="(item, index) in list"
            :key="item"
            :size="size"
            shape="circle"
            :color="colors[type][index]"
          >
            {{ index + 1 }}
          </ele-tag>
        </div>
      </div>
      <div class="ele-cell">
        <div>尺寸选择:</div>
        <div class="ele-cell-content">
          <a-radio-group :options="sizes" v-model:value="size" />
        </div>
      </div>
      <div class="ele-cell">
        <div>主题选择:</div>
        <div class="ele-cell-content">
          <a-radio-group :options="types" v-model:value="type" />
        </div>
      </div>
    </a-card>
    <a-card title="标签输入" :bordered="false">
      <ele-edit-tag v-model:data="tags" :size="size" :color="colors[type][0]" />
      <div style="padding: 8px 0">{{ JSON.stringify(tags) }}</div>
      <div style="padding: 8px 0">自定义异步验证:</div>
      <ele-edit-tag
        v-model:data="tags2"
        :size="size"
        :color="colors[type][0]"
        :validator="validator"
      />
    </a-card>
  </div>
</template>

<script setup>
  import { ref } from 'vue';

  // 尺寸
  const sizes = ref([
    { label: 'mini', value: 'mini' },
    { label: 'small', value: 'small' },
    { label: 'middle', value: 'middle' },
    { label: 'large', value: 'large' }
  ]);

  // 选中尺寸
  const size = ref('mini');

  // 颜色
  const colors = ref([
    ['', 'blue', 'green', 'orange', 'red'],
    ['#909399', '#1890ff', '#52c41a', '#fa8c16', '#f5222d']
  ]);

  // 主题
  const types = ref([
    { label: 'presets', value: 0 },
    { label: 'custom', value: 1 }
  ]);

  // 选中主题
  const type = ref(0);

  // 标签输入
  const tags = ref(['标签一', '标签二', '标签三']);

  //
  const list = ['一', '二', '三', '四', '五'];

  // 标签输入
  const tags2 = ref(['标签一', '标签二', '标签三']);

  //
  const validator = (value) => {
    return new Promise((_resolve, reject) => {
      setTimeout(() => {
        reject(new Error(value + '不合法, 请重新输入'));
      }, 1000);
    });
  };
</script>

<script>
  export default {
    name: 'ExtensionTag'
  };
</script>

<style lang="less" scoped>
  .ele-cell + .ele-cell {
    margin-top: 16px;
  }
</style>
