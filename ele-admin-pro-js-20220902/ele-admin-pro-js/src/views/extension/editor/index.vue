<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 按钮 -->
      <div style="margin-bottom: 16px">
        <a-space>
          <a-button type="primary" class="ele-btn-icon" @click="setContent">
            修改内容
          </a-button>
          <a-button type="primary" class="ele-btn-icon" @click="showHtml">
            获取html
          </a-button>
          <a-button type="primary" class="ele-btn-icon" @click="showText">
            获取文本
          </a-button>
          <a-button
            type="primary"
            :danger="!disabled"
            class="ele-btn-icon"
            @click="toggleDisabled"
          >
            {{ disabled ? '启用' : '禁用' }}
          </a-button>
        </a-space>
      </div>
      <!-- 编辑器 -->
      <tinymce-editor
        ref="editorRef"
        :init="config"
        v-model:value="content"
        :disabled="disabled"
      />
    </a-card>
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  import { Modal } from 'ant-design-vue/es';
  import { htmlToText } from 'ele-admin-pro/es';
  import TinymceEditor from '@/components/TinymceEditor/index.vue';

  const editorRef = ref(null);

  const config = ref({
    height: 520,
    // 自定义文件上传(这里使用把选择的文件转成 blob 演示)
    file_picker_callback: (callback, _value, meta) => {
      const input = document.createElement('input');
      input.setAttribute('type', 'file');
      // 设定文件可选类型
      if (meta.filetype === 'image') {
        input.setAttribute('accept', 'image/*');
      } else if (meta.filetype === 'media') {
        input.setAttribute('accept', 'video/*');
      }
      input.onchange = () => {
        const file = input.files?.[0];
        if (!file) {
          return;
        }
        if (meta.filetype === 'media') {
          if (!file.type.startsWith('video/')) {
            editorRef.value?.alert({ content: '只能选择视频文件' });
            return;
          }
        }
        if (file.size / 1024 / 1024 > 20) {
          editorRef.value?.alert({ content: '大小不能超过 20MB' });
          return;
        }
        const reader = new FileReader();
        reader.onload = (e) => {
          if (e.target?.result != null) {
            const blob = new Blob([e.target.result], { type: file.type });
            callback(URL.createObjectURL(blob));
          }
        };
        reader.readAsArrayBuffer(file);
      };
      input.click();
    }
  });

  const content = ref('');

  const disabled = ref(false);

  /* 获取编辑器内容 */
  const showHtml = () => {
    Modal.info({
      maskClosable: true,
      content: content.value
    });
  };

  /* 获取编辑器纯文本内容 */
  const showText = () => {
    Modal.info({
      maskClosable: true,
      content: htmlToText(content.value)
    });
  };

  /* 修改编辑器内容 */
  const setContent = () => {
    content.value = [
      '<div style="text-align: center;color: #fff;background-image: linear-gradient(-90deg, rgb(62,119,255), rgb(159,98,212), rgb(255,78,170));padding: 32px 0;">',
      '   <div style="font-size: 28px;margin-bottom: 16px;">EleAdminPro后台管理模板</div>',
      '   <div style="font-size:18px">通用型后台管理模板，界面美观、开箱即用，拥有丰富的组件和模板</div>',
      '</div><br/>'
    ].join('');
  };

  /* 禁用启用切换 */
  const toggleDisabled = () => {
    disabled.value = !disabled.value;
  };
</script>

<script>
  export default {
    name: 'ExtensionEditor'
  };
</script>
