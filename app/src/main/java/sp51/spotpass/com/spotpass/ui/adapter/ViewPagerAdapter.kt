package sp51.spotpass.com.spotpass.ui.adapter

import android.support.v4.view.ViewPager
import android.support.v4.view.PagerAdapter
import android.view.View


/**
 * @Time : 2018/5/14 no 11:52
 * @USER : vvguoliang
 * @File : ViewPagerAdapter.java
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
 *
 * @author YeChao
 * @功能描述：ViewPager适配器，用来绑定数据和view
 */
class ViewPagerAdapter(//界面列表
        private val views: ArrayList<View>?) : PagerAdapter() {

    /**
     * 获得当前界面数
     */
    override fun getCount(): Int {
        return views?.size ?: 0
    }

    /**
     * 判断是否由对象生成界面
     */
    override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
        return arg0 === arg1
    }

    /**
     * 销毁position位置的界面
     */
    override fun destroyItem(container: View, position: Int, `object`: Any) {
        (container as ViewPager).removeView(views!![position])
    }

    /**
     * 初始化position位置的界面
     */
    override fun instantiateItem(container: View, position: Int): Any {
        (container as ViewPager).addView(views!![position], 0)
        return views[position]
    }

}