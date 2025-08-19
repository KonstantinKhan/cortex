package com.cortext.common.repository

import com.cortext.common.models.CommonErrorModel
import com.cortext.common.models.TaskModel


data class DbTaskResponse(
    override val success: Boolean,
    override val errors: List<CommonErrorModel> = emptyList(),
    override val result: TaskModel = TaskModel.NONE
): ITaskResponse<TaskModel> {
    constructor(e: Exception) : this(false, listOf(CommonErrorModel(e)))
}

