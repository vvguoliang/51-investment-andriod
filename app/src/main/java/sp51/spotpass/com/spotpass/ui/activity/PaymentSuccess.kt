package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_payment_success.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.MainActivity
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.base.BaseActivityManager
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.utils.SPUtils

@SuppressLint("Registered")
/**
 * @Time : 2018/5/17 no 18:12
 * @USER : vvguoliang
 * @File : PaymentSuccess.java
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
 * 充值成功
 */
class PaymentSuccess : BaseActivity(), View.OnClickListener {

    private var type: Int = 4

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_payment_success
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        title_right_arrow_white.setOnClickListener(this)
        payment_continue_recharge.setOnClickListener(this)
        payment_return_home.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun doBusiness() {
        type = intent.getIntExtra("type", 4)
        title_right_arrow_white.visibility = View.VISIBLE
        title_textview.visibility = View.VISIBLE
        name = getString(R.string.textView_recharge_success)
        title_textview.text = name

        payment_alipa_wechat.text = SPUtils.getInstance(this, "recharg").getString("recharg1", "")
        payment_account.text = "**** **** **** ${SPUtils.getInstance(this, "recharg").getString("recharg2", "").substring(SPUtils.getInstance(this, "recharg").getString("recharg2", "").length - 4, SPUtils.getInstance(this, "recharg").getString("recharg2", "").length)}"
        payment_amount_money.text = SPUtils.getInstance(this, "recharg").getString("recharg3", "")
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.payment_continue_recharge -> {
                finish()
            }
            R.id.payment_return_home -> {
                when (type) {
                    4 -> {
                        startActivity(Intent(this@PaymentSuccess, RechargeActivity::class.java))
                        finish()
                    }
                    else -> {
                        setResult(200)
                        finish()
                    }
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
        ImmersionBar.with(this@PaymentSuccess)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }
}