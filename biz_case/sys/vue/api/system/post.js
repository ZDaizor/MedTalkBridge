import request from '@/utils/request'

// 查询用户与岗位关联列表
export function listPost(query) {
  return request({
    url: '/system/post/list',
    method: 'get',
    params: query
  })
}

// 查询用户与岗位关联详细
export function getPost(userId) {
  return request({
    url: '/system/post/' + userId,
    method: 'get'
  })
}

// 新增用户与岗位关联
export function addPost(data) {
  return request({
    url: '/system/post',
    method: 'post',
    data: data
  })
}

// 修改用户与岗位关联
export function updatePost(data) {
  return request({
    url: '/system/post',
    method: 'put',
    data: data
  })
}

// 删除用户与岗位关联
export function delPost(userId) {
  return request({
    url: '/system/post/' + userId,
    method: 'delete'
  })
}
