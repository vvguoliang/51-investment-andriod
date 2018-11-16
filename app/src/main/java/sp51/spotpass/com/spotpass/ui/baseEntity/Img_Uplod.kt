package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class Img_Uplod(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("file_url") var fileUrl: String // /storage/emulated/0/IMAGE/IMG_20180617_124341.jpg/d7a582266daba7f7f57b8d0ae3bf4038.jpeg
    )
}