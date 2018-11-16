package sp51.spotpass.com.spotpass.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import sp51.spotpass.com.spotpass.ui.ftagment.SampleFragment

/**
 * @Time : 2018/5/8 no 13:15
 * @USER : vvguoliang
 * @File : TransactionTabAdapter.java
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
class DocumentTabAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    lateinit var lazy: ArrayList<Fragment>

    override fun getItem(position: Int): Fragment? {
        return lazy.get(position)
    }

    override fun getCount() = lazy.size

    override fun getPageTitle(position: Int) = when (position) {
        0 -> "跟单广场"
        1 -> "一起挂单"
        2 -> "关注牛人"
        else -> "我参与的"
    }
}