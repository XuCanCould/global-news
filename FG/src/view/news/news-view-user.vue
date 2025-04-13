<template>
  <div class="container">
    <div class="header">
      <el-button class="back-btn" type="primary" plain @click="goToLogin">登录</el-button>
      <el-button @click="$router.go(-1)" class="back-btn" type="primary" plain> 返回列表 </el-button>
    </div>

    <div v-if="loading" class="loading">加载中...</div>

    <div v-else-if="newsDetail !== null" class="detail-content">
      <h1 class="title">{{ newsDetail.title }}</h1>
      <div class="meta">
        <!-- <span class="category">分类：{{ getCategoryName(newsDetail.category) }}</span> -->
        <!-- <span class="source">来源：{{ getSourceName(newsDetail.source) }}</span> -->
        <!-- <span class="time">发布时间：{{ 111 }}</span> -->
      </div>
      <el-divider></el-divider>
      <div class="content" v-html="newsDetail.content"></div>
    </div>

    <div v-else class="no-data">未找到该新闻</div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import newsModel from '@/model/news'
import { ElMessage } from 'element-plus'

export default {
  setup() {
    const route = useRoute()
    const router = useRouter()
    const newsDetail = ref(null)
    const loading = ref(true)
    const processedContent = ref('')

    const getNewsDetail = async () => {
      try {
        loading.value = true
        const res = await newsModel.getNews(route.params.id)
        if (res) {
          newsDetail.value = res
        } else {
          ElMessage.error('获取详情失败')
        }
      } catch (error) {
        console.error('请求失败:', error)
        ElMessage.error('网络请求异常')
      } finally {
        loading.value = false
      }
    }

    const goToLogin = () => {
      router.push('/login')
    }

    onMounted(() => {
      if (route.params.id) {
        getNewsDetail()
      }
    })

    return {
      newsDetail,
      loading,
      processedContent,
      goToLogin,
    }
  },
}
</script>

<style lang="scss" scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 40px 20px;

  .back-btn {
    margin-bottom: 30px;
  }

  .title {
    font-size: 28px;
    font-weight: bold;
    color: #2c3e50;
    margin-bottom: 20px;
    line-height: 1.4;
  }

  .meta {
    font-size: 14px;
    color: #999;
    margin-bottom: 20px;

    span {
      margin-right: 20px;

      &::before {
        content: '•';
        margin-right: 5px;
        color: #409eff;
      }
    }
  }
  .content {
    font-size: 16px;
    line-height: 2;
    color: #333;
    word-break: break-word;
    text-align: justify;

    // 每段落缩进 2 个字符（中文推荐）
    ::v-deep p {
      text-indent: 2em;
      margin-bottom: 1em;
    }

    // 图片样式优化
    ::v-deep img {
      max-width: 100%;
      margin: 20px 0;
      border-radius: 6px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
      display: block;
      margin-left: auto;
      margin-right: auto;
    }

    // 标题优化（如果内容中有 H2、H3）
    ::v-deep h2,
    ::v-deep h3 {
      font-weight: bold;
      margin: 1.2em 0 0.6em;
      line-height: 1.5;
      color: #222;
    }

    // 列表优化
    ::v-deep ul {
      padding-left: 2em;
      margin-bottom: 1em;
    }

    ::v-deep li {
      line-height: 2;
    }
  }
}
</style>
