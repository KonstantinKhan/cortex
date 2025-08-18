package com.cortex.transport.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDTO(
    val message: String,
    val field: String = ""
)
