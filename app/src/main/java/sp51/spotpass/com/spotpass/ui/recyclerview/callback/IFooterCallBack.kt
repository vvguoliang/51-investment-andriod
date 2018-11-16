package sp51.spotpass.com.spotpass.ui.recyclerview.callback

import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView

/**
 * @Time : 2018/5/2 no 下午6:23
 * @USER : vvguoliang
 * @File : IFooterCallBack.java
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

interface IFooterCallBack {

    /**
     * footerview是否显示中
     *
     * @return
     */
    var isShowing: Boolean

    /**
     * 获得footerview的高度
     *
     * @return
     */
    val footerHeight: Int

    /**
     * 当不是到达底部自动加载更多的时候，需要自己写点击事件
     *
     * @param xRefreshView
     */
    fun callWhenNotAutoLoadMore(xRefreshView: XRefreshView)

    /**
     * 正常状态，例如需要点击footerview才能加载更多，主要是到达底部不自动加载更多时会被调用
     */
    fun onStateReady()

    /**
     * 正在刷新
     */
    fun onStateRefreshing()

    /**
     * 当footerview被上拉时，松开手指即可加载更多
     */
    fun onReleaseToLoadMore()

    /**
     * 刷新结束 在此方法中不要调用show()方法
     *
     * @param hidefooter footerview是否被隐藏,hideFooter参数由XRefreshView.stopLoadMore(boolean)传入
     */
    fun onStateFinish(hidefooter: Boolean)

    /**
     * 已无更多数据 在此方法中不要调用show()方法
     */
    fun onStateComplete()


    /**
     * 设置显示或者隐藏footerview 不要在onStateFinish和onStateComplete中调用此方法
     *
     * @param show
     */
    fun show(show: Boolean)
}

