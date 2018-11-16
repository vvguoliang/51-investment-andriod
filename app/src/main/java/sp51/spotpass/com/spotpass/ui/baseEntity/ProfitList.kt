package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class ProfitList(
    @SerializedName("return_code") val returnCode: Int,
    @SerializedName("return_msg") val returnMsg: String,
    @SerializedName("data") val data: List<X>
) {
    data class X(
        @SerializedName("id") val id: Int,
        @SerializedName("username") val username: String,
        @SerializedName("head_img") val headImg: String,
        @SerializedName("percent") val percent: Int,
        @SerializedName("points") val points: Int,
        @SerializedName("order_no") val orderNo: Int
    )
}