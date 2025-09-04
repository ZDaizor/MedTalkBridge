import request from '@/utils/request'

// 查询内容得分详情列表
export function listDetails(query) {
  return request({
    url: '/system/details/list',
    method: 'get',
    params: query
  })
}

// 查询内容得分详情详细
export function getDetails(scoreId) {
  return request({
    url: '/system/details/' + scoreId,
    method: 'get'
  })
}

// 新增内容得分详情
export function addDetails(data) {
  return request({
    url: '/system/details',
    method: 'post',
    data: data
  })
}

// 修改内容得分详情
export function updateDetails(data) {
  return request({
    url: '/system/details',
    method: 'put',
    data: data
  })
}

// 删除内容得分详情
export function delDetails(scoreId) {
  return request({
    url: '/system/details/' + scoreId,
    method: 'delete'
  })
}
