package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class MyBankCard(
        @SerializedName("return_code") var returnCode: String, //100
        @SerializedName("return_msg") var returnMsg: String, //成功
        @SerializedName("data") var data: Data
) {
    data class Data(
            @SerializedName("cardlist") var cardlist: List<Cardlist>,
            @SerializedName("account") var account: String, //17326945212
            @SerializedName("holdno") var holdno: Any, //null
            @SerializedName("systemtime") var systemtime: Any //null
    ) {
        data class Cardlist(
                @SerializedName("bankId") var bankId: String, //102
                @SerializedName("cardNo") var cardNo: String, //6212260241011812127
                @SerializedName("cerName") var cerName: String, //张三
                @SerializedName("cerNo") var cerNo: String, //130406198601021236
                @SerializedName("bankName") var bankName: String, //中国工商银行
                @SerializedName("province") var province: String, //-
                @SerializedName("city") var city: String, //-
                @SerializedName("branchName") var branchName: String, //-
                @SerializedName("url") var url: String //-
        )
    }
}