import request from '@/utils/request'

// 查询病例列表
export function listCase(query) {
  return request({
    url: '/system/case/list',
    method: 'get',
    params: query
  })
}

// 查询病例详细
export function getCase(caseId) {
  return request({
    url: '/system/case/' + caseId,
    method: 'get'
  })
}

// 新增病例
export function addCase(data) {
  return request({
    url: '/system/case',
    method: 'post',
    data: data
  })
}

// 修改病例
export function updateCase(data) {
  return request({
    url: '/system/case',
    method: 'put',
    data: data
  })
}

// 删除病例
export function delCase(caseId) {
  return request({
    url: '/system/case/' + caseId,
    method: 'delete'
  })
}
