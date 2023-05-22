import request from "@/utils/request";

/**
 * 分页查询材料
 */
export async function pageBgmSupplyMaterial(params) {
  const res = await request.get('/bgm/bgmSupplyMaterial/page',{
    params
  });
  console.log(res)
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询材料列表
 */
export async function listBgmSupplyMaterial(params) {
  console.log(params);
  const res = await request.get('/bgm/bgmSupplyMaterial', {
    params
  });
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加材料
 */
export async function addBgmSupplyMaterial(data) {
  const res = await request.post('/bgm/bgmSupplyMaterial', data);
  if (res.data.code === 0) {
    return res.data.message;
  }

  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改材料
 */
export async function updateBgmSupplyMaterial(data) {
  const res = await request.put('/bgm/bgmSupplyMaterial', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除材料
 */
export async function removeBgmSupplyMaterial(id) {
  console.log("id=="+id);
  const res = await request.delete('/bgm/bgmSupplyMaterial/' + id);
  console.log(res);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}


/**
 * 批量删除材料
 */
export async function removeBgmMaterials(data) {
  const res = await request.delete('/bgm/bgmMaterial/batch', {
    data
  });
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
