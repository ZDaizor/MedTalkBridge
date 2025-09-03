import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listStep(query) {
  return request({
    url: '/system/step/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getStep(stepId) {
  return request({
    url: '/system/step/' + stepId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addStep(data) {
  return request({
    url: '/system/step',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateStep(data) {
  return request({
    url: '/system/step',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delStep(stepId) {
  return request({
    url: '/system/step/' + stepId,
    method: 'delete'
  })
}
