import request from '@/utils/request'

// 查询操作日志记录列表
export function listLog(query) {
  return request({
    url: '/system/log/list',
    method: 'get',
    params: query
  })
}

// 查询操作日志记录详细
export function getLog(operId) {
  return request({
    url: '/system/log/' + operId,
    method: 'get'
  })
}

// 新增操作日志记录
export function addLog(data) {
  return request({
    url: '/system/log',
    method: 'post',
    data: data
  })
}

// 修改操作日志记录
export function updateLog(data) {
  return request({
    url: '/system/log',
    method: 'put',
    data: data
  })
}

// 删除操作日志记录
export function delLog(operId) {
  return request({
    url: '/system/log/' + operId,
    method: 'delete'
  })
}
