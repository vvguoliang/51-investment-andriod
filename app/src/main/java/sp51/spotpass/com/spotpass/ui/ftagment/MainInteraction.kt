package sp51.spotpass.com.spotpass.ui.ftagment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseFragment
import sp51.spotpass.com.spotpass.ui.activity.MainInteractionDetailsActivity
import sp51.spotpass.com.spotpass.ui.activity.ReleaseActivity
import sp51.spotpass.com.spotpass.ui.adapter.SimpleAdapterInteraction
import sp51.spotpass.com.spotpass.ui.baseEntity.CirclessList
import sp51.spotpass.com.spotpass.ui.baseEntity.ViDeoList
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.http.HttpImplements
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshViewFooter
import java.util.*
import kotlin.collections.ArrayList

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
 * 互动
 */
@SuppressLint("ResourceAsColor")
class MainInteraction : BaseFragment(), View.OnClickListener {

    private lateinit var custom_view: XRefreshView

    private lateinit var recycler_view_test_rv: RecyclerView

    private lateinit var title_integral_rule: TextView

    private lateinit var title_post_video: LinearLayout

    private lateinit var title_post: TextView

    private lateinit var title_video: TextView

    private var list: ArrayList<CirclessList.Data.Data> = ArrayList()

    private var listVideo: ArrayList<ViDeoList.Data.Data> = ArrayList()

    private var page = 0

    private var url = ""

    private var type = 1

    private var boolean: Boolean = false

    private var boolean1: Boolean = false

    private lateinit var simpleAdapterInteraction: SimpleAdapterInteraction

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.far_interaction, container, false)
        custom_view = view.findViewById(R.id.custom_view)
        recycler_view_test_rv = view.findViewById(R.id.recycler_view_test_rv)
        title_integral_rule = view.findViewById(R.id.title_integral_rule)
        title_post_video = view.findViewById(R.id.title_post_video)
        title_post = view.findViewById(R.id.title_post)
        title_video = view.findViewById(R.id.title_video)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        simpleAdapterInteraction = SimpleAdapterInteraction(activity)

        title_integral_rule.text = getString(R.string.textView_post)
        title_integral_rule.visibility = View.GONE
        title_integral_rule.setOnClickListener(this)

        title_post_video.visibility = View.GONE
        title_post.setOnClickListener(this)
        title_video.setOnClickListener(this)

        title_textview.visibility = View.VISIBLE
        title_textview.text = getString(R.string.navigation_interaction)

        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)

        getRecycler_view_test_rv()

        recycler_view_test_rv.layoutManager = LinearLayoutManager(activity)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(activity), DividerItemDecoration.VERTICAL))

        recycler_view_test_rv.adapter = simpleAdapterInteraction
        getColoText(type, true)
    }

    private fun getRecycler_view_test_rv() {
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        simpleAdapterInteraction.customLoadMoreView = XRefreshViewFooter(activity)

        custom_view.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                if (list != null && list.size > 0) {
                    list.clear()
                }
                page = 0
                boolean = true
                boolean1 = false
                getHttp()
            }

            override fun onLoadMore(isSilence: Boolean) {
                page++
                boolean1 = true
                boolean = false
                if (type == 1) {
                    HttpALl.get().setGETCIRCLESSLIST1("GETCIRCLESSLIST", "$url${page + 1}", mHandler, activity!!)
                } else {
                    HttpALl.get().setGETCIRCLESSLIST1("GETVIDEOLIST", "$url${page + 1}", mHandler, activity!!)
                }
            }
        })
        simpleAdapterInteraction.setOnItemClickListener { _, data ->
//            if (type == 1) {
//                if (list != null && list.size > 0) {
//                    val intent = Intent(activity, MainInteractionDetailsActivity::class.java)
//                    intent.putExtra("look", "0")
//                    intent.putExtra("id", list[data].id)
//                    startActivity(intent)
//                }
//            }
//            else {
//                if (listVideo != null && listVideo.size > 0) {
//                    val intent = Intent(activity, SimpleDetailModeActivity::class.java)
//                    intent.putExtra("videoUrl", "${HttpImplements.get().Https51}${listVideo[data].videoUrl}")
//                    intent.putExtra("coverUrl", "${HttpImplements.get().Https51}${listVideo[data].coverUrl}")
//                    activity!!.startActivity(intent)
//                }
//            }
        }

    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1012 -> {
                    val videoView = msg.obj as ViDeoList
                    if (page == 0 && listVideo.size > 0) {
                        listVideo.clear()
                    }
                    for (indes in videoView.data.data) {
                        listVideo.add(indes)
                    }
                    url = videoView.data.firstPageUrl.substring(0, videoView.data.firstPageUrl.length - 1)
                }
                1019 -> {
                    val circlasslist = msg.obj as CirclessList
                    if (page == 0 && list.size > 0) {
                        list.clear()
                    }
                    url = circlasslist.data.firstPageUrl.substring(0, circlasslist.data.firstPageUrl.length - 1)
                    for (index in circlasslist.data.data) {
                        list.add(index)
                    }
                }
            }
            if (type == 2) {
                simpleAdapterInteraction.setDatavideo(type, listVideo)
            } else if (type == 1) {
                simpleAdapterInteraction.setData(type, list)
            }
            if (boolean) {
                custom_view.stopRefresh()
            } else if (boolean1) {
                custom_view.setLoadComplete(true)
            }
        }
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
            R.id.title_integral_rule -> startActivity(Intent(activity, ReleaseActivity::class.java))
        }
    }


    private fun getColoText(type: Int, boolean: Boolean) {
        page = 0
        if (boolean) {
            title_integral_rule.visibility = View.GONE
            title_post.setBackgroundResource(R.drawable.shape_left_red_while)
            title_post.setTextColor(Color.parseColor("#FF6D64"))
            getHttp()
            title_video.setBackgroundResource(R.drawable.shape_right_while_red)
            title_video.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            getVdeolist()
            title_integral_rule.visibility = View.GONE
            title_post.setBackgroundResource(R.drawable.shape_left_while_red)
            title_post.setTextColor(Color.parseColor("#FFFFFF"))
            title_video.setBackgroundResource(R.drawable.shape_right_red_while)
            title_video.setTextColor(Color.parseColor("#FF6D64"))

        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            getHttp()
            getVdeolist()
        }
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("MainInteraction") //统计页面("MainScreen"为页面名称，可自定义)
    }


    private fun getHttp() {
        HttpALl.get().setGETCIRCLESSLIST(mHandler, activity!!)
    }

    private fun getVdeolist() {
        HttpALl.get().setGETVIDEOLIST(mHandler, activity!!)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("MainInteraction")
    }


}
