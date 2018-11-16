package sp51.spotpass.com.spotpass.ui.http

import android.content.Context
import android.os.Handler

/**
 * @Time : 2018/4/16 no 下午1:33
 * @USER : vvguoliang
 * @File : HttpOnekeyIM.java
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
interface HttpOnekeyIM {

    abstract fun setMap(context: Context, map: Map<String, Any>): Map<String, Any>

    /**
     * 判断code 返回值时候成立
     * @param context
     * @param code
     * @param msg
     * @return
     */
    abstract fun setBoolen(context: Context, code: String, msg: String, mHandler: Handler): Boolean?
}