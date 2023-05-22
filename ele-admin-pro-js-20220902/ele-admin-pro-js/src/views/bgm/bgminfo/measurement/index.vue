<template>
  <div>
    <el-button type="primary" @click="handleCreate">新建</el-button>
    <el-table :data="tableData" border stripe @selection-change="handleSelectionChange"
              :show-selection="true">
      <el-table-column type="selection"></el-table-column>
      <el-table-column label="测量单位编号" prop="measurementId"></el-table-column>
      <el-table-column label="测量单位名称" prop="measurementName"></el-table-column>
      <el-table-column label="测量单位符号" prop="measurementSymbol"></el-table-column>
      <el-table-column label="测量单位状态">
        <template #default="{row}">
          <el-tag :type="row.measurementStatus === 1 ? 'success' : 'danger'"
                  :class="{'bgm-shallow-red-bg': row.measurementStatus === '0', 'bgm-shallow-green-bg': row.measurementStatus === '1'}"
          >
            {{ row.measurementStatus === 1 ? '启用' : '停用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" type="action">
        <template v-slot="scope">
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row.measurementId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="total,prev,pager,next"
      :total="total"
      :page-size="pageSize"
      @current-change="handleCurrentChange"
    ></el-pagination>
    <el-dialog title="新建/编辑测量单位" v-model="isModalVisible">
      <el-form :model="formData">
        <el-form-item label="测量单位名称" prop="measurementName">
          <el-input v-model="formData.measurementName"></el-input>
        </el-form-item>
        <el-form-item label="测量单位符号" prop="measurementSymbol">
          <el-input v-model="formData.measurementSymbol"></el-input>
        </el-form-item>
        <el-form-item label="测量单位状态" prop="measurementStatus">
          <el-select v-model="formData.measurementStatus">
            <el-option label="启用" value="1"></el-option>
            <el-option label="停用" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="isModalVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave">{{ formData.measurementId ? '保存' : '创建' }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import {addBgmMeasurement, pageBgmMeasurement, removeBgmMeasurement, updateBgmMeasurement} from "@/api/bgm/bgminfo/measurements";
import {messageLoading} from 'ele-admin-pro/es';
import {message} from "ant-design-vue/es";

    const tableData = ref([]);
    const isModalVisible = ref(false);
    const formData = ref({
      measurementId: '',
      measurementName: '',
      measurementSymbol: '',
      measurementStatus: '1',
    });
    const total = ref(0);
    const pageSize = ref(10);
    const currentPage = ref(1);
    const datasource = pageBgmMeasurement;
    const selectedRows = ref([]); // 定义选中行的数组
    const getTableData = async () => {
      const res = await datasource({
        page: currentPage.value,
        limit: pageSize.value,
      });
      tableData.value = res.list;
      total.value = res.count;
      console.log("计量单位："+tableData.value)
    };

    onMounted(() => {
      getTableData();
    });

    const handleCreate = () => {
      formData.value = {};
      isModalVisible.value = true;
    };

    const handleSave = () => {
      console.log(formData.value);
      const hide = messageLoading('提交中...', 0);
      (formData.value.measurementId ? updateBgmMeasurement : addBgmMeasurement)(formData.value)
        .then((msg) => {
          hide();
          isModalVisible.value = false;
          getTableData();
          message.success(msg);
        })
        .catch((e) => {
          hide();
          message.error(e.message);
        });
    };
    const handleSelectionChange = (rows) => {
      selectedRows.value = rows; // 更新选中行的数组
    };
    const handleCurrentChange = (val) => {
      currentPage.value = val;
      getTableData();
    }

    const handleEdit = row => {
      formData.value=selectedRows.value[0];
      console.log("da::"+JSON.stringify(selectedRows.value[0].valueOf()));
      isModalVisible.value = true;
    }

    const handleDelete = (rowId) => {
      const hide = messageLoading('请求中..', 0);
      removeBgmMeasurement(rowId)
        .then((msg) => {
          hide();
          getTableData();
          message.success(msg);
        })
        .catch((e) => {
          hide();
          message.error(e.message);
        });
    };

</script>

<style>
.el-table {width: 100%;}
.el-pagination {display: flex; justify-content: center;}
.bgm-shallow-red-bg {
  background-color: #fff1f0 !important;
}
.bgm-shallow-green-bg {
  background-color: #f0fff4 !important;
}
</style>
