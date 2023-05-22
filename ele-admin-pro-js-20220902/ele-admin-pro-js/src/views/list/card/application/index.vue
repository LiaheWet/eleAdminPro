<template>
  <div class="ele-body">
    <a-card :bordered="false" :body-style="{ padding: '44px 16px' }">
      <div style="max-width: 500px; margin: 0 auto">
        <a-input-search
          size="large"
          enter-button="搜索"
          placeholder="请输入内容"
          v-model:value="keyword"
        />
      </div>
    </a-card>
    <a-row :gutter="16">
      <a-col
        v-for="(item, index) in data"
        :key="index"
        :xl="6"
        :lg="8"
        :md="12"
        :sm="12"
        :xs="24"
      >
        <a-card :bordered="false" hoverable style="margin-top: 16px">
          <div class="ele-cell" style="margin-bottom: 16px">
            <a-avatar size="large" :src="item.cover" />
            <h6 class="ele-cell-content ele-elip">{{ item.title }}</h6>
          </div>
          <div class="ele-elip" style="margin-bottom: 6px">
            网址: {{ item.url }}
          </div>
          <div class="ele-elip">最后更新时间: {{ item.time }}</div>
          <template #actions>
            <a-tooltip title="下载">
              <download-outlined />
            </a-tooltip>
            <a-tooltip title="编辑">
              <edit-outlined />
            </a-tooltip>
            <a-tooltip title="分享">
              <share-alt-outlined />
            </a-tooltip>
            <a-dropdown placement="bottom">
              <ellipsis-outlined />
              <template #overlay>
                <a-menu>
                  <a-menu-item>1st menu item</a-menu-item>
                  <a-menu-item>2nd menu item</a-menu-item>
                  <a-menu-item>3rd menu item</a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </template>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import {
    DownloadOutlined,
    EditOutlined,
    ShareAltOutlined,
    EllipsisOutlined
  } from '@ant-design/icons-vue';

  const data = ref([]);

  data.value = [
    {
      title: 'ElementUI',
      url: 'https://element.eleme.cn',
      time: '2 小时前',
      cover:
        'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
    },
    {
      title: 'Vue.js',
      url: 'https://cn.vuejs.org',
      time: '4 小时前',
      cover:
        'https://cdn.eleadmin.com/20200609/b6a811873e704db49db994053a5019b2.jpg'
    },
    {
      title: 'Vuex',
      url: 'https://vuex.vuejs.org',
      time: '12 小时前',
      cover:
        'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
    },
    {
      title: 'Vue Router',
      url: 'https://vuex.vuejs.org',
      time: '14 小时前',
      cover:
        'https://cdn.eleadmin.com/20200609/f6bc05af944a4f738b54128717952107.jpg'
    },
    {
      title: 'Sass',
      url: 'https://www.sass.hk',
      time: '10 小时前',
      cover:
        'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
    },
    {
      title: 'Axios',
      url: 'http://www.axios-js.com',
      time: '16 小时前',
      cover:
        'https://cdn.eleadmin.com/20200609/faa0202700ee455b90fe77d8bef98bc0.jpg'
    },
    {
      title: 'Webpack',
      url: 'https://www.webpackjs.com',
      time: '6 小时前',
      cover:
        'https://cdn.eleadmin.com/20200609/d3519518b00d42d3936b2ab5ce3a4cc3.jpg'
    },
    {
      title: 'Node.js',
      url: 'http://nodejs.cn',
      time: '8 小时前',
      cover:
        'https://cdn.eleadmin.com/20200609/fe9196dd091e438fba115205c1003ee7.jpg'
    }
  ];

  const keyword = ref('');
</script>

<script>
  export default {
    name: 'ListCardApplication'
  };
</script>
