<template>
  <div class="container">
    <!-- <div class="lin-info">
      <div class="lin-info-left">
        <div class="welcome">
          <img src="../../assets/image/about/welcome.png" class="welcome-title" alt="" />
          <div class="subtitle">
            <div class="guide">感谢林间有风开源的代码，这是他们的官方网站</div>
            <div class="link"><a href="https://www.talelin.com" target="_blank">https://talelin.com</a></div>
          </div>
        </div>
        <img class="welcome-bg" src="../../assets/image/about/header-bg.png" alt="" />
      </div>
      <div class="lin-info-right">
        <div class="team-detail">
          <div class="team-box">
            <div class="team-title">产品团队</div>
            <ul class="team-ul">
              <li>
                <span class="shadow-box"> <i class="team-shadow"></i> </span> <span class="team-role">策划</span>
                <span class="team-name">七月</span>
              </li>
              <li>
                <span class="shadow-box"> <i class="team-shadow"></i> </span> <span class="team-role">研发</span>
                <span class="team-name">
                  <ul v-if="!showTeam">
                    <li>Pedro</li>
                    <li>一飞</li>
                    <li>凉面</li>
                    <li>圈圈</li>
                    <li>家乐</li>
                    <li>Jocky</li>
                    <li>流乔</li>
                    <li>西麦</li>
                  </ul>
                  <ul v-else>
                    <li>林间有风 CMS 组</li>
                  </ul>
                </span>
              </li>
              <li>
                <span class="shadow-box"> <i class="team-shadow"></i> </span> <span class="team-role">设计</span>
                <span class="team-name">瓜瓜</span>
              </li>
            </ul>
          </div>
          <div class="team-icon"><img src="../../assets/image/about/qrcode.jpg" alt="" /></div>
          <p class="team-label">林间有风公众号</p>
        </div>
      </div>
    </div> -->
    <!-- <div class="quantity-statistics">
      <div class="quantity-item">
        <div class="quantity-detail">
          <div class="quantity-detail-box">
            <div class="quantity-title">总访问量</div>
            <div class="quantity-border-line"></div>
            <div class="quantity">11,590</div>
          </div>
        </div>
        <div class="quantity-icon"><img src="../../assets/image/about/icon.png" alt="" /></div>
      </div>
      <div class="quantity-item">
        <div class="quantity-detail">
          <div class="quantity-detail-box">
            <div class="quantity-title">总用户数</div>
            <div class="quantity-border-line"></div>
            <div class="quantity">51,862</div>
          </div>
        </div>
        <div class="quantity-icon"><img src="../../assets/image/about/icon.png" alt="" /></div>
      </div>
      <div class="quantity-item">
        <div class="quantity-detail">
          <div class="quantity-detail-box">
            <div class="quantity-title">新增访问量 (月)</div>
            <div class="quantity-border-line"></div>
            <div class="quantity">1,862</div>
          </div>
        </div>
        <div class="quantity-icon"><img src="../../assets/image/about/icon.png" alt="" /></div>
      </div>
      <div class="quantity-item">
        <div class="quantity-detail">
          <div class="quantity-detail-box">
            <div class="quantity-title">新增用户数</div>
            <div class="quantity-border-line"></div>
            <div class="quantity">1,323</div>
          </div>
        </div>
        <div class="quantity-icon"><img src="../../assets/image/about/icon.png" alt="" /></div>
      </div>
    </div> -->
    <div class="information">
      <div class="personal">
        <div class="personal-title">个人信息</div>
        <img src="../../assets/image/about/avatar.png" class="personal-avatar" />
        <div class="personal-influence">
          <div class="personal-influence-item">
            <div class="personal-influence-num color1">{{ basicData.viewsCount }}</div>
            <div class="personal-influece-label">总访问量</div>
          </div>
          <div class="personal-influence-item">
            <div class="personal-influence-num color2">{{ basicData.userCount }}</div>
            <div class="personal-influece-label">用户量</div>
          </div>
          <div class="personal-influence-item">
            <div class="personal-influence-num color3">{{ basicData.newsCount }}</div>
            <div class="personal-influece-label">新闻数</div>
          </div>
        </div>
        <el-tabs v-model="activeName" class="personal-tabs">
          <el-tab-pane label="最新" name="first">
            <div
              class="latest-news-item"
              v-for="item in latestNews"
              :key="item.id"
              @click="handleArticle(`/news/detail/${item.id}`)"
            >
              {{ item.title }}
            </div>
          </el-tab-pane>

          <!-- <el-tab-pane label="最热作品" name="second">
            <div class="content">为什么程序员们愿意在GitHub上开源...</div>
          </el-tab-pane> -->
        </el-tabs>
      </div>
      <div class="article">
        <div class="article-title">热门文章</div>
        <div class="article-list">
          <div
            class="article-item"
            v-for="item in hotNews"
            :key="item.id"
            @click="handleArticle(item.link || item.source || '#')"
          >
            <img class="article-thumb" src="../../assets/image/about/open-source.jpg" alt="" />
            <div class="article-detail">
              <p class="article-detail-title">{{ item.title }}</p>
              <!-- <div class="article-detail-content" v-html="item.content"></div> -->
              <div class="article-detail-content">
                {{ limitContent(item.content) }}
              </div>
              <div class="article-tool">
                <div class="pubdate">{{ formatTime(item.update_time) || '未知时间' }}</div>
                <div class="article-about">
                  <span><i class="iconfont icon-shoucang"></i>{{ item.like_count || 0 }}</span>
                  <el-divider direction="vertical"></el-divider>
                  <span><i class="iconfont icon-pinglun"></i>{{ item.comments || 0 }}</span>
                  <!-- <el-divider direction="vertical"></el-divider>
                  <span><i class="iconfont icon-fenxiang"></i>{{ item.shareCount || 56 }}</span> -->
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { ref, onMounted } from 'vue'
import AdminModel from '@/lin/model/admin'
import newsModel from '@/model/news'

export default {
  setup() {
    const showTeam = ref(false)
    const activeName = ref('first')
    const basicData = ref({
      viewsCount: 0,
      userCount: 0,
      newsCount: 0,
    })
    const hotNews = ref([])
    const latestNews = ref([])

    const { clientWidth } = document.body

    onMounted(async () => {
      showTeam.value = clientWidth > 1200 && clientWidth < 1330

      const res = await AdminModel.getBasicData()
      basicData.value = {
        newsCount: res.news_count,
        viewsCount: res.views_count,
        userCount: res.user_count,
      }

      // 获取热门文章
      const newsRes = await newsModel.gethotNews()
      hotNews.value = newsRes || []

      // 获取最新作品数据
      const resLatest = await newsModel.getLatestNews()
      latestNews.value = resLatest || []
    })

    const handleArticle = link => {
      window.open(link)
    }

    const limitContent = (content, length = 200) => {
      if (!content) return ''
      const div = document.createElement('div')
      div.innerHTML = content
      const text = div.textContent || div.innerText || ''
      return text.length > length ? text.slice(0, length) + '...' : text
    }

    const formatTime = rawTime => {
      const [datePart, timePart] = rawTime.split('T')
      const time = timePart.split('.')[0] // 去掉毫秒和时区
      return datePart.replace(/-/g, '/') + ' ' + time
    }

    return {
      showTeam,
      activeName,
      basicData,
      hotNews,
      handleArticle,
      limitContent,
      formatTime,
      latestNews,
    }
  },
}
</script>

<style scoped lang="scss">
.container {
  padding: 20px;
  .lin-info {
    display: flex;
    flex: 1;
    height: 160px;
    width: 100%;
    .lin-info-left {
      position: relative;
      width: 690px;
      height: 100%;
      background: rgba(69, 119, 255, 1);
      box-shadow: 0px 2px 14px 0px rgba(243, 243, 243, 1);
      border-radius: 8px;
      .welcome {
        margin: 28px 0 0 30px;
        .welcome-title {
          width: 366px;
          height: 31px;
        }
        .subtitle {
          display: flex;
          flex-direction: column;
          margin-top: 16px;
          color: #fff;
          .guide {
            margin-right: 20px;
            font-size: 14px;
            font-weight: 400;
            line-height: 20px;
          }
          .link {
            margin-top: 6px;
            width: 160px;
            height: 22px;
            background: rgba(44, 95, 233, 1);
            border-radius: 11px;
            text-align: center;
            line-height: 20px;
            color: rgba(255, 255, 255, 1);
          }
        }
      }
      .welcome-bg {
        position: absolute;
        bottom: 0;
        right: 10px;
        width: 393px;
        height: 121px;
      }
    }
    .lin-info-right {
      flex: 1;
      margin-left: 20px;
      height: 100%;
      display: flex;
      flex-direction: column;
      .team-detail {
        position: relative;
        height: 160px;
        background: rgba(255, 176, 139, 1);
        box-shadow: 0px 2px 14px 0px rgba(243, 243, 243, 1);
        border-radius: 8px;

        .team-box {
          margin: 20px 0 0 22px;

          .team-ul {
            margin-top: 15px;

            li {
              height: 20px;
              line-height: 20px;
              margin-bottom: 15px;
              font-size: 14px;

              .shadow-box {
                position: relative;
                display: inline-block;
                margin-right: 10px;
                width: 14px;
                height: 14px;
                border-radius: 14px;
                background-color: #fff;
                transform: translateY(2px);

                .team-shadow {
                  position: absolute;
                  top: 25%;
                  left: 25%;
                  display: inline-block;
                  width: 6px;
                  height: 6px;
                  border-radius: 6px;
                  background-color: #ffb9a4;
                }
              }

              .team-role {
                display: inline-block;
                width: 30px;
                margin-right: 15px;
                font-weight: 400;
                color: #45526b;
              }

              .team-name {
                font-weight: 400;
                color: #fff;

                ul {
                  display: inline;

                  li {
                    display: inline;
                    margin-right: 15px;
                  }
                }
              }
            }
          }
        }

        .team-icon {
          position: absolute;
          top: 25%;
          right: 33px;
          width: 70px;
          height: 70px;
          display: flex;
          justify-content: center;
          align-items: center;
          background-color: #fff;
          box-shadow: 0 0 10px 0 #cfd5e3;

          img {
            width: 62px;
            height: 62px;
          }
        }
        .team-label {
          position: absolute;
          top: 73%;
          right: 20px;
          font-size: 13px;
          font-weight: 400;
          color: rgba(69, 82, 107, 1);
          line-height: 20px;
        }
      }
    }
  }
  .quantity-statistics {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
    height: 90px;
    .quantity-item {
      display: flex;
      width: 23%;
      height: 100%;
      background: rgba(255, 255, 255, 1);
      box-shadow: 0px 2px 14px 0px rgba(243, 243, 243, 1);
      border-radius: 8px;
      .quantity-detail {
        flex: 1;
        .quantity-detail-box {
          margin: 12px 0 0 30px;
          .quantity-title {
            margin-bottom: 2px;
            height: 20px;
            line-height: 20px;
            color: #495468;
            font-size: 14px;
            font-weight: 400;
          }
          .quantity-border-line {
            width: 46px;
            height: 2px;
            background: rgba(73, 84, 104, 1);
          }
          .quantity {
            margin-top: 7px;
            height: 48px;
            font-size: 32px;
            color: rgba(73, 84, 104, 1);
            line-height: 38px;
            letter-spacing: 2px;
          }
        }
      }
      .quantity-icon {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 90px;
        height: 100%;
        background: rgba(69, 119, 255, 0.1);
        border-top-right-radius: 8px;
        border-bottom-right-radius: 8px;
        img {
          width: 28px;
          height: 33px;
        }
      }
    }
  }
  .information {
    margin-top: 20px;
    display: flex;
    .personal {
      width: 320px;
      height: 100%;
      margin-right: 20px;
      background: rgba(255, 255, 255, 1);
      box-shadow: 0px 2px 14px 0px rgba(243, 243, 243, 1);
      border-radius: 8px;
      .personal-title {
        margin: 20px 0 10px 20px;
        height: 22px;
        line-height: 22px;
        font-weight: 500;
        color: #596c8e;
        font-size: 16px;
      }
      .personal-avatar {
        width: 140px;
        height: 140px;
        margin: 0 auto 40px;
        border-radius: 75px;
        box-shadow: 0 0 30px 0 #cfd5e3;
      }
      .personal-influence {
        display: flex;
        justify-content: space-between;
        padding: 0 30px 40px;
        .personal-influence-item {
          display: flex;
          flex-direction: column;
          align-items: center;
          .personal-influence-num {
            font-size: 28px;
            line-height: 34px;
            &.color1 {
              color: #00c292;
            }
            &.color2 {
              color: #fec108;
            }
            &.color3 {
              color: #03a9f3;
            }
          }
          .personal-influece-label {
            font-size: 12px;
            font-weight: 400;
            color: #8c98ae;
            line-height: 17px;
          }
        }
      }
      .personal-tabs {
        margin-bottom: 20px;
      }
      .personal-tabs :v-deep(.is-top) {
        width: 320px;
        display: flex;
        justify-content: space-around;
      }
      .personal-tabs :v-deep(.el-tabs__content) {
        text-indent: 20px;
      }
    }
    .article {
      flex: 1;
      height: 100%;
      padding: 20px;
      background: rgba(255, 255, 255, 1);
      box-shadow: 0px 2px 14px 0px rgba(243, 243, 243, 1);
      border-radius: 8px;
      .article-title {
        height: 22px;
        line-height: 22px;
        font-weight: 500;
        color: #596c8e;
        font-size: 16px;
        margin-bottom: 20px;
      }
      .article-list {
        cursor: pointer;
        .article-item {
          display: flex;
          flex-direction: row;
          justify-content: flex-start;

          .article-thumb {
            width: 120px;
            height: 120px;
            border-radius: 8px;
            margin-right: 30px;
          }
          .article-detail {
            flex: 1;
            border-bottom: 1px #ecedef solid;
            margin-bottom: 20px;
            &.article-last {
              border-bottom: none;
              margin-bottom: 0;
            }
            .article-detail-title {
              height: 22px;
              font-size: 16px;
              font-weight: 400;
              color: rgba(69, 82, 107, 1);
              line-height: 22px;
            }
            .article-detail-content {
              margin-top: 10px;
              font-size: 14px;
              font-weight: 400;
              color: rgba(140, 152, 174, 1);
              line-height: 22px;
            }
          }
          .article-tool {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            margin: 10px 0 20px 0;
            font-size: 12px;
            line-height: 17px;
            font-weight: 400;
            color: #808da3;
            .article-about {
              .iconfont {
                line-height: 17px;
                margin-right: 6px;
                font-size: 12px;
              }
            }
          }
        }
      }
    }
  }
}

@media screen and (max-width: 1200px) {
  .container .lin-info .lin-info-right {
    display: none;
  }
  .container .lin-info .lin-info-left {
    width: 100%;
  }
  .container .quantity-statistics .quantity-item {
    width: 32%;
    &:last-child {
      display: none;
    }
  }
  .container .information .personal {
    display: none;
  }
}

@media screen and (max-width: 1200px) {
  .container .lin-info .lin-info-left {
    width: 100%;
  }
}

.latest-news-item {
  padding: 10px 16px;
  margin-bottom: 10px;
  background-color: #f9f9f9;
  border-radius: 8px;
  font-size: 16px;
  transition: all 0.3s;
  line-height: 1.6;
}

.latest-news-item:hover {
  background-color: #e6f7ff;
  color: #409eff;
}
</style>
