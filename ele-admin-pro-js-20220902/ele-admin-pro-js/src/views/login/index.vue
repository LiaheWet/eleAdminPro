<template>
  <div
    :class="[
      'login-wrapper',
      ['', 'login-form-right', 'login-form-left'][direction]
    ]"
  >
    <a-form
      ref="formRef"
      :model="form"
      :rules="rules"
      class="login-form ele-bg-white"
    >
      <h4>{{ t('login.title') }}</h4>
      <a-form-item name="username">
        <a-input
          allow-clear
          size="large"
          v-model:value="form.username"
          :placeholder="t('login.username')"
        >
          <template #prefix>
            <user-outlined />
          </template>
        </a-input>
      </a-form-item>
      <a-form-item name="password">
        <a-input-password
          size="large"
          v-model:value="form.password"
          :placeholder="t('login.password')"
        >
          <template #prefix>
            <lock-outlined />
          </template>
        </a-input-password>
      </a-form-item>
      <a-form-item name="code">
        <div class="login-input-group">
          <a-input
            allow-clear
            size="large"
            v-model:value="form.code"
            :placeholder="t('login.code')"
          >
            <template #prefix>
              <safety-certificate-outlined />
            </template>
          </a-input>
          <a-button class="login-captcha" @click="changeCaptcha">
            <img v-if="captcha" :src="captcha" alt="" />
          </a-button>
        </div>
      </a-form-item>
      <a-form-item>
        <a-checkbox v-model:checked="form.remember">
          {{ t('login.remember') }}
        </a-checkbox>
        <router-link
          to="/forget"
          class="ele-pull-right"
          style="line-height: 22px"
        >
          {{ t('login.forget') }}
        </router-link>
      </a-form-item>
      <a-form-item>
        <a-button
          block
          size="large"
          type="primary"
          :loading="loading"
          @click="submit"
        >
          {{ loading ? t('login.loading') : t('login.login') }}
        </a-button>
      </a-form-item>
      <div class="ele-text-center" style="padding-bottom: 32px">
        <qq-outlined class="login-oauth-icon" style="background: #3492ed" />
        <wechat-outlined class="login-oauth-icon" style="background: #4daf29" />
        <weibo-outlined class="login-oauth-icon" style="background: #cf1900" />
      </div>
    </a-form>
    <div class="login-copyright">
      copyright © 2022 eleadmin.com all rights reserved.
    </div>
    <!-- 多语言切换 -->
    <div style="position: absolute; right: 30px; top: 20px; z-index: 999">
      <i18n-icon
        placement="bottomLeft"
        :style="{ fontSize: '18px', color: '#fff' }"
      />
    </div>
    <!-- 实际项目去掉这段 -->
    <div style="position: absolute; left: 30px; top: 20px; z-index: 999">
      <a-radio-group v-model:value="direction" size="small">
        <a-radio-button :value="2">居左</a-radio-button>
        <a-radio-button :value="0">居中</a-radio-button>
        <a-radio-button :value="1">居右</a-radio-button>
      </a-radio-group>
    </div>
  </div>
</template>

<script setup>
  import { ref, reactive, computed, unref } from 'vue';
  import { useI18n } from 'vue-i18n';
  import { useRouter } from 'vue-router';
  import { message } from 'ant-design-vue/es';
  import {
    UserOutlined,
    LockOutlined,
    SafetyCertificateOutlined,
    QqOutlined,
    WechatOutlined,
    WeiboOutlined
  } from '@ant-design/icons-vue';
  import I18nIcon from '@/layout/components/i18n-icon.vue';
  import { getToken } from '@/utils/token-util';
  import { goHomeRoute, cleanPageTabs } from '@/utils/page-tab-util';
  import { login, getCaptcha } from '@/api/login';

  const { currentRoute } = useRouter();
  const { t } = useI18n();

  // 登录框方向, 0 居中, 1 居右, 2 居左
  const direction = ref(0);

  //
  const formRef = ref(null);

  // 加载状态
  const loading = ref(false);

  // 表单数据
  const form = reactive({
    username: 'admin',
    password: 'admin',
    code: '',
    remember: true
  });

  // 验证码 base64 数据
  const captcha = ref('');

  // 验证码内容, 实际项目去掉
  const text = ref('');

  // 表单验证规则
  const rules = computed(() => {
    return {
      username: [
        {
          required: true,
          message: t('login.username'),
          type: 'string',
          trigger: 'blur'
        }
      ],
      password: [
        {
          required: true,
          message: t('login.password'),
          type: 'string',
          trigger: 'blur'
        }
      ],
      code: [
        {
          required: true,
          message: t('login.code'),
          type: 'string',
          trigger: 'blur'
        }
      ]
    };
  });

  /* 跳转到首页 */
  const goHome = () => {
    const { query } = unref(currentRoute);
    goHomeRoute(query.from);
  };

  /* 提交 */
  const submit = () => {
    if (!formRef.value) {
      return;
    }
    formRef.value.validate().then(() => {
      if (form.code.toLowerCase() !== text.value) {
        message.error('验证码错误');
        return;
      }
      loading.value = true;
      login(form)
        .then((msg) => {
          message.success(msg);
          cleanPageTabs();
          goHome();
        })
        .catch((e) => {
          message.error(e.message);
          loading.value = false;
        });
    });
  };

  /* 获取图形验证码 */
  const changeCaptcha = () => {
    // 这里演示的验证码是后端返回base64格式的形式, 如果后端地址直接是图片请参考忘记密码页面
    getCaptcha()
      .then((data) => {
        captcha.value = data.base64;
        // 实际项目后端一般会返回验证码的key而不是直接返回验证码的内容, 登录用key去验证, 你可以根据自己后端接口修改
        text.value = data.text;
        // 自动回填验证码, 实际项目去掉这个
        form.code = data.text;
        formRef.value?.clearValidate();
      })
      .catch((e) => {
        message.error(e.message);
      });
  };

  if (getToken()) {
    goHome();
  } else {
    changeCaptcha();
  }
</script>

<style lang="less" scoped>
  /* 背景 */
  .login-wrapper {
    padding: 48px 16px 0 16px;
    position: relative;
    box-sizing: border-box;
    background-image: url('@/assets/bg-login.jpg');
    background-repeat: no-repeat;
    background-size: cover;
    min-height: 100vh;

    &:before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: rgba(0, 0, 0, 0.2);
    }
  }

  /* 卡片 */
  .login-form {
    width: 360px;
    margin: 0 auto;
    max-width: 100%;
    padding: 0 28px;
    box-sizing: border-box;
    box-shadow: 0 3px 6px rgba(0, 0, 0, 0.15);
    border-radius: 2px;
    position: relative;
    z-index: 2;

    h4 {
      padding: 22px 0;
      text-align: center;
    }
  }

  .login-form-right .login-form {
    margin: 0 15% 0 auto;
  }

  .login-form-left .login-form {
    margin: 0 auto 0 15%;
  }

  /* 验证码 */
  .login-input-group {
    display: flex;
    align-items: center;

    :deep(.ant-input-affix-wrapper) {
      flex: 1;
    }

    .login-captcha {
      width: 102px;
      height: 40px;
      margin-left: 10px;
      padding: 0;

      & > img {
        width: 100%;
        height: 100%;
      }
    }
  }

  /* 第三方登录图标 */
  .login-oauth-icon {
    color: #fff;
    padding: 5px;
    margin: 0 12px;
    font-size: 18px;
    border-radius: 50%;
    cursor: pointer;
  }

  /* 底部版权 */
  .login-copyright {
    color: #eee;
    text-align: center;
    padding: 48px 0 22px 0;
    position: relative;
    z-index: 1;
  }

  /* 响应式 */
  @media screen and (min-height: 640px) {
    .login-wrapper {
      padding-top: 0;
    }

    .login-form {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translateX(-50%);
      margin-top: -230px;
    }

    .login-form-right .login-form,
    .login-form-left .login-form {
      left: auto;
      right: 15%;
      transform: translateX(0);
      margin: -230px auto auto auto;
    }

    .login-form-left .login-form {
      right: auto;
      left: 15%;
    }

    .login-copyright {
      position: absolute;
      left: 0;
      right: 0;
      bottom: 0;
    }
  }

  @media screen and (max-width: 768px) {
    .login-form-right .login-form,
    .login-form-left .login-form {
      left: 50%;
      right: auto;
      margin-left: 0;
      margin-right: auto;
      transform: translateX(-50%);
    }
  }
</style>
