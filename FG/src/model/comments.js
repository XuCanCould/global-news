import request from '@/utils/request'

class Comments {
  /**
   * 获取当前用户的评论列表
   * @returns {Promise} 请求Promise
   */
  async getMyComments() {
    return request.get('v1/comments/myComments')
  }

  /**
   * 获取指定新闻的评论
   * @param {number} newsId - 新闻ID
   * @returns {Promise} 请求Promise
   */
  async getNewsComments(newsId) {
    return request.get(`v1/comments/newsComments/${newsId}`)
  }

  /**
   * 创建评论
   * @param {Object} commentData - 评论数据
   * @returns {Promise} 请求Promise
   */
  async createComment(commentData) {
    return request.post('v1/comments', commentData)
  }

  /**
   * 删除评论
   * @param {number} commentId - 评论ID
   * @returns {Promise} 请求Promise
   */
  async deleteComment(commentId) {
    return request._delete(`v1/comments/${commentId}`)
  }
}

export default new Comments()
