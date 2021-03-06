package sp51.spotpass.com.spotpass.ui.kchartlib.formatter

import sp51.spotpass.com.spotpass.ui.kchartlib.base.IDateTimeFormatter
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.DateUtil
import java.util.*

/**
 * @Time : 2018/5/18 no 15:37
 * @USER : vvguoliang
 * @File : DateFormatter.java
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
class DateFormatter : IDateTimeFormatter {
    override fun format(date: Date, int: Int): String {
        if (int == 1) {
            if (date != null) { //未拉升
                return DateUtil.shortTimeFormat.format(date)
            } else {
                return ""
            }
        } else if (int == 2) { // 拉升
            if (date != null) {
                return DateUtil.shTimeFormat.format(date)
            } else {
                return ""
            }
        } else {
            if (date != null) { // 默认
                return DateUtil.DateFormat.format(date)
            } else {
                return ""
            }
        }
    }
}