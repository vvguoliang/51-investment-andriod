package sp51.spotpass.com.spotpass.base

import android.os.Bundle
import android.view.View

/**
 * @Time : 2018/3/20 no 下午2:21
 * @USER : vvguoliang
 * @File : IViewSpecification.java
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
interface IViewSpecification {

    /**
     * 初始化Bundle参数
     */
    fun initParams(arguments: Bundle?)

    /**
     * 绑定布局
     */
    fun bindLayout(): Int

    /**
     * 初始化view
     */
    fun initView(rootView: View)

    /**
     * 设置监听
     */
    fun setListener()

    /**
     * 处理业务逻辑
     */
    fun doBusiness()

    /**
     * 沉浸式
     */
    fun initImmersionBar()
}