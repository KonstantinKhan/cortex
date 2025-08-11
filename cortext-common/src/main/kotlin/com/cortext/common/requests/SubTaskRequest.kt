package com.cortext.common.requests

import com.cortext.common.models.Task
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubTaskRequest(
    @SerialName("parent_id")
    val parentId: String,
    @SerialName("child")
    val child: Task
)
