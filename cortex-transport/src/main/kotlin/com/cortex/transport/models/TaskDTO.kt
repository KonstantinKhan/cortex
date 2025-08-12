package com.cortex.transport.models

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class TaskDTO
@OptIn(ExperimentalTime::class)
constructor(
    override val title: String,
    override val description: String?,
    @Serializable(with = InstantSerializer::class)
    @SerialName("created_at")
    override val createAt: Instant,
    @Required
    override val id: String,
    @Required
    override val label: String,
    val status: String
) : Node
