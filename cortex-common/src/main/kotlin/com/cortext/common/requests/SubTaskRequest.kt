package com.cortext.common.requests

import com.cortext.common.models.TaskModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class SubTaskRequest(
    val parentId: String,
    val child: TaskModel
)
