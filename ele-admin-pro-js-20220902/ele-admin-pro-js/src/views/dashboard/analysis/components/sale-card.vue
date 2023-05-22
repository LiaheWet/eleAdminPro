<template>
  <a-card :bordered="false" :body-style="{ padding: 0 }">
    <a-tabs
      size="large"
      v-model:activeKey="saleSearch.type"
      class="monitor-card-tabs"
      @change="onSaleTypeChange"
    >
      <a-tab-pane tab="销售额" key="saleroom" />
      <a-tab-pane tab="访问量" key="visits" />
      <template #rightExtra>
        <a-space size="middle" class="analysis-tabs-extra hidden-lg-and-down">
          <a-radio-group v-model:value="saleSearch.dateType">
            <a-radio-button value="1">今天</a-radio-button>
            <a-radio-button value="2">本周</a-radio-button>
            <a-radio-button value="3">本月</a-radio-button>
            <a-radio-button value="4">本年</a-radio-button>
          </a-radio-group>
          <div style="width: 300px">
            <a-range-picker
              value-format="YYYY-MM-DD"
              v-model:value="saleSearch.datetime"
            />
          </div>
        </a-space>
      </template>
    </a-tabs>
    <div style="padding-bottom: 10px">
      <a-row :gutter="16">
        <a-col :lg="17" :md="15" :sm="24" :xs="24">
          <div v-if="saleSearch.type === 'saleroom'" class="demo-monitor-title">
            销售量趋势
          </div>
          <div v-else class="demo-monitor-title">访问量趋势</div>
          <v-chart
            ref="saleChartRef"
            :option="saleChartOption"
            style="height: 320px"
          />
        </a-col>
        <a-col :lg="7" :md="9" :sm="24" :xs="24">
          <div v-if="saleSearch.type === 'saleroom'">
            <div class="demo-monitor-title">门店销售额排名</div>
            <div
              v-for="(item, index) in saleroomRankData"
              :key="index"
              class="demo-monitor-rank-item ele-cell"
            >
              <ele-tag
                shape="circle"
                :color="index < 3 ? '#314659' : ''"
                style="border: none"
              >
                {{ index + 1 }}
              </ele-tag>
              <div class="ele-cell-content ele-elip">{{ item.name }}</div>
              <div class="ele-text-secondary">{{ item.value }}</div>
            </div>
          </div>
          <div v-else>
            <div class="demo-monitor-title">门店访问量排名</div>
            <div
              v-for="(item, index) in visitsRankData"
              :key="index"
              class="demo-monitor-rank-item ele-cell"
            >
              <ele-tag
                shape="circle"
                :color="index < 3 ? '#314659' : ''"
                style="border: none"
              >
                {{ index + 1 }}
              </ele-tag>
              <div class="ele-cell-content ele-elip">{{ item.name }}</div>
              <div class="ele-text-secondary">{{ item.value }}</div>
            </div>
          </div>
        </a-col>
      </a-row>
    </div>
  </a-card>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { use } from 'echarts/core';
  import { CanvasRenderer } from 'echarts/renderers';
  import { BarChart } from 'echarts/charts';
  import { GridComponent, TooltipComponent } from 'echarts/components';
  import VChart from 'vue-echarts';
  import { getSaleroomList } from '@/api/dashboard/analysis';
  import useEcharts from '@/utils/use-echarts';

  use([CanvasRenderer, BarChart, GridComponent, TooltipComponent]);

  //
  const saleChartRef = ref(null);

  useEcharts([saleChartRef]);

  // 销售额柱状图配置
  const saleChartOption = reactive({});

  // 门店销售排名数据
  const saleroomRankData = ref([
    { name: '工专路 1 号店', value: '323,234' },
    { name: '工专路 2 号店', value: '323,234' },
    { name: '工专路 3 号店', value: '323,234' },
    { name: '工专路 4 号店', value: '323,234' },
    { name: '工专路 5 号店', value: '323,234' },
    { name: '工专路 6 号店', value: '323,234' },
    { name: '工专路 7 号店', value: '323,234' }
  ]);

  // 门店访问排名数据
  const visitsRankData = ref([
    { name: '工专路 1 号店', value: '323,234' },
    { name: '工专路 2 号店', value: '323,234' },
    { name: '工专路 3 号店', value: '323,234' },
    { name: '工专路 4 号店', value: '323,234' },
    { name: '工专路 5 号店', value: '323,234' },
    { name: '工专路 6 号店', value: '323,234' },
    { name: '工专路 7 号店', value: '323,234' }
  ]);

  // 销售量趋势数据
  const saleroomData1 = ref([]);

  // 访问量趋势数据
  const saleroomData2 = ref([]);

  // 销售量搜索参数
  const saleSearch = reactive({
    type: 'saleroom',
    dateType: '1',
    datetime: ['2022-01-08', '2022-02-12']
  });

  /* 获取销售量数据 */
  const getSaleroomData = () => {
    getSaleroomList()
      .then((data) => {
        saleroomData1.value = data.list1;
        saleroomData2.value = data.list2;
        onSaleTypeChange();
      })
      .catch((e) => {
        message.error(e.message);
      });
  };

  /* 销售量tab选择改变事件 */
  const onSaleTypeChange = () => {
    if (saleSearch.type === 'saleroom') {
      Object.assign(saleChartOption, {
        tooltip: {
          trigger: 'axis'
        },
        xAxis: [
          {
            type: 'category',
            data: saleroomData1.value.map((d) => d.month)
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            type: 'bar',
            data: saleroomData1.value.map((d) => d.value)
          }
        ]
      });
    } else {
      Object.assign(saleChartOption, {
        tooltip: {
          trigger: 'axis'
        },
        xAxis: [
          {
            type: 'category',
            data: saleroomData2.value.map((d) => d.month)
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            type: 'bar',
            data: saleroomData2.value.map((d) => d.value)
          }
        ]
      });
    }
  };

  getSaleroomData();
</script>

<style lang="less" scoped>
  .monitor-card-tabs :deep(.ant-tabs-nav) {
    padding: 0 16px;
  }

  .demo-monitor-title {
    padding: 6px 20px;
  }

  .demo-monitor-rank-item {
    padding: 0 20px;
    margin-top: 18px;
  }
</style>
