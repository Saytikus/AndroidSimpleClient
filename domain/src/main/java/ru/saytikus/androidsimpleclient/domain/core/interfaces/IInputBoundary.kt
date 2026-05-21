package ru.saytikus.androidsimpleclient.domain.core.interfaces

interface IInputBoundary<ReturnType, CommandType> {
    suspend operator fun invoke(cmd: CommandType): ReturnType
}