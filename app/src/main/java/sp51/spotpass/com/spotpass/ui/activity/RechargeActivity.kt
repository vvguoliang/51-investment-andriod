package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_recharge.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.R.id.*
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.PictureAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.MyBankCard
import sp51.spotpass.com.spotpass.ui.baseEntity.RechargAmount
import sp51.spotpass.com.spotpass.ui.baseEntity.RechargeYunJu
import sp51.spotpass.com.spotpass.ui.baseEntity.UpacpRechatge
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.http.HttpImplements
import sp51.spotpass.com.spotpass.ui.utils.RandomCode
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils

@SuppressLint("Registered")
/**
 * @Time : 2018/5/16 no 18:29
 * @USER : vvguoliang
 * @File : RechargeActivity.java
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
class RechargeActivity : BaseActivity(), View.OnClickListener, AdapterView.OnItemClickListener {

    private lateinit var pictureAdapter: PictureAdapter

    private var alipay_W: String = ""

    private var money: String = ""

    private var token_id: String = ""

    private var payReturnUrl: String = ""

    private var recharstring: ArrayList<String> = ArrayList()

    private var list: ArrayList<String>? = null

    private var int: Int = 5

    private var type = 0;

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_recharge
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        title_right_arrow_white.setOnClickListener(this)
        recharge_linear1.setOnClickListener(this)
        recharge_linear3.setOnClickListener(this)
        recharge_linear2.setOnClickListener(this)
        recharge_button.setOnClickListener(this)

        recharge_checkbox1.setOnClickListener(this)
        recharge_checkbox2.setOnClickListener(this)
        recharge_checkbox3.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun doBusiness() {
        title_right_arrow_white.visibility = View.VISIBLE
        title_textview.visibility = View.VISIBLE
        name = getString(R.string.textView_gold_entry)
        title_textview.text = name

        pictureAdapter = PictureAdapter(this)
        recharge_gridview.adapter = pictureAdapter
        recharge_gridview.onItemClickListener = this
        recharge_linear.visibility = View.GONE
        recharge_linear.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        pictureAdapter.getViweH(position)
        int = position
        if (recharstring != null && recharstring.size > 0) {
            money = recharstring[int]
            recharge_button.text = alipay_W + "支付￥" + money
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.recharge_linear1 -> {
                type = 0
                getBRCheck(type)
            }
            R.id.recharge_linear3 -> {
                type = 2
                getBRCheck(type)
            }
            R.id.recharge_linear2 -> {
                type = 1
                getBRCheck(type)
            }
            R.id.recharge_checkbox1 -> {
                type = 0
                getBRCheck(type)
            }
            R.id.recharge_checkbox2 -> {
                type = 1
                getBRCheck(type)
            }
            R.id.recharge_checkbox3 -> {
                type = 2
                getBRCheck(type)
            }
            R.id.recharge_linear -> {

            }
            R.id.recharge_button -> {
                var string = ""
                if (TextUtils.isEmpty(payReturnUrl)) {
                    string = "${HttpImplements.get().HttpS}success.html"
                } else {
                    string = payReturnUrl
                }
                when (type) {
                    0 -> {
                        val intent = Intent(this, MyBankCardActivity::class.java)
                        intent.putExtra("type", "0")
                        startActivityForResult(intent, 220)
                    }
                    1 -> HttpALl.get().setrechargeYunJu(SPUtils.getInstance(this, "login").getString("phone", ""), "ALIPAY", "", money, "", string, mHandler, this)
                    else -> HttpALl.get().setupacpRechargeIPS(SPUtils.getInstance(this, "login").getString("phone", ""), "", "", money, list!![3], string, mHandler, this)
                }
                    SPUtils.getInstance(this, "recharg").put("recharg1", alipay_W)
                    SPUtils.getInstance(this, "recharg").put("recharg2", SPUtils.getInstance(this, "login").getString("phone", ""))
                    SPUtils.getInstance(this, "recharg").put("recharg3", money)
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1009 -> {
                    token_id = msg.obj as String
                    startActivityForResult(Intent(this@RechargeActivity, PaymentSuccess::class.java), 200)
                }
                1026 -> {
                    if (recharstring != null && recharstring.size > 0) {
                        recharstring.clear()
                    }
                    val rechargAmount = msg.obj as RechargAmount
                    if (rechargAmount.data.rechargAmount.contains(",")) {
                        recharstring = rechargAmount.data.rechargAmount.split(",") as ArrayList<String>
                        int = 5
                    } else {
                        int = 0
                        recharstring.add(0, rechargAmount.data.rechargAmount)
                    }
                    pictureAdapter.PictureAdapter(recharstring)
                    payReturnUrl = rechargAmount.data.payReturnUrl
                    getBRCheck(0)
                    pictureAdapter.getViweH(int)
                }
                1036 -> {
                    val upacpRechatge = msg.obj as UpacpRechatge
                    val intent = Intent(this@RechargeActivity, WebViewActivity::class.java)
                    intent.putExtra("url", upacpRechatge.data.submithtml)
                    intent.putExtra("type", "3")
                    startActivity(intent)
                }
                1057 -> {
                    val rechargeYunJu = msg.obj as RechargeYunJu
                    val intent = Intent(this@RechargeActivity, WebViewActivity::class.java)
                    intent.putExtra("url", rechargeYunJu.data.payUrl)
                    intent.putExtra("type", "4")
                    startActivity(intent)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200 && resultCode == 200) {
            finish()
        } else if (requestCode == 220 && resultCode == 220) {
            list = ArrayList()
            list!!.add(data!!.getStringExtra("bankId"))
            list!!.add(data.getStringExtra("bankName"))
            list!!.add(data.getStringExtra("branchName"))
            list!!.add(data.getStringExtra("cardNo"))
            list!!.add(data.getStringExtra("cerName"))
            list!!.add(data.getStringExtra("cerNo"))
            list!!.add(data.getStringExtra("city"))
            list!!.add(data.getStringExtra("province"))
            list!!.add(data.getStringExtra("account"))
            recharge_text.text = "您选择：${data.getStringExtra("bankName")}"
            HttpALl.get().setupacpRecharge(data.getStringExtra("account"), "", "", money, data.getStringExtra("cardNo"), "${HttpImplements.get().HttpS}success.html", mHandler, this)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getBRCheck(i: Int) {
        alipay_W = when (i) {
            0 -> {
                recharge_checkbox1.setBackgroundResource(R.mipmap.ic_check_mark)
                recharge_checkbox2.setBackgroundResource(R.mipmap.ic_check_mark_no)
                recharge_checkbox3.setBackgroundResource(R.mipmap.ic_check_mark_no)
                getString(R.string.textView_union_pay)
            }
            1 -> {
                recharge_checkbox2.setBackgroundResource(R.mipmap.ic_check_mark)
                recharge_checkbox1.setBackgroundResource(R.mipmap.ic_check_mark_no)
                recharge_checkbox3.setBackgroundResource(R.mipmap.ic_check_mark_no)
                getString(R.string.textView_alipay)
            }
            else -> {
                recharge_checkbox1.setBackgroundResource(R.mipmap.ic_check_mark_no)
                recharge_checkbox2.setBackgroundResource(R.mipmap.ic_check_mark_no)
                recharge_checkbox3.setBackgroundResource(R.mipmap.ic_check_mark)
                getString(R.string.textView_huanxun_pay)
            }
        }
        money = recharstring[int]
        recharge_button.text = alipay_W + "支付￥" + money
    }

    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        HttpALl.get().setrechargAmount(mHandler, this)
        MobclickAgent.onPageStart(name) //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    public override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(name) //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this)
    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(R.id.top_view)?.init()
        ImmersionBar.with(this@RechargeActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }
}