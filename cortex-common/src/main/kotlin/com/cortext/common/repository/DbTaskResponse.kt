package com.cortext.common.repository

import com.cortext.common.models.TaskModel

data class DbTaskResponse(
    override val success: Boolean,
    override val result: TaskModel
): ITaskResponse<TaskModel>
