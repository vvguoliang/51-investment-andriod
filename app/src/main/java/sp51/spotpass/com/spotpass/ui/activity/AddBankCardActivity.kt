package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_add_bank_card.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.ui.utils.IdentityUtils
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import java.net.URLEncoder

@SuppressLint("Registered")
/**
 * @Time : 2018/5/20 no 21:35
 * @USER : vvguoliang
 * @File : AddBankCardActivity.java
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
class AddBankCardActivity : BaseActivity(), View.OnClickListener {

    private var id: String = ""

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_add_bank_card
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        name = getString(R.string.textView_add_bank)
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)
        title_textview.visibility = View.VISIBLE
        title_textview.text = name

        addbank_button.setOnClickListener(this)
        bank_list_linear.setOnClickListener(this)
    }

    override fun doBusiness() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.addbank_button -> {
                if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(bank_name.text.toString())) {
                    if (TextUtils.isEmpty(addbank_edittext_name.text.toString().trim())) {
                        Toast.makeText(this, getString(R.string.textView_bank_cardholder1), Toast.LENGTH_LONG).show()
                    } else if (TextUtils.isEmpty(addbank_edittext_No.text.toString().trim())) {
                        Toast.makeText(this, getString(R.string.textView_bank_card_identity1), Toast.LENGTH_LONG).show()
                    } else if (!IdentityUtils.checkIDCard(addbank_edittext_No.text.toString())) {
                        Toast.makeText(this, getString(R.string.textView_bank_card_identity1), Toast.LENGTH_LONG).show()
                    } else if (TextUtils.isEmpty(addbank_edittext_bank.text.toString())) {
                        Toast.makeText(this, getString(R.string.textView_bank_card), Toast.LENGTH_LONG).show()
                    } else if (TextUtils.isEmpty(bank_name.text.toString())) {
                        Toast.makeText(this, getString(R.string.textView_input_bank_name1), Toast.LENGTH_LONG).show()
                    } else if (TextUtils.isEmpty(addbank_edittext_phone.text.toString())) {
                        Toast.makeText(this, getString(R.string.textView_input_account_phone), Toast.LENGTH_LONG).show()
                    } else if (!AppUtil.instance.isChinaPhoneLegal(addbank_edittext_phone.text.toString())) {
                        Toast.makeText(this, getString(R.string.textView_input_account_phone), Toast.LENGTH_LONG).show()
                    } else {
                        HttpALl.get().setBindCard("bindCard",
                                SPUtils.getInstance(this, "login").getString("phone", ""),
                                "",
                                id,
                                addbank_edittext_bank.text.toString().trim(),
                                addbank_edittext_name.text.toString().trim(),
                                addbank_edittext_No.text.toString().trim(),
                                bank_name.text.toString().trim(), mHandler, this)//
//                        HttpALl.get().setBindCard("bindCard",
//                                SPUtils.getInstance(this, "login").getString("phone", ""),
//                                "3a74d80f-7ada-4846-bf7c-8e366dc660c3",
//                                id,
//                                addbank_edittext_bank.text.toString().trim(),
//                                addbank_edittext_name.text.toString().trim(),
//                                addbank_edittext_No.text.toString().trim(),
//                                bank_name.text.toString().trim(), mHandler, this)//
                    }
                }
            }
            R.id.bank_list_linear -> {
                startActivityForResult(Intent(this, BankLIstActivity::class.java), 208)
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1030 -> finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 208 && resultCode == 208) {
            bank_name.text = data!!.getStringExtra("name")
            id = data.getStringExtra("id")
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
        ImmersionBar.with(this@AddBankCardActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }

}