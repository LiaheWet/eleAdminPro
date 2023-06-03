<script xmlns:el-col="http://www.w3.org/1999/html">
import {computed, ref, onMounted, reactive,} from 'vue';
import { message } from "ant-design-vue/es";
import {
  findAllVendorService, findVendorAccount, findVendorContact, findVendorMaterial, findMaterial, removeVendor,
  removeVendorAccountList, removeVendorContactList, removeVendorMaterialList,
  saveOrUpdateAccount, saveOrUpdateContact, saveOrUpdateVendor, saveVendorMaterial
} from '@/api/bgm/bgmMaterial/vendor';



export default {
  name: "index",
  setup() {

    const vendorList = ref([]);

    const search = ref('');

    onMounted(() => {
      findAllVendor();
       findMaterialList();
    });

    // 弹出框数据
    let ruleForm=reactive({
      //供应商基本信息
      dialogData: {},
      //银行账户表格列表属性
      accountTableColumns : [
        // {prop: 'vendorId',label: '供应商ID',type: 'input',visible: false},
        { prop: 'accountName', label: '账户名称', type: 'input' },
        { prop: 'accountNumber', label: '账号', type: 'input' },
        { prop: 'accountBank', label: '开户银行', type: 'input' },
      ],
      //银行账户列表
      accountList:[],
      //银行账户删除列表
      accountDeleteList:[],
      //联系人表格列表属性
      contactTableColumns : [
        // {prop: 'vendorId',label: '供应商ID',type: 'input',visible: false},
        { prop: 'contactName', label: '联系人姓名', type: 'input' },
        { prop: 'contactPost', label: '职务', type: 'input' },
        { prop: 'contactPhone', label: '电话', type: 'input' },
      ],
      //联系人列表
      contactList:[],
      //联系人删除列表
      contactDeleteList:[],
      //供应商原材料列表
      vendorMaterial:[],
      //供应商原材料删除列表
      vendorMaterialDeleteList:[]

    })

    function addVendor(){
      dialogTitle.value="新增"
      ruleForm.dialogData={}
      ruleForm.contactList=[]
      ruleForm.accountList=[]
      ruleForm.vendorMaterial=[]
      outerVisible.value=true
    }


    //分页查询
    function findAllVendor (currentPage , pageSize)  {

      findAllVendorService(currentPage, pageSize)
        .then((data) => {
          vendorList.value = data.list;
          totalRecords.value = data.count;
          console.log("shua",vendorList.value);
        })
        .catch(error => {
          message.error('分页查询异常')
          console.log('分页查询异常')
        });
    };


    // 供应商标签过滤
    const filterTag = (value, row) => {
      return row.vendorStatus === value;
    };

    //原材料标签过滤
    const filterMaterialTag = (value, row) => {
      return row.smStatus === value;
    };

    //修改弹窗
    const outerVisible = ref(false);
    let innerVisible = ref(false);

    //分页
    let currentPage = ref(1);
    let pageSize = ref(10);

    //改变每页行数
    function handleSizeChange(val) {
      pageSize.value = val;
      findAllVendor(currentPage.value, pageSize.value);
    }

    //重新分页
    function handleCurrentChange(val) {
      currentPage.value = val;
      findAllVendor(currentPage.value, pageSize.value);
    }

    //数据总行数
    let totalRecords = ref(1);

    //分页和搜索同时生效
    const filteredTableData = computed(() => {
      const filterList= vendorList.value?.filter((data) =>
        !search.value || data.vendorName.includes(search.value)||data.buyer.includes(search.value)
      );
      totalRecords.value=filterList.length;
      return filterList.slice(pageSize.value * (currentPage.value - 1), pageSize.value * currentPage.value);

    });

    // 当前数据行
    let currentRow = ref()
    //点击数据行
    const handleVendorCurrentChange = (val) => {
      currentRow.value = val
    }



    //启用供应商
    function Enable(currentRow){
      currentRow.vendorStatus=1
      saveOrUpdateVendor(currentRow).then(  () => {
        message.success("已启用");
        // findAllVendor()
        // outerVisible.value=false
      })
        .catch(error => {
          message.error('保存供应商基础信息异常')
          console.log('保存供应商基础信息异常')
        });
    }

    //停用供应商
    function Deactivate(currentRow){
      currentRow.vendorStatus=0
      saveOrUpdateVendor(currentRow).then(  () => {
        message.success("已停用");
      })
        .catch(error => {
          message.error('保存供应商基础信息异常')
          console.log('保存供应商基础信息异常')
        });
    }

    //删除供应商
    const handleDelete = ( row) => {
      if(typeof(row) !== 'undefined'){
        removeVendor(row.vendorId).then(
          (msg) => {
            message.success(msg);
            findAllVendor()
            outerVisible.value=false
          })
          .catch(error => {
            message.error('删除异常')

          });
      }
      else alert("请选择要删除的数据")
    };

    //基础信息表单

    //编辑表单
    const handleEdit = ( row) => {
      if(typeof(row) !== 'undefined'){
        dialogTitle.value="修改"

        //数据置空
        ruleForm.accountDeleteList=[]
        ruleForm.contactDeleteList=[]
        ruleForm.vendorMaterialDeleteList=[]
        // selectedIds=[]

        // 执行操作，例如：编辑行数据
        ruleForm.dialogData=row
        outerVisible.value=true;

        // 打开弹出框时查找该供应商的银行账户
        findVendorAccount(ruleForm.dialogData.vendorId).then(
          (data) => {
            ruleForm.accountList=data
            console.log(' ruleForm.accountList', ruleForm.accountList)
          })
          .catch(error => {
            message.error('查找银行账户异常')
          })

        // 打开弹出框时查找该供应商的联系人
        findVendorContact(ruleForm.dialogData.vendorId).then(
          (data) => {
            ruleForm.contactList=data
          })
          .catch(error => {
            message.error('查找该供应商的联系人异常')
          });

        // 打开弹出框时查找该供应商的原材料
        findVendorMaterial(ruleForm.dialogData.vendorId).then(
          (data) => {
            ruleForm.vendorMaterial=data
            console.log("vendorMaterial",ruleForm.vendorMaterial)
          })
          .catch(error => {
            message.error('打开弹出框时查找该供应商的原材料异常')
          });
      }
      else alert("请选择操作的数据")

    };


    //表单校验
    const rules = reactive({
      vendorName: [
        { required: true, message: '请输入供应商名称', trigger: 'blur' },
        // { min: 1, message: '名称不为空', trigger: 'blur' },
      ],
      vendorRepresent: [
        { required: true, message: '请输入法人代表姓名', trigger: 'blur' },
        { min: 2, max: 5, message: '名字长度在2-5', trigger: 'blur' },
      ],
      vendorPhone: [
        { required: true, message: '请输入联系电话', trigger: 'blur' },
        { pattern: /^'1'[3-9]\d{9}$/, message: '格式错误', trigger: 'blur' },
      ],
      vendorAddress: [
        { required: true, message: '请输入地址', trigger: 'blur' },
        { min: 1,  message: '地址不能为空', trigger: 'blur' },
      ],
      vendorEmail:[
        {required: true , message:'请输入qq邮箱',trigger:'blur'},
        {message: '格式错误!请输入9位或10位数字@qq.com' , pattern: /^[1-9]\d{8,9}@qq\.com$/,trigger: 'blur'}
        // {validator : validateEmail ,trigger:'blur'}
      ],
      vendorFax:[
        {required: true , message:'请输入传真号',trigger:'blur'},
        {message: '格式错误！例：+86或010-12345678' , pattern: /^('+'86\s)?\d{3,4}-\d{7,8}$/,trigger: 'blur'}
      ],
      vendorPostal:[
        {required: false ,trigger:'blur'},
        {message: '格式错误！请输入六位数' , pattern: /^\d{6}$/,trigger: 'blur'}
      ],
      taxId:[
        {required: false,trigger:'blur'},
        {message: '格式错误！请输入15或18位数' , pattern: / ^\d{15}|\d{18}$/,trigger: 'blur'}
      ],
    })


    //添加银行账户
    const addAccountRow = () => {
      ruleForm.accountList.push({vendorId:ruleForm.dialogData.vendorId});

    };

    //删除银行账户
    const accountDelete = (row) => {
      const index = ruleForm.accountList.indexOf(row);
      if (index >= 0) {
        ruleForm.accountList.splice(index, 1);
      }
      if(typeof (row.id)!=='undefined'){
        ruleForm.accountDeleteList.push(row.id)
      }



    };

    //添加联系人
    const addContactRow = () => {
      ruleForm.contactList.push({vendorId:ruleForm.dialogData.vendorId});
    };

    //删除联系人
    const contactDelete = (row) => {
      const index = ruleForm.contactList.indexOf(row);
      if (index >= 0) {
        ruleForm.contactList.splice(index, 1);
      }
      if(typeof (row.id)!=='undefined'){
        ruleForm.contactDeleteList.push(row.id)
      }



    };

    const saveDialog=(ruleForm)=>{
      //保存供应商基本信息
      if (ruleForm.dialogData !==null){
        saveOrUpdateVendor(ruleForm.dialogData).then(  (msg) => {
          message.success("操作成功");
          findAllVendor()
          outerVisible.value=false
        })
          .catch(error => {
            message.error('保存供应商基础信息异常')
            console.log('保存供应商基础信息异常')
          });
      }

      //保存联系人
      if (ruleForm.contactList.length !==0){
        saveOrUpdateContact(ruleForm.contactList).then( )
          .catch(error => {
            message.error('保存联系人异常')
            console.log('保存联系人异常')
          });
      }
      // 保存银行账户
      if (ruleForm.accountList.length!==0&&ruleForm.accountList){
        saveOrUpdateAccount(ruleForm.accountList).then()
          .catch(error => {
            message.error('保存银行账户异常')
            console.log('保存银行账户异常')
          });
      }

      //批量删除银行账户
      if(ruleForm.accountDeleteList.length!==0){
        console.log("删除的数据", ruleForm.accountDeleteList)
        removeVendorAccountList(ruleForm.accountDeleteList).then()
          .catch(error => {
          message.error('删除银行账户异常')
          console.log('删除银行账户异常')
        });

      }
      //批量删除联系人
      if(ruleForm.contactDeleteList.length!==0){
        console.log("删除的数据", ruleForm.contactDeleteList)
        removeVendorContactList(ruleForm.contactDeleteList).then((msg)=>{})
          .catch(error => {
            message.error('联系人异常')
            console.log('删除银行账户异常')
          });

      }

      //批量删除供应商原材料
      if(ruleForm.vendorMaterialDeleteList.length!==0){
        console.log("删除的数据", ruleForm.vendorMaterialDeleteList)
        removeVendorMaterialList(ruleForm.vendorMaterialDeleteList).then((msg)=>{})
          .catch(error => {
            message.error('供应商原材料异常')
            console.log('删除供应商原材料异常')
          });
      }
    }

    //原材料查询
    function findMaterialList() {
      findMaterial().then((data)=>{
        materialList.value=data;
        console.log("materialList",materialList.value)
      })
        .catch(error => {
          console.log(materialList.value)
          message.error('原材料查询异常')
          console.log('原材料查询异常')
        });

    }
    //原材料列表
    let materialList=ref([])

    let selectedIds=ref([])


    function handleSelectionChange(selection) {
      // 获取选中的行的原材料名称，并保存到selectedIds数组中
      selectedIds.value = selection.map(record => record.supplyMaterialId);

      console.log('###选中selectedIds.value',selectedIds.value)
      console.log('selectedIds.length',selectedIds.value.length)


    }

    //供应商批量添加原材料
    const handleBatchOperate=()=> {

      if(selectedIds.length===0)
        alert('未选择数据')
      else{
        console.log('选择选择原材料',selectedIds)
        const selectedRows = materialList.value.filter(item => selectedIds.value.includes(item.supplyMaterialId))

        // 在每个选中的行数据中增加 vendorId的属性
        for (let i = 0; i < selectedRows.length; i++) {
          selectedRows[i].vendorId = ruleForm.dialogData.vendorId
          selectedRows[i].supplyMaterialId=selectedRows[i].supplyMaterialId
        }
        console.log("selectedRows",selectedRows)
        saveVendorMaterial(selectedRows).then(
          (msg)=>{
            message.success(msg)
            findVendorMaterial(ruleForm.dialogData.vendorId).then(
              (data) => {
                ruleForm.vendorMaterial=data
              })
            innerVisible.value=false
        })
      }

      materialTable.value.clearSelection()
      innerVisible.value=false
      console.log('保存后selectedIds.value',selectedIds.value)

    }

    function closeMaterialTable(){
      materialTable.value.clearSelection()
      selectedIds.value=[]
      innerVisible.value=false
    }




    // 删除原材料
    function deleteVendorMaterial(row){
      // removeVendorMaterial(row.id).then(
      //   (msg) => {
      //     message.success(msg);
      //     findVendorMaterial(ruleForm.dialogData.vendorId).then(
      //       (data) => {
      //         ruleForm.vendorMaterial=data
      //
      //       })
      //
      //   })
      //   .catch(error => {
      //     message.error('删除供应商原材料异常!')
      //     console.log('删除供应商原材料异常')
      //   });

      const index = ruleForm.vendorMaterial.indexOf(row);
      if (index >= 0) {
        ruleForm.vendorMaterial.splice(index, 1);
      }
      ruleForm.vendorMaterialDeleteList.push(row.id)

    }


    // 下面基本是用于实现前端布局的代码
    // 下面基本是用于实现前端布局的代码

    //引用原材料添加表格
    const materialTable = ref()

    function openInner() {
      innerVisible.value=true
      selectedIds.value=[]

    }

    //外层对话框标题
    const dialogTitle=ref();
    // 存储初始位置的坐标信息
    const startCoord = ref();

    function handleDragStart(e) {
      const dialog = e.currentTarget;
      startCoord.value = {
        left: parseInt(dialog.style.left || '0'),
        top: parseInt(dialog.style.top || '0'),
        x: e.clientX,
        y: e.clientY
      };
    }

    function handleDrag(e) {
      const dialog = e.currentTarget;
      if (!startCoord.value) return;

      const offsetX = e.clientX - startCoord.value.x;
      const offsetY = e.clientY - startCoord.value.y;
      const left = startCoord.value.left + offsetX;
      const top = startCoord.value.top + offsetY;
      const x = `${left}px`;
      const y = `${top}px`;

      dialog.style.cssText += `;left:${x};top:${y}`;
      e.preventDefault();
    }



    return {

      currentRow,
      handleVendorCurrentChange,


      vendorList,
      search,
      Enable,
      Deactivate,


      handleEdit,
      handleDelete,
      filterTag,

      outerVisible,
      innerVisible,

      dialogTitle,
      startCoord,
      handleDragStart,
      handleDrag,


      // 分页
      totalRecords,
      currentPage,
      pageSize,
      handleSizeChange,
      handleCurrentChange,
      filteredTableData,

      // 对话框表单

      ruleForm,
      rules,


      accountDelete,
      addAccountRow,
      addContactRow,
      contactDelete,
      saveDialog,
      addVendor,

      filterMaterialTag,

      materialList,
      selectedIds,

      materialTable,
      openInner,
      handleSelectionChange,//原材料多选
      closeMaterialTable,//关闭时取消选中
      handleBatchOperate, //批量添加原材料
      deleteVendorMaterial,// 删除供应商原材料

    };
  }
};
</script>

<template>
  <div >
    <div style="display: flex ">
      <el-icon style="margin-top: 5px;margin-right: -14px;margin-left: 14px"><List /></el-icon>
      <p style="font-weight:bold;color:#87ceeb" >原材料供应商</p>
    </div>



    <div class="button-wrapper" >
      <el-input style="margin-right: 510px" label="搜索"  v-model="search" size="small" placeholder="请输入单位或采购员关键词" />
      <el-button type="primary" plain icon="Plus" size="small" @click="addVendor">新建</el-button>
      <el-button type="danger" plain icon="Delete" size="small" :disabled="isDisabled" @click="handleDelete(currentRow)">删除</el-button>
      <el-button type="warning" plain icon="Edit"  size="small" :disabled="isDisabled" @click="handleEdit(currentRow)">编辑</el-button>
      <el-button type="success" plain icon="Edit"  size="small" v-show="currentRow && currentRow.vendorStatus ===0 " @click="Enable(currentRow)">启用</el-button>
      <el-button type="danger" plain icon="Edit" size="small" v-show="currentRow && currentRow.vendorStatus ===1 " @click="Deactivate(currentRow)">停用</el-button>
    </div>


  <!-- 表格 -->
  <el-table :data="filteredTableData" border style="width: 100%"
            @row-dblclick="handleEdit"
            @current-change="handleVendorCurrentChange"
            highlight-current-row
            >

    <el-table-column label="序号" type="index" width="80" prop="filteredTableData.$index" header-align="center" />
    <el-table-column label="单位名称" prop="vendorName" header-align="center" />
    <el-table-column label="联系电话" prop="vendorPhone" header-align="center" />
    <el-table-column label="联系地址" prop="vendorAddress" header-align="center" />
    <el-table-column label="采购员" width="80px" prop="buyer" header-align="center"/>

    <el-table-column
      prop="vendorStatus"
      label="状态"
      header-align="center"
      width="70px"
      :filters="[
        { text: '启用', value: 1},
        { text: '停用', value: 0},
      ]"
      :filter-method="filterTag"
      filter-placement="bottom-end"
    >
      <template #default="scope">
        <el-tag
          :type="scope.row.vendorStatus === 1 ? '' : 'success'"
          disable-transitions
        >{{ scope.row.status ===1?'启用':'停用'}}</el-tag
        >
      </template>
    </el-table-column>

    <el-table-column label="备注" prop="remark" header-align="center"/>

<!--    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="请输入单位关键词" />
      </template>
      <template #default="scope">
        <el-button
          size="small"
          @click="handleEdit(scope.$index, scope.row)"
        >编辑</el-button>
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)"
        >删除</el-button>
      </template>

    </el-table-column>-->

  </el-table>


  <!-- 修改弹窗 -->
<!--    style="width: 900px"-->
  <el-dialog v-model="outerVisible"
             :footer="false"
             draggable
             @dragstart="handleDragStart"
             @drag="handleDrag"
             :style="{ 'max-height': maxHeight }"
          >

    <template #header="{ close, titleId, titleClass }">
      <div class="my-header">
        <h4 :id="titleId" :class="titleClass">{{dialogTitle}}</h4>
      </div>
    </template>

    <h3 style="margin-top: -35px;margin-bottom: 5px;font-weight:bold;color:#87ceeb">原材料供应商</h3>

    <el-tabs type="border-card" >
      <el-tab-pane >
        <template #label>
        <span class="custom-tabs-label">
          <el-icon><Calendar /></el-icon>
          <span>基本信息</span>
        </span>
        </template>

<!--        基本信息表单-->
         <el-form

           :model="ruleForm.dialogData"
           :rules="rules"
           label-width="auto"
           class="demo-ruleForm"
           size="small"
           status-icon>

<!--           信息表单-->
           <div>
             <el-row>
               <el-col :span="8">
                 <el-form-item label="单位名称" prop="vendorName" size="small"  >
                   <el-input style="width: 180px"  v-model="ruleForm.dialogData.vendorName"/>
                 </el-form-item>
               </el-col>

               <el-col :span="8">
                 <el-form-item label="地址" prop="vendorAddress" size="small">
                   <el-input style="width: 180px" v-model="ruleForm.dialogData.vendorAddress" />
                 </el-form-item>
               </el-col>

               <el-col :span="8">
                 <el-form-item label="法人代表" prop="vendorRepresent" size="small">
                   <el-input style="width: 180px" v-model="ruleForm.dialogData.vendorRepresent"/>
                 </el-form-item>
               </el-col>

             </el-row>

             <el-row>

               <el-col :span="8">
                 <el-form-item label="联系电话" prop="vendorPhone" size="small" >
                   <el-input style="width: 180px" v-model="ruleForm.dialogData.vendorPhone" />
                 </el-form-item>
               </el-col>

               <el-col :span="8">
                 <el-form-item label="电子邮箱" prop="vendorEmail" size="small">
                   <el-input style="width: 180px" v-model="ruleForm.dialogData.vendorEmail" />
                 </el-form-item>
               </el-col>

               <el-col :span="8">
                 <el-form-item label="传真号" prop="vendorFax" size="small">

                   <el-input style="width: 180px" v-model="ruleForm.dialogData.vendorFax" />
                 </el-form-item>
               </el-col >

             </el-row>

             <el-row>

               <el-col :span="8">
                 <el-form-item label="邮编" prop="vendorPostal" size="small">
                   <el-input style="width: 180px" v-model="ruleForm.dialogData.vendorPostal" />
                 </el-form-item>
               </el-col>

               <el-col :span="8">
                 <el-form-item label="纳税号" prop="taxId" size="small">
                   <el-input style="width: 180px" v-model="ruleForm.dialogData.taxId" />
                 </el-form-item>
               </el-col>

               <el-col :span="8">
                 <el-form-item label="采购员" prop="buyer" size="small">
                   <el-input style="width: 180px" v-model="ruleForm.dialogData.buyer" />
                 </el-form-item>
               </el-col >

             </el-row>

             <el-row>
               <el-col :span="24">
                 <el-form-item label="备注" prop="remark" size="small">
                   <el-input style="width: 710px;" v-model="ruleForm.dialogData.remark" type="textarea" />
                 </el-form-item>
                 <br>
               </el-col>
             </el-row>

           </div>

         </el-form>

<!--        银行账户表格-->
        <hr style="margin-top: -20px">
        <div>
          <el-table :data="ruleForm.accountList" style="width: 100%" border size="small">
            <el-table-column v-for="(column, index) in ruleForm.accountTableColumns"
                             :key="index"
                             :prop="column.prop"
                             :label="column.label"
                             header-align="center">
              <template v-if="column.type === 'input'" v-slot="{row}">
                <el-input v-model="row[column.prop]" />
              </template>
            </el-table-column>
            <el-table-column label="操作">

              <template v-slot:header>
                <el-button type="primary" plain size="small" icon="Plus" @click="addAccountRow">添加</el-button>
              </template>

              <template #default="{row}">

                <el-button  type="danger" plain size="small" icon="Delete" @click="accountDelete(row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
<!--          <el-button type="primary" @click="addRow">新增行</el-button>-->
        </div>

<!--        联系人表格-->
        <hr>
        <div>
          <el-table :data="ruleForm.contactList" style="width: 100%" border size="small">
            <el-table-column v-for="(column, index) in ruleForm. contactTableColumns"
                             :key="index"
                             :prop="column.prop"
                             header-align="center"
                             :label="column.label">
              <template v-if="column.type === 'input'" v-slot="{row}">
                <el-input v-model="row[column.prop]" />
              </template>
            </el-table-column>
            <el-table-column label="操作">

              <template v-slot:header>
                <el-button type="primary" icon="Plus" size="small" plain @click="addContactRow">添加</el-button>
              </template>

              <template #default="{row}">
                <el-button type="danger" icon="Delete" size="small" plain @click="contactDelete(row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
<!--          <el-button type="primary" @click="addContactRow">新增行</el-button>-->
        </div>



      </el-tab-pane>

      <el-tab-pane label="供应原材料">
<!--        供应商原材料-->

      <el-table :data="ruleForm.vendorMaterial" style="width: 100%" border>
            <el-table-column prop="smName" label="供应原材料" header-align="center"></el-table-column>
            <el-table-column prop="smTypeName" label="平台原材料" header-align="center"></el-table-column>
            <el-table-column
              prop="smStatus"
              width="80px"
              label="状态"
              :filters="[
                { text: '启用', value: 1},
                { text: '停用', value: 0},
              ]"
              :filter-method="filterMaterialTag"
              filter-placement="bottom-end">
              <template #default="scope">
                <el-tag
                  :type="scope.row.smStatus === 1 ? '' : 'success'"
                  disable-transitions
                >{{ scope.row.smStatus ===1?'启用':'停用'}}</el-tag
                >
              </template>
            </el-table-column>
            <el-table-column label="操作" width="120px">
              <template v-slot:default="scope">
                <el-button type="danger" icon="Delete" size="small" plain @click="deleteVendorMaterial(scope.row)">删除</el-button>
              </template>
              <template v-slot:header>
                <el-button type="primary" plain size="small" icon="Plus" @click="openInner">添加</el-button>
              </template>
            </el-table-column>
          </el-table>

      </el-tab-pane>
    </el-tabs>

<!--    弹出框 基本信息保存-->
    <template v-slot:footer>
      <el-button class="saveButton"   @click="outerVisible=false">关闭</el-button>
      <el-button class="saveButton"  type="primary" plain @click="saveDialog(ruleForm)">保存</el-button>

    </template>

<!--    @close="findMaterialList"-->
    <el-dialog
      v-model="innerVisible"
      width="50%"
      title="添加原材料"
      append-to-body
      :visible.sync="innerVisible"
      :close-on-click-modal="false"
      @close="closeMaterialTable"
    >
        <!-- Inner Dialog Content -->
      <!-- 多选表格 --><!--        :row-key="row => row.supplyMaterialName"-->
      <el-table ref="materialTable"
        :data="materialList"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        border
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="supplyMaterialId" label="id" header-align="center"></el-table-column>
        <el-table-column prop="smTypeName" label="类别" header-align="center"></el-table-column>
        <el-table-column prop="smName" label="供应原材料" header-align="center"></el-table-column>
        <el-table-column prop="smTypeName" label="平台原材料" header-align="center"></el-table-column>

      </el-table>

      <!-- 批量操作按钮 -->

      <template #footer>
        <div class="dialog-footer">

          <el-button  @click="closeMaterialTable">取消</el-button>
          <el-button

            type="primary"
            plain
            size="medium"
            :disabled="!selectedIds || !Array.isArray(selectedIds) || selectedIds.length === 0"
            @click="handleBatchOperate"
          >
            保存
          </el-button>
        </div>
      </template>
    </el-dialog>




  </el-dialog>


  <!-- 分页 -->
  <div class="demo-pagination-block">
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[2, 5, 10,20]"
      :small="false"
      :disabled="false"
      :background="false"
      :total="totalRecords"
      layout="sizes, prev, pager, next"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>

  </div>
</template>

<style>

p{
  margin-left: 20px;
  margin-top: 2px;
  margin-bottom: 2px;
}

.button-wrapper {
  border: 2px solid rgba(0, 0, 0, 0.2);
  display: flex;
  justify-content: flex-end;
  /*margin-top: 10px;*/
  margin-right: 0px;
  /*margin-bottom: 10px;*/
  padding: 5px 20px 5px 5px;
}

/*.button-wrapper .el-button {*/
/*  margin-right: 10px;*/
/*}*/

.button-wrapper .el-input {
  width: 300px;
  margin-right: 30px;
}


.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}

.demo-tabs .custom-tabs-label span {
  vertical-align: middle;
  margin-left: 4px;
}

.form-item-row{
  position: relative;
  left: -60px;

}
h2{
  position: relative;
  top: -20px;
}

.saveButton{
  position: relative;
  right: 30px;
  bottom: 20px;
}


</style>
