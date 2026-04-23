package ru.saytikus.androidsimpleclient.domain.registration.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.entities.Profile
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IRepository
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