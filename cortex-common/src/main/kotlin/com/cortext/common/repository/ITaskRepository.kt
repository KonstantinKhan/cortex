package com.cortext.common.repository

import com.cortext.common.models.TaskModel

interface ITaskRepository {
    fun tasks(): DbTasksResponse
    fun createTask(request: DbTaskRequest): DbTaskResponse
    fun createSubtask(request: DbTaskRequest): DbTaskResponse
    fun asyncCreateTasksBatch(tasks: List<TaskModel>)
}