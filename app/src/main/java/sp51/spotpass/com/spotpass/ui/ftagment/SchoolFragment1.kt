package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.umeng.analytics.MobclickAgent
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.activity.WebViewActivity
import sp51.spotpass.com.spotpass.ui.adapter.SchoolFragmentSimpleAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.BeginerDetail
import sp51.spotpass.com.spotpass.ui.baseEntity.TradingRules
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @Time : 2018/5/31 no 10:34
 * @USER : vvguoliang
 * @File : SchoolFragment1.java
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
class SchoolFragment1 : BaseFragment() {

    private lateinit var custom_view: XRefreshView

    private lateinit var recycler_view_test_rv: RecyclerView

    private lateinit var schoolFragmentSimpleAdapter: SchoolFragmentSimpleAdapter

    private var mLoadCount = 0

    private var list: ArrayList<Map<String, Any>> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.far_billng_record1, container, false)
        custom_view = view.findViewById(R.id.custom_view)
        recycler_view_test_rv = view.findViewById(R.id.recycler_view_test_rv)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        schoolFragmentSimpleAdapter = SchoolFragmentSimpleAdapter(activity)

        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        recycler_view_test_rv.layoutManager = LinearLayoutManager(activity)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(activity), DividerItemDecoration.VERTICAL))

        recycler_view_test_rv.adapter = schoolFragmentSimpleAdapter

        getRecycler_view_test_rv()
    }

    private fun getRecycler_view_test_rv() {
        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                mLoadCount = 0
                HttpALl.get().settradingRules(mLoadCount.toLong(), mHandler, activity!!)
            }

            override fun onLoadMore(isSilence: Boolean) {
                mLoadCount++
                HttpALl.get().settradingRules(mLoadCount.toLong(), mHandler, activity!!)
            }
        })

        schoolFragmentSimpleAdapter.setOnItemClickListener { _, data ->
            if (list != null && list.size > 0) {
                HttpALl.get().setbeginerDetail(list[data]["id"].toString().toLong(), mHandler, activity!!)
            }
        }
    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1039 -> {
                    if (mLoadCount == 0) {
                        if (list != null && list.isNotEmpty()) {
                            list.clear()
                        }
                        custom_view.stopRefresh()
                    } else {
                        custom_view.setLoadComplete(true)
                    }
                    val tradingrules = msg.obj as TradingRules
                    for (index in tradingrules.data.data) {
                        val map = HashMap<String, Any>()
                        map["id"] = index.id
                        map["title"] = index.title
                        list.add(map)
                    }

                    schoolFragmentSimpleAdapter.setData(1, list)
                }
                1040 -> {
                    val beginerDetail = msg.obj as BeginerDetail
                    val intent = Intent(activity, WebViewActivity::class.java)
                    intent.putExtra("url", AppUtil.instance.Html(beginerDetail.data.title, beginerDetail.data.content, beginerDetail.data.updatedTime))
                    intent.putExtra("type", "3")
                    startActivity(intent)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        HttpALl.get().settradingRules(mLoadCount.toLong(), mHandler, activity!!)
        MobclickAgent.onPageStart("SchoolFragment1") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("SchoolFragment1")
    }


}