package buu.informatics.s59160141.whatthefish.models

import com.google.gson.annotations.SerializedName

data class Fishs(
    @SerializedName("thNames") val thNames: List<String>,
    @SerializedName("engNames") val engNames: List<String>,
    @SerializedName("icon") val icon: String,
    @SerializedName("scienceName") val scienceName: String
)