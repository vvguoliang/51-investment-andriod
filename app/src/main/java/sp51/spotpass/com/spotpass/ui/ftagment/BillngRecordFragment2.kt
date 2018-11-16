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
import sp51.spotpass.com.spotpass.ui.baseEntity.GetYYOutCashList
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
class BillngRecordFragment2(billingRecordActivity: BillingRecordActivity) : BaseFragment(), View.OnClickListener {

    private lateinit var custom_view: XRefreshView

    private lateinit var recycler_view_test_rv: RecyclerView

    private lateinit var billingRecordFragmentSimpleAdapter: BillingRecordFragmentSimpleAdapter

    private var mLoadCount = 0

    private var mLoad: Boolean = false

    private var billingRecordActivity: BillingRecordActivity? = null

    private lateinit var list: ArrayList<Map<String, Any>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.far_billng_record2, container, false)
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
        val map1 = mapOf(Pair("name", "体现中..."), Pair("money", "-6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val map2 = mapOf(Pair("name", "体现中..."), Pair("money", "-6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val map3 = mapOf(Pair("name", "体现中..."), Pair("money", "-6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val map4 = mapOf(Pair("name", "体现中..."), Pair("money", "-6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val map5 = mapOf(Pair("name", "提现成功"), Pair("money", "-6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val map6 = mapOf(Pair("name", "提现成功"), Pair("money", "-6541.26"), Pair("time", AppUtil.instance.getTime()), Pair("bank_name", "提现到王**的银行卡"))
        val list = listOf(map1, map2, map3, map4, map5, map6)
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
                mLoad = true
                HttpALl.get().setGetYYOutCashList("GetYYOutCashList",
                        SPUtils.getInstance(activity!!, "login").getString("phone", ""),
                        "", mHandler, activity!!)
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

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1017 -> {
                    if (mLoad) {
                        custom_view.stopRefresh()
                        mLoad = false
                    }
                    val getYYOutCashList = msg.obj as GetYYOutCashList
                    list = ArrayList()
                    for (indext in getYYOutCashList.data.yYOutCashList) {
                        val map = HashMap<String, Any>()
                        map["applycapital"] = indext.applycapital
                        map["applystate"] = indext.applystate
                        map["applytime"] = indext.applytime
                        map["bankName"] = indext.bankName
                        map["bankType"] = indext.bankType
                        map["cardNo"] = indext.cardNo
                        map["iscitywide"] = indext.iscitywide
                        map["checktime"] = indext.checktime
                        map["paycapital"] = indext.paycapital
                        map["subBankName"] = indext.subBankName
                        map["usrname"] = indext.usrname
                        map["usrtype"] = indext.usrtype
                        list.add(map)
                    }
                    billingRecordFragmentSimpleAdapter.setData(2, list)
                }
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            HttpALl.get().setGetYYOutCashList("GetYYOutCashList",
                    SPUtils.getInstance(activity!!, "login").getString("phone", ""),
                    "", mHandler, activity!!)
        }
    }

    override fun onClick(v: View?) {
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("BillngRecordFragment2") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("BillngRecordFragment2")
    }
}