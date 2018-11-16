package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_signlog.*
import kotlinx.android.synthetic.main.view_title_textview.*
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.SignLogSimpleAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.MessageList
import sp51.spotpass.com.spotpass.ui.baseEntity.SignLog
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshViewFooter

@SuppressLint("Registered")
/**
 * @Time : 2018/6/16 no 15:34
 * @USER : vvguoliang
 * @File : SignLogActivity.java
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
class SignLogActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
        }
    }

    private var mLoadCount = 0

    private var boolean: Int = 0

    private var signLog: SignLog? = null

    private val list = ArrayList<Map<String, Any>>()

    private lateinit var signLogSimpleAdapter: SignLogSimpleAdapter

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_signlog
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {

        name = getString(R.string.textView_record_old)
        title_textview.visibility = View.VISIBLE
        title_textview.text = name
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)

        signLogSimpleAdapter = SignLogSimpleAdapter(this)
        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        recycler_view_test_rv.layoutManager = LinearLayoutManager(this)

        getRecycler_view_test_rv()

        recycler_view_test_rv.adapter = signLogSimpleAdapter
        signLogSimpleAdapter.customLoadMoreView = XRefreshViewFooter(this@SignLogActivity)
    }

    override fun doBusiness() {
    }


    private fun getRecycler_view_test_rv() {
        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                boolean = 0
                mLoadCount = 0
                if (signLog != null && signLog!!.data.data.isNotEmpty() && signLog!!.data.total == list.size) {
                    custom_view.stopRefresh()
                } else {
                    HttpALl.get().setsignLog(mLoadCount.toLong(), mHandler, this@SignLogActivity)
                }
            }

            override fun onLoadMore(isSilence: Boolean) {
                mLoadCount++
                boolean = 1
                if (signLog != null && signLog!!.data.data.isNotEmpty() && signLog!!.data.total == list.size) {
                    custom_view.setLoadComplete(true)
                } else {
                    HttpALl.get().setsignLog(mLoadCount.toLong(), mHandler, this@SignLogActivity)
                }
            }
        })
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1043 -> {
                    if (boolean == 0) {
                        if (list != null && list.size > 0) {
                            list.clear()
                        }
                    }
                    signLog = msg.obj as SignLog
                    for (index in signLog!!.data.data) {
                        val map = HashMap<String, Any>()
                        map["points"] = index.points
                        map["created_time"] = index.createdTime
                        map["type_name"] = index.typeName
                        list.add(map)
                    }
                    if (list.size > 0) {
                        signLogSimpleAdapter.setData(list)
                    } else {
                        if (boolean == 0) {
                            custom_view.visibility = View.GONE
                            view_xrefresh_linear.visibility = View.VISIBLE
                        }
                    }

                    if (boolean == 0) {
                        custom_view.stopRefresh()
                    } else if (boolean == 1) {
                        custom_view.setLoadComplete(true)
                    }
                }
            }
        }
    }


    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        HttpALl.get().setsignLog(mLoadCount.toLong(), mHandler, this)
        MobclickAgent.onPageStart(name) //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    public override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(name) //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this)
    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(R.id.top_view)?.init()
        ImmersionBar.with(this@SignLogActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }
}