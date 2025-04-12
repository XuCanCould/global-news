/* eslint-disable class-methods-use-this */
import _axios, { get, put, _delete } from '@/lin/plugin/axios'

// 我们通过 class 这样的语法糖使模型这个概念更加具象化，其优点：耦合性低、可维护性。
class News {
  // constructor() {}

  // 类中的方法可以代表一个用户行为
  async createBook(data) {
    return _axios({
      method: 'post',
      url: 'v1/news',
      data,
    })
  }

  // 在这里通过 async await 语法糖让代码同步执行
  // 1. await 一定要搭配 async 来使用
  // 2. await 后面跟的是一个 Promise 对象
  async getNews(id) {
    const res = await get(`v1/news/${id}`)
    return res
  }

  async editBook(id, info) {
    const res = await put(`v1/news/${id}`, info)
    return res
  }

  async deleteBook(id) {
    const res = await _delete(`v1/news/${id}`)
    return res
  }

  async getBatchNews({ count = this.uCount, page = this.uPage, country = this.uCountry }) {
    const res = await get('v1/news', {
      count,
      page,
      country
    })
    return res
  }
}

export default new News()
