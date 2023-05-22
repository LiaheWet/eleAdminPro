<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <edit-form />
    </a-card>
  </div>
</template>

<script setup>
  import EditForm from '../components/edit-form.vue';
</script>

<script>
  export default {
    name: 'ListBasicAdd'
  };
</script>
