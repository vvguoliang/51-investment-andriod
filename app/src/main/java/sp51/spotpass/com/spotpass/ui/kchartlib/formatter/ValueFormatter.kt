package sp51.spotpass.com.spotpass.ui.kchartlib.formatter

import sp51.spotpass.com.spotpass.ui.kchartlib.base.IValueFormatter

/**
 * @Time : 2018/5/10 no 18:59
 * @USER : vvguoliang
 * @File : ValueFormatter.java
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
 * Value格式化类
 */

class ValueFormatter : IValueFormatter {
    override fun format(value: Float): String {
        return String.format("%.2f", value)
    }
}