package com.cortext.common.repository

import com.cortext.common.models.TaskId
import com.cortext.common.models.TaskModel

data class DbTaskRequest(
    val task: TaskModel,
    val relatedTaskId: TaskId = TaskId.NONE,
)
