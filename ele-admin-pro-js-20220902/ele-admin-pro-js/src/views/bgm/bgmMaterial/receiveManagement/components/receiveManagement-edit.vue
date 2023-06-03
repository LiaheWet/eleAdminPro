<template>
  <div>
    <el-dialog
      v-model="showDialog"
      :before-close="closeForm"
      :show-close="false"
      :title="title"
      draggable="true"
      style="width: 1000px;  height: 515px;margin-left: 300px;margin-top:160px;background: #7ab2e0;"
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
               :rules="rules"
               :model="props.formData"
               class="formClass"
               label-width="100px">
        <el-row style="border-bottom: 1px solid #c9bcbc;">
          <el-col :span="4" style="display: flex; align-items: center;">
            <el-icon size="large">
              <Document/>
            </el-icon>
            <label style="color: #00a3ff;font-size: 15px; margin-left: 10px;">收料管理</label>
          </el-col>
          <el-col :span="8">
            <span style="display: flex; align-items: center;">
              收料编号：<div style="color: #d5d2d2">{{ "自动生成" }}</div>
             </span>
          </el-col>
          <el-col :span="6">
            <div class="serial-number-value">收料方式：{{ "手动收料" }}</div>
          </el-col>
          <el-col :span="6">
            <div class="serial-number-value" style="display:flex; align-items: center;margin-bottom: 3px">状态：
              <div style="display: flex; justify-content: center; align-items: center;" :class="{
                            'selected0': formData.managementStatus === 0,
                            'selected1': formData.managementStatus === 1,
                            'selected2': formData.managementStatus === 2,
                            'selected3': formData.managementStatus === 3,
                          }">{{getStatus(formData.managementStatus)}}</div>
            </div>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;margin-top: 5px;height:48px">
          <el-col :span="8">
            <el-form-item label="供货协议:" prop="agreementNumber">
              <el-input v-model="formData.agreementNumber" placeholder="请选择供货协议" readonly
                        style="width: 300px" @click="showAgreementDialog">
                <i class="el-select__caret el-input__icon el-icon-arrow-down"></i>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="供应原材料:" prop="supplyName">
              <el-select v-model="formData.supplyName"
                         clearable
                         placeholder="请选择供应材料"
                         style="width: 550px;" @change="handleAgreementAmIdChange">
                <el-option
                  v-for="agreementMaterial in agreementMaterialList"
                  :key="agreementMaterial.amId"
                  :label="agreementMaterial.supplyName"
                  :value="agreementMaterial.supplyName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;margin-top: 0px;height: 48px">
          <el-col :span="8">
            <el-form-item label="平台原材料:">
              <el-input v-model="formData.materialName" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="规格型号:">
              <el-input v-model="formData.materialspecs" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="供应商:">
              <el-input v-model="formData.agreementVendorName" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;margin-top: 0px;height: 48px">
          <el-col :span="8">
            <el-form-item label="过毛时间:" prop="fleeceTime">
              <el-date-picker
                v-model="formData.fleeceTime"
                placeholder="选择过毛时间"
                type="datetime"
                value-format="YYYY-MM-DD HH:mm:ss"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8" style="height: 50px;">
            <el-form-item label="毛重( t ):" prop="grossWeight">
              <el-input v-model="formData.grossWeight"
                        type="number"
                        @blur="handleTotalPriceBlur"
                        :formatter="(value) => {return /^\d+(?:\.\d{1,2})?$/.test(value) ? value : ''}" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="车辆:" prop="transportVehicle">
              <el-input v-model="formData.transportVehicle"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;margin-top: 0px;height: 48px">
          <el-col :span="8">
            <el-form-item label="过皮时间:" prop="peelingTime">
              <el-date-picker
                v-model="formData.peelingTime"
                placeholder="选择过皮时间"
                type="datetime"
                value-format="YYYY-MM-DD HH:mm:ss"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="皮重( t ):" prop="tareWeight">
              <el-input v-model="formData.tareWeight"
                        type="number"
                        @blur="handleTotalPriceBlur"
                        :formatter="(value) => {return /^\d+(?:\.\d{1,2})?$/.test(value) ? value : ''}"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="司机:" prop="driverName">
              <el-input v-model="formData.driverName"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;margin-top: 0px;height: 48px">
          <el-col :span="8">
            <el-form-item label="供货数量:" prop="supplyNumber">
              <el-input v-model="formData.supplyNumber"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="净重( t ):">
              <el-input v-model="formData.netWeight" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="明扣( t ):" prop="buckleWeight">
              <el-input v-model="formData.buckleWeight"
                        type="number"
                        @blur="handleTotalPriceBlur"
                        :formatter="(value) => {return /^\d+(?:\.\d{1,2})?$/.test(value) ? value : ''}"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;margin-top: 0px;height: 48px">
          <el-col :span="8">
            <el-form-item label="储料仓:" prop="storageBin">
              <el-input v-model="formData.storageBin"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="实收重量( t ):" >
              <el-input v-model="formData.collectedWeight" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="入库重量( t ):" >
              <el-input v-model="formData.warehousingWeight" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;height: 35px;">
          <el-col :span="24">
            <el-form-item label="备注:">
              <el-input v-model="formData.remark"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="background: #ffffff;height: 40px;border-bottom: 1px solid #c9bcbc;">
          <el-col :span="12">
            <el-form-item label="异常原因:">
              <el-input v-model="formData.cancellation"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="过毛人员:" prop="overHairedPersonnel">
              <el-select v-model="formData.overHairedPersonnel" placeholder="请选择采购人">
                <el-option
                  v-for="purchaser in purchasers"
                  :key="purchaser.id"
                  :label="purchaser.purchaserName"
                  :value="purchaser.purchaserName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="过皮人员:" prop="overSkinnedPersonnel">
              <el-select v-model="formData.overSkinnedPersonnel" placeholder="请选择采购人">
                <el-option
                  v-for="purchaser in purchasers"
                  :key="purchaser.id"
                  :label="purchaser.purchaserName"
                  :value="purchaser.purchaserName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item style="float: right;margin-top: 5px">
          <el-button type="primary" v-if="title=='新增'||title=='修改'" @click="submitForm">
            <el-icon><CircleCheck /></el-icon>保存
          </el-button>
          <el-button v-if="title=='新增'||title=='异常处理'" type="success" @click="submitForm1">
            <el-icon><Finished /></el-icon>提交
          </el-button>
          <el-button @click="closeForm">
            <el-icon>
              <CircleClose/>
            </el-icon>
            关闭
          </el-button>
        </el-form-item>
      </el-form>

      <!--      选择来往单位对话框-->
      <el-dialog v-model="agreementDialogVisible" :center="true" draggable="true"
                 style="width: 900px;height: 500px;margin-top: 200px;margin-left: 360px" title="选择采购协议">
        <el-header style="height: 30px">
          <el-row>
            <el-col :span="6">
              <el-form-item label="采购协议:">
                <el-input v-model="selectAgreementData.agreementNumber"/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label=" 往来单位:">
                <el-input v-model="selectAgreementData.agreementVendorName"/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label=" 采购员:">
                <el-input v-model="selectAgreementData.agreementPurchaser"/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-button class="add-button" style="color: #8eb97e;margin-left: 10px" @click="getAgreementData">查询
              </el-button>
              <el-button class="modify-button" style="color: #00a3ff" @click="reset">重置</el-button>
            </el-col>
          </el-row>
        </el-header>
        <el-main>
          <el-table :data="agreementData"
                    :default-sort="{ prop: 'agreementCreateTime', order: 'descending' }"
                    border
                    height="300px"
                    stripe
                    @current-change="chooseRow">
            <el-table-column type="index" width="30px"></el-table-column>
            <el-table-column label="协议编号" prop="agreementNumber" width="120px"></el-table-column>
            <el-table-column label="制定时间" prop="agreementCreateTime" sortable width="160px"></el-table-column>
            <el-table-column label="往来单位" prop="agreementVendorName" width="120px"></el-table-column>
            <el-table-column label="联系人" prop="agreementContact" width="120px"></el-table-column>
            <el-table-column label="联系电话" prop="agreementPhone" width="120px"></el-table-column>
            <el-table-column label="采购时间" prop="agreementBuyTime" width="160px"></el-table-column>
            <el-table-column label="采购员" prop="agreementPurchaser" width="120px"></el-table-column>
            <el-table-column label="备注" prop="agreementRemark"></el-table-column>
          </el-table>
        </el-main>
        <el-footer style="position: absolute; bottom: 0; width: 100%;">
          <el-row>
            <el-col :span="18">
              <!--          分页-->
              <el-pagination
                v-model:current-page="currentPageAgreement"
                v-model:page-size="pageSizeAgreement"
                :page-sizes="[10, 20, 30, 40]"
                :total="totalAgreement"
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
  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {message} from "ant-design-vue/es";
import * as Vue from "lodash-es";
import {messageLoading} from "ele-admin-pro/es";

import {
  pageBgmAgreement, listBgmAgreementMaterial
} from "@/api/bgm/bgmMaterial/agreement";
import {addBgmReceiveManagement, updateBgmReceiveManagement} from "@/api/bgm/bgmMaterial/receiveManagement";

const emit = defineEmits(['onClose'])
const props = defineProps({
  showDialog: false,
  formData: {},
  purchasers: [],
  title: ''
});

//-----数据定义------
const agreementData = ref([]);//采购协议列表
const totalAgreement = ref(0);
const pageSizeAgreement = ref(10);
const currentPageAgreement = ref(1);
const ruleFormRef = ref(null);

let agreementDialogVisible = ref(false);//控制vendor对话框
const selectAgreementData = reactive({
  agreementNumber: '',
  agreementVendorName: '',
  agreementPurchaser: '',
})//话框的搜索框
const agreementRow = ref([]);//选择的采购协议（暂存数据）
let agreementMaterialList = ref([]);//选择的供应商的供应材料(可能为一部分)
//---------函数=---------

const showAgreementDialog = () => {
  agreementDialogVisible.value = true;
  getAgreementData();
};//控制agreement对话框
const getAgreementData = async () => {
  const res = await pageBgmAgreement({
    page: currentPageAgreement.value,
    limit: pageSizeAgreement.value,
    agreementNumber: selectAgreementData.agreementNumber,
    agreementVendorName: selectAgreementData.agreementVendorName,
    agreementPurchaser: selectAgreementData.agreementPurchaser,
  });
  agreementData.value = res.list;
  totalAgreement.value = res.count;
  console.log("getAgreementData", res);
};//获取Agreement列表
const reset = () => {
  selectAgreementData.agreementNumber = '';
  selectAgreementData.agreementVendorName = '';
  selectAgreementData.agreementPurchaser = '';
}//重置对话框的搜索框
const handleSizeChange = async (val) => {
  pageSizeAgreement.value = val;
  await getAgreementData();
};//分页显示
const handleCurrentChange = async (val) => {
  currentPageAgreement.value = val;
  await getAgreementData();
};//分页显示

const chooseRow = async (val) => {
  if (val != null) {
    const data = await pageBgmAgreement({
      agreementId: val.agreementId,
    });
    agreementRow.value = data.list[0];
    props.formData.agreementNumber = agreementRow.value.agreementNumber;
    props.formData.agreementVendorName = agreementRow.value.agreementVendorName;
    props.formData.vendorId = agreementRow.value.agreementVendorId;
  }
  //console.log("agreementRow.value", JSON.stringify(agreementRow.value));

};//在agreement列表选择 供应协议 的行，获取数据  console.log(agreementRow)
const chooseRowCancel = () => {
  agreementRow.value = null;
  agreementDialogVisible.value = false;
};//取消选择行chooseRowCancel，清理数据
const chooseRowYes = async () => {
  const res = await listBgmAgreementMaterial({
    amNumber: props.formData.agreementNumber,
  });
  //console.log("res", res);
  agreementMaterialList.value = res;
  console.log("agreementMaterialList", agreementMaterialList.value);
  agreementDialogVisible.value = false;
};//确定选择chooseRowYes，并传递数据（agreementVendorName，agreementVendorId，bgmVendorContactList）console.log(bgmVendorContactList);
const handleAgreementAmIdChange = async () => {
  const res = await listBgmAgreementMaterial({
    amNumber: props.formData.agreementNumber,
    supplyName:props.formData.supplyName,
  });
  props.formData.materialName = res[0].materialName;
  props.formData.materialspecs = res[0].specs;
  props.formData.smId = res[0].smId;

};//进一步根据supplyName查询数据并赋值
const handleTotalPriceBlur = () => {
  const grossWeight = parseFloat(props.formData.grossWeight);
  const tareWeight = parseFloat(props.formData.tareWeight);
  const buckleWeight=parseFloat(props.formData.buckleWeight);
  const totalPrice = isNaN(grossWeight) || isNaN(tareWeight) ? '' : (grossWeight - tareWeight - buckleWeight).toFixed(2);
  props.formData.collectedWeight= props.formData.netWeight=props.formData.warehousingWeight= totalPrice;
  props.formData.claspWeight=buckleWeight;
}
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
//--------数据交互----
//提交表单数据
const rules = reactive({
  agreementNumber: [
    {
      required: true,
      message: '请选择供货协议',
      trigger: 'blur',
    },
  ],
  supplyName: [
    {
      required: true,
      message: '请选择供应原材料',
      trigger: 'blur',
    },
  ],
  fleeceTime: [
    {
      required: true,
      message: '请选择过毛时间',
      trigger: 'blur',
    },
  ],
  grossWeight: [
    {
      required: true,
      message: '请输入毛重',
      trigger: 'blur',
    },
  ],
  transportVehicle: [
    {
      required: true,
      message: '请输入车牌号',
      trigger: 'blur',
    },
  ],
  peelingTime: [
    {
      required: true,
      message: '请选择过皮时间',
      trigger: 'blur',
    },
  ],
  tareWeight: [
    {
      required: true,
      message: '请输入皮重',
      trigger: 'blur',
    },
  ],
  driverName: [
    {
      required: true,
      message: '请输入司机名字',
      trigger: 'blur',
    },
  ],
  supplyNumber: [
    {
      required: true,
      message: '请输入供应数量',
      trigger: 'blur',
    },
  ],
  storageBin: [
    {
      required: true,
      message: '请输入储料仓',
      trigger: 'blur',
    },
  ],
  buckleWeight: [
    {
      required: true,
      message: '请输入明扣',
      trigger: 'blur',
    },
  ],
  overHairedPersonnel: [
    {
      required: true,
      message: '请选择过毛人员',
      trigger: 'blur',
    },
  ],
  overSkinnedPersonnel: [
    {
      required: true,
      message: '请选择过皮人员',
      trigger: 'blur',
    },
  ],
});
const submitForm = async () => {
  const formEl = ruleFormRef.value;
  const valid = await formEl.validate();
  console.log("提交表单数据：" + JSON.stringify(props.formData));
  if(valid) {
    const hide = messageLoading('提交中...', 0);
    (props.formData.id ? updateBgmReceiveManagement : addBgmReceiveManagement)(props.formData)
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
};//保存数据，状态为制单（0）
const submitForm1 = () => {
  if(props.title=="异常处理"){
    props.formData.managementStatus=2;
  }else {
    props.formData.managementStatus=3;
  }
  console.log("提交表单数据：" + JSON.stringify(props.formData));
  const hide = messageLoading('提交中...', 0);
  (props.formData.id ? updateBgmReceiveManagement : addBgmReceiveManagement)(props.formData)
    .then((msg) => {
      hide();
      closeForm();
      message.success(msg);
    })
    .catch((e) => {
      hide();
      message.error(e.message);
    });
};//提交数据，状态为已完成（3）
//取消提交表单数据
const closeForm = () => {
  //console.log("props.formData",props.formData);
  emit('onClose');
};
const init = () => {
  if (props.formData.agreementNumber) {
    chooseRowYes();
    props.formData.supplyName = props.formData.smName;
    props.formData.agreementVendorName = props.formData.vendorName;
    props.formData.materialName = props.formData.smTypeName;
    props.formData.materialspecs = props.formData.smSpecs;
  }
}
</script>
<style scoped>
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 0px;
}

.formClass {
  height: 440px;
  background: white;
  padding: 5px;
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

</style>
