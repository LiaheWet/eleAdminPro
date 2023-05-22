<!-- markdown 解析 -->
<template>
  <!-- eslint-disable vue/no-v-html -->
  <div
    ref="rootRef"
    v-html="content"
    class="markdown-body"
    @click="handleClick"
  >
  </div>
</template>

<script setup>
  import { ref, watch, onMounted, onBeforeUnmount, nextTick } from 'vue';
  import { getProcessor } from 'bytemd';

  const props = defineProps({
    value: String,
    plugins: Array,
    sanitize: Function
  });

  const rootRef = ref(null);
  const content = ref(null);
  const cbs = ref([]);

  const on = () => {
    if (props.plugins && rootRef.value && content.value) {
      cbs.value = props.plugins.map(({ viewerEffect }) => {
        return (
          viewerEffect &&
          viewerEffect({
            markdownBody: rootRef.value,
            file: content.value
          })
        );
      });
    }
  };

  const off = () => {
    if (cbs.value) {
      cbs.value.forEach((cb) => cb && cb());
    }
  };

  const handleClick = (e) => {
    const $ = e.target;
    if ($.tagName !== 'A') {
      return;
    }

    const href = $.getAttribute('href');
    if (!href || !href.startsWith('#')) {
      return;
    }

    const dest = rootRef.value?.querySelector('#user-content-' + href.slice(1));
    if (dest) {
      dest.scrollIntoView();
    }
  };

  watch(
    [() => props.value, () => props.plugins, () => props.sanitize],
    () => {
      try {
        content.value = getProcessor({
          plugins: props.plugins,
          sanitize: props.sanitize
        }).processSync(props.value);
      } catch (e) {
        console.error(e);
      }
      off();
      nextTick(() => {
        on();
      });
    },
    {
      immediate: true
    }
  );

  onMounted(() => {
    on();
  });

  onBeforeUnmount(() => {
    off();
  });
</script>
