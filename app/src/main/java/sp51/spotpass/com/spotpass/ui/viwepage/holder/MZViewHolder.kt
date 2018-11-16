package sp51.spotpass.com.spotpass.ui.viwepage.holder

import android.content.Context
import android.view.View

/**
 * @Time : 2018/5/4 no 11:54
 * @USER : vvguoliang
 * @File : MZViewHolder.java
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
interface MZViewHolder<T> {
    /**
     * 创建View
     * @param context
     * @return
     */
    fun createView(context: Context): View

    /**
     * 绑定数据
     * @param context
     * @param position
     * @param data
     */
    fun onBind(context: Context, position: Int, data: T)
}