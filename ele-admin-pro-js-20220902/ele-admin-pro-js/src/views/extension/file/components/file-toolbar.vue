<template>
  <ele-toolbar>
    <a-space>
      <a-upload :show-upload-list="false" :customRequest="onUpload">
        <a-button type="primary" class="ele-btn-icon">
          <template #icon>
            <upload-outlined />
          </template>
          <span>上传</span>
        </a-button>
      </a-upload>
      <a-button type="dashed" class="ele-btn-icon" @click="openFolderAdd">
        <template #icon>
          <folder-add-outlined />
        </template>
        <span>新建文件夹</span>
      </a-button>
      <a-button
        danger
        type="dashed"
        :disabled="!checked.length"
        :class="['ele-btn-icon', { 'hidden-xs-only': styleResponsive }]"
        @click="removeBatch"
      >
        <template #icon>
          <delete-outlined />
        </template>
        <span>删除</span>
      </a-button>
    </a-space>
    <template #action>
      <!-- 搜索框 -->
      <div
        style="max-width: 240px"
        :class="{ 'hidden-sm-and-down': styleResponsive }"
      >
        <a-input-search v-model:value="search" placeholder="搜索您的文件" />
      </div>
      <!-- 显示方式切换 -->
      <menu-outlined
        v-if="grid"
        class="ele-file-tool-btn"
        @click="toggleShowType"
      />
      <appstore-outlined
        v-else
        class="ele-file-tool-btn"
        @click="toggleShowType"
      />
    </template>
  </ele-toolbar>
  <!-- 新建文件夹弹窗 -->
  <folder-add
    v-model:visible="folderAddVisible"
    :parent-id="parentId"
    @done="onDone"
  />
</template>

<script setup>
  import { ref, createVNode } from 'vue';
  import { message, Modal } from 'ant-design-vue/es';
  import {
    MenuOutlined,
    AppstoreOutlined,
    DeleteOutlined,
    UploadOutlined,
    FolderAddOutlined,
    ExclamationCircleOutlined
  } from '@ant-design/icons-vue';
  import { messageLoading } from 'ele-admin-pro/es';
  import { storeToRefs } from 'pinia';
  import { useThemeStore } from '@/store/modules/theme';
  import { uploadFile } from '@/api/system/file';
  import { addUserFile, removeUserFiles } from '@/api/system/user-file';
  import FolderAdd from './folder-add.vue';

  // 是否开启响应式布局
  const themeStore = useThemeStore();
  const { styleResponsive } = storeToRefs(themeStore);

  const props = defineProps({
    // 是否网格展示
    grid: Boolean,
    // 选中数据
    checked: Array,
    // 父级 id
    parentId: Number
  });

  const emit = defineEmits(['update:grid', 'done']);

  // 搜索关键字
  const search = ref('');

  // 新建文件夹弹窗是否打开
  const folderAddVisible = ref(false);

  /* 上传 */
  const onUpload = ({ file }) => {
    if (file.size / 1024 / 1024 > 100) {
      message.error('大小不能超过 100MB');
      return false;
    }
    const hide = messageLoading('上传中..', 0);
    uploadFile(file)
      .then((data) => {
        addUserFile({
          name: data.name,
          isDirectory: 0,
          parentId: props.parentId,
          path: data.path,
          length: data.length,
          contentType: data.contentType
        })
          .then(() => {
            hide();
            message.success('上传成功');
            onDone();
          })
          .catch((e) => {
            hide();
            message.error(e.message);
          });
      })
      .catch((e) => {
        hide();
        message.error(e.message);
      });
    return false;
  };

  /* 打开新建文件夹弹窗 */
  const openFolderAdd = () => {
    folderAddVisible.value = true;
  };

  /* 批量删除 */
  const removeBatch = () => {
    Modal.confirm({
      title: '提示',
      content: '确定要删除选中的文件吗?',
      icon: createVNode(ExclamationCircleOutlined),
      maskClosable: true,
      onOk: () => {
        const hide = messageLoading('请求中..', 0);
        removeUserFiles(props.checked.map((d) => d.id))
          .then((msg) => {
            hide();
            message.success(msg);
            onDone();
          })
          .catch((e) => {
            hide();
            message.error(e.message);
          });
      }
    });
  };

  /* 完成刷新列表数据 */
  const onDone = () => {
    emit('done');
  };

  /* 显示方式切换 */
  const toggleShowType = () => {
    emit('update:grid', !props.grid);
  };
</script>

<style lang="less" scoped>
  /* 图标按钮 */
  .ele-file-tool-btn {
    font-size: 20px;
    margin-left: 16px;
    cursor: pointer;
  }
</style>
