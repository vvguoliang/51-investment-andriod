package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_my_assets.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.baseEntity.QryAccount
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.utils.Util

@SuppressLint("Registered")
/**
 * @Time : 2018/5/20 no 15:07
 * @USER : vvguoliang
 * @File : MyAssetsActivity.java
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
 * 我的总资产
 */
class MyAssetsActivity : BaseActivity(), View.OnClickListener {

    private var totalPrice: String = "0.00"
    private var holdingPrice: String = "0.00"
    private var balance: String = "0.00"

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_my_assets
    }

    override fun initView(rootView: View) {
    }

    @SuppressLint("SetTextI18n")
    override fun setListener() {
        name = getString(R.string.textView_my_assets_total)
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)
        title_textview.visibility = View.VISIBLE
        title_textview.text = name
        title_imageview_historical_bill.visibility = View.VISIBLE
        title_imageview_historical_bill.setOnClickListener(this)

        my_assets_money_text.text = totalPrice
        my_available_balance_text.text = balance
        my_holding_assets_text.text = holdingPrice

        my_assets_forward.setOnClickListener(this)
        my_assets_bank.setOnClickListener(this)
    }

    override fun doBusiness() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.title_imageview_historical_bill -> {
                startActivity(Intent(this, BillingRecordActivity::class.java))
            }
            R.id.my_assets_forward -> {

            }
            R.id.my_assets_bank -> {
                val intent = Intent(this, MyBankCardActivity::class.java)
                intent.putExtra("type", "0")
                startActivity(intent)
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                114 -> {
                    Util.registerA(this@MyAssetsActivity)
                }
                1003 -> {
                    val qryAccount = msg.obj as QryAccount
                    my_assets_money_text.text = qryAccount.data.totalPrice
                    my_holding_assets_text.text = qryAccount.data.holdingPrice
                    my_available_balance_text.text = qryAccount.data.balance
                }
            }
        }
    }


    private lateinit var name: String


    public override fun onResume() {
        super.onResume()
        HttpALl.get().setQryAccount("qryAccount", SPUtils.getInstance(this, "login").getString("login", ""), "",
                "", mHandler, this)
        MobclickAgent.onPageStart(name); //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    public override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(name); //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this)
    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(R.id.top_view)?.init()
        ImmersionBar.with(this@MyAssetsActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }
}