<template>
  <a-card title="基础演示" :bordered="false">
    <!-- 操作按钮 -->
    <a-space style="margin-bottom: 16px">
      <a-button
        type="primary"
        :disabled="!ready"
        class="ele-btn-icon"
        @click="play"
      >
        播放
      </a-button>
      <a-button
        type="primary"
        :disabled="!ready"
        class="ele-btn-icon"
        @click="pause"
      >
        暂停
      </a-button>
      <a-button
        type="primary"
        :disabled="!ready"
        class="ele-btn-icon"
        @click="replay"
      >
        重新播放
      </a-button>
      <a-button
        type="primary"
        :disabled="!ready"
        class="ele-btn-icon"
        @click="changeSrc"
      >
        切换视频源
      </a-button>
    </a-space>
    <!-- 播放器 -->
    <ele-xg-player :config="config" @player="onPlayer" />
  </a-card>
</template>

<script setup>
  import { ref, reactive } from 'vue';

  // 视频播放器配置
  const config = reactive({
    id: 'demoPlayer1',
    lang: 'zh-cn',
    fluid: true,
    // 视频地址
    url: 'https://s1.pstatp.com/cdn/expire-1-M/byted-player-videos/1.0.0/xgplayer-demo.mp4',
    // 封面
    poster:
      'https://imgcache.qq.com/open_proj/proj_qcloud_v2/gateway/solution/general-video/css/img/scene/1.png',
    // 开启倍速播放
    playbackRate: [0.5, 1, 1.5, 2],
    // 开启画中画
    pip: true
  });

  // 视频播放器是否实例化完成
  const ready = ref(false);

  // 视频播放器实例
  let player;

  /* 播放器渲染完成 */
  const onPlayer = (e) => {
    player = e;
    player.on('play', () => {
      ready.value = true;
    });
  };

  /* 播放 */
  const play = () => {
    if (player && player.paused) {
      player.play();
    }
  };

  /* 暂停 */
  const pause = () => {
    if (player) {
      player.pause();
    }
  };

  /* 重新播放 */
  const replay = () => {
    if (player) {
      player.replay();
    }
  };

  /* 切换视频源 */
  const changeSrc = () => {
    if (player) {
      player.src =
        'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_TheatricalTeaser_WeAreOverwatch_zhCN.mp4';
      if (player.paused) {
        player.play();
      }
    }
  };
</script>
