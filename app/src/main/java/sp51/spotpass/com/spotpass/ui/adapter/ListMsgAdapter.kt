package sp51.spotpass.com.spotpass.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.ui.baseEntity.CirclessList

/**
 * @Time : 2018/6/4 no 17:04
 * @USER : vvguoliang
 * @File : ListMsgAdapter.java
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
class ListMsgAdapter(private val context: Context) : BaseAdapter() {

    private var list: List<CirclessList.Data.Data.Comments>? = null

    fun getData(list: List<CirclessList.Data.Data.Comments>) {
        this.list = list
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val viewHolder: ViewHolder?
        if (convertView == null) {

            viewHolder = ViewHolder()
            // 获得容器
            convertView = LayoutInflater.from(this.context).inflate(R.layout.view_list_masg_adapter_item, null)
            // 初始化组件
            viewHolder.interaction_simple_name_loan1 = convertView.findViewById(R.id.interaction_simple_name_loan1)
            viewHolder.interaction_simple_name_loan1_content1 = convertView.findViewById(R.id.interaction_simple_name_loan1_content1)
            // 给converHolder附加一个对象
            convertView.tag = viewHolder
        } else {
            // 取得converHolder附加的对象
            viewHolder = convertView.tag as ViewHolder?
        }
        viewHolder!!.interaction_simple_name_loan1!!.text = "${list!![position].user.name}:"
        viewHolder.interaction_simple_name_loan1_content1!!.text = list!![position].content

        return convertView!!
    }

    override fun getItem(position: Int): Any {
        return list!![position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        if (list != null && list!!.size > 0) {
            return list!!.size
        } else {
            return 0
        }
    }

    internal inner class ViewHolder {
        var interaction_simple_name_loan1: TextView? = null
        var interaction_simple_name_loan1_content1: TextView? = null
    }
}