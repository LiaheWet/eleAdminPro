import request from '@/utils/request';

/**
 * 查询字典列表
 */
export async function listDictionaries(params) {
  const res = await request.get('/system/dictionary', {
    params
  });
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加字典
 */
export async function addDictionary(data) {
  const res = await request.post('/system/dictionary', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改字典
 */
export async function updateDictionary(data) {
  const res = await request.put('/system/dictionary', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除字典
 */
export async function removeDictionary(id) {
  const res = await request.delete('/system/dictionary/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
