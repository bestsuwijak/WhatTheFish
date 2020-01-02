package buu.informatics.s59160141.whatthefish.services

import buu.informatics.s59160141.whatthefish.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import io.reactivex.Observable

interface GithubAPI {
    @GET("search/users")
    fun searchUsers(@Query("q")q: String?): Observable<UserResponse>
}