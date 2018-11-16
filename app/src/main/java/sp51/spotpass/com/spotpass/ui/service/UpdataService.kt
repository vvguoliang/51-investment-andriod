package sp51.spotpass.com.spotpass.ui.service

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.webkit.MimeTypeMap
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.support.v4.content.FileProvider
import android.os.Build
import android.os.Environment.getExternalStorageDirectory
import android.app.DownloadManager.EXTRA_DOWNLOAD_ID
import android.app.DownloadManager.ACTION_DOWNLOAD_COMPLETE
import android.content.BroadcastReceiver
import android.os.IBinder
import android.app.Service.START_NOT_STICKY
import java.nio.file.Files.exists
import android.content.IntentFilter
import sp51.spotpass.com.spotpass.R.string.app_name
import android.app.DownloadManager.Request.NETWORK_WIFI
import android.app.DownloadManager.Request.NETWORK_MOBILE
import android.app.Service
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import java.io.File


/**
 * @Time : 2018/5/31 no 17:47
 * @USER : vvguoliang
 * @File : UpdataService.java
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
@SuppressLint("Registered")
/**
 * Created by vvguoliang on 2017/7/26.\
 *
 *
 * 版本更新
 */

class UpdataService : Service() {
    /**
     * 安卓系统下载类
     */
    private var manager: DownloadManager? = null
    /**
     * 接收下载完的广播
     */
    private var receiver: DownloadCompleteReceiver? = null
    private var url: String? = null
    private val DOWNLOADPATH = "/apk/"//下载路径，如果不定义自己的路径，6.0的手机不自动安装

    /**
     * 初始化下载器
     */
    private fun initDownManager() {
        manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        receiver = DownloadCompleteReceiver()
        //设置下载地址
        val down = DownloadManager.Request(Uri.parse(url))
        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
        down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        down.setAllowedOverRoaming(false)
        val mimeTypeMap = MimeTypeMap.getSingleton()
        val mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url))
        down.setMimeType(mimeString)
        // 下载时，通知栏显示途中
        down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        // 显示下载界面
        down.setVisibleInDownloadsUi(true)
        // 设置下载后文件存放的位置
        down.setDestinationInExternalPublicDir(DOWNLOADPATH, "jsy.apk")
        down.setTitle(this.getString(R.string.app_name))
        // 将下载请求放入队列
        manager!!.enqueue(down)
        //注册下载广播
        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        url = intent.getStringExtra("url")
        val path = Environment.getExternalStorageDirectory().absolutePath + DOWNLOADPATH + "jsy.apk"
        val file = File(path)
        if (file.exists()) {
            file.delete()
        }
        try {
            // 调用下载
            initDownManager()
        } catch (e: Exception) {
            e.printStackTrace()
            ToatUtils.showShort1(applicationContext, "下载失败")
        }

        return Service.START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {

        return null
    }

    override fun onDestroy() {
        if (receiver != null)
        // 注销下载广播
            unregisterReceiver(receiver)
        super.onDestroy()
    }

    // 接受下载完成后的intent
    internal inner class DownloadCompleteReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            //判断是否下载完成的广播
            if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
                //获取下载的文件id
                val downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (manager!!.getUriForDownloadedFile(downId) != null) {
                    //自动安装apk
                    val file = File(Environment.getExternalStorageDirectory().absolutePath + DOWNLOADPATH + "jsy.apk")
                    openFile(file, context)
                    //installAPK(context);
                } else {
                    ToatUtils.showShort1(context, "下载失败")
                }
                //停止服务并关闭广播
                this@UpdataService.stopSelf()
            }
        }
    }

    fun openFile(file: File, context: Context) {
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        val data: Uri
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // "net.csdn.blog.ruancoder.fileprovider"即是在清单文件中配置的authorities
            data = FileProvider.getUriForFile(context, "com.jsy.jsydemo.fileProvider", file)
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        } else {
            data = Uri.fromFile(file)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        intent.setDataAndType(data, "application/vnd.android.package-archive")
        context.startActivity(intent)
    }

    fun getMIMEType(var0: File): String? {
        val var1: String?
        val var2 = var0.name
        val var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length).toLowerCase()
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3)
        return var1
    }
}