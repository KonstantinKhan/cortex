package com.cortex.transport.models

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

interface Entity {
    val id: String
    val label: String
}

interface Node : Entity {
    val title: String
    val description: String?

    @OptIn(ExperimentalTime::class)
    val createAt: Instant
}

interface Relation : Entity {

}