package buu.informatics.s59160141.whatthefish.models

import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("name") val name: String,
    @SerializedName("size") val size: Double
)