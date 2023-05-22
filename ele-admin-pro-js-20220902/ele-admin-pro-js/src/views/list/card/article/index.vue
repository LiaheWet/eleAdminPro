<template>
  <div class="ele-body">
    <a-card
      :bordered="false"
      :body-style="{ padding: '44px 16px' }"
      style="margin-bottom: 16px"
    >
      <div style="max-width: 500px; margin: 0 auto">
        <a-input-search
          size="large"
          enter-button="搜索"
          placeholder="请输入内容"
          v-model:value="keyword"
        />
      </div>
    </a-card>
    <a-card :bordered="false" :body-style="{ padding: '16px 8px' }">
      <a-image-preview-group>
        <a-list
          :data-source="data"
          :loading="loading && page === 1"
          item-layout="vertical"
          size="large"
        >
          <template #renderItem="{ item, index }">
            <a-list-item :key="index">
              <a-list-item-meta :title="item.title">
                <template #description>
                  <a-tag v-for="(tag, i) in item.tags" :key="i">
                    {{ tag }}
                  </a-tag>
                </template>
              </a-list-item-meta>
              <div class="ele-text-heading">
                {{ item.content }}
              </div>
              <div class="ele-cell" style="margin-top: 16px">
                <a-avatar :src="item.avatar" size="small" />
                <div class="ele-cell-content">
                  {{ item.user }} 发表于 {{ item.time }}
                </div>
              </div>
              <template #extra>
                <div class="list-image-wrap">
                  <a-image width="100%" :src="item.cover" />
                </div>
              </template>
              <template #actions>
                <span>
                  <like-outlined />
                  <span><s></s>{{ item.likes }}</span>
                </span>
                <span>
                  <star-outlined />
                  <span><s></s>{{ item.favorites }}</span>
                </span>
                <span>
                  <message-outlined />
                  <span><s></s>{{ item.comments }}</span>
                </span>
              </template>
            </a-list-item>
          </template>
          <template #loadMore>
            <div class="ele-text-center" style="margin-top: 16px">
              <a-button v-if="page !== 1" :loading="loading" @click="query">
                {{ loading ? '加载中..' : '加载更多' }}
              </a-button>
            </div>
          </template>
        </a-list>
      </a-image-preview-group>
    </a-card>
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import {
    LikeOutlined,
    StarOutlined,
    MessageOutlined
  } from '@ant-design/icons-vue';

  const data = ref([]);

  const loading = ref(false);

  const page = ref(2);

  const query = () => {
    loading.value = true;
    setTimeout(() => {
      loading.value = false;
      page.value++;
      data.value = data.value.concat(data.value.slice(0, 3));
    }, 1000);
  };

  data.value = [
    {
      title: 'ElementUI',
      content:
        'Element，一套为开发者、设计师和产品经理准备的基于 Vue 2.0 的组件库，提供了配套设计资源，帮助你的网站快速成型。',
      time: '2 小时前',
      cover:
        'https://cdn.eleadmin.com/20200610/RZ8FQmZfHkcffMlTBCJllBFjEhEsObVo.jpg',
      tags: ['EleAdminPro', 'UI框架', '设计语言'],
      user: 'SunSmile',
      avatar:
        'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg',
      favorites: 104,
      likes: 189,
      comments: 15
    },
    {
      title: 'Vue.js',
      content:
        'Vue 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。',
      time: '4 小时前',
      cover:
        'https://cdn.eleadmin.com/20200610/WLXm7gp1EbLDtvVQgkeQeyq5OtDm00Jd.jpg',
      tags: ['EleAdminPro', 'UI框架', '设计语言'],
      user: '你的名字很好听',
      avatar:
        'https://cdn.eleadmin.com/20200609/b6a811873e704db49db994053a5019b2.jpg',
      favorites: 104,
      likes: 189,
      comments: 15
    },
    {
      title: 'Vuex',
      content:
        'Vuex 是一个专为 Vue.js 应用程序开发的状态管理模式。它采用集中式存储管理应用的所有组件的状态，并以相应的规则保证状态以一种可预测的方式发生变化。',
      time: '12 小时前',
      cover:
        'https://cdn.eleadmin.com/20200610/4Z0QR2L0J1XStxBh99jVJ8qLfsGsOgjU.jpg',
      tags: ['EleAdminPro', 'UI框架', '设计语言'],
      user: '全村人的希望',
      avatar:
        'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg',
      favorites: 104,
      likes: 189,
      comments: 15
    },
    {
      title: 'Vue Router',
      content:
        'Vue Router 是 Vue.js 官方的路由管理器。它和 Vue.js 的核心深度集成，让构建单页面应用变得易如反掌。',
      time: '14 小时前',
      cover:
        'https://cdn.eleadmin.com/20200610/ttkIjNPlVDuv4lUTvRX8GIlM2QqSe0jg.jpg',
      tags: ['EleAdminPro', 'UI框架', '设计语言'],
      user: 'Jasmine',
      avatar:
        'https://cdn.eleadmin.com/20200609/f6bc05af944a4f738b54128717952107.jpg',
      favorites: 104,
      likes: 189,
      comments: 15
    },
    {
      title: 'Sass',
      content: 'Sass 是世界上最成熟、稳定、强大的专业级 CSS 扩展语言。',
      time: '10 小时前',
      cover:
        'https://cdn.eleadmin.com/20200610/fAenQ8nvRjL7x0i0jEfuDBZHvJfHf3v6.jpg',
      tags: ['EleAdminPro', 'UI框架', '设计语言'],
      user: '酷酷的大叔',
      avatar:
        'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg',
      favorites: 104,
      likes: 189,
      comments: 15
    },
    {
      title: 'Axios',
      content:
        'Axios 是一个基于 promise 的 HTTP 库，可以用在浏览器和 node.js 中。',
      time: '16 小时前',
      cover:
        'https://cdn.eleadmin.com/20200610/LrCTN2j94lo9N7wEql7cBr1Ux4rHMvmZ.jpg',
      tags: ['EleAdminPro', 'UI框架', '设计语言'],
      user: 'SunSmile',
      avatar:
        'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg',
      favorites: 104,
      likes: 189,
      comments: 15
    },
    {
      title: 'Webpack',
      content:
        'webpack 是一个模块打包器。webpack 的主要目标是将 JavaScript 文件打包在一起，打包后的文件用于在浏览器中使用。',
      time: '6 小时前',
      cover:
        'https://cdn.eleadmin.com/20200610/yeKvhT20lMU0f1T3Y743UlGEOLLnZSnp.jpg',
      tags: ['EleAdminPro', 'UI框架', '设计语言'],
      user: '全村人的希望',
      avatar:
        'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg',
      favorites: 104,
      likes: 189,
      comments: 15
    },
    {
      title: 'Node.js',
      content:
        'Node.js 是一个基于 Chrome V8 引擎的 JavaScript 运行环境。Node.js 使用了一个事件驱动、非阻塞式 I/O 的模型，使其轻量又高效。',
      time: '8 小时前',
      cover:
        'https://cdn.eleadmin.com/20200610/CyrCNmTJfv7D6GFAg39bjT3eRkkRm5dI.jpg',
      tags: ['EleAdminPro', 'UI框架', '设计语言'],
      user: 'Jasmine',
      avatar:
        'https://cdn.eleadmin.com/20200609/f6bc05af944a4f738b54128717952107.jpg',
      favorites: 104,
      likes: 189,
      comments: 15
    }
  ];

  const keyword = ref('');
</script>

<script>
  export default {
    name: 'ListCardArticle'
  };
</script>

<style lang="less" scoped>
  .list-image-wrap {
    width: 280px;
    border-radius: 6px;
    overflow: hidden;
  }

  @media screen and (max-width: 880px) {
    .list-image-wrap {
      width: 200px;
    }
  }

  @media screen and (max-width: 576px) {
    .list-image-wrap {
      width: 100%;
    }
  }
</style>
