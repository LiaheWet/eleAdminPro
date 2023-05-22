<!-- 顶栏消息通知 -->
<template>
  <a-dropdown
    v-model:visible="visible"
    placement="bottom"
    :trigger="['click']"
    :overlay-style="{ padding: '0 10px' }"
  >
    <a-badge :count="unreadNum" class="ele-notice-trigger" :offset="[6, 4]">
      <bell-outlined style="padding: 8px 0" />
    </a-badge>
    <template #overlay>
      <div class="ant-dropdown-menu ele-notice-pop">
        <div @click.stop="">
          <a-tabs v-model:active-key="active" :centered="true">
            <a-tab-pane key="notice" :tab="noticeTitle">
              <a-list item-layout="horizontal" :data-source="notice">
                <template #renderItem="{ item }">
                  <a-list-item>
                    <a-list-item-meta
                      :title="item.title"
                      :description="item.time"
                    >
                      <template #avatar>
                        <a-avatar :style="{ background: item.color }">
                          <template #icon>
                            <component :is="item.icon" />
                          </template>
                        </a-avatar>
                      </template>
                    </a-list-item-meta>
                  </a-list-item>
                </template>
              </a-list>
              <div v-if="notice.length" class="ele-cell ele-notice-actions">
                <div class="ele-cell-content" @click="clearNotice">
                  清空通知
                </div>
                <a-divider type="vertical" />
                <router-link
                  to="/user/message?type=notice"
                  class="ele-cell-content"
                >
                  查看更多
                </router-link>
              </div>
            </a-tab-pane>
            <a-tab-pane key="letter" :tab="letterTitle">
              <a-list item-layout="horizontal" :data-source="letter">
                <template #renderItem="{ item }">
                  <a-list-item>
                    <a-list-item-meta :title="item.title">
                      <template #avatar>
                        <a-avatar :src="item.avatar" />
                      </template>
                      <template #description>
                        <div>{{ item.content }}</div>
                        <div>{{ item.time }}</div>
                      </template>
                    </a-list-item-meta>
                  </a-list-item>
                </template>
              </a-list>
              <div v-if="letter.length" class="ele-cell ele-notice-actions">
                <div class="ele-cell-content" @click="clearLetter">
                  清空私信
                </div>
                <a-divider type="vertical" />
                <router-link
                  to="/user/message?type=letter"
                  class="ele-cell-content"
                >
                  查看更多
                </router-link>
              </div>
            </a-tab-pane>
            <a-tab-pane key="todo" :tab="todoTitle">
              <a-list item-layout="horizontal" :data-source="todo">
                <template #renderItem="{ item }">
                  <a-list-item>
                    <a-list-item-meta :description="item.description">
                      <template #title>
                        <div class="ele-cell">
                          <div class="ele-cell-content">{{ item.title }}</div>
                          <a-tag :color="[void 0, 'red', 'blue'][item.status]">
                            {{ ['未开始', '即将到期', '进行中'][item.status] }}
                          </a-tag>
                        </div>
                      </template>
                    </a-list-item-meta>
                  </a-list-item>
                </template>
              </a-list>
              <div v-if="todo.length" class="ele-cell ele-notice-actions">
                <div class="ele-cell-content" @click="clearTodo">
                  清空待办
                </div>
                <a-divider type="vertical" />
                <router-link
                  to="/user/message?type=todo"
                  class="ele-cell-content"
                >
                  查看更多
                </router-link>
              </div>
            </a-tab-pane>
          </a-tabs>
        </div>
      </div>
    </template>
  </a-dropdown>
</template>

<script setup>
  import { ref, computed } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { getUnreadNotice } from '@/api/layout';

  // 是否显示
  const visible = ref(false);
  // 选项卡选中
  const active = ref('notice');
  // 通知数据
  const notice = ref([]);
  // 私信数据
  const letter = ref([]);
  // 待办数据
  const todo = ref([]);

  // 通知标题
  const noticeTitle = computed(() => {
    return '通知' + (notice.value.length ? `(${notice.value.length})` : '');
  });

  // 私信标题
  const letterTitle = computed(() => {
    return '私信' + (letter.value.length ? `(${letter.value.length})` : '');
  });

  // 待办标题
  const todoTitle = computed(() => {
    return '待办' + (todo.value.length ? `(${todo.value.length})` : '');
  });

  // 未读数量
  const unreadNum = computed(() => {
    return notice.value.length + letter.value.length + todo.value.length;
  });

  /* 查询数据 */
  const query = () => {
    getUnreadNotice()
      .then((result) => {
        notice.value = result.notice;
        letter.value = result.letter;
        todo.value = result.todo;
      })
      .catch((e) => {
        message.error(e.message);
      });
  };

  /* 清空通知 */
  const clearNotice = () => {
    notice.value = [];
  };

  /* 清空通知 */
  const clearLetter = () => {
    letter.value = [];
  };

  /* 清空通知 */
  const clearTodo = () => {
    todo.value = [];
  };

  query();
</script>

<script>
  import {
    BellOutlined,
    NotificationFilled,
    PushpinFilled,
    VideoCameraFilled,
    CarryOutFilled,
    BellFilled
  } from '@ant-design/icons-vue';

  export default {
    name: 'HeaderNotice',
    components: {
      BellOutlined,
      NotificationFilled,
      PushpinFilled,
      VideoCameraFilled,
      CarryOutFilled,
      BellFilled
    }
  };
</script>

<style lang="less">
  .ele-notice-trigger.ant-badge {
    color: inherit;
  }

  .ele-notice-pop {
    &.ant-dropdown-menu {
      padding: 0;
      width: 336px;
      max-width: 100%;
      margin-top: 11px;
    }

    // 内容
    .ant-list-item {
      padding-left: 24px;
      padding-right: 24px;
      transition: background-color 0.3s;
      cursor: pointer;

      &:hover {
        background: hsla(0, 0%, 60%, 0.05);
      }
    }

    .ant-tag {
      margin: 0;
    }

    // 操作按钮
    .ele-notice-actions {
      border-top: 1px solid hsla(0, 0%, 60%, 0.15);

      & > .ele-cell-content {
        line-height: 46px;
        text-align: center;
        transition: background-color 0.3s;
        cursor: pointer;
        color: inherit;

        &:hover {
          background: hsla(0, 0%, 60%, 0.05);
        }
      }
    }
  }
</style>
