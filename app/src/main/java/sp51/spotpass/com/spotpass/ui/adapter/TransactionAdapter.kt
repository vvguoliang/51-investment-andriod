package sp51.spotpass.com.spotpass.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v4.view.PagerAdapter
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
 * 交易的件数和几手
 */
class TransactionAdapter : PagerAdapter() {

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
        val map = list!!.get(position)
        val view = LayoutInflater.from(container.context).inflate(R.layout.view_selectionvarieties_viewholder, null)
        //在这里可以做相应的操作
        select_linear = view.findViewById(R.id.select_linear) as LinearLayout
        select_text1 = view.findViewById(R.id.select_text1) as TextView
        select_text2 = view.findViewById(R.id.select_text2) as TextView
        select_text3 = view.findViewById(R.id.select_text3) as TextView
        select_text4 = view.findViewById(R.id.select_text4) as TextView

        select_text1!!.text = map["stkname"] as CharSequence?

        container.addView(view)    //这一步很重要
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}