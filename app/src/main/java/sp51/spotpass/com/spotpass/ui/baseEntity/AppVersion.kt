package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class AppVersion(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("version") var version: String, // 1.0
            @SerializedName("update_type") var updateType: Int, // 1
            @SerializedName("app_url") var appUrl: String
    )
}