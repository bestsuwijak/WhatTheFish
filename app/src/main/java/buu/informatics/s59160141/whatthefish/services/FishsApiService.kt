package buu.informatics.s59160141.whatthefish.services

import buu.informatics.s59160141.whatthefish.models.Fish
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("http://thefishdev.buu.in.th/")
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface FishsApiService {
    @GET("api/fishs")
    fun getFishs(): Deferred<List<Fish>>
}

object FishsApi {
    val retrofitService : FishsApiService by lazy { retrofit.create(FishsApiService::class.java) }
}