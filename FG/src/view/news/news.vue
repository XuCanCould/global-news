<template>
  <div class="container">
    <div class="title" v-if="!editBookId">添加新闻{{ editBookId }}</div>
    <div class="title" v-else>
      <span>修改新闻</span> <span class="back" @click="back"> <i class="iconfont icon-fanhui"></i> 返回 </span>
    </div>

    <div class="wrap">
      <el-row>
        <el-col :lg="16" :md="20" :sm="24" :xs="24">
          <el-form :model="book" status-icon ref="form" label-width="100px" @submit.prevent :rules="rules">
            <el-form-item label="新闻标题" prop="title">
              <el-input v-model="book.title" placeholder="请填写新闻标题"></el-input>
            </el-form-item>
            <el-row>
              <el-form-item label="新闻分类" prop="category">
              <el-select v-model="book.category" placeholder="请选择">
                <el-option label="国内新闻" value="国内新闻"></el-option>
                <el-option label="国际新闻" value="国际新闻"></el-option>
                <el-option label="体育新闻" value="体育新闻"></el-option>
                <el-option label="娱乐新闻" value="娱乐新闻"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="来源" prop="category">
              <el-select v-model="book.category" placeholder="请选择">
                <el-option label="国内新闻" value="国内新闻"></el-option>
                <el-option label="国际新闻" value="国际新闻"></el-option>
                <el-option label="体育新闻" value="体育新闻"></el-option>
                <el-option label="娱乐新闻" value="娱乐新闻"></el-option>
              </el-select>
            </el-form-item>
            </el-row>
            <el-form-item label="封面" prop="image">
              <el-input v-model="book.image" placeholder="请填写封面地址"></el-input>
            </el-form-item>
            <el-form-item label="简介" prop="summary">
              <el-input
                type="textarea"
                :autosize="{ minRows: 4, maxRows: 8 }"
                placeholder="请输入简介"
                v-model="book.summary"
              >
              </el-input>
            </el-form-item>
            <div class="editor">
              <vue3-tinymce v-model="state.content" :setting="state.setting" />
            </div>
            <el-form-item class="submit">
              <el-button type="primary" @click="submitForm">保 存</el-button>
              <el-button @click="resetForm">重 置</el-button>
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

export default {
  components: {
    Vue3Tinymce,
  },
  props: {
    editBookId: {
      type: Number,
      default: null,
    },
  },
  setup(props, context) {
    const form = ref(null)
    const loading = ref(false)
    const book = reactive({ title: '', author: '', summary: '', image: '' })
    const state = reactive({
      content: '编辑新闻内容...',
      setting: {
        height: 400,
        language_url: '/langs/zh_CN.js',
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
      if (props.editBookId) {
        getBook()
      }
    })

    const getBook = async () => {
      loading.value = true
      const res = await newsModel.getBook(props.editBookId)
      listAssign(book, res)
      loading.value = false
      listAssign(state, { content: res.content || '' })
    }

    // 重置表单
    const resetForm = () => {
      form.value.resetFields()
    }

    const submitForm = async formName => {
      form.value.validate(async valid => {
        if (valid) {
          let res = {}
          if (props.editBookId) {
            res = await newsModel.editBook(props.editBookId, book)
            context.emit('editClose')
          } else {
            res = await newsModel.createBook(book)
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
      book,
      form,
      rules,
      state,
      resetForm,
      submitForm,
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
    summary: [{ validator: checkInfo, trigger: 'blur', required: true }],
    image: [{ validator: checkInfo, trigger: 'blur', required: true }],
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
