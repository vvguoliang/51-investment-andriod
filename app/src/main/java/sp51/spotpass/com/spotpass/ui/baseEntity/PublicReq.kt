package sp51.spotpass.com.spotpass.ui.baseEntity

/**
 * 注册
 */

data class PublicReq(
    val capitalId: String,
    val sessionId: String,
    val dbid: Any,
    val return_code: String,
    val return_msg: String,
    val account: String,
    val holdno: Any,
    val systemtime: Any
)