package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_my_bank_card.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.MyBankCardAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.MyBankCard
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import android.view.MotionEvent
import android.view.ViewGroup.LayoutParams.FILL_PARENT
import android.widget.LinearLayout
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.act_register.*
import sp51.spotpass.com.spotpass.ui.utils.RandomCode


@SuppressLint("Registered")
/**
 * @Time : 2018/5/17 no 21:05
 * @USER : vvguoliang
 * @File : MyBankCardActivity.java
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
class MyBankCardActivity : BaseActivity(), View.OnClickListener, AdapterView.OnItemClickListener {

    private lateinit var myBankCardAdapter: MyBankCardAdapter

    private var type: String = ""

    private var myBankCard: MyBankCard? = null

    private var list: ArrayList<Map<String, String?>>? = null

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_my_bank_card
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        type = intent.getStringExtra("type")
        title_right_arrow_white.setOnClickListener(this)
        my_bank_linear1.setOnClickListener(this)
        my_bank_listview.onItemClickListener = this
    }

    override fun doBusiness() {
        if (type == "0") {
            my_bank_linear1.visibility = View.VISIBLE
        } else {
            my_bank_linear1.visibility = View.GONE
        }
        title_right_arrow_white.visibility = View.VISIBLE
        title_textview.visibility = View.VISIBLE
        name = getString(R.string.textView_my_bank)
        title_textview.text = name
        myBankCardAdapter = MyBankCardAdapter(this)
        my_bank_listview.adapter = myBankCardAdapter

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> {
                setResult(221)
                finish()}
            R.id.my_bank_linear1 -> startActivity(Intent(this, AddBankCardActivity::class.java))
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (type == "1") {
            finish()
        } else if (type == "0") {
            if (list != null && list!!.size > 0) {
                val intent = Intent()
                intent.putExtra("bankId", list!![position]["bankId"])
                intent.putExtra("bankName", list!![position]["bankName"])
                intent.putExtra("branchName", list!![position]["branchName"])
                intent.putExtra("cardNo", list!![position]["cardNo"])
                intent.putExtra("cerName", list!![position]["cerName"])
                intent.putExtra("cerNo", list!![position]["cerNo"])
                intent.putExtra("city", list!![position]["city"])
                intent.putExtra("province", list!![position]["province"])
                intent.putExtra("url", list!![position]["url"])
                intent.putExtra("account", SPUtils.getInstance(this, "login").getString("phone", ""))
                setResult(220, intent)
                finish()
            }
        } else {
            val intent = Intent(this, BankCardDetailsActivity::class.java)
            if (list != null && list!!.size > 0) {
                intent.putExtra("bankId", list!![position]["bankId"])
                intent.putExtra("bankName", list!![position]["bankName"])
                intent.putExtra("branchName", list!![position]["branchName"])
                intent.putExtra("cardNo", list!![position]["cardNo"])
                intent.putExtra("cerName", list!![position]["cerName"])
                intent.putExtra("cerNo", list!![position]["cerNo"])
                intent.putExtra("city", list!![position]["city"])
                intent.putExtra("province", list!![position]["province"])
                if (TextUtils.isEmpty(list!![position]["url"])) {
                    intent.putExtra("url", "")
                } else {
                    intent.putExtra("url", list!![position]["url"])
                }
                intent.putExtra("account", SPUtils.getInstance(this, "login").getString("phone", ""))
            }
            startActivity(intent)
        }

    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1004 -> {
                    list = ArrayList()
                    myBankCard = msg.obj as MyBankCard
                    for (idext in myBankCard!!.data.cardlist) {
                        val byts = RandomCode.newInstance().getByt(idext.cerName)
                        val map = mapOf(Pair("bankId", idext.bankId),
                                Pair("bankName", idext.bankName), Pair("branchName", idext.branchName),
                                Pair("cardNo", idext.cardNo), Pair("cerName", byts), Pair("cerNo", idext.cerNo),
                                Pair("city", idext.city), Pair("province", idext.province),
                                Pair("account", myBankCard!!.data.account), Pair("url", idext.url))
                        list!!.add(map)
                    }
                    myBankCardAdapter.run {
                        getListData(list!!)
                        notifyDataSetChanged()
                    }
                    if (myBankCard!!.data.cardlist.size > 21) {
                        my_bank_linear1.visibility = View.GONE
                    } else {
                        my_bank_linear1.visibility = View.VISIBLE
                    }
                    setHeight()
                }
            }
        }
    }

    fun setHeight() {
        var height = 0
        val count = myBankCardAdapter.count
        for (i in 0 until count) {
            val temp = myBankCardAdapter.getView(i, null, my_bank_listview)
            temp!!.measure(0, 0)
            height += temp.measuredHeight
        }
        val params = this.my_bank_listview.layoutParams
        params.width = RelativeLayout.LayoutParams.FILL_PARENT
        params.height = height
        my_bank_listview.layoutParams = params
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (type == "0") {
                setResult(221)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        HttpALl.get().setQryBankCardList("qryBankCardList",
                SPUtils.getInstance(this, "login").getString("phone", ""),
                SPUtils.getInstance(this, "login").getString("sessionId", "")
                , mHandler, this)
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
        ImmersionBar.with(this@MyBankCardActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }

}
