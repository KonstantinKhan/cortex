package com.cortex.mapping

import com.cortex.transport.models.TaskDTO
import com.cortext.common.models.TaskId
import com.cortext.common.models.TaskModel
import com.cortext.common.models.TaskStatus
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun TaskDTO.toModel() = TaskModel(
    uuid = TaskId(uuid),
    label = label,
    title = title,
    description = description ?: "",
    createdAt = createdAt,
    status = TaskStatus.fromValue(status)
)