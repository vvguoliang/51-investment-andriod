@file:Suppress("DEPRECATION")

package sp51.spotpass.com.spotpass.ui.utils

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.app.Activity
import android.app.AlertDialog
import android.widget.Toast
import android.content.DialogInterface
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.util.Log
import junit.framework.Assert
import sp51.spotpass.com.spotpass.ui.activity.RegisterActivity
import sp51.spotpass.com.spotpass.ui.view.Dialog.NickNameDialogBuilder
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * @Time : 2018/6/15 no 18:12
 * @USER : vvguoliang
 * @File : Util.java
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
object Util {


    private var mToast: Toast? = null

    /*
     * 16进制数字字符集
     */
    private val hexString = "0123456789ABCDEF"

    private val MAX_DECODE_PICTURE_SIZE = 1920 * 1440

    fun bmpToByteArray(bmp: Bitmap, needRecycle: Boolean): ByteArray {
        val output = ByteArrayOutputStream()
        bmp.compress(CompressFormat.PNG, 100, output)
        if (needRecycle) {
            bmp.recycle()
        }

        val result = output.toByteArray()
        try {
            output.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    fun registerA(activity: Activity) {
        NickNameDialogBuilder(activity).cancelText("取消").message("现在登录").messageeditext_text(false).sureText("确定").setCancelOnClickListener { }.setSureOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            intent.putExtra("type", 0)
            activity.startActivityForResult(intent, 209)
        }.title("未登录").build().show()
    }

    fun registerA1(context: Context) {
        NickNameDialogBuilder(context).cancelText("取消").message("现在登录").messageeditext_text(false).sureText("确定").setCancelOnClickListener { }.setSureOnClickListener {
            val intent = Intent(context, RegisterActivity::class.java)
            intent.putExtra("type", 0)
            context.startActivity(intent)
        }.title("未登录").build().show()
    }


    @SuppressLint("SimpleDateFormat")
            /**
             * 两个时间相差距离多少天多少小时多少分多少秒
             * @param str1 时间参数 1 格式：1990-01-01 12:00:00
             * @param str2 时间参数 2 格式：2009-01-01 12:00:00
             * @return String 返回值为：xx天xx小时xx分xx秒
             */
    fun getDistanceTime(str1: String, str2: String): String {
        val df = SimpleDateFormat("HH:mm")
        val one: Date
        val two: Date
        var day: Long = 0
        var hour: Long = 0
        var min: Long = 0
        var sec: Long = 0
        val str3 = getDateToString(str2.toLong(), "HH:mm")
        try {
            one = df.parse(str1)
            two = df.parse(str3)
            val time1 = one.time
            val time2 = two.time
            val diff: Long
            if (time1 < time2) {
                diff = time2 - time1
            } else {
                diff = time1 - time2
            }
            day = diff / (24 * 60 * 60 * 1000)
            hour = diff / (60 * 60 * 1000) - day * 24
            min = diff / (60 * 1000) - day * 24 * 60 - hour * 60
            sec = diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return "${formatTurnSecond("$hour:$min:$sec")}"
//        return day.toString() + "天" + hour + "小时" + min + "分" + sec + "秒"
    }

    fun getRandom(rand: Float): Float {
        val min = rand - 20
        val max = rand + 20
        val random = Random()
        return random.nextInt(max.toInt()) % (max - min + 1) + min
    }

    /*
    * 将时分秒转为秒数
    * */
    fun formatTurnSecond(time: String): Long {
        val index1 = time.indexOf(":")
        val index2 = time.indexOf(":", index1 + 1)
        val hh = Integer.parseInt(time.substring(0, index1))
        val mi = Integer.parseInt(time.substring(index1 + 1, index2))
        val ss = Integer.parseInt(time.substring(index2 + 1))

        Log.e("", "formatTurnSecond: 时间== " + hh * 60 * 60 + mi * 60 + ss)
        return (hh * 60 * 60 + mi * 60).toLong()
    }


    @SuppressLint("SimpleDateFormat")
    fun getDateToString(milSecond: Long, pattern: String): String {
        val date = Date(milSecond)
        val format = SimpleDateFormat(pattern)
        return format.format(date)
    }

    /**
     * 以最省内存的方式读取图片
     */
    fun readBitmap(path: String): Bitmap? {
        try {
            val stream = FileInputStream(File(path + "test.jpg"))
            val opts = BitmapFactory.Options()
            opts.inSampleSize = 8
            opts.inPurgeable = true
            opts.inInputShareable = true
            return BitmapFactory.decodeStream(stream, null, opts)
        } catch (e: OutOfMemoryError) {
            return null
        } catch (e: Exception) {
            return null
        }
    }

    fun extractThumbNail(path: String?, height: Int, width: Int, crop: Boolean): Bitmap? {
        Assert.assertTrue(path != null && path != "" && height > 0 && width > 0)

        val options = BitmapFactory.Options()

        try {
            options.inJustDecodeBounds = true
            val tmp = BitmapFactory.decodeFile(path, options)
            tmp?.recycle()

            val beY = options.outHeight * 1.0 / height
            val beX = options.outWidth * 1.0 / width
            options.inSampleSize = (if (crop) if (beY > beX) beX else beY else if (beY < beX) beX else beY).toInt()
            if (options.inSampleSize <= 1) {
                options.inSampleSize = 1
            }

            // NOTE: out of memory error
            while (options.outHeight * options.outWidth / options.inSampleSize > MAX_DECODE_PICTURE_SIZE) {
                options.inSampleSize++
            }

            var newHeight = height
            var newWidth = width
            if (crop) {
                if (beY > beX) {
                    newHeight = (newWidth.toDouble() * 1.0 * options.outHeight.toDouble() / options.outWidth).toInt()
                } else {
                    newWidth = (newHeight.toDouble() * 1.0 * options.outWidth.toDouble() / options.outHeight).toInt()
                }
            } else {
                if (beY < beX) {
                    newHeight = (newWidth.toDouble() * 1.0 * options.outHeight.toDouble() / options.outWidth).toInt()
                } else {
                    newWidth = (newHeight.toDouble() * 1.0 * options.outWidth.toDouble() / options.outHeight).toInt()
                }
            }

            options.inJustDecodeBounds = false

            var bm: Bitmap? = BitmapFactory.decodeFile(path, options) ?: return null

            val scale = Bitmap.createScaledBitmap(bm!!, newWidth, newHeight, true)
            if (scale != null) {
                bm.recycle()
                bm = scale
            }

            if (crop) {
                val cropped = Bitmap.createBitmap(bm, bm.width - width shr 1, bm.height - height shr 1, width, height)
                        ?: return bm

                bm.recycle()
                bm = cropped
            }
            return bm

        } catch (e: OutOfMemoryError) {
        }

        return null
    }

    fun showResultDialog(context: Context, msg: String?,
                         title: String) {
        if (msg == null) return
        val rmsg = msg.replace(",", "\n")
        AlertDialog.Builder(context).setTitle(title).setMessage(rmsg)
                .setNegativeButton("知道了", null).create().show()
    }


    fun showConfirmCancelDialog(context: Context,
                                title: String, message: String,
                                posListener: DialogInterface.OnClickListener): AlertDialog {
        val dlg = AlertDialog.Builder(context).setMessage(message)
                .setPositiveButton("确认", posListener)
                .setNegativeButton("取消", null).create()
        dlg.setCanceledOnTouchOutside(false)
        dlg.show()
        return dlg
    }

    /**
     * 打印消息并且用Toast显示消息
     *
     * @param activity
     * @param message
     * @param logLevel
     * 填d, w, e分别代表debug, warn, error; 默认是debug
     */
    @JvmOverloads
    fun toastMessage(activity: Activity,
                     message: String, logLevel: String? = null) {
        if ("w" == logLevel) {
        } else if ("e" == logLevel) {
        } else {
        }
        activity.runOnUiThread {
            // TODO Auto-generated method stub
            if (mToast != null) {
                mToast!!.cancel()
                mToast = null
            }
            mToast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
            mToast!!.show()
        }
    }

    /**
     * 根据一个网络连接(String)获取bitmap图像
     *
     * @param imageUri
     * @return
     * @throws MalformedURLException
     */
    fun getbitmap(imageUri: String): Bitmap? {
        // 显示网络上的图片
        var bitmap: Bitmap?
        try {
            val myFileUrl = URL(imageUri)
            val conn = myFileUrl
                    .openConnection() as HttpURLConnection
            conn.doInput = true
            conn.connect()
            val `is` = conn.inputStream
            bitmap = BitmapFactory.decodeStream(`is`)
            `is`.close()

        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
            bitmap = null
        } catch (e: IOException) {
            e.printStackTrace()
            bitmap = null
        }

        return bitmap
    }


    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context
     * The context.
     * @param uri
     * The Uri to query.
     * @param selection
     * (Optional) Filter used in the query.
     * @param selectionArgs
     * (Optional) Selection arguments used in the query.
     * [url=home.php?mod=space&uid=7300]@return[/url] The value of
     * the _data column, which is typically a file path.
     */
    fun getDataColumn(context: Context, uri: Uri?, selection: String?, selectionArgs: Array<String>?): String? {

        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)

        try {
            cursor = context.contentResolver.query(uri, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            if (cursor != null)
                cursor.close()
        }
        return null
    }

    /**
     * @param uri
     * The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri
     * The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri
     * The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    /**
     * @param uri
     * The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }

}
/**
 * 打印消息并且用Toast显示消息
 *
 * @param activity
 * @param message
 * 填d, w, e分别代表debug, warn, error; 默认是debug
 */
