import request from "@/utils/request";
import {setToken} from "@/utils/token-util";

// 分页查询供应商
export async function findAllVendorService(params){
  const res = await request.get('/bgm/bgmVendor/page',{params});

  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));

}

// 通过供应商id查找银行账户
export async function findVendorAccount(id){
  const res = await request.get('/bgm/bgm-vendor-account/'+id);

  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));

}

// 通过供应商id查找联系人
export async function findVendorContact(id){
  const res = await request.get('/bgm/bgm-vendor-contact/'+id);

  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));

}

// 通过供应商id查找原材料
export async function findVendorMaterial(id){
  const res = await request.get('/bgm/bgm-vendor-material/'+id);

  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));

}

// 通过id删除供应商
export async function removeVendor(id){
  const res = await request.delete('/bgm/bgmVendor/'+id);

  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));

}

// 通过id删除银行账户
export async function removeVendorAccount(id){
  const res = await request.delete('/bgm/bgm-vendor-account/'+id);

  if (res.data.code === 0)  {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));

}

// 批量删除银行账户
export async function removeVendorAccountList(data){
  const res = await request.delete('/bgm/bgm-vendor-account/batch', {data});
  console.log("批量删除",res)
  if (res.data.code === 0)  {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));

}

// 通过id删除联系人
export async function removeVendorContact(id){
  const res = await request.delete('/bgm/bgm-vendor-contact/'+id);

  if (res.data.code === 0 ) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

// 批量删除联系人
export async function removeVendorContactList(data){
  const res = await request.delete('/bgm/bgm-vendor-contact/batch', {data});
  console.log("批量删除",res)
  if (res.data.code === 0)  {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));

}


// 通过id删除供应商原材料
export async function removeVendorMaterial(id){
  const res = await request.delete('/bgm/bgm-vendor-material/'+id);

  if (res.data.code === 0 ) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

// 批量删除供应商原材料
export async function removeVendorMaterialList(data){
  const res = await request.delete('/bgm/bgm-vendor-material/batch', {data});
  console.log("批量删除",res)
  if (res.data.code === 0)  {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));

}



// 保存添加或修改供应基本信息
export async function saveOrUpdateVendor(dialogData) {
  const res = await request.post('/bgm/bgmVendor/',dialogData);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

// 保存添加或修改供应商银行账户
export async function saveOrUpdateAccount(accountList) {
  const res = await request.post('/bgm/bgm-vendor-account/batch',accountList);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

// 保存添加或修改联系人
export async function saveOrUpdateContact(contactList) {
  const res = await request.post('/bgm/bgm-vendor-contact/batch', contactList);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

//查找所有原材料
export async function findMaterial(){
  const res = await request.get('/bgm/bgmSupplyMaterial');

  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

export async function saveVendorMaterial(selectedRows) {
  const res = await request.post('/bgm/bgm-vendor-material/batch', selectedRows);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

