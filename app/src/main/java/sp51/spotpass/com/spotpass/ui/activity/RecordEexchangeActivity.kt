package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.view_title_textview.*
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.BankListAdapter
import sp51.spotpass.com.spotpass.ui.adapter.EexchangeAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.Exchangehistory
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@SuppressLint("Registered")
/**
 * @Time : 2018/6/6 no 18:58
 * @USER : vvguoliang
 * @File : RecordEexchangeActivity.java
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
class RecordEexchangeActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
        }
    }

    private var type: String = ""

    private lateinit var eexchangeAdapter: EexchangeAdapter

    private lateinit var list: ArrayList<Map<String, String>>

    private var boolean: Boolean = false

    private var mconl = 0


    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_record_eexchange
    }

    override fun initView(rootView: View) {
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun setListener() {
        type = intent.getStringExtra("type")
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)
        title_textview.visibility = View.VISIBLE
        name = getString(R.string.textView_record_eexchange)
        title_textview.text = name

        eexchangeAdapter = EexchangeAdapter(this)
        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        recycler_view_test_rv.layoutManager = LinearLayoutManager(this)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL))

        val headerView = eexchangeAdapter.setHeaderView(R.layout.view_adapter_eexchange, recycler_view_test_rv)
        val eexchang_line1 = headerView.findViewById<LinearLayout>(R.id.eexchang_line1)
        val eexchang_line2 = headerView.findViewById<LinearLayout>(R.id.eexchang_line2)
        val eexchang_time1 = headerView.findViewById<TextView>(R.id.eexchang_time1)
        val eexchang_time2 = headerView.findViewById<TextView>(R.id.eexchang_time2)
        val eexchang_operation1 = headerView.findViewById<TextView>(R.id.eexchang_operation1)
        val eexchang_operation2 = headerView.findViewById<TextView>(R.id.eexchang_operation2)
        val eexchang_source2 = headerView.findViewById<TextView>(R.id.eexchang_source2)
        if (type == "1") {
            eexchang_line1.visibility = View.VISIBLE
            eexchang_line2.visibility = View.GONE
            eexchang_time1.text = getString(R.string.textView_time1)
            eexchang_operation1.text = getString(R.string.textView_eexchang_operation)
            name = getString(R.string.textView_record_eexchange)
        } else {
            eexchang_line1.visibility = View.GONE
            eexchang_line2.visibility = View.VISIBLE
            eexchang_time2.text = getString(R.string.textView_time1)
            eexchang_operation2.text = getString(R.string.textView_eexchang_umber)
            eexchang_source2.text = getString(R.string.textView_eexchang_source)
            name = getString(R.string.textView_record_old)
        }
        title_textview.text = name
        getRecycler_view_test_rv()
//        if (type == "1") {
//            list = ArrayList()
//            var map1 = HashMap<String, String>()
//            map1["time"] = AppUtil.instance.getTime().toString()
//            map1["operation"] = "兑换8元体验券1张"
//            list.add(map1)
//            map1 = HashMap<String, String>()
//            map1["time"] = AppUtil.instance.getTime().toString()
//            map1["operation"] = "兑换8元体验券1张"
//            list.add(map1)
//            map1 = HashMap<String, String>()
//            map1["time"] = AppUtil.instance.getTime().toString()
//            map1["operation"] = "兑换8元体验券1张"
//            list.add(map1)
//            eexchangeAdapter.setData(list, type)
//        } else {
//            list = ArrayList()
//            var map1 = HashMap<String, String>()
//            map1["time"] = AppUtil.instance.getTime().toString()
//            map1["operation"] = "+50"
//            map1["source"] = "0"
//            list.add(map1)
//            map1 = HashMap<String, String>()
//            map1["time"] = AppUtil.instance.getTime().toString()
//            map1["operation"] = "-50"
//            map1["source"] = "1"
//            list.add(map1)
//            map1 = HashMap<String, String>()
//            map1["time"] = AppUtil.instance.getTime().toString()
//            map1["operation"] = "+50"
//            map1["source"] = "0"
//            list.add(map1)
//            eexchangeAdapter.setData(list, type)
//        }
        recycler_view_test_rv.adapter = eexchangeAdapter
    }

    override fun doBusiness() {
    }

    private fun getRecycler_view_test_rv() {
        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                //模拟数据加载失败的情况
//                    val random = Random()
//                    val success = random.nextBoolean()
//                    if (success) {
//                        custom_view.stopRefresh()
//                    } else {
//                        custom_view.stopRefresh(false)
//                    }
                //或者
//                custom_view.stopRefresh(success);
                boolean = true
                mconl = 0
            }

            override fun onLoadMore(isSilence: Boolean) {
                mconl++
                if (custom_view.hasLoadCompleted()) {
                }
                custom_view.stopLoadMore(false)
                custom_view.setLoadComplete(true)
                // 刷新完成必须调用此方法停止加载
                custom_view.stopLoadMore(false)
            }
        })
    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1058 -> {
                    val exchangehistory = msg.obj as Exchangehistory
                    if (list != null && list.size > 0) {
                        list.clear()
                    } else {
                        list = ArrayList()
                    }
                    for (indes in exchangehistory.data.data) {
                        val map = HashMap<String, String>()
                        map["time"] = indes.createdTime
                        map["operation"] = indes.typeName
                        list.add(map)
                    }
                    if (boolean) {
                        boolean = false
                        custom_view.stopRefresh()
                    }
                    eexchangeAdapter.setData(list, type)
                    recycler_view_test_rv.adapter = eexchangeAdapter
                }
            }
        }
    }

    private var name: String = ""
    public override fun onResume() {
        super.onResume()
        HttpALl.get().exchangehistory(mconl.toLong(), mHandler, this)
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
        ImmersionBar.with(this@RecordEexchangeActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }
}