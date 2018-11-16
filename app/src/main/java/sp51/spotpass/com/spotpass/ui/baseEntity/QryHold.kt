package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class QryHold(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("holdlist") var holdlist: List<Holdlist>,
            @SerializedName("holdno") var holdno: Any, // null
            @SerializedName("systemtime") var systemtime: Any, // null
            @SerializedName("total_price") var total_price: String // null

    ) {

        data class Holdlist(
                @SerializedName("holdno") var holdno: String, // 824827
                @SerializedName("stkcode") var stkcode: String, // ERB2
                @SerializedName("stkname") var stkname: String, // 中世纪骑士2件
                @SerializedName("bsflag") var bsflag: String, // 1
                @SerializedName("holdprice") var holdprice: String, // 4023.30000
                @SerializedName("holdqty") var holdqty: String, // 1
                @SerializedName("profitprice") var profitprice: String, // 4028.30000
                @SerializedName("stopprice") var stopprice: String, // 4019.50000
                @SerializedName("holdmargin") var holdmargin: String, // 8.00
                @SerializedName("opentime") var opentime: String, // 2018/6/12 22:16:13
                @SerializedName("tyqflag") var tyqflag: String, // N
                @SerializedName("current_price") var current_price: String // N
        )
    }
}