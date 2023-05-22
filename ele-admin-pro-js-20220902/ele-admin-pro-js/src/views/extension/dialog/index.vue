<template>
  <div class="ele-body ele-body-card">
    <demo-modal />
    <multiple-modal />
  </div>
</template>

<script setup>
  import DemoModal from './components/demo-modal.vue';
  import MultipleModal from './components/multiple-modal.vue';
</script>

<script>
  export default {
    name: 'ExtensionDialog'
  };
</script>
