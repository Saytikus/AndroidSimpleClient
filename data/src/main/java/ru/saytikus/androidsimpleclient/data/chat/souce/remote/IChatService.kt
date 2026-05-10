package ru.saytikus.androidsimpleclient.data.chat.souce.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.saytikus.androidsimpleclient.data.chat.dto.ChatListItemDto
import ru.saytikus.androidsimpleclient.data.chat.dto.CreatePrivateChatAnswerDto
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.RetrofitEndpoints
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface IChatService {

    @GET("/api/chats")
    suspend fun getProfileChats(): Response<List<ChatListItemDto>>

    @OptIn(ExperimentalUuidApi::class)
    @POST(RetrofitEndpoints.CREATE_PRIVATE_CHAT)
    suspend fun createPrivateChat(@Path("userId") userId: Uuid): Response<CreatePrivateChatAnswerDto>
}