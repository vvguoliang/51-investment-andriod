package com.github.mikephil.charting.stockChart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.NumberUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeftMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    private TextView markerTv1;
    private TextView markerTv2;
    private TextView markerTv3;
    private TextView markerTv4;
    private TextView markerTv5;
    private TextView marker_tv6;
    private float yValForXIndex1;
    private float yValOpen1;
    private float yValHigh1;
    private float yValLow1;
    private Long yValDateMills1;
    private int precision;

    private boolean layou = false;

    public LeftMarkerView(Context context, int layoutResource, int precision) {
        super(context, layoutResource);
        if (layoutResource == R.layout.my_markerview1) {
            markerTv1 = findViewById(R.id.marker_tv);
            markerTv1.setTextSize(10);
            layou = true;
        } else {
            layou = false;
            marker_tv6 = findViewById(R.id.marker_tv6);
            marker_tv6.setTextSize(10);
            markerTv1 = findViewById(R.id.marker_tv1);
            markerTv1.setTextSize(10);
            markerTv2 = findViewById(R.id.marker_tv2);
            markerTv2.setTextSize(10);
            markerTv3 = findViewById(R.id.marker_tv3);
            markerTv3.setTextSize(10);
            markerTv4 = findViewById(R.id.marker_tv4);
            markerTv4.setTextSize(10);
            markerTv5 = findViewById(R.id.marker_tv5);
            markerTv5.setTextSize(10);
        }
        this.precision = precision;
    }

    public void setData(float yValForXIndex1) {
        this.yValForXIndex1 = yValForXIndex1;
    }

    public void setData1(float yValForXIndex1, float yValOpen1, float yValHigh1, float yValLow1, Long yValDateMills1) {
        this.yValForXIndex1 = yValForXIndex1;
        this.yValOpen1 = yValOpen1;
        this.yValHigh1 = yValHigh1;
        this.yValLow1 = yValLow1;
        this.yValDateMills1 = yValDateMills1;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (layou) {
            markerTv1.setText(NumberUtils.keepPrecisionR(yValForXIndex1, precision));
        } else {
            String[] getData = getDateToString(yValDateMills1, "yyy-MM-dd HH:mm").split(" ");
            marker_tv6.setText(getData[0]);
            markerTv1.setText("时间  " + getData[1]);
            markerTv2.setText("开盘  " + NumberUtils.keepPrecisionR(yValOpen1, precision));
            markerTv3.setText("收盘  " + NumberUtils.keepPrecisionR(yValForXIndex1, precision));
            markerTv4.setText("最高  " + NumberUtils.keepPrecisionR(yValHigh1, precision));
            markerTv5.setText("最低  " + NumberUtils.keepPrecisionR(yValLow1, precision));
        }
    }

    private String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
