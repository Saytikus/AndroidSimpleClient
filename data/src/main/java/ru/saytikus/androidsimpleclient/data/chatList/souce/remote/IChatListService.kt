package ru.saytikus.androidsimpleclient.data.chatList.souce.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.saytikus.androidsimpleclient.data.chatList.dto.ChatListItemDto

interface IChatListService {

    @GET("/api/chats")
    suspend fun getProfileChats(): Response<List<ChatListItemDto>>
}