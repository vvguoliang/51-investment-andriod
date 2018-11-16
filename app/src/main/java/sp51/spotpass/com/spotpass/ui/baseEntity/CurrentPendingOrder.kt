package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class CurrentPendingOrder(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("current_page") var currentPage: Int, // 1
            @SerializedName("data") var data: List<Data>,
            @SerializedName("first_page_url") var firstPageUrl: String, // http://www.dcdsinternet.com/api/v1/Exchang/currentPendingOrder?page=1
            @SerializedName("from") var from: Int, // 1
            @SerializedName("last_page") var lastPage: Int, // 1
            @SerializedName("last_page_url") var lastPageUrl: String, // http://www.dcdsinternet.com/api/v1/Exchang/currentPendingOrder?page=1
            @SerializedName("next_page_url") var nextPageUrl: Any, // null
            @SerializedName("path") var path: String, // http://www.dcdsinternet.com/api/v1/Exchang/currentPendingOrder
            @SerializedName("per_page") var perPage: Int, // 15
            @SerializedName("prev_page_url") var prevPageUrl: Any, // null
            @SerializedName("to") var to: Int, // 2
            @SerializedName("total") var total: Int // 2
    ) {

        data class Data(
                @SerializedName("id") var id: Int, // 12
                @SerializedName("qtecode") var qtecode: String, // ERB2
                @SerializedName("stkcode") var stkcode: String, // ERB2
                @SerializedName("bsflag") var bsflag: Int, // 1
                @SerializedName("price") var price: String, // 8.00
                @SerializedName("orderprice") var orderprice: Double, // 3984.6
                @SerializedName("orderqty") var orderqty: Int, // 1
                @SerializedName("losspoints") var losspoints: String, // 0.76
                @SerializedName("profitpoints") var profitpoints: String, // 0.76
                @SerializedName("qtecode_name") var qtecodeName: String, // 欧洲红酒
                @SerializedName("created_time") var createdTime: String // 2018-06-30 14:46:50
        )
    }
}