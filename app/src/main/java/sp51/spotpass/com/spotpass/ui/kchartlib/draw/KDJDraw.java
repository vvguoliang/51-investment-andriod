package sp51.spotpass.com.spotpass.ui.kchartlib.draw;

/**
 * @Time : 2018/5/10 no 19:46
 * @USER : vvguoliang
 * @File : KDJDraw.java
 * @Software: Android Studio
 * code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
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

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Pair;

import sp51.spotpass.com.spotpass.ui.kchartlib.BaseKChartView;
import sp51.spotpass.com.spotpass.ui.kchartlib.entity.IKDJ;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.CanvasUtils;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.XAlign;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.YAlign;

/**
 * KDJ实现类
 * Created by tifezh on 2016/6/19.
 */

public class KDJDraw extends BaseChartDraw<IKDJ> {

    private Paint mKPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mDPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mJPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * 构造方法
     *
     * @param rect       显示区域
     * @param KChartView {@link BaseKChartView}
     */
    public KDJDraw(Rect rect, BaseKChartView KChartView) {
        super(rect, KChartView);
    }


    @Override
    protected void foreachDrawChart(Canvas canvas, int i, IKDJ curPoint, IKDJ lastPoint) {
        drawLine(canvas, mKPaint, i, curPoint.getK(), lastPoint.getK());
        drawLine(canvas, mDPaint, i, curPoint.getD(), lastPoint.getD());
        drawLine(canvas, mJPaint, i, curPoint.getJ(), lastPoint.getJ());
    }

    @Override
    public void drawValues(@NonNull Canvas canvas, int start, int stop) {
        IKDJ point = getDisplayItem();
        float x = this.getMKChartView().getTextPaint().measureText(getValueFormatter().format(getMaxValue())+" ");
        new CanvasUtils().drawTexts(canvas,x,0, XAlign.LEFT, YAlign.TOP,
                new Pair<>(mKPaint,"K:" + this.getMKChartView().formatValue(point.getK()) + " "),
                new Pair<>(mDPaint,"D:" + this.getMKChartView().formatValue(point.getD()) + " "),
                new Pair<>(mJPaint,"J:" + this.getMKChartView().formatValue(point.getJ()) + " "));
    }

    @Override
    public float getMaxValue(IKDJ point) {
        return Math.max(point.getK(), Math.max(point.getD(), point.getJ()));
    }

    @Override
    public float getMinValue(IKDJ point) {
        return Math.min(point.getK(), Math.min(point.getD(), point.getJ()));
    }

    /**
     * 设置K颜色
     */
    public void setKColor(int color) {
        mKPaint.setColor(color);
    }

    /**
     * 设置D颜色
     */
    public void setDColor(int color) {
        mDPaint.setColor(color);
    }

    /**
     * 设置J颜色
     */
    public void setJColor(int color) {
        mJPaint.setColor(color);
    }

    /**
     * 设置曲线宽度
     */
    public void setLineWidth(float width)
    {
        mKPaint.setStrokeWidth(width);
        mDPaint.setStrokeWidth(width);
        mJPaint.setStrokeWidth(width);
    }

    /**
     * 设置文字大小
     */
    public void setTextSize(float textSize)
    {
        mKPaint.setTextSize(textSize);
        mDPaint.setTextSize(textSize);
        mJPaint.setTextSize(textSize);
    }


}
