/**
 * 路由配置
 */
import NProgress from 'nprogress';
import { createRouter, createWebHistory } from 'vue-router';
import { WHITE_LIST, REDIRECT_PATH, LAYOUT_PATH } from '@/config/setting';
import { useUserStore } from '@/store/modules/user';
import { getToken } from '@/utils/token-util';
import { routes, getMenuRoutes } from './routes';

NProgress.configure({
  speed: 200,
  minimum: 0.02,
  trickleSpeed: 200,
  showSpinner: false
});

const router = createRouter({
  routes,
  history: createWebHistory(),
  scrollBehavior() {
    return { top: 0 };
  }
});

/**
 * 路由守卫
 */
router.beforeEach(async (to, from) => {
  if (!from.path.includes(REDIRECT_PATH)) {
    NProgress.start();
  }
  if (!getToken()) {
    // 未登录跳转登录界面
    if (!WHITE_LIST.includes(to.path)) {
      return {
        path: '/login',
        query: to.path === LAYOUT_PATH ? {} : { from: to.path }
      };
    }
    return;
  }
  // 注册动态路由
  const userStore = useUserStore();
  if (!userStore.menus) {
    const { menus, homePath } = await userStore.fetchUserInfo();
    if (menus) {
      router.addRoute(getMenuRoutes(menus, homePath));
      return { ...to, replace: true };
    }
  }
});

router.afterEach((to) => {
  if (!to.path.includes(REDIRECT_PATH) && NProgress.isStarted()) {
    setTimeout(() => {
      NProgress.done(true);
    }, 200);
  }
});

export default router;
