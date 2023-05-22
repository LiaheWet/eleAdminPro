<template>
  <div class="ele-body ele-body-card">
    <excel-export />
    <excel-import />
  </div>
</template>

<script setup>
  import ExcelExport from './components/excel-export.vue';
  import ExcelImport from './components/excel-import.vue';
</script>

<script>
  export default {
    name: 'ExtensionExcel'
  };
</script>
