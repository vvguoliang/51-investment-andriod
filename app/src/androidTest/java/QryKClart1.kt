package <default> (D:\SpotPass\SpotPass\app\src\androidTest\java)

import com.google.gson.annotations.SerializedName

data class QryKClart1(
    @SerializedName("return_code") var returnCode: String, //100
    @SerializedName("return_msg") var returnMsg: String, //成功
    @SerializedName("list") var list: List
) {
    data class List(
        @SerializedName("qtecode") var qtecode: String, //ARW
        @SerializedName("count") var count: String, //120
        @SerializedName("sourceArray") var sourceArray: List<SourceArray>,
        @SerializedName("account") var account: Any, //null
        @SerializedName("holdno") var holdno: Any, //null
        @SerializedName("systemtime") var systemtime: Any //null
    ) {
        data class SourceArray(
            @SerializedName("time") var time: String, //03:59
            @SerializedName("price") var price: String //3366.4
        )
    }
}