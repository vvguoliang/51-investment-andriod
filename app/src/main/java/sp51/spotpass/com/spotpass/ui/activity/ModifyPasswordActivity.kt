package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_modify_password.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.utils.CountDownTimerUtils
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import java.time.Instant

@SuppressLint("Registered")
/**
 * @Time : 2018/5/20 no 18:48
 * @USER : vvguoliang
 * @File : ModifyPasswordActivity.java
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
class ModifyPasswordActivity : BaseActivity(), View.OnClickListener {

    private lateinit var countDownTimer: CountDownTimerUtils

    private var type: String = ""

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_modify_password
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        type = intent.getStringExtra("type")
        if (type == "1") {
            name = getString(R.string.textView_forget_password)
        } else {
            name = getString(R.string.textView_modify_password)
        }
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)
        title_textview.visibility = View.VISIBLE
        title_textview.text = name

        modify_password_edittext_phone.setText(SPUtils.getInstance(this, "login").getString("phone", ""))
        modify_password_text_code.setOnClickListener(this)
        modify_password_button.setOnClickListener(this)
    }

    override fun doBusiness() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.modify_password_text_code -> {
                if (TextUtils.isEmpty(modify_password_edittext_phone.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_phton), Toast.LENGTH_LONG).show()
                } else if (!AppUtil.instance.isChinaPhoneLegal(modify_password_edittext_phone.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_phtonCode), Toast.LENGTH_LONG).show()
                } else {
                    countDownTimer = CountDownTimerUtils(modify_password_text_code, 60 * 1000, 1000)
                    countDownTimer.start()
                    HttpALl.get().setPhoneCode(modify_password_edittext_phone.text.toString().toLong(), mHandler, this)
                }
            }
            R.id.modify_password_button -> {
                if (TextUtils.isEmpty(modify_password_edittext_phone.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_phton), Toast.LENGTH_LONG).show()
                } else if (!AppUtil.instance.isChinaPhoneLegal(modify_password_edittext_phone.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_phtonCode), Toast.LENGTH_LONG).show()
                } else if (TextUtils.isEmpty(modify_password_edittext_code.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_code), Toast.LENGTH_LONG).show()
                } else if (TextUtils.isEmpty(modify_password_edittext_password.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_input_account_password_new), Toast.LENGTH_LONG).show()
                } else if (TextUtils.isEmpty(modify_password_edittext_again_password.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_input_account_again_password_new), Toast.LENGTH_LONG).show()
                } else if (modify_password_edittext_password.text.toString() != modify_password_edittext_again_password.text.toString()) {
                    Toast.makeText(this, getString(R.string.textView_input_password_two), Toast.LENGTH_LONG).show()
                    modify_password_edittext_password.setText("")
                    modify_password_edittext_again_password.setText("")
                } else {
                    HttpALl.get().setresetpassword(modify_password_edittext_phone.text.toString(), modify_password_edittext_code.text.toString()
                            , modify_password_edittext_password.text.toString(), modify_password_edittext_again_password.text.toString(), mHandler, this)
                }
            }

        }
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1000 -> {
                    ToatUtils.showShort1(this@ModifyPasswordActivity, "密码修改成功，请妥善保存新密码")
                    val intent = Intent(this@ModifyPasswordActivity,RegisterActivity::class.java)
                    intent.putExtra("type",0)
                    startActivity(intent)
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
        ImmersionBar.with(this@ModifyPasswordActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }

}