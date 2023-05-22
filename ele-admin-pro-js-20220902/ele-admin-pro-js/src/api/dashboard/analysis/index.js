import request from '@/utils/request';

/**
 * 获取支付笔数数据
 */
export async function getPayNumList() {
  const res = await request.get(
    'https://cdn.eleadmin.com/20200610/analysis-pay-num.json'
  );
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 获取销售量数据
 */
export async function getSaleroomList() {
  const res = await request.get(
    'https://cdn.eleadmin.com/20200610/analysis-saleroom.json'
  );
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 获取最近 1 小时访问情况数据
 * @returns {Promise<Object>}
 */
export async function getVisitHourList() {
  const res = await request.get(
    'https://cdn.eleadmin.com/20200610/analysis-visits.json'
  );
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 获取词云数据
 */
export async function getWordCloudList() {
  const res = await request.get(
    'https://cdn.eleadmin.com/20200610/analysis-hot-search.json'
  );
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}
