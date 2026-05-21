package ru.saytikus.androidsimpleclient.domain.core.interfaces

interface IObserveInputBoundary<ReturnType> {
    operator fun invoke(): ReturnType
}