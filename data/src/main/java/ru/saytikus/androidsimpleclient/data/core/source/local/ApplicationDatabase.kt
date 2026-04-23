package ru.saytikus.androidsimpleclient.data.core.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.saytikus.androidsimpleclient.data.core.profile.source.local.ProfileDao
import ru.saytikus.androidsimpleclient.data.core.profile.source.local.RoomProfile

@Database(

    entities = [
        RoomProfile::class
               ],

    version = 1,

    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    companion object {

        @Volatile private var INSTANCE: ApplicationDatabase? = null

        fun getInstance(context: Context): ApplicationDatabase = INSTANCE
            ?: synchronized(this) {

                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
        }

        fun buildDatabase(context: Context): ApplicationDatabase = Room.databaseBuilder(
            context.applicationContext,
            ApplicationDatabase::class.java,
            "SimpleClientStorage.db"
        )
            // TODO migration
            .fallbackToDestructiveMigration(false)
            .build()

    }

}