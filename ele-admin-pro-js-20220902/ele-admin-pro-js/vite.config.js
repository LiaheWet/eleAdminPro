import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import ViteCompression from 'vite-plugin-compression';
import ViteComponents from 'unplugin-vue-components/vite';
import { AntDesignVueResolver } from 'unplugin-vue-components/resolvers';
import { EleAdminResolver } from 'ele-admin-pro/lib/utils/resolvers';
import { DynamicAntdLess } from 'ele-admin-pro/lib/utils/dynamic-theme';
import { resolve } from 'path';

export default defineConfig(({ command }) => {
  const isBuild = command === 'build';
  return {
    resolve: {
      alias: {
        '@/': resolve('src') + '/',
        'vue-i18n': 'vue-i18n/dist/vue-i18n.cjs.js'
      }
    },
    plugins: [
      vue(),
      // 组件按需引入
      ViteComponents({
        dts: false,
        resolvers: [
          AntDesignVueResolver({
            importStyle: isBuild ? 'less' : false
          }),
          EleAdminResolver({
            importStyle: isBuild ? 'less' : false
          })
        ]
      }),
      // gzip 压缩
      ViteCompression({
        disable: !isBuild,
        threshold: 10240,
        algorithm: 'gzip',
        ext: '.gz'
      })
    ],
    css: {
      preprocessorOptions: {
        less: {
          javascriptEnabled: true,
          plugins: [new DynamicAntdLess()],
          modifyVars: {
            // 组件样式开发环境全局引入生产环境按需引入
            'style-entry-file': isBuild ? 'as-needed' : 'global-import'
          }
        }
      }
    },
    optimizeDeps: {
      include: [
        'sortablejs',
        'vuedraggable',
        'echarts/core',
        'echarts/charts',
        'echarts/renderers',
        'echarts/components',
        'vue-echarts',
        'echarts-wordcloud',
        'xlsx'
      ]
    },
    build: {
      target: 'chrome63',
      chunkSizeWarningLimit: 2000
    }
  };
});
