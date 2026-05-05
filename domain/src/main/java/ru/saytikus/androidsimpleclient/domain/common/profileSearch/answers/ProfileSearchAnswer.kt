package ru.saytikus.androidsimpleclient.domain.common.profileSearch.answers

data class ProfileSearchAnswer(

    val items: List<ProfileSearchListItem>,

    val query: String?,

    val totalCount: Int
)