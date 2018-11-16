package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class CirclessList(
        @SerializedName("return_code") var returnCode: Int, //100
        @SerializedName("return_msg") var returnMsg: String, //成功
        @SerializedName("data") var data: Data
) {
    data class Data(
            @SerializedName("current_page") var currentPage: Int, //1
            @SerializedName("data") var data: List<Data>,
            @SerializedName("first_page_url") var firstPageUrl: String, //http://www.youjifarm.cn/api/v1/circless/get-circless-list?page=1
            @SerializedName("from") var from: Int, //1
            @SerializedName("last_page") var lastPage: Int, //1
            @SerializedName("last_page_url") var lastPageUrl: String, //http://www.youjifarm.cn/api/v1/circless/get-circless-list?page=1
            @SerializedName("next_page_url") var nextPageUrl: Any, //null
            @SerializedName("path") var path: String, //http://www.youjifarm.cn/api/v1/circless/get-circless-list
            @SerializedName("per_page") var perPage: String, //15
            @SerializedName("prev_page_url") var prevPageUrl: Any, //null
            @SerializedName("to") var to: Int, //2
            @SerializedName("total") var total: Int //2
    ) {
        data class Data(
                @SerializedName("id") var id: Int, //2
                @SerializedName("user_id") var userId: Int, //2
                @SerializedName("content") var content: String, //设置完可以被赋值的属性之后，我们就可以使用 create 方法在数据库中插入一条新的记录。create 方法返回保存后的模型实例：
                @SerializedName("comments_num") var commentsNum: Int, //5
                @SerializedName("likes_num") var likesNum: Int, //2
                @SerializedName("is_best") var isBest: Int, //1
                @SerializedName("created_at") var createdAt: String, //1527821244
                @SerializedName("created") var created: String, //2天前
                @SerializedName("imgs") var imgs: List<Imgs>,
                @SerializedName("comments") var comments: List<Comments>,
                @SerializedName("user") var user: User
        ) {
            data class User(
                    @SerializedName("id") var id: Int, //2
                    @SerializedName("name") var name: String, //分析师
                    @SerializedName("avatar") var avatar: String, //http://www.youjifarm.cn/storage/images/c42cdf2fd35ede0faf174744485d59a6.png
                    @SerializedName("role_name") var role_name: String, //http://www.youjifarm.cn/storage/images/c42cdf2fd35ede0faf174744485d59a6.png
                    @SerializedName("role") var role: String //http://www.youjifarm.cn/storage/images/c42cdf2fd35ede0faf174744485d59a6.png
            )

            data class Imgs(
                    @SerializedName("id") var id: Int, //2
                    @SerializedName("circles_id") var circles_id: Int, //2
                    @SerializedName("img") var img: String //"crcles/20180601105850.png"
            )

            data class Comments(
                    @SerializedName("id") var id: Int, //2
                    @SerializedName("circles_id") var circles_id: Int, //2
                    @SerializedName("content") var content: String, //2
                    @SerializedName("user_id") var user_id: Int, //2
                    @SerializedName("user") var user: User
            ) {
                data class User(
                        @SerializedName("id") var id: Int, //2
                        @SerializedName("name") var name: String //2
                )
            }
        }
    }
}