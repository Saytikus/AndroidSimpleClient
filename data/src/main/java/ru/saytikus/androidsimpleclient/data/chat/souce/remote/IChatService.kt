package ru.saytikus.androidsimpleclient.data.chat.souce.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.saytikus.androidsimpleclient.data.chat.dto.A3ChatListItemDto

interface IChatService {

    @GET("/api/chats")
    suspend fun getProfileChats(): Response<List<A3ChatListItemDto>>
}