package sp51.spotpass.com.spotpass.ui.kchartlib.base

import android.graphics.Canvas

/**
 * @Time : 2018/5/10 no 18:49
 * @USER : vvguoliang
 * @File : IChartDraw.java
 * @Software: Android Studio
 *code is far away from bugs with the god animal protecting
 *   I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃        ┃
 * **┃ ┳┛  ┗┳ ┃
 * **┃    ┻   ┃
 * **┗━┓    ┏━┛
 * ****┃    ┗━━━┓
 * ****┃ 神兽保佑 ┣┓
 * ****┃ 永无BUG！┏┛
 * ****┗┓┓┏━┳┓┏┛┏┛
 * ******┃┫┫  ┃┫┫
 * ******┗┻┛  ┗┻┛
 */

interface IChartDraw<T> : IDraw {

    /**
     * 获取绘制区域的顶部
     */
    val top: Int

    /**
     * 获取绘制区域的左部
     */
    val left: Int

    /**
     * 获取绘制区域的右部
     */
    val right: Int

    /**
     * 获取绘制区域的底部
     */
    val bottom: Int

    /**
     * 获取绘制高度
     */
    val height: Int

    /**
     * 获取绘制宽度
     */
    val width: Int

    /**
     * 获取当前显示区域实体中最大的值
     */
    val maxValue: Float

    /**
     * 获取当前显示区域实体中最小的值
     */
    val minValue: Float

    /**
     * 获取格式化器
     */
    val valueFormatter: IValueFormatter

    /**
     * 在绘制之前计算
     * @param start 显示区域实体索引的开始
     * @param stop 显示区域实体索引的结束
     */
    fun calculate(start: Int, stop: Int)

    /**
     * 画图表
     * 注意：在此方法画出来的图表会缩放和平移
     * @param canvas [Canvas]
     * @param start 显示区域实体索引的开始
     * @param stop 显示区域实体索引的结束
     */
    fun drawCharts(canvas: Canvas, start: Int, stop: Int)

    /**
     * 画数值
     * 注意：在此方法画出来的图表不会缩放和平移
     * @param canvas [Canvas]
     * @param start 显示区域实体索引的开始
     * @param stop 显示区域实体索引的结束
     */
    fun drawValues(canvas: Canvas, start: Int, stop: Int)

    /**
     * 将数值转换为坐标值
     */
    fun getY(value: Float): Float
}