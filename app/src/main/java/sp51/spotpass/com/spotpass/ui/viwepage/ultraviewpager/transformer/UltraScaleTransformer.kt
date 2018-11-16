package sp51.spotpass.com.spotpass.ui.viwepage.ultraviewpager.transformer

import android.support.v4.view.ViewPager
import android.view.View

/**
 * @Time : 2018/5/23 no 19:31
 * @USER : vvguoliang
 * @File : UltraScaleTransformer1.java
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
class UltraScaleTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))

        if (position < 0) { // [-1,0]
            // Scale the page down (between MIN_SCALE and 1)
            page.scaleX = scaleFactor
            page.scaleY = scaleFactor

        } else if (position == 0f) {
            page.scaleX = 1f
            page.scaleY = 1f

        } else if (position <= 1) { // (0,1]
            // Scale the page down (between MIN_SCALE and 1)
            page.scaleX = scaleFactor
            page.scaleY = scaleFactor

        }
    }

    companion object {
        private val MIN_SCALE = 0.75f
    }
}
