package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umeng.analytics.MobclickAgent
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.kchartlib.MinuteChartView
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.DataRequest
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.KLineEntity
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.MinuteLineEntity
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.DateUtil
import java.text.ParseException
import java.util.ArrayList

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
class ExampleFragment1 : BaseFragment() {

    private lateinit var minuteChartView: MinuteChartView

    private lateinit var minuteChartView1: MinuteChartView

    private var i: Int = 0

    fun getFragmen(i: Int) {
        this.i = i
    }

    private var listdata1: ArrayList<MinuteLineEntity> = ArrayList()

    fun getType(listdata1: List<KLineEntity>) {
        for (indxt in listdata1) {
            val minuteLine = MinuteLineEntity()
            minuteLine.price = indxt.volume
            minuteLine.time = DateUtil.shortTimeFormat.parse(indxt.Date)
            this.listdata1.add(minuteLine)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.far_example1, container, false)
//        minuteChartView = view.findViewById(R.id.minuteChartView)
//        minuteChartView1 = view.findViewById(R.id.minuteChartView1)
        return view
    }

    private fun initView1() = try {
        //整体开始时间
        val startTime = DateUtil.shortTimeFormat.parse("06:00")
        //整体的结束时间
        val endTime = DateUtil.shortTimeFormat.parse("24:00")
        //休息开始时间
        val firstEndTime = DateUtil.shortTimeFormat.parse("11:30")
        //休息结束时间
        val secondStartTime = DateUtil.shortTimeFormat.parse("13:00")
        //获取随机生成的数据
        val minuteData = DataRequest.getMinuteData(listdata1, startTime,
                endTime,
                null,
                null)
        minuteChartView.initData(minuteData,
                startTime,
                endTime,
                null,
                null,
                listdata1.get(listdata1.size - 1).price)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    private fun initView2() = try {
        //整体开始时间
        val startTime = DateUtil.shortTimeFormat.parse("06:00")
        //整体的结束时间
        val endTime = DateUtil.shortTimeFormat.parse("24:00")
        //休息开始时间
        val firstEndTime = DateUtil.shortTimeFormat.parse("11:30")
        //休息结束时间
        val secondStartTime = DateUtil.shortTimeFormat.parse("13:00")
        //获取随机生成的数据
        val minuteData = DataRequest.getMinuteData(listdata1, startTime,
                endTime,
                null,
                null)
        minuteChartView1.initData(minuteData,
                startTime,
                endTime,
                null,
                null,
                listdata1.get(listdata1.size - 1).price)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

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
            minuteChartView.visibility = View.VISIBLE
            minuteChartView1.visibility = View.GONE
        } else {
            minuteChartView.visibility = View.GONE
            minuteChartView1.visibility = View.VISIBLE
        }
        initView1()
        minuteChartView.invalidate()
        initView2()
        minuteChartView1.invalidate()
        MobclickAgent.onPageStart("ExampleFragment1") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("ExampleFragment1")
    }
}