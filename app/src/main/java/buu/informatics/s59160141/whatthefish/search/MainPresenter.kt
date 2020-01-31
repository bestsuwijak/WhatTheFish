package buu.informatics.s59160141.whatthefish.search

import android.util.Log
import buu.informatics.s59160141.whatthefish.database.getDatabase
import buu.informatics.s59160141.whatthefish.models.Fish
import buu.informatics.s59160141.whatthefish.repository.FishesRepository
import buu.informatics.s59160141.whatthefish.services.FishsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainPresenter(private val view: MainView) {

    //    var retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("http://10.0.2.2/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//    private val fishesRepository = FishesRepository(getDatabase(this))
//    val playlist = fishesRepository.fishes
//    private var viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//    fun searchUser(q: String?) {
//        coroutineScope.launch {
//            try {
//                val playlist = fishesRepository.fishes
////                Log.i("test123", "result: ${userList[0].engName[0]}")
//                view.setAdapterData(playlist)
//            } catch (e: Exception) {
//                Log.i("", "fail: ${e.message}")
//            }
//        }
//    }

}