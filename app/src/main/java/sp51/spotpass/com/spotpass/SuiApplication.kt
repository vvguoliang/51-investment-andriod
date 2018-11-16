package sp51.spotpass.com.spotpass

import android.app.Application
import cn.jpush.android.api.JPushInterface
import com.facebook.drawee.backends.pipeline.Fresco
import com.umeng.commonsdk.UMConfigure
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent


/**
 * @Time : 2018/5/2 no 下午2:04
 * @USER : vvguoliang
 * @File : SuiApplication.java
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
class SuiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this;//存储引用
        Fresco.initialize(this)

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        /*
注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，UMConfigure.init调用中appkey和channel参数请置为null）。
*/
        UMConfigure.init(
                this,
                "5afe3244b27b0a3ff90001c1",
                AppUtil.instance.getChannel(this, 0),
                UMConfigure.DEVICE_TYPE_PHONE,
                "6f75798ab9d2c63b68984e6b8e0c51ab"
        )

        UMConfigure.setEncryptEnabled(false);

//        MobclickAgent.setSessionContinueMillis(1000 * 40);

        val mPushAgent = PushAgent.getInstance(this)
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String) {
                //注册成功会返回device token
            }

            override fun onFailure(s: String, s1: String) {}
        })
    }

    private var instance: SuiApplication? = null

    fun getInstance(): SuiApplication? {
        return instance
    }
}