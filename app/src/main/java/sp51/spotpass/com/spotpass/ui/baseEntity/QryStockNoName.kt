package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class QryStockNoName(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: List<Data>
) {

    data class Data(
            @SerializedName("quote") var quote: String, // GBP
            @SerializedName("price") var price: String, // 3597.9
            @SerializedName("floatPrice") var floatPrice: String, // 18.1
            @SerializedName("floatPercent") var floatPercent: String, // 0.5
            @SerializedName("lastPrice") var lastPrice: String = "0.0" // 0.5
    )
}