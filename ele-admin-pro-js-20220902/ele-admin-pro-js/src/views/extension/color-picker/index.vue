<template>
  <div class="ele-body ele-body-card">
    <a-card title="颜色选择器" :bordered="false">
      <a-space>
        <ele-color-picker
          size="large"
          :show-alpha="true"
          v-model:value="color"
          :predefine="predefineColors"
        />
        <ele-color-picker
          :show-alpha="true"
          v-model:value="color2"
          :predefine="predefineColors"
        />
        <ele-color-picker
          size="small"
          :show-alpha="true"
          v-model:value="color3"
          :predefine="predefineColors"
        />
      </a-space>
    </a-card>
  </div>
</template>

<script setup>
  import { ref } from 'vue';

  // 选中颜色
  const color = ref('rgba(255, 69, 0, 0.68)');

  // 选中颜色
  const color2 = ref('rgba(255, 69, 0, 0.68)');

  // 选中颜色
  const color3 = ref('rgba(255, 69, 0, 0.68)');

  // 预设颜色
  const predefineColors = ref([
    '#ff4500',
    '#ff8c00',
    '#ffd700',
    '#90ee90',
    '#00ced1',
    '#1e90ff',
    '#c71585',
    'rgba(255, 69, 0, 0.68)',
    'rgb(255, 120, 0)',
    'hsv(51, 100, 98)',
    'hsva(120, 40, 94, 0.5)',
    'hsl(181, 100%, 37%)',
    'hsla(209, 100%, 56%, 0.73)',
    '#c7158577'
  ]);
</script>

<script>
  export default {
    name: 'ExtensionColorPicker'
  };
</script>
