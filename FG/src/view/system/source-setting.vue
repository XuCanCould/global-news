<template>
  <div class="container">
    <div class="header">
      <div class="title">新闻来源设置</div>
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
            <el-form-item label="来源" prop="category">
              <el-select v-model="news.source" placeholder="请选择">
                <el-option label="中国新闻网" value="0101"></el-option>
                <el-option label="国际新闻" value="国际新闻"></el-option>
                <el-option label="体育新闻" value="体育新闻"></el-option>
                <el-option label="娱乐新闻" value="娱乐新闻"></el-option>
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
export default {
  data() {
    return {
      siteName: '我的管理后台',
      themeColor: '#409EFF'
    }
  },
  methods: {
    saveSettings() {
      this.$message.success('设置保存成功')
    }
  }
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
      color: rgb(57, 99, 188);
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
