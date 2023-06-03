<template>

  <div class="common-layout">
    <el-container>
      <el-header>
        <!--  标头-->
        <div class="title-container">
          <el-icon size="20px" style="margin-right: 5px">
            <ChromeFilled/>
          </el-icon>
          <h8 class="page-title">采购协议</h8>
          <el-divider direction="vertical"/>
          <el-button  color="#7dd733"  plain @click="openDialog"><el-icon><FirstAidKit /></el-icon>新增</el-button>
          <el-button :disabled="isStatus1()" color="#00a3ff" plain @click="openDialogEdit"><el-icon><EditPen /></el-icon>修改</el-button>
          <el-button :disabled="!isStatus0()" color="red" plain @click="deleteAgreement"><el-icon><Delete /></el-icon>删除</el-button>
          <el-divider direction="vertical"/>
          <el-button v-if="isStatus2()" color="red" plain @click="updateStatus3"><el-icon><ZoomIn /></el-icon>停用</el-button>
          <el-button v-if="isStatus3()" color="#7dd733" plain @click="updateStatus2"><el-icon><ZoomIn /></el-icon>启用</el-button>
          <el-divider v-if="isStatus2()||isStatus3()" direction="vertical"/>
          <el-button color="#00a3ff" plain @click="SeeDetails"><el-icon><ZoomIn /></el-icon>查看详情</el-button>
          <el-button :disabled="!isStatus1()" color="#00a3ff" plain  @click="audit"><el-icon><Edit /></el-icon>审核</el-button>
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
                  />
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="供应商:" label-width="70">
                  <el-select v-model="selectFormData.agreementVendorId" placeholder="请选择供应商" size="small">
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
                  <el-select v-model="selectFormData.agreementSupplyName" placeholder="请选择供应原材料" size="small">
                    <el-option
                      v-for="sm in supplyMaterials"
                      :key="sm.supplyMaterialId"
                      :label="sm.smName"
                      :value="sm.smName">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row style="margin-top: -35px;margin-left: 15px;">
              <span>制定期间:</span>
            </el-row>
            <el-row style="margin-top: -10px;margin-left: 70px">
              <el-col :span="5">
                <el-form-item label=" ">
                  <el-date-picker
                    v-model="selectFormData.makeTime2"
                    format="YYYY-MM-DD HH:mm:ss"
                    size="small"
                    type="datetime"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="采购员:" label-width="70">
                  <el-select v-model="selectFormData.agreementPurchaser" placeholder="请选择采购人" size="small">
                    <el-option
                      v-for="purchaser in purchasers"
                      :key="purchaser.id"
                      :label="purchaser.purchaserName"
                      :value="purchaser.purchaserName">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item label="协议编号:" label-width="100">
                  <el-input v-model="selectFormData.agreementNumber" size="small"/>
                </el-form-item>
              </el-col>

              <el-col :span="4" style="margin-left: 11px;margin-top: -12px">
                <el-form-item>
                  <el-button icon="Search" plain style="width: 65px" type="primary" @click="select">查询</el-button>
                  <el-button icon="Close" native-type="reset" plain style="width: 65px" @click="reset">重置</el-button>
                </el-form-item>
              </el-col>

            </el-row>
          </el-form>
        </div>
      </el-header>
      <el-main>
        <el-container>
          <!--主体-->
          <el-main style="padding-top: 2px;padding-left: 2px;width: 1000px">
            <el-table :data="tableData"
                      border
                      highlight-current-row
                      size="small"
                      stripe
                      style="height: 1000px;"
                      @current-change="CurrentChange">
              <el-table-column type="index" width="30px"></el-table-column>
              <el-table-column label="协议编号" prop="agreementNumber" width="90px"></el-table-column>
              <el-table-column label="状态" prop="agreementStatus" width="80px">
                <template #default="{row}">
                  <el-tag
                    :class="{
                            'selected0': row.agreementStatus === 0,
                            'selected1': row.agreementStatus === 1,
                            'selected2': row.agreementStatus === 2,
                            'selected3': row.agreementStatus === 3,
                            'selected4': row.agreementStatus === 4
                          }">
                   {{getStatus(row.agreementStatus)}}
                  </el-tag>
                </template>
              </el-table-column>
              <el-table-column label="制定时间" prop="agreementCreateTime" width="130px"></el-table-column>
              <el-table-column label="供应商" prop="agreementVendorName"></el-table-column>
              <el-table-column label="联系人" prop="agreementContact"></el-table-column>
              <el-table-column label="联系方式" prop="agreementPhone" width="90px"></el-table-column>
              <el-table-column label="备注" prop="agreementRemark" width="90px"></el-table-column>
            </el-table>
          </el-main>
          <!--侧边数据-->
          <el-aside width="370px">
            <el-table :data="tableData1" :span-method="mergeCreateTime"
                      border
                      size="small"
                      stripe>
              <el-table-column label="创建时间" prop="createTime" width="130px"></el-table-column>
              <el-table-column label="供应原材料" prop="supplyName" width="160px"></el-table-column>
              <el-table-column label="售价">
                <template #default="{ row }">
                  {{ row.price }} 元/吨
                </template>
              </el-table-column>
            </el-table>
          </el-aside>

        </el-container>
      </el-main>
      <el-footer>
        <!--          分页 和  状态选择器-->
        <div class="demo-pagination-block" style="margin-top: 2px">
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
                     @click="toggleStatus(1)">审核中
          </el-button>
          <el-button :class="{ 'selected2': selectedStatus.includes(2), 'default': !selectedStatus.includes(2) }"
                     class="add-button"
                     style="height: 30px"
                     @click="toggleStatus(2)">执行中
          </el-button>
          <el-button :class="{ 'selected3': selectedStatus.includes(3), 'default': !selectedStatus.includes(3) }"
                     class="add-button"
                     style="height: 30px"
                     @click="toggleStatus(3)">停用
          </el-button>
          <el-button :class="{ 'selected4': selectedStatus.includes(4), 'default': !selectedStatus.includes(4) }"
                     class="add-button"
                     style="height: 30px"
                     @click="toggleStatus(4)">已完成
          </el-button>

        </div>
      </el-footer>
    </el-container>

    <!--    对话框-->
    <agreement-edit :formData="formData" :purchasers="purchasers"
                    :show-dialog="showDialog"
                    :vendors="vendors"
                    :title="title"
                    @onClose="closeDialog"/>

  </div>


</template>

<script setup>
import {computed, createVNode, onMounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {ExclamationCircleOutlined} from "@ant-design/icons-vue";
import {messageLoading} from "ele-admin-pro";
import {message, Modal} from "ant-design-vue/es";
import {
  pageBgmAgreement,
  removeBgmAgreementPro,
  selectBgmAgreementPro, updateBgmAgreement,
} from "@/api/bgm/bgmMaterial/agreement";
import {pageBgmVendor} from "@/api/bgm/bgmMaterial/vendor";
import {pageBgmSupplyMaterial} from "@/api/bgm/bgmMaterial/supply";
import {pageBgmPurchaser} from "@/api/bgm/bgmMaterial/purchaser";
import AgreementEdit from "@/views/bgm/bgmMaterial/agreement/components/agreement-edit.vue";
//-------供应商--------
const tableData = ref([]);
const total = ref(0);
const pageSize = ref(10);
const currentPage = ref(1);
const tableData1 = ref([]);//右侧小的表格
const showDialog = ref(false);//对话框控制
const formData = ref({
  agreementNumber: '',
  agreementCreateTime: '',
});//表单
const title=ref('');
const currentRow = ref(null);//表格选择行
//搜索栏属性
const selectFormData = reactive({
  agreementVendorId: '',//搜索供应商名字
  agreementSupplyName: '',//搜索供应原材料
  agreementPurchaser: '',//搜索采购人
  agreementNumber: '',//搜索订单编号
  makeTime1: new Date(`${new Date().getFullYear()}-01-01T00:00:00`),
  makeTime2: new Date(`${new Date().getFullYear()}-12-31T23:59:59`),
})
//-----搜索栏(所需数据)--------
const purchasers = ref([]);//采购人列表
const vendors = ref([]);//供应商
const supplyMaterials = ref([]);//供应材料（未实现）
const selectedStatus = ref([0, 1, 2, 3, 4]);//状态集合

const getTableData = async () => {
  const res = await pageBgmAgreement({
    // const res = await pageBgmAgreement({
    page: currentPage.value,
    limit: pageSize.value,
  });
  console.log('成功获取到table数据：', res);
  tableData.value = res.list;
  total.value = res.count;
  //console.log(tableData.value);
  //获取第一个侧边表格数据
  if (res.count > 0) {
    await CurrentChange(tableData.value[0]);
  }
};//获取表格数据
const select = async () => {
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
  const param = {
    page: currentPage.value,
    limit: pageSize.value,
    agreementNumber: selectFormData.agreementNumber,
    agreementPurchaser: selectFormData.agreementPurchaser,
    agreementVendorId: selectFormData.agreementVendorId,
    time1: time1,
    time2: time2,
    agreementStatusList: selectedStatus.value
  }
  console.log("按钮", param.agreementStatusList)
  const res = await pageBgmAgreement(param);
  console.log('成功获取到table数据：', res);
  tableData.value = res.list;
  total.value = res.count;
  //console.log(tableData.value);
  //获取第一个侧边表格数据
  if (res.count > 0) {
    await CurrentChange(tableData.value[0]);
  }
}//搜索框的搜索功能
const reset = () => {
  selectFormData.agreementVendorId = '';
  selectFormData.agreementSupplyName = '';
  selectFormData.agreementPurchaser = '';
  selectFormData.agreementNumber = '';
  selectFormData.makeTime1 = new Date(`${new Date().getFullYear()}-01-01T00:00:00`);
  selectFormData.makeTime2 = new Date(`${new Date().getFullYear()}-12-31T23:59:59`);
};//重置查询框
const handleSizeChange = async (val) => {
  pageSize.value = val;
  await getTableData();
};//分页
const handleCurrentChange = async (val) => {
  currentPage.value = val;
  await getTableData();
};//分页
//----搜索栏-----
const getSelectVendors = async () => {
  const res = await pageBgmVendor({});
  //console.log('成功获取到SelectVendors数据：', res);
  vendors.value = res.list;
}//搜索栏,获取供应商
const getSelectPurchasers = async () => {
  const res = await pageBgmPurchaser({});
  //console.log('成功获取到SelectPurchaser数据：', res);
  purchasers.value = res.list;
}//搜索栏,获取采购人
const getSelectSupplyMaterials = async () => {
  const res = await pageBgmSupplyMaterial({});
  //console.log('成功获取到SelectSupplyMaterial数据：', res);
  supplyMaterials.value = res.list;

}//搜索栏,获取供应原材料
//--选中行  （获取agreementId查询所有数据）
const CurrentChange = async (val) => {
  //console.log("agreementId:",val.agreementId);
  if (val == null) {
    val = tableData.value[0];
  }
  const data = await selectBgmAgreementPro({
    agreementId: val.agreementId,
  });
  //console.log("根据agreementId获得的一条完整协议：", JSON.stringify(data));
  if (data == null) {
    tableData1.value = null;
  } else {
    tableData1.value = data.supplyMaterialList;
    //console.log("tableData1",JSON.stringify(tableData1.value));
  }
  //console.log("data",data);
  currentRow.value = data;
};//****选中行事件（获取agreementId查询所有数据），并将侧边数据显示
const mergeCreateTime = ({row, column, rowIndex, columnIndex}) => {
  if (columnIndex === 0) {
    if (rowIndex > 0 && tableData1.value[rowIndex].createTime === tableData1.value[rowIndex - 1].createTime) {
      return {
        rowspan: 0,
        colspan: 0
      }
    } else {
      let rowspan = 1;
      for (let i = rowIndex + 1; i < tableData1.value.length; i++) {
        if (tableData1.value[i].createTime === tableData1.value[rowIndex].createTime) {
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
}//合并侧边表格函数（时间）

const getSerialNumber = () => {
  const now = new Date();
  const year = now.getFullYear().toString().slice(-2);
  const month = (now.getMonth() + 1).toString().padStart(2, '0');
  const date = now.getDate().toString().padStart(2, '0');
  const serial = Math.floor(Math.random() * 900) + 100;

  return `XY${year}${month}${date}${serial}`;
}//获取编号，给新增订单（表单）
const getCurrentTime = () => {
  const now = new Date();
  const year = now.getFullYear().toString();
  const month = (now.getMonth() + 1).toString().padStart(2, '0');
  const date = now.getDate().toString().padStart(2, '0');
  const hours = now.getHours().toString().padStart(2, '0');
  const minutes = now.getMinutes().toString().padStart(2, '0');
  const seconds = now.getSeconds().toString().padStart(2, '0');
  return `${year}-${month}-${date} ${hours}:${minutes}:${seconds}`;
}//获取当前时间，给新增订单（表单）
const toggleStatus = (status) => {
  const index = selectedStatus.value.indexOf(status)
  if (index === -1) {
    selectedStatus.value.push(status) // 选中状态
  } else {
    selectedStatus.value.splice(index, 1) // 取消选中状态
  }
  selectedStatus.value.sort((a, b) => a - b) // 对数组元素进行排序
  select();
};//更改状态集
const getStatus=(status)=>{
  switch (status) {
    case 0:
      return '制单';
    case 1:
      return '审核中';
    case 2:
      return '执行中';
    case 3:
      return '停用';
    case 4:
      return '已完成';
    default:
      return '';
  }
}//选择状态表现方式（根据状态值）
//------对话框（对表单数据传递）----
const closeDialog = () => {
  formData.value = {};
  showDialog.value = false;
  getTableData();
};//关闭对话框
const openDialog = () => {
  title.value="新增";
  showDialog.value = true;
  formData.value.agreementNumber = getSerialNumber();
  formData.value.agreementCreateTime = getCurrentTime();
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
const audit = () => {
  // 如果没有选中行，则提示用户先选择一行
  if (!currentRow.value) {
    ElMessage.warning('请先选择要审核的行！');
    return;
  }
  title.value="审核";
  formData.value = currentRow.value;
  //console.log("formData",JSON.stringify(formData.value));
  showDialog.value = true;

};//审核按钮
const deleteAgreement = () => {
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
      removeBgmAgreementPro(currentRow.value.agreementId)
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
const updateStatus3 = () => {
 /* console.log("cc",currentRow.value.agreementStatus);
  currentRow.value.agreementStatus=3;
  console.log("cc",currentRow.value.agreementStatus);*/
  currentRow.value.agreementStatus=3;
  const hide = messageLoading('提交中...', 0);
  updateBgmAgreement(currentRow.value)
    .then((msg) => {
      hide();
      message.success(msg);
      getTableData();
    })
    .catch((e) => {
      hide();
      message.error(e.message);
    });
}//停用按钮，状态改为停用（3）
const updateStatus2 = () => {
  currentRow.value.agreementStatus=2;
  const hide = messageLoading('提交中...', 0);
  updateBgmAgreement(currentRow.value)
    .then((msg) => {
      hide();
      message.success(msg);
      getTableData();
    })
    .catch((e) => {
      hide();
      message.error(e.message);
    });
}//启用按钮，状态改为执行中（2）

const isStatus0= () => {
  if(currentRow&&currentRow.value){
    if (currentRow.value.agreementStatus==0){
      return true
    }
  }
}//判断为0状态
const isStatus1= () => {
  if(currentRow&&currentRow.value){
    if (currentRow.value.agreementStatus==1){
      return true
    }
  }
}//判断为1状态
const isStatus2= () => {
  if(currentRow&&currentRow.value){
    if (currentRow.value.agreementStatus==2){
      return true
    }
  }
}//判断为2状态
const isStatus3= () => {
  if(currentRow&&currentRow.value){
    if (currentRow.value.agreementStatus==3){
      return true
    }
  }
}//判断为3状态
const isStatus4= () => {
  if(currentRow&&currentRow.value){
    if (currentRow.value.agreementStatus==4){
      return true
    }
  }
}//判断为4状态
onMounted(() => {
  //获取表格数据
  getTableData();
  //搜索栏,
  getSelectVendors();//获取供应商列表
  getSelectPurchasers();//获取采购员
  getSelectSupplyMaterials();//获取供应原材料
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
  margin-left: 200px;
  margin-bottom: 16px;
  display: flex;
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
  background-color: #f38541;
  color: #fff;
}

.selected3 {
  width: 60px;
  background-color: #e51c33;
  color: #fff;
}

.selected4 {
  width: 60px;
  background-color: #3cc007;
  color: #fff;
}

.disabled-button {
  margin-left: 2px;
  margin-right: 2px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 15px;
  cursor: not-allowed;
  opacity: 0.5;
}
.add-button, .modify-button, .delete-button, .adm-button {
  margin-left: 2px;
  margin-right: 2px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 15px;
}
.default {
  background-color: #fff;
  color: #000;
  width: 60px;
}
</style>
