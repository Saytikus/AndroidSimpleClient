package ru.saytikus.androidsimpleclient.domain.common.interfaces

interface IInputBoundary<ReturnType, CommandType> {
    suspend operator fun invoke(cmd: CommandType): ReturnType
}