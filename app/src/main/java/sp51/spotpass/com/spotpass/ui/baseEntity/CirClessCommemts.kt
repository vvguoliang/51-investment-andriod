package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class CirClessCommemts(
    @SerializedName("return_code") var returnCode: Int, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("data") var data: Data
) {
    data class Data(
        @SerializedName("current_page") var currentPage: Int, //1
        @SerializedName("data") var data: List<Data>,
        @SerializedName("first_page_url") var firstPageUrl: String, //http://www.youjifarm.cn/api/v1/circless/get-circless-comments?page=1
        @SerializedName("from") var from: Int, //1
        @SerializedName("last_page") var lastPage: Int, //1
        @SerializedName("last_page_url") var lastPageUrl: String, //http://www.youjifarm.cn/api/v1/circless/get-circless-comments?page=1
        @SerializedName("next_page_url") var nextPageUrl: Any, //null
        @SerializedName("path") var path: String, //http://www.youjifarm.cn/api/v1/circless/get-circless-comments
        @SerializedName("per_page") var perPage: String, //15
        @SerializedName("prev_page_url") var prevPageUrl: Any, //null
        @SerializedName("to") var to: Int, //3
        @SerializedName("total") var total: Int //3
    ) {
        data class Data(
            @SerializedName("user_id") var userId: Int, //19
            @SerializedName("circles_id") var circlesId: Int, //1
            @SerializedName("content") var content: String, //查询构建器还可以用于编写连接语句，关于 SQL 的几种连接类型
            @SerializedName("created_at") var createdAt: String, //1527822382
            @SerializedName("created") var created: String, //4天前
            @SerializedName("user") var user: User
        ) {
            data class User(
                @SerializedName("id") var id: Int, //19
                @SerializedName("name") var name: String, //张艺谋
                @SerializedName("avatar") var avatar: String //http://www.youjifarm.cn/storage/images/c42cdf2fd35ede0faf174744485d59a6.png
            )
        }
    }
}