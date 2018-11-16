@file:Suppress("DEPRECATION")

package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.os.*
import android.text.*
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_designates.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import sp51.spotpass.com.spotpass.ui.baseEntity.QryAccount
import sp51.spotpass.com.spotpass.ui.baseEntity.QryStock
import sp51.spotpass.com.spotpass.ui.baseEntity.QryStockNoName
import sp51.spotpass.com.spotpass.ui.baseEntity.TodayPrice
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.service.LastPriceService
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import sp51.spotpass.com.spotpass.ui.utils.Util
import sp51.spotpass.com.spotpass.ui.view.Dialog.DialogBuilderRule
import java.text.DecimalFormat
import java.util.HashMap


@SuppressLint("Registered", "ParcelCreator")
/**
 * @Time : 2018/5/19 no 15:48
 * @USER : vvguoliang
 * @File : DesignatesActivity.java
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
class DesignatesActivity : BaseActivity(), View.OnClickListener {

    private var stkcode: String = ""

    private var stkcode1: String = ""

    private var quoteName: String = ""

    private var quote: String = ""

    private var highPrice = ""

    private var lowPrice = ""

    private var price: Float = 0.0f

    private var priceString: String = ""

    private var price1: String = ""

    private var losspoints = "0.76"

    private var profitpoints = "0.76"

    private var handBy: Int = 1

    private var list: ArrayList<TextView>? = null

    private var listprofit: ArrayList<TextView>? = null

    private var loss: ArrayList<TextView>? = null

    private var hands: Int = 1

    private var mapList: ArrayList<Map<String, Any>>? = null

    private var money: String = "0.00"

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_designates
    }

    override fun initView(rootView: View) {
    }

    @SuppressLint("SetTextI18n")
    override fun setListener() {
        stkcode = intent.getStringExtra("stkcode")
        quoteName = intent.getStringExtra("quoteName")
        quote = intent.getStringExtra("price")
        stkcode1 = intent.getStringExtra("stkcode1")
        name = getString(R.string.textView_buy_register)
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)

        title_textview.visibility = View.VISIBLE
        title_textview.text = quoteName

        intent = Intent(this, LastPriceService::class.java)
        intent.putExtra("qtecode", stkcode)
        bindService(intent, serviceConnection1, Context.BIND_AUTO_CREATE)

        designates_dialog_text.setText(quote)

        designates_dialog_text.setSelection(designates_dialog_text.text.toString().length)

        designates_dialog_text.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                if (lowPrice.toFloat() > s.toString().toFloat()) {
                    designates_dialog_text.setText(lowPrice)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        designates_dialog_plus.setOnClickListener(this)
        designates_dialog_minus_sign.setOnClickListener(this)
        list = ArrayList()
        list!!.add(number_hands1)
        list!!.add(number_hands2)
        list!!.add(number_hands3)
        list!!.add(number_hands4)
        for (indx in list!!) {
            indx.setOnClickListener(this)
        }
        listprofit = ArrayList()
        listprofit!!.add(target_profit76)
        listprofit!!.add(target_profit20)
        listprofit!!.add(target_profit40)
        listprofit!!.add(target_profit60)
        listprofit!!.add(target_profit80)
        listprofit!!.add(target_profit100)
        loss = ArrayList()
        loss!!.add(stop_loss76)
        loss!!.add(stop_loss20)
        loss!!.add(stop_loss30)
        loss!!.add(stop_loss40)
        loss!!.add(stop_loss50)
        loss!!.add(stop_loss60)

        for (profit in listprofit!!) {
            profit.setOnClickListener(this)
        }
        for (profit in loss!!) {
            profit.setOnClickListener(this)
        }
        text_hands1.setOnClickListener(this)
        text_hands2.setOnClickListener(this)
        buy_text.setOnClickListener(this)
        fall_text.setOnClickListener(this)
        text_hands3.text = "$hands"


        gold_entry_linear.setOnClickListener(this)
    }

    override fun doBusiness() {
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        var profitpointsfloat = 0.0f
        var losspointsfloat = 0.0f
        var marginrate = ""
        if (mapList != null && mapList!!.size > 0) {
            profitpointsfloat = mapList!![handBy]["profitpoints"].toString().toFloat()
            losspointsfloat = mapList!![handBy]["losspoints"].toString().toFloat()
            marginrate = mapList!![handBy]["marginrate"].toString()
        }
        val df = DecimalFormat("#####0.0")
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.designates_dialog_plus -> {
                price = designates_dialog_text.text.toString().toFloat() + 0.1f
                designates_dialog_text.setText(df.format(price))
            }
            R.id.designates_dialog_minus_sign -> {
                price = designates_dialog_text.text.toString().toFloat() - 0.1f
                designates_dialog_text.setText(df.format(price))
            }
            R.id.number_hands1 -> {
                handBy = 0
                getBy(handBy)
            }
            R.id.number_hands2 -> {
                handBy = 1
                getBy(handBy)
            }
            R.id.number_hands3 -> {
                handBy = 2
                getBy(handBy)
            }
            R.id.number_hands4 -> {
                handBy = 3
                getBy(handBy)
            }
            R.id.text_hands1 -> {
                if (mapList != null && mapList!!.size > 0) {
                    if (hands + 1 <= mapList!!.get(handBy).get("sumlimitqry").toString().toInt()) {
                        hands++
                        text_hands3.text = "$hands"
                        marginrate("${marginrate.toFloat() * hands}")
                        cost_text.text = "(手续费 ${df.format(mapList!![handBy]["closefeerate"].toString().toFloat() * hands)} 元，平仓时收取)"

                        holdfeerate(df.format(mapList!![handBy]["holdfeerate"].toString().toFloat() * hands))
                    } else {
                        ToatUtils.showShort1(this, "您已超出当前规格和手数，请选择其他相关规格和手数")
                    }
                }
            }
            R.id.text_hands2 -> {
                if (hands - 2 >= 0) {
                    hands--
                    text_hands3.text = "$hands"
                    marginrate("${marginrate.toFloat() * hands}")
                    cost_text.text = "(手续费 ${df.format(mapList!![handBy]["closefeerate"].toString().toFloat() * hands)} 元，平仓时收取)"
                    holdfeerate(df.format(mapList!![handBy]["holdfeerate"].toString().toFloat() * hands))
                }
            }
            R.id.target_profit76 -> {
                profitpoints = "${profitpointsfloat * 0.76}"
                getType(0, 1)
            }
            R.id.target_profit20 -> {
                profitpoints = "${profitpointsfloat * 0.20}"
                getType(1, 1)
            }
            R.id.target_profit40 -> {
                profitpoints = "${profitpointsfloat * 0.40}"
                getType(2, 1)
            }
            R.id.target_profit60 -> {
                profitpoints = "${profitpointsfloat * 0.60}"
                getType(3, 1)
            }
            R.id.target_profit80 -> {
                profitpoints = "${profitpointsfloat * 0.80}"
                getType(4, 1)
            }
            R.id.target_profit100 -> {
                profitpoints = "${profitpointsfloat * 1}"
                getType(5, 1)
            }
            R.id.stop_loss76 -> {
                losspoints = "${losspointsfloat * 0.76}"
                getType(0, 2)
            }
            R.id.stop_loss20 -> {
                losspoints = "${losspointsfloat * 0.20}"
                getType(1, 2)
            }
            R.id.stop_loss30 -> {
                losspoints = "${losspointsfloat * 0.30}"
                getType(2, 2)
            }
            R.id.stop_loss40 -> {
                losspoints = "${losspointsfloat * 0.40}"
                getType(3, 2)
            }
            R.id.stop_loss50 -> {
                losspoints = "${losspointsfloat * 0.50}"
                getType(4, 2)
            }
            R.id.stop_loss60 -> {
                losspoints = "${losspointsfloat * 0.60}"
                getType(5, 2)
            }
            R.id.buy_text -> {
                if (TextUtils.isEmpty(SPUtils.getInstance(this, "login").getString("login", ""))) run {
                    Util.registerA(this@DesignatesActivity)
                } else {
                    if (money.toFloat() > 0) {
                        DialogBuilderRule(this).title("挂单").message(Html.fromHtml("您确定挂单并且<br>同意<font color='#FF4081'><small>《挂单建仓限制条款》</small></font>吗？</br>")).cancelText("取消").sureText("确定").messageClickLkistener(true).setCancelOnClickListener { }.setSureOnClickListener {
                            if (mapList != null && mapList!!.size > 0) {
                                HttpALl.get().setPendingOrder(price1, stkcode1, 1, designates_dialog_text.text.toString(), hands.toLong(), losspoints, profitpoints, 4, mHandler, this)
                            }
                        }.build().show()
                    } else {
                        ToatUtils.showShort1(this, "您的账户资金不足，请充值")
                    }
                }
            }
            R.id.fall_text -> {
                if (TextUtils.isEmpty(SPUtils.getInstance(this, "login").getString("login", ""))) run {
                    Util.registerA(this@DesignatesActivity)
                } else {
                    if (money.toFloat() > 0) {
                        DialogBuilderRule(this).title("挂单").message(Html.fromHtml("您确定挂单并且<br>同意<font color='#FF4081'><small>《挂单建仓限制条款》</small></font>吗？</br>")).messageClickLkistener(true).cancelText("取消").sureText("确定").setCancelOnClickListener { }.setSureOnClickListener {
                            if (mapList != null && mapList!!.size > 0) {
                                HttpALl.get().setPendingOrder(price1, stkcode1, 2, designates_dialog_text.text.toString(), hands.toLong(), losspoints, profitpoints, 4, mHandler, this)
                            }
                        }.build().show()
                    } else {
                        ToatUtils.showShort1(this, "您的账户资金不足，请充值")
                    }
                }
            }
            R.id.gold_entry_linear -> {
                if (TextUtils.isEmpty(SPUtils.getInstance(this, "login").getString("login", ""))) run {
                    Util.registerA(this@DesignatesActivity)
                } else {
                    startActivity(Intent(this@DesignatesActivity, RechargeActivity::class.java))
                }
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1009 -> {
                    val qryStockNoName = msg.obj as QryStockNoName
                    val qtepricelist = qryStockNoName.data[0]
                    title_textview.text = "$quoteName ${qtepricelist.price}"
                    priceString = qryStockNoName.data[0].price
                }
                1008 -> {
                    val todaypricet = msg.obj as TodayPrice
                    highPrice = todaypricet.data.sourceArray.highPrice
                    lowPrice = todaypricet.data.sourceArray.lowPrice
                }
                1011 -> {
                    val qryStock = msg.obj as QryStock
                    mapList = ArrayList()
                    for (indxe in qryStock.data.stocklist) {
                        if (stkcode == indxe.quotecode && indxe.marginrate.toLong() <= 500) {
                            val map = HashMap<String, Any>()
                            map["stkcode"] = indxe.stkcode
                            map["stkname"] = indxe.stkname
                            map["stktype"] = indxe.stktype
                            map["stksize"] = indxe.stksize
                            map["marginrate"] = indxe.marginrate
                            map["openfeerate"] = indxe.openfeerate
                            map["closefeerate"] = indxe.closefeerate
                            map["holdfeerate"] = indxe.holdfeerate
                            map["orderlimitqry"] = indxe.orderlimitqry
                            map["sumlimitqry"] = indxe.sumlimitqry
                            map["quotecode"] = indxe.quotecode
                            map["losspoints"] = indxe.losspoints
                            map["profitpoints"] = indxe.profitpoints
                            map["unitprice"] = indxe.unitprice
                            mapList!!.add(map)
                        }
                    }
//                    for ((indexe, vale) in mapList!!.withIndex()) {
//                        list!![indexe].text = vale["stkname"].toString()
//                    }
                    for ((index, vale) in mapList!!.withIndex()) {
                        if (stkcode1 == vale["stkcode"].toString()) {
                            getBy(index)
                            marginrate(mapList!![index]["marginrate"].toString())
                            cost_text.text = "(手续费 ${mapList!![index]["closefeerate"]} 元，平仓时收取)"
                        }
                    }
                }
                1003 -> {
                    val qryAccount = msg.obj as QryAccount
                    money = qryAccount.data.balance

                    val spannableString = SpannableString("可用金额  ${money}元")
                    spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF6D64")), 4, spannableString.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    balance_text.text = spannableString
                }
                1056 -> ToatUtils.showShort1(this@DesignatesActivity, "挂单成功")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getBy(type: Int) {
        hands = 1
        text_hands3.text = "${hands}"
        for ((index, vtextview) in this.list!!.withIndex()) {
            if (type == index) {
                vtextview.setTextColor(Color.WHITE)
                vtextview.setBackgroundResource(R.drawable.shape_corner_while_blue)

                holdfeerate(mapList!![type]["holdfeerate"].toString())

                marginrate(mapList!![type]["marginrate"].toString())

                stkcode1 = mapList!![type]["stkcode"].toString()

                price1 = mapList!![type]["marginrate"].toString()

                cost_text.text = "(手续费 ${mapList!![type]["closefeerate"]} 元，平仓时收取)"
            } else {
                vtextview.setTextColor(Color.BLACK)
                vtextview.setBackgroundResource(R.drawable.shape_corner_blue)
            }
        }
    }

    private fun holdfeerate(holdfeerate: String) {
        val spannableString2 = SpannableString("波动1个点赚" + holdfeerate + "元")
        spannableString2.setSpan(ForegroundColorSpan(Color.parseColor("#FF6D64")), 6, spannableString2.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        designates_dialog_wave.text = spannableString2
    }

    private fun marginrate(marginrate: String) {
        val spannableString1 = SpannableString("预计花费" + marginrate + "元")
        spannableString1.setSpan(ForegroundColorSpan(Color.parseColor("#FF6D64")), 4, spannableString1.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spend_text.text = spannableString1
    }


    private fun getType(type: Int, intint: Int) {
        if (intint == 1) {
            for ((profit, value) in listprofit!!.withIndex()) {
                if (profit == type) {
                    value.setTextColor(Color.WHITE)
                    value.setBackgroundResource(R.drawable.shape_c20_red)
                } else {
                    value.setTextColor(Color.BLACK)
                    value.setBackgroundResource(R.drawable.shape_corner_red_while)
                }
            }
        } else {
            for ((profit, value) in loss!!.withIndex()) {
                if (profit == type) {
                    value.setTextColor(Color.WHITE)
                    value.setBackgroundResource(R.drawable.shape_corner_green)
                } else {
                    value.setTextColor(Color.BLACK)
                    value.setBackgroundResource(R.drawable.shape_c5_green_whlie)
                }
            }
        }
    }

    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        HttpALl.get().setTodayPrice(stkcode, mHandler, this)
        HttpALl.get().setQryStock("qryStock", stkcode, mHandler, this)
        if (!TextUtils.isEmpty(SPUtils.getInstance(this, "login").getString("phone", "")))
            HttpALl.get().setQryAccount("qryAccount", SPUtils.getInstance(this, "login").getString("phone", ""), "", "", mHandler, this)
        MobclickAgent.onPageStart(name) //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    private val serviceConnection1 = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as LastPriceService.Binder
            val myService = binder.service
            myService.setCallback(object : LastPriceService.Callback {

                override fun onDataChange(data: Any) {
                    val msg = Message()
                    msg.what = 1009
                    msg.obj = data
                    mHandler.sendMessage(msg)
                }
            })
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }
    }

    override fun onPause() {
        super.onPause()
        if (AppUtil.instance.isServiceRunning(this, "sp51.spotpass.com.spotpass.ui.service.LastPriceService")) {
            unbindService(serviceConnection1)
        }
        MobclickAgent.onPageEnd(name) //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this)
    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(R.id.top_view)?.init()
        ImmersionBar.with(this@DesignatesActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }
}
