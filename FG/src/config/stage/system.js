// system.js
const systemConfig = {
  route: null,
  name: null,
  title: '新闻设置',
  type: 'folder', // 文件夹类型
  icon: 'iconfont icon-zidingyi',
  filePath: 'view/setting/',
  order: 5, // 控制菜单顺序
  inNav: true,
  children: [
    {
      title: '来源设置',
      type: 'view',
      name: 'SourceSetting',
      route: '/source',
      filePath: 'view/system/source-setting.vue',
      inNav: true,
      icon: 'el-icon-setting',
      permission: ['admin'] // 权限控制
    },
    {
      title: '分类设置',
      type: 'view',
      name: 'CategorySetting',
      route: '/category',
      filePath: 'view/system/category-setting.vue',
      inNav: true,
      icon: 'el-icon-setting',
      permission: ['admin'] // 权限控制
    },
    {
      title: '国家和地区设置',
      type: 'view',
      name: 'CountrySetting',
      route: '/country',
      filePath: 'view/system/country-setting.vue',
      inNav: true,
      icon: 'el-icon-setting',
      permission: ['admin'] // 权限控制
    }
  ]
}

export default systemConfig
