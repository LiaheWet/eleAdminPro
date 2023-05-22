import request from "@/utils/request";

/*------------------材料类别-------------------------*/
/**
 * 查询材料类别列表
 */
export async function listBgmMaterialType() {

  const res = await request.get('/bgm/bgmMaterialType/integrate');
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}
/**
 * 添加材料类别
 */
export async function addBgmMaterialType(data) {
  const res = await request.post('/bgm/bgmMaterialType', data);
  if (res.data.code === 0) {
    return res.data.message;
  }

  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改材料类别
 */
export async function updateBgmMaterialType(data) {
  const res = await request.put('/bgm/bgmMaterialType', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除材料类别
 */
export async function removeBgmMaterialType(id) {
  console.log("id=="+id);
  const res = await request.delete('/bgm/bgmMaterialType/' + id);
  console.log(res);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}



/*------------------材料-------------------------*/
/**
 * 分页查询材料
 */
export async function pageBgmMaterial(params) {
  const res = await request.get('/bgm/bgmMaterial/page',{
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
export async function listBgmMaterial(params) {
  console.log(params);
  const res = await request.get('/bgm/bgmMaterial', {
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
export async function addBgmMaterial(data) {
  const res = await request.post('/bgm/bgmMaterial', data);
  if (res.data.code === 0) {
    return res.data.message;
  }

  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改材料
 */
export async function updateBgmMaterial(data) {
  const res = await request.put('/bgm/bgmMaterial', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除材料
 */
export async function removeBgmMaterial(id) {
  console.log("id=="+id);
  const res = await request.delete('/bgm/bgmMaterial/' + id);
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
