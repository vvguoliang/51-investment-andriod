package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class Markey(
    @SerializedName("return_code") var returnCode: String, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("data") var data: List<Data>
) {
    data class Data(
        @SerializedName("exchange_id") var exchangeId: Int, //1
        @SerializedName("name") var name: String, //英国香水
        @SerializedName("qtecode") var qtecode: String //GBP
    )
}