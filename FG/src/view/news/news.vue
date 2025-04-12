<template>
  <div class="container">
    <div class="title">
      <span v-if="!editNewsId">添加新闻</span>
      <span v-else>修改新闻</span>
      <span class="back" @click="back"> <i class="iconfont icon-fanhui"></i> 返回 </span>
    </div>

    <div class="wrap">
      <el-row justify="center">
        <el-col :lg="16" :md="20" :sm="24" :xs="24">
          <el-form ref="form" :model="news" :rules="rules" label-width="100px" status-icon @submit.prevent>
            <el-form-item label="新闻标题" prop="title">
              <el-input v-model="news.title" placeholder="请填写新闻标题" />
            </el-form-item>

            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="新闻分类" prop="category">
                  <el-select v-model="news.category" placeholder="请选择">
                    <el-option v-for="item in categoryList" :key="item.id" :label="item.name" :value="item.name" />
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="国家或地区" prop="country">
                  <el-select v-model="news.country" placeholder="请选择">
                    <el-option v-for="item in countryList" :key="item.id" :label="item.name" :value="item.value" />
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="来源" prop="source">
                  <el-select v-model="news.source" placeholder="请选择">
                    <el-option v-for="item in sourceList" :key="item.id" :label="item.name" :value="item.name" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <div class="editor">
              <vue3-tinymce v-model="news.content" :setting="state.setting" />
            </div>

            <el-form-item class="submit">
              <el-button @click="resetForm">暂存</el-button>
              <el-button @click="resetForm">重 置</el-button>
              <el-button type="primary" @click="submitForm">保 存</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import { reactive, ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import newsModel from '@/model/news'
import Vue3Tinymce from '@jsdawn/vue3-tinymce'
import settingModel from '@/model/setting'

export default {
  components: {
    Vue3Tinymce,
  },
  props: {
    editNewsId: Number,
  },
  setup(props, { emit }) {
    const form = ref(null)
    const loading = ref(false)

    const news = reactive({
      title: '',
      category: '',
      country: '',
      source: '',
      content: '',
    })

    const sourceList = ref([])
    const countryList = ref([])
    const categoryList = ref([])

    const state = reactive({
      setting: {
        height: 400,
        language_url: '/vue3-tinymce/langs/zh_CN.js',
        language: 'zh_CN',
      },
    })

    const { rules } = getRules()

    const getList = async (type, targetList) => {
      try {
        loading.value = true
        const res = await settingModel.getSettings(type)
        targetList.value = res
      } catch (err) {
        console.error(`获取设置（类型 ${type}）失败：`, err.message)
      } finally {
        loading.value = false
      }
    }

    const getNews = async () => {
      try {
        loading.value = true
        const res = await newsModel.getNews(props.editNewsId)
        Object.assign(news, res)
      } finally {
        loading.value = false
      }
    }

    const resetForm = () => {
      Object.assign(news, {
        title: '',
        category: '',
        country: '',
        source: '',
        content: '',
      })
      form.value?.resetFields()
    }

    const submitForm = () => {
      form.value.validate(async valid => {
        if (!valid) {
          ElMessage.error('请将信息填写完整')
          return
        }

        let res = props.editNewsId ? await newsModel.editBook(props.editNewsId, news) : await newsModel.createBook(news)

        if (res.code < window.MAX_SUCCESS_CODE) {
          ElMessage.success(res.message)
          if (props.editNewsId) emit('editClose')
          else resetForm()
        }
      })
    }

    const back = () => emit('editClose')

    onMounted(() => {
      getList(1, sourceList)
      getList(2, categoryList)
      getList(3, countryList)
      if (props.editNewsId) getNews()
    })

    return {
      form,
      news,
      state,
      rules,
      sourceList,
      countryList,
      categoryList,
      resetForm,
      submitForm,
      back,
    }
  },
}

function getRules() {
  const required = msg => [{ required: true, message: msg, trigger: 'blur' }]
  return {
    rules: {
      title: required('请输入标题'),
      category: required('请选择分类'),
      country: required('请选择国家/地区'),
      source: required('请选择来源'),
      content: required('请输入内容'),
    },
  }
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

  .editor {
    margin-top: 20px;
    padding-left: 20px;
  }

  .submit {
    float: left;
  }
}
</style>
