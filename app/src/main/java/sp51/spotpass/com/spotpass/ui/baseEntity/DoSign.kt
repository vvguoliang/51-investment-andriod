package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class DoSign(
        @SerializedName("return_code") var returnCode: String = "", // 100
        @SerializedName("return_msg") var returnMsg: String = "", // 签到成功
        @SerializedName("data") var data: Data = Data()
) {

    data class Data(
            @SerializedName("point") var point: Int = 0, // 40
            @SerializedName("days") var days: Int = 0, // 2
            @SerializedName("nextPoint") var nextPoint: Int = 0 // 70
    )
}