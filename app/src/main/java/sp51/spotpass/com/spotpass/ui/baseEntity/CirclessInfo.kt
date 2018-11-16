package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class CirclessInfo(
    @SerializedName("return_code") var returnCode: Int, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("data") var data: Data
) {
    data class Data(
        @SerializedName("id") var id: Int, //1
        @SerializedName("user_id") var userId: Int, //3
        @SerializedName("content") var content: String, //当用户通过 HTTP 请求传递一个不被期望的参数值时就会出现安全隐患，然后该参数以不被期望的方式修改数据库中的字段值。例如，恶意用户通过 HTTP 请求发送一个 is_admin 参数，然后该参数映射到模型的 create 方法，从而允许用户将自己变成管理员。
        @SerializedName("comments_num") var commentsNum: Int, //10
        @SerializedName("likes_num") var likesNum: Int, //11
        @SerializedName("created_at") var createdAt: String, //1527821244
        @SerializedName("created") var created: String, //2018-06-01
        @SerializedName("imgs") var imgs: List<Img>,
        @SerializedName("likes") var likes: List<Like>,
        @SerializedName("user") var user: User
    ) {
        data class Like(
            @SerializedName("id") var id: Int, //1
            @SerializedName("circles_id") var circlesId: Int, //1
            @SerializedName("user_id") var userId: Int, //21
            @SerializedName("user") var user: User
        ) {
            data class User(
                @SerializedName("id") var id: Int, //21
                @SerializedName("avatar") var avatar: String //http://www.youjifarm.cn/storage/images/c42cdf2fd35ede0faf174744485d59a6.png
            )
        }
        data class User(
            @SerializedName("id") var id: Int, //3
            @SerializedName("role") var role: String, //专家
            @SerializedName("avatar") var avatar: String //http://www.youjifarm.cn/storage/images/c42cdf2fd35ede0faf174744485d59a6.png
        )
        data class Img(
            @SerializedName("id") var id: Int, //2
            @SerializedName("circles_id") var circlesId: Int, //1
            @SerializedName("img") var img: String //crcles/20180601105850.png
        )
    }
}