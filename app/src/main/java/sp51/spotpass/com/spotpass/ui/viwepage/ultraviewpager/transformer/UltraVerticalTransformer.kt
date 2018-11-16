package sp51.spotpass.com.spotpass.ui.viwepage.ultraviewpager.transformer

import android.support.v4.view.ViewPager
import android.view.View

/**
 * @Time : 2018/5/23 no 19:32
 * @USER : vvguoliang
 * @File : UltraVerticalTransformer1.java
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
class UltraVerticalTransformer : ViewPager.PageTransformer {
    var position: Float = 0.toFloat()
        private set

    override fun transformPage(view: View, position: Float) {
        view.translationX = view.width * -position
        this.position = position * view.height
        view.translationY = this.position
    }
}
