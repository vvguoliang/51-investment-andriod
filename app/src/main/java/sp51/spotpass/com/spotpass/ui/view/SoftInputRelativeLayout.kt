package sp51.spotpass.com.spotpass.ui.view

import android.content.Context
import android.graphics.Rect
import android.support.v4.view.ViewCompat.onApplyWindowInsets
import android.os.Build
import android.util.AttributeSet
import android.view.WindowInsets
import android.widget.RelativeLayout



/**
 * @Time : 2018/5/7 no 20:53
 * @USER : vvguoliang
 * @File : SoftInputRelativeLayout.java
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
 * 使用自定义相对布局进行编辑框不掩盖
 */
class SoftInputRelativeLayout : RelativeLayout {

    private val mInsets = IntArray(4)

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun computeSystemWindowInsets(`in`: WindowInsets, outLocalInsets: Rect): WindowInsets {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Intentionally do not modify the bottom inset. For some reason,
            // if the bottom inset is modified, window resizing stops working.
            // TODO: Figure out why.

            mInsets[0] = outLocalInsets.left
            mInsets[1] = outLocalInsets.top
            mInsets[2] = outLocalInsets.right

            outLocalInsets.left = 0
            outLocalInsets.top = 0
            outLocalInsets.right = 0
        }

        return super.computeSystemWindowInsets(`in`, outLocalInsets)

    }

    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            mInsets[0] = insets.systemWindowInsetLeft
            mInsets[1] = insets.systemWindowInsetTop
            mInsets[2] = insets.systemWindowInsetRight
            return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0,
                    insets.systemWindowInsetBottom))
        } else {
            return insets
        }
    }

}