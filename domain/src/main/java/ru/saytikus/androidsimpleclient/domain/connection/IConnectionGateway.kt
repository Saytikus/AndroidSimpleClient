package ru.saytikus.androidsimpleclient.domain.connection

import kotlinx.coroutines.flow.Flow

interface IConnectionGateway {

    val connectionState: Flow<ConnectionState>

    suspend fun connect()

    suspend fun disconnect()
}