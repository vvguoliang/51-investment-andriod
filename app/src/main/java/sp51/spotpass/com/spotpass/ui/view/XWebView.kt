package sp51.spotpass.com.spotpass.ui.view

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

/**
 * @Time : 2018/5/2 no 下午6:30
 * @USER : vvguoliang
 * @File : XWebView.java
 * @Software: Android Studio
 *code is far away from bugs with the god animal protecting
 *   I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃   ☃   ┃
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
class XWebView : WebView {

    val isBottom: Boolean
        get() = computeVerticalScrollRange() == height + scrollY

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    public override fun computeVerticalScrollRange(): Int {
        return super.computeVerticalScrollRange()
    }
}
