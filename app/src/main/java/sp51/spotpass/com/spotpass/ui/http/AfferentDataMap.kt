@file:Suppress("NAME_SHADOWING")

package sp51.spotpass.com.spotpass.ui.http

import android.text.TextUtils
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import java.security.MessageDigest
import java.util.*


/**
 * @Time : 2018/4/16 no 下午2:19
 * @USER : vvguoliang
 * @File : AfferentDataMap.java
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
class AfferentDataHttpMap private constructor() : AfferentMap {
    override fun setcancelOrder(id: Long): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["id"] = id
        return getToake(map, timeMillis)
    }

    override fun setPendingOrder(price :String,stkcode: String, bsflag: Long, orderprice: String, orderqty: Long, losspoints: String, profitpoints: String, orderfrom: Long): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["stkcode"] = stkcode
        map["price"] = price
        map["bsflag"] = bsflag
        map["orderprice"] = orderprice
        map["orderqty"] = orderqty
        map["losspoints"] = losspoints
        map["profitpoints"] = profitpoints
        map["orderfrom"] = orderfrom
        return getToake(map, timeMillis)
    }

    override fun seteditNickName(nickname: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["nickname"] = nickname
        return getToake(map, timeMillis)
    }

    override fun seteditAvatar(avatar: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["avatar"] = avatar
        return getToake(map, timeMillis)
    }

    override fun setguestService(content: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["content"] = content
        return getToake(map, timeMillis)
    }

    override fun setnormalQuestionsDetail(id: Long): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["id"] = id
        return getToake(map, timeMillis)
    }

    override fun setvouchersExchange(price: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["price"] = price
        return getToake(map, timeMillis)
    }

    override fun setcashApply(account: String, capitalId: String, sessionId: String, pwd: String, cashAmt: String, cardNo: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = "cashApply"
        map["account"] = account
        map["capitalId"] = capitalId
        map["sessionId"] = sessionId
        map["pwd"] = pwd
        map["cashAmt"] = cashAmt
        map["cardNo"] = cardNo
        return getToake(map, timeMillis)
    }

    override fun setsetcashApplyWithoutVcode(account: String, capitalId: String, sessionId: String, pwd: String, rechargeAmt: String, cardNo: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = "cashApplyWithoutVcode"
        map["account"] = account
        map["capitalId"] = capitalId
        map["sessionId"] = sessionId
        map["pwd"] = pwd
        map["cashAmt"] = rechargeAmt
        map["cardNo"] = cardNo
        return getToake(map, timeMillis)
    }

    override fun setnormalQuestionsList(page: Long): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["page"] = page
        return getToake(map, timeMillis)
    }

    override fun setrechargeYunJu(account: String, yunju_banktype: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = "rechargeYunJu"
        map["account"] = account
        map["yunju_banktype"] = yunju_banktype
        map["sessionId"] = sessionId
        map["rechargeAmt"] = rechargeAmt
//        map["cardNo"] = cardNo
        map["redirectUrl"] = redirectUrl
        return getToake(map, timeMillis)
    }

    override fun setupacpRechargeIPS(account: String, capitalId: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = "upacpRechargeIPS"
        map["account"] = account
        map["capitalId"] = capitalId
        map["sessionId"] = sessionId
        map["rechargeAmt"] = rechargeAmt
//        map["cardNo"] = cardNo
        map["redirectUrl"] = redirectUrl
        return getToake(map, timeMillis)
    }

    override fun setupacpRecharge(account: String, capitalId: String, sessionId: String, rechargeAmt: String, cardNo: String, redirectUrl: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = "upacpRecharge"
        map["account"] = account
        map["capitalId"] = capitalId
        map["sessionId"] = sessionId
        map["rechargeAmt"] = rechargeAmt
        map["cardNo"] = cardNo
        map["redirectUrl"] = redirectUrl
        return getToake(map, timeMillis)
    }

    override fun setresetpassword(mobile: String, captcha: String, new_password: String, confirm_password: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["mobile"] = mobile
        map["captcha"] = captcha
        map["new_password"] = new_password
        map["confirm_password"] = confirm_password
        return getToake(map, timeMillis)
    }

    override fun setClose(account: String, capitalId: String, sessionId: String, stkcode: String, bsflag: String,
                          orderno: String, orderprice: String, orderqty: String, ticketflag: String, orderfrom: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = "close"
        map["account"] = account
        map["capitalId"] = capitalId
        map["sessionId"] = sessionId
        map["stkcode"] = stkcode
        map["bsflag"] = bsflag
        map["orderno"] = orderno
        map["orderprice"] = orderprice
        map["orderqty"] = orderqty
        map["ticketflag"] = ticketflag
        map["orderfrom"] = orderfrom
        return getToake(map, timeMillis)
    }

    override fun setqryHold(): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = "qryHold"
        return getToake(map, timeMillis)
    }

    override fun setqryTicket(): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = "qryTicket"
        return getToake(map, timeMillis)
    }

    override fun setremoveCard(account: String, sessionId: String, cardNo: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cardNo"] = cardNo
        map["account"] = account
        map["sessionId"] = sessionId
        map["cmdType"] = "removeCard"
        return getToake(map, timeMillis)
    }

    override fun setmessageDetail(id: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["id"] = id
        return getToake(map, timeMillis)
    }

    override fun setmessage(page: Long): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["page"] = page
        return getToake(map, timeMillis)
    }

    override fun setallPendingOrder(page: Long): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["page"] = page
        return getToake(map, timeMillis)
    }

    override fun setcurrentPendingOrder(page: Long): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["page"] = page
        return getToake(map, timeMillis)
    }

    override fun setQRYQUOTETRDTIME(cmdType: String, qtecode: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = cmdType
        map["qtecode"] = qtecode
        return getToake(map, timeMillis)
    }

    override fun setCIRCLESSCOMMENTS(id: Long, content: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["id"] = id
        map["content"] = content
        return getToake(map, timeMillis)
    }

    override fun setCIRCLESSLIKES(id: Long): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["id"] = id
        return getToake(map, timeMillis)
    }

    override fun setLOGOUT(cmdType: String, account: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = cmdType
        map["account"] = account
        return getToake(map, timeMillis)
    }

    override fun setQryStock(qtecode: String, cmdType: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = cmdType
        map["qtecode"] = qtecode
        return getToake(map, timeMillis)
    }

    override fun setGetYYOutCashList(cmdType: String, account: String, sessionId: String): Map<String, Any> {
        return getQryBankCardList(cmdType, account, sessionId)
    }

    override fun setGetRechargeList(cmdType: String, account: String, sessionId: String): Map<String, Any> {
        return getQryBankCardList(cmdType, account, sessionId)
    }

    override fun setQryOrderH(cmdType: String, account: String, capitalId: String, sessionId: String, indexDate: String, indexOrderNo: String): Map<String, Any> {
        return getQryOrderH(cmdType, account, capitalId, sessionId, indexDate, indexOrderNo)
    }

    private fun getQryOrderH(cmdType: String, account: String, capitalId: String, sessionId: String, indexDate: String, indexOrderNo: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = cmdType
        map["account"] = account
        map["capitalId"] = capitalId
        map["sessionId"] = sessionId
        map["indexDate"] = indexDate
        map["indexOrderNo"] = indexOrderNo
        return getToake(map, timeMillis)
    }

    override fun setQryKChart(cmdType: String, qryKChartCode: String, qtecode: String): Map<String, Any> {
        return getQryKChart(cmdType, qryKChartCode, qtecode)
    }

    private fun getQryKChart(cmdType: String, qryKChartCode: String, qtecode: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = cmdType
        map["qryKChartCode"] = qryKChartCode
        map["qtecode"] = qtecode
        return getToake(map, timeMillis)
    }

    override fun setLastPrice(qtecode: String): Map<String, Any> {
        return getLastPrice(qtecode)
    }

    private fun getLastPrice(qtecode: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["qtecode"] = qtecode
        return getToake(map, timeMillis)
    }

    override fun setRecommend(): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        return getToake(null, timeMillis)
    }

    override fun setMARKET(): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        return getToake(null, timeMillis)
    }

    override fun setQryBankCardList(cmdType: String, account: String, sessionId: String): Map<String, Any> {
        return getQryBankCardList(cmdType, account, sessionId)
    }

    private fun getQryBankCardList(cmdType: String, account: String, sessionId: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = cmdType
        map["account"] = account
        map["sessionId"] = sessionId
        return getToake(map, timeMillis)
    }

    override fun setBindCard(cmdType: String, account: String, sessionId: String, bankId: String, cardNo: String, cerName: String, cerNo: String, bankName: String): Map<String, Any> {
        return getBindCard(cmdType, account, sessionId, bankId, cardNo, cerName, cerNo, bankName)
    }

    private fun getBindCard(cmdType: String, account: String, sessionId: String, bankId: String, cardNo: String, cerName: String, cerNo: String, bankName: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = cmdType
        map["account"] = account
        map["sessionId"] = sessionId
        map["bankId"] = bankId
        map["cardNo"] = cardNo
        map["cerName"] = cerName
        map["cerNo"] = cerNo
        map["bankName"] = bankName
        return getToake(map, timeMillis)
    }

    override fun setBankList(): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        return getToake(null, timeMillis)
    }

    override fun setQryAccount(cmdType: String, account: String, capitalId: String, sessionId: String): Map<String, Any> {
        return getQryAccount(cmdType, account, capitalId, sessionId)
    }

    private fun getQryAccount(cmdType: String, account: String, capitalId: String, sessionId: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = cmdType
        map["account"] = account
        map["capitalId"] = capitalId
        map["sessionId"] = sessionId
        return getToake(map, timeMillis)
    }

    override fun setopenGranaryProvideRelief(cmdType: String, account: String, capitalId: String, sessionId: String, stkcode: String,
                                             bsflag: String, orderprice: String, orderqty: String, losspoints: String, profitpoints: String,
                                             ticketflag: String, orderfrom: String): Map<String, Any> {
        return getopenGranaryProvideRelief(cmdType, account, capitalId, sessionId, stkcode, bsflag, orderprice, orderqty, losspoints,
                profitpoints, ticketflag, orderfrom)
    }

    private fun getopenGranaryProvideRelief(cmdType: String, account: String, capitalId: String, sessionId: String, stkcode: String,
                                            bsflag: String, orderprice: String, orderqty: String, losspoints: String, profitpoints: String,
                                            ticketflag: String, orderfrom: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = cmdType
        map["account"] = account
        map["capitalId"] = capitalId
        map["sessionId"] = sessionId
        map["stkcode"] = stkcode
        map["bsflag"] = bsflag
        map["bsflag"] = bsflag
        map["orderprice"] = orderprice
        map["orderqty"] = orderqty
        map["losspoints"] = losspoints
        map["profitpoints"] = profitpoints
        map["ticketflag"] = ticketflag
        map["orderfrom"] = orderfrom
        return getToake(map, timeMillis)

    }

    override fun setWeChatPayment(cmdType: String, account: String, capitalId: String, sessionId: String, rechargeAmt: String): Map<String, Any> {
        return getWeChatPayment(cmdType, account, capitalId, sessionId, rechargeAmt)
    }

    private fun getWeChatPayment(cmdType: String, account: String, capitalId: String, sessionId: String, rechargeAmt: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = cmdType
        map["account"] = account
        map["rechargeAmt"] = rechargeAmt
        map["capitalId"] = capitalId
        map["sessionId"] = sessionId
        return getToake(map, timeMillis)
    }

    override fun setPublicReqlogin(accoBnt: String, pwd: String, ip: String): Map<String, Any> {
        return getPublicReqlogin(accoBnt, pwd, ip)
    }

    private fun getPublicReqlogin(account: String, pwd: String, ip: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = "login"
        map["account"] = account
        map["pwd"] = pwd
        map["ip"] = ip
        return getToake(map, timeMillis)
    }

    override fun setPublicReq(account: String, pwd: String, mbid: String, qbid: String, vcode: String): Map<String, Any> {
        return getPublicReq(account, pwd, mbid, qbid, vcode)
    }

    private fun getPublicReq(account: String, pwd: String, mbid: String, qbid: String, vcode: String): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["cmdType"] = "registerWithoutVcode"
        map["account"] = account
//        map["pwd"] = setString2MD5(pwd, timeMillis.toLong(), 0)
        map["pwd"] = pwd
        if (!TextUtils.isEmpty(mbid)) {
            map["mbid"] = mbid
        } else {
            map["mbid"] = "MB000001"
        }
        if (!TextUtils.isEmpty(qbid)) {
            map["qbid"] = qbid
        } else {
            map["qbid"] = "-"
        }
        if (!TextUtils.isEmpty(vcode)) {
            map["vcode"] = vcode
        }
        map["account"] = account
        return getToake(map, timeMillis)
    }

    override fun setVcode(account: Long): Map<String, Any> {
        return getVcode(account)
    }

    private fun getVcode(account: Long): Map<String, Any> {
        val timeMillis: String = setTimeMillis()
        val map = HashMap<String, Any>()
        map["account"] = account
        return getToake(map, timeMillis)
    }

    private fun getToake(mapkv: Map<String, Any>?, timeMillis: String): Map<String, Any> {
        val map = HashMap<String, Any>()
        val map1 = HashMap<String, Any>()
        map["timeStamp"] = timeMillis
        if (mapkv != null && mapkv.isNotEmpty()) {
            for ((k, v) in mapkv) {
                if (!TextUtils.isEmpty(v.toString())) {
                    map[k] = v
                }
                map1[k] = v
            }
        }
        map1["timeStamp"] = timeMillis.toLong()
        map1["signature"] = setString2MD5(setString(map), timeMillis.toLong(), 1)
        return map1
    }

    private fun getToake1(mapkv: Map<String, Any>?, timeMillis: String): Map<String, Any> {
        val map = HashMap<String, Any>()
        val map1 = HashMap<String, Any>()
//        map["timeStamp"] = timeMillis
        if (mapkv != null && mapkv.isNotEmpty()) {
            for ((k, v) in mapkv) {
                if (!TextUtils.isEmpty(v.toString())) {
                    map[k] = v
                }
                map1[k] = v
            }
        }
//        map1["timeStamp"] = timeMillis.toLong()
        map1["sign"] = setString2MD51(setString(map), 1)
        return map1
    }

    private fun setString(map: HashMap<String, Any>): String {
        val keyset = map.keys

        val list = ArrayList<String>(keyset)

        list.sort()
        var string = ""
//这种打印出的字符串顺序和微信官网提供的字典序顺序是一致的  
        for (i in list.indices) {
            string += list[i] + "=" + map[list[i]] + "&"
        }
        return string
    }

    /**
     * 时间戳
     */
    override fun setTimeMillis(): String {
        val dt = Date()
        val time = dt.time
        return time.toString()
    }

    override fun setString2MD5(inStr: String, timeMillis: Long, type: Int): String {
        if (type == 0) {
            return string2MD5(inStr)
        } else {
            return string2MD5(inStr + "key=654321")//GLSCTRADEAPIDBQIANYUNHUIV1
        }
    }

    fun setString2MD51(inStr: String, type: Int): String {
        return if (type == 0) {
            string2MD5(inStr)
        } else {
            string2MD5(inStr + "key=123456")
        }
    }

    /***
     * MD5加码 生成32位md5 ?
     */
    private fun string2MD5(inStr: String): String {
        val md5: MessageDigest?
        try {
            md5 = MessageDigest.getInstance("MD5")
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
        val byteArray = inStr.toByteArray()
        val md5Bytes = md5!!.digest(byteArray)
        val hexValue = StringBuffer()
        for (i in md5Bytes.indices) {
            val `val` = md5Bytes[i].toInt() and 0xff
            if (`val` < 16)
                hexValue.append("0")
            hexValue.append(Integer.toHexString(`val`))
        }
        val ch = hexValue.toString().toCharArray()
        val sbf = StringBuffer()
        for (i in 0 until ch.size) {
            sbf.append(charToUpperCase(ch[i]))
        }
        return sbf.toString()
    }

    /**转大写 */
    private fun charToUpperCase(ch: Char): Char {
        var ch = ch
        if (ch.toInt() in 97..122) {
            ch -= 32
        }
        return ch
    }

    /**
     * 单例对象实例
     */
    companion object {
        fun get(): AfferentDataHttpMap {
            return Inner.afferentMap
        }
    }

    private object Inner {
        val afferentMap = AfferentDataHttpMap()
    }
}
