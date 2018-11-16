@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package sp51.spotpass.com.spotpass.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import kotlinx.android.synthetic.main.act_start_up.*
import sp51.spotpass.com.spotpass.MainActivity
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.view.CountDownProgressView
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.widget.ImageView
import sp51.spotpass.com.spotpass.ui.adapter.ViewPagerAdapter
import android.view.animation.AlphaAnimation
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umeng.analytics.MobclickAgent
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.ui.utils.Camera.UserCenterRealize
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import android.R.attr.duration
import java.util.Base64.getDecoder
import com.bumptech.glide.gifdecoder.GifDecoder
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import sp51.spotpass.com.spotpass.R.id.image_starup
import sp51.spotpass.com.spotpass.R.id.starUp_countview
import android.graphics.drawable.Drawable
import android.support.annotation.Nullable
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.target.Target


/**
 * @Time : 2018/5/14 no 10:34
 * @USER : vvguoliang
 * @File : StartUpActivity.java
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
class StartUpActivity : BaseActivity(), CountDownProgressView.OnProgressListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    // 定义ViewPager适配器
    private var vpAdapter: ViewPagerAdapter? = null
    // 定义一个ArrayList来存放View
    private var views: ArrayList<View>? = null
    // 引导图片资源
    private val pics = intArrayOf(R.mipmap.ic_boot1_page, R.mipmap.ic_boot2_page, R.mipmap.ic_boot3_page, R.mipmap.ic_boot4_page)
    // 底部小点的图片
    private var points: Array<ImageView>? = null
    // 记录当前选中位置
    private var currentIndex: Int = 0

    private var userCenterRealize: UserCenterRealize? = null

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.start_up_button -> {
                starUp_relative2.visibility = View.GONE
                starUp_relative1.visibility = View.VISIBLE
                SPUtils.getInstance(this, "first_time").put("first_time", true)
                starUp_countview.start()
                starUp_countview.setProgressListener(this)
            }
            R.id.starUp_countview -> {
                startActivity(Intent(this, MainActivity::class.java))
                starUp_countview.stop()
                finish()

            }

        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (position === pics.size - 1) {
            //最后一个，实现动画浮现
            start_up_button.visibility = View.VISIBLE
            val aa = AlphaAnimation(0f, 1f)
            aa.duration = 1000
            start_up_button.startAnimation(aa)
        } else {
            start_up_button.visibility = View.GONE
        }
    }

    override fun onPageSelected(position: Int) {
    }

    override fun onProgress(progress: Int) {
        if (progress == 0) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_start_up
    }

    override fun initView(rootView: View) {
        userCenterRealize = UserCenterRealize()
        userCenterRealize!!.getUpdata(this, "")
        if (SPUtils.getInstance(this, "first_time").getBoolean("first_time", false)) {
            starUp_relative2.visibility = View.GONE
            starUp_relative1.visibility = View.VISIBLE
            starUp_countview.start()
            starUp_countview.setProgressListener(this)
            starUp_countview.setOnClickListener(this)
            val options = RequestOptions()
            options.centerCrop().placeholder(R.mipmap.ic_start_up1).error(R.mipmap.ic_start_up1).fallback(R.mipmap.ic_start_up1)
            Glide.with(this).load(R.mipmap.ic_start_up1).apply(options).into(image_starup)
        } else {
            starUp_relative2.visibility = View.VISIBLE
            starUp_relative1.visibility = View.GONE

            view_page.addOnPageChangeListener(this)
            start_up_button.setOnClickListener(this)

            //初始化引导资源
            views = ArrayList()
            for (i in 0 until pics.size) {
                val imageView = ImageView(this)
                imageView.scaleType = ImageView.ScaleType.FIT_XY
                imageView.setImageResource(pics[i])
                views!!.add(imageView)
            }
            //设置引导页
            vpAdapter = ViewPagerAdapter(views)
            view_page.adapter = vpAdapter
        }

    }

    override fun setListener() {
    }

    override fun doBusiness() {

    }


    public override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("启动页面"); //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    public override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("启动页面"); //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
         if (requestCode == AppUtil.instance.MY_PERMISSIONS_REQUEST_READ_SD) {
            if (!TextUtils.isEmpty("${grantResults[0]}") && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userCenterRealize!!.getUpdata(this, "")
            } else {
                Toast.makeText(this, "请授予读SD卡权限", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == AppUtil.instance.MY_PERMISSIONS_REQUEST_WRITE_SK) {
            if (!TextUtils.isEmpty("${grantResults[0]}") && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userCenterRealize!!.getUpdata(this, "")
            } else {
                Toast.makeText(this, "请授予写SD卡权限", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == AppUtil.instance.MY_PERMISSIONS_REQUEST_READ_SD_PHOTOALBUM) {
            if (!TextUtils.isEmpty("${grantResults[0]}") && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userCenterRealize!!.getUpdata(this, "")
            } else {
                Toast.makeText(this, "请授予读SD卡权限", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == AppUtil.instance.MY_PERMISSIONS_REQUEST_WRITE_SK_PHOTOALBUM) {
            if (!TextUtils.isEmpty("${grantResults[0]}") && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userCenterRealize!!.getUpdata(this, "")
            } else {
                Toast.makeText(this, "请授予写SD卡权限", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(R.id.top_view)?.init()
        ImmersionBar.with(this@StartUpActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()

    }
}