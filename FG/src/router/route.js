import homeRouter from './home-router'

const routes = [
  // 新增的根路径路由，指向earth.vue
  {
    path: '/',
    name: 'Earth',
    component: () => import('@/view/news/earth')
  },
  // 原有的Home路由保持不变，处理子路由
  {
    path: '/', // 路径仍为根，但实际匹配时由子路由处理
    name: 'Home',
    component: () => import('@/view/home/home'),
    children: [
      ...homeRouter,
      {
        path: '/news/detail/:id',
        name: 'NewsView',
        component: () => import('@/view/news/news-view.vue'),
        props: true
      }
    ],
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/view/login/login')
  },
  {
    redirect: '/404',
    path: '/:pathMatch(.*)',
  }
]

export default routes
