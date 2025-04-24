<template>
  <div class="container">
    <div class="header">
      <el-button @click="$router.go(-1)" class="back-btn" type="primary" plain> 返回列表 </el-button>
      <el-button v-if="!isLoggedIn" class="back-btn" type="primary" plain @click="goToLogin">登录</el-button>
    </div>

    <div v-if="loading" class="loading">加载中...</div>

    <div v-else-if="newsDetail !== null" class="detail-content">
      <h1 class="title">{{ newsDetail.title }}</h1>
      <div class="meta">
        <span class="category">分类：{{ newsDetail.category }}</span>
        <span class="source">来源：{{ newsDetail.source }}</span>
        <span class="updater">发布人：{{ newsDetail.updater }}</span>
        <span class="views">浏览量：{{ newsDetail.views }}</span>
        <span class="like-count">点赞：{{ newsDetail.like_count }}</span>
        <span class="comments">评论：{{ newsDetail.comments }}</span>
      </div>
      <el-divider></el-divider>
      <div class="content" v-html="newsDetail.content"></div>
    </div>

    <div v-else class="no-data">未找到该新闻</div>
    <br />
    <!-- 评论区域 -->
    <el-divider>评论</el-divider>

    <!-- 已有评论 -->
    <div v-if="comments.length > 0">
      <div v-for="comment in comments" :key="comment.id" class="comment">
        <div class="comment-header">
          <div>
            <strong>{{ comment.nickname }}</strong>
            <small style="margin-left: 10px">{{ formatTime(comment.update_time) }}</small>
          </div>
        </div>

        <div class="comment-content">
          {{ comment.content }}
        </div>

        <div class="card-actions" v-if="isLoggedIn">
          <el-button type="danger" plain size="small" @click.stop="handleDelete(comment.id)">删除</el-button>
        </div>
      </div>
    </div>

    <div v-else>暂无评论</div>
    <br /><br /><br /><br />
    <!-- 发布评论 -->
    <div class="comment-box" v-if="isLoggedIn">
      <el-input type="textarea" v-model="newComment" placeholder="说点什么吧..." rows="3" />
      <el-button type="primary" style="margin-top: 10px" @click="submitComment"> 提交评论 </el-button>
    </div>
    <div v-else class="comment-box">
      <el-alert title="请登录后发表评论" type="info" show-icon />
    </div>
  </div>
</template>

<script>
import { ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import newsModel from '@/model/news'
import commentsModel from '@/model/comments' // 引入评论模块
import { ElMessageBox, ElMessage } from 'element-plus'
import store from '../../store'

export default {
  setup() {
    const route = useRoute()
    const router = useRouter()
    const newsDetail = ref(null)
    const loading = ref(true)
    const comments = ref([]) // 存储评论
    const isLoggedIn = computed(() => store.state.loggedIn)
    const newComment = ref('')

    // 获取新闻详情
    const getNewsDetail = async () => {
      try {
        loading.value = true
        const res = await newsModel.getNews(route.params.id)
        if (res) {
          newsDetail.value = res

          // 当评论数不为0时，查询评论
          if (res.comments > 0) {
            getComments(res.id)
          }
          console.log('登录信息:', isLoggedIn.value)
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

    // 获取评论
    const getComments = async newsId => {
      try {
        const res = await commentsModel.getNewsComments(newsId)
        comments.value = res.data || []
      } catch (error) {
        console.error('获取评论失败:', error)
        ElMessage.error('获取评论失败')
      }
    }
    const submitComment = async () => {
      if (!newComment.value.trim()) {
        ElMessage.warning('评论内容不能为空')
        return
      }
      console.log('提交评论:', newComment)

      try {
        const res = await commentsModel.createComment({
          news_id: newsDetail.value.id,
          content: newComment._value,
        })
        if (res.data.code == 16) {
          ElMessage.success('评论成功！')
          newComment.value = '' // 清空输入框
          getComments(newsDetail.value.id) // 重新获取评论列表
        } else {
          ElMessage.error(res.message || '评论失败')
        }
      } catch (error) {
        console.error('提交评论失败:', error)
        ElMessage.error('提交失败，请稍后重试')
      }
    }

    // 返回登录页
    const goToLogin = () => {
      router.push('/login')
    }

    onMounted(() => {
      if (route.params.id) {
        getNewsDetail()
      }
    })
    const formatTime = rawTime => {
      const [datePart, timePart] = rawTime.split('T')
      const time = timePart.split('.')[0] // 去掉毫秒和时区
      return datePart.replace(/-/g, '/') + ' ' + time
    }

    const handleDelete = id => {
      ElMessageBox.confirm('此操作将删除该评论, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      })
        .then(async () => {
          try {
            const res = await commentsModel.deleteComment(id)
            if (res.code < window.MAX_SUCCESS_CODE) {
              ElMessage.success(res.message)
              getComments(newsDetail.value.id)
            } else {
              ElMessage.error(res.message || '删除失败')
            }
          } catch (err) {
            ElMessage.error('请求出错，删除失败')
          }
        })
        .catch(() => {
          // 用户取消删除
        })
    }

    return {
      newsDetail,
      loading,
      comments,
      goToLogin,
      isLoggedIn,
      newComment,
      submitComment,
      formatTime,
      handleDelete,
    }
  },
}
</script>

<style lang="scss" scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 40px 20px;

  .header {
    display: flex;
    justify-content: space-between; /* 将按钮分布在容器的两端 */
    align-items: center; /* 垂直居中对齐 */
    margin-bottom: 30px;
  }
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
        content: '|';
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

    ::v-deep p {
      text-indent: 2em;
      margin-bottom: 1em;
    }

    ::v-deep img {
      max-width: 100%;
      margin: 20px 0;
      border-radius: 6px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
      display: block;
      margin-left: auto;
      margin-right: auto;
    }

    ::v-deep h2,
    ::v-deep h3 {
      font-weight: bold;
      margin: 1.2em 0 0.6em;
      line-height: 1.5;
      color: #222;
    }

    ::v-deep ul {
      padding-left: 2em;
      margin-bottom: 1em;
    }

    ::v-deep li {
      line-height: 2;
    }
  }
  .comment {
    position: relative;
    padding: 15px 20px 20px;
    border: 1px solid #e0e0e0;
    border-radius: 10px;
    margin-bottom: 15px;
    background-color: #ffffff;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.03);
    transition: box-shadow 0.2s ease;

    &:hover {
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
    }

    .comment-header {
      display: flex;
      justify-content: space-between;
      align-items: center;

      strong {
        font-size: 15px;
        color: #409eff;
      }

      small {
        color: #999;
        font-size: 12px;
      }
    }

    .comment-content {
      margin-top: 8px;
      color: #333;
      font-size: 14px;
    }

    .card-actions {
      display: flex;
      justify-content: flex-end;
      margin-top: 10px;
    }
  }
}
</style>
