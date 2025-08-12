package com.cortext.common.repository

import com.cortext.common.models.TaskModel

data class DbTasksResponse(
    override val success: Boolean,
    override val result: List<TaskModel>
) : ITaskResponse<List<TaskModel>>
