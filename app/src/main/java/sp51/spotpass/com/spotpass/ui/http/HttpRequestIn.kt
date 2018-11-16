package sp51.spotpass.com.spotpass.ui.http

import android.content.Context
import android.os.Handler
import java.io.File

/**
 * @Time : 2018/4/17 no 下午4:21
 * @USER : vvguoliang
 * @File : HttpRequestIn.kt
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
interface HttpRequestIn {

    fun setPublicPost(context: Context, map: Map<String, Any>, mHnadler: Handler, url: String, name: String)

    fun setPublicGet(context: Context, mHnadler: Handler, url: String, name: String)

    fun setPublicGetAuthorization(context: Context, mHnadler: Handler, url: String, name: String)

    fun setuploadAsync(context: Context, mHnadler: Handler, url: String, name: String, is_face: String, name1: File)

    fun setPublicOther(context: Context, map: Map<String, Any>, mHnadler: Handler, url: String, name: String)

    fun setHOME(context: Context, mHnadler: Handler, result: String)

    fun setLOGIN(context: Context, mHnadler: Handler, result: String)

    fun setPublicReq(context: Context, mHnadler: Handler, result: String)

    fun setpublicReqlogin(context: Context, mHnadler: Handler, result: String)

    fun setHasp(context: Context, mHnadler: Handler, result: String)

    fun setWeChatPayment(context: Context, mHnadler: Handler, result: String)

    fun setOpenGranaryProvideRelief(context: Context, mHnadler: Handler, result: String)

    fun setQryAccount(context: Context, mHnadler: Handler, result: String)

    fun setBankList(context: Context, mHnadler: Handler, result: String)

    fun setBindCard(context: Context, mHnadler: Handler, result: String)

    fun setQryBankCardList(context: Context, mHnadler: Handler, result: String)

    fun setRecommend(context: Context, mHnadler: Handler, result: String)

    fun setLastPrice(context: Context, mHnadler: Handler, result: String)

    fun TodayPrice(context: Context, mHnadler: Handler, result: String)

    fun setQryKChart(context: Context, mHnadler: Handler, result: String)

    fun setQryOrderH(context: Context, mHnadler: Handler, result: String)

    fun setGetYYOutCashList(context: Context, mHnadler: Handler, result: String)

    fun setGetRechargeList(context: Context, mHnadler: Handler, result: String)

    fun setQryStock(context: Context, mHnadler: Handler, result: String)

    fun setGETVIDEOLIST(context: Context, mHnadler: Handler, result: String)

    fun setGETPROFITLIST(context: Context, mHnadler: Handler, result: String)

    fun setGETCIRCLESSLIST(context: Context, mHnadler: Handler, result: String)

    fun setGETINDEXSHOW(context: Context, mHnadler: Handler, result: String)

    fun setGETCIRCLESSINFO(context: Context, mHnadler: Handler, result: String)

    fun setGETCIRCLESSCOMMENTS(context: Context, mHnadler: Handler, result: String)

    fun setGETTOPCAROUSELS(context: Context, mHnadler: Handler, result: String)

    fun setGETCOLLECTLIST(context: Context, mHnadler: Handler, result: String)

    fun setMARKET(context: Context, mHnadler: Handler, result: String)

    fun setLOGOUT(context: Context, mHnadler: Handler, result: String)

    fun setREFRESHTOKEN(context: Context, mHnadler: Handler, result: String)

    fun setCIRCLESSLIKES(context: Context, mHnadler: Handler, result: String)

    fun setCIRCLESSCOMMENTS(context: Context, mHnadler: Handler, result: String)

    fun setQRYQUOTETRDTIME(context: Context, mHnadler: Handler, result: String)

    fun setcurrentPendingOrder(context: Context, mHnadler: Handler, result: String)

    fun setallPendingOrder(context: Context, mHnadler: Handler, result: String)

    fun setdoSign(context: Context, mHnadler: Handler, result: String)

    fun setsignLog(context: Context, mHnadler: Handler, result: String)

    fun setgetvoucherslist(context: Context, mHnadler: Handler, result: String)

    fun setupload(context: Context, mHnadler: Handler, result: String)

    fun setmessage(context: Context, mHnadler: Handler, result: String)

    fun setrechargAmount(context: Context, mHnadler: Handler, result: String)

    fun setremoveCard(context: Context, mHnadler: Handler, result: String)

    fun setguestServiceListd(context: Context, mHnadler: Handler, result: String)

    fun setqryTicket(context: Context, mHnadler: Handler, result: String)

    fun setqryHold(context: Context, mHnadler: Handler, result: String)

    fun setClose(context: Context, mHnadler: Handler, result: String)

    fun setresetpassword(context: Context, mHnadler: Handler, result: String)

    fun setupacpRecharge(context: Context, mHnadler: Handler, result: String)

    fun setupacpRechargeIPS(context: Context, mHnadler: Handler, result: String)

    fun setrechargeYunJu(context: Context, mHnadler: Handler, result: String)

    fun setnormalQuestionsList(context: Context, mHnadler: Handler, result: String)

    fun setnormalQuestionsDetail(context: Context, mHnadler: Handler, result: String)

    fun setcashApplyWithoutVcode(context: Context, mHnadler: Handler, result: String)

    fun setcashApply(context: Context, mHnadler: Handler, result: String)

    fun setprofitNews(context: Context, mHnadler: Handler, result: String)

    fun settradingRules(context: Context, mHnadler: Handler, result: String)

    fun setbeginerDetail(context: Context, mHnadler: Handler, result: String)

    fun setguideToUse(context: Context, mHnadler: Handler, result: String)

    fun setinvestmentIntroduction(context: Context, mHnadler: Handler, result: String)

    fun setvouchersExchange(context: Context, mHnadler: Handler, result: String)

    fun setguestService(context: Context, mHnadler: Handler, result: String)

    fun setmessageDetail(context: Context, mHnadler: Handler, result: String)

    fun seteditAvatar(context: Context, mHnadler: Handler, result: String)

    fun setimg_upload(context: Context, mHnadler: Handler, result: String)

    fun seteditNickName(context: Context, mHnadler: Handler, result: String)

    fun setappVersion(context: Context, mHnadler: Handler, result: String)

    fun setinfo(context: Context, mHnadler: Handler, result: String)

    fun Httpwallstreetcn(context: Context, mHnadler: Handler, result: String)

    fun PendingOrder(context: Context, mHnadler: Handler, result: String)

    fun setCancelPendingOrder(context: Context, mHnadler: Handler, result: String)

    fun setexchangehistory(context: Context, mHnadler: Handler, result: String)

    fun setqrystock(context: Context, mHnadler: Handler, result: String)

    fun setqrykchart(context: Context, mHnadler: Handler, result: String)

    fun setqrykprice(context: Context, mHnadler: Handler, result: String)

}