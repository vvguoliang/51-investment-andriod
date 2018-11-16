package sp51.spotpass.com.spotpass.ui.kchartlib.utils

import android.text.TextUtils
import sp51.spotpass.com.spotpass.ui.http.AfferentDataHttpMap


/**
 * @Time : 2018/5/28 no 15:20
 * @USER : vvguoliang
 * @File : ConvertUtil.java
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
class ConvertUtil {

    /**
     * 单例对象实例
     */
    companion object {
        fun get(): ConvertUtil {
            return Inner.convertutil
        }
    }

    private object Inner {
        val convertutil = ConvertUtil()
    }

    //把String转化为float
    fun convertToFloat(number: String, defaultValue: Float): Float {
        if (number == null || number.isEmpty()) {
            return defaultValue
        }
        try {
            return java.lang.Float.parseFloat(number)
        } catch (e: Exception) {
            return defaultValue
        }

    }

    //把String转化为double
    fun convertToDouble(number: String, defaultValue: Double): Double {
        if (TextUtils.isEmpty(number)) {
            return defaultValue
        }
        try {
            return java.lang.Double.parseDouble(number)
        } catch (e: Exception) {
            return defaultValue
        }

    }

    //把String转化为int
    fun convertToInt(number: String, defaultValue: Int): Int {
        if (TextUtils.isEmpty(number)) {
            return defaultValue
        }
        try {
            return Integer.parseInt(number)
        } catch (e: Exception) {
            return defaultValue
        }

    }

}