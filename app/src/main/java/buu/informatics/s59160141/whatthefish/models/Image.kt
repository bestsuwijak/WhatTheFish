package buu.informatics.s59160141.whatthefish.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("name") val name: String,
    @SerializedName("size") val size: Double
)