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

/**
 * Created by Administrator on 2016/9/12.
 */
@SuppressLint("ViewConstructor")
public class BarTomMarkerView extends MarkerView {

    private TextView markerTv;
    private String date;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public BarTomMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        markerTv = findViewById(R.id.marker_tv);
        markerTv.setTextSize(10);
    }

    public void setData(String date) {
        this.date = date;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        markerTv.setText(date);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

}
