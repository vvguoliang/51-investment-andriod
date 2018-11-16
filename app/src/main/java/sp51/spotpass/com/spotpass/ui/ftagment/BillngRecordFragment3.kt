package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umeng.analytics.MobclickAgent
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.activity.BillingRecordActivity
import sp51.spotpass.com.spotpass.ui.adapter.BillingRecordFragmentSimpleAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.GetRechargeList
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@SuppressLint("ValidFragment")
/**
 * @Time : 2018/5/20 no 17:12
 * @USER : vvguoliang
 * @File : BillngRecordFragment1.java
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
class BillngRecordFragment3(billingRecordActivity: BillingRecordActivity) : BaseFragment(), View.OnClickListener {

    private lateinit var custom_view: XRefreshView

    private lateinit var recycler_view_test_rv: RecyclerView

    private lateinit var billingRecordFragmentSimpleAdapter: BillingRecordFragmentSimpleAdapter

    private var mLoadCount = 0

    private var list: ArrayList<Map<String, Any>>? = null

    private var billingRecordActivity: BillingRecordActivity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.far_billng_record3, container, false)
        custom_view = view.findViewById(R.id.custom_view)
        recycler_view_test_rv = view.findViewById(R.id.recycler_view_test_rv)
        this.billingRecordActivity = billingRecordActivity
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billingRecordFragmentSimpleAdapter = BillingRecordFragmentSimpleAdapter(this.billingRecordActivity)

        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        recycler_view_test_rv.layoutManager = LinearLayoutManager(this.billingRecordActivity)

        getRecycler_view_test_rv()
        recycler_view_test_rv.adapter = billingRecordFragmentSimpleAdapter

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
                mLoadCount == 0
                HttpALl.get().setGetRechargeList("GetRechargeList",
                        SPUtils.getInstance(activity!!, "login").getString("phone", ""),
                        "", mHandler, activity!!)
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
                1037 -> {
                    if (mLoadCount == 0) {
                        custom_view.stopRefresh()
                    }
                    val getRechargeList = msg.obj as GetRechargeList
                    list = ArrayList()
                    for (index in getRechargeList.data.rechargeList) {
                        val map = HashMap<String, Any>()
                        map["custid"] = index.custid
                        map["exchangerate"] = index.exchangerate
                        map["gdscode"] = index.gdscode
                        map["gdsname"] = index.gdsname
                        map["gdsprice"] = index.gdsprice
                        map["gdsqty"] = index.gdsqty
                        map["guid"] = index.guid
                        map["memo"] = index.memo
                        map["mid"] = index.mid
                        map["openid"] = index.openid
                        map["ordercapital"] = index.ordercapital
                        map["orderno"] = index.orderno
                        map["orderstatus"] = index.orderstatus
                        map["ordertime"] = index.ordertime
                        map["ordertype"] = index.ordertype
                        map["payamt"] = index.payamt
                        map["pwd"] = index.pwd
                        list!!.add(map)
                    }
                    billingRecordFragmentSimpleAdapter.setData(3, list)
                }
            }
        }
    }

    override fun onClick(v: View?) {
    }

    override fun onResume() {
        super.onResume()
        HttpALl.get().setGetRechargeList("GetRechargeList",
                SPUtils.getInstance(activity!!, "login").getString("phone", ""),
                "", mHandler, activity!!)
        MobclickAgent.onPageStart("BillngRecordFragment3") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("BillngRecordFragment3")
    }
}