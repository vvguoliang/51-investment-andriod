@file:Suppress("UNUSED_EXPRESSION")

package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.view_title_textview.*
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.IntegralMallSimpleAdapter
import sp51.spotpass.com.spotpass.ui.adapter.ProfitListSimpleAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.GetVouchersList
import sp51.spotpass.com.spotpass.ui.baseEntity.QryAccount
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.GetAssetsFiles
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshViewFooter
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import sp51.spotpass.com.spotpass.ui.utils.Util
import sp51.spotpass.com.spotpass.ui.view.Dialog.NickNameDialogBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @Time : 2018/5/5 no 1:00
 * @USER : vvguoliang
 * @File : IntegralMallActivity.java
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
 *
 * 积分商城
 */
class IntegralMallActivity : BaseActivity(), View.OnClickListener {

    private var mLoadCount = 0

    private lateinit var integralMallSimpleAdapter: IntegralMallSimpleAdapter

    private lateinit var integral_mall_num: TextView

    private var success: Boolean = false

    private val list: ArrayList<Map<String, Any>>? = ArrayList()

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_integral_mall
    }

    override fun initView(rootView: View) {
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun setListener() {

        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        integralMallSimpleAdapter = IntegralMallSimpleAdapter(this)
        getRecycler_view_test_rv()

        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)

        title_integral_rule.visibility = View.VISIBLE
        title_integral_rule.setOnClickListener(this)

        name = getString(R.string.textView_integral_mall)

        title_textview.visibility = View.VISIBLE
        title_textview.text = name

        recycler_view_test_rv.layoutManager = LinearLayoutManager(this)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL))

        val headerView = integralMallSimpleAdapter.setHeaderView(R.layout.view_integral_mall_content, recycler_view_test_rv)

        val look_details_relativce = headerView.findViewById<RelativeLayout>(R.id.look_details_relativce)
        look_details_relativce.setOnClickListener(this)
        integral_mall_num = headerView.findViewById(R.id.integral_mall_num)
        val integral_mall_grade = headerView.findViewById<TextView>(R.id.integral_mall_grade)
        integral_mall_grade.text = getString(R.string.textView_designates_no_up)
        val integral_mall_exchange_history = headerView.findViewById<LinearLayout>(R.id.integral_mall_exchange_history)
        integral_mall_exchange_history.setOnClickListener(this)

        integral_mall_num.text = "0"
        recycler_view_test_rv.adapter = integralMallSimpleAdapter
        integralMallSimpleAdapter.setOnItemClickListener { view, data ->
            if (list != null && list.size > 0) {
                HttpALl.get().setvouchersExchange(list[data-1]["price"].toString(), mHandler, this)
            }
        }
    }

    override fun doBusiness() {
    }

    override fun onClick(v: View?) {
        val intent: Intent?
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.title_integral_rule -> {
                intent = Intent(this@IntegralMallActivity, WebViewActivity::class.java)
                intent.putExtra("url", GetAssetsFiles.getString(this, "体验券使用说明.htm"))
                intent.putExtra("type", "3")
                startActivity(intent)
            }
            R.id.look_details_relativce -> {
                if (TextUtils.isEmpty(SPUtils.getInstance(this, "login").getString("login", ""))) {
                    Util.registerA(this@IntegralMallActivity)
                } else {
                    startActivity(Intent(this, SignLogActivity::class.java))
                }
            }
            R.id.integral_mall_exchange_history -> {
                if (TextUtils.isEmpty(SPUtils.getInstance(this, "login").getString("login", ""))) {
                    Util.registerA(this@IntegralMallActivity)
                } else {
                    intent = Intent(this, RecordEexchangeActivity::class.java)
                    intent.putExtra("type", "1")
                    startActivity(intent)
                }
            }
        }
    }


    private fun getRecycler_view_test_rv() {
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)

        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                success = true
                mLoadCount = 0
                getHttp()
            }

            override fun onLoadMore(isSilence: Boolean) {
            }
        })
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1003 -> {
                    val qryAccount = msg.obj as QryAccount
                    val shoppoints = qryAccount.data.shoppoints
                    integral_mall_num.text = shoppoints
                }
                1033 -> {
                    if (list != null && list.size > 0) {
                        list.clear()
                    }
                    val getVouchersList = msg.obj as GetVouchersList
                    for (index in getVouchersList.data) {
                        val map = HashMap<String, Any>()
                        map["id"] = index.id
                        map["createdAt"] = index.createdAt
                        map["exchangeId"] = index.exchangeId
                        map["name"] = index.name
                        map["price"] = index.price
                        map["status"] = index.status
                        list!!.add(map)
                    }
                    integralMallSimpleAdapter.setData(list)
                    if (success) {
                        custom_view.stopRefresh()
                    }
                }
                1044 -> {
                    mLoadCount = 0
                    getHttp()
                    ToatUtils.showShort1(this@IntegralMallActivity, "您兑换成功")
                }
            }
        }
    }


    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(R.id.top_view)?.init()
        ImmersionBar.with(this@IntegralMallActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }

    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        getHttp()
        MobclickAgent.onPageStart(name); //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    private fun getHttp() {
        if (!TextUtils.isEmpty(SPUtils.getInstance(this, "login").getString("phone", "")))
            HttpALl.get().setQryAccount("qryAccount",
                    SPUtils.getInstance(this, "login").getString("phone", ""), "", "", mHandler, this)
        HttpALl.get().setgetvoucherslist(mHandler, this)
    }

    public override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(name); //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this)
    }
}