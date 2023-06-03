import request from "@/utils/request";

/**
 * 分页查询供应商
 */
export async function selectBgmVendorPro(params) {
  const res = await request.get('/bgm/bgmVendor/selectPro',{
    params
  });
  console.log(res)
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}
export async function pageBgmVendor(params) {
  const res = await request.get('/bgm/bgmVendor/page',{
    params
  });
  console.log(res)
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询供应商列表
 */
export async function listBgmVendor(params) {
  console.log(params);
  const res = await request.get('/bgm/bgmVendor', {
    params
  });
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加供应商
 */
export async function addBgmVendor(data) {
  const res = await request.post('/bgm/bgmVendor', data);
  if (res.data.code === 0) {
    return res.data.message;
  }

  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改供应商
 */
export async function updateBgmVendor(data) {
  const res = await request.put('/bgm/bgmVendor', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除供应商
 */
export async function removeBgmVendor(id) {
  console.log("id=="+id);
  const res = await request.delete('/bgm/bgmVendor/' + id);
  console.log(res);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}


/**
 * 批量删除供应商
 */
export async function removeBgmVendors(data) {
  const res = await request.delete('/bgm/bgmVendor/batch', {
    data
  });
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
