package ru.saytikus.androidsimpleclient.data.core.profile.repository

import android.database.sqlite.SQLiteConstraintException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.core.profile.source.local.ProfileDao
import ru.saytikus.androidsimpleclient.data.core.profile.source.local.toDomain
import ru.saytikus.androidsimpleclient.data.core.profile.source.local.toRoom
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.profile.Profile
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IRepository
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Single
class ProfileRepository(

    private val profileDao: ProfileDao

) : IRepository<Uuid, Profile> {


    override suspend fun add(entity: Profile): MbResult<Unit> {
        try {
            profileDao.insertProfile(entity.toRoom())

        } catch (e: SQLiteConstraintException) {
            return MbResult.Failure(MbError(DomainError.RepositoryError.EntityAlreadyExists))
        }

        return MbResult.Success(Unit)
    }

    override suspend fun update(entity: Profile) {
        profileDao.updateProfile(entity.toRoom())
    }

    override suspend fun remove(entityId: Uuid) {
        profileDao.deleteProfile(entityId.toString())
    }

    override suspend fun contains(entityId: Uuid): Boolean {
        return profileDao.exists(entityId.toString())
    }

    override suspend fun find(entityId: Uuid): Profile? {
        return profileDao.find(entityId.toString())?.toDomain()
    }

    override suspend fun findAll(): Map<Uuid, Profile> {
        return profileDao.getAllProfiles().firstOrNull()?.associateBy(
            { Uuid.parse(it.userId) },
            { it.toDomain() }
        ) ?: emptyMap()
    }

    override suspend fun deleteAll() {
        profileDao.deleteAll()
    }

    override suspend fun count(): UInt {
        return profileDao.count().toUInt()
    }

    override fun observe(): Flow<Map<Uuid, Profile>> =
        profileDao.getAllProfiles()
            .map { list ->
                list.associateBy(
                    { Uuid.parse(it.userId) },
                    {it.toDomain()}
                )
            }
            .flowOn(Dispatchers.IO)


}