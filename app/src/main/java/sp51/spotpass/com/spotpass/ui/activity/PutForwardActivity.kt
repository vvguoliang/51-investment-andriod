package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.view.TextureView
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_put_forward.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.baseEntity.QryAccount
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import android.text.InputFilter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import sp51.spotpass.com.spotpass.ui.utils.CashierInputFilter
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils


@SuppressLint("Registered")
/**
 * @Time : 2018/5/17 no 19:46
 * @USER : vvguoliang
 * @File : PutForwardActivity.java
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
 * 提现
 */
class PutForwardActivity : BaseActivity(), View.OnClickListener {

    private var list: ArrayList<String>? = null

    private var balance: String = ""

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_put_forward
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        title_right_arrow_white.setOnClickListener(this)
        forward_bank_linear.setOnClickListener(this)
        forward_bank_all.setOnClickListener(this)
        forward_bank_button.setOnClickListener(this)
        recharge_linear.setOnClickListener(this)

        val filters = arrayOf<InputFilter>(CashierInputFilter())

        forward_bank_money.filters = filters
    }

    override fun doBusiness() {
        title_right_arrow_white.visibility = View.VISIBLE
        title_textview.visibility = View.VISIBLE
        name = getString(R.string.textView_out_goldy)
        title_textview.text = name

        recharge_linear.visibility = View.VISIBLE
        forward_bank_linear.visibility = View.GONE

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.forward_bank_linear -> {
                val intent = Intent(this, MyBankCardActivity::class.java)
                intent.putExtra("type", "2")
                startActivityForResult(intent, 208)
            }
            R.id.forward_bank_all -> forward_bank_money.setText(balance)
            R.id.forward_bank_button -> {
                if (list != null && list!!.size > 0) {
                    HttpALl.get().setcashApplyWithoutVcode(list!![8], "", "", "", forward_bank_money.text.toString(), list!![3], mHandler, this)
                } else {
                    ToatUtils.showShort1(this, "您没有选择银行卡")
                }
            }
            R.id.recharge_linear -> {
                val intent = Intent(this, MyBankCardActivity::class.java)
                intent.putExtra("type", "0")
                startActivityForResult(intent, 220)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 208 && resultCode == 208) {

        } else if (requestCode == 220 && resultCode == 220) {
            list = ArrayList()
            list!!.add(data!!.getStringExtra("bankId"))
            list!!.add(data.getStringExtra("bankName"))
            list!!.add(data.getStringExtra("branchName"))
            list!!.add(data.getStringExtra("cardNo"))
            list!!.add(data.getStringExtra("cerName"))
            list!!.add(data.getStringExtra("cerNo"))
            list!!.add(data.getStringExtra("city"))
            list!!.add(data.getStringExtra("province"))
            list!!.add(data.getStringExtra("url"))
            list!!.add(data.getStringExtra("account"))
            recharge_linear.visibility = View.GONE
            forward_bank_linear.visibility = View.VISIBLE
            forward_bank_name.text = data.getStringExtra("bankName")
            forward_bank_num.text = "银行卡（尾号${data.getStringExtra("cardNo").subSequence(data.getStringExtra("cardNo").length - 4, data.getStringExtra("cardNo").length)}）"
            val options = RequestOptions()
            options.centerCrop().placeholder(R.mipmap.ic_banner).error(R.mipmap.ic_banner).fallback(R.mipmap.ic_banner)
            Glide.with(this).load(data.getStringExtra("url")).apply(options).into(forward_bank_image)
        }
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1009 -> {
                }
                1003 -> {
                    val qryAccount = msg.obj as QryAccount
                    balance = qryAccount.data.balance
                    fut_Forward.text = "账户余额${balance}"
                }
                1056 -> finish()
            }
        }
    }

    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        HttpALl.get().setQryAccount("qryAccount", SPUtils.getInstance(this, "login").getString("phone", ""), "", "", mHandler, this)
        MobclickAgent.onPageStart(name) //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    public override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(name) //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this)
    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(R.id.top_view)?.init()
        ImmersionBar.with(this@PutForwardActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }
}