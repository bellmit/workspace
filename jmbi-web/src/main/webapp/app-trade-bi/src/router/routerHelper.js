export function getRoutes(routeData, subViewFolder) {
  return routeData.map(x => ({
    path: `/${x.path}`,
    name: x.path,
    component: () =>
      import (`@/views/${subViewFolder}/${x.path}`),
    meta: {
      menuId: x.menuId
    }
  }))
}
