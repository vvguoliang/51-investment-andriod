package sp51.spotpass.com.spotpass.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.*
import android.support.annotation.RequiresApi
import android.text.Html
import android.view.View
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.utils.CountDownTimerUtils
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.widget.Toast
import com.umeng.analytics.MobclickAgent
import com.yanzhenjie.permission.AndPermission
import kotlinx.android.synthetic.main.act_register.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R.id.text_text
import sp51.spotpass.com.spotpass.ui.baseEntity.AppVersion
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.ui.baseEntity.PublicReq
import sp51.spotpass.com.spotpass.ui.baseEntity.PublicReqLoign
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.GetAssetsFiles
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.view.Dialog.Vison.VisionShowDialog
import java.util.*

@SuppressLint("Registered")
/**
 * @Time : 2018/5/16 no 14:03
 * @USER : vvguoliang
 * @File : RegisterActivity.java
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
 *
 */
class RegisterActivity : BaseActivity(), View.OnClickListener {

    private var type: Int = 0

    private var ip: String = ""

    private lateinit var countDownTimer: CountDownTimerUtils

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_register
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        HttpALl.get().setappVersion(mHandler, this)
        visionShowDialog = VisionShowDialog(this)
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)

        sign_in_text.setOnClickListener(this)
        register_text.setOnClickListener(this)
        sign_in_button.setOnClickListener(this)
        code_text.setOnClickListener(this)
        text_text.setOnClickListener(this)
        text_text1.setOnClickListener(this)

        if (!TextUtils.isEmpty(SPUtils.getInstance(this, "login").getString("phone", ""))) {
            val sp: String = SPUtils.getInstance(this, "login").getString("phone", "")
            phone_edit.setText(sp)
        }
        name = this.getString(R.string.textView_register_sign_up)
    }

    override fun doBusiness() {
        type = intent.getIntExtra("type", 0)
        if (type == 0) { //判断注册登入
            getButton(2)
        } else {
            getButton(1)
        }
        HttpALl.get().setHasp(mHandler, this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.sign_in_text -> {  // 登录
                getButton(2)

            }
            R.id.register_text -> {  // 注册
                getButton(1)

            }
            R.id.title_right_arrow_white -> finish()
            R.id.sign_in_button -> {
                if (TextUtils.isEmpty(phone_edit.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_phton), Toast.LENGTH_LONG).show()
                } else if (!AppUtil.instance.isChinaPhoneLegal(phone_edit.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_phtonCode), Toast.LENGTH_LONG).show()
                } else if (sign_in_button.text.equals(getString(R.string.textView_sign_in))) { // 登录
                    if (TextUtils.isEmpty(password_text.text)) {
                        Toast.makeText(this, getString(R.string.textView_password), Toast.LENGTH_LONG).show()
                    } else {
                        HttpALl.get().setPublicReqlogin(phone_edit.text.toString(), password_text.text.toString(),
                                ip, mHandler, this)
                    }
                } else {
                    if (!AppUtil.instance.isChinaPhoneLegal(phone_edit.text.toString())) {
                        Toast.makeText(this, getString(R.string.textView_phtonCode), Toast.LENGTH_LONG).show()
                    } else if (TextUtils.isEmpty(code_edit.text.toString())) {
                        Toast.makeText(this, getString(R.string.textView_code), Toast.LENGTH_LONG).show()
                    } else if (TextUtils.isEmpty(password_text.text)) {
                        Toast.makeText(this, getString(R.string.textView_password), Toast.LENGTH_LONG).show()
                    } else {
                        HttpALl.get().setPublicReq(phone_edit.text.toString(),
                                password_text.text.toString(), "", "", code_edit.text.toString(), mHandler, this)
                    }
                }
            }
            R.id.code_text -> {  // 点击时间
                if (TextUtils.isEmpty(phone_edit.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_phton), Toast.LENGTH_LONG).show()
                } else if (!AppUtil.instance.isChinaPhoneLegal(phone_edit.text.toString())) {
                    Toast.makeText(this, getString(R.string.textView_phtonCode), Toast.LENGTH_LONG).show()
                } else {
                    countDownTimer = CountDownTimerUtils(code_text, 60 * 1000, 1000)
                    countDownTimer.start()
                    HttpALl.get().setPhoneCode(phone_edit.text.toString().toLong(), mHandler, this)
                }
            }
            R.id.text_text -> {
                if (text_text.text.toString() == getString(R.string.textView_forget_password)) {
                    val intent = Intent(this, ModifyPasswordActivity::class.java)
                    intent.putExtra("type", "1")
                    startActivity(intent)
                } else {
                    val intent = Intent(this, WebViewActivity::class.java)
                    intent.putExtra("type", "1")
                    intent.putExtra("url", GetAssetsFiles.getString(this, "风险揭示书v1.htm"))
                    startActivity(intent)
                }
            }
            R.id.text_text1 -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("type", "1")
                intent.putExtra("url", GetAssetsFiles.getString(this, "免责声明.htm"))
                startActivity(intent)
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1000 -> {
                    val publicreq: PublicReq = msg.obj as PublicReq
                    //当用户使用自有账号登录时，可以这样统计：
                    MobclickAgent.onProfileSignIn(phone_edit.text.toString());
                    getButton(2)
                }
                1001 -> ip = msg.obj as String
                1002 -> {
                    val publicReqLoign: PublicReqLoign = msg.obj as PublicReqLoign
                    val intent = Intent()
                    intent.putExtra("regtime", publicReqLoign.data.regtime)
                    intent.putExtra("qbid", publicReqLoign.data.qbid)
                    intent.putExtra("balance", publicReqLoign.data.balance)
                    intent.putExtra("holdmargin", publicReqLoign.data.holdmargin)
                    intent.putExtra("frozenmargin", publicReqLoign.data.frozenmargin)
                    intent.putExtra("incapital", publicReqLoign.data.incapital)
                    intent.putExtra("outcapital", publicReqLoign.data.outcapital)
                    intent.putExtra("matchfee", publicReqLoign.data.matchfee)
                    intent.putExtra("holdfee", publicReqLoign.data.holdfee)
                    intent.putExtra("lastprofit", publicReqLoign.data.lastprofit)
                    intent.putExtra("initasset", publicReqLoign.data.initasset)
                    intent.putExtra("shoppoints", publicReqLoign.data.shoppoints)
                    intent.putExtra("account", phone_edit.text.toString())

                    SPUtils.getInstance(this@RegisterActivity, "Authorization").put("Authorization", publicReqLoign.data.token.toString())
                    SPUtils.getInstance(this@RegisterActivity, "login").put("login", "true")
                    SPUtils.getInstance(this@RegisterActivity, "login").put("phone", phone_edit.text.toString())
                    //登出
                    MobclickAgent.onProfileSignOff()
                    setResult(209, intent)
                    finish()
                }
                1051 -> {
                    val appVersion = msg.obj as AppVersion
                    // 申请多个权限。大神的界面
                    AndPermission.with(this@RegisterActivity)
                            .requestCode(VisionShowDialog.REQUEST_CODE_PERMISSION_SD)
                            .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                            // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                            .rationale { _, rationale ->
                                // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                                AndPermission.rationaleDialog(this@RegisterActivity, rationale).show()
                            }
                            .send()
                    mLoadCount = appVersion.data.updateType
                    url = appVersion.data.appUrl
                    version = appVersion.data.version
                    val version1 = AppUtil.instance.getVersionName(1, this@RegisterActivity)!!.replace(".", "")
                    if (version1.toLong() > version.replace(".", "").toLong()) {
                        if (mLoadCount == 1) {
                            visionShowDialog!!.ShowDialog(version, "建议升级", url, mLoadCount)
                        } else {
                            visionShowDialog!!.ShowDialog(version, "强制升级", url, mLoadCount)
                        }
                    }
                }
            }
        }
    }

    private fun getButton(i: Int) {
        if (i == 1) {
            sign_in_text.setTextColor(Color.parseColor("#999999"))
            register_text.setTextColor(Color.parseColor("#FFFFFF"))
            code_relative.visibility = View.VISIBLE
            sign_in_button.text = getString(R.string.textView_register)
            var ssb = SpannableStringBuilder(getString(R.string.textView_agree))
            ssb.setSpan(ForegroundColorSpan(Color.parseColor("#FF6D64")), 14,
                    getString(R.string.textView_agree).length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            text_text.text = ssb  // 底部的文案
            text_text1.visibility = View.VISIBLE
            ssb = SpannableStringBuilder(getString(R.string.textView_isclaimer))
            ssb.setSpan(ForegroundColorSpan(Color.parseColor("#FF6D64")), 1,
                    getString(R.string.textView_isclaimer).length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            text_text1.text = ssb
        } else {
            sign_in_text.setTextColor(Color.parseColor("#FFFFFF"))
            register_text.setTextColor(Color.parseColor("#999999"))
            code_relative.visibility = View.GONE
            sign_in_button.text = getString(R.string.textView_sign_in)
            phone_edit.setText("")
            password_text.setText("")
            text_text1.visibility = View.GONE
            val ssb = SpannableStringBuilder(getString(R.string.textView_forget_password))
            ssb.setSpan(ForegroundColorSpan(Color.parseColor("#FF6D64")), 0,
                    getString(R.string.textView_forget_password).length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            text_text.text = ssb  // 底部的文案
        }
    }


    private var visionShowDialog: VisionShowDialog? = null
    private var url: String? = null
    private var version = ""
    private var mLoadCount = 0

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            VisionShowDialog.REQUEST_CODE_SETTING -> {
                Toast.makeText(this, R.string.message_setting_back, Toast.LENGTH_LONG).show()
                //设置成功，再次请求更新
                visionShowDialog!!.ShowDialog(version, "建议升级", url, mLoadCount)
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
        ImmersionBar.with(this@RegisterActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }
}