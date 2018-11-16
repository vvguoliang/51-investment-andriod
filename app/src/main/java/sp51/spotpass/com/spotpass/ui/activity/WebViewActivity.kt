@file:Suppress("DEPRECATION", "OverridingDeprecatedMember")

package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.text.Html
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_webview.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.GetAssetsFiles
import android.content.Intent
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils


/**
 * @Time : 2018/5/19 no 9:23
 * @USER : vvguoliang
 * @File : WebViewActivity.java
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
class WebViewActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.title_right_arrow_white -> {
                if (title_button_fork_white.visibility == View.VISIBLE) {
                    banner_webView.goBack()// 返回前一个页面
                } else {
                    finish()
                }
            }
            R.id.title_button_fork_white -> finish()
        }
    }

    private var url: String = ""

    private var type: String = ""

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_webview
    }

    override fun initView(rootView: View) {
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun setListener() {
        title_right_arrow_white.visibility = View.VISIBLE
        title_textview.visibility = View.VISIBLE
        url = intent.getStringExtra("url")
        type = intent.getStringExtra("type")
        getSettings()

        banner_webView.webViewClient = webViewClient
        banner_webView.webChromeClient = webChromeClient
        if (!TextUtils.isEmpty(url) && "null" != url) {
            when (type) {
                "1" -> banner_webView.loadData(url, "text/html; charset=UTF-8", null)
                "2" -> {
                    val text = GetAssetsFiles.getString(this, "jiaoyi.htm")
                    banner_webView.loadData(url, "text/html", "UTF-8")
                }
                "3" -> banner_webView.loadData(url, "text/html; charset=UTF-8", null)
                else -> {
                    title_button_fork_white.visibility = View.VISIBLE
                    title_button_fork_white.setOnClickListener(this)
                    banner_webView.loadUrl(url)
                }
            }
            banner_linear.visibility = View.VISIBLE
        } else {
            title_textview.text = ""
            banner_linear.visibility = View.GONE
        }
        title_right_arrow_white.setOnClickListener(this)
    }

    override fun doBusiness() {
    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(R.id.top_view)?.init()
        ImmersionBar.with(this@WebViewActivity)
                .statusBarDarkFont(false, 1.0f)
                .init()

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && banner_webView.canGoBack()) {
            banner_webView.goBack()// 返回前一个页面
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    private val webViewClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.contains("success.html")) {
                val intent = Intent(this@WebViewActivity, PaymentSuccess::class.java)
                intent.putExtra("type", 4)
                startActivity(intent)
                finish()
            } else if (url.contains("platformapi/startapp")) {
                startAlipayActivity(url);
                // android  6.0 两种方式获取intent都可以跳转支付宝成功,7.1测试不成功
            } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                    && (url.contains("platformapi") && url.contains("startapp"))) {
                startAlipayActivity(url);
            } else {
                banner_webView.loadUrl(url)
            }
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            if (view.title.contains("404") || view.title.contains("找不到") ||
                    view.title.contains("about:")) {
                title_textview.text = ""
                banner_linear.visibility = View.GONE
            } else {
                title_textview.text = view.title
                banner_linear.visibility = View.VISIBLE
            }
        }
    }

    private val webChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            banner_progressBar.progress = newProgress
            if (newProgress == 100) {
                banner_progressBar.visibility = View.GONE
            } else {
                banner_progressBar.visibility = View.VISIBLE
            }
        }
    }

    // 调起支付宝并跳转到指定页面
    private fun startAlipayActivity(url: String) {
        val intent: Intent
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.component = null
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            ToatUtils.showShort1(this, "您没有安装支付宝")
        }

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    private fun getSettings() {
        banner_webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null) //渲染加速器
        banner_webView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH) //提高渲染的优先级
        banner_webView.removeJavascriptInterface("searchBoxJavaBridge_") //防止360
        val settings = banner_webView.settings

        settings.blockNetworkImage = false
        settings.saveFormData = false
        settings.allowContentAccess = true
        settings.defaultTextEncodingName = "UTF-8"
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH)

        settings.allowFileAccessFromFileURLs = true

        settings.javaScriptEnabled = true  //支持js
        settings.useWideViewPort = true  //将图片调整到适合webview的大小
        settings.setSupportZoom(true)  //支持缩放
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN //支持内容重新布局
        settings.supportMultipleWindows()  //多窗口
        settings.cacheMode = WebSettings.LOAD_NO_CACHE  //关闭webview中缓存
        settings.allowFileAccess = true  //设置可以访问文件
        settings.setNeedInitialFocus(true) //当webview调用requestFocus时为webview设置节点
        settings.builtInZoomControls = true //设置支持缩放
        settings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        settings.loadWithOverviewMode = true // 缩放至屏幕的大小
        settings.loadsImagesAutomatically = true  //支持自动加载图片

        settings.databaseEnabled = false//开启数据库形式存储
        settings.domStorageEnabled = false//开启DOM形式存储
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }

    public override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("webviewactivity") //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    public override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("webviewactivity") //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this)
    }
}