package ru.saytikus.androidsimpleclient.domain.core.features.profile.useCases

import ru.saytikus.androidsimpleclient.domain.core.features.profile.model.Profile
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class GetSavedProfilesUseCase @OptIn(ExperimentalUuidApi::class) constructor(

    private val profileRepo: IRepository<Uuid, Profile>

) : IInputBoundary<List<Profile>, Unit> {

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun invoke(cmd: Unit): List<Profile> {
        return profileRepo.findAll().values.toList()
    }
}