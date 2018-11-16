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
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.activity.BillingRecordActivity
import sp51.spotpass.com.spotpass.ui.adapter.BillingRecordFragmentSimpleAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.QryOrderH
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import java.util.*
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
class BillngRecordFragment1(billingRecordActivity: BillingRecordActivity) : BaseFragment(), View.OnClickListener {

    private lateinit var custom_view: XRefreshView

    private lateinit var recycler_view_test_rv: RecyclerView

    private lateinit var billingRecordFragmentSimpleAdapter: BillingRecordFragmentSimpleAdapter

    private var mLoadCount = 0

    private lateinit var list: ArrayList<Map<String, Any>>

    private var billingRecordActivity: BillingRecordActivity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.far_billng_record1, container, false)
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
        val map1 = mapOf(Pair("name", "产品名称"), Pair("money", "6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val map2 = mapOf(Pair("name", "产品名称"), Pair("money", "6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val map3 = mapOf(Pair("name", "产品名称"), Pair("money", "6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val map4 = mapOf(Pair("name", "产品名称"), Pair("money", "6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val map5 = mapOf(Pair("name", "产品名称"), Pair("money", "6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val map6 = mapOf(Pair("name", "产品名称"), Pair("money", "6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val list = listOf(map1, map2, map3, map4, map5, map6)

        recycler_view_test_rv.adapter = billingRecordFragmentSimpleAdapter
    }


    private fun getRecycler_view_test_rv() {
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
                1016 -> {
                    val qryOrderH = msg.obj as QryOrderH
                    val map1 = mapOf(Pair("name", "产品名称"), Pair("money", "6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
                    list = ArrayList()
                    for (index in qryOrderH.data.orderHlist) {
                        val map = HashMap<String, Any>()
                        map.put("money", index.openprice)
                        map.put("name", index.stkname)
                        map.put("time", index.closetime)
                        map.put("closeprofit", index.closeprofit)
                        map.put("bsflag", index.bsflag)
                        map.put("orderqty", index.orderqty)
                        map.put("bank_name", "")
                        list.add(map)
                    }
                    billingRecordFragmentSimpleAdapter.setData(1, list)
                }
            }
        }
    }

    override fun onClick(v: View?) {
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
        }
    }

    override fun onResume() {
        super.onResume()
        HttpALl.get().setQryOrderH("qryOrderH",
                SPUtils.getInstance(activity!!, "login").getString("phone", ""),
                "", "", "", "", mHandler, activity!!)
        MobclickAgent.onPageStart("BillngRecordFragment1") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("BillngRecordFragment1")
    }
}