package com.cortext.common.repository

import com.cortext.common.models.TaskModel

interface ITaskRepository {
    fun tasks(): DbTasksResponse
    fun createTask(request: DbTaskRequest): DbTaskResponse
    fun createSubtask(request: DbTaskRequest): DbTaskResponse
    fun asyncCreateTasksBatch(tasks: List<TaskModel>)

    object NONE: ITaskRepository {
        override fun tasks(): DbTasksResponse {
            TODO("Not yet implemented")
        }

        override fun createTask(request: DbTaskRequest): DbTaskResponse {
            TODO("Not yet implemented")
        }

        override fun createSubtask(request: DbTaskRequest): DbTaskResponse {
            TODO("Not yet implemented")
        }

        override fun asyncCreateTasksBatch(tasks: List<TaskModel>) {
            TODO("Not yet implemented")
        }

    }
}