package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class GetVouchersList(
        @SerializedName("return_code") var returnCode: Int, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: List<Data>
) {

    data class Data(
            @SerializedName("id") var id: Int, // 2
            @SerializedName("exchange_id") var exchangeId: Int, // 1
            @SerializedName("name") var name: String, // 100元券
            @SerializedName("price") var price: Int, // 100
            @SerializedName("status") var status: Int, // 1
            @SerializedName("created_at") var createdAt: String, // 1528352204
            @SerializedName("updated_at") var updatedAt: String // 1528352204
    )
}