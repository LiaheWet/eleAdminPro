<template>
  <a-card :bordered="false" title="热门搜索">
    <v-chart
      ref="hotSearchChartRef"
      :option="hotSearchChartOption"
      style="height: 330px"
    />
  </a-card>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { message } from 'ant-design-vue/es';
  import { use } from 'echarts/core';
  import { CanvasRenderer } from 'echarts/renderers';
  import { LineChart, BarChart } from 'echarts/charts';
  import { GridComponent, TooltipComponent } from 'echarts/components';
  import VChart from 'vue-echarts';
  import 'echarts-wordcloud';
  import { wordCloudColor } from 'ele-admin-pro/es';
  import { getWordCloudList } from '@/api/dashboard/analysis';
  import useEcharts from '@/utils/use-echarts';

  use([CanvasRenderer, LineChart, BarChart, GridComponent, TooltipComponent]);

  //
  const hotSearchChartRef = ref(null);

  useEcharts([hotSearchChartRef]);

  // 词云图表配置
  const hotSearchChartOption = reactive({});

  /* 获取词云数据 */
  const getWordCloudData = () => {
    getWordCloudList()
      .then((data) => {
        Object.assign(hotSearchChartOption, {
          tooltip: {
            show: true,
            confine: true,
            borderWidth: 1
          },
          series: [
            {
              type: 'wordCloud',
              width: '100%',
              height: '100%',
              sizeRange: [12, 24],
              gridSize: 6,
              textStyle: {
                color: wordCloudColor
              },
              emphasis: {
                textStyle: {
                  shadowBlur: 8,
                  shadowColor: 'rgba(0, 0, 0, .15)'
                }
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

  getWordCloudData();
</script>
