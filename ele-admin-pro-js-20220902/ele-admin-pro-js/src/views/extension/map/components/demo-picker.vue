<template>
  <a-card title="地图位置选择器" :bordered="false">
    <a-space>
      <div style="width: 140px">
        <a-select v-model:value="searchType" class="ele-fluid">
          <a-select-option :value="0">POI检索模式</a-select-option>
          <a-select-option :value="1">关键字检索模式</a-select-option>
        </a-select>
      </div>
      <a-button class="ele-btn-icon" @click="openMapPicker">
        打开地图位置选择器
      </a-button>
    </a-space>
    <div style="margin-top: 12px">选择位置: {{ result.location }}</div>
    <div style="margin-top: 12px">详细地址: {{ result.address }}</div>
    <div style="margin-top: 12px">经 纬 度 : {{ result.lngAndLat }}</div>
  </a-card>
  <!-- 地图位置选择弹窗 -->
  <ele-map-picker
    :need-city="true"
    :dark-mode="darkMode"
    v-model:visible="visible"
    :search-type="searchType"
    @done="onChoose"
  />
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { useThemeStore } from '@/store/modules/theme';
  import { storeToRefs } from 'pinia';

  const themeStore = useThemeStore();
  const { darkMode } = storeToRefs(themeStore);

  // 是否显示地图选择弹窗
  const visible = ref(false);

  // 地点检索类型
  const searchType = ref(0);

  // 选择结果
  const result = reactive({
    location: '',
    address: '',
    lngAndLat: ''
  });

  /* 打开位置选择 */
  const openMapPicker = () => {
    visible.value = true;
  };

  /* 地图选择后回调 */
  const onChoose = (location) => {
    console.log(location);
    result.location = `${location.city?.province}/${location.city?.city}/${location.city?.district}`;
    result.address = `${location.name} ${location.address}`;
    result.lngAndLat = `${location.lng},${location.lat}`;
    visible.value = false;
  };
</script>
