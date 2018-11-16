package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class RechargAmount(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("recharg_amount") var rechargAmount: String, // 500,1000,1500,2000,2500,3000,3500,4000,4500
            @SerializedName("pay_return_url") var payReturnUrl: String
    )
}