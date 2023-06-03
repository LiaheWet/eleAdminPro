<template>
    <el-dialog
      v-model="showDialog"
      :before-close="closeForm"
      :show-close="false"
      :title="title"
      draggable="true"
      style="width: 1150px;  height: 600px;margin-left: 250px;background: #7ab2e0;"
      @open="init()"
    >
      <template #header="{ close, titleId, titleClass }">
        <div class="my-header">
          <h4 :id="titleId" :class="titleClass" style="font-size:medium ">{{title}}</h4>
          <el-button type="danger" @click="close">
            <el-icon class="el-icon--left">
              <CircleCloseFilled/>
            </el-icon>
            Close
          </el-button>
        </div>
      </template>
      <el-form ref="ruleFormRef"
               :model="props.formData"
               :rules="rules"
               class="formClass"
               label-width="100px">
        <el-row style="border-bottom: 1px solid #c9bcbc;">
          <el-col :span="4" style="display: flex; align-items: center;">
            <el-icon size="large">
              <Document/>
            </el-icon>
            <label style="color: #00a3ff;font-size: 15px; margin-left: 10px;">采购协议</label>
          </el-col>
          <el-col :span="8">
            <span style="display: flex; align-items: center;">
              编号：<div style="color:blue;">{{ props.formData.agreementNumber }}</div>
             </span>
          </el-col>
          <el-col :span="8">
             <span style="display: inline-block;">
              <div class="serial-number-value">制定时间：{{ props.formData.agreementCreateTime }}</div>
             </span>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;margin-top: 5px;height: 50px">
          <el-col :span="8">
            <el-form-item label="往来单位:" prop="agreementVendorName">
              <el-input v-model="props.formData.agreementVendorName" placeholder="请选择往来单位" readonly
                        style="width: 300px" @click="showVendorDialog">
                <i class="el-select__caret el-input__icon el-icon-arrow-down"></i>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="供应方式:">
              <el-input v-model="props.formData.agreementMethod" :model-value="'往来单位运输'" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人:" prop="agreementContact" >
              <el-select v-model="props.formData.agreementContact"
                         placeholder="请选择联系人"
                         style="width: 300px;" @change="handleAgreementContactChange">
                <el-option
                  v-for="vendorContact in bgmVendorContactList"
                  :key="vendorContact.id"
                  :label="vendorContact.contactName"
                  :value="vendorContact.contactName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;margin-top: 0px;height: 35px">
          <el-col :span="8">
            <el-form-item label="联系电话:" prop="agreementPhone">
              <el-input v-model="formData.agreementPhone"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="采购时间:" prop="agreementBuyTime">
              <el-date-picker
                v-model="props.formData.agreementBuyTime"
                style="width: 300px"
                placeholder="选择采购日期"
                type="datetime"
                value-format="YYYY-MM-DD hh:mm:ss"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交货地点:" prop="agreementAddress">
              <el-input v-model="formData.agreementAddress"/>
            </el-form-item>
          </el-col>
        </el-row>
        <div class="common-layout" style="margin-top: 14px">
          <el-container>
            <el-header style="border: 1px solid #dcd0d0;height: 35px">
              <div class="title-container" style="display: flex; justify-content: space-between; align-items: center;">
                <h8 style="font-size: 15px;font-weight: bold; margin: 0;">原材料价格</h8>
                <el-button style="color: #16a42c; height: 28px;" @click="onAddItem">
                  <el-icon>
                    <Plus/>
                  </el-icon>
                  添加执行日期
                </el-button>
              </div>
            </el-header>

            <el-main style="padding-top: 2px;padding-left: 0px;padding-right: 0px;height: 250px;">
              <el-table :data="props.formData.supplyMaterialList"
                        :rules="rules"
                        border
                        size="small" stripe>
                <el-table-column label="执行期" width="190px">
                  <template #default="{ row, $index }">
                    <el-date-picker
                      v-model="row.createTime"
                      style="width: 180px;"
                      type="datetime"
                      value-format="YYYY-MM-DD hh:mm:ss"
                    />
                  </template>
                </el-table-column>
                <el-table-column label="供应原材料"  width="160px">
                  <template #default="{ row, $index }">
                    <el-select v-model="row.supplyName"
                               clearable
                               editable placeholder="供应原材料" @change="handleSupplyNameChange(row)">
                      <el-option
                        v-for="supplyNames in bgmSupplyMaterialList"
                        :key="supplyNames.supplyMaterialId"
                        :label="supplyNames.smName"
                        :value="supplyNames.smName">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column label="平台原材料" width="120px">
                  <template #default="{ row, $index }">
                    <el-input v-model="row.materialName" clearable editable placeholder="平台原材料" readonly></el-input>
                  </template>
                </el-table-column>
                <el-table-column label="计量单位" width="80">
                  <template #default="{ row, $index }">
                    <el-select v-model="row.measurement" editable placeholder="计量单位">
                      <el-option label="吨" value="吨"></el-option>
                      <el-option label="千克" value="千克"></el-option>
                      <el-option label="方" value="方"></el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column label="计划数量">
                  <template #default="{ row, $index }">
                    <el-input v-model="row.number" :formatter="(value) => {return /^\d+$/.test(value) ? value : ''}"
                              :maxlength="5" clearable editable placeholder="计划数量"
                              @blur="handleTotalPriceBlur(row)"></el-input>
                  </template>
                </el-table-column>
                <el-table-column label="单价" width="120px">
                  <template #default="{ row, $index }">
                    <el-input v-model="row.price"
                              :formatter="(value) => {return /^\d+(?:\.\d{1,2})?$/.test(value) ? value : ''}"
                              clearable editable placeholder="单价" type="number"
                              @blur="handleTotalPriceBlur(row)"></el-input>
                  </template>
                </el-table-column>
                <el-table-column label="计划金额">
                  <template #default="{ row, $index }">
                    <el-input v-model="row.totalPrice" clearable editable placeholder="计划金额" readonly></el-input>
                  </template>
                </el-table-column>
                <el-table-column label="操作" type="action" width="80px">
                  <template #default="scope">
                    <el-button size="mini" type="danger" @click="deleteRow(scope.$index)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-main>
          </el-container>
        </div>
        <el-row style="background: #ffffff;height: 50px;">
          <el-col :span="12">
            <el-form-item label="异常处理:" >
              <el-input v-model="props.formData.agreementException"/>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="违约责任:" prop="agreementBreach">
              <el-input v-model="props.formData.agreementBreach"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;height: 40px;">
          <el-col :span="8">
            <el-form-item label="备注:">
              <el-input v-model="props.formData.agreementRemark"/>
            </el-form-item>
          </el-col>
          <el-col :span="8" style="width: 300px">
            <el-form-item label="采购人:">
              <el-select v-model="props.formData.agreementPurchaser" placeholder="请选择采购人" style="width: 200px">
                <el-option
                  v-for="purchaser in purchasers"
                  :key="purchaser.id"
                  :label="purchaser.purchaserName"
                  :value="purchaser.purchaserName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8" style="width: 300px">
            <el-form-item style="float: left;margin-left: 10px" >
              <el-button type="primary" v-if="title=='新增'||title=='修改'" @click="submitForm">
                <el-icon><CircleCheck /></el-icon>保存
              </el-button>
              <el-button v-if="title=='新增'" type="success" @click="submitForm1">
                <el-icon><Finished /></el-icon>提交
              </el-button>
              <el-button v-if="title=='审核'" type="success" @click="submitForm2">
                <el-icon><Finished /></el-icon>审核通过
              </el-button>
              <el-button @click="closeForm">
                <el-icon>
                  <CircleClose/>
                </el-icon>关闭
              </el-button>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>

      <!--      选择来往单位对话框-->
      <el-dialog v-model="vendorDialogVisible" :center="true"
                 style="width: 800px;height: 500px;margin-top: 150px;margin-left: 400px" title="选择往来单位">
        <el-header style="height: 30px">
          <el-row>
            <el-col :span="9">
              <el-form-item label="单位名称:">
                <el-input v-model="selectDate.vendorName"/>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="采购员:">
                <el-input v-model="selectDate.buyer"/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-button class="add-button" style="color: #8eb97e;margin-left: 10px" @click="getVendorData">查询</el-button>
              <el-button class="modify-button" style="color: #00a3ff" @click="reset">重置</el-button>
            </el-col>
          </el-row>
        </el-header>
        <el-main>
          <el-table :data="vendorData"
                    border
                    height="300px"
                    stripe
                    @current-change="chooseRow">
            <el-table-column type="index" width="30px"></el-table-column>
            <el-table-column label="单位名称" prop="vendorName"></el-table-column>
            <el-table-column label="单位地址" prop="vendorAddress"></el-table-column>
            <el-table-column label="联系人" prop="vendorRepresent" width="90px"></el-table-column>
            <el-table-column label="联系方式" prop="vendorPhone"></el-table-column>
            <el-table-column label="采购员" prop="buyer" width="90px"></el-table-column>
          </el-table>
        </el-main>
        <el-footer style="position: absolute; bottom: 0; width: 100%;">
          <el-row>
            <el-col :span="18">
              <!--          分页-->
              <el-pagination
                v-model:current-page="currentPageVendor"
                v-model:page-size="pageSizeVendor"
                :page-sizes="[10, 20, 30, 40]"
                :total="totalVendor"
                layout="total, sizes, prev, pager, next, jumper"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
              />
            </el-col>
            <el-col :span="6">
              <el-button @click="chooseRowCancel">取消</el-button>
              <el-button type="primary" @click="chooseRowYes">确定</el-button>
            </el-col>
          </el-row>
        </el-footer>
      </el-dialog>
    </el-dialog>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {selectBgmVendorPro, pageBgmVendor} from "@/api/bgm/bgmMaterial/vendor";
import * as Vue from "lodash-es";
import {messageLoading} from "ele-admin-pro/es";
import {addBgmAgreementPro, updateBgmAgreementPro} from "@/api/bgm/bgmMaterial/agreement";
import {message} from "ant-design-vue/es";

const emit = defineEmits(['onClose'])
const props = defineProps({
  showDialog: false,
  formData: {
    supplyMaterialList: [],
  },
  vendors: [],
  purchasers: [],
  title: ''
});
const vendorData = ref([]);//供应商列表
const totalVendor = ref(0);
const pageSizeVendor = ref(10);
const currentPageVendor = ref(1);
const ruleFormRef = ref(null);

const vendorRow = ref([]);//选择的供应商（暂存数据）
let bgmVendorContactList = ref([]);//选择的供应商的联系人
let bgmSupplyMaterialList = ref([]);//选择的供应商的供应材料
let vendorDialogVisible = ref(false);//控制vendor对话框
const selectDate=ref({
  vendorName:'',
  buyer:'',
})
//---------关于vendor 数据的展示 获取数据 ————————
const onAddItem = () => {
  if (props.formData.agreementVendorName == null) {
    ElMessage.warning('请先选择往来单位');
  } else {
    const now = new Date();
    const year = now.getFullYear().toString();
    const month = (now.getMonth() + 1).toString().padStart(2, '0');
    const date = now.getDate().toString().padStart(2, '0');
    const newItem = {
      createTime: `${year}-${month}-${date} 08:00:00`,
      supplyName: '',
      materialName: '',
      specs:'',
      measurement: '吨',
      price: '0.00',
      number: '0',
      totalPrice: '0.00'
    };
    if (!props.formData) {
      props.formData = {supplyMaterialList: [newItem]};
    } else if (!props.formData.supplyMaterialList) {
      props.formData.supplyMaterialList = [newItem];
    } else {
      props.formData.supplyMaterialList.push(newItem);
    }
  }
};//增加具体材料行
const deleteRow = (index) => {
  props.formData.supplyMaterialList.splice(index, 1);
};//delete具体材料行
const getVendorData = async () => {
  const res = await pageBgmVendor({
    page: currentPageVendor.value,
    limit: pageSizeVendor.value,
    vendorName:selectDate.value.vendorName,
    buyer:selectDate.value.buyer,
  });
  vendorData.value = res.list;
  totalVendor.value = res.count;
  // console.log("getVendorData",res);
  // console.log("bgmVendorAccountList",res.list[0].bgmVendorContactList);
};//获取Vendor列表
const reset = () => {
  selectDate.vendorName='';
  selectDate.buyer='';
}
const handleSizeChange = async (val) => {
  pageSizeVendor.value = val;
  await getVendorData();
};//分页显示
const handleCurrentChange = async (val) => {
  currentPageVendor.value = val;
  await getVendorData();
};//分页显示

const showVendorDialog = () => {
  vendorDialogVisible.value = true;
  getVendorData();
};//控制vendor对话框
const chooseRow = async (val) => {
  if (val != null) {
    const data = await selectBgmVendorPro({
      vendorId: val.vendorId,
    });
    vendorRow.value = data;
  }
  //console.log("vendorRow", JSON.stringify(vendorRow.value));
};//在Vendor列表选择供应商的行，获取数据  console.log(vendorRow)
const chooseRowCancel = () => {
  vendorRow.value = null;
  vendorDialogVisible.value = false;
};//取消选择行chooseRowCancel，清理数据
const chooseRowYes = () => {
  props.formData.agreementVendorName = vendorRow.value.vendorName;
  props.formData.agreementVendorId = vendorRow.value.vendorId;
  vendorDialogVisible.value = false;
  bgmVendorContactList.value = vendorRow.value.bgmVendorContactList;
  bgmSupplyMaterialList.value = vendorRow.value.bgmSupplyMaterialList;
  //console.log("bgmVendorContactList", bgmVendorContactList.value);
  //console.log("bgmSupplyMaterialList", bgmSupplyMaterialList.value);

};//确定选择chooseRowYes，并传递数据（agreementVendorName，agreementVendorId，bgmVendorContactList）console.log(bgmVendorContactList);
const handleAgreementContactChange = () => {
  let agreementPhone = "";
  for (const contact of bgmVendorContactList.value) {
    if (contact.contactName === props.formData.agreementContact) {
      agreementPhone = contact.contactPhone;
      break;
    }
  }

  props.formData.agreementPhone = agreementPhone;
}//获取联系人电话（根据选择的联系人：agreementPhone）
const handleSupplyNameChange = (row) => {
  /*const smName = props.formData.supplyMaterialList.smName;
  console.log("supplyMaterialId",smName);*/
  let materialName = "";
  let specs="";
  let smId=0;
  for (const contact of bgmSupplyMaterialList.value) {
    if (contact.smName === row.supplyName) {
      materialName = contact.smTypeName;
      specs=contact.smSpecs;
      smId=contact.supplyMaterialId;
      break;
    }
  }
  row.materialName = materialName;
  row.specs=specs;
  row.smId=smId;
}//根据所选择的供应原材料（supplyName）将smTypeName填入平台原材料（materialName）
const handleTotalPriceBlur = (row) => {
  const number = parseFloat(row.number);
  const price = parseFloat(row.price);
  const totalPrice = isNaN(number) || isNaN(price) ? '' : (number * price).toFixed(2);
  row.totalPrice = totalPrice;
}
//--------数据交互----
//提交表单数据
const rules = reactive({
  agreementVendorName: [
    {
      required: true,
      message: '请选择往来单位',
      trigger: 'blur',
    },
  ],
  agreementContact: [
    {
      required: true,
      message: '请选择联系人',
      trigger: 'blur',
    },
  ],

  agreementPhone: [
    {
      required: true,
      message: '请输入联系电话',
      trigger: 'blur',
    },
  ],
  agreementBuyTime: [
    {
      required: true,
      message: '请选择采购时间',
      trigger: 'blur',
    },
  ],
  agreementAddress: [
    {
      required: true,
      message: '请输入交货地址',
      trigger: 'blur',
    },
  ],
  agreementBreach: [
    {
      required: true,
      message: '请输入违约责任',
      trigger: 'blur',
    },
  ],
});
const submitForm = async () => {
  const formEl = ruleFormRef.value;
  const valid = await formEl.validate();
  console.log("提交表单数据：" + JSON.stringify(props.formData));
  if(valid){
    const hide = messageLoading('提交中...', 0);
    (props.formData.agreementId ? updateBgmAgreementPro : addBgmAgreementPro)(props.formData)
      .then((msg) => {
        hide();
        closeForm();
        message.success(msg);
      })
      .catch((e) => {
        hide();
        message.error(e.message);
      });
  }
};//保存按钮
const submitForm1 = () => {
  props.formData.agreementStatus=1;
  console.log("提交表单数据：" + JSON.stringify(props.formData));
  const hide = messageLoading('提交中...', 0);
  (props.formData.agreementId ? updateBgmAgreementPro : addBgmAgreementPro)(props.formData)
    .then((msg) => {
      hide();
      closeForm();
      message.success(msg);
    })
    .catch((e) => {
      hide();
      message.error(e.message);
    });
};//提交按钮
const submitForm2 = () => {
  props.formData.agreementStatus=2;
  console.log("提交表单数据：" + JSON.stringify(props.formData));
  const hide = messageLoading('提交中...', 0);
  (props.formData.agreementId ? updateBgmAgreementPro : addBgmAgreementPro)(props.formData)
    .then((msg) => {
      hide();
      closeForm();
      message.success(msg);
    })
    .catch((e) => {
      hide();
      message.error(e.message);
    });
};//提交按钮
//取消提交表单数据
const closeForm = () => {
  //console.log("props.formData",props.formData);
  emit('onClose');
};


const init = async () => {
  if (props.formData.agreementVendorId) {
    const data = await selectBgmVendorPro({
      vendorId: props.formData.agreementVendorId,
    });
    vendorRow.value = data;
    console.log("vendorRow.value",vendorRow.value)
    chooseRowYes();
  }

}//初始化判断（如果为修改直接获得agreementVendorId，并从后端得值）
</script>
<style scoped>
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 0px;
}

.formClass {
  height: 520px;
  background: white;
  padding: 5px;
}

.title-container {
  display: flex;
  align-items: center;
  width: 100%;
  height: 35px;

}
</style>
