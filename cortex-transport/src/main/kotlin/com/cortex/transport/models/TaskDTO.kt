package com.cortex.transport.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class Task @OptIn(ExperimentalTime::class) constructor(
    override val title: String,
    override val description: String?,
    @Contextual
    override val createAt: Instant,
    override val id: String,
    override val label: String
) : Node
