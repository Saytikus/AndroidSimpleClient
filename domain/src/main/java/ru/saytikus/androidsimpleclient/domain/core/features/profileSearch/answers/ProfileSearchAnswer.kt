package ru.saytikus.androidsimpleclient.domain.core.features.profileSearch.answers

data class ProfileSearchAnswer(

    val items: List<ProfileSearchListItem>,

    val query: String?,

    val totalCount: Int
)