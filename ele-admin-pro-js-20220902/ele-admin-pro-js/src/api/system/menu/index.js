import request from '@/utils/request';

/**
 * 查询菜单列表
 */
export async function listMenus(params) {
  const res = await request.get('/system/menu', {
    params
  });
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加菜单
 */
export async function addMenu(data) {
  const res = await request.post('/system/menu', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改菜单
 */
export async function updateMenu(data) {
  const res = await request.put('/system/menu', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除菜单
 */
export async function removeMenu(id) {
  const res = await request.delete('/system/menu/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
