package sp51.spotpass.com.spotpass.ui.http

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.widget.Toast
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.ui.activity.RegisterActivity
import sp51.spotpass.com.spotpass.ui.activity.TokensActivityDialog
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import java.util.HashMap

/**
 * @Time : 2018/4/16 no 下午1:34
 * @USER : vvguoliang
 * @File : HttpOneKey.java
 * @Software: Android Studio
 *code is far away from bugs with the god animal protecting
 *   I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃   ☃   ┃
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
class HttpOnekey private constructor() : HttpOnekeyIM {

    override fun setMap(context: Context, map: Map<String, Any>): Map<String, Any> {
        return getMapProt(context, map)

    }

    override fun setBoolen(context: Context, code: String, msg: String, mHandler: Handler): Boolean? {
        return getObject(context, code, msg, mHandler)
    }

    private fun getMapProt(context: Context, objectMap: Map<String, Any>): Map<String, Any> {
        val map = HashMap<String, Any>()
        return map
    }

    private fun getObject(context: Context, code: String, msg: String, mHandler: Handler): Boolean? {
        var boolea: Boolean? = false
        when (code) {
            "200" -> boolea = true
            "201" -> {
            }
            "202" -> boolea = false
            "203" -> boolea = false
            "204" -> boolea = false
            "205" -> {
            }
            "443" -> {
                SPUtils.getInstance(context, "login").put("login", "")
                val intent = Intent(context, RegisterActivity::class.java)
                intent.putExtra("type", 0)
                context.startActivity(intent)
            }
            "444" -> {
                boolea = false
                ToatUtils.showShort1(context, context.getString(R.string.textView_please_again))
                context.startActivity(Intent(context, TokensActivityDialog::class.java))
            }
            "114" -> {
                boolea = false
                SPUtils.getInstance(context, "login").put("login", "")
                val message = Message()
                message.what = 114
                mHandler.sendMessage(message)
                ToatUtils.showShort1(context, msg)
            }
            "100" -> boolea = true
            else -> {
                boolea = false
                ToatUtils.showShort1(context, msg)
            }
        }
        return boolea
    }

    /**
     * 单例对象实例
     */
    companion object {
        fun get(): HttpOnekey {
            return Inner.httpOnekey
        }
    }

    private object Inner {
        val httpOnekey = HttpOnekey()
    }

}