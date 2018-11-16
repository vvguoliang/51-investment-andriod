package sp51.spotpass.com.spotpass.ui.http

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.Log
import com.google.gson.Gson
import okhttp3.Request
import org.json.JSONObject
import sp51.spotpass.com.spotpass.ui.baseEntity.*
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import java.io.File
import java.io.IOException


/**
 * @Time : 2018/4/16 no 下午1:25
 * @USER : vvguoliang
 * @File : HttpRequest.java
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
@SuppressWarnings("JavaDoc")
class HttpRequest private constructor() : HttpRequestIn {

    override fun setqrykprice(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, QryKprice::class.java)
        val message = Message()
        message.what = 1008
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setqrykchart(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, QryKchartr1::class.java)
        val message = Message()
        message.what = 1010
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setqrystock(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, qrystock1::class.java)
        val message = Message()
        message.what = 1011
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setexchangehistory(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, Exchangehistory::class.java)
        val message = Message()
        message.obj = gosn
        message.what = 1058
        mHnadler.handleMessage(message)
    }

    override fun setCancelPendingOrder(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1057
        mHnadler.handleMessage(message)
    }

    override fun PendingOrder(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1056
        mHnadler.handleMessage(message)
    }

    override fun Httpwallstreetcn(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, Httpwallstreetcn::class.java)
        val message = Message()
        message.obj = gosn
        message.what = 1053
        mHnadler.handleMessage(message)
    }

    override fun setinfo(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, InFo::class.java)
        val message = Message()
        message.obj = gosn
        message.what = 1052
        mHnadler.handleMessage(message)
    }

    override fun setappVersion(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, AppVersion::class.java)
        val message = Message()
        message.what = 1051
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun seteditNickName(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1050
        mHnadler.handleMessage(message)
    }

    override fun setuploadAsync(context: Context, mHnadler: Handler, url: String, name: String, is_face: String, name1: File) {
        OkHttpManager.uploadAsync(context, url, name, is_face, name1, object : DataCallBack {
            override fun requestFailure(request: Request, name: String, e: IOException) {
                val message = Message()
                message.what = 205   // 链接超时
                mHnadler.handleMessage(message)
            }

            override fun requestSuccess(result: String, name: String) {
                val json = JSONObject(result)
                if (HttpOnekey.get().setBoolen(context, json.getString("return_code"), json.getString("return_msg"), mHnadler)!!) {
                    setimg_upload(context, mHnadler, result)
                }
            }
        })
    }

    override fun setimg_upload(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, Img_Uplod::class.java)
        val message = Message()
        message.what = 1048
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun seteditAvatar(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1049
        mHnadler.handleMessage(message)
    }

    override fun setmessageDetail(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, MessageDetail::class.java)
        val message = Message()
        message.what = 1047
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setguestService(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1046
        mHnadler.handleMessage(message)
    }

    override fun setvouchersExchange(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1044
        mHnadler.handleMessage(message)
    }

    override fun setinvestmentIntroduction(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, TradingRules::class.java)
        val message = Message()
        message.what = 1042
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setguideToUse(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, TradingRules::class.java)
        val message = Message()
        message.what = 1041
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setbeginerDetail(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, BeginerDetail::class.java)
        val message = Message()
        message.what = 1040
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun settradingRules(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, TradingRules::class.java)
        val message = Message()
        message.what = 1039
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setprofitNews(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, ProfitNews::class.java)
        val message = Message()
        message.what = 1038
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setcashApply(context: Context, mHnadler: Handler, result: String) {
    }

    override fun setcashApplyWithoutVcode(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1056
        mHnadler.handleMessage(message)
    }

    override fun setnormalQuestionsDetail(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, NormalQuestionsDetail::class.java)
        val message = Message()
        message.what = 1045
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setnormalQuestionsList(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, NormalQuestionsList::class.java)
        val message = Message()
        message.what = 1035
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setrechargeYunJu(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, RechargeYunJu::class.java)
        val message = Message()
        message.what = 1057
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setupacpRechargeIPS(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, UpacpRechatge::class.java)
        val message = Message()
        message.what = 1036
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setupacpRecharge(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, UpacpRechatge::class.java)
        val message = Message()
        message.what = 1036
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setresetpassword(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1000
        mHnadler.handleMessage(message)
    }

    override fun setClose(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1000
        mHnadler.handleMessage(message)
    }

    override fun setqryHold(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, QryHold::class.java)
        val message = Message()
        message.what = 1032
        message.obj = gosn
        mHnadler.handleMessage(message)
        HttpALl.get().setqryHold(mHnadler, context)
    }

    override fun setqryTicket(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, QryTicket::class.java)
        val message = Message()
        message.what = 1034
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setguestServiceListd(context: Context, mHnadler: Handler, result: String) {
    }

    override fun setremoveCard(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, Untie::class.java)
        val message = Message()
        message.what = 1027
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setrechargAmount(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, RechargAmount::class.java)
        val message = Message()
        message.what = 1026
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setmessage(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, MessageList::class.java)
        val message = Message()
        message.what = 1031
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setupload(context: Context, mHnadler: Handler, result: String) {
    }

    override fun setgetvoucherslist(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, GetVouchersList::class.java)
        val message = Message()
        message.what = 1033
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setsignLog(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, SignLog::class.java)
        val message = Message()
        message.what = 1043
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setdoSign(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, DoSign::class.java)
        val message = Message()
        message.what = 1054
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setallPendingOrder(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, AllPendingOrder::class.java)
        val message = Message()
        message.what = 1057
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setcurrentPendingOrder(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, CurrentPendingOrder::class.java)
        val message = Message()
        message.what = 1055
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setQRYQUOTETRDTIME(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, QryQuoteTrdTime::class.java)
        val message = Message()
        message.what = 1025
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setCIRCLESSCOMMENTS(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1024
        mHnadler.handleMessage(message)
    }

    override fun setCIRCLESSLIKES(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1021
        mHnadler.handleMessage(message)
    }

    override fun setREFRESHTOKEN(context: Context, mHnadler: Handler, result: String) {
        var jsonObject = JSONObject(result)
        jsonObject = JSONObject(jsonObject.getString("data"))
        SPUtils.getInstance(context, "Authorization").put("Authorization", jsonObject.getString("token"))
        val message = Message()
        message.what = 1000
        mHnadler.handleMessage(message)
    }

    override fun setLOGOUT(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1020
        mHnadler.handleMessage(message)
    }

    override fun setMARKET(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, Markey::class.java)
        val message = Message()
        message.what = 1018
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setGETCOLLECTLIST(context: Context, mHnadler: Handler, result: String) {
    }

    override fun setGETTOPCAROUSELS(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, GetTopcaRousels::class.java)
        val message = Message()
        message.what = 1015
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setGETCIRCLESSCOMMENTS(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, CirClessCommemts::class.java)
        val message = Message()
        message.what = 1023
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setGETCIRCLESSINFO(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, CirclessInfo::class.java)
        val message = Message()
        message.what = 1022
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setGETINDEXSHOW(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, TindexShow::class.java)
        val message = Message()
        message.what = 1014
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setGETCIRCLESSLIST(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, CirclessList::class.java)
        val message = Message()
        message.what = 1019
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setGETPROFITLIST(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, ProfitList::class.java)
        val message = Message()
        message.what = 1013
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setGETVIDEOLIST(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, ViDeoList::class.java)
        val message = Message()
        message.what = 1012
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setQryStock(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, QryStock::class.java)
        val message = Message()
        message.what = 1011
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setGetYYOutCashList(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, GetYYOutCashList::class.java)
        val message = Message()
        message.what = 1017
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setGetRechargeList(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, GetRechargeList::class.java)
        val message = Message()
        message.what = 1037
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setQryOrderH(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, QryOrderH::class.java)
        val message = Message()
        message.what = 1016
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setQryKChart(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, QryKChart::class.java)
        val message = Message()
        message.what = 1010
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun TodayPrice(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, TodayPrice::class.java)
        val message = Message()
        message.what = 1008
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setLastPrice(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, QryStockNoName::class.java)
        val message = Message()
        message.what = 1007
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setRecommend(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, Recommend::class.java)
        val message = Message()
        message.what = 1006
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setQryBankCardList(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, MyBankCard::class.java)
        val message = Message()
        message.what = 1004
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setBindCard(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1030
        mHnadler.handleMessage(message)
    }

    override fun setBankList(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, BankList::class.java)
        val message = Message()
        message.what = 1005
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setQryAccount(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, QryAccount::class.java)
        val message = Message()
        message.what = 1003
        message.obj = gosn
        mHnadler.handleMessage(message)
    }

    override fun setOpenGranaryProvideRelief(context: Context, mHnadler: Handler, result: String) {
        val message = Message()
        message.what = 1035
        mHnadler.handleMessage(message)
    }


    override fun setWeChatPayment(context: Context, mHnadler: Handler, result: String) {
        val json = JSONObject(result)
        val message = Message()
        message.what = 1009
        message.obj = json.getString("token_id")
        mHnadler.handleMessage(message)
    }

    override fun setpublicReqlogin(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, PublicReqLoign::class.java)
        val message = Message()
        message.what = 1002
        message.obj = gosn
        mHnadler.handleMessage(message)
    }


    override fun setPublicReq(context: Context, mHnadler: Handler, result: String) {
        val gosn = Gson().fromJson(result, PublicReq::class.java)
        val message = Message()
        message.what = 1000
        message.obj = gosn
        mHnadler.handleMessage(message)
    }


    override fun setPublicPost(context: Context, map: Map<String, Any>, mHnadler: Handler, url: String, name: String) {
        OkHttpManager.postAsync(context, url, name, map,
                object : DataCallBack {
                    override fun requestFailure(request: Request, name: String, e: IOException) {
                        val message = Message()
                        message.what = 205   // 链接超时
                        mHnadler.handleMessage(message)
                    }

                    @Throws(Exception::class)
                    override fun requestSuccess(result: String, name: String) {
                        val json = JSONObject(result)
                        if (HttpOnekey.get().setBoolen(context, json.getString("return_code"), json.getString("return_msg"), mHnadler)!!) {
                            when (name) {
                                "LOGIN" -> setLOGIN(context, mHnadler, result)
                                "publicReq" -> setPublicReq(context, mHnadler, result)
                                "publicReqlogin" -> setpublicReqlogin(context, mHnadler, result)
                                "publicWeChatPayment" -> setWeChatPayment(context, mHnadler, result)
                                "openGranaryProvideRelief" -> setOpenGranaryProvideRelief(context, mHnadler, result)
                                "QryAccount" -> setQryAccount(context, mHnadler, result)//HTML
                                "BankList" -> setBankList(context, mHnadler, result)
                                "bindCard" -> setBindCard(context, mHnadler, result)
                                "QryBankCardList" -> setQryBankCardList(context, mHnadler, result)
                                "Recommend" -> setRecommend(context, mHnadler, result)
                                "LastPrice" -> setLastPrice(context, mHnadler, result)
                                "TodayPrice" -> TodayPrice(context, mHnadler, result)
                                "QryKChart" -> setQryKChart(context, mHnadler, result)
                                "QryOrderH" -> setQryOrderH(context, mHnadler, result)
                                "GetYYOutCashList" -> setGetYYOutCashList(context, mHnadler, result)
                                "GetRechargeList" -> setGetRechargeList(context, mHnadler, result)
                                "QryStock" -> setQryStock(context, mHnadler, result)
                                "MARKET" -> setMARKET(context, mHnadler, result)
                                "LOGOUT" -> setLOGOUT(context, mHnadler, result)
                                "REFRESHTOKEN" -> setREFRESHTOKEN(context, mHnadler, result)
                                "CIRCLESSLIKES" -> setCIRCLESSLIKES(context, mHnadler, result)
                                "GETVIDEOLIST" -> setGETVIDEOLIST(context, mHnadler, result)
                                "GETPROFITLIST" -> setGETPROFITLIST(context, mHnadler, result)
                                "GETCIRCLESSLIST" -> setGETCIRCLESSLIST(context, mHnadler, result)
                                "GETINDEXSHOW" -> setGETINDEXSHOW(context, mHnadler, result)
                                "GETCIRCLESSINFO" -> setGETCIRCLESSINFO(context, mHnadler, result)
                                "GETCIRCLESSCOMMENTS" -> setGETCIRCLESSCOMMENTS(context, mHnadler, result)
                                "GETTOPCAROUSELS" -> setGETTOPCAROUSELS(context, mHnadler, result)
                                "GETCOLLECTLIST" -> setGETCOLLECTLIST(context, mHnadler, result)
                                "CIRCLESSCOMMENTS" -> setCIRCLESSCOMMENTS(context, mHnadler, result)
                                "QRYQUOTETRDTIME" -> setQRYQUOTETRDTIME(context, mHnadler, result)
                                "doSign" -> setdoSign(context, mHnadler, result)
                                "getvoucherslist" -> setgetvoucherslist(context, mHnadler, result)
                                "upload" -> setupload(context, mHnadler, result)
                                "message" -> setmessage(context, mHnadler, result)
                                "rechargAmount" -> setrechargAmount(context, mHnadler, result)
                                "removeCard" -> setremoveCard(context, mHnadler, result)
                                "guestServiceList" -> setguestServiceListd(context, mHnadler, result)
                                "qryTicket" -> setqryTicket(context, mHnadler, result)
                                "qryHold" -> setqryHold(context, mHnadler, result)
                                "Close" -> setClose(context, mHnadler, result)
                                "resetpassword" -> setresetpassword(context, mHnadler, result)
                                "upacpRecharge" -> setupacpRecharge(context, mHnadler, result)
                                "upacpRechargeIPS" -> setupacpRechargeIPS(context, mHnadler, result)
                                "rechargeYunJu" -> setrechargeYunJu(context, mHnadler, result)
                                "cashApplyWithoutVcode" -> setcashApplyWithoutVcode(context, mHnadler, result)
                                "cashApply" -> setcashApply(context, mHnadler, result)
                                "profitNews" -> setprofitNews(context, mHnadler, result)
                                "beginerDetail" -> setbeginerDetail(context, mHnadler, result)
                                "vouchersExchange" -> setvouchersExchange(context, mHnadler, result)
                                "normalQuestionsDetail" -> setnormalQuestionsDetail(context, mHnadler, result)
                                "guestService" -> setguestService(context, mHnadler, result)
                                "messageDetail" -> setmessageDetail(context, mHnadler, result)
                                "editAvatar" -> seteditAvatar(context, mHnadler, result)
                                "editNickName" -> seteditNickName(context, mHnadler, result)
                                "appVersion" -> setappVersion(context, mHnadler, result)
                                "info" -> setinfo(context, mHnadler, result)
                                "PendingOrder" -> PendingOrder(context, mHnadler, result)
                                "cancelPendingOrder" -> setCancelPendingOrder(context, mHnadler, result)
                                "qrystock" -> setqrystock(context, mHnadler, result)
                            }
                        }
                    }
                })
    }

    override fun setPublicGet(context: Context, mHnadler: Handler, url: String, name: String) {
        OkHttpManager.getAsync(context, url, name, object : DataCallBack {
            override fun requestFailure(request: Request, name: String, e: IOException) {
                val message = Message()
                message.what = 205   // 链接超时
                mHnadler.handleMessage(message)
            }

            override fun requestSuccess(result: String, name: String) {
                if (name == "Httpwallstreetcn") {
                    Httpwallstreetcn(context, mHnadler, result)
                } else {
                    val json = JSONObject(result)
                    if (HttpOnekey.get().setBoolen(context, json.getString("return_code"), json.getString("return_msg"), mHnadler)!!) {
                        when (name) {
                            "GETVIDEOLIST" -> setGETVIDEOLIST(context, mHnadler, result)
                            "GETPROFITLIST" -> setGETPROFITLIST(context, mHnadler, result)
                            "GETCIRCLESSLIST" -> setGETCIRCLESSLIST(context, mHnadler, result)
                            "GETINDEXSHOW" -> setGETINDEXSHOW(context, mHnadler, result)
                            "GETCIRCLESSINFO" -> setGETCIRCLESSINFO(context, mHnadler, result)
                            "GETCIRCLESSCOMMENTS" -> setGETCIRCLESSCOMMENTS(context, mHnadler, result)
                            "GETTOPCAROUSELS" -> setGETTOPCAROUSELS(context, mHnadler, result)
                            "GETCOLLECTLIST" -> setGETCOLLECTLIST(context, mHnadler, result)
                            "normalQuestionsList" -> setnormalQuestionsList(context, mHnadler, result)
                            "tradingRules" -> settradingRules(context, mHnadler, result)
                            "guideToUse" -> setguideToUse(context, mHnadler, result)
                            "investmentIntroduction" -> setinvestmentIntroduction(context, mHnadler, result)
                            "signLog" -> setsignLog(context, mHnadler, result)
                            "allPendingOrder" -> setallPendingOrder(context, mHnadler, result)
                            "currentPendingOrder" -> setcurrentPendingOrder(context, mHnadler, result)
                            "exchangehistory" -> setexchangehistory(context, mHnadler, result)
                            "qrykchart" -> setqrykchart(context, mHnadler, result)
                            "qrykprice" -> setqrykprice(context, mHnadler, result)
                        }
                    }
                }
            }
        })
    }

    override fun setPublicGetAuthorization(context: Context, mHnadler: Handler, url: String, name: String) {
        OkHttpManager.getAsyncAuthorization(context, url, name, object : DataCallBack {
            override fun requestFailure(request: Request, name: String, e: IOException) {
                val message = Message()
                message.what = 205   // 链接超时
                mHnadler.handleMessage(message)
            }

            override fun requestSuccess(result: String, name: String) {
                val json = JSONObject(result)
                if (HttpOnekey.get().setBoolen(context, json.getString("return_code"), json.getString("return_msg"), mHnadler)!!) {
                    when (name) {

                    }
                }
            }
        })
    }


    override fun setHOME(context: Context, mHnadler: Handler, result: String) {
//        val gosn = Gson().fromJson(result, HomeData::class.java)
//        val message = Message()
//        message.what = 1000
//        message.obj = gosn
//        mHnadler.handleMessage(message)
    }


    override fun setLOGIN(context: Context, mHnadler: Handler, result: String) {
//        val gosn = Gson().fromJson(result, LoginData::class.java)
//        val message = Message()
//        message.what = 1001
//        message.obj = gosn
//        mHnadler.handleMessage(message)
    }

    override fun setHasp(context: Context, mHnadler: Handler, result: String) {
        val ip: String = result
        val ips = ip.split("=")
        val jsonObject = JSONObject(ips[1])
        val message = Message()
        message.what = 1001
        message.obj = jsonObject.getString("cip")
        mHnadler.handleMessage(message)
    }

    override fun setPublicOther(context: Context, map: Map<String, Any>, mHnadler: Handler, url: String, name: String) {
        OkHttpManager.postAsync(context, url, name, map, object : DataCallBack {

            override fun requestFailure(request: Request, name: String, e: IOException) {
                val message = Message()
                message.what = 205   // 链接超时
                mHnadler.handleMessage(message)
            }

            override fun requestSuccess(result: String, name: String) {
                when (name) {
                    "Hasp" -> setHasp(context, mHnadler, result)
                }
            }
        })
    }

    /**
     * 单例对象实例
     */
    companion object {
        fun get(): HttpRequest {
            return Inner.httpRequest
        }
    }

    private object Inner {
        val httpRequest = HttpRequest()
    }
}