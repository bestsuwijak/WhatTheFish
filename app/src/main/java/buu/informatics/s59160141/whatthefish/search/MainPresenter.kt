package buu.informatics.s59160141.whatthefish.search

import android.util.Log
import buu.informatics.s59160141.whatthefish.models.Fishs
import buu.informatics.s59160141.whatthefish.services.FishsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainPresenter(private val view: MainView) {

//    var retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("http://10.0.2.2/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    lateinit var fishList: List<Fishs>

    fun searchUser(q: String?) {
        coroutineScope.launch {
            var getFishsDeferred = FishsApi.retrofitService.getFishs()
            try {
                var listResult = getFishsDeferred.await()
                fishList = listResult
//                Log.i("test123", "result: ${userList[0].engName[0]}")
                view.setAdapterData(fishList)
            } catch (e: Exception) {
                Log.i("", "fail: ${e.message}")
            }
        }
    }

}