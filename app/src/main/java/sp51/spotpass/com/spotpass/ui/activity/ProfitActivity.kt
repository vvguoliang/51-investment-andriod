package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.view_title_textview.*
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.ProfitSimpleAdapter
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshViewFooter
import java.util.*

@SuppressLint("Registered")
/**
 * @Time : 2018/5/19 no 12:46
 * @USER : vvguoliang
 * @File : ProfitActivity.java
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
class ProfitActivity : BaseActivity(), View.OnClickListener {

    private var mLoadCount = 0

    private lateinit var profitSimpleAdapter: ProfitSimpleAdapter

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_profit
    }

    override fun initView(rootView: View) {
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    @TargetApi(Build.VERSION_CODES.KITKAT)
    override fun setListener() {

        val name1 = intent.getStringExtra("name")
        val time = intent.getStringExtra("time")
        val e = intent.getStringExtra("e")
        val l = intent.getStringExtra("l")
        val l1 = intent.getStringExtra("l1")

        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        profitSimpleAdapter = ProfitSimpleAdapter(this)
        getRecycler_view_test_rv()

        name = getString(R.string.textView_profit_details)

        recycler_view_test_rv.layoutManager = LinearLayoutManager(this)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL))

        val headerView = profitSimpleAdapter.setHeaderView(R.layout.act_profit_adapter, recycler_view_test_rv)

        val profit_text_time = headerView.findViewById<TextView>(R.id.profit_text_time)
        val profit_relative_name = headerView.findViewById<TextView>(R.id.profit_relative_name)
        val profit_e_text = headerView.findViewById<TextView>(R.id.profit_e_text)
        val profit_l_text = headerView.findViewById<TextView>(R.id.profit_l_text)
        val profit_reward_text = headerView.findViewById<TextView>(R.id.profit_reward_text)
        profit_text_time.text = time
        profit_relative_name.text = name1
        profit_e_text.text = e + "元"
        profit_l_text.text = l
        profit_reward_text.text = l1

        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)

        name = getString(R.string.textView_profit_list)

        title_textview.visibility = View.VISIBLE
        title_textview.text = name

        val map1 = mapOf(Pair("name", "分析师"), Pair("context", "盈利746%预计奖励18600积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map2 = mapOf(Pair("name", "分析师"), Pair("context", "盈利600%预计奖励15000积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map3 = mapOf(Pair("name", "分析师"), Pair("context", "盈利451%预计奖励13200积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val list = listOf(map1, map2, map3)
        profitSimpleAdapter.setData(list)
        recycler_view_test_rv.adapter = profitSimpleAdapter
    }

    override fun doBusiness() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
        }
    }

    private fun getRecycler_view_test_rv() {
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        profitSimpleAdapter.customLoadMoreView = XRefreshViewFooter(this)

        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                mHandler.postDelayed({
                    //模拟数据加载失败的情况
                    val random = Random()
                    val success = random.nextBoolean()
                    if (success) {
                        custom_view.stopRefresh()
                    } else {
                        custom_view.stopRefresh(false)
                    }
                    //或者
                    //                        xRefreshView1.stopRefresh(success);
                }, 2000)
            }

            override fun onLoadMore(isSilence: Boolean) {
                mHandler.postDelayed({
                    if (custom_view.hasLoadCompleted()) {
                    }
                    //                        for (int i = 0; i < 6; i++) {
                    //                            adapter.insert(new SpecialProduct("More ", "21"),
                    //                                    adapter.getAdapterItemCount());
                    //                        }
                    mLoadCount++
                    if (mLoadCount >= 2) {
                        custom_view.setLoadComplete(true)
                    } else {
                        // 刷新完成必须调用此方法停止加载
                        custom_view.stopLoadMore(false)
                    }
                }, 1000)
            }
        })
    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1000 -> {
                }
            }
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
        ImmersionBar.with(this@ProfitActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }
}