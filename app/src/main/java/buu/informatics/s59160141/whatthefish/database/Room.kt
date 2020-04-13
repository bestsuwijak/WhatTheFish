package buu.informatics.s59160141.whatthefish.database

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FishDao {
    @Transaction
    @Query("select * from database_fishes")
    fun getFishes(): List<DatabaseAll>

    @Transaction
    @Query("select * from database_fishes WHERE number = :search")
    fun numberFind(search: String): List<DatabaseAll>

    @Transaction
    @Query("SELECT * FROM database_fishes INNER JOIN eng_names ON databaseFishId = engNameOwnerId INNER JOIN th_names ON databaseFishId = thNameOwnerId WHERE eng_name LIKE :search OR scienceName LIKE :search OR th_name LIKE :search")
    fun multiFind(search: String): List<DatabaseAll>

    @Transaction
    @Query("UPDATE database_fishes SET foundFish = 1, stringDateFound = :dateFound, stringTimeFound = :timeFound WHERE number LIKE :number")
    fun updateFoundFish(number: String, dateFound: String, timeFound: String)

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
                "fishes").allowMainThreadQueries().build()
        }
    }
    return INSTANCE
}