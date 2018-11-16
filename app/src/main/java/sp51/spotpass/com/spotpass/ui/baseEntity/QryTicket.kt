package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class QryTicket(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("ticketlist") var ticketlist: List<Ticketlist>,
            @SerializedName("holdno") var holdno: Any, // null
            @SerializedName("systemtime") var systemtime: Any // null
    ) {

        data class Ticketlist(
                @SerializedName("pzcode") var pzcode: String, // Q500
                @SerializedName("pzname") var pzname: String, // 500元体验券
                @SerializedName("excount") var excount: String, // 10
                @SerializedName("Qprice") var qprice: String, // 500.00
                @SerializedName("enddate") var enddate: String // 2018/6/20 0:00:00
        )
    }
}