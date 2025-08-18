package com.cortex.task.janus.repository

import com.cortext.common.models.TaskId
import com.cortext.common.models.TaskModel
import com.cortext.common.models.TaskStatus
import com.cortext.common.repository.DbTaskRequest
import com.cortext.common.repository.DbTaskResponse
import com.cortext.common.repository.DbTasksResponse
import com.cortext.common.repository.ITaskRepository
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import java.util.Date
import java.util.UUID
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.time.toKotlinInstant

class JanusGraphTaskRepository : ITaskRepository {
    private val g: GraphTraversalSource = traversal().withRemote("conf/remote-graph.properties")

    @OptIn(ExperimentalTime::class)
    override fun tasks(): DbTasksResponse {
        val result = g.V().hasLabel("Task")
            .elementMap<Any>().toList()
            .map { vertex ->
                TaskModel(
                    uuid = vertex.taskIdProperty("uuid"),
                    label = "Task",
                    title = vertex.stringProperty("title"),
                    description = vertex.stringProperty("description"),
                    createdAt = vertex.dateProperty("createdAt"),
                    status = vertex.statusProperty("status")
                )
            }
        return DbTasksResponse(
            success = false, result = result
        )
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

fun Map<Any, Any>.stringProperty(key: String): String {
    val value =
        this[key] ?: throw IllegalArgumentException("Missing key: $key. Available keys: ${this.keys}")
    return when (value) {
        is String -> value
        else -> throw IllegalArgumentException("Value for key '$key' is not a String: $value (${value::class.simpleName})")
    }
}

fun Map<Any, Any>.taskIdProperty(key: String): TaskId {
    val value =
        this[key] ?: throw IllegalArgumentException("Missing key: $key. Available keys: ${this.keys}")
    return when (value) {
        is UUID -> TaskId(value)
        else -> throw IllegalArgumentException("Value for key '$key' is not a UUID: $value (${value::class.simpleName})")
    }
}

fun Map<Any, Any>.statusProperty(key: String): TaskStatus {
    val value =
        this[key] ?: throw IllegalArgumentException("Missing key: $key. Available keys: ${this.keys}")
    return when (value) {
        is String -> TaskStatus.fromValue(value)
        else -> throw IllegalArgumentException("Value for key '$key' is not a String: $value (${value::class.simpleName})")
    }
}

@OptIn(ExperimentalTime::class)
fun Map<Any, Any>.dateProperty(key: String): Instant {
    val value =
        this[key] ?: throw IllegalArgumentException("Missing key: $key. Available keys: ${this.keys}")
    return when (value) {
        is Date -> value.toInstant().toKotlinInstant()
        else -> throw IllegalArgumentException("Value for key '$key' is not a Date: $value (${value::class.simpleName})")
    }
}