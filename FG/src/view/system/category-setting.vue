<template>
  <div class="container">
    <div class="header">
      <div class="title">新闻分类设置</div>
      <el-button type="primary" plain @click="handleAdd">添加分类</el-button>
    </div>

    <el-table :data="sourceList" v-loading="loading">
      <el-table-column prop="name" label="分类名称" width="200">
        <template #default="{ row }">
          <el-input v-model="row.name" v-if="row.editing" />
          <span v-else>{{ row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column prop="is_enable" label="是否启用" width="200" align="center">
        <template #default="{ row }">
          <el-switch v-model="row.is_enable" :disabled="!row.editing" />
        </template>
      </el-table-column>

      <el-table-column label="操作" width="200" fixed="right">
        <template #default="{ row }">
          <el-button v-if="!row.editing" type="primary" plain size="small" @click="handleEdit(row)">修改</el-button>

          <el-button v-if="row.editing" type="success" plain size="small" @click="saveEdit(row)">保存</el-button>

          <el-button type="danger" size="small" plain @click="handleDelete(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { ElMessageBox, ElMessage } from 'element-plus'
import settingModel from '@/model/setting' // 假设你已创建来源模型

export default {
  setup() {
    const sourceList = ref([])
    const loading = ref(false)
    const editCache = ref({}) // 用于保存编辑前的数据

    // SOURCE(1, "来源"),
    // CATEGORY(2, "分类"),

    // 初始化数据
    const getSources = async () => {
      try {
        loading.value = true
        const res = await settingModel.getSettings(2)
        sourceList.value = res.map(item => ({
          ...item,
          editing: false // 添加编辑状态标记
        }))
      } finally {
        loading.value = false
      }
    }

    // 添加来源
    const handleAdd = () => {
      sourceList.value.unshift({
        id: -Date.now(), // 临时ID
        sourceName: '新来源',
        enabled: true,
        editing: true,
        type: 2
      })
    }

    // 开始编辑
    const handleEdit = (row) => {
      editCache.value[row.id] = { ...row }
      row.editing = true
    }

    // 保存修改// 保存修改
    const saveEdit = async (row) => {
      try {
        loading.value = true
        if (row.id > 0) { // 已有数据更新
          await settingModel.updateSource(row.id, {
            name: row.name,
            value: row.name,
            is_enable: row.is_enable,
          })
        } else { // 新增数据
          const res = await settingModel.createSource({
            name: row.name,
            value: row.name,
            is_enable: row.is_enable,
            type: 2
          })
          row.id = res.data.id // 更新真实ID
        }
        row.editing = false
        ElMessage.success('保存成功')
        getSources() // 调用此方法刷新页面数据
      } catch (error) {
        Object.assign(row, editCache.value[row.id])
        ElMessage.error('保存失败')
      } finally {
        loading.value = false
      }
    }

    // 删除来源
    const handleDelete = (id) => {
      ElMessageBox.confirm('确定删除该来源？', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await settingModel.deleteSource(id)
        sourceList.value = sourceList.value.filter(item => item.id !== id)
        ElMessage.success('删除成功')
      })
    }

    onMounted(() => {
      getSources()
    })

    return {
      sourceList,
      loading,
      handleAdd,
      handleEdit,
      saveEdit,
      handleDelete
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
    margin-bottom: 20px;

    .title {
      height: 59px;
      line-height: 59px;
      color: $parent-title-color;
      font-size: 16px;
      font-weight: 500;
    }
  }

  .action-buttons {
    display: flex;
    gap: 8px;
  }
}
</style>
