<template>
  <a-card :bordered="false" title="用户满意度">
    <div class="ele-cell ele-text-center">
      <div class="ele-cell-content" style="font-size: 24px">856</div>
      <div class="ele-cell-content">
        <div class="monitor-face-smile"><i></i></div>
        <div class="ele-text-secondary ele-elip" style="margin-top: 8px">
          正面评论
        </div>
      </div>
      <h2 class="ele-cell-content ele-text-success">82%</h2>
    </div>
    <a-divider style="margin: 26px 0" />
    <div class="ele-cell ele-text-center">
      <div class="ele-cell-content" style="font-size: 24px">60</div>
      <div class="ele-cell-content">
        <div class="monitor-face-cry"><i></i></div>
        <div class="ele-text-secondary ele-elip" style="margin-top: 8px">
          负面评论
        </div>
      </div>
      <h2 class="ele-cell-content ele-text-danger">9%</h2>
    </div>
  </a-card>
</template>

<style lang="less" scoped>
  .monitor-face-smile,
  .monitor-face-cry {
    width: 50px;
    height: 50px;
    display: inline-block;
    background: #fbd971;
    border-radius: 50%;
    position: relative;
  }

  .monitor-face-smile > i,
  .monitor-face-smile:before,
  .monitor-face-smile:after,
  .monitor-face-cry > i,
  .monitor-face-cry:before,
  .monitor-face-cry:after {
    width: 28px;
    height: 28px;
    border-radius: 50%;
    transform: rotate(225deg);
    border: 3px solid #f0c419;
    border-right-color: transparent !important;
    border-bottom-color: transparent !important;
    position: absolute;
    bottom: 8px;
    left: 11px;
  }

  .monitor-face-smile:before,
  .monitor-face-smile:after,
  .monitor-face-cry:before,
  .monitor-face-cry:after {
    content: '';
    width: 12px;
    height: 12px;
    left: 8px;
    top: 14px;
    border-color: #f29c1f;
    transform: rotate(45deg);
  }

  .monitor-face-smile:after,
  .monitor-face-cry:after {
    left: auto;
    right: 8px;
  }

  .monitor-face-cry > i {
    transform: rotate(45deg);
    bottom: -6px;
  }
</style>
