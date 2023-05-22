<template>
  <div>
    <!--  标头-->
    <div class="title-container">
      <el-icon size="20px" style="margin-right: 5px">
        <ChromeFilled/>
      </el-icon>
      <h8 class="page-title">原材料</h8>
      <el-divider direction="vertical"/>
      <el-button class="add-button" style="color: #8eb97e" @click="openDialog">新增</el-button>
      <el-button class="modify-button" style="color: #00a3ff" @click="handleEdit">修改</el-button>
      <el-button class="delete-button" style="color: red" @click="handleRemove">删除</el-button>
    </div>

    <!--    查询部分-->
    <el-form>
      <div class="title-container1" style="display: flex; flex-direction: row; align-items: center;">
        <label>请选择材料类型：</label>
        <el-tree-select v-model="typeId" :data="treeData" :props="{
                    children: 'children',
                    label: 'materialTypeName',
                    value: 'materialTypeId',
                    hasChildren: true
                  }" :check-strictly="false" placeholder="请选择材料类型"></el-tree-select>
        <label style="margin-left: 10px">供应原材料：</label>
        <el-input v-model="inputName" style="width: 200px; margin-right: 10px;"></el-input>
        <el-select v-model="StatusSelect" placeholder="请选择状态" style="width: 120px;">
          <el-option label="启用" value="1"></el-option>
          <el-option label="停用" value="0"></el-option>
        </el-select>
        <el-button class="add-button" style="color: #8eb97e" @click="select">查询</el-button>
        <el-button class="modify-button" style="color: #00a3ff" @click="reset">重置</el-button>
      </div>
    </el-form>
    <!-- 表格内容-->
    <el-table :data="tableData"
              highlight-current-row
              style="width: 1150px"
              border
              @current-change="CurrentChange"
              :span-method="objectSpanMethod" >
      <el-table-column type="index"></el-table-column>
      <el-table-column label="平台原材料" prop="smTypeName"></el-table-column>
      <el-table-column label="供应原材料" prop="smName"></el-table-column>
      <el-table-column label="规格型号" prop="smSpecs"></el-table-column>
      <el-table-column label="备注" prop="smRemark"></el-table-column>
      <el-table-column label="状态" prop="smStatus" width="80px">
        <template #default="{row}">
          <el-tag
            :type="row.smStatus === 1 ? 'success' : 'danger'"
            :class="{'bgm-shallow-red-bg': row.smStatus === 0,'bgm-shallow-green-bg': row.smStatus === 1}">
            {{ row.smStatus === 1 ? '启用' : '停用' }}
          </el-tag>
        </template>
      </el-table-column>
    </el-table>

    <!--          分页-->
    <div class="demo-pagination-block" >
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 30, 40]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>

    <!--    弹出框-->
    <supply-edit :show-dialog="showDialog" @onClose="handleCloseDialog" :formData="formData" />

  </div>

</template>

<script setup>
import {ElMessage} from "element-plus";
import {ExclamationCircleOutlined} from "@ant-design/icons-vue";
import {messageLoading} from "ele-admin-pro";
import {message, Modal} from "ant-design-vue/es";

import {createVNode, onMounted, ref} from "vue";

import {listBgmMaterialType} from "@/api/bgm/bgminfo/material";
import {pageBgmSupplyMaterial, removeBgmSupplyMaterial} from "@/api/bgm/bgmMaterial/supply";
import SupplyEdit from './components/supply-edit.vue';

//------材料类别--------
const treeData = ref([]);
let typeId = ref(null);

//-------------材料----------------
const tableData = ref([]);
let formData = ref({
  supplyMaterialId: '',
  smTypeId: '',
  smTypeName: '',
  smName: '',
  smSpecs: '',
  smRemark: '',
  smStatus: 1 // 默认为启用
});
const total = ref(0);
const pageSize = ref(10);
const currentPage = ref(1);
const inputName = ref();//供应原材料
const StatusSelect = ref(null);//状态选择
const showDialog = ref(false);//控制对话框
const currentRow=ref(null);//表格选择行


//------树形结构-------
//树形初始化获取值
const fetchTreeData = async () => {
  try {
    const res = await listBgmMaterialType();
    console.log('成功获取到树形数据：', res);
    treeData.value = res;

  } catch (error) {
    console.error(error);
  }
};
//获取表格数据
const getTableData = async () => {
  const res = await pageBgmSupplyMaterial({
    page: currentPage.value,
    limit: pageSize.value,
  });
  console.log('成功获取到table数据：', res);
  tableData.value = res.list;
  total.value = res.count;
};
//查询按钮
const select = async () => {
  console.log("ssss:" + StatusSelect.value);
  const res = await pageBgmSupplyMaterial({
    page: currentPage.value,
    limit: pageSize.value,
    smTypeId: typeId.value,
    smName: inputName.value,
    smStatus: StatusSelect.value,
  });
  console.log('成功查询到table数据：', res);
  tableData.value = res.list;
  total.value = res.count;
};
//重置查询框
const reset = async () => {
  typeId.value = null;
  inputName.value = '';
  StatusSelect.value = null;
};
//分页
const handleSizeChange = async (val) => {
  pageSize.value = val;
  await getTableData();
};
const handleCurrentChange = async (val) => {
  currentPage.value = val;
  await getTableData();
};
//合并相同的列
const objectSpanMethod = ({row, column, rowIndex, columnIndex}) => {
  if (columnIndex === 1) { // 合并第一列相同 smPName 的行
    if (rowIndex > 0 && tableData.value[rowIndex].smTypeId === tableData.value[rowIndex - 1].smTypeId) {
      return {
        rowspan: 0,
        colspan: 0
      }
    } else {
      let rowspan = 1;
      for (let i = rowIndex + 1; i < tableData.value.length; i++) {
        if (tableData.value[i].smTypeId === tableData.value[rowIndex].smTypeId) {
          rowspan++;
        } else {
          break;
        }
      }
      return {
        rowspan,
        colspan: 1
      }
    }
  } else {
    return {
      rowspan: 1,
      colspan: 1
    }
  }
};

const handleCloseDialog = () => {
  console.log('父组件的 handleClose 方法被调用了。');
  showDialog.value = false;
  getTableData();
};
//选中行
const CurrentChange=(val)=>{
  currentRow.value = val;
};
//添加按钮功能
const openDialog = () => {
  formData.value={};
  showDialog.value=true;
};
//修改按钮功能
const handleEdit=()=> {
  // 如果没有选中行，则提示用户先选择一行
  if (!currentRow.value) {
    ElMessage.warning('请先选择要修改的行！');
    return;
  }
  formData.value=currentRow.value;
  showDialog.value=true;
};
//删除按钮功能
const handleRemove = () => {
  if (!currentRow.value) {
    ElMessage.warning('请先选择要修改的行！');
    return;
  }
  formData=currentRow.value;
  console.log('当前选中的行数据：', formData.valueOf());
  console.log("删除Id"+formData.supplyMaterialId);
  Modal.confirm({
    title: '提示',
    content: '确定要删除选中的供应原来材料吗?',
    icon: createVNode(ExclamationCircleOutlined),
    maskClosable: true,
    onOk: () => {
      const hide = messageLoading('请求中..', 0);
      removeBgmSupplyMaterial(formData.supplyMaterialId)
        .then((msg) => {
          hide();
          message.success(msg);
          getTableData();
        })
        .catch((e) => {
          hide();
          message.error(e.message);
        });
    }
  });
}

onMounted(() => {
  //获取树形结构
  fetchTreeData();
  //获取表格数据
  getTableData();

});

</script>

<style scoped>
.title-container {
  display: flex;
  align-items: center;
  width: 100%;
  background: white;
  height: 38px;
  padding-left: 10px;
  border: 1px solid #dcd0d0;
}

.page-title {
  margin: 0;
  font-size: 15px;
  font-weight: bold;
  color: #00a3ff;
  flex: 1;
}

.add-button, .modify-button, .delete-button, .adm-button {
  margin-left: 2px;
  margin-right: 2px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 15px;
  height: 25px;
  margin-bottom: 1px;
}

.title-container1 {
  display: flex;
  align-items: center; /* 垂直居中对齐 */
  background: white;
  height: 38px;
  line-height: 38px;
  padding-left: 10px;
  border: 1px solid #dcd0d0;
}

.el-tree-select .el-input {
  width: auto !important; /* 输入框宽度自适应 */


}

.demo-pagination-block  {
  margin-top: 30px;
}

.demo-pagination-block  {
  margin-bottom: 16px;
}

</style>
