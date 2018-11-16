package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.umeng.analytics.MobclickAgent
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.adapter.SampleFragmentSimpleAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.CurrentPendingOrder
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.recyclerview.SpacesItemDecoration
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import sp51.spotpass.com.spotpass.ui.view.Dialog.PubilcDialogBuilder
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
class SampleFragment2 : BaseFragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.eexchang_time4 -> {
                eexchang_operation3.setTextColor(Color.parseColor("#333333"))
                eexchang_time4.setTextColor(Color.parseColor("#FF6D64"))
                if (list != null && list!!.size > 0) {
                    list!!.clear()
                }
            }
            R.id.eexchang_operation3 -> {
                eexchang_operation3.setTextColor(Color.parseColor("#FF6D64"))
                eexchang_time4.setTextColor(Color.parseColor("#333333"))
                if (list != null && list!!.size > 0) {
                    list!!.clear()
                }
            }
        }
    }

    private lateinit var custom_view: XRefreshView

    private lateinit var recycler_view_test_rv: RecyclerView

    private lateinit var sampleFragmentSimpleAdapter: SampleFragmentSimpleAdapter

    private var mLoadCount = 0

    private lateinit var eexchang_line3: LinearLayout

    private lateinit var eexchang_time4: TextView

    private lateinit var eexchang_operation3: TextView

    private lateinit var eexchang_time3: TextView

    private var list: ArrayList<Map<String, Any>>? = ArrayList()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity == null) {
            activity == context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sample, container, false)
        custom_view = view.findViewById(R.id.custom_view)
        recycler_view_test_rv = view.findViewById(R.id.recycler_view_test_rv)
        return view
    }

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
        val headerView = sampleFragmentSimpleAdapter.setHeaderView(R.layout.view_adapter_eexchange, recycler_view_test_rv)

        eexchang_line3 = headerView.findViewById(R.id.eexchang_line3)
        eexchang_time4 = headerView.findViewById(R.id.eexchang_time4)
        eexchang_operation3 = headerView.findViewById(R.id.eexchang_operation3)
        eexchang_time3 = headerView.findViewById(R.id.eexchang_time3)
        eexchang_time3.visibility = View.GONE

        eexchang_line3.visibility = View.VISIBLE

        eexchang_time4.setOnClickListener(this)
        eexchang_time4.text = "正在挂单"
        eexchang_time4.setTextColor(Color.parseColor("#FF6D64"))
        eexchang_operation3.setOnClickListener(this)
        eexchang_operation3.text = "挂单历史"
        eexchang_operation3.setTextColor(Color.parseColor("#333333"))

        eexchang_time4.visibility = View.GONE
        eexchang_operation3.visibility = View.GONE


        getRecycler_view_test_rv()
        recycler_view_test_rv.adapter = sampleFragmentSimpleAdapter
        sampleFragmentSimpleAdapter.setOnItemClickListener(object : SampleFragmentSimpleAdapter.OnRecyclerViewItemClickListener {
            override fun onItemClick(view: View?, type: Int, data: Int) {
                if (type == 2) {
                    PubilcDialogBuilder(context).title("撤单").cancelText("取消").message("您确定要撤单吗？").sureText("确定").setCancelOnClickListener { v13 -> }.setSureOnClickListener { v14 ->
                        HttpALl.get().setCancelPendingOrder(list!![data -1]["id"].toString().toLong(), mHandler, activity!!) }.build().show()
                }
            }
        })
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
                HttpALl.get().setcurrentPendingOrder(mLoadCount.toLong(), mHandler, activity!!)
            }

            override fun onLoadMore(isSilence: Boolean) {
//                mHandler.postDelayed({
//                    if (custom_view.hasLoadCompleted()) {
//                    }
                //                        for (int i = 0; i < 6; i++) {
                //                            adapter.insert(new SpecialProduct("More ", "21"),
                //                                    adapter.getAdapterItemCount());
                //                        }
                mLoadCount++
//                    if (mLoadCount >= 2) {
//                        custom_view.setLoadComplete(true)
//                    } else {
//                        // 刷新完成必须调用此方法停止加载
//                        custom_view.stopLoadMore(false)
//                    }
//                }, 1000)
                HttpALl.get().setcurrentPendingOrder(mLoadCount.toLong(), mHandler, activity!!)
            }
        })
    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1055 -> {
                    if (mLoadCount == 0) {
                        if (list != null && list!!.size > 0) {
                            list!!.clear()
                        }
                    }
                    val currentPendingOrder = msg.obj as CurrentPendingOrder
                    for (index in currentPendingOrder.data.data) {
                        val map = HashMap<String, Any>()
                        map["bsflag"] = index.bsflag
                        map["createdTime"] = index.createdTime
                        map["id"] = index.id
                        map["losspoints"] = index.losspoints
                        map["orderprice"] = index.orderprice
                        map["orderqty"] = index.orderqty
                        map["profitpoints"] = index.profitpoints
                        map["qtecode"] = index.qtecode
                        map["qtecodeName"] = index.qtecodeName
                        map["stkcode"] = index.stkcode
                        map["price"] = index.price
                        map["type"] = "1"
                        list!!.add(map)
                    }
                    if (mLoadCount == 0) {
                        custom_view.stopRefresh()
                    } else {
                        custom_view.setLoadComplete(true)
                    }
                    sampleFragmentSimpleAdapter.setData(2, list, null)
                }
                1057 -> {
                    ToatUtils.showShort1(activity!!, "已撤单")
                    onResume()
                    sampleFragmentSimpleAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        HttpALl.get().setcurrentPendingOrder(mLoadCount.toLong(), mHandler, activity!!)
//        HttpALl.get().setallPendingOrder(mLoadCount.toLong(), mHandler, activity!!)
        MobclickAgent.onPageStart("SampleFragment2") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("SampleFragment2")
    }
}