package ru.saytikus.androidsimpleclient.data.core.features.message.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.saytikus.androidsimpleclient.data.core.features.message.dto.MessagesWithCursorDto
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.RetrofitEndpoints
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface IMessageService {

    @GET(RetrofitEndpoints.GET_MESSAGES_WITH_CURSOR)
    suspend fun getMessagesWithCursor(
        @Path("chatId") chatId: Uuid,

        @Query("cursor") cursor: String?,

        @Query("limit") limit: Int
    ) : Response<MessagesWithCursorDto>
}