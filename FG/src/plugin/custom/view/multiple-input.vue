<template>
  <div class="lin-container">
    <div class="lin-title">多重输入</div>
    <div class="lin-wrap-ui">
      <el-card class="box-card" style="margin-bottom: 50px; padding-bottom: 20px">
        <div class="block-box">
          <i class="iconfont icon-jia plus" v-if="!list.length" @click="addContent"></i>
          <el-row class="input-row" v-for="(item, index) in list" :key="index">
            <el-input v-model="item.text" placeholder="请输入内容" class="input-detail"></el-input>
            <div class="function">
              <i class="iconfont icon-jian1 minus" @click="removeContent(index)"></i>
              <i class="iconfont icon-jia plus" v-if="index === list.length - 1" @click="addContent"></i>
            </div>
          </el-row>
        </div>
        <el-collapse>
          <el-collapse-item title="查看代码" name="2">
            <div style="white-space: pre-wrap">{{ base }}</div>
          </el-collapse-item>
        </el-collapse>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      /* eslint-disable */
      base: `
        <div class="block-box">
          <i class="iconfont icon-jia plus" v-if="!list.length" @click="addContent"></i>
          <el-row class="input-row" v-for="(item,index) in list" :key="index">
            <el-input
              v-model="item.text"
              placeholder="请输入内容"
              class="input-detail"
              ></el-input>
            <div class="function">
              <i class="iconfont icon-jian1 minus" @click="removeContent(index)"></i>
              <i class="iconfont icon-jia plus" v-if="index === list.length-1" @click="addContent"></i>
            </div>
          </el-row>
        </div>
        <script>
        export default {
          methods: {
            addContent() {
              this.list.push({
                text: '',
                type: 'plus',
              })
            },
            removeContent(index) {
              this.list.splice(index, 1)
            }
          },
        }
        <\/script>`,
      list: [],
    }
  },
  methods: {
    addContent() {
      this.list.push({
        text: '',
        type: 'plus',
      })
    },
    removeContent(index) {
      this.list.splice(index, 1)
    },
  },
}
</script>

<style lang="scss" scoped>
.lin-wrap-ui :v-deep(.el-card__body) {
  padding-top: 30px;
  padding-bottom: 0px;
}
.lin-wrap-ui :v-deep(.el-collapse) {
  border-top: none;
  border-bottom: none;
  cursor: pointer;
  .el-collapse-item__header {
    border-bottom: none;
    color: #2f4e8c;
    padding-left: calc(100% - 77px);
  }

  .el-collapse-item__content {
    background: #e9f0f8;
    color: #2f4e8c;
    border-radius: 4px;
    padding: 0px 20px 20px 20px;
    margin-bottom: 20px;
  }
}
.lin-wrap-ui {
  padding: 30px 40px;
}

.label-title {
  margin-bottom: 10px;
}

.block-box {
  background: #f7f7f7;
  padding: 20px;
  .plus {
    cursor: pointer;
    font-size: 24px;
    font-weight: 500;
    color: #3765b6;
  }

  .input-row {
    display: flex;
    justify-content: space-between;
    width: 300px;
    margin-bottom: 20px;
    align-items: center;

    .input-detail {
      width: 200px;
    }

    .function {
      display: flex;
      justify-content: space-between;
      width: 60px;
      height: 36px;
      line-height: 36px;
      cursor: pointer;

      .iconfont {
        font-size: 24px;
        font-weight: 500;

        &.plus {
          color: #3765b6;
        }

        &.minus {
          font-size: 26px;
          color: #c7485b;
        }
      }
    }
  }
}
</style>
