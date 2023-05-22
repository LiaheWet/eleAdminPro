import request from "@/utils/request";

/**
 * 分页查询计量单位
 */
export async function pageBgmMeasurement(params) {
  const res = await request.get('/bgm/bgmMeasurement/page', { params });
  console.log(res);
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询计量单位列表
 */
export async function listBgmMeasurement(params) {
  console.log(params);
  const res = await request.get('/bgm/bgmMeasurement', {
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
export async function addBgmMeasurement(data) {
  const res = await request.post('/bgm/bgmMeasurement', data);
  if (res.data.code === 0) {
    return res.data.message;
  }

  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改计量单位
 */
export async function updateBgmMeasurement(data) {
  const res = await request.put('/bgm/bgmMeasurement', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除计量单位
 */
export async function removeBgmMeasurement(id) {
  console.log("id=="+id);
  const res = await request.delete('/bgm/bgmMeasurement/' + id);
  console.log(res);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
