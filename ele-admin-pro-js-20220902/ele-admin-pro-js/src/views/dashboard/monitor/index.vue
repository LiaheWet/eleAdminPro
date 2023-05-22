<template>
  <div class="ele-body ele-body-card">
    <statistics-card />
    <a-row :gutter="16">
      <a-col :lg="18" :md="24" :sm="24" :xs="24">
        <map-card />
      </a-col>
      <a-col :lg="6" :md="24" :sm="24" :xs="24">
        <a-row :gutter="16">
          <a-col :lg="24" :md="12" :sm="12" :xs="24">
            <online-num />
          </a-col>
          <a-col :lg="24" :md="12" :sm="12" :xs="24">
            <browser-card />
          </a-col>
        </a-row>
      </a-col>
    </a-row>
    <a-row :gutter="16">
      <a-col :xl="12" :lg="24" :md="24" :sm="24" :xs="24">
        <user-rate />
      </a-col>
      <a-col :xl="6" :lg="12" :md="12" :sm="12" :xs="24">
        <user-satisfaction />
      </a-col>
      <a-col :xl="6" :lg="12" :md="12" :sm="12" :xs="24">
        <user-liveness />
      </a-col>
    </a-row>
  </div>
</template>

<script setup>
  import StatisticsCard from './components/statistics-card.vue';
  import MapCard from './components/map-card.vue';
  import OnlineNum from './components/online-num.vue';
  import BrowserCard from './components/browser-card.vue';
  import UserRate from './components/user-rate.vue';
  import UserSatisfaction from './components/user-satisfaction.vue';
  import UserLiveness from './components/user-liveness.vue';
</script>

<script>
  export default {
    name: 'DashboardMonitor'
  };
</script>
