package ru.saytikus.androidsimpleclient.data.chat.souce.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.saytikus.androidsimpleclient.data.chat.dto.A3ChatListItemDto
import ru.saytikus.androidsimpleclient.domain.chat.answers.A3ChatListItem

interface IChatService {

    @GET("/api/chats")
    suspend fun getProfileChats(): Response<List<A3ChatListItemDto>>
}