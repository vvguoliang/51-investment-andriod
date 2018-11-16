package sp51.spotpass.com.spotpass.ui.viwepage.transformer

import android.support.v4.view.ViewPager
import android.view.View

/**
 * @Time : 2018/5/4 no 11:57
 * @USER : vvguoliang
 * @File : ScaleYTransformer1.java
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
class ScaleYTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {

        if (position < -1) {
            page.scaleY = MIN_SCALE
        } else if (position <= 1) {
            //
            val scale = Math.max(MIN_SCALE, 1 - Math.abs(position))
            page.scaleY = scale
            /*page.setScaleX(scale);

            if(position<0){
                page.setTranslationX(width * (1 - scale) /2);
            }else{
                page.setTranslationX(-width * (1 - scale) /2);
            }*/

        } else {
            page.scaleY = MIN_SCALE
        }
    }

    companion object {
        private val MIN_SCALE = 0.9f
    }

}