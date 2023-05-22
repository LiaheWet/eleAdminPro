<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <a-spin :spinning="loading">
        <edit-form :data="user" />
      </a-spin>
    </a-card>
  </div>
</template>

<script setup>
  import { ref, unref } from 'vue';
  import { useRouter } from 'vue-router';
  import { message } from 'ant-design-vue/es';
  import EditForm from '../components/edit-form.vue';
  import { getUser } from '@/api/system/user';

  const { currentRoute } = useRouter();

  // 查询状态
  const loading = ref(true);

  // 用户信息
  const user = ref();

  /* 查询用户信息 */
  const query = () => {
    const { query } = unref(currentRoute);
    if (query.id) {
      getUser(Number(query.id))
        .then((data) => {
          loading.value = false;
          user.value = data;
        })
        .catch((e) => {
          message.error(e.message);
        });
    }
  };

  query();
</script>

<script>
  export default {
    name: 'ListBasicEdit'
  };
</script>
