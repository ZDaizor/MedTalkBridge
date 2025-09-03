import request from '@/utils/request'

// 查询系统访问记录列表
export function listLogininfor(query) {
  return request({
    url: '/system/logininfor/list',
    method: 'get',
    params: query
  })
}

// 查询系统访问记录详细
export function getLogininfor(infoId) {
  return request({
    url: '/system/logininfor/' + infoId,
    method: 'get'
  })
}

// 新增系统访问记录
export function addLogininfor(data) {
  return request({
    url: '/system/logininfor',
    method: 'post',
    data: data
  })
}

// 修改系统访问记录
export function updateLogininfor(data) {
  return request({
    url: '/system/logininfor',
    method: 'put',
    data: data
  })
}

// 删除系统访问记录
export function delLogininfor(infoId) {
  return request({
    url: '/system/logininfor/' + infoId,
    method: 'delete'
  })
}
