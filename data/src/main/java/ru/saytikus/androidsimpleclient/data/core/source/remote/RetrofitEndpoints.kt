package ru.saytikus.androidsimpleclient.data.core.source.remote

import kotlin.uuid.ExperimentalUuidApi

object RetrofitEndpoints {

    private const val ROOT = "/api"

    private const val AUTH_SUBROOT = "$ROOT/Auth"

    private const val CHAT_SUBROOT = "$ROOT/chats"

    private const val USERS_SUBROOT = "$ROOT/Users"



    const val LOGIN = "$AUTH_SUBROOT/login"

    const val REGISTER = "$AUTH_SUBROOT/register"


    const val CHATS = CHAT_SUBROOT

    const val SEARCH = "$USERS_SUBROOT/search"

    const val PRIVATE_CHAT = "$CHAT_SUBROOT/private"

    const val CREATE_PRIVATE_CHAT = "$PRIVATE_CHAT/{userId}"
}