package ru.saytikus.androidsimpleclient.di

import android.content.Context
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.core.profile.source.local.ProfileDao
import ru.saytikus.androidsimpleclient.data.core.source.local.ApplicationDatabase
import kotlin.uuid.ExperimentalUuidApi

@Module
@OptIn(ExperimentalUuidApi::class)
class LocalStorageModule {

    @Single
    fun provideDatabase(content: Context): ApplicationDatabase {
        return ApplicationDatabase.Companion.buildDatabase(content)
    }


    @Single
    fun provideProfileDao(db: ApplicationDatabase): ProfileDao =
        db.profileDao()
}