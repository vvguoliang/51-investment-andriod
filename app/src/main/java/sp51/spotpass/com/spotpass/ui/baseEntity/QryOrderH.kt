package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class QryOrderH(
    @SerializedName("return_code") var returnCode: String, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("data") var data: Data
) {
    data class Data(
        @SerializedName("orderHlist") var orderHlist: List<OrderHlist>,
        @SerializedName("account") var account: String, //17326945212
        @SerializedName("holdno") var holdno: Any, //null
        @SerializedName("systemtime") var systemtime: Any //null
    ) {
        data class OrderHlist(
            @SerializedName("indexdate") var indexdate: String, //20180529
            @SerializedName("orderno") var orderno: String, //2799
            @SerializedName("stkcode") var stkcode: String, //ARW1
            @SerializedName("stkname") var stkname: String, //奔富干红1件
            @SerializedName("bsflag") var bsflag: String, //2
            @SerializedName("orderstate") var orderstate: String, //4
            @SerializedName("orderqty") var orderqty: String, //1
            @SerializedName("opentime") var opentime: String, //2018/5/29 21:42:10
            @SerializedName("closetime") var closetime: String, //2018/5/29 21:46:20
            @SerializedName("openprice") var openprice: String, //3345.30000
            @SerializedName("closeprice") var closeprice: String, //3346.90000
            @SerializedName("profitprice") var profitprice: Any, //null
            @SerializedName("stopprice") var stopprice: Any, //null
            @SerializedName("holdmargin") var holdmargin: String, //8.00
            @SerializedName("matchfee") var matchfee: String, //0.70
            @SerializedName("holdfee") var holdfee: String, //0.00
            @SerializedName("closeprofit") var closeprofit: String, //1.60
            @SerializedName("tyqflag") var tyqflag: String, //N
            @SerializedName("shoppoints") var shoppoints: String, //8
            @SerializedName("holdno") var holdno: String //815197
        )
    }
}