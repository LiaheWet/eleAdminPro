<template>
  <div>
    <!--    标头-->
    <div class="title-container" style="background: white;padding-left: 10px;height: 35px">
      <el-icon size="20px" style="margin-right: 5px">
        <ChromeFilled/>
      </el-icon>
      <h8 class="page-title">原材料</h8>
    </div>
    <div>
      <div class="tree-table-demo">
        <!--     材料类型树形控件-->
        <div class="tree-container">
          <div class="page-header">
            <div class="title-container">
              <h8 class="page-title1" style="width: 180px;">类别</h8>
              <div class="button-container" style="padding-right:  10px;">
                <el-button
                  :class="{ 'disabled-button': isMaterialTypePid.valueOf(), 'adm-button': !isMaterialTypePid.valueOf() }"
                  style="color: #8eb97e" @click="handleCreateMT">新增
                </el-button>
                <el-button
                  :class="{ 'disabled-button': !isMaterialTypePid.valueOf(), 'adm-button': isMaterialTypePid.valueOf() }"
                  style="color: #00a3ff" @click="handleCreateMT">修改
                </el-button>
                <el-button
                  :class="{ 'disabled-button': !isMaterialTypePid.valueOf(), 'adm-button': isMaterialTypePid.valueOf() }"
                  style="color: red" @click="handleDeleteMT">删除
                </el-button>
              </div>
            </div>
          </div>
          <el-tree :data="treeData" :default-expanded-keys="[0]" :props="treeProps" node-key="materialTypeId"
                   @node-click="handleTreeNodeClick"></el-tree>
        </div>
        <!--      添加、编辑材料类型对话框-->
        <el-dialog v-model="isModalVisibleType" style="width: 400px;height: 200px" title="新建/编辑材料类型">
          <el-form :model="formDataType">
            <el-form-item label="材料类型名称" prop="materialTypeName">
              <el-input v-model="formDataType.materialTypeName"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" style="float: right;">
            <el-button @click="isModalVisibleType = false">取消</el-button>
            <el-button type="primary" @click="handleSaveMT">{{ isMaterialTypePid ? '保存' : '创建' }}</el-button>
          </div>
        </el-dialog>
        <!--     材料表格功能头-->
        <div class="table-container">
          <div class="page-header">
            <div class="title-container">
              <h8 class="page-title1">原材料</h8>
              <div class="button-container" style="padding-right:  10px;">
                <el-button class="add-button" style="color: #7dd733" @click="selectAll">重置</el-button>
                <el-button class="add-button" style="color: #8eb97e" @click="openAddDialog">新增</el-button>
                <el-button class="modify-button" style="color: #00a3ff" @click="openEditDialog">修改</el-button>
                <el-button class="delete-button" style="color: red" @click="removeMaterialBatch">删除</el-button>
              </div>
            </div>
          </div>
          <!-- 表格内容-->
          <el-table :data="tableData" :show-selection="true" border stripe style="width: 900px;height: 500px"
                    @selection-change="handleSelectionChange">
            <el-table-column type="selection"></el-table-column>
            <el-table-column type="index"></el-table-column>
            <el-table-column label="名称" prop="materialName" sortable></el-table-column>
            <el-table-column label="类别ID" prop="materialTypeId" sortable></el-table-column>
            <el-table-column label="类别" prop="materialType"></el-table-column>
            <el-table-column label="规格型号" prop="materialSpecs"></el-table-column>
            <el-table-column label="计量单位" prop="materialMeasurement"></el-table-column>
            <el-table-column :filter-method="handleFilter" :filters="[{text:'启用',value:1},{text: '停用',value: 0}]" label="状态"
                             prop="materialStatus">
              <template #default="{row}">
                <el-tag
                  :class="{'bgm-shallow-red-bg': row.materialStatus === 0,'bgm-shallow-green-bg': row.materialStatus === 1}"
                  :type="row.materialStatus === 1 ? 'success' : 'danger'">
                  {{ row.materialStatus === 1 ? '启用' : '停用' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="备注" prop="materialRemark"></el-table-column>
          </el-table>
          <!--          分页-->
          <el-footer>
            <div class="demo-pagination-block">
              <el-pagination
                v-model:current-page="currentPage"
                v-model:page-size="pageSize"
                :page-sizes="[10, 20, 30, 40]"
                :total="total"
                layout="total, sizes, prev, pager, next, jumper"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
              />
            </div>
          </el-footer>
          <!--添加材料对话框-->
          <el-dialog v-model="isModalVisible" title="添加原材料">
            <el-form :model="formData" label-width="80px">
              <el-form-item label="名称" prop="materialName">
                <el-input v-model="formData.materialName"></el-input>
              </el-form-item>
              <el-form-item label="类别" prop="materialType">
                <el-tree-select v-model="formDataType" :check-strictly="false" :data="treeData" :props="{
                    children: 'children',
                    label: 'materialTypeName',
                    value: 'materialTypeId',
                    hasChildren: true
                  }" placeholder="请选择材料类型"></el-tree-select>
              </el-form-item>
              <el-form-item label="规格型号" prop="materialSpecs">
                <el-input v-model="formData.materialSpecs"></el-input>
              </el-form-item>
              <el-form-item label="计量单位" prop="materialMeasurement">
                <label>：千克</label>
              </el-form-item>
              <el-form-item label="状态" prop="materialStatus">
                <el-radio-group v-model="formData.materialStatus">
                  <el-radio :label="1">启用</el-radio>
                  <el-radio :label="0">停用</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="备注" prop="materialRemark">
                <el-input v-model="formData.materialRemark" type="textarea"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer">
              <el-button @click="isModalVisible = false">取消</el-button>
              <el-button type="primary" @click="formData.materialId ? updateForm() : submitForm()">
                {{ formData.materialId ? '保存' : '创建' }}
              </el-button>
            </div>
          </el-dialog>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ElTree, ElSelect, ElOption, ElDialog, ElMessage} from 'element-plus';
import {ref, onMounted, reactive, createVNode} from 'vue';
import {
  listBgmMaterialType,
  updateBgmMaterialType,
  addBgmMaterialType,
  removeBgmMaterialType
} from "@/api/bgm/bgminfo/material"
import {pageBgmMaterial, addBgmMaterial, updateBgmMaterial, removeBgmMaterials} from "@/api/bgm/bgminfo/material";
import {messageLoading} from 'ele-admin-pro/es';
import {message, Modal} from "ant-design-vue/es";
import {ExclamationCircleOutlined} from "@ant-design/icons-vue";

//-------------材料类型----------------
const treeData = ref([]);
const treeProps = ref({
  children: 'children',
  label: 'materialTypeName'
});
//材料类型显示控制
let isModalVisibleType = ref(false);
let formDataType = ref({
  materialTypeId: '',
  materialTypeName: '',
  materialTypePid: '',
});
//获取materialTypePid用来显示button的不同状态
const isMaterialTypePid = ref(false);
//默认materialTypeId中间值
let PassMaterialTypeId = 1;


//-------------材料----------------
const tableData = ref([]);
const selectedRows = ref([]); // 定义选中行的数组
const isModalVisible = ref(false);
const formData = ref({
  materialId: '',
  materialTypeId: '',
  materialName: '',
  materialType: '',
  materialSpecs: '',
  materialMeasurement: '千克',
  materialStatus: 1, // 默认为启用
  materialRemark: ''
});
const total = ref(0);
const pageSize = ref(10);
const currentPage = ref(1);


//-------------材料类型方法------------
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
//父子节点的点击事件,
const handleTreeNodeClick = (data) => {
  console.log("materialTypePid:" + data.materialTypePid + "  materialTypeId:" + data.materialTypeId);
  if (data.materialTypePid === 0) {
    isMaterialTypePid.value = false;
  } else {
    isMaterialTypePid.value = true;
  }
  PassMaterialTypeId = data.materialTypeId;
  if (data.materialTypePid !== 0) {
    relistBgmMaterialType(PassMaterialTypeId);
  }
};

//控制对话框
const handleCreateMT = () => {
  formDataType.value = {};
  isModalVisibleType.value = true;
}
//添加、修改材料类型，
const handleSaveMT = () => {
  console.log("formDataType:" + formDataType.value);
  const hide = messageLoading('提交中...', 0);
  let func;
  if (isMaterialTypePid.value) {
    formDataType.value.materialTypeId = PassMaterialTypeId;
    func = updateBgmMaterialType;
  } else {
    formDataType.value.materialTypePid = PassMaterialTypeId;
    func = addBgmMaterialType;
  }
  func(formDataType.value)
    .then((msg) => {
      hide();
      isModalVisibleType.value = false;
      // 更新treeData
      fetchTreeData();
      message.success(msg);
    })
    .catch((e) => {
      hide();
      message.error(e.message);
    });

}
//删除材料类型，
const handleDeleteMT = () => {
  const hide = messageLoading('请求中..', 0);
  formDataType.value.materialTypeId = PassMaterialTypeId;
  removeBgmMaterialType(formDataType.value.materialTypeId)
    .then((msg) => {
      hide();
      // 更新treeData
      fetchTreeData();
      message.success(msg);
    })
    .catch((e) => {
      hide();
      message.error(e.message);
    });
}


//-------------材料类型------------
const handleSizeChange = async (val) => {
  pageSize.value = val;
  await getTableData();
};
const handleCurrentChange = async (val) => {
  currentPage.value = val;
  await getTableData();
};
const getTableData = async () => {

  const res = await pageBgmMaterial({
    page: currentPage.value,
    limit: pageSize.value,
  });
  console.log('成功获取到table数据：', res);
  tableData.value = res.list;
  total.value = res.count;
  console.log(tableData.value);
};

const relistBgmMaterialType = async (Id) => {
  const res = await pageBgmMaterial({
    page: currentPage.value,
    limit: pageSize.value,
    materialTypeId: Id
  });
  tableData.value = res.list;
  total.value = res.count;

};
//重置所有数据
const selectAll = () => {
  fetchTreeData();
  getTableData();
};
//状态筛选栏
const handleFilter = (value, row) => {
  return value === row.materialStatus;
};

//控制对话框添加
const openAddDialog = () => {
  isModalVisible.value = true;
  formData.value = {};
  formDataType.value = '';
};

//提交添加表单
const submitForm = () => {

  formData.value.materialTypeId = formDataType.value;
  console.log("formData:" + JSON.stringify(formData.value));
  const hide = messageLoading('提交中...', 0);
  addBgmMaterial(formData.value)
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

//选中框修改
const handleSelectionChange = (rows) => {
  selectedRows.value = rows; // 更新选中行的数组
};
//修改表单
const openEditDialog = () => {
  if (selectedRows.value.length !== 1) {
    ElMessage.warning('请选择一条数据进行修改！');
    return;
  } else {
    console.log("row:  " + JSON.stringify(selectedRows.value[0]));
    isModalVisible.value = true;
    formData.value = selectedRows.value[0];//表单赋值
    formDataType.value = formData.value.materialTypeId;//下拉框赋值
  }
};

//提交修改表单
const updateForm = () => {

  formData.value.materialTypeId = formDataType.value;
  const hide = messageLoading('提交中...', 0);
  updateBgmMaterial(formData.value)
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
}


//删除数据
const removeMaterialBatch = () => {
  if (!selectedRows.value.length) {
    message.error('请至少选择一条数据');
    return;
  }
  Modal.confirm({
    title: '提示',
    content: '确定要删除选中的角色吗?',
    icon: createVNode(ExclamationCircleOutlined),
    maskClosable: true,
    onOk: () => {
      const hide = messageLoading('请求中..', 0);
      removeBgmMaterials(selectedRows.value.map((d) => d.materialId))
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
};

onMounted(() => {
  //获取树形结构
  fetchTreeData();
  //获取表格数据
  getTableData();
});

</script>

<style scoped>
.page-header {
  border-bottom: 1px solid #ddd;
  padding-bottom: 1px;
  padding-top: 5px;
  padding-left: 20px;
  height: 35px;
  background: white;
  margin: 1px 1px 1px 1px;
}

.page-title {
  margin: 0;
  font-size: 15px;
  font-weight: bold;
  color: #00a3ff;
  flex: 1;
}

.page-title1 {
  margin: 0;
  font-size: 12px;
  font-weight: bold;
  color: #71838d;
  flex: 1;
}

.tree-table-demo {
  display: flex;
}

.tree-container {
  flex: 4;
}

.table-container {
  flex: 12;
}

.add-button, .modify-button, .delete-button, .adm-button {
  margin-left: 2px;
  margin-right: 2px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 15px;
  height: 25px;
}

.disabled-button {
  margin-left: 8px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 15px;
  height: 25px;
  cursor: not-allowed;
  opacity: 0.5;
}

.title-container {
  display: flex;
  align-items: center;
  width: 100%;
}
/*.el-footer {
  position: fixed;
  bottom: 5px;
  left: 0px;
  width: 100%;
  height: 35px;
  background: white;
}*/
.demo-pagination-block{
  margin-top: 50px;
  padding-left: 10px;
  margin-bottom: 16px;
  display: flex;
  margin-top: 2px;
  background: white;
}
</style>
