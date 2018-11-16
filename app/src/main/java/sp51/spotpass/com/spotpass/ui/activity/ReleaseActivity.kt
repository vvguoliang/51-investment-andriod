package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_release.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar

@SuppressLint("Registered")
/**
 * @Time : 2018/5/7 no 20:59
 * @USER : vvguoliang
 * @File : ReleaseActivity.java
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
class ReleaseActivity : BaseActivity(), View.OnClickListener {

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_release
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        name = getString(R.string.textView_post)
        title_textview.visibility = View.VISIBLE
        title_textview.text = name

        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)

        title_integral_rule.visibility = View.VISIBLE
        title_integral_rule.text = this.getString(R.string.textView_post_release)
        title_integral_rule.setOnClickListener(this)

        release_picture_text.setOnClickListener(this)
        release_emoticon_text.setOnClickListener(this)
        release_varieties_text.setOnClickListener(this)
    }

    override fun doBusiness() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.title_integral_rule -> finish()
            R.id.release_picture_text -> println("=========")
            R.id.release_emoticon_text -> println("=========")
            R.id.release_varieties_text -> startActivityForResult(Intent(this, VarietiesActivity::class.java), 100)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == 100) {
            val name: String = data!!.getStringExtra("name")
            println("======$name=====111==")

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
        ImmersionBar.with(this@ReleaseActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }
}