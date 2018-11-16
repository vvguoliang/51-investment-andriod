package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class BeginerDetail(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("title") var title: String, // 交易规则
            @SerializedName("content") var content: String, // 交易规则
            @SerializedName("updated_time") var updatedTime: String // 2018-06-14 22:01:45
    )
}