// 接口地址
export const API_BASE_URL = import.meta.env.VITE_API_URL;

// 项目名称
export const PROJECT_NAME = import.meta.env.VITE_APP_NAME;

// 不显示侧栏的路由
export const HIDE_SIDEBARS = [];

// 不显示页脚的路由
export const HIDE_FOOTERS = [
  '/system/dictionary',
  '/system/organization',
  '/form/advanced',
  '/bgm/bgmMaterial/vendor',
  '/bgm/bgmMaterial/supply'
];

// 页签同路由不同参数可重复打开的路由
export const REPEATABLE_TABS = [];

// 不需要登录的路由
export const WHITE_LIST = ['/login', '/forget'];

// 开启 KeepAlive 后仍然不需要缓存的路由地址
export const KEEP_ALIVE_EXCLUDES = [];

// 直接指定菜单数据
export const USER_MENUS = undefined;

// 首页名称, 为空则取第一个菜单的名称
export const HOME_TITLE = undefined;

// 首页路径, 为空则取第一个菜单的地址
export const HOME_PATH = undefined;

// 外层布局的路由地址
export const LAYOUT_PATH = '/';

// 刷新路由的路由地址
export const REDIRECT_PATH = '/redirect';

// 开启页签栏是否缓存组件
//export const TAB_KEEP_ALIVE = !import.meta.env.DEV;
export const TAB_KEEP_ALIVE = true;

// token 传递的 header 名称
export const TOKEN_HEADER_NAME = 'Authorization';

// token 存储的名称
export const TOKEN_STORE_NAME = 'access_token';

// 主题配置存储的名称
export const THEME_STORE_NAME = 'theme';

// i18n 缓存的名称
export const I18N_CACHE_NAME = 'i18n-lang';

// 是否开启国际化功能
export const I18N_ENABLE = true;

// 高德地图 key , 自带的只能用于测试, 正式项目请自行到高德地图官网申请 key
export const MAP_KEY = '006d995d433058322319fa797f2876f5';

// EleAdminPro 授权码, 自带的只能用于演示, 正式项目请更换为自己的授权码
export const LICENSE_CODE =
  'dk9mcwJyetRWQlxWRiojIzJCLi8mcQ5WaxojI0NWZqJWdi4Wah12bkJCLlxWZu8mcwJiOt92Yu4WatRWYVJiOiQWaiwiIsIySMZGbt50Qi42bpNnclZnI0nIwEjLxIiO0NW=';
