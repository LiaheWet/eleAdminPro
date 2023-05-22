<template>
  <a-row :gutter="16">
    <a-col :lg="8" :md="24" :sm="24" :xs="24">
      <a-card title="列表拖拽排序" :bordered="false">
        <div class="demo-drag-list">
          <vue-draggable
            v-model="list"
            item-key="id"
            :animation="300"
            handle=".sort-handle"
            :set-data="() => void 0"
          >
            <template #item="{ element }">
              <div class="demo-drag-list-item ele-cell">
                <drag-outlined class="sort-handle ele-text-secondary" />
                <div class="ele-cell-content">{{ element.name }}</div>
              </div>
            </template>
          </vue-draggable>
        </div>
      </a-card>
    </a-col>
    <a-col :lg="16" :md="24" :sm="24" :xs="24">
      <a-card title="列表相互拖拽" :bordered="false">
        <a-row :gutter="16">
          <a-col :span="12">
            <div class="demo-drag-list">
              <vue-draggable
                v-model="list1"
                item-key="id"
                :animation="300"
                handle=".sort-handle"
                group="demoDragList"
                :set-data="() => void 0"
              >
                <template #item="{ element }">
                  <div class="demo-drag-list-item ele-cell">
                    <drag-outlined class="sort-handle ele-text-secondary" />
                    <div class="ele-cell-content">{{ element.name }}</div>
                  </div>
                </template>
              </vue-draggable>
            </div>
          </a-col>
          <a-col :span="12">
            <div class="demo-drag-list">
              <vue-draggable
                v-model="list2"
                item-key="id"
                :animation="300"
                handle=".sort-handle"
                group="demoDragList"
                :set-data="() => void 0"
              >
                <template #item="{ element }">
                  <div class="demo-drag-list-item ele-cell">
                    <drag-outlined class="sort-handle ele-text-secondary" />
                    <div class="ele-cell-content">{{ element.name }}</div>
                  </div>
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
  import { DragOutlined } from '@ant-design/icons-vue';
  import VueDraggable from 'vuedraggable';

  const list = ref([
    { id: 1, name: '项目0000001' },
    { id: 2, name: '项目0000002' },
    { id: 3, name: '项目0000003' },
    { id: 4, name: '项目0000004' },
    { id: 5, name: '项目0000005' }
  ]);

  const list1 = ref([
    { id: 1, name: '项目0000001' },
    { id: 2, name: '项目0000002' },
    { id: 3, name: '项目0000003' },
    { id: 4, name: '项目0000004' },
    { id: 5, name: '项目0000005' }
  ]);

  const list2 = ref([
    { id: 6, name: '项目0000006' },
    { id: 7, name: '项目0000007' },
    { id: 8, name: '项目0000008' },
    { id: 9, name: '项目0000009' },
    { id: 10, name: '项目0000010' }
  ]);
</script>

<style lang="less" scoped>
  .demo-drag-list > div {
    border: 1px solid hsla(0, 0%, 60%, 0.2);
    min-height: 81px;
  }

  .demo-drag-list-item {
    line-height: 1;
    padding: 12px 16px;

    & + .demo-drag-list-item {
      border-top: 1px solid hsla(0, 0%, 60%, 0.2);
    }

    &.sortable-chosen {
      background: hsla(0, 0%, 60%, 0.1);
    }

    .sort-handle {
      cursor: move;
      font-size: 16px;
    }
  }
</style>
