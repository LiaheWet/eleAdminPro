import request from '@/utils/request';

/**
 * 获取当前登录的用户信息、菜单、权限、角色
 */
export async function getUserInfo() {
  const res = await request.get('/auth/user');
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改当前登录的用户密码
 */
export async function updatePassword(data) {
  const res = await request.put('/auth/password', data);
  if (res.data.code === 0) {
    return res.data.message ?? '修改成功';
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询未读通知
 */
export async function getUnreadNotice() {
  return {
    notice: [
      {
        color: '#60B2FC',
        icon: 'NotificationFilled',
        title: '你收到了一封14份新周报',
        time: '2020-07-27 18:30:18'
      },
      {
        color: '#F5686F',
        icon: 'PushpinFilled',
        title: '许经理同意了你的请假申请',
        time: '2020-07-27 09:08:36'
      },
      {
        color: '#7CD734',
        icon: 'VideoCameraFilled',
        title: '陈总邀请你参加视频会议',
        time: '2020-07-26 18:30:01'
      },
      {
        color: '#FAAD14',
        icon: 'CarryOutFilled',
        title: '你推荐的刘诗雨已通过第三轮面试',
        time: '2020-07-25 16:38:46'
      },
      {
        color: '#2BCACD',
        icon: 'BellFilled',
        title: '你的6月加班奖金已发放',
        time: '2020-07-25 11:03:31'
      }
    ],
    letter: [
      {
        avatar:
          'https://cdn.eleadmin.com/20200609/c184eef391ae48dba87e3057e70238fb.jpg',
        title: 'SunSmile 评论了你的日志',
        content: '写的不错, 以后多多向你学习~',
        time: '2020-07-27 18:30:18'
      },
      {
        avatar:
          'https://cdn.eleadmin.com/20200609/948344a2a77c47a7a7b332fe12ff749a.jpg',
        title: '刘诗雨 点赞了你的日志',
        content: '写的不错, 以后多多向你学习~',
        time: '2020-07-27 09:08:36'
      },
      {
        avatar:
          'https://cdn.eleadmin.com/20200609/2d98970a51b34b6b859339c96b240dcd.jpg',
        title: '酷酷的大叔 评论了你的周报',
        content: '写的不错, 以后多多向你学习~',
        time: '2020-07-26 18:30:01'
      },
      {
        avatar:
          'https://cdn.eleadmin.com/20200609/f6bc05af944a4f738b54128717952107.jpg',
        title: 'Jasmine 点赞了你的周报',
        content: '写的不错, 以后多多向你学习~',
        time: '2020-07-25 11:03:31'
      }
    ],
    todo: [
      {
        status: 0,
        title: '刘诗雨的请假审批',
        description: '刘诗雨在 07-27 18:30 提交的请假申请'
      },
      {
        status: 1,
        title: '第三方代码紧急变更',
        description: '需要在 2020-07-27 之前完成'
      },
      {
        status: 2,
        title: '信息安全考试',
        description: '需要在 2020-07-26 18:30 前完成'
      },
      {
        status: 2,
        title: 'EleAdmin发布新版本',
        description: '需要在 2020-07-25 11:03 前完成'
      }
    ]
  };
}
