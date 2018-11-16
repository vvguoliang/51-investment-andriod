package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class AllPendingOrder(
        @SerializedName("return_code") var returnCode: String = "", // 100
        @SerializedName("return_msg") var returnMsg: String = "", // 成功
        @SerializedName("data") var data: Data = Data()
) {

    data class Data(
            @SerializedName("current_page") var currentPage: Int = 0, // 1
            @SerializedName("data") var data: List<Data> = listOf(),
            @SerializedName("first_page_url") var firstPageUrl: String = "", // http://www.youjifarm.cn/api/v1/Exchang/allPendingOrder?page=1
            @SerializedName("from") var from: Int = 0, // 1
            @SerializedName("last_page") var lastPage: Int = 0, // 1
            @SerializedName("last_page_url") var lastPageUrl: String = "", // http://www.youjifarm.cn/api/v1/Exchang/allPendingOrder?page=1
            @SerializedName("next_page_url") var nextPageUrl: Any = Any(), // null
            @SerializedName("path") var path: String = "", // http://www.youjifarm.cn/api/v1/Exchang/allPendingOrder
            @SerializedName("per_page") var perPage: Int = 0, // 15
            @SerializedName("prev_page_url") var prevPageUrl: Any = Any(), // null
            @SerializedName("to") var to: Int = 0, // 5
            @SerializedName("total") var total: Int = 0 // 5
    ) {

        data class Data(
                @SerializedName("qtecode") var qtecode: String = "", // ERB
                @SerializedName("stkcode") var stkcode: String = "", // ERB2
                @SerializedName("bsflag") var bsflag: Int = 0, // 1
                @SerializedName("orderprice") var orderprice: Double = 0.0, // 2541.2
                @SerializedName("orderqty") var orderqty: Int = 0, // 20
                @SerializedName("losspoints") var losspoints: String = "", // 0.00
                @SerializedName("profitpoints") var profitpoints: String = "", // 0.00
                @SerializedName("qtecode_name") var qtecodeName: String = "", // 欧洲红酒
                @SerializedName("created_time") var createdTime: String = "" // 2018-06-24 21:47:13
        )
    }
}