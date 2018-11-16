@file:Suppress("NAME_SHADOWING")

package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.support.annotation.RequiresApi
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import com.umeng.analytics.MobclickAgent
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.adapter.SampleFragmentSimpleAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.*
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.ExampleActivity
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.service.LastPriceService
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import java.util.*
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
class SampleFragment : BaseFragment() {

    private lateinit var custom_view: XRefreshView

    private lateinit var recycler_view_test_rv: RecyclerView

    private lateinit var sampleFragmentSimpleAdapter: SampleFragmentSimpleAdapter

    private var mLoadCount = 0

    private lateinit var list: ArrayList<Map<String, Any>>

    private lateinit var list1: ArrayList<Map<String, String>>

    private lateinit var listqrt: ArrayList<Map<String, Any>>

    private var mapList1: ArrayList<Map<String, Any>>? = null

    private lateinit var mapList: ArrayList<List<Map<String, Any>>>

    private var data = 0

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
        //添加Android自带的分割线
//        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(activity), DividerItemDecoration.VERTICAL))

        getRecycler_view_test_rv()
        recycler_view_test_rv.adapter = sampleFragmentSimpleAdapter

        sampleFragmentSimpleAdapter.setOnItemClickListener { _, _, data ->
            if (list.size > 0) {
                this.data = data
                HttpALl.get().setqrykprice(list1[data]["quote"].toString(), mHandler, activity!!)
            }
        }
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
    var mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1008 -> {
                    val todayPrice = msg.obj as QryKprice
                    val target = todayPrice.data
                    val ClosePrice = target.closePrice
                    if (list != null && list.size > 0) {
                        val intent = Intent(activity, ExampleActivity::class.java)
                        intent.putExtra("stkcode", list[data]["qtecode"].toString())
                        intent.putExtra("quoteName", list[data]["name"].toString())
                        intent.putExtra("HighPrice", target.highPrice)
                        intent.putExtra("LowPrice", target.lowPrice)
                        intent.putExtra("ClosePrice", ClosePrice)
                        intent.putExtra("OpenPrice", target.openPrice)
                        startActivity(intent)
                    }
                }
                1018 -> {
                    val markey = msg.obj as Markey
                    list = ArrayList()
                    for (index in markey.data) {
                        val map = HashMap<String, Any>()
                        map["exchange_id"] = index.exchangeId
                        map["name"] = index.name
                        map["qtecode"] = index.qtecode
                        list.add(map)
                    }
                    getQRYQUOTETRDTIME(list[0]["qtecode"].toString())
                }
                1011 -> {
                    val stocklist = msg.obj as qrystock1
                    mapList = ArrayList()
                    val qtecode = StringBuilder()
                    for (indexe in listqrt) {
                        mapList1 = ArrayList()
                        for (index in stocklist.data) {
                            if (indexe["qtecode"]!! == index.quotecode) {
                                val map = HashMap<String, Any>()
                                map["stkcode"] = index.stkcode
                                map["stkname"] = index.stkname
                                map["stktype"] = index.stktype
                                map["stksize"] = index.stksize
                                map["marginrate"] = index.marginrate
                                map["openfeerate"] = index.openfeerate
                                map["closefeerate"] = index.closefeerate
                                map["holdfeerate"] = index.holdfeerate
                                map["orderlimitqry"] = index.orderlimitqry
                                map["sumlimitqry"] = index.sumlimitqry
                                map["quotecode"] = index.quotecode
                                map["losspoints"] = index.losspoints
                                map["profitpoints"] = index.profitpoints
                                map["unitprice"] = index.unitprice
                                map["name"] = indexe["name"].toString()
                                map["exchange_id"] = indexe["exchange_id"].toString()
                                map["qtecode"] = indexe["qtecode"].toString()
                                mapList1!!.add(map)
                            }
                        }
                        qtecode.append(indexe["qtecode"]).append("|")
                        if (mapList1!!.size > 0) {
                            mapList.add(mapList1!!)
                        }
                    }
//                    if (!AppUtil.instance.isServiceRunning(activity!!, "sp51.spotpass.com.spotpass.ui.service.LastPriceService")) {
                    val intent = Intent(activity, LastPriceService::class.java)
                    intent.putExtra("qtecode", qtecode.toString().substring(0, qtecode.toString().length - 1))
                    activity!!.bindService(intent, this@SampleFragment.serviceConnection, Activity.BIND_AUTO_CREATE)
//                    }
                    sampleFragmentSimpleAdapter.setData(0, listqrt, mapList)
                }
                1025 -> {
                    val qryQuoteTrdTime = msg.obj as QryQuoteTrdTime
                    listqrt = ArrayList()
                    for ((index, values) in list.withIndex()) {
                        val map = HashMap<String, Any>()
                        map["memo"] = qryQuoteTrdTime.data.memo
                        map["trdTime"] = qryQuoteTrdTime.data.trdTime
                        map["setTime"] = qryQuoteTrdTime.data.setTime
                        map["exchange_id"] = values["exchange_id"].toString()
                        map["name"] = values["name"].toString()
                        map["qtecode"] = values["qtecode"].toString()
                        listqrt.add(map)
                    }
                    getQtecode(listqrt[0]["qtecode"].toString())
                }
            }
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as LastPriceService.Binder
            val myService = binder.service
            myService.setCallback(object : LastPriceService.Callback {
                override fun onDataChange(data: Any) {
                    val qtepricelist = data as QryStockNoName
                    list1 = ArrayList<Map<String, String>>()
                    for (index in qtepricelist.data) {
                        val map = HashMap<String, String>()
                        map["floatPercent"] = index.floatPercent
                        map["floatPrice"] = index.floatPrice
                        map["price"] = index.price
                        map["quote"] = index.quote
                        map["lastPrice"] = index.lastPrice
                        list1.add(map)
                    }
                    sampleFragmentSimpleAdapter.getshijian(list1)
                }
            })
        }

        override fun onServiceDisconnected(name: ComponentName) {

        }
    }

    private fun getQtecode(qtecode: String) {
        HttpALl.get().setqrystock(qtecode, mHandler, this.activity!!)
    }

    private fun getQRYQUOTETRDTIME(qtecode: String) {
        HttpALl.get().setQRYQUOTETRDTIME("qryQuoteTrdTime", qtecode, mHandler, this.activity!!)
    }

    override fun onResume() {
        super.onResume()
        HttpALl.get().setMARKET(mHandler, this.activity!!)
        MobclickAgent.onPageStart("SampleFragment") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("SampleFragment")
    }

    override fun onStop() {
        super.onStop()
        activity!!.unbindService(serviceConnection)
    }

}