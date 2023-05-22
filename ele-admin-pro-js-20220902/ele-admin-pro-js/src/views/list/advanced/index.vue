<template>
  <div class="ele-body ele-body-card">
    <a-card :bordered="false">
      <a-row>
        <a-col :md="8" :sm="24" :xs="24">
          <div class="ele-text-center">
            <div style="margin-bottom: 8px">进行中的任务</div>
            <h2>10 个任务</h2>
          </div>
        </a-col>
        <a-col :md="8" :sm="24" :xs="24">
          <div class="ele-text-center">
            <div style="margin-bottom: 8px">剩余任务</div>
            <h2>3 个任务</h2>
          </div>
        </a-col>
        <a-col :md="8" :sm="24" :xs="24">
          <div class="ele-text-center">
            <div style="margin-bottom: 8px">任务总耗时</div>
            <h2>120 个小时</h2>
          </div>
        </a-col>
      </a-row>
    </a-card>
    <a-card :bordered="false">
      <!-- 头部工具栏 -->
      <ele-toolbar title="复杂列表">
        <template #action>
          <a-space size="middle">
            <a-radio-group v-model:value="where.state" @change="query">
              <a-radio-button value="0">全部</a-radio-button>
              <a-radio-button value="1">进行中</a-radio-button>
              <a-radio-button value="2">已完成</a-radio-button>
            </a-radio-group>
            <div style="width: 200px" class="hidden-sm-and-down">
              <a-input-search
                v-model:value="where.keyword"
                placeholder="请输入"
                @search="query"
              />
            </div>
          </a-space>
        </template>
      </ele-toolbar>
      <a-button block type="dashed" @click="openEdit()">
        <template #icon>
          <plus-outlined />
        </template>
        <span>添加</span>
      </a-button>
      <!-- 数据列表 -->
      <a-spin :spinning="loading">
        <div style="min-height: 100px">
          <div v-for="item in data" :key="item.id">
            <div class="basic-list-item">
              <div class="ele-cell">
                <a-avatar :size="60" shape="square" :src="item.cover" />
                <div class="ele-cell-content">
                  <div class="ele-cell-title">{{ item.title }}</div>
                  <div class="ele-cell-desc">{{ item.content }}</div>
                </div>
              </div>
              <div class="basic-list-item-owner">
                <div class="ele-text-heading">发布人</div>
                <div class="ele-text-secondary">{{ item.user }}</div>
              </div>
              <div class="basic-list-item-time">
                <div class="ele-text-heading">开始时间</div>
                <div class="ele-text-secondary">{{ item.time }}</div>
              </div>
              <div class="basic-list-item-progress">
                <a-progress :status="item.status" :percent="item.progress" />
              </div>
              <div class="basic-list-item-tool">
                <a-space>
                  <a @click="openEdit(item)">编辑</a>
                  <a-divider type="vertical" />
                  <a-dropdown>
                    <a>更多<down-outlined class="ele-text-small" /></a>
                    <template #overlay>
                      <a-menu @click="(obj) => dropClick(obj.key, item)">
                        <a-menu-item key="share">分享</a-menu-item>
                        <a-menu-item key="remove">删除</a-menu-item>
                      </a-menu>
                    </template>
                  </a-dropdown>
                </a-space>
              </div>
            </div>
            <a-divider />
          </div>
        </div>
        <div class="ele-text-center" style="margin-top: 18px">
          <a-pagination
            :total="count"
            v-model:page-size="limit"
            show-quick-jumper
            v-model:current="page"
            @change="query"
          />
        </div>
      </a-spin>
    </a-card>
    <!-- 编辑弹窗 -->
    <ele-modal
      :width="460"
      v-model:visible="visible"
      :confirm-loading="submitLoading"
      :title="form.id ? '任务编辑' : '任务添加'"
      :body-style="{ paddingBottom: '8px' }"
      @ok="submit"
    >
      <a-form
        ref="formRef"
        :model="form"
        :rules="rules"
        :label-col="{ md: 5, sm: 5, xs: 24 }"
        :wrapper-col="{ md: 19, sm: 19, xs: 24 }"
      >
        <a-form-item label="任务名称:" name="title">
          <a-input
            allow-clear
            v-model:value="form.title"
            placeholder="请输入任务名称"
          />
        </a-form-item>
        <a-form-item label="开始时间:" name="time">
          <a-date-picker
            show-time
            class="ele-fluid"
            v-model:value="form.time"
            placeholder="请选择开始时间"
            value-format="YYYY-MM-DD HH:mm:ss"
          />
        </a-form-item>
        <a-form-item label="负责人:" name="user">
          <a-select
            allow-clear
            v-model:value="form.user"
            placeholder="请选择负责人"
          >
            <a-select-option value="SunSmile">SunSmile</a-select-option>
            <a-select-option value="Pojin">Pojin</a-select-option>
            <a-select-option value="SuperWill">SuperWill</a-select-option>
            <a-select-option value="Jasmine">Jasmine</a-select-option>
            <a-select-option value="Vast">Vast</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="任务描述:">
          <a-textarea
            :rows="4"
            v-model:value="form.content"
            placeholder="请输入任务描述"
          />
        </a-form-item>
      </a-form>
    </ele-modal>
  </div>
</template>

<script setup>
  import { ref, reactive, createVNode } from 'vue';
  import { message, Modal } from 'ant-design-vue/es';
  import {
    PlusOutlined,
    DownOutlined,
    ExclamationCircleOutlined
  } from '@ant-design/icons-vue';
  import useFormData from '@/utils/use-form-data';

  //
  const formRef = ref(null);

  // 列表加载状态
  const loading = ref(false);

  // 列表数据
  const data = ref([]);

  // 搜索表单
  const where = reactive({
    state: '0',
    keyword: ''
  });

  // 第几页
  const page = ref(1);

  // 每页多少条
  const limit = ref(5);

  // 总数量
  const count = ref(0);

  // 编辑弹窗是否显示
  const visible = ref(false);

  // 编辑弹窗表单数据
  const { form, resetFields, assignFields } = useFormData({
    id: undefined,
    title: 'Vue Router',
    time: undefined,
    user: '',
    content: ''
  });

  // 编辑弹窗表单验证规则
  const rules = reactive({
    title: [
      {
        required: true,
        message: '请输入任务名称',
        type: 'string',
        trigger: 'blur'
      }
    ],
    time: [
      {
        required: true,
        message: '请选择开始时间',
        type: 'string',
        trigger: 'blur'
      }
    ],
    user: [
      {
        required: true,
        message: '请选择负责人',
        type: 'string',
        trigger: 'blur'
      }
    ]
  });

  // 编辑表单提交状态
  const submitLoading = ref(false);

  /* 查询数据 */
  const query = () => {
    loading.value = true;
    setTimeout(() => {
      loading.value = false;
      count.value = 25;
      data.value = [
        {
          id: 1,
          title: 'ElementUI',
          time: '2020-06-13 08:33:12',
          user: 'SunSmile',
          progress: 87,
          content:
            'Element，一套为开发者、设计师和产品经理准备的基于 Vue 2.0 的组件库，提供了配套设计资源，帮助你的网站快速成型。',
          cover:
            'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg'
        },
        {
          id: 2,
          title: 'Vue.js',
          time: '2020-06-13 06:40:13',
          user: 'Pojin',
          progress: 100,
          content:
            'Vue 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。',
          cover:
            'https://cdn.eleadmin.com/20200609/b6a811873e704db49db994053a5019b2.jpg'
        },
        {
          id: 3,
          title: 'Vuex',
          time: '2020-06-13 04:40:20',
          user: 'SuperWill',
          progress: 75,
          content:
            'Vuex 是一个专为 Vue.js 应用程序开发的状态管理模式。它采用集中式存储管理应用的所有组件的状态，并以相应的规则保证状态以一种可预测的方式发生变化。',
          cover:
            'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg'
        },
        {
          id: 4,
          title: 'Vue Router',
          time: '2020-06-13 02:40:05',
          user: 'Jasmine',
          progress: 65,
          content:
            'Vue Router 是 Vue.js 官方的路由管理器。它和 Vue.js 的核心深度集成，让构建单页面应用变得易如反掌。',
          cover:
            'https://cdn.eleadmin.com/20200609/f6bc05af944a4f738b54128717952107.jpg'
        },
        {
          id: 5,
          title: 'Sass',
          time: '2020-06-13 00:40:58',
          user: 'Vast',
          progress: 45,
          status: 'exception',
          content: 'Sass 是世界上最成熟、稳定、强大的专业级 CSS 扩展语言。',
          cover:
            'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg'
        }
      ];
    }, 300);
  };

  /* 显示编辑弹窗 */
  const openEdit = (row) => {
    visible.value = true;
    resetFields();
    formRef.value?.clearValidate();
    if (row) {
      assignFields(row);
    }
  };

  /* 保存编辑 */
  const submit = () => {
    if (!formRef.value) {
      return;
    }
    formRef.value
      .validate()
      .then(() => {
        submitLoading.value = true;
        setTimeout(() => {
          submitLoading.value = false;
          visible.value = false;
          message.success('保存成功');
          if (form.id) {
            // 保存修改
            Object.assign(
              data.value[data.value.findIndex((d) => d.id === form.id)],
              form
            );
          } else {
            // 保存添加
            data.value.push({
              ...form,
              id: new Date().getTime(),
              cover:
                'https://cdn.eleadmin.com/20200610/RZ8FQmZfHkcffMlTBCJllBFjEhEsObVo.jpg'
            });
          }
        }, 300);
      })
      .catch(() => {});
  };

  /* 下拉菜单点击事件 */
  const dropClick = (key, item) => {
    console.log(item);
    if (key === 'remove') {
      // 删除
      Modal.confirm({
        title: '提示',
        content: '确定删除该任务吗?',
        icon: createVNode(ExclamationCircleOutlined),
        maskClosable: true,
        onOk: () => {
          message.success('删除成功');
        }
      });
    } else if (key === 'share') {
      message.success('点击了分享');
    }
  };

  query();
</script>

<script>
  export default {
    name: 'ListAdvanced'
  };
</script>

<style lang="less" scoped>
  /* 列表样式 */
  .basic-list-item {
    display: flex;
    align-items: center;
    padding: 16px 8px;

    & > .ele-cell {
      flex: 1;
    }

    & > div + div {
      margin-left: 20px;
      flex-shrink: 0;
    }

    .basic-list-item-owner {
      width: 80px;
    }

    .basic-list-item-time {
      width: 160px;
    }

    .basic-list-item-progress {
      width: 180px;
    }

    .ele-text-heading + .ele-text-secondary {
      margin-top: 8px;
    }
  }

  /* 响应式 */
  @media screen and (max-width: 1340px) {
    .basic-list-item {
      & > div + div {
        margin-left: 10px;
      }

      .basic-list-item-owner {
        width: 70px;
      }

      .basic-list-item-time {
        width: 140px;
      }

      .basic-list-item-progress {
        width: 100px;
      }
    }
  }

  @media screen and (max-width: 1100px) {
    .basic-list-item {
      display: block;

      .basic-list-item-owner,
      .basic-list-item-time,
      .basic-list-item-progress {
        width: auto;
        margin: 8px 0 0 0;
        display: flex;
        align-items: center;
      }

      .basic-list-item-tool {
        margin-top: 8px;
        text-align: right;
      }

      .ele-text-heading + .ele-text-secondary {
        margin: 0 0 0 16px;
      }
    }
  }
</style>
