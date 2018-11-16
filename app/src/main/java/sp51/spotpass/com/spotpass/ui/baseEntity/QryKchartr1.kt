package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class QryKchartr1(
        @SerializedName("return_code") var returnCode: Int, // 100
        @SerializedName("return_msg") var returnMsg: String, // 操作成功
        @SerializedName("data") var data: List<Data>
) {

    data class Data(
            @SerializedName("time") var time: Int, // 1530201180
            @SerializedName("price") var price: String, // 3270.3
            @SerializedName("openPrice") var openPrice: String, // 3270.3
            @SerializedName("closePrice") var closePrice: String, // 3270.3
            @SerializedName("highPrice") var highPrice: String, // 3270.3
            @SerializedName("lowPrice") var lowPrice: String // 3270.3
    )
}