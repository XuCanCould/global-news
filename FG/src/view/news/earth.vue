<template>
  <!-- 必须设置明确的宽高 -->
  <div ref="chartContainer" class="earth-container"></div>
</template>

<script>
// 1. 引入库和地图数据
import earthFlyLine from 'earth-flyline'
import worldGeoJSON from '../../../public/static/js/world.json'

export default {
  name: 'EarthMap',
  data() {
    return {
      chartInstance: null // 保存图表实例
    }
  },
  mounted() {
    // 2. 注册地图
    earthFlyLine.registerMap('world', worldGeoJSON)

    // 3. 初始化图表
    this.initChart()

    // 4. 窗口变化自适应
    window.addEventListener('resize', this.handleResize)
  },
  beforeUnmount() {
    // 5. 销毁实例释放资源
    if (this.chartInstance) {
      this.chartInstance.dispose()
    }
    window.removeEventListener('resize', this.handleResize)
  },
  methods: {
    initChart() {
      const options = {
        dom: this.$refs.chartContainer, // Vue 的 ref 引用
        map: 'world', // 使用注册的地图名称
        config: {
          R: 140,
          earth: {
            color: "#13162c" // 地球底色
          },
          mapStyle: {
            areaColor: "#2e3564", // 区域颜色
            lineColor: "#797eff"  // 边界线颜色
          },
          spriteStyle: {
            color: "#797eff" // 光圈颜色
          },
          pathStyle: {
            color: "#cd79ff" // 飞线路径
          },
          flyLineStyle: {
            color: "#cd79ff" // 飞线动画
          },
          regions: {
            China: {
              areaColor: "#2e3564" // 中国区域颜色
            }
          }
        }
      }

      try {
        this.chartInstance = earthFlyLine.init(options)
        this.addSampleFlyLine() // 添加示例飞线
      } catch (error) {
        console.error('图表初始化失败:', error)
      }
    },
    handleResize() {
      // 窗口变化时自适应
      this.chartInstance?.resize()
    },
    addSampleFlyLine() {
      // 示例：添加北京到纽约的飞线
      if (this.chartInstance?.addFlyLine) {
        this.chartInstance.addFlyLine({
          from: [116.4, 39.9],  // 北京坐标 [经度, 纬度]
          to: [-74.0, 40.7],    // 纽约坐标
          color: "#cd79ff"
        })
      }
    }
  }
}
</script>

<style scoped>
.earth-container {
  width: 100%;
  height: 100vh;
  background: #040D21; /* 建议使用深色背景 */
}
</style>
