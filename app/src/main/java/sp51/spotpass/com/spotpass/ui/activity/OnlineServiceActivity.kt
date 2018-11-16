package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_online_service.*
import kotlinx.android.synthetic.main.view_title_textview.*
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.OnlineServiceeAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.NormalQuestionsDetail
import sp51.spotpass.com.spotpass.ui.baseEntity.NormalQuestionsList
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import sp51.spotpass.com.spotpass.ui.utils.Util
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@SuppressLint("Registered")
/**
 * @Time : 2018/6/7 no 20:29
 * @USER : vvguoliang
 * @File : OnlineServiceActivity.java
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
class OnlineServiceActivity : BaseActivity(), View.OnClickListener {

    private val list: ArrayList<Map<String, Any>> = ArrayList()
    private lateinit var online_serview_edittext: EditText
    private lateinit var online_serview_button: Button

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.online_serview_button -> {
                if (TextUtils.isEmpty(SPUtils.getInstance(this, "login").getString("login", ""))) {
                    Util.registerA(this@OnlineServiceActivity)
                } else {
                    if (!TextUtils.isEmpty(online_serview_edittext.text.toString())) {
                        HttpALl.get().setguestService(online_serview_edittext.text.toString(), mHandler, this)
                    } else {
                        ToatUtils.showShort1(this, "请输入您的问题或加客服QQ3103948176")
                    }
                }
            }
        }
    }

    private var mLoadCount = 0

    private var boolean: Int = 0

    private lateinit var onlineServiceeAdapter: OnlineServiceeAdapter

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_online_service
    }

    override fun initView(rootView: View) {
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun setListener() {
        name = getString(R.string.textView_my_service)
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)
        title_textview.visibility = View.VISIBLE
        title_textview.text = name
        onlineServiceeAdapter = OnlineServiceeAdapter(this)

        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        recycler_view_test_rv.layoutManager = LinearLayoutManager(this)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL))

        val headerView = onlineServiceeAdapter.setHeaderView(R.layout.view_online_service, recycler_view_test_rv)
        val online_service_textview = headerView.findViewById<TextView>(R.id.online_service_textview)
        val online_service_image = headerView.findViewById<ImageView>(R.id.online_service_image)
        online_service_textview.text = getString(R.string.textView_setview_wenti)
        online_service_image.visibility = View.GONE

        val title_linear = findViewById<LinearLayout>(R.id.title_linear)
        title_linear.visibility = View.VISIBLE

        online_serview_edittext = findViewById(R.id.online_serview_edittext)
        online_serview_button = findViewById(R.id.online_serview_button)

        getRecycler_view_test_rv()
        recycler_view_test_rv.adapter = onlineServiceeAdapter

        online_serview_button.setOnClickListener(this)

        onlineServiceeAdapter.setOnItemClickListener { view, data ->
            if (list != null && list.size > 0) {
                HttpALl.get().setnormalQuestionsDetail(list[data - 1]["id"].toString().toLong(), mHandler, this)
            }
        }

        online_serview_edittext.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s!!.isNotEmpty()) {
                    online_serview_button.setBackgroundResource(R.drawable.shape_c5_red)
                } else {
                    online_serview_button.setBackgroundResource(R.drawable.shape_c5_grle_while)
                }
            }
        })
    }

    override fun doBusiness() {
    }

    private fun getRecycler_view_test_rv() {
        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                HttpALl.get().setnormalQuestionsList(mLoadCount.toLong(), mHandler, this@OnlineServiceActivity)
                boolean = 1
            }

            override fun onLoadMore(isSilence: Boolean) {
                mLoadCount++
                boolean = 2
                HttpALl.get().setnormalQuestionsList(mLoadCount.toLong(), mHandler, this@OnlineServiceActivity)
            }
        })
    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1035 -> {
                    if (boolean == 1) {
                        if (list != null && list.size > 0)
                            list.clear()
                        custom_view.stopRefresh()
                    } else if (boolean == 2) {
                        custom_view.setLoadComplete(true)
                    }
                    val normalQuestionsList = msg.obj as NormalQuestionsList
                    for (indxe in normalQuestionsList.data.data) {
                        val map = HashMap<String, Any>()
                        map["id"] = indxe.id
                        map["title"] = indxe.title
                        list.add(map)
                    }
                    onlineServiceeAdapter.setData(list)
                }
                1045 -> {
                    val normalQuestionsDetail = msg.obj as NormalQuestionsDetail
                    val intent = Intent(this@OnlineServiceActivity, WebViewActivity::class.java)
                    intent.putExtra("url", AppUtil.instance.Html(normalQuestionsDetail.data.title, normalQuestionsDetail.data.content, normalQuestionsDetail.data.updatedTime))
                    intent.putExtra("type", "3")
                    startActivity(intent)
                }
                1046 -> {
                    online_serview_button.setBackgroundResource(R.drawable.shape_c5_grle_while)
                    ToatUtils.showShort1(this@OnlineServiceActivity, "您的建议已经提交，请耐心等待客服回复")
                    AppUtil.instance.getManager(this@OnlineServiceActivity, online_serview_edittext)
                }
            }
        }
    }

    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        if (list != null && list.size > 0)
            list.clear()
        HttpALl.get().setnormalQuestionsList(mLoadCount.toLong(), mHandler, this)
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
        ImmersionBar.with(this@OnlineServiceActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }
}