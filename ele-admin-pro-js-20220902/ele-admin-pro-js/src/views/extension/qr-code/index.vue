<template>
  <div class="ele-body">
    <a-card title="二维码" :bordered="false">
      <div ref="printRef" class="demo-qrcode-images ele-bg-white">
        <div class="demo-qrcode-image-item">
          <div class="demo-qr-code-title">canvas 渲染</div>
          <ele-qr-code
            :value="text"
            :size="size"
            :level="level"
            :margin="margin"
            :image-settings="image"
          />
        </div>
        <div class="demo-qrcode-image-item">
          <div class="demo-qr-code-title">svg 渲染</div>
          <ele-qr-code-svg
            :value="text"
            :size="size"
            :level="level"
            :margin="margin"
            :image-settings="image"
          />
        </div>
      </div>
      <a-form
        style="max-width: 340px"
        :label-col="{ flex: '88px' }"
        :wrapper-col="{ flex: '1' }"
      >
        <a-form-item label="二维码内容" style="flex-wrap: nowrap">
          <a-input v-model:value="text" :maxlength="150" />
        </a-form-item>
        <a-form-item label="容错等级" style="flex-wrap: nowrap">
          <a-select v-model:value="level">
            <a-select-option value="L">L</a-select-option>
            <a-select-option value="M">M</a-select-option>
            <a-select-option value="Q">Q</a-select-option>
            <a-select-option value="H">H</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="尺寸" style="flex-wrap: nowrap">
          <a-slider v-model:value="size" :min="80" :max="280" :step="40" />
        </a-form-item>
        <a-form-item label="间距" style="flex-wrap: nowrap">
          <a-slider v-model:value="margin" :min="0" :max="20" />
        </a-form-item>
        <a-form-item label="自定义图片" style="flex-wrap: nowrap">
          <a-switch
            v-model:checked="showImage"
            size="small"
            @change="onShowImageChange"
          />
        </a-form-item>
        <template v-if="showImage">
          <a-form-item label="图片地址" style="flex-wrap: nowrap">
            <a-input v-model:value="image.src" :maxlength="400" />
          </a-form-item>
          <a-form-item label="图片宽高" style="flex-wrap: nowrap">
            <div class="ele-cell">
              <div style="width: 80px; margin-right: 20px">
                <a-input-number
                  v-model:value="image.width"
                  size="small"
                  :min="0"
                  :max="size"
                  class="ele-fluid"
                />
              </div>
              <div style="width: 80px">
                <a-input-number
                  v-model:value="image.height"
                  size="small"
                  :min="0"
                  :max="size"
                  class="ele-fluid"
                />
              </div>
            </div>
          </a-form-item>
          <a-form-item label="位置居中" style="flex-wrap: nowrap">
            <div class="ele-cell">
              <a-switch
                v-model:checked="center"
                size="small"
                @change="onCenterChange"
              />
              <template v-if="!center">
                <div style="padding: 0 10px">x</div>
                <div style="width: 60px">
                  <a-input-number
                    v-model:value="image.x"
                    size="small"
                    :min="0"
                    :max="size"
                    class="ele-fluid"
                  />
                </div>
                <div style="padding: 0 10px">y</div>
                <div style="width: 60px">
                  <a-input-number
                    v-model:value="image.y"
                    size="small"
                    :min="0"
                    :max="size"
                    class="ele-fluid"
                  />
                </div>
              </template>
            </div>
          </a-form-item>
          <a-form-item label="背景擦除" style="flex-wrap: nowrap">
            <a-switch v-model:checked="image.excavate" size="small" />
          </a-form-item>
          <a-form-item style="flex-wrap: nowrap">
            <div style="padding-left: 88px">
              <a-button type="primary" @click="print">打印</a-button>
            </div>
          </a-form-item>
        </template>
      </a-form>
    </a-card>
  </div>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { printElement } from 'ele-admin-pro/es';

  const IMAGE_SRC = 'https://cdn.eleadmin.com/20200610/logo-radius.png';

  const text = ref('https://eleadmin.com');

  const level = ref('L');

  const size = ref(120);

  const margin = ref(0);

  const showImage = ref(true);

  const image = reactive({
    src: IMAGE_SRC,
    width: 28,
    height: 28,
    x: undefined,
    y: undefined,
    excavate: false
  });

  const center = ref(true);

  const printRef = ref(null);

  const onShowImageChange = (checked) => {
    if (checked) {
      image.src = IMAGE_SRC;
    } else {
      image.src = undefined;
    }
  };

  const onCenterChange = (checked) => {
    if (checked) {
      image.x = undefined;
      image.y = undefined;
    } else {
      image.x = 0;
      image.y = 0;
    }
  };

  const print = () => {
    printElement(printRef.value);
  };
</script>

<script>
  export default {
    name: 'ExtensionQrCode'
  };
</script>

<style lang="less" scoped>
  .demo-qrcode-images {
    display: flex;
    padding-bottom: 16px;
    margin-bottom: 4px;
    position: sticky;
    top: 0;
    overflow: auto;
    z-index: 1;

    .demo-qrcode-image-item {
      padding: 0 20px;

      .demo-qr-code-title {
        margin-bottom: 6px;
      }
    }
  }

  @media screen and (max-width: 768px) {
    .demo-qrcode-images .demo-qrcode-image-item {
      padding: 0 10px;
    }
  }
</style>
