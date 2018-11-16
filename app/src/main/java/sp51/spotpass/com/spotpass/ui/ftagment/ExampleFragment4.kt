package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.stockChart.data.KLineData
import com.github.mikephil.charting.stockChart.view.KLineView
import com.umeng.analytics.MobclickAgent
import org.json.JSONArray
import org.json.JSONObject
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.kchartlib.KChartView
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.DataHelper
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.KChartAdapter
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.KLineEntity
import sp51.spotpass.com.spotpass.ui.kchartlib.formatter.DateFormatter

@SuppressLint("ValidFragment")
/**
 * @Time : 2018/5/22 no 10:02
 * @USER : vvguoliang
 * @File : ExampleFragment1.java
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
class ExampleFragment4 : BaseFragment() {

//    private lateinit var mKChartView: KChartView
//
//    private lateinit var mKChartView1: KChartView
//
//    private lateinit var mAdapter: KChartAdapter
//
//    private lateinit var mAdapter1: KChartAdapter

    private lateinit var combinedchart: KLineView

    private var kLineData: KLineData? = null

    private var type: Int = 0

    fun getType(listdata1: List<KLineEntity>, type: Int) {
        if (listdata1 != null && listdata1.size > 0) {
            this.listdata1 = listdata1 as java.util.ArrayList<KLineEntity>
            this.type = type
            getData()
        }
    }

    fun getTyp(date: Long, Price: Float, type: Int) {
        if (this.listdata1 != null && this.listdata1.size > 0) {
            if (date - this.listdata1[this.listdata1.size - 1].Date.toLong() < 15 * 60 * 60) {  // 1分钟
                var Open: Float = 0.toFloat()
                var High: Float = 0.toFloat()
                var Low: Float = 0.toFloat()
                var Volume: Float = 0.toFloat()
                for ((index, vale) in this.listdata1.withIndex()) {
                    if (index == this.listdata1.size - 1) {
                        Open = this.listdata1[index].Open
                        High = this.listdata1[index].High
                        Low = this.listdata1[index].Low
                        Volume = this.listdata1[index].volume
                        this.listdata1.removeAt(index)
                    }
                }
                val kLineEntity = KLineEntity()
                kLineEntity.date = "$date"
                kLineEntity.Close = Price
                kLineEntity.Open = Open
                kLineEntity.High = High
                kLineEntity.Low = Low
                kLineEntity.Volume = Volume
                this.listdata1.add(kLineEntity)

            } else if (date - this.listdata1[this.listdata1.size - 1].Date.toLong() == 15 * 60 * 60L) {
                if (settype != null) {
                    settype!!.onItemClick(3)
                }
            }
            this.type = type
            getData()
        }
    }

    private var settype : setTypeimage? = null

    interface setTypeimage{
        fun onItemClick(data: Int)
    }

    fun getTypeimage(listener: setTypeimage) {
        this.settype = listener
    }


    fun getData() {
        val jsonObject = JSONObject()
        val array = JSONArray()
//        if (listdata1.isNotEmpty()) {
        for ((indxt, vaule) in this.listdata1.withIndex()) {
            val jsonObject = JSONArray()
            jsonObject.put(vaule.Date + "000")
            jsonObject.put(vaule.Open)
            jsonObject.put(vaule.high)
            jsonObject.put(vaule.low)
            jsonObject.put(vaule.Close)
            jsonObject.put(vaule.Close)
            jsonObject.put(vaule.Close)
            jsonObject.put(vaule.Close)
            jsonObject.put(vaule.Close)
            array.put(jsonObject)
        }
        jsonObject.put("data", array)

        combinedchart.initChart(false)
//            SPUtils.getInstance(activity!!, "json").put("json", jsonObject.toString())
//        } else {
//            jsonObject = JSONObject(SPUtils.getInstance(activity!!, "json").getString("json", ""))
//        }
        kLineData!!.parseKlineData(jsonObject)
        combinedchart.setMaxVisibleXCount(0)
        combinedchart.setDataToChart(kLineData, this.type)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.far_example2, container, false)
//        mKChartView = view.findViewById(R.id.kchart_view)
//        mKChartView1 = view.findViewById(R.id.kchart_view1)
        combinedchart = view.findViewById(R.id.combinedchart)
        kLineData = KLineData(activity)
        return view
    }

//    private fun initView1() {
//        mAdapter = KChartAdapter()
//        mKChartView.adapter = mAdapter
//        mKChartView.dateTimeFormatter = DateFormatter()
//        mKChartView.setGridRows(4)
//        mKChartView.setGridColumns(4)
//        mKChartView.setOnSelectedChangedListener { view, point, index ->
//            val data = point as KLineEntity
//            Log.i("onSelectedChanged", "index:" + index + " closePrice:" + data.closePrice)
//        }
//    }
//
//    private fun initData1(listdata1: List<KLineEntity>) {
//        mKChartView.showLoading()
//        Thread {
//            val data =listdata1
//            DataHelper.calculate(data)
//            activity!!.runOnUiThread {
//                mAdapter.setNewData(data)
//                mKChartView.startAnimation()
//                mKChartView.refreshEnd()
//            }
//        }.start()
//    }
//
//    private fun initView2() {
//        mAdapter1 = KChartAdapter()
//        mKChartView1.adapter = mAdapter1
//        mKChartView1.dateTimeFormatter = DateFormatter()
//        mKChartView1.setGridRows(4)
//        mKChartView1.setGridColumns(4)
//        mKChartView1.setOnSelectedChangedListener { view, point, index ->
//            val data = point as KLineEntity
//            Log.i("onSelectedChanged", "index:" + index + " closePrice:" + data.closePrice)
//        }
//    }
//
//    private fun initData2(listdata1: List<KLineEntity>) {
//        mKChartView1.showLoading()
//        Thread {
//            val data = listdata1
//            DataHelper.calculate(data)
//            activity!!.runOnUiThread {
//                mAdapter1.setNewData(data)
//                mKChartView1.startAnimation()
//                mKChartView1.refreshEnd()
//            }
//        }.start()
//    }
//
//    override fun onConfigurationChanged(newConfig: Configuration?) {
//        super.onConfigurationChanged(newConfig)
//        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            mKChartView.setGridRows(3)
//            mKChartView.setGridColumns(8)
//            mKChartView1.setGridRows(3)
//            mKChartView1.setGridColumns(8)
//        } else if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            mKChartView.setGridRows(4)
//            mKChartView.setGridColumns(4)
//            mKChartView1.setGridRows(4)
//            mKChartView1.setGridColumns(4)
//        }
//    }

//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        println("=====$isVisibleToUser====")
//        if (isVisibleToUser) {
//            if (i == 0) {
//                initView1()
//                initData1()
//            } else {
//                initView2()
//                initData2()
//            }
//        }
//    }

    private var listdata1: ArrayList<KLineEntity> = ArrayList()

    override fun onResume() {
        super.onResume()
//        if (i == 0) {
//            mKChartView.visibility = View.VISIBLE
//            mKChartView1.visibility = View.GONE
//        } else {
//            mKChartView.visibility = View.GONE
//            mKChartView1.visibility = View.VISIBLE
//        }
//        initView1()
//        initView2()
        MobclickAgent.onPageStart("ExampleFragment4") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("ExampleFragment4")
    }
}