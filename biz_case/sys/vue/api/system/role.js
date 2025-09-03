import request from '@/utils/request'

// 查询用户和角色关联列表
export function listRole(query) {
  return request({
    url: '/system/role/list',
    method: 'get',
    params: query
  })
}

// 查询用户和角色关联详细
export function getRole(userId) {
  return request({
    url: '/system/role/' + userId,
    method: 'get'
  })
}

// 新增用户和角色关联
export function addRole(data) {
  return request({
    url: '/system/role',
    method: 'post',
    data: data
  })
}

// 修改用户和角色关联
export function updateRole(data) {
  return request({
    url: '/system/role',
    method: 'put',
    data: data
  })
}

// 删除用户和角色关联
export function delRole(userId) {
  return request({
    url: '/system/role/' + userId,
    method: 'delete'
  })
}
