package sp51.spotpass.com.spotpass.wxapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils

@SuppressLint("Registered")
/**
 * @Time : 2018/6/15 no 18:17
 * @USER : vvguoliang
 * @File : WXEntryActivity1.java
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
class WXEntryActivity : FragmentActivity(), IWXAPIEventHandler {

    private var api: IWXAPI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        api = WXAPIFactory.createWXAPI(this, "wx43b913bd07fb3715", false)
        api!!.handleIntent(getIntent(), this)
    }

    protected override fun onStart() {
        // TODO Auto-generated method stub
        super.onStart()
        api = WXAPIFactory.createWXAPI(this, "wx43b913bd07fb3715", false)
        api!!.handleIntent(getIntent(), this)
    }

    protected fun findViewById() {
        // TODO Auto-generated method stub

    }

    protected fun initView() {
        // TODO Auto-generated method stub

    }

    // 微信发送请求到第三方应用时，会回调到该方法
    override fun onReq(baseReq: BaseReq) {
        when (baseReq.getType()) {
            ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX -> ToatUtils.showShort1(this@WXEntryActivity, "===COMMAND_GETMESSAGE_FROM_WX===")
            ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX -> ToatUtils.showShort1(this@WXEntryActivity, "===COMMAND_SHOWMESSAGE_FROM_WX===")
            else -> {
            }
        }
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    override fun onResp(baseResp: BaseResp) {
        when (baseResp.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                // if(api!=null)
                // wxresp.onSuccess();
                ToatUtils.showShort1(this, "发送成功")
                finish()
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                // if(wxresp!=null)
                // wxresp.onFail();
                ToatUtils.showShort1(this, "分享取消")
                finish()
            }
            BaseResp.ErrCode.ERR_AUTH_DENIED -> {
                ToatUtils.showShort1(this, "分享被拒绝")
                finish()
            }
            BaseResp.ErrCode.ERR_UNSUPPORT -> {
                ToatUtils.showShort1(this, "不支持格式")
                finish()
            }
            BaseResp.ErrCode.ERR_SENT_FAILED -> {
                ToatUtils.showShort1(this, "发送失败")
                finish()
            }
            else -> {
                ToatUtils.showShort1(this, "分享返回")
                finish()
            }
        }
    }
}