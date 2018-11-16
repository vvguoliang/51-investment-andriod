package sp51.spotpass.com.spotpass.ui.utils.Camera

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Handler
import java.io.File


/**
 * @Time : 2018/5/31 no 17:11
 * @USER : vvguoliang
 * @File : UserCenterModel.java
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
/**
 * Created by vvguoliang on 2017/7/1.
 *
 * 权限接口调用
 */

interface UserCenterModel {

    /**
     * 创建img文件
     *
     * @return
     */
    val imagefile: File?

    /**
     * 拍照
     *
     * @param context
     */
    fun getFileByPhotograph(context: Context)

    /**
     * 相册
     *
     * @param context
     */
    fun getFileByPhotoAlbum(context: Context)

    /**
     * 开始拍照
     *
     * @param context
     */
    fun startPhotograph(context: Context)

    /**
     * 调用相册
     *
     * @param context
     */
    fun startPhotoAlbum(context: Context)


    /**
     * 剪裁图片
     *
     * @param activity
     */
    fun startClip(activity: Activity, file: File)

    /**
     * API24 以上调用
     *
     * @param context
     * @param imageFile
     * @return
     */
    fun getImageContentUri(context: Context, imageFile: File): Uri

    /**
     * 版本更新
     *
     * @param context
     */
    fun getUpdata(context: Context, url: String)

    /**
     * 写入和读取
     * @param context
     */
    fun getReadWRite(context: Context, mHandler: Handler)

}