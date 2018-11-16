package sp51.spotpass.com.spotpass.ui.http

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import java.io.File

@SuppressLint("ParcelCreator")
/**
 * @Time : 2018/5/17 no 14:47
 * @USER : vvguoliang
 * @File : HttpALl.java
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
class HttpALl : HttpALlManger {

    override fun setqrykprice(qtecode: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicGet(context, mHandler, "${HttpImplements.get().getHttp(context, "qrykprice")}?qtecode=$qtecode", "qrykprice")
    }

    override fun setqrykchart(qtecode: String, qryKChartCode: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicGet(context, mHandler, "${HttpImplements.get().getHttp(context, "qrykchart")}?qtecode=$qtecode&qryKChartCode=$qryKChartCode", "qrykchart")
    }

    override fun setqrystock(qtecode: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setLastPrice(qtecode), mHandler, HttpImplements.get().getHttp(context, "qrystock"), "qrystock")
    }

    override fun exchangehistory(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicGet(context, mHandler, "${HttpImplements.get().getHttp(context, "exchangehistory")}?page=$page", "exchangehistory")
    }

    override fun setCancelPendingOrder(id: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setcancelOrder(id), mHandler, HttpImplements.get().getHttp(context, "cancelPendingOrder"), "cancelPendingOrder")
    }

    override fun setPendingOrder(price: String, stkcode: String, bsflag: Long, orderprice: String, orderqty: Long, losspoints: String, profitpoints: String, orderfrom: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setPendingOrder(price, stkcode, bsflag, orderprice, orderqty, losspoints, profitpoints, orderfrom), mHandler, HttpImplements.get().getHttp(context, "PendingOrder"), "PendingOrder")
    }

    override fun setinfo(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map, mHandler, HttpImplements.get().getHttp(context, "info"), "info")
    }

    override fun setappVersion(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setRecommend(), mHandler, HttpImplements.get().getHttp(context, "appVersion"), "appVersion")
    }

    override fun seteditNickName(nickname: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().seteditNickName(nickname), mHandler, HttpImplements.get().getHttp(context, "editNickName"), "editNickName")
    }

    override fun seteditAvatar(editAvatar: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().seteditAvatar(editAvatar), mHandler, HttpImplements.get().getHttp(context, "editAvatar"), "editAvatar")
    }

    override fun setimg_upload(is_face: String, name1: File, mHandler: Handler, context: Context) {
        HttpRequest.get().setuploadAsync(context, mHandler, HttpImplements.get().getHttp(context, "img_upload"), "img_upload", is_face, name1)
    }

    override fun setguestService(content: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setguestService(content), mHandler, HttpImplements.get().getHttp(context, "guestService"), "guestService")
    }

    override fun setvouchersExchange(price: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setvouchersExchange(price), mHandler, HttpImplements.get().getHttp(context, "vouchersExchange"), "vouchersExchange")
    }


    override fun setsignLog(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicGet(context, mHandler, "${HttpImplements.get().getHttp(context, "signLog")}?page=$page", "signLog")
    }

    override fun setinvestmentIntroduction(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicGet(context, mHandler, "${HttpImplements.get().getHttp(context, "investmentIntroduction")}?page=$page", "investmentIntroduction")
    }

    override fun setguideToUse(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicGet(context, mHandler, "${HttpImplements.get().getHttp(context, "guideToUse")}?page=$page", "guideToUse")
    }

    override fun setbeginerDetail(id: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setCIRCLESSLIKES(id), mHandler, HttpImplements.get().getHttp(context, "beginerDetail"), "beginerDetail")
    }

    override fun settradingRules(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicGet(context, mHandler, "${HttpImplements.get().getHttp(context, "tradingRules")}?page=$page", "tradingRules")
    }

    override fun setprofitNews(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map, mHandler, HttpImplements.get().getHttp(context, "profitNews"), "profitNews")
    }

    override fun setcashApply(account: String, capitalId: String, sessionId: String, pwd: String, cashAmt: String, cardNo: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setcashApply(account, capitalId, sessionId, pwd, cashAmt, cardNo),
                mHandler, HttpImplements.get().getHttp(context, "cashApply"), "cashApply")
    }

    override fun setcashApplyWithoutVcode(account: String, capitalId: String, sessionId: String, pwd: String, rechargeAmt: String, cardNo: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setsetcashApplyWithoutVcode(account, capitalId, sessionId, pwd, rechargeAmt, cardNo),
                mHandler, HttpImplements.get().getHttp(context, "cashApplyWithoutVcode"), "cashApplyWithoutVcode")
    }

    override fun setnormalQuestionsDetail(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setnormalQuestionsDetail(page),
                mHandler, HttpImplements.get().getHttp(context, "normalQuestionsDetail"), "normalQuestionsDetail")
    }

    override fun setnormalQuestionsList(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicGet(context,
                mHandler, "${HttpImplements.get().getHttp(context, "normalQuestionsList")}?page=$page", "normalQuestionsList")
    }

    override fun setupacpRecharge(account: String, capitalId: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setupacpRecharge(account, capitalId, sessionId, rechargeAmt, cardNo, redirectUrl),
                mHandler, HttpImplements.get().getHttp(context, "upacpRecharge"), "upacpRecharge")
    }

    override fun setupacpRechargeIPS(account: String, capitalId: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setupacpRechargeIPS(account, capitalId, sessionId, rechargeAmt, cardNo, redirectUrl),
                mHandler, HttpImplements.get().getHttp(context, "upacpRechargeIPS"), "upacpRechargeIPS")
    }

    override fun setrechargeYunJu(account: String, yunju_banktype: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setrechargeYunJu(account, yunju_banktype, sessionId, rechargeAmt, cardNo, redirectUrl),
                mHandler, HttpImplements.get().getHttp(context, "rechargeYunJu"), "rechargeYunJu")
    }

    override fun setresetpassword(mobile: String, captcha: String, new_password: String, confirm_password: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setresetpassword(mobile, captcha, new_password, confirm_password),
                mHandler, HttpImplements.get().getHttp(context, "resetpassword"), "resetpassword")
    }

    override fun setClose(account: String, capitalId: String, sessionId: String, stkcode: String, bsflag: String, orderno: String,
                          orderprice: String, orderqty: String, ticketflag: String, orderfrom: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setClose(account, capitalId, sessionId, stkcode, bsflag, orderno, orderprice,
                orderqty, ticketflag, orderfrom),
                mHandler, HttpImplements.get().getHttp(context, "Close"), "Close")
    }

    override fun setqryHold(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setqryHold(),
                mHandler, HttpImplements.get().getHttp(context, "qryHold"), "qryHold")
    }

    override fun setqryTicket(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setqryTicket(),
                mHandler, HttpImplements.get().getHttp(context, "qryTicket"), "qryTicket")
    }

    override fun setguestServiceList(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setmessage(page),
                mHandler, HttpImplements.get().getHttp(context, "guestServiceList"), "guestServiceList")
    }

    override fun setremoveCard(account: String, sessionId: String, cardNo: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setremoveCard(account, sessionId, cardNo),
                mHandler, HttpImplements.get().getHttp(context, "removeCard"), "removeCard")
    }

    override fun setrechargAmount(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setRecommend(),
                mHandler, HttpImplements.get().getHttp(context, "rechargAmount"), "rechargAmount")
    }

    override fun setmessageDetail(id: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setmessageDetail(id),
                mHandler, HttpImplements.get().getHttp(context, "messageDetail"), "messageDetail")
    }

    override fun setmessage(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setmessage(page),
                mHandler, HttpImplements.get().getHttp(context, "message"), "message")
    }

    override fun setaboutUs(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setRecommend(),
                mHandler, HttpImplements.get().getHttp(context, "aboutUs"), "aboutUs")
    }

    override fun setupload(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setRecommend(),
                mHandler, HttpImplements.get().getHttp(context, "upload"), "upload")
    }

    override fun setgetvoucherslist(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map,
                mHandler, HttpImplements.get().getHttp(context, "getvoucherslist"), "getvoucherslist")
    }

    override fun setdoSign(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setRecommend(),
                mHandler, HttpImplements.get().getHttp(context, "doSign"), "doSign")
    }

    override fun setallPendingOrder(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicGet(context,
                mHandler, "${HttpImplements.get().getHttp(context, "allPendingOrder")}?page=$page", "allPendingOrder")
    }

    override fun setcurrentPendingOrder(page: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicGet(context,
                mHandler, "${HttpImplements.get().getHttp(context, "currentPendingOrder")}?page=$page", "currentPendingOrder")
    }

    override fun setQRYQUOTETRDTIME(cmdType: String, qtecode: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setQRYQUOTETRDTIME(cmdType, qtecode),
                mHandler, HttpImplements.get().getHttp(context, "QRYQUOTETRDTIME"), "QRYQUOTETRDTIME")
    }

    override fun setCIRCLESSCOMMENTS(id: Long, content: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setCIRCLESSCOMMENTS(id, content),
                mHandler, HttpImplements.get().getHttp(context, "CIRCLESSCOMMENTS"), "CIRCLESSCOMMENTS")
    }

    val map: Map<String, Any> = HashMap()

    override fun setCIRCLESSLIKES(id: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setCIRCLESSLIKES(id),
                mHandler, HttpImplements.get().getHttp(context, "CIRCLESSLIKES"), "CIRCLESSLIKES")
    }

    override fun setGETCIRCLESSLIST1(name: String, url: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map, mHandler, url, name)
    }

    override fun setREFRESHTOKEN(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map,
                mHandler, HttpImplements.get().getHttp(context, "REFRESHTOKEN"), "REFRESHTOKEN")
    }

    override fun setLOGOUT(cmdType: String, account: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setLOGOUT(cmdType, account),
                mHandler, HttpImplements.get().getHttp(context, "LOGOUT"), "LOGOUT")
    }

    override fun setMARKET(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setRecommend(),
                mHandler, HttpImplements.get().getHttp(context, "MARKET"), "MARKET")
    }

    override fun setGETCOLLECTLIST(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map,
                mHandler, HttpImplements.get().getHttp(context, "GETCOLLECTLIST"), "GETCOLLECTLIST")
    }

    override fun setGETTOPCAROUSELS(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map,
                mHandler, HttpImplements.get().getHttp(context, "GETTOPCAROUSELS"), "GETTOPCAROUSELS")
    }

    override fun setGETCIRCLESSCOMMENTS(id: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setCIRCLESSLIKES(id),
                mHandler, HttpImplements.get().getHttp(context, "GETCIRCLESSCOMMENTS"), "GETCIRCLESSCOMMENTS")
    }

    override fun setGETCIRCLESSINFO(id: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setCIRCLESSLIKES(id),
                mHandler, HttpImplements.get().getHttp(context, "GETCIRCLESSINFO"), "GETCIRCLESSINFO")//AfferentDataHttpMap.get().setCIRCLESSLIKES(id)
    }

    override fun setGETINDEXSHOW(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map,
                mHandler, HttpImplements.get().getHttp(context, "GETINDEXSHOW"), "GETINDEXSHOW")
    }

    override fun setGETCIRCLESSLIST(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map,
                mHandler, HttpImplements.get().getHttp(context, "GETCIRCLESSLIST"), "GETCIRCLESSLIST")
    }

    override fun setGETPROFITLIST(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map,
                mHandler, HttpImplements.get().getHttp(context, "GETPROFITLIST"), "GETPROFITLIST")
    }

    override fun setGETVIDEOLIST(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, map,
                mHandler, HttpImplements.get().getHttp(context, "GETVIDEOLIST"), "GETVIDEOLIST")
    }

    override fun setQryStock(cmdType: String, qtecode: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setQryStock(qtecode, cmdType),
                mHandler, HttpImplements.get().getHttp(context, "QryStock"), "QryStock")
    }

    override fun setGetYYOutCashList(cmdType: String, account: String, sessionId: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setGetYYOutCashList(cmdType, account, sessionId),
                mHandler, HttpImplements.get().getHttp(context, "GetYYOutCashList"), "GetYYOutCashList")
    }

    override fun setGetRechargeList(cmdType: String, account: String, sessionId: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setGetRechargeList(cmdType, account, sessionId),
                mHandler, HttpImplements.get().getHttp(context, "GetRechargeList"), "GetRechargeList")
    }

    override fun setQryOrderH(cmdType: String, account: String, capitalId: String, sessionId: String, indexDate: String, indexOrderNo: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setQryOrderH(cmdType, account, capitalId, sessionId, indexDate, indexOrderNo),
                mHandler, HttpImplements.get().getHttp(context, "QryOrderH"), "QryOrderH")
    }

    override fun setQryKChart(cmdType: String, qryKChartCode: String, qtecode: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setQryKChart(cmdType, qryKChartCode, qtecode),
                mHandler, HttpImplements.get().getHttp(context, "QryKChart"), "QryKChart")
    }

    override fun setTodayPrice(qtecode: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setLastPrice(qtecode),
                mHandler, HttpImplements.get().getHttp(context, "TodayPrice"), "TodayPrice")
    }

    override fun setLastPrice(qtecode: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setLastPrice(qtecode),
                mHandler, HttpImplements.get().getHttp(context, "LastPrice"), "LastPrice")
    }

    override fun setRecommend(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setRecommend(),
                mHandler, HttpImplements.get().getHttp(context, "Recommend"), "Recommend")
    }

    override fun setQryBankCardList(cmdType: String, account: String, sessionId: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setQryBankCardList(cmdType, account, sessionId),
                mHandler, HttpImplements.get().getHttp(context, "QryBankCardList"), "QryBankCardList")
    }

    override fun setBindCard(cmdType: String, account: String, sessionId: String, bankId: String, cardNo: String, cerName: String, cerNo: String, bankName: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setBindCard(cmdType, account, sessionId, bankId, cardNo, cerName, cerNo, bankName),
                mHandler, HttpImplements.get().getHttp(context, "bindCard"), "bindCard")
    }

    override fun setBankList(mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setBankList(),
                mHandler, HttpImplements.get().getHttp(context, "BankList"), "BankList")
    }

    override fun setQryAccount(cmdType: String, account: String, capitalId: String, sessionId: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setQryAccount(cmdType, account, capitalId, sessionId),
                mHandler, HttpImplements.get().getHttp(context, "QryAccount"), "QryAccount")
    }

    override fun setopenGranaryProvideRelief(cmdType: String, account: String, capitalId: String, sessionId: String, stkcode: String, bsflag: String,
                                             orderprice: String, orderqty: String, losspoints: String, profitpoints: String, ticketflag: String,
                                             orderfrom: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setopenGranaryProvideRelief(cmdType, account, capitalId, sessionId,
                stkcode, bsflag, orderprice, orderqty, losspoints, profitpoints, ticketflag, orderfrom),
                mHandler, HttpImplements.get().getHttp(context, "openGranaryProvideRelief"), "openGranaryProvideRelief")
    }

    override fun setWeChatPayment(cmdType: String, account: String, capitalId: String, sessionId: String, rechargeAmt: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setWeChatPayment(cmdType, account, capitalId, sessionId, rechargeAmt),
                mHandler, HttpImplements.get().getHttp(context, "publicWeChatPayment"), "publicWeChatPayment")
    }

    /**
     * IP
     */
    override fun setHasp(mHandler: Handler, context: Context) {
        val map: Map<String, Any> = HashMap()
        HttpRequest.get().setPublicOther(context, map, mHandler, HttpImplements.get().getHttp(context, "Hasp"), "Hasp")
    }

    /**
     * 登录
     */
    override fun setPublicReqlogin(account: String, pwd: String, ip: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setPublicReqlogin(account, pwd, ip),
                mHandler, HttpImplements.get().getHttp(context, "publicReqlogin"), "publicReqlogin")
    }

    /**
     * 注册
     */
    override fun setPublicReq(account: String, pwd: String, mbid: String, qbid: String, vcode: String, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setPublicReq(account, pwd, mbid, qbid, vcode),
                mHandler, HttpImplements.get().getHttp(context, "publicReq"), "publicReq")
    }

    override fun setPhoneCode(phone: Long, mHandler: Handler, context: Context) {
        HttpRequest.get().setPublicPost(context, AfferentDataHttpMap.get().setVcode(phone),
                mHandler, HttpImplements.get().getHttp(context, "getPhoneCode"), "getPhoneCode")
    }

    /**
     * 单例对象实例
     */
    companion object {
        fun get(): HttpALl {
            return Inner.httpALl
        }
    }

    private object Inner {
        val httpALl = HttpALl()
    }

}