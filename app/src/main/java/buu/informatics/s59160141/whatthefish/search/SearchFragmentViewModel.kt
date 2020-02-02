package buu.informatics.s59160141.whatthefish.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.informatics.s59160141.whatthefish.database.getDatabase
import buu.informatics.s59160141.whatthefish.models.Fish
import buu.informatics.s59160141.whatthefish.repository.FishesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchFragmentViewModel(application: Application, private val view: MainView) : AndroidViewModel(application) {

    private val fishesRepository = FishesRepository(getDatabase(application))
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun multiSearch(query: String) {
//        lateinit var fishList: List<Fish> =
        viewModelScope.launch {
            try {
                val fishList = fishesRepository.searchMulti(query)
//                val fishList = fishesRepository.fishes
//                Log.i("testdatabase", fishList.toString())
                view.setAdapterData(fishList)
//                fishList = fishesRepository.fishes
//                Log.i("test123", "result: ${userList[0].engName[0]}")

            } catch (e: Throwable) {
                Timber.d("search fail")
            }

        }
//        return fishList
    }


    class Factory(val app: Application, private val view: MainView) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchFragmentViewModel(app, view) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}