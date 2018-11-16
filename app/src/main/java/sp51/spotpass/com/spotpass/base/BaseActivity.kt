package sp51.spotpass.com.spotpass.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.umeng.analytics.MobclickAgent
import com.umeng.message.PushAgent
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar

/**
 * @Time : 2018/3/20 no 下午2:22
 * @USER : vvguoliang
 * @File : BaseActivity.java
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
 */
abstract class BaseActivity : FragmentActivity(), IViewSpecification {

    /**
     * 是否允许屏幕转动  默认不允许
     */
    var isAllowScreenRotate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseActivityManager.activityManager.pushActivity(this)

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL)

        PushAgent.getInstance(this).onAppStart()

        if (!isAllowScreenRotate) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        val bundle = intent.extras
        initParams(bundle)

        val viewContent = layoutInflater.inflate(bindLayout(), null)
        setContentView(viewContent)
        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar()

        initView(viewContent)
        setListener()
        doBusiness()
    }

    protected var mImmersionBar: ImmersionBar? = null
    private var imm: InputMethodManager? = null

    /**
     * 设置statusBar主题
     */
    override fun initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar!!.init()
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected fun isImmersionBarEnabled(): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        this.imm = null
        if (mImmersionBar != null)
            mImmersionBar!!.destroy()  //在BaseActivity里销毁
    }

    override fun finish() {
        super.finish()
        hideSoftKeyBoard()
    }

    fun hideSoftKeyBoard() {
        val localView = currentFocus
        if (this.imm == null) {
            this.imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        }
        if (localView != null && this.imm != null) {
            this.imm!!.hideSoftInputFromWindow(localView.windowToken, 2)
        }
    }

    fun startActivity(targetActivity: Class<*>) {
        startActivity(Intent(this, targetActivity))
    }

    fun startActivity(targetActivity: Class<*>, bundle: Bundle?) {
        startActivity(Intent(this, targetActivity).putExtras(bundle))
    }

    fun startActivityForResult(cls: Class<*>, bundle: Bundle?,
                               requestCode: Int) {
        val intent = Intent()
        intent.setClass(this, cls)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        if (!fragment.isAdded)
            transaction.add(frameId, fragment)
        fragmentManager.fragments.filter { it.id == fragment.id }.map { transaction.hide(it) }
        transaction.show(fragment)
        transaction.commit()
    }
}