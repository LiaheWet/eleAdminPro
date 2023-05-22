<template>
  <div class="ele-body">
    <a-card title="条形码" :bordered="false">
      <div ref="printRef" class="demo-barcode-images ele-bg-white">
        <ele-bar-code :value="text" :tag="tag" :options="options" />
      </div>
      <a-form
        style="max-width: 340px"
        :label-col="{ flex: '88px' }"
        :wrapper-col="{ flex: '1' }"
      >
        <a-form-item label="条码类型" style="flex-wrap: nowrap">
          <a-radio-group :value="options.format" @update:value="updateFormat">
            <a-radio value="CODE128">CODE128</a-radio>
            <a-radio value="EAN13">EAN13</a-radio>
          </a-radio-group>
        </a-form-item>
        <a-form-item label="渲染方式" style="flex-wrap: nowrap">
          <a-radio-group v-model:value="tag">
            <a-radio value="svg">svg</a-radio>
            <a-radio value="img">img</a-radio>
            <a-radio value="canvas">canvas</a-radio>
          </a-radio-group>
        </a-form-item>
        <a-form-item label="条码文本" style="flex-wrap: nowrap">
          <a-select v-if="options.format === 'EAN13'" v-model:value="text">
            <a-select-option value="1234567890128">
              1234567890128
            </a-select-option>
            <a-select-option value="6971872201359">
              6971872201359
            </a-select-option>
            <a-select-option value="6954531770199">
              6954531770199
            </a-select-option>
            <a-select-option value="6923644240318">
              6923644240318
            </a-select-option>
          </a-select>
          <a-input v-else v-model:value="text" :maxlength="20" />
        </a-form-item>
        <a-form-item label="高度" style="flex-wrap: nowrap">
          <a-slider
            v-model:value="options.height"
            :min="40"
            :max="160"
            :step="10"
          />
        </a-form-item>
        <a-form-item label="宽度" style="flex-wrap: nowrap">
          <a-slider v-model:value="options.width" :min="1" :max="6" />
        </a-form-item>
        <a-form-item label="间距" style="flex-wrap: nowrap">
          <a-slider v-model:value="options.margin" :min="0" :max="40" />
        </a-form-item>
        <a-form-item label="显示文本" style="flex-wrap: nowrap">
          <a-switch v-model:checked="options.displayValue" size="small" />
        </a-form-item>
        <a-form-item
          v-if="options.displayValue"
          label="文本大小"
          style="flex-wrap: nowrap"
        >
          <a-slider
            v-model:value="options.fontSize"
            :min="12"
            :max="36"
            :step="2"
          />
        </a-form-item>
        <a-form-item
          v-if="options.displayValue && options.format === 'CODE128'"
          label="文本位置"
          style="flex-wrap: nowrap"
        >
          <a-radio-group v-model:value="options.textPosition">
            <a-radio value="bottom">bottom</a-radio>
            <a-radio value="top">top</a-radio>
          </a-radio-group>
        </a-form-item>
        <a-form-item style="flex-wrap: nowrap">
          <div style="padding-left: 88px">
            <a-button type="primary" @click="print">打印</a-button>
          </div>
        </a-form-item>
      </a-form>
    </a-card>
  </div>
</template>

<script setup>
  import { ref, reactive, nextTick } from 'vue';
  import { printElement } from 'ele-admin-pro/es';

  const printRef = ref(null);

  const text = ref('1234567890');

  const tag = ref('svg');

  const options = reactive({
    height: 60,
    width: 2,
    margin: 2,
    displayValue: true,
    textPosition: 'bottom',
    fontSize: 14,
    format: 'CODE128'
  });

  const updateFormat = (value) => {
    if (value === 'EAN13') {
      text.value = '1234567890128';
      nextTick(() => {
        options.format = value;
      });
    } else {
      options.format = value;
    }
  };

  const print = () => {
    printElement(printRef.value);
  };
</script>

<script>
  export default {
    name: 'ExtensionBarCode'
  };
</script>

<style lang="less" scoped>
  .demo-barcode-images {
    padding-bottom: 16px;
    margin-bottom: 4px;
    position: sticky;
    top: 0;
    overflow: auto;
    z-index: 1;
    line-height: 0;
  }
</style>
