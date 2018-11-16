package sp51.spotpass.com.spotpass.ui.kchartlib.draw;

/**
 * @Time : 2018/5/10 no 19:56
 * @USER : vvguoliang
 * @File : RSIDraw.java
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
import sp51.spotpass.com.spotpass.ui.kchartlib.entity.IRSI;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.CanvasUtils;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.XAlign;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.YAlign;

/**
 * RSI实现类
 * Created by tifezh on 2016/6/19.
 */

public class RSIDraw extends BaseChartDraw<IRSI> {

    private Paint mRSI1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mRSI2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mRSI3Paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * 构造方法
     *
     * @param rect       显示区域
     * @param KChartView {@link BaseKChartView}
     */
    public RSIDraw(Rect rect, BaseKChartView KChartView) {
        super(rect, KChartView);
    }

    @Override
    protected void foreachDrawChart(Canvas canvas, int i, IRSI curPoint, IRSI lastPoint) {
        drawLine(canvas, mRSI1Paint, i, curPoint.getRsi1(), lastPoint.getRsi1());
        drawLine(canvas, mRSI2Paint, i, curPoint.getRsi2(), lastPoint.getRsi2());
        drawLine(canvas, mRSI3Paint, i, curPoint.getRsi3(), lastPoint.getRsi3());
    }

    @Override
    public void drawValues(@NonNull Canvas canvas, int start, int stop) {
        IRSI point = getDisplayItem();
        float x = this.getMKChartView().getTextPaint().measureText(getValueFormatter().format(getMaxValue())+" ");
        new CanvasUtils().drawTexts(canvas,x,0, XAlign.LEFT, YAlign.TOP,
                new Pair<>(mRSI1Paint,"RSI1:" + this.getMKChartView().formatValue(point.getRsi1()) + " "),
                new Pair<>(mRSI2Paint,"RSI2:" + this.getMKChartView().formatValue(point.getRsi2()) + " "),
                new Pair<>(mRSI3Paint,"RSI3:" + this.getMKChartView().formatValue(point.getRsi3()) + " "));
    }

    @Override
    public float getMaxValue(IRSI point) {
        return Math.max(point.getRsi1(), Math.max(point.getRsi2(), point.getRsi3()));
    }

    @Override
    public float getMinValue(IRSI point) {
        return Math.min(point.getRsi1(), Math.min(point.getRsi2(), point.getRsi3()));
    }

    public void setRSI1Color(int color) {
        mRSI1Paint.setColor(color);
    }

    public void setRSI2Color(int color) {
        mRSI2Paint.setColor(color);
    }

    public void setRSI3Color(int color) {
        mRSI3Paint.setColor(color);
    }

    /**
     * 设置曲线宽度
     */
    public void setLineWidth(float width)
    {
        mRSI1Paint.setStrokeWidth(width);
        mRSI2Paint.setStrokeWidth(width);
        mRSI3Paint.setStrokeWidth(width);
    }

    /**
     * 设置文字大小
     */
    public void setTextSize(float textSize)
    {
        mRSI2Paint.setTextSize(textSize);
        mRSI3Paint.setTextSize(textSize);
        mRSI1Paint.setTextSize(textSize);
    }
}
