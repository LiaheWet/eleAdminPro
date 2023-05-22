<template>
  <div class="ele-body ele-body-card">
    <a-row :gutter="16">
      <a-col :xxl="6" :xl="7" :lg="9" :md="10" :sm="24" :xs="24">
        <a-card :bordered="false">
          <div class="ele-text-center">
            <div class="user-info-avatar-group" @click="openCropper">
              <a-avatar :size="110" :src="form.avatar" />
              <upload-outlined class="user-info-avatar-icon" />
            </div>
            <h1>{{ loginUser.nickname }}</h1>
            <div>{{ loginUser.introduction }}</div>
          </div>
          <div class="user-info-list">
            <div class="ele-cell">
              <user-outlined />
              <div class="ele-cell-content">资深前端工程师</div>
            </div>
            <div class="ele-cell">
              <home-outlined />
              <div class="ele-cell-content">某某公司 - 研发部 - 某某组</div>
            </div>
            <div class="ele-cell">
              <environment-outlined />
              <div class="ele-cell-content">中国 • 浙江省 • 杭州市</div>
            </div>
            <div class="ele-cell">
              <tag-outlined />
              <div class="ele-cell-content">JavaScript、HTML、CSS</div>
            </div>
          </div>
          <a-divider dashed />
          <h6>标签</h6>
          <div class="user-info-tags">
            <a-tag>很有想法的</a-tag>
            <a-tag>专注设计</a-tag>
            <a-tag>辣~</a-tag>
            <a-tag>大长腿</a-tag>
            <a-tag>川妹子</a-tag>
            <a-tag>海纳百川</a-tag>
          </div>
        </a-card>
      </a-col>
      <a-col :xxl="18" :xl="17" :lg="15" :md="14" :sm="24" :xs="24">
        <a-card
          :bordered="false"
          :body-style="{ paddingTop: '0px', minHeight: '600px' }"
        >
          <a-tabs v-model:active-key="active" size="large">
            <a-tab-pane tab="基本信息" key="info">
              <a-form
                ref="formRef"
                :model="form"
                :rules="rules"
                :label-col="{ lg: 4, md: 6, sm: 4, xs: 24 }"
                :wrapper-col="{ lg: 20, md: 18, sm: 20, xs: 24 }"
                style="max-width: 580px; margin-top: 20px"
              >
                <a-form-item label="昵称" name="nickname">
                  <a-input
                    v-model:value="form.nickname"
                    placeholder="请输入昵称"
                    allow-clear
                  />
                </a-form-item>
                <a-form-item label="性别" name="sex">
                  <a-select
                    v-model:value="form.sex"
                    placeholder="请选择性别"
                    allow-clear
                  >
                    <a-select-option value="保密">保密</a-select-option>
                    <a-select-option value="男">男</a-select-option>
                    <a-select-option value="女">女</a-select-option>
                  </a-select>
                </a-form-item>
                <a-form-item label="邮箱" name="email">
                  <a-input
                    v-model:value="form.email"
                    placeholder="请输入邮箱"
                    allow-clear
                  />
                </a-form-item>
                <a-form-item label="个人简介">
                  <a-textarea
                    v-model:value="form.introduction"
                    placeholder="请输入个人简介"
                    :rows="4"
                  />
                </a-form-item>
                <a-form-item label="街道地址">
                  <a-input
                    v-model:value="form.address"
                    placeholder="请输入街道地址"
                    allow-clear
                  />
                </a-form-item>
                <a-form-item label="联系电话:">
                  <div class="ele-cell">
                    <a-input v-model:value="form.tellPre" style="width: 65px" />
                    <div class="ele-cell-content">
                      <a-input
                        v-model:value="form.tell"
                        placeholder="请输入联系电话"
                        allow-clear
                      />
                    </div>
                  </div>
                </a-form-item>
                <a-form-item :wrapper-col="{ md: { offset: 6 } }">
                  <a-button type="primary" :loading="loading" @click="save">
                    {{ loading ? '保存中..' : '保存更改' }}
                  </a-button>
                </a-form-item>
              </a-form>
            </a-tab-pane>
            <a-tab-pane tab="账号绑定" key="account">
              <div class="user-account-list">
                <div class="ele-cell">
                  <div class="ele-cell-content">
                    <div class="ele-cell-title">密保手机</div>
                    <div class="ele-cell-desc">已绑定手机: 138****8293</div>
                  </div>
                  <a>去修改</a>
                </div>
                <a-divider />
                <div class="ele-cell">
                  <div class="ele-cell-content">
                    <div class="ele-cell-title">密保邮箱</div>
                    <div class="ele-cell-desc">
                      已绑定邮箱: eleadmin@eclouds.com
                    </div>
                  </div>
                  <a>去修改</a>
                </div>
                <a-divider />
                <div class="ele-cell">
                  <div class="ele-cell-content">
                    <div class="ele-cell-title">密保问题</div>
                    <div class="ele-cell-desc">未设置密保问题</div>
                  </div>
                  <a>去设置</a>
                </div>
                <a-divider />
                <div class="ele-cell">
                  <qq-outlined class="user-account-icon" />
                  <div class="ele-cell-content">
                    <div class="ele-cell-title">绑定QQ</div>
                    <div class="ele-cell-desc">当前未绑定QQ账号</div>
                  </div>
                  <a>去绑定</a>
                </div>
                <a-divider />
                <div class="ele-cell">
                  <wechat-outlined class="user-account-icon" />
                  <div class="ele-cell-content">
                    <div class="ele-cell-title">绑定微信</div>
                    <div class="ele-cell-desc">当前未绑定绑定微信账号</div>
                  </div>
                  <a>去绑定</a>
                </div>
                <a-divider />
                <div class="ele-cell">
                  <alipay-outlined class="user-account-icon" />
                  <div class="ele-cell-content">
                    <div class="ele-cell-title">绑定支付宝</div>
                    <div class="ele-cell-desc">当前未绑定绑定支付宝账号</div>
                  </div>
                  <a>去绑定</a>
                </div>
              </div>
            </a-tab-pane>
          </a-tabs>
        </a-card>
      </a-col>
    </a-row>
    <!-- 头像裁剪弹窗 -->
    <ele-cropper-modal
      :src="form.avatar"
      v-model:visible="visible"
      :options="{ autoCropArea: 1, viewMode: 1, dragMode: 'move' }"
      @done="onCrop"
    />
  </div>
</template>

<script setup>
  import { ref, reactive, computed } from 'vue';
  import {
    UploadOutlined,
    UserOutlined,
    HomeOutlined,
    EnvironmentOutlined,
    TagOutlined,
    QqOutlined,
    WechatOutlined,
    AlipayOutlined
  } from '@ant-design/icons-vue';
  import { message } from 'ant-design-vue/es';
  import { useUserStore } from '@/store/modules/user';

  const userStore = useUserStore();

  //
  const formRef = ref(null);

  // tab 页选中
  const active = ref('info');

  // 保存按钮 loading
  const loading = ref(false);

  // 是否显示裁剪弹窗
  const visible = ref(false);

  // 登录用户信息
  const loginUser = computed(() => userStore.info ?? {});

  // 表单数据
  const form = reactive({
    nickname: loginUser.value.nickname,
    sex: '保密',
    email: 'eleadmin@eclouds.com',
    introduction: loginUser.value.introduction,
    address: '',
    tellPre: '0752',
    tell: '',
    avatar: 'https://cdn.eleadmin.com/20200610/avatar.jpg'
  });

  // 表单验证规则
  const rules = reactive({
    nickname: [
      {
        required: true,
        message: '请输入昵称',
        type: 'string'
      }
    ],
    sex: [
      {
        required: true,
        message: '请选择性别',
        type: 'string'
      }
    ],
    email: [
      {
        required: true,
        message: '请输入邮箱',
        type: 'string'
      }
    ]
  });

  /* 修改登录用户 */
  const updateLoginUser = (obj) => {
    userStore.setInfo({ ...loginUser.value, ...obj });
  };

  /* 保存更改 */
  const save = () => {
    if (!formRef.value) {
      return;
    }
    formRef.value
      .validate()
      .then(() => {
        loading.value = true;
        setTimeout(() => {
          loading.value = false;
          message.success('保存成功');
          updateLoginUser(form);
        }, 800);
      })
      .catch(() => {});
  };

  /* 头像裁剪完成回调 */
  const onCrop = (result) => {
    form.avatar = result;
    visible.value = false;
    updateLoginUser(form);
  };

  /* 打开图片裁剪 */
  const openCropper = () => {
    visible.value = true;
  };
</script>

<script>
  export default {
    name: 'UserProfile'
  };
</script>

<style lang="less" scoped>
  /* 用户资料卡片 */
  .user-info-avatar-group {
    margin: 16px 0;
    display: inline-block;
    position: relative;
    cursor: pointer;

    .user-info-avatar-icon {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      color: #fff;
      font-size: 30px;
      display: none;
      z-index: 2;
    }

    &:hover .user-info-avatar-icon {
      display: block;
    }

    &:after {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: transparent;
      transition: background-color 0.3s;
    }

    &:hover:after {
      background-color: rgba(0, 0, 0, 0.4);
    }

    & + h1 {
      margin-bottom: 8px;
    }
  }

  /* 用户信息列表 */
  .user-info-list {
    margin: 47px 0 32px 0;

    .ele-cell + .ele-cell {
      margin-top: 16px;
    }

    & + .ant-divider {
      margin-bottom: 16px;
    }
  }

  /* 用户标签 */
  .user-info-tags {
    margin: 16px 0 4px 0;

    .ant-tag {
      margin: 0 12px 8px 0;
    }
  }

  /* 用户账号绑定列表 */
  .user-account-list {
    & > .ele-cell {
      padding: 16px 8px;
    }

    .user-account-icon {
      color: #fff;
      padding: 8px;
      font-size: 26px;
      border-radius: 50%;

      &.anticon-qq {
        background: #3492ed;
      }

      &.anticon-wechat {
        background: #4daf29;
      }

      &.anticon-alipay {
        background: #1476fe;
      }
    }
  }
</style>
