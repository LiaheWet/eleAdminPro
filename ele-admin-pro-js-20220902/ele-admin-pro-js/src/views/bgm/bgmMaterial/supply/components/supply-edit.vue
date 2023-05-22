<template>
  <div>
    <el-dialog
      draggable="true"
      @open="selectedMaterialSpecs"
      :before-close="closeForm"
      v-model="showDialog"
      title="编辑供应原材料">
    <el-form :model="props.formData" label-width="100px" ref="form">
      <el-form-item label="平台原材料">
        <el-select v-model="props.formData.smTypeId" @change="selectedMaterialSpecs" placeholder="请选择" clearable>
          <el-option v-for="item in materialData" :key="item.materialId" :label="item.materialName" :value="item.materialId" />
        </el-select>
      </el-form-item>
      <el-form-item label="供应原材料">
        <el-input v-model="props.formData.smName" />
      </el-form-item>
      <el-form-item label="规格型号">
        <el-input v-model="props.formData.smSpecs" readonly />
      </el-form-item>
      <el-form-item label="状态">
        <el-radio-group v-model="props.formData.smStatus">
          <el-radio :label="1">启用</el-radio>
          <el-radio :label="0">停用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="props.formData.smRemark" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="closeForm">取消</el-button>
      </el-form-item>
    </el-form>
    </el-dialog>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {listBgmMaterial} from "@/api/bgm/bgminfo/material";
import {updateBgmSupplyMaterial,addBgmSupplyMaterial} from "@/api/bgm/bgmMaterial/supply";
import {messageLoading} from "ele-admin-pro";
import {message} from "ant-design-vue/es";

const materialData = ref([]);

const emit = defineEmits(['onClose'])
const props = defineProps({
  showDialog: false,
  //onClose: Function,
  formData:{},
});
  //获取下拉选项数据
 const getMaterialData= async () => {
   try {
     const res = await listBgmMaterial();
     console.log('成功获取到材料数据：', res);
     materialData.value = res;
   } catch (error) {
     console.error(error);
   }
 };
//获取materialSpecs
 const selectedMaterialSpecs=()=> {
   const selectedMaterial = materialData.value.find(
     (m) => m.materialId === props.formData.smTypeId
   );
   const materialSpecs = selectedMaterial ? selectedMaterial.materialSpecs : "";
   const smTypeName = selectedMaterial ? selectedMaterial.materialName : "";
   props.formData.smSpecs = materialSpecs;
   props.formData.smTypeName = smTypeName;
};

//提交表单数据
const submitForm = () => {
  console.log("提交表单数据："+JSON.stringify(props.formData));
  console.log("supplyMaterialId："+props.formData.supplyMaterialId);
  const hide = messageLoading('提交中...', 0);
    (props.formData.supplyMaterialId ?updateBgmSupplyMaterial : addBgmSupplyMaterial)(props.formData)
      .then((msg) => {
        hide();
        closeForm();
        message.success(msg);
      })
      .catch((e) => {
        hide();
        message.error(e.message);
      });

};
const closeForm=()=>{
  emit('onClose')
};

onMounted(() => {

  //获取材料数据
  getMaterialData();

});


</script>

<style scoped>
.form-wrapper {
  margin: 20px;
  position: relative;
}

.form-item {
  margin-bottom: 20px;
}

.form-item.el-form-item--mini {
  margin-bottom: 10px;
}

.form-item .el-input {
  width: 100%;
}

.el-form-item--radio .el-radio-group {
  margin-top: 5px;
}

.form-item:nth-child(odd) {
  display: inline-block;
  width: calc(50% - 10px);
  margin-right: 20px;
}

.form-item:nth-child(even) {
  display: inline-block;
  width: calc(50% - 10px);
  margin-left: 20px;
}

.button-group {
  position: absolute;
  bottom: 20px;
  right: 20px;
}
</style>
