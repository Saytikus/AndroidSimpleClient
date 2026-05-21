package ru.saytikus.androidsimpleclient.domain.core.interfaces

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult

interface ISingleObjectRepository<EntityType> {

    suspend fun update(entity: EntityType): MbResult<Unit>

    suspend fun getOnce(): EntityType

    suspend fun reset()

    fun observe(): Flow<EntityType>
}