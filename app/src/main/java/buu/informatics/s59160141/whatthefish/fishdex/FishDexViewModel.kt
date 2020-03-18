package buu.informatics.s59160141.whatthefish.fishdex

import android.app.Application
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

class FishDexViewModel(application: Application, private val view: fishDexView) :
    AndroidViewModel(application) {

    private val fishesRepository = FishesRepository(getDatabase(application))
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getFishes(){
        viewModelScope.launch {
            try {
                val fishList = fishesRepository.fishes
                view.setAdapterDataGrid(fishList)
            } catch (e: Throwable) {
                Timber.d("getFishes fail")
            }
        }
    }


    class Factory(val app: Application, val fishdexview: fishDexView) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FishDexViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FishDexViewModel(app, fishdexview) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}