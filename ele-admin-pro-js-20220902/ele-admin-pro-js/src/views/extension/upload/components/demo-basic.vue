<template>
  <a-card title="基础示例" :bordered="false">
    <ele-image-upload
      v-model:value="images"
      :limit="8"
      :disabled="disabled"
      :before-upload="onBeforeUpload"
      :drag="true"
      @upload="onUpload"
      @item-click="onItemClick"
    />
    <div class="ele-cell">
      <a-button type="primary" @click="getData">获取数据</a-button>
      <div style="line-height: 22px"><em></em>禁用:</div>
      <div class="ele-cell-content">
        <a-radio-group v-model:value="disabled">
          <a-radio :value="false">否</a-radio>
          <a-radio :value="true">是</a-radio>
        </a-radio-group>
      </div>
    </div>
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

  // 禁用
  const disabled = ref(false);

  /* 获取当前数据 */
  const getData = () => {
    console.log('data:', images.value);
    message.success('数据已打印在控制台');
  };

  /* 上传事件 */
  const onUpload = (d) => {
    const item = images.value.find((t) => t.uid === d.uid) ?? d;
    console.log('item:', item);
    // 模拟上传
    if (images.value.length !== 5) {
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
    } else {
      item.status = 'uploading';
      if (item.progress == null) {
        item.progress = 20;
      } else if (item.progress < 80) {
        item.progress += 20;
      }
      setTimeout(() => {
        item.status = 'exception';
        message.error('上传失败, 服务器繁忙');
      }, 2000);
    }
  };

  /* 上传前钩子 */
  const onBeforeUpload = (file) => {
    if (!file.type.startsWith('image')) {
      message.error('只能选择图片');
      return false;
    }
    if (file.size / 1024 / 1024 > 2) {
      message.error('大小不能超过 2MB');
      return false;
    }
  };

  /* item 点击事件 */
  const onItemClick = (item) => {
    console.log('item-click:', item);
  };
</script>
