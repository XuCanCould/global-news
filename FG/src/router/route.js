import homeRouter from './home-router'

const routes = [
  // 关键：添加根路径重定向到 /earth
  {
    path: '/',
    redirect: '/earth'
  },
  {
    path: '/earth', // 独立路径
    name: 'Earth',
    component: () => import('@/view/news/earth')
  },
  {
    path: '/earth/:country',
    name: 'DetailPage',
    component: () => import('@/view/news/news-list-user.vue'),
    props: true
  },
  {
    path: '/earth/:country/:id',
    name: 'NewsUserView',
    component: () => import('@/view/news/news-view-user.vue'),
    props: true
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
