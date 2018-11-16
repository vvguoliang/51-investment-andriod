package sp51.spotpass.com.spotpass.ui.kchartlib.formatter

import sp51.spotpass.com.spotpass.ui.kchartlib.base.IValueFormatter
import java.util.*

/**
 * @Time : 2018/5/10 no 18:24
 * @USER : vvguoliang
 * @File : formatter.java
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
 *
 * 对较大数据进行格式化
 */
class BigValueFormatter : IValueFormatter {

    //必须是排好序的
    private val values = intArrayOf(10000, 1000000, 100000000)
    private val units = arrayOf("万", "百万", "亿")

    override fun format(value: Float): String {
        var value = value
        var unit = ""
        var i = values.size - 1
        while (i >= 0) {
            if (value > values[i]) {
                value /= values[i].toFloat()
                unit = units[i]
                break
            }
            i--
        }
        return String.format(Locale.getDefault(), "%.2f", value) + unit
    }
}
