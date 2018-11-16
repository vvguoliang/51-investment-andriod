@file:Suppress("DEPRECATION")

package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.stockChart.data.KTimeData
import com.github.mikephil.charting.stockChart.view.OneDayView
import com.taobao.accs.ACCSManager.bindService
import com.umeng.analytics.MobclickAgent
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.kchartlib.MPchart.Constant
import sp51.spotpass.com.spotpass.ui.kchartlib.MinuteChartView
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.DataRequest
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.KLineEntity
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.MinuteLineEntity
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.DateUtil
import sp51.spotpass.com.spotpass.ui.utils.RandomCode
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import sp51.spotpass.com.spotpass.ui.utils.Util
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
class ExampleFragment0 : BaseFragment() {

//    private lateinit var minuteChartView: MinuteChartView
//
//    private lateinit var minuteChartView1: MinuteChartView

    private lateinit var lineChar: OneDayView

    private var kTimeData: KTimeData? = null

    private var i: Int = 0

    private var time: Long = 0L

    private var time1: Long = 0L

    private var jason = ""

    fun getFragmen(i: Int) {
        this.i = i
    }

    private var listdata1: ArrayList<KLineEntity> = ArrayList()

    fun getType(listdata1: List<KLineEntity>, jason: String) {
        this.listdata1 = listdata1 as ArrayList<KLineEntity>
        this.jason = jason
        getData()

    }

    fun getTy(date: Long, Price: Float, jason: String) {
        if (date - this.listdata1[this.listdata1.size - 1].Date.toLong() < 60) {
            for ((index, vale) in this.listdata1.withIndex()) {
                if (index == this.listdata1.size - 1) {
                    this.listdata1.removeAt(index)
                }
            }
            val kLineEntity = KLineEntity()
            kLineEntity.date = "$date"
            kLineEntity.Volume = Price
            this.listdata1.add(kLineEntity)

        } else if (date - this.listdata1[this.listdata1.size - 1].Date.toLong() == 60L) {
            val kLineEntity = KLineEntity()
            kLineEntity.date = "$date"
            kLineEntity.Volume = Price
            this.listdata1.add(kLineEntity)
        }
        this.jason = jason
        getData()
    }

    fun getData() {
        val jsonObject = JSONObject()
        val array = JSONArray()
//        if (listdata1.isNotEmpty()) {
        for ((indxt, vaule) in this.listdata1.withIndex()) {
            val jsonObject = JSONArray()
            jsonObject.put(vaule.Date + "000")
            jsonObject.put(vaule.volume)
            array.put(jsonObject)
            if (indxt == this.listdata1.size - 1) {
                time = (vaule.date + "000").toLong()
            }
            if (indxt == 0) {
                time1 = (vaule.date + "000").toLong()
            }
        }
        jsonObject.put("data", array)
        if (!TextUtils.isEmpty(this.jason)) {
            jsonObject.put("preClose", this.jason.toDouble())
        } else {
            jsonObject.put("preClose", 0.00)
        }

        lineChar.initChart(false)
        kTimeData!!.parseTimeData(jsonObject)
        lineChar.getXL(time, time1)

        lineChar.setDataToChart(kTimeData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.far_example1, container, false)
//        minuteChartView = view.findViewById(R.id.minuteChartView)
//        minuteChartView1 = view.findViewById(R.id.minuteChartView1)
        lineChar = view.findViewById(R.id.lineChar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var obje = JSONObject()
        kTimeData = KTimeData()
//        //测试数据
//        try {
//            obje = JSONObject(Constant.TIMEDATA)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
    }

//    private fun initView1(listdata: List<MinuteLineEntity>) = try {
//        //整体开始时间
//        val startTime = DateUtil.shortTimeFormat.parse("06:00")
//        //整体的结束时间
//        val endTime = DateUtil.shortTimeFormat.parse("24:00")
//        //休息开始时间
//        val firstEndTime = DateUtil.shortTimeFormat.parse("00:00")
//        //休息结束时间
//        val secondStartTime = DateUtil.shortTimeFormat.parse("00:00")
//        //获取随机生成的数据
//        val minuteData = DataRequest.getMinuteData(listdata, startTime,
//                endTime,
//                null,
//                null)
//        minuteChartView.initData(minuteData,
//                startTime,
//                endTime,
//                null,
//                null,
//                listdata.get(listdata.size - 1).price)
//    } catch (e: ParseException) {
//        e.printStackTrace()
//    }
//
//    private fun initView2(listdata: List<MinuteLineEntity>) = try {
//        //整体开始时间
//        val startTime = DateUtil.shortTimeFormat.parse("09:30")
//        //整体的结束时间
//        val endTime = DateUtil.shortTimeFormat.parse("15:00")
//        //休息开始时间
//        val firstEndTime = DateUtil.shortTimeFormat.parse("00:00")
//        //休息结束时间
//        val secondStartTime = DateUtil.shortTimeFormat.parse("00:00")
//        //获取随机生成的数据
//        val minuteData = DataRequest.getMinuteData(listdata, startTime,
//                endTime,
//                null,
//                null)
//        minuteChartView1.initData(minuteData,
//                startTime,
//                endTime,
//                null,
//                null,
//                listdata[listdata.size - 1].price)
//    } catch (e: ParseException) {
//        e.printStackTrace()
//    }

    //    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        println("=====$isVisibleToUser====")
//        if (isVisibleToUser) {
//            if (i == 0) {
//                aBoolean = true
//                initView1()
//            } else {
//                aBoolean = false
//                initView2()
//            }
//        }
//    }


    override fun onResume() {
        super.onResume()
        if (i == 0) {
//                minuteChartView.visibility = View.VISIBLE
//                minuteChartView1.visibility = View.GONE
//                initView1(listdata1)
        } else {
//                minuteChartView.visibility = View.GONE
//                minuteChartView1.visibility = View.VISIBLE
//                initView2(listdata1)
        }
        MobclickAgent.onPageStart("ExampleFragment0") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("ExampleFragment0")
    }
}