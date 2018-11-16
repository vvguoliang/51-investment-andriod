package sp51.spotpass.com.spotpass.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView


/**
 * @Time : 2018/5/17 no 21:22
 * @USER : vvguoliang
 * @File : MyBankCardAdapter.java
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
class MyBankCardAdapter(val context: Context) : BaseAdapter() {

    private var list: List<Map<String, String?>>? = null

    fun getListData(list: List<Map<String, String?>>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        if (list != null && list!!.isNotEmpty()) {
            return list!!.size
        } else {
            return 0
        }
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val holder: TestViewHolder
        val v: View
        if (convertView == null) {
            v = View.inflate(context, R.layout.view_mybankcard_adapter_item, null)
            holder = TestViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as TestViewHolder
        }
        if (list != null && list!!.isNotEmpty()) {
            val cart = list!![position]["cardNo"]!!.substring(list!![position]["cardNo"]!!.length - 4, list!![position]["cardNo"]!!.length)
            holder.my_bank_item_name.text = list!![position]["bankName"]
            holder.my_bank_item_moer.text = "**** **** **** $cart"
            val options = RequestOptions()
            options.centerCrop().placeholder(R.mipmap.ic_banner).error(R.mipmap.ic_banner).fallback(R.mipmap.ic_banner)
            Glide.with(context).load(list!!.get(position).get("url")).apply(options).into(holder.my_bank_item_image)
        }
        return v
    }

    override fun getItem(position: Int): Any? {
        return list!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class TestViewHolder(viewItem: View) {
        var my_bank_item_linear: LinearLayout = viewItem.findViewById(R.id.my_bank_item_linear) as LinearLayout
        var my_bank_item_image: ImageView = viewItem.findViewById(R.id.my_bank_item_image) as CircleImageView
        var my_bank_item_name: TextView = viewItem.findViewById(R.id.my_bank_item_name) as TextView
        var my_bank_item_bank: TextView = viewItem.findViewById(R.id.my_bank_item_bank) as TextView
        var my_bank_item_moer: TextView = viewItem.findViewById(R.id.my_bank_item_moer) as TextView
    }

}
