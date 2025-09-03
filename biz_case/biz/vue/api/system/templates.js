import request from '@/utils/request'

// 查询内容得分模板列表
export function listTemplates(query) {
  return request({
    url: '/system/templates/list',
    method: 'get',
    params: query
  })
}

// 查询内容得分模板详细
export function getTemplates(templatesId) {
  return request({
    url: '/system/templates/' + templatesId,
    method: 'get'
  })
}

// 新增内容得分模板
export function addTemplates(data) {
  return request({
    url: '/system/templates',
    method: 'post',
    data: data
  })
}

// 修改内容得分模板
export function updateTemplates(data) {
  return request({
    url: '/system/templates',
    method: 'put',
    data: data
  })
}

// 删除内容得分模板
export function delTemplates(templatesId) {
  return request({
    url: '/system/templates/' + templatesId,
    method: 'delete'
  })
}
