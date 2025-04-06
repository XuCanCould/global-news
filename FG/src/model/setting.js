import { get, post, put, del } from '@/lin/plugin/axios'

class Source {
  // 获取来源列表
  async getSettings(type) {
    return get(`/v1/setting/${type}`)
  }

  // 创建来源
  async createSource(data) {
    return post('/v1/setting', data)
  }

  // 更新来源
  async updateSource(id, data) {
    return put(`/v1/setting/${id}`, data)
  }

  // 删除来源
  async deleteSource(id) {
    return del(`/v1/setting/${id}`)
  }
}

export default new Source()
