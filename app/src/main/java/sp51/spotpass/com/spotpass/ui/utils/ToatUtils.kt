package sp51.spotpass.com.spotpass.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import android.view.Gravity


/**
 * @Time : 2018/5/31 no 17:48
 * @USER : vvguoliang
 * @File : ToatUtils.java
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
@SuppressLint("ShowToast")
class ToatUtils private constructor() {

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        var isShow = true

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        private var toast: Toast? = null

        /**
         * 短时间显示Toast
         *
         * @param context
         * @param message
         */
        fun showShort1(context: Context, message: CharSequence) {
            if (toast == null || ToatUtils.context !== context) {
                ToatUtils.context = context
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
                toast!!.setGravity(Gravity.CENTER, 0, 120)
            }
            toast!!.setText(message)
            toast!!.show()
        }

        /**
         * 短时间显示Toast
         *
         * @param context
         * @param message
         */
        fun showShort(context: Context?, message: CharSequence) {

            if (context == null)
                return
            if (isShow)
                ToatUtils.showShort1(context, message)
        }

        /**
         * 短时间显示Toast
         *
         * @param context
         * @param message
         */
        fun showShort(context: Context, message: Int) {
            if (toast == null || ToatUtils.context !== context) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            }
            toast!!.setText(message)
            toast!!.show()
        }

        /**
         * 长时间显示Toast
         *
         * @param context
         * @param message
         */
        fun showLong(context: Context, message: CharSequence) {
            if (toast == null || ToatUtils.context !== context) {
                ToatUtils.context = context
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
                toast!!.setGravity(Gravity.CENTER, 0, 120)
            }
            toast!!.setText(message)
            toast!!.show()
        }

        /**
         * 长时间显示Toast
         *
         * @param context
         * @param message
         */
        fun showLong(context: Context, message: Int) {
            if (toast == null || ToatUtils.context !== context) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            }
            toast!!.setText(message)
            toast!!.show()
        }

        /**
         * 自定义显示Toast时间
         *
         * @param context
         * @param message
         * @param duration
         */
        fun show(context: Context, message: CharSequence, duration: Int) {
            if (toast == null || ToatUtils.context !== context) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            }
            toast!!.setText(message)
            toast!!.show()
        }

        /**
         * 自定义显示Toast时间
         *
         * @param context
         * @param message
         * @param duration
         */
        fun show(context: Context, message: Int, duration: Int) {
            if (toast == null || ToatUtils.context !== context) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            }
            toast!!.setText(message)
            toast!!.show()
        }
    }

}
