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
      filePath: 'view/news/news-setting.vue', // 需要补全文件路径
    },
    // 新增详情页路由
    {
      title: '新闻详情',
      type: 'view',
      name: 'NewsDetail',
      route: '/news/detail/:id', // 动态路由参数
      filePath: 'view/news/news-view.vue',
      inNav: false, // 不在侧边栏显示
      hidden: true // 添加额外隐藏标识（根据路由渲染逻辑需要）
    }
  ],
}

export default newsRouter
