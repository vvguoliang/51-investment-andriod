package sp51.spotpass.com.spotpass.ui.utils

import com.tencent.tauth.UiError
import android.app.Activity
import org.json.JSONObject
import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import com.tencent.tauth.IUiListener



/**
 * @Time : 2018/6/15 no 18:11
 * @USER : vvguoliang
 * @File : BaseUIListener.java
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
class BaseUIListener : IUiListener {

    private var mContext: Context? = null
    private var mIsCaneled: Boolean = false
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                ON_COMPLETE -> {
                    val response = msg.obj as JSONObject
                    Util.showResultDialog(mContext!!, response.toString(), "onComplete")
//                    Util.dismissDialog()
                }
                ON_ERROR -> {
                    val e = msg.obj as UiError
                    Util.showResultDialog(mContext!!, "errorMsg:" + e.errorMessage
                            + "errorDetail:" + e.errorDetail, "onError")
//                    Util.dismissDialog()
                }
                ON_CANCEL -> Util.toastMessage((mContext as Activity?)!!, "onCancel")
            }
        }
    }

    constructor(mContext: Context) : super() {
        this.mContext = mContext
    }


    constructor(mContext: Context, mScope: String) : super() {
        this.mContext = mContext
    }

    fun cancel() {
        mIsCaneled = true
    }


    override fun onComplete(response: Any) {
        if (mIsCaneled) return
        val msg = mHandler.obtainMessage()
        msg.what = ON_COMPLETE
        msg.obj = response
        mHandler.sendMessage(msg)
    }

    override fun onError(e: UiError) {
        if (mIsCaneled) return
        val msg = mHandler.obtainMessage()
        msg.what = ON_ERROR
        msg.obj = e
        mHandler.sendMessage(msg)
    }

    override fun onCancel() {
        if (mIsCaneled) return
        val msg = mHandler.obtainMessage()
        msg.what = ON_CANCEL
        mHandler.sendMessage(msg)
    }

    fun getmContext(): Context? {
        return mContext
    }

    fun setmContext(mContext: Context) {
        this.mContext = mContext
    }

    companion object {
        private val ON_COMPLETE = 0
        private val ON_ERROR = 1
        private val ON_CANCEL = 2
    }

}
