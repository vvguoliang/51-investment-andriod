package sp51.spotpass.com.spotpass.ui.kchartlib

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import sp51.spotpass.com.spotpass.R

/**
 * @Time : 2018/5/10 no 18:14
 * @USER : vvguoliang
 * @File : TabView.java
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
class TabView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    private val mTextView: TextView
    private val mIndicator: View

    init {
        addView(LayoutInflater.from(getContext()).inflate(R.layout.kchrat_item_tab, null))
        mTextView = findViewById<View>(R.id.tab_text) as TextView
        mIndicator = findViewById(R.id.indicator)
    }

    fun setTextColor(color: ColorStateList?) {
        if (color != null) {
            mTextView.setTextColor(color)
        }
    }

    fun setText(text: String) {
        mTextView.text = text
    }

    fun setIndicatorColor(color: Int) {
        mIndicator.setBackgroundColor(color)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        mIndicator.visibility = if (selected) View.VISIBLE else View.INVISIBLE
    }
}