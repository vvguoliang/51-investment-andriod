package sp51.spotpass.com.spotpass.ui.utils.Camera

import android.annotation.SuppressLint
import android.provider.MediaStore
import android.provider.DocumentsContract
import android.content.ContentUris
import android.annotation.TargetApi
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import android.media.ExifInterface
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.*


/**
 * @Time : 2018/5/31 no 17:20
 * @USER : vvguoliang
 * @File : BitmapUtils.java
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

object BitmapUtils {

    /**
     * 判断是否有SD卡
     *
     * @return
     */
    fun existSDCard(): Boolean {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
    }

    /**
     * 从文件中读取图片
     *
     * @param file
     * @return
     */
    fun getFileBitmap(file: File): Bitmap? {
        var fis: FileInputStream? = null
        var bitmap: Bitmap? = null
        try {
            //把图片转化为字节流
            fis = FileInputStream(file)
            //把流转化图片
            bitmap = BitmapFactory.decodeStream(fis)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } finally {
            try {
                if (fis != null) {
                    fis.close()//关闭流
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return bitmap
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    fun getBitmapDegree(path: String): Int {
        var degree = 0
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            val exifInterface = ExifInterface(path)
            // 获取图片的旋转信息
            val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL)
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return degree
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    fun rotateBitmapByDegree(bm: Bitmap, degree: Int): Bitmap {
        var returnBm: Bitmap? = null

        // 根据旋转角度，生成旋转矩阵
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.width, bm.height, matrix, true)
        } catch (ignored: OutOfMemoryError) {
        }

        if (returnBm == null) {
            returnBm = bm
        }
        if (bm != returnBm) {
            bm.recycle()
        }
        return returnBm
    }

    /**
     * 删除文档
     *
     * @param file
     */
    fun deleteFile(file: File?): Boolean {
        try {
            if (file != null) {
                if (FileUtils.deleteFile(file.absolutePath)) {
                    AppUtil.instance.mImageFile = null
                    Log.d("删除文件", "true")
                    return true
                } else {
                    return false
                }
            } else {
                Log.d("删除文件路径为空", "false")
                return false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("删除文件", "false")
            return false
        }

    }


    /**
     * 保存图片
     *
     * @param bitmap
     * @param file
     */
    fun saveBitmapFile(bitmap: Bitmap, file: File): Boolean {
        try {
            val bos = BufferedOutputStream(FileOutputStream(file))
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            bos.flush()
            bos.close()
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }

    }


    object FileUtils {

        /**
         * 删除单个文件
         *
         * @param filePath 被删除文件的文件名
         * @return 文件删除成功返回true，否则返回false
         */
        fun deleteFile(filePath: String): Boolean {
            val file = File(filePath)
            return file.isFile && file.exists() && file.delete()
        }


        /**
         * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
         *
         * @param context
         * @param imageUri
         * @author yaoxing
         * @date 2014-10-12
         */
        @TargetApi(19)
        fun getImageAbsolutePath(context: Context, imageUri: Uri?): String? {
            if (context == null || imageUri == null)
                return null
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
                if (isExternalStorageDocument(imageUri)) {
                    val docId = DocumentsContract.getDocumentId(imageUri)
                    val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        return "${Environment.getExternalStorageDirectory()}/${split[1]}"
                    }
                } else if (isDownloadsDocument(imageUri)) {
                    val id = DocumentsContract.getDocumentId(imageUri)
                    val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id))
                    return getDataColumn(context, contentUri, null, null)
                } else if (isMediaDocument(imageUri)) {
                    val docId = DocumentsContract.getDocumentId(imageUri)
                    val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type = split[0]
                    var contentUri: Uri? = null
                    if ("image" == type) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    } else if ("video" == type) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    } else if ("audio" == type) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }
                    val selection = MediaStore.Images.Media._ID + "=?"
                    val selectionArgs = arrayOf(split[1])
                    return getDataColumn(context, contentUri, selection, selectionArgs)
                }
            } // MediaStore (and general)
            else if ("content".equals(imageUri.scheme, ignoreCase = true)) {
                // Return the remote address
                return if (isGooglePhotosUri(imageUri)) imageUri.lastPathSegment else getDataColumn(context, imageUri, null, null)
            } else if ("file".equals(imageUri.scheme, ignoreCase = true)) {
                return imageUri.path
            }// File
            return null
        }

        @SuppressLint("Recycle")
        private fun getDataColumn(context: Context, uri: Uri?, selection: String?, selectionArgs: Array<String>?): String? {
            var cursor: Cursor? = null
            val column = MediaStore.Images.Media.DATA
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
         * @param uri The Uri to check.
         * @return Whether the Uri authority is ExternalStorageProvider.
         */
        private fun isExternalStorageDocument(uri: Uri): Boolean {
            return "com.android.externalstorage.documents" == uri.authority
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is DownloadsProvider.
         */
        private fun isDownloadsDocument(uri: Uri): Boolean {
            return "com.android.providers.downloads.documents" == uri.authority
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is MediaProvider.
         */
        private fun isMediaDocument(uri: Uri): Boolean {
            return "com.android.providers.media.documents" == uri.authority
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is Google Photos.
         */
        fun isGooglePhotosUri(uri: Uri): Boolean {
            return "com.google.android.apps.photos.content" == uri.authority
        }
    }

    /**
     * 文件保存的路径
     */
    val FILE_PATH = Environment.getExternalStorageDirectory().path

    /**
     * 从本地SD卡获取网络图片，key是url的MD5值
     *
     * @return
     */
    fun getBitmap(bitmap: Bitmap) {
        try {
            val fileName = "jsy_ic_launcher.png"
            val file = File("$FILE_PATH/$fileName")
            if (!file.exists()) {
                file.mkdir()
            }
            file.createNewFile()
            val fOut = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fOut)
            fOut.flush()
            fOut.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}