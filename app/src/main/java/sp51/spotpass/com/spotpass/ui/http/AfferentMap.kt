package sp51.spotpass.com.spotpass.ui.http

/**
 * @Time : 2018/4/16 no 下午2:17
 * @USER : vvguoliang
 * @File : AfferentMap.kt
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
interface AfferentMap {

    fun setVcode(account: Long): Map<String, Any>

    fun setString2MD5(inStr: String, timeMillis: Long, type: Int): String

    fun setTimeMillis(): String

    /**
     * 经纪人编码  mbid
     * 全民经纪人编码，如没有值，传- qbid
     */
    fun setPublicReq(account: String, pwd: String, mbid: String, qbid: String, vcode: String): Map<String, Any>

    /**
     * 登录
     */
    fun setPublicReqlogin(accoBnt: String, pwd: String, ip: String): Map<String, Any>

    /**
     * 微信支付
     */
    fun setWeChatPayment(cmdType: String, account: String, capitalId: String, sessionId: String, rechargeAmt: String): Map<String, Any>

    /**
     * 开仓
     */
    fun setopenGranaryProvideRelief(cmdType: String, account: String, capitalId: String, sessionId: String, stkcode: String, bsflag: String,
                                    orderprice: String, orderqty: String, losspoints: String, profitpoints: String, ticketflag: String,
                                    orderfrom: String): Map<String, Any>

    /**
     * 开仓
     */
    fun setPendingOrder(price: String, stkcode: String, bsflag: Long, orderprice: String, orderqty: Long, losspoints: String, profitpoints: String, orderfrom: Long): Map<String, Any>

    /**
     * 交易商账户
     */
    fun setQryAccount(cmdType: String, account: String, capitalId: String, sessionId: String): Map<String, Any>

    /**
     * 银行卡列表
     */
    fun setBankList(): Map<String, Any>

    /**
     * 绑定银行卡
     */
    fun setBindCard(cmdType: String, account: String, sessionId: String, bankId: String, cardNo: String, cerName: String, cerNo: String, bankName: String): Map<String, Any>

    /**
     * 查询银行卡
     */
    fun setQryBankCardList(cmdType: String, account: String, sessionId: String): Map<String, Any>

    /**
     * 推荐行情最新
     */
    fun setRecommend(): Map<String, Any>

    /**
     * 推荐行情最新
     */
    fun setMARKET(): Map<String, Any>

    /**
     * 推荐行情最新
     */
    fun setLastPrice(qtecode: String): Map<String, Any>

    /**
     * 推荐行情最新
     */
    fun setQryKChart(cmdType: String, qryKChartCode: String, qtecode: String): Map<String, Any>

    /**
     * 历史
     */
    fun setQryOrderH(cmdType: String, account: String, capitalId: String, sessionId: String, indexDate: String, indexOrderNo: String): Map<String, Any>

    /**
     * 提现记录
     */
    fun setGetYYOutCashList(cmdType: String, account: String, sessionId: String): Map<String, Any>

    /**
     * 充值记录
     */
    fun setGetRechargeList(cmdType: String, account: String, sessionId: String): Map<String, Any>

    /**
     * 交易品种查询
     */
    fun setQryStock(qtecode: String, cmdType: String): Map<String, Any>

    /**
     * 交易品种查询
     */
    fun setLOGOUT(cmdType: String, account: String): Map<String, Any>

    /**
     * 交易品种查询
     */
    fun setCIRCLESSLIKES(id: Long): Map<String, Any>

    /**
     * 交易品种查询
     */
    fun setCIRCLESSCOMMENTS(id: Long, content: String): Map<String, Any>

    /**
     * 交易品种查询
     */
    fun setQRYQUOTETRDTIME(cmdType: String, qtecode: String): Map<String, Any>

    fun setcurrentPendingOrder(page: Long): Map<String, Any>

    fun setcancelOrder(id: Long): Map<String, Any>

    fun setallPendingOrder(page: Long): Map<String, Any>

    fun setmessage(page: Long): Map<String, Any>

    fun setmessageDetail(id: String): Map<String, Any>

    fun setremoveCard(account: String, sessionId: String, cardNo: String): Map<String, Any>

    fun setqryTicket(): Map<String, Any>

    fun setqryHold(): Map<String, Any>

    fun setClose(account: String, capitalId: String, sessionId: String, stkcode: String,
                 bsflag: String, orderno: String, orderprice: String, orderqty: String, ticketflag: String, orderfrom: String): Map<String, Any>

    fun setresetpassword(mobile: String, captcha: String, new_password: String, confirm_password: String): Map<String, Any>

    fun setupacpRecharge(account: String, capitalId: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String): Map<String, Any>

    fun setupacpRechargeIPS(account: String, capitalId: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String): Map<String, Any>

    fun setrechargeYunJu(account: String, yunju_banktype: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String): Map<String, Any>

    fun setsetcashApplyWithoutVcode(account: String, capitalId: String, sessionId: String, pwd: String, rechargeAmt: String, cardNo: String): Map<String, Any>

    fun setcashApply(account: String, capitalId: String, sessionId: String, pwd: String, cashAmt: String, cardNo: String): Map<String, Any>

    fun setnormalQuestionsList(page: Long): Map<String, Any>

    fun setvouchersExchange(price: String): Map<String, Any>

    fun setnormalQuestionsDetail(id: Long): Map<String, Any>

    fun setguestService(content: String): Map<String, Any>

    fun seteditAvatar(avatar: String): Map<String, Any>

    fun seteditNickName(nickname: String): Map<String, Any>

}