<!-- markdown 编辑器 -->
<template>
  <div ref="rootRef" class="ele-bytemd-wrap"></div>
</template>

<script setup>
  import { onMounted, ref, watch } from 'vue';
  import { Editor } from 'bytemd';
  import 'bytemd/dist/index.min.css';

  const props = defineProps({
    value: String,
    plugins: Array,
    sanitize: Function,
    mode: String,
    previewDebounce: Number,
    placeholder: String,
    editorConfig: Object,
    locale: Object,
    uploadImages: Function,
    overridePreview: Function,
    maxLength: Number,
    height: String,
    fullZIndex: {
      type: Number,
      default: 999
    }
  });

  const emit = defineEmits(['update:value', 'change']);

  const rootRef = ref(null);
  const editor = ref(null);

  onMounted(() => {
    editor.value = new Editor({
      target: rootRef.value,
      props
    });
    editor.value.$on('change', (e) => {
      emit('update:value', e.detail.value);
      emit('change', e.detail.value);
    });
  });

  watch(
    [
      () => props.value,
      () => props.plugins,
      () => props.sanitize,
      () => props.mode,
      () => props.previewDebounce,
      () => props.placeholder,
      () => props.editorConfig,
      () => props.locale,
      () => props.uploadImages,
      () => props.maxLength
    ],
    () => {
      const option = { ...props };
      for (let key in option) {
        if (typeof option[key] === 'undefined') {
          delete option[key];
        }
      }
      editor.value?.$set(option);
    }
  );
</script>

<style lang="less" scoped>
  // 修改编辑器高度
  .ele-bytemd-wrap :deep(.bytemd) {
    height: v-bind(height);

    // 修改全屏的 zIndex
    &.bytemd-fullscreen {
      z-index: v-bind(fullZIndex);
    }

    // 去掉默认的最大宽度限制
    .CodeMirror .CodeMirror-lines {
      max-width: 100%;
    }

    pre.CodeMirror-line,
    pre.CodeMirror-line-like {
      padding: 0 24px;
    }

    .markdown-body {
      max-width: 100%;
      padding: 16px 24px;
    }

    // 去掉 github 图标
    .bytemd-toolbar-right > .bytemd-toolbar-icon:last-child {
      display: none;
    }
  }
</style>
