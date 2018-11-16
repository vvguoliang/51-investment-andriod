package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class GetYYOutCashList(
        @SerializedName("return_code") var returnCode: String = "", // 100
        @SerializedName("return_msg") var returnMsg: String = "", // 成功
        @SerializedName("data") var data: Data = Data()
) {

    data class Data(
            @SerializedName("YYOutCashList") var yYOutCashList: List<YYOutCash> = listOf(),
            @SerializedName("holdno") var holdno: Any = Any(), // null
            @SerializedName("systemtime") var systemtime: Any = Any() // null
    ) {

        data class YYOutCash(
                @SerializedName("cardNo") var cardNo: String = "", // 6228480018673668572
                @SerializedName("bankName") var bankName: String = "", // 中国农业银行
                @SerializedName("subBankName") var subBankName: String = "", // 中国农业银行
                @SerializedName("usrname") var usrname: String = "", // 秦国良
                @SerializedName("bankType") var bankType: String = "", // 103
                @SerializedName("usrtype") var usrtype: String = "", // 1
                @SerializedName("iscitywide") var iscitywide: String = "", // 1
                @SerializedName("applycapital") var applycapital: String = "", // 10.00
                @SerializedName("paycapital") var paycapital: String = "", // 10.00
                @SerializedName("applystate") var applystate: String = "", // 1
                @SerializedName("applytime") var applytime: String = "", // 2018-06-22T20:31:35
                @SerializedName("checktime") var checktime: String = "" // 1900-01-01T00:00:00
        )
    }
}