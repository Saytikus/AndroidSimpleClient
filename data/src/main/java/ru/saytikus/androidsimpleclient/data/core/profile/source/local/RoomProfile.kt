package ru.saytikus.androidsimpleclient.data.core.profile.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity("profiles")
data class RoomProfile @OptIn(ExperimentalUuidApi::class) constructor(

    @PrimaryKey
    val userId: String,

    val username: String,

    val email: String,

    val displayName: String
)