package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class GetTopcaRousels(
    @SerializedName("return_code") var returnCode: Int, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("data") var data: List<Data>
) {
    data class Data(
        @SerializedName("id") var id: Int, //2
        @SerializedName("title") var title: String, //carousel
        @SerializedName("img") var img: String, //http://51.loc/uploads/images/694e195695611fc26340481abd276060.png
        @SerializedName("weight") var weight: Int, //1
        @SerializedName("updated_at") var updatedAt: String, //1527752031
        @SerializedName("description") var description: String //1527752031
    )
}