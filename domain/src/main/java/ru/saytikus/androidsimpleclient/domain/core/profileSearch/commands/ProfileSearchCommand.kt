package ru.saytikus.androidsimpleclient.domain.core.profileSearch.commands

data class ProfileSearchCommand(

    val q: String,

    val limit: Int
)