package sp51.spotpass.com.spotpass.ui.view.Dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.act_add_bank_card.view.*
import sp51.spotpass.com.spotpass.R


/**
 * @Time : 2018/5/31 no 17:52
 * @USER : vvguoliang
 * @File : BottomDialog.java
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
/**
 * 自定义dialog
 *
 * vvguolaing 2017-06-29
 */
@SuppressLint("UseSparseArrays")
class BottomDialog(context: Context, layout: Int) : Dialog(context, R.style.customDialog), View.OnClickListener {

    private var view: View = LayoutInflater.from(context).inflate(layout, null)
    private var map: MutableMap<Int, View.OnClickListener> = HashMap()
    private var width: Int = 0
    private var height: Int = 0

    fun setWidthHeight(width: Int, height: Int) {
        this.width = width
        this.height = height
    }

    fun setOnClick(viewId: Int, onClickListener: View.OnClickListener) {
        map[viewId] = onClickListener
        val view = Companion.findViewById(this, viewId)
        view.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)
        if (width <= 0) {
        } else {
            view.layoutParams.width = width
        }
        if (height <= 0) {
        } else {
            view.layoutParams.height = height
        }
        view.layoutParams = view.layoutParams
    }

    override fun onClick(v: View) {
        dismiss()
        if (map.containsKey(v.id) && map[v.id] != null) {
            map[v.id]!!.onClick(v)
        }
    }

    companion object {
        fun findViewById(bottomDialog: BottomDialog, id: Int): View {
            return bottomDialog.view.findViewById(id)
        }
    }
}
