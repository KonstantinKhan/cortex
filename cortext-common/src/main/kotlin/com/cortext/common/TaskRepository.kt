package com.cortext.common

import com.cortext.common.models.Task
import org.neo4j.driver.types.Node

interface TaskRepository {
    fun tasks(): List<Node>
    fun createTask(task: Task)
    fun createSubtask(parentId: String, child: Task)
    fun asyncCreateTasksBatch(tasks: List<Task>)
}