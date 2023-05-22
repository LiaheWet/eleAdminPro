import request from "@/utils/request";


/**
 * 根据信息查询协议相关信息
 */
export async function selectBgmAgreementPro(params) {
  const res = await request.get('/bgm/bgmAgreement/selectPro',{
    params
  });
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 分页查询协议
 */
export async function pageBgmAgreement(params) {
  const res = await request.get('/bgm/bgmAgreement/page',{
    params
  });
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加协议pro
 */
export async function addBgmAgreementPro(data) {
  const res = await request.post('/bgm/bgmAgreement/savePro', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
/**
 * 修改协议Pro
 */
export async function updateBgmAgreementPro(data) {
  const res = await request.put('/bgm/bgmAgreement/updatePro', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));

}
/**
 * 删除协议
 */
export async function removeBgmAgreementPro(id) {
  console.log("id=="+id);
  const res = await request.delete('/bgm/bgmAgreement/' + id);
  console.log(res);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}


/**
 * 查询协议列表
 */
export async function listBgmAgreement(params) {
  const res = await request.get('/bgm/bgmAgreement', {
    params
  });
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加协议
 */
export async function addBgmAgreement(data) {
  const res = await request.post('/bgm/bgmAgreement', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改协议
 */
export async function updateBgmAgreement(data) {
  const res = await request.put('/bgm/bgmAgreement', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}




/**
 * 批量删除协议
 */
export async function removeBgmAgreements(data) {
  const res = await request.delete('/bgm/bgmAgreement/batch', {
    data
  });
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
