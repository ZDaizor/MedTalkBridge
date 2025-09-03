import request from '@/utils/request'

// 查询学生问诊列列表
export function listSessions(query) {
  return request({
    url: '/system/sessions/list',
    method: 'get',
    params: query
  })
}

// 查询学生问诊列详细
export function getSessions(sessionId) {
  return request({
    url: '/system/sessions/' + sessionId,
    method: 'get'
  })
}

// 新增学生问诊列
export function addSessions(data) {
  return request({
    url: '/system/sessions',
    method: 'post',
    data: data
  })
}

// 修改学生问诊列
export function updateSessions(data) {
  return request({
    url: '/system/sessions',
    method: 'put',
    data: data
  })
}

// 删除学生问诊列
export function delSessions(sessionId) {
  return request({
    url: '/system/sessions/' + sessionId,
    method: 'delete'
  })
}
