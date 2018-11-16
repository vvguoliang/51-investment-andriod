package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.graphics.Color
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
import android.widget.LinearLayout
import android.widget.TextView
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.adapter.SampleFragmentSimpleAdapter
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import java.util.*
import android.R.attr.button
import android.content.Intent
import android.graphics.Typeface
import android.support.v4.view.ViewPager
import android.view.Gravity
import sp51.spotpass.com.spotpass.playtablayout.PlayTabLayout
import sp51.spotpass.com.spotpass.ui.activity.DetailsHoldingActivity
import sp51.spotpass.com.spotpass.ui.baseEntity.QryHold
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import java.text.DecimalFormat
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


@SuppressLint("ValidFragment")
/**
 * @Time : 2018/5/8 no 13:23
 * @USER : vvguoliang
 * @File : SampleFragment.java
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
class SampleFragment1 : BaseFragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v!!.id) {
        }
    }

    private lateinit var custom_view: XRefreshView

    private lateinit var recycler_view_test_rv: RecyclerView

    private lateinit var sampleFragmentSimpleAdapter: SampleFragmentSimpleAdapter

    private lateinit var eexchang_line3: LinearLayout

    private lateinit var eexchang_open: LinearLayout

    private lateinit var eexchang_time4: TextView

    private lateinit var eexchang_operation3: TextView

    private var mLoadCount = 0

    private val list: ArrayList<Map<String, Any>>? = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sample, container, false)
        custom_view = view.findViewById(R.id.custom_view)
        recycler_view_test_rv = view.findViewById(R.id.recycler_view_test_rv)
        return view
    }

    @SuppressLint("RtlHardcoded")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sampleFragmentSimpleAdapter = SampleFragmentSimpleAdapter(activity)

        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        recycler_view_test_rv.layoutManager = LinearLayoutManager(activity)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(activity), DividerItemDecoration.VERTICAL))

        val headerView = sampleFragmentSimpleAdapter.setHeaderView(R.layout.view_adapter_eexchange, recycler_view_test_rv)
        eexchang_line3 = headerView.findViewById(R.id.eexchang_line3)
        eexchang_time4 = headerView.findViewById(R.id.eexchang_time4)
        eexchang_operation3 = headerView.findViewById(R.id.eexchang_operation3)
        eexchang_open = headerView.findViewById(R.id.eexchang_open)
        eexchang_open.visibility = View.GONE
        headerView.findViewById<TextView>(R.id.eexchang_time3).visibility = View.GONE
        val paint = eexchang_operation3.paint
        paint.isFakeBoldText = true

        eexchang_line3.visibility = View.VISIBLE
        eexchang_time4.text = getString(R.string.textView_holding_list)
        eexchang_time4.setTextColor(Color.parseColor("#FF6D64"))

        getRecycler_view_test_rv()
        recycler_view_test_rv.adapter = sampleFragmentSimpleAdapter

        sampleFragmentSimpleAdapter.setOnItemClickListener { _, type, data ->
            if (type == 1) {
//                ToatUtils.showShort1(activity!!, "暂时未开放")
//                startActivity(Intent(activity, DetailsHoldingActivity::class.java))
            }
        }
    }


    private fun getRecycler_view_test_rv() {
        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
//                mHandler.postDelayed({
//                    //模拟数据加载失败的情况
//                    val random = Random()
//                    val success = random.nextBoolean()
//                    if (success) {
//                        custom_view.stopRefresh()
//                    } else {
//                        custom_view.stopRefresh(false)
//                    }
//                    //或者
//                    //                        xRefreshView1.stopRefresh(success);
//                }, 2000)
                mLoadCount = 0
                HttpALl.get().setqryHold(mHandler, activity!!)

            }

            override fun onLoadMore(isSilence: Boolean) {
//                mHandler.postDelayed({
//                    if (custom_view.hasLoadCompleted()) {
//                    }
//                    //                        for (int i = 0; i < 6; i++) {
//                    //                            adapter.insert(new SpecialProduct("More ", "21"),
//                    //                                    adapter.getAdapterItemCount());
//                    //                        }
//                    mLoadCount++
//                    if (mLoadCount >= 2) {
//                        custom_view.setLoadComplete(true)
//                    } else {
//                        // 刷新完成必须调用此方法停止加载
//                        custom_view.stopLoadMore(false)
//                    }
//                }, 1000)
            }
        })
    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1032 -> {
                    if (list != null && list.size > 0) {
                        list.clear()
                    }
                    var current_price = 0f
                    val qryHold = msg.obj as QryHold
                    for (index in qryHold.data.holdlist) {
                        val map = HashMap<String, Any>()
                        map["holdno"] = index.holdno
                        map["stkcode"] = index.stkcode
                        map["stkname"] = index.stkname
                        map["bsflag"] = index.bsflag
                        map["holdprice"] = index.holdprice
                        map["holdqty"] = index.holdqty
                        map["profitprice"] = index.profitprice
                        map["stopprice"] = index.stopprice
                        map["holdmargin"] = index.holdmargin
                        map["opentime"] = index.opentime
                        map["tyqflag"] = index.tyqflag
                        map["current_price"] = index.current_price
                        list!!.add(map)
                    }
                    current_price = qryHold.data.total_price.toFloat()
                    if (mLoadCount == 0) {
                        custom_view.stopRefresh()
                    }
                    if (current_price > 0) {
                        eexchang_operation3.setTextColor(Color.parseColor("#FF6D64"))
                    } else {
                        eexchang_operation3.setTextColor(Color.parseColor("#028845"))
                    }
                    eexchang_operation3.text = qryHold.data.total_price
                    eexchang_operation3.typeface = Typeface.defaultFromStyle(Typeface.BOLD)//加粗
                    eexchang_operation3.paint.isFakeBoldText = true//加粗
                    eexchang_operation3.textSize = 16f
                    sampleFragmentSimpleAdapter.setData(1, list, null)
                }
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            HttpALl.get().setqryHold(mHandler, activity!!)
        }
    }


    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("SampleFragment1") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("SampleFragment1")
    }

}