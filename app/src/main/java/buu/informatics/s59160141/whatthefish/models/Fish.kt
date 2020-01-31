package buu.informatics.s59160141.whatthefish.models

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class Fish(
    @SerializedName("thNames") val thNames: List<String>,
    @SerializedName("engNames") val engNames: List<String>,
    @SerializedName("scienceName") val scienceName: String,
    @SerializedName("appearance") val appearance: String,
    @SerializedName("habitat") val habitat: String,
    @SerializedName("dissemination") val dissemination: String,
    @SerializedName("editedAt") val editedAt: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("textureImages") val textureImages: List<TextureImage>,
    @SerializedName("images") val images: List<Image>,
    @SerializedName("model") val model: Model,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("number") val number: String,
    @SerializedName("status") var status: Int = 0

)