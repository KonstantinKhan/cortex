package com.cortex.transport.models

import kotlinx.serialization.Serializable

@Serializable
data class Nesting(
    override val id: String,
    override val label: String
): Relation
