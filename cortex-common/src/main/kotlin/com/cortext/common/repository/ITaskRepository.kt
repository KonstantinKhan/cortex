package com.cortext.common.repository

import com.cortext.common.models.TaskModel
import com.cortext.common.requests.SubTaskRequest
import org.neo4j.driver.types.Node

interface ITaskRepository {
    fun tasks(): DbTasksResponse
    fun createTask(request: DbTaskRequest): DbTaskResponse
    fun createSubtask(request: SubTaskRequest): Node
    fun asyncCreateTasksBatch(tasks: List<TaskModel>)
}