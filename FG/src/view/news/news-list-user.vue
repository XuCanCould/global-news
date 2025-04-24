<template>
  <div>
    <!-- 列表页面 -->
    <div class="container" v-if="!showEdit && !showDetail">
      <div class="header">
        <div class="left-part">
          <el-button plain type="primary" class="back-earth-btn" @click="goBackToEarth"> 返回地球页面 </el-button>
        </div>

        <div class="right-part">
          <el-button v-if="!isLoggedIn" class="back-btn" type="primary" plain @click="goToLogin"> 登录 </el-button>
          <user v-else />
          <div class="title">新闻列表</div>
        </div>
      </div>
      <!-- 表格 -->
      <!-- 卡片式布局 -->
      <div class="news-cards">
        <div class="news-card" v-for="(item, index) in news" :key="index">
          <div class="card-title" @click="handleView(item.id)">
            {{ item.title }}
          </div>
          <div class="card-content" v-html="item.content"></div>
        </div>
      </div>
    </div>

    <!-- 详情页面 -->
    <div v-if="showDetail">
      <book-modify @editClose="editClose" :editNewsId="editNewsId" mode="view"></book-modify>
    </div>

    <!-- 编辑页面 -->
    <div v-if="showEdit">
      <book-modify @editClose="editClose" :editNewsId="editNewsId" mode="edit"></book-modify>
    </div>

    <!-- 分页 -->
    <div class="pagination">
      <el-pagination
        :total="totalNum"
        :background="true"
        :page-size="pageCount"
        v-if="refreshPagination"
        :current-page="currentPage"
        layout="prev, pager, next, jumper"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { onMounted, ref, computed } from 'vue'
import { ElMessageBox, ElMessage } from 'element-plus'
import newsModel from '@/model/news'
import { useRoute, useRouter } from 'vue-router'
import User from '@/component/layout/user'
import BookModify from './news'
import store from '../../store'

export default {
  components: {
    BookModify,
    User,
  },
  setup(props) {
    const route = useRoute() // 获取 Vue Router 实例
    const router = useRouter()
    const country = ref(route.params.country || null)

    const comments = ref()
    const news = ref([])
    const editNewsId = ref(1)
    const loading = ref(false)
    const showEdit = ref(false)
    const showDetail = ref(false)
    const refreshPagination = ref(true)
    const totalNum = ref(0)
    const pageCount = ref(10)
    const currentPage = ref(1)
    const isLoggedIn = computed(() => store.state.loggedIn)

    // 页数增加的时候，因为缓存的缘故，需要刷新Pagination组件
    // const { loading, totalNum, tableData, pageCount, currentPage, getAdminUsers } = useUserList()

    onMounted(() => {
      getBatchNews()
    })

    const goToLogin = () => {
      router.push('/login') // 跳转到登录路由
    }

    const goBackToEarth = () => {
      router.push({ path: '/earth' })
    }

    const getBatchNews = async () => {
      console.log('打印country值', country._value)
      try {
        loading.value = true
        const response = await newsModel.getBatchNews({
          page: 0,
          count: 10,
          country: country._value,
        })

        // 🚫 如果没有数据，提示并返回
        if (response.total === 0) {
          ElMessage.warning('未找到当前国家信息')
          router.push({ path: '/earth' })
          return
        }

        // 处理 content 字段
        response.items.forEach(item => {
          item.content = item.content
            .replace(/<[^>]*>/g, '') // 去除 HTML 标签
            .replace(/\s+/g, ' ') // 将多个空格和换行符替换为单个空格
            .trim() // 去除首尾空格
          item.content += '……'
        })

        news.value = response.items
        loading.value = false
      } catch (error) {
        loading.value = false
        if (error.code === 10020) {
          news.value = []
        }
      }
    }

    const handleEdit = id => {
      showEdit.value = true
      editNewsId.value = id
    }

    const handleView = id => {
      if (!country.value) {
        ElMessage.warning('未获取到国家信息，无法跳转')
        return
      }

      router.push({
        name: 'NewsUserView',
        params: {
          country: country.value,
          id: id,
        },
      })
    }

    const handleDelete = id => {
      ElMessageBox.confirm('此操作将删除该新闻, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(async () => {
        const res = await newsModel.deleteBook(id)
        if (res.code < window.MAX_SUCCESS_CODE) {
          getBatchNews()
          ElMessage.success(`${res.message}`)
        }
      })
    }

    const editClose = () => {
      showEdit.value = false
      showDetail.value = false
      getBatchNews()
    }

    const indexMethod = index => index + 1

    const handleCurrentChange = page => {
      currentPage.value = page
      getBatchNews()
    }
    return {
      news,
      loading,
      showEdit,
      showDetail,
      editClose,
      handleEdit,
      handleView,
      editNewsId,
      indexMethod,
      handleDelete,
      refreshPagination,
      totalNum,
      pageCount,
      currentPage,
      handleCurrentChange,
      goBackToEarth,
      goToLogin,
      isLoggedIn,
    }
  },
}
</script>

<style lang="scss" scoped>
.container {
  padding: 0 30px;

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;

    .left-part {
      .back-earth-btn {
        margin: 20px 0;
      }
    }

    .right-part {
      display: flex;
      align-items: center;
      gap: 20px;

      .title {
        font-size: 20px;
        margin: 0;
      }
    }
  }

  // 原有其他样式保持不变
  .news-cards {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
    gap: 24px;
    margin-top: 20px;

    .news-card {
      border: 1px solid #ebeef5;
      border-radius: 8px;
      padding: 20px;
      transition: all 0.3s ease;
      background: #fff;
      cursor: pointer;
      box-shadow: 0 2px 6px rgba(0, 0, 0, 0.04);

      &:hover {
        box-shadow: 0 6px 20px rgba(0, 0, 0, 0.08);
        transform: translateY(-2px);
      }

      .card-title {
        font-size: 20px;
        font-weight: 600;
        color: #303133;
        margin-bottom: 12px;
        line-height: 1.4;
      }

      .card-content {
        color: #606266;
        font-size: 14px;
        line-height: 1.8;
        text-align: justify;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
      }

      .card-actions {
        margin-top: 10px;
        display: flex;
        justify-content: flex-end;
        gap: 8px;
      }
    }
  }
  .pagination {
    display: flex;
    justify-content: center;
    margin: 30px 0;
  }
  .back-earth-btn {
    margin: 20px 0;
  }
}
</style>
