import { createApp } from 'vue';
import App from './App.vue';
import store from './store';
import router from './router';
import permission from './utils/permission';
import i18n from './i18n';
import './styles/index.less';
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
const app = createApp(App);

app.use(store);
app.use(router);
app.use(permission);
app.use(ElementPlus);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
};
app.use(i18n);


app.mount('#app');
