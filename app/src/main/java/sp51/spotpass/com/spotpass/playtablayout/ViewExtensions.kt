package sp51.spotpass.com.spotpass.playtablayout

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Property
import android.view.View
/**
 * @Time : 2018/5/8 no 12:46
 * @USER : vvguoliang
 * @File : ViewExtensions.java
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
internal object ViewUtils {

    abstract class IntProperty<T>(name: String) : Property<T, Int>(Int::class.java, name) {

        abstract fun setValue(type: T, value: Int)

        override fun set(type: T, value: Int?) = setValue(type, value!!.toInt())
    }
}

internal val View.BACKGROUND_COLOR: Property<View, Int>
    get() = object : ViewUtils.IntProperty<View>("backgroundColor") {

        override fun setValue(type: View, value: Int) = type.setBackgroundColor(value)

        override fun get(view: View) = (background as? ColorDrawable)?.color
                ?: Color.TRANSPARENT
    }