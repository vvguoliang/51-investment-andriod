package sp51.spotpass.com.spotpass.ui.kchartlib.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * @Time : 2018/5/10 no 18:31
 * @USER : vvguoliang
 * @File : DateUtil.java
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
 * 时间工具类
 */
@SuppressLint("SimpleDateFormat")
object DateUtil {
    var longTimeFormat = SimpleDateFormat("MM-dd HH:mm")
    var shortTimeFormat = SimpleDateFormat("HH:mm")
    var DateFormat = SimpleDateFormat("MM-dd")
    var shTimeFormat = SimpleDateFormat("MM-dd HH:mm")
}