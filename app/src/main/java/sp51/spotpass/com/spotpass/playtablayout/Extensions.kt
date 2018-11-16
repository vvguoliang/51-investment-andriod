package sp51.spotpass.com.spotpass.playtablayout

import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat

/**
 * @Time : 2018/5/8 no 12:46
 * @USER : vvguoliang
 * @File : Extensions.java
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
internal fun Context.color(colorResId: Int) = ContextCompat.getColor(this, colorResId)

internal inline fun on21orAbove(up: () -> Unit, down: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        up()
    } else {
        down()
    }
}