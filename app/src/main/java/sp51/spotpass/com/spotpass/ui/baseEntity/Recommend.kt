package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class Recommend(
    @SerializedName("return_code") var returnCode: String, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("data") var data: Data
) {
    data class Data(
        @SerializedName("qtepricelist") var qtepricelist: List<Qtepricelist>,
        @SerializedName("account") var account: Any, //null
        @SerializedName("holdno") var holdno: Any, //null
        @SerializedName("systemtime") var systemtime: Any //null
    ) {
        data class Qtepricelist(
            @SerializedName("quote") var quote: String, //GBP
            @SerializedName("price") var price: String, //3655.2
            @SerializedName("floatPrice") var floatPrice: String, //15.9
            @SerializedName("floatPercent") var floatPercent: String, //0.4
            @SerializedName("quoteName") var quoteName: String, //英国香水
            @SerializedName("exchangeId") var exchangeId: Int //1
        )
    }
}