package ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.commands

data class ProfileSearchCommand(

    val q: String,

    val limit: Int
)