<template>
  <div class="container">
    <el-button @click="$router.go(-1)" class="back-btn">返回列表</el-button>
    <div v-if="loading" class="loading">加载中...</div>

    <div v-else-if="newsDetail !== null" class="detail-content">
      <h1 class="title">{{ newsDetail.title }}</h1>
      <div class="meta">
        <!-- <span class="category">分类：{{ getCategoryName(newsDetail.category) }}</span> -->
        <!-- <span class="source">来源：{{ getSourceName(newsDetail.source) }}</span> -->
        <span class="time">发布时间：{{ 111 }}</span>
      </div>
      <div class="content" v-html="newsDetail.content"></div>
    </div>

    <div v-else class="no-data">未找到该新闻</div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import newsModel from '@/model/news'
import { ElMessage } from 'element-plus'

export default {
  setup() {
    const route = useRoute()
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

    onMounted(() => {
      if (route.params.id) {
        getNewsDetail()
      }
    })

    return {
      newsDetail,
      loading,
      processedContent,
    }
  },
}
</script>

<style lang="scss" scoped>
.container {
  padding: 0 30px;

  .back-btn {
    margin-bottom: 30px;
  }

  .title {
    font-size: 24px;
    color: #333;
    margin-bottom: 20px;
  }

  .meta {
    margin-bottom: 30px;
    color: #666;

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
    line-height: 1.8;
    font-size: 16px;
    color: #444;

    ::v-deep(img) {
      max-width: 100%;
      margin: 20px 0;
      border-radius: 4px;
    }
  }

  .loading,
  .no-data {
    text-align: center;
    padding: 50px;
    color: #999;
  }
}
</style>
