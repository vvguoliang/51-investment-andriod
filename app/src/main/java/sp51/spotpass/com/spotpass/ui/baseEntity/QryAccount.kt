package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class QryAccount(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("regtime") var regtime: Any, // null
            @SerializedName("qbid") var qbid: Any, // null
            @SerializedName("balance") var balance: String, // 100000.00
            @SerializedName("holdmargin") var holdmargin: String, // 0.00
            @SerializedName("frozenmargin") var frozenmargin: String, // 0.00
            @SerializedName("incapital") var incapital: String, // 0.00
            @SerializedName("outcapital") var outcapital: String, // 0.00
            @SerializedName("closeprofit") var closeprofit: String, // 0.00
            @SerializedName("matchfee") var matchfee: String, // 0.00
            @SerializedName("holdfee") var holdfee: String, // 0.00
            @SerializedName("lastprofit") var lastprofit: String, // 0.00
            @SerializedName("initasset") var initasset: String, // 100000.00
            @SerializedName("shoppoints") var shoppoints: String, // 0
            @SerializedName("account") var account: String, // 13717578792
            @SerializedName("holdno") var holdno: Any, // null
            @SerializedName("systemtime") var systemtime: Any, // null
            @SerializedName("avatar") var avatar: String, // http://www.youjifarm.cn/storage/images/c42cdf2fd35ede0faf174744485d59a6.png
            @SerializedName("name") var name: String, // 张靓颖
            @SerializedName("exchangeName") var exchangeName: String, // 果立商城
            @SerializedName("holdingPrice") var holdingPrice: String, // 0
            @SerializedName("totalPrice") var totalPrice: String, // 100000
            @SerializedName("couponCount") var couponCount: String // 40
    )
}