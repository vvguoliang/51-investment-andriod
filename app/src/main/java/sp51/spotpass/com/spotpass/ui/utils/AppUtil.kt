package sp51.spotpass.com.spotpass.ui.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Base64
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.NetworkInterface
import java.net.ServerSocket
import java.net.SocketException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

/**
 * @Time : 2018/4/2 no 下午10:34
 * @USER : vvguoliang
 * @File : AppUtil.java
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
@Suppress("DEPRECATION")
@SuppressLint("HardwareIds")
class AppUtil
/**
 * private的构造函数用于避免外界直接使用new来实例化对象
 */
private constructor() {

    var mBuildVersion: Int? = android.os.Build.VERSION.SDK_INT//当前SDK版本

    val MY_PERMISSIONS_REQUEST_CAMERA = 100//拍照权限
    val MY_PERMISSIONS_REQUEST_READ_SD = 101//读SD卡权限
    val MY_PERMISSIONS_REQUEST_WRITE_SK = 102//写SD卡权限
    val MY_PERMISSIONS_REQUEST_READ_SD_PHOTOALBUM = 103//写SD卡权限
    val MY_PERMISSIONS_REQUEST_WRITE_SK_PHOTOALBUM = 104//写SD卡权限
    val CAPTURE_IMAGE_REQUEST = 104//拍照后的返回值
    val LOAD_IMAGE_REQUEST = 105//相册的返回值
    val CLIP_IMAGE_REQUEST = 106//剪裁图片的返回值
    val IMAGE_TYPE = "image/*"
    var mOutFile: File? = null//图片uri路径
    var mImageFile: File? = null//图片file路径
    val MY_PERMISSIONS_PHONE_READWRITE = 110

    val TIME = 2000

    /**
     * 判断摄像头是否可用
     * 主要针对6.0 之前的版本，现在主要是依靠try...catch... 报错信息，感觉不太好，
     * 以后有更好的方法的话可适当替换
     *
     * @return
     */
    // setParameters 是针对魅族MX5 做的。MX5 通过Camera.open() 拿到的Camera
    // 对象不为null
    val isCameraCanUse: Boolean
        get() {
            var canUse = true
            var mCamera: Camera? = null
            try {
                mCamera = Camera.open()
                val mParameters = mCamera!!.parameters
                mCamera.parameters = mParameters
            } catch (e: Exception) {
                canUse = false
            }

            if (mCamera != null) {
                mCamera.release()
            }
            return canUse
        }
    val macAddress: String
        get() {
            val macAddress: String
            val buf = StringBuilder()
            var networkInterface: NetworkInterface?
            try {
                networkInterface = NetworkInterface.getByName("eth1")
                if (networkInterface == null) {
                    networkInterface = NetworkInterface.getByName("wlan0")
                }
                if (networkInterface == null) {
                    return "02:00:00:00:00:02"
                }
                val addr = networkInterface.hardwareAddress
                for (b in addr) {
                    buf.append(String.format("%02X:", b))
                }
                if (buf.length > 0) {
                    buf.deleteCharAt(buf.length - 1)
                }
                macAddress = buf.toString()
            } catch (e: SocketException) {
                e.printStackTrace()
                return "02:00:00:00:00:02"
            }

            return macAddress
        }

    private var lastClickTime: Long = 0

    val isFastDoubleClick: Boolean
        get() {
            val time = System.currentTimeMillis()
            val timeD = time - lastClickTime
            if (timeD in 1..499) {
                return true
            }
            lastClickTime = time
            return false
        }

    /**
     * 单例对象实例
     */
    private object AppUtilHolder {
        internal val INSTANCE = AppUtil()
    }

    /**
     * 屏幕分辨率
     *
     * @param context
     * @return
     */
    fun Dispay(context: Activity): IntArray {
        val windowManager = context.windowManager
        val display = windowManager.defaultDisplay
        val screenWidth = display.width
        val screenHeight = display.height
        return intArrayOf(screenWidth, screenHeight)
    }

    fun Html(title: String, content: String, updatedTime: String): String {
        return "<!doctype html public \"-//w3c//dtd xhtml 1.0 transitional//en\" \"http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />" +
                "<title>${title}</title>" +
                "</head><body>" +
                "<div class=\"artlist\" align=\"left\" vertical-align=\"middle\"><font face=\"微软雅黑\" size=\"10\" color=\"#333333\">${content}</font><br/></div></body></html>"
    }

    /**
     * 屏幕分辨率
     *
     * @param context
     * @return
     */
    fun Dispay(context: Context): IntArray {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return intArrayOf(outMetrics.widthPixels, outMetrics.heightPixels)
    }

    fun getTextw(view: View): TextWatcher {

        return object : TextWatcher {

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                if (editable.length > 0) {
                    view.visibility = View.VISIBLE
                } else {
                    view.visibility = View.GONE
                }
            }
        }
    }

    //设置EditText可输入和不可输入状态
    fun editTextable(editText: EditText, editable: Boolean) {
        if (!editable) { // disable editing password
            editText.isFocusable = false
            editText.isFocusableInTouchMode = false // user touches widget on phone with touch screen
            editText.isClickable = false // user navigates with wheel and selects widget
        } else { // enable editing of password
            editText.isFocusable = true
            editText.isFocusableInTouchMode = true
            editText.isClickable = true
        }
    }

    fun setNumber(context: Context, text: String, namestring: String): Long? {
        val stringa = SPUtils.getInstance(context, namestring).getStringSet(namestring)
        if (!TextUtils.isEmpty(stringa.toString())) {
            val mapList = SPUtils.getInstance(context, "list").getInfo(context, stringa.toString())
            var i = 0
            while (mapList.size > i) {
                if (!TextUtils.isEmpty(text)) {
                    if (text == mapList.get(i).get("name")) {
                        return java.lang.Long.parseLong(mapList.get(i)["listNo"].toString())
                    }
                }
                i++
            }
        }
        return 0L
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称 版本号
     */
    fun getVersionName(code: Int, context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0)
            return if (code == 1) {
                packageInfo.versionName
            } else {
                packageInfo.versionCode.toString() + ""
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return null
    }


    /**
     * 随机数
     */
    fun getRandom(context: Context): Int {
        val random = Random()
        return random.nextInt(10)
    }


    fun getManager(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * 某天和最近时间
     *
     * @return
     */
    fun getSimpTime(time: String, serverTime: String): Long? {
        @SuppressLint("SimpleDateFormat") val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var diff: Long
        try {
            val date = sdf.parse(time)
            val date1 = sdf.parse(serverTime)
            diff = date.time - date1.time//这样得到的差值是微秒级别
            val days = diff / (1000 * 60 * 60 * 24)
            val hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
            val minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60)
            return days
        } catch (e: ParseException) {
            e.printStackTrace()
            diff = 0L
        }

        return diff
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(): String? {
        val formatter = SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ")
        val curDate = Date(System.currentTimeMillis())//获取当前时间
        return formatter.format(curDate)
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime1(): String? {
        val formatter = SimpleDateFormat("HH:mm")
        val curDate = Date(System.currentTimeMillis())//获取当前时间
        return formatter.format(curDate)
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime2(): String? {
        val formatter = SimpleDateFormat("HH:mm")
        val curDate = Date(System.currentTimeMillis())//获取当前时间
        return formatter.format(curDate)
    }

    @SuppressLint("SimpleDateFormat")
    fun date2TimeStamp(): String {
        try {
            val sdf = SimpleDateFormat("HH:mm")
            return (sdf.parse(getTime2()).time).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    @SuppressLint("ObsoleteSdkInt")
    fun getAppDetailSettingIntent(context: Context): Intent {
        val localIntent = Intent()
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
            localIntent.data = Uri.fromParts("package", context.packageName, null)
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.action = Intent.ACTION_VIEW
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails")
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.packageName)
        }
        return localIntent
    }

    fun startSocket(activity: Activity, intent: Intent) {
        Thread {
            val server: ServerSocket
            try {
                server = ServerSocket(8989)
                val socket = server.accept()
                //                String line;
                //                BufferedReader is = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
                //                //由系统标准输入设备构造BufferedReader对象
                //                line = is.readLine();
                //                if (line.contains( "success" )) {
                Handler(activity.mainLooper).post {
                    activity.startActivity(intent)
                    activity.finish()
                }
                //                }
                //                //在标准输出上打印从客户端读入的字符串
                //                is.close(); //关闭Socket输入流
                socket.close() //关闭Socket
                server.close() //关闭ServerSocket
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }


    @SuppressLint("MissingPermission")
            /**
             * 判断是否有卡槽
             *
             * @param context
             */
    fun getCardSlot(context: Context): Boolean {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var simSer: String? = null
        simSer = tm.simSerialNumber
        return TextUtils.isEmpty(simSer)
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    @Throws(PatternSyntaxException::class)
    fun isChinaPhoneLegal(str: String): Boolean {
        val regExp = "^1(2|3|4|5|6|7|8|9)\\d{9}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 返回市场。 如果获取失败返回defaultChannel
     *
     * @param context
     * @return
     */
    fun getChannel(context: Context, id_channle: Int): String {
        // 从apk中获取
        val mChannel = getChannelFromApk1(context, CHANNEL_KEY)
        return mChannle(id_channle, mChannel)
    }

    private fun getChannelFromApk1(context: Context, channelKey: String): String? {
        try {
            val appInfo = context.packageManager.getApplicationInfo(context.packageName,
                    PackageManager.GET_META_DATA)
            return appInfo.metaData.getString(channelKey)
        } catch (exception: Exception) {
            return null
        }

    }

    fun stringtoBitmap(string: ByteArray): Bitmap? {
        //将字符串转换成Bitmap类型
        var bitmap: Bitmap? = null
        try {
            val bitmapArray: ByteArray
            bitmapArray = Base64.decode(string, Base64.DEFAULT)
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bitmap
    }

    /**
     * 转二进制
     *
     * @param bm
     * @return
     */
    fun bitmap2Bytes(bm: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }

    /**
     * 渠道id
     */
    private fun mChannle(id_channle: Int, channle: String?): String {
        return if ("QD0111" == channle) {
            if (id_channle == 1) {
                "安卓应用市场" + "QD0111"
            } else {
                "QD0111"
            }
        } else if ("QD0026" == channle) {
            if (id_channle == 1) {
                "360手机助手" + "QD0026"
            } else {
                "QD0026"
            }
        } else if ("QD0029" == channle) {
            if (id_channle == 1) {
                "豌豆荚开发者中心" + "QD0029"
            } else {
                "QD0029"
            }
        } else if ("QD0107" == channle) {
            if (id_channle == 1) {
                "木蚂蚁开发者中心" + "QD0107"
            } else {
                "QD0107"
            }
        } else if ("QD0035" == channle) {
            if (id_channle == 1) {
                "小米应用商店" + "QD0035"
            } else {
                "QD0035"
            }
        } else if ("QD0085" == channle) {
            if (id_channle == 1) {
                "华为应用商店" + "QD0085"
            } else {
                "QD0085"
            }
        } else if ("QD0028" == channle) {
            if (id_channle == 1) {
                "PP助手开发者中心" + "QD0028"
            } else {
                "QD0028"
            }
        } else if ("QD0030" == channle) {
            if (id_channle == 1) {
                "安智开发者联盟" + "QD0030"
            } else {
                "QD0030"
            }
        } else if ("QD0021" == channle) {
            if (id_channle == 1) {
                "OPPO商店" + "QD0021"
            } else {
                "QD0021"
            }
        } else if ("QD0025" == channle) {
            if (id_channle == 1) {
                "魅族商店" + "QD0025"
            } else {
                "QD0025"
            }
        } else if ("QD0022" == channle) {
            if (id_channle == 1) {
                "VIVO商店" + "QD0022"
            } else {
                "QD0022"
            }
        } else if ("QD0018" == channle) {
            if (id_channle == 1) {
                "联通沃商店" + "QD0018"
            } else {
                "QD0018"
            }
        } else if ("QD0024" == channle) {
            if (id_channle == 1) {
                "搜狗手机助手" + "QD0024"
            } else {
                "QD0024"
            }
        } else if ("QD0108" == channle) {
            if (id_channle == 1) {
                "应用汇" + "QD0108"
            } else {
                "QD0108"
            }
        } else if ("QD0109" == channle) {
            if (id_channle == 1) {
                "酷派" + "QD0109"
            } else {
                "QD0109"
            }
        } else if ("QD0034" == channle) {
            if (id_channle == 1) {
                "应用宝" + "QD0034"
            } else {
                "QD0034"
            }
        } else if ("QD0031" == channle) {
            if (id_channle == 1) {
                "历趣市场" + "QD0031"
            } else {
                "QD0031"
            }
        } else if ("QD0112" == channle) {
            if (id_channle == 1) {
                "机锋开发者平台" + "QD0112"
            } else {
                "QD0112"
            }
        } else if ("QD0113" == channle) {
            if (id_channle == 1) {
                "自然" + "QD0113"
            } else {
                "QD0113"
            }
        } else if ("QD0106" == channle) {
            if (id_channle == 1) {
                "三星" + "QD0106"
            } else {
                "QD0106"
            }
        } else if ("QD0110" == channle) {
            if (id_channle == 1) {
                "神马" + "QD0110"
            } else {
                "QD0110"
            }
        } else if ("QD0023" == channle) {
            if (id_channle == 1) {
                "百度手机助手" + "QD0023"
            } else {
                "QD0023"
            }
        } else if ("QD0020" == channle) {
            if (id_channle == 1) {
                "sogou开发者" + "QD0020"
            } else {
                "QD0020"
            }
        } else if ("QD0019" == channle) {
            if (id_channle == 1) {
                "优亿市场" + "QD0019"
            } else {
                "QD0019"
            }
        } else if ("QD0016" == channle) {
            if (id_channle == 1) {
                "91手机商城发布中心" + "QD0016"
            } else {
                "QD0016"
            }
        } else if ("QD0033" == channle) {
            if (id_channle == 1) {
                "联想乐商店" + "QD0033"
            } else {
                "QD0033"
            }
        } else if ("QD0032" == channle) {
            if (id_channle == 1) {
                "锤子科技开发者" + "QD0032"
            } else {
                "QD0032"
            }
        } else if ("QD0012" == channle) {
            if (id_channle == 1) {
                "乐视" + "QD0012"
            } else {
                "QD0012"
            }
        } else if ("QD0115" == channle) {
            if (id_channle == 1) {
                "酷安" + "QD0115"
            } else {
                "QD0115"
            }
        } else if ("QD0116" == channle) {
            if (id_channle == 1) {
                "阿里平台" + "QD0116"
            } else {
                "QD0115"
            }
        } else if ("QD0007" == channle) {
            if (id_channle == 1) {
                "今日头条" + "QD0007"
            } else {
                "QD0007"
            }
        } else if ("QD0081" == channle) {
            if (id_channle == 1) {
                "广点通-分包-3" + "QD0081"
            } else {
                "QD0081"
            }
        } else if ("QD0080" == channle) {
            if (id_channle == 1) {
                "广点通-分包-2" + "QD0080"
            } else {
                "QD0080"
            }
        } else if ("QD0079" == channle) {
            if (id_channle == 1) {
                "广点通-分包-3" + "QD0079"
            } else {
                "QD0079"
            }
        } else if ("QD0073" == channle) {
            if (id_channle == 1) {
                "新浪A" + "QD0073"
            } else {
                "QD0073"
            }
        } else if ("QD0074" == channle) {
            if (id_channle == 1) {
                "新浪B" + "QD0074"
            } else {
                "QD0074"
            }
        } else if ("QD0075" == channle) {
            if (id_channle == 1) {
                "新浪C" + "QD0075"
            } else {
                "QD0075"
            }
        } else if ("QD0054" == channle) {
            if (id_channle == 1) {
                "应用宝-推广" + "QD0054"
            } else {
                "QD0054"
            }
        } else if ("QD0009" == channle) {
            if (id_channle == 1) {
                "今日头条" + "QD0009"
            } else {
                "QD0009"
            }
        } else if ("QD0099" == channle) {
            if (id_channle == 1) {
                "乐推-E" + "QD0099"
            } else {
                "QD0099"
            }
        } else if ("QD0098" == channle) {
            if (id_channle == 1) {
                "乐推-D" + "QD0098"
            } else {
                "QD0098"
            }
        } else if ("QD0097" == channle) {
            if (id_channle == 1) {
                "乐推-C" + "QD0097"
            } else {
                "QD0097"
            }
        } else if ("QD0096" == channle) {
            if (id_channle == 1) {
                "乐推-B" + "QD0096"
            } else {
                "QD0096"
            }
        } else if ("QD0095" == channle) {
            if (id_channle == 1) {
                "乐推-A" + "QD0095"
            } else {
                "QD0095"
            }
        } else if ("QD0057" == channle) {
            if (id_channle == 1) {
                "应用宝推广" + "QD0057"
            } else {
                "QD0057"
            }
        } else if ("QD0056" == channle) {
            if (id_channle == 1) {
                "应用宝推广" + "QD0056"
            } else {
                "QD0056"
            }
        } else {
            ""
        }
    }

    /**
     * 判断服务是否开启
     *
     * @return
     */

    fun isServiceRunning(context: Context, ServiceName: String): Boolean {
//        if (TextUtils.isEmpty(ServiceName)) return false
//
//        val activityManager: ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        val runningService = activityManager.getRunningServices(30) as ArrayList<ActivityManager.RunningServiceInfo>
//        for (index in runningService) {
//            if (index.service.className.toString().equals(ServiceName)) {
//                return true
//            }
//        }
//        return false
        var isRunning = false
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningService = activityManager.getRunningServices(200) as ArrayList<ActivityManager.RunningServiceInfo>
        if (runningService.size <= 0) {
            return false
        }
        for (index in runningService) {
            if (index.service.className.toString() == ServiceName) {
                isRunning = true
                break
            }
        }
        return isRunning
    }

    /**
     * 判断是否有SD卡
     *
     * @return
     */
    fun existSDCard(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    companion object {

        val instance: AppUtil
            get() = AppUtilHolder.INSTANCE


        /**
         * 获取屏幕分辨率
         *
         * @param context
         * @return
         */
        fun getScreenDispaly(context: Context): IntArray {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            var width = 0// 手机屏幕的宽度
            var height = 0
            width = windowManager.defaultDisplay.width
            height = windowManager.defaultDisplay.height// 手机屏幕的高度
            return intArrayOf(width, height)
        }

        private val CHANNEL_KEY = "UMENG_CHANNEL"
    }

}
