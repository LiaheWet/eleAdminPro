<template>
  <a-card title="轨迹展示及轨迹回放" :bordered="false">
    <div
      ref="trackMapRef"
      style="height: 360px; max-width: 800px; margin-bottom: 16px"
    >
    </div>
    <a-space>
      <a-button type="primary" class="ele-btn-icon" @click="startTrackAnim">
        开始移动
      </a-button>
      <a-button type="primary" class="ele-btn-icon" @click="pauseTrackAnim">
        暂停移动
      </a-button>
      <a-button type="primary" class="ele-btn-icon" @click="resumeTrackAnim">
        继续移动
      </a-button>
    </a-space>
  </a-card>
</template>

<script setup>
  import { ref, watch, onMounted, onUnmounted } from 'vue';
  import AMapLoader from '@amap/amap-jsapi-loader';
  import { useThemeStore } from '@/store/modules/theme';
  import { storeToRefs } from 'pinia';
  import { MAP_KEY } from '@/config/setting';

  const themeStore = useThemeStore();
  const { darkMode } = storeToRefs(themeStore);

  //
  const trackMapRef = ref(null);

  // 小车轨迹地图的实例
  let mapInsTrack;

  // 小车的 marker
  let carMarker;

  // 轨迹路线
  const lineData = [
    [116.478935, 39.997761],
    [116.478939, 39.997825],
    [116.478912, 39.998549],
    [116.478912, 39.998549],
    [116.478998, 39.998555],
    [116.478998, 39.998555],
    [116.479282, 39.99856],
    [116.479658, 39.998528],
    [116.480151, 39.998453],
    [116.480784, 39.998302],
    [116.480784, 39.998302],
    [116.481149, 39.998184],
    [116.481573, 39.997997],
    [116.481863, 39.997846],
    [116.482072, 39.997718],
    [116.482362, 39.997718],
    [116.483633, 39.998935],
    [116.48367, 39.998968],
    [116.484648, 39.999861]
  ];

  /* 渲染轨迹回放地图 */
  const renderTrackMap = () => {
    AMapLoader.load({
      key: MAP_KEY,
      version: '2.0',
      plugins: ['AMap.MoveAnimation', 'AMap.Marker', 'AMap.Polyline']
    })
      .then((AMap) => {
        // 渲染地图
        const option = {
          zoom: 17,
          center: [116.478935, 39.997761],

          mapStyle: darkMode.value ? 'amap://styles/dark' : void 0
        };
        mapInsTrack = new AMap.Map(trackMapRef.value, option);
        // 创建小车 marker
        carMarker = new AMap.Marker({
          map: mapInsTrack,
          position: [116.478935, 39.997761],
          icon: 'https://a.amap.com/jsapi_demos/static/demo-center-v2/car.png',
          offset: new AMap.Pixel(-13, -26)
        });
        // 绘制轨迹
        new AMap.Polyline({
          map: mapInsTrack,
          path: lineData,
          showDir: true,
          strokeColor: '#2288FF', // 线颜色
          strokeOpacity: 1, // 线透明度
          strokeWeight: 6 // 线宽
          //strokeStyle: 'solid'  // 线样式
        });
        // 通过的轨迹
        const passedPolyline = new AMap.Polyline({
          map: mapInsTrack,
          showDir: true,
          strokeColor: '#44BB55', // 线颜色
          strokeOpacity: 1, // 线透明度
          strokeWeight: 6 // 线宽
        });
        // 小车移动回调
        carMarker.on('moving', (e) => {
          passedPolyline.setPath(e.passedPath);
        });
        // 地图自适应
        mapInsTrack.setFitView();
      })
      .catch((e) => {
        console.error(e);
      });
  };

  /* 开始轨迹回放动画 */
  const startTrackAnim = () => {
    if (carMarker) {
      carMarker.stopMove();
      carMarker.moveAlong(lineData, {
        duration: 200,
        autoRotation: true
      });
    }
  };

  /* 暂停轨迹回放动画 */
  const pauseTrackAnim = () => {
    if (carMarker) {
      carMarker.pauseMove();
    }
  };

  /* 继续开始轨迹回放动画 */
  const resumeTrackAnim = () => {
    if (carMarker) {
      carMarker.resumeMove();
    }
  };

  /* 渲染地图 */
  onMounted(() => {
    renderTrackMap();
  });

  /* 销毁地图 */
  onUnmounted(() => {
    if (mapInsTrack) {
      mapInsTrack.destroy();
    }
  });

  /* 同步框架暗黑模式切换 */
  watch(darkMode, () => {
    if (mapInsTrack) {
      if (darkMode.value) {
        mapInsTrack.setMapStyle('amap://styles/dark');
      } else {
        mapInsTrack.setMapStyle('amap://styles/normal');
      }
    }
  });
</script>
