<!-- 用户导入弹窗 -->
<template>
  <ele-modal
    :width="520"
    :footer="null"
    title="导入用户"
    :visible="visible"
    @update:visible="updateVisible"
  >
    <a-spin :spinning="loading">
      <a-upload-dragger
        accept=".xls,.xlsx"
        :show-upload-list="false"
        :customRequest="doUpload"
        style="padding: 24px 0; margin-bottom: 16px"
      >
        <p class="ant-upload-drag-icon">
          <cloud-upload-outlined />
        </p>
        <p class="ant-upload-hint">将文件拖到此处，或点击上传</p>
      </a-upload-dragger>
    </a-spin>
    <div class="ele-text-center">
      <span>只能上传xls、xlsx文件，</span>
      <a
        href="https://cdn.eleadmin.com/20200610/用户导入模板.xlsx"
        download="用户导入模板.xlsx"
      >
        下载模板
      </a>
    </div>
  </ele-modal>
</template>

<script setup>
  import { ref } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { CloudUploadOutlined } from '@ant-design/icons-vue';
  import { importUsers } from '@/api/system/user';

  const emit = defineEmits(['done', 'update:visible']);

  defineProps({
    // 是否打开弹窗
    visible: Boolean
  });

  // 导入请求状态
  const loading = ref(false);

  /* 上传 */
  const doUpload = ({ file }) => {
    if (
      ![
        'application/vnd.ms-excel',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      ].includes(file.type)
    ) {
      message.error('只能选择 excel 文件');
      return false;
    }
    if (file.size / 1024 / 1024 > 10) {
      message.error('大小不能超过 10MB');
      return false;
    }
    loading.value = true;
    importUsers(file)
      .then((msg) => {
        loading.value = false;
        message.success(msg);
        updateVisible(false);
        emit('done');
      })
      .catch((e) => {
        loading.value = false;
        message.error(e.message);
      });
    return false;
  };

  /* 更新 visible */
  const updateVisible = (value) => {
    emit('update:visible', value);
  };
</script>
