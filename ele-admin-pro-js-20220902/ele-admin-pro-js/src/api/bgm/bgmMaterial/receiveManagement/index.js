import request from "@/utils/request";

/**
 * 分页查询材料
 */
export async function pageBgmReceiveManagement(params) {
  const url = 'bgm/bgm-receive-management/page?' + Object.keys(params)
    .map(k => `${encodeURIComponent(k)}=${encodeURIComponent(params[k])}`)
    .join('&');
  const res = await request.get(url);
  console.log(res)
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询材料列表
 */
export async function listBgmReceiveManagement(params) {
  console.log(params);
  const res = await request.get('/bgm/bgm-receive-management', {
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
export async function addBgmReceiveManagement(data) {
  const res = await request.post('/bgm/bgm-receive-management', data);
  if (res.data.code === 0) {
    return res.data.message;
  }

  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改材料
 */
export async function updateBgmReceiveManagement(data) {
  const res = await request.put('/bgm/bgm-receive-management', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除材料
 */
export async function removeBgmReceiveManagement(id) {
  console.log("id=="+id);
  const res = await request.delete('/bgm/bgm-receive-management/' + id);
  console.log(res);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}


/**
 * 批量删除材料
 */
export async function removeReceiveManagements(data) {
  const res = await request.delete('/bgm/bgm-receive-management/batch', {
    data
  });
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
