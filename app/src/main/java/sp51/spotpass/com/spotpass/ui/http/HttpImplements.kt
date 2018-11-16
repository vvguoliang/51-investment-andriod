package sp51.spotpass.com.spotpass.ui.http

import android.content.Context

/**
 * @Time : 2018/4/16 no 下午1:18
 * @USER : vvguoliang
 * @File : HttpImplements.java
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
class HttpImplements private constructor() {

    /**
     * 单例对象实例
     */
    companion object {
        fun get(): HttpImplements {
            return Inner.httpImplements
        }
    }

    private object Inner {
        val httpImplements = HttpImplements()
    }

    private val Hasp = "http://pv.sohu.com/cityjson?ie=utf-8"

    val Https51 = "http://51-1256641882.cosbj.myqcloud.com/"

        val HttpS = "http://www.dcdsinternet.com/"
//    val HttpS = "http://192.168.1.13/"
//    val HttpS = "http://www.youjifarm.cn/"

    private val getPhoneCode = "api/v1/Register/getPhoneCode"

    private val publicReq = "api/v1/GateWay/publicReq"

    private val publicReqlogin = "api/v1/GateWay/publicReq"

    private val publicWeChatPayment = "api/v1/GateWay/publicReq"

    private val publicopenGranaryProvideRelief = "api/v1/GateWay/publicReq"

    private val publicQryAccount = "api/v1/GateWay/publicReq"

    private val publicBankList = "api/v1/Account/bankList"

    private val publicRecommend = "api/v1/Home/recommend"

    private val publicLastPrice = "api/v1/Exchang/lastPrice"

    private val publicTodayPrice = "api/v1/Exchang/todayPrice"

    private val GETVIDEOLIST = "api/v1/curriculum/get-video-list"

    private val GETPROFITLIST = "api/v1/profit/get-profit-list"

    private val GETCIRCLESSLIST = "api/v1/circless/get-circless-list"

    private val GETINDEXSHOW = "api/v1/circless/get-index-show"

    private val GETCIRCLESSINFO = "api/v1/circless/get-circless-info"

    private val GETCIRCLESSCOMMENTS = "api/v1/circless/get-circless-comments"

    private val GETTOPCAROUSELS = "api/v1/exchange-carousels/get-top-carousels"

    private val GETCOLLECTLIST = "api/v1/curriculum/get-collect-list"

    private val MARKET = "api/v1/Exchang/market"

    private val REFRESHTOKEN = "api/auth/refresh-token"

    private val CIRCLESSLIKES = "api/v1/circless/circless-likes"

    private val CIRCLESSCOMMENTS = "api/v1/circless/circless-comments"

    private val currentPendingOrder = "api/v1/Exchang/currentPendingOrder"

    private val allPendingOrder = "api/v1/Exchang/allPendingOrder"

    private val PendingOrder = "api/v1/Exchang/pendingOrder"

    private val doSign = "api/v1/Account/doSign"

    private val getvoucherslist = "api/v1/vouchers/get-vouchers-list"

    private val signLog = "api/v1/Account/signLog"

    private val upload = "api/img-upload"

    private val aboutUs = "api/v1/Home/aboutUs"

    private val message = "api/v1/Account/message"

    private val messageDetail = "api/v1/Account/messageDetail"

    private val rechargAmount = "api/v1/Exchang/rechargAmount"

    private val guestServiceList = "api/v1/Home/guestServiceList"

    private val resetpassword = "api/v1/user/reset-password"

    private val normalQuestionsList = "api/v1/Home/normalQuestionsList"

    private val normalQuestionsDetail = "api/v1/Home/normalQuestionsDetail"

    private val profitNews = "api/v1/Home/profitNews"

    private val tradingRules = "api/v1/Home/tradingRules"

    private val beginerDetail = "api/v1/Home/beginerDetail"

    private val guideToUse = "api/v1/Home/guideToUse"

    private val investmentIntroduction = "/api/v1/Home/investmentIntroduction"

    private val vouchersExchange = "api/v1/Account/vouchersExchange"

    private val guestService = "api/v1/Account/guestService"

    private val editAvatar = "api/v1/Account/editAvatar"

    private val img_upload = "api/img-upload"

    private val editNickName = "api/v1/Account/editNickName"

    private val appVersion = "api/v1/Home/appVersion"

    private val info = "api/v1/share/info"

    private val cancelPendingOrder = "api/v2/order/cancel"

    private val exchangehistory = "api/v1/Account/exchange-history"

    private val qrystock = "api/v2/exchange/qrystock"

    private val qrykchart = "api/v2/exchange/qrykchart"

    private val qrykprice = "api/v2/exchange/qrykprice"

    private val pending = "api/v2/order/pending"

    /**
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
    private fun HttpImplements() = Unit

    /* 首页分类 */
    private val HttpUSER_HOME = "$HttpS/front/home"

    /* 客服 */
    private val HttpUSER_kefu = "$HttpS/customer/kefu_list"

    /* 登入+注册*/
    private val HttpUSER_publicReq = "$HttpS$publicReq"

    private val HttpUSER_QRYQUOTETRDTIME = "$HttpS$publicReq"

    private val HttpUSER_removeCard = "$HttpS$publicReq"

    private val HttpUSER_qryTicket = "$HttpS$publicReq"

    private val HttpUSER_qryHold = "$HttpS$publicReq"

    private val HttpUSER_Close = "$HttpS$publicReq"

    private val HttpUSER_upacpRechargeIPS = "$HttpS$publicReq"

    private val HttpUSER_upacpRecharge = "$HttpS$publicReq"

    private val HttpUSER_rechargeYunJu = "$HttpS$publicReq"

    private val HttpUSER_publicReqlogin = "$HttpS$publicReqlogin"

    private val HttpUSER_publicWeChatPayment = "$HttpS$publicWeChatPayment"

    private val HttpUSER_openGranaryProvideRelief = "$HttpS$publicopenGranaryProvideRelief"

    private val HttpUSER_CODE = "$HttpS$getPhoneCode"

    private val HttpUSER_QryAccount = "$HttpS$publicQryAccount"

    private val HttpUSER_BindCard = "$HttpS$publicReq"

    private val HttpUSER_cashApplyWithoutVcode = "$HttpS$publicReq"

    private val HttpUSER_cashApply = "$HttpS$publicReq"
//    private val HttpUSER_BindCard = "http://mini.gllsce.com:8001/api/GateWay/publicReq"

    private val HttpUSER_QryBankCardList = "$HttpS$publicReq"

    private val HttpUSER_QryKChart = "$HttpS$publicReq"

    private val HttpUSER_LastPrice = "$HttpS$publicLastPrice"

    private val HttpUSER_TodayPrice = "$HttpS$publicTodayPrice"

    private val HttpUSER_QryOrderH = "$HttpS$publicQryAccount"

    private val HttpUSER_GetYYOutCashList = "$HttpS$publicQryAccount"

    private val HttpUSER_GetRechargeList = "$HttpS$publicQryAccount"

    private val HttpUSER_LOGOUT = "$HttpS$publicQryAccount"

    private val HttpUSER_GETVIDEOLIST = "$HttpS$GETVIDEOLIST"

    private val HttpUSER_GETPROFITLIST = "$HttpS$GETPROFITLIST"

    private val HttpUSER_GETCIRCLESSLIST = "$HttpS$GETCIRCLESSLIST"

    private val HttpUSER_GETINDEXSHOW = "$HttpS$GETINDEXSHOW"

    private val HttpUSER_GETCIRCLESSINFO = "$HttpS$GETCIRCLESSINFO"

    private val HttpUSER_GETCIRCLESSCOMMENTS = "$HttpS$GETCIRCLESSCOMMENTS"

    private val HttpUSER_GETTOPCAROUSELS = "$HttpS$GETTOPCAROUSELS"

    private val HttpUSER_GETCOLLECTLIST = "$HttpS$GETCOLLECTLIST"

    private val HttpUSER_MARKET = "$HttpS$MARKET"

    private val HttpUSER_REFRESHTOKEN = "$HttpS$REFRESHTOKEN"

    private val HttpUSER_CIRCLESSLIKES = "$HttpS$CIRCLESSLIKES"

    private val HttpUSER_CIRCLESSCOMMENTS = "$HttpS$CIRCLESSCOMMENTS"

    private val HttpUSER_currentPendingOrder = "$HttpS$currentPendingOrder"

    private val HttpUSER_allPendingOrder = "$HttpS$allPendingOrder"

    private val HttpUSER_PendingOrder = "$HttpS$PendingOrder"

    private val HttpUSER_doSign = "$HttpS$doSign"

    private val HttpUSER_getvoucherslist = "$HttpS$getvoucherslist"

    private val HttpUSER_signLog = "$HttpS$signLog"

    private val HttpUSER_upload = "$HttpS$upload"

    private val HttpUSER_aboutUs = "$HttpS$aboutUs"

    private val HttpUSER_message = "$HttpS$message"

    private val HttpUSER_messageDetail = "$HttpS$messageDetail"

    private val HttpUSER_rechargAmount = "$HttpS$rechargAmount"

    private val HttpUSER_guestServiceList = "$HttpS$guestServiceList"

    private val HttpUSER_resetpassword = "$HttpS$resetpassword"

    private val HttpUSER_normalQuestionsList = "$HttpS$normalQuestionsList"

    private val HttpUSER_normalQuestionsDetail = "$HttpS$normalQuestionsDetail"

    private val HttpUSER_profitNews = "$HttpS$profitNews"

    private val HttpUSER_tradingRules = "$HttpS$tradingRules"

    private val HttpUSER_beginerDetail = "$HttpS$beginerDetail"

    private val HttpUSER_guideToUse = "$HttpS$guideToUse"

    private val HttpUSER_investmentIntroduction = "$HttpS$investmentIntroduction"

    private val HttpUSER_vouchersExchange = "$HttpS$vouchersExchange"

    private val HttpUSER_guestService = "$HttpS$guestService"

    private val HttpUSER_editAvatar = "$HttpS$editAvatar"

    private val HttpUSER_img_upload = "$HttpS$img_upload"

    private val HttpUSER_editNickName = "$HttpS$editNickName"

    private val HttpUSER_appVersion = "$HttpS$appVersion"

    private val HttpUSER_info = "$HttpS$info"

    private val HttpUSER_cancelPendingOrder = "$HttpS$cancelPendingOrder"

    private val HttpUSER_exchangehistory = "$HttpS$exchangehistory"

    private val HttpUSER_qrystock = "$HttpS$qrystock"

    private val HttpUSER_qrykchart = "$HttpS$qrykchart"

    private val HttpUSER_qrykprice = "$HttpS$qrykprice"

    private val HttpUSER_pending = "$HttpS$pending"
    /**
     * 银行卡列表
     */
    private val HttpUSER_BankList = "$HttpS$publicBankList"

    /**
     * 银行卡列表
     */
    private val HttpUSER_Recommend = "$HttpS$publicRecommend"


    fun getHttp(context: Context, publicString: String): String {
        var publicS = ""
        when (publicString) {
            "HOME" -> publicS = HttpUSER_HOME
            "KEFU" -> publicS = HttpUSER_kefu
            "publicReq" -> publicS = HttpUSER_publicReq
            "publicReqlogin" -> publicS = HttpUSER_publicReqlogin
            "getPhoneCode" -> publicS = HttpUSER_CODE
            "Hasp" -> publicS = Hasp
            "publicWeChatPayment" -> publicS = HttpUSER_publicWeChatPayment
            "openGranaryProvideRelief" -> publicS = HttpUSER_openGranaryProvideRelief
            "QryAccount" -> publicS = HttpUSER_QryAccount
            "BankList" -> publicS = HttpUSER_BankList
            "bindCard" -> publicS = HttpUSER_BindCard
            "QryBankCardList" -> publicS = HttpUSER_QryBankCardList
            "Recommend" -> publicS = HttpUSER_Recommend
            "LastPrice" -> publicS = HttpUSER_LastPrice
            "TodayPrice" -> publicS = HttpUSER_TodayPrice
            "QryKChart" -> publicS = HttpUSER_QryKChart
            "QryOrderH" -> publicS = HttpUSER_QryOrderH
            "GetYYOutCashList" -> publicS = HttpUSER_GetYYOutCashList
            "GetRechargeList" -> publicS = HttpUSER_GetRechargeList
            "QryStock" -> publicS = HttpUSER_GetRechargeList
            "GETVIDEOLIST" -> publicS = HttpUSER_GETVIDEOLIST
            "GETPROFITLIST" -> publicS = HttpUSER_GETPROFITLIST
            "GETCIRCLESSLIST" -> publicS = HttpUSER_GETCIRCLESSLIST
            "GETINDEXSHOW" -> publicS = HttpUSER_GETINDEXSHOW
            "GETCIRCLESSINFO" -> publicS = HttpUSER_GETCIRCLESSINFO
            "GETCIRCLESSCOMMENTS" -> publicS = HttpUSER_GETCIRCLESSCOMMENTS
            "GETTOPCAROUSELS" -> publicS = HttpUSER_GETTOPCAROUSELS
            "GETCOLLECTLIST" -> publicS = HttpUSER_GETCOLLECTLIST
            "MARKET" -> publicS = HttpUSER_MARKET
            "LOGOUT" -> publicS = HttpUSER_LOGOUT
            "REFRESHTOKEN" -> publicS = HttpUSER_REFRESHTOKEN
            "CIRCLESSLIKES" -> publicS = HttpUSER_CIRCLESSLIKES
            "CIRCLESSCOMMENTS" -> publicS = HttpUSER_CIRCLESSCOMMENTS
            "QRYQUOTETRDTIME" -> publicS = HttpUSER_QRYQUOTETRDTIME
            "currentPendingOrder" -> publicS = HttpUSER_currentPendingOrder
            "allPendingOrder" -> publicS = HttpUSER_allPendingOrder
            "doSign" -> publicS = HttpUSER_doSign
            "getvoucherslist" -> publicS = HttpUSER_getvoucherslist
            "signLog" -> publicS = HttpUSER_signLog
            "upload" -> publicS = HttpUSER_upload
            "aboutUs" -> publicS = HttpUSER_aboutUs
            "message" -> publicS = HttpUSER_message
            "messageDetail" -> publicS = HttpUSER_messageDetail
            "rechargAmount" -> publicS = HttpUSER_rechargAmount
            "removeCard" -> publicS = HttpUSER_removeCard
            "guestServiceList" -> publicS = HttpUSER_guestServiceList
            "qryTicket" -> publicS = HttpUSER_qryTicket
            "qryHold" -> publicS = HttpUSER_qryHold
            "Close" -> publicS = HttpUSER_Close
            "resetpassword" -> publicS = HttpUSER_resetpassword
            "upacpRecharge" -> publicS = HttpUSER_upacpRecharge
            "upacpRechargeIPS" -> publicS = HttpUSER_upacpRechargeIPS
            "rechargeYunJu" -> publicS = HttpUSER_rechargeYunJu
            "normalQuestionsList" -> publicS = HttpUSER_normalQuestionsList
            "normalQuestionsDetail" -> publicS = HttpUSER_normalQuestionsDetail
            "cashApplyWithoutVcode" -> publicS = HttpUSER_cashApplyWithoutVcode
            "cashApply" -> publicS = HttpUSER_cashApply
            "profitNews" -> publicS = HttpUSER_profitNews
            "tradingRules" -> publicS = HttpUSER_tradingRules
            "beginerDetail" -> publicS = HttpUSER_beginerDetail
            "guideToUse" -> publicS = HttpUSER_guideToUse
            "investmentIntroduction" -> publicS = HttpUSER_investmentIntroduction
            "vouchersExchange" -> publicS = HttpUSER_vouchersExchange
            "guestService" -> publicS = HttpUSER_guestService
            "editAvatar" -> publicS = HttpUSER_editAvatar
            "img_upload" -> publicS = HttpUSER_img_upload
            "editNickName" -> publicS = HttpUSER_editNickName
            "appVersion" -> publicS = HttpUSER_appVersion
            "info" -> publicS = HttpUSER_info
            "Httpwallstreetcn" -> publicS = Httpwallstreetcn
//            "PendingOrder" -> publicS = HttpUSER_PendingOrder
            "cancelPendingOrder" -> publicS = HttpUSER_cancelPendingOrder
            "exchangehistory" -> publicS = HttpUSER_exchangehistory
            "qrystock" -> publicS = HttpUSER_qrystock
            "qrykchart" -> publicS = HttpUSER_qrykchart
            "qrykprice" -> publicS = HttpUSER_qrykprice
            "PendingOrder" -> publicS = HttpUSER_pending
        }
        return publicS //+ SPUtils.getInstance(context, "token")
    }


    val Httpwallstreetcn = "https://forexdata.wallstreetcn.com/real?en_prod_code=USDOLLARINDEX,EURUSD,GBPUSD,USDJPY,USDCHF,USDCAD,AUDUSD,NZDUSD,USDCNY,USDCNH&fields=prod_name,preclose_px,price_precision,open_px,high_px,low_px,update_time,last_px,px_change,px_change_rate,market_type,trade_status"
    val real_list = "https://forexdata.wallstreetcn.com/real_list?fields=prod_name,preclose_px,open_px,high_px,low_px,update_time,last_px,px_change,px_change_rate,price_precision,market_type,trade_status&type=commodity&page=0&limit=34"
}