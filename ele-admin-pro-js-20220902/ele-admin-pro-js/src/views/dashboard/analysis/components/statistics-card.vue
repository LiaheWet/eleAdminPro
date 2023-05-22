<!-- 统计卡片 -->
<template>
  <a-row :gutter="16">
    <a-col :lg="6" :md="12" :sm="24" :xs="24">
      <a-card class="analysis-chart-card" :bordered="false">
        <div class="ele-text-secondary ele-cell">
          <div class="ele-cell-content">总销售额</div>
          <a-tooltip title="指标说明">
            <question-circle-outlined />
          </a-tooltip>
        </div>
        <h1 class="analysis-chart-card-num">¥ 126,560</h1>
        <div class="analysis-chart-card-content" style="padding-top: 16px">
          <a-space size="middle">
            <span class="analysis-trend-text">
              周同比12% <caret-up-outlined class="ele-text-danger" />
            </span>
            <span class="analysis-trend-text">
              日同比11% <caret-down-outlined class="ele-text-success" />
            </span>
          </a-space>
        </div>
        <a-divider />
        <div>日销售额 ￥12,423</div>
      </a-card>
    </a-col>
    <a-col :lg="6" :md="12" :sm="24" :xs="24">
      <a-card class="analysis-chart-card" :bordered="false">
        <div class="ele-text-secondary ele-cell">
          <div class="ele-cell-content">访问量</div>
          <ele-tag color="red">日</ele-tag>
        </div>
        <h1 class="analysis-chart-card-num">8,846</h1>
        <v-chart
          ref="visitChartRef"
          :option="visitChartOption"
          style="height: 40px"
        />
        <a-divider />
        <div>日访问量 1,234</div>
      </a-card>
    </a-col>
    <a-col :lg="6" :md="12" :sm="24" :xs="24">
      <a-card class="analysis-chart-card" :bordered="false">
        <div class="ele-text-secondary ele-cell">
          <div class="ele-cell-content">支付笔数</div>
          <ele-tag color="blue">月</ele-tag>
        </div>
        <h1 class="analysis-chart-card-num">6,560</h1>
        <v-chart
          ref="payNumChartRef"
          :option="payNumChartOption"
          style="height: 40px"
        />
        <a-divider />
        <div>转化率 60%</div>
      </a-card>
    </a-col>
    <a-col :lg="6" :md="12" :sm="24" :xs="24">
      <a-card class="analysis-chart-card" :bordered="false">
        <div class="ele-text-secondary ele-cell">
          <div class="ele-cell-content">活动运营效果</div>
          <ele-tag color="green">周</ele-tag>
        </div>
        <h1 class="analysis-chart-card-num">78%</h1>
        <div class="analysis-chart-card-content" style="padding-top: 16px">
          <a-progress
            :percent="78"
            :show-info="false"
            stroke-color="#13c2c2"
            status="active"
          />
        </div>
        <a-divider />
        <a-space size="middle">
          <span class="analysis-trend-text">
            周同比12% <caret-up-outlined class="ele-text-danger" />
          </span>
          <span class="analysis-trend-text">
            日同比11% <caret-down-outlined class="ele-text-success" />
          </span>
        </a-space>
      </a-card>
    </a-col>
  </a-row>
</template>

<script setup>
  import { ref, reactive } from 'vue';
  import { message } from 'ant-design-vue/es';
  import {
    QuestionCircleOutlined,
    CaretUpOutlined,
    CaretDownOutlined
  } from '@ant-design/icons-vue';
  import { use } from 'echarts/core';
  import { CanvasRenderer } from 'echarts/renderers';
  import { LineChart, BarChart } from 'echarts/charts';
  import { GridComponent, TooltipComponent } from 'echarts/components';
  import VChart from 'vue-echarts';
  import { getPayNumList } from '@/api/dashboard/analysis';
  import useEcharts from '@/utils/use-echarts';

  use([CanvasRenderer, LineChart, BarChart, GridComponent, TooltipComponent]);

  //
  const visitChartRef = ref(null);
  const payNumChartRef = ref(null);

  useEcharts([visitChartRef, payNumChartRef]);

  // 访问量折线图配置
  const visitChartOption = reactive({});

  // 支付笔数柱状图配置
  const payNumChartOption = reactive({});

  /* 获取支付笔数数据 */
  const getPayNumData = () => {
    getPayNumList()
      .then((data) => {
        Object.assign(visitChartOption, {
          color: '#975fe5',
          tooltip: {
            trigger: 'axis',
            formatter:
              '<i class="ele-chart-dot" style="background: #975fe5;"></i>{b0}: {c0}'
          },
          grid: {
            top: 10,
            bottom: 0,
            left: 0,
            right: 0
          },
          xAxis: [
            {
              show: false,
              type: 'category',
              boundaryGap: false,
              data: data.map((d) => d.date)
            }
          ],
          yAxis: [
            {
              show: false,
              type: 'value',
              splitLine: {
                show: false
              }
            }
          ],
          series: [
            {
              type: 'line',
              smooth: true,
              symbol: 'none',
              areaStyle: {
                opacity: 0.5
              },
              data: data.map((d) => d.value)
            }
          ]
        });

        Object.assign(payNumChartOption, {
          tooltip: {
            trigger: 'axis',
            formatter:
              '<i class="ele-chart-dot" style="background: #5b8ff9;"></i>{b0}: {c0}'
          },
          grid: {
            top: 10,
            bottom: 0,
            left: 0,
            right: 0
          },
          xAxis: [
            {
              show: false,
              type: 'category',
              data: data.map((d) => d.date)
            }
          ],
          yAxis: [
            {
              show: false,
              type: 'value',
              splitLine: {
                show: false
              }
            }
          ],
          series: [
            {
              type: 'bar',
              data: data.map((d) => d.value)
            }
          ]
        });
      })
      .catch((e) => {
        message.error(e.message);
      });
  };

  getPayNumData();
</script>

<style lang="less" scoped>
  .analysis-chart-card {
    :deep(.ant-card-body) {
      padding: 16px 22px 12px 22px;
    }

    :deep(.ant-divider) {
      margin: 12px 0;
    }
  }

  .analysis-chart-card-num {
    font-size: 30px;
  }

  .analysis-chart-card-content {
    height: 40px;
  }

  .analysis-trend-text {
    white-space: nowrap;
  }
</style>
