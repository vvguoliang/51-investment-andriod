package sp51.spotpass.com.spotpass.ui.kchartlib.draw;

/**
 * @Time : 2018/5/10 no 19:58
 * @USER : vvguoliang
 * @File : VolumeDraw.java
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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Pair;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.kchartlib.BaseKChartView;
import sp51.spotpass.com.spotpass.ui.kchartlib.base.IValueFormatter;
import sp51.spotpass.com.spotpass.ui.kchartlib.entity.IVolume;
import sp51.spotpass.com.spotpass.ui.kchartlib.formatter.BigValueFormatter;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.CanvasUtils;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.ViewUtil;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.XAlign;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.YAlign;

/**
 * 成交量
 * Created by hjm on 2017/11/14 17:49.
 */

public class VolumeDraw extends BaseChartDraw<IVolume> {

    private Paint mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mGreenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma5Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma10Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int pillarWidth = 0;

    /**
     * 构造方法
     *
     * @param rect       显示区域
     * @param KChartView {@link BaseKChartView}
     */
    public VolumeDraw(Rect rect, BaseKChartView KChartView) {
        super(rect, KChartView);
        Context context = this.getMKChartView().getContext();
        mRedPaint.setColor(ContextCompat.getColor(context, R.color.chart_red));
        mGreenPaint.setColor(ContextCompat.getColor(context, R.color.chart_green));
        pillarWidth = ViewUtil.INSTANCE.Dp2Px(context, 4);
    }


    private void drawHistogram(
            Canvas canvas, IVolume curPoint, float curX) {

        float r = pillarWidth / 2;
        float top = getY(curPoint.getVolume());
        float bottom = getY(0);
        if (curPoint.getClosePrice() >= curPoint.getOpenPrice()) {//涨
            canvas.drawRect(curX - r, top, curX + r, bottom, mRedPaint);
        } else {
            canvas.drawRect(curX - r, top, curX + r, bottom, mGreenPaint);
        }

    }

    @Override
    public void drawValues(@NonNull Canvas canvas, int start, int stop) {
        IVolume point = getDisplayItem();
        float x = this.getMKChartView().getTextPaint().measureText(getValueFormatter().format(getMaxValue())+" ");
        new CanvasUtils().drawTexts(canvas,x,0, XAlign.LEFT, YAlign.TOP,
                new Pair<>(this.getMKChartView().getTextPaint(),"VOL:" + getValueFormatter().format(point.getVolume()) + " "),
                new Pair<>(ma5Paint,"MA5:" + getValueFormatter().format(point.getMA5Volume()) + " "),
                new Pair<>(ma10Paint,"MA10:" + getValueFormatter().format(point.getMA10Volume()) + " "));
    }

    @Override
    protected void foreachDrawChart(Canvas canvas, int i, IVolume curPoint, IVolume lastPoint) {
        drawHistogram(canvas, curPoint, getX(i));
        drawLine(canvas, ma5Paint, i, curPoint.getMA5Volume(), lastPoint.getMA5Volume());
        drawLine(canvas, ma10Paint, i, curPoint.getMA10Volume(), lastPoint.getMA10Volume());
    }

    @Override
    public float getMaxValue(IVolume point) {
        return Math.max(point.getVolume(), Math.max(point.getMA5Volume(), point.getMA10Volume()));
    }

    @Override
    public float getMinValue(IVolume point) {
        return 0;
    }

    public IValueFormatter getValueFormatter() {
        return new BigValueFormatter();
    }

    /**
     * 设置 MA5 线的颜色
     *
     */
    public void setMa5Color(int color) {
        this.ma5Paint.setColor(color);
    }

    /**
     * 设置 MA10 线的颜色
     */
    public void setMa10Color(int color) {
        this.ma10Paint.setColor(color);
    }

    public void setLineWidth(float width) {
        this.ma5Paint.setStrokeWidth(width);
        this.ma10Paint.setStrokeWidth(width);
    }

    /**
     * 设置文字大小
     *
     */
    public void setTextSize(float textSize) {
        this.ma5Paint.setTextSize(textSize);
        this.ma10Paint.setTextSize(textSize);
    }


}
