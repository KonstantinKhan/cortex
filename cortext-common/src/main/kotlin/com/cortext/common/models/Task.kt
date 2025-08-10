package com.cortext.common.models

import com.cortext.common.serializer.InstantSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable()
data class Task(
    val id: String,
    val title: String,
    val description: String,
    @Serializable(with = InstantSerializer::class)
    @SerialName("created_at")
    val createdAt: Instant,
    val status: TaskStatus
)