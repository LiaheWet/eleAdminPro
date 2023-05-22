<template>
  <div class="ele-body ele-body-card">
    <a-card title="省市区级联选择" :bordered="false">
      <div style="max-width: 280px">
        <regions-select
          v-model:value="city"
          placeholder="请选择省市区"
          class="ele-fluid"
        />
      </div>
    </a-card>
    <a-card title="省市级联选择" :bordered="false">
      <div style="max-width: 280px">
        <regions-select
          v-model:value="provinceCity"
          placeholder="请选择省市"
          type="provinceCity"
          class="ele-fluid"
        />
      </div>
    </a-card>
    <a-card title="省选择" :bordered="false">
      <div style="max-width: 280px">
        <regions-select
          v-model:value="province"
          placeholder="请选择省"
          type="province"
          class="ele-fluid"
        />
      </div>
    </a-card>
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import RegionsSelect from '@/components/RegionsSelect/index.vue';

  // 选中的省市区
  const city = ref([]);

  // 选中的省市
  const provinceCity = ref([]);

  // 选中的省
  const province = ref([]);
</script>

<script>
  export default {
    name: 'ExtensionRegions'
  };
</script>
