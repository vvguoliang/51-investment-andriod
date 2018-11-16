package sp51.spotpass.com.spotpass.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.ViewGroup
import java.util.*
import android.widget.TextView
import sp51.spotpass.com.spotpass.MainActivity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.taobao.accs.utl.l
import sp51.spotpass.com.spotpass.R


/**
 * @Time : 2018/5/23 no 17:56
 * @USER : vvguoliang
 * @File : SelectionVarietiesAdapter.java
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
class SelectionVarietiesAdapter : PagerAdapter() {

    private var list: List<Map<String, Any>>? = null

    private var int: Int = 0

    var select_linear: LinearLayout? = null
    var select_text1: TextView? = null
    var select_text2: TextView? = null
    var select_text3: TextView? = null
    var select_text4: TextView? = null

    fun getData(list: List<Map<String, Any>>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun getINt(int: Int) {
        this.int = int
        notifyDataSetChanged()
    }

    override fun getPageWidth(position: Int): Float {
        return 0.6f
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    private var mChildCount = 0

    override fun notifyDataSetChanged() {
        mChildCount = count
        super.notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any): Int {
        if (mChildCount > 0) {
            mChildCount--
            return PagerAdapter.POSITION_NONE
        }
        return super.getItemPosition(`object`)
    }


    override fun getCount(): Int {
        if (list != null && list!!.isNotEmpty()) {
            return list!!.size
        } else {
            return 0
        }
    }

    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.view_selectionvarieties_viewholder, null)
        //在这里可以做相应的操作
        select_linear = view.findViewById(R.id.select_linear) as LinearLayout
        select_text1 = view.findViewById(R.id.select_text1) as TextView
        select_text2 = view.findViewById(R.id.select_text2) as TextView
        select_text3 = view.findViewById(R.id.select_text3) as TextView
        select_text4 = view.findViewById(R.id.select_text4) as TextView

        if (list != null && list!!.isNotEmpty()) {
            val map = list!![position]
            select_text1!!.text = map["stkname"] as CharSequence?

            val spannableString = SpannableString("波动1个点赚${map["holdfeerate"]}元")
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF6D64")), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF6D64")), spannableString.length - 1, spannableString.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            select_text4!!.text = spannableString

            select_text3!!.text = map["marginrate"] as CharSequence?

            if (int == position) {
                select_linear!!.setBackgroundResource(R.drawable.shape_corner_while_yellowish)
                select_text1!!.setTextColor(Color.parseColor("#FF6D64"))
                select_text2!!.setTextColor(Color.parseColor("#FF6D64"))
                select_text3!!.setTextColor(Color.parseColor("#DF3536"))
            } else {
                select_linear!!.setBackgroundResource(R.drawable.shape_corner_yellowish_while)
                select_text1!!.setTextColor(Color.parseColor("#333333"))
                select_text2!!.setTextColor(Color.parseColor("#333333"))
                select_text3!!.setTextColor(Color.parseColor("#333333"))
            }
        }
        container.addView(view)    //这一步很重要
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as LinearLayout
        container.removeView(view)
    }

}