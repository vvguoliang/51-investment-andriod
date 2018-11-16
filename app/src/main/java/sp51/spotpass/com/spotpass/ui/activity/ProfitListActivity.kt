package sp51.spotpass.com.spotpass.ui.activity

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
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.view_title_textview.*
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.ProfitListSimpleAdapter
import sp51.spotpass.com.spotpass.ui.baseEntity.ProfitList
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshViewFooter
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView
import java.util.*
import sp51.spotpass.com.spotpass.ui.view.Dialog.DialogBuilder
import kotlin.collections.ArrayList


@SuppressLint("Registered")
/**
 * @Time : 2018/5/4 no 18:53
 * @USER : vvguoliang
 * @File : ProfitListActivity.java
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
 * 盈利榜
 */
class ProfitListActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.title_question_mark -> {
                var dialo = DialogBuilder(this@ProfitListActivity)
                        .title("上榜规则")
                        .message("1、\t将当前交易日内6:00到次日凌晨4:00间盈利的平仓单晒单成功（用券单不可参加）\n" +
                                "2、\t盈利率排名前20折即可上榜，产生后前三名榜单定时更新。\n" +
                                "3、\t上榜者奖励积分由系统在次日凌晨5:00自动发放。\n")
                        .setCancelable(false)
                        .setSureOnClickListener {

                        }.build().show()
            }
            R.id.profit_relative1 -> {
                val intent = Intent(this, ProfitActivity::class.java)
                intent.putExtra("name", list1!![0].get("name"))
                intent.putExtra("time", list1!![0].get("time"))
                val string = list1!![0].get("context")!!.split("%")
                intent.putExtra("e", string[0].subSequence(2, string[0].length))
                intent.putExtra("l", "${string[0].subSequence(2, string[0].length)}%")
                intent.putExtra("l1", string[1].subSequence(4, string[1].length))
                startActivity(intent)
            }
            R.id.profit_relative2 -> {
                val intent = Intent(this, ProfitActivity::class.java)
                intent.putExtra("name", list1!![1].get("name"))
                intent.putExtra("time", list1!![1].get("time"))
                val string = list1!![1].get("context")!!.split("%")
                intent.putExtra("e", string[0].subSequence(2, string[0].length))
                intent.putExtra("l", "${string[0].subSequence(2, string[0].length)}%")
                intent.putExtra("l1", string[1].subSequence(4, string[1].length))
                startActivity(intent)
            }
            R.id.profit_relative3 -> {
                val intent = Intent(this, ProfitActivity::class.java)
                intent.putExtra("name", list1!![2].get("name"))
                intent.putExtra("time", list1!![2].get("time"))
                val string = list1!![2].get("context")!!.split("%")
                intent.putExtra("e", string[0].subSequence(2, string[0].length))
                intent.putExtra("l", "${string[0].subSequence(2, string[0].length)}%")
                intent.putExtra("l1", string[1].subSequence(4, string[1].length))
                startActivity(intent)
            }
        }
    }

    private var mLoadCount = 0

    private lateinit var profitListSimpleAdapter: ProfitListSimpleAdapter

    private var list1: List<Map<String, String?>>? = null

    private lateinit var profit_relative1_text1: TextView
    private lateinit var profit_relative1_text2: TextView
    private lateinit var circleimage1: CircleImageView
    private lateinit var profit_relative2_text1: TextView
    private lateinit var profit_relative2_text2: TextView
    private lateinit var circleimage2: CircleImageView
    private lateinit var profit_relative3_text1: TextView
    private lateinit var profit_relative3_text2: TextView
    private lateinit var circleimage3: CircleImageView

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_profit_list
    }

    override fun initView(rootView: View) {
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun setListener() {

        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        profitListSimpleAdapter = ProfitListSimpleAdapter(this)
        getRecycler_view_test_rv()

        recycler_view_test_rv.layoutManager = LinearLayoutManager(this)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL))

        val headerView = profitListSimpleAdapter.setHeaderView(R.layout.view_profit_list_content, recycler_view_test_rv)

        val profit_text_time = headerView.findViewById<TextView>(R.id.profit_text_time)
        profit_text_time.text = AppUtil.instance.getTime()

//        headerView.findViewById<RelativeLayout>(R.id.profit_relative1).setOnClickListener(this)
        profit_relative1_text1 = headerView.findViewById(R.id.profit_relative1_text1)
        profit_relative1_text2 = headerView.findViewById(R.id.profit_relative1_text2)
        circleimage1 = headerView.findViewById(R.id.circleimage1)

//        headerView.findViewById<RelativeLayout>(R.id.profit_relative2).setOnClickListener(this)
        profit_relative2_text1 = headerView.findViewById(R.id.profit_relative2_text1)
        profit_relative2_text2 = headerView.findViewById(R.id.profit_relative2_text2)
        circleimage2 = headerView.findViewById(R.id.circleimage2)

//        headerView.findViewById<RelativeLayout>(R.id.profit_relative3).setOnClickListener(this)
        profit_relative3_text1 = headerView.findViewById(R.id.profit_relative3_text1)
        profit_relative3_text2 = headerView.findViewById(R.id.profit_relative3_text2)
        circleimage3 = headerView.findViewById(R.id.circleimage3)

        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)

        title_question_mark.visibility = View.GONE
        title_question_mark.setOnClickListener(this)

        name = getString(R.string.textView_profit_list)

        title_textview.visibility = View.VISIBLE
        title_textview.text = name

        val map1 = mapOf(Pair("name", "分析师"), Pair("context", "盈利746%预计奖励18600积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map2 = mapOf(Pair("name", "分析师"), Pair("context", "盈利600%预计奖励15000积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map3 = mapOf(Pair("name", "分析师"), Pair("context", "盈利451%预计奖励13200积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map4 = mapOf(Pair("name", "分析师"), Pair("context", "盈利400%预计奖励12000积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map5 = mapOf(Pair("name", "分析师"), Pair("context", "盈利395%预计奖励119000积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map6 = mapOf(Pair("name", "分析师"), Pair("context", "盈利365%预计奖励11500积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map7 = mapOf(Pair("name", "分析师"), Pair("context", "盈利330%预计奖励10900积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map8 = mapOf(Pair("name", "分析师"), Pair("context", "盈利290%预计奖励10500积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map9 = mapOf(Pair("name", "分析师"), Pair("context", "盈利260%预计奖励10000积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map10 = mapOf(Pair("name", "分析师"), Pair("context", "盈利240%预计奖励9000积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map11 = mapOf(Pair("name", "分析师"), Pair("context", "盈利210%预计奖励8500积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map12 = mapOf(Pair("name", "分析师"), Pair("context", "盈利190%预计奖励7500积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map13 = mapOf(Pair("name", "分析师"), Pair("context", "盈利150%预计奖励7200积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map14 = mapOf(Pair("name", "分析师"), Pair("context", "盈利120%预计奖励6900积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map15 = mapOf(Pair("name", "分析师"), Pair("context", "盈利110%预计奖励65000积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map16 = mapOf(Pair("name", "分析师"), Pair("context", "盈利90%预计奖励5900积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map17 = mapOf(Pair("name", "分析师"), Pair("context", "盈利80%预计奖励5200积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map18 = mapOf(Pair("name", "分析师"), Pair("context", "盈利70%预计奖励4900积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map19 = mapOf(Pair("name", "分析师"), Pair("context", "盈利60%预计奖励4200积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))
        val map20 = mapOf(Pair("name", "分析师"), Pair("context", "盈利50%预计奖励4000积分"), Pair("image", ""), Pair("money", "1.0"), Pair("time", AppUtil.instance.getTime()))

        val list = listOf(map1, map2, map3, map4, map5, map6, map7, map8, map9, map10, map11, map12, map13, map14, map15, map16, map17, map18, map19, map20)
        list1 = list.subList(0, 3)
        val list2 = list.subList(3, list.size)

        getThree(0, map1, profit_relative1_text1, profit_relative1_text2, circleimage1)
        getThree(1, map2, profit_relative2_text1, profit_relative2_text2, circleimage2)
        getThree(2, map3, profit_relative3_text1, profit_relative3_text2, circleimage3)


        profitListSimpleAdapter.setData(list2)
        recycler_view_test_rv.adapter = profitListSimpleAdapter

//        profitListSimpleAdapter.setOnItemClickListener({ _, data ->
//            val intent = Intent(this, ProfitActivity::class.java)
//            intent.putExtra("name", list2.get(data - 1).get("name"))
//            intent.putExtra("time", list2.get(data - 1).get("time"))
//            val string = list2.get(data - 1).get("context")!!.split("%")
//
//            intent.putExtra("e", string[0].subSequence(2, string[0].length))
//            intent.putExtra("l", "${string[0].subSequence(2, string[0].length)}%")
//            intent.putExtra("l1", string[1].subSequence(4, string[1].length))
//            startActivity(intent)
//        })
    }

    private fun getThree(i: Int, list: Map<String, String?>, textView1: TextView, textView2: TextView, circleImageView: CircleImageView) {
        val strings1 = list["context"]!!.split("%")
        val ssb1 = SpannableStringBuilder(strings1[0] + "%")
        ssb1.setSpan(ForegroundColorSpan(Color.parseColor("#FF6D64")), 2, (strings1[0] + "%").length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView1.text = ssb1

        val string = strings1[1].subSequence(0, 4)
        val ssb11 = SpannableStringBuilder(strings1[1].subSequence(4, strings1[1].length))
        ssb11.setSpan(ForegroundColorSpan(Color.parseColor("#FF6D64")), 0, strings1[1].subSequence(4, strings1[1].length).length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView2.text = ssb11

        val options = RequestOptions()
        options.centerCrop().placeholder(R.mipmap.ic_banner).error(R.mipmap.ic_banner).fallback(R.mipmap.ic_banner)
        Glide.with(this).load(list["image"]).apply(options).into(circleImageView)
    }

    override fun doBusiness() {

    }

    private fun getRecycler_view_test_rv() {
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        profitListSimpleAdapter.customLoadMoreView = XRefreshViewFooter(this)

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
                1013 -> {
                    val profitList = msg.obj as ProfitList
                    val list = ArrayList<Map<String, String>>()
                    for (indxe in profitList.data) {
                        when (indxe.orderNo) {
                            1 -> {
                                val map1 = mapOf(Pair("name", indxe.username), Pair("context", "盈利${indxe.percent}%预计奖励${indxe.points}积分"), Pair("image", indxe.headImg))
                                getThree(1, map1, profit_relative1_text1, profit_relative1_text2, circleimage1)
                            }
                            2 -> {
                                val map1 = mapOf(Pair("name", indxe.username), Pair("context", "盈利${indxe.percent}%预计奖励${indxe.points}积分"), Pair("image", indxe.headImg))
                                getThree(0, map1, profit_relative2_text1, profit_relative2_text2, circleimage2)
                            }
                            3 -> {
                                val map1 = mapOf(Pair("name", indxe.username), Pair("context", "盈利${indxe.percent}%预计奖励${indxe.points}积分"), Pair("image", indxe.headImg))
                                getThree(2, map1, profit_relative3_text1, profit_relative3_text2, circleimage3)
                            }
                            else -> {
                                val map1 = mapOf(Pair("name", indxe.username), Pair("context", "盈利${indxe.percent}%预计奖励${indxe.points}积分"), Pair("image", indxe.headImg))
                                list.add(map1)
                            }
                        }
                    }
                    profitListSimpleAdapter.setData(list)
                }
            }
        }
    }


    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        HttpALl.get().setGETPROFITLIST(mHandler, this)
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
        ImmersionBar.with(this@ProfitListActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }

}