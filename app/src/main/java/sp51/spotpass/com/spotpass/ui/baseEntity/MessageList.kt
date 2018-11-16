package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class MessageList(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: List<Data>
) {

    data class Data(
            @SerializedName("id") var id: String, // 4da7588a-63fa-4b23-a3a1-4abfa57055f0
            @SerializedName("data") var data: Data,
            @SerializedName("read_at") var readAt: Any, // null
            @SerializedName("created_time") var createdTime: String // 2018-06-09 21:57:57
    ) {

        data class Data(
                @SerializedName("title") var title: String, // 开户成功
                @SerializedName("content") var content: String // 恭喜您！成功在果立商城开户。
        )
    }
}