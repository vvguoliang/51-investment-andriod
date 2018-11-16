package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class QryKChart(
        @SerializedName("return_code") var returnCode: String, //100
        @SerializedName("return_msg") var returnMsg: String, //成功
        @SerializedName("data") var data: Data
) {
    data class Data(
            @SerializedName("sourceArray") var sourceArray: List<SourceArray>
    ) {
        data class SourceArray(
                @SerializedName("time") var time: String, //2018-06-02 04:00:00
                @SerializedName("openPrice") var openPrice: String = "0.0", //3366
                @SerializedName("closePrice") var closePrice: String = "0.0", //3366.4
                @SerializedName("highPrice") var highPrice: String = "0.0", //3366.4
                @SerializedName("lowPrice") var lowPrice: String = "0.0", //3365.6
                @SerializedName("price") val price: String = "0.0"
        )
    }
}