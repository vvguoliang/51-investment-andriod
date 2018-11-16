package sp51.spotpass.com.spotpass.ui.http

import okhttp3.Request
import java.io.IOException

/**
 * @Time : 2018/4/2 no 下午10:58
 * @USER : vvguoliang
 * @File : DataCallBack.java
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
interface DataCallBack {

    fun requestFailure(request: Request, name: String, e: IOException)

    @Throws(Exception::class)
    fun requestSuccess(result: String, name: String)
}