<template>
  <div>
    <!-- 列表页面 -->
    <div class="container" v-if="!showEdit">
      <div class="header">
        <div class="title">新闻列表</div>
      </div>
      <!-- 表格 -->
      <el-table :data="news" v-loading="loading">
        <el-table-column type="index" :index="indexMethod" label="序号" width="100"></el-table-column>
        <el-table-column prop="title" label="新闻标题"></el-table-column>
        <el-table-column prop="category" label="分类"></el-table-column>
        <el-table-column prop="source" label="来源"></el-table-column>
        <el-table-column prop="content" label="新闻内容"></el-table-column>
        <!-- <el-table-column prop="updater" label="上传人"></el-table-column>
        <el-table-column prop="updaterTime" label="上传时间"></el-table-column> -->
        <el-table-column label="操作" fixed="right" width="275">
          <template #default="scope">
            <el-button plain size="small" type="primary" @click="handleEdit(scope.row.id)">编辑</el-button>
            <el-button plain size="small" type="danger" @click="handleDelete(scope.row.id)"
              v-permission="{ permission: '删除新闻', type: 'disabled' }">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 编辑页面 -->
    <book-modify v-else @editClose="editClose" :editBookId="editBookId"></book-modify>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination :total="totalNum" :background="true" :page-size="pageCount" v-if="refreshPagination"
        :current-page="currentPage" layout="prev, pager, next, jumper" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue'
import { ElMessageBox, ElMessage } from 'element-plus'
import newsModel from '@/model/news'
import BookModify from './news'

export default {
  components: {
    BookModify,
  },
  setup() {
    const news = ref([])
    const editBookId = ref(1)
    const loading = ref(false)
    const showEdit = ref(false)
    const refreshPagination = ref(true)
    // 页数增加的时候，因为缓存的缘故，需要刷新Pagination组件
    // const { loading, totalNum, tableData, pageCount, currentPage, getAdminUsers } = useUserList()

    onMounted(() => {
      getBatchNews()
    })

    const getBatchNews = async () => {
      try {
        loading.value = true
        const response = await newsModel.getBatchNews({
          page: 0,
          count: 10,
        })

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
      editBookId.value = id
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
      getBatchNews()
    }

    const indexMethod = index => index + 1

    return {
      news,
      loading,
      showEdit,
      editClose,
      handleEdit,
      editBookId,
      indexMethod,
      handleDelete,
      refreshPagination,
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

    .title {
      height: 59px;
      line-height: 59px;
      color: $parent-title-color;
      font-size: 16px;
      font-weight: 500;
    }
  }

  .pagination {
    display: flex;
    justify-content: flex-end;
    margin: 20px;
  }
}
</style>
