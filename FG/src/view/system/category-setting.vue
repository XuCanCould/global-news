<template>
  <div class="container">
    <div class="header">
      <div class="title">新闻分类设置</div>
    </div>

    <!-- 示例：基础设置表单 -->
    <el-form label-width="120px">
      <el-form-item label="站点名称">
        <el-input v-model="siteName" />
      </el-form-item>

      <el-form-item label="主题颜色">
        <el-color-picker v-model="themeColor" />
      </el-form-item>

      <el-button type="primary" @click="saveSettings">保存</el-button>
    </el-form>
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

<style scoped>
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
