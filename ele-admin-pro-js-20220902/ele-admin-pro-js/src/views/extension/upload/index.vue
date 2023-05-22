<template>
  <div class="ele-body ele-body-card">
    <demo-basic />
    <demo-advanced />
    <demo-multiple />
  </div>
</template>

<script setup>
  import DemoBasic from './components/demo-basic.vue';
  import DemoAdvanced from './components/demo-advanced.vue';
  import DemoMultiple from './components/demo-multiple.vue';
</script>

<script>
  export default {
    name: 'ExtensionUpload'
  };
</script>
