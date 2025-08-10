package com.cortex.app

import com.cortex.app.config.AppKtorConfig
import com.cortex.mapping.toTask
import com.cortext.common.models.Task
import com.cortext.common.models.TaskStatus
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import java.time.Instant

suspend fun ApplicationCall.createTask(config: AppKtorConfig) {
    println("start")
    var request: Task =
        Task(id = "", title = "", description = "", createdAt = Instant.now(), status = TaskStatus.UNKNOWN)
    try {
        request = receive<Task>() as Task
    } catch (e: Exception) {
        println(e.message)
    }

    println("request: $request")
    val response = config.taskRepository.createTask(request).map { it.toTask() }
    respond(response)
}