package com.cortext.common

import com.cortext.common.models.Task
import com.cortext.common.requests.SubTaskRequest
import org.neo4j.driver.types.Node

interface ITaskRepository {
    fun tasks(): List<Node>
    fun createTask(task: Task): List<Node>
    fun createSubtask(request: SubTaskRequest): Node
    fun asyncCreateTasksBatch(tasks: List<Task>)
}