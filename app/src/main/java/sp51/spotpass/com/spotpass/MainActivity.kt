@file:Suppress("DEPRECATED_IDENTITY_EQUALS", "DEPRECATION")

package sp51.spotpass.com.spotpass

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.text.TextUtils
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import sp51.spotpass.com.spotpass.base.BaseBackExitActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.ftagment.*
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.ui.utils.Camera.UserCenterRealize
import sp51.spotpass.com.spotpass.ui.utils.Camera.BitmapUtils
import android.content.Intent
import android.util.Log
import android.view.Gravity
import sp51.spotpass.com.spotpass.ui.view.Dialog.BottomDialog
import java.io.File
import com.taobao.accs.utl.UtilityImpl.isForeground
import android.content.BroadcastReceiver
import android.content.Context
import android.support.v4.content.LocalBroadcastManager
import android.content.IntentFilter
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.RadioButton


/**
 * @Time : 2018/5/2 no 下午2:17
 * @USER : vvguoliang
 * @File : MainActivity.java
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
@SuppressLint("RestrictedApi", "Registered")
class MainActivity : BaseBackExitActivity(), RadioGroup.OnCheckedChangeListener {

    internal val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_home -> position = 0
            R.id.rb_type -> position = 1
            R.id.rb_community -> position = 2
//            R.id.rb_cart -> position = 3
            R.id.rb_user -> position = 3
        }
        viewpager.setCurrentItem(position, true)
    }

    private var position: Int = 0
    private var bluesFragments = listOf(MainHome(), MainInteraction(), MainTransaction(), MainMy())//MainDocumentary()

    private var userCenterRealize: UserCenterRealize? = null

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView(rootView: View) {
        //定义底部标签图片大小和位置
        val drawable_news = resources.getDrawable(R.drawable.home_button_selector_transaction)
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(0, 0, 70, 75)
        //设置图片在文字的哪个方向
        findViewById<RadioButton>(R.id.rb_community).setCompoundDrawables(null, drawable_news, null, null)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun setListener() {
        userCenterRealize = UserCenterRealize()
        position = intent.getIntExtra("position", 0)
        var position1 = 0
        when (position) {
            0 -> position1 = R.id.rb_home
            1 -> position1 = R.id.rb_type
            2 -> position1 = R.id.rb_community
//            3 -> position1 = R.id.rb_cart
            else -> position1 = R.id.rb_user
        }
        navigation.check(position1)
        navigation.setOnCheckedChangeListener(this)
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        disableShiftMode(navigation)
        viewpager.adapter = viewPagerAdapter(supportFragmentManager, bluesFragments as List<BaseFragment>)
        viewpager.setCurrentItem(position, true)
//        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//
//            }
//
//            override fun onPageSelected(position: Int) {
////                navigation.menu.getItem(position).isChecked = true
//
//            }
//        })
    }

    override fun doBusiness() {
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewpager.setCurrentItem(0, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_interaction -> {
                viewpager.setCurrentItem(1, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_transaction -> {
                viewpager.setCurrentItem(2, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_documentary -> {
                viewpager.setCurrentItem(3, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_my -> {
                viewpager.setCurrentItem(4, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    //继承 FragmentPagerAdapter 创建适配器，利用主构造函数传值
    class viewPagerAdapter(fm: FragmentManager?, var list: List<BaseFragment>) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return list[position]
        }

        override fun getCount(): Int {
            return list.size
        }

        override fun getItemPosition(`object`: Any): Int {
            // 最简单解决 notifyDataSetChanged() 页面不刷新问题的方法
            return POSITION_NONE
        }
    }


    fun disableShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView

                item.setShiftingMode(false)
                // set once again checked value, so view will be updated

                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalAccessException) {
        }

    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(top_view)?.init()
        ImmersionBar.with(this@MainActivity)
                .statusBarDarkFont(false, 1.0f)
                .init()
    }

}