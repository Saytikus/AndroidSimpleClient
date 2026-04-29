package ru.saytikus.androidsimpleclient.domain.common.interfaces

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult

interface ISingleObjectRepository<EntityType> {

    suspend fun update(entity: EntityType): MbResult<Unit>

    suspend fun getOnce(): EntityType

    suspend fun reset()

    fun observe(): Flow<EntityType>
}