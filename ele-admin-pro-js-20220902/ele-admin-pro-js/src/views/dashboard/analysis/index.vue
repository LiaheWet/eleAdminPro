<template>
  <div class="ele-body ele-body-card">
    <statistics-card />
    <sale-card />
    <a-row :gutter="16">
      <a-col :lg="16" :md="14" :sm="24" :xs="24">
        <visit-hour />
      </a-col>
      <a-col :lg="8" :md="10" :sm="24" :xs="24">
        <hot-search />
      </a-col>
    </a-row>
  </div>
</template>

<script setup>
  import StatisticsCard from './components/statistics-card.vue';
  import SaleCard from './components/sale-card.vue';
  import VisitHour from './components/visit-hour.vue';
  import HotSearch from './components/hot-search.vue';
</script>

<script>
  export default {
    name: 'DashboardAnalysis'
  };
</script>
