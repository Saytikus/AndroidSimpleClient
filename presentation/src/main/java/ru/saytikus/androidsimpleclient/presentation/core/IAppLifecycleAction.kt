package ru.saytikus.androidsimpleclient.presentation.core

interface IAppLifecycleAction {

    val name: String

    suspend fun onAppResume() {}

    suspend fun onAppPause() {}
}