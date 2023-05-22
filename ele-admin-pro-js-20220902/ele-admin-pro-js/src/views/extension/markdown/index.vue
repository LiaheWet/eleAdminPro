<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 按钮 -->
      <div style="margin-bottom: 16px">
        <a-space>
          <a-button type="primary" class="ele-btn-icon" @click="setContent">
            修改内容
          </a-button>
          <a-button type="primary" class="ele-btn-icon" @click="showText">
            获取内容
          </a-button>
        </a-space>
      </div>
      <!-- 编辑器 -->
      <byte-md-editor
        v-model:value="content"
        :locale="zh_Hans"
        :plugins="plugins"
        height="600px"
        :editorConfig="{ lineNumbers: true }"
      />
    </a-card>
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import { Modal } from 'ant-design-vue/es';
  import ByteMdEditor from '@/components/ByteMdEditor/index.vue';
  // 中文语言文件
  import zh_Hans from 'bytemd/locales/zh_Hans.json';
  // 链接、删除线、复选框、表格等的插件
  import gfm from '@bytemd/plugin-gfm';
  // 插件的中文语言文件
  import zh_HansGfm from '@bytemd/plugin-gfm/locales/zh_Hans.json';
  // 预览界面的样式，这里用的 github 的 markdown 主题
  import 'github-markdown-css/github-markdown-light.css';

  // 编辑器内容，双向绑定
  const content = ref('');

  // 插件
  const plugins = ref([
    gfm({
      locale: zh_HansGfm
    })
  ]);

  /* 获取编辑器内容 */
  const showText = () => {
    Modal.info({
      maskClosable: true,
      content: content.value
    });
  };

  /* 修改编辑器内容 */
  const setContent = () => {
    content.value = '> **EleAdminPro**后台管理模板';
  };
</script>

<script>
  export default {
    name: 'ExtensionMarkdown'
  };
</script>
