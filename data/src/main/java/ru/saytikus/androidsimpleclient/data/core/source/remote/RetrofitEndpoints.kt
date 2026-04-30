package ru.saytikus.androidsimpleclient.data.core.source.remote

object RetrofitEndpoints {

    private const val ROOT = "/api"

    private const val AUTH_SUBROOT = "$ROOT/Auth"

    private const val CHAT_SUBROOT = "$ROOT/chats"



    const val LOGIN = "$AUTH_SUBROOT/login"

    const val REGISTER = "$AUTH_SUBROOT/register"


    const val CHATS = CHAT_SUBROOT

}