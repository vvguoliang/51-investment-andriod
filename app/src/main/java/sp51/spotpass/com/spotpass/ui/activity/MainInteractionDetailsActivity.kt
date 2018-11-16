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
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_main_interaction_details.*
import kotlinx.android.synthetic.main.view_title_textview.*
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.InteractionDetailSimpleAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.CirClessCommemts
import sp51.spotpass.com.spotpass.ui.baseEntity.CirclessInfo
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.http.HttpImplements
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import sp51.spotpass.com.spotpass.ui.utils.Util
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("Registered")
/**
 * @Time : 2018/5/7 no 18:21
 * @USER : vvguoliang
 * @File : MainInteractionDetailsActivity.java
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
@TargetApi(Build.VERSION_CODES.KITKAT)
@RequiresApi(Build.VERSION_CODES.KITKAT)
class MainInteractionDetailsActivity : BaseActivity(), View.OnClickListener {

    private var mLoadCount = 0

    private lateinit var integralMallSimpleAdapter: InteractionDetailSimpleAdapter

    private var look: String = ""

    private var id: Int = 0

    private var boolean: Boolean = false

    private var list: ArrayList<CirClessCommemts.Data.Data> = ArrayList()

    private lateinit var interaction_simple_details_interpretation1: TextView

    private var interaction_details_image: CircleImageView? = null
    private var interaction_details_text: TextView? = null
    private var interaction_details_time: TextView? = null
    private var interaction_simple_content: TextView? = null
    private var interaction_simple_details_image1: ImageView? = null
    private var interaction_simple_details_image2: ImageView? = null
    private var interaction_simple_details_image3: ImageView? = null
    private var interaction_simple_image: ImageView? = null
    private var interaction_simple_details_text: TextView? = null
    private var interaction_simple_details: TextView? = null

    override fun initParams(arguments: Bundle?) {

    }

    override fun bindLayout(): Int {
        return R.layout.act_main_interaction_details
    }

    override fun initView(rootView: View) {
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun setListener() {
        look = intent.getStringExtra("look")
        id = intent.getIntExtra("id", 0)
        name = if (look == "1" || look == "0") {
            getString(R.string.textView_details)
        } else {
            getString(R.string.textView_exper_interpretation)
        }
        title_textview.visibility = View.VISIBLE
        title_textview.text = name
        interaction_details_button.setOnClickListener(this)


        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        integralMallSimpleAdapter = InteractionDetailSimpleAdapter(this)
        getRecycler_view_test_rv()

        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)

        recycler_view_test_rv.layoutManager = LinearLayoutManager(this)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL))

        val headerView = integralMallSimpleAdapter.setHeaderView(R.layout.view_interaction_details, recycler_view_test_rv)

        interaction_simple_details_interpretation1 = headerView.findViewById(R.id.interaction_simple_details_interpretation1)
        interaction_details_image = headerView.findViewById(R.id.interaction_details_image) as CircleImageView
        interaction_details_text = headerView.findViewById(R.id.interaction_details_text) as TextView
        interaction_details_time = headerView.findViewById(R.id.interaction_details_time) as TextView
        interaction_simple_content = headerView.findViewById(R.id.interaction_simple_content) as TextView
        interaction_simple_details_image1 = headerView.findViewById(R.id.interaction_simple_details_image1) as ImageView
        interaction_simple_details_image2 = headerView.findViewById(R.id.interaction_simple_details_image2) as ImageView
        interaction_simple_details_image3 = headerView.findViewById(R.id.interaction_simple_details_image3) as ImageView
        interaction_simple_details = headerView.findViewById(R.id.interaction_simple_details) as TextView

        interaction_simple_image = headerView.findViewById(R.id.interaction_simple_image) as ImageView
        interaction_simple_details_text = headerView.findViewById(R.id.interaction_simple_details_text) as TextView
        interaction_simple_image!!.setOnClickListener(this)
        interaction_simple_details_text!!.setOnClickListener(this)

        if (look == "1" || look == "0") {
            interaction_simple_details_image1!!.visibility = View.GONE
            interaction_simple_details_image2!!.visibility = View.GONE
            interaction_simple_details_image3!!.visibility = View.GONE
            interaction_simple_details_interpretation1.visibility = View.VISIBLE
        } else {
            interaction_simple_details_interpretation1.visibility = View.GONE
        }

        recycler_view_test_rv.adapter = integralMallSimpleAdapter
    }

    override fun doBusiness() {

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.interaction_details_button -> {
                if (TextUtils.isEmpty(SPUtils.getInstance(this, "Authorization").getString("Authorization"))) {
                    Util.registerA(this@MainInteractionDetailsActivity)
                } else {
                    HttpALl.get().setCIRCLESSCOMMENTS(id.toLong(), button_edittext_context.text.toString(), mHandler, this)
                }
            }
            R.id.interaction_simple_image, R.id.interaction_simple_details_text -> {
                HttpALl.get().setCIRCLESSLIKES(id.toLong(), mHandler, this)
            }
        }
    }

    private fun getRecycler_view_test_rv() {
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)

        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                boolean = true
                getHttp()
            }

            override fun onLoadMore(isSilence: Boolean) {
                mHandler.postDelayed({
                    if (custom_view.hasLoadCompleted()) {
                    }
                    //                        for (int i = 0; i < 6; i++) {
                    //                            adapter.insert(new SpecialProduct("More ", "21"),
                    //                                    adapter.getAdapterItemCount());
                    //                        }
                    mLoadCount++
                    if (mLoadCount >= 2) {
                        custom_view.setLoadComplete(true)
                    } else {
                        // 刷新完成必须调用此方法停止加载
                        custom_view.stopLoadMore(false)
                    }
                }, 1000)
            }
        })
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1022 -> {
                    val circlessInfo = msg.obj as CirclessInfo
                    interaction_details_text!!.text = circlessInfo.data.user.role
                    interaction_details_time!!.text = circlessInfo.data.created
                    interaction_simple_content!!.text = circlessInfo.data.content
                    val options = RequestOptions()
                    options.centerCrop().placeholder(R.mipmap.ic_banner).error(R.mipmap.ic_banner).fallback(R.mipmap.ic_banner);
                    Glide.with(this@MainInteractionDetailsActivity).load(circlessInfo.data.user.avatar).apply(options).into(interaction_details_image)
                    for (index in circlessInfo.data.imgs) {
                        if (index.id != null && index.id > 0) {
                            if (index.id == 1) {
                                Glide.with(this@MainInteractionDetailsActivity).load("${HttpImplements.get().HttpS}${index.img}").apply(options).into(interaction_simple_details_image1)
                            } else if (index.id == 2) {
                                Glide.with(this@MainInteractionDetailsActivity).load("${HttpImplements.get().HttpS}${index.img}").apply(options).into(interaction_simple_details_image2)
                            } else if (index.id == 3) {
                                Glide.with(this@MainInteractionDetailsActivity).load("${HttpImplements.get().HttpS}${index.img}").apply(options).into(interaction_simple_details_image3)
                            }
                        } else {
                            interaction_simple_details_image1!!.visibility = View.GONE
                            interaction_simple_details_image2!!.visibility = View.GONE
                            interaction_simple_details_image3!!.visibility = View.GONE
                        }
                    }
                    interaction_simple_details_text!!.text = "(${circlessInfo.data.likesNum})"
                    interaction_simple_details!!.text = "评论 (${circlessInfo.data.commentsNum})"
                    Glide.with(this@MainInteractionDetailsActivity).load(circlessInfo.data.user.avatar).apply(options).into(interaction_details_image)

                    if (boolean) {
                        boolean = false
                        custom_view.stopRefresh()
                    }
                }
                1023 -> {
                    val circlesscommemts = msg.obj as CirClessCommemts
                    if (list != null && list.size > 0) {
                        list.clear()
                    }
                    for (index in circlesscommemts.data.data) {
                        list.add(index)
                    }
                    integralMallSimpleAdapter.setData(list, look)
                }
                1024 -> {
                    ToatUtils.showShort1(this@MainInteractionDetailsActivity, "请稍后,我们会对你的评论作出处理")
                    boolean = false
                    button_edittext_context.setText("")
                    AppUtil.instance.getManager(this@MainInteractionDetailsActivity, button_edittext_context)
                    getHttp()
                }
            }
        }
    }

    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        getHttp()
        MobclickAgent.onPageStart(name); //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    private fun getHttp() {
        HttpALl.get().setGETCIRCLESSINFO(id.toLong(), mHandler, this)
        HttpALl.get().setGETCIRCLESSCOMMENTS(id.toLong(), mHandler, this)
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
        ImmersionBar.with(this@MainInteractionDetailsActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }
}