<template>

  <div class="common-layout">
    <el-container>
      <el-header>
        <!--  标头-->
        <div class="title-container">
          <el-icon size="20px" style="margin-right: 5px">
            <ChromeFilled/>
          </el-icon>
          <h8 class="page-title">原材料供应商</h8>
          <el-divider direction="vertical"/>
          <el-button class="add-button" style="color: #8eb97e" @click="">新增</el-button>
          <el-button class="modify-button" style="color: #00a3ff" @click="">修改</el-button>
          <el-button class="delete-button" style="color: red" @click="">删除</el-button>

          <el-divider direction="vertical"/>
          <el-button class="add-button" style="color: #8eb97e" @click="">启用</el-button>
          <el-button class="modify-button" style="color: #00a3ff" @click="">关注</el-button>
        </div>
        <!--    查询部分-->
        <el-form>
          <div class="title-container1" style="display: flex; flex-direction: row; align-items: center;">
            <label>单位名称：</label>
            <el-input v-model="inputName" style="width: 200px; margin-right: 10px;"></el-input>
            <label style="margin-left: 10px">采购员：</label>
            <el-input v-model="inputName" style="width: 200px; margin-right: 10px;"></el-input>
            <el-button class="add-button" style="color: #8eb97e" @click="select">查询</el-button>
            <el-button class="modify-button" style="color: #00a3ff" @click="reset">重置</el-button>
          </div>
        </el-form>

      </el-header>
      <el-main >
        <el-table :data="tableData"
                  highlight-current-row
                  style="width: 1480px"
                  border
                  stripe
                  @current-change="CurrentChange"
                  :span-method="objectSpanMethod" >
          <el-table-column type="index"></el-table-column>
          <el-table-column label="关注" prop="focus"></el-table-column>
          <el-table-column label="单位名称" prop="vendorName"></el-table-column>
          <el-table-column label="单位类型" prop="vendorType"></el-table-column>
          <el-table-column label="联系电话" prop="vendorPhone"></el-table-column>
          <el-table-column label="联系地址" prop="vendorAddress"></el-table-column>
          <el-table-column label="采购员" prop="buyer"></el-table-column>
          <el-table-column label="状态" prop="vendorStatus" width="70px">
            <template #default="{row}">
              <el-tag
                :type="row.vendorStatus === 1 ? 'success' : 'danger'"
                :class="{'bgm-shallow-red-bg': row.vendorStatus === 0,'bgm-shallow-green-bg': row.vendorStatus === 1}">
                {{ row.vendorStatus === 1 ? '启用' : '停用' }}
              </el-tag>
            </template>
          </el-table-column >
          <el-table-column label="备注" prop=""></el-table-column>

        </el-table>
      </el-main>
      <el-footer>
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
          <el-button class="add-button" style="color: #8eb97e" @click="">启用</el-button>
          <el-button class="modify-button" style="color: #00a3ff" @click="">停用</el-button>
        </div>
      </el-footer>
    </el-container>
  </div>


</template>

<script setup>
import {onMounted, ref} from "vue";
import {ElMessage} from "element-plus";
import {ExclamationCircleOutlined} from "@ant-design/icons-vue";
import {messageLoading} from "ele-admin-pro";
import {message, Modal} from "ant-design-vue/es";
import {pageBgmVendor,removeBgmVendor} from "@/api/bgm/bgmMaterial/vendor";

//-------供应商--------
const tableData = ref([]);
const total = ref(0);
const pageSize = ref(10);
const currentPage = ref(1);




//获取表格数据
const getTableData = async () => {
  const res = await pageBgmVendor({
    page: currentPage.value,
    limit: pageSize.value,
  });
  console.log('成功获取到table数据：', res);
  tableData.value = res.list;
  total.value = res.count;
  console.log(tableData.value);
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




onMounted(() => {

  //获取表格数据
  getTableData();

});
</script>

<style scoped>
.el-header {
  padding-left: 0px;
  padding-right: 2px;
}

.el-main {
  padding-left: 0px;
  padding-top: 18px;
  height: 500px;
  padding-right: 2px;
}

.el-footer {
  position: fixed;
  bottom: 10px;
  left: 0px;
  width: 100%;
  height: 30px;
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
.demo-pagination-block  {
  margin-left: 200px;
  margin-bottom: 16px;
  display: flex;
}
</style>
