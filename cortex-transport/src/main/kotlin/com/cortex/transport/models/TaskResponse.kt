package com.cortex.transport.models

import kotlinx.serialization.Serializable

@Serializable
data class TaskResponse(
    val result: List<TaskDTO>,
    val errors: List<ErrorDTO>
)
