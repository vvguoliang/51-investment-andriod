@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umeng.analytics.MobclickAgent
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.widget.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.far_my.*
import sp51.spotpass.com.spotpass.MainActivity
import sp51.spotpass.com.spotpass.base.BaseActivityManager
import sp51.spotpass.com.spotpass.ui.activity.*
import sp51.spotpass.com.spotpass.ui.baseEntity.InFo
import sp51.spotpass.com.spotpass.ui.baseEntity.QryAccount
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.http.HttpImplements
import sp51.spotpass.com.spotpass.ui.view.Dialog.PopUtils
import sp51.spotpass.com.spotpass.ui.view.Dialog.PopUtils.*
import android.graphics.BitmapFactory
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.bumptech.glide.request.RequestOptions
import sp51.spotpass.com.spotpass.ui.baseEntity.DoSign
import sp51.spotpass.com.spotpass.ui.utils.*
import sp51.spotpass.com.spotpass.ui.view.Dialog.DoSingDialogBuilder


/**
 * @Time : 2018/5/2 no 下午2:31
 * @USER : vvguoliang
 * @File : MainHome.java
 * @Software: Android Studio
 *code is far away from bugs with the god animal protecting
 *   I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃   ☃   ┃
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
class MainMy : BaseFragment(), View.OnClickListener, ClickListener {

    private lateinit var my_button: Button

    private lateinit var my_button1: Button

    private lateinit var my_circleimage: CircleImageView

    private lateinit var my_circleimage1: CircleImageView

    private lateinit var my_integral_mall: LinearLayout //积分商城

    private lateinit var my_course_collection: LinearLayout // 课程收藏

    private lateinit var my_online_service: LinearLayout // 在线客服

    private lateinit var my_account_management: LinearLayout // 账号管理

    private lateinit var my_new: LinearLayout // 消息中心

    private lateinit var my_about_us: LinearLayout // 关于我们

    private lateinit var my_relative: RelativeLayout // 显示和隐藏

    private lateinit var my_linear: LinearLayout // 显示和隐藏

    private lateinit var my_linear1: LinearLayout // 登入或注册

    private lateinit var my_available_linear: LinearLayout // 登入或注册

    private var intent: Intent? = null

    private var account: String = ""

    private lateinit var my_name: TextView
    private lateinit var my_integral: TextView
    private lateinit var my_growth_value: TextView
    private lateinit var my_assets_text: TextView
    private lateinit var my_holding_assets_text: TextView
    private lateinit var my_available_balance_text: TextView
    private lateinit var title_textview: TextView

    private lateinit var activity: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.activity = this.getActivity()!!
        val view = inflater.inflate(R.layout.far_my, container, false)
        my_button = view.findViewById(R.id.my_button)
        my_button1 = view.findViewById(R.id.my_button1)
        my_circleimage = view.findViewById(R.id.my_circleimage)
        my_circleimage1 = view.findViewById(R.id.my_circleimage1)
        my_integral_mall = view.findViewById(R.id.my_integral_mall)
        my_course_collection = view.findViewById(R.id.my_course_collection)
        my_online_service = view.findViewById(R.id.my_online_service)
        my_account_management = view.findViewById(R.id.my_account_management)
        my_new = view.findViewById(R.id.my_new)
        my_about_us = view.findViewById(R.id.my_about_us)

        my_relative = view.findViewById(R.id.my_relative)
        my_linear = view.findViewById(R.id.my_linear)
        my_available_linear = view.findViewById(R.id.my_available_linear)
        my_linear1 = view.findViewById(R.id.my_linear1)
        my_name = view.findViewById(R.id.my_name)
        my_integral = view.findViewById(R.id.my_integral)
        my_growth_value = view.findViewById(R.id.my_growth_value)
        my_assets_text = view.findViewById(R.id.my_assets_text)
        my_holding_assets_text = view.findViewById(R.id.my_holding_assets_text)
        my_available_balance_text = view.findViewById(R.id.my_available_balance_text)
        title_textview = view.findViewById(R.id.title_textview)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        my_button.setOnClickListener(this)
        my_button1.setOnClickListener(this)
        my_circleimage.setOnClickListener(this)
        my_integral_mall.setOnClickListener(this)
        my_course_collection.setOnClickListener(this)
        my_online_service.setOnClickListener(this)
        my_account_management.setOnClickListener(this)
        my_new.setOnClickListener(this)
        my_about_us.setOnClickListener(this)
        my_linear1.setOnClickListener(this)
        my_linear.setOnClickListener(this)
        account_exit_account.setOnClickListener(this)
        my_growth_value.setOnClickListener(this)
        my_share.setOnClickListener(this)

        if (TextUtils.isEmpty(SPUtils.getInstance(this.activity, "login").getString("login", ""))) {
            getLiner(0)
        } else {
            getLiner(1)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getLiner(i: Int) {
        if (i == 0) {
            my_linear1.visibility = View.VISIBLE
            my_relative.visibility = View.GONE
            my_linear.visibility = View.GONE
            my_available_linear.visibility = View.GONE
            account_exit_account.visibility = View.GONE
            Glide.with(activity).load(R.mipmap.ic_gethead).into(my_circleimage1)
        } else {
            my_linear1.visibility = View.GONE
            my_relative.visibility = View.VISIBLE
            my_linear.visibility = View.VISIBLE
            my_available_linear.visibility = View.VISIBLE
            account_exit_account.visibility = View.VISIBLE

            my_name.text = SPUtils.getInstance(this.activity, "login").getString("phone", "")
            my_integral.text = "金币：0个"
            my_assets_text.text = "0.00"
            my_holding_assets_text.text = "0.00"
            my_available_balance_text.text = "0.00"
//            val spannableString = SpannableString("代金券2张")
//            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000")), 3, spannableString.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//            my_exchange_text.text = spannableString
            Glide.with(activity).load(R.mipmap.ic_gethead).into(my_circleimage)
        }
        title_textview.visibility = View.VISIBLE
        title_textview.text = getString(R.string.textView_personal_center)

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onClick(v: View?) {
        if (TextUtils.isEmpty(SPUtils.getInstance(activity, "login").getString("login", ""))) {
            val intent = Intent(activity, RegisterActivity::class.java)
            intent.putExtra("type", 1)
            startActivityForResult(intent, 209)
        } else
            when (v!!.id) {
                R.id.my_button -> startActivity(Intent(activity, RechargeActivity::class.java))
                R.id.my_button1 -> {
                    if (my_available_balance_text.text.toString().toFloat() <= 0) {
                        ToatUtils.showShort1(activity, "您的余额不足，不能提现")
                    } else {
                        startActivity(Intent(activity, PutForwardActivity::class.java))
                    }
                }
                R.id.my_circleimage -> {
                    startActivity(Intent(activity, AccountManagementActivity::class.java))
                }
                R.id.my_share -> HttpALl.get().setinfo(mHandler, activity)
                R.id.my_integral_mall -> startActivity(Intent(activity, IntegralMallActivity::class.java))
                R.id.my_course_collection -> HttpALl.get().setGETCOLLECTLIST(mHandler, activity)
                R.id.my_online_service -> startActivity(Intent(activity, OnlineServiceActivity::class.java))
                R.id.my_account_management -> startActivity(Intent(activity, AccountManagementActivity::class.java))
                R.id.my_new -> startActivity(Intent(activity, NewActivity::class.java))
                R.id.my_about_us -> startActivity(Intent(activity, AboutUsActivity::class.java))
                R.id.my_linear1 -> {
                    intent = Intent(activity, RegisterActivity::class.java)
                    intent!!.putExtra("type", 1)
                    startActivityForResult(intent, 209)
                }
                R.id.my_linear -> {
                    intent = Intent(activity, MyAssetsActivity::class.java)
                    intent!!.putExtra("totalPrice", my_assets_text.text)
                    intent!!.putExtra("holdingPrice", my_holding_assets_text.text)
                    intent!!.putExtra("balance", my_available_balance_text.text)
                    startActivity(intent)
                }
                R.id.account_exit_account -> HttpALl.get().setLOGOUT("logout",
                        SPUtils.getInstance(activity, "login").getString("phone", ""), mHandler, activity)
                R.id.my_growth_value -> HttpALl.get().setdoSign(mHandler, activity)
            }
    }

    override fun setUplistener(builder: PopBuilder) {
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 209 && resultCode == 209) {
            val mbid = data!!.getStringExtra("regtime")
            val qbid = data.getStringExtra("qbid")
            val balance = data.getStringExtra("balance")
            val holdmargin = data.getStringExtra("holdmargin")
            val frozenmargin = data.getStringExtra("frozenmargin")
            val incapital = data.getStringExtra("incapital")
            val outcapital = data.getStringExtra("outcapital")
            val matchfee = data.getStringExtra("matchfee")
            val holdfee = data.getStringExtra("holdfee")
            val lastprofit = data.getStringExtra("lastprofit")
            val initasset = data.getStringExtra("initasset")
            val shoppoints = data.getStringExtra("shoppoints")
            account = data.getStringExtra("account")

            my_name.text = SPUtils.getInstance(this.activity, "login").getString("phone", "")
            my_integral.text = "金币：0个"
            my_assets_text.text = "0.00"
            my_holding_assets_text.text = "0.00"
            my_available_balance_text.text = "0.00"

            getLiner(1)
        }
    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                114 -> {
//                    val intent = Intent(activity, RegisterActivity::class.java)
//                    intent.putExtra("type", 2)
//                    startActivityForResult(intent, 209)
                }
                1003 -> {
                    val qryAccount = msg.obj as QryAccount
                    if (TextUtils.isEmpty(qryAccount.data.name)) {
                        my_name.text = SPUtils.getInstance(activity, "login").getString("phone", "")
                    } else {
                        my_name.text = qryAccount.data.name
                    }
                    SPUtils.getInstance(activity, "login").put("name", my_name.text.toString())
                    val options = RequestOptions()
                    options.centerCrop().placeholder(R.mipmap.ic_gethead).error(R.mipmap.ic_gethead).fallback(R.mipmap.ic_gethead)
                    Glide.with(activity).load(qryAccount.data.avatar).apply(options).into(my_circleimage)
                    SPUtils.getInstance(activity, "img").put("img", qryAccount.data.avatar)
                    my_integral.text = "金币：${qryAccount.data.shoppoints}个"
                    my_assets_text.text = qryAccount.data.totalPrice
                    my_holding_assets_text.text = qryAccount.data.holdingPrice
                    my_available_balance_text.text = qryAccount.data.balance
                }
                1020 -> {
                    SPUtils.getInstance(activity, "Authorization").put("Authorization", "")
                    SPUtils.getInstance(activity, "login").put("login", "")
                    BaseActivityManager.activityManager.finishAll()
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.putExtra("position", 0)
                    startActivity(intent)
                }
                1052 -> {
                    val bmp = BitmapFactory.decodeResource(resources, R.mipmap.ic_preferential_red_envelopes)

                    val info = msg.obj as InFo
                    ShareWxapTencent(activity as Activity, info.data.url,
                            info.data.platform.qq.title, info.data.platform.qq.description,
                            info.data.platform.wechat.title, info.data.platform.wechat.description,
                            bmp, info.data.platform.qq.appId, info.data.platform.qq.appKey,
                            info.data.platform.wechat.appId, info.data.platform.wechat.appKey)
                }
                1054 -> {
                    val doSign = msg.obj as DoSign
                    DoSingDialogBuilder(activity)
                            .title("签到成功")
                            .urlText("https://www.baidu.com")
                            .cancelText("获得<font color='#FFFF00'><small> ${doSign.data.point} </small></font>金币")
                            .sureText("已连续签到第<font color='#FFFF00'><small> ${doSign.data.days} </small></font> 天<br>明天连续签到将获得<font color='#FFFF00'><small> ${doSign.data.nextPoint} </small></font>金币</br>")// + "已连续签到第  ${doSign.data.days}  天\n明天连续签到将获得${doSign.data.nextPoint}金币")
                            .setCancelOnClickListener {
                                onResume()
                            }
                            .setSureOnClickListener {
                                val intent = Intent(activity, WebViewActivity::class.java)
                                intent.putExtra("url", "${HttpImplements.get().HttpS}sign_rules.html")
                                intent.putExtra("type", "4")
                                startActivity(intent)
                            }
                            .build().show()
                    my_integral.text = "金币：${doSign.data.point}个"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 标记
        isCreated = true
    }

    private var isCreated = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isCreated) {
            return
        }

        if (isVisibleToUser) {
            onResume()

        }
    }

    override fun onResume() {
        super.onResume()
        if (!TextUtils.isEmpty(SPUtils.getInstance(activity, "login").getString("login", ""))) {
            HttpALl.get().setQryAccount("qryAccount", SPUtils.getInstance(this.activity, "login").getString("phone", ""), "", "", mHandler, this.activity)
        }
        MobclickAgent.onPageStart("MainMy") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("MainMy")
    }

    private fun getHead(): Int {
        var int = 0

        if (SPUtils.getInstance(this.activity, "myHead").getInt("myHeadUrl${my_name.text}", 0) == 0) {
            when (AppUtil.instance.getRandom(this.activity)) {
                0 -> int = R.mipmap.ic_head_portrait0
                1 -> int = R.mipmap.ic_head_portrait1
                2 -> int = R.mipmap.ic_head_portrait2
                3 -> int = R.mipmap.ic_head_portrait3
                4 -> int = R.mipmap.ic_head_portrait4
                5 -> int = R.mipmap.ic_head_portrait5
                6 -> int = R.mipmap.ic_head_portrait6
                7 -> int = R.mipmap.ic_head_portrait7
                8 -> int = R.mipmap.ic_head_portrait8
                9 -> int = R.mipmap.ic_head_portrait9
            }
            SPUtils.getInstance(this.activity, "myHead").put("myHeadUrl${my_name.text}", int)
            return int
        } else {
            return SPUtils.getInstance(this.activity, "myHead").getInt("myHeadUrl${my_name.text}", R.mipmap.ic_head_portrait0)
        }
    }
}