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
          <template #cover>
            <img :src="item.cover" alt="" />
          </template>
          <a-card-meta :title="item.title">
            <template #description>
              <div class="project-list-desc" :title="item.content">
                {{ item.content }}
              </div>
            </template>
          </a-card-meta>
          <div class="ele-cell">
            <div class="ele-cell-content ele-text-secondary">
              {{ item.time }}
            </div>
            <ele-avatar-list :data="item.users" size="small" />
          </div>
        </a-card>
      </a-col>
    </a-row>
    <div class="ele-text-center" style="margin-top: 18px">
      <a-pagination
        :total="count"
        v-model:current="page"
        v-model:page-size="limit"
        @change="query"
      />
    </div>
  </div>
</template>

<script setup>
  import { ref } from 'vue';

  const data = ref([]);

  const keyword = ref('');

  // 第几页
  const page = ref(1);

  // 每页多少条
  const limit = ref(8);

  // 总数量
  const count = ref(0);

  /* 查询数据 */
  const query = () => {
    count.value = 40;
    data.value = [
      {
        title: 'ElementUI',
        content:
          'Element，一套为开发者、设计师和产品经理准备的基于 Vue 2.0 的组件库，提供了配套设计资源，帮助你的网站快速成型。',
        time: '2 小时前',
        cover:
          'https://cdn.eleadmin.com/20200610/RZ8FQmZfHkcffMlTBCJllBFjEhEsObVo.jpg',
        users: [
          {
            name: 'SunSmile',
            avatar:
              'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
          },
          {
            name: '酷酷的大叔',
            avatar:
              'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
          },
          {
            name: 'Jasmine',
            avatar:
              'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
          }
        ]
      },
      {
        title: 'Vue.js',
        content:
          'Vue 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。',
        time: '4 小时前',
        cover:
          'https://cdn.eleadmin.com/20200610/WLXm7gp1EbLDtvVQgkeQeyq5OtDm00Jd.jpg',
        users: [
          {
            name: 'SunSmile',
            avatar:
              'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
          },
          {
            name: '酷酷的大叔',
            avatar:
              'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
          },
          {
            name: 'Jasmine',
            avatar:
              'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
          }
        ]
      },
      {
        title: 'Vuex',
        content:
          'Vuex 是一个专为 Vue.js 应用程序开发的状态管理模式。它采用集中式存储管理应用的所有组件的状态，并以相应的规则保证状态以一种可预测的方式发生变化。',
        time: '12 小时前',
        cover:
          'https://cdn.eleadmin.com/20200610/4Z0QR2L0J1XStxBh99jVJ8qLfsGsOgjU.jpg',
        users: [
          {
            name: 'SunSmile',
            avatar:
              'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
          },
          {
            name: '酷酷的大叔',
            avatar:
              'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
          },
          {
            name: 'Jasmine',
            avatar:
              'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
          }
        ]
      },
      {
        title: 'Vue Router',
        content:
          'Vue Router 是 Vue.js 官方的路由管理器。它和 Vue.js 的核心深度集成，让构建单页面应用变得易如反掌。',
        time: '14 小时前',
        cover:
          'https://cdn.eleadmin.com/20200610/ttkIjNPlVDuv4lUTvRX8GIlM2QqSe0jg.jpg',
        users: [
          {
            name: 'SunSmile',
            avatar:
              'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
          },
          {
            name: '酷酷的大叔',
            avatar:
              'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
          },
          {
            name: 'Jasmine',
            avatar:
              'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
          }
        ]
      },
      {
        title: 'Sass',
        content: 'Sass 是世界上最成熟、稳定、强大的专业级 CSS 扩展语言。',
        time: '10 小时前',
        cover:
          'https://cdn.eleadmin.com/20200610/fAenQ8nvRjL7x0i0jEfuDBZHvJfHf3v6.jpg',
        users: [
          {
            name: 'SunSmile',
            avatar:
              'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
          },
          {
            name: '酷酷的大叔',
            avatar:
              'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
          },
          {
            name: 'Jasmine',
            avatar:
              'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
          }
        ]
      },
      {
        title: 'Axios',
        content:
          'Axios 是一个基于 promise 的 HTTP 库，可以用在浏览器和 node.js 中。',
        time: '16 小时前',
        cover:
          'https://cdn.eleadmin.com/20200610/LrCTN2j94lo9N7wEql7cBr1Ux4rHMvmZ.jpg',
        users: [
          {
            name: 'SunSmile',
            avatar:
              'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
          },
          {
            name: '酷酷的大叔',
            avatar:
              'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
          },
          {
            name: 'Jasmine',
            avatar:
              'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
          }
        ]
      },
      {
        title: 'Webpack',
        content:
          'webpack 是一个模块打包器。webpack 的主要目标是将 JavaScript 文件打包在一起，打包后的文件用于在浏览器中使用。',
        time: '6 小时前',
        cover:
          'https://cdn.eleadmin.com/20200610/yeKvhT20lMU0f1T3Y743UlGEOLLnZSnp.jpg',
        users: [
          {
            name: 'SunSmile',
            avatar:
              'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
          },
          {
            name: '酷酷的大叔',
            avatar:
              'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
          },
          {
            name: 'Jasmine',
            avatar:
              'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
          }
        ]
      },
      {
        title: 'Node.js',
        content:
          'Node.js 是一个基于 Chrome V8 引擎的 JavaScript 运行环境。Node.js 使用了一个事件驱动、非阻塞式 I/O 的模型，使其轻量又高效。',
        time: '8 小时前',
        cover:
          'https://cdn.eleadmin.com/20200610/CyrCNmTJfv7D6GFAg39bjT3eRkkRm5dI.jpg',
        users: [
          {
            name: 'SunSmile',
            avatar:
              'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
          },
          {
            name: '酷酷的大叔',
            avatar:
              'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
          },
          {
            name: 'Jasmine',
            avatar:
              'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
          }
        ]
      }
    ];
  };

  query();
</script>

<script>
  export default {
    name: 'ListCardProject'
  };
</script>

<style lang="less" scoped>
  .project-list-desc {
    height: 44px;
    line-height: 22px;
    margin-bottom: 20px;
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
  }
</style>
