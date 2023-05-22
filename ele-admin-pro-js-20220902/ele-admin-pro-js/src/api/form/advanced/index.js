/**
 * 获取数据
 */
export async function queryList() {
  const data = [
    {
      key: '1',
      number: '00001',
      name: 'John Brown',
      department: '研发部'
    },
    {
      key: '2',
      number: '00002',
      name: 'Jim Green',
      department: '产品部'
    },
    {
      key: '3',
      number: '00003',
      name: 'Joe Black',
      department: '产品部'
    }
  ];
  return data;
}
