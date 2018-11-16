package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class BankList(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: List<Data>
) {

    data class Data(
            @SerializedName("id") var id: Int, // 809
            @SerializedName("name") var name: String, // 兴业银行
            @SerializedName("url") var url: String,
            @SerializedName("bgcolor") var bgcolor: String
    )
}