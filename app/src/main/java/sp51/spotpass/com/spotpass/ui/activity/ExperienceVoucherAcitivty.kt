package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_experience_voucher.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.utils.SPUtils

@SuppressLint("Registered")
/**
 * @Time : 2018/6/13 no 15:51
 * @USER : vvguoliang
 * @File : ExperienceVoucherAcitivty.java
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
class ExperienceVoucherAcitivty : BaseActivity() {

    private var Qprice: String = ""

    private var voucher1: Boolean = false
    private var voucher2: Boolean = false
    private var voucher3: Boolean = false
    private var voucher4: Boolean = false

    private var voucherint1: Int = 0
    private var voucherint2: Int = 0
    private var voucherint3: Int = 0
    private var voucherint4: Int = 0

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_experience_voucher
    }

    override fun initView(rootView: View) {
        Qprice = intent.getStringExtra("Qprice")
        name = getString(R.string.textView_vocucher)

        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener { finish() }

        title_textview.visibility = View.VISIBLE
        title_textview.text = name
        when (Qprice) {
            "8.00" -> {
                voucher_linear1.setBackgroundResource(R.drawable.shape_corner_light_blue)
                voucher_linear2.setBackgroundResource(R.drawable.shape_c5_grle_while)
                voucher_linear3.setBackgroundResource(R.drawable.shape_c5_grle_while)
                voucher_linear4.setBackgroundResource(R.drawable.shape_c5_grle_while)

                voucher_linear1.setOnClickListener {
                    if (voucher1) {
                        voucherint1 = 0
                        voucher_linear1.setBackgroundResource(R.drawable.shape_corner_light_blue)
                        voucher1 = false
                    } else {
                        voucherint1 = 1
                        voucher1 = true
                        voucher_linear1.setBackgroundResource(R.drawable.shape_c5_grle_while)
                    }
                }
            }
            "100.00" -> {
                voucher_linear1.setBackgroundResource(R.drawable.shape_c5_grle_while)
                voucher_linear2.setBackgroundResource(R.drawable.shape_corner_light_lavender)
                voucher_linear3.setBackgroundResource(R.drawable.shape_c5_grle_while)
                voucher_linear4.setBackgroundResource(R.drawable.shape_c5_grle_while)

                voucher_linear2.setOnClickListener {
                    if (voucher2) {
                        voucherint2 = 0
                        voucher_linear2.setBackgroundResource(R.drawable.shape_corner_light_lavender)
                        voucher2 = false
                    } else {
                        voucherint2 = 1
                        voucher_linear2.setBackgroundResource(R.drawable.shape_c5_grle_while)
                        voucher2 = true
                    }
                }
            }
            "200.00" -> {
                voucher_linear1.setBackgroundResource(R.drawable.shape_c5_grle_while)
                voucher_linear2.setBackgroundResource(R.drawable.shape_c5_grle_while)
                voucher_linear3.setBackgroundResource(R.drawable.shape_corner_deep_blue)
                voucher_linear4.setBackgroundResource(R.drawable.shape_c5_grle_while)

                voucher_linear3.setOnClickListener {
                    if (voucher3) {
                        voucherint3 = 0
                        voucher_linear3.setBackgroundResource(R.drawable.shape_corner_deep_blue)
                        voucher3 = false
                    } else {
                        voucherint3 = 1
                        voucher_linear3.setBackgroundResource(R.drawable.shape_c5_grle_while)
                        voucher3 = true
                    }
                }
            }
            "500.00" -> {
                voucher_linear1.setBackgroundResource(R.drawable.shape_c5_grle_while)
                voucher_linear2.setBackgroundResource(R.drawable.shape_c5_grle_while)
                voucher_linear3.setBackgroundResource(R.drawable.shape_c5_grle_while)
                voucher_linear4.setBackgroundResource(R.drawable.shape_corner_light_orange)

                voucher_linear4.setOnClickListener {
                    if (voucher4) {
                        voucherint4 = 0
                        voucher_linear4.setBackgroundResource(R.drawable.shape_corner_light_orange)
                        voucher4 = false
                    } else {
                        voucherint4 = 1
                        voucher_linear4.setBackgroundResource(R.drawable.shape_c5_grle_while)
                        voucher4 = true
                    }
                }
            }
        }


        button.setOnClickListener {
            when (Qprice) {
                "8.00" -> {
                    if (voucherint1 == 0) {
                        SPUtils.getInstance(this, "voucher").put("voucher", "1")
                    } else {
                        SPUtils.getInstance(this, "voucher").put("voucher", "0")
                    }
                }
                "100.00" -> {
                    if (voucherint2 == 0) {
                        SPUtils.getInstance(this, "voucher").put("voucher", "2")
                    } else {
                        SPUtils.getInstance(this, "voucher").put("voucher", "0")
                    }
                }
                "200.00" -> {
                    if (voucherint3 == 0) {
                        SPUtils.getInstance(this, "voucher").put("voucher", "3")
                    } else {
                        SPUtils.getInstance(this, "voucher").put("voucher", "0")
                    }
                }
                "500.00" -> {
                    if (voucherint4 == 0) {
                        SPUtils.getInstance(this, "voucher").put("voucher", "4")
                    } else {
                        SPUtils.getInstance(this, "voucher").put("voucher", "0")
                    }
                }
            }
            setResult(210)
            finish()
        }

    }

    override fun setListener() {
    }

    override fun doBusiness() {
    }


    private var name: String = ""
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
        ImmersionBar.with(this@ExperienceVoucherAcitivty)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }

}