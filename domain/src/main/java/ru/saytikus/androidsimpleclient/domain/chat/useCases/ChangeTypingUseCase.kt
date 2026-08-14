package ru.saytikus.androidsimpleclient.domain.chat.useCases

import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.dto.ChangeTypingCommand
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary

class ChangeTypingUseCase(

    private val chatGateway: IChatGateway

) : IInputBoundary<MbResult<Unit>, ChangeTypingCommand> {

    override suspend fun invoke(cmd: ChangeTypingCommand): MbResult<Unit> {
        return chatGateway.changeTyping(cmd)
    }
}