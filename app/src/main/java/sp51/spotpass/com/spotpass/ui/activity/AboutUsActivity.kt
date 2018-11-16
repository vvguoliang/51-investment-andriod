package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_about_us.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.ui.baseEntity.InFo
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.GetAssetsFiles
import sp51.spotpass.com.spotpass.ui.utils.ShareWxapTencent


@SuppressLint("Registered")
/**
 * @Time : 2018/5/16 no 17:54
 * @USER : vvguoliang
 * @File : AboutUsActivity.java
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
class AboutUsActivity : BaseActivity(), View.OnClickListener {

    private lateinit var name: String

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_about_us
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        title_right_arrow_white.setOnClickListener(this)
        my_risk_introduction.setOnClickListener(this)
        about_us.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun doBusiness() {
        name = getString(R.string.textView_my_about_us)
        title_right_arrow_white.visibility = View.VISIBLE
        title_textview.visibility = View.VISIBLE
        title_textview.text = name

        edition_text.text = "v" + AppUtil.instance.getVersionName(1, this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.my_risk_introduction -> {
                intent = Intent(this@AboutUsActivity, WebViewActivity::class.java)
                intent.putExtra("url", GetAssetsFiles.getString(this, "风险揭示书v1.htm"))
                intent.putExtra("type", "3")
                startActivity(intent)
            }
            R.id.about_us -> finish()
        }
    }

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
        ImmersionBar.with(this@AboutUsActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }

}