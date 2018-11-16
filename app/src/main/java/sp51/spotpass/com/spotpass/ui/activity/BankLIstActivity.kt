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
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.view_title_textview.*
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.BankListAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.BankList
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("Registered")
/**
 * @Time : 2018/5/24 no 13:51
 * @USER : vvguoliang
 * @File : BankLIstActivity.java
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
class BankLIstActivity : BaseActivity(), View.OnClickListener {

    private lateinit var bankListAdapter: BankListAdapter

    private var list: ArrayList<Map<String, Any?>>? = null

    private var boolean: Boolean = false

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_bank_list

    }

    override fun initView(rootView: View) {
        HttpALl.get().setBankList(mHandler, this)
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun setListener() {
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)
        title_textview.visibility = View.VISIBLE
        name = getString(R.string.textView_bank_list)
        title_textview.text = name

        bankListAdapter = BankListAdapter(this)
        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        recycler_view_test_rv.layoutManager = LinearLayoutManager(this)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL))

        getRecycler_view_test_rv()
        val map1 = mapOf<String, Any>(Pair("name", "中国工商银行"), Pair("id", "102"), Pair("url", ""))
        list = ArrayList()
        list!!.add(map1)
        bankListAdapter.setData(list)
        recycler_view_test_rv.adapter = bankListAdapter

        bankListAdapter.setOnItemClickListener({ _, data ->
            val intent = Intent()
            intent.putExtra("name", list!![data].get("name").toString())
            intent.putExtra("id", list!![data].get("id").toString())
            setResult(208, intent)
            finish()
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
        }
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
                HttpALl.get().setBankList(mHandler, this@BankLIstActivity)
            }

            override fun onLoadMore(isSilence: Boolean) {
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
                1005 -> {
                    if (list != null && list!!.size > 0) {
                        list!!.clear()
                    } else {
                        list = ArrayList()
                    }
                    if (boolean) {
                        boolean = false
                        custom_view.stopRefresh()
                    }
                    val bankList = msg.obj as BankList
                    for (index in bankList.data) {
                        val map = mapOf(Pair("name", index.name), Pair("id", index.id), Pair("url", index.url))
                        list!!.add(map)
                    }
                    bankListAdapter.setData(list)
                    recycler_view_test_rv.adapter = bankListAdapter
                }
            }
        }
    }

    private var name: String = ""
    public override fun onResume() {
        super.onResume()
        HttpALl.get().setBankList(mHandler, this@BankLIstActivity)
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
        ImmersionBar.with(this@BankLIstActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }


}