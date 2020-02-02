package buu.informatics.s59160141.whatthefish.Intro

import android.app.Application
import android.util.Log
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
import java.io.IOException

class Intro3ViewModel (application: Application) : AndroidViewModel(application){

    private val fishesRepository = FishesRepository(getDatabase(application))
    val playlist = fishesRepository.fishes
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)



    fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                if (playlist.isEmpty()) {
                    fishesRepository.resetFishes()
                    Log.i("testdatabase", "refresh database")
                }else{
                    Log.i("testdatabase", "no refresh database")
                }

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                Timber.d(networkError.toString())
            }
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(Intro3ViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return Intro3ViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}