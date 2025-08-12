package com.cortex.transport.models

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

interface Entity {
    @Required
    val id: String

    @Required
    val label: String
}

interface Node : Entity {
    val title: String
    val description: String?

    @Required
    @OptIn(ExperimentalTime::class)
    @SerialName("create_at")
    val createAt: Instant
}

interface Relation : Entity {

}