import request from '@/utils/request';

/**
 * 分页查询用户文件
 */
export async function pageUserFiles(params) {
  const res = await request.get('/system/user-file/page', { params });
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询用户文件列表
 */
export async function listUserFiles(params) {
  const res = await request.get('/system/user-file', {
    params
  });
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加用户文件
 */
export async function addUserFile(data) {
  const res = await request.post('/system/user-file', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改用户文件
 */
export async function updateUserFile(data) {
  const res = await request.put('/system/user-file', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除用户文件
 */
export async function removeUserFile(id) {
  const res = await request.delete('/system/user-file/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 批量删除用户文件
 */
export async function removeUserFiles(data) {
  const res = await request.delete('/system/user-file/batch', { data });
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
