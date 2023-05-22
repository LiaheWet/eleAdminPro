<template>
  <div class="ele-body ele-body-card">
    <print-this />
    <print-div />
    <print-html />
    <print-page />
    <print-advanced />
  </div>
</template>

<script setup>
  import PrintThis from './components/print-this.vue';
  import PrintDiv from './components/print-div.vue';
  import PrintHtml from './components/print-html.vue';
  import PrintPage from './components/print-page.vue';
  import PrintAdvanced from './components/print-advanced.vue';
</script>

<script>
  export default {
    name: 'ExtensionPrinter'
  };
</script>
