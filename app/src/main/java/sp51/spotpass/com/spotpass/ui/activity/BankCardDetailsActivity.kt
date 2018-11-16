package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_bankcard_details.*
import kotlinx.android.synthetic.main.act_my_bank_card.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.baseEntity.MyBankCard
import sp51.spotpass.com.spotpass.ui.http.HttpALl

@SuppressLint("Registered")
/**
 * @Time : 2018/5/18 no 12:01
 * @USER : vvguoliang
 * @File : BankCardDetailsActivity.java
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
 * 银行卡详情
 */
class BankCardDetailsActivity : BaseActivity(), View.OnClickListener {

    private var bankId: String = ""
    private var bankName: String = ""
    private var branchName: String = ""
    private var cardNo: String = ""
    private var cerName: String = ""
    private var cerNo: String = ""
    private var city: String = ""
    private var province: String = ""
    private var account: String = ""
    private var url: String = ""

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_bankcard_details
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        title_right_arrow_white.setOnClickListener(this)
        bank_unbind_button.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun doBusiness() {
        bankId = intent.getStringExtra("bankId")
        bankName = intent.getStringExtra("bankName")
        branchName = intent.getStringExtra("branchName")
        cardNo = intent.getStringExtra("cardNo")
        cerName = intent.getStringExtra("cerName")
        cerNo = intent.getStringExtra("cerNo")
        city = intent.getStringExtra("city")
        province = intent.getStringExtra("province")
        account = intent.getStringExtra("account")
        url = intent.getStringExtra("url")
        title_right_arrow_white.visibility = View.VISIBLE
        title_textview.visibility = View.VISIBLE
        name = getString(R.string.textView_bank_card_details)
        title_textview.text = name
        forward_bank_name.text = bankName
        forward_bank_num.text = "银行卡（尾号）${cardNo.substring(cardNo.length - 4, cardNo.length)}"
        forwrrd_bank_name.text = "*${cerName.substring(cerName.length - 1, cerName.length)}"
        forward_bank_phone.text = "${account.substring(0, 3)}****${account.substring(account.length - 4, account.length)}"

        val options = RequestOptions()
        options.centerCrop().placeholder(R.mipmap.ic_banner).error(R.mipmap.ic_banner).fallback(R.mipmap.ic_banner)
        Glide.with(this).load(url).apply(options).into(forward_bank_image)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.bank_unbind_button -> {
                HttpALl.get().setremoveCard(account, "", cardNo.trim(), mHandler, this@BankCardDetailsActivity)
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1027 -> {
                    finish()
                }
            }
        }
    }

    private lateinit var name: String
    public override fun onResume() {
        super.onResume()
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
        ImmersionBar.with(this@BankCardDetailsActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }
}