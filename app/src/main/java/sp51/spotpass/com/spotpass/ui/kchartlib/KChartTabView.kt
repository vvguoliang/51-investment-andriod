package sp51.spotpass.com.spotpass.ui.kchartlib

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.ViewUtil

/**
 * @Time : 2018/5/10 no 18:34
 * @USER : vvguoliang
 * @File : KChartTabView.java
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
class KChartTabView : RelativeLayout, View.OnClickListener {

    internal lateinit var mLlContainer: LinearLayout
    internal lateinit var mTvFullScreen: TextView
    private var mTabSelectListener: TabSelectListener? = null
    //当前选择的index
    private var mSelectedIndex = 0
    private var mColorStateList: ColorStateList? = null
    private var mIndicatorColor: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        val view = LayoutInflater.from(context).inflate(R.layout.kchrat_layout_tab, null, false)
        val layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtil.Dp2Px(context, 30))
        view.setLayoutParams(layoutParams)
        addView(view)
        mLlContainer = findViewById<View>(R.id.ll_container) as LinearLayout
        mTvFullScreen = findViewById<View>(R.id.tv_fullScreen) as TextView
        mTvFullScreen.setOnClickListener {
            val activity = context as Activity
            val isVertical = context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
            if (isVertical) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            } else {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            }
        }
        mTvFullScreen.isSelected = true
        if (mColorStateList != null) {
            mTvFullScreen.setTextColor(mColorStateList)
        }
    }

    override fun onClick(v: View) {
        if (mSelectedIndex >= 0 && mSelectedIndex < mLlContainer.childCount) {
            mLlContainer.getChildAt(mSelectedIndex).isSelected = false
        }
        mSelectedIndex = mLlContainer.indexOfChild(v)
        v.isSelected = true
        mTabSelectListener!!.onTabSelected(mSelectedIndex)
    }

    interface TabSelectListener {
        /**
         * 选择tab的位置序号
         *
         * @param position
         */
        fun onTabSelected(position: Int)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 添加选项卡
     *
     * @param text 选项卡文字
     */
    fun addTab(text: String) {
        val tabView = sp51.spotpass.com.spotpass.ui.kchartlib.TabView(context)
        tabView.setOnClickListener(this)
        tabView.setText(text)
        tabView.setTextColor(mColorStateList)
        tabView.setIndicatorColor(mIndicatorColor)
        mLlContainer.addView(tabView)
        //第一个默认选中
        if (mLlContainer.childCount == 1) {
            tabView.isSelected = true
            mSelectedIndex = 0
            onTabSelected(mSelectedIndex)
        }
    }

    /**
     * 设置选项卡监听
     *
     * @param listener TabSelectListener
     */
    fun setOnTabSelectListener(listener: TabSelectListener) {
        this.mTabSelectListener = listener
        //默认选中上一个位置
        if (mLlContainer.childCount > 0 && mTabSelectListener != null) {
            mTabSelectListener!!.onTabSelected(mSelectedIndex)
        }
    }

    private fun onTabSelected(position: Int) {
        if (mTabSelectListener != null) {
            mTabSelectListener!!.onTabSelected(position)
        }
    }

    fun setTextColor(color: ColorStateList) {
        mColorStateList = color
        for (i in 0 until mLlContainer.childCount) {
            val tabView = mLlContainer.getChildAt(i) as TabView
            tabView.setTextColor(mColorStateList)
        }
        if (mColorStateList != null) {
            mTvFullScreen.setTextColor(mColorStateList)
        }
    }

    fun setIndicatorColor(color: Int) {
        mIndicatorColor = color
        for (i in 0 until mLlContainer.childCount) {
            val tabView = mLlContainer.getChildAt(i) as TabView
            tabView.setIndicatorColor(mIndicatorColor)
        }
    }

}
