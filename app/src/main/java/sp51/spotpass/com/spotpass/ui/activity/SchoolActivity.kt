package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_school.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.playtablayout.TouchableTabLayout
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.BillingRecordTabAdapter
import sp51.spotpass.com.spotpass.ui.adapter.SchoolRecordTabAdapter
import sp51.spotpass.com.spotpass.ui.ftagment.*
import java.util.ArrayList

@SuppressLint("Registered")
/**
 * @Time : 2018/5/31 no 10:07
 * @USER : vvguoliang
 * @File : SchoolActivity.java
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
 * 在线学堂
 */
class SchoolActivity : BaseActivity(), TouchableTabLayout.OnTabSelectedListener, View.OnClickListener {

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
        }
    }

    override fun onTabSelected(tab: TouchableTabLayout.Tab) {
        tab.getIcon()?.setColorFilter(Color.parseColor("#999999"), PorterDuff.Mode.SRC_IN)
    }

    override fun onTabUnselected(tab: TouchableTabLayout.Tab) {
        tab.getIcon()?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
    }

    override fun onTabReselected(tab: TouchableTabLayout.Tab) {
    }

    private var tabLayout: TouchableTabLayout? = null

    private lateinit var schoolRecordTabAdapter: SchoolRecordTabAdapter

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_school
    }

    override fun initView(rootView: View) {
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun setListener() {
        name = getString(R.string.textView_school)

        title_textview.visibility = View.VISIBLE
        title_textview.text = name
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)


        transaction_PlayTab.colors = intArrayOf(R.color.white, R.color.white, R.color.white)

        schoolRecordTabAdapter = SchoolRecordTabAdapter(supportFragmentManager)
        val lazy: ArrayList<Fragment> = ArrayList()
        lazy.add(0, SchoolFragment1())
        lazy.add(1, SchoolFragment2())
        lazy.add(2, SchoolFragment3())

        schoolRecordTabAdapter.geList(list = lazy)
        transaction_viewpage.adapter = schoolRecordTabAdapter

        tabLayout = transaction_PlayTab.tabLayout
        with(tabLayout) {
            this!!.setupWithViewPager(transaction_viewpage)
            tabMode = TabLayout.MODE_FIXED
            tabGravity = TabLayout.GRAVITY_FILL
            setSelectedTabIndicatorHeight(7)
            setSelectedTabIndicatorColor(android.graphics.Color.parseColor("#FF6D64"))
            setTabTextColors(Color.parseColor("#999999"), Color.parseColor("#FF6D64"))
            tabLayout!!.addOnTabSelectedListener(this@SchoolActivity)
        }
    }

    override fun doBusiness() {
    }

    private lateinit var name: String


    public override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart(name); //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    public override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(name); //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this)
    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(R.id.top_view)?.init()
        ImmersionBar.with(this@SchoolActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }
}