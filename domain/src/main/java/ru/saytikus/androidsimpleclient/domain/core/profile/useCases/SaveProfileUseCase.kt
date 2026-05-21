package ru.saytikus.androidsimpleclient.domain.core.profile.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IRepository
import ru.saytikus.androidsimpleclient.domain.core.profile.model.Profile
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class SaveProfileUseCase(

    private val profileRepo: IRepository<Uuid, Profile>

) : IInputBoundary<MbResult<Unit>, Profile> {

    override suspend fun invoke(cmd: Profile): MbResult<Unit> {
        val saveResult = profileRepo.add(cmd)

        return when(saveResult) {

            // TODO logger
            is MbResult.Failure -> {
                println(saveResult.error.error)

                saveResult
            }

            is MbResult.Success<Unit> -> {
                println("Profile successfully saved.")

                saveResult
            }
        }
    }
}