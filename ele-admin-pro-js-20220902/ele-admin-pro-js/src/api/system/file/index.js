import request from '@/utils/request';

/**
 * 上传文件
 */
export async function uploadFile(file) {
  const formData = new FormData();
  formData.append('file', file);
  const res = await request.post('/file/upload', formData);
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 上传 base64 文件
 * @param base64 文件数据
 * @param fileName 文件名称
 */
export async function uploadBase64File(base64, fileName) {
  const formData = new FormData();
  formData.append('base64', base64);
  if (fileName) {
    formData.append('fileName', fileName);
  }
  const res = await request.post('/file/upload/base64', formData);
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 分页查询文件上传记录
 */
export async function pageFiles(params) {
  const res = await request.get('/file/page', { params });
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除文件
 */
export async function removeFile(id) {
  const res = await request.delete('/file/remove/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 批量删除文件
 */
export async function removeFiles(data) {
  const res = await request.delete('/file/remove/batch', {
    data
  });
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
