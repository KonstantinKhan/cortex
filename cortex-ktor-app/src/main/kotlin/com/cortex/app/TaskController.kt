package com.cortex.app

import com.cortex.app.config.AppKtorConfig
import com.cortex.mapping.toDTO
import com.cortex.mapping.toModel
import com.cortex.transport.models.TaskRequest
import com.cortext.common.models.TaskId
import com.cortext.common.repository.DbTaskRequest
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
suspend fun ApplicationCall.createTask(config: AppKtorConfig) {
    val request: TaskRequest = try {
        receive<TaskRequest>()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
    val response = config.taskRepository.createTask(DbTaskRequest(request.task.toModel()))
    respond(response.result.toDTO())
}

suspend fun ApplicationCall.createSubTask(config: AppKtorConfig) {
    val request = try {
        receive<TaskRequest>()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
    val response = config.taskRepository
        .createSubtask(
            DbTaskRequest(
                request.task.toModel(),
                TaskId(request.relatedTaskId ?: ""),
            )
        )
    respond(response.result.toDTO())
}