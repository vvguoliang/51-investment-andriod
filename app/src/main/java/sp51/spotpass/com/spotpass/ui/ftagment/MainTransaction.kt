package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.*
import android.support.annotation.RequiresApi
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import com.umeng.analytics.MobclickAgent
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.playtablayout.PlayTabLayout
import sp51.spotpass.com.spotpass.playtablayout.TouchableTabLayout
import sp51.spotpass.com.spotpass.ui.adapter.TransactionFragmentSimpleAdapter
import sp51.spotpass.com.spotpass.ui.adapter.TransactionTabAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.Httpwallstreetcn
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.service.WallstreetcnService
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @Time : 2018/5/2 no 下午2:31
 * @USER : vvguoliang
 * @File : MainHome.java
 * @Software: Android Studio
 *code is far away from bugs with the god animal protecting
 *   I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃   ☃   ┃
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
 * 交易
 */
class MainTransaction : BaseFragment(), View.OnClickListener, TouchableTabLayout.OnTabSelectedListener {

    private lateinit var transaction_PlayTab: PlayTabLayout

    private lateinit var transaction_viewpage: ViewPager

    private lateinit var title_post: TextView

    private lateinit var title_video: TextView

    private var type: Int = 1

    private lateinit var tabLayout: TouchableTabLayout

    private lateinit var transactionTabAdapter: TransactionTabAdapter

    private lateinit var transactione_linear1: LinearLayout

    private lateinit var transactione_linear2: LinearLayout

    private var list: ArrayList<Map<String, Any?>>? = null

    private lateinit var custom_view: XRefreshView

    private lateinit var recycler_view_test_rv: RecyclerView

    private lateinit var transactionFragmentSimpleAdapter: TransactionFragmentSimpleAdapter

    private var mLoadCount = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.far_transaction, container, false)
        transaction_PlayTab = view.findViewById(R.id.transaction_PlayTab)
        transaction_viewpage = view.findViewById(R.id.transaction_viewpage)
        title_post = view.findViewById(R.id.title_post)
        title_video = view.findViewById(R.id.title_video)
        transactione_linear1 = view.findViewById(R.id.transactione_linear1)
        transactione_linear2 = view.findViewById(R.id.transactione_linear2)
        view.findViewById<LinearLayout>(R.id.title_post_video).visibility = View.VISIBLE

        custom_view = view.findViewById(R.id.custom_view)
        recycler_view_test_rv = view.findViewById(R.id.recycler_view_test_rv)
        return view
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transaction_PlayTab.colors = intArrayOf(R.color.white, R.color.white, R.color.white)

        transactionTabAdapter = TransactionTabAdapter(childFragmentManager)
        val lazy: ArrayList<Fragment> = ArrayList()
        lazy.add(0, SampleFragment())
        lazy.add(1, SampleFragment1())
        lazy.add(2, sp51.spotpass.com.spotpass.ui.ftagment.SampleFragment2())

        transactionTabAdapter.geList(list = lazy)
        transaction_viewpage.adapter = transactionTabAdapter

        tabLayout = transaction_PlayTab.tabLayout
        with(tabLayout) {
            setupWithViewPager(transaction_viewpage)
            tabMode = TabLayout.MODE_FIXED
            tabGravity = TabLayout.GRAVITY_FILL
            setSelectedTabIndicatorHeight(7)
            setSelectedTabIndicatorColor(Color.parseColor("#FF6D64"))
            setTabTextColors(activity?.let { ContextCompat.getColor(it, R.color.black_grey) }!!, Color.parseColor("#FF6D64"))
            tabLayout.addOnTabSelectedListener(this@MainTransaction)
        }

        title_post.text = getString(R.string.navigation_transaction)
        title_video.text = getString(R.string.textView_market)
        title_post.setOnClickListener(this)
        title_video.setOnClickListener(this)
        getColoText(type, true)

        transactionFragmentSimpleAdapter = TransactionFragmentSimpleAdapter(activity)

        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        recycler_view_test_rv.layoutManager = LinearLayoutManager(activity)

        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        getRecycler_view_test_rv()

        recycler_view_test_rv.adapter = transactionFragmentSimpleAdapter

//        transactionFragmentSimpleAdapter.setOnItemClickListener(({ _, data ->
//        }))
    }

    private fun getRecycler_view_test_rv() {
        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                mHandler.postDelayed({
                    //模拟数据加载失败的情况
                    val random = Random()
                    val success = random.nextBoolean()
                    if (success) {
                        custom_view.stopRefresh()
                    } else {
                        custom_view.stopRefresh(false)
                    }
                    //或者
                    //                        xRefreshView1.stopRefresh(success);
                }, 2000)
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

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1053 -> {
                    val httpwallstreetcn = msg.obj as Httpwallstreetcn
                    list = ArrayList()
                    for (index in (0..7)) {
                        val map = HashMap<String, Any>()
                        when (index) {
                            0 -> {
                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.aUDUSD.withIndex()) {
                                    map["$inde"] = valaue
                                }
                                map["Name"] = "AUDUSD"
                            }
                            1 -> {
                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.eURUSD.withIndex()) {
                                    map["$inde"] = valaue
                                }
                                map["Name"] = "EURUSD"
                            }
                            2 -> {
                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.gBPUSD.withIndex()) {
                                    map["$inde"] = valaue
                                }
                                map["Name"] = "GBPUSD"
                            }
                            3 -> {
                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.nZDUSD.withIndex()) {
                                    map["$inde"] = valaue
                                }
                                map["Name"] = "NZDUSD"
                            }
                            4 -> {
                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.uSDCAD.withIndex()) {
                                    map["$inde"] = valaue
                                }
                                map["Name"] = "USDCAD"
                            }
                            5 -> {
                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.uSDCHF.withIndex()) {
                                    map["$inde"] = valaue
                                }
                                map["Name"] = "USDCHF"
                            }
//                            6 -> {
//                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.uSDCNH.withIndex()) {
//                                    map["$inde"] = valaue
//                                }
//                                map["Name"] = "USDCNH"
//                            }
//                            7 -> {
//                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.uSDCNY.withIndex()) {
//                                    map["$inde"] = valaue
//                                }
//                                map["Name"] = "USDCNY"
//                            }
                            6 -> {
                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.uSDJPY.withIndex()) {
                                    map["$inde"] = valaue
                                }
                                map["Name"] = "USDJPY"
                            }
//                            9 -> {
//                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.fields.withIndex()){
//                                    map["$inde"] = valaue
//                                }
//                                map["Name"] = "FIELDS"
//                            }
                            else -> {
                                for ((inde, valaue) in httpwallstreetcn.data.snapshot.uSDOLLARINDEX.withIndex()) {
                                    map["$inde"] = valaue
                                }
                                map["Name"] = "USDOLLARINDEX"
                            }
                        }
                        list!!.add(map)
                    }
                    transactionFragmentSimpleAdapter.setData(list)

                }
            }
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as WallstreetcnService.Binder
            val myService = binder.service
            myService.setCallback(object : WallstreetcnService.Callback {
                override fun onDataChange(data: Any) {
                    val msg = Message()
                    msg.what = 1053
                    msg.obj = data
                    mHandler.sendMessage(msg)
                }
            })
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onTabSelected(tab: TouchableTabLayout.Tab) {
//        if (tab.getText() == "挂单") {
//            tabLayout.getTabAt(2)?.setIcon(R.mipmap.ic_down_arrow)
//            testPopupWindowType1()
//        }
        tab.getIcon()?.setColorFilter(Color.parseColor("#999999"), PorterDuff.Mode.SRC_IN)
    }

    override fun onTabUnselected(tab: TouchableTabLayout.Tab) {
        tab.getIcon()?.setColorFilter(ContextCompat.getColor(this.activity!!, R.color.white), PorterDuff.Mode.SRC_IN);
    }

    override fun onTabReselected(tab: TouchableTabLayout.Tab) {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_post -> {
                type = 1
                getColoText(type, true)
            }
            R.id.title_video -> {
                type = 2
                getColoText(type, false)
            }
            R.id.holding_on_list -> {
                if (mPopupWindow != null) {
                    mPopupWindow!!.dismiss()
                }
            }
            R.id.holding_historical -> {
                if (mPopupWindow != null) {
                    mPopupWindow!!.dismiss()
                }
            }
        }
    }

    private fun getColoText(type: Int, boolean: Boolean) {
        if (boolean) {
            title_post.setBackgroundResource(R.drawable.shape_left_red_while)
            title_post.setTextColor(Color.parseColor("#FF6D64"))

            title_video.setBackgroundResource(R.drawable.shape_right_while_red)
            title_video.setTextColor(Color.parseColor("#FFFFFF"))

            transactione_linear1.visibility = View.VISIBLE
            transactione_linear2.visibility = View.GONE
        } else {
            title_post.setBackgroundResource(R.drawable.shape_left_while_red)
            title_post.setTextColor(Color.parseColor("#FFFFFF"))

            title_video.setBackgroundResource(R.drawable.shape_right_red_while)
            title_video.setTextColor(Color.parseColor("#FF6D64"))

            transactione_linear1.visibility = View.GONE
            transactione_linear2.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        if (!AppUtil.instance.isServiceRunning(activity!!, "sp51.spotpass.com.spotpass.ui.service.WallstreetcnService")) {
            activity!!.bindService(Intent(activity!!, WallstreetcnService::class.java), serviceConnection, Activity.BIND_AUTO_CREATE)
        }
        MobclickAgent.onPageStart("MainTransaction") //统计页面("MainScreen"为页面名称，可自定义)
    }

    override fun onPause() {
        super.onPause()
        if (AppUtil.instance.isServiceRunning(activity!!, "sp51.spotpass.com.spotpass.ui.service.WallstreetcnService")) {
            activity!!.unbindService(serviceConnection)
        }
        MobclickAgent.onPageEnd("MainTransaction")
    }

    private var mPopupWindow: PopupWindow? = null

    @SuppressLint("InflateParams")
    private fun getPopupWindowContentView(): View {
        val contentView = LayoutInflater.from(activity).inflate(R.layout.popup_content_layout, null)
        contentView.findViewById<TextView>(R.id.holding_on_list).setOnClickListener(this)
        contentView.findViewById<TextView>(R.id.holding_historical).setOnClickListener(this)
        return contentView
    }

    @SuppressLint("RtlHardcoded")
    private fun testPopupWindowType1() {
        val contentView = getPopupWindowContentView()
        // 创建PopupWindow时候指定高宽时showAsDropDown能够自适应(能够根据剩余空间自动选中向上向下弹出)
        // 如果设置为wrap_content,showAsDropDown会认为下面空间一直很充足（我以认为这个Google的bug）
        // 备注如果PopupWindow里面有ListView,ScrollView时，一定要动态设置PopupWindow的大小
        mPopupWindow = PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        // popupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        // popupWindow.showAsDropDown(mButton1);  // 默认在mButton1的左下角显示
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val xOffset = -(tabLayout.width - contentView.measuredWidth + 330)
        val yOffset = 330
        mPopupWindow!!.showAtLocation(tabLayout, Gravity.TOP or Gravity.RIGHT, xOffset, yOffset)    // 在mButton1的中间显示
    }
}