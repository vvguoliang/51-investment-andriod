package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class QryKprice(
        @SerializedName("return_code") var returnCode: Int, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("time") var time: String, // 21:07
            @SerializedName("openPrice") var openPrice: String, // 3971
            @SerializedName("closePrice") var closePrice: String, // 3971.8
            @SerializedName("highPrice") var highPrice: String, // 3972.3
            @SerializedName("lowPrice") var lowPrice: String // 3970.8
    )
}