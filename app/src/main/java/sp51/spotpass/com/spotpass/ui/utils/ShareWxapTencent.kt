package sp51.spotpass.com.spotpass.ui.utils

import sp51.spotpass.com.spotpass.ui.utils.Camera.BitmapUtils
import android.os.Bundle
import android.view.Gravity
import sp51.spotpass.com.spotpass.ui.view.Dialog.BottomDialog
import android.graphics.Bitmap
import android.app.Activity
import android.text.TextUtils
import android.view.View
import com.tencent.connect.share.QQShare
import com.tencent.connect.share.QzoneShare
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.tencent.tauth.Tencent
import com.tencent.wxop.stat.StatConfig
import sp51.spotpass.com.spotpass.R
import java.io.File


@Suppress("UNREACHABLE_CODE")
/**
 * @Time : 2018/6/15 no 15:28
 * @USER : vvguoliang
 * @File : ShareWxapTencent.java
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
class ShareWxapTencent(private val mActivity: Activity, url: String, mQqTitle: String, mQqSummary: String, mWxwebtitle: String,
                       mWxwebdescription: String, private val bitmap: Bitmap?, qqid: String, qqkey: String, wechatid: String, wechatkey: String) : View.OnClickListener {

    private var url: String? = ""

    private var qqid: String? = ""
    private var qqkey: String? = ""
    private var wechatid: String? = ""
    private var wechatkey: String? = ""
    /**
     * 微信分享
     */
    private var api: IWXAPI? = null

    private var mQqTitle = ""

    private var mQqSummary = ""

    private var mWxwebtitle = ""

    private var mWxwebdescription = ""

    private var mSXSDialog: BottomDialog? = null

    init {
        if (!TextUtils.isEmpty(url) || url != "null") {
            this.url = url
        } else {
            ToatUtils.showShort1(mActivity, "操作失败，请重试")
        }
        if (!TextUtils.isEmpty(mQqTitle) && mQqTitle != "null") {
            this.mQqTitle = mQqTitle
        }
        if (!TextUtils.isEmpty(mQqSummary) && mQqSummary != "null") {
            this.mQqSummary = mQqSummary
        }
        if (!TextUtils.isEmpty(mWxwebtitle) && mWxwebtitle != "null") {
            this.mWxwebtitle = mWxwebtitle
        }
        if (!TextUtils.isEmpty(mWxwebdescription) && mWxwebdescription != "null") {
            this.mWxwebdescription = mWxwebdescription
        }
        this.qqid = qqid
        this.qqkey = qqkey
        this.wechatid = wechatid
        this.wechatkey = wechatkey
        BitmapUtils.saveBitmapFile(bitmap!!, File(BitmapUtils.FILE_PATH+"/test.jpg"))
        getShare()
    }

    private fun getShare() {
        api = WXAPIFactory.createWXAPI(mActivity, wechatid, false)
        api!!.registerApp(wechatid)

        mTencent = Tencent.createInstance(qqid, mActivity)
        StatConfig.setAppKey(mActivity, qqkey)

        if (url == null || url == "") {
            ToatUtils.showShort1(mActivity, "操作失败，请重试")
            return
        }
        if (mSXSDialog == null) {
            mSXSDialog = BottomDialog(mActivity, R.layout.my_friends_botton_popupwindow)
        }
        mSXSDialog!!.window!!.setWindowAnimations(R.style.AnimBottom)
        mSXSDialog!!.window!!.setGravity(Gravity.BOTTOM)
        mSXSDialog!!.setWidthHeight(AppUtil.getScreenDispaly(mActivity)[0], 0)
        mSXSDialog!!.setOnClick(R.id.tab_rb_1, this)
        mSXSDialog!!.setOnClick(R.id.tab_rb_2, this)
        mSXSDialog!!.setOnClick(R.id.tab_rb_3, this)
        mSXSDialog!!.setOnClick(R.id.tab_rb_4, this)
        mSXSDialog!!.setOnClick(R.id.my_pop_cancel_button, this)
        if (!mActivity.isFinishing) {
            mSXSDialog!!.show()
        }
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.tab_rb_1// 微信
            -> shareToWXshare(0)
            R.id.tab_rb_2// 朋友圈
            -> shareToWXshare(1)
            R.id.tab_rb_3// QQ空间
            -> shareToQQzone()
            R.id.tab_rb_4// QQ
            -> onClickShare()
            else -> {
            }
        }
    }

    private fun onClickShare() {
        val params = Bundle()
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT)
        params.putString(QQShare.SHARE_TO_QQ_TITLE, mQqTitle)
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, mQqSummary)
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url)
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, BitmapUtils.FILE_PATH + "/test.jpg")
        //        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "");SHARE_TO_QQ_IMAGE_LOCAL_URL
        //        params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其他附加功能");
        if (mTencent != null)
            mTencent!!.shareToQQ(mActivity, params, sp51.spotpass.com.spotpass.ui.utils.BaseUIListener(mActivity))
    }

    private fun shareToQQzone() {
        val params = Bundle()
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT)
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, mQqTitle)
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, mQqSummary)
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url)
        val imageUrls = ArrayList<String>()
        imageUrls.add(BitmapUtils.FILE_PATH + "/jsy_ic_launcher.png")
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls)
        params.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN)
        if (mTencent != null)
            mTencent!!.shareToQzone(mActivity, params, sp51.spotpass.com.spotpass.ui.utils.BaseUIListener(mActivity))
    }

    private fun shareToWXshare(WX_FRIENDS: Int) {
        /*
         * 微信分享 （这里仅提供一个分享网页的示例，其它请参看官网示例代码）
         *
         * @param flag
         *            (0:分享到微信好友，1：分享到微信朋友圈)
         */
        val webpage = WXWebpageObject()
        webpage.webpageUrl = url
        val msg = WXMediaMessage(webpage)
        msg.title = mWxwebtitle
        msg.description = mWxwebdescription
        // 这里替换一张自己工程里的图片资源
        msg.setThumbImage(bitmap)
        val req = SendMessageToWX.Req()
        req.transaction = buildTransaction("webpage")
        req.message = msg
        req.scene = if (WX_FRIENDS == 0) SendMessageToWX.Req.WXSceneSession else SendMessageToWX.Req.WXSceneTimeline
        if (api != null)
            api!!.sendReq(req)
    }

    private fun buildTransaction(type: String?): String {
        return if (type == null) System.currentTimeMillis().toString() else type + System.currentTimeMillis()
    }

    companion object {

        /**
         * 腾讯分享
         */
        var mTencent: Tencent? = null
    }
}