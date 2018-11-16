package sp51.spotpass.com.spotpass.ui.kchartlib.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.annotation.CallSuper
import sp51.spotpass.com.spotpass.ui.kchartlib.BaseKChartView
import sp51.spotpass.com.spotpass.ui.kchartlib.base.IChartDraw
import sp51.spotpass.com.spotpass.ui.kchartlib.base.IValueFormatter

/**
 * @Time : 2018/5/10 no 19:09
 * @USER : vvguoliang
 * @File : BaseChartDraw.java
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
/**
 * 图形绘制基类
 * Created by tifezh on 2018/3/30.
 */

abstract class BaseChartDraw<T>
/**
 * 构造方法
 * @param rect 显示区域
 * @param KChartView [BaseKChartView]
 */
(private val mRect: Rect, protected var mKChartView: BaseKChartView) : IChartDraw<T> {

    final override var maxValue: Float = 0.toFloat()
        private set

    final override var minValue: Float = 0.toFloat()
        private set

    private var mScaleY: Float = 0.toFloat()

    override val top: Int
        get() = mRect.top

    override val bottom: Int
        get() = mRect.bottom

    override val left: Int
        get() = mRect.left

    override val right: Int
        get() = mRect.right

    override val height: Int
        get() = mRect.height()

    override val width: Int
        get() = mRect.width()

    /**
     * 获取全部数据集合
     */
    protected val data: List<T>
        get() = mKChartView.getAdapter().getData() as List<T>

    /**
     * 获取实体个数
     */
    protected val dataCount: Int
        get() = mKChartView.getAdapter().getCount()

    /**
     * 获取显示的值
     * 长按状态下显示长按的值
     * 非长按状态下显示最右边的值
     */
    val displayItem: T
        get() =
            mKChartView.getItem(if (mKChartView.isLongPress()) mKChartView.getSelectedIndex() else mKChartView.getStopIndex()) as T

    override val valueFormatter: IValueFormatter
        get() = mKChartView.getValueFormatter()

    override fun draw(canvas: Canvas) {
        canvas.save()
        canvas.translate(mKChartView.translateX(), top.toFloat())
        canvas.scale(mKChartView.getScaleX(), 1f)
        drawCharts(canvas, mKChartView.getStartIndex(), mKChartView.getStopIndex())
        canvas.restore()
        canvas.save()
        canvas.translate(0f, top.toFloat())
        drawValues(canvas, mKChartView.getStartIndex(), mKChartView.getStopIndex())
        canvas.restore()
    }

    @CallSuper
    override fun calculate(start: Int, stop: Int) {
        maxValue = java.lang.Float.MIN_VALUE
        minValue = java.lang.Float.MAX_VALUE
        val data = data
        for (i in start until stop) {
            val point = data[i]
            foreachCalculate(i, point)
        }
        //        if(mMaxValue!=mMinValue) {
        //            float padding = (mMaxValue - mMinValue) * 0.05f;
        //            mMaxValue += padding;
        //            mMinValue -= padding;
        //        } else {
        //            //当最大值和最小值都相等的时候 分别增大最大值和 减小最小值
        //            mMaxValue += 1f;
        //            mMinValue -= 1f;
        //            if (mMaxValue == 0) {
        //                mMaxValue = 1;
        //            }
        //        }

        mScaleY = mRect.height() * 1f / (maxValue - minValue)
    }

    @CallSuper
    override fun drawCharts(canvas: Canvas, start: Int, stop: Int) {
        val data = data
        for (i in mKChartView.getStartIndex()..mKChartView.getStopIndex()) {
            val currentPoint = data[i]
            val lastPoint = if (i == mKChartView.getStartIndex()) currentPoint else data[i - 1]
            foreachDrawChart(canvas, i, currentPoint, lastPoint)
        }
    }

    /**
     * 循环遍历显示区域的实体
     * 在[BaseChartDraw.calculate]} 中被循环调用
     * @param i 索引
     * @param point 数据实体
     */
    @CallSuper
    protected fun foreachCalculate(i: Int, point: T) {
        maxValue = Math.max(maxValue, getMaxValue(point))
        minValue = Math.min(minValue, getMinValue(point))
    }

    /**
     * 循环遍历显示区域的实体
     * 在[BaseChartDraw.drawCharts] 中被调用
     * @param i 当前点的索引值
     * @param curPoint 当前点实体
     * @param lastPoint 上一个点实体
     */
    protected abstract fun foreachDrawChart(canvas: Canvas, i: Int, curPoint: T, lastPoint: T)


    /**
     * 获取当前实体中最大的值
     *
     * @param point 当前实体
     */
    protected abstract fun getMaxValue(point: T): Float

    /**
     * 获取当前实体中最小的值
     *
     * @param point 实体
     */
    protected abstract fun getMinValue(point: T): Float

    override fun getY(value: Float): Float {
        return (maxValue - value) * mScaleY
    }

    /**
     * 根据索引索取x坐标
     * @param index 索引值
     * @see BaseKChartView.getX
     */
    fun getX(index: Int): Float {
        return mKChartView.getX(index)
    }

    /**
     * 画线
     * @param curIndex 当前点索引
     * @param curValue 当前点的值
     * @param lastValue 前一个点的值
     */
    fun drawLine(canvas: Canvas, paint: Paint, curIndex: Int, curValue: Float, lastValue: Float) {
        //如果是第一个点就不用画线
        if (curIndex != mKChartView.getStartIndex()) {
            canvas.drawLine(getX(curIndex - 1), getY(lastValue), getX(curIndex), getY(curValue), paint)
        }
    }

    /**
     * 画矩形
     * @param curIndex 当前点的index
     * @param width 矩形的宽度
     * @param topValue 上方的值
     * @param bottomValue 底部的值
     */
    fun drawRect(canvas: Canvas, paint: Paint, curIndex: Int, width: Float, topValue: Float, bottomValue: Float) {
        var topValue = topValue
        var bottomValue = bottomValue
        topValue = getY(topValue)
        bottomValue = getY(bottomValue)
        val x = getX(curIndex)
        canvas.drawRect(x - width / 2f, topValue, x + width / 2f, bottomValue, paint)
    }
}