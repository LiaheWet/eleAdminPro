<template>
  <a-card
    :bordered="false"
    title="用户活跃度"
    :body-style="{ padding: '56px 0' }"
  >
    <div class="ele-cell">
      <div class="ele-cell-content ele-text-center">
        <div class="monitor-progress-group">
          <a-progress
            type="circle"
            :percent="70"
            stroke-color="#52c41a"
            :show-info="false"
            :width="161"
          />
          <a-progress
            type="circle"
            :percent="60"
            stroke-color="#1890ff"
            :show-info="false"
            :width="121"
            :stroke-width="5"
          />
          <a-progress
            type="circle"
            :percent="35"
            stroke-color="#f5222d"
            :show-info="false"
            :width="91"
            :stroke-width="4"
          />
        </div>
      </div>
      <div class="monitor-progress-legends">
        <div class="ele-text-small ele-elip">
          <a-badge color="green" text="活跃率: 70%" />
        </div>
        <div class="ele-text-small ele-elip">
          <a-badge color="blue" text="留存率: 60%" />
        </div>
        <div class="ele-text-small ele-elip">
          <a-badge color="red" text="跳出率: 35%" />
        </div>
      </div>
    </div>
  </a-card>
</template>

<style lang="less" scoped>
  .monitor-progress-group {
    position: relative;
    display: inline-block;

    .ant-progress:not(:first-child) {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      margin-top: -1px;
    }
  }

  .monitor-progress-legends {
    padding-right: 24px;

    :deep(.ant-badge-status-text) {
      font-size: 12px;
    }

    & > div + div {
      margin-top: 8px;
    }
  }
</style>
