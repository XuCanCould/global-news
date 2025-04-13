import axios from 'axios'
import { getToken } from '@/lin/util/token'

const request = axios.create({
  baseURL: 'http://localhost:5000', // 或你的后端 API 基础路径
  timeout: 5000,

})

// 请求拦截器：统一加 token
request.interceptors.request.use(
  config => {
    const token = getToken('access_token')
    if (token) {
      config.headers['Authorization'] = `${token}`
    }
    return config
  },
  error => Promise.reject(error)
)

export default request
