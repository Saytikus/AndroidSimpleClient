package ru.saytikus.androidsimpleclient.data.core.profile.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Dao
interface ProfileDao {

    @Query("SELECT * FROM profiles WHERE userId = :profileId")
    suspend fun find(profileId: String): RoomProfile?

    @Transaction
    @Query("SELECT * FROM profiles")
    fun getAllProfiles(): Flow<List<RoomProfile>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfile(profile: RoomProfile)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProfile(profile: RoomProfile)

    @Query("DELETE FROM profiles WHERE userId = :profileUuid")
    suspend fun deleteProfile(profileUuid: String)

    @Query("""SELECT EXISTS(
        SELECT 1 FROM profiles WHERE userId = :profileUuid
        )""")
    suspend fun exists(profileUuid: String): Boolean

    @Query("SELECT COUNT(*) FROM profiles")
    suspend fun count(): Int

    @Query("DELETE FROM profiles")
    suspend fun deleteAll()
}