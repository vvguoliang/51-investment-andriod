package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class MessageDetail(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("id") var id: String, // 5bc7ba5d-5be5-41d5-a121-ead3b37d2e65
            @SerializedName("data") var data: Data,
            @SerializedName("read_at") var readAt: Any, // null
            @SerializedName("created_time") var createdTime: String // 2018-06-13 19:59:44
    ) {

        data class Data(
                @SerializedName("title") var title: String, // 买跌建仓
                @SerializedName("content") var content: String // 买涨单，您已买入XX产品，XX规格，XX手。
        )
    }
}