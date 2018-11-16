package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class Httpwallstreetcn(
        @SerializedName("code") var code: Int = 0, // 200
        @SerializedName("data") var data: Data = Data()
) {

    data class Data(
            @SerializedName("snapshot") var snapshot: Snapshot = Snapshot()
    ) {

        data class Snapshot(
                @SerializedName("AUDUSD") var aUDUSD: List<String> = listOf(),
                @SerializedName("EURUSD") var eURUSD: List<String> = listOf(),
                @SerializedName("GBPUSD") var gBPUSD: List<String> = listOf(),
                @SerializedName("NZDUSD") var nZDUSD: List<String> = listOf(),
                @SerializedName("USDCAD") var uSDCAD: List<String> = listOf(),
                @SerializedName("USDCHF") var uSDCHF: List<String> = listOf(),
                @SerializedName("USDJPY") var uSDJPY: List<String> = listOf(),
                @SerializedName("USDOLLARINDEX") var uSDOLLARINDEX: List<String> = listOf()
        )
    }
}