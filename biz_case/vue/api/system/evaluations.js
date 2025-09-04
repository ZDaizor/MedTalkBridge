import request from '@/utils/request'

// 查询学生病例得分总列表
export function listEvaluations(query) {
  return request({
    url: '/system/evaluations/list',
    method: 'get',
    params: query
  })
}

// 查询学生病例得分总详细
export function getEvaluations(evaluationId) {
  return request({
    url: '/system/evaluations/' + evaluationId,
    method: 'get'
  })
}

// 新增学生病例得分总
export function addEvaluations(data) {
  return request({
    url: '/system/evaluations',
    method: 'post',
    data: data
  })
}

// 修改学生病例得分总
export function updateEvaluations(data) {
  return request({
    url: '/system/evaluations',
    method: 'put',
    data: data
  })
}

// 删除学生病例得分总
export function delEvaluations(evaluationId) {
  return request({
    url: '/system/evaluations/' + evaluationId,
    method: 'delete'
  })
}
