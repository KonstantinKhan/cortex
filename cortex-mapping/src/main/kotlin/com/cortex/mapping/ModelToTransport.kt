package com.cortex.mapping

import com.cortext.common.models.TaskModel
import com.cortex.transport.models.TaskDTO
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun TaskModel.toDTO() = TaskDTO(
    id = id.asString(),
    label = label,
    title = title,
    description = description,
    createAt = createdAt,
    status = status.toString()
)