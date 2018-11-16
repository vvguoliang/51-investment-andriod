package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class Untie(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("account") var account: String, // 13717578792
            @SerializedName("holdno") var holdno: Any, // null
            @SerializedName("systemtime") var systemtime: Any // null
    )
}