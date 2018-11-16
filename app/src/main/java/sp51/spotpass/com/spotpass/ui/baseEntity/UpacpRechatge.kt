package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName


data class UpacpRechatge(
        @SerializedName("return_code") var returnCode: String, // 100
        @SerializedName("return_msg") var returnMsg: String, // 成功
        @SerializedName("data") var data: Data
) {

    data class Data(
            @SerializedName("submithtml") var submithtml: String, // <html><head><meta http-equiv="Content-Type" content="text/html; charset=System.Text.UTF8Encoding" /></head><body onload="OnLoadSubmit();"><form id="pay_form" action="https://gateway.95516.com/gateway/api/frontTransReq.do" method="post"><input type="hidden" name="version" id="version" value="5.0.0" /><input type="hidden" name="encoding" id="encoding" value="UTF-8" /><input type="hidden" name="txnType" id="txnType" value="01" /><input type="hidden" name="txnSubType" id="txnSubType" value="01" /><input type="hidden" name="bizType" id="bizType" value="000201" /><input type="hidden" name="signMethod" id="signMethod" value="01" /><input type="hidden" name="channelType" id="channelType" value="08" /><input type="hidden" name="accessType" id="accessType" value="0" /><input type="hidden" name="frontUrl" id="frontUrl" value="https://www.baidu.com" /><input type="hidden" name="backUrl" id="backUrl" value="http://sc.gllsce.com:8523/CallBackHandlers/upacpCallBackHandler.aspx" /><input type="hidden" name="currencyCode" id="currencyCode" value="156" /><input type="hidden" name="accType" id="accType" value="01" /><input type="hidden" name="accNo" id="accNo" value="6228480018673668572" /><input type="hidden" name="merId" id="merId" value="827261057220126" /><input type="hidden" name="orderId" id="orderId" value="8792201806151905126345" /><input type="hidden" name="txnTime" id="txnTime" value="20180615190512" /><input type="hidden" name="txnAmt" id="txnAmt" value="1000" /><input type="hidden" name="certId" id="certId" value="73708626979" /><input type="hidden" name="signature" id="signature" value="gdLH4TfQGpU4kFa/vOv72eEvwUC4BwzUN3UMcDPyNJleRC7ifxDzzEfJGbDN0fZtC1L4vmgxorI5UeZXi2+Rys/Nt5lkAolHYtX6EZlWZoXKjO8Rtr5h6jNi8VJcshzXbKHOBQ6KPDZlyiX6gqT2zzbzhNkyeiNw1SjvIAh4j+m5wCT8WFlQsT9wVd2xvTbgFs/9Uz8ctMTJKoMzJlgvlpc/lsVsTdpQFdcBkAxle+HZ+8eM9WbTkBnqmXG08c1nujPECHbmeJueOIH/QHhdjiBCi/+2zacPJDXx6LdlOalYKmNuN0i6qiybQIG6cHV1ZBtmgQrXEk+HwkIVAFd3pA==" /></form><script type="text/javascript">function OnLoadSubmit(){document.getElementById("pay_form").submit();}</script></body></html>
            @SerializedName("tn") var tn: Any, // null
            @SerializedName("orderNo") var orderNo: String, // 8792201806151905126345
            @SerializedName("account") var account: String, // 13717578792
            @SerializedName("holdno") var holdno: Any, // null
            @SerializedName("systemtime") var systemtime: Any // null
    )
}