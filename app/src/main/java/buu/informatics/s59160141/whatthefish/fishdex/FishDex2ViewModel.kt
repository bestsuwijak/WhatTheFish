package buu.informatics.s59160141.whatthefish.fishdex

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.informatics.s59160141.whatthefish.database.getDatabase
import buu.informatics.s59160141.whatthefish.repository.FishesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

class FishDex2ViewModel(application: Application, private val a2View: fishDex2View) :
    AndroidViewModel(application) {

    private val fishesRepository = FishesRepository(getDatabase(application))
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getFishes(){
        viewModelScope.launch {
            try {
                val fishList = fishesRepository.fishes
                a2View.setAdapterDataGrid(fishList)
            } catch (e: Throwable) {
                Timber.d("getFishes fail")
            }
        }
    }


    class Factory(val app: Application, val fishdex2view: fishDex2View) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FishDex2ViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FishDex2ViewModel(app, fishdex2view) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}