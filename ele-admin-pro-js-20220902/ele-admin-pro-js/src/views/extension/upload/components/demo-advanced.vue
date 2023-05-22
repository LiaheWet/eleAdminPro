<template>
  <a-card title="手动上传" :bordered="false">
    <ele-image-upload
      v-model:value="images"
      :auto-upload="false"
      :before-remove="onBeforeRemove"
    />
    <div class="ele-cell">
      <a-button type="primary" :loading="loading" @click="onSubmit">
        提交
      </a-button>
    </div>
  </a-card>
</template>

<script setup>
  import { ref, createVNode } from 'vue';
  import { message, Modal } from 'ant-design-vue/es';
  import { ExclamationCircleOutlined } from '@ant-design/icons-vue';

  const images = ref([
    {
      uid: 1,
      url: 'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg',
      status: 'done'
    },
    {
      uid: 2,
      url: 'https://cdn.eleadmin.com/20200610/WLXm7gp1EbLDtvVQgkeQeyq5OtDm00Jd.jpg',
      status: 'done'
    },
    {
      uid: 3,
      url: 'https://cdn.eleadmin.com/20200609/f6bc05af944a4f738b54128717952107.jpg',
      status: 'done'
    }
  ]);

  const loading = ref(false);

  /* 手动上传 */
  const onSubmit = () => {
    if (checkDone()) {
      submitForm();
      return;
    }
    loading.value = true;
    images.value.forEach((item) => {
      if (!item.status) {
        uploadItem(item);
      }
    });
  };

  /* 上传图片 */
  const uploadItem = (item) => {
    // 模拟上传
    if (item.progress == null) {
      item.progress = 20;
    } else {
      item.progress += 20;
    }
    item.status = 'uploading';
    const timer = setInterval(() => {
      if (item.progress == null) {
        item.progress = 20;
      } else {
        item.progress += 20;
      }
      if (item.progress === 100) {
        item.status = 'done';
        clearInterval(timer);
        // 每个图片上传完成后都检查是否全部上传完成
        if (checkDone()) {
          submitForm();
        }
      }
    }, Math.round(Math.random() * 2500) + 500);
  };

  /* 检查是否全部上传完毕 */
  const checkDone = () => {
    return !images.value.some((d) => d.status !== 'done');
  };

  /* 全部上传完毕后与其它表单数据一起提交 */
  const submitForm = () => {
    message.success('已全部上传完毕');
    console.log('data:', images.value);
    loading.value = false;
  };

  /* 删除增加确认弹窗 */
  const onBeforeRemove = () => {
    return new Promise((resolve, reject) => {
      Modal.confirm({
        title: '提示',
        content: '确定要删除吗?',
        icon: createVNode(ExclamationCircleOutlined),
        maskClosable: true,
        onOk: () => {
          resolve();
        },
        onCancel: () => {
          reject();
        }
      });
    });
  };
</script>
