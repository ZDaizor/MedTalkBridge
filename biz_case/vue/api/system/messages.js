import request from '@/utils/request'

// 查询问诊对话记录详情列表
export function listMessages(query) {
  return request({
    url: '/system/messages/list',
    method: 'get',
    params: query
  })
}

// 查询问诊对话记录详情详细
export function getMessages(messagesId) {
  return request({
    url: '/system/messages/' + messagesId,
    method: 'get'
  })
}

// 新增问诊对话记录详情
export function addMessages(data) {
  return request({
    url: '/system/messages',
    method: 'post',
    data: data
  })
}

// 修改问诊对话记录详情
export function updateMessages(data) {
  return request({
    url: '/system/messages',
    method: 'put',
    data: data
  })
}

// 删除问诊对话记录详情
export function delMessages(messagesId) {
  return request({
    url: '/system/messages/' + messagesId,
    method: 'delete'
  })
}
