package buu.informatics.s59160141.whatthefish.database

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.room.*
import buu.informatics.s59160141.whatthefish.Intro.Intro3

@Dao
interface FishDao {
    @Transaction
    @Query("select * from database_fishes")
    fun getFishes(): List<DatabaseAll>


    @Transaction
    @Query("select * from database_fishes INNER JOIN eng_names ON engNameOwnerId = databaseFishId WHERE eng_name = :search")
    fun multiFind(search: String): List<DatabaseAll>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatabaseFishes( fish: DatabaseFishes)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertDatabaseThNames( thName: DatabaseThNames)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertDatabaseEngNames( engName: DatabaseEngNames)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertDatabaseTextureImages( textureImages: DatabaseTextureImages)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertDatabaseImages( image: DatabaseImages)
}

@Database(entities = [DatabaseFishes::class, DatabaseThNames::class, DatabaseEngNames::class, DatabaseTextureImages::class, DatabaseImages::class], version = 1)
abstract class FishesDatabase: RoomDatabase() {
    abstract val fishDao: FishDao
}

private lateinit var INSTANCE: FishesDatabase

fun getDatabase(context: Context): FishesDatabase {
    synchronized(FishesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                FishesDatabase::class.java,
                "fishes").build()
        }
    }
    return INSTANCE
}