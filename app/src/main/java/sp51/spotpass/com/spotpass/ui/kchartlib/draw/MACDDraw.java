package sp51.spotpass.com.spotpass.ui.kchartlib.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Pair;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.kchartlib.BaseKChartView;
import sp51.spotpass.com.spotpass.ui.kchartlib.entity.IMACD;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.CanvasUtils;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.XAlign;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.YAlign;

/**
 * @Time : 2018/5/10 no 19:48
 * @USER : vvguoliang
 * @File : MACDDraw.java
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
public class MACDDraw extends BaseChartDraw<IMACD>{

    private Paint mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mGreenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mDIFPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mDEAPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mMACDPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**macd 中柱子的宽度*/
    private float mMACDWidth = 10;

    public MACDDraw(Rect rect, BaseKChartView view) {
        super(rect,view);
        Context context=view.getContext();
        mRedPaint.setColor(ContextCompat.getColor(context, R.color.chart_red));
        mGreenPaint.setColor(ContextCompat.getColor(context,R.color.chart_green));

        setLineWidth(this.getMKChartView().getLineWidth());
        setTextSize(this.getMKChartView().getTextSize());
        mDEAPaint.setColor(ContextCompat.getColor(context,R.color.chart_ma5));
        mDIFPaint.setColor(ContextCompat.getColor(context,R.color.chart_ma10));
    }

    @Override
    public void drawValues(@NonNull Canvas canvas, int start, int stop) {
        IMACD point = getDisplayItem();
        float x = this.getMKChartView().getTextPaint().measureText(getValueFormatter().format(getMaxValue())+" ");
        new CanvasUtils().drawTexts(canvas,x,0, XAlign.LEFT, YAlign.TOP,
                new Pair<>(mDIFPaint,"DIF:" + this.getMKChartView().formatValue(point.getDif()) + " "),
                new Pair<>(mDEAPaint,"DEA:" + this.getMKChartView().formatValue(point.getDea()) + " "),
                new Pair<>(mMACDPaint,"MACD:" + this.getMKChartView().formatValue(point.getMacd()) + " "));
    }

    @Override
    protected void foreachDrawChart(Canvas canvas, int i, IMACD curPoint, IMACD lastPoint) {
        drawMACD(canvas,i, curPoint.getMacd());
        drawLine(canvas,mDEAPaint,i,curPoint.getDea(),lastPoint.getDea());
        drawLine(canvas,mDIFPaint,i,curPoint.getDif(),lastPoint.getDif());
    }

    @Override
    public float getMaxValue(IMACD point) {
        return Math.max(point.getMacd(), Math.max(point.getDea(), point.getDif()));
    }

    @Override
    public float getMinValue(IMACD point) {
        return Math.min(point.getMacd(), Math.min(point.getDea(), point.getDif()));
    }
    /**
     * 画macd
     */
    private void drawMACD(Canvas canvas, int index, float macd) {
        if (macd > 0) {
            drawRect(canvas,mRedPaint,index,mMACDWidth,macd,0);
        } else {
            drawRect(canvas,mGreenPaint,index,mMACDWidth,0,macd);
        }
    }

    /**
     * 设置DIF颜色
     */
    public void setDIFColor(int color) {
        this.mDIFPaint.setColor(color);
    }

    /**
     * 设置DEA颜色
     */
    public void setDEAColor(int color) {
        this.mDEAPaint.setColor(color);
    }

    /**
     * 设置MACD颜色
     */
    public void setMACDColor(int color) {
        this.mMACDPaint.setColor(color);
    }

    /**
     * 设置MACD的宽度
     * @param MACDWidth
     */
    public void setMACDWidth(float MACDWidth) {
        mMACDWidth = MACDWidth;
    }

    /**
     * 设置曲线宽度
     */
    public void setLineWidth(float width)
    {
        mDEAPaint.setStrokeWidth(width);
        mDIFPaint.setStrokeWidth(width);
        mMACDPaint.setStrokeWidth(width);
    }

    /**
     * 设置文字大小
     */
    public void setTextSize(float textSize)
    {
        mDEAPaint.setTextSize(textSize);
        mDIFPaint.setTextSize(textSize);
        mMACDPaint.setTextSize(textSize);
    }
}

