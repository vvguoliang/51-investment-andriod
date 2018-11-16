package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class NormalQuestionsList(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("current_page") var currentPage: Int, // 1
            @SerializedName("data") var data: List<Data>,
            @SerializedName("first_page_url") var firstPageUrl: String, // http://www.youjifarm.cn/api/v1/Home/normalQuestionsList?page=1
            @SerializedName("from") var from: Int, // 1
            @SerializedName("last_page") var lastPage: Int, // 1
            @SerializedName("last_page_url") var lastPageUrl: String, // http://www.youjifarm.cn/api/v1/Home/normalQuestionsList?page=1
            @SerializedName("next_page_url") var nextPageUrl: Any, // null
            @SerializedName("path") var path: String, // http://www.youjifarm.cn/api/v1/Home/normalQuestionsList
            @SerializedName("per_page") var perPage: Int, // 15
            @SerializedName("prev_page_url") var prevPageUrl: Any, // null
            @SerializedName("to") var to: Int, // 1
            @SerializedName("total") var total: Int // 1
    ) {

        data class Data(
                @SerializedName("id") var id: Int, // 4
                @SerializedName("title") var title: String // 常见问题1
        )
    }
}