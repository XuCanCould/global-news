<template>
  <div class="container">
    <div class="title" v-if="!editNewsId">添加新闻{{ editNewsId }}</div>
    <div class="title" v-else>
      <span>修改新闻</span> <span class="back" @click="back"> <i class="iconfont icon-fanhui"></i> 返回 </span>
    </div>

    <div class="wrap">
      <el-row>
        <el-col :lg="16" :md="20" :sm="24" :xs="24">
          <el-form :model="news" status-icon ref="form" label-width="100px" @submit.prevent :rules="rules">
            <el-form-item label="新闻标题" prop="title">
              <el-input v-model="news.title" placeholder="请填写新闻标题"></el-input>
            </el-form-item>
            <el-row>
              <el-form-item label="新闻分类" prop="category">
                <el-select v-model="news.category" placeholder="请选择">
                  <el-option label="国内新闻" value="01"></el-option>
                  <el-option label="国际新闻" value="国际新闻"></el-option>
                  <el-option label="体育新闻" value="体育新闻"></el-option>
                  <el-option label="娱乐新闻" value="娱乐新闻"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="来源" prop="source">
                <el-select v-model="news.source" placeholder="请选择">
                  <el-option v-for="item in sourceList" :key="item.id" :label="item.name" :value="item.id"></el-option>
                </el-select>
              </el-form-item>
            </el-row>
            <div class="editor">
              <vue3-tinymce v-model="news.content" :setting="state.setting" />
            </div>
            <br>
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
    editNewsId: {
      type: Number,
      default: null,
    },
  },
  setup(props, context) {
    const form = ref(null)
    const loading = ref(false)
    const news = reactive({ title: '', category: '', source: '', content: '' })
    const sourceList = ref([]) // 来源列表
    const state = reactive({
      setting: {
        height: 400,
        language_url: '/vue3-tinymce/langs/zh_CN.js',
        language: 'zh_CN',
      },
    })

    const listAssign = (a, b) => Object.keys(a).forEach(key => {
      a[key] = b[key] || a[key]
    })

    /**
     * 表单规则验证
     */
    const { rules } = getRules()

    onMounted(() => {
      getSources() // 页面加载时获取来源列表
      if (props.editNewsId) {
        getNews()
      }
    })

    const getNews = async () => {
      loading.value = true
      const res = await newsModel.getNews(props.editNewsId)
      listAssign(news, res)
      loading.value = false
      listAssign(state, { content: res.content || '' })
    }
    // 获取来源列表
    const getSources = async () => {
      try {
        loading.value = true
        const res = await settingModel.getSettings(1) // 假设 1 表示获取来源
        sourceList.value = res.map(item => ({
          ...item,
          editing: false // 添加编辑状态标记（可选）
        }))
      } catch (error) {
        console.error('Error fetching sources:', error.message)
      } finally {
        loading.value = false
      }
    }

    // 重置表单
    const resetForm = () => {
      news.source = null
      news.content = null
      form.value.resetFields()
    }

    const submitForm = async formName => {
      form.value.validate(async valid => {
        if (valid) {
          let res = {}
          if (props.editNewsId) {
            res = await newsModel.editBook(props.editNewsId, news)
            context.emit('editClose')
          } else {
            res = await newsModel.createBook(news)
            resetForm(formName)
          }
          if (res.code < window.MAX_SUCCESS_CODE) {
            ElMessage.success(`${res.message}`)
          }
        } else {
          console.error('error submit!!')
          ElMessage.error('请将信息填写完整')
        }
      })
    }

    const back = () => {
      context.emit('editClose')
    }

    return {
      back,
      news,
      form,
      rules,
      state,
      resetForm,
      submitForm,
      sourceList,
    }
  },
}

/**
 * 表单验证规则
 */
function getRules() {
  /**
   * 验证回调函数
   */
  const checkInfo = (rule, value, callback) => {
    if (!value) {
      callback(new Error('信息不能为空'))
    }
    callback()
  }
  const rules = {
    title: [{ validator: checkInfo, trigger: 'blur', required: true }],
    author: [{ validator: checkInfo, trigger: 'blur', required: true }],
    content: [{ validator: checkInfo, trigger: 'blur', required: true }],
    // image: [{ validator: checkInfo, trigger: 'blur', required: true }],
  }
  return { rules }
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
