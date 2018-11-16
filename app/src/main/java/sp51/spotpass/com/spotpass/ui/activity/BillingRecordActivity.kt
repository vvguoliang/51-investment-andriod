package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_billing_record.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.playtablayout.TouchableTabLayout
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.BillingRecordTabAdapter
import sp51.spotpass.com.spotpass.ui.ftagment.*

@SuppressLint("Registered")
/**
 * @Time : 2018/5/20 no 15:45
 * @USER : vvguoliang
 * @File : BillingRecordActivity.java
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
class BillingRecordActivity : BaseActivity(), View.OnClickListener, TouchableTabLayout.OnTabSelectedListener {

    override fun onTabSelected(tab: TouchableTabLayout.Tab) {
        tab.getIcon()?.setColorFilter(Color.parseColor("#999999"), PorterDuff.Mode.SRC_IN)
    }

    override fun onTabUnselected(tab: TouchableTabLayout.Tab) {
        tab.getIcon()?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
    }

    override fun onTabReselected(tab: TouchableTabLayout.Tab) {
    }

    private lateinit var billingRecordTabAdapter: BillingRecordTabAdapter

    private lateinit var tabLayout: TouchableTabLayout

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_billing_record
    }

    override fun initView(rootView: View) {
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun setListener() {
        name = getString(R.string.textView_billing_record)

        title_textview.visibility = View.VISIBLE
        title_textview.text = name
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)


        transaction_PlayTab.colors = intArrayOf(R.color.white, R.color.white, R.color.white)

        billingRecordTabAdapter = BillingRecordTabAdapter(supportFragmentManager)
        val lazy: ArrayList<Fragment> = ArrayList()
        lazy.add(0, BillngRecordFragment1(this))
        lazy.add(1, BillngRecordFragment2(this))
        lazy.add(2, BillngRecordFragment3(this))

        billingRecordTabAdapter.geList(list = lazy)
        transaction_viewpage.adapter = billingRecordTabAdapter

        tabLayout = transaction_PlayTab.tabLayout
        with(tabLayout) {
            setupWithViewPager(transaction_viewpage)
            tabMode = android.support.design.widget.TabLayout.MODE_FIXED
            tabGravity = android.support.design.widget.TabLayout.GRAVITY_FILL
            setSelectedTabIndicatorHeight(7)
            setSelectedTabIndicatorColor(android.graphics.Color.parseColor("#FF6D64"))
            setTabTextColors(Color.parseColor("#999999"), Color.parseColor("#FF6D64"))
            tabLayout.addOnTabSelectedListener(this@BillingRecordActivity)
        }

    }

    override fun doBusiness() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
        }
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
        ImmersionBar.with(this@BillingRecordActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }
}