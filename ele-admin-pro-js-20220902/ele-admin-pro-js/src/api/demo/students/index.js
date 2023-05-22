import request from '@/utils/request';

/**
 * 分页查询角色
 */
export async function pageStudents(params) {
  const res = await request.get('/demo/students/page', { params });
  console.log(res)
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询角色列表
 */
export async function listStudents(params) {
  console.log(params);
  const res = await request.get('/demo/students', {
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
export async function addStudent(data) {
  const res = await request.post('/demo/students', data);
  if (res.data.code === 0) {
    return res.data.message;
  }

  console.log("data");
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改角色
 */
export async function updateStudent(data) {
  const res = await request.put('/demo/students', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除角色
 */
export async function removeStudent(id) {
  console.log("id=="+id);
  const res = await request.delete('/demo/students1/' + id);
  console.log(res);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 批量删除角色
 */
export async function removeStudents(data) {
  const res = await request.delete('/demo/students/batch', {
    data
  });
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}



