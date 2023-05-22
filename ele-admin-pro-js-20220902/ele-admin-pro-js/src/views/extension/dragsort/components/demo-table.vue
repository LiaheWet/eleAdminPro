<template>
  <a-card title="表格拖拽排序" :bordered="false">
    <template #extra>
      <a @click="viewData">查看数据</a>
    </template>
    <a-row :gutter="16">
      <a-col
        v-for="(item, index) in taskList"
        :key="index"
        :lg="8"
        :md="24"
        :sm="24"
        :xs="24"
      >
        <table class="ele-table ele-table-border ele-table-medium">
          <colgroup>
            <col width="40" />
            <col />
            <col width="80" />
          </colgroup>
          <thead>
            <tr>
              <th></th>
              <th>任务名称</th>
              <th style="text-align: center">状态</th>
            </tr>
          </thead>
          <vue-draggable
            tag="tbody"
            item-key="id"
            :animation="300"
            :modelValue="item"
            group="demoDragTable"
            handle=".demo-table-drag-handle"
            :set-data="() => void 0"
            @update:modelValue="(value) => updateModelValue(value, index)"
          >
            <template #item="{ element }">
              <tr>
                <td style="text-align: center">
                  <drag-outlined
                    class="demo-table-drag-handle ele-text-secondary"
                    style="cursor: move"
                  />
                </td>
                <td>{{ element.taskName }}</td>
                <td style="text-align: center">
                  <span
                    :class="
                      ['ele-text-warning', 'ele-text-success', 'ele-text-info'][
                        element.status
                      ]
                    "
                  >
                    {{ ['未开始', '进行中', '已完成'][element.status] }}
                  </span>
                </td>
              </tr>
            </template>
            <template #footer v-if="!item.length">
              <tr style="background: none">
                <td colspan="3">
                  <div class="ele-text-secondary ele-text-center">暂无数据</div>
                </td>
              </tr>
            </template>
          </vue-draggable>
        </table>
      </a-col>
    </a-row>
  </a-card>
  <ele-modal v-model:visible="visible" title="拖拽后数据" :footer="null">
    <div style="max-height: 240px; overflow: auto">
      <pre>{{ result }}</pre>
    </div>
  </ele-modal>
</template>

<script setup>
  import { ref } from 'vue';
  import { DragOutlined } from '@ant-design/icons-vue';
  import VueDraggable from 'vuedraggable';
  import 'ant-design-vue/es/table/style';

  //
  const taskList = ref([]);

  //
  const result = ref('');

  //
  const visible = ref(false);

  /* 更新数据 */
  const updateModelValue = (value, index) => {
    taskList.value[index] = value;
  };

  /* 查看数据 */
  const viewData = () => {
    result.value = JSON.stringify(taskList.value, null, 4);
    visible.value = true;
  };

  // 处理数据
  const temp = [];
  for (let i = 0; i < 18; i++) {
    const index = parseInt(String(i / 6));
    if (temp[index] == null) {
      temp[index] = [];
    }
    temp[index].push({
      id: i,
      taskName: '测试任务' + (i + 1),
      status: 0
    });
  }
  taskList.value = temp;
</script>

<style lang="less" scoped>
  /* 表格行拖拽按下去样式 */
  .ele-table tr.sortable-chosen {
    background: hsla(0, 0%, 60%, 0.1);
  }
</style>
