package sp51.spotpass.com.spotpass.ui.http

import android.content.Context
import android.os.Handler
import java.io.File

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
interface HttpALlManger {

    fun setPhoneCode(phone: Long, mHandler: Handler, context: Context)

    fun setPublicReq(account: String, pwd: String, mbid: String, qbid: String, vcode: String, mHandler: Handler, context: Context)

    fun setPublicReqlogin(account: String, pwd: String, ip: String, mHandler: Handler, context: Context)

    fun setHasp(mHandler: Handler, context: Context)

    fun setWeChatPayment(cmdType: String, account: String, capitalId: String, sessionId: String, rechargeAmt: String, mHandler: Handler, context: Context)

    fun setopenGranaryProvideRelief(cmdType: String, account: String, capitalId: String, sessionId: String, stkcode: String,
                                    bsflag: String, orderprice: String, orderqty: String, losspoints: String, profitpoints: String,
                                    ticketflag: String, orderfrom: String, mHandler: Handler, context: Context)

    fun setQryAccount(cmdType: String, account: String, capitalId: String, sessionId: String, mHandler: Handler, context: Context)

    fun setBankList(mHandler: Handler, context: Context)

    fun setBindCard(cmdType: String, account: String, sessionId: String, bankId: String, cardNo: String, cerName: String, cerNo: String, bankName: String, mHandler: Handler, context: Context)

    fun setQryBankCardList(cmdType: String, account: String, sessionId: String, mHandler: Handler, context: Context)

    fun setRecommend(mHandler: Handler, context: Context)

    fun setLastPrice(qtecode: String, mHandler: Handler, context: Context)

    fun setTodayPrice(qtecode: String, mHandler: Handler, context: Context)

    fun setQryKChart(cmdType: String, qryKChartCode: String, qtecode: String, mHandler: Handler, context: Context)

    fun setQryOrderH(cmdType: String, account: String, capitalId: String, sessionId: String, indexDate: String, indexOrderNo: String, mHandler: Handler, context: Context)

    fun setGetYYOutCashList(cmdType: String, account: String, sessionId: String, mHandler: Handler, context: Context)

    fun setGetRechargeList(cmdType: String, account: String, sessionId: String, mHandler: Handler, context: Context)

    fun setQryStock(cmdType: String, qtecode: String, mHandler: Handler, context: Context)

    fun setGETVIDEOLIST(mHandler: Handler, context: Context)

    fun setGETPROFITLIST(mHandler: Handler, context: Context)

    fun setGETCIRCLESSLIST(mHandler: Handler, context: Context)

    fun setGETINDEXSHOW(mHandler: Handler, context: Context)

    fun setGETCIRCLESSINFO(id: Long, mHandler: Handler, context: Context)

    fun setGETCIRCLESSCOMMENTS(id: Long, mHandler: Handler, context: Context)

    fun setGETTOPCAROUSELS(mHandler: Handler, context: Context)

    fun setGETCOLLECTLIST(mHandler: Handler, context: Context)

    fun setMARKET(mHandler: Handler, context: Context)

    fun setLOGOUT(cmdType: String, account: String, mHandler: Handler, context: Context)

    fun setREFRESHTOKEN(mHandler: Handler, context: Context)

    fun setGETCIRCLESSLIST1(name: String, url: String, mHandler: Handler, context: Context)

    fun setCIRCLESSLIKES(id: Long, mHandler: Handler, context: Context)

    fun setCIRCLESSCOMMENTS(id: Long, content: String, mHandler: Handler, context: Context)

    fun setQRYQUOTETRDTIME(cmdType: String, qtecode: String, mHandler: Handler, context: Context)

    fun setcurrentPendingOrder(page: Long, mHandler: Handler, context: Context)

    fun setallPendingOrder(page: Long, mHandler: Handler, context: Context)

    fun setupload(page: Long, mHandler: Handler, context: Context)

    fun setdoSign(mHandler: Handler, context: Context)

    fun setgetvoucherslist(mHandler: Handler, context: Context)

    fun setaboutUs(mHandler: Handler, context: Context)

    fun setmessage(page: Long, mHandler: Handler, context: Context)

    fun setmessageDetail(id: String, mHandler: Handler, context: Context)

    fun setrechargAmount(mHandler: Handler, context: Context)

    fun setremoveCard(account: String, sessionId: String, cardNo: String, mHandler: Handler, context: Context)

    fun setguestServiceList(page: Long, mHandler: Handler, context: Context)

    fun setqryTicket(mHandler: Handler, context: Context)

    fun setqryHold(mHandler: Handler, context: Context)

    fun setClose(account: String, capitalId: String, sessionId: String, stkcode: String,
                 bsflag: String, orderno: String, orderprice: String, orderqty: String, ticketflag: String, orderfrom: String, mHandler: Handler, context: Context)

    fun setresetpassword(mobile: String, captcha: String, new_password: String, confirm_password: String, mHandler: Handler, context: Context)

    fun setupacpRecharge(account: String, capitalId: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String, mHandler: Handler, context: Context)

    fun setupacpRechargeIPS(account: String, capitalId: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String, mHandler: Handler, context: Context)

    fun setcashApplyWithoutVcode(account: String, capitalId: String, sessionId: String, pwd: String, rechargeAmt: String, cardNo: String, mHandler: Handler, context: Context)

    fun setcashApply(account: String, capitalId: String, sessionId: String, pwd: String, cashAmt: String, cardNo: String, mHandler: Handler, context: Context)

    fun setrechargeYunJu(account: String, yunju_banktype: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String, mHandler: Handler, context: Context)

    fun setnormalQuestionsList(page: Long, mHandler: Handler, context: Context)

    fun setnormalQuestionsDetail(page: Long, mHandler: Handler, context: Context)

    fun setprofitNews(mHandler: Handler, context: Context)

    fun settradingRules(page: Long, mHandler: Handler, context: Context)

    fun setbeginerDetail(id: Long, mHandler: Handler, context: Context)

    fun setguideToUse(page: Long, mHandler: Handler, context: Context)

    fun setinvestmentIntroduction(page: Long, mHandler: Handler, context: Context)

    fun setsignLog(page: Long, mHandler: Handler, context: Context)

    fun setvouchersExchange(price: String, mHandler: Handler, context: Context)

    fun setguestService(content: String, mHandler: Handler, context: Context)

    fun setimg_upload(is_face: String, name1: File, mHandler: Handler, context: Context)

    fun seteditAvatar(editAvatar: String, mHandler: Handler, context: Context)

    fun seteditNickName(nickname: String, mHandler: Handler, context: Context)

    fun setappVersion(mHandler: Handler, context: Context)

    fun setinfo(mHandler: Handler, context: Context)

    fun setPendingOrder(price: String, stkcode: String, bsflag: Long, orderprice: String, orderqty: Long, losspoints: String, profitpoints: String, orderfrom: Long, mHandler: Handler, context: Context)

    fun setCancelPendingOrder(id: Long, mHandler: Handler, context: Context)

    fun exchangehistory(page: Long, mHandler: Handler, context: Context)

    fun setqrystock(qtecode: String, mHandler: Handler, context: Context)

    fun setqrykchart(qtecode: String, qryKChartCode: Long, mHandler: Handler, context: Context)

    fun setqrykprice(qtecode: String, mHandler: Handler, context: Context)
}