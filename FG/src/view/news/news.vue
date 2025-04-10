<template>
  <div class="container">
    <div class="title">
      <span>新闻详情</span>
      <span class="back" @click="goBack"> <i class="iconfont icon-fanhui"></i> 返回 </span>
    </div>

    <div class="wrap">
      <el-row>
        <el-col :lg="16" :md="20" :sm="24" :xs="24">
          <el-form :model="newsDetail" label-width="100px">
            <el-form-item label="新闻标题">
              <div class="detail-content">{{ newsDetail.title }}</div>
            </el-form-item>
            <el-row>
              <el-form-item label="新闻分类">
                <div class="detail-content">{{ getCategoryName(newsDetail.category) }}</div>
              </el-form-item>
              <el-form-item label="来源">
                <div class="detail-content">{{ getSourceName(newsDetail.source) }}</div>
              </el-form-item>
              <el-form-item label="关联国家">
                <div class="detail-content">{{ getCountryName(newsDetail.country) }}</div>
              </el-form-item>
            </el-row>
            <el-form-item label="新闻内容">
              <div class="detail-content" v-html="newsDetail.content"></div>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import newsModel from '@/model/news'
import settingModel from '@/model/setting'
import { ElMessage } from 'element-plus'

export default {
  setup() {
    const route = useRoute()
    const router = useRouter()
    const newsDetail = ref(null)
    const sourceList = ref([])
    const countryList = ref([])

    // 获取分类名称
    const getCategoryName = (categoryCode) => {
      const categoryMap = {
        '01': '国内新闻',
        '02': '国际新闻',
        '03': '体育新闻',
        '04': '娱乐新闻'
      }
      return categoryMap[categoryCode] || categoryCode
    }

    // 获取来源名称
    const getSourceName = (sourceId) => {
      const source = sourceList.value.find(item => item.id === sourceId)
      return source ? source.name : sourceId
    }

    // 获取国家名称
    const getCountryName = (countryId) => {
      const country = countryList.value.find(item => item.id === countryId)
      return country ? country.name : countryId
    }

    const getNewsDetail = async () => {
      try {
        const res = await newsModel.getNews(route.params.id)
        if (res.code === 200) {
          newsDetail.value = res.data
        } else {
          ElMessage.error('获取详情失败')
        }
      } catch (error) {
        console.error('请求失败:', error)
        ElMessage.error('网络请求异常')
      }
    }

    // 获取来源列表
    const getSources = async () => {
      try {
        const res = await settingModel.getSettings(1) // 假设 1 表示获取来源
        sourceList.value = res
      } catch (error) {
        console.error('Error fetching sources:', error.message)
      }
    }

    // 获取国家列表
    const getCountrys = async () => {
      try {
        const res = await settingModel.getSettings(3) // 假设 3 表示获取国家
        countryList.value = res
      } catch (error) {
        console.error('Error fetching countries:', error.message)
      }
    }

    const goBack = () => {
      router.go(-1)
    }

    onMounted(() => {
      getNewsDetail()
      getSources()
      getCountrys()
    })

    return {
      newsDetail,
      getCategoryName,
      getSourceName,
      getCountryName,
      goBack,
      sourceList,
      countryList
    }
  },
}
</script>

<style lang="scss" scoped>
.container {
  .title {
    height: 59px;
    line-height: 59px;
    color: $parent-title-color;
    font-size: 16px;
    font-weight: 500;
    text-indent: 40px;
    border-bottom: 1px solid #dae1ec;

    .back {
      float: right;
      margin-right: 40px;
      cursor: pointer;
    }
  }

  .wrap {
    padding: 20px;
  }

  .detail-content {
    border: 1px solid #ebeef5;
    border-radius: 4px;
    padding: 15px;
    background-color: #fff;
  }
}
</style>
