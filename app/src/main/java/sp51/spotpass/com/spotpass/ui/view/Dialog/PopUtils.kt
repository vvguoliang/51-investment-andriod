@file:Suppress("UNCHECKED_CAST")

package sp51.spotpass.com.spotpass.ui.view.Dialog

import android.widget.TextView
import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.graphics.drawable.ColorDrawable
import android.widget.PopupWindow
import android.view.LayoutInflater
import android.util.SparseArray
import android.view.View
import android.widget.ImageView


/**
 * @Time : 2018/5/31 no 20:50
 * @USER : vvguoliang
 * @File : PopUtils.java
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
 * 封装的PopupWidow的工具类
 * 用英文装个逼不容易啊--调用很简单，直接new完传递参数，实现方法，通过builder调用方法，可以自己扩展
 */
abstract class PopUtils
/**
 * The constructor of MyPopupWindow
 *
 * @param context     Interface to global information about an application environment
 * @param layoutResId The resource id of the layout embedded inside the PopupWindow
 * @param width       the width of the PopupWindow
 * @param height      the height of the PopupWindow
 * @param view        a parent view to get the android.view.View.getWindowToken() token from
 * @param gravity     the gravity which controls the placement of the popup window
 * @param x           the PopupWindow's x location offset
 * @param y           the PopupWindow's y location offset
 */
(mContext: Context, mlayoutResId: Int, mwidth: Int, mHeight: Int, view: View?, gravity: Int, x: Int, y: Int, callback: ClickListener) {

    private var mCallback: ClickListener? = null

    init {

        setCallBack(callback)

        val builder = PopBuilder.createPopupWindow(mContext, mlayoutResId, mwidth, mHeight, view!!, gravity, x, y, mCallback)
    }

    class PopBuilder private constructor(context: Context, private val mItem: View) {

        private val mViews: SparseArray<View> = SparseArray()

        /**
         * 得到视图
         *
         * @param id 控件资源id
         * @param <T> 类型
         *
         * @return T
        </T> */
        fun <T : View> getView(id: Int): T? {
            var t: T? = mViews.get(id) as T?
            if (t == null) {
                t = mItem.findViewById(id)
                mViews.put(id, t)
            }
            return t
        }

        /**
         * 使窗口消失
         * @return
         */
        fun dismiss(): PopBuilder {
            if (window != null) {
                window!!.dismiss()
            }
            return this
        }

        /**
         * 设置是否可见
         *
         * @param id         控件id
         * @param visibility 是否可见
         *
         * @return PopBuilder
         */
        fun setVisibility(id: Int, visibility: Int): PopBuilder {
            getView<View>(id)!!.visibility = visibility
            return this
        }

        /**
         * 设置图片资源
         *
         * @param id          控件id
         * @param drawableRes drawable资源id
         *
         * @return PopBuilder
         */
        fun setImageResource(id: Int, drawableRes: Int): PopBuilder {
            val view = getView<View>(id)
            if (view is ImageView) {
                view.setImageResource(drawableRes)
            } else {
                view!!.setBackgroundResource(drawableRes)
            }
            return this
        }

        /**
         * 设置文本
         *
         * @param id   控件id
         * @param text 文本内容
         *
         * @return PopBuilder
         */
        fun setText(id: Int, text: CharSequence): PopBuilder {
            val view = getView<View>(id)
            if (view is TextView) {
                view.text = text
            }
            return this
        }

        companion object {

            private var window: PopupWindow? = null

            fun createPopupWindow(context: Context, layoutResId: Int, width: Int, height: Int, parent: View, gravity: Int, x: Int, y: Int, callback: ClickListener?): PopBuilder {

                // 利用layoutInflater获得View
                val inflater = (context as Activity)
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view = inflater.inflate(layoutResId, null)

                val builder = PopBuilder(context, view)

                window = PopupWindow(view, width, height)

                // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
                window!!.isFocusable = true
                window!!.isTouchable = true

                // 设置触摸外面时消失
                window!!.isOutsideTouchable = true
                window!!.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN

                // 监听PopupWindow关闭，如果为关闭状态则设置为空
                window!!.setOnDismissListener {
                    window = null

                    // 主界面完全显示
                    val params = context.window.attributes
                    params.alpha = 1.0f
                    context.window.attributes = params
                }

                // 实例化一个ColorDrawable颜色为透明，不设置为半透明是因为带圆角
                val dw = ColorDrawable(context.getResources().getColor(android.R.color.transparent))
                window!!.setBackgroundDrawable(dw)

                window!!.showAtLocation(parent, gravity, x, y)

                // 主界面变暗
                val params = context.window.attributes
                params.alpha = 0.4f
                context.window.attributes = params


                //点击事件回调
                if (window != null) {
                    callback!!.setUplistener(builder)
                }

                return builder

            }
        }
    }

    /**
     * 用于回调的接口
     */
    interface ClickListener {
        fun setUplistener(builder: PopBuilder)
    }

    /**
     * 设置回调对象
     * @param callBack 回调对象
     */
    private fun setCallBack(callBack: ClickListener) {
        this.mCallback = callBack
    }
}
