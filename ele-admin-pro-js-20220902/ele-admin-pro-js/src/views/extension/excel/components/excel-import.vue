<template>
  <a-card title="导入 Excel" :bordered="false">
    <!-- 操作按钮 -->
    <ele-toolbar :tools="[]">
      <a-space>
        <a-upload
          :before-upload="importFile"
          :show-upload-list="false"
          accept=".xls,.xlsx"
        >
          <a-button type="primary" class="ele-btn-icon">导入</a-button>
        </a-upload>
        <a-upload
          :before-upload="importFile2"
          :show-upload-list="false"
          accept=".xls,.xlsx"
        >
          <a-button type="primary" class="ele-btn-icon">导入拆分合并</a-button>
        </a-upload>
        <a-upload
          :before-upload="importFile3"
          :show-upload-list="false"
          accept=".xls,.xlsx"
        >
          <a-button type="primary" class="ele-btn-icon">导入保持合并</a-button>
        </a-upload>
      </a-space>
    </ele-toolbar>
    <div style="overflow: auto">
      <table class="ele-table ele-table-border" style="min-width: max-content">
        <thead>
          <tr>
            <th></th>
            <th
              v-for="item in importTitle"
              :key="item"
              style="text-align: center"
            >
              {{ item }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in importData" :key="index">
            <td style="text-align: center">{{ index + 1 }}</td>
            <template v-for="key in importTitle">
              <td
                v-if="
                  item['__colspan__' + key] !== 0 &&
                  item['__rowspan__' + key] !== 0
                "
                :key="key"
                :colspan="item['__colspan__' + key]"
                :rowspan="item['__rowspan__' + key]"
                style="text-align: center"
              >
                {{ item[key] }}
              </td>
            </template>
          </tr>
        </tbody>
      </table>
    </div>
    <a-row :gutter="32">
      <a-col :md="12" :xs="24">
        <div style="margin: 16px 0">二维数组格式数据:</div>
        <pre style="max-height: 300px; padding: 16px; overflow: auto"
          >{{ JSON.stringify(importDataAoa, null, 4) }}
          </pre
        >
      </a-col>
      <a-col :md="12" :xs="24">
        <div style="margin: 16px 0">JSON格式数据:</div>
        <pre style="max-height: 300px; padding: 16px; overflow: auto"
          >{{ JSON.stringify(importData, null, 4) }}
          </pre
        >
      </a-col>
    </a-row>
  </a-card>
</template>

<script setup>
  import { ref } from 'vue';
  import { utils, read } from 'xlsx';
  import { message } from 'ant-design-vue/es';

  // 导入数据的列
  const importTitle = ref(['A', 'B', 'C', 'D', 'E', 'F', 'G']);

  // 导入的数据
  const importData = ref([]);

  // 导入数据二维数组形式
  const importDataAoa = ref([]);

  /* 导入本地 excel 文件 */
  const importFile = (file) => {
    if (
      ![
        'application/vnd.ms-excel',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      ].includes(file.type)
    ) {
      message.error('只能选择 excel 文件');
      return false;
    }
    if (file.size / 1024 / 1024 > 20) {
      message.error('大小不能超过 20MB');
      return false;
    }
    const reader = new FileReader();
    reader.onload = (e) => {
      const data = new Uint8Array(e.target?.result);
      const workbook = read(data, { type: 'array' });
      const sheetNames = workbook.SheetNames;
      const worksheet = workbook.Sheets[sheetNames[0]];
      // 解析成二维数组
      const aoa = utils.sheet_to_json(worksheet, { header: 1 });
      // 生成表格需要的数据
      let list = [];
      let maxCols = 0;
      let title = [];
      aoa.forEach((d) => {
        if (d.length > maxCols) {
          maxCols = d.length;
        }
        const row = {};
        for (let i = 0; i < d.length; i++) {
          const key = getCharByIndex(i);
          row[key] = d[i];
          row['__colspan__' + key] = 1;
          row['__rowspan__' + key] = 1;
        }
        list.push(row);
      });
      for (let i = 0; i < maxCols; i++) {
        title.push(getCharByIndex(i));
      }
      importTitle.value = title;
      importData.value = list;
      importDataAoa.value = aoa;
    };
    reader.readAsArrayBuffer(file);
    return false;
  };

  /* 导入 excel 拆分合并单元格 */
  const importFile2 = (file) => {
    if (
      ![
        'application/vnd.ms-excel',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      ].includes(file.type)
    ) {
      message.error('只能选择 excel 文件');
      return false;
    }
    if (file.size / 1024 / 1024 > 20) {
      message.error('大小不能超过 20MB');
      return false;
    }
    const reader = new FileReader();
    reader.onload = (e) => {
      const data = new Uint8Array(e.target?.result);
      const workbook = read(data, { type: 'array' });
      const sheetNames = workbook.SheetNames;
      const worksheet = workbook.Sheets[sheetNames[0]];
      // 解析成二维数组
      const aoa = utils.sheet_to_json(worksheet, { header: 1 });
      // 拆分合并单元格
      if (worksheet['!merges']) {
        worksheet['!merges'].forEach((m) => {
          for (let r = m.s.r; r <= m.e.r; r++) {
            for (let c = m.s.c; c <= m.e.c; c++) {
              aoa[r][c] = aoa[m.s.r][m.s.c];
            }
          }
        });
      }
      // 生成表格需要的数据
      let list = [];
      let maxCols = 0;
      let title = [];
      aoa.forEach((d) => {
        if (d.length > maxCols) {
          maxCols = d.length;
        }
        const row = {};
        for (let i = 0; i < d.length; i++) {
          row[getCharByIndex(i)] = d[i];
        }
        list.push(row);
      });
      for (let i = 0; i < maxCols; i++) {
        title.push(getCharByIndex(i));
      }
      importTitle.value = title;
      importData.value = list;
      importDataAoa.value = aoa;
    };
    reader.readAsArrayBuffer(file);
    return false;
  };

  /* 导入 excel 读取合并信息 */
  const importFile3 = (file) => {
    if (
      ![
        'application/vnd.ms-excel',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      ].includes(file.type)
    ) {
      message.error('只能选择 excel 文件');
      return false;
    }
    if (file.size / 1024 / 1024 > 20) {
      message.error('大小不能超过 20MB');
      return false;
    }
    const reader = new FileReader();
    reader.onload = (e) => {
      const data = new Uint8Array(e.target?.result);
      const workbook = read(data, { type: 'array' });
      const sheetNames = workbook.SheetNames;
      const worksheet = workbook.Sheets[sheetNames[0]];
      // 解析成二维数组
      const aoa = utils.sheet_to_json(worksheet, { header: 1 });
      // 生成表格需要的数据
      let list = [];
      let maxCols = 0;
      let title = [];
      aoa.forEach((d) => {
        if (d.length > maxCols) {
          maxCols = d.length;
        }
        const row = {};
        for (let i = 0; i < d.length; i++) {
          row[getCharByIndex(i)] = d[i];
        }
        list.push(row);
      });
      for (let i = 0; i < maxCols; i++) {
        title.push(getCharByIndex(i));
      }
      // 记录合并单元格
      if (worksheet['!merges']) {
        worksheet['!merges'].forEach((m) => {
          for (let r = m.s.r; r <= m.e.r; r++) {
            for (let c = m.s.c; c <= m.e.c; c++) {
              const cc = getCharByIndex(c);
              list[r]['__colspan__' + cc] = 0;
              list[r]['__rowspan__' + cc] = 0;
            }
          }
          const char = getCharByIndex(m.s.c);
          list[m.s.r]['__colspan__' + char] = m.e.c - m.s.c + 1;
          list[m.s.r]['__rowspan__' + char] = m.e.r - m.s.r + 1;
        });
      }
      importTitle.value = title;
      importData.value = list;
      importDataAoa.value = aoa;
    };
    reader.readAsArrayBuffer(file);
    return false;
  };

  /* 生成Excel列字母序号 */
  const getCharByIndex = (index) => {
    const chars = [
      'A',
      'B',
      'C',
      'D',
      'E',
      'F',
      'G',
      'H',
      'I',
      'J',
      'K',
      'L',
      'M',
      'N',
      'O',
      'P',
      'Q',
      'R',
      'S',
      'T',
      'U',
      'V',
      'W',
      'X',
      'Y',
      'Z'
    ];
    if (index < chars.length) {
      return chars[index];
    }
    const n = parseInt(String(index / chars.length));
    const m = index % chars.length;
    return chars[n] + chars[m];
  };
</script>
