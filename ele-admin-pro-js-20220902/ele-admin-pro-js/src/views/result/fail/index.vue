<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <div style="max-width: 960px; margin: 0 auto">
        <a-result
          status="error"
          title="提交失败"
          sub-title="请核对并修改以下信息后，再重新提交。"
        >
          <div>您提交的内容有如下错误:</div>
          <div class="error-tips-item">
            <close-circle-outlined class="ele-text-danger" />
            <div>您的账户已被冻结</div>
            <a>立即解冻&gt;</a>
          </div>
          <div class="error-tips-item">
            <close-circle-outlined class="ele-text-danger" />
            <div>您的账户还不具备申请资格</div>
            <a>立即升级&gt;</a>
          </div>
          <template #extra>
            <a-space size="middle">
              <a-button type="primary">返回修改</a-button>
              <a-button>重新提交</a-button>
            </a-space>
          </template>
        </a-result>
      </div>
    </a-card>
  </div>
</template>

<script setup>
  import { CloseCircleOutlined } from '@ant-design/icons-vue';
</script>

<script>
  export default {
    name: 'ResultFail'
  };
</script>

<style lang="less" scoped>
  .error-tips-item {
    display: flex;
    align-items: center;
    margin-top: 16px;

    & > div {
      margin: 0 10px;
    }

    a {
      white-space: nowrap;
    }
  }
</style>
