<template>
  <div>

    <el-header>
      <!--  标头-->
      <div class="title-container">
        <el-icon size="20px" style="margin-right: 5px">
          <ChromeFilled/>
        </el-icon>
        <h8 class="page-title">收料管理</h8>
        <el-divider direction="vertical"/>
        <el-button  color="#7dd733"  plain @click="openDialog"><el-icon><FirstAidKit /></el-icon>新增</el-button>
        <el-button :disabled="!isStatus0()" color="#00a3ff" plain @click="openDialogEdit"><el-icon><EditPen /></el-icon>修改</el-button>
        <el-button :disabled="!isStatus0()" color="red" plain @click="deleteManagement"><el-icon><Delete /></el-icon>删除</el-button>
        <el-divider direction="vertical"/>
        <el-button color="#00a3ff" plain @click="SeeDetails"><el-icon><ZoomIn /></el-icon>查看详情</el-button>
        <el-button :disabled="isStatus0()||isStatus2()" color="#085b83" plain @click="handException"><el-icon><Pointer /></el-icon>异常处理</el-button>
        <el-button :disabled="isStatus0()||isStatus2()" color="red " plain @click="updateStatus2"><el-icon><CloseBold /></el-icon>作废</el-button>
        <el-button :disabled="isStatus0()||isStatus3()" color="#7dd733" plain @click="updateStatus3"><el-icon><Select /></el-icon>取消作废</el-button>
        <el-divider direction="vertical"/>
        <el-button class="add-button" style="color: #8eb97e" @click="exportExcel"><el-icon><DocumentChecked /></el-icon>导出</el-button>
      </div>
      <!--    查询部分-->
      <div style="background-color: #edeef0;">
        <el-form :data="selectFormData" label-width="auto" style="background: white;height: 60px">
          <el-row style="margin-left: 70px">
            <el-col :span="5">
              <el-form-item label=" ">
                <el-date-picker
                  v-model="selectFormData.makeTime1"
                  size="small"
                  type="datetime"
                  value-format="YYYY-MM-DD HH:mm:ss"
                />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="供应商:" label-width="70" >
                <el-select v-model="selectFormData.vendorId" placeholder="请选择供应商" size="small">
                  <el-option
                    v-for="vendor in vendors"
                    :key="vendor.vendorId"
                    :label="vendor.vendorName"
                    :value="vendor.vendorId">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="供应原材料:" label-width="100">
                <el-select v-model="selectFormData.smName" placeholder="请选择原材料类别" size="small">
                  <el-option
                    v-for="sm in supplyMaterials"
                    :key="sm.supplyMaterialId"
                    :label="sm.smName"
                    :value="sm.smName">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="运输车辆:" label-width="100">
                <el-input v-model="selectFormData.transportVehicle" size="small"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row style="margin-top: -35px;margin-left: 15px;">
            <span>进场期间:</span>
          </el-row>
          <el-row style="margin-top: -10px;margin-left: 70px">
            <el-col :span="5">
              <el-form-item label=" ">
                <el-date-picker
                  v-model="selectFormData.makeTime2"
                  size="small"
                  type="datetime"
                  value-format="YYYY-MM-DD HH:mm:ss"
                />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="储料仓:" label-width="70">
                <el-input v-model="selectFormData.storageBin" size="small"/>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="平台原材料:" label-width="100">
                <el-select v-model="selectFormData.smTypeName" placeholder="请选择平台原材料" size="small">
                <el-option
                  v-for="materials in materialData"
                  :key="materials.materialId"
                  :label="materials.materialName"
                  :value="materials.materialName">
                </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="收料编号:" label-width="100">
                <el-input v-model="selectFormData.managementNumber" size="small"/>
              </el-form-item>
            </el-col>
            <el-col :span="4" style="margin-left: 11px;margin-top: -12px">
              <el-form-item>
                <el-button icon="Search" plain style="width: 65px" type="primary" @click="getTableData">查询</el-button>
                <el-button icon="Close" native-type="reset" plain style="width: 65px" @click="reset">重置</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-header>

    <!--主体-->
    <el-table :data="tableData"
              border
              height="525"
              highlight-current-row
              size="small"
              stripe
              @current-change="CurrentChange">
      <el-table-column fixed type="index" width="30px"></el-table-column>
      <el-table-column fixed label="收料编号" prop="managementNumber" width="90px"></el-table-column>
      <el-table-column fixed label="状态" prop="managementStatus">
        <template #default="{row}">
          <el-tag
            :class="{
                            'selected0': row.managementStatus === 0,
                            'selected1': row.managementStatus === 1,
                            'selected2': row.managementStatus === 2,
                            'selected3': row.managementStatus === 3,
                          }">
            {{ getStatus(row.managementStatus) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="平台原材料" prop="smTypeName" width="130px"></el-table-column>
      <el-table-column label="供应原材料" prop="smName" width="150px"></el-table-column>
      <el-table-column label="规格型号" prop="smSpecs"></el-table-column>
      <el-table-column label="供应商" prop="vendorName" width="150px"></el-table-column>
      <el-table-column label="收料方式" prop="receiveMethod"></el-table-column>
      <el-table-column label="过毛时间" prop="fleeceTime" width="130px"></el-table-column>
      <el-table-column label="实收重量(t)" prop="collectedWeight"></el-table-column>
      <el-table-column label="实收数量" prop="supplyNumber"></el-table-column>
      <el-table-column label="累计扣杂(t)" prop="claspWeight"></el-table-column>
      <el-table-column label="入库重量(t)" prop="warehousingWeight"></el-table-column>
      <el-table-column label="储料仓" prop="storageBin"></el-table-column>
      <el-table-column label="过皮时间" prop="peelingTime" width="130px"></el-table-column>
      <el-table-column label="毛重(t)" prop="grossWeight"></el-table-column>
      <el-table-column label="皮重(t)" prop="tareWeight"></el-table-column>
      <el-table-column label="净重(t)" prop="netWeight"></el-table-column>
      <el-table-column label="运输车辆" prop="transportVehicle"></el-table-column>
      <el-table-column label="收料人" prop="overHairedPersonnel"></el-table-column>
      <el-table-column label="作废原因" prop="cancellation"></el-table-column>
      <el-table-column label="供货协议" prop="agreementNumber" width="100px"></el-table-column>
      <el-table-column label="备注" prop="remark"></el-table-column>
    </el-table>

    <!--          分页 和  状态选择器-->
    <div class="demo-pagination-block">
      <!--          分页-->
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 30, 40]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
      <!--          状态选择器-->
      <el-button :class="{ 'selected0': selectedStatus.includes(0), 'default': !selectedStatus.includes(0) }"
                 class="add-button"
                 style="height: 30px"
                 @click="toggleStatus(0)">制单
      </el-button>
      <el-button :class="{ 'selected1': selectedStatus.includes(1), 'default': !selectedStatus.includes(1) }"
                 class="add-button"
                 style="height: 30px"
                 @click="toggleStatus(1)">收料中
      </el-button>
      <el-button :class="{ 'selected2': selectedStatus.includes(2), 'default': !selectedStatus.includes(2) }"
                 class="add-button"
                 style="height: 30px"
                 @click="toggleStatus(2)">已停用
      </el-button>
      <el-button :class="{ 'selected3': selectedStatus.includes(3), 'default': !selectedStatus.includes(3) }"
                 class="add-button"
                 style="height: 30px"
                 @click="toggleStatus(3)">已完成
      </el-button>
    </div>

    <!--    对话框-->
    <receiveManagement-edit :formData="formData" :purchasers="purchasers"
                            :title="title"
                            :show-dialog="showDialog" @onClose="closeDialog"/>

  </div>
</template>

<script setup>
import {createVNode, onMounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {ExclamationCircleOutlined} from "@ant-design/icons-vue";
import {messageLoading} from "ele-admin-pro";
import {message, Modal} from "ant-design-vue/es";
import {
  pageBgmReceiveManagement,
  removeBgmReceiveManagement,
  updateBgmReceiveManagement
} from "@/api/bgm/bgmMaterial/receiveManagement";
import {pageBgmPurchaser} from "@/api/bgm/bgmMaterial/purchaser";
import dayjs from 'dayjs';
import {pageBgmSupplyMaterial} from "@/api/bgm/bgmMaterial/supply";
import {listBgmMaterial} from "@/api/bgm/bgminfo/material";
import {pageBgmVendor} from "@/api/bgm/bgmMaterial/vendor";
import ReceiveManagementEdit from "@/views/bgm/bgmMaterial/receiveManagement/components/receiveManagement-edit.vue";
import {utils, writeFile} from "xlsx";


const tableData = ref([]);
const total = ref(0);
const pageSize = ref(10);
const currentPage = ref(1);
const formData = ref({});//表单
const purchasers = ref([]);//采购人列表
const currentRow = ref(null);//表格选择行
const selectedStatus = ref([0, 1, 2, 3]);//状态集合
const title=ref('');
const showDialog = ref(false);//对话框控制
const selectFormData = reactive({
  vendorId: '',//搜索供应商名字
  agreementSupplyName: '',//搜索供应原材料
  storageBin: '',//搜索储料仓
  transportVehicle: '',
  managementNumber: '',//搜索编号
  smName:'',
  smTypeName:'',
  makeTime1: `${dayjs().startOf('month').format('YYYY-MM-DD HH:mm:ss')}`,// 当前月份的第一天
  makeTime2: `${dayjs().endOf('month').format('YYYY-MM-DD HH:mm:ss')}`,// 当前月份的最后一天
})//搜索框定义
const supplyMaterials = ref([]);//供应材料
const materialData = ref([]);//平台原材料
const vendors = ref([]);//供应商

const getTableData = async () => {
  const time1 = new Date(selectFormData.makeTime1).toLocaleString('en-US', {
    timeZone: 'Asia/Shanghai',
    year: 'numeric',
    month: 'short',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    weekday: 'short'
  });
  const time2 = new Date(selectFormData.makeTime2).toLocaleString('en-US', {
    timeZone: 'Asia/Shanghai',
    year: 'numeric',
    month: 'short',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    weekday: 'short'
  });
  const res = await pageBgmReceiveManagement({
    page: currentPage.value,
    limit: pageSize.value,
    vendorId: selectFormData.vendorId,
    storageBin: selectFormData.storageBin,
    transportVehicle: selectFormData.transportVehicle,
    managementNumber: selectFormData.managementNumber,
    smName:selectFormData.smName,
    smTypeName:selectFormData.smTypeName,
    time1: time1,
    time2: time2,
    managementStatusList: selectedStatus.value
  });
  console.log('成功获取到table数据：', res);
  tableData.value = res.list;
  total.value = res.count;
};//获取表格数据
const handleSizeChange = async (val) => {
  pageSize.value = val;
  await getTableData();
};//分页
const handleCurrentChange = async (val) => {
  currentPage.value = val;
  await getTableData();
};//分页
const reset = () => {
  selectFormData.vendorId = '';
  selectFormData.storageBin = '';
  selectFormData.transportVehicle = '';
  selectFormData.managementNumber = '';
  selectFormData.smName='',
  selectFormData.smTypeName='',
  selectFormData.makeTime1 = dayjs().startOf('month').format('YYYY-MM-DD HH:mm:ss');
  selectFormData.makeTime2 = dayjs().endOf('month').format('YYYY-MM-DD HH:mm:ss');
};//重置查询框
const getSelectPurchasers = async () => {
  const res = await pageBgmPurchaser({});
  //console.log('成功获取到SelectPurchaser数据：', res);
  purchasers.value = res.list;
}//获取采购人
const getSelectSupplyMaterials = async () => {
  const res = await pageBgmSupplyMaterial({});
  //console.log('成功获取到SelectSupplyMaterial数据：', res);
  supplyMaterials.value = res.list;

}//选择供应原材料(搜索栏)
const getSelectMaterials = async () => {
  try {
    const res = await listBgmMaterial();
    materialData.value = res;
  } catch (error) {
    console.error(error);
  }
};//树形初始化获取值(搜索栏)
const getSelectVendors = async () => {
  const res = await pageBgmVendor({});
  //console.log('成功获取到SelectVendors数据：', res);
  vendors.value = res.list;
}//搜索栏,获取供应商

//----搜索栏-----
const toggleStatus = (status) => {
  const index = selectedStatus.value.indexOf(status)
  if (index === -1) {
    selectedStatus.value.push(status) // 选中状态
  } else {
    selectedStatus.value.splice(index, 1) // 取消选中状态
  }
  selectedStatus.value.sort((a, b) => a - b) // 对数组元素进行排序
  getTableData();
};//更改状态集
const getStatus = (status) => {
  switch (status) {
    case 0:
      return '制单';
    case 1:
      return '收料中';
    case 2:
      return '已作废';
    case 3:
      return '已完成';
    default:
      return '';
  }
}//选择状态表现方式（根据状态值）
const CurrentChange = (val) => {
  currentRow.value = val;
};//选中行
//------对话框（对表单数据传递）----
const closeDialog = () => {
  formData.value = {};
  showDialog.value = false;
  getTableData();
};//关闭对话框
const openDialog = () => {
  title.value="新增";
  showDialog.value = true;

};//打开对话框
const openDialogEdit = () => {
  // 如果没有选中行，则提示用户先选择一行
  if (!currentRow.value) {
    ElMessage.warning('请先选择要修改的行！');
    return;
  }
  title.value="修改";
  formData.value = currentRow.value;
  //console.log("formData",JSON.stringify(formData.value));
  showDialog.value = true;
}//打开对话框并传值（表单数据）
const SeeDetails = () => {
  // 如果没有选中行，则提示用户先选择一行
  if (!currentRow.value) {
    ElMessage.warning('请先选择要查看的行！');
    return;
  }
  title.value="查看详情";
  formData.value = currentRow.value;
  //console.log("formData",JSON.stringify(formData.value));
  showDialog.value = true;
};//查看详情按钮
const handException = () => {
  // 如果没有选中行，则提示用户先选择一行
  if (!currentRow.value) {
    ElMessage.warning('请先选择要查看的行！');
    return;
  }
  title.value="异常处理";
  formData.value = currentRow.value;
  //console.log("formData",JSON.stringify(formData.value));
  showDialog.value = true;
};//查看详情按钮
const updateStatus2 = () => {
  currentRow.value.managementStatus=2;
  const hide = messageLoading('提交中...', 0);
  updateBgmReceiveManagement(currentRow.value)
    .then((msg) => {
      hide();
      message.success(msg);
      getTableData();
    })
    .catch((e) => {
      hide();
      message.error(e.message);
    });
}//作废按钮
const updateStatus3 = () => {
  currentRow.value.managementStatus=3;
  const hide = messageLoading('提交中...', 0);
  updateBgmReceiveManagement(currentRow.value)
    .then((msg) => {
      hide();
      message.success(msg);
      getTableData();
    })
    .catch((e) => {
      hide();
      message.error(e.message);
    });
}//取消作废按钮
const deleteManagement = () => {
  if (!currentRow.value) {
    ElMessage.warning('请先选择要删除的行！');
    return;
  }
  Modal.confirm({
    title: '提示',
    content: '确定要删除选中的协议吗?',
    icon: createVNode(ExclamationCircleOutlined),
    maskClosable: true,
    onOk: () => {
      const hide = messageLoading('请求中..', 0);
      removeBgmReceiveManagement(currentRow.value.id)
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
}//删除协议及关联数据
const exportExcel = () => {

// 遍历 tableData 计算重量总和
  let srt=`总计：${total.value}条`;
  let totalCollectedWeight = 0;
  let totalClaspWeight=0;
  let totalGrossWeight = 0;
  let totalTareWeight = 0;
  let totalNetWeight = 0;
    const array = [
      ['收料单'],
      [selectFormData.makeTime1,'至',selectFormData.makeTime2],
      ['收料编号', '过毛时间', '供货协议', '原材料', '规格型号', '供应商',
      '运输车辆', '实收重量', '累计扣杂', '储料仓', '收料人','毛重', '皮重', '净重', '备注'],
    ];
  tableData.value.forEach((d) => {
    array.push([
      d.managementNumber,
      d.fleeceTime,
      d.agreementNumber,
      d.smTypeName,
      d.smSpecs,
      d.vendorName,
      d.transportVehicle,
      d.collectedWeight,
      d.claspWeight,
      d.storageBin,
      d.overHairedPersonnel,
      d.grossWeight,
      d.tareWeight,
      d.netWeight,
      d.remark,
    ]);

    // 累加重量总和
    totalCollectedWeight += Number(d.collectedWeight);
    totalClaspWeight += Number(d.claspWeight);
    totalGrossWeight += Number(d.grossWeight);
    totalTareWeight += Number(d.tareWeight);
    totalNetWeight += Number(d.netWeight);
  });
  array.push([
    srt, '', '', '', '', '', '',
    totalCollectedWeight, totalClaspWeight, '', '',
    totalGrossWeight, totalTareWeight, totalNetWeight, ''
  ]);
    const sheetName = 'Sheet1';
    const workbook = {
      SheetNames: [sheetName],
      Sheets: {}
    };
    const sheet = utils.aoa_to_sheet(array);
    workbook.Sheets[sheetName] = sheet;
    // 设置列宽
    sheet['!cols'] = [
      { wch: 20 },
      { wch: 20 },
      { wch: 20 },
      { wch: 10 },
      { wch: 20 },
      { wch: 40 },
      { wch: 10 }
    ];
    writeFile(workbook, `收料单-${new Date().toLocaleDateString()}.xlsx`);
}//导出按钮，导出excel
const isStatus0= () => {
  if(currentRow&&currentRow.value){
    if (currentRow.value.managementStatus==0){
      return true
    }
  }
}//判断为0状态
const isStatus1= () => {
  if(currentRow&&currentRow.value){
    if (currentRow.value.managementStatus==1){
      return true
    }
  }
}//判断为1状态
const isStatus2= () => {
  if(currentRow&&currentRow.value){
    if (currentRow.value.managementStatus==2){
      return true
    }
  }
}//判断为2状态
const isStatus3= () => {
  if(currentRow&&currentRow.value){
    if (currentRow.value.managementStatus==3){
      return true
    }
  }
}//判断为3状态
onMounted(() => {
  //获取表格数据
  getTableData();
  getSelectPurchasers();
  //搜索栏,
  getSelectSupplyMaterials();//获取 供应原材料列表（搜索框）
  getSelectMaterials();//获取 平台原材料列表（搜索框）
  getSelectVendors();//获取 供应商列表（搜索框）
});
</script>

<style scoped>
.el-header {
  padding-left: 0px;
  padding-right: 2px;
  height: 100px;
  border-bottom: 1px solid #fdfdfe;
}

.el-main {
  padding-left: 0px;
  padding-top: 0px;
  padding-right: 2px;
}

.el-footer {
  position: fixed;
  bottom: 5px;
  left: 0px;
  width: 100%;
  height: 35px;
  background: white;
}

.title-container {
  display: flex;
  align-items: center;
  width: 100%;
  background: white;
  height: 38px;
  padding-left: 10px;
  border: 1px solid #dcd0d0;
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

.page-title {
  margin: 0;
  font-size: 15px;
  font-weight: bold;
  color: #00a3ff;
  flex: 1;
}

.demo-pagination-block {
  margin-top: 0px;
  padding-left: 50px;
  margin-bottom: 16px;
  display: flex;
  margin-top: 2px;background: white
}

.el-table__body-wrapper {
  overflow-x: auto;
}

.selected0 {
  width: 60px;
  background-color: #71838d;
  color: #fff;
}

.selected1 {
  width: 60px;
  background-color: #409EFF;
  color: #fff;
}


.selected2 {
  width: 60px;
  background-color: #e51c33;
  color: #fff;
}

.selected3 {
  width: 60px;
  background-color: #3cc007;
  color: #fff;
}

.default {
  width: 60px;
}

</style>
