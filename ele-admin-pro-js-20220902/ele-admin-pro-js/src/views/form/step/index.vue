<template>
  <div>
    <a-page-header :ghost="false" title="分步表单">
      <div class="ele-text-secondary">
        将一个冗长或用户不熟悉的表单任务分成多个步骤，指导用户完成。
      </div>
    </a-page-header>
    <div class="ele-body">
      <a-card :bordered="false">
        <div style="max-width: 800px; margin: 0 auto">
          <div style="margin: 10px 0 30px 0">
            <a-steps :current="active">
              <a-step title="第一步" description="填写转账信息" />
              <a-step title="第二步" description="确认转账信息" />
              <a-step title="第三步" description="转账成功" />
            </a-steps>
          </div>
          <step-edit v-if="active === 0" @done="onDone" />
          <step-confirm
            v-if="active === 1"
            :data="form"
            @done="onNext"
            @back="onBack"
          />
          <step-success v-if="active === 2" :data="form" @back="onBack" />
        </div>
        <div v-if="active === 0">
          <a-divider style="margin: 35px 0 25px 0" />
          <a-alert type="info">
            <template #description>
              <h6 style="margin: 5px 0 15px 0">说明</h6>
              <h6 style="margin-bottom: 10px">转账到支付宝</h6>
              <p style="margin-bottom: 15px">
                如果需要，这里可以放一些关于产品的常见问题说明。如果需要，
                这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。
              </p>
              <h6 style="margin-bottom: 10px">转账到微信</h6>
              <p style="margin-bottom: 15px">
                如果需要，这里可以放一些关于产品的常见问题说明。如果需要，
                这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。
              </p>
            </template>
          </a-alert>
        </div>
      </a-card>
    </div>
  </div>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import StepEdit from './components/step-edit.vue';
  import StepConfirm from './components/step-confirm.vue';
  import StepSuccess from './components/step-success.vue';

  // 选中步骤
  const active = ref(0);

  //
  const form = reactive({});

  //
  const onDone = (data) => {
    Object.assign(form, data);
    active.value = 1;
  };

  //
  const onNext = () => {
    active.value = 2;
  };

  //
  const onBack = () => {
    active.value = 0;
  };
</script>

<script>
  export default {
    name: 'FormStep'
  };
</script>
