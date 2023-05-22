<template>
  <div class="ele-body">
    <a-card :bordered="false" :body-style="{ padding: '0px' }">
      <div class="ele-cell ele-cell-align-top ele-user-message">
        <div class="message-menu-wrap">
          <a-menu :selected-keys="active" :mode="mode">
            <a-menu-item key="notice">
              <router-link to="/user/message?type=notice">
                <a-badge v-if="unReadNotice" :count="unReadNotice" />
                <span>系统通知</span>
              </router-link>
            </a-menu-item>
            <a-menu-item key="letter">
              <router-link to="/user/message?type=letter">
                <a-badge v-if="unReadLetter" :count="unReadLetter" />
                <span>用户私信</span>
              </router-link>
            </a-menu-item>
            <a-menu-item key="todo">
              <router-link to="/user/message?type=todo">
                <a-badge v-if="unReadTodo" :count="unReadTodo" />
                <span>代办事项</span>
              </router-link>
            </a-menu-item>
          </a-menu>
        </div>
        <div class="ele-cell-content" style="overflow-x: hidden">
          <transition name="slide-right" mode="out-in">
            <message-notice
              v-if="active.includes('notice')"
              @update-data="queryUnReadNum"
            />
            <message-letter
              v-else-if="active.includes('letter')"
              @update-data="queryUnReadNum"
            />
            <message-todo v-else @update-data="queryUnReadNum" />
          </transition>
        </div>
      </div>
    </a-card>
  </div>
</template>

<script setup>
  import { ref, watch, unref, computed } from 'vue';
  import { useRouter } from 'vue-router';
  import { storeToRefs } from 'pinia';
  import { message } from 'ant-design-vue/es';
  import { useThemeStore } from '@/store/modules/theme';
  import MessageNotice from './components/message-notice.vue';
  import MessageLetter from './components/message-letter.vue';
  import MessageTodo from './components/message-todo.vue';
  import { getUnReadNum } from '@/api/user/message';

  const { currentRoute } = useRouter();
  const themeStore = useThemeStore();
  const { screenWidth } = storeToRefs(themeStore);

  // 导航选中
  const active = ref([]);

  // 通知未读数量
  const unReadNotice = ref(0);

  // 私信未读数量
  const unReadLetter = ref(0);

  // 代办未读数量
  const unReadTodo = ref(0);

  // 导航模式
  const mode = computed(() => {
    return screenWidth.value < 768 ? 'horizontal' : 'inline';
  });

  watch(
    currentRoute,
    (route) => {
      const { path, query } = unref(route);
      if (path === '/user/message') {
        const defaultType = 'notice';
        if (!query.type) {
          active.value = [defaultType];
        } else if (typeof query.type === 'string') {
          active.value = [query.type || defaultType];
        } else if (query.type.length && query.type[0]) {
          active.value = [query.type[0]];
        } else {
          active.value = [defaultType];
        }
      }
    },
    {
      immediate: true
    }
  );

  /* 查询未读数量 */
  const queryUnReadNum = () => {
    getUnReadNum()
      .then((result) => {
        unReadNotice.value = result.notice;
        unReadLetter.value = result.letter;
        unReadTodo.value = result.todo;
      })
      .catch((e) => {
        message.error(e.message);
      });
  };

  queryUnReadNum();
</script>

<script>
  export default {
    name: 'UserMessage'
  };
</script>

<style lang="less" scoped>
  .message-menu-wrap {
    width: 150px;
    display: flex;

    :deep(.ant-menu) {
      padding-top: 16px;

      .ant-badge {
        vertical-align: -2px;
        margin-right: 10px;
      }

      .ant-badge-count {
        height: 16px;
        line-height: 16px;
        border-radius: 8px;
        box-shadow: none;
        min-width: 16px;
        padding: 0 2px;
      }

      .ant-scroll-number-only {
        height: 16px;

        & > p.ant-scroll-number-only-unit {
          height: 16px;
        }
      }
    }

    & + .ele-cell-content {
      padding: 16px 24px;
      overflow: auto;
    }
  }

  @media screen and (max-width: 768px) {
    .ele-user-message {
      display: block;

      & > .ele-cell-content {
        padding: 16px 16px;
      }
    }

    .message-menu-wrap {
      width: auto;
      display: block;

      :deep(.ant-menu) {
        padding-top: 0;
      }
    }
  }
</style>
