import request from '@/utils/request';

/**
 * 分页查询角色
 */
export async function pageRoles(params) {
  const res = await request.get('/system/role/page', { params });
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询角色列表
 */
export async function listRoles(params) {
  const res = await request.get('/system/role', {
    params
  });
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加角色
 */
export async function addRole(data) {
  const res = await request.post('/system/role', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改角色
 */
export async function updateRole(data) {
  const res = await request.put('/system/role', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除角色
 */
export async function removeRole(id) {
  const res = await request.delete('/system/role/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 批量删除角色
 */
export async function removeRoles(data) {
  const res = await request.delete('/system/role/batch', {
    data
  });
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 获取角色分配的菜单
 */
export async function listRoleMenus(roleId) {
  const res = await request.get('/system/role-menu/' + roleId);
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改角色菜单
 */
export async function updateRoleMenus(roleId, data) {
  const res = await request.put('/system/role-menu/' + roleId, data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
