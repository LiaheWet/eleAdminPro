<!-- 我的任务 -->
<template>
  <a-card
    :title="title"
    :bordered="false"
    :body-style="{ padding: '10px', height: '358px' }"
    class="workplace-table-card"
  >
    <template #extra>
      <more-icon @remove="onRemove" @edit="onEdit" />
    </template>
    <div style="overflow: auto; position: relative">
      <table class="ele-table" style="table-layout: fixed; min-width: 300px">
        <colgroup>
          <col width="38" />
          <col width="65" />
          <col />
          <col width="70" />
        </colgroup>
        <thead>
          <tr>
            <th style="position: sticky; left: 0"></th>
            <th style="text-align: center">优先级</th>
            <th>任务名称</th>
            <th style="text-align: center">状态</th>
          </tr>
        </thead>
        <vue-draggable
          tag="tbody"
          item-key="id"
          v-model="taskList"
          handle=".sort-handle"
          :animation="300"
          :set-data="() => void 0"
        >
          <template #item="{ element }">
            <tr>
              <td style="text-align: center; position: sticky; left: 0">
                <menu-outlined class="sort-handle ele-text-secondary" />
              </td>
              <td style="text-align: center">
                <ele-tag
                  :color="['red', 'orange', 'blue'][element.priority - 1]"
                  shape="circle"
                >
                  {{ element.priority }}
                </ele-tag>
              </td>
              <td class="ele-elip" :title="element.taskName">
                <a>{{ element.taskName }}</a>
              </td>
              <td style="text-align: center">
                <span v-if="element.status === 0" class="ele-text-warning">
                  未开始
                </span>
                <span v-else-if="element.status === 1" class="ele-text-success">
                  进行中
                </span>
                <span v-else-if="element.status === 2" class="ele-text-info">
                  已完成
                </span>
              </td>
            </tr>
          </template>
        </vue-draggable>
      </table>
    </div>
  </a-card>
</template>

<script setup>
  import { ref } from 'vue';
  import VueDraggable from 'vuedraggable';
  import { MenuOutlined } from '@ant-design/icons-vue';
  import MoreIcon from './more-icon.vue';

  defineProps({
    title: String
  });

  const emit = defineEmits(['remove', 'edit']);

  // 我的任务数据
  const taskList = ref([]);

  /* 查询我的任务 */
  const queryTaskList = () => {
    taskList.value = [
      {
        id: 1,
        priority: 1,
        taskName: '解决项目一的bug',
        status: 0
      },
      {
        id: 2,
        priority: 2,
        taskName: '解决项目二的bug',
        status: 0
      },
      {
        id: 3,
        priority: 2,
        taskName: '解决项目三的bug',
        status: 1
      },
      {
        id: 4,
        priority: 3,
        taskName: '解决项目四的bug',
        status: 1
      },
      {
        id: 5,
        priority: 3,
        taskName: '解决项目五的bug',
        status: 2
      },
      {
        id: 6,
        priority: 3,
        taskName: '解决项目六的bug',
        status: 2
      }
    ];
  };

  const onRemove = () => {
    emit('remove');
  };

  const onEdit = () => {
    emit('edit');
  };

  queryTaskList();
</script>

<style lang="less" scoped>
  .ele-table tr.sortable-chosen {
    background: hsla(0, 0%, 60%, 0.1);
  }

  .workplace-table-card .sort-handle {
    cursor: move;
  }
</style>
