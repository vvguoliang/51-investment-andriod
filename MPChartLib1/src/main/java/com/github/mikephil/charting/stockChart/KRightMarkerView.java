package com.github.mikephil.charting.stockChart;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class KRightMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    private TextView markerTv1;
    private TextView marker_tv6;
    private TextView markerTv2;
    private TextView markerTv3;
    private TextView markerTv4;
    private TextView markerTv5;
    private float yValForXIndex2;
    private float yValOpen2;
    private float yValHigh2;
    private float yValLow2;
    private Long yValDateMills2;
    private int precision;

    public KRightMarkerView(Context context, int layoutResource, int precision) {
        super(context, layoutResource);
        this.precision = precision;
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

    public void setData(float yValForXIndex2, float yValOpen2, float yValHigh2, float yValLow2, Long yValDateMills2) {
        this.yValForXIndex2 = yValForXIndex2;
        this.yValOpen2 = yValOpen2;
        this.yValHigh2 = yValHigh2;
        this.yValLow2 = yValLow2;
        this.yValDateMills2 = yValDateMills2;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String[] getData = getDateToString(yValDateMills2, "yyy-MM-dd HH:mm").split(" ");
        marker_tv6.setText(getData[0]);
        markerTv1.setText("时间  " + getData[1]);
        markerTv2.setText("开盘  " + NumberUtils.keepPrecisionR(yValOpen2, precision));
        markerTv3.setText("收盘  " + NumberUtils.keepPrecisionR(yValForXIndex2, precision));
        markerTv4.setText("最高  " + NumberUtils.keepPrecisionR(yValHigh2, precision));
        markerTv5.setText("最低  " + NumberUtils.keepPrecisionR(yValLow2, precision));
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
