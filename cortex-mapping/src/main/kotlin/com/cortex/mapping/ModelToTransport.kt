package com.cortex.mapping

import com.cortex.transport.models.ErrorDTO
import com.cortext.common.models.TaskModel
import com.cortex.transport.models.TaskDTO
import com.cortext.common.models.CommonErrorModel
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun TaskModel.toDTO() = TaskDTO(
    uuid = uuid.asString(),
    label = label,
    title = title,
    description = description,
    createdAt = createdAt,
    status = status.toString()
)

fun CommonErrorModel.toDTO(): ErrorDTO = ErrorDTO(
    message = message,
    field = field
)