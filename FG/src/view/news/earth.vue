<template>
  <div class="earth-container">
    <div class="news-title">寰球新闻</div>
    <div ref="chartContainer" class="chart-area"></div>
  </div>
</template>

<script>
import earthFlyLine from 'earth-flyline'
import worldGeoJSON from '../../../public/static/js/world.json'

export default {
  name: 'EarthMap',
  data() {
    return {
      // ❌ 不在这里定义 chartInstance
    }
  },
  mounted() {
    // ✅ 使用 this.chartInstance 定义为普通属性
    this.chartInstance = null

    earthFlyLine.registerMap('world', worldGeoJSON)
    this.initChart()
    window.addEventListener('resize', this.handleResize)
  },
  beforeUnmount() {
    this.chartInstance?.destroy() // ✅ 修复 destory 拼写错误
    window.removeEventListener('resize', this.handleResize)
  },
  methods: {
    initChart() {
      const options = {
        dom: this.$refs.chartContainer,
        map: 'world',
        config: {
          R: 140,
          earth: {
            color: "#13162c"
          },
          mapStyle: {
            areaColor: "#2e3564",
            lineColor: "#797eff"
          }
        }
      }

      try {
        this.chartInstance = earthFlyLine.init(options)

        // 添加一个可点击的点数据
        const pointData = [
          {
            id: 'city1',
            lon: 116.4074,
            lat: 39.9042,
            name: 'Beijing',
            customInfo: 'Hello Earth!',
            style: {
              color: '#ff6600',
              size: 8
            }
          }
        ]
        this.chartInstance.addData('point', pointData)

        // 添加点击事件监听
        this.chartInstance.on('click', (event, mesh) => {
          if (mesh && mesh.userData) {
            console.log('✅ 点击 userData:', mesh.userData)
          }
        })
      } catch (error) {
        console.error('初始化失败:', error)
      }
    },
    handleResize() {
      this.chartInstance?.resize()
    }
  }
}
</script>

<style scoped>
.earth-container {
  width: 100%;
  height: 100vh;
  background: #040D21;
}
.chart-area {
  width: 100%;
  height: 100%;
}

.news-title {
  position: absolute;
  top: 20px;
  left: 20px;
  color: rgba(255, 255, 255, 0.25); /* 白色文字，透明度为50% */
  font-size: 45px;
  font-weight: bold;
  z-index: 10; /* 确保文字在地图上方 */
  writing-mode: vertical-rl; /* 竖排文字 */
  transform: rotate(0deg); /* 旋转文字，使其从上到下排列 */
  letter-spacing: 15px; /* 字间距 */
}
</style>
