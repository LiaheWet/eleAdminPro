<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <div style="max-width: 960px; margin: 0 auto">
        <a-result
          status="success"
          title="提交成功"
          sub-title="提交结果页用于反馈一系列操作任务的处理结果，如果仅是简单操作，使用 Message 全局提示反馈即可。灰色区域可以显示一些补充的信息。"
        >
          <div>已提交申请，等待部门审核。</div>
          <template #extra>
            <a-space size="middle">
              <a-button type="primary">返回列表</a-button>
              <a-button>查看项目</a-button>
              <a-button>打印</a-button>
            </a-space>
          </template>
        </a-result>
      </div>
    </a-card>
  </div>
</template>

<script>
  export default {
    name: 'ResultSuccess'
  };
</script>
