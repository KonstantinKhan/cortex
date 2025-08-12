package com.cortex.transport.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskRequest(
    val task: TaskDTO,
    @SerialName("related_task_id")
    val relatedTaskId: String?
)
