const newsRouter = {
  route: null,
  name: null,
  title: '新闻管理',
  type: 'folder', // 类型: folder, tab, view
  icon: 'iconfont icon-tushuguanli',
  isElementIcon: false,
  filePath: 'view/news/', // 文件路径
  order: null,
  inNav: true,
  children: [
    {
      title: '新闻列表',
      type: 'view',
      name: 'NewsCreate',
      route: '/news/list',
      filePath: 'view/news/news-list.vue',
      inNav: true,
      icon: 'iconfont icon-tushuguanli',
      isElementIcon: false,
    },
    {
      title: '添加新闻',
      type: 'view',
      name: 'BookCreate',
      route: '/news/add',
      filePath: 'view/news/news.vue',
      inNav: true,
      icon: 'iconfont icon-add',
      isElementIcon: false,
    },
    {
      title: '新闻设置',
      type: 'view',
      name: 'NewsSetting',
      route: '/news/setting',
    },
  ],
}

export default newsRouter
