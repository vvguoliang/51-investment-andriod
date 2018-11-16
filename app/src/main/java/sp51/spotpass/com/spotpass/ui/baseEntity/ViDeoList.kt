package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class ViDeoList(
    @SerializedName("return_code") var returnCode: Int, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("data") var data: Data
) {
    data class Data(
        @SerializedName("current_page") var currentPage: Int, //1
        @SerializedName("data") var data: List<Data>,
        @SerializedName("first_page_url") var firstPageUrl: String, //http://www.youjifarm.cn/api/v1/curriculum/get-video-list?page=1
        @SerializedName("from") var from: Int, //1
        @SerializedName("last_page") var lastPage: Int, //1
        @SerializedName("last_page_url") var lastPageUrl: String, //http://www.youjifarm.cn/api/v1/curriculum/get-video-list?page=1
        @SerializedName("next_page_url") var nextPageUrl: Any, //null
        @SerializedName("path") var path: String, //http://www.youjifarm.cn/api/v1/curriculum/get-video-list
        @SerializedName("per_page") var perPage: String, //15
        @SerializedName("prev_page_url") var prevPageUrl: Any, //null
        @SerializedName("to") var to: Int, //2
        @SerializedName("total") var total: Int //2
    ) {
        data class Data(
            @SerializedName("id") var id: Int, //2
            @SerializedName("title") var title: String, //股市大全2
            @SerializedName("video_url") var videoUrl: String, //video/douying1.mp4
            @SerializedName("content") var content: String, //股市大全2
            @SerializedName("cover_url") var coverUrl: String, //crcles/20180601105850.png
            @SerializedName("learn_num") var learnNum: Int, //2
            @SerializedName("created_at") var createdAt: String //0
        )
    }
}