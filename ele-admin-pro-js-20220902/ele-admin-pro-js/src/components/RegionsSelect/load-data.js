import request from '@/utils/request';
const BASE_URL = import.meta.env.BASE_URL;
let reqPromise;

/**
 * 获取省市区数据
 */
export function getRegionsData() {
  if (!reqPromise) {
    reqPromise = new Promise((resolve, reject) => {
      request
        .get(BASE_URL + 'json/regions-data.json', {
          baseURL: ''
        })
        .then((res) => {
          resolve(res.data ?? []);
        })
        .catch((e) => {
          reject(e);
        });
    });
  }
  return reqPromise;
}
