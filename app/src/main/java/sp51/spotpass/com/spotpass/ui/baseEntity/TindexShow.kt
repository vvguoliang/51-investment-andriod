package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class TindexShow(
    @SerializedName("return_code") var returnCode: Int, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("data") var data: List<X>
) {
    data class X(
        @SerializedName("id") var id: Int, //2
        @SerializedName("user_id") var userId: Int, //2
        @SerializedName("content") var content: String, //设置完可以被赋值的属性之后，我们就可以使用 create 方法在数据库中插入一条新的记录。create 方法返回保存后的模型实例：
        @SerializedName("created_at") var createdAt: String, //1527821244
        @SerializedName("role_name") var roleName: String, //专家解读
        @SerializedName("created") var created: String, //8小时前
        @SerializedName("user") var user: User
    ) {
        data class User(
            @SerializedName("id") var id: Int, //2
            @SerializedName("name") var name: String, //王宝强
            @SerializedName("avatar") var avatar: String //http:\/\/www.youjifarm.cn\/storage\/images\/c42cdf2fd35ede0faf174744485d59a6.png
        )
    }
}