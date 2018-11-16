package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class RechargeYunJu(
        @SerializedName("return_code") var returnCode: String = "", // 100
        @SerializedName("return_msg") var returnMsg: String = "", // 成功
        @SerializedName("data") var data: Data = Data()
) {

    data class Data(
            @SerializedName("payType") var payType: Any = Any(), // null
            @SerializedName("qrcode_url") var qrcodeUrl: Any = Any(), // null
            @SerializedName("pay_url") var payUrl: String = "", // https://pay.cloudfanit.cn/v1/pay/alipay/alipay_qr/6B543D69A68E937839D040115F7F1A10B8776E9355188D58
            @SerializedName("orderNo") var orderNo: String = "", // 879220180622204241330
            @SerializedName("holdno") var holdno: Any = Any(), // null
            @SerializedName("systemtime") var systemtime: Any = Any() // null
    )
}