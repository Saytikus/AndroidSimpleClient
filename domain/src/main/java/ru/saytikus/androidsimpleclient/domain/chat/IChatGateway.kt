package ru.saytikus.androidsimpleclient.domain.chat

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult

interface IChatGateway {

    suspend fun getProfileChats(): MbResult<List<Chat>>
}