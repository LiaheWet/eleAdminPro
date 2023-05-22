<template>
  <div class="ele-body ele-body-card">
    <lazy-tree-table />
    <default-sorter />
    <reset-sorter />
    <multiple-sorter />
    <merge-cell />
  </div>
</template>

<script setup>
  import LazyTreeTable from './components/lazy-tree-table.vue';
  import DefaultSorter from './components/default-sorter.vue';
  import ResetSorter from './components/reset-sorter.vue';
  import MultipleSorter from './components/multiple-sorter.vue';
  import MergeCell from './components/merge-cell.vue';
</script>

<script>
  export default {
    name: 'ExampleTable'
  };
</script>
