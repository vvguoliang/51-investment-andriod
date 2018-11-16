package sp51.spotpass.com.spotpass.ui.baseEntity

import com.google.gson.annotations.SerializedName

data class X(
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("url") var url: String
)