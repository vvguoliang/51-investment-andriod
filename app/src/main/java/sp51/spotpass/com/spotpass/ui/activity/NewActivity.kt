package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.view_title_textview.*
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.NEWSimpleAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.MessageDetail
import sp51.spotpass.com.spotpass.ui.baseEntity.MessageList
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshViewFooter
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@SuppressLint("Registered")
/**
 * @Time : 2018/6/1 no 15:00
 * @USER : vvguoliang
 * @File : NewActivity.java
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
class NewActivity : BaseActivity(), View.OnClickListener {

    private var mLoadCount = 0

    private var boolean: Int = 0

    private val list = ArrayList<Map<String, Any>>()

    private lateinit var newSimpleAdapter: NEWSimpleAdapter

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
        }
    }

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_new
    }

    override fun initView(rootView: View) {
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun setListener() {
        name = getString(R.string.textView_my_new)
        title_textview.visibility = View.VISIBLE
        title_textview.text = name
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)

        newSimpleAdapter = NEWSimpleAdapter(this)
        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        recycler_view_test_rv.layoutManager = LinearLayoutManager(this)

        getRecycler_view_test_rv()

        recycler_view_test_rv.adapter = newSimpleAdapter

        newSimpleAdapter.customLoadMoreView = XRefreshViewFooter(this)

        newSimpleAdapter.setOnItemClickListener { view, data ->
//            if (list != null && list.size > 0) {
//                HttpALl.get().setmessageDetail(list[data]["id"].toString(), mHandler, this)
//            }
        }

    }

    override fun doBusiness() {
    }

    private fun getRecycler_view_test_rv() {
        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                boolean = 0
                if (list != null && list.size > 0) {
                    list.clear()
                }
                mLoadCount = 0
                HttpALl.get().setmessage(mLoadCount.toLong(), mHandler, this@NewActivity)
            }

            override fun onLoadMore(isSilence: Boolean) {
                mLoadCount++
                HttpALl.get().setmessage(mLoadCount.toLong(), mHandler, this@NewActivity)
            }
        })
    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1031 -> {
                    if (mLoadCount == 0) {
                        if (list != null && list.size > 0) {
                            list.clear()
                        }
                    }
                    val message = msg.obj as MessageList
                    for (index in message.data) {
                        val map = HashMap<String, Any>()
                        map["id"] = index.id
                        map["created_time"] = index.createdTime
                        map["title"] = index.data.title
                        map["content"] = index.data.content
                        list.add(map)
                    }
                    newSimpleAdapter.setData(list)

                    if (boolean == 0) {
                        custom_view.stopRefresh()
                    } else if (boolean == 1) {
                        custom_view.setLoadComplete(true)
                    }
                }
                1047 -> {
                    val messageDetail = msg.obj as MessageDetail
                    val intent = Intent(this@NewActivity, WebViewActivity::class.java)
                    intent.putExtra("url", AppUtil.instance.Html(messageDetail.data.data.title, messageDetail.data.data.content, messageDetail.data.createdTime))
                    intent.putExtra("type", "3")
                    startActivity(intent)
                }
            }
        }
    }

    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        HttpALl.get().setmessage(mLoadCount.toLong(), mHandler, this)
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
        ImmersionBar.with(this@NewActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }
}