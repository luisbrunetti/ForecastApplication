package com.example.mvvmpractice.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmpractice.data.db.dao.CurrentWeatherDao
import com.example.mvvmpractice.data.db.entity.Current

@Database(
    entities = [Current::class],
    version = 1
)
abstract class ForecastDatabase: RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object{
        @Volatile private var instance : ForecastDatabase? = null
        private val LOCK = Any() //Dummy Object to check that two thread are not doing the same thing
        operator fun invoke(context:Context) = instance ?: synchronized(LOCK){ //Check if instance if not null. If not null just call instance varaible
            //On other hand if it's null it will call synchronized block. There
            instance ?: buildDatabase(context).also { instance = it } //If here is initialized return instance variable

        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java,"forecast.db").
            build()
    }
}