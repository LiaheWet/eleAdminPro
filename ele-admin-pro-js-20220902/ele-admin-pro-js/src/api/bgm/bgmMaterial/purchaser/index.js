import request from "@/utils/request";

/**
 * 分页查询计量单位
 */
export async function pageBgmPurchaser(params) {
  const res = await request.get('/bgm/bgmPurchaser/page', { params });
  console.log(res);
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询计量单位列表
 */
export async function listBgmPurchaser(params) {
  console.log(params);
  const res = await request.get('/bgm/bgmPurchaser', {
    params
  });
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加计量单位
 */
export async function addBgmPurchaser(data) {
  const res = await request.post('/bgm/bgmPurchaser', data);
  if (res.data.code === 0) {
    return res.data.message;
  }

  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改计量单位
 */
export async function updateBgmPurchaser(data) {
  const res = await request.put('/bgm/bgmPurchaser', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除计量单位
 */
export async function removeBgmPurchaser(id) {
  console.log("id=="+id);
  const res = await request.delete('/bgm/bgmPurchaser/' + id);
  console.log(res);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
