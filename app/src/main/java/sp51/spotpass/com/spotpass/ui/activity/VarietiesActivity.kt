package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.view_title_textview.*
import kotlinx.android.synthetic.main.view_xrefresh_recycler.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.adapter.ProfitListSimpleAdapter
import sp51.spotpass.com.spotpass.ui.adapter.VarietiesSimpleAdapter
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshViewFooter
import java.util.*

@SuppressLint("Registered")
abstract
/**
 * @Time : 2018/5/8 no 10:29
 * @USER : vvguoliang
 * @File : VarietiesActivity.java
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
class VarietiesActivity : BaseActivity(), View.OnClickListener {

    private var mLoadCount = 0

    private lateinit var varietiesSimpleAdapter: VarietiesSimpleAdapter

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_varieties
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun initView(rootView: View) {

        custom_view.pullLoadEnable = true
        recycler_view_test_rv.setHasFixedSize(true)
        varietiesSimpleAdapter = VarietiesSimpleAdapter(this)
        getRecycler_view_test_rv()

        recycler_view_test_rv.layoutManager = LinearLayoutManager(this)
        //添加Android自带的分割线
        recycler_view_test_rv.addItemDecoration(DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL))

        val list = ArrayList<String>()
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg")


        name = getString(R.string.textView_market_variety)
        title_textview.visibility = View.VISIBLE
        title_textview.text = name

        title_integral_rule.visibility = View.VISIBLE
        title_integral_rule.text = getString(R.string.textView_cancel)
        title_integral_rule.setOnClickListener(this)

        varietiesSimpleAdapter.setData(list)
        recycler_view_test_rv.adapter = varietiesSimpleAdapter

        varietiesSimpleAdapter.setOnItemClickListener { _, data ->
            run {
                val intent = Intent()
                intent.putExtra("name", "nandkjakjdnkjand")
                setResult(
                        100,
                        intent
                )
                finish()
            }
        }
    }

    override fun setListener() {
    }

    override fun doBusiness() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_integral_rule -> finish()
        }
    }

    private fun getRecycler_view_test_rv() {
        custom_view.setPinnedTime(1000)
        custom_view.pullLoadEnable = true
        custom_view.setMoveForHorizontal(true)
        custom_view.setAutoLoadMore(true)
        varietiesSimpleAdapter.customLoadMoreView = XRefreshViewFooter(this)

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
                1000 -> {
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
        ImmersionBar.with(this@VarietiesActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }

}