<template>
  <a-row :gutter="16">
    <a-col :lg="8" :md="24" :sm="24" :xs="24">
      <a-card title="宫格拖拽排序" :bordered="false">
        <div class="demo-drag-grid">
          <vue-draggable
            v-model="grid"
            item-key="id"
            :animation="300"
            :set-data="() => void 0"
          >
            <template #item="{ element }">
              <div class="demo-drag-grid-item">{{ element.name }}</div>
            </template>
          </vue-draggable>
        </div>
      </a-card>
    </a-col>
    <a-col :lg="16" :md="24" :sm="24" :xs="24">
      <a-card title="宫格相互拖拽" :bordered="false">
        <a-row :gutter="16">
          <a-col :span="12">
            <div class="demo-drag-grid">
              <vue-draggable
                v-model="grid1"
                item-key="id"
                :animation="300"
                group="demoDragGrid"
                :set-data="() => void 0"
              >
                <template #item="{ element }">
                  <div class="demo-drag-grid-item">{{ element.name }}</div>
                </template>
              </vue-draggable>
            </div>
          </a-col>
          <a-col :span="12">
            <div class="demo-drag-grid">
              <vue-draggable
                v-model="grid2"
                item-key="id"
                :animation="300"
                group="demoDragGrid"
                :set-data="() => void 0"
              >
                <template #item="{ element }">
                  <div class="demo-drag-grid-item">{{ element.name }}</div>
                </template>
              </vue-draggable>
            </div>
          </a-col>
        </a-row>
      </a-card>
    </a-col>
  </a-row>
</template>

<script setup>
  import { ref } from 'vue';
  import VueDraggable from 'vuedraggable';

  const grid = ref([
    { id: 1, name: '001' },
    { id: 2, name: '002' },
    { id: 3, name: '003' },
    { id: 4, name: '004' },
    { id: 5, name: '005' },
    { id: 6, name: '006' }
  ]);

  const grid1 = ref([
    { id: 1, name: '001' },
    { id: 2, name: '002' },
    { id: 3, name: '003' },
    { id: 4, name: '004' },
    { id: 5, name: '005' },
    { id: 6, name: '006' }
  ]);

  const grid2 = ref([
    { id: 7, name: '007' },
    { id: 8, name: '008' },
    { id: 9, name: '009' },
    { id: 10, name: '010' },
    { id: 11, name: '011' },
    { id: 12, name: '012' }
  ]);
</script>

<style lang="less" scoped>
  @import 'ant-design-vue/es/style/themes/default.less';

  .demo-drag-grid {
    position: relative;

    & > div {
      border: 1px solid @border-color-split;
      border-right: none;
      border-bottom: none;
      display: grid;
      grid-template-columns: repeat(3, 33.33%);
      min-height: 201px;
    }

    &:before,
    &:after {
      content: '';
      position: absolute;
      background: @border-color-split;
      bottom: 0;
      right: 0;
    }

    &:before {
      width: 1px;
      top: 0;
    }

    &:after {
      height: 1px;
      left: 0;
    }
  }

  .demo-drag-grid-item {
    cursor: move;
    border: 1px solid @border-color-split;
    border-top: none;
    border-left: none;
    height: 100px;
    line-height: 100px;
    white-space: nowrap;
    word-break: break-all;
    overflow: hidden;
    text-overflow: ellipsis;
    text-align: center;

    &.sortable-chosen {
      background: hsla(0, 0%, 60%, 0.1);
    }
  }
</style>
