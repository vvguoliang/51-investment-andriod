package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class ProfitNews(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: List<Data>
) {

    data class Data(
            @SerializedName("user_name") var userName: String, // 张小成
            @SerializedName("money") var money: String // 118.00
    )
}