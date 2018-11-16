package sp51.spotpass.com.spotpass.ui.viwepage.ultraviewpager.transformer

import android.support.v4.view.ViewPager
import android.view.View

/**
 * @Time : 2018/5/23 no 19:30
 * @USER : vvguoliang
 * @File : UltraDepthScaleTransformer1.java
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
class UltraDepthScaleTransformer : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val scale = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
        val rotation = MAX_ROTATION * Math.abs(position)

        if (position <= 0f) {
            view.translationX = view.width.toFloat() * -position * 0.19f
            view.pivotY = 0.5f * view.height
            view.pivotX = 0.5f * view.width
            view.scaleX = scale
            view.scaleY = scale
            view.rotationY = rotation
        } else if (position <= 1f) {
            view.translationX = view.width.toFloat() * -position * 0.19f
            view.pivotY = 0.5f * view.height
            view.pivotX = 0.5f * view.width
            view.scaleX = scale
            view.scaleY = scale
            view.rotationY = -rotation
        }
    }

    companion object {
        private val MIN_SCALE = 0.5f
        private val MAX_ROTATION = 30f
    }
}