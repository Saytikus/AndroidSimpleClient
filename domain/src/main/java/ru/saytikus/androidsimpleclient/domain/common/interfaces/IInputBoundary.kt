package ru.saytikus.androidsimpleclient.domain.common.interfaces

interface IInputBoundary<ReturnType, CommandType> {
    suspend fun invoke(cmd: CommandType): ReturnType
}