/**
 * 分页查询通知
 */
export async function pageNotices(_params) {
  const result = {
    count: 10,
    list: [
      {
        id: 21,
        title: 'EleAdmin新版本发布，欢迎体验',
        time: '2020-07-24 11:35',
        status: 0
      },
      {
        id: 22,
        title: 'EleAdmin新版本发布，欢迎体验',
        time: '2020-07-24 11:35',
        status: 0
      },
      {
        id: 23,
        title: 'EleAdmin新版本发布，欢迎体验',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 24,
        title: 'EleAdmin新版本发布，欢迎体验',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 25,
        title: 'EleAdmin新版本发布，欢迎体验',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 26,
        title: 'EleAdmin新版本发布，欢迎体验',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 27,
        title: 'EleAdmin新版本发布，欢迎体验',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 28,
        title: 'EleAdmin新版本发布，欢迎体验',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 29,
        title: 'EleAdmin新版本发布，欢迎体验',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 30,
        title: 'EleAdmin新版本发布，欢迎体验',
        time: '2020-07-24 11:35',
        status: 1
      }
    ]
  };
  return result;
}

/**
 * 分页查询私信
 */
export async function pageLetters(_params) {
  const result = {
    count: 10,
    list: [
      {
        id: 11,
        title: 'Jasmine给你发来了一条私信',
        time: '2020-07-24 11:35',
        status: 0
      },
      {
        id: 12,
        title: 'Jasmine给你发来了一条私信',
        time: '2020-07-24 11:35',
        status: 0
      },
      {
        id: 13,
        title: 'Jasmine给你发来了一条私信',
        time: '2020-07-24 11:35',
        status: 0
      },
      {
        id: 14,
        title: 'Jasmine给你发来了一条私信',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 15,
        title: 'Jasmine给你发来了一条私信',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 16,
        title: 'Jasmine给你发来了一条私信',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 17,
        title: 'Jasmine给你发来了一条私信',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 18,
        title: 'Jasmine给你发来了一条私信',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 19,
        title: 'Jasmine给你发来了一条私信',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 20,
        title: 'Jasmine给你发来了一条私信',
        time: '2020-07-24 11:35',
        status: 1
      }
    ]
  };
  return result;
}

/**
 * 分页查询代办
 */
export async function pageTodos(_params) {
  const result = {
    count: 10,
    list: [
      {
        id: 1,
        title: '你有两条任务待完成，不要忘了哦~',
        time: '2020-07-24 11:35',
        status: 0
      },
      {
        id: 2,
        title: '你有两条任务待完成，不要忘了哦~',
        time: '2020-07-24 11:35',
        status: 0
      },
      {
        id: 3,
        title: '你有两条任务待完成，不要忘了哦~',
        time: '2020-07-24 11:35',
        status: 0
      },
      {
        id: 4,
        title: '你有两条任务待完成，不要忘了哦~',
        time: '2020-07-24 11:35',
        status: 0
      },
      {
        id: 5,
        title: '你有两条任务待完成，不要忘了哦~',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 6,
        title: '你有两条任务待完成，不要忘了哦~',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 7,
        title: '你有两条任务待完成，不要忘了哦~',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 8,
        title: '你有两条任务待完成，不要忘了哦~',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 9,
        title: '你有两条任务待完成，不要忘了哦~',
        time: '2020-07-24 11:35',
        status: 1
      },
      {
        id: 10,
        title: '你有两条任务待完成，不要忘了哦~',
        time: '2020-07-24 11:35',
        status: 1
      }
    ]
  };
  return result;
}

/**
 * 查询未读数量
 */
export async function getUnReadNum() {
  return {
    notice: 2,
    letter: 3,
    todo: 4
  };
}
