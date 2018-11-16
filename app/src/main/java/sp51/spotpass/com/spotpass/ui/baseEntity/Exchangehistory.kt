package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class Exchangehistory(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("current_page") var currentPage: Int, // 1
            @SerializedName("data") var data: List<Data>,
            @SerializedName("first_page_url") var firstPageUrl: String, // http://www.dcdsinternet.com/api/v1/Account/exchange-history?page=1
            @SerializedName("from") var from: Int, // 1
            @SerializedName("last_page") var lastPage: Int, // 1
            @SerializedName("last_page_url") var lastPageUrl: String, // http://www.dcdsinternet.com/api/v1/Account/exchange-history?page=1
            @SerializedName("next_page_url") var nextPageUrl: Any, // null
            @SerializedName("path") var path: String, // http://www.dcdsinternet.com/api/v1/Account/exchange-history
            @SerializedName("per_page") var perPage: Int, // 15
            @SerializedName("prev_page_url") var prevPageUrl: Any, // null
            @SerializedName("to") var to: Int, // 13
            @SerializedName("total") var total: Int // 13
    ) {

        data class Data(
                @SerializedName("created_time") var createdTime: String, // 2018-06-06 23:52:33
                @SerializedName("type_name") var typeName: String // 兑换8元券
        )
    }
}