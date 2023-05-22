import request from '@/utils/request';

/**
 * 分页查询登录日志
 */
export async function pageLoginRecords(params) {
  const res = await request.get('/system/login-record/page', { params });
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询登录日志列表
 */
export async function listLoginRecords(params) {
  const res = await request.get('/system/login-record', { params });
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}
