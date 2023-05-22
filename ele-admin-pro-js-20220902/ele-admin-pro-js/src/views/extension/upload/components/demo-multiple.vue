<template>
  <a-card title="支持多选" :bordered="false">
    <ele-image-upload
      v-model:value="images"
      :limit="8"
      :drag="true"
      :multiple="true"
      :upload-handler="uploadHandler"
      @upload="onUpload"
    />
    <a-button type="primary" @click="getData">获取数据</a-button>
  </a-card>
</template>

<script setup>
  import { ref } from 'vue';
  import { message } from 'ant-design-vue/es';

  // 已上传数据
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

  /* 获取当前数据 */
  const getData = () => {
    console.log('data:', images.value);
    message.success('数据已打印在控制台');
  };

  /* 上传事件 */
  const uploadHandler = (file) => {
    const item = {
      file,
      uid: file.uid,
      name: file.name,
      progress: 0,
      status: undefined
    };
    if (!file.type.startsWith('image')) {
      message.error('只能选择图片');
      return;
    }
    if (file.size / 1024 / 1024 > 2) {
      message.error('大小不能超过 2MB');
      return;
    }
    item.url = window.URL.createObjectURL(file);
    images.value.push(item);
    onUpload(item);
  };

  /* 上传 item */
  const onUpload = (d) => {
    const item = images.value.find((t) => t.uid === d.uid) ?? d;
    console.log('item:', item);
    // 模拟上传
    item.status = 'uploading';
    item.progress = 20;
    const timer = setInterval(() => {
      if (item.progress == null) {
        item.progress = 20;
      } else {
        item.progress += 20;
      }
      if (item.progress === 100) {
        item.status = 'done';
        clearInterval(timer);
      }
    }, 1000);
  };
</script>
