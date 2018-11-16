package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class NormalQuestionsDetail(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("content") var content: String, // 常见问题1答案
            @SerializedName("title") var title: String, // 常见问题1
            @SerializedName("updated_time") var updatedTime: String // 2018-06-12 23:06:28
    )
}