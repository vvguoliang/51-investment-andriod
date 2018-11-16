package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class TodayPrice(
    @SerializedName("return_code") var returnCode: String, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("data") var data: Data
) {
    data class Data(
        @SerializedName("sourceArray") var sourceArray: SourceArray
    ) {
        data class SourceArray(
            @SerializedName("time") var time: String, //2018-06-01
            @SerializedName("openPrice") var openPrice: String, //3989.6
            @SerializedName("closePrice") var closePrice: String, //3977.3
            @SerializedName("highPrice") var highPrice: String, //3998.7
            @SerializedName("lowPrice") var lowPrice: String, //3964.3
            @SerializedName("yesterdayPrice") var yesterdayPrice: String //3989.3
        )
    }
}