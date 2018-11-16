package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName
data class Cardlist(
    @SerializedName("bankId") var bankId: String,
    @SerializedName("cardNo") var cardNo: String,
    @SerializedName("cerName") var cerName: String,
    @SerializedName("cerNo") var cerNo: String,
    @SerializedName("bankName") var bankName: String,
    @SerializedName("province") var province: String,
    @SerializedName("city") var city: String,
    @SerializedName("branchName") var branchName: String
)