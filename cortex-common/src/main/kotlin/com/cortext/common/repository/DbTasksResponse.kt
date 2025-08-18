package com.cortext.common.repository

import com.cortext.common.models.CommonErrorModel
import com.cortext.common.models.KeyValidationException
import com.cortext.common.models.TaskModel

data class DbTasksResponse(
    override val success: Boolean,
    override val errors: List<CommonErrorModel> = emptyList(),
    override val result: List<TaskModel> = emptyList()
) : ITaskResponse<List<TaskModel>> {
    constructor(e: KeyValidationException) : this(false, errors = listOf(CommonErrorModel(e)))
    constructor(e: Exception) : this(false, listOf(CommonErrorModel(e)))
}
