<template>
  <div class="ele-body ele-body-card">
    <a-row :gutter="16">
      <a-col :lg="12" :md="24" :sm="24" :xs="24">
        <a-card title="基础演示" :bordered="false">
          <!-- 操作按钮 -->
          <a-space style="margin-bottom: 16px">
            <a-button
              type="primary"
              :disabled="!ready1"
              class="ele-btn-icon"
              @click="play"
            >
              播放
            </a-button>
            <a-button
              type="primary"
              :disabled="!ready1"
              class="ele-btn-icon"
              @click="pause"
            >
              暂停
            </a-button>
            <a-button
              type="primary"
              :disabled="!ready1"
              class="ele-btn-icon"
              @click="replay"
            >
              重新播放
            </a-button>
            <a-button
              type="primary"
              :disabled="!ready1"
              class="ele-btn-icon"
              @click="changeSrc"
            >
              切换视频源
            </a-button>
          </a-space>
          <!-- 播放器 -->
          <ele-xg-player :config="config1" @player="onPlayer1" />
        </a-card>
      </a-col>
      <a-col :lg="12" :md="24" :sm="24" :xs="24">
        <a-card title="显示弹幕" :bordered="false">
          <!-- 操作按钮 -->
          <a-space style="margin-bottom: 16px">
            <a-input
              style="width: 160px"
              v-model:value="text"
              placeholder="请输入弹幕内容"
              :disabled="!ready2"
            />
            <a-button
              type="primary"
              :disabled="!ready2"
              class="ele-btn-icon"
              @click="shoot"
            >
              发射
            </a-button>
          </a-space>
          <!-- 播放器 -->
          <ele-xg-player :config="config2" @player="onPlayer2" />
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { message } from 'ant-design-vue/es';

  // 视频播放器一配置
  const config1 = reactive({
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

  // 视频播放器一是否实例化完成
  const ready1 = ref(false);

  // 视频播放器二配置
  const config2 = reactive({
    id: 'demoPlayer2',
    lang: 'zh-cn',
    fluid: true,
    url: 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_TheatricalTeaser_WeAreOverwatch_zhCN.mp4',
    poster:
      'https://imgcache.qq.com/open_proj/proj_qcloud_v2/gateway/solution/general-video/css/img/scene/1.png',
    danmu: {
      comments: [
        {
          id: '1',
          start: 0,
          txt: '空降',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '2',
          start: 1500,
          txt: '前方高能',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '3',
          start: 3500,
          txt: '弹幕护体',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '4',
          start: 4500,
          txt: '弹幕护体',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '5',
          start: 6000,
          txt: '前方高能',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '6',
          start: 8500,
          txt: '弹幕护体',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '7',
          start: 10000,
          txt: '666666666',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '8',
          start: 12500,
          txt: '前方高能',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '9',
          start: 15500,
          txt: '666666666',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '10',
          start: 16500,
          txt: '666666666',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '11',
          start: 18000,
          txt: '关弹幕，保智商',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '12',
          start: 20500,
          txt: '关弹幕，保智商',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '13',
          start: 22000,
          txt: '666666666',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '14',
          start: 25500,
          txt: '666666666',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        },
        {
          id: '15',
          start: 26000,
          txt: '前方高能',
          duration: 15000,
          color: true,
          style: {
            color: '#ffcd08',
            fontSize: '20px'
          }
        }
      ]
    }
  });

  // 视频播放器二是否实例化完成
  const ready2 = ref(false);

  // 弹幕输入内容
  const text = ref('');

  // 视频播放器一实例
  let player1;

  // 视频播放器二实例
  let player2;

  /* 播放器一渲染完成 */
  const onPlayer1 = (player) => {
    player1 = player;
    player1.on('play', () => {
      ready1.value = true;
    });
  };

  /* 播放 */
  const play = () => {
    if (player1 && player1.paused) {
      player1.play();
    }
  };

  /* 暂停 */
  const pause = () => {
    if (player1) {
      player1.pause();
    }
  };

  /* 重新播放 */
  const replay = () => {
    if (player1) {
      player1.replay();
    }
  };

  /* 切换视频源 */
  const changeSrc = () => {
    if (player1) {
      player1.src =
        'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_TheatricalTeaser_WeAreOverwatch_zhCN.mp4';
      if (player1.paused) {
        player1.play();
      }
    }
  };

  /* 播放器二渲染完成 */
  const onPlayer2 = (player) => {
    player2 = player;
    player2.on('play', () => {
      ready2.value = true;
    });
  };

  /* 发射弹幕 */
  const shoot = () => {
    if (!player2) {
      return;
    }
    if (!text.value) {
      message.error('请输入弹幕内容');
      return;
    }
    player2.danmu.sendComment({
      id: new Date().getTime(),
      duration: 15000,
      color: true,
      start: player2.currentTime * 1000,
      txt: text.value,
      style: {
        color: '#fa1f41',
        fontSize: '20px',
        border: 'solid 1px #fa1f41'
      }
    });
    text.value = '';
  };
</script>

<script>
  export default {
    name: 'ExtensionPlayer'
  };
</script>
