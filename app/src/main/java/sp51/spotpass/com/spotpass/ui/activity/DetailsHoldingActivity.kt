package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_details_holding.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import sp51.spotpass.com.spotpass.ui.view.Dialog.PubilcDialogBuilder

@SuppressLint("Registered")
/**
 * @Time : 2018/6/7 no 13:36
 * @USER : vvguoliang
 * @File : DetailsHoldingActivity.java
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
 * 持仓详情
 */
class DetailsHoldingActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.single_position_button -> {
                PubilcDialogBuilder(this).title("平仓").cancelText("取消").sureText("确定").setCancelOnClickListener { }.setSureOnClickListener { ToatUtils.showShort1(this, "您已经平仓") }.build().show()
            }
        }
    }

    override fun bindLayout(): Int {
        return R.layout.act_details_holding
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        name = getString(R.string.textView_holding_details)

        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)
        title_textview.visibility = View.VISIBLE
        title_textview.text = name

        title_relative.setBackgroundColor(Color.parseColor("#FF6D64"))
        title_relative.setBackgroundColor(Color.parseColor("#028845"))

        holding_prompt.text = getString(R.string.textView_holding_prompt)
        holding_prompt_context.text = getString(R.string.textView_holding_prompt_context)

        single_position_button.text = getString(R.string.textView_holding_flat_out)
        single_position_button.setOnClickListener(this)
    }

    override fun doBusiness() {
    }

    override fun initParams(arguments: Bundle?) {
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
        ImmersionBar.with(this@DetailsHoldingActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }

}