package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class QryQuoteTrdTime(
    @SerializedName("return_code") var returnCode: String, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("data") var data: Data
) {
    data class Data(
        @SerializedName("serverTime") var serverTime: String, //2018-06-05 22:24:30
        @SerializedName("isTrd") var isTrd: String, //Y
        @SerializedName("memo") var memo: String, //交易日
        @SerializedName("trdStatus") var trdStatus: String, //1
        @SerializedName("trdTime") var trdTime: String, //06:00#04:00,06:00
        @SerializedName("setTime") var setTime: String, //04:00#06:00
        @SerializedName("canTrd") var canTrd: String, //Y
        @SerializedName("account") var account: Any, //null
        @SerializedName("holdno") var holdno: Any, //null
        @SerializedName("systemtime") var systemtime: Any //null
    )
}