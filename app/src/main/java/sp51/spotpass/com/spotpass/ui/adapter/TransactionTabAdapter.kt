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
class TransactionTabAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    lateinit var lazy: ArrayList<Fragment>
    fun geList(list: ArrayList<Fragment>) {
        lazy = list
    }

    override fun getItem(position: Int): Fragment? {
        return lazy.get(position)
    }

    override fun getCount() = lazy.size

    override fun getPageTitle(position: Int) = when (position) {
        0 -> "买卖"
        1 -> "持仓"
        else -> "挂单"
    }
}