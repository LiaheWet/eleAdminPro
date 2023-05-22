<template>
  <div class="ele-body">
    <a-card title="基本信息" :bordered="false">
      <a-form
        class="ele-form-detail"
        :label-col="{ md: 2, sm: 4, xs: 6 }"
        :wrapper-col="{ md: 22, sm: 20, xs: 18 }"
      >
        <a-form-item label="账号">
          <div class="ele-text-secondary">{{ form.username }}</div>
        </a-form-item>
        <a-form-item label="用户名">
          <div class="ele-text-secondary">{{ form.nickname }}</div>
        </a-form-item>
        <a-form-item label="性别">
          <div class="ele-text-secondary">{{ form.sexName }}</div>
        </a-form-item>
        <a-form-item label="手机号">
          <div class="ele-text-secondary">{{ form.phone }}</div>
        </a-form-item>
        <a-form-item label="角色">
          <a-tag v-for="item in form.roles" :key="item.roleId" color="blue">
            {{ item.roleName }}
          </a-tag>
        </a-form-item>
        <a-form-item label="创建时间">
          <div class="ele-text-secondary">{{ form.createTime }}</div>
        </a-form-item>
        <a-form-item label="状态">
          <a-badge
            v-if="typeof form.status === 'number'"
            :status="['processing', 'error'][form.status]"
            :text="['正常', '冻结'][form.status]"
          />
        </a-form-item>
      </a-form>
    </a-card>
  </div>
</template>

<script setup>
  import { ref, watch, unref } from 'vue';
  import { useRouter } from 'vue-router';
  import { message } from 'ant-design-vue/es';
  import { toDateString } from 'ele-admin-pro/es';
  import useFormData from '@/utils/use-form-data';
  import { setPageTabTitle } from '@/utils/page-tab-util';
  import { getUser } from '@/api/system/user';
  const ROUTE_PATH = '/list/basic/details';

  const { currentRoute } = useRouter();

  // 用户信息
  const { form, assignFields } = useFormData({
    userId: undefined,
    username: '',
    nickname: '',
    sexName: '',
    phone: '',
    roles: [],
    createTime: undefined,
    status: undefined
  });

  // 请求状态
  const loading = ref(true);

  const query = () => {
    const { params } = unref(currentRoute);
    const id = params.id;
    if (!id || form.userId === Number(id)) {
      return;
    }
    loading.value = true;
    getUser(Number(id))
      .then((data) => {
        loading.value = false;
        assignFields({
          ...data,
          createTime: toDateString(data.createTime)
        });
        // 修改页签标题
        if (unref(currentRoute).path.startsWith(ROUTE_PATH)) {
          setPageTabTitle(data.nickname + '的信息');
        }
      })
      .catch((e) => {
        loading.value = false;
        message.error(e.message);
      });
  };

  watch(
    currentRoute,
    (route) => {
      const { fullPath } = unref(route);
      if (!fullPath.startsWith(ROUTE_PATH)) {
        return;
      }
      query();
    },
    { immediate: true }
  );
</script>

<script>
  export default {
    name: 'ListBasicDetails'
  };
</script>
