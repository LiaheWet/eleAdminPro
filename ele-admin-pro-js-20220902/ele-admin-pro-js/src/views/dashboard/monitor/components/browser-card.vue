<template>
  <a-card :bordered="false" title="浏览器分布" :body-style="{ padding: '0px' }">
    <v-chart
      ref="browserChartRef"
      :option="browserChartOption"
      style="height: 222px"
    />
  </a-card>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { use } from 'echarts/core';
  import { CanvasRenderer } from 'echarts/renderers';
  import { PieChart } from 'echarts/charts';
  import { TooltipComponent, LegendComponent } from 'echarts/components';
  import VChart from 'vue-echarts';
  import { getBrowserCountList } from '@/api/dashboard/monitor';
  import useEcharts from '@/utils/use-echarts';

  use([CanvasRenderer, PieChart, TooltipComponent, LegendComponent]);

  //
  const browserChartRef = ref(null);

  useEcharts([browserChartRef]);

  // 浏览器分布饼图配置
  const browserChartOption = reactive({});

  /* 获取用户浏览器分布数据 */
  const getBrowserCountData = () => {
    getBrowserCountList()
      .then((data) => {
        Object.assign(browserChartOption, {
          tooltip: {
            trigger: 'item',
            confine: true,
            borderWidth: 1
          },
          legend: {
            bottom: 5,
            itemWidth: 10,
            itemHeight: 10,
            icon: 'circle',
            data: data.map((d) => d.name)
          },
          series: [
            {
              type: 'pie',
              radius: ['45%', '70%'],
              center: ['50%', '43%'],
              label: {
                show: false
              },
              data: data
            }
          ]
        });
      })
      .catch((e) => {
        message.error(e.message);
      });
  };

  getBrowserCountData();
</script>
