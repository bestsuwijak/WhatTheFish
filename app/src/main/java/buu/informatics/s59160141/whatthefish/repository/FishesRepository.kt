package buu.informatics.s59160141.whatthefish.repository

import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.Transformations
import buu.informatics.s59160141.whatthefish.database.*
import buu.informatics.s59160141.whatthefish.models.Fish
import buu.informatics.s59160141.whatthefish.services.FishsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import timber.log.Timber

class FishesRepository(private val database: FishesDatabase) {

    val fishes: List<Fish> = database.fishDao.getFishes().asModelFish()

    fun searchMulti(query: String): List<Fish> {
//                                                                                        Log.i("testdatabase", "search is start")
        val inputData = database.fishDao.multiFind("%${query}%").asModelFish()
//                                                                                        Log.i("testdatabase", "search is getdata")
        if (inputData.isNotEmpty()){
//                                                                                    Log.i("testdatabase", "search is input not empty")
        var outputData: ArrayList<Fish> = arrayListOf(inputData[0])
        for (i in 0 until (inputData.size)){
//                                                                                    Log.i("testdatabase", "search is begin loop")
            val row = outputData.find {it.number.startsWith(inputData[i].number)}
//                                                                                    Log.i("testdatabase", "search is after val row")
            if (row == null){
//                                                                                        Log.i("testdatabase", "search is added")
                outputData.add(inputData[i])
            }else{
//                                                                                        Log.i("testdatabase", "search is row not null")
            }
        }
//                                                                                    Log.i("testdatabase", "search is fished add return")
            return outputData as List<Fish>
        }
//                                                                                Log.i("testdatabase", "search is null")
        return database.fishDao.multiFind("%${query}%").asModelFish()
    }

    fun searchNumber(query: String): List<Fish> {
        return database.fishDao.numberFind(query).asModelFish()

    }


    suspend fun resetFishes() {
        withContext(Dispatchers.IO) {
            Timber.d("reset fishes is called")
            val getFishsDeferred = FishsApi.retrofitService.getFishs().await()

            var countTh = 0
            var countEn = 0
            var countTe = 0
            var countIm = 0

//            Log.i("testdatabase", getFishsDeferred[0].toString())

            for (i in 0 until (getFishsDeferred.size)) {
                database.fishDao.insertDatabaseFishes(
                    DatabaseFishes(
                        "fis${i}",
                        getFishsDeferred[i].scienceName,
                        getFishsDeferred[i].appearance,
                        getFishsDeferred[i].habitat,
                        getFishsDeferred[i].dissemination,
                        getFishsDeferred[i].editedAt,
                        getFishsDeferred[i].createdAt,
                        DatabaseModel(
                            getFishsDeferred[i].model.name,
                            getFishsDeferred[i].model.size
                        ),
                        DatabaseIcon(getFishsDeferred[i].icon.name, getFishsDeferred[i].icon.size),
                        getFishsDeferred[i].number,
                        getFishsDeferred[i].status
                    )
                )
                for (j in 0 until (getFishsDeferred[i].thNames.size)) {
                    database.fishDao.insertDatabaseThNames(
                        DatabaseThNames(
                            thNameId = "thN${countTh}",
                            thNameOwnerId = "fis${i}",
                            thName = getFishsDeferred[i].thNames[j]
                        )
                    )
                    countTh++
                }
                for (j in 0 until (getFishsDeferred[i].engNames.size)) {
                    database.fishDao.insertDatabaseEngNames(
                        DatabaseEngNames(
                            engNameId = "eng${countEn}",
                            engNameOwnerId = "fis${i}",
                            engName = getFishsDeferred[i].engNames[j]
                        )
                    )
                    countEn++
                }
                for (j in 0 until (getFishsDeferred[i].textureImages.size)) {
                    database.fishDao.insertDatabaseTextureImages(
                        DatabaseTextureImages(
                            databaseTextureImageId = "tex${countTe}",
                            textureImageOwnerId = "fis${i}",
                            nameTextureImage = getFishsDeferred[i].textureImages[j].name,
                            sizeTextureImage = getFishsDeferred[i].textureImages[j].size
                        )
                    )
                    countTe++
                }
                for (j in 0 until (getFishsDeferred[i].images.size)) {
                    database.fishDao.insertDatabaseImages(
                        DatabaseImages(
                            databaseImageId = "ima${countIm}",
                            imageOwnerId = "fis${i}",
                            nameImage = getFishsDeferred[i].images[j].name,
                            sizeImage = getFishsDeferred[i].images[j].size
                        )
                    )
                    countIm++
                }
            }
        }
    }
}