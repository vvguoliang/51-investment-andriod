package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class GetRechargeList(
        @SerializedName("return_code") var returnCode: String = "", // 100
        @SerializedName("return_msg") var returnMsg: String = "", // 成功
        @SerializedName("data") var data: Data = Data()
) {

    data class Data(
            @SerializedName("RechargeList") var rechargeList: List<Recharge> = listOf(),
            @SerializedName("holdno") var holdno: Any = Any(), // null
            @SerializedName("systemtime") var systemtime: Any = Any() // null
    ) {

        data class Recharge(
                @SerializedName("orderno") var orderno: String = "", // 87922018062220592221717
                @SerializedName("custid") var custid: String = "", // 13717578792
                @SerializedName("openid") var openid: String = "", // -
                @SerializedName("pwd") var pwd: String = "", // EF9E1E3BEA1EE19FC081145866BC8FEB
                @SerializedName("exchangerate") var exchangerate: String = "", // 1.00000
                @SerializedName("ordercapital") var ordercapital: String = "", // 1000.00
                @SerializedName("payamt") var payamt: String = "", // 1000.00
                @SerializedName("gdscode") var gdscode: Any = Any(), // null
                @SerializedName("gdsname") var gdsname: Any = Any(), // null
                @SerializedName("gdsprice") var gdsprice: Any = Any(), // null
                @SerializedName("gdsqty") var gdsqty: Any = Any(), // null
                @SerializedName("orderstatus") var orderstatus: String = "", // 1
                @SerializedName("ordertype") var ordertype: String = "", // 9
                @SerializedName("ordertime") var ordertime: String = "", // 2018-06-22T20:59:22
                @SerializedName("memo") var memo: String = "",
                @SerializedName("guid") var guid: String = "", // 32e4da8b-7b8c-493f-8bb2-5377e1e36fc1
                @SerializedName("mid") var mid: String = "" // MB003122
        )
    }
}