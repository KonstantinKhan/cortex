package com.cortex.transport.models

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

interface Entity {
    val uuid: String
    val label: String
}

interface Node : Entity {
    val title: String
    val description: String?

    @OptIn(ExperimentalTime::class)
    val createdAt: Instant
}

interface Relation : Entity {

}