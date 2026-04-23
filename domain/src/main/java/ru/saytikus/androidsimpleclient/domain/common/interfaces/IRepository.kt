package ru.saytikus.androidsimpleclient.domain.common.interfaces

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult

interface IRepository<EntityKey, EntityType> {

    suspend fun add(entity: EntityType): MbResult<Unit>

    suspend fun update(entity: EntityType)

    suspend fun remove(entityId: EntityKey)

    suspend fun contains(entityId: EntityKey): Boolean

    suspend fun find(entityId: EntityKey): EntityType?

    suspend fun findAll(): Map<EntityKey, EntityType>

    suspend fun deleteAll()

    suspend fun count(): UInt

    fun observe(): Flow<Map<EntityKey, EntityType>>
}