package buu.informatics.s59160141.whatthefish.qrcode

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
import java.lang.IllegalArgumentException

class QRViewModel(application: Application) : AndroidViewModel(application) {

    private val fishesRepository = FishesRepository(getDatabase(application))
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun searchQR(query: String): Fish? {
        val result = fishesRepository.searchNumber(query)
        if (result != null){
            return result
        }
        return null
    }

    fun updateFoundFish(query: String, dateFound: String, timeFound: String){
        fishesRepository.updateFoundFish(query, dateFound, timeFound)
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QRViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return QRViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}