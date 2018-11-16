package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class PublicReqLoign(
        @SerializedName("return_code") var returnCode: String, //100
        @SerializedName("return_msg") var returnMsg: String, //成功
        @SerializedName("data") var data: Data
) {
    data class Data(
            @SerializedName("regtime") var regtime: String?, //null
            @SerializedName("qbid") var qbid: String?, //-
            @SerializedName("balance") var balance: String?, //200017.90
            @SerializedName("holdmargin") var holdmargin: String?, //0.00
            @SerializedName("frozenmargin") var frozenmargin: String?, //0.00
            @SerializedName("incapital") var incapital: String?, //0.00
            @SerializedName("outcapital") var outcapital: String?, //0.00
            @SerializedName("closeprofit") var closeprofit: String?, //0.00
            @SerializedName("matchfee") var matchfee: String?, //0.00
            @SerializedName("holdfee") var holdfee: String?, //0.00
            @SerializedName("lastprofit") var lastprofit: String?, //0.00
            @SerializedName("initasset") var initasset: String?, //200017.90
            @SerializedName("shoppoints") var shoppoints: String?, //168
            @SerializedName("account") var account: String?, //17326945212
            @SerializedName("holdno") var holdno: Any?, //null
            @SerializedName("systemtime") var systemtime: Any?, //null
            @SerializedName("token") var token: String? //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjIsImlzcyI6Imh0dHA6Ly93d3cueW91amlmYXJtLmNuL2FwaS92MS9HYXRlV2F5L3B1YmxpY1JlcSIsImlhdCI6MTUyNzkzNDc2MCwiZXhwIjoxNTI3OTM4MzYwLCJuYmYiOjE1Mjc5MzQ3NjAsImp0aSI6IkhOZmtoYXRVSFVqdkVMT1cifQ.bNk8_LZjBXRO7QJvReV5UjlfWzYSgEIAmQ4kz12uLhY
    )
}