package sp51.spotpass.com.spotpass.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.GridView



/**
 * @Time : 2018/5/16 no 21:22
 * @USER : vvguoliang
 * @File : CustomeGridView.java
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
 * 自定义上下不滚动的GridView
 */
class CustomeGridView(context: Context, attrs: AttributeSet) : GridView(context, attrs) {

    /**
     * 设置上下不滚动
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return if (ev.action == MotionEvent.ACTION_MOVE) {
            true//true:禁止滚动
        } else super.dispatchTouchEvent(ev)

    }
}