package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class qrystock1(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: List<Data>
) {

    data class Data(
            @SerializedName("stkcode") var stkcode: String, // NI5
            @SerializedName("stkname") var stkname: String, // L镍0.4吨
            @SerializedName("stktype") var stktype: String, // 6
            @SerializedName("stksize") var stksize: String, // 0.4
            @SerializedName("marginrate") var marginrate: String, // 500
            @SerializedName("openfeerate") var openfeerate: String, // 0
            @SerializedName("closefeerate") var closefeerate: String, // 48
            @SerializedName("holdfeerate") var holdfeerate: String, // 6
            @SerializedName("unitprice") var unitprice: String, // 1
            @SerializedName("orderlimitqry") var orderlimitqry: String, // 10
            @SerializedName("sumlimitqry") var sumlimitqry: String, // 30
            @SerializedName("quotecode") var quotecode: String, // NI
            @SerializedName("losspoints") var losspoints: String, // 938
            @SerializedName("profitpoints") var profitpoints: String // 2500
    )
}