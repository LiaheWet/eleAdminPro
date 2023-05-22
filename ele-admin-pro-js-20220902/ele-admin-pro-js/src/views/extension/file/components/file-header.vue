<!-- 文件目录面包屑 -->
<template>
  <div class="ele-file-breadcrumb-group ele-cell">
    <div class="ele-cell-content ele-cell">
      <div
        v-if="directorys.length"
        class="ele-file-breadcrumb-back ele-text-primary"
        @click="goBack"
      >
        返回上一级
      </div>
      <div class="ele-file-breadcrumb-list ele-cell-content ele-cell">
        <div
          :class="[
            'ele-file-breadcrumb-item ele-cell',
            { 'ele-text-primary': !!directorys.length }
          ]"
          @click="goRoot"
        >
          <div class="ele-file-breadcrumb-item-title">全部文件</div>
          <right-outlined v-if="directorys.length" class="ele-text-secondary" />
        </div>
        <div
          v-for="(item, i) in directorys"
          :key="item.id"
          :class="[
            'ele-file-breadcrumb-item ele-cell',
            { 'ele-text-primary': i !== directorys.length - 1 }
          ]"
          @click="goDirectory(i)"
        >
          <div class="ele-file-breadcrumb-item-title">{{ item.name }}</div>
          <right-outlined
            v-if="i !== directorys.length - 1"
            class="ele-text-secondary"
          />
        </div>
      </div>
    </div>
    <div class="hidden-xs-only">已全部加载，共 {{ total }} 个</div>
  </div>
</template>

<script setup>
  import { RightOutlined } from '@ant-design/icons-vue';

  const props = defineProps({
    // 文件夹数据
    directorys: Array,
    // 总文件数
    total: Number
  });

  const emit = defineEmits(['update:directorys']);

  /* 回到上级 */
  const goBack = () => {
    emit(
      'update:directorys',
      props.directorys.slice(0, props.directorys.length - 1)
    );
  };

  /* 回到根目录 */
  const goRoot = () => {
    if (props.directorys.length) {
      emit('update:directorys', []);
    }
  };

  /* 回到指定目录 */
  const goDirectory = (index) => {
    if (index !== props.directorys.length - 1) {
      emit('update:directorys', props.directorys.slice(0, index + 1));
    }
  };
</script>

<style lang="less" scoped>
  /* 文件目录面包屑 */
  .ele-file-breadcrumb-group {
    line-height: 1;
  }

  .ele-file-breadcrumb-back {
    padding-right: 12px;
    border-right: 1px solid hsla(0, 0%, 60%, 0.3);
  }

  .ele-file-breadcrumb-back:hover,
  .ele-file-breadcrumb-item.ele-text-primary:hover
    > .ele-file-breadcrumb-item-title {
    text-decoration: underline;
    cursor: pointer;
  }

  .ele-file-breadcrumb-item .anticon {
    margin: 0 4px;
    font-size: 12px;
  }

  @media screen and (max-width: 768px) {
    .ele-table-tool > .ele-table-tool-title + div,
    .ele-file-breadcrumb-group > .ele-cell-content + div {
      display: none;
    }
  }
</style>
