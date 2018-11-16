package sp51.spotpass.com.spotpass.ui.view.Dialog

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.view.Gravity
import android.view.View
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.ui.utils.Camera.UserCenterRealize


/**
 * @Time : 2018/5/31 no 17:57
 * @USER : vvguoliang
 * @File : ShowDialog.java
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
 * Created by vvguoliang on 2017/6/30.
 * 公共类 Diaog
 */

class ShowDialog
/**
 * private的构造函数用于避免外界直接使用new来实例化对象
 */
private constructor() {

    /**
     * 单例对象实例
     */
    private object ShowDialogHolder {
        internal val INSTANCE = ShowDialog()
    }

    /**
     * readResolve方法应对单例对象被序列化时候
     */
    private fun readResolve(): Any {
        return instance
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
// 提示对话框方法
    fun showDialog(context: Context, mHandler: Handler, userCenterRealize: UserCenterRealize) {
        val sxsDialog = BottomDialog(context, R.layout.buttom_dialog)
        sxsDialog.window!!.setWindowAnimations(R.style.AnimBottom)
        sxsDialog.setWidthHeight(AppUtil.instance.Dispay(context)[0], 0)
        sxsDialog.window!!.setGravity(Gravity.BOTTOM)

        sxsDialog.setOnClick(R.id.btn_pick_photo1, View.OnClickListener {
            userCenterRealize.getFileByPhotograph(context)
            sxsDialog.dismiss()
        })
        sxsDialog.setOnClick(R.id.btn_pick_photo2, View.OnClickListener {
            userCenterRealize.getFileByPhotoAlbum(context)
            sxsDialog.dismiss()
        })
        sxsDialog.setOnClick(R.id.btn_cancel, View.OnClickListener {
            //取消
            sxsDialog.dismiss()
        })
        sxsDialog.show()
    }

    companion object {

        val instance: ShowDialog
            get() = ShowDialogHolder.INSTANCE
    }

}
