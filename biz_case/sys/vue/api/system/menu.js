import request from '@/utils/request'

// 查询角色和菜单关联列表
export function listMenu(query) {
  return request({
    url: '/system/menu/list',
    method: 'get',
    params: query
  })
}

// 查询角色和菜单关联详细
export function getMenu(roleId) {
  return request({
    url: '/system/menu/' + roleId,
    method: 'get'
  })
}

// 新增角色和菜单关联
export function addMenu(data) {
  return request({
    url: '/system/menu',
    method: 'post',
    data: data
  })
}

// 修改角色和菜单关联
export function updateMenu(data) {
  return request({
    url: '/system/menu',
    method: 'put',
    data: data
  })
}

// 删除角色和菜单关联
export function delMenu(roleId) {
  return request({
    url: '/system/menu/' + roleId,
    method: 'delete'
  })
}
