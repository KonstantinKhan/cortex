package com.cortex.app

import com.cortex.app.config.AppKtorConfig
import com.cortex.mapping.toTask
import com.cortext.common.models.Task
import com.cortext.common.models.TaskStatus
import com.cortext.common.requests.SubTaskRequest
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import java.time.Instant

suspend fun ApplicationCall.createTask(config: AppKtorConfig) {
    var request: Task =
        Task(id = "", title = "", description = "", createdAt = Instant.now(), status = TaskStatus.UNKNOWN)
    try {
        request = receive<Task>() as Task
    } catch (e: Exception) {
        println(e.message)
    }
    val response = config.taskRepository.createTask(request).map { it.toTask() }
    respond(response)
}

suspend fun ApplicationCall.createSubTask(config: AppKtorConfig) = try {
    val request = receive<SubTaskRequest>()
    val response = config.taskRepository
        .createSubtask(
            SubTaskRequest(
                request.parentId,
                request.child
            )
        ).toTask()
    respond(response)
} catch (e: Exception) {
    throw RuntimeException(e.message)
}