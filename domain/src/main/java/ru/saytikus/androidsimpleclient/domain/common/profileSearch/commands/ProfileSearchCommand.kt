package ru.saytikus.androidsimpleclient.domain.common.profileSearch.commands

data class ProfileSearchCommand(

    val q: String,

    val limit: Int
)