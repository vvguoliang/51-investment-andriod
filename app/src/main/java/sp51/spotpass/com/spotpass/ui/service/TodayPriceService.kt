package sp51.spotpass.com.spotpass.ui.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.R.string.cancel
import android.content.Context.ALARM_SERVICE
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import sp51.spotpass.com.spotpass.ui.http.HttpALl


@SuppressLint("Registered")
/**
 * @Time : 2018/5/28 no 17:27
 * @USER : vvguoliang
 * @File : LastPriceService.java
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
class TodayPriceService : Service() {

    private var pushthread = false

    private lateinit var qtecode: String

    private var callback: Callback? = null

    override fun onBind(intent: Intent?): IBinder {
        qtecode = intent!!.getStringExtra("qtecode")

        //判断当系统版本大于20，即超过Android5.0时，我们采用线程循环的方式请求。
        //当小于5.0时的系统则采用定时唤醒服务的方式执行循环
        pushthread = !TextUtils.isEmpty(qtecode)
        getPushThread()
        return Binder()
    }

    inner class Binder : android.os.Binder() {
        val service: TodayPriceService
            get() = this@TodayPriceService
    }

    //循环请求的线程
    private fun getPushThread() {
        Thread(Runnable {
            while (pushthread) {
                try {
                    Thread.sleep(10000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }).start()
    }

    override fun onDestroy() {
        pushthread = false;
        super.onDestroy()
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1008 -> {
                    if (callback != null) {
                        callback!!.onDataChange(msg.obj)
                    }

                }
            }
        }
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun onDataChange(data: Any)
    }


    //启动服务和定时器
    fun getConnet(mContext: Context, qtecode: String) {
        try {
            val intent = Intent(mContext, TodayPriceService::class.java)
            intent.putExtra("qtecode", qtecode)
            val currentapiVersion = android.os.Build.VERSION.SDK_INT
            if (currentapiVersion > 20) {
                //一般的启动服务的方式
                mContext.startService(intent)
            } else {
                //定时唤醒服务的启动方式
                val pIntent = PendingIntent.getService(mContext, 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT)
                val alarmManager = mContext
                        .getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis(), 10000, pIntent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    //停止由AlarmManager启动的循环
    fun stop(mContext: Context) {
        val intent = Intent(mContext, TodayPriceService::class.java)
        val pIntent = PendingIntent.getService(mContext, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = mContext
                .getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pIntent)
    }
}