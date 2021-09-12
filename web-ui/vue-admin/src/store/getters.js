import config from '@/config'

const getters = {
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  introduction: state => state.user.introduction,
  roles: state => state.user.roles,
  permissions: state => state.user.permissions,
  permission_routes: state => state.permission.routes,
  topbarRouters:state => state.permission.topbarRouters,
  defaultRoutes:state => state.permission.defaultRoutes,
  sidebarRouters:state => state.permission.sidebarRouters,

  MyShare:state =>state.fileList.MyShare,

  // 登录状态
  isLogin: (state) => state.user.token,
  // 用户姓名
  username: (state) => state.user.name,
  // 用户ID
  userId: (state) => state.user.userId,
  // 表格显示列
  selectedColumnList: (state) =>
    state.fileList.selectedColumnList === undefined
      ? config.allColumnList
      : state.fileList.selectedColumnList.split(","),
  // 文件查看模式
  fileModel: (state) => state.fileList.fileModel === undefined ? 0 : Number(state.fileList.fileModel),
  // 网格模式 & 时间线模式下 文件图标大小
  gridSize: (state) => state.fileList.gridSize
}
export default getters
