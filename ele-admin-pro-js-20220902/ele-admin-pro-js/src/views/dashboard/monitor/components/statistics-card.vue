<!-- 统计卡片 -->
<template>
  <a-row :gutter="16">
    <a-col :lg="6" :md="12" :sm="12" :xs="24">
      <a-card :bordered="false" class="monitor-count-card">
        <ele-tag color="blue" shape="circle" size="large">
          <eye-filled />
        </ele-tag>
        <h1 class="monitor-count-card-num">21.2 k</h1>
        <div class="monitor-count-card-text">总访问人数</div>
        <ele-avatar-list :data="visitUsers" size="small" :max="4" />
      </a-card>
    </a-col>
    <a-col :lg="6" :md="12" :sm="12" :xs="24">
      <a-card :bordered="false" class="monitor-count-card">
        <ele-tag color="orange" shape="circle" size="large">
          <fire-filled />
        </ele-tag>
        <h1 class="monitor-count-card-num">1.6 k</h1>
        <div class="monitor-count-card-text">点击量（近30天）</div>
        <div class="monitor-count-card-trend ele-text-success">
          <up-outlined />
          <span>110.5%</span>
        </div>
        <a-tooltip title="指标说明">
          <question-circle-outlined class="monitor-count-card-tips" />
        </a-tooltip>
      </a-card>
    </a-col>
    <a-col :lg="6" :md="12" :sm="12" :xs="24">
      <a-card :bordered="false" class="monitor-count-card">
        <ele-tag color="red" shape="circle" size="large">
          <flag-filled />
        </ele-tag>
        <h1 class="monitor-count-card-num">826.0</h1>
        <div class="monitor-count-card-text">到达量（近30天）</div>
        <div class="monitor-count-card-trend ele-text-danger">
          <down-outlined />
          <span>15.5%</span>
        </div>
      </a-card>
    </a-col>
    <a-col :lg="6" :md="12" :sm="12" :xs="24">
      <a-card :bordered="false" class="monitor-count-card">
        <ele-tag color="green" shape="circle" size="large">
          <thunderbolt-filled />
        </ele-tag>
        <h1 class="monitor-count-card-num">28.8 %</h1>
        <div class="monitor-count-card-text">转化率（近30天）</div>
        <div class="monitor-count-card-trend ele-text-success">
          <up-outlined />
          <span>65.8%</span>
        </div>
        <a-tooltip title="指标说明">
          <question-circle-outlined class="monitor-count-card-tips" />
        </a-tooltip>
      </a-card>
    </a-col>
  </a-row>
</template>

<script setup>
  import { ref } from 'vue';
  import {
    QuestionCircleOutlined,
    EyeFilled,
    FireFilled,
    FlagFilled,
    ThunderboltFilled,
    UpOutlined,
    DownOutlined
  } from '@ant-design/icons-vue';

  // 访问人数
  const visitUsers = ref([
    {
      key: 1,
      name: 'SunSmile',
      avatar:
        'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
    },
    {
      key: 2,
      name: '你的名字很好听',
      avatar:
        'https://cdn.eleadmin.com/20200609/b6a811873e704db49db994053a5019b2.jpg'
    },
    {
      key: 3,
      name: '全村人的希望',
      avatar:
        'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
    },
    {
      key: 4,
      name: 'Jasmine',
      avatar:
        'https://cdn.eleadmin.com/20200609/f6bc05af944a4f738b54128717952107.jpg'
    },
    {
      key: 5,
      name: '酷酷的大叔',
      avatar:
        'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
    },
    {
      key: 6,
      name: '管理员',
      avatar: 'https://cdn.eleadmin.com/20200610/avatar.jpg'
    }
  ]);
</script>

<style lang="less" scoped>
  .monitor-count-card {
    text-align: center;

    .monitor-count-card-num {
      margin-top: 6px;
      font-size: 32px;
    }

    .monitor-count-card-text {
      font-size: 12px;
      margin: 8px 0;
      opacity: 0.8;
    }

    .monitor-count-card-trend {
      font-weight: bold;
      line-height: 26px;

      & > .anticon {
        margin-right: 6px;
      }
    }

    .monitor-count-card-tips {
      position: absolute;
      top: 16px;
      right: 16px;
      cursor: pointer;
      opacity: 0.6;
    }
  }
</style>
