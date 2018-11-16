package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class InFo(
        @SerializedName("return_code") var returnCode: Int, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("url") var url: String, // http://51.loc/packages/test.apk
            @SerializedName("platform") var platform: Platform
    ) {

        data class Platform(
                @SerializedName("wechat") var wechat: Wechat,
                @SerializedName("qq") var qq: Qq
        ) {

            data class Wechat(
                    @SerializedName("app_id") var appId: String, // wechatid
                    @SerializedName("app_key") var appKey: String, // wechatXXX
                    @SerializedName("title") var title: String, // 微信标题
                    @SerializedName("description") var description: String // 微信描述
            )


            data class Qq(
                    @SerializedName("app_id") var appId: String, // qqid
                    @SerializedName("app_key") var appKey: String, // qqXXX
                    @SerializedName("title") var title: String, // qq标题
                    @SerializedName("description") var description: String // qq描述
            )
        }
    }
}