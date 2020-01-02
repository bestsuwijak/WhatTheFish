package buu.informatics.s59160141.whatthefish.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("total_count") val total_count: Int,
    @SerializedName("incomplete_results") val incomplete_results: Boolean,
    @SerializedName("items") val users: List<User>
)