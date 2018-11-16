package com.github.mikephil.charting.stockChart.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import com.github.mikephil.charting.R;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.VolFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.stockChart.BarBottomMarkerView;
import com.github.mikephil.charting.stockChart.BarTomMarkerView;
import com.github.mikephil.charting.stockChart.CoupleChartGestureListener;
import com.github.mikephil.charting.stockChart.LeftMarkerView;
import com.github.mikephil.charting.stockChart.TimeBarChart;
import com.github.mikephil.charting.stockChart.TimeLineChart;
import com.github.mikephil.charting.stockChart.TimeRightMarkerView;
import com.github.mikephil.charting.stockChart.TimeXAxis;
import com.github.mikephil.charting.stockChart.data.KTimeData;
import com.github.mikephil.charting.stockChart.event.BaseEvent;
import com.github.mikephil.charting.stockChart.model.CirclePositionTime;
import com.github.mikephil.charting.stockChart.model.TimeDataModel;
import com.github.mikephil.charting.utils.CommonUtil;
import com.github.mikephil.charting.utils.NumberUtils;
import com.github.mikephil.charting.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 当日分时图view
 */
public class OneDayView extends BaseView {

    private Context mContext;
    TimeLineChart lineChart;
    TimeBarChart barChart;
    FrameLayout cirCleView;

    private LineDataSet d1, d2;
    private BarDataSet barDataSet;

    TimeXAxis xAxisLine;
    YAxis axisRightLine;
    YAxis axisLeftLine;

    TimeXAxis xAxisBar;
    YAxis axisLeftBar;
    YAxis axisRightBar;

    private CoupleChartGestureListener gestureListenerLine;
    private CoupleChartGestureListener gestureListenerBar;
    private boolean landscape = false;
    private int maxCount = 79201;//最大可见数量，即分时一天最大数据点数
    private SparseArray<String> xLabels = new SparseArray<>();//X轴刻度label

    public OneDayView(Context context) {
        this(context, null);
    }

    public OneDayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_time, this);
        lineChart = findViewById(R.id.line_chart);
        barChart = findViewById(R.id.bar_chart);
        cirCleView = findViewById(R.id.circle_frame_time);

        EventBus.getDefault().register(this);

        playHeartbeatAnimation(cirCleView.findViewById(R.id.anim_view));

    }

    /**
     * 初始化图表属性
     */
    public void initChart(boolean landscape) {
        this.landscape = landscape;
        //主图
        lineChart.setScaleEnabled(false);
        lineChart.setDrawBorders(true);
        lineChart.setBorderColor(ContextCompat.getColor(mContext, R.color.border_color));
        lineChart.setBorderWidth(0.7f);
        lineChart.setNoDataText("加载中...");
        Legend lineChartLegend = lineChart.getLegend();
        lineChartLegend.setEnabled(false);
        lineChart.setDescription(null);
        //副图
        barChart.setScaleEnabled(false);
        barChart.setDrawBorders(true);
        barChart.setBorderColor(ContextCompat.getColor(mContext, R.color.border_color));
        barChart.setBorderWidth(0.7f);
        barChart.setNoDataText("加载中...");
        Legend barChartLegend = barChart.getLegend();
        barChartLegend.setEnabled(false);
        barChart.setDescription(null);

        //主图X轴
        xAxisLine = (TimeXAxis) lineChart.getXAxis();
        xAxisLine.setDrawAxisLine(false);
        xAxisLine.setTextColor(ContextCompat.getColor(mContext, R.color.label_text));
        xAxisLine.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisLine.setAvoidFirstLastClipping(true);
        xAxisLine.setGridColor(ContextCompat.getColor(mContext, R.color.grid_color));
        xAxisLine.setTextColor(ContextCompat.getColor(mContext, R.color.label_text));

        //主图左Y轴
        axisLeftLine = lineChart.getAxisLeft();
        axisLeftLine.setLabelCount(5, true);
        axisLeftLine.setDrawGridLines(true);
        axisLeftLine.setValueLineInside(true);
        axisLeftLine.setDrawTopBottomGridLine(false);
        axisLeftLine.setDrawAxisLine(false);
        axisLeftLine.setPosition(landscape ? YAxis.YAxisLabelPosition.OUTSIDE_CHART : YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisLeftLine.setGridColor(ContextCompat.getColor(mContext, R.color.grid_color));
        final int[] valueint = {0};
        axisLeftLine.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                valueint[0]++;
                if (valueint[0] == 1 || valueint[0] == 2) {
                    axisLeftLine.setTextColor(ContextCompat.getColor(mContext, R.color.down_color));
                } else if (valueint[0] == 4 || valueint[0] == 5) {
                    axisLeftLine.setTextColor(ContextCompat.getColor(mContext, R.color.up_color));
                    if (valueint[0] == 5) {
                        valueint[0] = 0;
                    }
                } else {
                    axisLeftLine.setTextColor(ContextCompat.getColor(mContext, R.color.label_text));
                }
                return NumberUtils.keepPrecisionR(value, 2);
            }
        });

        //主图右Y轴
        axisRightLine = lineChart.getAxisRight();
        axisRightLine.setLabelCount(5, true);
        axisRightLine.setDrawTopBottomGridLine(false);
        axisRightLine.setDrawGridLines(true);
        axisRightLine.enableGridDashedLine(CommonUtil.dip2px(mContext, 4), CommonUtil.dip2px(mContext, 3), 0);
        axisRightLine.setDrawAxisLine(false);
        axisRightLine.setValueLineInside(true);
        axisRightLine.setPosition(landscape ? YAxis.YAxisLabelPosition.OUTSIDE_CHART : YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisRightLine.setAxisLineColor(ContextCompat.getColor(mContext, R.color.grid_color));
        axisRightLine.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value > 0) {
                    axisRightLine.setTextColor(ContextCompat.getColor(mContext, R.color.up_color));
                } else if (value < 0) {
                    axisRightLine.setTextColor(ContextCompat.getColor(mContext, R.color.down_color));
                } else {
                    axisRightLine.setTextColor(ContextCompat.getColor(mContext, R.color.label_text));
                }
                DecimalFormat mFormat = new DecimalFormat("#0.00%");
                return mFormat.format(value);
            }
        });

        //副图X轴
        xAxisBar = (TimeXAxis) barChart.getXAxis();
        xAxisBar.setDrawLabels(false);
        xAxisBar.setDrawAxisLine(false);
        xAxisBar.setTextColor(ContextCompat.getColor(mContext, R.color.label_text));
        xAxisBar.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisBar.setAvoidFirstLastClipping(true);
        xAxisBar.setGridColor(ContextCompat.getColor(mContext, R.color.grid_color));

        //副图左Y轴
        axisLeftBar = barChart.getAxisLeft();
        axisLeftBar.setDrawGridLines(false);
        axisLeftBar.setDrawAxisLine(false);
        axisLeftBar.setTextColor(ContextCompat.getColor(mContext, R.color.label_text));
        axisLeftBar.setPosition(landscape ? YAxis.YAxisLabelPosition.OUTSIDE_CHART : YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisLeftBar.setDrawLabels(true);
        axisLeftBar.setLabelCount(2, true);
        axisLeftBar.setAxisMinimum(0);
        axisLeftBar.setSpaceTop(5);
        axisLeftBar.setValueLineInside(true);

        //副图右Y轴
        axisRightBar = barChart.getAxisRight();
        axisRightBar.setDrawLabels(false);
        axisRightBar.setDrawGridLines(true);
        axisRightBar.setDrawAxisLine(false);
        axisRightBar.setLabelCount(3, false);
        axisRightBar.setDrawTopBottomGridLine(false);
        axisRightBar.enableGridDashedLine(CommonUtil.dip2px(mContext, 4), CommonUtil.dip2px(mContext, 3), 0);

        //设置图表偏移边距
        int left_right = 0;
        if (landscape) {
            left_right = CommonUtil.dip2px(mContext, 50);
        } else {
            left_right = CommonUtil.dip2px(mContext, 5);
        }
        lineChart.setViewPortOffsets(left_right, CommonUtil.dip2px(mContext, 5), left_right, CommonUtil.dip2px(mContext, 15));
        barChart.setViewPortOffsets(left_right, 0, left_right, CommonUtil.dip2px(mContext, 15));

        //手势联动监听
        gestureListenerLine = new CoupleChartGestureListener(lineChart, new Chart[]{barChart});
        gestureListenerBar = new CoupleChartGestureListener(barChart, new Chart[]{lineChart});
        lineChart.setOnChartGestureListener(gestureListenerLine);
        barChart.setOnChartGestureListener(gestureListenerBar);

        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                lineChart.highlightValue(h);
                barChart.highlightValue(new Highlight(h.getX(), h.getDataSetIndex(), -1));
            }

            @Override
            public void onNothingSelected() {
                barChart.highlightValues(null);
            }
        });
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                barChart.highlightValue(h);
                lineChart.highlightValue(new Highlight(h.getX(), h.getDataSetIndex(), -1));
            }

            @Override
            public void onNothingSelected() {
                lineChart.highlightValues(null);
            }
        });

    }

    /**
     * 是否显示坐标轴label
     *
     * @param isShow
     */
    private void setShowLabels(boolean isShow) {
        lineChart.getAxisLeft().setDrawLabels(isShow);
        lineChart.getAxisRight().setDrawLabels(isShow);
        lineChart.getXAxis().setDrawLabels(isShow);
        barChart.getAxisLeft().setDrawLabels(isShow);
    }

    private Long time = 0L;

    double timedou = 5.5 * 3600 * 1000;
    Long time11 = 0L;
    Long time17 = 0L;
    Long time22 = 0L;
    Long time04 = 0L;

    private Long time1 = date2TimeStamp("06:00:00.000");

    public void getXL(Long time, Long time1) {
        this.time = time;
        if (time1 != 0L) {
            this.time1 = time1;
        }
    }


    /**
     * 设置分时数据
     *
     * @param mData
     */
    public void setDataToChart(KTimeData mData) {

        if (mData.getDatas().size() == 0) {
            cirCleView.setVisibility(View.GONE);
            lineChart.setNoDataText("暂无数据");
            barChart.setNoDataText("暂无数据");
            return;
        } else {
            cirCleView.setVisibility(View.VISIBLE);
        }

        setShowLabels(true);
        setMarkerView(mData);
//        setBottomMarkerView(mData);

        axisLeftLine.setAxisMinimum(mData.getMin());
        axisLeftLine.setAxisMaximum(mData.getMax());


        if (Float.isNaN(mData.getPercentMax()) || mData.getPercentMax() == 0) {
            axisLeftBar.setAxisMaximum(0);
            axisRightLine.setAxisMinimum(-0.01f);
            axisRightLine.setAxisMaximum(0.01f);
        } else {
            axisLeftBar.setAxisMaximum(mData.getVolMaxTime());
            axisRightLine.setAxisMinimum(mData.getPercentMin());
            axisRightLine.setAxisMaximum(mData.getPercentMax());
        }

        axisLeftBar.setValueFormatter(new VolFormatter());

        ArrayList<Entry> lineCJEntries = new ArrayList<>();
        ArrayList<Entry> lineJJEntries = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0, j = 0; i < mData.getDatas().size(); i++, j++) {
            TimeDataModel t = mData.getDatas().get(j);
            if (t == null) {
                lineCJEntries.add(new Entry(i, i, Float.NaN));
                lineJJEntries.add(new Entry(i, i, Float.NaN));
                barEntries.add(new BarEntry(i, i, Float.NaN));
                continue;
            }
            lineCJEntries.add(new Entry(i, i, (float) mData.getDatas().get(i).getNowPrice()));
            lineJJEntries.add(new Entry(i, i, (float) mData.getDatas().get(i).getAveragePrice()));
            barEntries.add(new BarEntry(i, i, mData.getDatas().get(i).getVolume()));
        }
        d1 = new LineDataSet(lineCJEntries, "oneday");
        d2 = new LineDataSet(lineJJEntries, "均价");
        d1.setDrawCircleDashMarker(true);
        d2.setDrawCircleDashMarker(false);
        d1.setDrawValues(false);
        d2.setDrawValues(false);
        d1.setLineWidth(0.7f);
        d2.setLineWidth(0.7f);
        d1.setColor(ContextCompat.getColor(mContext, R.color.minute_blue));
        d2.setColor(ContextCompat.getColor(mContext, R.color.minute_yellow));
        d1.setDrawFilled(true);
        d1.setFillColor(ContextCompat.getColor(mContext, R.color.fill_Color));
        d1.setHighLightColor(ContextCompat.getColor(mContext, R.color.highLight_Color));
        d2.setHighlightEnabled(false);
        d1.setDrawCircles(false);
        d2.setDrawCircles(false);
        d1.setAxisDependency(YAxis.AxisDependency.LEFT);
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        sets.add(d2);
        LineData cd = new LineData(sets);
        lineChart.setData(cd);

        barDataSet = new BarDataSet(barEntries, "成交量");
        barDataSet.setHighLightColor(ContextCompat.getColor(mContext, R.color.highLight_Color));
        barDataSet.setDrawValues(false);
        barDataSet.setNeutralColor(ContextCompat.getColor(mContext, R.color.equal_color));
        barDataSet.setIncreasingColor(ContextCompat.getColor(mContext, R.color.up_color));
        barDataSet.setDecreasingColor(ContextCompat.getColor(mContext, R.color.down_color));
        barDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        barDataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        //下面方法需在填充数据后调用
        xAxisLine.setXLabels(getXLabels());
        xAxisLine.setLabelCount(getXLabels().size(), true);
        xAxisBar.setXLabels(getXLabels());
        xAxisBar.setLabelCount(getXLabels().size(), true);
        lineChart.setVisibleXRange(maxCount, maxCount);
        barChart.setVisibleXRange(maxCount, maxCount);

        lineChart.moveViewToX(mData.getDatas().size() - 1);
        barChart.moveViewToX(mData.getDatas().size() - 1);
        lineChart.invalidate();
        barChart.invalidate();
    }

    public void dynamicsAddOne(String xValue, float chPrice, float junPrice, float vol, int length) {
        int index = length - 1;
        LineData lineData = lineChart.getData();
        ILineDataSet d1 = lineData.getDataSetByIndex(0);
        d1.addEntry(new Entry(index, index, chPrice));
        ILineDataSet d2 = lineData.getDataSetByIndex(1);
        d2.addEntry(new Entry(index, index, junPrice));

        BarData barData = barChart.getData();
        IBarDataSet barDataSet = barData.getDataSetByIndex(0);
        barDataSet.addEntry(new BarEntry(index, index, vol));
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        barData.notifyDataChanged();
        barChart.notifyDataSetChanged();
        lineChart.setVisibleXRange(maxCount, maxCount);
        barChart.setVisibleXRange(maxCount, maxCount);

        lineChart.moveViewToX(index);
        barChart.moveViewToX(index);
    }

    public void dynamicsUpdateOne(String xValue, float chPrice, float junPrice, float vol, int length) {
        int index = length - 1;
        LineData lineData = lineChart.getData();
        ILineDataSet d1 = lineData.getDataSetByIndex(0);
        Entry e = d1.getEntryForIndex(index);
        d1.removeEntry(e);
        d1.addEntry(new Entry(index, index, chPrice));

        ILineDataSet d2 = lineData.getDataSetByIndex(1);
        Entry e2 = d2.getEntryForIndex(index);
        d2.removeEntry(e2);
        d2.addEntry(new Entry(index, index, junPrice));

        BarData barData = barChart.getData();
        IBarDataSet barDataSet = barData.getDataSetByIndex(0);
        barDataSet.removeEntry(index);
        barDataSet.addEntry(new BarEntry(index, index, vol));

        //不可见修改数据不刷新
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.moveViewToX(index);

        barData.notifyDataChanged();
        barChart.notifyDataSetChanged();
        barChart.moveViewToX(index);
    }

    public void cleanData() {
        if (lineChart != null && lineChart.getLineData() != null) {
            setShowLabels(false);
            lineChart.clearValues();
            barChart.clearValues();
        }
        if (cirCleView != null) {
            cirCleView.setVisibility(View.GONE);
        }
    }

    private void setMarkerView(KTimeData mData) {
        LeftMarkerView leftMarkerView = new LeftMarkerView(mContext, R.layout.my_markerview1, 2);
        TimeRightMarkerView rightMarkerView = new TimeRightMarkerView(mContext, R.layout.my_markerview1);
        BarTomMarkerView barTomMarkerView = new BarTomMarkerView(mContext, R.layout.my_markerview1);
        lineChart.setMarker(leftMarkerView, rightMarkerView, barTomMarkerView, mData);
    }

//    private void setBottomMarkerView(KTimeData kDatas) {
//        BarBottomMarkerView bottomMarkerView = new BarBottomMarkerView(mContext, R.layout.my_markerview);
//        barChart.setMarker(bottomMarkerView, kDatas);
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(BaseEvent event) {
        if (event.method == 1) {
            CirclePositionTime position = (CirclePositionTime) event.obj;
            cirCleView.setX(position.cx - CommonUtil.dip2px(mContext, 7));
            cirCleView.setY(position.cy - CommonUtil.dip2px(mContext, 9));
        }
    }

    private void playHeartbeatAnimation(final View heartbeatView) {
        AnimationSet swellAnimationSet = new AnimationSet(true);
        swellAnimationSet.addAnimation(new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
        swellAnimationSet.setDuration(1000);
        swellAnimationSet.setInterpolator(new AccelerateInterpolator());
        swellAnimationSet.setFillAfter(true);//动画终止时停留在最后一帧~不然会回到没有执行之前的状态
        heartbeatView.startAnimation(swellAnimationSet);
        swellAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AnimationSet shrinkAnimationSet = new AnimationSet(true);
                shrinkAnimationSet.addAnimation(new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
                shrinkAnimationSet.setDuration(1000);
                shrinkAnimationSet.setInterpolator(new DecelerateInterpolator());
                shrinkAnimationSet.setFillAfter(false);
                heartbeatView.startAnimation(shrinkAnimationSet);// 动画结束时重新开始，实现心跳的View
                shrinkAnimationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        playHeartbeatAnimation(heartbeatView);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    public void setXLabels(SparseArray<String> xLabels) {
        this.xLabels = xLabels;
    }

    public SparseArray<String> getXLabels() {
//        if (xLabels.size() == 0) {
//            xLabels.put(0, "06:00");
//            xLabels.put(60, "11:30");
//            xLabels.put(121, "17:00");
//            xLabels.put(182, "22:30");
//            xLabels.put(241, "04:00");
        int piut6 = 0;
//            int piut11 = 60;
//            int piut17 = 121;
//            int piut22 = 182;
//            int piut04 = 241;
//            int piut11 = 330;
//            int piut17 = 661;
//            int piut22 = 992;
//            int piut04 = 1321;
        int piut11 = 330;  // 倍数是 330 * 1.54 倍
        int piut17 = 661;
        int piut22 = 992;
        int piut04 = 1321;
//            int piut11 = 19800;
//            int piut17 = 39601;
//            int piut22 = 59402;
//            int piut04 = 79201;

        if (xLabels.size() == 0) {
            time11 = time1 + Math.round(timedou);
            time17 = time1 + Math.round(timedou) * 2;
            time22 = time1 + Math.round(timedou) * 3;
            time04 = time1 + Math.round(timedou) * 4;
        }

//        if (time >= time1 && time <= time11) {
//            piut11 = 660;
//            piut17 = 991;
//            piut22 = 1322;
//            piut04 = 4261;
//        } else if (time > time11 && time <= time17) {
//            piut11 = 600;
//            piut17 = 1651;
//            piut22 = 2642;
//            piut04 = 4261;
//        } else if (time > time17 && time <= time22) {
//            piut11 = 660;
//            piut17 = 991;
//            piut22 = 2641;
//            piut04 = 4261;
//        } else {
//            piut11 = 660;
//            piut17 = 2641;
//            piut22 = 3962;
//            piut04 = 5281;
//        }
//        piut11 = 1320;
//        piut17 = 2641;
//        piut22 = 3962;
//        piut04 = 5281;
        xLabels.put(piut6, getDateToString(time1, "HH:mm") + "");
        xLabels.put(piut11, getDateToString(time11, "HH:mm") + "");
        xLabels.put(piut17, getDateToString(time17, "HH:mm") + "");
        xLabels.put(piut22, getDateToString(time22, "HH:mm") + "");
        xLabels.put(piut04, getDateToString(time04, "HH:mm") + "");
        this.maxCount = piut04;
//        }
        return xLabels;
    }

    private Long date2TimeStamp(String date) {
        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
        date = mYear + "-" + mMonth + "-" + mDay + "  " + date;
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(date).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public void eventBusUnregister() {
        EventBus.getDefault().unregister(this);
    }

    public CoupleChartGestureListener getGestureListenerLine() {
        return gestureListenerLine;
    }

    public CoupleChartGestureListener getGestureListenerBar() {
        return gestureListenerBar;
    }
}
