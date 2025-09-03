import request from '@/utils/request'

// 查询角色和部门关联列表
export function listDept(query) {
  return request({
    url: '/system/dept/list',
    method: 'get',
    params: query
  })
}

// 查询角色和部门关联详细
export function getDept(roleId) {
  return request({
    url: '/system/dept/' + roleId,
    method: 'get'
  })
}

// 新增角色和部门关联
export function addDept(data) {
  return request({
    url: '/system/dept',
    method: 'post',
    data: data
  })
}

// 修改角色和部门关联
export function updateDept(data) {
  return request({
    url: '/system/dept',
    method: 'put',
    data: data
  })
}

// 删除角色和部门关联
export function delDept(roleId) {
  return request({
    url: '/system/dept/' + roleId,
    method: 'delete'
  })
}
