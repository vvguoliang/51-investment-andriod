package sp51.spotpass.com.spotpass.ui.utils.Camera

import android.Manifest
import android.graphics.BitmapFactory
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.app.Activity
import android.graphics.Bitmap
import android.content.Intent
import android.provider.MediaStore
import android.content.ContentValues
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.widget.Toast
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.util.Log
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.ui.service.UpdataService
import sp51.spotpass.com.spotpass.ui.utils.Camera.BitmapUtils.existSDCard
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


/**
 * @Time : 2018/5/31 no 17:09
 * @USER : vvguoliang
 * @File : UserCenterRealize.java
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
 * 授权+拍照和剪切
 */
class UserCenterRealize : UserCenterModel {

    override val imagefile: File?
        get() {
            var mediaStorageDir: File? = null
            val mediaFile: File
            try {
                mediaStorageDir = File(Environment.getExternalStorageDirectory().toString() + "/IMAGE/")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (!mediaStorageDir!!.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    return null
                }
            }
            @SuppressLint("SimpleDateFormat") val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            mediaFile = File(mediaStorageDir.path + File.separator + "IMG_" + timeStamp + ".jpg")
            Log.d("创建文件成功", mediaFile.toString())
            return mediaFile

        }

    private val sxs_icon = R.mipmap.ic_launcher
    private var thumb: Bitmap? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun getFileByPhotograph(context: Context) {
        val activity = context as Activity
        if (AppUtil.instance.mBuildVersion!! >= 23) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //申请相机权限
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA),
                        AppUtil.instance.MY_PERMISSIONS_REQUEST_CAMERA)
            } else {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请读SD权限
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            AppUtil.instance.MY_PERMISSIONS_REQUEST_READ_SD)
                } else {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        //申请写SD权限
                        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                AppUtil.instance.MY_PERMISSIONS_REQUEST_WRITE_SK)
                    } else {
                        startPhotograph(context)
                    }
                }
            }
        } else {
            //拍照
            startPhotograph(context)
        }

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun getFileByPhotoAlbum(context: Context) {
        val activity = context as Activity
        if (AppUtil.instance.mBuildVersion!! >= 23) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请读SD权限
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        AppUtil.instance.MY_PERMISSIONS_REQUEST_READ_SD_PHOTOALBUM)
            } else {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请写SD权限
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            AppUtil.instance.MY_PERMISSIONS_REQUEST_WRITE_SK_PHOTOALBUM)
                } else {
                    startPhotoAlbum(context)
                }
            }
        } else {
            startPhotoAlbum(context)
        }
    }

    override fun startPhotoAlbum(context: Context) {
        if (!existSDCard()) {
            Toast.makeText(context, "未检测到SD卡", Toast.LENGTH_SHORT).show()
            return
        }
        val a = context as Activity
        val getAlbum = Intent(Intent.ACTION_GET_CONTENT)
        getAlbum.type = AppUtil.instance.IMAGE_TYPE
        a.startActivityForResult(getAlbum, AppUtil.instance.LOAD_IMAGE_REQUEST)
    }

    override fun startPhotograph(context: Context) {
        if (!existSDCard()) {
            Toast.makeText(context, "未检测到SD卡", Toast.LENGTH_SHORT).show()
            return
        }
        val activity = context as Activity
        if (AppUtil.instance.mBuildVersion!! < 24) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            AppUtil.instance.mImageFile = imagefile
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(AppUtil.instance.mImageFile))
            activity.startActivityForResult(intent, AppUtil.instance.CAPTURE_IMAGE_REQUEST)
        } else {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val contentValues = ContentValues(1)
            AppUtil.instance.mImageFile = imagefile
            contentValues.put(MediaStore.Images.Media.DATA, AppUtil.instance.mImageFile!!.absolutePath)
            val uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            activity.startActivityForResult(intent, AppUtil.instance.CAPTURE_IMAGE_REQUEST)
        }
    }


    override fun startClip(activity: Activity, file: File) {
        if (null == file) {
            return
        }
        AppUtil.instance.mOutFile = imagefile
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(getImageContentUri(activity, file), AppUtil.instance.IMAGE_TYPE)
        intent.putExtra("crop", "true")
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        intent.putExtra("outputX", 180)
        intent.putExtra("outputY", 180)
        intent.putExtra("scale", true)
        intent.putExtra("return-data", false)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(AppUtil.instance.mOutFile))
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
        intent.putExtra("noFaceDetection", true)
        activity.startActivityForResult(intent, AppUtil.instance.CLIP_IMAGE_REQUEST)
    }

    override fun getImageContentUri(context: Context, imageFile: File): Uri {
        val filePath = imageFile.absolutePath
        val uri: Uri?
        @SuppressLint("Recycle") val cursor = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Images.Media._ID),
                MediaStore.Images.Media.DATA + "=? ",
                arrayOf<String>(filePath), null)

        if (cursor != null && cursor.moveToFirst()) {
            val id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID))
            val baseUri = Uri.parse("content://media/external/images/media")
            uri = Uri.withAppendedPath(baseUri, "" + id)
        } else {
            if (imageFile.exists()) {
                val values = ContentValues()
                values.put(MediaStore.Images.Media.DATA, filePath)
                uri = context.contentResolver.insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            } else {
                uri = null
            }
        }
        return uri!!
    }


    override fun getUpdata(context: Context, url: String) {
        val activity = context as Activity
        if (AppUtil.instance.mBuildVersion!! >= 23) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED&&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                //申请联系人权限  允许程序读取用户联系人数据
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE),
                        AppUtil.instance.MY_PERMISSIONS_REQUEST_WRITE_SK)
            } else {
//                val intent = Intent(context, UpdataService::class.java)
//                intent.putExtra("url", url)
//                context.startService(intent)
            }
        } else {
//            val intent = Intent(context, UpdataService::class.java)
//            intent.putExtra("url", url)
//            context.startService(intent)
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun getReadWRite(context: Context, mHandler: Handler) {
        val activity = context as Activity
        if (AppUtil.instance.mBuildVersion!! >= 23) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请联系人权限  允许程序读取用户联系人数据
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        AppUtil.instance.MY_PERMISSIONS_PHONE_READWRITE)
            } else {
                thumb = BitmapFactory.decodeResource(context.getResources(), sxs_icon)
                BitmapUtils.getBitmap(thumb!!)
                val message = Message()
                message.obj = thumb
                mHandler.sendMessage(message)
            }
        } else {
            thumb = BitmapFactory.decodeResource(context.getResources(), sxs_icon)
            BitmapUtils.getBitmap(thumb!!)
            val message = Message()
            message.obj = thumb
            mHandler.sendMessage(message)
        }

    }
}