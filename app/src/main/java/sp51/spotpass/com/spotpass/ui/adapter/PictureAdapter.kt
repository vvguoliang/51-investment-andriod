@file:Suppress("NAME_SHADOWING")

package sp51.spotpass.com.spotpass.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.R.id.recharge_adapter_linear


/**
 * @Time : 2018/5/16 no 21:23
 * @USER : vvguoliang
 * @File : PictureAdapter.java
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
class PictureAdapter(val context: Context) : BaseAdapter() {

    private var recharge_adapter: ArrayList<String>? = null

    private var iInt: Int = count - 1

    fun PictureAdapter(recharge_adapter: ArrayList<String>) {
        this.recharge_adapter = recharge_adapter
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return if (recharge_adapter != null && recharge_adapter!!.size > 0) {
            recharge_adapter!!.size
        } else {
            0
        }
    }

    override fun getItem(position: Int): Any {
        return recharge_adapter!![position]
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewHolder: ViewHolder?
        if (convertView == null) {
            viewHolder = ViewHolder()
            // 获得容器
            convertView = LayoutInflater.from(this.context).inflate(R.layout.view_recharge_adapter, null)
            // 初始化组件
            viewHolder.recharge_adapter_linear = convertView.findViewById(R.id.recharge_adapter_linear)
            viewHolder.title = convertView.findViewById(R.id.recharge_adapter_text)
            // 给converHolder附加一个对象
            convertView.tag = viewHolder
        } else {
            // 取得converHolder附加的对象
            viewHolder = convertView.tag as ViewHolder?
        }

        if (recharge_adapter!!.size > 0) {
            // 给组件设置资源
            viewHolder!!.title!!.text = recharge_adapter!![position]
            if (position == iInt) {
                viewHolder.recharge_adapter_linear!!.setBackgroundResource(R.drawable.shape_c5_red)
                viewHolder.title!!.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                viewHolder.recharge_adapter_linear!!.setBackgroundResource(R.drawable.shape_corner_red_while)
                viewHolder.title!!.setTextColor(Color.parseColor("#FF6D64"))
            }
        }
        return convertView!!
    }

    fun getViweH(i: Int) {
        this.iInt = i
        notifyDataSetChanged()
    }


    internal inner class ViewHolder {
        var title: TextView? = null
        var recharge_adapter_linear: LinearLayout? = null
    }

}